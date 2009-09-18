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
import com.virginmoneygiving.givingbatch.domain.GiftAidReceived;
import com.virginmoneygiving.givingbatch.domain.GiftAidReceivedBatch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;

/**
 * This class is used to make a gliss call for GiftAidReceived job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class GiftAidReceivedUploadListener extends StepExecutionListenerSupport
    implements ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(GiftAidReceivedUploadListener.class);

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
     * @param list not used
     */
    @SuppressWarnings("unchecked")
    public void afterWrite(List ignored) {

        GiftAidReceivedBatch giftAidReceivedBatch = new GiftAidReceivedBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        if (executionContext != null) {
            List<GiftAidReceived> giftAidReceivedList =
                (List<GiftAidReceived>) executionContext
                    .get(Constant.GIFTAID_RECEIVED_OBJECT_LIST);
            if (giftAidReceivedList != null) {
                giftAidReceivedBatch.setGiftAidReceived(giftAidReceivedList);
            }
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
        }
        Batch batch = Util.getBatchObject(Constant.GIFT_AID_RECEIVED, batchNumber);
        giftAidReceivedBatch.setBatch(batch);
        MessageHeader messageHeader = Util.getMessageHeaderObject(Constant.GIFT_AID_RECEIVED);
        giftAidReceivedBatch.setMessageHeader(messageHeader);

        try {
            batchDelegate.processGiftAidReceived(giftAidReceivedBatch);
        } catch (Exception exception) {
            LOGGER.error("In afterWrite of GiftAidReceivedUploadListener :" +exception);
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
