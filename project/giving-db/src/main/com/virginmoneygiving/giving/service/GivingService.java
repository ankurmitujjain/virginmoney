package com.virginmoneygiving.giving.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.Timestamp;

import com.virginmoneygiving.giving.vo.VMGUserDetails;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupPageDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.TeamMemberDetailDTO;
import com.virginmoneygiving.giving.domain.ActivityType;
import com.virginmoneygiving.giving.domain.Address;
import com.virginmoneygiving.giving.domain.AlertTrigger;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.BankAccountType;
import com.virginmoneygiving.giving.domain.CardProvider;
import com.virginmoneygiving.giving.domain.CharitableActivity;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAdminDetailsVO;
import com.virginmoneygiving.giving.domain.CharityAdministrator;
import com.virginmoneygiving.giving.domain.CharityAdministratorSubset;
import com.virginmoneygiving.giving.domain.CharityCategory;
import com.virginmoneygiving.giving.domain.CharityDonation;
import com.virginmoneygiving.giving.domain.CharityEventAdministrator;
import com.virginmoneygiving.giving.domain.CharityHMRCDetailsDomain;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistration;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistrationLog;
import com.virginmoneygiving.giving.domain.CharitySubset;
import com.virginmoneygiving.giving.domain.CharityVO;
import com.virginmoneygiving.giving.domain.CommissionCharityDetails;
import com.virginmoneygiving.giving.domain.Country;
import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.giving.domain.Donor;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.EventActivity;
import com.virginmoneygiving.giving.domain.EventFeeDetails;
import com.virginmoneygiving.giving.domain.EventFeeDetailsVO;
import com.virginmoneygiving.giving.domain.EventHomepageDetailsDVO;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.giving.domain.FundraiserActivityDonation;
import com.virginmoneygiving.giving.domain.FundraiserDonorVO;
import com.virginmoneygiving.giving.domain.FundraiserGroup;
import com.virginmoneygiving.giving.domain.FundraiserGroupMember;
import com.virginmoneygiving.giving.domain.FundraiserGroupType;
import com.virginmoneygiving.giving.domain.FundraisingInfoDVO;
import com.virginmoneygiving.giving.domain.FundrasierURLDetails;
import com.virginmoneygiving.giving.domain.Location;
import com.virginmoneygiving.giving.domain.OfflineRegModule;
import com.virginmoneygiving.giving.domain.OwnOrganisedEventsDVO;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.domain.PageStatus;
import com.virginmoneygiving.giving.domain.PageType;
import com.virginmoneygiving.giving.domain.PageWidget;
import com.virginmoneygiving.giving.domain.PageWidgetType;
import com.virginmoneygiving.giving.domain.PaymentCard;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.PersonalAddress;
import com.virginmoneygiving.giving.domain.ProfanityReportModel;
import com.virginmoneygiving.giving.domain.RegisteredCharityUser;
import com.virginmoneygiving.giving.domain.RegistrationForm;
import com.virginmoneygiving.giving.domain.RegularDonation;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventParameter;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventResult;
import com.virginmoneygiving.giving.domain.TrusteeDetails;
import com.virginmoneygiving.giving.domain.UrlDetails;
import com.virginmoneygiving.giving.domain.UrlType;
import com.virginmoneygiving.giving.domain.User;
import com.virginmoneygiving.giving.domain.UserProfile;
import com.virginmoneygiving.giving.domain.UserRole;

import com.virginmoneygiving.giving.domain.CharityCustomFieldSubset;
import com.virginmoneygiving.giving.domain.CharityCustomFieldValueSubset;
import com.virginmoneygiving.giving.domain.SearchCriteriaSubset;

/**
 * Methods to save and fetch the Charity Details.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added methods fetchAllRequiredForms,
 * fetchAllCharityCategories.
 * @author Siva Kumar -added methods fetchAllCountrys , fetchAllCardTypes,
 * checkForProfanity ,savePaymentcardDetails
 * @author Edwin Tauro - Add save functionality for fundraiser.
 * @author Edwin Tauro - Added method findLiveCharitiesLikeName
 * @author Tusahr Lad - Added method updateCharityAddress
 * @author Diptirmaya Rout - Added methods updateCharityDescription,
 * saveCharityPageDetails, fetchCharityHomePageDetails,
 * fetchPageWidgetType.
 */
@SuppressWarnings("unchecked")
public interface GivingService {

    /**
     * Get Charity Details by Id.
     * 
     * @param charityId of type String
     * 
     * @return Charity type object
     */
    Charity fetchCharityDetailsById(Long charityId);
    
    /**
     * Get Charity Details by vmg charity url.
     * 
     * @param vmgCharityUrl of type String
     * 
     * @return Charity type object
     */
    Charity fetchCharityDetailsByVmgCharityUrl(String vmgCharityUrl);

    /**
     * Fetch charityDetails by given IDs.
     * 
     * @param charityIds of type String eg: 1,3,5
     * 
     * @return List of Charity object.
     */
    List<Charity> fetchCharityDetailsByIds(String charityIds);

    /**
     * Get charity details by charity name. <br>
     * Gets all charities irrespective of thier status.
     * 
     * @param charityName of type String
     * 
     * @return List of charities
     */
    List<CharitySubset> findCharityByName(String charityName);

    /**
     * Find charities based on charity name. This medhod uses commission data to
     * fetch matched charity list. <br>
     * Will fetch only those charities which are having org type as 'R' -
     * Registered.
     * 
     * @param charitySearchString of type String.
     * @param startRow the start row
     * @param chunkSize the chunk size
     * 
     * @return List of objects of CommissionCharityDetails.
     */
    List<CommissionCharityDetails> findCharityByNameFromCommissionData(
            String charitySearchString, int startRow, int chunkSize);
    
    /**
     * Find total count of charities based on charity name. This medhod uses commission data to
     * fetch matched charity list <br>
     * Will fetch only those charities which are having org type as 'R' -
     * Registered.
     * 
     * @param charitySearchString of type String.
     * 
     * @return total count of matching CommissionCharityDetails.
     */
    int findTotalCountCharityByNameFromCommissionData(
            String charitySearchString);    

    /**
     * Get Charity Administrator List for Given Charity Id.
     * 
     * @param charityId of type Long
     * 
     * @return List of Charity Administrator object.
     */
    List<CharityAdministratorSubset> findCharityAdministratorUsers(
            Long charityId);

    /**
     * Get the data for drop down for provided Module code.
     * 
     * @param moduleCode Module code for which data is to be fetched
     * 
     * @return List of OfflineRegModule objects.
     */
    List<OfflineRegModule> fetchModuleData(String moduleCode);

    /**
     * Fetch Charity Off line Registration List by given Charity ID.
     * 
     * @param charityId of type Long
     * 
     * @return List of Charity Off line Registration object.
     */
    List<CharityOfflineRegistration> fetchCharityOfflineRegData(Long charityId);

    /**
     * Save Charity Off line Registration Data.
     * 
     * @param charityOfflineRegistration of type charityOfflineRegistration
     * 
     * @return Charity Off line Registration object.
     */
    CharityOfflineRegistration saveCharityOfflineRegData(
            CharityOfflineRegistration charityOfflineRegistration);

    /**
     * Save Charity Global Alerts Details.
     * 
     * @param alertTriggerList for the charity.
     * 
     * @return saved alertTrigger for the charity.
     */
    List<AlertTrigger> saveAlertTriggers(List<AlertTrigger> alertTriggerList);

    /**
     * Load Charity Global Alerts Details.
     * 
     * @param charityId of type long.
     * 
     * @return saved alertTrigger for the charity.
     */

    List<AlertTrigger> loadAlertTriggers(Long charityId);

    /**
     * Get charity details by charity name. <br>
     * Gets all charities irrespective of thier status.
     * 
     * @param charityName of type String
     * 
     * @return List of charities
     */
    List<CharitySubset> findLiveCharitiesLikeName(String charityName);

