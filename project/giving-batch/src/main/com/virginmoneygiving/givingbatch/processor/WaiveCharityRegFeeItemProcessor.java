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
 * The Class WaiveCharityRegFeeItemProcessor.
 * 
 * @author taruna
 */
public class WaiveCharityRegFeeItemProcessor implements
        ItemProcessor<Payment, Payment> {
  
    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /** create Logger instance for to log the information. */
    private static Logger LOGGER =
            Logger.getLogger(WaiveCharityRegFeeItemProcessor.class);
    
    /** payment serice. * */
    private PaymentService paymentService;
    
    
    /** {@inheritDoc} */
    public Payment process(Payment payment) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("PaymentInitiatedProcessor.process - Start");
        }
        updateBatchStatus(payment,
                MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_WAIVED.getCode());
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("PaymentInitiatedProcessor.process - End");
        }
        return payment;

    }
    
    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param status of type String.
     * @param payment the payment
     */
    public void updateBatchStatus(Payment payment, String status) {
        
        payment.setBatchStatus(MasterDataCodeConstants.PaymentStatusPending.CHARITY_REG_FEE_PAYMENT_WAIVED_PENDING.getCode());
        
        Set<VatAmount> vatAmounts = payment.getVatAmount();
        
        // How can this test ever fail? What if there's more than one vat 
        // record for a payment? Can that ever happen?
        if ( vatAmounts != null && vatAmounts.size() > 0 ) {
        	VatAmount vatAmount = vatAmounts.iterator().next();
        	vatAmount.setBatchStatus(MasterDataCodeConstants.PaymentStatusPending.VAT_CRF_WAIVED_PENDING.getCode());
        }
        
        paymentService.savePaymentDetails(payment);
    }

}
