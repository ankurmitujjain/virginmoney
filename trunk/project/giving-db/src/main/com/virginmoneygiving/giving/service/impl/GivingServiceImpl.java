package com.virginmoneygiving.giving.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.constants.ServiceConstants;
import com.virginmoneygiving.giving.dao.CharityDAO;
import com.virginmoneygiving.giving.dao.DonorDAO;
import com.virginmoneygiving.giving.dao.EventDAO;
import com.virginmoneygiving.giving.dao.FundraiserDAO;
import com.virginmoneygiving.giving.dao.UserManagementDAO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.FundraiserGroupPageDetailDTO;
import com.virginmoneygiving.giving.datatransferobjects.TeamMemberDetailDTO;
import com.virginmoneygiving.giving.domain.ActivityType;
import com.virginmoneygiving.giving.domain.Address;
import com.virginmoneygiving.giving.domain.AddressType;
import com.virginmoneygiving.giving.domain.AlertTrigger;
import com.virginmoneygiving.giving.domain.AuditAttributes;
import com.virginmoneygiving.giving.domain.Bank;
import com.virginmoneygiving.giving.domain.BankAccount;
import com.virginmoneygiving.giving.domain.BankAccountType;
import com.virginmoneygiving.giving.domain.BankAddress;
import com.virginmoneygiving.giving.domain.CardProvider;
import com.virginmoneygiving.giving.domain.CharitableActivity;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.CharityAddress;
import com.virginmoneygiving.giving.domain.CharityAdminDetailsVO;
import com.virginmoneygiving.giving.domain.CharityAdministrator;
import com.virginmoneygiving.giving.domain.CharityAdministratorSubset;
import com.virginmoneygiving.giving.domain.CharityCategory;
import com.virginmoneygiving.giving.domain.CharityCustomFieldSubset;
import com.virginmoneygiving.giving.domain.CharityCustomFieldValueSubset;
import com.virginmoneygiving.giving.domain.CharityDonation;
import com.virginmoneygiving.giving.domain.CharityEventAdministrator;
import com.virginmoneygiving.giving.domain.CharityHMRCDetailsDomain;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistration;
import com.virginmoneygiving.giving.domain.CharityOfflineRegistrationLog;
import com.virginmoneygiving.giving.domain.CharityRegister;
import com.virginmoneygiving.giving.domain.CharityStatus;
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
import com.virginmoneygiving.giving.domain.OfflineRegStatus;
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
import com.virginmoneygiving.giving.domain.SearchCriteriaSubset;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventParameter;
import com.virginmoneygiving.giving.domain.SearchFundraisingEventResult;
import com.virginmoneygiving.giving.domain.TrusteeDetails;
import com.virginmoneygiving.giving.domain.UrlDetails;
import com.virginmoneygiving.giving.domain.UrlType;
import com.virginmoneygiving.giving.domain.User;
import com.virginmoneygiving.giving.domain.UserProfile;
import com.virginmoneygiving.giving.domain.UserRole;
import com.virginmoneygiving.giving.entitylisterer.ControlData;
import com.virginmoneygiving.giving.entitylisterer.ThreadLocalControlDataStorage;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.giving.vo.VMGUserDetails;

/**
 * Methods to save and fetch the Charity Details.
 *
 * @author Sejal Shah
 * @author Puneet Swarup - added methods fetchAllRequiredForms,
 *         fetchAllCharityCategories
 * @author Vishakha Sawant - added method getCharityStatus.
 * @author Siva Kumar -added methods fetchAllCountrys , fetchAllCardTypes,
 *         checkForProfanity , savePaymentcardDetails, fetchOtherOrganisedEvents
 *         , fetchOwnedOrganisedEvents.
 * @author vikas kale - added Donor related methods.
 * @author Edwin Tauro - Added method findLiveCharitiesLikeName
 * @author Tusahr Lad - Added method updateCharityAddress
 * @author Diptirmaya Rout - Added methods updateCharityDescription,
 *         saveCharityPageDetails, fetchCharityHomePageDetails.
 */
public class GivingServiceImpl implements GivingService {

    /** declare instance variable of type CharityDAO. */
    private CharityDAO charityDAO;

    /** Hold the DAO implementation for the fundraiser. */
    private FundraiserDAO fundraiserDAO;

    /** Hold the DAO implementation for the Donor. */
    private DonorDAO donorDAO;

    /** Hold the DAO implementation for the User Management. */
    private UserManagementDAO userManagementDAO;

    /** Hold the DAO implementation for the Event. */
    private EventDAO eventDAO;

    /** Logger instance. */
    private static final Logger LOGGER =
            Logger.getLogger(GivingServiceImpl.class);

    /**
     * @param charityDAO
     *            the charityDAO to set
     */
    public void setCharityDAO(CharityDAO charityDAO) {
        this.charityDAO = charityDAO;
    }

    /**
     * Setter method for userManagementDAO.
     *
     * @param userManagementDAO
     *            the userManagementDAO
     */
    public void setUserManagementDAO(UserManagementDAO userManagementDAO) {
        this.userManagementDAO = userManagementDAO;
    }

    /**
     * Setter method for donorDAO.
     *
     * @param donorDAO
     *            the donorDAO to set
     */
    public void setDonorDAO(DonorDAO donorDAO) {
        this.donorDAO = donorDAO;
    }

    /**
     * Sets the instance of the {@link FundraiserDAO}.
     *
     * @param fundraiserDAO
     *            the {@link FundraiserDAO}.
     */
    public void setFundraiserDAO(FundraiserDAO fundraiserDAO) {
        this.fundraiserDAO = fundraiserDAO;
    }

