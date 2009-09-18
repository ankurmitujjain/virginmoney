package com.virginmoneygiving.givingbatch.serviceproxy;

import java.math.BigDecimal;

import com.virginmoneygiving.givingbatch.domain.Charity;
import com.virginmoneygiving.givingbatch.domain.Donor;
import com.virginmoneygiving.givingbatch.domain.Person;
import com.virginmoneygiving.givingmanagement.service.messages.GivingManagementServiceFaultException;
import com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementServiceFaultException;

/**
 * This interface used to call web services functions.
 * 
 * @author Srinivas Nageli
 * @author taruna
 */
public interface GiftAidClaimServiceProxy {

    /**
     * To generate a sequence number.
     * 
     * @param transactionType Transaction type
     * @param referenceType Reference type
     * 
     * @return sequence number
     * 
     * @throws Exception in case of exception
     */
    String generateSequence(String transactionType, String referenceType)
            throws PaymentManagementServiceFaultException;

    /**
     * Calculate the transitional relief.
     * 
     * @param giftAidAmount the gift aid amount
     * @param transactionType the transaction type
     * 
     * @return the transitional relief amount
     * 
     * @throws Exception the exception
     */
    BigDecimal calculateTransitionalRelief(BigDecimal giftAidAmount,
            String transactionType) throws PaymentManagementServiceFaultException;

    /**
     * Fetch charityDetails by given IDs.
     * 
     * @param id of type Long eg: 1
     * 
     * @return List of Charity object.
     * 
     * @throws Exception the exception
     */
    Charity fetchCharityDetailsById(Long id) throws GivingManagementServiceFaultException;

    /**
     * Fetch donor details using donation id.
     * 
     * @param donationId the donation id.
     * 
     * @return donor object with donor details.
     * 
     * @throws Exception the exception
     */
    Donor fetchDonorDetails(Long donationId) throws GivingManagementServiceFaultException;
    
    /**
     * Fetch person details using donation id.
     * 
     * @param donationId the donation id.
     * 
     * @return person object with person details.
     * 
     * @throws Exception the exception
     */
    Person fetchPersonDetailsOfDonation(Long donationId) throws GivingManagementServiceFaultException;
    
    /**
     * Fetch VMG HMRC Reference Number from Charity_Offline_reg table.
     * 
     * @param charityId the charity id
     * 
     * @return the string
     * 
     * @throws Exception the exception
     */
    String fetchVmgHMRCReference(Long charityId) throws GivingManagementServiceFaultException;

}
