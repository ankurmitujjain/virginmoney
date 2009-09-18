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
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimedBatch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.givingbatch.domain.Transaction;

/**
 * This class is used to make a gliss call for GiftAidClaimed.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class GiftAidClaimedUploadListener extends StepExecutionListenerSupport
        implements ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
            Logger.getLogger(GiftAidClaimedUploadListener.class);

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
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedUploadListener::afterWrite() - START");
        }

        GiftAidClaimedBatch giftAidClaimedBatch = new GiftAidClaimedBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);
        if (executionContext != null) {
            List<Transaction> giftAidClaimedList =
                    (List<Transaction>) executionContext
                            .get(Constant.GIFTAID_CLAIMED_OBJECT_LIST);
            giftAidClaimedBatch.setGiftAidClaimed(giftAidClaimedList);

            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("GiftAidClaimedUploadListener:*******BATCH NUMBER ********* :" + batchNumber);
            }
        }

        Batch batch =
                Util.getBatchObject(Constant.GIFT_AID_CLAIMED, batchNumber);
        giftAidClaimedBatch.setBatch(batch);
        MessageHeader messageHeader =
                Util.getMessageHeaderObject(Constant.GIFT_AID_CLAIMED);
        giftAidClaimedBatch.setMessageHeader(messageHeader);

        try {
            batchDelegate.processGiftAidClaimed(giftAidClaimedBatch);
        }
        catch (Exception exception ) {
            throw new RuntimeException(exception);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedUploadListener::afterWrite() - END");
        }
    }

    /**
     * Unimplemented.
     * 
     * @param arg0 not used
     */
    public void beforeWrite(List arg0) {
    }

    /**
     * Unimplmented.
     * 
     * @param arg0 not used
     * @param arg1 not used
     */
    public void onWriteError(Exception arg0, List arg1) {
    }
}
