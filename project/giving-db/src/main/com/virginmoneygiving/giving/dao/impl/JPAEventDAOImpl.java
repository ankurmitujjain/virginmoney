package com.virginmoneygiving.giving.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.dao.EventDAO;
import com.virginmoneygiving.giving.domain.Address;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.BankAccountType;
import com.virginmoneygiving.giving.domain.CharitableActivity;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAdminDetailsVO;
import com.virginmoneygiving.giving.domain.CharityAdministrator;
import com.virginmoneygiving.giving.domain.CharityEventAdministrator;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.EventActivity;
import com.virginmoneygiving.giving.domain.EventFeeDetails;
import com.virginmoneygiving.giving.domain.EventFeeDetailsVO;
import com.virginmoneygiving.giving.domain.EventHomepageDetailsDVO;
import com.virginmoneygiving.giving.domain.EventStatus;
import com.virginmoneygiving.giving.domain.FundRaiserDVO;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.giving.domain.FundraisingInfoDVO;
import com.virginmoneygiving.giving.domain.Location;
import com.virginmoneygiving.giving.domain.OwnOrganisedEventsDVO;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventParameter;

/**
 * DAO for Events functionality.
 *
 * @author Saurabh Herwadkar
 * @author Siva Kumar - fetchOwnedOrganisedEvents,
 * saveCharitableActivityDetails, saveEventFeeDetails,
 * saveEventActivityDeatils, saveCharityEventAdministrator,
 * deleteEventActivitiesForEvent , fetchCharityAdministrators ,
 * fetchBankAccountDetails
 */
@Repository
public class JPAEventDAOImpl implements EventDAO {

	private static final long ONE_HOUR = 60 * 60 * 1000L;

	/** The number of fundraiser pages to be retreived */
	private static final int FUNDRAISER_PAGE_LIMIT = 9;
    /**
     * Constructor to initialize class.
     */
    public JPAEventDAOImpl() {
    	 if (LOGGER.isInfoEnabled()) {
    		 LOGGER.info("Initializing JPAEventDAOImpl.");
    	 }
    }

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(JPAEventDAOImpl.class);

    /** An Entity Manager Instance. */
    private EntityManager entityManager;

    /**
     * Dependency injection of JPA EntityManager.
     *
     * @param entityManager an entity manger.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
    	if (LOGGER.isTraceEnabled()) {
    		LOGGER.trace("JPA Entity Manager injected");
    	}
        this.entityManager = entityManager;
    }

    public FundraisingInfoDVO fetchTheLatestFundraisers(final Long eventId, final Long charityId){
        
    	if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("INPUT : eventId = " + eventId + " charityId = "
                + charityId);
        }
		List<FundRaiserDVO> fundRaiserDVOList = new ArrayList<FundRaiserDVO>();
		FundraisingInfoDVO fundraisingInfoDVO = new FundraisingInfoDVO();
		FundRaiserDVO fundRaiserDVO = null;

		if (eventId != null && charityId != null) {

			final Query fetchLimitedPages =
                entityManager.createNamedQuery("fetchLimitedPagesBasedOnEventId");
			fetchLimitedPages.setParameter("eventId", eventId);
			fetchLimitedPages.setMaxResults(FUNDRAISER_PAGE_LIMIT);
			
			List<Page> pageList = fetchLimitedPages.getResultList();
						 
			if (pageList != null) {
				fundraisingInfoDVO.setFundraiserCount(pageList.size());
			}

			// If size>9 get subset of 9			 
			if (pageList != null && pageList.size() > 0) {				
				LOGGER.debug("Page list size " + pageList.size());
				for (Page page : pageList) {
					fundRaiserDVO = new FundRaiserDVO();
					setFundRaiserRelevantDetails(fundRaiserDVO, page, eventId);
					fundRaiserDVOList.add(fundRaiserDVO);
				}
			}
			//Populate DVO contents
			fundraisingInfoDVO.setList(fundRaiserDVOList);
		}
		return fundraisingInfoDVO;    	
    }
    
    
    /** {@inheritDoc} */
	public FundraisingInfoDVO fetchNineRandomFundraisers(final Long eventId,
			final Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchNineRandomFundraisers() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("INPUT : eventId = " + eventId + " charityId = "
                + charityId);
        }
		List<FundRaiserDVO> fundRaiserDVOList = new ArrayList<FundRaiserDVO>();
		FundraisingInfoDVO fundraisingInfoDVO = new FundraisingInfoDVO();
		FundRaiserDVO fundRaiserDVO = null;

