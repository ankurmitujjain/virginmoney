package com.virginmoneygiving.givingbatch.transformer;

import java.sql.Date;
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
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.domain.TransitionalReliefClaimed;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.TransitionalReliefAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * Transform TransitionalReliefAmount into TransitionalReliefClaimed instances
 * for batch processing.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Ian Priest
 */
public class TransitionalReliefClaimedTransformer implements StepExecutionListener,
        ItemProcessor<TransitionalReliefAmount, TransitionalReliefClaimed> {
	
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(TransitionalReliefClaimedTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** Instance of GivingBatchHelper. * */
    private GivingBatchHelper generateSequenceHelper;

    /** payment service. * */
    private PaymentService paymentService;
    
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
     * Sets the generate sequence helper.
     * 
     * @param generateSequenceHelper the generateSequenceHelper to set
     */
    public void setGenerateSequenceHelper(GivingBatchHelper generateSequenceHelper) {
        this.generateSequenceHelper = generateSequenceHelper;
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
     * Transform the inbound instances
     * 
     * Stub class checked in for backup purposes.
     * TODO: Get an answer on the mapping and do this properly
     * 
     * @param transitionalReliefAmount the instance to transform
     * 
     * @return TransitionalReliefClaimed the transformed instance
     * 
     * @throws Exception the exception
     */
    public TransitionalReliefClaimed process(TransitionalReliefAmount transitionalReliefAmount) throws GivingBatchException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("In TransitionalReliefClaimedTransformer" + transitionalReliefAmount.getId());
        }
        // Data driven by GLIS service object rather than use case following
        // advice from hunardeep.chopra@virginmoney.com
        TransitionalReliefClaimed transitionalReliefClaimed = new TransitionalReliefClaimed();
        
        transitionalReliefClaimed.setAmount(transitionalReliefAmount.getTransitionalAmount());
        
        // BankAccountUniqueId is, according to email from john.fordham@virginmoney.com, 
        // "concatenation of sort-code and account number"
        String sortCode = transitionalReliefAmount.getPayment().getTargetBankSortCode();
        String bankAccount = transitionalReliefAmount.getPayment().getTargetBankAccount();

        if (sortCode != null && bankAccount != null) {
            String bankAccountNumber = Util.getBankAccountNumber(bankAccount);
            String bankSortCode = Util.getSortCode(sortCode);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("The Sort Code:" + bankSortCode);
                LOGGER.debug("The Bank Account Number :" + bankAccountNumber);
            }
            transitionalReliefClaimed.setBankAccountUniqueId(bankSortCode.concat(bankAccountNumber));
        }

        transitionalReliefClaimed.setClaimPeriodFrom(transitionalReliefAmount.getFromDate());
        transitionalReliefClaimed.setClaimPeriodTo(transitionalReliefAmount.getToDate());
        transitionalReliefClaimed.setCustomer(Constant.CUSTOMER);
        transitionalReliefClaimed.setDate(new Date(transitionalReliefAmount.getCreatedDateTime().getTime()));
        // Not required
        //trc.setEventRef(eventRef);
        transitionalReliefClaimed.setInvoiceNumber(transitionalReliefAmount.getFinanceReference());
        transitionalReliefClaimed.setTransactionStatus(transitionalReliefAmount.getTransitionalReliefStatus().getCode());

        transitionalReliefClaimed.setTransactionType(transitionalReliefAmount.getPayment().getPaymentType().getCode());
        String summaryInvoiceNumber = updateObjectInContext(transitionalReliefClaimed);
        transitionalReliefClaimed.setGroupInvoiceNumber(summaryInvoiceNumber);
        saveSummaryInvoiceNumber(transitionalReliefAmount.getId(), summaryInvoiceNumber);
        
        //Step 2: Here for every charity, we create the BatchEntity record.
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(transitionalReliefAmount.getId().toString());
        batchEntity.setNextBatchStatus(MasterDataCodeConstants.TransitionalRelief.TRANSITIONAL_RELIEF_CLAIMED.getCode());
        batchEntity.setPreviousStatus(null);

        batchEntity.setEntityReference(summaryInvoiceNumber);  //HunarC: Added this
        batchEntity.setBaseReference(transitionalReliefAmount.getFinanceReference());    //HunarC: Added this

        batchEntity.setEntityTypeCode(Constant.ENTITY_TRANSITIONAL_RELIEF);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch Transitional Relief Entity : TransitionalReliefClaimedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch Transitional Relief Entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
            
        return transitionalReliefClaimed;
    }
    
    /**
     * HUNARC: This is the new version.
     * This method is used for to get the execution context.If it is null then
     * put TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST.
     * 
     * @param transitionalReliefClaimed of type Transaction to add this object to
     * executionContextList.
     * 
     * @return the string
     */
    private String updateObjectInContext(TransitionalReliefClaimed transitionalReliefClaimed) {
        LOGGER.debug("Inside new TransitionalReliefClaimedTransformer UpdateObjectContext -Start.");
        ExecutionContext executionContext =stepExecution.getJobExecution().getExecutionContext();
        List<TransitionalReliefClaimed> trnRelClaimedObjectList =
                (List<TransitionalReliefClaimed>) executionContext.get(Constant.TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST);
        String invoiceNumber = transitionalReliefClaimed.getInvoiceNumber();
        if (trnRelClaimedObjectList == null || trnRelClaimedObjectList.size() == 0) {
            invoiceNumber = getGeneratedInvoiceNumber();
            trnRelClaimedObjectList = new ArrayList<TransitionalReliefClaimed>();
            transitionalReliefClaimed.setInvoiceNumber(invoiceNumber);

            TransitionalReliefClaimed summaryTransaction = new TransitionalReliefClaimed();
            summaryTransaction.setAmount(transitionalReliefClaimed.getAmount());
            summaryTransaction.setBankAccountUniqueId(transitionalReliefClaimed.getBankAccountUniqueId());
            summaryTransaction.setClaimPeriodFrom(transitionalReliefClaimed.getClaimPeriodFrom());
            summaryTransaction.setClaimPeriodTo(transitionalReliefClaimed.getClaimPeriodTo());
            summaryTransaction.setCustomer(transitionalReliefClaimed.getCustomer());
            summaryTransaction.setDate(transitionalReliefClaimed.getDate());
            summaryTransaction.setEventRef(transitionalReliefClaimed.getEventRef());
            summaryTransaction.setTransactionStatus(transitionalReliefClaimed.getTransactionStatus());
            summaryTransaction.setTransactionType(transitionalReliefClaimed.getTransactionType());
            summaryTransaction.setInvoiceNumber(invoiceNumber);

            trnRelClaimedObjectList.add(summaryTransaction);
            executionContext.put(Constant.TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST, trnRelClaimedObjectList);
            LOGGER.debug("TransitionalReliefClaimedTransformer UpdateObjectContext-New Tran Invoice/Amount = "
                    + transitionalReliefClaimed.getInvoiceNumber() + "/" + transitionalReliefClaimed.getAmount());
        }
        else {
            TransitionalReliefClaimed summaryTransitionalReliefClaimed = trnRelClaimedObjectList.get(0);
            invoiceNumber = summaryTransitionalReliefClaimed.getInvoiceNumber();
            summaryTransitionalReliefClaimed.setAmount(summaryTransitionalReliefClaimed.getAmount().add(transitionalReliefClaimed.getAmount()));
            LOGGER.debug("TransitionalReliefClaimedTransformer UpdateObjectContext-Update Tran Invoice/Amount = "
                    + summaryTransitionalReliefClaimed.getInvoiceNumber() + "/" + summaryTransitionalReliefClaimed.getAmount());
        }
        LOGGER.debug("Inside TransitionalReliefClaimedTransformer UpdateObjectContext -End Inv Num = " + invoiceNumber);
        return invoiceNumber;
    }


    /**
     * Add the transformed object to the Job context.
     * 
     * @param transitionalReliefClaimed of type registrationFee to add this object to
     * executionContextList.
     */
 // Changing method name as per Naming Conventions.
