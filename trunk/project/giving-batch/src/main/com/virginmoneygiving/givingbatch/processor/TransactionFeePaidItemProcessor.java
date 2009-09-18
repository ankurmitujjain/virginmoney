package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the TransactionFeePaidItemProcessor batch.
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class TransactionFeePaidItemProcessor implements
        ItemProcessor<Payment, Payment> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(TransactionFeePaidItemProcessor.class);

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
            LOGGER.trace("TransactionFeePaidItemProcessor.process - Start");
        }
        updateBatchStatus(payment.getId(),
        		MasterDataCodeConstants.VMGTranscationFee.VMG_TRASACTION_FEE_PAID.getCode());
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TransactionFeePaidItemProcessor.process - End");
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
        paymentService.updateStatusFeeCharge(id, status);
    }

}