		if (eventId != null && charityId != null) {

			List<Page> pageList = entityManager.createNamedQuery(
					"fetchPagesBasedOnEventId")
					.setParameter("eventId", eventId).getResultList();
			/**
			 * Shuffle the list for randomization
			 */
			Collections.shuffle(pageList);
			/**
			 * Set the size of list before we take sublist of String
			 */
			if (pageList != null) {
				fundraisingInfoDVO.setFundraiserCount(pageList.size());
			}

			/**
			 * If size>9 get subset of 9
			 */
			if (pageList != null && pageList.size() > 0) {

				for (Page page : pageList) {
					fundRaiserDVO = new FundRaiserDVO();
					setFundRaiserRelevantDetails(fundRaiserDVO, page, eventId);
					fundRaiserDVOList.add(fundRaiserDVO);

				}

			}

			/**
			 * Populate DVO contents
			 */
			fundraisingInfoDVO.setList(fundRaiserDVOList);
			/**
			 * Return
			 */
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchNineRandomFundraisers() method - END");
        }
		return fundraisingInfoDVO;

	}

    /**
     * Set fundraiser details.
     *
     * @param fundRaiserDVO the fundraiser data value object.
     * @param page the page
     * @param eventId the event id
     */
	private void setFundRaiserRelevantDetails(FundRaiserDVO fundRaiserDVO,
			Page page, Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::setFundRaiserRelevantDetails() method - START");
        }
		String imageUrl = "";
		String urlPart1 = "";
		String urlPart2 = "";
		String url = MasterDataCodeConstants.FUNDRAISING_PAGE_HASH;

		if (page != null && page.getUrl() != null) {

			urlPart2 = page.getUrl();

		}

		if (page != null && page.getFundraiserActivity() != null) {

			FundraiserActivity fundraiserActivity = page
					.getFundraiserActivity();

			if (fundraiserActivity.getFundraiserImageUrl() != null) {

				imageUrl = fundraiserActivity.getFundraiserImageUrl();

			}

			if (fundraiserActivity.getFundraiser() != null
					&& fundraiserActivity.getFundraiser().getUrlDetails() != null
					&& fundraiserActivity.getFundraiser().getUrlDetails()
							.getUrl() != null) {

				urlPart1 = page.getFundraiserActivity().getFundraiser()
						.getUrlDetails().getUrl();

			}

		}

		if (urlPart1 != null && urlPart2 != null
				&& StringUtils.isNotEmpty(urlPart1)
				&& StringUtils.isNotEmpty(urlPart2)) {

			url = MasterDataCodeConstants.FUNDRAISING_PAGE_PARAM1 + urlPart1
					+ "&" + MasterDataCodeConstants.FUNDRAISING_PAGE_PARAM2
					+ urlPart2;

		}
		
		String forename = "";
		try {
			forename = page.getFundraiser().getUserRole().getUser().getPerson().getForename();
		} catch(Exception e) {
			LOGGER.debug("Unable to retrieve forename for fundraiser.");
			LOGGER.debug(e);
		}

		/**
		 * Populate details in DVO
		 */
		fundRaiserDVO.setForeName(forename);
		fundRaiserDVO.setImageUrl(imageUrl);
		fundRaiserDVO.setHomePageLink(url);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::setFundRaiserRelevantDetails() method - END");
        }
	}





    /**
     * Private method to map a event to its DVO.
     *
     * @param event the event
     * @param eventHomepageDetailsDVO eventHomepageDetailsDVO, Event event
     */
    private void populateRelevantEventtDetails(Event event,
            EventHomepageDetailsDVO eventHomepageDetailsDVO) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::populateRelevantEventtDetails() method - START");
        }
        /**
         * Populate basic details information
         */
        eventHomepageDetailsDVO.setEventName(event.getName());
        if (event.getLocation() != null) {
            eventHomepageDetailsDVO.setEventLocation(event.getLocation()
                    .getDescription());
        }
        eventHomepageDetailsDVO.setEventDate(event.getEventDatetime());
        eventHomepageDetailsDVO.setEventTime(event.getEventTimeText());
        eventHomepageDetailsDVO.setDescription(event.getDescription());
        eventHomepageDetailsDVO.setContactName(event.getContactName());
        eventHomepageDetailsDVO.setContactPhone(event.getContactPhone());
        eventHomepageDetailsDVO.setContactEmail(event.getContactEmailAddress());
        if (event.getContactAddress() != null) {
            eventHomepageDetailsDVO.setTownCity(event.getContactAddress()
                    .getTownCity());
            eventHomepageDetailsDVO.setBuildingName(event.getContactAddress()
                    .getBuildingName());
            if (event.getContactAddress().getBuildingNumber() == null
                    || event.getContactAddress().getBuildingNumber()
                            .equalsIgnoreCase("null")) {
                eventHomepageDetailsDVO.setBuildingNumber("");
            }
            else {

                eventHomepageDetailsDVO.setBuildingNumber(event
                        .getContactAddress().getBuildingNumber());
            }

            eventHomepageDetailsDVO.setPostCode(event.getContactAddress()
                    .getPostCode());
            eventHomepageDetailsDVO.setAddressLine1(event.getContactAddress()
                    .getAddressLine1());
            eventHomepageDetailsDVO.setAddressLine2(event.getContactAddress()
                    .getAddressLine2());
        }

        eventHomepageDetailsDVO.setJoiningInstructions(event
                .getJoiningInstructions());
        eventHomepageDetailsDVO.setWebsiteUrl(event.getWebsiteUrl());
        eventHomepageDetailsDVO.setEventLogo(event.getLogoUrl());
        eventHomepageDetailsDVO.setOperationEventInd(event
                .getOperationalEventInd());
        eventHomepageDetailsDVO
                .setEventStatus(event.getEventStatus().getCode());
        /**
         * Calculate number of days before event goes live
         */
        long diff = rangeBetweenDates(event.getEventDatetime().getTime());
        eventHomepageDetailsDVO.setDaysToGo(String.valueOf(diff));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::populateRelevantEventtDetails() method - END");
        }
    }

    /** {@inheritDoc} */
    public EventHomepageDetailsDVO getEventDetails(final Long eventId,
            final Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::getEventDetails() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("INPUT : eventId = " + eventId + " charityId = "
                + charityId);
        }
        EventHomepageDetailsDVO eventHomepageDetailsDVO =
                new EventHomepageDetailsDVO();
        if (eventId != null) {
            /**
             * Fetch the event details
             */
            Event event =
                    (Event) (entityManager
                            .createNamedQuery("fetchEventDetails")
                            .setParameter("eventId", eventId).getSingleResult());

            /**
             * Populate DVO
             */
            populateRelevantEventtDetails(event, eventHomepageDetailsDVO);

			if (event != null && event.getMaximumCharities() != null
					&& event.getMaximumCharities() == 1) {

				eventHomepageDetailsDVO
						.setSingleCharityEvent(MasterDataCodeConstants.YES);

			} else if (event != null && event.getMaximumCharities() == null
					&& event.getCharitableActivities() != null
					&& event.getCharitableActivities().size() == 1) {

				CharitableActivity charitableActivity = event
						.getCharitableActivities().iterator().next();
				if (MasterDataCodeConstants.YES
						.equalsIgnoreCase(charitableActivity
								.getVmgManageFeeInd())) {

					eventHomepageDetailsDVO
							.setSingleCharityEvent(MasterDataCodeConstants.YES);

				}

			} else {
				eventHomepageDetailsDVO
						.setSingleCharityEvent(MasterDataCodeConstants.NO);

			}
		}
        
        
       
        

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::getEventDetails() method - END");
        }
        return eventHomepageDetailsDVO;
    } // end of method

    /** {@inheritDoc} */
    public String fetchCharityLogo(final Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityLogo() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("INPUT : charityId = " + charityId);
        }
        Charity charity =
                (Charity) (entityManager.createNamedQuery("fetchCharityLogo")
                        .setParameter("charityId", charityId).getSingleResult());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityLogo() method - END");
        }
        return charity.getLogoUrl();

    }

    /**
     * Private method to calculate number of days.
     *
     * @param eventStartDate the event start date
     *
     * @return long difference
     */
    private long rangeBetweenDates(final long eventStartDate) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::rangeBetweenDates() method - START");
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::rangeBetweenDates() method - END");
        }
        return ((eventStartDate - new java.util.Date().getTime() + ONE_HOUR) / (ONE_HOUR * 24));

    }

    /** {@inheritDoc} */
    public List<Event> fetchOtherOrganisedEvents(
            SearchFundraisingEventParameter searchEventParameters) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOtherOrganisedEvents() method - START");
        }

        StringBuffer queryString = new StringBuffer("Select DISTINCT e ");
        queryString
                .append("From Event e, Location l, EventActivity ea, ActivityType at ");
        queryString.append("Where e.id = ea.event.id ");
        queryString
                .append(" and ea.activityType.code = at.code and e.location.isoCode = l.isoCode ");
        queryString.append(" and e.eventStatus.code = 'PUB' ");
        if (searchEventParameters.getCharityId() != null) {
            queryString.append(" and  ((e.operationalEventInd = 'Y' ");
            queryString.append(" and e.id not in ( ");
            queryString.append(" select ca.event.id from ");
            queryString.append(" CharitableActivity ca where ca.charity.id = :charityId ");
            queryString.append(")) OR ");
            queryString.append(" (e.operationalEventInd = 'N' ");
            queryString.append(" and e.id not in ( ");
            queryString.append(" select ca.event.id from ");
            queryString
                    .append(" CharitableActivity ca where ca.vmgManageFeeInd = 'Y') ");
            queryString.append(" and e.id not in ( ");
            queryString.append(" select ca.event.id from ");
            queryString.append(" CharitableActivity ca where ca.charity.id = :charityId ")
            .append(") And e.maximumCharities is null)) ");
        }

        if (StringUtils.isNotBlank(searchEventParameters.getEventname())) {
            queryString.append("And (e.name like :eventName ")
            .append(" or e.description like :eventName) ");
        }

        if (StringUtils.isNotBlank(searchEventParameters.getEventType())) {
            queryString.append("And at.code = :activityType ");
        }

        if (StringUtils.isNotBlank(searchEventParameters.getLocation())) {
            queryString.append("And l.isoCode = :locationCode ");
        }

        if (StringUtils.isNotBlank(searchEventParameters.getEventDate())) {
            queryString.append("And  e.eventDatetime between :startDate and :endDate ");
        }

        final Query searchOtherOrganisedEvents =
                entityManager.createQuery(queryString.toString());

        if (searchEventParameters.getCharityId() != null) {
            Long charityId = searchEventParameters.getCharityId();
            searchOtherOrganisedEvents.setParameter("charityId", charityId);
        }

        if (StringUtils.isNotBlank(searchEventParameters.getEventname())) {
            String eventName = searchEventParameters.getEventname();
            eventName = ("%").concat(eventName).concat(("%"));
            searchOtherOrganisedEvents.setParameter("eventName", eventName);
        }

        if (StringUtils.isNotBlank(searchEventParameters.getEventType())) {
            String activityType = searchEventParameters.getEventType();
            searchOtherOrganisedEvents.setParameter("activityType", activityType);
        }

        if (StringUtils.isNotBlank(searchEventParameters.getLocation())) {
            String locationCode = searchEventParameters.getLocation();
            searchOtherOrganisedEvents.setParameter("locationCode", locationCode);
        }

        if (StringUtils.isNotBlank(searchEventParameters.getEventDate())) {
            Calendar calendar = new GregorianCalendar();
            Timestamp startDate = null;
            Timestamp endDate = null;
            String requestDate = searchEventParameters.getEventDate();
            StringTokenizer requestDateStringTokenizer = new StringTokenizer(requestDate, " ");
            int requestDateMonth = Integer.parseInt(requestDateStringTokenizer.nextToken()) - 1;
            int requestDateYear = Integer.parseInt(requestDateStringTokenizer.nextToken());
            int requestDateDay = 1;

            /*
             * Check if the selected month and year is same as current, if true
             * then use current date and if future month then use the first day
             * i.e. 1
             */
            if (requestDateYear == calendar.get(Calendar.YEAR)
                    && requestDateMonth == calendar.get(Calendar.MONTH)) {
                requestDateDay = calendar.get(Calendar.DATE);
            }
            calendar = new GregorianCalendar(requestDateYear, requestDateMonth, requestDateDay);
            startDate = new Timestamp(calendar.getTimeInMillis());
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, maxDay);
            endDate = new Timestamp(calendar.getTimeInMillis());
            searchOtherOrganisedEvents.setParameter("startDate", startDate);
            searchOtherOrganisedEvents.setParameter("endDate", endDate);
        }

        final List<Event> searchOtherOrganisedEventList =
                (List<Event>) searchOtherOrganisedEvents.getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOtherOrganisedEvents() method - END");
        }
        return searchOtherOrganisedEventList;
    }

    /** {@inheritDoc} */
    public List<OwnOrganisedEventsDVO> fetchOperationalOrganisedEvents(
            final List<String> statusList, final Long userId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOperationalOrganisedEvents() method - START");
        }
        List<Event> searchOwnedOrganisedEventList = new ArrayList();
        List<OwnOrganisedEventsDVO> ownedEventsList =
                new ArrayList<OwnOrganisedEventsDVO>();

        if (statusList != null && statusList.size() > 0) {
            searchOwnedOrganisedEventList =
                    (List<Event>) entityManager.createNamedQuery(
                            "fetchOperationalOrganisedEvents").setParameter(
                            "selectedStatus", statusList).getResultList();
            /*
             * Populate DVO with Event details
             */
            for (Event event : searchOwnedOrganisedEventList) {
                OwnOrganisedEventsDVO organisedEventsDVO =
                        new OwnOrganisedEventsDVO();
                populateOwnedOrganisedEventDetails(organisedEventsDVO, event);
                ownedEventsList.add(organisedEventsDVO);
            } // for
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOperationalOrganisedEvents() method - END");
        }
        return ownedEventsList;
    }

    /** {@inheritDoc} */
    public List<OwnOrganisedEventsDVO> fetchOwnedOrganisedEvents(
            final List<String> statusList, final Long charityId,
            final Long userId) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOwnedOrganisedEvents() method - START");
        }
        List<Event> searchOwnedOrganisedEventList = new ArrayList<Event>();
        List<OwnOrganisedEventsDVO> ownedEventsList =
                new ArrayList<OwnOrganisedEventsDVO>();

        if (statusList != null && charityId != null) {

            /*
             * If Charity user is fetching own event records then he can only
             * see those records for which he is authorised as administrator.
             */
            if (userId != null && userId.longValue() > 0) {
                searchOwnedOrganisedEventList =
                        (List<Event>) entityManager.createNamedQuery(
                                "searchOwnAuthOrganisedEvents").setParameter(
                                "charityId", charityId).setParameter(
                                "selectedStatus", statusList).setParameter(
                                "userId", userId).getResultList();
            }
            else {
                searchOwnedOrganisedEventList =
                        (List<Event>) entityManager.createNamedQuery(
                                "searchOwnedOrganisedEvents").setParameter(
                                "charityId", charityId).setParameter(
                                "selectedStatus", statusList).getResultList();
            }

            OwnOrganisedEventsDVO organisedEventsDVO;
            EventStatus eventStatus;
            /**
             * Populate DVO with Event details
             */
            for (Event event : searchOwnedOrganisedEventList) {

                organisedEventsDVO = new OwnOrganisedEventsDVO();

                /**
                 * Populate
                 */
                populateOwnedOrganisedEventDetails(organisedEventsDVO, event);

                /**
                 * Finally add DVO to list
                 */
                ownedEventsList.add(organisedEventsDVO);

            } // for

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOwnedOrganisedEvents() method - END");
        }
        /**
         * return
         */
        return ownedEventsList;

    }

    /**
     * Method to populate OrgnaisedEventDVO from Event.
     *
     * @param organisedEventsDVO orgnaisedEventDVO
     * @param event event
     */
    private void populateOwnedOrganisedEventDetails(
            OwnOrganisedEventsDVO organisedEventsDVO, Event event) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::populateOwnedOrganisedEventDetails() method - START");
        }
        /**
         * Set basic details
         */
        organisedEventsDVO.setEventId(event.getId());
        organisedEventsDVO.setEventDateTime(event.getEventDatetime());
        organisedEventsDVO.setEventTimeText(event.getEventTimeText());
        organisedEventsDVO.setName(event.getName());
        organisedEventsDVO.setLogoUrl(event.getLogoUrl());
        /**
         * Set location
         */
        Location location = new Location();
        if (event.getLocation() != null) {
            location.setDescription(event.getLocation().getDescription());
        }
        organisedEventsDVO.setLocation(location);
        /**
         * Set event status
         */
        EventStatus eventStatus = new EventStatus();
        eventStatus.setCode(event.getEventStatus().getCode());
        eventStatus.setDescription(event.getEventStatus().getDescription());
        organisedEventsDVO.setEventStatus(eventStatus);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::populateOwnedOrganisedEventDetails() method - END");
        }
    }

    // TODO Consolidate methods to fetch all events and four latest events with
    // better logic
    /** {@inheritDoc} */
    public List<OwnOrganisedEventsDVO> fetchOwnedFourLatestOrganisedEvents(
            final List<String> statusList, final Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOwnedFourLatestOrganisedEvents() method - START");
        }
        List<OwnOrganisedEventsDVO> ownedEventsLatestList =
                new ArrayList<OwnOrganisedEventsDVO>();

        if (charityId != null) {

            List<String> latestList = new ArrayList<String>();
            latestList.add("PUB"); // TODO Write better logic to hard code
                                   // published status

            List<Event> searchOwnedOrganisedEventList =
                    (List<Event>) entityManager.createNamedQuery(
                            "searchOwnedFourLatestOrganisedEvents")
                            .setParameter("charityId", charityId).setParameter(
                                    "selectedStatus", latestList)
                            .setMaxResults(4).getResultList();

            LOGGER.trace("After fetching event details by given input");

            OwnOrganisedEventsDVO organisedEventsDVO;

            for (Event event : searchOwnedOrganisedEventList) {

                organisedEventsDVO = new OwnOrganisedEventsDVO();

                /**
                 * Populate
                 */
                populateOwnedOrganisedEventDetails(organisedEventsDVO, event);

                /**
                 * Finally add DVO to list
                 */
                ownedEventsLatestList.add(organisedEventsDVO);

            } // for

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOwnedFourLatestOrganisedEvents() method - END");
        }
        /**
         * Return list
         */
        return ownedEventsLatestList;

    }

    /** {@inheritDoc} */
    public List<CharityAdminDetailsVO> fetchCharityEventAdminList(
            Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityEventAdminList() method - START");
        }

        final String namedQuery = "fetchAllCharityEventAdminList";
        final Query fetchAllCharityEventAdminQuery =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "charitableActivityId", charitableActivityId);

        List<Object[]> resultSetList =
                fetchAllCharityEventAdminQuery.getResultList();
        final List<CharityAdminDetailsVO> charityEventAdminList =
                new ArrayList<CharityAdminDetailsVO>();

        for (Object[] objArray : resultSetList) {
            CharityAdminDetailsVO charityAdminDetailsVO =
                    new CharityAdminDetailsVO();
            charityAdminDetailsVO.setCharityAdminId((Long) objArray[0]);
            charityAdminDetailsVO.setTitle((String) objArray[1]);
            charityAdminDetailsVO.setForeName((String) objArray[2]);
            charityAdminDetailsVO.setSurname((String) objArray[3]);
            charityAdminDetailsVO.setEmailAddress((String) objArray[4]);
            charityEventAdminList.add(charityAdminDetailsVO);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityEventAdminList() method - END");
        }
        return charityEventAdminList;
    }

    /** {@inheritDoc} */
    public List<CharityAdminDetailsVO> fetchCharityAdminList(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityAdminList() method - START");
        }

        final String namedQuery = "fetchAllCharityAdminList";
        final Query fetchAllCharityAdminQuery =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "charityId", charityId);

        List<Object[]> resultSetList =
                fetchAllCharityAdminQuery.getResultList();
        final List<CharityAdminDetailsVO> charityEventAdminList =
                new ArrayList<CharityAdminDetailsVO>();

        for (Object[] objArray : resultSetList) {
            CharityAdminDetailsVO charityAdminDetailsVO =
                    new CharityAdminDetailsVO();
            charityAdminDetailsVO.setCharityAdminId((Long) objArray[1]);
            charityAdminDetailsVO.setTitle((String) objArray[2]);
            charityAdminDetailsVO.setForeName((String) objArray[3]);
            charityAdminDetailsVO.setSurname((String) objArray[4]);
            charityAdminDetailsVO.setEmailAddress((String) objArray[5]);
            charityEventAdminList.add(charityAdminDetailsVO);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityAdminList() method - END");
        }
        return charityEventAdminList;
    }

    /** {@inheritDoc} */
    public Event saveEventDetails(final Event eventDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventDetails() method - START");
        }
        Event event = entityManager.merge(eventDetails);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventDetails() method - END");
        }
        return event;
    }

    /** {@inheritDoc} */
    public Address saveAddreeDetails(final Address addressDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveAddreeDetails() method - START");
        }
        entityManager.merge(addressDetails);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveAddreeDetails() method - END");
        }
        return addressDetails;
    }

    /** {@inheritDoc} */
    public EventFeeDetailsVO fetchEventFeeList(final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeList() method - START");
        }

        final String namedQuery = "fetchEventFeeList";
        // TODO delete this query
        /*
         * SELECT e.id, e.name, e.paymentInstructions, c.id, c.name,
         * ca.alternateFeePayInd, ef FROM Event e, CharitableActivity ca,
         * Charity c, EventFeeDetails ef WHERE e.id = :eventId and e.id =
         * ca.event.id and ca.charity.id = c.id and ca.eventCreatorInd = 'Y' and
         * ca.vmgManageFeeInd = 'Y' and ca.id = ef.charitableActivity.id
         */
        final Query fetchAllFeeListQuery =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "eventId", eventId);

        List<Object[]> resultSetList = fetchAllFeeListQuery.getResultList();

        final EventFeeDetailsVO eventFeeDetailsVO = new EventFeeDetailsVO();
        List<EventFeeDetails> eventFeeDetailsList = new ArrayList<EventFeeDetails>();
        for (Object[] objArray : resultSetList) {
            if (eventFeeDetailsVO.getEventId() == 0) {
                eventFeeDetailsVO.setEventId((Long) objArray[0]);
                eventFeeDetailsVO.setEventName((String) objArray[1]);
                eventFeeDetailsVO.setFeeInstructions((String) objArray[2]);
                eventFeeDetailsVO.setCharityId((Long) objArray[3]);
                eventFeeDetailsVO.setCharityName((String) objArray[4]);
                eventFeeDetailsVO.setAlternateFeeInd((String) objArray[5]);
            }
            eventFeeDetailsList.add((EventFeeDetails) objArray[6]);
        }
        eventFeeDetailsVO.setEventFeeList(eventFeeDetailsList);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeList() method - END");
        }
        return eventFeeDetailsVO;
    }

    /** {@inheritDoc} */
    public Event fetchEventFeeStatus(final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeStatus() method - START");
        }
        final String namedQuery = "fetchEventFeeStatus";
        /*
         * TODO check for event fee details link in fundraiser activity to get
         * exact count of online registered fundraiser count.
         */
        final Query fetchEventFeeStatusQuery =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "eventId", eventId);

        List resultSetList = fetchEventFeeStatusQuery.getResultList();
        if (resultSetList.size() > 0) {
            return (Event) resultSetList.get(0);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeStatus() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public CharitableActivity saveCharitableActivityDetails(
            final CharitableActivity charitableActivityDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveCharitableActivityDetails() method - START");
        }
        CharitableActivity charitableActivityDetailsObject =
                entityManager.merge(charitableActivityDetails);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveCharitableActivityDetails() method - END");
        }
        return charitableActivityDetailsObject;
    }

    /** {@inheritDoc} */
    public EventFeeDetails saveEventFeeDetails(
            final EventFeeDetails eventFeeDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventFeeDetails() method - START");
        }
        EventFeeDetails eventFeeDetailsObject =
                entityManager.merge(eventFeeDetails);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventFeeDetails() method - END");
        }
        return eventFeeDetailsObject;
    }

    /** {@inheritDoc} */
    public EventActivity saveEventActivityDeatils(
            final EventActivity eventActivityDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventActivityDeatils() method - START");
        }
        EventActivity eventActivityObject =
                entityManager.merge(eventActivityDetails);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventActivityDeatils() method - END");
        }
        return eventActivityObject;
    }

    /** {@inheritDoc} */
    public CharityEventAdministrator saveCharityEventAdministrator(
            final CharityEventAdministrator charityEventAdministrator) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveCharityEventAdministrator() method - START");
        }
        CharityEventAdministrator charityEventAdministratorObject =
                entityManager.merge(charityEventAdministrator);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveCharityEventAdministrator() method - END");
        }
        return charityEventAdministratorObject;
    }

    /** {@inheritDoc} */
    public void deleteEventActivitiesForEvent(final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteEventActivitiesForEvent() method - START");
        }
        entityManager.createNamedQuery("deleteEventActivity")
            .setParameter("eventId", eventId)
            .executeUpdate();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteEventActivitiesForEvent() method - END");
        }
    }

    /** {@inheritDoc} */
    public CharityAdministrator fetchCharityAdministrators(
            final Long charityAdminId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityAdministrators() method - START");
        }
        CharityAdministrator charityAdmininstartor =
                entityManager.find(CharityAdministrator.class, charityAdminId);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityAdministrators() method - END");
        }
        return charityAdmininstartor;
    }

    /** {@inheritDoc} */
    public BankAccount fetchBankAccountDetails(final Long bankAccountId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchBankAccountDetails() method - START");
        }
        BankAccount bankAccount =
                entityManager.find(BankAccount.class, bankAccountId);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchBankAccountDetails() method - END");
        }
        return bankAccount;
    }

    /** {@inheritDoc} */
    public CharitableActivity fetchCancelEventRight(final Long eventId,
            final Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCancelEventRight() method - START");
        }
        final String namedQuery = "fetchCancelEventRight";
        final Query fetchCancelEventRightQuery =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "eventId", eventId)
                        .setParameter("charityId", charityId);

        List resultSetList = fetchCancelEventRightQuery.getResultList();
        if (resultSetList.size() > 0) {
            return (CharitableActivity) resultSetList.get(0);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCancelEventRight() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public Event fetchEventDetail(final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventDetail() method - START");
        }
        Event eventObject = entityManager.find(Event.class, eventId);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventDetail() method - END");
        }
        return eventObject;
    }

    /** {@inheritDoc} */
    public CharitableActivity fetchCharitableActivityDetail(
            final Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharitableActivityDetail() method - START");
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharitableActivityDetail() method - END");
        }
        return entityManager.find(CharitableActivity.class,
                charitableActivityId);
    }

    /** {@inheritDoc} */
    public List<EventFeeDetails> fetchEventFeeDetails(
            final Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeDetails() method - START");
        }
        final Query fetchEventFeeDetailQuery =
                entityManager
                        .createQuery(
                                "SELECT ef FROM EventFeeDetails ef "
                                        + "WHERE ef.charitableActivity.id=:charitableActivityId")
                        .setParameter("charitableActivityId",
                                charitableActivityId);

        List<EventFeeDetails> resultSetList =
                fetchEventFeeDetailQuery.getResultList();
        if (resultSetList.size() > 0) {
            return resultSetList;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeDetails() method - END");
        }
        return new ArrayList<EventFeeDetails>();
    }

    /** {@inheritDoc} */
    public void deleteEventFeeDetails(final Long eventFeeId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteEventFeeDetails() method - START");
        }
        final Query deleteEventFeeDetails =
                entityManager
                        .createQuery(
                                "DELETE from EventFeeDetails ef where ef.id = :eventFeeId")
                        .setParameter("eventFeeId", eventFeeId);
        deleteEventFeeDetails.executeUpdate();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteEventFeeDetails() method - END");
        }
    }

    /** {@inheritDoc} */
    public List<CharityEventAdministrator> fetchCharityEventAdministrators(
            final Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityEventAdministrators() method - START");
        }
        final Query fetchEventFeeDetailQuery =
                entityManager
                        .createQuery(
                                "SELECT cea FROM CharityEventAdministrator cea"
                                        + " WHERE cea.charitableActivity.id=:charitableActivityId")
                        .setParameter("charitableActivityId",
                                charitableActivityId);

        List<CharityEventAdministrator> resultSetList =
                fetchEventFeeDetailQuery.getResultList();
        if (resultSetList.size() > 0) {
            return resultSetList;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityEventAdministrators() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public List<CharityAdminDetailsVO> fetchCharityAdministratorEmailList(
            final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityAdministratorEmailList() method - START");
        }

        final String namedQuery = "fetchCharityAdministratorEmailList";
        final Query fetchCharityAdministratorEmailListQuery =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "eventId", eventId);

        List<Object[]> resultSetList =
                fetchCharityAdministratorEmailListQuery.getResultList();
        final List<CharityAdminDetailsVO> charityEventAdminList =
                new ArrayList<CharityAdminDetailsVO>();

        for (Object[] objArray : resultSetList) {
            CharityAdminDetailsVO charityAdminDetailsVO =
                    new CharityAdminDetailsVO();
            charityAdminDetailsVO.setPersonId((Long) objArray[0]);
            charityAdminDetailsVO.setTitle((String) objArray[1]);
            charityAdminDetailsVO.setForeName((String) objArray[2]);
            charityAdminDetailsVO.setSurname((String) objArray[3]);
            charityAdminDetailsVO.setEmailAddress((String) objArray[4]);
            charityAdminDetailsVO.setEventCreatorInd((String) objArray[5]);
            charityAdminDetailsVO.setCharityId((Long) objArray[6]);
            charityEventAdminList.add(charityAdminDetailsVO);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityAdministratorEmailList() method - END");
        }
        return charityEventAdminList;
    }

    /** {@inheritDoc} */
    public boolean updateEventStatus(Long eventId, String newStatus) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::updateEventStatus() method - START");
        }
        Event event = entityManager.getReference(Event.class, eventId);
        if (event != null) {
            final Query fetchEventStatus =
                    entityManager
                            .createQuery(
                                    "SELECT es FROM EventStatus es WHERE es.code=:code")
                            .setParameter("code", newStatus);
            event.setEventStatus((EventStatus) fetchEventStatus
                    .getSingleResult());
            // Ticket 14706: Set the expiry date when an event is expired.
            if (newStatus != null && newStatus.equalsIgnoreCase(MasterDataCodeConstants.EXPIRED)) {
                Calendar calendar = new GregorianCalendar();
                event.setExpiryDate(new Timestamp(calendar.getTimeInMillis()));
            }
            entityManager.merge(event);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::updateEventStatus() method - END");
        }
        return true;
    }


    /** {@inheritDoc} */
    public boolean updateFundraiserEventFee(Long fundraiserId, Long eventId,
            String feeReference, String feeSituation, BigDecimal feeAmount, Long fundraiserActivityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::updateFundraiserEventFee() method - START");
        }
        final Query updateFundraiserEventFeeQuery =
                entityManager.createQuery(
                                "UPDATE FundraiserActivity fa SET "
                                        + " fa.feeReference = :feeReference, "
                                        + " fa.feeSituation = :feeSituation, "
                                        + " fa.feeAmount = :feeAmount, "
                                        + " fa.updatedUser = :updatedUser, "
                                        + " fa.updatedProcess = :updatedProcess, "
                                        + " fa.updatedIPAddress = :updatedIPAddress "
                                        + " WHERE fa.fundraiser.id = :fundraiserId "
                                        + " AND fa.event.id = :eventId"
                                        + " AND fa.id = :fundraiserActivityId")
                        .setParameter("fundraiserId", fundraiserId)
                        .setParameter("eventId", eventId)
                        .setParameter("fundraiserActivityId", fundraiserActivityId)
                        .setParameter("feeReference", feeReference)
                        .setParameter("feeSituation", feeSituation)
                        .setParameter("feeAmount", feeAmount)
                        .setParameter("updatedUser", attributes.getCreatedUser())
                        .setParameter("updatedProcess", attributes.getCreatedProcess())
                        .setParameter("updatedIPAddress", attributes.getCreatedIPAddress());
        int numberOfRecords = updateFundraiserEventFeeQuery.executeUpdate();
        if (numberOfRecords == 1) {
            return true;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::updateFundraiserEventFee() method - END");
        }
        return false;
    }

    /** {@inheritDoc} */
    public int fetchOnlineRegisteredFundraiserCount(final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOnlineRegisteredFundraiserCount() method - START");
        }
        final Query fetchOnlineEventRegisteredCountQuery =
                entityManager
                        .createQuery(
                         "SELECT count(fa.id) FROM FundraiserActivity fa "
                         + " WHERE fa.event.id = :eventId AND fa.feeSituation IS NOT NULL ")
                        .setParameter("eventId", eventId);
        Long count =
                (Long) fetchOnlineEventRegisteredCountQuery.getSingleResult();
        if (count > 0) {
            return count.intValue();
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchOnlineRegisteredFundraiserCount() method - END");
        }
        return 0;
    }

    /** {@inheritDoc} */
    public EventFeeDetails fetchEventFeeDetailsByFeeId(final Long feeId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeDetailsByFeeId() method - START");
        }
        final Query fetchEventDetailQuery =
                entityManager.createQuery(
                        "SELECT e FROM EventFeeDetails e WHERE e.id=:feeId")
                        .setParameter("feeId", feeId);

        List resultSetList = fetchEventDetailQuery.getResultList();
        if (resultSetList.size() > 0) {
            return (EventFeeDetails) resultSetList.get(0);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchEventFeeDetailsByFeeId() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public boolean deleteCharitableActivityDetails(
            final Long charitableActivityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteCharitableActivityDetails() method - START");
        }
        final Query deleteCharitableActivityDetails =
                entityManager
                        .createQuery(
                                "DELETE from CharitableActivity ca where ca.id = :charitableActivityId")
                        .setParameter("charitableActivityId",
                                charitableActivityId);
        int numberOfRecords = deleteCharitableActivityDetails.executeUpdate();
        if (numberOfRecords == 1) {
            return true;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteCharitableActivityDetails() method - END");
        }
        return false;
    }

    /** {@inheritDoc} */
    public void deleteCharityEventAdministratorsForEvent(
            final Long charitableActivityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteCharityEventAdministratorsForEvent() method - START");
        }
        final Query deleteCharityEventAdministrators =
                entityManager
                        .createQuery(
                                "DELETE from CharityEventAdministrator cea"
                                        + " where cea.charitableActivity.id = :charitableActivityId")
                        .setParameter("charitableActivityId",
                                charitableActivityId);
        deleteCharityEventAdministrators.executeUpdate();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteCharityEventAdministratorsForEvent() method - END");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean saveEventStatus(final Long eventId,
            final String newEventStatus, final AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventStatus() method - START");
        }
        final Query updateEventStatusQuery =
                entityManager
                        .createQuery(
                                "UPDATE Event e SET e.eventPrevStatus.code=:eventStatus, "
                                + " e.updatedIPAddress = :updatedIPAddress, "
                                + " e.updatedProcess = :updatedProcess, "
                                + " e.updatedUser = :updatedUser "
                                + " WHERE e.id =:eventId")
                        .setParameter("eventStatus", newEventStatus)
                        .setParameter("eventId", eventId)
                        .setParameter("updatedIPAddress", attributes.getCreatedIPAddress())
                        .setParameter("updatedProcess", attributes.getCreatedProcess())
                        .setParameter("updatedUser", attributes.getCreatedUser());
        int updatedRecords = updateEventStatusQuery.executeUpdate();
        if (updatedRecords >= 1) {
            return true;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventStatus() method - END");
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public List<BankAccount> fetchCharityBankDetailsByAccountType(Long charityId,
            List<BankAccountType> charityBankTypeList) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityBankDetailsByAccountType() method - START");
        }
        List<String> bankAccountTypeList = new ArrayList<String>();
        for (BankAccountType bankAccountType : charityBankTypeList) {
            bankAccountTypeList.add(bankAccountType.getCode());
        }
        List<BankAccount> bankDetailsList =
                entityManager.createNamedQuery("fetchCharityBankDetailsForEvent")
                        .setParameter("charityId", charityId)
                        .setParameter("bankAccountTypeList", bankAccountTypeList)
                        .getResultList();

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("bankDetailsList size " + bankDetailsList.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharityBankDetailsByAccountType() method - END");
        }

        return bankDetailsList;
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.giving.dao.EventDAO#saveEventAdministrator(com.virginmoneygiving.giving.domain.CharityAdministrator, long, long, com.virginmoneygiving.giving.domain.AuditAttributes)
     */
    public void saveEventAdministrator(CharityAdministrator charityAdministrator,
            long userId, long charityId, final AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventAdministrator() method - START");
        }
        Query query = null;
        boolean flag = true;

        if (charityAdministrator != null) {
            charityId = charityAdministrator.getCharity().getId();
            flag = false;

        } else {
            query = entityManager.createNamedQuery("fetchCharityAdministratorByUserId")
                .setParameter("charityId", charityId)
                .setParameter("userId", userId)
                .setParameter("roleCode", MasterDataCodeConstants.Roles.ROLE_CHARITY_ADMIN.getCode());
            charityAdministrator = (CharityAdministrator) query.getSingleResult();
        }

        // Fetch list of all events having 'AllUsersInd' as 'Y' and for the above charity.
        List<CharitableActivity> list = fetchCharitableActivityByCharityIdAndAllUsersInd(charityId);

        for (CharitableActivity activity : list) {
            CharityEventAdministrator administrator = new CharityEventAdministrator();

            // Set charity administrator
            administrator.setCharityAdministrator(charityAdministrator);

            CharitableActivity newCharitableActivity = new CharitableActivity();
            newCharitableActivity.setId(activity.getId());
            administrator.setCharitableActivity(newCharitableActivity);

            // Check to see if the record already exists.
            if (flag) {
                query = entityManager.createNamedQuery("fetchCharityEventAdministratorByCharitableActivity")
                    .setParameter("charitableActivityId", activity.getId())
                    .setParameter("charityAdminId", charityAdministrator.getId());
                CharityEventAdministrator charityEventAdministrator = null;
                List results = query.getResultList();
                if ( (results != null) && (results.size() > 0) ) {
                    charityEventAdministrator = (CharityEventAdministrator) results.get(0);
                    administrator = charityEventAdministrator;
                }
            }

            administrator = entityManager.merge(administrator);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::saveEventAdministrator() method - END");
        }
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.giving.dao.EventDAO#deleteEventAdministrator(long, long)
     */
    public void deleteEventAdministrator(long userId, long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteEventAdministrator() method - START");
        }
        Query query = null;
        query = entityManager.createNamedQuery("fetchCharityAdministratorByUserId")
            .setParameter("charityId", charityId)
            .setParameter("userId", userId)
            .setParameter("roleCode", MasterDataCodeConstants.Roles.ROLE_CHARITY_ADMIN.getCode());
        CharityAdministrator charityAdministrator = (CharityAdministrator) query.getSingleResult();

        // Fetch list of all events having 'AllUsersInd' as 'Y' and for the above charity.
        List<CharitableActivity> charitableActivityList = fetchCharitableActivityByCharityIdAndAllUsersInd(charityId);

        for (CharitableActivity activity : charitableActivityList) {
            CharityEventAdministrator charityEventAdministrator = null;

            query = entityManager.createNamedQuery("fetchCharityEventAdministratorByCharitableActivity")
                .setParameter("charitableActivityId", activity.getId())
                .setParameter("charityAdminId", charityAdministrator.getId());

            List results = query.getResultList();

            if ( (results != null) && (results.size() > 0)) {
                charityEventAdministrator = (CharityEventAdministrator) results.get(0);
                query = entityManager.createNamedQuery("deleteCharityEventAdministrator")
                    .setParameter("charityAdminId", charityEventAdministrator.getCharityAdministrator().getId());
                query.executeUpdate();
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::deleteEventAdministrator() method - END");
        }
    }

    /**
     * Fetch charitable activity by charity id and all users ind.
     *
     * @param charityId the charity id
     *
     * @return the list< charitable activity>
     */
    private List<CharitableActivity> fetchCharitableActivityByCharityIdAndAllUsersInd(final
            long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharitableActivityByCharityIdAndAllUsersInd() method - START");
        }
        Query query =
            entityManager.createNamedQuery("fetchCharitableActivityByCharityIdAllUsersInd").setParameter(
                    "charityId", charityId);
        List<CharitableActivity> charitableActivities = query.getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAEventDAOImpl::fetchCharitableActivityByCharityIdAndAllUsersInd() method - END");
        }
        return charitableActivities;
    }

} // end of class
