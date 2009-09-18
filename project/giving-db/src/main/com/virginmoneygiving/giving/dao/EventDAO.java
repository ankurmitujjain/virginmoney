package com.virginmoneygiving.giving.dao;

import java.math.BigDecimal;
import java.util.List;

import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.BankAccountType;
import com.virginmoneygiving.giving.domain.CharitableActivity;
import com.virginmoneygiving.giving.domain.CharityAdminDetailsVO;
import com.virginmoneygiving.giving.domain.CharityAdministrator;
import com.virginmoneygiving.giving.domain.CharityEventAdministrator;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.EventActivity;
import com.virginmoneygiving.giving.domain.EventFeeDetails;
import com.virginmoneygiving.giving.domain.EventFeeDetailsVO;
import com.virginmoneygiving.giving.domain.EventHomepageDetailsDVO;
import com.virginmoneygiving.giving.domain.FundraisingInfoDVO;
import com.virginmoneygiving.giving.domain.OwnOrganisedEventsDVO;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventParameter;

/**
 * Interface for Event DAO funtionality.
 * 
 * @author Saurabh Herwadkar
 * @author Siva Kumar -added fetchOtherOrganisedEvents ,
 * fetchOwnedOrganisedEvents, saveCharitableActivityDetails,
 * saveEventFeeDetails, saveEventActivityDeatils,
 * saveCharityEventAdministrator, deleteEventActivitiesForEvent ,
 * fetchCharityAdministrators , fetchBankAccountDetails
 */
public interface EventDAO {

    /**
     * Method to get nine random Fundraisers associated with an event.
     * 
     * @param eventId of
     * type String
     * @param charityId of type Long
     * 
     * @return FundraisingInfoDVO
     */
    FundraisingInfoDVO fetchNineRandomFundraisers(final Long eventId, final Long charityId);
        
    /**
     * Method to get last nine Fundraisers associated with an event.
     *  
     * @param eventId of
     * type String
     * @param charityId of type Long
     * 
     * @return FundraisingInfoDVO
     */
    FundraisingInfoDVO fetchTheLatestFundraisers(final Long eventId, final Long charityId);

    /**
     * This method is used to get all events that can be joined  by a charity.
     * Search Criteria:
     * Published event
     * Open to all event
     * Event which you are not part as creator or as donation split option
     * 
     * @param searchEventParameters of type SearchFundraisingEventParameter
     * 
     * @return list of Events
     */
    List<Event> fetchOtherOrganisedEvents(
            SearchFundraisingEventParameter searchEventParameters);

    /**
     * This method fetch all organized events created by vng operations.
     * 
     * @param statusList of type List of Strings
     * @param userId of type Long
     * 
     * @return list of OwnOrganisedEventsDVO
     */
    List<OwnOrganisedEventsDVO> fetchOperationalOrganisedEvents(List<String> statusList, Long userId);

    /**
     * Get the Owned Organised Events with given details.
     * 
     * @param statusList of type List String
     * @param charityId of type Long
     * @param userId of type Long
     * 
     * @return list of OwnOrganisedEventsDVO
     */
    List<OwnOrganisedEventsDVO> fetchOwnedOrganisedEvents(
            final List<String> statusList, final Long charityId, final Long userId);


    /**
     * Get the Owned four latest Organised Events with given details.
     * 
     * @param statusList of type List String
     * @param charityId of type Long
     * 
     * @return list of OwnOrganisedEventsDVO
     */
    List<OwnOrganisedEventsDVO> fetchOwnedFourLatestOrganisedEvents(
            final List<String> statusList, final Long charityId);

    /**
     * Fetch the list of charity administrator for an event.
     * 
     * @param charitableActivityId of tyep Long
     * 
     * @return list of charity event administrators satisfying criteria
     */
    List<CharityAdminDetailsVO> fetchCharityEventAdminList(
            Long charitableActivityId);

    /**
     * Fetch the list of charity administrator.
     * 
     * @param charityId of type Long
     * 
     * @return list of charity administrators satisfying criteria
     */
    List<CharityAdminDetailsVO> fetchCharityAdminList(Long charityId);