    /**
     * Get charity details by charity registration number.
     * 
     * @param charityRegNumber of type String
     * 
     * @return List of charities
     */
    List<Charity> findCharityByRegNumber(String charityRegNumber);

    /**
     * Save Charity Details.
     * 
     * @param charityDetails of type Charity
     * 
     * @return long value
     */
    long saveCharityDetails(Charity charityDetails);

    /**
     * Update Charity Details.
     * 
     * @param charityDetails of type Charity
     * 
     * @return Charity the instance of updated {@link Charity}.
     */
    Charity updateCharityDetails(Charity charityDetails);

    /**
     * Fetch all the forms, required for charity registration.
     * 
     * @return list of {@link RegistrationForm}.
     */
    List<RegistrationForm> fetchAllRequiredForms();

    /**
     * Fetch all the charity categories.
     * 
     * @return list of {@link CharityCategory}.
     */
    List<CharityCategory> fetchAllCharityCategories();

    /**
     * Determine if the charity is already registered based on charity
     * registration number.
     * 
     * @param charityRegNumber of type String
     * 
     * @return true for registered charity otherwise false
     */
    boolean isCharityRegistered(String charityRegNumber);

    /**
     * Determine if the email address already exist.
     * 
     * @param emailAddress of type String
     * @param dobDay of type Integer
     * @param dobMonth of type Integer
     * @param dobYear of type Integer
     * 
     * @return true for existing account otherwise false
     */
    boolean isAccountExist(String emailAddress, Integer dobDay,
            Integer dobMonth, Integer dobYear);

    /**
     * Fetch all the Countrys .
     * 
     * @return list of {@link Country}.
     */
    List<Country> fetchAllCountries();

    /**
     * Fetch all the Card Types .
     * 
     * @return list of {@link CardProvider}.
     */
    List<CardProvider> fetchAllCardTypes();

    /**
     * To get charity status code.
     * 
     * @param charityId the id for the charity which status being fetched.
     * 
     * @return charityStatus value.
     */
    String getCharityStatus(Long charityId);

    /**
     * Get Person Details by Id.
     * 
     * @param personId of type String
     * 
     * @return Person type object
     */
    Person fetchPersonById(Long personId);

    /**
     * save the payment card details.
     * 
     * @param paymentCardDetails of type Payment Card
     * 
     * @return payment card id.
     */
    long savePaymentcardDetails(PaymentCard paymentCardDetails);

    /**
     * Persist the {@link Fundraiser} as a new record.
     * 
     * @param fundraiser the unique name of the {@link Fundraiser}.
     * 
     * @return Long the unique identifier of the persisted {@link Fundraiser}.
     */
    Long saveFundraiser(Fundraiser fundraiser);

    /**
     * fetch charities belongs to the category ID passed as input parameter.
     * 
     * @param charityCategoryId Id of the table CharityCategory.
     * 
     * @return List of {@link Charity}.
     */
    List<Charity> fetchCharitiesByCategoryId(Long charityCategoryId);

    /**
     * Fetch all records from the Event table.
     * 
     * @param eventName Name of the event.
     * 
     * @return List containing all the records from Events table.
     */
    List<Event> fetchAllEvents(String eventName);

    /**
     * Fetch all records from the Location table.
     * 
     * @return List containing all the records from location table.
     */
    List<Location> fetchAllLocations();

    /**
     * Fetch all records from the ActivityType table.
     * 
     * @param reason reason value based on the activity type will be fetched.
     * 
     * @return List containing all the records from ActivityType table.
     */
    List<ActivityType> fetchAllActivityTypes(String reason);

    /**
     * Fetch Event, charity, location and event date time based on the search
     * criteria.
     * 
     * @param parameter {@link SearchFundraisingEventParameter} Search parameter event
     * and charity and time period and event type (all are nillable
     * parameters so can be empty).
     * 
     * @return result set List .
     */
    List<SearchFundraisingEventResult> searchFundraisingEvent(
            SearchFundraisingEventParameter parameter);

    /**
     * This method is used to fetch payment card details for the specified
     * person.
     * 
     * @param personId contains person id.
     * 
     * @return {@link PaymentCard} contains person's payment card details.
     */
    PaymentCard fetchPaymentcardDetails(Long personId);

    /**
     * Fetch fundraiser activity details for the given fundraiserActivityId.
     * 
     * @param fundraiserActivityId fundraiserActivityId value.
     * 
     * @return {@link FundraiserActivity} object.
     */
    FundraiserActivity fetchFundraiserActivityDetailsById(
            Long fundraiserActivityId);

    /**
     * Persist the {@link Donor} as a new record.
     * 
     * @param donor the unique name of the {@link Donor}.
     * 
     * @return Long the unique identifier of the persisted {@link Donor}.
     */
    Long saveDonorPersonalDetails(Donor donor);
    
    /**
     * If donation is made by unregistered user, then his first name, sur name is stored as person object in database.
     * The first name, sur name is got from card details
     * 
     * @param person the person
     * 
     * @return the person
     */
    public Person savePerson(Person person);

    /**
     * This method is used to fetch donor's personal details in the system.
     * Personal details are like title, forename, surname, address, contact
     * details.
     * <p />
     * 
     * @param donorId contains donor id.
     * 
     * @return {@link Donor} contains donor's personal details.
     */
    Donor fetchDonorPersonalDetails(Long donorId);

    /**
     * Returns true indicating the fundraising reason details were saved else
     * false.
     * 
     * @param fundraiserActivity {@link FundraiserActivity} the fundraising acitivity details.
     * 
     * @return FundraiserActivity the persisted object with it's id.
     */
    FundraiserActivity saveFundraisingReason(
            FundraiserActivity fundraiserActivity);

    /**
     * Get the {@link Fundraiser) By passing fundraiser identifier.
     * 
     * @param fundraiserId of type Long
     * 
     * @return Fundraiser the {@link Fundraiser}.
     */
    Fundraiser fetchFundraiserDetailsById(Long fundraiserId);

    /**
     * UPdate the {@link Donor} with existing record.
     * 
     * @param donor the unique name of the {@link Donor}.
     * 
     * @return Donor Object of {@link Donor}.
     */
    Donor updateDonorPersonalDetails(Donor donor);

    /**
     * Persist the new donation details.
     * 
     * @param donation the donation details to persist.
     * 
     * @return the unique identifier for the persisted object.
     */
    Donation saveDonationDetails(Donation donation);

    /**
     * Persist the {@link CharityDonation} with given details.
     * 
     * @param charityDonation the charity donation to persist.
     * 
     * @return the persisted charity donation.
     */
    CharityDonation saveCharityDonation(CharityDonation charityDonation);

    /**
     * Persist the {@link FundraiserActivityDonation} with given details.
     * 
     * @param activityDonation the fundraiser activity donation.
     * 
     * @return the persisted fundraiser activity donation.
     */
    FundraiserActivityDonation saveFundraiserActivityDonation(
            FundraiserActivityDonation activityDonation);

    /**
     * Determine if another user having same profilr url.
     * 
     * @param url - user entered url.
     * @param urlType Url entity name, can be Fundraiser, Charity, Fundraising Team.
     * 
     * @return - true or false.
     */
    boolean isURLAvailable(String url, String urlType);

    /**
     * Get number of existing user with same name.
     * 
     * @param firstname - firstname of the user
     * @param lastname - last name of the user
     * 
     * @return - number of existing user with same name and having profile
     */
    int getRegisteredUserCount(String firstname, String lastname);

    /**
     * Store url in url details against the fundraiser.
     * 
     * @param fundraiser - object of fundraiser.
     */
    void storeUrl(Fundraiser fundraiser);

