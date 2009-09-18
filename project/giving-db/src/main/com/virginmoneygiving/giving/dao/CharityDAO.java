package com.virginmoneygiving.giving.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.virginmoneygiving.giving.domain.Address;
import com.virginmoneygiving.giving.domain.AlertTrigger;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.Bank;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.BankAccountType;
import com.virginmoneygiving.giving.domain.BankAddress;
import com.virginmoneygiving.giving.domain.CardProvider;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAdministratorSubset;
import com.virginmoneygiving.giving.domain.CharityCategory;
import com.virginmoneygiving.giving.domain.CharityHMRCDetailsDomain;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistration;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistrationLog;
import com.virginmoneygiving.giving.domain.CharitySubset;
import com.virginmoneygiving.giving.domain.CharityVO;
import com.virginmoneygiving.giving.domain.CommissionCharityDetails;
import com.virginmoneygiving.giving.domain.Country;
import com.virginmoneygiving.giving.domain.OfflineRegModule;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.domain.PageWidget;
import com.virginmoneygiving.giving.domain.PageWidgetType;
import com.virginmoneygiving.giving.domain.PaymentCard;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.ProfanityReportModel;
import com.virginmoneygiving.giving.domain.RegisteredCharityUser;
import com.virginmoneygiving.giving.domain.RegistrationForm;
import com.virginmoneygiving.giving.domain.TrusteeDetails;
import com.virginmoneygiving.giving.domain.SearchCriteriaSubset;
import com.virginmoneygiving.giving.domain.CharityCustomFieldSubset;
import com.virginmoneygiving.giving.domain.CharityCustomFieldValueSubset;
import com.virginmoneygiving.giving.domain.RegularDonation;

/**
 * DAO to handle operations related to charity functionality.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added methods fetchAllRequiredForms,
 * fetchAllCharityCategories.
 * @author Vishakha Sawant - added method getCharityStatus.
 * @author Siva Kumar - added methods fetchAllCountrys , fetchAllCardTypes,
 * checkForProfanity , savePaymentcardDetails
 * @author Edwin Tauro - Find charity by name live
 * @author Diptirmaya Rout - added methods
 * saveCharityPageDetails,fetchCharityHomePageDetails.
 */
@SuppressWarnings("unchecked")
public interface CharityDAO {

    /**
     * Fetch particular charityDetails by given ID.
     * 
     * @param charityId of type String
     * 
     * @return Charity object details
     */
    Charity fetchCharityDetailsById(Long charityId);

    /**
     * Fetch particular charityDetails by given ID.
     * 
     * @param vmgCharityUrl the vmg charity url
     * 
     * @return Charity object details
     */
    Charity fetchCharityDetailsByVmgCharityUrl(String vmgCharityUrl);

    /**
     * Fetch charityDetails by given IDs.
     * 
     * @param charityIds of type String eg: 1,3,5
     * 
     * @return List of Charity object.
     */
    List <Charity> fetchCharityDetailsByIds(String charityIds);

 /**
  * Fetch Charity Administrator List by given Charity ID.
  * 
  * @param charityId of type Long
  * 
  * @return List of Charity administrator object.
  */
    List<CharityAdministratorSubset> findCharityAdministratorUsers(Long charityId);

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
    List<CharityOfflineRegistration> fetchCharityOfflineRegistrationData(Long charityId);

    /**
     * Save Charity Off line Registration Data.
     * 
     * @param charityOfflineRegistration of type charityOfflineRegistration
     * 
     * @return Charity Off line Registration object.
     */
    CharityOfflineRegistration saveCharityOfflineRegistrationData(CharityOfflineRegistration charityOfflineRegistration);

    /**
     * Save Charity Global Alert Settings.
     * 
     * @param alertTriggerList list for charity.
     * 
     * @return saved alertTrigger list for charity.
     */
    List<AlertTrigger> saveAlertTriggers(List<AlertTrigger> alertTriggerList);


    /**
     * Load Charity Global Alert Settings.
     * 
     * @param charityId of type long.
     * 
     * @return saved alertTrigger list for charity.
     */
    List<AlertTrigger> loadAlertTriggers(final Long charityId);

    /**
     * Find charities based on charity name. <br>
     * Will fetch all charities irrespective of their status.
     * 
     * @param charityName of type String
     * 
     * @return List of charities
     */
    List<CharitySubset> findCharityByName(String charityName);

