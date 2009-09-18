package com.virginmoneygiving.givingbatch.processor;

import javax.annotation.Resource;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoney.glis.messages.GiftAidReceived;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.PaymentStatus;
import com.virginmoneygiving.payment.service.PaymentService;
//TODO- This class is not being used and need to be removed.

/**
 * This class is an Item Processor for the CollectedRegistrationFees batch.
 * 
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * 
 * TODO: does it need to change the db status?
 * 
 * @author ian priest
 */
public class GiftAidReceivedProcessor implements
        ItemProcessor<GiftAidReceived, GiftAidReceived> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidReceivedProcessor.class);
    
    /** payment service. * */
    private PaymentService paymentService;

    /** Mapper. */
    private DozerBeanMapper dozer;
    
    /**
     * TODO Alert service.
     * 
     * @param dozer the dozer
     */
    
    @Resource
	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	/**
	 * Sets the payment service.
	 * 
	 * @param paymentService the paymentService to set
	 */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /** {@inheritDoc} */
    public GiftAidReceived process(GiftAidReceived paymentCollected) throws GlisServiceException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("CreateRegistrationFeesProcessor.process - Start");
        }
        /*
         * Try to find the payment record for the collected payment.
         * Will throw an exception and force a rollback if it fails
         */
        Payment payment = getPayment(paymentCollected);
        
        /*
         * Update it's status
         */
        updatePaymentBatchStatus(payment);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("CreateRegistrationFeesProcessor.process - End");
        }
        return paymentCollected;

    }

    /**
     * Process.
     * 
     * @param giftAidAmount of type GiftAidAmount.
     * 
     * @return GiftAidAmount object.
     * 
     * @throws Exception object.
     */
    public GiftAidAmount process(GiftAidAmount giftAidAmount) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedItemProcessor.process - Start");
        }
        updateGiftAidBatchStatus(giftAidAmount.getPayment().getId(),
                Constant.GIFT_AID_STATUS_GIFT_AID_CLAIMED);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedItemProcessor.process - End");
        }
        return giftAidAmount;

    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param id the id
     * @param status the status
     */
    public void updateGiftAidBatchStatus(Long id, String status) {

        paymentService.updateGiftAidStatus(id, status);

    }

    /**
     * Get the payment from the database if it can be mapped.
     * 
     * Note the returned payment is mapped from the discovered on, so is completely
     * disconnected from hibernate.
     * 
     * @param paymentCollected the payment collected
     * 
     * @return payment that matches the paymentCollected parameter
     * 
     * @throws GlisServiceException if payment can;t be found
     */
    private Payment getPayment(GiftAidReceived paymentCollected) throws GlisServiceException {
		
    	com.virginmoneygiving.payment.domain.GiftAidReceived domainPaymentCollected =
    		new com.virginmoneygiving.payment.domain.GiftAidReceived();
    	
    	dozer.map(paymentCollected, domainPaymentCollected);
    	
    	// Will throw an exception if the payment can't be matched
		Payment payment = paymentService.fetchReceivedGiftAid(domainPaymentCollected);
		
		if ( payment == null ) {
			throw new GlisServiceException(LOGGER, "Can't identify payment for record: " 
					+ ", feeAmount=" + domainPaymentCollected.getGiftAidAmount()
					+ ", invoiceNumber=" + domainPaymentCollected.getInvoiceNumber());
		}
		
		// Disconnect payment from hibernate to avoid annoying exceptions of many types
		Payment domainPayment = new Payment();
		dozer.map(payment, domainPayment);
		return domainPayment;
	}

	/**
	 * Update the batch status of the payment and vat records to show they've been passed to GLIS.
	 * 
	 * @param payment the payment
	 */
    public void updatePaymentBatchStatus(Payment payment) {
                
        PaymentStatus status = new PaymentStatus();
        status.setCode(MasterDataCodeConstants.GIFT_AID_SETTLED);
        payment.setPaymentStatus(status);
        payment.setBatchStatus(status.getCode());
        
        
        paymentService.savePaymentDetails(payment);
        
    }

}
