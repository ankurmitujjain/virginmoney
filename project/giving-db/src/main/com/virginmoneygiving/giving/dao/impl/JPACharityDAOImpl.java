package com.virginmoneygiving.giving.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;
import org.springmodules.cache.annotations.Cacheable;

import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;
import com.virginmoneygiving.giving.constants.MasterDataCodeConstants.CharityUserRoles;
import com.virginmoneygiving.giving.dao.CharityDAO;
import com.virginmoneygiving.giving.domain.*;
import com.virginmoneygiving.giving.util.JPACharityReportingHelper;

/**
 * DAO for Charity functionality.
 * 
 * @author Sejal Shah
 * @author Vishakha Sawant - added getCharityStatus method.
 * @author Siva Kumar -added methods fetchAllCountrys , fetchAllCardTypes,
 * checkForProfanity , savePaymentcardDetails
 * @author Puneet Swarup - removed unused methods.
 * @author Edwin Tauro - Find charity by name live
 * @author Diptirmaya Rout - Add saveCharityPageDetails,
 * fetchCharityHomePageDetails method.
 */
@SuppressWarnings("unchecked")
@Repository
public class JPACharityDAOImpl implements CharityDAO {
    
    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(JPACharityDAOImpl.class);
    
    /** An Entity Manager Instance. */
    private EntityManager entityManager;

    /**
     * Constructor to initialize class.
     */
    public JPACharityDAOImpl() {
    	if (LOGGER.isInfoEnabled()) {
    		LOGGER.info("Initializing JPACharityDAOImpl.");
    	}
    }