    /**
     * Sets the instance of the {@link EventDAO}.
     *
     * @param eventDAO
     *            the eventDAO to set
     */
    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Charity fetchCharityDetailsById(Long charityId) {
        return charityDAO.fetchCharityDetailsById(charityId);
    }
    
    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Charity fetchCharityDetailsByVmgCharityUrl(String vmgCharityUrl) {
        return charityDAO.fetchCharityDetailsByVmgCharityUrl(vmgCharityUrl);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Charity> fetchCharityDetailsByIds(String charityIds) {
        return charityDAO.fetchCharityDetailsByIds(charityIds);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityAdministratorSubset> findCharityAdministratorUsers(
            Long charityId) {
        return charityDAO.findCharityAdministratorUsers(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OfflineRegModule> fetchModuleData(String moduleCode) {
        return charityDAO.fetchModuleData(moduleCode);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityOfflineRegistration> fetchCharityOfflineRegData(
            Long charityId) {
        return charityDAO.fetchCharityOfflineRegistrationData(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityOfflineRegistration saveCharityOfflineRegData(
            CharityOfflineRegistration charityOfflineRegistration) {
        return charityDAO.saveCharityOfflineRegistrationData(charityOfflineRegistration);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AlertTrigger> saveAlertTriggers(
            List<AlertTrigger> alertTriggerList) {
        return charityDAO.saveAlertTriggers(alertTriggerList);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<AlertTrigger> loadAlertTriggers(Long charityId) {
        return charityDAO.loadAlertTriggers(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharitySubset> findCharityByName(String charityName) {

        return charityDAO.findCharityByName(charityName);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int findTotalCountCharityByNameFromCommissionData(
            String charitySearchString) {
        return charityDAO
                .findTotalCountCharityByNameFromCommissionData(charitySearchString);
    }
    
    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CommissionCharityDetails> findCharityByNameFromCommissionData(
            String charitySearchString, int startRow, int chunkSize) {
        return charityDAO
                .findCharityByNameFromCommissionData(charitySearchString, startRow, chunkSize);
    }    

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharitySubset> findLiveCharitiesLikeName(String charityName) {
        return charityDAO.findCharitiesLikeNameForStatus(charityName,
                MasterDataCodeConstants.CHARITY_STATUS_CODE_LIV);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Charity> findCharityByRegNumber(String charityRegNumber) {
        return charityDAO.findCharityByRegistrationNumber(charityRegNumber);
    }

    /** {@inheritDoc} */
    public long saveCharityDetails(Charity charityDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityDetails() method - START");
        }
        long charityId = charityDAO.saveCharityDetails(charityDetails);
        CharityOfflineRegistration charityOfflineRegistration =
                getCharityOfflineRegistration();
        Charity charity = new Charity();
        charity.setId(Long.valueOf(charityId));
        charityOfflineRegistration.setCharity(charity);
        
        // Set the created user for auditing purpose in thread local object.
        final Set<CharityAdministrator> charityAdminList = charityDetails.getCharityAdministrators();        
        for (final CharityAdministrator charityAdministrator : charityAdminList) {
            ControlData controlData = ThreadLocalControlDataStorage.get();
            controlData.setUsername(String.valueOf(charityAdministrator.getUserRole().getUser().getId()));            
        }
        
        charityDAO.saveCharityOfflineRegistrationData(charityOfflineRegistration);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityDetails() method - END");
        }
        return charityId;
    }

    /**
     * Method to set default charity registration statuses. Setting all statuses
     * to OUT_STD as a default status.
     *
     * @return object of {@link CharityOfflineRegistration}.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private CharityOfflineRegistration getCharityOfflineRegistration() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::getCharityOfflineRegistration() method - START");
        }
        CharityOfflineRegistration charityOfflineRegistration =
                new CharityOfflineRegistration();
        OfflineRegStatus offlineRegStatus =
                getOfflineRegStatus(MasterDataCodeConstants.OfflineRegStatus.ORS_OUTSTANDING
                        .getCode());
        charityOfflineRegistration.setBankStatementStatus(offlineRegStatus);
        charityOfflineRegistration.setCharityVerifyStatus(offlineRegStatus);
        charityOfflineRegistration.setGiftFormStatus(offlineRegStatus);
        charityOfflineRegistration.setHmrcFormStatus(offlineRegStatus);
        charityOfflineRegistration.setRegFeeStatus(offlineRegStatus);
        // charityOfflineRegistration.setTrusteeStatus(offlineRegStatus);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::getCharityOfflineRegistration() method - END");
        }
        return charityOfflineRegistration;
    }

    /**
     * Create a new instance of {@link OfflineRegStatus} object using the
     * offline registration status code.
     *
     * @param offlineRegStatusCode
     *            Offline Registration Status Code.
     * @return {@link CharityRegister}.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private static OfflineRegStatus getOfflineRegStatus(
            String offlineRegStatusCode) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::getOfflineRegStatus() method - START");
        }
        final OfflineRegStatus offlineRegStatus = new OfflineRegStatus();
        offlineRegStatus.setCode(offlineRegStatusCode);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::getOfflineRegStatus() method - END");
        }
        return offlineRegStatus;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Charity updateCharityDetails(Charity charityDetails) {
        return charityDAO.updateCharityDetails(charityDetails);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityCategory> fetchAllCharityCategories() {
        return charityDAO.fetchAllCharityCategories();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<RegistrationForm> fetchAllRequiredForms() {
        return charityDAO.fetchAllRequiredForms();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean isCharityRegistered(String charityRegNumber) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::isCharityRegistered() method - START");
        }
        boolean charityRegistered = false;
        charityRegistered = charityDAO.isCharityRegistered(charityRegNumber);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::isCharityRegistered() method - START");
        }
        return charityRegistered;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean isAccountExist(String emailAddress, Integer dobDay,
            Integer dobMonth, Integer dobYear) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::isAccountExist() method - START");
        }
        boolean accountExists = false;
        accountExists =
                charityDAO.isAccountExist(emailAddress, dobDay, dobMonth,
                        dobYear);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::isAccountExist() method - START");
        }
        return accountExists;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public String getCharityStatus(Long charityId) {
        return charityDAO.getCharityStatus(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Country> fetchAllCountries() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchAllCountries() method");
        }
        return charityDAO.fetchAllCountries();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CardProvider> fetchAllCardTypes() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchAllCardTypes() method");
        }
        return charityDAO.fetchAllCardTypes();
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Person fetchPersonById(Long personId) {
        return charityDAO.fetchPersonById(personId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public long savePaymentcardDetails(PaymentCard paymentCardDetails) {
        return charityDAO.savePaymentcardDetails(paymentCardDetails);
    }

    /** {@inheritDoc} */
    public Long saveFundraiser(Fundraiser fundraiser) {
        return fundraiserDAO.saveFundraiser(fundraiser);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Charity> fetchCharitiesByCategoryId(Long charityCategoryId) {
        return charityDAO.fetchCharitiesByCategoryId(charityCategoryId);
    }

    /* Esakki Yadav Added this methods for UCFUR0020 */

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Event> fetchAllEvents(String eventName) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchAllEvents() method");
        }
        return fundraiserDAO.findEventsByName(eventName);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Location> fetchAllLocations() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchAllLocations() method");
        }
        return fundraiserDAO.fetchAllLocations();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<ActivityType> fetchAllActivityTypes(String reason) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchAllActivityTypes() method");
        }
        return fundraiserDAO.fetchAllActivityTypes(reason);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<SearchFundraisingEventResult> searchFundraisingEvent(
            SearchFundraisingEventParameter parameter) {
        return fundraiserDAO.searchFundraisingEvent(parameter);
    }

    /* End of methods for UCFUR0020 */

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, 
        noRollbackFor = NoResultException.class)
    public PaymentCard fetchPaymentcardDetails(Long personId) {
        return donorDAO.fetchPaymentcardDetails(personId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public FundraiserActivity fetchFundraiserActivityDetailsById(
            Long fundraiserActivityId) {
        return fundraiserDAO.fetchFundraiserActivityById(fundraiserActivityId);
    }

    /* End of methods for UCFUR0020 */

    /** {@inheritDoc} */
    public Long saveDonorPersonalDetails(Donor donor) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveDonorPersonalDetails() method");
        }
        return donorDAO.saveDonorPersonalDetails(donor);
    }
    
    /** {@inheritDoc} */
    public Person savePerson(Person person) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::savePerson() method");
        }
        return donorDAO.savePerson(person);
    }
    
    /** {@inheritDoc} */
    public Person savePersonAndAddress(PersonalAddress personalAddress){
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::savePersonAndAddress() method");
        }
        return donorDAO.savePersonAndAddress(personalAddress);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Donor fetchDonorPersonalDetails(Long donorId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchDonorPersonalDetails() method");
        }
        return donorDAO.fetchDonorPersonalDetails(donorId);
    }
    
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public FundraiserActivity saveFundraisingReason(
            FundraiserActivity fundraiserActivity) {
        return fundraiserDAO.saveFundraisingReason(fundraiserActivity);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Fundraiser fetchFundraiserDetailsById(Long fundraiserId) {
        return fundraiserDAO.fetchFundraiserDetailsById(fundraiserId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Donor updateDonorPersonalDetails(Donor donor) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateDonorPersonalDetails() method");
        }
        return donorDAO.updateDonorPersonalDetails(donor);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Donation saveDonationDetails(Donation donation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveDonationDetails() method");
        }
        return donorDAO.saveDonationDetails(donation);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityDonation saveCharityDonation(CharityDonation charityDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityDonation() method");
        }
        return donorDAO.saveCharityDonation(charityDonation);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public RegularDonation saveRegularDonation(RegularDonation regDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveRegularDonation() method");
        }
        return donorDAO.saveRegularDonation(regDonation);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public FundraiserActivityDonation saveFundraiserActivityDonation(
            FundraiserActivityDonation activityDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveFundraiserActivityDonation() method");
        }
        return donorDAO.saveFundraiserActivityDonation(activityDonation);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean isURLAvailable(String url, String urlType) {
        return fundraiserDAO.checkIfUrlIsAvailable(url, urlType);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getRegisteredUserCount(String firstname, String lastname) {
        return fundraiserDAO.getRegisteredUserCount(firstname, lastname);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void storeUrl(Fundraiser fundraiser) {
        fundraiserDAO.storeUrl(fundraiser);

    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<UserRole> fetchUserRoles(String emailAddress, int dobDay,
            int dobMonth, int dobYear) {
        return userManagementDAO.fetchUserRoles(emailAddress, dobDay, dobMonth,
                dobYear);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Event getEventDetailsById(Long eventId) {
        return fundraiserDAO.getEventDetailsById(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCharityStatus(Long id) {
        Charity charity = charityDAO.fetchCharityDetailsById(id);
        CharityStatus charityStatus = null;
        if (charity != null && charity.getCharityStatus() != null) {
            charityStatus = new CharityStatus();
            charityStatus.setCode(ServiceConstants.PROCESSED);
            charity.setCharityStatus(charityStatus);
        }
        charityDAO.saveCharityDetails(charity);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean isPaymentCardDetailsExist(String emailAddress, int dobDay,
            int dobMonth, int dobYear) {
        return fundraiserDAO.isPaymentCardDetailsExist(emailAddress, dobDay,
                dobMonth, dobYear);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Long fetchDonorIdByUserRoleId(Long userRoleId) {
        return donorDAO.fetchDonorIdByUserRoleId(userRoleId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BankAccount> fetchCharityBankDetails(Long charityId) {
        return charityDAO.fetchCharityBankDetails(charityId);
    }
    
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BankAccount> fetchCharityBankDetailsByAccountType(Long charityId,
            List<BankAccountType> charityBankTypeList) {
        return eventDAO.fetchCharityBankDetailsByAccountType(charityId, charityBankTypeList);
    }    

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean isVmgCharityURLExist(final String vmgCharityURL) {
        return charityDAO.isVmgCharityURLExist(vmgCharityURL);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Donor fetchDonorDetails(Long donationId) {
        return donorDAO.fetchDonorDetails(donationId);
    }
    
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Person fetchPersonDetailsOfDonation(Long donationId) {
        return donorDAO.fetchPersonDetailsOfDonation(donationId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Person fetchPersonDetails(String emailAddress, int dobDay,
            int dobMonth, int dobYear) {
        return userManagementDAO.fetchPersonDetails(emailAddress, dobDay,
                dobMonth, dobYear);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean updateCharityBankDetailsStatus(Long bankAccountId,
            Long charityId, String defaultAccountIndicator, String userName,
            AuditAttributes attributes) {
        return charityDAO.updateCharityBankDetailsStatus(bankAccountId,
                charityId, defaultAccountIndicator, userName, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<RegularDonation> findRegularDonationsByDonorId(final Long donorId) {
        return charityDAO.findRegularDonationsByDonorId(donorId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public RegularDonation getRegularDonation(Long id) {
        return donorDAO.getRegularDonation(id);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public RegularDonation updateRegularDonation(RegularDonation regularDonation) {
        return donorDAO.updateRegularDonation(regularDonation);
    }


    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public RegularDonation cancelRegularDonation(Long regularDonationId) {
        return donorDAO.cancelRegularDonation(regularDonationId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Donation updateDonation(Donation donation) {
        return donorDAO.updateDonation(donation);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityDonation updateCharityDonation(CharityDonation charityDonation) {
        return donorDAO.updateCharityDonation(charityDonation);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityDonation fetchCharityDonation(Long id) {
        return donorDAO.fetchCharityDonation(id);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Donation fetchDonation(Long id) {
        return donorDAO.fetchDonation(id);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateCharityAddress(Long id, Address adminAddress,
            AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateCharityAddress() method - START");
        }
        final Charity charity = charityDAO.fetchCharityDetailsById(id);
        final Set<CharityAddress> charityAddresses = charity.getCharityAddresses();
        boolean updateCharityAdminAddress = false;

        for (CharityAddress address : charityAddresses) {
            String addressTypeCode = "";

            if (address.getAddress().getAddressType() != null) {
                addressTypeCode = address.getAddress().getAddressType().getCode();
            }

            if (addressTypeCode.equals(adminAddress.getAddressType().getCode())) {
                adminAddress.setId(address.getAddress().getId());
                adminAddress.setAddressType(address.getAddress().getAddressType());
               // DAOHelper.setAuditInformation(attributes, adminAddress);
                address.setAddress(adminAddress);
                updateCharityAdminAddress = true;
                break;
            }
        }

        if (updateCharityAdminAddress) {
            //DAOHelper.setAuditInformation(attributes, adminAddress);
            //charityAddresses.add(adminAddress);
            //charity.setCharityAddresses(charityAddresses);
            //charity = charityDAO.updateCharityDetails(charity);
            charityDAO.updateCharityDetails(charity);
        }

        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("GivingServiceImpl.updateCharityAdminAddress end status : "
	                        + updateCharityAdminAddress);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateCharityAddress() method - END");
        }
        return updateCharityAdminAddress;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User getUser(String emailAddress, int dayOfBirth, int monthOfBirth,
            int yearOfBirth) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::getUser() method - START");
        }
        User user =
                userManagementDAO.getUser(emailAddress, dayOfBirth,
                        monthOfBirth, yearOfBirth);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::getUser() method - END");
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public void saveUser(User user) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveUser() method - START");
        }
        userManagementDAO.saveUser(user);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveUser() method - END");
        }

    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean removeCharityBankAccount(Long charityId, Long bankAccountId,
            String bankAccountStatus, AuditAttributes auditAttributes) {
        return charityDAO.removeCharityBankAccount(charityId, bankAccountId,
                bankAccountStatus, auditAttributes);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityVO> fetchCharityList(String charityName, Long vmgRefNo,
            String postCode, String charityNumber) {
        return charityDAO.findCharityByCriteria(charityName, vmgRefNo,
                postCode, charityNumber);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<FundraiserDonorVO> fetchFundraiserAndDonorList(String name,
            Long vmgRefNo, String postCode) {
        return fundraiserDAO.fetchFundraisersAndDonorsByCriteria(name,
                vmgRefNo, postCode);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Event> fetchOtherOrganisedEvents(
            final SearchFundraisingEventParameter searchEventParameters) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchOtherOrganisedEvents() method");
        }
        return eventDAO.fetchOtherOrganisedEvents(searchEventParameters);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OwnOrganisedEventsDVO> fetchOperationalOrganisedEvents(
            List<String> statusList, Long userId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchOperationalOrganisedEvents() method");
        }
        return eventDAO.fetchOperationalOrganisedEvents(statusList, userId);
    }

    /** {@inheritDoc} */
    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OwnOrganisedEventsDVO> fetchOwnedOrganisedEvents(
            List<String> statusList, Long charityId, Long userId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchOwnedOrganisedEvents() method");
        }
        return eventDAO
                .fetchOwnedOrganisedEvents(statusList, charityId, userId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OwnOrganisedEventsDVO> fetchOwnedFourLatestOrganisedEvents(
            List<String> statusList, Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchOwnedFourLatestOrganisedEvents() method");
        }
        return eventDAO.fetchOwnedFourLatestOrganisedEvents(statusList,
                charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public FundraisingInfoDVO fetchNineRandomFundraisers(Long eventId,
            Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchNineRandomFundraisers() method");
        }
        //return eventDAO.fetchNineRandomFundraisers(eventId, charityId);
        return eventDAO.fetchTheLatestFundraisers(eventId, charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityAdminDetailsVO> fetchCharityEventAdminList(
            final Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchCharityEventAdminList() method");
        }
        return eventDAO.fetchCharityEventAdminList(charitableActivityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityAdminDetailsVO> fetchCharityAdminList(
            final Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchCharityAdminList() method");
        }
        return eventDAO.fetchCharityAdminList(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public EventHomepageDetailsDVO fetchEventDetails(Long eventId,
            Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchEventDetails() method");
        }
        return eventDAO.getEventDetails(eventId, charityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateCharityDescription(Long id, String charityDescription,
            AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateCharityDescription() method - START");
        }
        //Charity charity = charityDAO.fetchCharityDetailsById(id);
        boolean updateCharityDescription = false;
        /*
         * charity.setExtendedDescription(charityDescription); charity =
         * charityDAO.updateCharityDetails(charity); / Check For Updtae or not
         * and Send E-mail to CharityAdministrator Accordingly
         */
        /*
         * if (charityDAO.fetchCharityDetailsById(id).getExtendedDescription()
         * != null) { if
         * (charityDAO.fetchCharityDetailsById(id).getExtendedDescription()
         * .trim().equals(charityDescription.trim())) { updateCharityDescription
         * = true; } } else if (charityDAO.fetchCharityDetailsById(id)
         * .getExtendedDescription() == null && charityDescription == null) {
         * updateCharityDescription = true; }
         */
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateCharityDescription() method - END");
        }
        return updateCharityDescription;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public long saveCharityPageDetails(Page charityPageDetails) {
        return charityDAO.saveCharityPageDetails(charityPageDetails);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Page> fetchCharityHomePageDetails(Long charityId) {
        return charityDAO.fetchCharityHomePageDetails(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBatchStatus(Long id, String status) {
        Charity charity = charityDAO.fetchCharityDetailsById(id);
        if (charity != null) {
            charity.setBatchStatus(status);
        }
        charityDAO.saveCharityDetails(charity);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveCharityBankDetails(BankAccount bankAccount) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityBankDetails() method - START");
        }
        Bank bank = bankAccount.getBank();
        Set<BankAddress> bankAddresses = bank.getBankAddress();
        bank.setBankAddress(null);
        bank = charityDAO.saveCharityBank(bank);
        AddressType addressType = new AddressType();
        addressType.setCode(MasterDataCodeConstants.BANK);
        for (BankAddress address : bankAddresses) {
            address.getAddress().setAddressType(addressType);

            address.setAddress(charityDAO.saveAddress(address.getAddress()));
            address.setBank(bank);
            address = charityDAO.saveBankAddress(address);
        }

        bankAccount.setBank(bank);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityBankDetails() method - END");
        }
        return charityDAO.saveCharityBankDetails(bankAccount);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUserPersonalDetails(User user) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateUserPersonalDetails() method");
        }
        return fundraiserDAO.updateUserPersonalDetails(user);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Event saveEventDetails(final Event eventDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveEventDetails() method");
        }
        return eventDAO.saveEventDetails(eventDetails);

    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Address saveAddress(final Address contactAddress) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveAddress() method");
        }
        return charityDAO.saveAddress(contactAddress);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public EventActivity saveEventActivity(final EventActivity eventActivity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveEventActivity() method");
        }
        return eventDAO.saveEventActivityDeatils(eventActivity);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEventActivitiesForEvent(final Long eventId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::deleteEventActivitiesForEvent() method");
        }
        eventDAO.deleteEventActivitiesForEvent(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharitableActivity saveCharitableActivity(
            final CharitableActivity charitableActivity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharitableActivity() method");
        }
        return eventDAO.saveCharitableActivityDetails(charitableActivity);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharitableActivity fetchCharitableActivityDetail(
            final Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchCharitableActivityDetail() method");
        }
        return eventDAO.fetchCharitableActivityDetail(charitableActivityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public EventFeeDetails saveEventFeeDetails(
            final EventFeeDetails eventFeeDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveEventFeeDetails() method");
        }
        return eventDAO.saveEventFeeDetails(eventFeeDetails);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEventFeeDetails(Long eventFeeId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::deleteEventFeeDetails() method");
        }
        eventDAO.deleteEventFeeDetails(eventFeeId, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityEventAdministrator saveCharityEventAdministrator(
            final CharityEventAdministrator charityEventAdministrator) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityEventAdministrator() method");
        }
        return eventDAO
                .saveCharityEventAdministrator(charityEventAdministrator);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCharityEventAdministratorsForEvent(
            final Long charitableActivityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::deleteCharityEventAdministratorsForEvent() method");
        }
        eventDAO.deleteCharityEventAdministratorsForEvent(charitableActivityId, attributes);

    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityAdministrator fetchCharityAdministrators(
            final Long charityAdminId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchCharityAdministrators() method");
        }
        return eventDAO.fetchCharityAdministrators(charityAdminId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public BankAccount fetchBankAccountDetails(final Long bankAccountId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchBankAccountDetails() method");
        }
        return eventDAO.fetchBankAccountDetails(bankAccountId);
    }


    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateCharityBankAccountStatus(Long bankAccountId,
            Long charityId, String bankAccountStatus, String userName,
            AuditAttributes attributes) {
        return charityDAO.updateCharityBankAccountStatus(bankAccountId,
                charityId, bankAccountStatus, userName, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean isFundraiserTeamAlreadyExist(String teamName) {
        return fundraiserDAO.isFundraiserTeamAlreadyExist(teamName);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveFundraiserGroup(FundraiserGroup fundraiserGroup) {
        return fundraiserDAO.saveFundraiserGroup(fundraiserGroup);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<TeamMemberDetailDTO> searchRegisteredFundraiserByEmailAddress(
            String emailAddress) {
        return fundraiserDAO
                .searchRegisteredFundraiserByEmailAddress(emailAddress);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveFundraiserTeamMember(
            FundraiserGroupMember fundraiserGroupMember) {
        return fundraiserDAO.saveFundraiserTeamMember(fundraiserGroupMember);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFundraiserTeamMember(
            FundraiserGroupMember fundraiserGroupMember) {
        fundraiserDAO.removeFundraiserTeamMember(fundraiserGroupMember);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<FundraiserGroupMember> fetchTeamMembers(long teamId) {
        return fundraiserDAO.fetchTeamMembers(teamId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public FundraiserGroup findFundraiserGroupById(long fundraiserTeamId) {
        return fundraiserDAO.findFundraiserGroupById(fundraiserTeamId);
    }
    
    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public FundraiserGroup findFundraiserGroupByIdAndMemeber(long fundraiserTeamId, long memberId) {
        return fundraiserDAO.findFundraiserGroupByIdAndMemeber(fundraiserTeamId, memberId);
    }    

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public FundraiserGroupType findFundraiserGroupTypeById(String code) {
        return fundraiserDAO.findFundraiserGroupTypeById(code);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityHMRCDetailsDomain> fetchHMRCreferenceNumber(
            List<CharityHMRCDetailsDomain> charityHMRCDetailsRequestList,
            String type) {
        return charityDAO.fetchHMRCreferenceNumber(
                charityHMRCDetailsRequestList, type);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public EventFeeDetailsVO fetchEventFeeList(Long eventId) {
        return eventDAO.fetchEventFeeList(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Event fetchEventFeeStatus(Long eventId) {
        return eventDAO.fetchEventFeeStatus(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public UrlType findUrlTypeByCode(String code) {

        return fundraiserDAO.findUrlTypeByCode(code);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BankAccountType> fetchBankAccountType() {
        return charityDAO.fetchBankAccountType();
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateBankDescriptionAndType(Long bankAccountId,
            String accountDescription, String accountType, String userName,
            AuditAttributes attributes) {
        return charityDAO.updateBankDescriptionAndType(bankAccountId,
                accountDescription, accountType, userName, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public CharitableActivity fetchCancelEventRight(Long eventId, Long charityId) {
        return eventDAO.fetchCancelEventRight(eventId, charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Event fetchEventDetail(Long eventId) {
        return eventDAO.fetchEventDetail(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Page fetchPage(String userUrl, String pageUrl) {
        return fundraiserDAO.fetchPage(userUrl, pageUrl);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Page fetchPage(Long id) {
        return fundraiserDAO.fetchPage(id);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public PageType fetchPageType(String code) {
        return fundraiserDAO.fetchPageType(code);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public PageWidgetType fetchPageWidgetType(String code) {
        return fundraiserDAO.fetchPageWidgetType(code);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public PageStatus fetchPageStatus(String code) {
        return fundraiserDAO.fetchPageStatus(code);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Page savePage(Page page) {
        return fundraiserDAO.savePage(page);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Fundraiser fetchFundraiser(long fundraiserId,
            boolean includeNonOwnerTeamActivities) {
        return fundraiserDAO.fetchFundraiser(fundraiserId,
                includeNonOwnerTeamActivities);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Fundraiser fetchFundraiserByUrl(String url,
            boolean includeNonOwnerTeamActivities) {
        return fundraiserDAO.fetchFundraiserByUrl(url,
                includeNonOwnerTeamActivities);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Fundraiser fetchFundraiserForUser(long userId) {
        return fundraiserDAO.fetchFundraiserForUser(userId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<FundraiserActivity> findTeamActivitiesWhereNotOwner(
            long fundraiserId) {
        return fundraiserDAO.findTeamActivitiesWhereNotOwner(fundraiserId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveProfanityEmails(ProfanityReportModel profanityReportModel) {
        return charityDAO.saveProfanityEmails(profanityReportModel);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CharityAdminDetailsVO> fetchCharityAdministratorEmailList(
            Long eventId) {
        return eventDAO.fetchCharityAdministratorEmailList(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateEventStatus(Long eventId, String newStatus) {
        return eventDAO.updateEventStatus(eventId, newStatus);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Fundraiser closeFundraiserAccount(Fundraiser fundraiser) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::closeFundraiserAccount() method - START");
        }
        UrlDetails oldUrlDetails = fundraiser.getUrlDetails();
        fundraiser.setUrlDetails(null); // nullify it ..

        final AuditAttributes attributes = new AuditAttributes();
        attributes.setUpdatedIPAddress(fundraiser.getUpdatedIPAddress());
        attributes.setUpdatedProcess(fundraiser.getUpdatedProcess());
        attributes.setUpdatedUser(fundraiser.getUpdatedUser());

        // Cihan's last code - to be uncommented and tested.
        // 3. Close all active individual fundraising pages as well for network related pages
        // givingDBService.fetchFundraiser(fundraiserId)
        if (fundraiser.getFundraiserActivities() != null
                && fundraiser.getFundraiserActivities().size() > 0) {
            for (FundraiserActivity activity : fundraiser
                    .getFundraiserActivities()) {
               // DAOHelper.setAuditInformation(attributes, activity);
                Page page = activity.getFundraiserPage();
                if(page!=null && MasterDataCodeConstants.FUNDRAISER_AS_INDIVIDUAL.equals(activity.getFundraisingAsIndicator())){
                	page.setPageStatus(fetchPageStatus(MasterDataCodeConstants.PAGE_STATUS_INACTIVE));
                } else if(page!=null && activity.getFundraiserGroup() != null 
                        && activity.getFundraiserGroup().getFundraiserGroupType().
                            getCode().equals(MasterDataCodeConstants.GROUP_TYPE_NETWORK)) {
                    page.setPageStatus(fetchPageStatus(MasterDataCodeConstants.PAGE_STATUS_INACTIVE));
                }

            }
        }
        List<FundraiserGroupDetailDTO> fundraiserGroups =new ArrayList<FundraiserGroupDetailDTO>();
        List<FundraiserGroupDetailDTO> fundraiserTeams=findFundraiserGroupsbyFundraiserId(fundraiser.getId(),MasterDataCodeConstants.GROUP_TYPE_TEAM);
        List<FundraiserGroupDetailDTO> fundraiserNetworks=findFundraiserGroupsbyFundraiserId(fundraiser.getId(),MasterDataCodeConstants.GROUP_TYPE_NETWORK);
        fundraiserGroups.addAll(fundraiserTeams);
        fundraiserGroups.addAll(fundraiserNetworks);
        
        for(FundraiserGroupDetailDTO fundraiserGroupDetail:fundraiserGroups){
        	FundraiserGroup group=findFundraiserGroupById(fundraiserGroupDetail.getFundraiserGroupId());
            // Remove the fundraiser from any teams that they belong to
            if (group != null) {
              //  DAOHelper.setAuditInformation(attributes, group);
                Set<FundraiserGroupMember> groupMembers =
                        group.getGroupMembers();
                for (FundraiserGroupMember groupMember : groupMembers) {
                    if (groupMember.getFundraiser().getId().equals(
                            fundraiser.getId())) {
                  //      DAOHelper.setAuditInformation(attributes, groupMember);
                        fundraiserDAO
                                .removeFundraiserTeamMember(groupMember);
                        break;
                    }
                }

            }        	
        }
        
        UserProfile userProfile =
                fundraiser.getUserRole().getUser().getUserProfile();
        if (userProfile != null) {
            // 5. Set 'personal preferences' to 'do not contact'
            userProfile.setContactByEmailIndicator(MasterDataCodeConstants.NO);
            userProfile.setContactByMailIndicator(MasterDataCodeConstants.NO);
            userProfile.setContactByPhoneIndicator(MasterDataCodeConstants.NO);
            userProfile.setContactBySmsIndicator(MasterDataCodeConstants.NO);
            userProfile.setAllCharityMarketingIndicator(MasterDataCodeConstants.NO);
            userProfile.setCharityMarketingIndicator(MasterDataCodeConstants.NO);
            userProfile.setVirginGroupMarketingIndicator(MasterDataCodeConstants.NO);
            userProfile.setVirginMoneyMarketingIndicator(MasterDataCodeConstants.NO);
            // 10. Set the Gift Aid status to 'not eligible for gift aid' GIFT
            // AID
            if (userProfile.getGiftAidUsuallyAppliesIndicator() != null) {
                userProfile.setGiftAidUsuallyAppliesIndicator(MasterDataCodeConstants.NO);
            }
            //  DAOHelper.setAuditInformation(attributes, userProfile);
        }


        // cancel any regular donations that the user has
        Long userId = fundraiser.getUserRole().getUser().getId();
        List<RegularDonation> regularDonations = findRegularDonationsByDonorId(donorDAO.fetchDonorIdByUserId(userId));
        if(regularDonations != null){
            for(RegularDonation regularDonation : regularDonations){
                regularDonation.setGiftAidEligibleIndicator(MasterDataCodeConstants.NO);
                regularDonation.setStatusIndicator(MasterDataCodeConstants.REGULAR_DONATION_STATUS_CANCELLED);
                donorDAO.saveRegularDonation(regularDonation);
            }
        }


        Fundraiser returningFundraiser =
                fundraiserDAO.closeFundraiseAccount(fundraiser);
        // TO DO if the user is a donor, cancel any future regular payment
        // collections

        // Release the fundraisers URL for re-use
        if (oldUrlDetails != null) {
          //  DAOHelper.setAuditInformation(attributes, oldUrlDetails);
            removeUrlDetails(oldUrlDetails);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::closeFundraiserAccount() method - END");
        }
        return returningFundraiser;

    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUrlDetails(UrlDetails urlDetails) {
        fundraiserDAO.removeUrlDetails(urlDetails);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TrusteeDetails> fetchCharityTrusteeDetails(Long charityId) {
        return charityDAO.fetchCharityTrusteeDetails(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCharityTrusteeDetails(List<String> trusteeTypes,
            Long charityId, AuditAttributes attributes) {
        charityDAO.deleteCharityTrusteeDetails(trusteeTypes, charityId, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public TrusteeDetails saveTrusteeDetails(TrusteeDetails trusteeDetails,
            Long charityId, AuditAttributes attributes) {
        return charityDAO.saveTrusteeDetails(trusteeDetails, charityId, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateFundraiserEventFee(Long fundraiserId, Long eventId,
            String feeRef, String feeSituation, BigDecimal feeAmount, Long fundraiserActivityId, AuditAttributes attributes) {
        return eventDAO.updateFundraiserEventFee(fundraiserId, eventId, feeRef,
                feeSituation, feeAmount, fundraiserActivityId, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public int fetchOnlineRegisteredFundraiserCount(Long eventId) {
        return eventDAO.fetchOnlineRegisteredFundraiserCount(eventId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public EventFeeDetails fetchEventFeeDetailsByFeeId(Long feeId) {
        return eventDAO.fetchEventFeeDetailsByFeeId(feeId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<FundraiserGroupPageDetailDTO> findPageDetailsbyFundraiserId(
            Long fundraiserId) {
        return fundraiserDAO.findPageDetailsbyFundraiserId(fundraiserId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void updatePagewithFundraiserGroupID(Long pageId,
            Long fundraiserGroupId, AuditAttributes attributes) {
        fundraiserDAO
                .updatePagewithFundraiserGroupID(pageId, fundraiserGroupId,
                        attributes);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Page findPageById(long pageId) {
        return fundraiserDAO.findPageById(pageId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteCharitableActivityDetails(Long charitableActivityId,
            AuditAttributes attributes) {
        return eventDAO.deleteCharitableActivityDetails(charitableActivityId,
                attributes);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true)
    public String fetchCharityLogo(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::closeFundraiserAccount() method");
        }
        return eventDAO.fetchCharityLogo(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<String> fetchUserPermissions(final String userName,
            final Integer dobDay, final Integer dobMonth, final Integer dobYear) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchUserPermissions() method - START");
        }
        final List<String> permissions =
                userManagementDAO.fetchUserPermissions(userName, dobDay,
                        dobMonth, dobYear);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchUserPermissions() method - END");
        }
        return permissions;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean grantPermissions(Long userId, List<String> permissions, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::grantPermissions() method - START");
        }
        final boolean result =
                userManagementDAO.grantPermissions(userId, permissions, attributes);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::grantPermissions() method - END");
        }
        return result;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<FundraiserGroupDetailDTO> findFundraiserGroupsbyFundraiserId(
            Long fundraiserId, String fundraiserGroupType) {
        return fundraiserDAO.findFundraiserGroupsbyFundraiserId(fundraiserId,
                fundraiserGroupType);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<EventFeeDetails> fetchEventFeeDetails(
            final Long charitableActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchEventFeeDetails() method");
        }
        return eventDAO.fetchEventFeeDetails(charitableActivityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<PageWidgetType> fetchPageWidgetType() {
        return charityDAO.fetchPageWidgetType();
    }
    
    /** {@inheritDoc} */

    public List<PageWidgetType> fetchPageWidgetTypeFromPageType(String pageType) {
        return charityDAO.fetchPageWidgetTypeFromPageType(pageType);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Page fetchCharityHomepageById(Long id) {
        return charityDAO.fetchCharityHomePageById(id);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PageWidget> fetchListofPageWidgetsById(Long id) {
        return charityDAO.fetchListofPageWidgetsById(id);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public String checkEventAttachedWithBank(Long bankAccountId) {
        return charityDAO.checkEventAttachedWithBank(bankAccountId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<String> fetchOperationUsersLoginName() {
        return userManagementDAO.fetchOperationUsersLoginName();
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public VMGUserDetails fetchVMGUserDetails(User user) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchVMGUserDetails() method - START");
        }
        VMGUserDetails vmgUserDetails = new VMGUserDetails();
        Charity charity = null;
        Long fundraiserId = null;
        Long donorId = null;
        List<Long> eventIds = new ArrayList<Long>();
        Long userId = user.getId();

        vmgUserDetails.setUserId(userId);
        vmgUserDetails.setPersonId(user.getPerson().getId());
        vmgUserDetails.setDayOfBirth(user.getPerson().getDobDay());
        vmgUserDetails.setMonthOfBirth(user.getPerson().getDobMonth());
        vmgUserDetails.setYearOfBirth(user.getPerson().getDobYear());
        vmgUserDetails.setLoginEmailAddress(user.getLoginEmailAddress());
        vmgUserDetails.setUsername(user.getUsername());
        vmgUserDetails.setTitle(user.getPerson().getTitle());
        vmgUserDetails.setForename(user.getPerson().getForename());
        vmgUserDetails.setSurname(user.getPerson().getSurname());

        String userName = user.getUsername();
        Integer dobDay = null;
        Integer dobMonth = null;
        Integer dobYear = null;

        if (StringUtils.isBlank(userName)) {
            userName = user.getLoginEmailAddress();
            dobDay = user.getPerson().getDobDay();
            dobMonth = user.getPerson().getDobMonth();
            dobYear = user.getPerson().getDobYear();
        }

        List<String> userRolesAndPermission =
                fetchUserPermissions(userName, dobDay, dobMonth, dobYear);

        vmgUserDetails.setRoleAndPermissions(userRolesAndPermission);
        if (userRolesAndPermission
                .contains(MasterDataCodeConstants.Roles.ROLE_FUNDRAISER
                        .getCode())) {
            Fundraiser fundraiser =
                    fundraiserDAO.fetchFundraiserByUserId(userId);
            eventIds = fundraiserDAO.fetchEventIdsByFundraiserId(fundraiserId);
            fundraiserId = fundraiser.getId();
            vmgUserDetails.setFundraiserId(fundraiserId);
            vmgUserDetails.setEventIds(eventIds);
            if (fundraiser.getUrlDetails() != null) {
                vmgUserDetails.setFundraiserUrl(fundraiser.getUrlDetails()
                        .getUrl());
            }
        }
        if (userRolesAndPermission
                .contains(MasterDataCodeConstants.Roles.ROLE_DONOR.getCode())) {
            donorId = donorDAO.fetchDonorIdByUserId(userId);
            vmgUserDetails.setDonorId(donorId);
        }
        if (userRolesAndPermission
                .contains(MasterDataCodeConstants.Roles.ROLE_CHARITY_ADMIN
                        .getCode())
                || (userRolesAndPermission
                        .contains(MasterDataCodeConstants.Roles.ROLE_CHARITY_USER
                                .getCode()))) {
            charity = charityDAO.fetchCharityByUserId(userId);
            eventIds = charityDAO.fetchEventIdsByCharityId(charity.getId());
            vmgUserDetails.setCharityId(charity.getId());
            vmgUserDetails.setCharityName(charity.getName());
            vmgUserDetails.setCharityStatus(charity.getCharityStatus()
                    .getCode());
            vmgUserDetails.setEventIds(eventIds);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchVMGUserDetails() method - END");
        }
        return vmgUserDetails;

    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public VMGUserDetails fetchVMGUserDetailsForExternalUser(
            String loginEmailAddress, Integer dayOfBirth, Integer monthOfBirth,
            Integer yearOfBirth) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchVMGUserDetailsForExternalUser() method - START");
        }
        User user =
                userManagementDAO.getUser(loginEmailAddress, dayOfBirth,
                        monthOfBirth, yearOfBirth);
        VMGUserDetails vmgUserDetails = fetchVMGUserDetails(user);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchVMGUserDetailsForExternalUser() method - END");
        }
        return vmgUserDetails;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public VMGUserDetails fetchVMGUserDetailsForInternalUser(String username) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchVMGUserDetailsForInternalUser() method - START");
        }
        User user = userManagementDAO.fetchUserByUserName(username);
        VMGUserDetails vmgUserDetails = fetchVMGUserDetails(user);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchVMGUserDetailsForInternalUser() method - END");
        }
        return vmgUserDetails;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CharityCustomFieldSubset> fetchCharityCustomFields(
            String charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchCharityCustomFields() method - START");
        }
        List<CharityCustomFieldSubset> charityCustomFieldList =
                charityDAO.fetchCharityCustomFields(charityId);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchCharityCustomFields() method - END");
        }
        return charityCustomFieldList;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean saveCharityCustomFields(
            final List<CharityCustomFieldSubset> charityCustomFieldList) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityCustomFields() method - START");
        }
        boolean processStatus =
                charityDAO.saveCharityCustomFields(charityCustomFieldList);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityCustomFields() method - END");
        }
        return processStatus;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List fetchReportingSearchResults(SearchCriteriaSubset searchCriteria) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchReportingSearchResults() method - START");
        }
        List recordList =
                charityDAO.fetchReportingSearchResults(searchCriteria);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchReportingSearchResults() method - END");
        }
        return recordList;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean saveCustomFieldValues(
            CharityCustomFieldValueSubset charityCustomFieldValueSubset,
            String charityId, String entityType, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCustomFieldValues() method - START");
        }
        boolean processStatus =
                charityDAO.saveCustomFieldValues(charityCustomFieldValueSubset,
                        charityId, entityType, attributes);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCustomFieldValues() method - END");
        }
        return processStatus;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public User fetchUserDetailsById(final long userId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchUserDetailsById() method - START");
        }
        User user = userManagementDAO.fetchUserDetailsById(userId);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchUserDetailsById() method - END");
        }
        return user;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getTrusteeCount(Long charityId) {
        return charityDAO.getTrusteeCount(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTrusteeCount(Long charityId, int trusteeCount,
            AuditAttributes attributes) {
        charityDAO.saveTrusteeCount(charityId, trusteeCount, attributes);
    }

    /** {@inheritDoc} */
    public void saveTrusteeStatus(Long charityId, String trusteeStatus,
            AuditAttributes attributes) {
        charityDAO.saveTrusteeStatus(charityId, trusteeStatus, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public String getOfflineCharityTrusteeStatus(Long charityId) {

        return charityDAO.getOfflineCharityTrusteeStatus(charityId);
    }

    /** {@inheritDoc} */
    public List<FundraiserActivityDonation> fetchFundraiserActivityDonations(
            Long fundraiserActivityId, Integer rowNumberLimit) {
        return donorDAO.fetchFundraiserActivityDonations(fundraiserActivityId,
                rowNumberLimit);
    }

    /** {@inheritDoc} */
    public List<Donation> fetchBiggestFundraiserActivityDonation(long activityId) {
        return donorDAO.fetchBiggestFundraiserActivityDonation(activityId);
    }

    /** {@inheritDoc} */

    public long countDonationsForActivity(long activityId) {
        return donorDAO.countDonationsForActivity(activityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
   public long fetchUserIdByUserName(String userName, String userFirst,
            String userSurName) {
        return userManagementDAO.fetchUserIdByUserName(userName, userFirst,
                userSurName);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateFundraiserActivity(FundraiserActivity fundraiserActivity) {
        fundraiserDAO.updateFundraiserActivity(fundraiserActivity);
    }

    /** {@inheritDoc} */
    public List<RegisteredCharityUser> fetchRegisteredUsersOfCharityById(
            Long charityId) {
        return charityDAO.fetchRegisteredUsersOfCharityById(charityId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public Charity putCharityLive(Long charityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::putCharityLive() method - START");
        }

        final Charity charity = charityDAO.fetchCharityDetailsById(charityId);
        charity.setCharityStatus(new CharityStatus());
        charity.getCharityStatus().setCode(
                MasterDataCodeConstants.CharityStatus.CHARITY_STATUS_LIVE
                        .getCode());
      //  DAOHelper.setAuditInformation(attributes, charity);
        charityDAO.saveCharityDetails(charity);

        final List<Page> pages = charityDAO.fetchCharityPages(charityId);
        for (Page page : pages) {
            page.setPublishedDateTime(new Timestamp(Calendar.getInstance()
                    .getTimeInMillis()));
            page.setPageStatus(new PageStatus());
            page.getPageStatus().setCode(
                    MasterDataCodeConstants.PageStatus.PAGE_STATUS_ACTIVE
                            .getCode());
           // DAOHelper.setAuditInformation(attributes, page);
            charityDAO.saveCharityPageDetails(page);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::putCharityLive() method - END");
        }
        return charity;
    }

    /** {@inheritDoc} */
    public void updateInvitedEmailAddressWithFundraiserId(String emailAddress,
            Long fundraiserId, AuditAttributes attributes) {
        fundraiserDAO.updateInvitedEmailAddressWithFundraiserId(emailAddress,
                fundraiserId, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public CharityAdministrator saveCharityAdministrator(CharityAdministrator charityAdmin) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::saveCharityAdministrator() method");
        }
        return userManagementDAO.saveCharityAdministrator(charityAdmin);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean grantPermissionsWithRoles(long userId,
            List<String> permissionsList, List<String> rolesList,
            AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::grantPermissionsWithRoles() method - START");
        }
        final boolean result =
                userManagementDAO.grantPermissionsWithRoles(userId,
                        permissionsList, rolesList, attributes);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::grantPermissionsWithRoles() method - END");
        }
        return result;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateFundraiserActivityWithGroupIdOrFundraiserId(Long fundraiserActivityId,
            Long fundraiserGroupId, Fundraiser fundraiser, AuditAttributes attributes) {
        fundraiserDAO.updateFundraiserActivityWithGroupIdOrFundraiserId(fundraiserActivityId,
                fundraiserGroupId, fundraiser, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getUserAccessRWCount() {
        return userManagementDAO.getUserAccessReadWrightCount();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Location fetchLocationByIsoCode(String isoCode) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::fetchLocationByIsoCode() method");
        }
        return fundraiserDAO.fetchLocationByIsoCode(isoCode);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean dateStampRegularDonation(Long donorId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::dateStampRegularDonation() method");
        }
        return donorDAO.dateStampRegularDonation(donorId);
    }

    /** {@inheritDoc} */
    public void deleteCharityUser(Long charityId, Long userId, AuditAttributes attributes) {
        charityDAO.deleteCharityUser(charityId, userId, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object[]> fetchEmailAddresssForSettlement(Set<Long> charityIds) {
       return charityDAO.fetchEmailAddresssForSettlement(charityIds);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveEventStatus(final Long eventId,
            final String newEventStatus, AuditAttributes attributes) {
        return eventDAO.saveEventStatus(eventId, newEventStatus, attributes);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
	public long countDonationsByDonor(long donorId) {
		return donorDAO.countDonationsByDonor(donorId);
	}

    /**
     * Update the Reguar donation status to newStatus in case 
	 * of unsuccessful regular donation through UCPHB0460.
     */
    @Transactional(propagation = Propagation.REQUIRED)
   	public boolean updateRegularDonationStatusInd(Long donorId, String newStatus) {
		
		return donorDAO.updateRegularDonationStatusInd(donorId, newStatus);
	}
    
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEventAdministrator(CharityAdministrator charityAdministrator, 
            long userId, long charityId, AuditAttributes attributes) {
        eventDAO.saveEventAdministrator(charityAdministrator, userId, charityId, attributes);
    }
    
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEventAdministrator(long userId, long charityId) {
        eventDAO.deleteEventAdministrator(userId, charityId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<String> fetchPageWidgets(Long pageId) {
		return fundraiserDAO.fetchPageWidgets(pageId);
	}
    
	  /** {@inheritDoc} */
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED)
	public Charity fetchCharityAudit(Long charityId) {
		
		return charityDAO.fetchCharityAudit(charityId);
	}

    /** {@inheritDoc} */
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED)
	public BankAccount fetchBankAccountAudit(Long bankAccountId) {
		
		return charityDAO.fetchBankAccountAudit(bankAccountId);
	}

    /** {@inheritDoc} */
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED)
	public Address fetchAddressAudit(Long addressId) {
		return charityDAO.fetchAddressAudit(addressId);
	}
    
    /** {@inheritDoc} */
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED)
    public FundrasierURLDetails fetchFundraiserURLByFundraiserActivityId(Long fundraiserActivityId) {
        return fundraiserDAO.fetchFundraiserURLByFundraiserActivityId(fundraiserActivityId);
    }
    /** {@inheritDoc} */
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED)
    public UserProfile fetchUserProfileByUserId(Long userId) {
        return fundraiserDAO.fetchUserProfileByUserId(userId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserProfile(UserProfile userProfile){
        fundraiserDAO.updateUserProfile(userProfile);
    }


    public List<FundraiserActivity> fetchRandomNetworkActivities(Long networkId, Long numberOfResults, Long fundraiserToExclude) {
        return fundraiserDAO.fetchRandomNetworkActivities(networkId, numberOfResults, fundraiserToExclude);
    }

    /** {@inheritDoc} */
    public List<AlertTrigger> fetchAlertTriggersForCharity(Long charityId) {
        return charityDAO.fetchAlertTriggersForCharity(charityId);
    }

    /** {@inheritDoc} */
    public Integer fetchCountOfDonationForCharityForSpecifiedPeriod(
            Long charityId, Long donorId, Long periodInMonths) {
        return charityDAO.fetchCountOfDonationForCharityForSpecifiedPeriod(
                charityId, donorId, periodInMonths);
    }

    /** {@inheritDoc} */
    public BigDecimal fetchSumOfDonationForCharityForSpecifiedPeriod(
            Long charityId, Long donorId, Long periodInMonths) {
        return charityDAO.fetchSumOfDonationForCharityForSpecifiedPeriod(
                charityId, donorId, periodInMonths);
    }

    /** {@inheritDoc} */
    public Integer fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod(
            Long charityId, Long fundraiserId, Long periodInMonths) {
        return charityDAO
                .fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod(
                        charityId, fundraiserId, periodInMonths);
    }

    /** {@inheritDoc} */
    public BigDecimal fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod(
            Long charityId, Long fundraiserActivityId, Long periodInMonths) {
        return charityDAO
                .fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod(
                        charityId, fundraiserActivityId, periodInMonths);
    }

    /** {@inheritDoc} */
    public BigDecimal fetchFundraiserDonationAmountForSingleEvent(
            Long charityId, Long fundraiserActivityId) {
        return charityDAO.fetchFundraiserDonationAmountForSingleEvent(
                charityId, fundraiserActivityId);
    }

    /** {@inheritDoc} */
    public List<String> fetchEmailAddressesForAlert(Long alertTriggerId) {
        return charityDAO.fetchEmailAddressesForAlert(alertTriggerId);
    }

    /** {@inheritDoc} */
    public String getCharityReference(final Long id, final Long charityId,
            final String userType) {
        return charityDAO.getCharityReference(id, charityId, userType);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateCharityAmendmentInd(Long charityId, String newStatus) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateCharityAmendmentInd() method");
        }
        return charityDAO.updateCharityAmendmentInd(charityId, newStatus);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateAddressAmendmentInd(Long addressId, String newStatus) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateAddressAmendmentInd() method");
        }
        return charityDAO.updateAddressAmendmentInd(addressId, newStatus);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateBankAccountAmendmentInd(Long bankAccountId, String newStatus) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingServiceImpl::updateBankAccountAmendmentInd() method");
        }
        return charityDAO.updateBankAccountAmendmentInd(bankAccountId, newStatus);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCharityBatchResponse(Charity charity) {
        if (charity != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("GivingServiceImpl::updateCharityBatchStatus() ID: " + charity.getId());
            }
            charityDAO.saveCharityDetails(charity);
        }

    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public FundraiserActivity fetchFundraiserActivityDetailsByTeamUrl(String teamUrl) {
        
            return fundraiserDAO.fetchFundraiserActivityByTeamUrl(teamUrl);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCharityBatchStatus(Long id, String status, boolean updateAmendmentInd, Timestamp cutoffDate) {
        Charity charity = charityDAO.fetchCharityDetailsById(id);
        boolean setAmendFlag = true;
        if (charity != null) {
            charity.setBatchStatus(status);
            if (updateAmendmentInd) {
                if (charity.getCharityStatus() == null ||charity.getBatchStatus() == null) {
                    setAmendFlag = false;
                }
                if (!charity.getCharityStatus().getCode().equalsIgnoreCase("LIV")
                        || !charity.getBatchStatus().equalsIgnoreCase("LIV")) {
                    setAmendFlag = false;
                }
                if (setAmendFlag) {
                    charity.setCharityUpdateInd(null);
                    for (CharityAddress address : charity.getCharityAddresses()) {
                        address.getAddress().setAddressUpdateInd(null);
                    }
                    for (BankAccount account : charity.getBankAccounts()) {
                        account.setBankAccountUpdateInd(null);
                    }
                }
            }
            charityDAO.saveCharityDetails(charity);
        }
    }
    
    public void updatePageStatus(Long pageId, String pageStatus) {

         fundraiserDAO.updatePageStatus( pageId, pageStatus);
        
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)  
    public List<CharityOfflineRegistrationLog> 
        fetchLastCharityOfflineStatusList(Long charityOfflineRegistrationId) {
        return charityDAO.fetchLastCharityOfflineStatusList(charityOfflineRegistrationId);
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public CharityOfflineRegistrationLog saveCharityOfflineRegistrationLog(
            final CharityOfflineRegistrationLog charityOfflineRegistrationLog) {
        return charityDAO.saveCharityOfflineRegistrationLog(charityOfflineRegistrationLog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public void updateDonationFailedIndicator(List<Long> donationIds) {
    	fundraiserDAO.updateDonationFailedIndicator(donationIds);
		
	}

    /** {@inheritDoc} */
    public BigDecimal fetchFundraiserActivityTotalDonations(
        Long fundraiserActivityId) {
        return fundraiserDAO.fetchFundraiserActivityTotalDonations(fundraiserActivityId);
    }

    /** {@inheritDoc} */
    public BigDecimal fetchFundraiserActivityTotalDonationsTaxBack(
        Long fundraiserActivityId) {
        return fundraiserDAO.fetchFundraiserActivityTotalDonationsTaxBack(fundraiserActivityId);
    }
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)  
	public boolean fetchDonationNotificationIndFromActivityId(
			Long fundraiserActivityId) {
		// TODO Auto-generated method stub
		return donorDAO.fetchDonationNotificationIndFromActivityId(fundraiserActivityId);
	}
    
    
    /** {@inheritDoc} */
	@Transactional(propagation = Propagation.REQUIRED)
	public void copyReportingCodes(final Page page) {
		charityDAO.copyReportingCodes(page);
	} 	 
	
    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<FundraiserActivity> fetchFundraiserActivityForMemberOfTeam(final Long fundraiserId) {
        return fundraiserDAO.fetchFundraiserActivityForMemberOfTeam(fundraiserId);
    }   
    
     /** {@inheritDoc} */
    public BigDecimal fetchTotalFundraiserDonationForCharityForSpecifiedPeriod(Long charityId, Long fundRaiserId,
                                                                               Long periodInMonths) {
        return charityDAO.fetchTotalFundraiserDonationForCharityForSpecifiedPeriod(
                charityId, fundRaiserId, periodInMonths);
    }

     /** {@inheritDoc} */
    public BigDecimal fetchTotalFundraiserDonationAmountForSingleCharityEvent(Long charityId,
                                                                              Long fundRaiserActivityId) {
        return charityDAO.fetchTotalFundraiserDonationAmountForSingleCharityEvent(
                charityId, fundRaiserActivityId);
    }  
    
    /** {@inheritDoc} */
    public Long fetchDonorIdByUserId(Long userId) {
        return donorDAO.fetchDonorIdByUserId(userId);
    }
    
    
}
