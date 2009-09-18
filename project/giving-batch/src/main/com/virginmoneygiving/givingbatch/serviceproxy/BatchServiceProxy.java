package com.virginmoneygiving.givingbatch.serviceproxy;

import com.virginmoneygiving.givingbatch.domain.CharitiesBatch;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollectedBatch;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.EventProcessing;
import com.virginmoneygiving.givingbatch.domain.FailedDonationPaymentBatch;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.GiftAidReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.MakeDonationWithActiveCard;
import com.virginmoneygiving.givingbatch.domain.SaveRegularDonationRequest;
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaidBatch;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaidBatch;
import com.virginmoneygiving.givingbatch.domain.RegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.jms.GlisMessageDelegate;

/**
 * This interface contains all the methods which are required to make a gliss call.
 * Methods in this class will return a boolean outcome depending on the processing of
 * the object send to the gliss.
 * 
 * @author Siva Kumar
 */
public interface BatchServiceProxy {
	
	/**
	 * This method used to process the DonationPaymentsInitiated.
	 * 
	 * @param donationPaymentInitiatedBatch of type DonationPaymentInitiatedBatch object
	 * 
	 * @return booelan value
	 * 
	 * @throws exception if donation not processed
	 * @throws Exception the exception
	 */
	boolean processDonationPaymentsInitiated(
			DonationPaymentInitiatedBatch donationPaymentInitiatedBatch)
			throws GivingBatchException;

	/**
	 * This method used to process the DonationPaymentsCollected.
	 * 
	 * @param donationPaymentCollectedBatch of type DonationPaymentCollectedBatch object
	 * 
	 * @return boolean value
	 * 
	 * @throws exception if donation is not collected
	 * @throws Exception the exception
	 */
	boolean processDonationPaymentsCollected(
			DonationPaymentCollectedBatch donationPaymentCollectedBatch)
			throws GivingBatchException;

	/**
	 * This method used to process the GiftAidReceived.
	 * 
	 * @param giftAidReceivedBatch of type GiftAidReceivedBatch object
	 * 
	 * @return boolean value
	 * 
	 * @throws exception if gift aid received is not processed
	 * @throws Exception the exception
	 */
	boolean processGiftAidReceived(GiftAidReceivedBatch giftAidReceivedBatch)
			throws GivingBatchException;

	/**
	 * This method used to process the GiftAidClaimed.
	 * 
	 * @param giftAidClaimedBatch of type GiftAidClaimedBatch object
	 * 
	 * @return status whether giftAidClaimed is processed or not
	 * 
	 * @throws exception if gift aid claimed is not processed
	 * @throws Exception the exception
	 */
	boolean processGiftAidClaimed(GiftAidClaimedBatch giftAidClaimedBatch)
			throws GivingBatchException;

	/**
	 * This method used to process the transactionChargePaid.
	 * 
	 * @param transactionChargePaidBatch of type TransactionChargePaidBatch object
	 * 
	 * @return status whether TransactionChargePaid is processed or not
	 * 
	 * @throws exception if Transaction charge paid is not processed
	 * @throws Exception the exception
	 */
	boolean processTransactionChargePaid(
			TransactionChargePaidBatch transactionChargePaidBatch)
			throws GivingBatchException;

	/**
	 * This method used to process the transactionFeePaid.
	 * 
	 * @param transactionFeePaidBatch of type TransactionFeePaidBatch object
	 * 
	 * @return status whether TransactionFeePaid is processed or not
	 * 
	 * @throws exception if Transaction fee paid is not processed
	 * @throws Exception the exception
	 */
	boolean processTransactionFeesPaid(
			TransactionFeePaidBatch transactionFeePaidBatch) throws GivingBatchException;

	/**
	 * This method used to process the registerCharities status.
	 * 
	 * @param charitiesBatch of type CharitiesBatch object
	 * 
	 * @return status whether registerCharities is processed or not
	 * 
	 * @throws exception if register charities is not processed
	 * @throws Exception the exception
	 */
	boolean registerCharities(CharitiesBatch charitiesBatch) throws GivingBatchException;

	/**
	 * This method used to process the donationPaymentCollectedBatch status.
	 * 
	 * @param donationPaymentCollectedBatch of type DonationPaymentCollectedBatch object
	 * 
	 * @return status whether EventRegistrationPaymentsCollected is processed or
	 * not
	 * 
	 * @throws exception if EventRegistrationPaymentsCollected is not processed
	 * @throws Exception the exception
	 */
	boolean processEventRegistrationPaymentsCollected(
			DonationPaymentCollectedBatch donationPaymentCollectedBatch)
			throws GivingBatchException;

	/**
	 * This method used to process the donationPaymentCollectedBatch status.
	 * 
	 * @param donationPaymentCollectedBatch of type DonationPaymentCollectedBatch object
	 * 
	 * @return status whether processRegularDonationPaymentsCollected is
	 * processed or not
	 * 
	 * @throws exception if processRegularDonationPaymentsCollected is not processed
	 * @throws Exception the exception
	 */

	boolean processRegularDonationPaymentsCollected(
			DonationPaymentCollectedBatch donationPaymentCollectedBatch)
			throws GivingBatchException;