    /**
     * Fetch user roles associated with passed combination email address and
     * date of birth.
     * <p />
     * 
     * @param emailAddress login emailAdress.
     * @param dobDay day of birth.
     * @param dobMonth month of birth.
     * @param dobYear year of birth.
     * 
     * @return List of {@link UserRole}.
     */
    List<UserRole> fetchUserRoles(String emailAddress, int dobDay,
            int dobMonth, int dobYear);

    /**
     * Gets the event details by id.
     * 
     * @param eventId of type Long
     * 
     * @return Event object
     */
    Event getEventDetailsById(Long eventId);

    /**
     * update charity status.
     * 
     * @param id id of the charity.
     */
    void updateCharityStatus(Long id);

    /**
     * Returns a boolean flag indicating if the payment details exists. <br>
     * 
     * @param emailAddress email address of user.
     * @param dobDay day of birth.
     * @param dobMonth month of birth.
     * @param dobYear year of birth.
     * 
     * @return boolean <code>true</code> if payment details exist in database
     * else false
     */
    boolean isPaymentCardDetailsExist(String emailAddress, int dobDay,
            int dobMonth, int dobYear);

    /**
     * Fetch donorId by using userRoleId.
     * 
     * @param userRoleId the userRoleId of the logged in user.
     * 
     * @return donorId.
     */
    Long fetchDonorIdByUserRoleId(Long userRoleId);

    /**
     * Fetch person details associated with passed combination email address and
     * date of birth.
     * <p />
     * 
     * @param emailAddress login emailAdress.
     * @param dobDay day of birth.
     * @param dobMonth month of birth.
     * @param dobYear year of birth.
     * 
     * @return Object of {@link Person}.
     */
    Person fetchPersonDetails(String emailAddress, int dobDay, int dobMonth,
            int dobYear);

    /**
     * Fetch donor details using donation id.
     * 
     * @param donationId the donation id.
     * 
     * @return Donor object with donor details.
     */
    Donor fetchDonorDetails(Long donationId);
    
    /**
     * Fetch Person details using donation id.
     * 
     * @param donationId the donation id.
     * 
     * @return Person object with Person details.
     */
    Person fetchPersonDetailsOfDonation(Long donationId);

    /**
     * Fetch bank account details for selected charity.
     * 
     * @param charityId Charity Id.
     * 
     * @return Bank details list.
     */
    List<BankAccount> fetchCharityBankDetails(Long charityId);
    
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
     * This method used to check the web site URL is exist or not.
     * 
     * @param vmgCharityURL the charity site URL.
     * 
     * @return boolean value true or false.
     */
    boolean isVmgCharityURLExist(String vmgCharityURL);

    /**
     * This method will update the account default account indicator for
     * selected bank account.
     * 
     * @param bankAccountId Bank Account Id
     * @param charityId Charity Id
     * @param defaultAccountIndicator Default account indicator.
     * @param userName the username
     * @param attributes the audit attributes
     * 
     * @return true or false.
     */
    Boolean updateCharityBankDetailsStatus(Long bankAccountId, Long charityId,
            String defaultAccountIndicator, String userName, AuditAttributes attributes);

    /**
     * Find regular donations by donor id.
     * 
     * @param donorId of type Long
     * 
     * @return list of charity donations.
     */
    List<RegularDonation> findRegularDonationsByDonorId(final Long donorId);


    /**
     * Get the regularDonation.
     * 
     * @param id the id
     * 
     * @return the regular donation
     */
    RegularDonation getRegularDonation(Long id);

    /**
     * Update the regularDonation.
     * 
     * @param regularDonation the regular donation
     * 
     * @return the regular donation
     */
    RegularDonation updateRegularDonation(RegularDonation regularDonation);


    /**
     * Cancel a regular donation.
     * 
     * @param regularDonationId the regular donation id
     * 
     * @return the regular donation
     */
    RegularDonation cancelRegularDonation(Long regularDonationId);

    /**
     * Update donation.
     * 
     * @param donation of type Donation
     * 
     * @return Donation object.
     */
    Donation updateDonation(Donation donation);

    /**
     * Update charity donation.
     * 
     * @param charityDonation of type CharityDonation
     * 
     * @return CharityDonation object
     */

    CharityDonation updateCharityDonation(CharityDonation charityDonation);

    /**
     * Fetch donation.
     * 
     * @param id of type Long
     * 
     * @return Donation object
     */
    Donation fetchDonation(Long id);

    /**
     * Fetch charity donation.
     * 
     * @param id of type Long
     * 
     * @return CharityDonation object
     */
    CharityDonation fetchCharityDonation(Long id);

    /**
     * To update the charity bank account deletion status.
     * 
     * @param charityId Charity Id.
     * @param bankAccountId Bank Account Id.
     * @param bankAccountStatus Bank account status.
     * @param auditAttributes the audit attributes
     * 
     * @return true or false.
     */
    Boolean removeCharityBankAccount(Long charityId, Long bankAccountId,
            String bankAccountStatus, AuditAttributes auditAttributes);

    /**
     * Update charity address.
     * 
     * @param id - charity Id
     * @param address - address for charity to be updated.
     * @param auditAttributes the audit attributes
     * 
     * @return boolean <code>true</code> updated status.
     */
    boolean updateCharityAddress(Long id, Address address,
            AuditAttributes auditAttributes);

    /**
     * Gets the user.
     * 
     * @param emailAddress the email address
     * @param dayOfBirth the day of birth
     * @param monthOfBirth the month of birth
     * @param yearOfBirth the year of birth
     * 
     * @return the user
     */
    User getUser(String emailAddress, int dayOfBirth, int monthOfBirth,
            int yearOfBirth);

    /**
     * Save user.
     * 
     * @param user the user
     */
    void saveUser(User user);

    /**
     * Fetch the list of charities based on given criteria.
     * 
     * @param charityName - name to be matched with charity
     * @param vmgRefNo - vmg reference number to be matched
     * @param postCode - post code to be matched
     * @param charityNumber - charity number to be matched
     * 
     * @return list of charities satisfying criteria
     */
    List<CharityVO> fetchCharityList(String charityName, Long vmgRefNo,
            String postCode, String charityNumber);

    /**
     * Fetch the list of fundraisers and donors based on given criteria.
     * 
     * @param name - name to be matched with fundraises and donors
     * @param vmgRefNo - vmg reference number to be matched
     * @param postCode - post code to be matched
     * 
     * @return list of fundraisers and donors satisfying criteria
     */
    List<FundraiserDonorVO> fetchFundraiserAndDonorList(String name,
            Long vmgRefNo, String postCode);

    /**
     * This method is used to get all events that can be joined by a charity.
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
    List<OwnOrganisedEventsDVO> fetchOperationalOrganisedEvents(
            List<String> statusList, Long userId);

    /**
     * Fetch user owned organised events.
     * 
     * @param statusList of type List of Strings
     * @param charityId of type Long
     * @param userId the user id
     * 
     * @return list of OwnOrganisedEventsDVO
     */
    List<OwnOrganisedEventsDVO> fetchOwnedOrganisedEvents(
            List<String> statusList, Long charityId, Long userId);

    /**
     * Get the Owned four latest Organised Events with given details.
     * 
     * @param statusList of type List of Strings
     * @param charityId of type Long
     * 
     * @return list of OwnOrganisedEventsDVO
     */
    List<OwnOrganisedEventsDVO> fetchOwnedFourLatestOrganisedEvents(
            List<String> statusList, Long charityId);

    /**
     * Fetch Nine RandomRaisers based on event id and charity id.
     * 
     * @param eventId - event id
     * @param charityId - chairty id
     * 
     * @return FundraisingInfoDVO
     */
    FundraisingInfoDVO fetchNineRandomFundraisers(Long eventId, Long charityId);

    /**
     * Fetch the list of charity administrator for an event.
     * 
     * @param charitableActivityId of type Long
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
     * Get all EventDetails based on event id and charity id.
     * 
     * @param eventId the event id
     * @param charityId the charity id
     * 
     * @return EventHomepageDetailsDVO event home page DVO
     */
    EventHomepageDetailsDVO fetchEventDetails(Long eventId,
            Long charityId);