    /**
     * FetchEvent home page details.
     * 
     * @param eventId of type Long
     * @param charityId of type Long
     * 
     * @return list of EventHomepageDetailsDVO
     */
    EventHomepageDetailsDVO getEventDetails(final Long eventId, final Long charityId);


    /**
     * Save the event details and it relation values.
     * 
     * @param eventDetails of type event object
     * 
     * @return id after saving the recod in data base.
     */
    Event saveEventDetails(final Event eventDetails);

    /**
     * Fetch the list of event fee details.
     * 
     * @param eventId of type long
     * 
     * @return list of event fees satisfying criteria
     */
    EventFeeDetailsVO fetchEventFeeList(final Long eventId);

    /**
     * This service is used to fetch event fee status i.e whether fundraiser can
     * pay event registration fee online.
     * 
     * @param eventId of type Long
     * 
     * @return Matching event object if satisfies critera
     */
    Event fetchEventFeeStatus(final Long eventId);

    /**
     * This method for save the CharitableActivityDetails.
     * 
     * @param charitableActivityDetails of type CharitableActivity
     * 
     * @return object of type CharitableActivity
     */
    CharitableActivity saveCharitableActivityDetails(
            final CharitableActivity charitableActivityDetails);

    /**
     * This method for save the EventFeeDetails.
     * 
     * @param eventFeeDetails of type EventFeeDetails
     * 
     * @return object of type EventFeeDetails.
     */
    EventFeeDetails saveEventFeeDetails(final EventFeeDetails eventFeeDetails);

    /**
     * This method for save  the EventActivity Details.
     * 
     * @param eventActivityDetails of type EventActivity
     * 
     * @return object of type EventActivity
     */
    EventActivity saveEventActivityDeatils(final EventActivity eventActivityDetails);

    /**
     * This method for save  the CharityEventAdmininstrator details.
     * 
     * @param charityEventAdministrator of type CharityEvnetAdministarator
     * 
     * @return object of type CharityEventAdministrator
     */
    CharityEventAdministrator saveCharityEventAdministrator(
            final CharityEventAdministrator charityEventAdministrator);

    /**
     * This  method for to delete the EventActivities for particular event.
     * 
     * @param eventId of type Long
     */
    void deleteEventActivitiesForEvent(final Long eventId);

    /**
     * This method used for to fetch the charityAdministartors.
     * 
     * @param charityAdminId of type Long
     * 
     * @return object of type CharityAdministrator
     */
    CharityAdministrator fetchCharityAdministrators(final Long charityAdminId);

    /**
     * This method is used for to fetch the BankAccount Details.
     * 
     * @param bankAccountId of type Long
     * 
     * @return object of type BankAccount
     */
    BankAccount fetchBankAccountDetails(final Long bankAccountId);

    /**
     * This method for to fetch the cancelEvents.
     * 
     * @param eventId of type Long
     * @param charityId of type Long
     * 
     * @return CharitableActivity object
     */
    CharitableActivity fetchCancelEventRight(final Long eventId, final Long charityId);

    /**
     * This method gets event domain object of the given id.
     * 
     * @param eventId - id of the event
     * 
     * @return - event domain object
     */
    Event fetchEventDetail(final Long eventId);

    /**
     * This method gets CharitableActivity domain object of the given id.
     * 
     * @param charitableActivityId - id of the CharitableActivity
     * 
     * @return - charitableActivity domain object
     */
    CharitableActivity fetchCharitableActivityDetail(final Long charitableActivityId);

    /**
     * This method is to fetch the list of event fee details for particular charitableActivity id.
     * 
     * @param charitableActivityId - id of the CharitableActivity
     * 
     * @return List of EventFeeDetails.
     */
    List<EventFeeDetails> fetchEventFeeDetails(final Long charitableActivityId);

    /**
     * This methods gets delete the eventFee Details of the given id.
     * 
     * @param eventFeeId -id of EventFee
     * @param auditAttributes the audit attributes
     */
     void deleteEventFeeDetails(final Long eventFeeId, AuditAttributes auditAttributes);

