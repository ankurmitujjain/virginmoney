package com.virginmoneygiving.giving.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;

import org.apache.log4j.Logger;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.constants.ServiceConstants;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityCustomFieldSubset;
import com.virginmoneygiving.giving.domain.CharityCustomFieldValueSubset;
import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.giving.domain.Donor;
import com.virginmoneygiving.giving.domain.DonorDetailsDVOSubset;
import com.virginmoneygiving.giving.domain.EmailAddress;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.EventDetailsDVOSubset;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.giving.domain.FundraiserDetailsDVOSubset;
import com.virginmoneygiving.giving.domain.FundraiserPageDetailsDVOSubset;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.PersonEmailAddress;
import com.virginmoneygiving.giving.domain.PersonTelephoneNumber;
import com.virginmoneygiving.giving.domain.PersonalAddress;
import com.virginmoneygiving.giving.domain.SearchCriteriaSubset;
import com.virginmoneygiving.giving.domain.TelephoneNumber;

/**
 * The Class JPACharityReportingHelper contains methods to get Donors, Events, Fundraisers, FundRaiserPage based
 * on search criteria and fetch Donors, Events, Fundraisers, FundRaiserPage according to charityId.
 * 
 * @author SunilH
 */
public class JPACharityReportingHelper {

    /** Logger for logging. */
    private static final Logger LOGGER =
            Logger.getLogger(JPACharityReportingHelper.class);

    /** An Entity Manager Instance. */
    private EntityManager entityManager;

    /** isCodesAllocated. */
    private boolean isCodesAllocated = false;

    /** fundraiserActivityList. */
    private List<FundraiserActivity> fundraiserActivityList;

    /** fundraisers list. */
    private List<Fundraiser> fundraiserList;

    /** events list. */
    private List<Event> eventList;

    /** donors list. */
    private List<Donor> donorList;

    /** charity. */
    private Charity charity;