    /**
     * Find charities based on charity name. This medhod uses commission data to
     * fetch matched charity list <br>
     * Will fetch only those charities which are having org type as 'R' -
     * Registered.
     * 
     * @param charitySearchString of type String.
     * @param startRow the start row
     * @param chunkSize the chunk size
     * 
     * @return List of objects of CharityDetails.
     */
    List<CommissionCharityDetails> findCharityByNameFromCommissionData(String charitySearchString, 
            int startRow, int chunkSize);
    
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
     * Find charities based on charity name. <br>
     * Will fetch only charities having status specified in the status code
     * parameter. <br>
     * Resultant object would contain the Id, Name and RegisteredCharityNumber
     * fields only.
     * 
     * @param charitySearchString the charity name part to search with.
     * @param statusCode the possible status code.
     * 
     * @return List of charities
     */
    List<CharitySubset> findCharitiesLikeNameForStatus(
            String charitySearchString, String statusCode);

    /**
     * find charities based on charity registration number.
     * 
     * @param charityRegistrationNumber of type String
     * 
     * @return List of charities
     */
    List<Charity> findCharityByRegistrationNumber(String charityRegistrationNumber);

    /**
     * Save the Charity Details.
     * 
     * @param charityDetails of type Charity
     * 
     * @return long value
     */
    long saveCharityDetails(Charity charityDetails);

    /**
     * Update the Charity Details.
     * 
     * @param charityDetails of type Charity
     * 
     * @return Charity object.
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
     * @param charityRegistrationNumber of type String
     * 
     * @return true for registered charity otherwise false
     */
    boolean isCharityRegistered(String charityRegistrationNumber);

    /**
     * To get charity status code.
     * 
     * @param charityId the charity id
     * 
     * @return charity status
     */
    String getCharityStatus(Long charityId);

    /**
     * Determine if the email address already exist.
     * 
     * @param emailAddress of type String
     * @param dobDay day from the date of birth.
     * @param dobMonth month from the date of birth.
     * @param dobYear year from the date of birth.
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
     * Fetch particular personDetails by given ID.
     * 
     * @param personId of type String
     * 
     * @return Person object details
     */
    Person fetchPersonById(Long personId);

    /**
     * save the payment card details.
     * 
     * @param paymentCardDetails of type PaymentCard
     * 
     * @return payment card id.
     */
    long savePaymentcardDetails(PaymentCard paymentCardDetails);

    /**
     * fetch charities belongs to the category ID passed as input parameter.
     * 
     * @param charityCategoryId Id of the table CharityCategory.
     * 
     * @return List of {@link Charity}.
     */
    List<Charity> fetchCharitiesByCategoryId(Long charityCategoryId);

    /**
     * This method used to check the vmg charity URL is exist or not.
     * 
     * @param vmgCharityURL the charity site URL.
     * 
     * @return boolean value true or false.
     */
    boolean isVmgCharityURLExist(String vmgCharityURL);

    // TODO Uncomment following after fix of whizible defect id 10026.
    /**
     * Fetch Bank account object by the given id.
     * 
     * @param charityId the charity id
     * 
     * @return BankAccount object. BankAccount fetchBankAccountById(Long
     * bankAccountId);
     */

    /**
     * Fetch bank account details for selected charity.
     * @param charityId
     *            Charity Id.
     * @return Bank details list.
     */
    List<BankAccount> fetchCharityBankDetails(Long charityId);

    /**
     * Find Regular Donor details.
     * 
     * @param donorId used to fetch details.
     * 
     * @return list of Regilar donor.
     */
    List<RegularDonation> findRegularDonationsByDonorId(Long donorId);
    
    /**
     * This method  will update the account status for selected bank account.
     * 
     * @param bankAccountId Bank Account Id
     * @param charityId Charity Id
     * @param defaultAccountIndicator Default account indicator.
     * @param userName the user name
     * @param auditAttributes the audit attributes
     * 
     * @return true or false.
     */
    Boolean updateCharityBankDetailsStatus(Long bankAccountId, Long charityId,
            String defaultAccountIndicator, String userName, AuditAttributes auditAttributes);