	/**
	 * Send new registration fee records to Oracle Financials.
	 * 
	 * @param registrationFeesBatch the new Registration Fees
	 * 
	 * @return true if processed ok
	 * 
	 * @throws Exception if there is a processing error
	 */
	boolean createRegistrationFees(RegistrationFeesBatch registrationFeesBatch)
			throws GivingBatchException;

	/**
	 * Send TransactionReliefClaimed records to glis.
	 * 
	 * @param transitionalReliefClaimedBatch the records
	 * 
	 * @return true if processed ok
	 * 
	 * @throws Exception if there was an error
	 */
	public boolean transitionalReliefClaimed(
			TransitionalReliefClaimedBatch transitionalReliefClaimedBatch)
			throws GivingBatchException;

	/**
	 * Call processEvent on GLIS. The GLIS processEvent() method is used to tell
	 * glis we want the results of some processing it should have done. The type
	 * of processing depends on the parameters of the serviceEventProcessing
	 * object sent with the request. GLIS should respond by sending a JMS that
	 * will be picked up by the {@link GlisMessageDelegate} and used to invoke
	 * another job.
	 * 
	 * @param domainEventProcessing the domain event processing
	 * 
	 * @return true, if process event
	 * 
	 * @throws Exception the exception
	 */
	 boolean processEvent(EventProcessing domainEventProcessing)
			throws GivingBatchException;

	/**
	 * This method used to process the event registration status.
	 * 
	 * @param donationPaymentInitiatedBatch the donation payment initiated batch
	 * 
	 * @return status whether event registration payment is processed or not
	 * 
	 * @throws exception if event registration payment is not processed
	 * @throws Exception the exception
	 */
	boolean processEventRegistrationPaymentsInitiated(
			DonationPaymentInitiatedBatch donationPaymentInitiatedBatch)
			throws GivingBatchException;

	/**
	 * Process event registration payments failed.
	 * 
	 * @param failedDonationPaymentBatch the failed donation payment batch
	 * 
	 * @return true, if process event registration payments failed
	 * 
	 * @throws Exception the exception
	 */
	boolean processEventRegistrationPaymentsFailed(
			FailedDonationPaymentBatch failedDonationPaymentBatch)
			throws GivingBatchException;

	/**
	 * Process failed donation payments.
	 * 
	 * @param failedDonationPaymentBatch the failed donation payment batch
	 * 
	 * @return true, if process failed donation payments
	 * 
	 * @throws Exception the exception
	 */
	 boolean processFailedDonationPayments(
			FailedDonationPaymentBatch failedDonationPaymentBatch)
			throws GivingBatchException;

	/**
	 * Transitional relief received.
	 * 
	 * @param transitionalReliefReceivedBatch the transitional relief received batch
	 * 
	 * @return true, if transitional relief received
	 * 
	 * @throws Exception the exception
	 */
	 boolean transitionalReliefReceived(
			TransitionalReliefReceivedBatch transitionalReliefReceivedBatch)
			throws GivingBatchException;

	/**
	 * Process web donation payments failed.
	 * 
	 * @param failedDonationPaymentBatch the failed donation payment batch
	 * 
	 * @return true, if process web donation payments failed
	 * 
	 * @throws Exception the exception
	 */
	 boolean processWebDonationPaymentsFailed(
			FailedDonationPaymentBatch failedDonationPaymentBatch)
			throws GivingBatchException;

	/**
	 * Waive registration fees.
	 * 
	 * @param waiveRegistrationFeesBatch the waive registration fees batch
	 * 
	 * @return true, if waive registration fees
	 * 
	 * @throws Exception the exception
	 */
	 boolean waiveRegistrationFees(
			WaiveRegistrationFeesBatch waiveRegistrationFeesBatch)
			throws GivingBatchException;

	/**
	 * Maintain charities.
	 * 
	 * @param charitiesBatch the charities batch
	 * 
	 * @return true, if maintain charities
	 * 
	 * @throws Exception the exception
	 */
	 boolean maintainCharities(CharitiesBatch charitiesBatch)
			throws GivingBatchException;

	/**
	 * Raise an alert when an exception is occured while processing.
	 * 
	 * @param jobName - job name
	 * @param jobId - job id
	 * @param ex - exception object
	 * 
	 * @throws Exception the exception
	 */
	 void raiseAlert(String jobName, String jobId, Exception ex)
			throws GivingBatchException;

	/**
	 * Collects the Regular Donation.
	 * 
	 * @param donationRequest the donation request
	 * 
	 * @return true, if make regular donation with active card
	 * 
	 * @throws Exception the exception
	 */
	 boolean makeRegularDonationWithActiveCard(
			MakeDonationWithActiveCard donationRequest) throws GivingBatchException;

	/**
	 * TO save the Regular Donation record through UCPHB0460.
	 * 
	 * @param donationRequest the donation request
	 * 
	 * @return true, if save regular donation
	 * 
	 * @throws Exception the exception
	 */
	 boolean saveRegularDonation(
			SaveRegularDonationRequest donationRequest) throws GivingBatchException;
}
