package com.virginmoneygiving.givingbatch.processor;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the CreateRegistrationFee batch.
 * 
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * 
 * TODO: does it need to change the db status?
 * 
 * @author ian priest
 */
public class CreateRegistrationFeesProcessor implements
        ItemProcessor<Payment, Payment> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(CreateRegistrationFeesProcessor.class);
    
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
       
        updateBatchStatus(payment);
        
        return payment;
    }

    /**
     * Update the batch status of the payment and vat records to show they've been passed to GLIS.
     * 
     * @param payment the payment
     */
    public void updateBatchStatus(Payment payment) {
    	
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("PaymentInitiatedProcessor.updateBatchStatus - Start");
        }
        
        if (payment.getPaymentStatus().getCode().equals("CRFPAYINIT")) {
            payment.setBatchStatus(MasterDataCodeConstants.PaymentStatusPending.CHARITY_REG_FEE_PAYMENT_INITIATED_PENDING.getCode());
            
            Set<VatAmount> vatAmounts = payment.getVatAmount();
            
            // How can this test ever fail? What if there's more than one vat 
            // record for a payment? Can that ever happen?
            if ( vatAmounts != null && vatAmounts.size() > 0 ) {
            	VatAmount vatAmount = vatAmounts.iterator().next();
            	vatAmount.setBatchStatus(MasterDataCodeConstants.PaymentStatusPending.VAT_CRF_INITIATED_PENDING.getCode());
            }
        }
        
        if (payment.getPaymentStatus().getCode().equals("CRFPWAIVED")) {
            payment.setBatchStatus(MasterDataCodeConstants.PaymentStatusPending.CHARITY_REG_FEE_PAYMENT_WAIVED_PENDING.getCode());
            
            Set<VatAmount> vatAmounts = payment.getVatAmount();
            
            // How can this test ever fail? What if there's more than one vat 
            // record for a payment? Can that ever happen?
            if ( vatAmounts != null && vatAmounts.size() > 0 ) {
                VatAmount vatAmount = vatAmounts.iterator().next();
                vatAmount.setBatchStatus(MasterDataCodeConstants.PaymentStatusPending.VAT_CRF_WAIVED_PENDING.getCode());
            }
        }
        
        paymentService.savePaymentDetails(payment);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("PaymentInitiatedProcessor.updateBatchStatus - End");
        }
    }

}