/*    private void updateObjectInContextOld(TransitionalReliefClaimed transitionalReliefClaimed) {
        
    	LOGGER
                .trace("Inside TransitionalReliefClaimedTransformer UpdateObjectContext -Start");
        
    	ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        
    	List<TransitionalReliefClaimed> transitionalReliefClaimedList =
                (List<TransitionalReliefClaimed>) executionContext
                        .get(Constant.TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST);
        if (transitionalReliefClaimedList == null) {
            
        	transitionalReliefClaimedList = new ArrayList<TransitionalReliefClaimed>();
            executionContext.put(Constant.TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST,
                    transitionalReliefClaimedList);
        }
        transitionalReliefClaimedList.add(transitionalReliefClaimed);
        LOGGER
                .trace("Inside TransitionalReliefClaimedTransformer UpdateObjectContext End.");
    }*/

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
        List<TransitionalReliefClaimed> tList = (List<TransitionalReliefClaimed>) executionContext
                        .get(Constant.TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST);
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
                LOGGER.error("Unexpected error setting batch status to complete when no records found"+exception.getMessage(), exception);
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
        String batchNumber = Util.getGeneratedBatchNumber(MasterDataCodeConstants.TransitionalRelief.TRANSITIONAL_RELIEF_CLAIMED.getCode());
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("transitionalReliefClaimedJob");
        serviceBatch.setNextBatchName("TRANSITIONAL_RELIEF_RECEIVED");
        createBatchRequest.setServiceBatch(serviceBatch);
        
        try {
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : TransitionalReliefClaimedTransformer."
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
     * Gets the generated invoice number.
     * 
     * @return the generated invoice number
     */
    public String getGeneratedInvoiceNumber() {
        String generateSequesnceNumber = paymentService.generateSequence("TRANSITIONAL_RELIEF", "INVOICE");
        String referenceNumber = generateSequenceHelper.prefixReferenceType("TRANSITIONAL_RELIEF", "INVOICE",
                generateSequesnceNumber);
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("generated sequence number is" + referenceNumber);
        }
        return referenceNumber;
    }

    /**
     * Save summary invoice number.
     * 
     * @param id the id.
     * @param summaryInvoiceNumber the summary invoice number.
     */
    public void saveSummaryInvoiceNumber(Long id, String summaryInvoiceNumber) {
        paymentService.saveSummaryTranReliefInvoiceNumber(id, summaryInvoiceNumber);
    }

}