    /**
     * Update charity description.
     * 
     * @param id of type Long
     * @param charityDescription - charityDescription for charity.
     * @param attributes the audit attributes
     * 
     * @return boolean value.
     */
    boolean updateCharityDescription(Long id, String charityDescription,
            AuditAttributes attributes);

    /**
     * Save Charity Details.
     * 
     * @param charityPageDetails of type Charity
     * 
     * @return long value
     */
    long saveCharityPageDetails(Page charityPageDetails);

    /**
     * Fetch Charity Home Page Details.
     * 
     * @param charityId the charity id
     * 
     * @return list of Page Details.
     */
    List<Page> fetchCharityHomePageDetails(Long charityId);

    /**
     * update batch status.
     * 
     * @param id id of the charity.
     * @param status the batch status
     */
    void updateBatchStatus(Long id, String status);

    /**
     * To save the charity bank account details.
     * 
     * @param bankAccount Bank account details.
     * 
     * @return True or false.
     */
    boolean saveCharityBankDetails(BankAccount bankAccount);

    /**
     * Update the {@link User} with existing record.
     * 
     * @param user the user
     * 
     * @return User Object of {@link User}.
     */
    User updateUserPersonalDetails(User user);

    /**
     * This method for to save the event details and it relation values.
     * 
     * @param eventDetails of type event object
     * 
     * @return Event event domain object after saving the recod in data base.
     */
    Event saveEventDetails(final Event eventDetails);

    /**
     * This method for to save the eventaddress details and it relation values.
     * 
     * @param contactAddress of type Address object
     * 
     * @return Address domain object after saving the recod in data base.
     */
    Address saveAddress(final Address contactAddress);

    /**
     * This method for to save the EventActivity details and it relation values.
     * 
     * @param eventActivity of type EventActivity object
     * 
     * @return EventActivity domain object after saving the recod in data base.
     */
    EventActivity saveEventActivity(final EventActivity eventActivity);

    /**
     * This method for to delete the eventActivities for particular eventId.
     * 
     * @param eventId Event ID
     */
    void deleteEventActivitiesForEvent(final Long eventId);

    /**
     * This method for save or update the CharitableActivity details and it
     * relation values.
     * 
     * @param charitableActivity of type CharitableActivity object
     * 
     * @return CharitableActivity domain object after saving the recod in data
     * base.
     */
    CharitableActivity saveCharitableActivity(
            final CharitableActivity charitableActivity);

    /**
     * This method for save or update the EventFeeDetails details and it
     * relation values.
     * 
     * @param eventFeeDetails of type EventFeeDetails object
     * 
     * @return EventFeeDetails EventFeeDetails domain object after saving the
     * recod in data base.
     */
    EventFeeDetails saveEventFeeDetails(final EventFeeDetails eventFeeDetails);

    /**
     * This method for delete the EventFeeDetails details for particular feeId.
     * 
     * @param eventFeeId Event Fee ID
     * @param attributes the audit attributes
     */
    void deleteEventFeeDetails(final Long eventFeeId, AuditAttributes attributes);

    /**
     * This method for save the CharityEventAdministrator details and it
     * relation values.
     * 
     * @param charityEventAdministrator of type CharityEventAdministrator object
     * 
     * @return CharityEventAdministrator domain object after saving the recod in
     * data base.
     */
    CharityEventAdministrator saveCharityEventAdministrator(
            final CharityEventAdministrator charityEventAdministrator);

    /**
     * This method for to delete the CharityEventAdministrator details for
     * particular charitableActivity Id.
     * 
     * @param charitableActivityId CharitableActivity ID
     * @param attributes the audit attributes
     */
    void deleteCharityEventAdministratorsForEvent(
            final Long charitableActivityId, AuditAttributes attributes);

    /**
     * This method for fetch charity administrator object.
     * 
     * @param charityAdminId charity admin id
     * 
     * @return CharityAdministrator CharityAdministrator object
     */
    CharityAdministrator fetchCharityAdministrators(final Long charityAdminId);

    /**
     * fetch Bank account object.
     * 
     * @param bankAccountId bank account Id
     * 
     * @return BankAccount object
     */
    BankAccount fetchBankAccountDetails(Long bankAccountId);

    /**
     * This method will update the account status for selected bank account.
     * 
     * @param bankAccountId Bank Account Id
     * @param charityId Charity Id
     * @param bankAccountStatus Default Account status.
     * @param userName the user name
     * @param auditAttributes the audit attributes
     * 
     * @return true or false.
     */
    boolean updateCharityBankAccountStatus(Long bankAccountId, Long charityId,
            String bankAccountStatus, String userName, AuditAttributes auditAttributes);

    /**
     * Checks if any team is already registered with the name passed as
     * parameter.
     * 
     * @param teamName the fundraiserTeamName
     * 
     * @return boolean, true is record found else false.
     */
    boolean isFundraiserTeamAlreadyExist(String teamName);

    /**
     * Persist the {@link FundraiserGroup} as a new record.
     * 
     * @param fundraiserGroup the fundraiserTeam details
     * 
     * @return Long the unique identifier of the persisted
     * {@link FundraiserGroup}.
     */
    Long saveFundraiserGroup(FundraiserGroup fundraiserGroup);

    /**
     * Searches registered fundraisers based on the email address send as input
     * parameter.
     * 
     * @param emailAddress the fundraiser emailAddress
     * 
     * @return List of TeamMemberDetailDTO
     */
    List<TeamMemberDetailDTO> searchRegisteredFundraiserByEmailAddress(
            String emailAddress);

    /**
     * Persist the {@link FundraiserGroupMember} as a new record.
     * 
     * @param fundraiserGroupMember the fundraiserTeamMember details
     * 
     * @return Long the unique identifier of the persisted
     * {@link FundraiserGroupMember}.
     */
    Long saveFundraiserTeamMember(FundraiserGroupMember fundraiserGroupMember);

    /**
     * Removed / Deletes the {@link FundraiserGroupMember} from the Table.
     * 
     * @param fundraiserGroupMember the fundraiserTeamMember details
     */
    void removeFundraiserTeamMember(FundraiserGroupMember fundraiserGroupMember);

    /**
     * Fetch all FundraiserGroupMember for the teamId passed.
     * 
     * @param teamId - teamId
     * 
     * @return - list of FundraiserGroupMember
     */
    List<FundraiserGroupMember> fetchTeamMembers(long teamId);

/**
 * Get the {@link FundraiserGroup) By passing FundraiserGroup identifier.
 * 
 * @param fundraiserTeamId of type Long
 * 
 * @return FundraiserGroup the {@link FundraiserGroup}.
 */
    FundraiserGroup findFundraiserGroupById(long fundraiserTeamId);
    
    /**
     * Get the {@link FundraiserGroup) By passing FundraiserGroup identifier.
     * 
     * @param fundraiserTeamId of type Long
     * @param memberId of type Long
     * 
     * @return FundraiserGroup the {@link FundraiserGroup}.
     */
    FundraiserGroup findFundraiserGroupByIdAndMemeber(long fundraiserTeamId, long memberId);     

/**
 * Get the {@link FundraiserGroupType) By passing FundraiserGroupType identifier.
 * 
 * @param code - FundraiserGroupType code
 * 
 * @return FundraiserGroupType the {@link FundraiserGroupType}.
 */
    FundraiserGroupType findFundraiserGroupTypeById(String code);

    /**
     * fetchHMRCreferenceNumber.
     * 
     * @param charityHMRCDetailsRequestList the charity HMRC details list
     * @param type the type
     * 
     * @return Event object
     */
    List<CharityHMRCDetailsDomain> fetchHMRCreferenceNumber(
            List<CharityHMRCDetailsDomain> charityHMRCDetailsRequestList,
            String type);

