package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.Batch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFee;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFeesBatch;
import com.virginmoneygiving.givingbatch.processor.WaiveCharityRegFeeItemProcessor;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;

/**
 * This class is used to make a gliss call for WaiveCharityRegFeeUpload job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class WaiveCharityRegFeeUploadTasklet extends
        StepExecutionListenerSupport implements
        ItemWriteListener<WaiveRegistrationFee> {

    /** create Logger instance for to log the information. */
    private static Logger LOGGER =
            Logger.getLogger(WaiveCharityRegFeeItemProcessor.class);

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
     * Call GLIS Service.
     * 
     * Will raise runtime exception to ensure transaction rollback if a
     * GLIS error is returned.
     * 
     * @param list not used
     */
    public void afterWrite(List<? extends WaiveRegistrationFee> list) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside WaiveCharityRegFeeUploadTasklet afterWrite method - Start");
        }
        WaiveRegistrationFeesBatch waiveRegistrationFeesBatch =
                new WaiveRegistrationFeesBatch();
        String batchNumber = null;              // Null check 13051 ::TarunA
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);
        if (executionContext != null) {
            List<WaiveRegistrationFee> waiveRegFeeList =
                    (List<WaiveRegistrationFee>) executionContext
                            .get(Constant.WAIVE_REG_FEE_OBJECT_LIST);
            waiveRegistrationFeesBatch.setRegistrationFee(waiveRegFeeList);
            
            batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
            }
            
        }

        Batch batch =
                Util
                        .getBatchObject(
                                MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED
                                        .getCode(), batchNumber);
        waiveRegistrationFeesBatch.setBatch(batch);
        MessageHeader messageHeader =
                Util
                        .getMessageHeaderObject(MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED
                                .getCode());
        waiveRegistrationFeesBatch.setMessageHeader(messageHeader);
        /*
         * Just re-throw any exception as unchecked to ensure a transaction
         * rollback.
         */

        try {
            batchDelegate.waiveRegistrationFees(waiveRegistrationFeesBatch);
        }
        catch (Exception exception) {
            throw new RuntimeException();
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside WaiveCharityRegistrationUploadTasklet afterWrite method- End");
        }
    }

    /** {@inheritDoc} */
    public void beforeWrite(List<? extends WaiveRegistrationFee> ignored) {

    }

    /** {@inheritDoc} */
    public void onWriteError(Exception exception,
            List<? extends WaiveRegistrationFee> ignored) {

    }

    /**
     * On error in step.
     * 
     * @param stepExecution the step execution
     * @param throwable the throwable
     * 
     * @return the exit status
     */
    public ExitStatus onErrorInStep(StepExecution stepExecution, Throwable throwable) {

        throwable.toString();
        return ExitStatus.FAILED;

    }

    /** {@inheritDoc} */
    public ExitStatus afterStep(StepExecution stepExecution) {

        LOGGER.trace("Inside afterStep method ");
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;

    }

}
