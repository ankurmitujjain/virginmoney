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
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaid;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaidBatch;

/**
 * This class is used to make a gliss call for RegularDonationTransactionFeePaid job.
 * In case of failure entire batch operation will be roll back.
 *
 * @author Tarun Adiwal
 */
public class RegularDonationTransactionFeePaidUploadListener extends
    StepExecutionListenerSupport implements ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(RegularDonationTransactionFeePaidUploadListener.class);

    /** create instance of batch delegate to injectinside xml. */
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
    public void afterWrite(List ignored) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                .trace("inside RegularDonationTransactionFeePaidUploadListener afterWrite method -Start");
        }
        TransactionFeePaidBatch transactionFeePaidBatch =
            new TransactionFeePaidBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        if (executionContext != null) {
            List<TransactionFeePaid> transactionFeePaidList =
                (List<TransactionFeePaid>) executionContext
                    .get(Constant.REGULAR_DONATION_TRANSACTION_FEE_PAID_OBJECT_LIST);
            transactionFeePaidBatch.setTransactionFee(transactionFeePaidList);
            
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
        }
        
        Batch batch = Util.getBatchObject(Constant.REGULAR_DONATION_TRANSACTION_FEE_PAID, batchNumber);
        transactionFeePaidBatch.setBatch(batch);
        MessageHeader messageHeader = Util.getMessageHeaderObject(Constant.REGULAR_DONATION_TRANSACTION_FEE_PAID);
        transactionFeePaidBatch.setMessageHeader(messageHeader);

        try {
            batchDelegate.processTransactionFeesPaid(transactionFeePaidBatch);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                .trace("inside RegularDonationTransactionFeePaidUploadListener afterWrite method -End");
        }
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