    /**
     * Fetch the list of event fee details.
     * 
     * @param eventId the event id
     * 
     * @return list of charity administrators satisfying criteria
     */
    EventFeeDetailsVO fetchEventFeeList(Long eventId);

    /**
     * This service is used to fetch event fee status i.e whether fundraiser can
     * pay event registration fee online.
     * 
     * @param eventId the event id
     * 
     * @return Matching event object if satisfies critera
     */
    Event fetchEventFeeStatus(Long eventId);

/**
 * Get the {@link UrlType) By passing FundraiserGroup identifier.
 * 
 * @param code - UrlType code
 * 
 * @return UrlType the {@link UrlType}.
 */
    UrlType findUrlTypeByCode(String code);

    /**
     * To fetch bank account type.
     * 
     * @return Bank account type list.
     */
    List<BankAccountType> fetchBankAccountType();

    /**
     * Update bank account description and account type.
     * 
     * @param bankAccountId Bank account id.
     * @param accountDescription Account description.
     * @param accountType Account type.
     * @param userName the user name
     * @param auditAttributes the audit attributes
     * 
     * @return true or false.
     */
    boolean updateBankDescriptionAndType(Long bankAccountId,
            String accountDescription, String accountType, String userName,
            AuditAttributes auditAttributes);

    /**
     * This service is used to check whther given charity can cancel this event.
     * If yes this method will return CharitableActivity Object otherwise null.
     * 
     * @param eventId the event id
     * @param charityId the charity id
     * 
     * @return Matching event object if satisfies critera
     */
    CharitableActivity fetchCancelEventRight(Long eventId, Long charityId);

    /**
     * This method for fetch event details for particular eventId.
     * 
     * @param eventId - id of the event
     * 
     * @return - event domain object
     */
    Event fetchEventDetail(final Long eventId);

    /**
     * This method gets event domain object of the given id.
     * 
     * @param charitableActivityId - id of the CharitableActivity
     * 
     * @return - charitableActivity domain object
     */
    CharitableActivity fetchCharitableActivityDetail(Long charitableActivityId);

    /**
     * Returns Page object of fundraiser type.
     * 
     * @param userUrl the user url
     * @param pageUrl the page url
     * 
     * @return the page
     */
    Page fetchPage(String userUrl, String pageUrl);

    /**
     * Fetch a Page with given id.
     * 
     * @param id the page id
     * 
     * @return the page
     */
    Page fetchPage(Long id);

    /**
     * Fetch the PageStatus with the given code.
     * 
     * @param code the code
     * 
     * @return the pagestatus
     */
    PageStatus fetchPageStatus(String code);

    /**
     * Fetch the PageType with the given code.
     * 
     * @param code the code
     * 
     * @return the pagetype.
     */
    PageType fetchPageType(String code);

    /**
     * Fetch the PageWidgetType.
     * 
     * @param code the code
     * 
     * @return the page widget type
     */
    PageWidgetType fetchPageWidgetType(String code);

    /**
     * Save the supplied page object.
     * 
     * @param page the page
     * 
     * @return the page
     */
    Page savePage(Page page);

    /**
     * Fetch the fundraiser.
     * 
     * @param fundraiserId the fundraiser id
     * @param includeNonOwnerTeamActivities flag
     * 
     * @return the fundraiser
     */
    Fundraiser fetchFundraiser(long fundraiserId,
            boolean includeNonOwnerTeamActivities);

    /**
     * Fetch the fundraiser having the supplied unique url.
     * 
     * @param url the url
     * @param includeNonOwnerTeamActivities the flag
     * 
     * @return fundraiser
     */
    Fundraiser fetchFundraiserByUrl(String url,
            boolean includeNonOwnerTeamActivities);

    /**
     * Fetc the Fundraiser for the given user, null if does not exist.
     * 
     * @param userId the userid
     * 
     * @return the fundraiser
     */
    Fundraiser fetchFundraiserForUser(long userId);

    /**
     * Find the activities where the fundraiser is a team member but not the
     * owner.
     * 
     * @param fundraiserId the fundraiser id
     * 
     * @return list of fundraiser activities.
     */
    List<FundraiserActivity> findTeamActivitiesWhereNotOwner(
            long fundraiserId);

    /**
     * This will store email information.
     * 
     * @param profanityReportModel - profanity report.
     * 
     * @return profanity report id.
     */
    Long saveProfanityEmails(ProfanityReportModel profanityReportModel);

    /**
     * This methods gets all event charity admin list with email etc.
     * 
     * @param eventId the event id.
     * 
     * @return a list of CharityAdminDetailsVO.
     */
    List<CharityAdminDetailsVO> fetchCharityAdministratorEmailList(Long eventId);

    /**
     * This service directly updates status of an event.
     * 
     * @param eventId - id of the event
     * @param newStatus - new status to be updated
     * 
     * @return - true in case of success and false in case of failure
     */
    boolean updateEventStatus(Long eventId, String newStatus);

    /**
     * Updates fund raiser and release fund raiser url for reuse.
     * 
     * @param fundrasier the fundrasier
     * 
     * @return the fundraiser
     */
    Fundraiser closeFundraiserAccount(Fundraiser fundrasier);

    /**
     * Removes given url from db ..
     * 
     * @param urlDetails the url details
     */
    void removeUrlDetails(UrlDetails urlDetails);

    /**
     * This method used to get the charity trustee details based on a charity
     * id.
     * 
     * @param charityId the charity id.
     * 
     * @return list of trustee details.
     */
    List<TrusteeDetails> fetchCharityTrusteeDetails(Long charityId);


    /**
     * This method used to delete the trustee details based on a trustee id.
     * 
     * @param trusteeTypes The list trustee ids.
     * @param charityId the charity id
     * @param auditAttributes the audit attributes
     */
    void deleteCharityTrusteeDetails(List<String> trusteeTypes, Long charityId,
            AuditAttributes auditAttributes);


    /**
     * This method used to save the trustee details.
     * 
     * @param trusteeDetails The trustee details object.
     * @param charityId of type Long
     * @param auditAttributes the audit attributes
     * 
     * @return The trustee details object.
     */
    TrusteeDetails saveTrusteeDetails(TrusteeDetails trusteeDetails,
            Long charityId, AuditAttributes auditAttributes);

    /**
     * Update fundraiser activity with event fee id to mark online registration
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
    boolean updateFundraiserEventFee(Long fundraiserId, Long eventId,
            String feeRef, String feeSituation, BigDecimal feeAmount,Long fundraiserActivityId,
            AuditAttributes auditAttributes);

    /**
     * Return fundraiser count which registered for event using online pay
     * method.
     * 
     * @param eventId organised event id
     * 
     * @return fundraiser count matching event id
     */
    int fetchOnlineRegisteredFundraiserCount(Long eventId);

    /**
     * This method gets eventFeeDetails domain object of the given id.
     * 
     * @param feeId - id of type long
     * 
     * @return - eventFeeDetails domain object
     */
    EventFeeDetails fetchEventFeeDetailsByFeeId(Long feeId);

    /**
     * This method for to join the event details.
     * 
     * @param fundraiserId the fundraiser id
     * 
     * @return eventId of type Long
     */
    /* Long joinEventDetails(Event eventDetails); */

    /**
     * Searches page details based on the fundraiser id send as input parameter.
     *
     * @param fundraiserId
     *            the fundraiserId fundraiserId
     * @return List<FundraiserGroupPageDetailDTO>.
     */
    List<FundraiserGroupPageDetailDTO> findPageDetailsbyFundraiserId(
            Long fundraiserId);

