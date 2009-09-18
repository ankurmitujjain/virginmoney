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
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimed;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimedBatch;

/**
 * Send Transitional Relief fees to GLIS for TransitionalReliefClaimed job .
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Ian Priest
 */
public class TransitionalReliefClaimedUploadListener extends StepExecutionListenerSupport
        implements ItemWriteListener<Object> {

    /** create instance of logger. */
    private static final Logger LOGGER =
            Logger.getLogger(TransitionalReliefClaimedUploadListener.class);

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
    @SuppressWarnings({ "unchecked", "unchecked" })
    public void afterWrite(List< ? extends Object> ignored) {
        TransitionalReliefClaimedBatch transitionalReliefClaimedBatch = new TransitionalReliefClaimedBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);

        if (executionContext != null) {
            List<TransitionalReliefClaimed> transitionalReliefClaimedList =
                    (List<TransitionalReliefClaimed>) executionContext
                            .get(Constant.TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST);
            if (transitionalReliefClaimedList != null) {
                transitionalReliefClaimedBatch
                        .setTransitionalReliefClaimed(transitionalReliefClaimedList);
            }
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
        }
        Batch batch = Util.getBatchObject(Constant.TRANSITIONAL_RELIEF_CLAIMED, batchNumber);
        transitionalReliefClaimedBatch.setBatch(batch);
        MessageHeader messageHeader = Util.getMessageHeaderObject(Constant.TRANSITIONAL_RELIEF_CLAIMED);
        transitionalReliefClaimedBatch.setMessageHeader(messageHeader);

        /*
         * Re-throw any error from GLIS as a RuntimeException to ensure
         * transaction rollback.
         */
        try {
            batchDelegate.transitionalReliefClaimed(transitionalReliefClaimedBatch);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
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
