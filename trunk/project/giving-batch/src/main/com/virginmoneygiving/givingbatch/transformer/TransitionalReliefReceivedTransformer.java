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
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefReceived;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.TransitionalReliefAmount;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.giving.domain.BankAccount;

/**
 * Transform TransitionalReliefAmount into TransitionalReliefReceived instances
 * for batch processing.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Ian Priest
 */
public class TransitionalReliefReceivedTransformer implements StepExecutionListener,
        ItemProcessor<TransitionalReliefAmount, TransitionalReliefReceived> {
	
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(TransitionalReliefReceivedTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;
    
    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /** giving Service. * */
    private GivingService givingService;

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
     * Sets the giving service.
     * 
     * @param givingService the giving service
     */
    public void setGivingService(GivingService givingService) {
        this.givingService = givingService;

    }

    /**
     * Transform the inbound instances
     * 
     * Stub class checked in for backup purposes.
     * TODO: Get an answer on the mapping and do this properly
     * 
     * @param transitionalReliefAmount the instance to transform
     * 
     * @return TransitionalReliefReceived the transformed instance
     * 
     * @throws Exception the exception
     */
    public TransitionalReliefReceived process(TransitionalReliefAmount transitionalReliefAmount) throws GivingBatchException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("In TransitionalReliefReceivedTransformer" + transitionalReliefAmount.getId());
        }
        // Data driven by GLIS service object rather than use case following
        // advice from hunardeep.chopra@virginmoney.com
        TransitionalReliefReceived transitionalReliefReceived = new TransitionalReliefReceived();
        Payment payment = transitionalReliefAmount.getPayment();
        PaymentType paymentType = new PaymentType();
        paymentType.setAmount(transitionalReliefAmount.getTransitionalAmount());
        //pt.setReferenceId();
        paymentType.setTransactionDate(transitionalReliefAmount.getUpdatedDateTime());
        if (paymentType.getTransactionDate() == null) {
            paymentType.setTransactionDate(transitionalReliefAmount.getCreatedDateTime());
        }
        paymentType.setTransactionStatus(transitionalReliefAmount.getTransitionalReliefStatus().getCode());
        paymentType.setTransactionType(payment.getPaymentType().getCode());
        
        transitionalReliefReceived.setAmountDetails(paymentType);
        
        // BankAccountUniqueId is, according to email from john.fordham@virginmoney.com, 
        // "concatenation of sort-code and account number"
        String sortCode = transitionalReliefAmount.getPayment().getTargetBankSortCode();
        String bankAccount = transitionalReliefAmount.getPayment().getTargetBankAccount();
        String bankUID = fetchDefaultBankAccount(transitionalReliefAmount.getPayment().getPaymentTarget());
        if (sortCode != null && bankAccount != null && bankUID == null) {
            String bankAccountNumber = Util.getBankAccountNumber(bankAccount);
            String bankSortCode = Util.getSortCode(sortCode);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("The Sort Code:" + bankSortCode);
                LOGGER.debug("The Bank Account Number :" + bankAccountNumber);
            }
            bankUID = bankSortCode.concat(bankAccountNumber);
        }
        transitionalReliefReceived.setBankAccountUniqueId(bankUID);
        transitionalReliefReceived.setCharityReference(transitionalReliefAmount.getCharityReference());
        
        // Not required
        //trc.setEventRef(eventRef);
        
        transitionalReliefReceived.setInvoiceNumber(transitionalReliefAmount.getFinanceReference());
        
        //Step 2: Here for every charity, we create the BatchEntity record.
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(transitionalReliefAmount.getId().toString());
        batchEntity.setNextBatchStatus(MasterDataCodeConstants.TransitionalRelief.TRANSITIONAL_RELIEF_RECEIVED.getCode());
        batchEntity.setPreviousStatus(MasterDataCodeConstants.TransitionalRelief.TRANSITIONAL_RELIEF_CLAIMED.getCode());

        batchEntity.setEntityReference(transitionalReliefAmount.getFinanceReference());  //HunarC: Added this
        batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

        batchEntity.setEntityTypeCode(Constant.ENTITY_TRANSITIONAL_RELIEF);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch Transitional Relief Entity : TransitionalReliefReceivedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch Transitional Relief Entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        
		// Add to job context
        updateObjectInContext(transitionalReliefReceived);
            
        return transitionalReliefReceived;
    }

    /**
     * Add the transformed object to the Job context.
     * 
     * @param transitionalReliefReceived of type registrationFee to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(TransitionalReliefReceived transitionalReliefReceived) {
        
    	ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        
    	List<TransitionalReliefReceived> transitionalReliefReceivedList =
                (List<TransitionalReliefReceived>) executionContext
                        .get(Constant.TRANSITIONAL_RELIEF_RECEIVED_OBJECT_LIST);
        if (transitionalReliefReceivedList == null) {
            
        	transitionalReliefReceivedList = new ArrayList<TransitionalReliefReceived>();
            executionContext.put(Constant.TRANSITIONAL_RELIEF_RECEIVED_OBJECT_LIST,
                    transitionalReliefReceivedList);
        }
        transitionalReliefReceivedList.add(transitionalReliefReceived);
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
        List<TransitionalReliefReceived> tList = (List<TransitionalReliefReceived>) executionContext
                        .get(Constant.TRANSITIONAL_RELIEF_RECEIVED_OBJECT_LIST);
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
                LOGGER.error("Unexpected error setting batch status to complete when no records found" + exception.getMessage(), exception);
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
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(MasterDataCodeConstants.TransitionalRelief.TRANSITIONAL_RELIEF_RECEIVED.getCode());
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("transitionalReliefReceivedJob");
        serviceBatch.setNextBatchName("TRANSITIONAL_RELIEF_SETTLED");
        createBatchRequest.setServiceBatch(serviceBatch);
        
        try {
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : TransitionalReliefReceivedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch  "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
    }

    /**
     * Fetch default bank account.
     * 
     * @param charityId the charity id
     * 
     * @return the string
     */
    private String fetchDefaultBankAccount(String charityId) {
        String result = "";
        long cId = 0;
        if (charityId != null) {
            cId = new Long(charityId).longValue();
        }
        List<BankAccount> accounts = givingService.fetchCharityBankDetails(cId);
        if (accounts != null && !accounts.isEmpty()) {
            for (BankAccount account : accounts) {
                if (account.getDefaultAccountIndicator().equalsIgnoreCase("Y")) {
                    String sortCode = account.getSortCode();
                    String bankAccount = account.getAccountNumber();
                    if (sortCode != null && bankAccount != null) {
                        String bankAccountNumber = Util.getBankAccountNumber(bankAccount);
                        String bankSortCode = Util.getSortCode(sortCode);
                        LOGGER.debug("The Sort Code:" + bankSortCode);
                        LOGGER.debug("The Bank Account Number :" + bankAccountNumber);
                        result = bankSortCode.concat(bankAccountNumber);
                        return result;
                    }
                }
            }
        }
        return null;
    }
}
