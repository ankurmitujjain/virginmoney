package com.virginmoneygiving.givingbatch.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.GiftAidReceived;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.domain.Payment;

/**
 * This class takes the GiftAidAmount object and transform it to GiftAidReceived
 * object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Tarun Adiwal
 */
public class GiftAidReceivedTransformer implements StepExecutionListener,
        ItemProcessor<GiftAidAmount, GiftAidReceived> {
    
    /** creating instance of Logger. */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidReceivedTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /**
     * Sets the locator impl.
     * 
     * @param locatorImpl the locatorImpl to set
     */
	public void setLocatorImpl(
			GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
		this.locatorImpl = locatorImpl;
	}

    /**
     * This method used to process the giftAidAmount details.
     * 
     * @param giftAidAmount of type GiftAidAmount
     * 
     * @return GiftAidReceived object
     * 
     * @throws Exception process the giftAidAmount details throws Exception.
     */
    public GiftAidReceived process(GiftAidAmount giftAidAmount)
             {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidReceivedTransformer - Start");
        }
        GiftAidReceived giftAidReceived = null;
        PaymentType paymentType = null;
        if (giftAidAmount != null && giftAidAmount.getPayment() != null) {

            Payment payment = giftAidAmount.getPayment();
            giftAidReceived = new GiftAidReceived();
            giftAidReceived.setInvoiceNumber(giftAidAmount
                    .getFinanceReference());
            giftAidReceived.setCharityReference(payment.getPaymentTarget());

            paymentType = new PaymentType();
            paymentType.setAmount(giftAidAmount.getGiftAidAmount());
            paymentType.setTransactionType(payment.getPaymentType().getCode());
            paymentType.setTransactionDate(giftAidAmount
                    .getGiftAidClaimedDate());
            paymentType.setTransactionStatus(giftAidAmount.getGiftAidStatus()
                    .getCode());
            // TODO: need to confirm from business.
            // paymentType.setReferenceId(referenceId);
            giftAidReceived.setAmountDetails(paymentType);
            
            if (payment.getTargetBankSortCode() != null
					&& payment.getTargetBankAccount() != null) {
				String bankAccount = Util.getBankAccountNumber(payment
						.getTargetBankAccount());
				String bankSortCode = Util.getSortCode(payment
						.getTargetBankSortCode());
				LOGGER.debug("The Sort Code:" + bankSortCode);
				LOGGER.debug("The Bank Account Number :" + bankAccount);
				giftAidReceived.setBankAccountUniqueId(bankSortCode
						.concat(bankAccount));
			}
        
        
        //Step 2: Here for every charity, we create the BatchEntity record.
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(giftAidAmount.getId().toString());

        batchEntity.setEntityReference(giftAidAmount.getFinanceReference());  //HunarC: Added this
        batchEntity.setBaseReference(giftAidAmount.getFinanceReference());    //HunarC: Added this

        batchEntity.setNextBatchStatus(MasterDataCodeConstants.GiftAid.GIFT_AID_RECEIVED.getCode());
        batchEntity.setPreviousStatus(MasterDataCodeConstants.GiftAid.GIFT_AID_CLAIMED.getCode());
        batchEntity.setEntityTypeCode(Constant.ENTITY_GIFTAID);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch GIFTAID Entity : GiftAidReceivedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch GIFTAID Entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }

        updateObjectInContext(giftAidReceived);
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidReceivedTransformer - End");
        }
        } // Null check 13051 :: TarunA
        return giftAidReceived;
    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put GiftAidReceivedObjectList.
     * 
     * @param giftAidReceived of type GiftAidReceived to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(GiftAidReceived giftAidReceived) {
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<GiftAidReceived> giftAidReceivedObjectList =
                (List<GiftAidReceived>) executionContext
                        .get(Constant.GIFTAID_RECEIVED_OBJECT_LIST);
        if (giftAidReceivedObjectList == null) {
            giftAidReceivedObjectList = new ArrayList();
            executionContext.put(Constant.GIFTAID_RECEIVED_OBJECT_LIST,
                    giftAidReceivedObjectList);
        }
        giftAidReceivedObjectList.add(giftAidReceived);
    }

    /**
     * This method will execute after step completes and returns status.
     * This method will update the batch status to BATCH_STATUS_SUCCESSFUL or
     * BATCH_STATUS_FAIL along with the reasons for the same.
     * 
     * @param stepExecution of type StepExecution
     * 
     * @return Exit Status after completion of step.
     */
    public ExitStatus afterStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        List<GiftAidReceived> tList = (List<GiftAidReceived>) executionContext
                        .get(Constant.GIFTAID_RECEIVED_OBJECT_LIST);
        if (tList == null || tList.size() < 1) {
            LOGGER.debug("inside afterStep method - No matching records found ... setting batch status to completed.");
            try {
                UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setEndDateTime(Util.buildXMLGregorianDate());
                request.setStatus(Constant.BATCH_STATUS_SUCCESSFUL);
                request.setErrorMessage("No records found.");
                locatorImpl.getGivingBatchExtManagementPort().updateBatchStatus(request);
                executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
            }
            catch (GivingBatchExtManagementServiceFaultException exception) {
                LOGGER.error("Unexpected error setting batch status to complete when no records found" +exception.getMessage(), exception);
            }
        }
        return ExitStatus.COMPLETED;
    }

    /**
     * This method is called before step starts and assign the step execution
     * values.
     * This method is used to create a batch record before starting the actual batch processing
     * and to populate the control data columns.
     * 
     * @param stepExecution of type StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("inside before step  execution context:" + stepExecution);
        }
        this.stepExecution = stepExecution;
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.GIFT_AID_RECEIVED);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);

        serviceBatch.setCurrentJobName("giftAidReceivedJob");
        serviceBatch.setNextBatchName("GiftAidSettled");
        createBatchRequest.setServiceBatch(serviceBatch);
        
        try {
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : GiftAidReceivedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch  "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        
    }

}