    /**
     * This method get the DonorList based on search criteria.
     * 
     * @param searchCriteria the search criteria
     * 
     * @return List of donors
     */
    public List getDonors(SearchCriteriaSubset searchCriteria) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getDonors() method - START");
        }
        Long charityId = Long.valueOf(searchCriteria.getCharityId());
        String month = searchCriteria.getMonth();
        String year = searchCriteria.getYear();
        String selectType = searchCriteria.getSelectType();
        String entityType = searchCriteria.getEntityType();
        String fieldLabelArray[] = getFieldLabels(charityId, entityType);
        List recordList = new ArrayList();
        donorList = getDonorList(charityId);

        if (donorList != null && donorList.size() > 0) {
        	if (LOGGER.isDebugEnabled()) {
        		LOGGER.debug("Found donorList size:" + donorList.size());
        	}
            for (Donor donor : donorList) {
                DonorDetailsDVOSubset donorDetailsDVOSubset =
                        new DonorDetailsDVOSubset();
                Long donorId = donor.getId();
                donorDetailsDVOSubset.setId("" + donorId);
                // populate donor personal details
                if (LOGGER.isDebugEnabled()) {
	                LOGGER.trace("Fetching donor personal details for donorId :"
	                        + donorId);
                }
                Person person =
                        (Person) (entityManager
                                .createNamedQuery("fetchPersonDetailsByDonorId")
                                .setParameter("donorId", donor.getId()))
                                .getSingleResult();
                if (person != null) {
                	if (LOGGER.isDebugEnabled()) {
                		LOGGER.trace("name :" + person.getForename());
                	}
                    donorDetailsDVOSubset.setName(person.getTitle() + " "
                            + person.getForename() + " " + person.getSurname());
                    Set<PersonalAddress> addressSet = person.getPersonalAddresses();
                    for (PersonalAddress donorAddress : addressSet) {
                        donorDetailsDVOSubset.setAddress(donorAddress.getAddress());
                    }
                    final Set<PersonEmailAddress> personEmailAddresses = person.getPersonEmailAddresses();
                    final Set<EmailAddress> emailAddressSet = new HashSet<EmailAddress>();
                    if (personEmailAddresses != null) {
                        for (PersonEmailAddress personEmailAddress : personEmailAddresses) {
                            emailAddressSet.add(personEmailAddress.getEmailAddress());
                        }
                    }
                    StringBuffer email = new StringBuffer("");
					for (EmailAddress emailAddress : emailAddressSet) {
						email.append(emailAddress.getEmailAddress() + " ");
					}
                    donorDetailsDVOSubset.setEmail(formatFieldLabel(email.toString()));

                    donorDetailsDVOSubset.setDateOfBirth(applyDateformat(person
                            .getDobDay(), person.getDobMonth(), person
                            .getDobYear()));

                    final Set<PersonTelephoneNumber> personTelephoneNumbers = person.getPersonTelephoneNumbers();
                    final Set<TelephoneNumber> telephoneNumbers = new HashSet<TelephoneNumber>();
                    if (personTelephoneNumbers != null) {
                        for (PersonTelephoneNumber personTelephoneNumber : personTelephoneNumbers) {
                            telephoneNumbers.add(personTelephoneNumber.getTelephoneNumber());
                        }
                    }
                    StringBuffer phoneNumbers = new StringBuffer();
                    for (TelephoneNumber telephoneNumber : telephoneNumbers) {
                        phoneNumbers.append(telephoneNumber.getPresentationNumber())
                        			.append(" ");
                    }
                    donorDetailsDVOSubset.setPhoneNumber(phoneNumbers.toString());
                }
                // populate donor custom field labels and values
                if (fieldLabelArray != null && fieldLabelArray.length > 0) {
                    donorDetailsDVOSubset
                            .setCustomFieldLabel1(fieldLabelArray[0]);
                    donorDetailsDVOSubset
                            .setCustomFieldLabel2(fieldLabelArray[1]);
                    donorDetailsDVOSubset
                            .setCustomFieldLabel3(fieldLabelArray[2]);
                    donorDetailsDVOSubset
                            .setCustomFieldLabel4(fieldLabelArray[3]);
                    donorDetailsDVOSubset
                            .setCustomFieldLabel5(fieldLabelArray[4]);
                }
                String fieldValueArray[] =
                        getFieldValues(charityId,
                                MasterDataCodeConstants.CustomFieldTypes_DONOR,
                                donorId);

                if (fieldValueArray != null && fieldValueArray.length > 0) {
                    donorDetailsDVOSubset
                            .setCustomFieldvalue1(fieldValueArray[0]);
                    donorDetailsDVOSubset
                            .setCustomFieldvalue2(fieldValueArray[1]);
                    donorDetailsDVOSubset
                            .setCustomFieldvalue3(fieldValueArray[2]);
                    donorDetailsDVOSubset
                            .setCustomFieldvalue4(fieldValueArray[3]);
                    donorDetailsDVOSubset
                            .setCustomFieldvalue5(fieldValueArray[4]);
                }
                double donationsMade =
                        getDonationsMade(charityId, donorId, month, year);
                if (donationsMade != 0d) {
                    donorDetailsDVOSubset.setDonationsMade("" + donationsMade);
                    if (selectType != null
                            && ServiceConstants.REPORTING_CODES_NOT_ALLOCATED
                                    .equals(selectType)) {
                        if (!isCodesAllocated()) {
                            recordList.add(donorDetailsDVOSubset);
                        }
                    }
                    else {
                        recordList.add(donorDetailsDVOSubset);
                    }
                }

            }

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getDonors() method - END");
        }
        return recordList;

    }

    /**
     * This method get the EventList based on search criteria.
     * 
     * @param searchCriteria the search criteria
     * 
     * @return List of events
     */
    public List getEvents(SearchCriteriaSubset searchCriteria) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getEvents() method - START");
        }
        Long charityId = Long.valueOf(searchCriteria.getCharityId());
        String month = searchCriteria.getMonth();
        String year = searchCriteria.getYear();
        String selectType = searchCriteria.getSelectType();
        String entityType = searchCriteria.getEntityType();
        String fieldLabelArray[] = getFieldLabels(charityId, entityType);
        List recordList = new ArrayList();
        charity = getCharity(charityId);
        eventList = getEventList(charityId);

        if (eventList != null && eventList.size() > 0) {
            for (Event event : eventList) {

                Timestamp createdDateTime = event.getCreatedDateTime();
                String str[] = ("" + createdDateTime).split("-");
                if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("Year=" + str[0]);
	                LOGGER.debug("Month=" + str[1]);
                }
                if (Integer.parseInt(year) == Integer.parseInt(str[0])
                        && Integer.parseInt(month) == (Integer.parseInt(str[1]) - 1)) {
                    EventDetailsDVOSubset eventDetailsDVO =
                            new EventDetailsDVOSubset();
                    Long eventId = event.getId();
                    eventDetailsDVO.setId("" + eventId);
                    eventDetailsDVO.setName(event.getName());
                    if (event.getLocation() != null) {
                        eventDetailsDVO.setLocation(event.getLocation()
                                .getDescription());
                    }
                    if (event.getEventStatus() != null) {
                        eventDetailsDVO.setType(event.getEventStatus()
                                .getDescription());
                    }
                    Timestamp eventDatetime = event.getEventDatetime();
                    if (eventDatetime != null) {
                        str = ("" + eventDatetime).split("-");
                        eventDetailsDVO.setEventDate(str[2].substring(0, 2)
                                + "/" + str[1] + "/" + str[0]);
                    }
                    eventDetailsDVO.setOrganiser(charity.getName());
                    eventDetailsDVO.setFundraisers(""
                            + getFundraiserCount(charityId, eventId));
                    // populate field labels and values
                    if (fieldLabelArray != null) {
                        eventDetailsDVO
                                .setCustomFieldLabel1(fieldLabelArray[0]);
                        eventDetailsDVO
                                .setCustomFieldLabel2(fieldLabelArray[1]);
                        eventDetailsDVO
                                .setCustomFieldLabel3(fieldLabelArray[2]);
                        eventDetailsDVO
                                .setCustomFieldLabel4(fieldLabelArray[3]);
                        eventDetailsDVO
                                .setCustomFieldLabel5(fieldLabelArray[4]);
                    }
                    String fieldValueArray[] =
                            getFieldValues(
                                    charityId,
                                    MasterDataCodeConstants.CustomFieldTypes_EVENT,
                                    eventId);
                    if (fieldValueArray != null && fieldValueArray.length > 0) {
                        eventDetailsDVO
                                .setCustomFieldvalue1(fieldValueArray[0]);
                        eventDetailsDVO
                                .setCustomFieldvalue2(fieldValueArray[1]);
                        eventDetailsDVO
                                .setCustomFieldvalue3(fieldValueArray[2]);
                        eventDetailsDVO
                                .setCustomFieldvalue4(fieldValueArray[3]);
                        eventDetailsDVO
                                .setCustomFieldvalue5(fieldValueArray[4]);
                    }
                    
                    String fieldCopyArray[] =
                        getCopyValues(
                                charityId,
                                MasterDataCodeConstants.CustomFieldTypes_EVENT,
                                eventId);
                    
                    if (fieldCopyArray != null && fieldCopyArray.length > 0) {
						eventDetailsDVO.setEventCopy1(fieldCopyArray[0]);
						eventDetailsDVO.setEventCopy2(fieldCopyArray[1]);
						eventDetailsDVO.setEventCopy3(fieldCopyArray[2]);
						eventDetailsDVO.setEventCopy4(fieldCopyArray[3]);
						eventDetailsDVO.setEventCopy5(fieldCopyArray[4]);
					}
                    
                    
                    if (selectType != null
                            && ServiceConstants.REPORTING_CODES_NOT_ALLOCATED
                                    .equals(selectType)) {
                        if (!isCodesAllocated()) {
                            recordList.add(eventDetailsDVO);
                        }
                    }
                    else {
                        recordList.add(eventDetailsDVO);
                    }
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getEvents() method - END");
        }
        return recordList;

    }

    /**
     * This method get the FundraiserList based on search criteria.
     * 
     * @param searchCriteria the search criteria
     * 
     * @return List of fundraisers
     */
    public List getFundraisers(SearchCriteriaSubset searchCriteria) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraisers() method - START");
        }
        Long charityId = Long.valueOf(searchCriteria.getCharityId());
        String month = searchCriteria.getMonth();
        String year = searchCriteria.getYear();
        String selectType = searchCriteria.getSelectType();
        String entityType = searchCriteria.getEntityType();
        String fieldLabelArray[] = getFieldLabels(charityId, entityType);
        List recordList = new ArrayList();
        fundraiserList = getFundraiserList(charityId);

        if (fundraiserList != null && fundraiserList.size() > 0) {
            for (Fundraiser fundraiser : fundraiserList) {
                Timestamp createdDateTime = fundraiser.getCreatedDateTime();
                String str[] = ("" + createdDateTime).split("-");
                if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("Year=" + str[0]);
	                LOGGER.debug("Month=" + str[1]);
                }
                if (Integer.parseInt(year) == Integer.parseInt(str[0])
                        && Integer.parseInt(month) == (Integer.parseInt(str[1]) - 1)) {
                    FundraiserDetailsDVOSubset fundraiserDetailsDVOSubset =
                            new FundraiserDetailsDVOSubset();
                    Long fundraiserId = fundraiser.getId();
                    fundraiserDetailsDVOSubset.setId("" + fundraiserId);
                    // populate fundraiser personal details
                    if (LOGGER.isDebugEnabled()) {
	                    LOGGER
	                            .debug("Fetching donor personal details for donorId :"
	                                    + fundraiserId);
                    }
                    Person person =
                            (Person) (entityManager
                                    .createNamedQuery("fetchPersonDetailsByFundraiserId")
                                    .setParameter("fundraiserId", fundraiser
                                            .getId())).getSingleResult();
                    if (person != null) {
                    	if (LOGGER.isDebugEnabled()) {
                    		LOGGER.debug("name :" + person.getForename());
                    	}
                        fundraiserDetailsDVOSubset.setName(person.getTitle()
                                + " " + person.getForename() + " "
                                + person.getSurname());
                        Set<PersonalAddress> addressSet = person.getPersonalAddresses();
                        for (PersonalAddress address : addressSet) {
                            fundraiserDetailsDVOSubset.setAddress(address.getAddress());
                        }
                        final Set<PersonEmailAddress> personEmailAddresses = person.getPersonEmailAddresses();
                        final Set<EmailAddress> emailAddressSet = new HashSet<EmailAddress>();
                        if (personEmailAddresses != null) {
                            for (PersonEmailAddress personEmailAddress : personEmailAddresses) {
                                emailAddressSet.add(personEmailAddress.getEmailAddress());
                            }
                        }
                        String email = new String();
                        if (emailAddressSet != null && emailAddressSet.size() >= 1){
                        	
                        }
                        
                        for (EmailAddress emailAddress : emailAddressSet) {
							if ((emailAddressSet.size() > 1 && emailAddress
									.getEmailAddressType()
									.equals(
											MasterDataCodeConstants.EMAIL_ADDRESS_TYPE_CONTACT))
									|| (emailAddressSet.size() == 1)) {

								email = emailAddress.getEmailAddress();
							}

						}
                        fundraiserDetailsDVOSubset
                                .setEmail(formatFieldLabel(email.toString()));

                        fundraiserDetailsDVOSubset
                                .setDateOfBirth(applyDateformat(person
                                        .getDobDay(), person.getDobMonth(),
                                        person.getDobYear()));

                        final Set<PersonTelephoneNumber> personTelephoneNumbers = person.getPersonTelephoneNumbers();
                        final Set<TelephoneNumber> telephoneNumbers = new HashSet<TelephoneNumber>();
                        if (personTelephoneNumbers != null) {
                            for (PersonTelephoneNumber personTelephoneNumber : personTelephoneNumbers) {
                                telephoneNumbers.add(personTelephoneNumber.getTelephoneNumber());
                            }
                        }
                        StringBuffer phoneNumbers = new StringBuffer();
                        for (TelephoneNumber telephoneNumber : telephoneNumbers) {
                            phoneNumbers.append(telephoneNumber.getPresentationNumber())
                                        .append(" ");
                        }
                        fundraiserDetailsDVOSubset.setPhoneNumber(phoneNumbers.toString());
                    }
                    fundraiserDetailsDVOSubset.setFundraiserPages(""
                            + fetchFundraiserPages(charityId, fundraiserId));
                    fundraiserDetailsDVOSubset.setFundraiserFunds(""
                            + getFundraiserFunds(charityId, fundraiserId));
                    // populate fundraiser custom field labels and values
                    fundraiserDetailsDVOSubset
                            .setCustomFieldLabel1(fieldLabelArray[0]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldLabel2(fieldLabelArray[1]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldLabel3(fieldLabelArray[2]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldLabel4(fieldLabelArray[3]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldLabel5(fieldLabelArray[4]);

                    String fieldValueArray[] =
                            getFieldValues(
                                    charityId,
                                    MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER,
                                    fundraiserId);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldvalue1(fieldValueArray[0]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldvalue2(fieldValueArray[1]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldvalue3(fieldValueArray[2]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldvalue4(fieldValueArray[3]);
                    fundraiserDetailsDVOSubset
                            .setCustomFieldvalue5(fieldValueArray[4]);

                    if (selectType != null
                            && ServiceConstants.REPORTING_CODES_NOT_ALLOCATED
                                    .equals(selectType)) {
                        if (!isCodesAllocated()) {
                            recordList.add(fundraiserDetailsDVOSubset);
                        }
                    }
                    else {
                        recordList.add(fundraiserDetailsDVOSubset);
                    }
                }
            }

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraisers() method - END");
        }
        return recordList;

    }

    /**
     * This method get the fundraiser ActivityList based on search criteria.
     * 
     * @param searchCriteria the search criteria
     * 
     * @return List of fundraiserActivities
     */
    public List getFundraiserPages(SearchCriteriaSubset searchCriteria) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserPages() method - START");
        }
        Long charityId = Long.valueOf(searchCriteria.getCharityId());
        String month = searchCriteria.getMonth();
        String year = searchCriteria.getYear();
        String selectType = searchCriteria.getSelectType();
        String entityType = searchCriteria.getEntityType();
        String fieldLabelArray[] = getFieldLabels(charityId, entityType);
        List recordList = new ArrayList();
        fundraiserActivityList = getFundraiserActivityList(charityId);
        if (fundraiserActivityList != null && fundraiserActivityList.size() > 0) {
        	if (LOGGER.isDebugEnabled()) {
	            LOGGER.debug("Found fundraiserActivityList size:"
	                    + fundraiserActivityList.size());
        	}
            for (FundraiserActivity fundraiserActivity : fundraiserActivityList) {
                Timestamp createdDateTime =
                        fundraiserActivity.getCreatedDateTime();
                String str[] = ("" + createdDateTime).split("-");
            	if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("Year=" + str[0]);
	                LOGGER.debug("Month=" + str[1]);
            	}
                if (Integer.parseInt(year) == Integer.parseInt(str[0])
                        && Integer.parseInt(month) == (Integer.parseInt(str[1]) - 1)) {

                    FundraiserPageDetailsDVOSubset fundraiserPageDetailsDVOSubset =
                            new FundraiserPageDetailsDVOSubset();
                    Long fundraiserActivityId = fundraiserActivity.getId();
                    if (LOGGER.isDebugEnabled()) {
	                    LOGGER
	                            .debug("FundraiserActivity Id"
	                                    + fundraiserActivityId);
                    }
                    fundraiserPageDetailsDVOSubset.setId(""
                            + fundraiserActivityId);
                    fundraiserPageDetailsDVOSubset
                            .setName(getFundraiserName(fundraiserActivity
                                    .getFundraiser().getId()));
                    Page page = fundraiserActivity.getFundraiserPage();
                    if (page != null) {
                        createdDateTime = page.getCreatedDateTime();
                        if (createdDateTime != null) {
                            str = ("" + createdDateTime).split("-");
                            fundraiserPageDetailsDVOSubset
                                    .setPageCreated(str[2].substring(0, 2)
                                            + "/" + str[1] + "/" + str[0]);
                        }

                        fundraiserPageDetailsDVOSubset.setPageTitle(page
                                .getTitle());
                        if (page.getPageType() != null) {
                            fundraiserPageDetailsDVOSubset.setPageType(page
                                    .getPageType().getDescription());
                        }
                        if (page.getPageStatus() != null) {
                            fundraiserPageDetailsDVOSubset.setPageStatus(page
                                    .getPageStatus().getDescription());
                        }
                    }
                    if (fundraiserActivity.getFundraisingTarget() != null) {
                        fundraiserPageDetailsDVOSubset.setTargetAmount(""
                                + fundraiserActivity.getFundraisingTarget()
                                        .doubleValue());
                    }
                    if (fundraiserActivity.getTotalDonations() != null) {
                        fundraiserPageDetailsDVOSubset.setAmountRaised(""
                                + fundraiserActivity.getTotalDonations()
                                        .doubleValue());
                    }
                    if (fundraiserActivity.getFundraisingReason() != null) {
                        fundraiserPageDetailsDVOSubset
                                .setFundraisingActivity(fundraiserActivity
                                        .getFundraisingReason()
                                        .getDescription());
                    }
                    if (fundraiserActivity.getEvent() != null) {
                        fundraiserPageDetailsDVOSubset
                                .setEvent(fundraiserActivity.getEvent()
                                        .getName());
                    }
                    if (fieldLabelArray != null && fieldLabelArray.length > 0) {
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldLabel1(fieldLabelArray[0]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldLabel2(fieldLabelArray[1]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldLabel3(fieldLabelArray[2]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldLabel4(fieldLabelArray[3]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldLabel5(fieldLabelArray[4]);
                    }
                    String fieldValueArray[] =
                            getFieldValues(
                                    charityId,
                                    MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY,
                                    fundraiserActivityId);
                    if (fieldValueArray != null && fieldValueArray.length > 0) {
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldvalue1(fieldValueArray[0]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldvalue2(fieldValueArray[1]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldvalue3(fieldValueArray[2]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldvalue4(fieldValueArray[3]);
                        fundraiserPageDetailsDVOSubset
                                .setCustomFieldvalue5(fieldValueArray[4]);
                    }

                    if (selectType != null
                            && ServiceConstants.REPORTING_CODES_NOT_ALLOCATED
                                    .equals(selectType)) {
                        if (!isCodesAllocated()) {
                            recordList.add(fundraiserPageDetailsDVOSubset);
                        }
                    }
                    else {
                        recordList.add(fundraiserPageDetailsDVOSubset);
                    }
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserPages() method - END");
        }
        return recordList;
    }

    /**
     * This method retrieve field values against charity,entityType & donorId.
     * 
     * @param charityId the charity id
     * @param entityType the entity type
     * @param id the id
     * 
     * @return fieldValueArray
     */

    public String[] getFieldValues(Long charityId, String entityType, Long id) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFieldValues() method - START");
        }
        setCodesAllocated(false);
        String fieldValueArray[] = new String[] { "", "", "", "", "" };

            List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetList =
                    getCharityCustomFieldSubset(charityId, entityType, id);
            if (charityCustomFieldValueSubsetList != null) {
                int count = 0;
                for (CharityCustomFieldValueSubset charityCustomFieldValueSubset : charityCustomFieldValueSubsetList) {
                    fieldValueArray[count++] =
                            charityCustomFieldValueSubset.getFieldValue();
                }

                for (String str : fieldValueArray) {
                    if (!"".equals(str.trim())) {
                        setCodesAllocated(true);
                        break;
                    }
                }
            }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFieldValues() method - END");
        }
        return fieldValueArray;
    }

    /**
     * This method get CharityCustomFieldSubset mapped to CUSTOM_FIELD_VALUES.
     * database table.
     * 
     * @param charityId the charity id
     * @param entityType the entity type
     * @param id the id
     * 
     * @return CharityCustomFieldSubset object
     */
    public List<CharityCustomFieldValueSubset> getCharityCustomFieldSubset(
            Long charityId, String entityType, Long id)  {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getCharityCustomFieldSubset() method - START");
        }
        List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetListAll =
                new ArrayList<CharityCustomFieldValueSubset>();
        List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetList =
                new ArrayList<CharityCustomFieldValueSubset>();
        List<CharityCustomFieldSubset> charityCustomFieldSubset =
                (entityManager.createNamedQuery(
                        "fetchCharityCustomFieldsByCode").setParameter(
                        "charityId", charityId).setParameter(
                        "customFieldTypeCode", entityType)).getResultList();
        List<Long> charityCustomFieldIdList = new ArrayList<Long>();

        if (charityCustomFieldSubset != null) {


                for (CharityCustomFieldSubset charityCustomField : charityCustomFieldSubset) {
                    Long charityCustomFieldId = charityCustomField.getId();
                    charityCustomFieldIdList.add(charityCustomFieldId);
                    if (LOGGER.isDebugEnabled()) {
	                    LOGGER.debug("charityCustomFieldId >>> "
	                            + charityCustomFieldId);
                    }
                    if (MasterDataCodeConstants.CustomFieldTypes_DONOR
                            .equals(entityType)) {
                        charityCustomFieldValueSubsetList =
                                (entityManager.createNamedQuery(
                                        "fetchCustomFieldValuesByDonor")
                                        .setParameter("charityCustomFieldId",
                                                charityCustomFieldId)
                                        .setParameter("donorId", id))
                                        .getResultList();
                    }
                    else if (MasterDataCodeConstants.CustomFieldTypes_EVENT
                            .equals(entityType)) {

                        charityCustomFieldValueSubsetList =
                                (entityManager.createNamedQuery(
                                        "fetchCustomFieldValuesByEvent")
                                        .setParameter("charityCustomFieldId",
                                                charityCustomFieldId)
                                        .setParameter("eventId", id))
                                        .getResultList();

                    }
                    else if (MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER
                            .equals(entityType)) {
                        charityCustomFieldValueSubsetList =
                                (entityManager.createNamedQuery(
                                        "fetchCustomFieldValuesByFundraiser")
                                        .setParameter("charityCustomFieldId",
                                                charityCustomFieldId)
                                        .setParameter("fundraiserId", id))
                                        .getResultList();
                    }
                    else if (MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY
                            .equals(entityType)) {
                        charityCustomFieldValueSubsetList =
                                (entityManager
                                        .createNamedQuery(
                                                "fetchCustomFieldValuesByFundraiserActivity")
                                        .setParameter("charityCustomFieldId",
                                                charityCustomFieldId)
                                        .setParameter("fundraiserActivityId",
                                                id)).getResultList();
                    }
                    if (charityCustomFieldValueSubsetList != null
                            && charityCustomFieldValueSubsetList.size() > 0) {
                        charityCustomFieldValueSubsetListAll
                                .add(charityCustomFieldValueSubsetList.get(0));
                    }

                }
                if (charityCustomFieldValueSubsetListAll.size() == 0) {
                    charityCustomFieldValueSubsetListAll =
                            new ArrayList<CharityCustomFieldValueSubset>();
                    // for(CharityCustomFieldValueSubset
                    // charityCustomFieldValueSubset:charityCustomFieldValueSubsetList){
                    int count = 0;
                    CharityCustomFieldValueSubset charityCustomFieldValueSubset =
                            null;
                    while (count < 5) {
                        charityCustomFieldValueSubset =
                                new CharityCustomFieldValueSubset();
                        if (count < charityCustomFieldIdList.size()) {
                            charityCustomFieldValueSubset
                                    .setCharityCustomFieldId(charityCustomFieldIdList
                                            .get(count));
                        }
                        charityCustomFieldValueSubset
                                .setStartDateTime(new Timestamp(Calendar
                                        .getInstance().getTimeInMillis()));
                        charityCustomFieldValueSubset.setFieldValue("");

                        if (MasterDataCodeConstants.CustomFieldTypes_DONOR
                                .equals(entityType)) {
                            charityCustomFieldValueSubset.setDonorId(id);
                        }
                        else if (MasterDataCodeConstants.CustomFieldTypes_EVENT
                                .equals(entityType)) {
                            charityCustomFieldValueSubset.setEventId(id);
                        }
                        else if (MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER
                                .equals(entityType)) {
                            charityCustomFieldValueSubset.setFundraiserId(id);
                        }
                        else if (MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY
                                .equals(entityType)) {
                            charityCustomFieldValueSubset
                                    .setFundraiserActivityId(id);
                        }
                        entityManager.merge(charityCustomFieldValueSubset);
                        charityCustomFieldValueSubsetListAll
                                .add(charityCustomFieldValueSubset);
                        count++;
                    }
                }

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getCharityCustomFieldSubset() method - END");
        }
        return charityCustomFieldValueSubsetListAll;
    }

    /**
     * Sets the entity manager.
     * 
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Checks if is codes allocated.
     * 
     * @return the isCodesAllocated
     */
    private boolean isCodesAllocated() {
        return isCodesAllocated;
    }

    /**
     * Sets the codes allocated.
     * 
     * @param isCodesAllocated the isCodesAllocated to set
     */
    private void setCodesAllocated(boolean isCodesAllocated) {
        this.isCodesAllocated = isCodesAllocated;
    }

    /**
     * This method retrives the fundraisers count for given charity id and event
     * id.
     * 
     * @param charityId the charity id
     * @param eventId the event id
     * 
     * @return fundraisersCount
     */
    private int getFundraiserCount(Long charityId, Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserCount() method - START");
        }
        int fundraisersCount = 0;

        fundraiserActivityList = getFundraiserActivityList(charityId);
        if (fundraiserActivityList != null
                && fundraiserActivityList.size() > 0) {
            for (FundraiserActivity fundraiserActivity : fundraiserActivityList) {
                if (fundraiserActivity.getEvent() != null
                        && fundraiserActivity.getEvent().getId().equals(eventId)) {
                    fundraisersCount++;
                }
            }
        }
        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("fundraisersCount:" + fundraisersCount);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserCount() method - END");
        }
        return fundraisersCount;
    }

    /**
     * This method get the total funds raised by fundraiser under given charity
     * id.
     * 
     * @param charityId the charity id
     * @param fundraiserId the fundraiser id
     * 
     * @return the fundraiser funds
     */
    
    private double getFundraiserFunds(Long charityId, Long fundraiserId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserFunds() method - START");
        }
        BigDecimal fundraiserFunds = new BigDecimal(0);
        fundraiserFunds = retriveFundraiserFunds(charityId, fundraiserId);
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("FundraiserFunds :" + fundraiserFunds);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserFunds() method - END");
        }
        return fundraiserFunds.doubleValue();
    }
    
    
    /**
     * This method gets the list of fundraising activities.
     * 
     * @param charityId the charity id
     * 
     * @return FundraiserActivity
     */
    private BigDecimal retriveFundraiserFunds(Long charityId, Long fundraiserId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserFunds() method - START");
        }
        BigDecimal fund = new BigDecimal(0);
        if (charityId != null && fundraiserId != null) {
        	
        	String query = "SELECT  IFNULL(SUM(D.GROSS_AMOUNT), 0) "
			        		+" FROM FUNDRAISER_ACTIVITY FRA, DONATION D, FUNDRAISER FR "
			        		+" WHERE FR.ID = FRA.FUNDRAISER_ID "
			        		+"       AND D.FUNDRAISER_ACTIVITY_ID = FRA.ID "
			        		+"       AND FR.FUNDRAISER_STATUS_CODE != 'FAKE' "
			        		+"       AND FRA.ID "
			        		+"        		IN "
			        		+"                (SELECT FCSP.FUNDRAISER_ACTIVITY_ID "
			        		+"                FROM FUNDRAISING_CHARITY_SPLIT FCSP "
			        		+"                WHERE FCSP.CHARITY_ID = :charityId) "
			        		+" AND FRA.FUNDRAISER_ID = :fundraiserId ";
        	fund =
                   (BigDecimal) (entityManager
                            .createNativeQuery(query)
                            .setParameter("charityId", charityId)
                            .setParameter("fundraiserId", fundraiserId))
                            .getSingleResult();

            if (fund != null) {
            	if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("Fund of fundraiserId : " + fundraiserId
							+ "  with charityId : " + charityId + "   : "
							+ fund.doubleValue());
            	}
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserFunds() method - END");
        }
        return fund;

    }

    /**
     * This method gets the list of fundraising activities.
     * 
     * @param charityId the charity id
     * 
     * @return FundraiserActivity
     */
    private List<FundraiserActivity> getFundraiserActivityList(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserActivityList() method - START");
        }
        if (fundraiserActivityList == null) {
            fundraiserActivityList =
                    (entityManager
                            .createNamedQuery("fetchFundraiserActivitiesByCharityId")
                            .setParameter("charityId", charityId))
                            .getResultList();

            if (fundraiserActivityList != null) {
            	if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("fundraiserActivityList :"
	                        + fundraiserActivityList.size());
            	}
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserActivityList() method - END");
        }
        return fundraiserActivityList;

    }

    /**
     * This method get fundraisers for given charity id.
     * 
     * @param charityId the charity id
     * 
     * @return list of fundraisers
     */
    private List<Fundraiser> getFundraiserList(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserList() method - START");
        }

        if (fundraiserList == null) {
            fundraiserList =
                    (entityManager
                            .createNamedQuery("fetchFundraisersByCharityId")
                            .setParameter("charityId", charityId))
                            .getResultList();
            if (fundraiserList != null) {
            	if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("fundraiserList :" + fundraiserList.size());
            	}
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserList() method - END");
        }
        return fundraiserList;
    }

    /**
     * This method get events for given charity id.
     * 
     * @param charityId the charity id
     * 
     * @return list of events
     */
    private List<Event> getEventList(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getEventList() method - START");
        }
        if (eventList == null) {
            eventList =
                    (entityManager.createNamedQuery("fetchEventsByCharityId")
                            .setParameter("charityId", charityId))
                            .getResultList();
            if (eventList != null) {
            	if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("eventList :" + eventList.size());
            	}
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getEventList() method - END");
        }
        return eventList;

    }

    /**
     * This method get donor for given charity id.
     * 
     * @param charityId the charity id
     * 
     * @return list of donors
     */
    private List<Donor> getDonorList(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getDonorList() method - START");
        }
        if (donorList == null) {
            donorList =
                    (entityManager.createNamedQuery("fetchDonorsByCharityId")
                            .setParameter("charityId", charityId))
                            .getResultList();
            if (donorList != null) {
            	if (LOGGER.isDebugEnabled()) {
	                LOGGER.debug("donorList :" + donorList.size());
            	}
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getDonorList() method - END");
        }
        return donorList;
    }

    /**
     * This method get charity for given charity id.
     * 
     * @param charityId the charity id
     * 
     * @return charity
     */
    private Charity getCharity(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getCharity() method - START");
        }
        if (charity == null) {
            charity =
                    (Charity) (entityManager
                            .createNamedQuery("fetchCharityById").setParameter(
                            "charityId", charityId)).getSingleResult();
            if (charity != null) {
            	if (LOGGER.isDebugEnabled()) {
            		LOGGER.debug("charity :" + charity.getName());
            	}
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getCharity() method - END");
        }
        return charity;
    }

    /**
     * This method gets the donations made by donor for given charity id.
     * 
     * @param charityId the charity id
     * @param donorId the donor id
     * @param month the month
     * @param year the year
     * 
     * @return the donations made
     */
    private double getDonationsMade(Long charityId, Long donorId, String month,
            String year) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getDonationsMade() method - START");
        }
        double donationsMade = 0d;

            List<Donation> donationList =
                    (entityManager.createNamedQuery(
                            "fetchDonationByDonorAndCharityId").setParameter(
                            "donorId", donorId).setParameter("charityId",
                            charityId)).getResultList();
            for (Donation donation : donationList) {
                Timestamp createdDateTime = donation.getCreatedDateTime();
                String str[] = ("" + createdDateTime).split("-");
                if (Integer.parseInt(year) == Integer.parseInt(str[0])
                        && Integer.parseInt(month) == (Integer.parseInt(str[1]) - 1)) {
                    donationsMade += donation.getGrossAmount().doubleValue();
                }
            }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("donationsMade :" + donationsMade);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getDonationsMade() method - END");
        }
        return donationsMade;
    }

    /**
     * This method retrieve fieldLabelArray against charity id.
     * 
     * @param charityId the charity id
     * @param entityType the entity type
     * 
     * @return fieldLabelArray
     */
    public String[] getFieldLabels(Long charityId, String entityType) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFieldLabels() method - START");
        }
        String fieldLabelArray[] = new String[] { "", "", "", "", "" };
        int count = 0;
        List<CharityCustomFieldSubset> charityCustomFieldList =
                fetchCharityCustomFieldLabels(charityId);
        if (charityCustomFieldList != null) {
            for (CharityCustomFieldSubset charityCustomFieldSubset : charityCustomFieldList) {
                String customFieldTypeCode =
                        charityCustomFieldSubset.getCustomFieldTypeCode();
                if (customFieldTypeCode != null
                        && customFieldTypeCode.equals(entityType)) {
                    fieldLabelArray[count++] =
                            charityCustomFieldSubset.getFieldLabel();
                    ;
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFieldLabels() method - END");
        }
        return fieldLabelArray;
    }

    /**
     * This method set Default CustomFieldLabels.
     * 
     * @param charityId the charity id
     * 
     * @return List of CharityCustomFieldSubset
     */
    public List<CharityCustomFieldSubset> setDefaultCustomFieldLabels(
            Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::setDefaultCustomFieldLabels() method - START");
        }
        List<CharityCustomFieldSubset> charityCustomFieldSubsetList =
                new ArrayList();

            StringTokenizer tempStringTokenizer = null;
            CharityCustomFieldSubset charityCustomFieldSubset = null;

            tempStringTokenizer =
                    new StringTokenizer(
                            MasterDataCodeConstants.CustomFieldTypes_DONOR_DEFAULT_LABEL,
                            MasterDataCodeConstants.DELIM);
            while (tempStringTokenizer.hasMoreTokens()) {
                charityCustomFieldSubset = new CharityCustomFieldSubset();
                charityCustomFieldSubset
                        .setCustomFieldTypeCode(MasterDataCodeConstants.CustomFieldTypes_DONOR);
                charityCustomFieldSubset.setFieldLabel(tempStringTokenizer
                        .nextToken());
                charityCustomFieldSubset.setCharityId(charityId);
                charityCustomFieldSubset.setStartDateTime(new Timestamp(
                        Calendar.getInstance().getTimeInMillis()));
                entityManager.merge(charityCustomFieldSubset);
                charityCustomFieldSubsetList.add(charityCustomFieldSubset);
            }

            tempStringTokenizer =
                    new StringTokenizer(
                            MasterDataCodeConstants.CustomFieldTypes_EVENT_DEFAULT_LABEL,
                            MasterDataCodeConstants.DELIM);
            while (tempStringTokenizer.hasMoreTokens()) {
                charityCustomFieldSubset = new CharityCustomFieldSubset();
                charityCustomFieldSubset
                        .setCustomFieldTypeCode(MasterDataCodeConstants.CustomFieldTypes_EVENT);
                charityCustomFieldSubset.setFieldLabel(tempStringTokenizer
                        .nextToken());
                charityCustomFieldSubset.setCharityId(charityId);
                charityCustomFieldSubset.setStartDateTime(new Timestamp(
                        Calendar.getInstance().getTimeInMillis()));
                entityManager.merge(charityCustomFieldSubset);
                charityCustomFieldSubsetList.add(charityCustomFieldSubset);
            }

            tempStringTokenizer =
                    new StringTokenizer(
                            MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_DEFAULT_LABEL,
                            MasterDataCodeConstants.DELIM);
            while (tempStringTokenizer.hasMoreTokens()) {
                charityCustomFieldSubset = new CharityCustomFieldSubset();
                charityCustomFieldSubset
                        .setCustomFieldTypeCode(MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER);
                charityCustomFieldSubset.setFieldLabel(tempStringTokenizer
                        .nextToken());
                charityCustomFieldSubset.setCharityId(charityId);
                charityCustomFieldSubset.setStartDateTime(new Timestamp(
                        Calendar.getInstance().getTimeInMillis()));
                entityManager.merge(charityCustomFieldSubset);
                charityCustomFieldSubsetList.add(charityCustomFieldSubset);
            }

            tempStringTokenizer =
                    new StringTokenizer(
                            MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY_DEFAULT_LABEL,
                            MasterDataCodeConstants.DELIM);
            while (tempStringTokenizer.hasMoreTokens()) {
                charityCustomFieldSubset = new CharityCustomFieldSubset();
                charityCustomFieldSubset
                        .setCustomFieldTypeCode(MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY);
                charityCustomFieldSubset.setFieldLabel(tempStringTokenizer
                        .nextToken());
                charityCustomFieldSubset.setCharityId(charityId);
                charityCustomFieldSubset.setStartDateTime(new Timestamp(
                        Calendar.getInstance().getTimeInMillis()));
                entityManager.merge(charityCustomFieldSubset);
                charityCustomFieldSubsetList.add(charityCustomFieldSubset);
            }
        if (LOGGER.isInfoEnabled()) {
        	LOGGER.info("New reporting customfields inserted in database ");
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::setDefaultCustomFieldLabels() method - END");
        }
        return charityCustomFieldSubsetList;
    }

    /**
     * Retrieves list of Custom fields matching the search criteria (by charity
     * id).
     * 
     * @param charityId the charity id
     * 
     * @return List of charity Custom Fields.
     */
    public List<CharityCustomFieldSubset> fetchCharityCustomFieldLabels(
            Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::fetchCharityCustomFieldLabels() method - START");
        }
        List<CharityCustomFieldSubset> charityCustomFieldList =
                (entityManager.createNamedQuery("fetchCharityCustomFields")
                        .setParameter("charityId", charityId)).getResultList();
        if (charityCustomFieldList != null
                && charityCustomFieldList.size() == 0) {
            charityCustomFieldList = setDefaultCustomFieldLabels(charityId);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::fetchCharityCustomFieldLabels() method - END");
        }
        return charityCustomFieldList;
    }

    /**
     * This method returns the number of fundraiser pages.
     * 
     * @param charityId the charity id
     * @param fundraiserId the fundraiser id
     * 
     * @return the int
     */
    private int fetchFundraiserPages(Long charityId, Long fundraiserId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::fetchFundraiserPages() method - START");
        }
        int pageCount = 0;

            List<Page> pages =  (entityManager.createNamedQuery("fetchFundraiserPages")
            							.setParameter("charityId", charityId)
            							.setParameter("fundraiserId", fundraiserId))
            							.getResultList();
            
            if (pages != null) {
                pageCount = pages.size();
            }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("FundraiserPages :" + pageCount);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::fetchFundraiserPages() method - END");
        }
        return pageCount;
    }

    /**
     * This method is used to get person full name for given fundraiser id.
     * 
     * @param fundraiserId the fundraiser id
     * 
     * @return fundraiser name
     */
    private String getFundraiserName(Long fundraiserId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserName() method - START");
        }
        String fundraiserName = "";

            Person person =
                    (Person) (entityManager
                            .createNamedQuery("fetchPersonDetailsByFundraiserId")
                            .setParameter("fundraiserId", fundraiserId))
                            .getSingleResult();
            if (person != null) {
                fundraiserName =
                        person.getTitle() + " " + person.getForename() + " "
                                + person.getSurname();
            }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("fundraiserName :" + fundraiserName);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::getFundraiserName() method - END");
        }
        return fundraiserName;
    }

    /**
     * This method is used to get the date in dd/mm/yyyy.
     * 
     * @param day the day
     * @param month the month
     * @param year the year
     * 
     * @return date in string format dd/mm/yyyy
     */
    private String applyDateformat(Integer day, Integer month, Integer year) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::applyDateformat() method - START");
        }
        String date = "";
        if (day != null && month != null && year != null) {
            date += (day.intValue() <= 9) ? "0" : "";
            date += day.intValue() + "/";
            date += (month.intValue() <= 9) ? "0" : "";
            date += month.intValue() + "/";
            date += year.intValue();
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("date :" + date);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::applyDateformat() method - END");
        }
        return date;
    }

    /**
     * This method formats the field labels by adding space if field label
     * length exceeds 10 characters.
     * 
     * @param fieldLabel the field label
     * 
     * @return formated field label
     */
    private String formatFieldLabel(String fieldLabel) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::formatFieldLabel() method - START");
        }
        if (fieldLabel != null && fieldLabel.indexOf(" ") != -1
                && fieldLabel.length() > 18) {
            fieldLabel =
                    fieldLabel.substring(0, 18) + " "
                            + fieldLabel.substring(18);
        }
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("fieldLabel :" + fieldLabel);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::formatFieldLabel() method - END");
        }
        return fieldLabel;

    }

    /**
     * Consolidate custom field list.
     * 
     * @param charityCustomFieldList the charity custom field list
     */
    public static void consolidateCustomFieldList(
            List<CharityCustomFieldSubset> charityCustomFieldList) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::consolidateCustomFieldList() method - START");
        }
        if (!charityCustomFieldList.isEmpty()) {
            StringBuffer donFieldLabel = new StringBuffer();
            StringBuffer eventFieldLabel = new StringBuffer();
            StringBuffer fundFieldLabel = new StringBuffer();
            StringBuffer actFieldLabel = new StringBuffer();

            CharityCustomFieldSubset donCustomFieldSubset =
                    new CharityCustomFieldSubset();
            CharityCustomFieldSubset eventCustomFieldSubset =
                    new CharityCustomFieldSubset();
            CharityCustomFieldSubset fundCustomFieldSubset =
                    new CharityCustomFieldSubset();
            CharityCustomFieldSubset actCustomFieldSubset =
                    new CharityCustomFieldSubset();

            boolean donCustomFieldSubsetUpdated = false;
            boolean eventCustomFieldSubsetUpdated = false;
            boolean fundCustomFieldSubsetUpdated = false;
            boolean actCustomFieldSubsetUpdated = false;

            for (CharityCustomFieldSubset charityCustomFieldSubset : charityCustomFieldList) {

                if (charityCustomFieldSubset.getCustomFieldTypeCode()
                        .equalsIgnoreCase(
                                MasterDataCodeConstants.CustomFieldTypes_DONOR)) {
                    donFieldLabel.append(
                            charityCustomFieldSubset.getFieldLabel()).append(
                            MasterDataCodeConstants.DELIM);

                    if (!donCustomFieldSubsetUpdated) {
                        donCustomFieldSubsetUpdated =
                                setCustomFieldValues(charityCustomFieldSubset,
                                        donCustomFieldSubset);
                    }
                }
                if (charityCustomFieldSubset.getCustomFieldTypeCode()
                        .equalsIgnoreCase(
                                MasterDataCodeConstants.CustomFieldTypes_EVENT)) {
                    eventFieldLabel.append(
                            charityCustomFieldSubset.getFieldLabel()).append(
                            MasterDataCodeConstants.DELIM);

                    if (!eventCustomFieldSubsetUpdated) {
                        eventCustomFieldSubsetUpdated =
                                setCustomFieldValues(charityCustomFieldSubset,
                                        eventCustomFieldSubset);
                    }
                }
                if (charityCustomFieldSubset
                        .getCustomFieldTypeCode()
                        .equalsIgnoreCase(
                                MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER)) {
                    fundFieldLabel.append(
                            charityCustomFieldSubset.getFieldLabel()).append(
                            MasterDataCodeConstants.DELIM);

                    if (!fundCustomFieldSubsetUpdated) {
                        fundCustomFieldSubsetUpdated =
                                setCustomFieldValues(charityCustomFieldSubset,
                                        fundCustomFieldSubset);
                    }
                }
                if (charityCustomFieldSubset
                        .getCustomFieldTypeCode()
                        .equalsIgnoreCase(
                                MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY)) {
                    actFieldLabel.append(
                            charityCustomFieldSubset.getFieldLabel()).append(
                            MasterDataCodeConstants.DELIM);

                    if (!actCustomFieldSubsetUpdated) {
                        actCustomFieldSubsetUpdated =
                                setCustomFieldValues(charityCustomFieldSubset,
                                        actCustomFieldSubset);
                    }
                }
            }
            charityCustomFieldList.clear();

            if (donFieldLabel.length() > MasterDataCodeConstants.DELIM.length()) {
                donFieldLabel =
                        new StringBuffer(donFieldLabel.substring(0,
                                donFieldLabel.length()
                                        - MasterDataCodeConstants.DELIM
                                                .length()));
            }

            if (eventFieldLabel.length() > MasterDataCodeConstants.DELIM
                    .length()) {
                eventFieldLabel =
                        new StringBuffer(eventFieldLabel.substring(0,
                                eventFieldLabel.length()
                                        - MasterDataCodeConstants.DELIM
                                                .length()));
            }

            if (fundFieldLabel.length() > MasterDataCodeConstants.DELIM
                    .length()) {
                fundFieldLabel =
                        new StringBuffer(fundFieldLabel.substring(0,
                                fundFieldLabel.length()
                                        - MasterDataCodeConstants.DELIM
                                                .length()));
            }

            if (actFieldLabel.length() > MasterDataCodeConstants.DELIM.length()) {
                actFieldLabel =
                        new StringBuffer(actFieldLabel.substring(0,
                                actFieldLabel.length()
                                        - MasterDataCodeConstants.DELIM
                                                .length()));
            }

            donCustomFieldSubset.setFieldLabel(donFieldLabel.toString());
            eventCustomFieldSubset.setFieldLabel(eventFieldLabel.toString());
            fundCustomFieldSubset.setFieldLabel(fundFieldLabel.toString());
            actCustomFieldSubset.setFieldLabel(actFieldLabel.toString());

            charityCustomFieldList.add(donCustomFieldSubset);
            charityCustomFieldList.add(eventCustomFieldSubset);
            charityCustomFieldList.add(fundCustomFieldSubset);
            charityCustomFieldList.add(actCustomFieldSubset);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::consolidateCustomFieldList() method - END");
        }
    }

    /**
     * Sets the custom field values.
     * 
     * @param sourceCustomFieldSubset the source custom field subset
     * @param destinationCustomFieldSubset the destination custom field subset
     * 
     * @return true, if sets the custom field values
     */
    private static boolean setCustomFieldValues(
            CharityCustomFieldSubset sourceCustomFieldSubset,
            CharityCustomFieldSubset destinationCustomFieldSubset) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::setCustomFieldValues() method - START");
        }
        destinationCustomFieldSubset.setCharityId(sourceCustomFieldSubset
                .getCharityId());
        destinationCustomFieldSubset.setCreatedDateTime(sourceCustomFieldSubset
                .getCreatedDateTime());
        destinationCustomFieldSubset
                .setCreatedIPAddress(sourceCustomFieldSubset
                        .getCreatedIPAddress());
        destinationCustomFieldSubset.setCreatedProcess(sourceCustomFieldSubset
                .getCreatedProcess());
        destinationCustomFieldSubset.setCreatedUser(sourceCustomFieldSubset
                .getCreatedUser());
        destinationCustomFieldSubset
                .setCustomFieldTypeCode(sourceCustomFieldSubset
                        .getCustomFieldTypeCode());
        destinationCustomFieldSubset.setId(sourceCustomFieldSubset.getId());
        destinationCustomFieldSubset.setStartDateTime(sourceCustomFieldSubset
                .getStartDateTime());
        destinationCustomFieldSubset.setUpdatedDateTime(sourceCustomFieldSubset
                .getUpdatedDateTime());
        destinationCustomFieldSubset
                .setUpdatedIPAddress(sourceCustomFieldSubset
                        .getUpdatedIPAddress());
        destinationCustomFieldSubset.setUpdatedProcess(sourceCustomFieldSubset
                .getUpdatedProcess());
        destinationCustomFieldSubset.setUpdatedUser(sourceCustomFieldSubset
                .getUpdatedUser());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityReportingHelper::setCustomFieldValues() method - END");
        }
        return true;
    }
    
    
    public List getEvtCodesForCharity(Long charityId) {

		List<CharityCustomFieldSubset> charityCustomFieldSubset = (entityManager
				.createNamedQuery("fetchCharityCustomFieldsByCode")
				.setParameter("charityId", charityId).setParameter(
				"customFieldTypeCode",MasterDataCodeConstants.CustomFieldTypes_EVENT)).getResultList();

		return charityCustomFieldSubset;

	}

	public  List getFacCodesForCharity(Long charityId) {

		List<CharityCustomFieldSubset> charityCustomFieldSubset = (entityManager
				.createNamedQuery("fetchCharityCustomFieldsByCode")
				.setParameter("charityId", charityId).setParameter(
				"customFieldTypeCode",MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY)).getResultList();

		return charityCustomFieldSubset;

	}
    
     /**
     * This method retrieve field copy against charity,entityType & donorId.
     * 
     * @param charityId the charity id
     * @param entityType the entity type
     * @param id the id
     * 
     * @return fieldCopyArray
     */

    public String[] getCopyValues(Long charityId, String entityType, Long id) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER
					.trace("JPACharityReportingHelper::getCopyValues() method - START");
		}
		setCodesAllocated(false);
		String fieldCopyArray[] = new String[] { "", "", "", "", "" };

		List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetList = getCharityCustomFieldSubset(
				charityId, entityType, id);
		if (charityCustomFieldValueSubsetList != null) {
			int count = 0;
			for (CharityCustomFieldValueSubset charityCustomFieldValueSubset : charityCustomFieldValueSubsetList) {

				if (charityCustomFieldValueSubset.getFieldCopy() != null
						&& MasterDataCodeConstants.YES
								.equalsIgnoreCase(charityCustomFieldValueSubset
										.getFieldCopy())) {

					fieldCopyArray[count++] = MasterDataCodeConstants.TRUE;

				} else {

					fieldCopyArray[count++] = MasterDataCodeConstants.FALSE;

				}

			}

		}
		if (LOGGER.isTraceEnabled()) {
			LOGGER
					.trace("JPACharityReportingHelper::getCopyValues() method - END");
		}
		return fieldCopyArray;
	}

    
}