    /**
     * Sets relations between Page and FundraiserGroup.
     * 
     * @param pageId the pageId pageId
     * @param fundraiserGroupId the fundraiserGroupId fundraiserGroupId
     * @param auditAttributes the audit attributes
     */
    void updatePagewithFundraiserGroupID(Long pageId, Long fundraiserGroupId,
            AuditAttributes auditAttributes);

/**
 * Get the {@link Page) By passing Page identifier.
 * 
 * @param pageId of type Long
 * 
 * @return Page the {@link Page}.
 */
    Page findPageById(long pageId);

    /**
     * This methods deletes the charitable activity Details.
     * 
     * @param charitableActivityId charitableActivity of type Long
     * @param auditAttributes the audit attributes
     * 
     * @return - true in case of success and false in case of failure
     */
    boolean deleteCharitableActivityDetails(Long charitableActivityId,
            AuditAttributes auditAttributes);

    /**
     * This methods fetches charity logo based on chairty id.
     * 
     * @param charityId long
     * 
     * @return String charityLogo
     */
    String fetchCharityLogo(Long charityId);

    /**
     * Fetch the permissions assigned to the user identified by given unique
     * username.
     * <p>
     * In case of external user login ie for charity user / donor / fundraiser
     * the username would be the login email address and dobDay, dobMonth,
     * dobYear would be respective parameters of users date of birth as provided
     * during registration.
     * <p>
     * In case this is an internal user ie operations user, the username would
     * be the actual username of the user used to login in internal systems. The
     * dobDay, dobMonth, dobYear would not be passed in this case.
     * <p>
     * 
     * @param dobDay the day of date of birth.
     * @param dobMonth the month of date of birth.
     * @param dobYear the year of date of birth.
     * @param userName the user name
     * 
     * @return list of permissions assigned to the user.
     */
    List<String> fetchUserPermissions(String userName, Integer dobDay,
            Integer dobMonth, Integer dobYear);

    /**
     * Grant given set of permissions to user identified by given userId.
     * 
     * @param userId the user identifier.
     * @param permissions the list of permissions.
     * @param auditAttributes the audit attributes
     * 
     * @return true if grant is successful, else false.
     */
    boolean grantPermissions(Long userId, List<String> permissions,
            AuditAttributes auditAttributes);

    /**
     * Fetch pageWidgetType.
     * 
     * @return List Of Page Widget Types.
     */
    List<PageWidgetType> fetchPageWidgetType();
    
    
    /**
     * Fetch pageWidgetType for the page type.
     * 
     * @param pageType the page type
     * 
     * @return List Of Page Widget Types.
     */
    List<PageWidgetType> fetchPageWidgetTypeFromPageType(String pageType);
    
    /**
     * Fetch Charity Page Details.
     * 
     * @param id the id
     * 
     * @return Page object.
     */
    Page fetchCharityHomepageById(Long id);

    /**
     * Fetch List Of Page Widgets.
     * 
     * @param id the id
     * 
     * @return the list< page widget>
     */
    List<PageWidget> fetchListofPageWidgetsById(Long id);

    /**
     * Get the {@link List<FundraiserGroupDetailDTO>) By passing fundraiserId.
     * 
     * @param fundraiserId of type Long
     * @param fundraiserGroupType of type String
     * 
     * @return List<FundraiserGroupDetailDTO> the {@link FundraiserGroupDetailDTO}.
     */
    List<FundraiserGroupDetailDTO> findFundraiserGroupsbyFundraiserId(
            Long fundraiserId, String fundraiserGroupType);

    /**
     * This method is for to fetch the event fee details for particular
     * charitableActivityId.
     * 
     * @param charitableActivityId of type Long
     * 
     * @return list of event fee details
     */
    List<EventFeeDetails> fetchEventFeeDetails(final Long charitableActivityId);

    /**
     * To check whether an event is attached with an bank or not.
     * 
     * @param bankAccountId Bank Account id.
     * 
     * @return the string
     */
    public String checkEventAttachedWithBank(Long bankAccountId);

    /**
     * Persist a regular donation.
     * 
     * @param regularDonation the donation
     * 
     * @return the persisted donation
     */
    public RegularDonation saveRegularDonation(RegularDonation regularDonation);

    /**
     * This method is used to fetch login username of the operations users
     * registered on the system.
     * 
     * @return List that contains operation user's login usernames.
     */
    List<String> fetchOperationUsersLoginName();

    /**
     * Fetch VMG user details to use in whole applications. This method will
     * return list of user user roles. <li>If user is fundraiser then
     * fundraiserId will be returned else null. <li>If user is donor then
     * donorId will be returned else null. <li>If user is charity user then
     * charityId will be returned else null. <li>If user is charity user or
     * fundraiser then list of eventIds will be returned.
     * 
     * @param user object of {@link User}.
     * 
     * @return object of {@link VMGUserdetails}.
     */
    VMGUserDetails fetchVMGUserDetails(User user);

    /**
     * Fetch VMG external user details to use in whole applications. External
     * users - Charity user, fundraiser, donor, etc.
     * 
     * @param loginEmailAddress login email address.
     * @param dayOfBirth day of birth.
     * @param monthOfBirth month of birth.
     * @param yearOfBirth year of birth.
     * 
     * @return object of {@link VMGUserDetails}.
     */
    VMGUserDetails fetchVMGUserDetailsForExternalUser(String loginEmailAddress,
            Integer dayOfBirth, Integer monthOfBirth, Integer yearOfBirth);

    /**
     * Fetch VMG internal user details to use in whole applications. Internal
     * users - Operation users.
     * 
     * @param username the username
     * 
     * @return object of {@link VMGUserDetails}.
     */
    VMGUserDetails fetchVMGUserDetailsForInternalUser(String username);

    /**
     * Retrieves list of Custom fields matching the search criteria (by charity
     * id). UCCHA090
     * 
     * @param charityId the charity id
     * 
     * @return List of charity Custom Fields.
     */
    List<CharityCustomFieldSubset> fetchCharityCustomFields(String charityId);


    /**
     * This method save list of charity reporting custom fields.
     * <p/>
     * It uses charity id as a look up parameter to save matching charities
     * custom field labels in charityCustomField List.
     * 
     * @param charityCustomFieldList list of charity custom field labels
     * 
     * @return boolean represents process status.
     */
    boolean saveCharityCustomFields(
            final List<CharityCustomFieldSubset> charityCustomFieldList);

    /**
     * This method retrieves list of charity reporting entities data based on
     * user search criteria.
     * 
     * @param searchCriteriaSubset the search criteria subset
     * 
     * @return List of charity entities against search criteria.
     */
    public List fetchReportingSearchResults(
            SearchCriteriaSubset searchCriteriaSubset);

    /**
     * This method saveCustomFieldValues.
     * 
     * @param auditAttributes the audit attributes
     * @param charityCustomFieldValueSubset the charity custom field value subset
     * @param charityId the charity id
     * @param entityType the entity type
     * 
     * @return boolean represents process status.
     */
    public boolean saveCustomFieldValues(
            CharityCustomFieldValueSubset charityCustomFieldValueSubset,
            String charityId, String entityType, AuditAttributes auditAttributes);

    /**
     * Fetch user details by id.
     * 
     * @param userId the user id
     * 
     * @return the user
     */
    User fetchUserDetailsById(final long userId);

    /**
     * This method used to get the trustee count based on a charity id.
     * 
     * @param charityId The charity id.
     * 
     * @return The trustee count.
     */
    int getTrusteeCount(Long charityId);

    /**
     * This method used to store the trustee count based on a charity id.
     * 
     * @param charityId The charity id.
     * @param trusteeCount The trustee count.
     * @param attributes the audit attributes
     */
    void saveTrusteeCount(Long charityId, int trusteeCount,
            AuditAttributes attributes);

    /**
     * This method used to store the trustee status based on a charity id.
     * 
     * @param charityId The charity id.
     * @param trusteeStatus The trustee status.
     * @param auditAttributes the audit attributes
     */
    void saveTrusteeStatus(Long charityId, String trusteeStatus,
            AuditAttributes auditAttributes);

