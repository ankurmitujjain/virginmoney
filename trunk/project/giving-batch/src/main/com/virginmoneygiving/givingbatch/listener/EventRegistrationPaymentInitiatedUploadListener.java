package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;

/**
 * This class is used to make a gliss call for EventRegistrationPaymentInitiated job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Yogesh Salunkhe
 */
public class EventRegistrationPaymentInitiatedUploadListener extends
    StepExecutionListenerSupport implements ItemWriteListener {

    /** Create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(EventRegistrationPaymentInitiatedUploadListener.class);

    /** Create instance of batch delegate to inject inside xml. */
    private BatchDelegate batchDelegate;

    /** Create instance of stepExecution. */
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
     * This is used for to execute the step and call the delegate after that it
     * returns status.
     * 
     * @param ignored not used
     */
    @SuppressWarnings("unchecked")
    public void afterWrite(List ignored) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationPaymentInitiatedUploadListener::afterWrite() - START");
        }

        DonationPaymentInitiatedBatch paymentInitiatedBatch =
            new DonationPaymentInitiatedBatch();
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);
        if (executionContext != null) {
            List<PaymentInitiated> paymentInitiatedList =
                (List<PaymentInitiated>) executionContext
                    .get(Constant.PAYMENT_INITIATED_OBJECT_LIST);
            if (paymentInitiatedList != null) {
                paymentInitiatedBatch.setPaymentInitiated(paymentInitiatedList);
            }
        }
        try {
            batchDelegate
                .processEventRegistrationPaymentsInitiated(paymentInitiatedBatch);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationPaymentInitiatedUploadListener::afterWrite() - END");
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
     * @param stepExecution not used
     * @param throwable exception thrown
     * 
     * @return FAILED status
     */
    public ExitStatus onErrorInStep(StepExecution stepExecution, Throwable throwable) {

        throwable.toString();
        return ExitStatus.FAILED;

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
