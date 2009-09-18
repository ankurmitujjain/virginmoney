package com.virginmoneygiving.giving.dao;

import java.util.List;

import com.virginmoneygiving.giving.domain.CharityDonation;
import com.virginmoneygiving.giving.domain.Donation;
import com.virginmoneygiving.giving.domain.Donor;
import com.virginmoneygiving.giving.domain.FundraiserActivityDonation;
import com.virginmoneygiving.giving.domain.PaymentCard;
import com.virginmoneygiving.giving.domain.Person;
import com.virginmoneygiving.giving.domain.PersonalAddress;
import com.virginmoneygiving.giving.domain.RegularDonation;

/**
 * DAO to handle operations related to donation functionality.
 * 
 * @author vikas kale
 */
public interface DonorDAO {

    /**
     * Stores the Donor's personal details into database. Personal details are
     * like title, forename, surname, address, contact details.
     * <p />
     * 
     * @param donor Domain object which represents the {@link Donor} details.
     * 
     * @return Long donorId.
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
     * This method is used to fetch payment card details for the specified person.
     * 
     * @param personId contains person id.
     * 
     * @return {@link PaymentCard} contains person's payment card details.
     */
    PaymentCard fetchPaymentcardDetails(Long personId);

    /**
     * Updates the Donor's personal details into database. Personal details are
     * like title, forename, surname, address, contact details.
     * <p />
     * 
     * @param donor Domain object which represents the {@link Donor} details.
     * 
     * @return Long donorId.
     */
    Donor updateDonorPersonalDetails(Donor donor);

    /**
     * Stores the donation details. The donation can either be against
     * charity or against a fundraiser activity.
     * 
     * @param donation the donation details.
     * 
     * @return the donation id.
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
     * Persist the {@link RegularDonation} with given details.
     * 
     * @param regularDonation the regular donation to persist.
     * 
     * @return the persisted regular donation.
     */
    RegularDonation saveRegularDonation(RegularDonation regularDonation);

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
     * This method is used to fetch donorId using userRoleId.
     * 
     * @param userRoleId the userRoleId of the logged in user.
     * 
     * @return donorId.
     */
    Long fetchDonorIdByUserRoleId(Long userRoleId);


    /**
     * Gets the regular donation.
     * 
     * @param id the id
     * 
     * @return the regular donation
     */
    RegularDonation getRegularDonation(Long id);

    /**
     * Update regular donation.
     * 
     * @param regularDonation the regular donation
     * 
     * @return the regular donation
     */
    RegularDonation updateRegularDonation(RegularDonation regularDonation);

    /**
     * Cancel regular donation.
     * 
     * @param regularDonationId the regular donation id
     * 
     * @return the regular donation
     */
    RegularDonation cancelRegularDonation(Long regularDonationId);

    /**
     * Update donation.
     * 
     * @param donation the donation
     * 
     * @return the donation
     */
    Donation updateDonation(Donation donation);
    
    /**
     * Update charity donation.
     * 
     * @param charityDonation the charity donation
     * 
     * @return the charity donation
     */
    CharityDonation updateCharityDonation(CharityDonation charityDonation);
    
    /**
     * Fetch donation.
     * 
     * @param id the id
     * 
     * @return the donation
     */
    Donation fetchDonation(Long id);
    
    /**
     * Fetch charity donation.
     * 
     * @param id the id
     * 
     * @return the charity donation
     */
    CharityDonation fetchCharityDonation(Long id);    

    /**
     * Fetch donor details using donation id.
     * 
     * @param donationId the donation id.
     * 
     * @return Donor object with donor details.
     */
    Donor fetchDonorDetails(Long donationId);
    
    /**
     * Fetch person details using donation id.
     * 
     * @param donationId the donation id.
     * 
     * @return Person object with person details.
     */
    Person fetchPersonDetailsOfDonation(Long donationId);
    
    
    /**
     * Fetch donorId using user Id.
     * 
     * @param userId to fetch donor id.
     * 
     * @return donor id.
     */
    Long fetchDonorIdByUserId(Long userId);

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
    public List<FundraiserActivityDonation> fetchFundraiserActivityDonations(
            Long fundraiserActivityId, Integer rowNumberLimit);
    
    /**
     * This service will datestamp the regular donation record.
     * 
     * @param donorId the donor id
     * 
     * @return true, if date stamp regular donation
     */
    boolean dateStampRegularDonation(Long donorId);

    /**
     * Find the biggest single donation for an activity.
     * (More than one can have the same value and therefore more than 1 can be returned)
     * 
     * @param activityId the activity id
     * 
     * @return the list< donation>
     */
    public List<Donation> fetchBiggestFundraiserActivityDonation(long activityId);

    /**
     * Count the total number of donations for a given activity.
     * 
     * @param activityId the activity id
     * 
     * @return the long
     */
    public long countDonationsForActivity(long activityId);
    
    /**
     * Count the total number of donations by a donor.
     * 
     * @param donorId the donor id
     * 
     * @return long count
     */
    public long countDonationsByDonor(long donorId);    
    
    /**
     * Update regular donation status ind.
     * 
     * @param donorId the donor id
     * @param newStatus the new status
     * 
     * @return true, if update regular donation status ind
     */
    public boolean updateRegularDonationStatusInd(Long donorId, String newStatus);
    
    /**
     * Save person and address.
     * 
     * @param personalAddress the personal address
     * 
     * @return the person
     */
    Person savePersonAndAddress(PersonalAddress personalAddress);
    /**
     * This method for get  the fetchDonationNotificationIndFromActivityId Details.
     * 
     * @param fundraiserActivityId of type fundraiserActivityId
     * 
     * @return boolean
     */
	boolean fetchDonationNotificationIndFromActivityId(Long fundraiserActivityId);

}
