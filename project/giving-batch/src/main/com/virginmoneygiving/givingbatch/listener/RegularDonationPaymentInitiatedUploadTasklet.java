package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.AttributeAccessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * This class is used to make a gliss call for RegularDonationPaymentInitiated job.
 * In case of failure entire batch operation will be roll back.
 *
 * @author Yogesh Salunkhe
 */
public class RegularDonationPaymentInitiatedUploadTasklet extends StepExecutionListenerSupport
         {

    /** Create instance of logger. */
    private static final Logger LOGGER =
            Logger.getLogger(RegularDonationPaymentInitiatedUploadTasklet.class);

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
     * Call GLIS Service.
     * 
     * Will raise runtime exception to ensure transaction rollback if a
     * GLIS error is returned.
     * 
     * @param list not used
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        DonationPaymentInitiatedBatch donationPaymentInitiatedBatch =
            new DonationPaymentInitiatedBatch();
        ExecutionContext executionContext =
                stepExecution.getExecutionContext();

        return super.afterStep(stepExecution);
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
     * @param attributeAccessor of type AttributeAccessor
     * 
     * @return Status of the Step
     * 
     * @throws exception if step is not executed properly
     * @throws Exception the exception
     */
    public RepeatStatus execute(StepContribution stepContribution,
            AttributeAccessor attributeAccessor)throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside RegularDonationPaymentInitiatedUploadTasklet execute method -Start");
        }

        DonationPaymentInitiatedBatch donationPaymentInitiatedBatch =
                new DonationPaymentInitiatedBatch();
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);
        if (executionContext != null) {
            List<PaymentInitiated> paymentInitiatedList =
                    (List<PaymentInitiated>) executionContext
                            .get(Constant.PAYMENT_INITIATED_OBJECT_LIST);
            if (paymentInitiatedList != null) {
                donationPaymentInitiatedBatch
                        .setPaymentInitiated(paymentInitiatedList);
            }
        }
        batchDelegate
                .processDonationPaymentsInitiated(donationPaymentInitiatedBatch);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside PaymentInitiatedUploadTasklet execute method -End");
        }
        return RepeatStatus.FINISHED;
    }

}
