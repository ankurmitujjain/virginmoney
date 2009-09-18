package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for Regular Donation Payment Failed batch.
 * This class takes the object returned by the ItemReader creates the
 * RegularDonationPaymentFailedRecord, update the process status in database and
 * pass the RegularDonationPaymentFailedRecord to ItemWriter
 * 
 * @author Yogesh Salunkhe
 */
public class RegularDonationPaymentFailedProcessor implements
        ItemProcessor<Payment, Payment> {

    /** Instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(RegularDonationPaymentFailedProcessor.class);
    
    /** Payment service. * */
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
            LOGGER.trace("RegularDonationPaymentFailedProcessor.process - Start");
        }
        updateBatchStatus(payment.getId(),
        		MasterDataCodeConstants.PaymentStatusPending.WEB_DONATION_PAYMENT_FAILED_PENDING.getCode());
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("RegularDonationPaymentFailedProcessor.process - End");
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
        paymentService.updateStatus(id, status);
    }

}