    /**
     * This method  will update the account deletion status for selected bank account.
     * 
     * @param charityId Charity Id.
     * @param bankAccountId Bank account id.
     * @param bankAccountStatus Bank account status.
     * @param auditAttributes the audit attributes
     * 
     * @return true or false.
     */
    Boolean removeCharityBankAccount(Long charityId, Long bankAccountId,
            String bankAccountStatus, AuditAttributes auditAttributes);

    /**
     * Fetch all charities with the given inputs.
     * 
     * @param charityName - charity Name
     * @param vmgRefNo - vmg reference number
     * @param postCode - postcode of charity
     * @param charityNumber - charity number
     * 
     * @return -  list of charities
     */
    List<CharityVO> findCharityByCriteria(String charityName,
            Long vmgRefNo, String postCode, String charityNumber);
    
    /**
     * Save the CharityPage Details.
     * 
     * @param pageDetails of type Charity
     * 
     * @return long value
     */
    long saveCharityPageDetails(Page pageDetails);

    /**
     * Fetch Page Titles and Body Contains.
     * 
     * @param charityId the charity id
     * 
     * @return pageList.
     */
    List<Page> fetchCharityHomePageDetails(Long charityId);

    /**
     * To save the charity bank account details.
     * 
     * @param bankAccount Bank account details.
     * 
     * @return True or false.
     */
    boolean saveCharityBankDetails(BankAccount bankAccount);

    /**
     * To Save bank details.
     * 
     * @param bank Bank object.
     * 
     * @return Bank.
     */
    Bank saveCharityBank(Bank bank);

    /**
     * To Save address details.
     * 
     * @param address address object.
     * 
     * @return Address.
     */
    Address saveAddress(Address address);

    /**
     * To Save bank address details.
     * 
     * @param bankAddress BankAddress object.
     * 
     * @return BankAddress.
     */
    BankAddress saveBankAddress(BankAddress bankAddress);

    /**
     * This method will update the account status for selected bank account.
     * 
     * @param bankAccountId Bank Account Id
     * @param charityId Charity Id
     * @param bankAccountStatus Default Account status.
     * @param userName the username
     * @param attributes the audit attributes
     * 
     * @return true or false.
     */
    boolean updateCharityBankAccountStatus(Long bankAccountId, Long charityId,
            String bankAccountStatus, String userName, AuditAttributes attributes);

    /**
     * Fetch HMRC reference number.
     * 
     * @param charityHMRCDetailsRequestList the hmrc list
     * @param type the type
     * 
     * @return list of HMRC details
     */
    List<CharityHMRCDetailsDomain> fetchHMRCreferenceNumber(
            List<CharityHMRCDetailsDomain> charityHMRCDetailsRequestList, String type);

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
     * @param userName the username
     * @param attributes the audit attributes
     * 
     * @return true or false.
     */
    boolean updateBankDescriptionAndType(Long bankAccountId,
            String accountDescription, String accountType, String userName,
            AuditAttributes attributes);

    /**
     * This will store email information.
     * 
     * @param profanityReportModel - profanity report.
     * 
     * @return profanity report id.
     */
    Long saveProfanityEmails(ProfanityReportModel profanityReportModel);

    /**
     * This method used to get the charity trustee details based on a charity id.
     * 
     * @param charityId the charity id.
     * 
     * @return list of trustee details.
     */
    List<TrusteeDetails> fetchCharityTrusteeDetails(Long charityId);

    /**
     * This method used to delete the trustee details based on a trustee id.
     * 
     * @param trusteeTypes The list of trustee types.
     * @param charityId The charity id.
     * @param attributes the audit attributes
     */
    void deleteCharityTrusteeDetails(List<String> trusteeTypes, Long charityId,
            AuditAttributes attributes);

    /**
     * This method used to save the trustee details.
     * 
     * @param trusteeDetails The trustee details object.
     * @param charityId the charity id
     * @param attributes the audit attributes
     * 
     * @return The trustee details object.
     */
    TrusteeDetails saveTrusteeDetails(TrusteeDetails trusteeDetails, Long charityId,
            AuditAttributes attributes);

    /**
     * Fetch Page Widget Types.
     * 
     * @return list of PageWidget Type.
     */
    List<PageWidgetType> fetchPageWidgetType();
    
    
    /**
     * Fetch Page Widget Types for the page type.
     * 
     * @param pageType the page type
     * 
     * @return list of PageWidget Type.
     */
    List<PageWidgetType> fetchPageWidgetTypeFromPageType(String pageType);
    
