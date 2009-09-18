package com.virginmoneygiving.givingbatch.delegate;

import com.virginmoneygiving.givingbatch.domain.CharitiesBatch;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollectedBatch;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.EventProcessing;
import com.virginmoneygiving.givingbatch.domain.FailedDonationPaymentBatch;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.GiftAidReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.MakeDonationWithActiveCard;
import com.virginmoneygiving.givingbatch.domain.RegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.domain.SaveRegularDonationRequest;
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaidBatch;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaidBatch;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.jms.GlisMessageDelegate;
import com.virginmoneygiving.givingbatch.serviceproxy.BatchServiceProxy;

/**
 * This class contains all the methods which are required to make a gliss call.
 * Methods in this class will return a boolean outcome depending on the processing of
 * the object send to the gliss.
 * 
 * @author Siva Kumar
 */
public class BatchDelegate {
    /** create instance of BatchServiceProxy value. */
    private BatchServiceProxy batchServiceProxy;

    /**
     * This is to inject the proxy in spring.
     * 
     * @param batchServiceProxy
     *            the batchServiceProxy to set
     */
    public void setBatchServiceProxy(BatchServiceProxy batchServiceProxy) {
        this.batchServiceProxy = batchServiceProxy;
    }

    /**
     * This method used to process the DonationPaymentsInitiated.
     * 
     * @param donationPaymentInitiatedBatch
     *            of type DonationPaymentInitiatedBatch object
     * @return booelan value
     * @throws exception
     *             if donation not processed
     */
    public boolean processDonationPaymentsInitiated(
            DonationPaymentInitiatedBatch donationPaymentInitiatedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processDonationPaymentsInitiated(donationPaymentInitiatedBatch);
    }

    /**
     * This method used to process the DonationPaymentsCollected.
     * 
     * @param donationPaymentCollectedBatch
     *            of type DonationPaymentCollectedBatch object
     * @return boolean value
     * @throws exception
     *             if donation is not collected
     */
    public boolean processDonationPaymentsCollected(
            DonationPaymentCollectedBatch donationPaymentCollectedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processDonationPaymentsCollected(donationPaymentCollectedBatch);
    }

    /**
     * This method used to process the GiftAidReceived.
     * 
     * @param giftAidReceivedBatch
     *            of type GiftAidReceivedBatch object
     * @return boolean value
     * @throws exception
     *             if gift aid received is not processed
     */
    public boolean processGiftAidReceived(
            GiftAidReceivedBatch giftAidReceivedBatch) throws GivingBatchException {
        return batchServiceProxy.processGiftAidReceived(giftAidReceivedBatch);
    }

    /**
     * This method used to process the GiftAidClaimed.
     * 
     * @param giftAidClaimedBatch
     *            of type GiftAidClaimedBatch object
     * @return status whether giftAidClaimed is processed or not
     * @throws exception
     *             if gift aid claimed is not processed
     */
    public boolean processGiftAidClaimed(GiftAidClaimedBatch giftAidClaimedBatch)
            throws GivingBatchException {
        return batchServiceProxy.processGiftAidClaimed(giftAidClaimedBatch);
    }

    /**
     * This method used to process the transactionChargePaid.
     * 
     * @param transactionChargePaidBatch
     *            of type TransactionChargePaidBatch object
     * @return status whether TransactionChargePaid is processed or not
     * @throws exception
     *             if Transaction charge paid is not processed
     */
    public boolean processTransactionChargePaid(
            TransactionChargePaidBatch transactionChargePaidBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processTransactionChargePaid(transactionChargePaidBatch);
    }

    /**
     * This method used to process the transactionFeePaid.
     * 
     * @param transactionFeePaidBatch
     *            of type TransactionFeePaidBatch object
     * @return status whether TransactionFeePaid is processed or not
     * @throws exception
     *             if Transaction fee paid is not processed
     */
    public boolean processTransactionFeesPaid(
            TransactionFeePaidBatch transactionFeePaidBatch) throws GivingBatchException {
        return batchServiceProxy
                .processTransactionFeesPaid(transactionFeePaidBatch);
    }

    /**
     * This method used to process the registerCharities status.
     * 
     * @param charitiesBatch
     *            of type CharitiesBatch object
     * @return status whether registerCharities is processed or not
     * @throws exception
     *             if register charities is not processed
     */
    public boolean registerCharities(CharitiesBatch charitiesBatch)
            throws GivingBatchException {
        return batchServiceProxy.registerCharities(charitiesBatch);
    }

    /**
     * This method used to process the EventRegistrationPaymentsCollected
     * status.
     * 
     * @param donationPaymentCollectedBatch
     *            of type DonationPaymentCollectedBatch object
     * @return status whether EventRegistrationPaymentsCollected is processed or
     *         not
     * @throws exception
     *             if EventRegistrationPaymentsCollected is not processed
     */
    public boolean processEventRegistrationPaymentsCollected(
            DonationPaymentCollectedBatch donationPaymentCollectedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processEventRegistrationPaymentsCollected(donationPaymentCollectedBatch);
    }

    /**
     * This method used to process the RegularDonationPaymentsCollected status.
     * 
     * @param donationPaymentCollectedBatch
     *            of type DonationPaymentCollectedBatch object
     * @return status whether RegularDonationPaymentsCollected is processed or
     *         not
     * @throws exception
     *             if RegularDonationPaymentsCollected is not processed
     */
    public boolean processRegularDonationPaymentsCollected(
            DonationPaymentCollectedBatch donationPaymentCollectedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processRegularDonationPaymentsCollected(donationPaymentCollectedBatch);
    }

