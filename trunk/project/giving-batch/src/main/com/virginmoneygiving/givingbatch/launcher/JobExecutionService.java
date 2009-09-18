package com.virginmoneygiving.givingbatch.launcher;

import java.util.Map;

import org.springframework.batch.core.configuration.JobLocator;

import com.virginmoney.glis.messages.CollectedRegistrationFeeBatch;
import com.virginmoney.glis.messages.SettledCharityPaymentsBatch;

/**
 * Interface for JOB Execution service.
 * 
 * @author henryj
 */
public interface JobExecutionService {

    /** The Constant JOB_NAME. */
    public static final String JOB_NAME = "jobName";

    /** The Constant JOB_NAME_BATCH_START. */
    public static final String JOB_NAME_BATCH_START = "START-BATCH-PROCESS";

    /**
     * Sets the job locator.
     * 
     * @param jobLocator the job locator
     */
    public void setJobLocator(JobLocator jobLocator);

    /**
     * execute job method.
     * 
     * @param jobParameters TODO
     */
    public void executeJob(Map<String, Object> jobParameters);

    /**
     * Execute a RegistrationFeeCollected.
     * 
     * @param collectedRegistrationFeeBatch the collected registration fee batch
     */
    public void executeCollectedRegistrationFeeJob(CollectedRegistrationFeeBatch collectedRegistrationFeeBatch);
    
    /**
     * Execute collected donation payment job.
     * 
     * @param donationPaymentCollectedBatch the donation payment collected batch
     */
    public void executeCollectedDonationPaymentJob(SettledCharityPaymentsBatch donationPaymentCollectedBatch);
    
    /**
     * Execute collected regular donation payment job.
     * 
     * @param regularDonationPaymentCollectedBatch the regular donation payment collected batch
     */
    public void executeCollectedRegularDonationPaymentJob(SettledCharityPaymentsBatch regularDonationPaymentCollectedBatch);
    
    /**
     * Execute collected event registration fee job.
     * 
     * @param eventRegistrationFeePaidBatch the event registration fee paid batch
     */
    public void executeCollectedEventRegistrationFeeJob(SettledCharityPaymentsBatch eventRegistrationFeePaidBatch);
    
    /**
     * Execute paid transaction charge job.
     * 
     * @param transactionChargePaidBatch the transaction charge paid batch
     */
    public void executePaidTransactionChargeJob(SettledCharityPaymentsBatch transactionChargePaidBatch);
    
    /**
     * Execute paid transaction fee job.
     * 
     * @param transactionFeePaidBatch the transaction fee paid batch
     */
    public void executePaidTransactionFeeJob(SettledCharityPaymentsBatch transactionFeePaidBatch);
    
    /**
     * Execute received gift aid job.
     * 
     * @param giftAidReceivedBatch the gift aid received batch
     */
    public void executeReceivedGiftAidJob(SettledCharityPaymentsBatch giftAidReceivedBatch);
    
    /**
     * Execute received transitional relief job.
     * 
     * @param transitionalReliefReceivedBatch the transitional relief received batch
     */
    public void executeReceivedTransitionalReliefJob(SettledCharityPaymentsBatch transitionalReliefReceivedBatch);
    
    /**
     * Execute settled payment.
     * 
     * @param paymentSettledBatch the payment settled batch
     */
    public void executeSettledPayment(SettledCharityPaymentsBatch paymentSettledBatch);
}
