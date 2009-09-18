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
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaid;
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaidBatch;

/**
 * This class is used to make a gliss call for TransactionChargePaid.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class TransactionChargePaidUploadListener extends
    StepExecutionListenerSupport implements ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(TransactionChargePaidUploadListener.class);

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
        LOGGER
            .info("inside TransactionChargePaidUploadListener afterWrite method -Start");

        TransactionChargePaidBatch transactionChargePaidBatch =
            new TransactionChargePaidBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        if (executionContext != null) {
            List<TransactionChargePaid> transactionChargePaidList =
                (List<TransactionChargePaid>) executionContext
                    .get(Constant.TRANSACTION_CHARGE_PAID_OBJECT_LIST);
            transactionChargePaidBatch
                .setTransactionCharge(transactionChargePaidList);
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
        }
        
        Batch batch = Util.getBatchObject(Constant.TRANSACTION_CHARGE_PAID, batchNumber);
        transactionChargePaidBatch.setBatch(batch);
        MessageHeader messageHeader = Util.getMessageHeaderObject(Constant.TRANSACTION_CHARGE_PAID);
        transactionChargePaidBatch.setMessageHeader(messageHeader);

        try {
            batchDelegate
                .processTransactionChargePaid(transactionChargePaidBatch);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        LOGGER
            .info("inside TransactionChargePaidUploadListener afterWrite method -End");

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
