package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the EventRegistrationTransactionFeePaidItemProcessor batch.
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class EventRegistrationTransactionFeePaidItemProcessor implements
        ItemProcessor<Payment, Payment> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger
                    .getLogger(EventRegistrationTransactionFeePaidItemProcessor.class);

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
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationTransactionFeePaidItemProcessor::process() - START");
        }
        updateBatchStatus(payment.getId(),
                Constant.ERF_PAYMENT_TRANSACTION_FEE_PAID_PROCESSED);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationTransactionFeePaidItemProcessor::process() - END");
        }
        return payment;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'TRNFEEPAID_PENDING'in the database in DB.
     * 
     * @param id of type Long.
     * @param status of type String.
     */
    public void updateBatchStatus(Long id, String status) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationTransactionFeePaidItemProcessor::updateBatchStatus() - START");
        }
        paymentService.updateStatusFeeCharge(id, status);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationTransactionFeePaidItemProcessor::updateBatchStatus() - END");
        }
    }

}
