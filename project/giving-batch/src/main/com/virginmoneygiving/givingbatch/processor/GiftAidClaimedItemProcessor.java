package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the GiftAidClaimedItemProcessor batch.
 * Takes the GiftAidAmount object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class GiftAidClaimedItemProcessor implements
        ItemProcessor<GiftAidAmount, GiftAidAmount> {

    /** instance for logger. * */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidClaimedItemProcessor.class);

    /** payment Service. * */
    private PaymentService paymentService;

    /** {@inheritDoc} */
    public GiftAidAmount process(GiftAidAmount giftAidAmount) throws GivingBatchException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimedItemProcessor.process - Start: " + giftAidAmount.getId());
        }
        updateBatchStatus(giftAidAmount.getPayment().getId(),
                Constant.GIFT_AID_STATUS_GIFT_AID_CLAIMED_PENDING);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimedItemProcessor.process - End: " + giftAidAmount.getId());
        }
        return giftAidAmount;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'GFTADCLAIM_PENDING'in the database in DB.
     * 
     * @param id the id
     * @param status the status
     */
    public void updateBatchStatus(Long id, String status) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedItemProcessor.updateBatchStatus - Start");
        }

        paymentService.updateGiftAidStatus(id, status);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedItemProcessor.updateBatchStatus - End");
        }
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
