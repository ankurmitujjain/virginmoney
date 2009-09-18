package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.Batch;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollected;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollectedBatch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;

/**
 * This class is used to make a gliss call for RegularDonationPaymentCollected job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class RegularDonationPaymentCollectedUploadListener extends
    StepExecutionListenerSupport implements ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(RegularDonationPaymentCollectedUploadListener.class);

    /** create instance of batch delegate to inject inside xml. */
    private BatchDelegate batchDelegate;

    /** create instance of stepExecution. */
    private StepExecution stepExecution;

    /**
     * Sets the batch delegate.
     * 
     * @param batchDelegate the batchDelegate to set
     */
    public void setBatchDelegate(BatchDelegate batchDelegate) {
        this.batchDelegate = batchDelegate;
    }

    /**
     * This method will assign the step execution context whenever transformer
     * called.
     * 
     * @param stepExecution of tyep StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * Call GLIS Service.
     * 
     * Will raise runtime exception to ensure transaction rollback if a
     * GLIS error is returned.
     * 
     * @param ignored not used
     */
    @SuppressWarnings("unchecked")
    public void afterWrite(List ignored) {
        LOGGER
            .info("Inside RegularDonationPaymentCollectedUploadListener afterWrite method -Start");

        DonationPaymentCollectedBatch donationPaymentCollectedBatch =
            new DonationPaymentCollectedBatch();
        String batchNumber = null;                  // Null check 13051 :: TarunA
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);
        if (executionContext != null) {
            List<DonationPaymentCollected> paymentCollectedList =
                (List<DonationPaymentCollected>) executionContext
                    .get(Constant.REGULAR_DONATION_PAYMENT_COLLECTED_OBJECT_LIST);
            if (paymentCollectedList != null) {
                donationPaymentCollectedBatch
                    .setWebDonationPayments(paymentCollectedList);
            }
            
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
        }
        Batch batch = Util.getBatchObject(Constant.REGULAR_DONATION_PAYMENT_COLLECTED, batchNumber);
        donationPaymentCollectedBatch.setBatch(batch);
        MessageHeader messageHeader = Util.getMessageHeaderObject(Constant.REGULAR_DONATION_PAYMENT_COLLECTED);
        donationPaymentCollectedBatch.setMessageHeader(messageHeader);

        try {
            batchDelegate
                .processRegularDonationPaymentsCollected(donationPaymentCollectedBatch);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        LOGGER
            .info("Inside RegularDonationPaymentCollectedUploadListener afterWrite method -Stop");
    }

    /**
     * Unimplemented.
     * 
     * @param ignored not used
     */
    public void beforeWrite(List ignored) {
    }

    /**
     * Unimplemented.
     * 
     * @param exception not used
     * @param list not used
     */
    public void onWriteError(Exception exception, List list) {
    }
}