    /**
     * Dependency injection of JPA EntityManager.
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
    public Charity fetchCharityDetailsById(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityDetailsById() method - START");
        }

        Charity charity = null;
        if(charityId != null){
            charity = entityManager.find(Charity.class, charityId);
            if (charity != null) {
            	if (LOGGER.isDebugEnabled()) {
            		LOGGER.debug("Retrieved Charity details : " + charity.getName());
            	}
            }else{
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Retrieved charity is NULL");
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityDetailsById() method - END");
        }
        return charity;
    }
    
    /** {@inheritDoc} */
    public Charity fetchCharityDetailsByVmgCharityUrl(String vmgCharityUrl) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityDetailsByVmgCharityUrl() method - START");
        }

        Charity charity = 
            (Charity) (entityManager.createNamedQuery("fetchCharityDetailsByVmgCharityUrl")
                .setParameter("vmgCharityUrl", vmgCharityUrl))
                .getSingleResult();
        if (charity != null) {
        	if (LOGGER.isDebugEnabled()) {
        		LOGGER.debug("Received Charity details : " + charity.getName());
        	}
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityDetailsByVmgCharityUrl() method - END");
        }
        return charity;
    }

    /** {@inheritDoc} */
    public List<CharityAdministratorSubset> findCharityAdministratorUsers(
            Long charityId) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityAdministratorUsers() method - START");
        }

        List<Object[]> charityAdministratorList =
                (entityManager
                        .createNamedQuery("findCharityAdministratorUsers")
                        .setParameter("charityId", charityId)).getResultList();
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.debug("charityAdministratorList size "
                + charityAdministratorList.size());
        }
        List<CharityAdministratorSubset> charityAdministratorDetails =
                new ArrayList<CharityAdministratorSubset>();

        for (Object[] objArray : charityAdministratorList) {
            CharityAdministratorSubset charityAdministratorSubset =
                    new CharityAdministratorSubset();
            charityAdministratorSubset.setId((Long) (objArray[0]));
            charityAdministratorSubset.setForename((String) objArray[1]);
            charityAdministratorSubset.setSurname((String) objArray[2]);
            charityAdministratorDetails.add(charityAdministratorSubset);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityAdministratorUsers() method - END");
        }
        return charityAdministratorDetails;
    }

    /** {@inheritDoc} */
    public List<OfflineRegModule> fetchModuleData(String moduleCode) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchModuleData() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("Before fetching module data for module code "
	                + moduleCode);
        }

        List<OfflineRegModule> offlineRegModuleList =
                (entityManager.createNamedQuery("fetchModuleData")
                        .setParameter("moduleCode", moduleCode))
                        .getResultList();
        if (LOGGER.isDebugEnabled()) {
	        LOGGER
	                .debug("After fetching module data for module code "
	                        + moduleCode);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchModuleData() method - END");
        }
        return offlineRegModuleList;
    }

    /** {@inheritDoc} */
    public CharityOfflineRegistration saveCharityOfflineRegistrationData(
            CharityOfflineRegistration charityOfflineRegistration) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityOfflineRegistrationData() method - START");
        }
        if (charityOfflineRegistration.getId() == null) {
            entityManager.persist(charityOfflineRegistration);
        }
        else {
            // TRAC 848:
            charityOfflineRegistration.setTrusteeStatus(
                    checkTrusteeStatusOnOffLineStatusSave(charityOfflineRegistration.getId(),
                                                          charityOfflineRegistration.getTrusteeStatus()));
            charityOfflineRegistration =
                    entityManager.merge(charityOfflineRegistration);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityOfflineRegistrationData() method - END");
        }
        return charityOfflineRegistration;
    }

    /** {@inheritDoc} */
    public List<CharityOfflineRegistration> fetchCharityOfflineRegistrationData(
            Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityOfflineRegistrationData() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
	        LOGGER
	                .debug("Before fetching CharityOfflineRegistration data for charity Id "
	                        + charityId);
        }
        List<CharityOfflineRegistration> charityOfflineRegistrationList =
                (entityManager.createNamedQuery("fetchCharityOfflineRegData")
                        .setParameter("charityId", charityId)).getResultList();
        if (LOGGER.isDebugEnabled()) {
	        LOGGER
	                .debug("After fetching CharityOfflineRegistration data for charity Id "
	                        + charityId);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityOfflineRegistrationData() method - END");
        }
        return charityOfflineRegistrationList;
    }

    /** {@inheritDoc} */
    public List<AlertTrigger> saveAlertTriggers(
            List<AlertTrigger> alertTriggerList) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveAlertTriggers() method - START");
        }
        List<AlertTrigger> savedAlertTriggerList =
                new ArrayList<AlertTrigger>();
        for (Iterator<AlertTrigger> alertIterator = alertTriggerList.iterator(); alertIterator
                .hasNext();) {
            AlertTrigger alertTrigger = alertIterator.next();
            if (alertTrigger.getId() != null && alertTrigger.getId() > 0) {
            	/* If the trigger has no users remove it as it causes problems
            	 * when re-creating the AlertTrigger in the future with the HQL used 
            	 * to fetch triggers. 
            	 */
            	if (alertTrigger.getAlertUsers().size() == 0) {
            	    entityManager.remove(alertTrigger);
            	} else {
            	    entityManager.merge(alertTrigger);
            	}
            }
            else {
                entityManager.persist(alertTrigger);
            }
            savedAlertTriggerList.add(alertTrigger);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveAlertTriggers() method - END");
        }
        return savedAlertTriggerList;
    }

    /** {@inheritDoc} */
    public List<AlertTrigger> loadAlertTriggers(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::loadAlertTriggers() method - START");
        }
        List<AlertTrigger> savedAlertTriggerList =
                new ArrayList<AlertTrigger>();

        savedAlertTriggerList =
                (entityManager.createNamedQuery("loadCharityAlertTrigger")
                        .setParameter("charityId", charityId)).getResultList();
        if (LOGGER.isTraceEnabled()) {
            for (AlertTrigger at : savedAlertTriggerList) {
                LOGGER.trace(at.getAlertType().getAlertName() + " with ID <" + at.getId() + ">");
            }
            LOGGER.trace("JPACharityDAOImpl::loadAlertTriggers() method - END");
        }
        return savedAlertTriggerList;
    }

    /** {@inheritDoc} */
    public List<Charity> fetchCharityDetailsByIds(String charityIds) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityDetailsByIds() method - START");
        }
        // Not supported by jpa:
        // "FROM Charity c where c.Id in (:charityIds)"-Always returns a single
        // row.
        String[] charityId = charityIds.split(",");
        List<Charity> charities = new ArrayList<Charity>();

        for (String charityIdList : charityId) {
            Charity charity =
                    entityManager.find(Charity.class, Long
                            .valueOf(charityIdList));

            charities.add(charity);
        }

        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("No of Charity recieved in resultset : "
	                + charities.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityDetailsByIds() method - END");
        }
        return charities;
    }

    /** {@inheritDoc} */
    public List<CharitySubset> findCharityByName(String charitySearchString) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByName() method - START");
        }

        List<Object[]> charityList =
                (entityManager.createNamedQuery("findCharityByCharityName")
                        .setParameter("charitySearchString", "%"
                                + charitySearchString + "%")).getResultList();
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("CharityList size " + charityList.size());
        }
        List<CharitySubset> charityDetailsList = new ArrayList<CharitySubset>();

        for (Object[] objArray : charityList) {
            CharitySubset charitySubset = new CharitySubset();
            charitySubset.setId((Long) (objArray[0]));
            charitySubset.setName((String) objArray[1]);
            charitySubset.setRegisteredCharityNumber((String) objArray[2]);
            charityDetailsList.add(charitySubset);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByName() method - END");
        }

        return charityDetailsList;
    }

    /** {@inheritDoc} */
    public List<CommissionCharityDetails> findCharityByNameFromCommissionData(
            String charitySearchString, int startRow, int chunkSize) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByNameFromCommissionData() method - START");
        }
       
        
        List<CommissionCharityDetails> charityDetailsList =
                (createQueryForCommissionCharitySearch(charitySearchString)).setFirstResult(startRow)
                        .setMaxResults(chunkSize)
                        .getResultList();

        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("CharityList size from commission data "
	                + charityDetailsList.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByNameFromCommissionData() method - END");
        }

        return charityDetailsList;
    }
    
    /** {@inheritDoc} */
    public int findTotalCountCharityByNameFromCommissionData(
            String charitySearchString) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findTotalCountCharityByNameFromCommissionData() method - START");
        }
        int count = 0;
        
        List<CommissionCharityDetails> charityDetailsList =
            (createQueryForCommissionCharitySearch(charitySearchString))
                    .getResultList();
        
        if (charityDetailsList != null && charityDetailsList.size() > 0) {
            count = charityDetailsList.size();
        }
        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug(" total count of CharityList size from commission data "
	                + count);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findTotalCountCharityByNameFromCommissionData() method - END");
        }
        return count;
    }    

    /**
     * Generates the query to get result in specific ordered format.
     * The search result will be in this order:
     * <ol>
     * <li>String only</li>
     * <li>Starts with string</li>
     * <li>Contains string</li>
     * <li>Contains any part of string (multi word search string)</li>
     * </ol>
     * 
     * @param charitySearchString search String.
     * @return object of {@link Query}.
     */
    private Query createQueryForCommissionCharitySearch(String charitySearchString){
    	
    	 charitySearchString = charitySearchString.trim();
         String searchText = "\""+charitySearchString+"\"";
         String searchTextForLike = charitySearchString + "%";
         String searchTextForSingleWord = charitySearchString.replace(" ", "* ") + "* -" + "\""+charitySearchString+ "\"";
         
         if (!charitySearchString.contains(" ")) {
         	searchTextForLike = charitySearchString + " %";
         }
         
         Query query = entityManager.createNamedQuery(
         "findCharityByNameFromCommissionData")
         .setParameter("searchTextForLike", searchTextForLike)
         .setParameter("searchText", searchText)
         .setParameter("searchTextForSingleWord", searchTextForSingleWord);
         
         return query;
    }
    
    /** {@inheritDoc} */
    public List<CharitySubset> findCharitiesLikeNameForStatus(
            String charitySearchString, String statusCode) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharitiesLikeNameForStatus() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
	        LOGGER
	                .trace("Before fetching charity details by charity name for status code "
	                        + statusCode);
        }

        List<Object[]> charityList =
                (entityManager.createNamedQuery(
                        "findCharitiesLikeCharityNameForStatus").setParameter(
                        "charitySearchString", "%" + charitySearchString + "%")
                        .setParameter("status", statusCode)).getResultList();

        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("CharityList size " + charityList.size());
        }
        List<CharitySubset> charityDetails = new ArrayList<CharitySubset>();

        for (Object[] objArray : charityList) {
            CharitySubset charitySubset = new CharitySubset();
            charitySubset.setId((Long) (objArray[0]));
            charitySubset.setName((String) objArray[1]);
            charitySubset.setRegisteredCharityNumber((String) objArray[2]);
            charityDetails.add(charitySubset);
        }

        if (LOGGER.isDebugEnabled()) {
	        LOGGER
	                .trace("After fetching charity details by charity name for status code "
	                        + statusCode);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharitiesLikeNameForStatus() method - END");
        }
        return charityDetails;
    }

    /** {@inheritDoc} */
    public List<Charity> findCharityByRegistrationNumber(String charityRegistrationNumber) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByRegistrationNumber() method - START");
        }

        List<Charity> charityList =
                (entityManager
                        .createNamedQuery("findCharityByRegistrationNumber")
                        .setParameter("charityRegistrationNumber", charityRegistrationNumber))
                        .getResultList();

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("CharityList size " + charityList.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByRegistrationNumber() method - END");
        }
        return charityList;
    }

    /** {@inheritDoc} */
    public long saveCharityDetails(Charity charityDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityDetails() method - START");
        }

        entityManager.persist(charityDetails);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityDetails() method - END");
        }
        return charityDetails.getId();
    }

    /** {@inheritDoc} */
    public Charity updateCharityDetails(Charity charityDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateCharityDetails() method");
        }
        if(charityDetails != null){
            charityDetails = entityManager.merge(charityDetails);
        }
        return charityDetails;
    }

    /** {@inheritDoc} */
    public List<RegistrationForm> fetchAllRequiredForms() {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllRequiredForms() method - START");
        }

        final Query fetchAllRequiredForms =
                entityManager.createQuery("FROM RegistrationForm");
        final List<RegistrationForm> registrationForms =
                fetchAllRequiredForms.getResultList();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllRequiredForms() method - END");
        }

        return registrationForms;
    }

    /** {@inheritDoc} */
    public List<CharityCategory> fetchAllCharityCategories() {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllCharityCategories() method - START");
        }

        final Query fetchAllCharityCategories =
                entityManager.createNamedQuery("fetchAllCharityCategories");
        final List<CharityCategory> charityCategories =
                fetchAllCharityCategories.getResultList();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllCharityCategories() method - END");
        }

        return charityCategories;
    }

    /** {@inheritDoc} */
    public boolean isCharityRegistered(String charityRegistrationNumber) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::isCharityRegistered() method - START");
        }

        boolean isCharityRegistered = false;

        try {

            Long count =
                    (Long) (entityManager
                            .createNamedQuery("isCharityRegistered")
                            .setParameter("charityRegistrationNumber", charityRegistrationNumber))
                            .getSingleResult();

            isCharityRegistered = true;
        }
        catch (NoResultException noResultException) {
            LOGGER.error("Error Occured Inside JPACharityDAOImpl::isCharityRegistered() method");
            LOGGER.error("INPUT : charityRegNumber =" + charityRegistrationNumber);
            LOGGER.error("No results found");
            // Silently ignore the exception.
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::isCharityRegistered() method - END");
        }

        return isCharityRegistered;
    }

    /** {@inheritDoc} */
    public boolean isAccountExist(String emailAddress, Integer dobDay,
            Integer dobMonth, Integer dobYear) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::isAccountExist() method - START");
        }

        boolean isAccountExist = false;
        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("Account information to be searched " + emailAddress + " "
	                + dobDay + " " + dobMonth + " " + dobYear);
        }
        Query isAccountExistQuery =
                entityManager.createNamedQuery("isAccountExist");
        isAccountExistQuery.setParameter("emailAddress", emailAddress);
        isAccountExistQuery.setParameter("dobDay", dobDay);
        isAccountExistQuery.setParameter("dobMonth", dobMonth);
        isAccountExistQuery.setParameter("dobYear", dobYear);

        try {

            Long userId = (Long) isAccountExistQuery.getSingleResult();
            isAccountExist = true;

        }
        catch (NoResultException noResultException) {
            LOGGER.error("Error Occured Inside JPACharityDAOImpl::isAccountExist() method");
            LOGGER.error("INPUT : emailAddress =" + emailAddress);
            LOGGER.debug("Account not exist");
            // Silently ignore the exception.
        }

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("isAccountExist " + isAccountExist);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::isAccountExist() method - END");
        }

        return isAccountExist;
    }

    /** {@inheritDoc} */
    public List<Country> fetchAllCountries() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllCountries() method - START");
        }

        final Query fetchAllCountrys =
                entityManager.createQuery("FROM Country order by name");
        final List<Country> countrys = fetchAllCountrys.getResultList();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllCountries() method - END");
        }
        return countrys;
    }

    /** {@inheritDoc} */
    public List<CardProvider> fetchAllCardTypes() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllCardTypes() method - START");
        }

        final Query fetchAllCardTypes =
                entityManager.createQuery("FROM CardProvider ");
        final List<CardProvider> cardTypes = fetchAllCardTypes.getResultList();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAllCardTypes() method - END");
        }
        return cardTypes;
    }

    /** {@inheritDoc} */
    public String getCharityStatus(Long charityId) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::getCharityStatus() method - START");
        }
        if (LOGGER.isTraceEnabled()) {
        	LOGGER.debug("charityId " + charityId);
        }

        String charityStatus = null;
        charityStatus =
                (String) (entityManager.createNamedQuery("getCharityStatus")
                        .setParameter("charityId", charityId))
                        .getSingleResult();

        if (LOGGER.isTraceEnabled()) {
        	LOGGER.debug("charityStatus " + charityStatus);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::getCharityStatus() method - END");
        }

        return charityStatus;
    }

    /** {@inheritDoc} */
    public Person fetchPersonById(Long personId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchPersonById() method - START");
        }

        Person person = entityManager.find(Person.class, personId);
        if (person != null) {
            LOGGER.debug("Received person details : " + person.getForename());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchPersonById() method - END");
        }
        return person;
    }

    /** {@inheritDoc} */
    public long savePaymentcardDetails(PaymentCard paymentCardDetails) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::savePaymentcardDetails() method - START");
        }

        if (paymentCardDetails.getId() == null) {
            entityManager.persist(paymentCardDetails);
        }
        else {
            PaymentCard merged = entityManager.merge(paymentCardDetails);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::savePaymentcardDetails() method - END");
        }
        return paymentCardDetails.getId();
    }

    /** {@inheritDoc} */
    public List<Charity> fetchCharitiesByCategoryId(Long charityCategoryId) {

        List<Charity> charityList = null;
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharitiesByCategoryId() method - START");
        }
        charityList =
                (List<Charity>) (entityManager
                        .createNamedQuery("fetchCharitiesByCategoryId")
                        .setParameter("charityCategoryId", charityCategoryId))
                        .getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharitiesByCategoryId() method - END");
        }
        return charityList;
    }

    /** {@inheritDoc} */
    public boolean isVmgCharityURLExist(final String vmgCharityURL) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::isVmgCharityURLExist() method - START");
        }
        boolean vmgCharityURLExist = false;
        List<String> vmgCharityURLs =
                (List<String>) entityManager.createNamedQuery(
                        "fetchAllVmgCharityURLs").getResultList();

        for (final String url : vmgCharityURLs) {

            if (vmgCharityURL.equalsIgnoreCase(url)) {
                vmgCharityURLExist = true;
                break;
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::isVmgCharityURLExist() method - END");
        }
        return vmgCharityURLExist;

    }

    // TODO Uncomment following after fix of whizible defect id 10026.
    /**
     * {@inheritDoc} public BankAccount fetchBankAccountById(Long bankAccountId)
     * { LOGGER.debug("fetchBankAccountById - Start"); return
     * entityManager.find(BankAccount.class, bankAccountId); }
     */

    /** {@inheritDoc} */
    public List<BankAccount> fetchCharityBankDetails(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityBankDetails() method - START");
        }
        List<BankAccount> bankDetailsList =
                (entityManager.createNamedQuery("fetchBankAccountForCharity")
                        .setParameter("charityId", charityId)).getResultList();

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("bankDetailsList size " + bankDetailsList.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityBankDetails() method - END");
        }

        return bankDetailsList;
    }

    /** {@inheritDoc} */
    public List<RegularDonation> findRegularDonationsByDonorId(Long donorId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findRegularDonationsByDonorId() method - START");
        }

        List<RegularDonation> regularDonations = (entityManager.createNamedQuery("findRegularDonationsByDonorId")
                                                            .setParameter("donorId", donorId)).getResultList();

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("Donations size " + regularDonations.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findRegularDonationsByDonorId() method - END");
        }
        return regularDonations;
    }

    /** {@inheritDoc} */
    public Boolean updateCharityBankDetailsStatus(Long bankAccountId,
            Long charityId, String defaultAccountIndicator, String userName,
            AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateCharityBankDetailsStatus() method - START");
        }
        List<BankAccount> bankDetailsList = fetchCharityBankDetails(charityId);

        for (BankAccount bankAccountDetails : bankDetailsList) {
            if (bankAccountDetails.getId().equals(bankAccountId)) {
                bankAccountDetails
                        .setDefaultAccountIndicator(defaultAccountIndicator);
                BankAccountStatus bankAccountStatus = bankAccountDetails.getAccountStatus();
                if(bankAccountStatus !=null && bankAccountStatus.getCode() != null && bankAccountStatus.getCode().equals("DLRQ")){
                    BankAccountStatus activeBankAccountStatus = new BankAccountStatus();
                    activeBankAccountStatus.setCode("ACT");
                    bankAccountDetails.setAccountStatus(activeBankAccountStatus);
                }
                
            }
            else {
                bankAccountDetails
                        .setDefaultAccountIndicator(MasterDataCodeConstants.NO);
            }
            entityManager.merge(bankAccountDetails);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateCharityBankDetailsStatus() method - END");
        }
        return true;
    }

    /** {@inheritDoc} */
    public Boolean removeCharityBankAccount(Long charityId, Long bankAccountId,
            String bankAccountStatus, AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::removeCharityBankAccount() method - START");
        }
        List<BankAccount> bankDetailsList =
                (entityManager.createNamedQuery("fetchBankAccountForCharity")
                        .setParameter("charityId", charityId)).getResultList();
        BankAccountStatus accountStatus = new BankAccountStatus();
        boolean updationSuccessful = false;
        for (BankAccount bankAccountDetails : bankDetailsList) {
            if (bankAccountDetails.getId().equals(bankAccountId)) {
                accountStatus.setCode(bankAccountStatus);
                bankAccountDetails.setAccountStatus(accountStatus);
                updationSuccessful = true;
            }
            entityManager.merge(bankAccountDetails);

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::removeCharityBankAccount() method - END");
        }
        return updationSuccessful;
    }

    /** {@inheritDoc} */
    public List<CharityVO> findCharityByCriteria(String charityName,
            Long vmgRefNo, String postCode, String charityNumber) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::findCharityByCriteria() method - START");
        }
        
        charityName = charityName.replace("'", "''");
        String queryString =
                "Select distinct c From Charity c inner join c.charityAddresses ca ";
        boolean wherePartIncluded = false;
        if (StringUtils.isNotBlank(charityName)) {
            queryString = queryString + " Where c.name like :charityName ";
            wherePartIncluded = true;
        }
        if (StringUtils.isNotBlank(charityNumber)) {
            if (wherePartIncluded) {
                queryString =
                        queryString + " And c.registeredCharityNumber like :charityNumber ";
            }
            else {
                queryString =
                        queryString + " Where c.registeredCharityNumber like :charityNumber ";
                wherePartIncluded = true;
            }
        }
        if (StringUtils.isNotBlank(postCode)) {            
            if (wherePartIncluded) {
                queryString =
                        queryString + " And ca.address.postCode like :postCode ";
            }
            else {
                queryString =
                        queryString + " Where ca.address.postCode like :postCode ";
                wherePartIncluded = true;
            }
        }
        if (vmgRefNo != null) {
            if (wherePartIncluded) {
                queryString = queryString + " And c.id = :vmgRefNo ";
            }
            else {
                queryString = queryString + " Where c.id = :vmgRefNo ";
            }
        }

        final Query fetchCharitiesQuery = entityManager.createQuery(queryString);
        if (StringUtils.isNotBlank(charityName)) {
            String charityLikeName = ("%").concat(charityName).concat("%");
            fetchCharitiesQuery.setParameter("charityName", charityLikeName);
        }
        if (StringUtils.isNotBlank(charityNumber)) {
            String charityLikeNumber = ("%").concat(charityNumber).concat("%");
            fetchCharitiesQuery.setParameter("charityNumber", charityLikeNumber);
        }
        if (StringUtils.isNotBlank(postCode)) {   
            StringBuffer searchPostcode = new StringBuffer();
            String percentage = "%";
            for (int i = 0; i < postCode.length(); i++) {
                searchPostcode.append(postCode.charAt(i));
                searchPostcode.append(percentage);
            }
            postCode = searchPostcode.toString();
            fetchCharitiesQuery.setParameter("postCode", postCode);
        }
        if (vmgRefNo != null) {
            fetchCharitiesQuery.setParameter("vmgRefNo", vmgRefNo);
        }
        
        final List<Charity> charities =
                (List<Charity>) fetchCharitiesQuery.getResultList();

        List<CharityVO> charityList = new ArrayList<CharityVO>();

        for (Charity charity : charities) {
            CharityVO charityVO = new CharityVO();
            charityVO.setCharityId(charity.getId());
            charityVO.setCharityName(charity.getName());
            charityVO.setVmgReference(charity.getId());
            charityVO.setCharityNumber(charity.getRegisteredCharityNumber());
            if (charity.getCharityAddresses().size() > 0) {
                charityVO.setPostCode(charity.getCharityAddresses().iterator()
                        .next().getAddress().getPostCode());
            }
            if (charity.getCharityStatus() != null) {
                charityVO
                        .setStatus(charity.getCharityStatus().getDescription());
            }
            charityList.add(charityVO);
        }
        LOGGER.trace("FetchCharities - End");
        return charityList;
    }

    /** {@inheritDoc} */
    public long saveCharityPageDetails(Page pageDetails) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityPageDetails() method - START");
        }
        pageDetails = entityManager.merge(pageDetails);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityPageDetails() method - END");
        }
        return pageDetails.getId();
    }

    /** {@inheritDoc} */
    public List<Page> fetchCharityHomePageDetails(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityPageDetails() method - START");
        }

        List<Page> charityHomePageDetails =
                (entityManager.createNamedQuery("fetchCharityHomePageDetails")
                        .setParameter("charityId", charityId)).getResultList();

        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("PageDetailsList size " + charityHomePageDetails.size());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityPageDetails() method - END");
        }
        return charityHomePageDetails;
    }

    /** {@inheritDoc} */
    public boolean saveCharityBankDetails(BankAccount bankAccount) {
        entityManager.merge(bankAccount);
        return true;
    }

    /** {@inheritDoc} */
    public Bank saveCharityBank(Bank bank) {
        return entityManager.merge(bank);
    }

    /** {@inheritDoc} */
    public Address saveAddress(Address address) {
        return entityManager.merge(address);
    }

    /** {@inheritDoc} */
    public BankAddress saveBankAddress(BankAddress bankAddress) {
        return entityManager.merge(bankAddress);
    }

    /** {@inheritDoc} */
    public boolean updateCharityBankAccountStatus(Long bankAccountId,
            Long charityId, String bankAccountStatus, String userName,
            AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateCharityBankAccountStatus() method - START");
        }
        List<BankAccount> bankDetailsList =
                (entityManager.createNamedQuery("fetchBankAccountForCharity")
                        .setParameter("charityId", charityId)).getResultList();
        BankAccountStatus accountStatus = new BankAccountStatus();
        boolean updationSuccessful = false;
        for (BankAccount bankAccountDetails : bankDetailsList) {
            if (bankAccountDetails.getId().equals(bankAccountId)) {
                accountStatus.setCode(bankAccountStatus);
                bankAccountDetails.setAccountStatus(accountStatus);
                updationSuccessful = true;
            }
            entityManager.merge(bankAccountDetails);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateCharityBankAccountStatus() method - END");
        }
        return updationSuccessful;
    }

    /** {@inheritDoc} */
    public List<CharityHMRCDetailsDomain> fetchHMRCreferenceNumber(
            List<CharityHMRCDetailsDomain> charityHMRCDetailsRequestList,
            String type) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchHMRCreferenceNumber() method - START");
        }
        List<CharityHMRCDetailsDomain> charityHMRCDetailsDomains =
                new ArrayList<CharityHMRCDetailsDomain>();
        if (charityHMRCDetailsRequestList != null
                && !charityHMRCDetailsRequestList.isEmpty()) {
            for (CharityHMRCDetailsDomain detailsDomain : charityHMRCDetailsRequestList) {
                LOGGER.trace("FetchCharities - End");
                List<Object[]> charityHMRCRefernceList = null;
                List<Object[]> charityIdList = null;

                Long charityId = detailsDomain.getId();
                String hmrcRef = detailsDomain.getHmrcRefNo();
                if (charityId != null && type.equalsIgnoreCase("ID")) {
                    charityHMRCRefernceList =
                            (entityManager
                                    .createNamedQuery("fetchCharityHmrcReferenceById")
                                    .setParameter("charityId", charityId))
                                    .getResultList();
                    CharityHMRCDetailsDomain idObj =
                            new CharityHMRCDetailsDomain();
                    if (charityHMRCRefernceList != null
                            && !charityHMRCRefernceList.isEmpty()) {
                        for (Object[] objArray : charityHMRCRefernceList) {

                            idObj.setId((Long) objArray[0]);
                            idObj.setHmrcRefNo((String) objArray[1]);

                        }
                    }
                    charityHMRCDetailsDomains.add(idObj);
                }
                else if (StringUtils.isNotBlank(hmrcRef)
                        && type.equalsIgnoreCase("HMRC")) {
                    charityIdList =
                            (entityManager
                                    .createNamedQuery("fetchCharityIdByHmrcReference")
                                    .setParameter("hmrcRef", hmrcRef))
                                    .getResultList();
                    CharityHMRCDetailsDomain hmrcRefObj =
                            new CharityHMRCDetailsDomain();
                    if (charityIdList != null && !charityIdList.isEmpty()) {
                        for (Object[] objArray : charityIdList) {

                            hmrcRefObj.setId((Long) objArray[0]);
                            hmrcRefObj.setHmrcRefNo((String) objArray[1]);

                        }
                    }
                    charityHMRCDetailsDomains.add(hmrcRefObj);
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchHMRCreferenceNumber() method - END");
        }
        return charityHMRCDetailsDomains;
    }

    /** {@inheritDoc} */
    public List<BankAccountType> fetchBankAccountType() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchBankAccountType() method - START");
        }

        List<BankAccountType> bankAccountTypeList =
                (entityManager.createNamedQuery("fetchCharityBankAccountType")
                        .getResultList());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchBankAccountType() method - END");
        }
        return bankAccountTypeList;
    }

    /** {@inheritDoc} */
    public boolean updateBankDescriptionAndType(Long bankAccountId,
            String accountDescription, String accountType, String userName,
            AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateBankDescriptionAndType() method - START");
        }

        List<BankAccount> bankDetailsList =
                (entityManager.createNamedQuery("fetchBankAccountForBank")
                        .setParameter("bankAccountId", bankAccountId))
                        .getResultList();

        boolean updationSuccessful = false;

        for (BankAccount bankAccountDetails : bankDetailsList) {
            if (bankAccountDetails.getId().equals(bankAccountId)) {
                BankAccountType bankAccountType = new BankAccountType();
                bankAccountType.setCode(accountType);
                bankAccountDetails.setBankAccountType(bankAccountType);
                if (accountType != null
                        && accountType
                                .equals(MasterDataCodeConstants.BankAccountType.BANK_ACCOUNT_TYPE_TRADING
                                        .getCode())) {
                    bankAccountDetails
                            .setDefaultAccountIndicator(MasterDataCodeConstants.NO);
                }
                bankAccountDetails.setAccountDescription(accountDescription);
                updationSuccessful = true;
            }
            entityManager.merge(bankAccountDetails);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::updateBankDescriptionAndType() method - END");
        }
        return updationSuccessful;
    }

    /** {@inheritDoc} */
    public Long saveProfanityEmails(ProfanityReportModel profanityReportModel) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveProfanityEmails() method - START");
        }
        ProfanityReport profanityReport = new ProfanityReport();
        profanityReport.setEmailDateTime(new Timestamp(profanityReportModel
                .getEmailDateTime().getTime()));
        profanityReport.setEmailContent(profanityReportModel.getEmailContent());
        profanityReport.setCategory(profanityReportModel.getCategory());

        entityManager.merge(profanityReport);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveProfanityEmails() method - END");
        }
        return profanityReport.getId();
    }

    /** {@inheritDoc} */
    public List<TrusteeDetails> fetchCharityTrusteeDetails(Long charityId) {
            
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityTrusteeDetails() method - START");
        }

        List<TrusteeDetails> trusteeDetails =
                (entityManager.createNamedQuery("fetchCharityTrusteeDetails")
                        .setParameter("charityId", charityId)).getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityTrusteeDetails() method - END");
        }

        return trusteeDetails;
    }

    /** {@inheritDoc} */
    public void deleteCharityTrusteeDetails(List<String> trusteeTypes,
            Long charityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::deleteCharityTrusteeDetails() method - START");
        }

        for (final String trusteeType : trusteeTypes) {
            Query query =
                    entityManager.createNamedQuery(
                            "deleteCharityTrusteeDetails").setParameter(
                            "trusteeTypeCode", trusteeType).setParameter(
                            "charityId", charityId);

            query.executeUpdate();

        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::deleteCharityTrusteeDetails() method - END");
        }

    }

    /** {@inheritDoc} */
    public TrusteeDetails saveTrusteeDetails(TrusteeDetails trusteeDetails,
            Long charityId, AuditAttributes attributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveTrusteeDetails() method - START");
        }

        Charity charity = fetchCharityDetailsById(charityId);
        trusteeDetails.setCharityDetails(charity);
        Set<PersonalAddress> addresses =
                trusteeDetails.getPersonDetails().getPersonalAddresses();
        AddressType addressType = new AddressType();
        addressType.setCode(MasterDataCodeConstants.ADDRESS_TYPE_REGISTERED);

        for (final PersonalAddress address : addresses) {
            address.getAddress().setAddressType(addressType);
        }
        TrusteeDetails persistTrusteeDetails =
                entityManager.merge(trusteeDetails);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveTrusteeDetails() method - END");
        }

        return persistTrusteeDetails;
    }

    /** {@inheritDoc} */
    public List<PageWidgetType> fetchPageWidgetType() {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchPageWidgetType() method - START");
        }
        final Query fetchWidgetTypes =
                entityManager.createQuery("FROM PageWidgetType");
        final List<PageWidgetType> widgetTypes =
                fetchWidgetTypes.getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchPageWidgetType() method - END");
        }
        return widgetTypes;
    }

    /** {@inheritDoc} */
    public List<PageWidgetType> fetchPageWidgetTypeFromPageType(String pageType) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchPageWidgetTypeFromPageType() method - START");
        }
        String pageTypeCode = "";
        if("CHARITY_PAGE_TYPE".equalsIgnoreCase(pageType)){
            pageTypeCode = "CHA";
        }
        final List<PageWidgetType> widgetTypes =
                entityManager.createNamedQuery("fetchPageWidgetTypeFromPageType")
                              .setParameter("pageTypeCode", pageTypeCode).getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchPageWidgetTypeFromPageType() method - END");
        }
        return widgetTypes;
    }

    /** {@inheritDoc} */
    public Page fetchCharityHomePageById(Long id) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityHomePageById() method - START");
        }
        Page page = entityManager.find(Page.class, id);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityHomePageById() method - END");
        }
        return page;
    }

    /** {@inheritDoc} */
    public List<PageWidget> fetchListofPageWidgetsById(Long pageId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchListofPageWidgetsById() method - START");
        }
        List<PageWidget> pageWidget =
                (entityManager.createNamedQuery("fetchListofPageWidgetsById")
                        .setParameter("pageId", pageId)).getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchListofPageWidgetsById() method - END");
        }
        return pageWidget;
    }

    /** {@inheritDoc} */
    public String checkEventAttachedWithBank(Long bankAccountId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::checkEventAttachedWithBank() method - START");
        }
        String eventStatus = null;
        List<String> eventStatusList =
                entityManager.createNamedQuery("checkEventAttachedWithBank")
                        .setParameter("bankAccountId", bankAccountId)
                        .getResultList();
        if (!eventStatusList.isEmpty()) {
            eventStatus = eventStatusList.get(0);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::checkEventAttachedWithBank() method - END");
        }
        return eventStatus;

    }

    /** {@inheritDoc} */
    public Charity fetchCharityByUserId(Long userId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityByUserId() method - START");
        }
        Charity charity =
                (Charity) entityManager
                        .createNamedQuery("fetchCharityByUserId").setParameter(
                                "userId", userId).getSingleResult();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityByUserId() method - END");
        }
        return charity;
    }

    /** {@inheritDoc} */
    public List<Long> fetchEventIdsByCharityId(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchEventIdsByCharityId() method - START");
        }
        List<Long> eventIds =
                (List<Long>) entityManager.createNamedQuery(
                        "fetchEventIdsByCharityId").setParameter("charityId",
                        charityId).getResultList();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchEventIdsByCharityId() method - END");
        }
        return eventIds;
    }

    /** {@inheritDoc} */
    public List<CharityCustomFieldSubset> fetchCharityCustomFields(
            String charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityCustomFields() method - START");
        }
        JPACharityReportingHelper jpaCharityReportingHelper =
                new JPACharityReportingHelper();
        jpaCharityReportingHelper.setEntityManager(entityManager);
        List<CharityCustomFieldSubset> charityCustomFieldList =
                jpaCharityReportingHelper.fetchCharityCustomFieldLabels(Long
                        .valueOf(charityId));
        
        if(charityCustomFieldList!=null){
            JPACharityReportingHelper.consolidateCustomFieldList(charityCustomFieldList);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityCustomFields() method - END");
        }
        return charityCustomFieldList;
    }
    
    /** {@inheritDoc} */
    public boolean saveCharityCustomFields(
            List<CharityCustomFieldSubset> charityCustomFieldList) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityCustomFields() method - START");
        }
        boolean processStatus = false;

                if (charityCustomFieldList != null) {
                    CharityCustomFieldSubset charityCustomFieldFirstRecord =
                            (CharityCustomFieldSubset) charityCustomFieldList.get(0);
                    Long charityId = charityCustomFieldFirstRecord.getCharityId();
                    List<CharityCustomFieldSubset> charityCustomFieldListForupdate =
                            (entityManager.createNamedQuery("fetchCharityCustomFields")
                                    .setParameter("charityId", charityId))
                                    .getResultList();
                    String fieldLabels[]=new String[charityCustomFieldListForupdate.size()];
                    String str[];
                    int count=0;
                    for (CharityCustomFieldSubset charityCustomField : charityCustomFieldList) {
                        if (charityCustomField.getCustomFieldTypeCode().equals(MasterDataCodeConstants.CustomFieldTypes_DONOR)) {
                            str=charityCustomField.getFieldLabel().split("##");
                            for(String tmpStr:str){
                                fieldLabels[count++]=tmpStr;
                            }
                        }else if (charityCustomField.getCustomFieldTypeCode().equals(MasterDataCodeConstants.CustomFieldTypes_EVENT)) {
                            str=charityCustomField.getFieldLabel().split("##");
                            for(String tmpStr:str){
                                fieldLabels[count++]=tmpStr;
                            }
                        }else if (charityCustomField.getCustomFieldTypeCode().equals(MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER)) {
                            str=charityCustomField.getFieldLabel().split("##");
                            for(String tmpStr:str){
                                fieldLabels[count++]=tmpStr;
                            }
                        }else if (charityCustomField.getCustomFieldTypeCode().equals(MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY)) {
                            str=charityCustomField.getFieldLabel().split("##");
                            for(String tmpStr:str){
                                fieldLabels[count++]=tmpStr;
                            }
                        }
                    }
                    count=0;
                    for (CharityCustomFieldSubset charityCustomFieldSubset : charityCustomFieldListForupdate) {
                        charityCustomFieldSubset.setFieldLabel(fieldLabels[count++]);
                        entityManager.merge(charityCustomFieldSubset);
                    }
                    processStatus = true;
                }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCharityCustomFields() method - END");
        }
        return processStatus;
    }

    /** {@inheritDoc} */
    public List fetchReportingSearchResults(SearchCriteriaSubset searchCriteria) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchReportingSearchResults() method - START");
        }
        List recordList = new ArrayList();

        if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug(" Input Search Criteria:");
	        LOGGER.debug("Charity Id:" + searchCriteria.getCharityId());
	        LOGGER.debug("Entity Type:" + searchCriteria.getEntityType());
	        LOGGER.debug("Month:" + searchCriteria.getMonth());
	        LOGGER.debug("Year:" + searchCriteria.getYear());
	        LOGGER.debug("Request Type:" + searchCriteria.getRequestType());
	        LOGGER.debug("SelectType:" + searchCriteria.getSelectType());
	        LOGGER.debug("searchCriteria entityType:"
	                + searchCriteria.getEntityType());
        }
        JPACharityReportingHelper jpaCharityReportingHelper =
                new JPACharityReportingHelper();
        jpaCharityReportingHelper.setEntityManager(entityManager);
        String entityType = searchCriteria.getEntityType();
        jpaCharityReportingHelper.setEntityManager(entityManager);
        if (MasterDataCodeConstants.CustomFieldTypes_DONOR.equals(entityType)) {
            recordList = jpaCharityReportingHelper.getDonors(searchCriteria);
        }
        else if (MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER
                .equals(entityType)) {
            recordList =
                    jpaCharityReportingHelper.getFundraisers(searchCriteria);
        }
        else if (MasterDataCodeConstants.CustomFieldTypes_FUNDRAISER_ACTIVITY
                .equals(entityType)) {
            recordList =
                    jpaCharityReportingHelper
                            .getFundraiserPages(searchCriteria);
        }
        else if (MasterDataCodeConstants.CustomFieldTypes_EVENT
                .equals(entityType)) {
            recordList = jpaCharityReportingHelper.getEvents(searchCriteria);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchReportingSearchResults() method - END");
        }
        return recordList;
    }

    /** {@inheritDoc} */
    public boolean saveCustomFieldValues(
            CharityCustomFieldValueSubset charityCustomFieldValue,
            String charityId, String entityType, AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCustomFieldValues() method - START");
        }
        boolean processStatus = false;

            Long id = charityCustomFieldValue.getId();
            String fieldValue[] = charityCustomFieldValue.getFieldValue().split(MasterDataCodeConstants.DELIM);
             String fieldCopy[] = charityCustomFieldValue.getFieldCopy().split(MasterDataCodeConstants.DELIM);
            JPACharityReportingHelper jpaCharityReportingHelper =
                    new JPACharityReportingHelper();
            jpaCharityReportingHelper.setEntityManager(entityManager);
            List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetList =
                    jpaCharityReportingHelper.getCharityCustomFieldSubset(Long
                            .valueOf(charityId), entityType, id);

            if (charityCustomFieldValueSubsetList != null && fieldValue != null) {
               int count=0;
               for(CharityCustomFieldValueSubset charityCustomFieldValueSubset:charityCustomFieldValueSubsetList){
                	int localCount=count++;
            	    charityCustomFieldValueSubset.setFieldValue(fieldValue[localCount]);
                    if(!MasterDataCodeConstants.EMPTY_STRING.equalsIgnoreCase(charityCustomFieldValue.getFieldCopy())){
                    charityCustomFieldValueSubset.setFieldCopy(MasterDataCodeConstants.TRUE.equalsIgnoreCase(fieldCopy[localCount])?MasterDataCodeConstants.COPY_SELECTED_TRUE:null);	
                    }
                    entityManager.merge(charityCustomFieldValueSubset);
                    processStatus = true;
                }
            }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::saveCustomFieldValues() method - END");
        }
        return processStatus;
    }

    /** {@inheritDoc} */
    public int getTrusteeCount(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::getTrusteeCount() method - START");
        }
        Object obj =
                entityManager.createNamedQuery("getTrusteeCount").setParameter(
                        "charityId", charityId).getSingleResult();
        int returnValue = 0;
        if (obj != null) {
            returnValue = ((Integer) obj).intValue();
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::getTrusteeCount() method - END");
        }
        return returnValue;
    }

    /** {@inheritDoc} */
    public void saveTrusteeCount(Long charityId, int trusteeCount,
            AuditAttributes attributes) {
        entityManager.createNamedQuery("saveTrsuteeCount")
            .setParameter("charityId", charityId)
            .setParameter("trusteeCount", trusteeCount)
            .setParameter("updatedIPAddress", attributes.getCreatedIPAddress())
            .setParameter("updatedProcess", attributes.getCreatedProcess())
            .setParameter("updatedUser", attributes.getCreatedUser())
            .executeUpdate();
    }

    /** {@inheritDoc} */
    public void saveTrusteeStatus(Long charityId, String trusteeStatus,
            AuditAttributes attributes) {
        entityManager.createNamedQuery("saveTrusteeStatus")
            .setParameter("charityId", charityId)
            .setParameter("trusteeStatusCode", trusteeStatus)
            .setParameter("updatedIPAddress", attributes.getCreatedIPAddress())
            .setParameter("updatedProcess", attributes.getCreatedProcess())
            .setParameter("updatedUser", attributes.getCreatedUser())
            .executeUpdate();
    }

    /** {@inheritDoc} */
    public String getOfflineCharityTrusteeStatus(Long charityId) {

        return (String) entityManager.createNamedQuery(
                "getOfflineCharityTrusteeStatus").setParameter("charityId",
                charityId).getSingleResult();
    }

    /** {@inheritDoc} */
    public List<RegisteredCharityUser> fetchRegisteredUsersOfCharityById(
            Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchRegisteredUsersOfCharityById() method - START");
        }
        List<String> rolesList = new ArrayList<String>();
        rolesList.add(CharityUserRoles.CHARITY_ROLE_US.getCode());
        rolesList.add(CharityUserRoles.CHARITY_ROLE_ADMIN.getCode());

        List<Object[]> charityUsersList =
                (entityManager
                        .createNamedQuery("fetchRegisteredUsersOfCharityById")
                        .setParameter("charityId", charityId)).setParameter(
                        "roleCode", rolesList).getResultList();
        List<RegisteredCharityUser> charityAdministratorDetails =
                new ArrayList<RegisteredCharityUser>();

        for (Object[] objArray : charityUsersList) {
            RegisteredCharityUser registeredCharityUsers =
                    new RegisteredCharityUser();
            registeredCharityUsers.setId((Long) (objArray[0]));
            registeredCharityUsers.setForename((String) objArray[1]);
            registeredCharityUsers.setSurname((String) objArray[2]);
            registeredCharityUsers.setTitle((String) objArray[3]);
            registeredCharityUsers.setLoginEmailAddress((String) objArray[4]);
            registeredCharityUsers.setBirthDay((Integer) (objArray[5]));
            registeredCharityUsers.setBirthMonth((Integer) (objArray[6]));
            registeredCharityUsers.setBirthYear((Integer) (objArray[7]));

            charityAdministratorDetails.add(registeredCharityUsers);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchRegisteredUsersOfCharityById() method - END");
        }
        return charityAdministratorDetails;
    }

    /** {@inheritDoc} */
    public List<Page> fetchCharityPages(final Long charityIf) {
        return entityManager.createNamedQuery("fetchCharityPages")
                .setParameter("charityId", charityIf).getResultList();
    }

    /** {@inheritDoc} */
    public void deleteCharityUser(Long charityId, Long userId,
            AuditAttributes auditAttributes) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::deleteCharityUser() method - charity id=" + charityId + " userId=" + userId);
        }
        Query query = null;

        query=entityManager.createNamedQuery("fetchUserRolesByUserId").setParameter("userId", userId);

        try {
            List<UserRole> userRoles =  query.getResultList();

            for (UserRole role : userRoles) {
                role.setEndDatetime(new Timestamp(Calendar.getInstance()
                        .getTimeInMillis()));
                User user = role.getUser();
                user.setEndDatetime(new Timestamp(Calendar.getInstance()
                        .getTimeInMillis()));
                entityManager.merge(role);
            }
        } catch (NoResultException e) {
            LOGGER.debug("No data found for query 'fetchUserRolesByUserId' : charityId=" + charityId +
             " userId=" + userId + " error=" + e);
        }

        // Also delete user from charity event administrator table, if exists.        
        query = entityManager.createNamedQuery("fetchCharityAdministratorByUserId")
            .setParameter("charityId", charityId)
            .setParameter("userId", userId)
            .setParameter("roleCode", MasterDataCodeConstants.Roles.ROLE_CHARITY_ADMIN.getCode());
        CharityAdministrator charityAdministrator = null;
        try {
            charityAdministrator = (CharityAdministrator) query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.debug("No data found for query 'fetchCharityAdministratorByUserId' : charityId=" + charityId +
             " userId=" + userId + " error=" + e);
        }


        if (charityAdministrator != null && charityAdministrator.getId() != null) {
// Fetch list of all events having 'AllUsersInd' as 'Y' and for the above charity.
            List<CharitableActivity> charitableActivityList = fetchCharitableActivityByCharityIdAndAllUsersInd(charityId);

            for (CharitableActivity activity : charitableActivityList) {
                CharityEventAdministrator charityEventAdministrator = null;

                query = entityManager.createNamedQuery("fetchCharityEventAdministratorByCharitableActivity")
                    .setParameter("charitableActivityId", activity.getId())
                    .setParameter("charityAdminId", charityAdministrator.getId());

                try {
                    charityEventAdministrator = (CharityEventAdministrator) query.getSingleResult();
                } catch (NoResultException e) {
                    LOGGER.debug("No data found for query 'fetchCharityEventAdministratorByCharitableActivity' : charityId=" + charityId +
                            " userId=" + userId +
                            " charitableActivityId=" + activity.getId() +
                            " charityAdminId=" + charityAdministrator.getId() +
                            " error=" + e);
                }

                if (charityEventAdministrator != null) {
                    query = entityManager.createNamedQuery("deleteCharityEventAdministrator")
                    .setParameter("charityAdminId", charityEventAdministrator.getCharityAdministrator().getId());
                    try {
                        query.executeUpdate();
                    } catch (NoResultException e) {
                        LOGGER.debug("No data found for query 'deleteCharityEventAdministrator' : charityId=" + charityId +
                                " userId=" + userId +
                                " charitableActivityId=" + activity.getId() +
                                " charityAdminId=" + charityAdministrator.getId() +
                                " error=" + e);
                    }
                }
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::deleteCharityUser() method - END");
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
        Query query =
            entityManager.createNamedQuery("fetchCharitableActivityByCharityIdAllUsersInd").setParameter(
                    "charityId", charityId);
        List<CharitableActivity> charitableActivities = query.getResultList();
        return charitableActivities;
    }

    /** {@inheritDoc} */
    public List<Object[]> fetchEmailAddresssForSettlement(Set<Long> charityIds) {
        final List<Object[]> emailIds =
                entityManager.createNamedQuery(
                        "fetchEmailAddresssForSettlement").setParameter(
                        "charityIds", charityIds)
                        .getResultList();
        
      return emailIds;
    
    }
    
    /** {@inheritDoc} */

    

	public Charity fetchCharityAudit(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityAudit() method - START");
        }
		 AuditReader auditReader = AuditReaderFactory.get(entityManager);
         final GregorianCalendar gregorianCalendar = new GregorianCalendar();
         gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
         gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
 				gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
 						.get(Calendar.DATE));
         Date currentDate = new Date(gregorianCalendar.getTimeInMillis());
		 Number revisionNumber = (Number)auditReader.getRevisionNumberForDate(currentDate);
         LOGGER.debug("##### Current Date :" +currentDate);
         LOGGER.debug("##### Revision Number :" +revisionNumber);
		 Charity charity = auditReader.find(Charity.class, charityId, revisionNumber);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCharityAudit() method - END");
        }
		return charity;
	}

	/** {@inheritDoc} */
	public BankAccount fetchBankAccountAudit(Long bankAccountId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchBankAccountAudit() method - START");
        }
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
				gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
						.get(Calendar.DATE));
        Date currentDate = new Date(gregorianCalendar.getTimeInMillis());
        Number revisionNumber = (Number)auditReader.getRevisionNumberForDate(currentDate);
        
        LOGGER.debug("##### Current Date :" +currentDate);
        LOGGER.debug("##### Revision Number :" +revisionNumber);
        
        BankAccount bankAccount = auditReader.find(BankAccount.class, bankAccountId, revisionNumber);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchBankAccountAudit() method - END");
        }
		return bankAccount;
	}

	/** {@inheritDoc} */
	public Address fetchAddressAudit(Long addressId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAddressAudit() method - START");
        }
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),
				gregorianCalendar.get(Calendar.MONTH), gregorianCalendar
						.get(Calendar.DATE));
        Date currentDate = new Date(gregorianCalendar.getTimeInMillis());
        Number revNumber = (Number)auditReader.getRevisionNumberForDate(currentDate);
        
        LOGGER.debug("##### Current Date :" +currentDate);
        LOGGER.debug("##### Revision Number :" +revNumber);
        
        Address address = auditReader.find(Address.class, addressId, revNumber);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAddressAudit() method - END");
        }		
		return address;
	}

	/** {@inheritDoc} */
	public List<AlertTrigger> fetchAlertTriggersForCharity(Long charityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAlertTriggersForCharity() method - START");
        }
		
		List<AlertTrigger> alertTriggers = 
            (entityManager.createNamedQuery("fetchAlertTriggersForCharity")
                    .setParameter("charityId", charityId)).getResultList();		
		LOGGER.debug("AlertDetailsList size " + alertTriggers.size());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchAlertTriggersForCharity() method - END");
        }

		return alertTriggers;
	}

	/** {@inheritDoc} */
    public Integer fetchCountOfDonationForCharityForSpecifiedPeriod(
            Long charityId, Long donorId, Long periodInMonths) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCountOfDonationForCharityForSpecifiedPeriod() method - START");
        }

        Query query = null;

        if (periodInMonths != null && periodInMonths.intValue() != 0) {
            Calendar toDate = Calendar.getInstance();
            Calendar fromDate = Calendar.getInstance();
            fromDate.set(Calendar.MONTH, fromDate.get(Calendar.MONTH)
                    - periodInMonths.intValue());

            /**
             * Between clause will not return current day records because of this we
             * have added one more day in to date. Same way one day is removed form
             * from date
             */
            fromDate.set(Calendar.DATE, fromDate.get(Calendar.DATE) - 1);
            toDate.set(Calendar.DATE, toDate.get(Calendar.DATE) + 1);
            query = entityManager.createNamedQuery(
                "fetchCountOfDonationForCharityForSpecifiedPeriod")
                .setParameter("charityId", charityId)
                .setParameter("donorId", donorId)
                .setParameter("fromDate",
                    new Timestamp(fromDate.getTimeInMillis()))
                .setParameter("toDate", new Timestamp(toDate
                    .getTimeInMillis()));
        } else {
            query = entityManager.createNamedQuery(
                "fetchCountOfDonationForCharity")
                .setParameter("charityId", charityId)
                .setParameter("donorId", donorId);
        }

        Long countOfDonation = (Long) query.getSingleResult();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCountOfDonationForCharityForSpecifiedPeriod() method - END");
        }
        return countOfDonation.intValue();
    }

    /** {@inheritDoc} */
    public BigDecimal fetchSumOfDonationForCharityForSpecifiedPeriod(
            Long charityId, Long donorId, Long periodInMonths) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchSumOfDonationForCharityForSpecifiedPeriod() method - START");
        }

        Query query = null;

        if (periodInMonths != null && periodInMonths.intValue() != 0) {
            Calendar toDate = Calendar.getInstance();
            Calendar fromDate = Calendar.getInstance();
            fromDate.set(Calendar.MONTH, fromDate.get(Calendar.MONTH)
                    - periodInMonths.intValue());
            fromDate.set(Calendar.DATE, fromDate.get(Calendar.DATE) - 1);
            toDate.set(Calendar.DATE, toDate.get(Calendar.DATE) + 1);

            query = entityManager.createNamedQuery(
                "fetchSumOfDonationForCharityForSpecifiedPeriod")
                .setParameter("charityId", charityId)
                .setParameter("donorId", donorId)
                .setParameter("fromDate", new Timestamp(fromDate.getTimeInMillis()))
                .setParameter("toDate", new Timestamp(toDate.getTimeInMillis()));
        } else {
            query = entityManager.createNamedQuery(
                "fetchSumOfDonationForCharity")
                .setParameter("charityId", charityId)
                .setParameter("donorId", donorId);
        }

        BigDecimal sumOfDonation = (BigDecimal) query.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchSumOfDonationForCharityForSpecifiedPeriod() method - END");
            LOGGER.trace("Returning sum of " + sumOfDonation + " for donor " + donorId);
        }
        return sumOfDonation;
    }

    /** {@inheritDoc} */
    public Integer fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod (
            Long charityId, Long fundraiserId, Long periodInMonths) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod() method - START");
        }

        Query query = null;

        if (periodInMonths != null && periodInMonths.intValue() != 0) {
            Calendar toDate = Calendar.getInstance();
            Calendar fromDate = Calendar.getInstance();
            fromDate.set(Calendar.MONTH, fromDate.get(Calendar.MONTH)
                    - periodInMonths.intValue());

            /**
             * Between clause will not return current day records because of
             * this we have added one more day in to date. Same way one day is
             * removed form from date
             */
            fromDate.set(Calendar.DATE, fromDate.get(Calendar.DATE) - 1);
            toDate.set(Calendar.DATE, toDate.get(Calendar.DATE) + 1);

            query = entityManager.createNamedQuery(
                                    "fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod")
                                    .setParameter("fundraiserId", fundraiserId)
                                    .setParameter("fromDate",
                                            new Timestamp(fromDate.getTimeInMillis()))
                                    .setParameter("toDate",
                                            new Timestamp(toDate.getTimeInMillis()));
        }
        else {
            query = entityManager.createNamedQuery(
                                    "fetchCountOfFundraiserPagesForCharity")
                                    .setParameter("fundraiserId", fundraiserId);
        }

        Long countOfDonation = (Long) query.getSingleResult();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchCountOfFundraiserPagesForCharityForSpecifiedPeriod() method - END");
        }
        return countOfDonation.intValue();
    }

    /** {@inheritDoc} */
    public BigDecimal fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod(
            Long charityId, Long fundraiserActivityId, Long periodInMonths) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::"
                    + "fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod() method - START");
        }
        Query query = null;

        if (periodInMonths != null && periodInMonths.intValue() != 0) {
            Calendar toDate = Calendar.getInstance();
            Calendar fromDate = Calendar.getInstance();
            fromDate.set(Calendar.MONTH, fromDate.get(Calendar.MONTH)
                    - periodInMonths.intValue());
            fromDate.set(Calendar.DATE, fromDate.get(Calendar.DATE) - 1);
            toDate.set(Calendar.DATE, toDate.get(Calendar.DATE) + 1);

            query = entityManager.createNamedQuery(
                                    "fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod")
                                    .setParameter("fundraiserActivityId",
                                            fundraiserActivityId)
                                    .setParameter("fromDate",
                                            new Timestamp(fromDate.getTimeInMillis()))
                                    .setParameter("toDate",
                                            new Timestamp(toDate.getTimeInMillis()));
        }
        else {
            query = entityManager.createNamedQuery(
                            "fetchSumOfFundraiserDonationForCharity")
                                .setParameter("fundraiserActivityId", fundraiserActivityId);
        }

        BigDecimal sumOfDonation = (BigDecimal) query.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchSumOfFundraiserDonationForCharityForSpecifiedPeriod() method - END");
        }
        return sumOfDonation;
    }
	
	/** {@inheritDoc} */
	public BigDecimal fetchFundraiserDonationAmountForSingleEvent(
			Long charityId, Long fundraiserActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchFundraiserDonationAmountForSingleEvent() method - START");
        }
		
		BigDecimal fundraiserDonationAmount = (BigDecimal)
        (entityManager.createNamedQuery(
		        "fetchFundraiserDonationAmountForSingleEvent")
		        .setParameter("fundraiserActivityId",fundraiserActivityId)) 
		        .getSingleResult();
		
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchFundraiserDonationAmountForSingleEvent() method - END");
        }
		return fundraiserDonationAmount;
	}

	/** {@inheritDoc} */
	public List<String> fetchEmailAddressesForAlert(Long alertTriggerId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchEmailAddressesForAlert() method - START");
        }
		List<String> emailAddresses = 
            (entityManager.createNamedQuery("fetchEmailAddressesForAlert")
                    .setParameter("alertTriggerId", alertTriggerId)).getResultList();	 
		
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchEmailAddressesForAlert() method - END");
        }
		return emailAddresses;
	}

	/** {@inheritDoc} */
    public String getCharityReference(final Long id, final Long charityId, final String userType) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::getCharityReference() method - START");
        }
        String charityReferences = "";
        List<String> charityReferenceList = null;
        if (id != null) {
            if ("D".equalsIgnoreCase(userType)) {
                charityReferenceList =
                        entityManager.createNamedQuery("getCharityReferenceValueForDonor")
                            .setParameter("charityId", charityId)
                            .setParameter("donorId", id)
                            .getResultList();
            } else if ("F".equalsIgnoreCase(userType)) {
                charityReferenceList =
                        entityManager.createNamedQuery("getCharityReferenceValueForFundraiser")
                            .setParameter("charityId", charityId)
                            .setParameter("fundraiserId", id)
                            .getResultList(); 
            }
            if (charityReferenceList != null && !charityReferenceList.isEmpty()) {
                final int listSize = charityReferenceList.size();
                for (int i = 0; i < listSize; i++) {
                    charityReferences = charityReferences.concat(charityReferenceList.get(i));
                    if (i < listSize - 1) {
                        charityReferences = charityReferences.concat(", ");
                    }
                }
            } else {
                charityReferences = null;
            }
        } else {
            charityReferences = null;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::getCharityReference() method - END");
        }
        return charityReferences;
    }

    /** {@inheritDoc} */
    public boolean updateCharityAmendmentInd(Long charityId, String newStatus) {
        boolean returnResult = true;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::updateCharityAmendmentInd() - Start: " + charityId + "/" + newStatus);
        }
        int result = entityManager.createNamedQuery("updateCharityAmendmentStatus")
            .setParameter("charityId", charityId)
            .setParameter("newStatus", newStatus)
            .executeUpdate();

        returnResult = result > 0;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::updateCharityAmendmentInd() - End: " + charityId + "/" + newStatus);
        }
        return returnResult;
    }

    /** {@inheritDoc} */
    public boolean updateAddressAmendmentInd(Long addressId, String newStatus) {
        boolean returnResult = true;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::updateAddressAmendmentInd() - Start: " + addressId + "/" + newStatus);
        }
        int result = entityManager.createNamedQuery("updateAddressAmendmentStatus")
            .setParameter("addressId", addressId)
            .setParameter("newStatus", newStatus)
            .executeUpdate();

        returnResult = result > 0;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::updateAddressAmendmentInd() - End: " + addressId + "/" + newStatus);
        }

        return returnResult;
    }

    /** {@inheritDoc} */
    public boolean updateBankAccountAmendmentInd(Long bankAccountId, String newStatus) {
        boolean returnResult = true;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::updateBankAccountAmendmentInd()-Start: " + bankAccountId + "/" + newStatus);
        }
        int result = entityManager.createNamedQuery("updateBankAccountAmendmentStatus")
            .setParameter("bankAccountId", bankAccountId)
            .setParameter("newStatus", newStatus)
            .executeUpdate();

        returnResult = result > 0;
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::updateBankAccountAmendmentInd()-End: " + bankAccountId + "/" + newStatus);
        }
        return returnResult;
    }
    
   
      /** {@inheritDoc} */
    public List<CharityOfflineRegistrationLog> 
        fetchLastCharityOfflineStatusList(Long charityOfflineRegistrationId) {
    	 if (LOGGER.isDebugEnabled()) {
             LOGGER.debug("JPACharityDAOImpl::fetchLastCharityOfflineStatusList()-Start: " + charityOfflineRegistrationId);
         }
        List<CharityOfflineRegistrationLog> charityOfflineRegistrationLogs = 
            (entityManager.createNamedQuery("fetchLastCharityOfflineStatusList")
                    .setParameter("charityOfflineRegistrationId", charityOfflineRegistrationId)).getResultList();   
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JPACharityDAOImpl::fetchLastCharityOfflineStatusList()-END: " + charityOfflineRegistrationId);
            LOGGER.debug("CharityOfflineRegistrationLog List size " + charityOfflineRegistrationLogs.size());
        }
       
        return charityOfflineRegistrationLogs;

    }
    /** {@inheritDoc} */
    public CharityOfflineRegistrationLog saveCharityOfflineRegistrationLog(
            final CharityOfflineRegistrationLog charityOfflineRegistrationLog) {
        CharityOfflineRegistrationLog charityOfflineRegistrationLogObject =
                entityManager.merge(charityOfflineRegistrationLog);
        return charityOfflineRegistrationLogObject;
    }
    
    
    /** {@inheritDoc} */
    public void copyReportingCodes(final Page page) {

		JPACharityReportingHelper charityReportingHelper = new JPACharityReportingHelper();
		charityReportingHelper.setEntityManager(entityManager);
		Event event = page.getEvent();
		Long eventId = event.getId();
		Iterator<CharitableActivity> iterator = event.getCharitableActivities()
				.iterator();

		List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetListAll;
		List<CharityCustomFieldValueSubset> charityCustomFieldValueSubsetList = new ArrayList<CharityCustomFieldValueSubset>();
		List<Long> pageIdList = new ArrayList<Long>();

		while (iterator.hasNext()) {

			charityCustomFieldValueSubsetListAll = new ArrayList<CharityCustomFieldValueSubset>();
			charityCustomFieldValueSubsetList = new ArrayList<CharityCustomFieldValueSubset>();
			pageIdList = new ArrayList<Long>();

			CharitableActivity charitableActivity = iterator.next();

			List<CharityCustomFieldSubset> eventCodesList = charityReportingHelper
					.getEvtCodesForCharity(charitableActivity.getCharity()
							.getId());
			List<CharityCustomFieldSubset> pageCodesList = charityReportingHelper
					.getFacCodesForCharity(charitableActivity.getCharity()
							.getId());

			for (CharityCustomFieldSubset charityCustomFieldEvent : eventCodesList) {

				Long charityCustomFieldId = charityCustomFieldEvent.getId();
				charityCustomFieldValueSubsetList = (entityManager
						.createNamedQuery("fetchCustomFieldValuesByEvent")
						.setParameter("charityCustomFieldId",
								charityCustomFieldId).setParameter("eventId",
						eventId)).getResultList();

				if (charityCustomFieldValueSubsetList != null
						&& charityCustomFieldValueSubsetList.size() > 0) {

					charityCustomFieldValueSubsetListAll
							.add(charityCustomFieldValueSubsetList.get(0));

				}

			}

			for (CharityCustomFieldSubset charityCustomFieldPage : pageCodesList) {

				Long charityCustomFieldIdPage = charityCustomFieldPage.getId();
				pageIdList.add(charityCustomFieldIdPage);

			}

			if (charityCustomFieldValueSubsetListAll != null
					&& charityCustomFieldValueSubsetListAll.size() > 0) {
				int count = 0;
				for (CharityCustomFieldValueSubset charityCustomFieldValueSubset : charityCustomFieldValueSubsetListAll) {

					CharityCustomFieldValueSubset customFieldValueSubset = new CharityCustomFieldValueSubset();
					customFieldValueSubset.setCharityCustomFieldId(pageIdList
							.get(count));
					customFieldValueSubset.setStartDateTime(new Timestamp(
							Calendar.getInstance().getTimeInMillis()));
					customFieldValueSubset.setFundraiserActivityId(page
							.getFundraiserActivity().getId());
					count++;

					if (charityCustomFieldValueSubset.getFieldCopy() != null
							&& MasterDataCodeConstants.YES
									.equalsIgnoreCase(charityCustomFieldValueSubset
											.getFieldCopy())) {

						customFieldValueSubset
								.setFieldValue(charityCustomFieldValueSubset
										.getFieldValue());

					} else {

						customFieldValueSubset
								.setFieldValue(MasterDataCodeConstants.EMPTY_STRING);

					}

					entityManager.merge(customFieldValueSubset);

				}

			}

		}

	}

    /** {@inheritDoc} */
    public BigDecimal fetchTotalFundraiserDonationForCharityForSpecifiedPeriod(Long charityId,
                                                                               Long fundRaiserId,
                                                                               Long periodInMonths) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::"
                    + "fetchTotalFundraiserDonationForCharityForSpecifiedPeriod() method - START");
        }
        Query query = null;

        if (periodInMonths != null && periodInMonths.intValue() != 0) {
            Calendar toDate = Calendar.getInstance();
            Calendar fromDate = Calendar.getInstance();
            fromDate.set(Calendar.MONTH, fromDate.get(Calendar.MONTH) - periodInMonths.intValue());
            fromDate.set(Calendar.DATE,  fromDate.get(Calendar.DATE) - 1);
            toDate.set(Calendar.DATE,    toDate.get(Calendar.DATE) + 1);

            query = entityManager.createNamedQuery("fetchTotalFundraiserDonationForCharityForSpecifiedPeriod")
                                    .setParameter("fundRaiserId", fundRaiserId)
                                    .setParameter("charityId",    charityId)
                                    .setParameter("fromDate", new Timestamp(fromDate.getTimeInMillis()))
                                    .setParameter("toDate",   new Timestamp(toDate.getTimeInMillis()));
        }
        else {
            query = entityManager.createNamedQuery("fetchTotalFundraiserDonationForCharity")
                                    .setParameter("fundRaiserId", fundRaiserId)
                                    .setParameter("charityId",    charityId);
        }

        BigDecimal sumOfDonation = (BigDecimal) query.getSingleResult();
        LOGGER.debug("JPACharityDAOImpl::fetchTotalFundraiserDonationForCharityForSpecifiedPeriod: " + sumOfDonation);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchTotalFundraiserDonationForCharityForSpecifiedPeriod() method - END");
        }
        return sumOfDonation;
    }

	/** {@inheritDoc} */
	public BigDecimal fetchTotalFundraiserDonationAmountForSingleCharityEvent(Long charityId,
                                                                              Long fundRaiserActivityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchTotalFundraiserDonationAmountForSingleCharityEvent() method - START");
        }

		BigDecimal fundraiserDonationAmount = (BigDecimal) (entityManager.createNamedQuery(
                "fetchTotalFundraiserDonationAmountForSingleCharityEvent")
                .setParameter("fundRaiserActivityId",fundRaiserActivityId)
                .setParameter("charityId",charityId))
                .getSingleResult();

        LOGGER.debug("JPACharityDAOImpl::fetchTotalFundraiserDonationAmountForSingleCharityEvent: "
                + fundraiserDonationAmount + ", Charity = " + charityId + ", FRA ID = " + fundRaiserActivityId);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPACharityDAOImpl::fetchTotalFundraiserDonationAmountForSingleCharityEvent() method - END");
        }
		return fundraiserDonationAmount;
	}

    //TRAC 848.
    private TrusteeStatus checkTrusteeStatusOnOffLineStatusSave(Long id, TrusteeStatus incomingStatus) {
        TrusteeStatus result = incomingStatus;
        if (result == null || result.getCode() == null) {
            CharityOfflineRegistration tempRec = entityManager.find(CharityOfflineRegistration.class, id);
            if (tempRec != null && tempRec.getTrusteeStatus() != null) {
                result = tempRec.getTrusteeStatus();
            }
        }
        return result;
    }


}
	