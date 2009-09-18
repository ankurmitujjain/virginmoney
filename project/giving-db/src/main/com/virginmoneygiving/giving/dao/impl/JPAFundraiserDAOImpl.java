package com.virginmoneygiving.giving.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.constants.ServiceConstants;
import com.virginmoneygiving.giving.dao.FundraiserDAO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupPageDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.TeamMemberDetailDTO;
import com.virginmoneygiving.giving.domain.*;
import com.virginmoneygiving.giving.util.DAOHelper;

/**
 * DAO for fundraiser registration and other activity details.
 * 
 * @author Edwin Tauro
 */
@Repository
public class JPAFundraiserDAOImpl implements FundraiserDAO {

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(JPAFundraiserDAOImpl.class);

    /** An Entity Manager Instance. */
    private EntityManager entityManager;
    
    /** The Constant UPDATE_PAGE_BY_ID. */
    private static final String UPDATE_PAGE_BY_ID =
        "updatePageById";

    /**
     * The JPA EntityManager. 
     * 
     * @param entityManager an entity manger.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
    	if (LOGGER.isInfoEnabled()) {
    		LOGGER.info("JPA Entity Manager injected");
    	}
        this.entityManager = entityManager;
    }

    /** {@inheritDoc} */
    public Long saveFundraiser(Fundraiser fundraiser) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraiser() method - START");
        }

        entityManager.persist(fundraiser);

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraiser() method - END");
        }

        return fundraiser.getId();
    }

    /* Methods used by Fundraising Event Search Usecase. */
    /** {@inheritDoc} */
    public List<Location> fetchAllLocations() {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchAllLocations() method - START");
        }

        final Query fetchAllLocations =
                entityManager.createQuery("FROM Location ORDER BY displayOrder");
        final List<Location> locations = fetchAllLocations.getResultList();

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchAllLocations() method - END");
        }
        return locations;
    }

    /** {@inheritDoc} */
    public List<Event> findEventsByName(String eventName) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchAllEvents() method - START");
        }

        final Query fetchAllEvents =
                entityManager.createQuery(
                        "FROM Event e WHERE "
                                + "e.name like :eventName ORDER BY e.name ")
                        .setParameter("eventName", eventName);
        final List<Event> events = fetchAllEvents.getResultList();

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchAllEvents() method - END");
        }
        return events;
    }

    /** {@inheritDoc} */
    public List<ActivityType> fetchAllActivityTypes(String reason) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchAllActivityTypes() method - START");
        }
        /*
         * Will always fetch the activity type as Other which is common for all
         * activities across fundraising reasons.
         */
        final String namedQuery = "fetchAllActivityTypes";
        final String otherReason = "OTH_REASON";
        final Query fetchAllActivityTypes =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "reason", reason).setParameter("otherReason",
                        otherReason);

        List<Object[]> resultSetList = fetchAllActivityTypes.getResultList();
        final List<ActivityType> activityTypes = new ArrayList<ActivityType>();

        for (Object[] objArray : resultSetList) {
            ActivityType activityType = new ActivityType();
            activityType.setCode((String) objArray[0]);
            activityType.setDescription((String) objArray[1]);
            FundraisingReason fundraisingReason = new FundraisingReason();
            fundraisingReason.setCode((String) objArray[2]);
            activityTypes.add(activityType);
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchAllActivityTypes() method - END");
        }
        return activityTypes;
    }

    /** {@inheritDoc} */
    public List<SearchFundraisingEventResult> searchFundraisingEvent(
            SearchFundraisingEventParameter parameter) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::searchFundraisingEvent() method - START");
        }
        Calendar calendar = new GregorianCalendar();
        String activityType = null;
        String charityName = null;
        String eventName = null;
        String eventDescription = null;
        String locationCode = null;
        // Current Date is set into this variable.
        Timestamp startDate = null;
        Timestamp endDate = null;

        List<SearchFundraisingEventResult> searchFundraisingEventResultList =
                new ArrayList<SearchFundraisingEventResult>();

        if (StringUtils.isBlank(parameter.getCharityName())) {
            charityName = "%";
        }
        else {
            charityName = parameter.getCharityName();
        }
        if (StringUtils.isBlank(parameter.getEventname())) {
            eventName = "%";
            eventDescription = "%";
        }
        else {
            eventName = "%" + parameter.getEventname() + "%";
            eventDescription = parameter.getEventname();
        }
        if (StringUtils.isBlank(parameter.getEventType())) {
            activityType = "%";
        }
        else {
            activityType = parameter.getEventType();
        }
        if (StringUtils.isBlank(parameter.getLocation())) {
            locationCode = "%";
        }
        else {
            locationCode = parameter.getLocation();
        }

        if (StringUtils.isBlank(parameter.getEventDate())) {
            int date = calendar.get(Calendar.DATE);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            calendar = new GregorianCalendar(year, month, date);
            startDate = new Timestamp(calendar.getTimeInMillis());
            calendar.add(Calendar.YEAR, 2);
            endDate = new Timestamp(calendar.getTimeInMillis());
        }
        else {
            String requestDate = parameter.getEventDate();
            StringTokenizer st = new StringTokenizer(requestDate, " ");
            int month = Integer.parseInt(st.nextToken()) - 1;
            int year = Integer.parseInt(st.nextToken());
            int date = 1;

            /*
             * Check if the selected month and year is same as current, if true
             * then use current date and if future month then use the first day
             * i.e. 1
             */
            if (year == calendar.get(Calendar.YEAR)
                    && month == calendar.get(Calendar.MONTH)) {
                date = calendar.get(Calendar.DATE);
            }
            calendar = new GregorianCalendar(year, month, date);
            startDate = new Timestamp(calendar.getTimeInMillis());
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, maxDay);
            endDate = new Timestamp(calendar.getTimeInMillis());
        }

        List<Object> searchFundraisingEventListAll = new ArrayList<Object>();
        List<Object> searchFundraisingEventListName;
        List<Object> searchFundraisingEventListDesc;
        if (charityName.equals("%")) {
            searchFundraisingEventListName =
                    (entityManager.createNamedQuery(
                            "searchFundraisingEvent_nocharity").setParameter(
                            "eventName", eventName + "%").setParameter(
                            "activityType", activityType).setParameter(
                            "locationCode", locationCode).setParameter(
                            "startDate", startDate).setParameter("endDate",
                            endDate)).getResultList();
        }
        else {
            searchFundraisingEventListName =
                    (entityManager.createNamedQuery(
                            "searchFundraisingEvent_charity").setParameter(
                            "eventName", eventName + "%").setParameter(
                            "activityType", activityType).setParameter(
                            "locationCode", locationCode).setParameter(
                            "charityName", charityName + "%").setParameter(
                            "startDate", startDate).setParameter("endDate",
                            endDate)).getResultList();
        }

        LOGGER.debug("searchFundraisingEventList size "
                + searchFundraisingEventListName.size());
        if (searchFundraisingEventListName.size() > 0) {
            searchFundraisingEventListAll
                    .addAll(searchFundraisingEventListName);
        }

        // TODO This implementation needs to be refactored by Solar
        // implementation.

        if (("%").equals(eventName)) { //Fixed 12706

            if (charityName.equals("%")) {
                searchFundraisingEventListDesc =
                        (entityManager
                                .createNamedQuery(
                                        "searchFundraisingEventbyDescription_nocharity")
                                .setParameter("eventName", eventName + "%")
                                .setParameter("eventDescription",
                                        "%" + eventDescription + "%")
                                .setParameter("activityType", activityType)
                                .setParameter("locationCode", locationCode)
                                .setParameter("startDate", startDate)
                                .setParameter("endDate", endDate))
                                .getResultList();
            }
            else {
                searchFundraisingEventListDesc =
                        (entityManager.createNamedQuery(
                                "searchFundraisingEventbyDescription_charity")
                                .setParameter("eventName", eventName + "%")
                                .setParameter("eventDescription",
                                        "%" + eventDescription + "%")
                                .setParameter("activityType", activityType)
                                .setParameter("locationCode", locationCode)
                                .setParameter("charityName", charityName + "%")
                                .setParameter("startDate", startDate)
                                .setParameter("endDate", endDate))
                                .getResultList();
            }
            if (searchFundraisingEventListDesc.size() > 0) {
                searchFundraisingEventListAll
                        .addAll(searchFundraisingEventListDesc);
            }
        }

        for (Object searchEventObject : searchFundraisingEventListAll) {
            Event event = (Event) searchEventObject;
            SearchFundraisingEventResult searchResult =
                    new SearchFundraisingEventResult();
            searchResult.setEventId(event.getId());
            searchResult.setEventName(event.getName());
            searchResult.setDescription(event.getDescription());
            searchResult.setLocation(event.getLocation().getDescription());
            searchResult.setEventDatetime(event.getEventDatetime());
            searchResult.setEventTimeText(event.getEventTimeText());
            searchResult.setFundraiserSplitOverrideInd(event
                    .getFundraiserSplitOverrideInd());
            searchResult.setOperationalEventInd(event.getOperationalEventInd());

            if (event.getMaximumCharities() != null) {
                int maxCharities = event.getMaximumCharities().intValue();
                if (maxCharities == 1) {
                    // single charity can participate in the event
                    searchResult
                            .setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_SINGLE);
                }
                else {
                    // restricted
                    searchResult
                            .setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_RESTRICTED);
                }
            }
            else {
                searchResult
                        .setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_ALL);
            }

            searchResult.setCharitableActivities(event.getCharitableActivities());
            if (event.getCharitableActivities() != null
                    && event.getCharitableActivities().size() == 1) {
                CharitableActivity charitableActivity =
                        event.getCharitableActivities().iterator().next();
                searchResult.setCharityId(charitableActivity.getCharity().getId());
                searchResult.setCharityName(charitableActivity.getCharity().getName());
                if( MasterDataCodeConstants.YES.equalsIgnoreCase(charitableActivity.getVmgManageFeeInd()) ){
                	searchResult.setCharitySupportedInd(MasterDataCodeConstants.CHARITIES_SUPPORTED_BY_EVENT_SINGLE);
                }
            }
            searchFundraisingEventResultList.add(searchResult);

        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::searchFundraisingEvent() method - END");
        }

        return searchFundraisingEventResultList;
    }

    /** {@inheritDoc} */
    public FundraiserActivity saveFundraisingReason(
            FundraiserActivity fundraiserActivity) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraisingReason() method - START");
        }

        // HunarC: TRAC 673: Should we delete the existing split details? See comments against
        // method checkFundraiserActivitySplitChanged() for details.
        if (fundraiserActivity.getId() != null && fundraiserActivity.getId() > 0
                && !fundraiserActivity.getFundraisingCharitySplit().isEmpty()) {
            FundraiserActivity tempActivity = fetchFundraiserActivityById(fundraiserActivity.getId());
            if (tempActivity != null && !tempActivity.getFundraisingCharitySplit().isEmpty()) {
                if (checkFundraiserActivitySplitChanged(tempActivity, fundraiserActivity)) {
                    int ctr = 0;
                    for (FundraisingCharitySplit frSplit : tempActivity.getFundraisingCharitySplit()) {
                        LOGGER.debug("JPAFundraiserDAOImpl::saveFundraisingReason - Clearing Splits for Activity Id = "
                                + fundraiserActivity.getId() + ", split ID = " + frSplit.getId());
                        entityManager.remove(frSplit);
                        ctr++;
                    }
                    LOGGER.debug("JPAFundraiserDAOImpl::saveFundraisingReason - Cleared existing Splits for Activity Id = "
                            + fundraiserActivity.getId() + ", count = " + ctr);
                }
            }
        }

        FundraiserActivity persistedFundraiserActivity =
                entityManager.merge(fundraiserActivity);

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraisingReason() method - END");
        }
        return persistedFundraiserActivity;
    }

    /** {@inheritDoc} */
    public Fundraiser fetchFundraiserDetailsById(Long fundraiserId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserDetailsById() method - START");
        }

        Fundraiser fundraiser =
                entityManager.find(Fundraiser.class, fundraiserId);
        if (fundraiser != null) {
        	if (LOGGER.isDebugEnabled()) {
        		LOGGER.debug("Received Charity details with status : "
                    + fundraiser.getFundraiserStatus().getDescription());
        	}
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserDetailsById() method - END");
        }
        return fundraiser;

    }

    /** {@inheritDoc} */
    public FundraiserActivity fetchFundraiserActivityById(
            Long fundraiserActivityId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserActivityById() method - START");
        }

        return entityManager.find(FundraiserActivity.class,
            fundraiserActivityId);
    }

    /** {@inheritDoc} */
    public boolean checkIfUrlIsAvailable(String url, String urlType) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::checkIfUrlIsAvailable() method - START");
        }
        boolean urlAvailable = true;
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("URL to be searched " + url);
        }

        Query countNumberOfURLQuery =
                entityManager.createNamedQuery("countNumberOfURLs");
        countNumberOfURLQuery.setParameter("urlType", urlType);
        countNumberOfURLQuery.setParameter("url", url);
        Long numberOfURLs = (Long) countNumberOfURLQuery.getSingleResult();
        if (numberOfURLs > 0) {
            urlAvailable = false;
        }

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("URL is available: " + urlAvailable);
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::checkIfUrlIsAvailable() method - END");
        }

        return urlAvailable;
    }

    /** {@inheritDoc} */
    public int getRegisteredUserCount(String firstname, String lastname) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::getRegisteredUserCount() method - START");
        }
        Long registeredUserCount;
        Query getNumberOfRegisteredUsersWithSameName =
                entityManager.createNamedQuery(
                        "getNumberOfRegisteredUsersWithSameName").setParameter(
                        "firstname", firstname).setParameter("lastname",
                        lastname);
        registeredUserCount =
                (Long) getNumberOfRegisteredUsersWithSameName.getSingleResult();
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("getRegisteredUserCount " + registeredUserCount);
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::getRegisteredUserCount() method - END");
        }

        return registeredUserCount.intValue();
    }

    /** {@inheritDoc} */
    public void storeUrl(Fundraiser fundraiser) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::storeUrl() method - START");
        }

        entityManager.merge(fundraiser);

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::storeUrl() method - END");
        }

    }

    /** {@inheritDoc} */
    public Event getEventDetailsById(Long eventId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::getEventDetailsById() method - START");
        }

        Event eventDetails = entityManager.find(Event.class, eventId);
        if (eventDetails != null) {
            LOGGER.debug("Received Event details with event id : "
                    + eventDetails.getDescription());
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::getEventDetailsById() method - END");
        }

        return eventDetails;
    }

    /** {@inheritDoc} */
    public boolean isPaymentCardDetailsExist(String emailAddress, int dobDay,
            int dobMonth, int dobYear) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::isPaymentCardDetailsExist() method - START");
        }

        boolean detailsExist = false;

        Query isPaymentCardDetailsExist =
                entityManager.createNamedQuery("isPaymentCardDetailsExist");
        isPaymentCardDetailsExist.setParameter("emailAddress", emailAddress);
        isPaymentCardDetailsExist.setParameter("dobDay", dobDay);
        isPaymentCardDetailsExist.setParameter("dobMonth", dobMonth);
        isPaymentCardDetailsExist.setParameter("dobYear", dobYear);

        List<PaymentCard> paymentCards =
                isPaymentCardDetailsExist.getResultList();
        if (paymentCards != null && paymentCards.size() > 0) {
            PaymentCard paymentCard = paymentCards.get(0);
            LOGGER.debug("payment card found: " + paymentCard.getId());
            detailsExist = true;
        }

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::isPaymentCardDetailsExist() method - END");
        }

        return detailsExist;
    }

    /** {@inheritDoc} */
    public List<FundraiserDonorVO> fetchFundraisersAndDonorsByCriteria(
            String name, Long vmgRefNo, String postCode) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraisersAndDonorsByCriteria() method - START");
        }
        String queryFundraiser =
                "Select distinct f.id as vmgRefId, p.forename as forename, p.surname as surname, "
                        + " a.postCode as postcode, p.dobDay as dobDay, p.dobMonth as dobMonth, p.dobYear as dobYear "
                        + " From Fundraiser f inner join f.userRole.user.person p inner join p.personalAddresses pa inner join pa.address a ";

        boolean wherePartIncluded = false;
        if (StringUtils.isNotBlank(name)) {
            queryFundraiser =
                    queryFundraiser + " Where CONCAT(forename, surname) like :name ";
            wherePartIncluded = true;
        }
        if (StringUtils.isNotBlank(postCode)) {
            StringBuffer searchPostcode = new StringBuffer();
            String percentage = "%";
            for (int i = 0; i < postCode.length(); i++) {
                searchPostcode.append(postCode.charAt(i));
                searchPostcode.append(percentage);
            }
            postCode = searchPostcode.toString();

            if (wherePartIncluded) {
                queryFundraiser =
                        queryFundraiser + " And postCode like :postCode ";
            }
            else {
                queryFundraiser =
                        queryFundraiser + " Where postCode like :postCode ";
                wherePartIncluded = true;
            }
        }
        if (vmgRefNo != null) {
            if (wherePartIncluded) {
                queryFundraiser =
                        queryFundraiser + " And f.id = :vmgRefNoForFundraiser ";
            }
            else {
                queryFundraiser =
                        queryFundraiser + " Where f.id = :vmgRefNoForFundraiser ";
            }
        }

        List<FundraiserDonorVO> fundraiserDonorList =
                new ArrayList<FundraiserDonorVO>();

        final Query fetchFundraisersQuery =
                entityManager.createQuery(queryFundraiser);
        //Fixed 12705
        if (StringUtils.isNotBlank(name)) {
        	name = name.replace(" ", "%");
            fetchFundraisersQuery.setParameter("name", "%"+name+"%");
        }
        if (StringUtils.isNotBlank(postCode)) {
            fetchFundraisersQuery.setParameter("postCode", postCode);
        }
        if (vmgRefNo != null) {
            fetchFundraisersQuery.setParameter("vmgRefNoForFundraiser", vmgRefNo);
        }
        final List<Object[]> fundraisers =
                (List<Object[]>) fetchFundraisersQuery.getResultList();
        for (Object[] fundraiser : fundraisers) {
            FundraiserDonorVO fundraiserDonorVO = new FundraiserDonorVO();
            fundraiserDonorVO
                    .setVmgReference((Long) fundraiser[ServiceConstants.ZERO]);
            fundraiserDonorVO.setName(fundraiser[ServiceConstants.ONE] + " "
                    + fundraiser[ServiceConstants.TWO]);
            fundraiserDonorVO
                    .setPostCode((String) fundraiser[ServiceConstants.THREE]);
            String strDateOfBirth =
                    ((Integer) fundraiser[ServiceConstants.FOUR]).toString()
                            + "/"
                            + ((Integer) fundraiser[ServiceConstants.FIVE])
                                    .toString()
                            + "/"
                            + ((Integer) fundraiser[ServiceConstants.SIX])
                                    .toString();
            fundraiserDonorVO.setDateOfBirth(strDateOfBirth);
            fundraiserDonorList.add(fundraiserDonorVO);
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraisersAndDonorsByCriteria() method - END");
        }
        return fundraiserDonorList;
    }

    /** {@inheritDoc} */
    public User updateUserPersonalDetails(User user) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateUserPersonalDetails() method");
        }
        return entityManager.merge(user);

    }

    /** {@inheritDoc} */
    public boolean isFundraiserTeamAlreadyExist(String teamName) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::isFundraiserTeamAlreadyExist() method - START");
        }
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.trace("Before checking team already registered or not "
                + teamName);
    	}

        boolean detailsExist = false;

        Query isFundraiserTeamAlreadyExist =
                entityManager.createNamedQuery("isFundraiserTeamAlreadyExist");
        isFundraiserTeamAlreadyExist.setParameter("teamName", teamName);

        List<FundraiserGroup> fundraiserGroup =
                isFundraiserTeamAlreadyExist.getResultList();
        if (fundraiserGroup != null && fundraiserGroup.size() > 0) {
        	if (LOGGER.isDebugEnabled()) {
        		LOGGER.debug("fundraiserTeam size " + fundraiserGroup.size());
        	}
            detailsExist = true;
        }

       	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::isFundraiserTeamAlreadyExist() method - END");
        }

        return detailsExist;
    }

    /** {@inheritDoc} */
    public Long saveFundraiserGroup(FundraiserGroup fundraiserGroup) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraiserGroup() method - START");
        }

        entityManager.persist(fundraiserGroup);

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraiserGroup() method - END");
        }

        return fundraiserGroup.getId();
    }

    /** {@inheritDoc} */
    public List<TeamMemberDetailDTO> searchRegisteredFundraiserByEmailAddress(
            String emailAddress) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::searchRegisteredFundraiserByEmailAddress() method - START");
        }

        final Query searchRegisteredFundraiserByEmailAddress =
                entityManager
                        .createNamedQuery("SearchRegisteredFundraiserByEmailAddress");

        searchRegisteredFundraiserByEmailAddress.setParameter("emailAddress",
               emailAddress + "%");
        /*searchRegisteredFundraiserByEmailAddress.setParameter("emailAddressType",
                MasterDataCodeConstants.EMAIL_ADDRESS_TYPE_PERSONAL );*/

        final List<Object[]> searchResult =
                searchRegisteredFundraiserByEmailAddress.getResultList();
        
        
        final List<Long> fundraiserList = entityManager.createNamedQuery(
				"SearchRegisteredFundraiserByEmailAddressTemp")
				.setParameter( "emailAddress", emailAddress + "%")
				.setParameter( "emailAddressTypeContact", MasterDataCodeConstants.EMAIL_ADDRESS_TYPE_CONTACT)
				.setParameter( "emailAddressTypePersonal", MasterDataCodeConstants.EMAIL_ADDRESS_TYPE_PERSONAL)
				.getResultList();

        List<TeamMemberDetailDTO> teamMemberList =
                DAOHelper.createTeamMemberList(searchResult, fundraiserList);
        
        

      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::searchRegisteredFundraiserByEmailAddress() method - END");
        }

        return teamMemberList;

    }

    /** {@inheritDoc} */
    public Long saveFundraiserTeamMember(
            FundraiserGroupMember fundraiserGroupMember) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraiserTeamMember() method - START");
        }

        entityManager.persist(fundraiserGroupMember);

      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::saveFundraiserTeamMember() method - END");
        }

        return fundraiserGroupMember.getId();

    }

    /** {@inheritDoc} */
    public void removeFundraiserTeamMember(
            FundraiserGroupMember fundraiserGroupMember) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::removeFundraiserTeamMember() method - START");
        }

        fundraiserGroupMember.setEndDatetime(new Timestamp(Calendar.getInstance()
                .getTimeInMillis()));
        entityManager.merge(fundraiserGroupMember);

      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::removeFundraiserTeamMember() method - END");
        }

    }

    /** {@inheritDoc} */
    public List<FundraiserGroupMember> fetchTeamMembers(long teamId) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchTeamMembers() method - START");
        }

        final Query fetchTeamMembers =
                entityManager.createQuery(
                        "FROM FundraiserGroupMember ftm where ftm.fundraiserGroup.id = :teamId"
                                + " and endDatetime is null")
                        .setParameter("teamId", teamId);

        final List<FundraiserGroupMember> fundraiserTeamMemberList =
                fetchTeamMembers.getResultList();

      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchTeamMembers() method - END");
        }
        return fundraiserTeamMemberList;

    }

    /** {@inheritDoc} */
    public FundraiserGroup findFundraiserGroupById(long fundraiserTeamId) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupById() method - START");
        }
        FundraiserGroup fundraiserGroup =
                entityManager.find(FundraiserGroup.class, fundraiserTeamId);
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupById() method - END");
        }
        return fundraiserGroup;
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public FundraiserGroup findFundraiserGroupByIdAndMemeber(long fundraiserTeamId, long memberId) {
        final Query fetchTeamMembers =
            entityManager.createQuery(
                    "SELECT fgm.fundraiserGroup FROM FundraiserGroupMember fgm where fgm.fundraiserGroup.id = :teamId "
                            + " and fgm.fundraiser.id = :memberId and fgm.endDatetime is null")
                    .setParameter("teamId", fundraiserTeamId)
                    .setParameter("memberId", memberId);

        final List<FundraiserGroup> fundraiserTeamMemberList =
            fetchTeamMembers.getResultList();
        if(fundraiserTeamMemberList != null && fundraiserTeamMemberList.size() > 0) {
            return fundraiserTeamMemberList.get(0);
        } else {
            return null;    
        }
    }

    /** {@inheritDoc} */
    public FundraiserGroupMember findFundraiserGroupMemberById(long memberId) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupMemberById() method - START");
        }
        FundraiserGroupMember fundraiserGroupMember =
                entityManager.find(FundraiserGroupMember.class, memberId);

      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupMemberById() method - END");
        }
        return fundraiserGroupMember;
    }

    /** {@inheritDoc} */
    public Page fetchPage(String userUrl, String pageUrl) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPage() method - START");
        }
        LOGGER.trace("INPUT :  userUrl" + userUrl + " and pageUrl "
                + pageUrl);
        final Query query =
                entityManager
                        .createQuery(
                                "FROM Page page where page.url = :pageUrl and page.fundraiser.urlDetails.url = :userUrl")
                        .setParameter("userUrl", userUrl);
        query.setParameter("pageUrl", pageUrl);
        Object result = query.getSingleResult();
        if (result != null) {
          	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("JPAFundraiserDAOImpl::fetchPage() method - END");
            }
            return (Page) result;
        }
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPage() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public Page fetchPage(Long id) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPage() method - START");
        }
      	if (LOGGER.isDebugEnabled()) {
      		LOGGER.debug("Fetching page with id: " + id);
      	}
        return entityManager.find(Page.class, id);
    }

    /** {@inheritDoc} */
    public Page savePage(Page page) {
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::savePage() method - START");
        }

        Page newPage = entityManager.merge(page);
      	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::savePage() method - END");
        }
        return newPage;
    }

    /** {@inheritDoc} */
    public Fundraiser fetchFundraiser(Long fundraiserId,
            boolean includeNonOwnerTeamActivities) {
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiser() method - START");
        }
     	
        Fundraiser fundraiser =
                entityManager.find(Fundraiser.class, fundraiserId);
        if (fundraiser != null && includeNonOwnerTeamActivities) {
            fundraiser = addNonLeaderTeamActivities(fundraiser);
        }
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiser() method - END");
        }
        return fundraiser;
    }

    /** {@inheritDoc} */
    public Fundraiser fetchFundraiserByUrl(String url,
            boolean includeNonOwnerTeamActivities) {
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserByUrl() method - START");
        }
        Query query =
                entityManager.createQuery(
                        "FROM Fundraiser f where f.urlDetails.url = :url ")
                        .setParameter("url", url);

        Object result = null;
        try {
            result = query.getSingleResult();
        }
        catch (NoResultException noResultException) {
            LOGGER.error("Error Occured Inside JPAFundraiserDAOImpl::fetchFundraiserByUrl() method ");
            LOGGER.error("INPUT : url =" + url );
            LOGGER.error("Error Details" + noResultException);

            try {
                // the supplied url could also belong to a group, in which case
                // return the team leader details
                query =
                        entityManager
                                .createQuery("SELECT f FROM Fundraiser f, FundraiserGroupMember fgm, FundraiserGroup fg where fgm.fundraiser = f "
                                        + "AND fgm.owner = :ownerInd "
                                        + "AND fgm member of fg.groupMembers "
                                        + "AND fg.urlDetails.url = :url ");
                query.setParameter("ownerInd", true);
                query.setParameter("url", url);

                // url is unique and should therefore have a single result (or
                // no result)
                result = query.getSingleResult();

                // if this is a team activity then the results should only ever include a single page
                FundraiserActivity teamActivity = null;
                for(FundraiserActivity activity : ((Fundraiser)result).getFundraiserActivities()){
                    if(activity.getFundraiserGroup() != null &&
                            activity.getFundraiserGroup().getUrlDetails() != null &&
                            url.equals(activity.getFundraiserGroup().getUrlDetails().getUrl())){
                        teamActivity = activity;
                        break;
                    }
                }
                Set<FundraiserActivity> activities = new HashSet();
                activities.add(teamActivity);
                ((Fundraiser)result).setFundraiserActivities(activities);

            }
            catch (NoResultException ex2) {
                LOGGER.error("Error Occured Inside JPAFundraiserDAOImpl::fetchFundraiserByUrl() method ");
                LOGGER.error("INPUT : url =" + url );
                LOGGER.error("Error Details" + ex2);                
            }
        }
        if (result != null) {
            Fundraiser fundraiser = (Fundraiser) result;
            if (includeNonOwnerTeamActivities) {
                fundraiser = addNonLeaderTeamActivities(fundraiser);
            }
         	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserByUrl() method - END");
            }
            return fundraiser;
        }
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserByUrl() method - END");
        }
        return null;
    }

    // this adds the team events where the fundraiser is not the team
    // leader/owner of the activity
    /** {@inheritDoc} */
    private Fundraiser addNonLeaderTeamActivities(Fundraiser fundraiser) {
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::addNonLeaderTeamActivities() method - START");
        }
        List<FundraiserActivity> nonLeaderTeamActivities =
                findTeamActivitiesWhereNotOwner(fundraiser.getId());
        if (nonLeaderTeamActivities != null
                && nonLeaderTeamActivities.size() > 0) {
            for (FundraiserActivity activity : nonLeaderTeamActivities) {
                fundraiser.getFundraiserActivities().add(activity);
            }
        }
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::addNonLeaderTeamActivities() method - END");
        }
        return fundraiser;
    }

    /** {@inheritDoc} */
    public Fundraiser fetchFundraiserForUser(Long userId) {
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserForUser() method - START");
        }
        final Query query =
                entityManager.createQuery(
                        "FROM Fundraiser f where f.userRole.user = :user ")
                        .setParameter("user",
                                entityManager.find(User.class, userId));
        Object result = query.getSingleResult();
        if (result != null) {
         	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserForUser() method - END");
            }
            return (Fundraiser) result;
        }
     	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserForUser() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public List<FundraiserActivity> findTeamActivitiesWhereNotOwner(
            long fundraiserId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findTeamActivitiesWhereNotOwner() method - START");
        }

        final Query query =
                entityManager
                        .createQuery("SELECT DISTINCT fa FROM Fundraiser f, FundraiserGroupMember fgm, FundraiserGroup fg, FundraiserActivity fa "
                                + "where fgm.fundraiser = :fundraiser "
                                + "AND fgm.owner = :ownerInd "
                                + "AND fgm member of fg.groupMembers "
                                + "AND fg.fundraiserGroupType = :groupType "
                                + "AND fa.fundraiserGroup = fg "
                                + "AND fgm.endDatetime IS NULL " );

        query.setParameter("fundraiser", entityManager.find(Fundraiser.class,
				Long.valueOf(fundraiserId)));
        query.setParameter("ownerInd", false);
        query.setParameter("groupType", entityManager.find(FundraiserGroupType.class,
        		MasterDataCodeConstants.GROUP_TYPE_TEAM));
        Object result = query.getResultList();

        if (result != null) {
        	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("JPAFundraiserDAOImpl::findTeamActivitiesWhereNotOwner() method - END");
            }
            return (List) result;
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findTeamActivitiesWhereNotOwner() method - END");
        }
        return null;
    }

    /** {@inheritDoc} */
    public PageStatus fetchPageStatus(String code) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPageStatus() method - START");
        }
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("Fetching pageStatus with code: " + code);
    	}
        return entityManager.find(PageStatus.class, code);
    }

    /** {@inheritDoc} */
    public PageType fetchPageType(String code) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPageType() method - START");
        }
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("Fetching pageType with code: " + code);
    	}
        return entityManager.find(PageType.class, code);
    }

    /** {@inheritDoc} */
    public PageWidgetType fetchPageWidgetType(String code) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPageWidgetType() method - START");
        }
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("Fetching pageWidgetType with code: " + code);
    	}
        return entityManager.find(PageWidgetType.class, code);
    }

    /** {@inheritDoc} */
    public FundraiserGroupType findFundraiserGroupTypeById(String code) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupTypeById() method - START");
        }
        FundraiserGroupType fundraiserGroupType =
                entityManager.find(FundraiserGroupType.class, code);
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupTypeById() method - END");
        }
        return fundraiserGroupType;
    }

    /** {@inheritDoc} */
    public UrlType findUrlTypeByCode(String code) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findUrlTypeByCode() method - START");
        }
        UrlType urlType = entityManager.find(UrlType.class, code);
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findUrlTypeByCode() method - END");
        }
        return urlType;
    }

    /** {@inheritDoc} */
    public Fundraiser closeFundraiseAccount(Fundraiser fundraiser) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::closeFundraiseAccount() method - START");
        }
        Fundraiser resultFundRaiser = entityManager.merge(fundraiser);
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::closeFundraiseAccount() method - END");
        }
        return resultFundRaiser;
    }

    /** {@inheritDoc} */
    public void removeUrlDetails(UrlDetails urlDetails) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::removeUrlDetails() method - START");
        }
        entityManager.remove(entityManager.merge(urlDetails));
       	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::removeUrlDetails() method - END");
        }
    }

    /** {@inheritDoc} */
    public List<FundraiserGroupPageDetailDTO> findPageDetailsbyFundraiserId(
            Long fundraiserId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findPageDetailsbyFundraiserId() method - START");
        }

        final Query findPageDetailsbyFundraiserIdQuery =
                entityManager.createNamedQuery("findPageDetailsbyFundraiserId");

        findPageDetailsbyFundraiserIdQuery.setParameter("fundraiserId",
                fundraiserId);

        final List<Object[]> searchResult =
                findPageDetailsbyFundraiserIdQuery.getResultList();

        List<FundraiserGroupPageDetailDTO> fundraiserGroupPageDetailsList =
                DAOHelper.createPageDetailsList(searchResult);

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findPageDetailsbyFundraiserId() method - END");
        }

        return fundraiserGroupPageDetailsList;

    }

    /** {@inheritDoc} */
    public void updatePagewithFundraiserGroupID(Long pageId,
            Long fundraiserGroupId, AuditAttributes attributes) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updatePagewithFundraiserGroupID() method - START");
        }
        Page page = null;
        FundraiserGroup fundraiserGroup = null;
        if (pageId != null) {
         page = findPageById(pageId);
        }
        if (fundraiserGroupId != null) {
            fundraiserGroup =
                findFundraiserGroupById(fundraiserGroupId);
        }
        if (page != null) {
                page.getFundraiserActivity().setFundraiserGroup(fundraiserGroup);
                page.getFundraiserActivity().setFundraisingAsIndicator(MasterDataCodeConstants.FUNDRAISER_AS_GROUP);
        DAOHelper.setAuditInformation(attributes, page);
        entityManager.merge(page);
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updatePagewithFundraiserGroupID() method - END");
        }
    }

    /** {@inheritDoc} */
    public Page findPageById(long pageId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findPageById() method - START");
        }
        Page page = entityManager.find(Page.class, pageId);
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findPageById() method - ENd");
        }
        return page;
    }

    /** {@inheritDoc} */
    public List<FundraiserGroupDetailDTO> findFundraiserGroupsbyFundraiserId(
            Long fundraiserId, String fundraiserGroupType) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupsbyFundraiserId() method - START");
        }

        final Query findPageDetailsbyFundraiserIdQuery =
                entityManager
                        .createNamedQuery("findFundraiserGroupsbyFundraiserId");

        findPageDetailsbyFundraiserIdQuery.setParameter("fundraiserId",
                fundraiserId);
        findPageDetailsbyFundraiserIdQuery.setParameter("fundraiserGroupType",
                fundraiserGroupType);

        final List<Object[]> searchResult =
                findPageDetailsbyFundraiserIdQuery.getResultList();

        List<FundraiserGroupDetailDTO> fundraiserGroupDetailsList =
                DAOHelper.createFundraiserGroupDetailsList(searchResult);

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::findFundraiserGroupsbyFundraiserId() method - END");
        }

        return fundraiserGroupDetailsList;

    }

    /** {@inheritDoc} */
    public Fundraiser fetchFundraiserByUserId(Long userId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserByUserId() method - START");
        }

        final Query fetchFundraiserByUserIdQuery =
                entityManager.createNamedQuery("fetchFundraiserByUserId");

        fetchFundraiserByUserIdQuery.setParameter("userId", userId);

        final Fundraiser fundraiser =
                (Fundraiser) fetchFundraiserByUserIdQuery.getSingleResult();

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserByUserId() method - END");
        }

        return fundraiser;
    }

    /** {@inheritDoc} */
    public List<Long> fetchEventIdsByFundraiserId(Long fundraiserId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchEventIdsByFundraiserId() method - START");
        }

        final Query fetchEventIdsByFundraiserIdQuery =
                entityManager.createNamedQuery("fetchEventIdsByFundraiserId");

        fetchEventIdsByFundraiserIdQuery.setParameter("fundraiserId",
                fundraiserId);

        final List<Long> eventIds =
                (List<Long>) fetchEventIdsByFundraiserIdQuery.getResultList();

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchEventIdsByFundraiserId() method - END");
        }

        return eventIds;
    }

    /** {@inheritDoc} */
    public void updateFundraiserActivity(FundraiserActivity fundraiserActivity) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateFundraiserActivity() method - START");
        }
        // entityManager.persist(page);
        entityManager.merge(fundraiserActivity);
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateFundraiserActivity() method - END");
        }
    }

    /** {@inheritDoc} */
    public void updateInvitedEmailAddressWithFundraiserId(String emailAddress,
            Long fundraiserId, AuditAttributes attributes) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateInvitedEmailAddressWithFundraiserId() method - START");
        }

        final Query updateInvitedEmailAddressWithFundraiserIdQuery =
                entityManager
                        .createNamedQuery("updateInvitedEmailAddressWithFundraiserIdQuery");

        updateInvitedEmailAddressWithFundraiserIdQuery.setParameter(
                "fundraiserId", fundraiserId);
        updateInvitedEmailAddressWithFundraiserIdQuery.setParameter(
                "emailAddress", emailAddress);
        updateInvitedEmailAddressWithFundraiserIdQuery
            .setParameter("updatedIPAddress", attributes.getCreatedIPAddress())
            .setParameter("updatedProcess", attributes.getCreatedProcess())
            .setParameter("updatedUser", attributes.getCreatedUser());

        updateInvitedEmailAddressWithFundraiserIdQuery.executeUpdate();

    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateInvitedEmailAddressWithFundraiserId() method - END");
        }
    }

    /** {@inheritDoc} */
    public void updateFundraiserActivityWithGroupIdOrFundraiserId(Long fundraiserActivityId,
            Long fundraiserGroupId, Fundraiser fundraiser, AuditAttributes attributes) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateFundraiserActivityWithGroupId() method - START");
        }
        FundraiserActivity persistedFundraiserActivity = null;
        FundraiserGroup fundraiserGroup = null;
        if (fundraiserActivityId != null) {
            persistedFundraiserActivity =
                fetchFundraiserActivityById(fundraiserActivityId);
        }
        if (fundraiserGroupId != null) {
            fundraiserGroup =   findFundraiserGroupById(fundraiserGroupId);
        }
        if (fundraiser != null && persistedFundraiserActivity != null) {
            persistedFundraiserActivity.setFundraiser(fundraiser);
        }
        if (fundraiserGroup != null && persistedFundraiserActivity != null) {
            persistedFundraiserActivity.setFundraiserGroup(fundraiserGroup);
        }
        if (persistedFundraiserActivity != null) {
        DAOHelper.setAuditInformation(attributes, persistedFundraiserActivity);
        entityManager.merge(persistedFundraiserActivity);
        }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateFundraiserActivityWithGroupId() method - END");
        }

    }

    /** {@inheritDoc} */
    public Location fetchLocationByIsoCode(String isoCode) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchLocationByIsoCode() method - START");
        }
        final Query fetchLocations =
                entityManager.createQuery(
                        "FROM Location l WHERE l.isoCode = :isoCode")
                        .setParameter("isoCode", isoCode);
        final List<Location> locations = fetchLocations.getResultList();
        if (locations != null && locations.size() > 0) {
        	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("JPAFundraiserDAOImpl::fetchLocationByIsoCode() method - END");
            }
            return locations.get(0);
        }
        else {
        	if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("JPAFundraiserDAOImpl::fetchLocationByIsoCode() method - END");
            }
            return null;
        }
    }

    /** {@inheritDoc} */
	public List<String> fetchPageWidgets(Long pageId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchPageWidgets() method - START");
        }
        final Query query =
            entityManager
                    .createQuery("SELECT widgetTypeCode FROM PageWidget pw "
                            + "where pw.pageId = :pageId ");

	    query.setParameter("pageId", pageId);
	    Object result = query.getResultList();
	
	    if (result != null) {
	    	if (LOGGER.isTraceEnabled()) {
	            LOGGER.trace("JPAFundraiserDAOImpl::fetchPageWidgets() method - END");
	        }
	        return (List<String>) result;
	    }
		if (LOGGER.isTraceEnabled()) {
	        LOGGER.trace("JPAFundraiserDAOImpl::fetchPageWidgets() method - END");
	    }
	    return null;
	}

    /** {@inheritDoc} */
    public FundrasierURLDetails fetchFundraiserURLByFundraiserActivityId(Long fundraiserActivityId) {
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserURLByFundraiserActivityId() method - START");
        }
        final String namedQuery = "fetchFundraiserURLByFundraiserActivityId";
        final Query fetchFundrasierURLDetails =
                entityManager.createNamedQuery(namedQuery).setParameter(
                        "fundraiserActivityId", fundraiserActivityId);

        FundrasierURLDetails details = new FundrasierURLDetails();
        List<Object[]> resultSetList = fetchFundrasierURLDetails.getResultList();

        for (Object[] objArray : resultSetList) {
            details.setPageURL((String) objArray[0]);
            details.setUserURL((String) objArray[1]);
        }
       	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchFundraiserURLByFundraiserActivityId() method - END");
        }
        return details;
    }

    /** {@inheritDoc} */
    public UserProfile fetchUserProfileByUserId(Long userId){
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchUserProfileByUserId() method - START");
        }
        final Query fetchUserProfile =
            entityManager.createQuery(
                    "FROM UserProfile up WHERE up.user.id = :userId")
                    .setParameter("userId", userId);
	    final UserProfile userProfile = (UserProfile) fetchUserProfile.getSingleResult();
		if (LOGGER.isTraceEnabled()) {
	        LOGGER.trace("JPAFundraiserDAOImpl::fetchUserProfileByUserId() method - END");
	    }
        return userProfile;
    }
   
    /** {@inheritDoc} */
    public void updateUserProfile(UserProfile userProfile){
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateUserProfile() method - START");
        }
        
        if (userProfile != null) {
            entityManager.merge(userProfile);
            }
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateUserProfile() method - END");
        }
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.giving.dao.FundraiserDAO#fetchRandomNetworkActivities(java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<FundraiserActivity> fetchRandomNetworkActivities(Long networkId, Long numberOfResults, Long fundraiserToExclude){
    	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::fetchRandomNetworkActivities() method - START");
        }
    	if(fundraiserToExclude != null){
    	    final Query fetchActiveMember =
    	        entityManager.createQuery(
                    "SELECT ftm.fundraiser.id FROM FundraiserGroupMember ftm where ftm.fundraiserGroup.id = :networkId"
                            + " and ftm.fundraiser.id=:fundraiserToExclude and endDatetime is null ")
                    .setParameter("networkId", networkId)
                    .setParameter("fundraiserToExclude", fundraiserToExclude);
    	    List<Long> groupMemeberResults = fetchActiveMember.getResultList();
    	    //check if this fundraiser is active on this network
    	    if(groupMemeberResults != null && groupMemeberResults.size() > 0) {
    	        //get all other fundraiser active on this network
    	        final Query fetchActiveTeamMembers =
                    entityManager.createQuery(
                        "SELECT ftm.fundraiser.id FROM FundraiserGroupMember ftm where ftm.fundraiserGroup.id = :networkId"
                                + " and ftm.fundraiser.id<>:fundraiserToExclude and endDatetime is null ")
                        .setParameter("networkId", networkId)
                        .setParameter("fundraiserToExclude", fundraiserToExclude);
                groupMemeberResults = fetchActiveTeamMembers.getResultList();
                if(groupMemeberResults != null && groupMemeberResults.size() > 0) {
                    StringBuilder queryString = new StringBuilder();
                    //queryString.append("FROM FundraiserActivity fa where fa.fundraiserGroup.id = :networkId ");
                    queryString.append("SELECT OBJECT(fa) FROM FundraiserActivity fa where fa.fundraiserGroup.id = :networkId ");
                    queryString.append(" and fa.fundraiser.id IN (:groupMemeberResults) ");
                    queryString.append(" order by rand()");
                    Query query = entityManager.createQuery(queryString.toString());
                    query.setParameter("networkId", networkId);
                    query.setParameter("groupMemeberResults", groupMemeberResults);
                    //query.setParameter("fundraiserToExclude", fundraiserToExclude);
                    query.setMaxResults(numberOfResults.intValue());
                    List<FundraiserActivity> results = query.getResultList();
                    return results;
                } else {
                    return new ArrayList<FundraiserActivity>();
                }
    	    } else {
    	        return new ArrayList<FundraiserActivity>();
    	    }
    	} else {
            StringBuilder queryString = new StringBuilder();
            queryString.append("FROM FundraiserActivity fa where fa.fundraiserGroup.id = :networkId ");
            queryString.append(" order by rand()");
            Query query = entityManager.createQuery(queryString.toString());
            query.setParameter("networkId", networkId);
            query.setMaxResults(numberOfResults.intValue());
            List<FundraiserActivity> results = query.getResultList();
            return results;
    	}
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.giving.dao.FundraiserDAO#fetchFundraiserActivityByTeamUrl(java.lang.String)
     */
    public FundraiserActivity fetchFundraiserActivityByTeamUrl(String teamUrl) {

        FundraiserActivity teamActivity = null;
        
        try {
            Query query = entityManager.createQuery(
                "SELECT f FROM Fundraiser f, FundraiserGroupMember fgm, FundraiserGroup fg "
                + "where fgm.fundraiser = f "
                + "AND fgm.owner = :ownerInd "
                + "AND fgm member of fg.groupMembers "
                + "AND fg.urlDetails.url = :url ");
            query.setParameter("ownerInd", true);
            query.setParameter("url", teamUrl);

            // url is unique and should therefore have a single result (or
            // no result)
            Fundraiser fundraiser = (Fundraiser)query.getSingleResult();

            // As this is a team activity the result should only ever include a single page
            Set<FundraiserActivity> fundraiserActivities = fundraiser.getFundraiserActivities();
            if ( fundraiserActivities.size() > 1 ) {
                LOGGER.warn("Expected one FundraiserActivity for teamUrl " + teamUrl 
                    + " but found " +  fundraiserActivities.size());    
            }

            for(FundraiserActivity activity : fundraiser.getFundraiserActivities()) {
                if(activity.getFundraiserGroup() != null &&
                        activity.getFundraiserGroup().getUrlDetails() != null &&
                        teamUrl.equals(activity.getFundraiserGroup().getUrlDetails().getUrl())){
                    teamActivity = activity;
                    break;
                }
            }

            if ( teamActivity == null ) {
                LOGGER.warn("No FundraiserActivity found for teamUrl " + teamUrl);
            }
        }
        catch (NoResultException noResultException) {
            LOGGER.warn("No Fundraiser found for teamUrl " + teamUrl);    
            LOGGER.info(noResultException);    
        }

        return teamActivity;
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.giving.dao.FundraiserDAO#updatePageStatus(java.lang.Long, java.lang.String)
     */
    public void updatePageStatus(Long pageId, String pageStatus) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAPaymentDAOImpl::saveSummaryInvoiceNumber() method - START");
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("pageId =" + pageId );
            LOGGER.debug("pageStatus =" + pageStatus );
        }
        final int updateCount =
                entityManager.createNamedQuery(UPDATE_PAGE_BY_ID)
                .setParameter("pageStatus",
                        pageStatus).setParameter("pageId", pageId)
                        .executeUpdate();
        if (updateCount == 0) {
            final String errorMessage = "No Records are updated.";
            LOGGER.error(errorMessage);
            LOGGER.error("pageId =" + pageId );
            LOGGER.error("pageStatus = " + pageStatus );
            throw new IllegalStateException(errorMessage);
        }
        else if (updateCount > 0) {
            LOGGER.debug("Records updated successfully");
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAPaymentDAOImpl::saveSummaryInvoiceNumber() method - END");
        }
    }

	public void updateDonationFailedIndicator(List<Long> donationIds) {
		
		if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateDonationFailedIndicator() - START");
        }
		
        for(Long id:donationIds){
        	Donation donation=entityManager.find(Donation.class, id);
        	if( donation != null) {
	        	donation.setDonationFailedInd(MasterDataCodeConstants.YES);
	        	entityManager.merge(donation);
        	}
        }
        
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAFundraiserDAOImpl::updateDonationFailedIndicator() - END");
        }
		
	}
    
    

    /**
     * {@inheritDoc}
     */
    public BigDecimal fetchFundraiserActivityTotalDonations(Long fundraiserActivityId) {

        // TODO: make it a named query
        final Query fetchTotalDonations =
            entityManager.createNativeQuery(
                "SELECT IFNULL(SUM(d.GROSS_AMOUNT),0) as totalFundRaised FROM DONATION d WHERE d.FUNDRAISER_ACTIVITY_ID = :fundraiserActivityId and d.DONATION_FAILED_IND = 'N'"
             )
             .setParameter("fundraiserActivityId", fundraiserActivityId);
        Number totalDonations = (Number)fetchTotalDonations.getSingleResult();
        return new BigDecimal(totalDonations.toString());
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal fetchFundraiserActivityTotalDonationsTaxBack(
        Long fundraiserActivityId) {

        // TODO: make it a named query
        final Query fetchTotalTaxRelief =
            entityManager.createNativeQuery(
                "SELECT IFNULL(SUM(fad.TAX_BACK_AMOUNT),0) as totalTaxBack " +
                "FROM FUNDRAISER_ACTIVITY_DONATION fad LEFT OUTER JOIN DONATION d ON fad.DONATION_ID = d.ID " +
                "WHERE fad.FUNDRAISER_ACTIVITY_ID = :fundraiserActivityId and d.DONATION_FAILED_IND = 'N'"
             )
             .setParameter("fundraiserActivityId", fundraiserActivityId);
        Number totalTaxRelief = (Number)fetchTotalTaxRelief.getSingleResult();
        return new BigDecimal(totalTaxRelief.toString());
    }
    
    /**
     * {@inheritDoc}
     */
    public List<FundraiserActivity> fetchFundraiserActivityForMemberOfTeam(Long fundraiserId) {
        List<FundraiserActivity> results =
                entityManager.createNamedQuery("fetchFundraiserActivityForMemberOfTeam")
                    .setParameter("fundraiserId", fundraiserId).getResultList();
        return results;
    }

    /**
     * TRAC 673.
     * There are UI actions (e.g. browser back button) which case duplicate records to be created.
     * This method checks whether the incoming split records are the same as existing ones, or are different.
     * If different, then the existing split is deleted, and new split created for the fundraiser activity.
     * @param currActivity Current Fundraiser activity details
     * @param newActivity new Fundraiser activity details
     * @return boolean result
     */
    private boolean checkFundraiserActivitySplitChanged(FundraiserActivity currActivity,
                                                        FundraiserActivity newActivity) {
        boolean result = false;
        if (currActivity == null || newActivity == null) {
            return result;
        }
        // If the new or existing split is empty, no need to remove anything.
        if (currActivity.getFundraisingCharitySplit().isEmpty() || newActivity.getFundraisingCharitySplit().isEmpty()) {
            return result;
        }
        // Compare existing and new split details. Comparision is on the basis of Charity ID and split percentage.
        int matchCtr = 0;
        for (FundraisingCharitySplit currSplit : currActivity.getFundraisingCharitySplit()) {
            for (FundraisingCharitySplit newSplit : newActivity.getFundraisingCharitySplit()) {
                if (currSplit.getCharity().getId().compareTo(newSplit.getCharity().getId()) == 0 &&
                        currSplit.getSplitPercentage().compareTo(newSplit.getSplitPercentage()) == 0) {
                    matchCtr++;
                }
            }
        }

        // If the matched records count differs from either the new or the old split, then there is a difference.
        // So we must delete the old split and create the new split. Return TRUE in this case.
        if (!(matchCtr == currActivity.getFundraisingCharitySplit().size())
                || !(matchCtr == newActivity.getFundraisingCharitySplit().size())) {
            result = true;
        }
        LOGGER.debug("JPAFundraiserDAOImpl::checkFundraiserActivitySplitChanged() Matched count = " + matchCtr
                + ", Existing size: " +  currActivity.getFundraisingCharitySplit().size()
                + ", New Size = " + newActivity.getFundraisingCharitySplit().size() + ", result = " + result);

        return result;
    }
}
