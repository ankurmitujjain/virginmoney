package com.virginmoneygiving.givingbatch.processor;
//TODO- This class is not being used and need to be removed.
import javax.annotation.Resource;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoney.glis.messages.TransitionalReliefReceived;
import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.PaymentStatus;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.domain.VatStatus;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the CollectedRegistrationFees batch.
 * 
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * 
 * TODO: does it need to change the db status?
 * 
 * @author ian priest
 */
public class TransitionalReliefSettledProcessor implements
        ItemProcessor<TransitionalReliefReceived, TransitionalReliefReceived> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(TransitionalReliefSettledProcessor.class);
    
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
    public TransitionalReliefReceived process(TransitionalReliefReceived paymentCollected) throws GlisServiceException {
       
        LOGGER.trace("CreateRegistrationFeesProcessor.process - Start");
       
        /*
         * Try to find the payment record for the collected payment.
         * Will throw an exception and force a rollback if it fails
         */
        Payment payment = getPayment(paymentCollected);
        
        /*
         * Update it's status
         */
        updateBatchStatus(payment);
        
        LOGGER.trace("CreateRegistrationFeesProcessor.process - End");
        return paymentCollected;

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
    private Payment getPayment(TransitionalReliefReceived paymentCollected) throws GlisServiceException {
		
    	com.virginmoneygiving.payment.domain.TransitionalReliefReceived domainPaymentCollected =
    		new com.virginmoneygiving.payment.domain.TransitionalReliefReceived();
    	
    	dozer.map(paymentCollected, domainPaymentCollected);
    	
    	// Will throw an exception if the payment can't be matched
		Payment payment = paymentService.fetchReceivedTransitionalRelief(domainPaymentCollected);
		
		if ( payment == null ) {
			throw new GlisServiceException(LOGGER, "Can't identify payment for record: " 
					+ "charityReference=" + domainPaymentCollected.getCharityReference()
					+ ", feeAmount=" + domainPaymentCollected.getTransitionalReliefAmount()
					+ ", invoiceNumber=" + domainPaymentCollected.getInvoiceNumber()
					+ ", vatAmount=" + domainPaymentCollected.getVatAmount() );
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
    public void updateBatchStatus(Payment payment) {
    	

        LOGGER.trace("PaymentInitiatedProcessor.updateBatchStatus - Start");
                
        PaymentStatus status = new PaymentStatus();
        status.setCode(MasterDataCodeConstants.TRANSITIONAL_RELIEF_SETTLED);
        payment.setPaymentStatus(status);
        payment.setBatchStatus(status.getCode());
        
        paymentService.savePaymentDetails(payment);
        
        LOGGER.trace("PaymentInitiatedProcessor.updateBatchStatus - End");
    }

}
