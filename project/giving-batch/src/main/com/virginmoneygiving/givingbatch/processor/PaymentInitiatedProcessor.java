package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the PaymentInitiatedProcessor batch.
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class PaymentInitiatedProcessor implements
        ItemProcessor<Payment, Payment>, StepExecutionListener {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(PaymentInitiatedProcessor.class);
    
    /** payment service. * */
    private PaymentService paymentService;

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;
    
    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;


    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * {@inheritDoc}
     */
    public Payment process(Payment payment) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("PaymentInitiatedProcessor.process - Start");
        }
        updateBatchStatus(payment.getId(),
                MasterDataCodeConstants.PaymentStatusPending.WEB_DONATION_PAYMENT_INITIATED_PENDING.getCode());
        if(LOGGER.isTraceEnabled()) {       
            LOGGER.trace("PaymentInitiatedProcessor.process - End");
        }
        return payment;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param id of type Long.
     * @param status of type String.
     */
    public void updateBatchStatus(Long id, String status) {
        LOGGER.trace("PaymentInitiatedProcessor.updateBatchStatus - Start");
        paymentService.updateStatus(id, status);
        LOGGER.trace("PaymentInitiatedProcessor.updateBatchStatus - End");
    }


    /** {@inheritDoc} **/
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.trace("inside afterStep method ");
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} **/
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.trace("inside before step  execution context:" + stepExecution);
        this.stepExecution = stepExecution;

    }

}
