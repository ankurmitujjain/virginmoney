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
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceived;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceivedBatch;

/**
 * Send Transitional Relief fees to GLIS for TransitionalReliefReceivedUpload job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Ian Priest
 */
public class TransitionalReliefReceivedUploadListener extends StepExecutionListenerSupport
        implements ItemWriteListener<Object> {

    /** create instance of logger. */
    private static final Logger LOGGER =
            Logger.getLogger(TransitionalReliefReceivedUploadListener.class);

    /** create instance of batch delegate. */
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
     * @param stepExecution of type StepExecution
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
    public void afterWrite(List< ? extends Object> ignored) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside TransitionalReliefReceivedTasklet afterWrite method - Start");
        }
        TransitionalReliefReceivedBatch transitionalReliefReceivedBatch = new TransitionalReliefReceivedBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);

        if (executionContext != null) {
            List<TransitionalReliefReceived> transitionalReliefReceivedList =
                    (List<TransitionalReliefReceived>) executionContext
                            .get(Constant.TRANSITIONAL_RELIEF_RECEIVED_OBJECT_LIST);
            if (transitionalReliefReceivedList != null) {
                transitionalReliefReceivedBatch
                        .setTransitionalReliefReceived(transitionalReliefReceivedList);
            }
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
        }

        
        Batch batch = Util.getBatchObject(Constant.TRANSITIONAL_RELIEF_RECEIVED, batchNumber);
        transitionalReliefReceivedBatch.setBatch(batch);
        MessageHeader messageHeader = Util.getMessageHeaderObject(Constant.TRANSITIONAL_RELIEF_RECEIVED);
        transitionalReliefReceivedBatch.setMessageHeader(messageHeader);

        /*
         * Call GLIS. Just re-throw any error as unchecked to ensure the
         * transaction rollback happens.
         */
        try {
            this.batchDelegate.transitionalReliefReceived(transitionalReliefReceivedBatch);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside TransitionalReliefReceivedTasklet afterWrite method - End");
        }
    }

    /**
     * Unimplemented.
     * 
     * @param ignored not used
     */
    public void beforeWrite(List< ? extends Object> ignored) {
    }

    /**
     * Unimplemented.
     * 
     * @param exception not used
     * @param list not used
     */
    public void onWriteError(Exception exception, List< ? extends Object> list) {
    }

}
