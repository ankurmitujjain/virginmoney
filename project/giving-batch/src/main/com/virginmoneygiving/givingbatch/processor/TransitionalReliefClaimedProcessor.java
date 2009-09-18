package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.TransitionalReliefAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the TransitionalReliefClaimed batch.
 * 
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * 
 * TODO: does it need to change the db status?
 * 
 * @author ian priest
 */
public class TransitionalReliefClaimedProcessor implements
        ItemProcessor<TransitionalReliefAmount, TransitionalReliefAmount> {

    /** instance of logger. * */
    private static Logger LOGGER =
            Logger.getLogger(TransitionalReliefClaimedProcessor.class);
    
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
    public TransitionalReliefAmount process(TransitionalReliefAmount transitionalReliefAmount) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TransitionalReliefClaimedProcessor.process - Start");
        }
        updateBatchStatus(transitionalReliefAmount);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TransitionalReliefClaimedProcessor.process - End");
        }
        return transitionalReliefAmount;

    }

    /**
     * Update batch-status to show transitionalReliefAmount has been processed by glis.
     * 
     * @param transitionalReliefAmount the transitional relief amount
     */
    public void updateBatchStatus(TransitionalReliefAmount transitionalReliefAmount) {
        
        transitionalReliefAmount.setBatchStatus(Constant.TRANSITIONAL_RELIEF_STATUS_PENDING);
        paymentService.saveTransitionalReliefAmount(transitionalReliefAmount);
        
    }

}