    /**
     * This method used to get the offline charity trustee status based on a
     * charity id.
     * 
     * @param charityId The charity id.
     * 
     * @return The status.
     */
    String getOfflineCharityTrusteeStatus(Long charityId);

    /**
     * Fetches fund raiser activity donations.
     * <p>
     * orders by donation time descending
     * </p>
     * 
     * @param fundraiserActivityId - fundraiser activity id
     * @param rowNumberLimit - number of maximum rows ..
     * 
     * @return the list< donation>
     */
    List<FundraiserActivityDonation> fetchFundraiserActivityDonations(
            Long fundraiserActivityId, Integer rowNumberLimit);

    /**
     * Find the biggest single donation for an activity. (More than one can have
     * the same value and therefore more than 1 can be returned)
     * 
     * @param activityId the activity id
     * 
     * @return the list< donation>
     */
    List<Donation> fetchBiggestFundraiserActivityDonation(long activityId);

    /**
     * Count the total number of donations for a given activity.
     * 
     * @param activityId the activity id
     * 
     * @return the long
     */
    long countDonationsForActivity(long activityId);

    /**
     * fetchUserIdByUserName.
     * 
     * @param userName the user name
     * @param userFirst the user first
     * @param userSurName the user sur name
     * 
     * @return userId
     */
    long fetchUserIdByUserName(String userName, String userFirst,
            String userSurName);

    /**
     * Update the FundraiserActivity object supplied.
     * 
     * @param fundraiserActivity the fundraiser activity
     */
    void updateFundraiserActivity(FundraiserActivity fundraiserActivity);

    /**
     * This method is used to get the registered users based on a charity id.
     * 
     * @param charityId The charity id.
     * 
     * @return List of registered users.
     */
    List<RegisteredCharityUser> fetchRegisteredUsersOfCharityById(
            Long charityId);

    /**
     * Save charity administrator.
     * 
     * @param charityAdmin the charity administrator
     * 
     * @return the admin id
     */
    CharityAdministrator saveCharityAdministrator(CharityAdministrator charityAdmin);

    /**
     * Update the status of given charity as LIVE, publish the pages associated
     * with this charity.
     * 
     * @param charityId the charity id
     * @param auditAttributes the audit attributes
     * 
     * @return charity
     */
    Charity putCharityLive(Long charityId, AuditAttributes auditAttributes);

    /**
     * If the registered Fundraiser has been previously invited by any other
     * fundraiser to join a team, this function will update the
     * InvitedEmailAddress with this Fundraiser ID.
     * 
     * @param emailAddress the emailAddress of the fundraiser
     * @param fundraiserId the id of fundraiser.
     * @param auditAttributes the audit attributes
     */
    void updateInvitedEmailAddressWithFundraiserId(String emailAddress,
            Long fundraiserId, AuditAttributes auditAttributes);

    /**
     * Function used to update Fundraiser_activity table with Fundraiser Group
     * ID.
     * 
     * @param fundraiserActivityId the fundraiser activity id.
     * @param fundraiserGroupId the fundraiser group id.
     * @param fundraiser TODO
     * @param auditAttributes the audit attributes
     */
    void updateFundraiserActivityWithGroupIdOrFundraiserId(Long fundraiserActivityId,
            Long fundraiserGroupId, Fundraiser fundraiser, AuditAttributes auditAttributes);

    /**
     * Grant given set of permissions to user identified by given userId.
     * 
     * @param userId the user identifier.
     * @param permissionsList the list of permissions.
     * @param rolesList the list of roles.
     * @param auditAttributes the audit attributes
     * 
     * @return true if grant is successful, else false.
     */
    boolean grantPermissionsWithRoles(long userId,
            List<String> permissionsList, List<String> rolesList, AuditAttributes auditAttributes);

    /**
     * This method is used to delete the charity user by setting the present end
     * time.
     * 
     * @param charityId The charity id.
     * @param userId the user id.
     * @param attributes the audit attributes
     */
    void deleteCharityUser(Long charityId, Long userId,
            AuditAttributes attributes);

    /**
     * Return count of users who have User Read write access.
     * 
     * @return count the count
     */
    int getUserAccessRWCount();


    /**
     * Fetch location by ISO code.
     * 
     * @param isoCode the isoCode
     * 
     * @return location
     */
    Location fetchLocationByIsoCode(String isoCode);

    /**
     * This service will date stamp the Regular donation record.
     * 
     * @param donorId the donor id
     * 
     * @return flag
     */
    boolean dateStampRegularDonation(Long donorId);
	 
	 /**
 	 * Fetch email addresses for settlement.
 	 * 
 	 * @param charityIds the charity ids
 	 * 
 	 * @return list of email addresses.
 	 */
    List<Object[]> fetchEmailAddresssForSettlement(Set<Long> charityIds);
	
    /**
     * Update the Reguar donation status to newStatus in case
     * of unsuccessful regular donation through UCPHB0460.
     * 
     * @param donorId the donor id
     * @param newStatus the new status
     * 
     * @return true, if update regular donation status ind
     */
	boolean updateRegularDonationStatusInd (Long donorId,String newStatus);

    /**
     * This method used for to save the event status after Comapre the previous
     * status for particular event.
     * 
     * @param eventId of type Long
     * @param newEvenStatus of type String
     * @param auditAttributes the audit attributes
     * 
     * @return boolean value
     */
    boolean saveEventStatus(final Long eventId, final String newEvenStatus, AuditAttributes auditAttributes);
    
    /**
     * Count the total number of donations for a donor.
     * 
     * @param donorId the donor id
     * 
     * @return the long
     */
    long countDonationsByDonor(long donorId);
    
    /**
     * Fetch charity audit.
     * 
     * @param charityId the charity id
     * 
     * @return the charity
     */
    Charity fetchCharityAudit(Long charityId);
    
    /**
     * Fetch bank account audit.
     * 
     * @param bankAccountId the bank account id
     * 
     * @return the bank account
     */
    BankAccount fetchBankAccountAudit(Long bankAccountId);
    
    /**
     * Fetch address audit.
     * 
     * @param addressId the address id
     * 
     * @return the address
     */
    Address fetchAddressAudit(Long addressId);
    

    /**
     * Fetch Page Widget Codes for a given page id.
     * 
     * @param pageId the page id
     * 
     * @return the list< string>
     */
    List<String> fetchPageWidgets(Long pageId);
    
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
    
    /**
     * Fetch FundrasierURLDetails with given page fundraiserActivityId.
     * 
     * @param fundraiserActivityId the fundraiserActivityId
     * 
     * @return FundrasierURLDetails
     */
    public FundrasierURLDetails fetchFundraiserURLByFundraiserActivityId(Long fundraiserActivityId);

    /**
     * Fetch user profile by user id.
     * 
     * @param userId the user id
     * 
     * @return the user profile
     */
    UserProfile fetchUserProfileByUserId(Long userId);

    /**
     * Update user profile.
     * 
     * @param userProfile the user profile
     */
    void updateUserProfile(UserProfile userProfile);

    /**
     * Fetch random network activities.
     * 
     * @param networkId the network id
     * @param numberOfResults the number of results
     * @param fundraiserToExclude the fundraiser to exclude
     * 
     * @return the list< fundraiser activity>
     */
    List<FundraiserActivity> fetchRandomNetworkActivities(Long networkId, Long numberOfResults, Long fundraiserToExclude);


    /**
     * Save person and address.
     * 
     * @param personalAddress the personal address
     * 
     * @return the person
     */
    Person savePersonAndAddress(PersonalAddress personalAddress);
    
    /**
     * To fetch the alert triggers for the charity.
     * 
     * @param charityId Charity Id.
     * 
     * @return List of alert triggers.
     */
    List<AlertTrigger> fetchAlertTriggersForCharity(Long charityId);
    
