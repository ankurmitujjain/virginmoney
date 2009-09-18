package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the RegularDonationTransactionChargePaidItemProcessor batch.
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class RegularDonationTransactionChargePaidItemProcessor implements
        ItemProcessor<Payment, Payment> {

    /** The LOGGER. */
    private static Logger LOGGER =
            Logger
                    .getLogger(RegularDonationTransactionChargePaidItemProcessor.class);

    /** The payment service. */
    private PaymentService paymentService;

    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /** {@inheritDoc} **/
    public Payment process(Payment payment) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("RegularDonationTransactionChargePaidItemProcessor.process - Start");
        }
        updateBatchStatus(payment.getId(),
                Constant.DON_PAYMENT_TRANSACTION_CHARGE_COLLECTED_PROCESSED);
        if(LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("RegularDonationTransactionChargePaidItemProcessor.process - End");
        }
        return payment;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param id the id
     * @param status the status
     */
    public void updateBatchStatus(Long id, String status) {
        paymentService.updateStatusCardCharge(id, status);
    }

}
