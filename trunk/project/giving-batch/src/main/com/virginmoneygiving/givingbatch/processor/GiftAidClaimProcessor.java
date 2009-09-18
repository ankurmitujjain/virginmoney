package com.virginmoneygiving.givingbatch.processor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.GiftAidClaimServiceProxy;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.domain.LastGiftAidClaim;
import com.virginmoneygiving.payment.domain.Payment;

import com.virginmoneygiving.payment.service.PaymentService;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

import javax.annotation.Resource;

/**
 * This class used to update the gift aid status and transitional relief amount.
 * This class takes the payment object returned by ItemReader and returns the ItemWriter object.
 * This class updates GiftAidStatus.It created batch records and update 
 * the status in batch table.
 * 
 * @author Srinivas Nageli
 */
public class GiftAidClaimProcessor implements StepExecutionListener, ItemProcessor<Payment, Payment> {

    /** instance for logger. * */
    private static final Logger LOGGER =
            Logger.getLogger(GiftAidClaimProcessor.class);

    /** payment Service. * */
    private PaymentService paymentService;

    /** gift Aid Claim Service Proxy. * */
    private GiftAidClaimServiceProxy giftAidClaimServiceProxy;

    /** The Constant TRANSITIONAL_RELIEF. */
    private static final String TRANSITIONAL_RELIEF = "TRANSITIONAL_RELIEF";
    
    /** The Constant WEB_DONATION. */
    private static final String WEB_DONATION = "WEB_DONATION";
    
    /** The last gift aid claim. */
    private LastGiftAidClaim lastGiftAidClaim = null;

    /** Step execution instance. */
    private ExecutionContext executionContext;

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtServiceLocator;

    /**
     * Sets the giving batch ext service locator.
     * 
     * @param givingBatchExtServiceLocator the new giving batch ext service locator
     */
    @Resource
    public void setGivingBatchExtServiceLocator(GivingBatchExtManagementServiceLocatorImpl
            givingBatchExtServiceLocator) {
        this.givingBatchExtServiceLocator = givingBatchExtServiceLocator;
    }

    /** {@inheritDoc} */
    public Payment process(Payment payment) throws GivingBatchException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::process() - START");
        }
        updateGiftAidStatus(payment);

        // ian.priest@opsera.com
        // If we return null Spring Batch will assume that processing stops
        // for the passed in payment. Allows us to ignore payments that
        // aren't eligible for gift-aid
       // Payment savedPayment = saveTransitionalReliefDeatails(payment);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::process() - END");
        }

        return payment;
    }

    /**
     * This method used to change the gift aid status.
     * 
     * @param payment the payment id.
     * 
     * @return payment object with updated details.
     */
    private Payment updateGiftAidStatus(Payment payment) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::updateGiftAidStatus() - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Processing to change the gift aid status: payment Id= " + payment.getId());
        }

        lastGiftAidClaim = fetchCurrentClaimedPeriod();
        final Set<GiftAidAmount> giftAidAmounts = payment.getGiftAidAmount();

        for (final GiftAidAmount giftAidAmount : giftAidAmounts) {
            giftAidAmount.getGiftAidStatus().setCode(
                    MasterDataCodeConstants.GiftAid.GIFT_AID_CLAIMED.getCode());
            giftAidAmount.setClaimedPeriodFrom(lastGiftAidClaim.getGiftAidClaimFromDate());
            giftAidAmount.setUpdatedIPAddress(Util.getHostIpaddress());
            giftAidAmount.setUpdatedProcess("giftAidClaimProcessJob");
            giftAidAmount.setClaimedPeriodTo(lastGiftAidClaim.getGiftAidClaimToDate());
            LOGGER.debug("Changing gift aid status: GA Id= " + giftAidAmount.getId());
            createBatchEntity(giftAidAmount.getId(), giftAidAmount.getFinanceReference(),
                              payment.getFinanceReference(), "Gift Aid claim processed");
        }
        payment.setUpdatedIPAddress(Util.getHostIpaddress());
        payment.setUpdatedProcess("giftAidClaimProcessJob");
        payment = paymentService.savePaymentDetails(payment);


        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::updateGiftAidStatus() - END");
        }

        return payment;
    }

   /**
    * Sets the payment service.
    * 
    * @param paymentService the paymentService to set
    */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Sets the gift aid claim service proxy.
     * 
     * @param giftAidClaimServiceProxy the giftAidClaimServiceProxy to set
     */
    public void setGiftAidClaimServiceProxy(
            GiftAidClaimServiceProxy giftAidClaimServiceProxy) {
        this.giftAidClaimServiceProxy = giftAidClaimServiceProxy;
    }
    
    /**
     * Fetch current claimed period.
     * 
     * @return the last gift aid claim
     */
    private LastGiftAidClaim fetchCurrentClaimedPeriod(){
    	return paymentService.fetchLastGiftAidClaimedPeriod(Constant.CURRENT);
    	
    }

    /**
     * Creates the batch entity.
     * 
     * @param giftAidAmountId the ga id
     * @param entityRef the entity ref
     * @param pRef the ref
     * @param msgText the msg text
     */
    private void createBatchEntity(Long giftAidAmountId, String entityRef, String pRef, String msgText) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::createBatchEntity() - START");
        }
    	String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
    	if(LOGGER.isDebugEnabled()){
    	    LOGGER.debug("Creating Batch Entity (Batch/Id/Ref): " + batchNumber + "/" + giftAidAmountId + "/" + pRef);
    	}
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_SUCCESSFUL);
        batchEntity.setEntityId(giftAidAmountId.toString());
        batchEntity.setNextBatchStatus(null);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_GIFTAID);
        batchEntity.setEntityReference(entityRef);
        batchEntity.setBaseReference(pRef);
        batchEntity.setErrorMessage(msgText);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch entity : GiftAidClaimProcessor:createBatchEntity"
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error occured while calling creating batch entity : GiftAidClaimProcessor:createBatchEntity",ex);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::createBatchEntity() - END");
        }
    }

    /** {@inheritDoc} */
    public ExitStatus afterStep(StepExecution stepexecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::afterStep() - START");
        }
        // Update batch status
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
        String errMsg  = "Batch processed.";
        String bStatus = Constant.BATCH_STATUS_SUCCESSFUL;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("inside afterStep method - updating batch status");
        }
        try {
            UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setEndDateTime(Util.buildXMLGregorianDate());
            request.setStatus(bStatus);
            request.setErrorMessage(errMsg);
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().updateBatchStatus(request);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch entity : GiftAidClaimProcessor:afterStep"
                        + e.getMessage(), e);
         }
        catch (Exception e) {
            LOGGER.error("Unexpected error setting batch status to complete in afterstep :GiftAidClaimProcessor:afterStep", e);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::afterStep() - END");
        }
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(StepExecution stepexecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::beforeStep() - START");
        }
        executionContext = stepexecution.getJobExecution().getExecutionContext();
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.GIFT_AID_STATUS_GIFT_AID_CLAIMED_PROCESSED);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In beforeStep batchNUmber = " + batchNumber);
        }
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepexecution);
        serviceBatch.setCurrentJobName("giftAidClaimProcessJob");
        serviceBatch.setNextBatchName("putCharityLiveBatchJob");
        createBatchRequest.setServiceBatch(serviceBatch);

        try{
        	givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	executionContext.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException exception) {
            LOGGER.error("Error occurred while creating batch : GiftAidClaimProcessor:beforeStep"
                        + exception.getMessage(), exception);

         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch :GiftAidClaimProcessor: beforeStep ", exception);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimProcessor::beforeStep() - END");
        }
    }
}