    /**
     * Fetch the sum of total donation amount for a charity within last some months.
     * 
     * @param charityId Charity id.
     * @param donorId Donor Id.
     * @param periodInMonths Periods in months.
     * 
     * @return Sum of donation.
     */
    BigDecimal fetchSumOfDonationForCharityForSpecifiedPeriod(Long charityId, Long donorId, Long periodInMonths);
    
    /**
     * Fetch the count of donation amount for a charity within last some months.
     * 
     * @param charityId Charity id.
     * @param donorId Donor Id.
     * @param periodInMonths Periods in months.
     * 
     * @return count of donation.
     */
    Integer fetchCountOfDonationForCharityForSpecifiedPeriod(Long charityId, Long donorId, Long periodInMonths);
    
    /**
     * Fetch the sum of total fundraiser donation amount for a charity within last some months.
     * 
     * @param charityId Charity id.
     * @param fundraiserActivityId Fundraiser activity id.
     * @param periodInMonths Periods in months.
     * 
     * @return Sum of donation.
     */
    BigDecimal fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod(Long charityId, Long fundraiserActivityId, Long periodInMonths);
    
    /**
     * Fetch the count of fundraiser pages for a charity within last some months.
     * 
     * @param charityId Charity id.
     * @param fundraiserId Fundraiser id.
     * @param periodInMonths Periods in months.
     * 
     * @return count of pages.
     */
    Integer fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod(Long charityId, Long fundraiserId, Long periodInMonths);
    
    /**
     * Fetch fundraiser donation amount for a single event.
     * 
     * @param charityId Charity id.
     * @param fundraiserActivityId Fundraiser activity id.
     * 
     * @return Donation amount.
     */
    BigDecimal fetchFundraiserDonationAmountForSingleEvent(Long charityId, Long fundraiserActivityId);
    
    /**
     * To get the email addresses for the given alert.
     * 
     * @param alertTriggerId Alert trigger id.
     * 
     * @return List of email addresses.
     */
    List<String> fetchEmailAddressesForAlert(Long alertTriggerId);

    /**
     * To get the charity reference for the user with given id and userType.
     * <p>
     * The id can be of donor or a fundraiser identified by userType ('D' / 'F')
     * 
     * @param id the donor / fundraiser id.
     * @param charityId the charity id.
     * @param userType the user type ('D' / 'F')
     * 
     * @return comma seperated string of charity references, if any, else null.
     */
    String getCharityReference(Long id, Long charityId, String userType);

    /**
     * Updates the amendment flag for the given charity to the new status.
     * 
     * @param charityId ID of the charity to update
     * @param newStatus new value for the indicator
     * 
     * @return Operation result
     */
    boolean updateCharityAmendmentInd(Long charityId, String newStatus);

    /**
     * Updates the amendment flag for the given address to the new status.
     * 
     * @param addressId ID of the address to update
     * @param newStatus new value for the indicator
     * 
     * @return Operation result
     */
    boolean updateAddressAmendmentInd(Long addressId, String newStatus);

    /**
     * Updates the amendment flag for the given bank account to the new status.
     * 
     * @param bankAccountId ID of the bank account to update
     * @param newStatus new value for the indicator
     * 
     * @return Operation result
     */
    boolean updateBankAccountAmendmentInd(Long bankAccountId, String newStatus);

    /**
     * This is used to save the charity details in the database. Unable to use the
     * UpdateCharity method because the underlying DAO uses 'merge' and it does not
     * always update the entity.
     * 
     * @param charity Charity to save.
     */
    void updateCharityBatchResponse(Charity charity);

    /**
     * Get a fundraiser activitity for a team.
     * 
     * @param teamUrl the team url
     * 
     * @return FundraiserActivity, or null
     */
    FundraiserActivity fetchFundraiserActivityDetailsByTeamUrl(String teamUrl);

    /**
     * Added this to allow the batch system to update charity status and also the amendment flags after successful
     * process completion.
     * 
     * @param id  Charity ID.
     * @param status Batch status.
     * @param updateAmendmentInd indiactes whether the amendment flags should be updated.
     * @param cutoffDate If updating amendment flags, cutoff date to use.
     */
    public void updateCharityBatchStatus(Long id, String status, boolean updateAmendmentInd, Timestamp cutoffDate);

    /**
     * Update page status.
     * 
     * @param pageId the page id
     * @param pageStatus the page status
     */
    void updatePageStatus(Long pageId, String pageStatus);

    /**
     * Fetches list of last charity offline log status from log history
     * 
     * @param charityOfflineRegistrationId charity offline registration id
     * @return list of last charity offline log status 
     */
    List<CharityOfflineRegistrationLog> fetchLastCharityOfflineStatusList(
		Long charityOfflineRegistrationId);
    
    /**
     * This method for save  the CharityOfflineRegistrationLog Details.
     * 
     * @param charityOfflineRegistrationLog of type CharityOfflineRegistrationLog
     * 
     * @return object of type CharityOfflineRegistrationLog
     */
    CharityOfflineRegistrationLog saveCharityOfflineRegistrationLog(
            final CharityOfflineRegistrationLog charityOfflineRegistrationLog);

    /**
     * Fetch total funds raised for a FundraiserActivity.
     *
     * The total will be the sum of all donations <b>not</b> including tax relief.
     * See {@link fetchFundraiserActivityTotalDonationsTaxRelief} for the tax relief.
     *
     * @param fundraiserActivityId the fundraiser activity id
     *
     * @return the total funds raised as a BigDecimal
     */
    BigDecimal fetchFundraiserActivityTotalDonations(Long fundraiserActivityId);

    /**
     * Fetch total tax-relief on donations for a FundraiserActivity.
     *
     * The total will be the sum of all tax relief for all donations.
     * See {@link fetchFundraiserActivityTotalDonations} for the donation total.
     *
     * @param fundraiserActivityId the fundraiser activity id
     * @return the total tax-relief as a BigDecimal
     */
    BigDecimal fetchFundraiserActivityTotalDonationsTaxBack(Long fundraiserActivityId);
 	  /**
     * This method for get  the fetchDonationNotificationIndFromActivityId Details.
     * 
     * @param fundraiserActivityId of type fundraiserActivityId
     * 
     * @return boolean
     */

	boolean fetchDonationNotificationIndFromActivityId(Long fundraiserActivityId);
 	
 
    void updateDonationFailedIndicator(List<Long> donationIds);
    
    /**
     * If reporting codes have been designated for an event,
     * they are copied over to fundraiser activity associated with this page
     * 
     * @param Page page
     */
     void copyReportingCodes(final Page page);
     
     /**
      * Get the all fundraiser activities of owner of team for given non owner team member.
      * @param fundraiserId non owner fundraiser id
      * @return List of Fundraiser Activity
      */
      List<FundraiserActivity> fetchFundraiserActivityForMemberOfTeam(Long fundraiserId);     

    /**
     * Method to retrieve the total donations received from a FUndraiser for a charity, in a given period.
     * @param charityId ID of the charity to check
     * @param fundRaiserId Fundraiser ID
     * @param periodInMonths Period in months to check in.
     * @return BigDecimal total donations
     */
     BigDecimal fetchTotalFundraiserDonationForCharityForSpecifiedPeriod(Long charityId, Long fundRaiserId,
                                                                               Long periodInMonths);

    /**
     * Method to retrieve the total donations received from a FUndraiser for a charity, for a given activity.
     * @param charityId ID of the charity to check
     * @param fundRaiserActivityId Fundraiser Activity ID
     * @return BigDecimal total donations
     */
     BigDecimal fetchTotalFundraiserDonationAmountForSingleCharityEvent(Long charityId, Long fundRaiserActivityId);

     /**
      * Method to retrieve donor Id for a given user.
      * @param userId ID of the user.
      * @return Long donor id.
      */
     Long fetchDonorIdByUserId(Long userId);
}

