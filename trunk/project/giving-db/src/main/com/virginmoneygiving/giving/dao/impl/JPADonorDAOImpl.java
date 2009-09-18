package com.virginmoneygiving.giving.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.virginmoneygiving.giving.dao.DonorDAO;
import com.virginmoneygiving.giving.domain.AddressType;
import com.virginmoneygiving.giving.domain.CharityDonation;
import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.giving.domain.Donor;
import com.virginmoneygiving.giving.domain.FundraiserActivityDonation;
import com.virginmoneygiving.giving.domain.PaymentCard;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.PersonalAddress;
import com.virginmoneygiving.giving.domain.RegularDonation;
import com.virginmoneygiving.giving.constants.MasterDataCodeConstants;

/**
 * DAO for Donation related functionality.
 * 
 * @author vikas kale
 * @author Puneet Swarup - changed logger class.
 */
public class JPADonorDAOImpl implements DonorDAO {

	/** Logger for logging. */
	private static final Logger LOGGER = Logger.getLogger(JPADonorDAOImpl.class);

	/** An Entity Manager Instance. */
	private EntityManager entityManager;

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
	public Long saveDonorPersonalDetails(Donor donor) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveDonorPersonalDetails() method - START");
        }
		entityManager.persist(donor);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveDonorPersonalDetails() method - END");
        }
		return donor.getId();
	}

	/** {@inheritDoc} */
	public Donor fetchDonorPersonalDetails(Long donorId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorPersonalDetails() method - START");
        }
		Donor donor = entityManager.find(Donor.class, donorId);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorPersonalDetails() method - END");
        }
		return donor;
	}

	/** {@inheritDoc} */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED,
        noRollbackFor = NoResultException.class)
	public PaymentCard fetchPaymentcardDetails(Long personId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchPaymentcardDetails() method - START");
        }
		PaymentCard paymentCard = (PaymentCard) entityManager.createNamedQuery(
				"fetchPaymentcardDetails").setParameter("personId", personId)
				.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchPaymentcardDetails() method - END");
        }
		return paymentCard;
	}

	/** {@inheritDoc} */
	public Donor updateDonorPersonalDetails(Donor donor) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::updateDonorPersonalDetails() method");
        }
		return entityManager.merge(donor);
	}

	/** {@inheritDoc} */
	public Donation saveDonationDetails(Donation donation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveDonationDetails() method - START");
        }
		entityManager.persist(donation);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveDonationDetails() method - END");
        }
		return donation;
	}
	/** {@inheritDoc} */
	public Person savePerson(Person person) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::savePerson() method - START");
        }
        entityManager.persist(person);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::savePerson() method - END");
        }
        return person;
	}
	
	/** {@inheritDoc} */
    public Person savePersonAndAddress(PersonalAddress personalAddress) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::savePersonAndAddress() method - START");
        }
        personalAddress.getAddress().setAddressType((AddressType)
                entityManager.find(AddressType.class, personalAddress.getAddress().getAddressType().getCode()));
        
        entityManager.persist(personalAddress);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::savePersonAndAddress() method - END");
        }
        return personalAddress.getPerson();
    }

	/** {@inheritDoc} */
	public CharityDonation saveCharityDonation(CharityDonation charityDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveCharityDonation() method - START");
        }
		entityManager.persist(charityDonation);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveCharityDonation() method - END");
        }
		return charityDonation;
	}

	/** {@inheritDoc} */
	public RegularDonation saveRegularDonation(RegularDonation regDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveRegularDonation() method - START");
        }
		entityManager.persist(regDonation);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveRegularDonation() method - END");
        }
		return regDonation;
	}

	/** {@inheritDoc} */
	public FundraiserActivityDonation saveFundraiserActivityDonation(
			FundraiserActivityDonation activityDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveFundraiserActivityDonation() method - START");
        }
		activityDonation = entityManager.merge(activityDonation);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::saveFundraiserActivityDonation() method - END");
        }
		return activityDonation;
	}

	/** {@inheritDoc} */
	public Long fetchDonorIdByUserRoleId(Long userRoleId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorIdByUserRoleId() method - START");
        }
		Long donorId = (Long) entityManager.createNamedQuery(
				"fetchDonorIdByUserRoleId").setParameter("id", userRoleId)
				.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorIdByUserRoleId() method - END");
        }
		return donorId;
	}


    /** {@inheritDoc} */
    public RegularDonation getRegularDonation(Long id) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::getRegularDonation() method");
        }
		return entityManager.find(RegularDonation.class, id);
    }


    /** {@inheritDoc} */
    public RegularDonation updateRegularDonation(RegularDonation regularDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::updateRegularDonation() method");
        }
		return entityManager.merge(regularDonation);
    }


    /** {@inheritDoc} */
    public RegularDonation cancelRegularDonation(Long regularDonationId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::cancelRegularDonation() method - START");
        }
        RegularDonation regularDonation = entityManager.find(RegularDonation.class, regularDonationId);

        // TODO - check the real code required here - this is a guess
        if(regularDonation != null ){
        	regularDonation.setStatusIndicator(MasterDataCodeConstants.REGULAR_DONATION_STATUS_CANCELLED);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::cancelRegularDonation() method - END");
        }
		return entityManager.merge(regularDonation);
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.giving.dao.DonorDAO#updateDonation(com.virginmoneygiving.giving.domain.Donation)
     */
    public Donation updateDonation(Donation donation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::updateDonation() method");
        }
		return entityManager.merge(donation);

	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.giving.dao.DonorDAO#updateCharityDonation(com.virginmoneygiving.giving.domain.CharityDonation)
	 */
	public CharityDonation updateCharityDonation(CharityDonation charityDonation) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::updateCharityDonation() method");
        }
		return entityManager.merge(charityDonation);
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.giving.dao.DonorDAO#fetchDonation(java.lang.Long)
	 */
	public Donation fetchDonation(Long id) {
		return entityManager.find(Donation.class, id);
	}

	/* (non-Javadoc)
	 * @see com.virginmoneygiving.giving.dao.DonorDAO#fetchCharityDonation(java.lang.Long)
	 */
	public CharityDonation fetchCharityDonation(Long id) {
		return entityManager.find(CharityDonation.class, id);
	}

	/** {@inheritDoc} */
	public Donor fetchDonorDetails(Long donationId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorDetails() method - START");
        }
		Donor donor = (Donor) entityManager.createNamedQuery(
				"fetchDonorDetails").setParameter("id", donationId)
				.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorDetails() method - END");
        }
		return donor;
	}

	/** {@inheritDoc} */
    public Person fetchPersonDetailsOfDonation(Long donationId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchPersonDetailsOfDonation() method - START");
        }
        Person person = (Person) entityManager.createNamedQuery(
                "fetchPersonDetailsOfDonation").setParameter("id", donationId)
                .getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchPersonDetailsOfDonation() method - END");
        }
        return person;
    }

	/** {@inheritDoc} */
	public Long fetchDonorIdByUserId(Long userId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorIdByUserId() method - START");
        }
		Long donorId = (Long) entityManager.createNamedQuery(
				"fetchDonorIdByUserId").setParameter("userId", userId)
				.getSingleResult();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonorIdByUserId() method - END");
        }
		return donorId;
	}

	/** {@inheritDoc} */
	public List<FundraiserActivityDonation> fetchFundraiserActivityDonations(
			Long fundraiserActivityId, Integer rowNumberLimit) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchFundraiserActivityDonations() method - START");
        }
        List<FundraiserActivityDonation> results = null;
        if(fundraiserActivityId != null && rowNumberLimit != null){
    		Query query = entityManager
    				.createNamedQuery("fetchFundraiserActivityDonations");
    		query
    				.setParameter("fundraiserActivityId",
    						fundraiserActivityId);
    		query.setMaxResults(rowNumberLimit);
    
    		results = query.getResultList();    
           
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchFundraiserActivityDonations() method - END");
        }
		return results;
	}

	/** {@inheritDoc} */
	public List<Donation> fetchBiggestFundraiserActivityDonation(long activityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchBiggestFundraiserActivityDonation() method - START");
        }
		Query query = entityManager
				.createQuery(
						"from Donation d "
								+ "where d.fundraiserActivity.id = :activityId and d.donationFailedInd='N' and d.grossAmount = (select max(grossAmount) from Donation where fundraiserActivity.id = :activityId and donationFailedInd='N')")
				.setParameter("activityId", activityId);
		List<Donation> results = query.getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchBiggestFundraiserActivityDonation() method - END");
        }
		return results;
	}

	/** {@inheritDoc} */
	public long countDonationsForActivity(long activityId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::countDonationsForActivity() method - START");
        }
		Query query = entityManager
				.createQuery(
						"select count(*) from Donation d where d.fundraiserActivity.id = :activityId and d.donationFailedInd='N'")
				.setParameter("activityId", activityId);
		Object result = query.getSingleResult();
		if (result == null) {
	        if (LOGGER.isTraceEnabled()) {
	            LOGGER.trace("JPADonorDAOImpl::countDonationsForActivity() method - END");
	        }
			return 0;
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::countDonationsForActivity() method - END");
        }
		return (Long) result;
	}

	/** {@inheritDoc} */
	public boolean dateStampRegularDonation(Long donorId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::dateStampRegularDonation() method - START");
        }
		boolean returnResult = false;
		Query query = entityManager
				.createNamedQuery("dateStampRegularDonationForProcessed");
		int result = query.setParameter("donorId", donorId).executeUpdate();
		if (result > 0) {
			returnResult = true;
		} else {
			returnResult = false;
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::dateStampRegularDonation() method - END");
        }
		return returnResult;
	}

	/** {@inheritDoc} */
	public long countDonationsByDonor(long donorId) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::countDonationsByDonor() method - START");
        }
        Query query = entityManager.createQuery("select count(*) from Donation d where d.donor.id = :donorId and d.donationFailedInd='N'")
        .setParameter("donorId", donorId);
		Object result = query.getSingleResult();
		if(result == null){
	        if (LOGGER.isTraceEnabled()) {
	            LOGGER.trace("JPADonorDAOImpl::countDonationsByDonor() method - END");
	        }
		    return 0;
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::countDonationsByDonor() method - END");
        }
		return (Long)result;

	}

	/** {@inheritDoc} */
	public boolean updateRegularDonationStatusInd(Long donorId, String newStatus) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::updateRegularDonationStatusInd() method - START");
        }
		boolean returnResult = false;
		Query query = entityManager.createNamedQuery(
				"updateRegularDonationStatus").setParameter("newStatus",
				newStatus).setParameter("donorId", donorId);
		int result = query.executeUpdate();
		if (result > 0) {
			returnResult = true;
		} else {
			returnResult = false;
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::updateRegularDonationStatusInd() method - END");
        }
		return returnResult;
	}

	/** {@inheritDoc} */
	public boolean fetchDonationNotificationIndFromActivityId(
			Long fundraiserActivityId) {
		if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonationNotificationIndFromActivityId() method - START");
        }
		boolean returnResult = false;
		String notificationVal = null;
		notificationVal=(String)entityManager.createNamedQuery(
				"fetchDonationNotificationIndFromActivityId").setParameter("fundraiserActivityId",fundraiserActivityId) .getSingleResult();
		
		if (notificationVal != null && notificationVal.equalsIgnoreCase("Y")) {
			returnResult = true;
		}
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPADonorDAOImpl::fetchDonationNotificationIndFromActivityId() method - END");
        }
		return returnResult;
	}

}
