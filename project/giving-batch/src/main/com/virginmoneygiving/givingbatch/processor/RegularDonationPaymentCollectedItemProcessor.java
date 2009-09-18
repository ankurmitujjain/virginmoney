package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the RegularDonationPaymentCollectedItemProcessor batch.
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class RegularDonationPaymentCollectedItemProcessor implements
        ItemProcessor<Payment, Payment> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(RegularDonationPaymentCollectedItemProcessor.class);
    
    /** payment service. * */
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
            LOGGER.trace("RegularDonationPaymentCollectedItemProcessor.process - Start");
        }
        updateBatchStatus(payment.getId(),
                MasterDataCodeConstants.PaymentStatusPending.WEB_DONATION_PAYMENT_COLLECTED_PENDING.getCode());
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("RegularDonationPaymentCollectedItemProcessor.process - End");
        }
        return payment;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'WEBPAYCOLL_PENDING'in the database in DB.
     * 
     * @param id of type Long.
     * @param status of type String.
     */
    public void updateBatchStatus(Long id, String status) {
        paymentService.updateStatus(id, status);
    }

}