    /**
     * Send new registration fee records to Oracle Financials.
     * 
     * @param registrationFeesBatch
     *            the new Registration Fees
     * @return true if processed ok
     * @throws Exception
     *             if there is a processing error
     */
    public boolean createRegistrationFees(
            RegistrationFeesBatch registrationFeesBatch) throws GivingBatchException {
        return batchServiceProxy.createRegistrationFees(registrationFeesBatch);
    }

    /**
     * Send TransactionReliefClaimed records to glis.
     * 
     * @param transitionalReliefClaimedBatch
     *            the records
     * @return true if processed ok
     * @throws Exception
     *             if there was an error
     */
    public boolean transitionalReliefClaimed(
            TransitionalReliefClaimedBatch transitionalReliefClaimedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .transitionalReliefClaimed(transitionalReliefClaimedBatch);
    }

    /**
     * Call processEvent on GLIS. The GLIS processEvent() method is used to tell
     * glis we want the results of some processing it should have done. The type
     * of processing depends on the parameters of the serviceEventProcessing
     * object sent with the request. GLIS should respond by sending a JMS that
     * will be picked up by the {@link GlisMessageDelegate} and used to invoke
     * another job.
     * 
     * @param domainEventProcessing
     * @return booelan value
     * @throws Exception
     */
    public boolean processEvent(EventProcessing eventProcessing)
            throws GivingBatchException {
        return batchServiceProxy.processEvent(eventProcessing);
    }

    /**
     * This method used to process the event registration status.
     * 
     * @param paymentInitiatedBatch
     *            of type DonationPaymentInitiatedBatch object
     * @return status whether event registration payment is processed or not
     * @throws exception
     *             if event registration payment is not processed
     */
    public boolean processEventRegistrationPaymentsInitiated(
            DonationPaymentInitiatedBatch paymentInitiatedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processEventRegistrationPaymentsInitiated(paymentInitiatedBatch);
    }

    /**
     * This method used to process the event registration status.
     * 
     * @param paymentInitiatedBatch
     *            of type DonationPaymentInitiatedBatch object
     * @return status whether event registration payment is processed or not
     * @throws exception
     *             if event registration payment is not processed
     */
    public boolean processFailedDonationPayments(
            FailedDonationPaymentBatch failedDonationPaymentBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processFailedDonationPayments(failedDonationPaymentBatch);
    }

    /**
     * This method used to process the event registration status.
     * 
     * @param paymentInitiatedBatch
     *            of type DonationPaymentInitiatedBatch object
     * @return status whether event registration payment is processed or not
     * @throws exception
     *             if event registration payment is not processed
     */
    public boolean processEventRegistrationPaymentsFailed(
            FailedDonationPaymentBatch failedDonationPaymentBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processEventRegistrationPaymentsFailed(failedDonationPaymentBatch);
    }

    /**
     * Transitional relief received.
     * 
     * @param transitionalReliefReceivedBatch the transitional relief received batch
     * 
     * @return true, if successful
     * 
     * @throws Exception the exception
     */
    public boolean transitionalReliefReceived(
            TransitionalReliefReceivedBatch transitionalReliefReceivedBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .transitionalReliefReceived(transitionalReliefReceivedBatch);
    }

    /**
     * This method used to process the failed web donation payments.
     * 
     * @param failedDonationPaymentBatch
     *            of type DonationPaymentInitiatedBatch object
     * @return status whether web donation payment is processed or not
     * @throws Exception
     *             if web donation payment is not processed
     */
    public boolean processWebDonationPaymentsFailed(
            FailedDonationPaymentBatch failedDonationPaymentBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .processWebDonationPaymentsFailed(failedDonationPaymentBatch);
    }


    /**
     * Waive registration fees.
     * 
     * @param waiveRegistrationFeesBatch the waive registration fees batch
     * 
     * @return true, if successful
     * 
     * @throws Exception the exception
     */
    public boolean waiveRegistrationFees(
            WaiveRegistrationFeesBatch waiveRegistrationFeesBatch)
            throws GivingBatchException {
        return batchServiceProxy
                .waiveRegistrationFees(waiveRegistrationFeesBatch);
    }


    /**
     * Maintain charities.
     * 
     * @param charitiesBatch the charities batch
     * 
     * @return true, if successful
     * 
     * @throws Exception the exception
     */
    public boolean maintainCharities(CharitiesBatch charitiesBatch)
            throws GivingBatchException {
        return batchServiceProxy.maintainCharities(charitiesBatch);
    }

    /**
     * Raise an alert when an exception is occured while processing.
     * 
     * @param jobName
     *            - job name
     * @param jobId
     *            - job id
     * @param ex
     *            - exception object
     * @throws Exception
     */
    public void raiseAlert(String jobName, String jobId, Exception ex)
            throws GivingBatchException {
        batchServiceProxy.raiseAlert(jobName, jobId, ex);
    }

    /**
     * Collects the Regular Donation.
     * 
     * @param activeCardRequest
     * @return booelan value
     * @throws Exception
     */
    public boolean makeRegularDonationWithActiveCard(
    		MakeDonationWithActiveCard activeCardRequest)
            throws GivingBatchException {
        
        return batchServiceProxy.makeRegularDonationWithActiveCard(activeCardRequest);

    }
    
    /**
     * To save regular donation through UCPHB0460.
     * @param donationRequest
     * @return booelan value
     * @throws Exception
     */
    public boolean saveRegularDonation(
			SaveRegularDonationRequest donationRequest)throws GivingBatchException {
    	return batchServiceProxy.saveRegularDonation(donationRequest);
    }
}