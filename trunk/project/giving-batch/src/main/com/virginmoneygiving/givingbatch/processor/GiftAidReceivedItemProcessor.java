package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the GiftAidReceivedItemProcessor batch.
 * Takes the GiftAidAmount object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class GiftAidReceivedItemProcessor implements
        ItemProcessor<GiftAidAmount, GiftAidAmount> {

    /** instance for logger. * */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidReceivedItemProcessor.class);

    /** payment Service. * */
    private PaymentService paymentService;

    /** {@inheritDoc} */
    public GiftAidAmount process(GiftAidAmount giftAidAmount) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidReceivedItemProcessor.process - Start");
        }
        updateBatchStatus(giftAidAmount.getPayment().getId(),
                Constant.GIFT_AID_STATUS_GIFT_AID_RECEIVED_PENDING);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidReceivedItemProcessor.process - End");
        }
        return giftAidAmount;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'GFTAIDRECD_PENDING'in the database in DB.
     * 
     * @param id of type Long
     * @param status of type String
     */
    public void updateBatchStatus(Long id, String status) {
        LOGGER.trace("GiftAidReceivedItemProcessor.updateBatchStatus - Start");

        paymentService.updateGiftAidStatus(id, status);

        LOGGER.trace("GiftAidReceivedItemProcessor.updateBatchStatus - End");
    }

    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
