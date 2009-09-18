package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.FailedDonationPaymentBatch;
import com.virginmoneygiving.givingbatch.domain.FailedPayment;

/**
 * This class is used to make a gliss call for EventRegistrationPaymentFailed job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Yogesh Salunkhe
 */
public class EventRegistrationPaymentFailedUploadTasklet extends
    StepExecutionListenerSupport implements Tasklet {

    /** Create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(EventRegistrationPaymentFailedUploadTasklet.class);

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
     * @param stepContribution of type StepContribution
     * @param chunkContext the chunk context
     * 
     * @return Status of the Step
     * 
     * @throws exception if step is not executed properly
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public RepeatStatus execute(StepContribution stepContribution,
            ChunkContext chunkContext) throws Exception {
        if (LOGGER.isTraceEnabled()) {
            LOGGER
                .trace("Inside EventRegistrationPaymentFailedUploadTasklet execute method -START");
        }

        FailedDonationPaymentBatch failedDonationPaymentBatch =
            new FailedDonationPaymentBatch();
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);
        if (executionContext != null) {
            List<FailedPayment> eventRegistrationPaymentFailedList =
                (List<FailedPayment>) executionContext
                    .get(Constant.EVENT_REGISTRATION_PAYMENT_OBJECT_LIST);
            if (eventRegistrationPaymentFailedList != null) {
                failedDonationPaymentBatch
                    .setFailedPayment(eventRegistrationPaymentFailedList);
            }
        }
        batchDelegate
            .processEventRegistrationPaymentsFailed(failedDonationPaymentBatch);
        if (LOGGER.isTraceEnabled()) {
            LOGGER
                .trace("Inside EventRegistrationPaymentFailedUploadTasklet execute method -END");
        }
        return RepeatStatus.FINISHED;
    }

}
