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
import com.virginmoneygiving.givingbatch.domain.RegistrationFee;
import com.virginmoneygiving.givingbatch.domain.RegistrationFeesBatch;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;

/**
 * Send new Charity registration fees to GLIS for CreateRegistrationFees job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Ian Priest
 */
public class CreateRegistrationFeesUploadListener extends
        StepExecutionListenerSupport implements ItemWriteListener<Object> {

    /** create instance of logger. */
    private static final Logger LOGGER =
            Logger.getLogger(CreateRegistrationFeesUploadListener.class);

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
     * Before step.
     * 
     * @param stepExecution of type StepExecution
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * Send updates to GLIS. If the write succeeds, the updates are sent to GLIS
     * here. If GLIS returns an error we can throw an exception and cause the
     * entire step to rollback, ensuring that the database records are returned
     * to their previous state.
     * 
     * @param ignored An ignored interface parameter
     */
    @SuppressWarnings("unchecked")
    public void afterWrite(List<? extends Object> ignored) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside CreateRegistrationFeesUploadListener afterWrite method - Start");
        }
        RegistrationFeesBatch registrationFeesBatch =
                new RegistrationFeesBatch();
        String batchNumber = null;
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);

        if (executionContext != null) {
            List<RegistrationFee> registrationFeeList =
                    (List<RegistrationFee>) executionContext
                            .get(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST);
            if (registrationFeeList != null) {
                registrationFeesBatch.setRegistrationFee(registrationFeeList);
            }
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
        registrationFeesBatch.setBatch(batch);
        MessageHeader messageHeader =
                Util
                        .getMessageHeaderObject(MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED
                                .getCode());
        registrationFeesBatch.setMessageHeader(messageHeader);
        /*
         * Just re-throw any exception as unchecked to ensure a transaction
         * rollback.
         */
        try {
            this.batchDelegate.createRegistrationFees(registrationFeesBatch);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside CreateRegistrationFeesUploadListener afterWrite method - End");
        }
    }

    /**
     * Unimplemented.
     * 
     * @param ignored not used
     */
    public void beforeWrite(List<? extends Object> ignored) {
        // not used
    }

    /**
     * Unimplemented.
     * 
     * @param exception not used
     * @param list not used
     */
    public void onWriteError(Exception exception, List<? extends Object> list) {
        // Ignored
    }

}
