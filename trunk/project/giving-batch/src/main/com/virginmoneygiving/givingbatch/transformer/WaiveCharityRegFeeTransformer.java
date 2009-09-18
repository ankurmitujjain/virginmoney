package com.virginmoneygiving.givingbatch.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.domain.TaxType;
import com.virginmoneygiving.givingbatch.domain.WaiveRegistrationFee;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.VatAmount;

/**
 * This class takes the Payment object and transform it to
 * WaiveRegistrationFee object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class WaiveCharityRegFeeTransformer implements StepExecutionListener,
        ItemProcessor<Payment, WaiveRegistrationFee> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(WaiveCharityRegFeeTransformer.class);

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

    /** {@inheritDoc} **/
    public WaiveRegistrationFee process(Payment payment) throws GivingBatchException {

		ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);

        WaiveRegistrationFee registrationFee = null;
        Set<VatAmount> vatAmountList = null;
        if (payment != null) {
            registrationFee = new WaiveRegistrationFee();
            PaymentType paymentType = new PaymentType();
            TaxType taxType = new TaxType();
            registrationFee.setCreditNoteNumber(payment
                    .getCreditNoteReference());
            registrationFee.setCharityReference(payment.getPaymentSource());
            paymentType.setAmount(payment.getGrossAmount());
            paymentType.setTransactionDate(payment.getCreatedDateTime());
            paymentType.setTransactionStatus(payment.getPaymentStatus()
                    .getCode());
            paymentType.setTransactionType(payment.getPaymentType().getCode());
            // paymentType.setReferenceId(referenceId);
            registrationFee.setFeeDetails(paymentType);
            vatAmountList = payment.getVatAmount();
            for (VatAmount vatAmount : vatAmountList) {
                taxType.setAmount(vatAmount.getVatAmount());
                if(vatAmount.getVatCode() != null){
                	taxType.setCode(vatAmount.getVatCode().getCode());
                	taxType.setRate(vatAmount.getVatCode().getRate());
                }

                taxType.setStatus(vatAmount.getVatStatus().getCode());
                
                taxType.setAmount(vatAmount.getVatAmount());
                taxType.setStatus(vatAmount.getVatStatus().getCode());
    			registrationFee.setVatDetails(taxType);
    			CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
    	        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
    	        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
    	        batchEntity.setEntityId(vatAmount.getId().toString());
    	        batchEntity.setNextBatchStatus(MasterDataCodeConstants.VAT.VAT_CRF_WAIVED.getCode());
    	        batchEntity.setPreviousStatus(null);

                batchEntity.setEntityReference(payment.getCreditNoteReference());  //HunarC: Added this
                batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

                batchEntity.setEntityTypeCode(Constant.ENTITY_VAT);
    	        ServiceBatch serviceBatch = new ServiceBatch();
    	        serviceBatch.setBatchNumber(batchNumber);
    	        batchEntity.setBatch(serviceBatch);
    	        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
    	        try {
    	            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
    	        }
    	        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
    	            LOGGER.error("Error occurred while creating batch VAT Entity : WaiveRegistrationFee."
    	                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
    	            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
    	        }
                catch (Exception exception) {
                    LOGGER.error("Error occured while calling creating batch  "+exception.getMessage(),exception);
                    throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
                }
    	        
            }
            registrationFee.setVatDetails(taxType);
            registrationFee.setInvoiceNumber(payment.getFinanceReference());

            if (payment.getTargetBankSortCode() != null
					&& payment.getTargetBankAccount() != null) {
				String bankAccount = Util.getBankAccountNumber(payment
						.getTargetBankAccount());
				String bankSortCode = Util.getSortCode(payment
						.getTargetBankSortCode());
				LOGGER.debug("The Sort Code:" + bankSortCode);
				LOGGER.debug("The Bank Account Number :" + bankAccount);
				registrationFee.setBankAccountUniqueId(bankSortCode
						.concat(bankAccount));
			}
        	
		//Step 2: Here for every charity, we create the BatchEntity record.
        
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(payment.getId().toString());
        batchEntity.setNextBatchStatus(MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_WAIVED.getCode());
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);

        batchEntity.setEntityReference(payment.getCreditNoteReference());  //HunarC: Added this
        batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch Payment Entity : WaiveRegistrationFee."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch (Exception exception) {
            LOGGER.error("Error occured while calling creating batch  "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        updateObjectInContext(registrationFee);
        } // Null check 13051 :: TarunA
        return registrationFee;
        
    }
    
    /**
     * This method is used for to get the execution context.If it is null then
     * put waiveRegistrationFeeObjectList.
     * 
     * @param waiveRegistrationFee of type WaiveRegistrationFee to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(WaiveRegistrationFee waiveRegistrationFee) {
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<WaiveRegistrationFee> waiveRegistrationFeeObjectList =
                (List<WaiveRegistrationFee>) executionContext
                        .get(Constant.WAIVE_REG_FEE_OBJECT_LIST);
        if (waiveRegistrationFeeObjectList == null) {
            waiveRegistrationFeeObjectList = new ArrayList<WaiveRegistrationFee>();
            executionContext.put(Constant.WAIVE_REG_FEE_OBJECT_LIST,
                    waiveRegistrationFeeObjectList);
        }
        waiveRegistrationFeeObjectList.add(waiveRegistrationFee);
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
        List<WaiveRegistrationFee> waiveRegistrationFeeList = (List<WaiveRegistrationFee>) executionContext
                        .get(Constant.WAIVE_REG_FEE_OBJECT_LIST);
        if (waiveRegistrationFeeList == null || waiveRegistrationFeeList.size() < 1) {
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
		LOGGER.trace("inside before step  execution context:" + stepExecution);
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
		this.stepExecution = stepExecution;
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
		// Step 1: Here before step starts, we first create the Batch record.
		CreateBatchRequest createBatchRequest = new CreateBatchRequest();
		String batchNumber = Util
				.getGeneratedBatchNumber(MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED
						.getCode());
		ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);

        serviceBatch.setCurrentJobName("waiveCharityRegFeeJob");
        serviceBatch.setNextBatchName("CharityRegistrationFee");
		createBatchRequest.setServiceBatch(serviceBatch);

		try {
			locatorImpl.getGivingBatchExtManagementPort().createBatch(
					createBatchRequest);
			context.put(Constant.BATCH_NUMBER, batchNumber);
		} 
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch VAT Entity : WaiveRegistrationFee."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception){
            LOGGER.error("Error occurred while creating batch VAT Entity : WaiveRegistrationFee."
                    + exception.getMessage(), exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        
	}

}