    /**
     * This method used for to fetch the charityEventAdmininstrators for particular charitableActivity id.
     * 
     * @param charitableActivityId -id of the CharitableActivity
     * 
     * @return List of CharityEventAdministrator.
     */
    List<CharityEventAdministrator> fetchCharityEventAdministrators(final Long charitableActivityId);

    /**
     * This methods gets all event charity admin list with email etc.
     * 
     * @param eventId -id of event
     * 
     * @return List of CharityAdministrators.
     */
    List<CharityAdminDetailsVO> fetchCharityAdministratorEmailList(final Long eventId);

    /**
     * This service directly updates status of an event .
     * 
     * @param eventId - id of the event
     * @param newStatus - new status to be updated
     * 
     * @return - true in case of success and false in case of failure
     */
    boolean updateEventStatus(final Long eventId, final String newStatus);

    /**
     * Update fundraiser activity with event fee details snapshot to mark online registration
     * for event.
     * 
     * @param fundraiserId fundraiser id
     * @param eventId organised event id
     * @param feeRef event fee reference
     * @param feeSituation event fee situation
     * @param feeAmount event fee amount.
     * @param auditAttributes the audit attributes
     * @param fundraiserActivityId the fundraiser activity id
     * 
     * @return true if updated else false
     */
    boolean updateFundraiserEventFee(Long fundraiserId, Long eventId, String feeRef,
            String feeSituation, BigDecimal feeAmount, Long fundraiserActivityId, AuditAttributes auditAttributes);

    /**
     * Return fundraiser count which registered for event using online pay method.
     * 
     * @param eventId organised event id
     * 
     * @return fundraiser count matching event id
     */
    int fetchOnlineRegisteredFundraiserCount(final Long eventId);

    /**
     * This method gets eventfeedetails domain object of the given id.
     * 
     * @param feeId  of the EventFeeDetails
     * 
     * @return - eventfeedetails domain object
     */
    EventFeeDetails fetchEventFeeDetailsByFeeId(final Long feeId);

     /**
      * This methods deletes the charitable activity Details for given charitable Activity Id.
      * 
      * @param charitableActivityId charitableActivity of type Long
      * @param auditAttributes the audit attributes
      * 
      * @return - true in case of success and false in case of failure
      */
    boolean deleteCharitableActivityDetails(final Long charitableActivityId,
            AuditAttributes auditAttributes);

    /**
     * This methods fetches charity logo based on chairty id.
     * 
     * @param charityId -id of charity
     * 
     * @return String charityLgo
     */
     String fetchCharityLogo(final Long charityId);

   /**
    * delete the CharityEventAdministrator  details.
    * 
    * @param charitableActivityId CharitableActivity ID
    * @param auditAttributes the audit attributes
    */
    void deleteCharityEventAdministratorsForEvent(final Long charitableActivityId,
            AuditAttributes auditAttributes);

    /**
     * This method used for to save the event status after
     * Comapre the previous status for particular event.
     * 
     * @param eventId of type Long
     * @param newEventStatus of type String action taken care by user.
     * @param auditAttributes the audit attributes
     * 
     * @return boolean value
     */
    boolean saveEventStatus(final Long eventId, final String newEventStatus,
            AuditAttributes auditAttributes);
    
    /**
     * Fetch bank details using charity id
     * and bank account type list.
     * 
     * @param charityId the charity id.
     * @param charityBankTypeList List of charity bank types like donation trading or both etc.
     * 
     * @return list of bank details.
     */
    List<BankAccount> fetchCharityBankDetailsByAccountType(Long charityId, 
            List<BankAccountType> charityBankTypeList);      

    /**
     * Save charity event administrator.
     * 
     * @param charityAdministrator - CharityAdministrator object.
     * @param userId the user id
     * @param charityId the charity id
     * @param auditAttributes the audit attributes
     */
    public void saveEventAdministrator(CharityAdministrator charityAdministrator, 
            long userId, long charityId, AuditAttributes auditAttributes);
    
    /**
     * delete charity event administrator.
     * 
     * @param userId the user id
     * @param charityId the charity id
     */
    public void deleteEventAdministrator(long userId, long charityId);
}