    /**
     * Charity Home Page Details.
     * 
     * @param pageId the id of page.
     * 
     * @return Page Object Of Charity Home Page.
     */
    Page fetchCharityHomePageById(Long pageId);

    /**
     * Fetch List of PageWidgets.
     * 
     * @param pageId the page id
     * 
     * @return list of page widget
     */
    List<PageWidget> fetchListofPageWidgetsById(Long pageId);

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
     * @param auditAttributes the audit attributes
     */
    void saveTrusteeCount(Long charityId, int trusteeCount, AuditAttributes auditAttributes);

    /**
     * This method used to store the trustee status based on a charity id.
     * 
     * @param charityId The charity id.
     * @param trusteeStatus The trustee status.
     * @param auditAttributes the audit attributes
     */
    void saveTrusteeStatus(Long charityId, String trusteeStatus, AuditAttributes auditAttributes);

    /**
     * To check whether an event is attached with an bank or not.
     * 
     * @param bankAccountId Bank Account id.
     * 
     * @return event
     */
    String checkEventAttachedWithBank(Long bankAccountId);

    /**
     * Fetch charity details using user id.
     * 
     * @param userId of type {@link Long}.
     * 
     * @return object of {@link Charity}
     */
    Charity fetchCharityByUserId(Long userId);

    /**
     * Fetch event ids for particular charity using charity Id.
     * 
     * @param charityId of type Long
     * 
     * @return list of eventIds of type {@link Long}.
     */
    List<Long> fetchEventIdsByCharityId(Long charityId);

    /**
     * Retrieves list of Custom fields matching the search criteria (by charity
     * id). UCCHA090
     * 
     * @param charityId charity id.
     * 
     * @return List of charity Custom Fields.
     */
    List<CharityCustomFieldSubset> fetchCharityCustomFields(String charityId);

    /**
     * This method save list of charity reporting custom fields.
     * <p/>
     * It uses charity id as a look up parameter to save matching charities custom field labels
     * in charityCustomField List.
     * 
     * @param charityCustomFieldList  list of charity custom field labels
     * 
     * @return boolean represents process status.
     */
     boolean saveCharityCustomFields(List<CharityCustomFieldSubset> charityCustomFieldList);

     /**
      * This method retrieves list of charity reporting entitys data
      * based on user search criteria.
      * 
      * @param searchCriteriaSubset  user search criteria
      * 
      * @return List of charity entities against search criteria.
      */
     List fetchReportingSearchResults(SearchCriteriaSubset searchCriteriaSubset);

     /**
      * This method saveCustomFieldValues.
      * 
      * @param charityCustomFieldValueSubset the sub set
      * @param charityId  the charity id
      * @param entityType the entity type
      * @param auditAttributes the audit attributes
      * 
      * @return boolean represents process status.
      */
     boolean saveCustomFieldValues(
            CharityCustomFieldValueSubset charityCustomFieldValueSubset,
            String charityId, String entityType, AuditAttributes auditAttributes);

     /**
      * This method used to get the offline charity trustee status based on a charity id.
      * 
      * @param charityId The charity id.
      * 
      * @return The status.
      */
     String getOfflineCharityTrusteeStatus(Long charityId);

     /**
      * This method is used to get the registered users based on a charity id.
      * 
      * @param charityId The charity id.
      * 
      * @return List of registered users.
      */
     List<RegisteredCharityUser> fetchRegisteredUsersOfCharityById(Long charityId);

     /**
      * Fetch all the pages related to this charity.
      * 
      * @param charityId the charity.
      * 
      * @return list of pages associated with above charity.
      */
     List<Page> fetchCharityPages(Long charityId);

     /**
      * This method is used to delete the charity user by setting the present end time.
      * 
      * @param charityId The charity id.
      * @param userId the user id
      * @param auditAttributes the audit attributes
      */
     void deleteCharityUser(Long charityId, Long userId, AuditAttributes auditAttributes);

     /**
      * Fetch email addresss for settlement.
      * 
      * @param charityIds the charity ids
      * 
      * @return the list< object[]>
      */
     List<Object[]> fetchEmailAddresssForSettlement(Set<Long> charityIds);
     
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
      * @param charityId the charityId.
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
     * If reporting codes have been designated for an event,
     * they are copied over to fundraiser activity associated with this page
     * 
     * @param Page page
     */
    void copyReportingCodes(final Page page);

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

}
