package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the EventRegistrationFeePaymentCollected batch.
 * Takes the payment object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class EventRegistrationFeePaymentCollectedItemProcessor implements
        ItemProcessor<Payment, Payment> {

    /** instance for logger. * */
    private static Logger LOGGER =
            Logger.getLogger(EventRegistrationFeePaymentCollectedItemProcessor.class);

    /** payment Service. * */
    private PaymentService paymentService;

    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /** {@inheritDoc} */
    public Payment process(Payment payment) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationFeePaymentCollectedItemProcessor.process - Start");
        }
                updateBatchStatus(payment.getId(),
                        MasterDataCodeConstants.PaymentStatusPending.EVENT_REG_FEE_PAYMENT_COLLECTED_PENDING.getCode());
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationFeePaymentCollectedItemProcessor.process - End");
        }
        return payment;

    }

    /**
     * The updateBatchStatus method calls paymentService and updates the
     * batch status to 'ERFPAYCOLL_PENDING'in the database in DB.
     * 
     * @param id of type Long.
     * @param status of type String.
     */
    public void updateBatchStatus(Long id, String status) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationFeePaymentCollectedItemProcessor.updateBatchStatus - Start");
        }
        paymentService.updateStatus(id, status);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationFeePaymentCollectedItemProcessor.updateBatchStatus - End");
        }
    }
}
