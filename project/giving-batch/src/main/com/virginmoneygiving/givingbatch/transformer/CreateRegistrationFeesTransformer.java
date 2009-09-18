package com.virginmoneygiving.givingbatch.transformer;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.domain.RegistrationFee;
import com.virginmoneygiving.givingbatch.domain.TaxType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes the Payment object and transforms it to RegistrationFee
 * object for GLIS.
 * Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 *
 * @author Ian Priest
 */
public class CreateRegistrationFeesTransformer implements
		StepExecutionListener, ItemProcessor<Payment, RegistrationFee> {

	/** Create instance of Logger to show Log information. */
	private static Logger LOGGER = Logger
			.getLogger(CreateRegistrationFeesTransformer.class);

	/** Declaring instance of Execution Context. */
	private StepExecution stepExecution;
	
	   /** Instance of GivingBatchHelper. * */
    private GivingBatchHelper generateSequenceHelper;

	/** GivingBatchExtManagementServiceLocatorImpl. * */
	private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

	   /** Payment service. * */
    private PaymentService paymentService;

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
	 * Process the payment details.
	 * 
	 * @param payment of type payment
	 * 
	 * @return RegistrationFee object
	 * 
	 * @throws if processing the payment details throws Exception.
	 * @throws Exception the exception
	 */
	public RegistrationFee process(Payment payment) throws GivingBatchException {
	    if(LOGGER.isDebugEnabled()) {
	        LOGGER.debug("In CreateRegistrationFeesTransformer" + payment);
	    }

		ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
    	
		RegistrationFee registrationFee = new RegistrationFee();

		// Doesn't seem to be needed in the use-case
		// registrationFee.setBankAccountUniqueId();

		registrationFee.setCharityReference(payment.getPaymentSource());

		// Not needed
		// registrationFee.setEventRef();

		/*
		 * Set up the payment type details (Why is it called FeeDetails on the
		 * RegistrationFee object?!)
		 */
		PaymentType paymentType = new PaymentType();
		registrationFee.setFeeDetails(paymentType);

        String invoiceOrCreditNoteNumber = payment.getFinanceReference();

        registrationFee.getFeeDetails().setAmount(payment.getGrossAmount());
		// registrationFee.getFeeDetails().getReferenceId(); // Doesn't seem to
		// be needed
		// For some reason the date is going to glis as null. Is it not getting
		// selected in Payment?
		registrationFee.getFeeDetails().setTransactionDate(
				new Date(payment.getCreatedDateTime().getTime()));
		registrationFee.getFeeDetails().setTransactionStatus(
				payment.getPaymentStatus().getCode());
		registrationFee.getFeeDetails().setTransactionType(
				payment.getPaymentType().getCode());

		// Invoice number. An unexplained change of name again....
		registrationFee.setInvoiceNumber(payment.getFinanceReference());
		if(payment.getPaymentStatus().getCode().equals("CRFPWAIVED")){
		    String creditNoteNumber = getGeneratedCreditNoteNumber(paymentService);
		    registrationFee.setCreditNoteNumber(creditNoteNumber);
		    saveCreditNoteNumber(payment.getId(), creditNoteNumber);
            invoiceOrCreditNoteNumber = creditNoteNumber;
        }
        if (payment.getTargetBankAccount() != null
                && payment.getTargetBankSortCode() != null) {
        	registrationFee.setBankAccountUniqueId(payment
                    .getTargetBankSortCode().concat(":").concat(
                            payment.getTargetBankAccount()));
        }

		/*
		 * Set up the Tax Type What happens if there is more than one VAT in the
		 * list? TODO: Where do I get VAT rate from?
		 */
		Set<VatAmount> vatAmounts = payment.getVatAmount();
		TaxType vatType = new TaxType();
		
		for (VatAmount vatAmount : vatAmounts) {
		    
			LOGGER.debug("In CreateRegistrationFeesTransformer VAT Details :" +vatAmount.toString());
			if (vatAmount.getVatCode() != null){
				vatType.setCode(vatAmount.getVatCode().getCode());
				vatType.setRate(vatAmount.getVatCode().getRate());
			}
			vatType.setAmount(vatAmount.getVatAmount());
			vatType.setStatus(vatAmount.getVatStatus().getCode());
			registrationFee.setVatDetails(vatType);
			CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
	        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
	        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
	        batchEntity.setEntityId(vatAmount.getId().toString());
	        if(payment.getPaymentStatus().getCode().equals(
	                MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED.getCode())){
	            batchEntity.setNextBatchStatus(MasterDataCodeConstants.VAT.VAT_CRF_INITIATED.getCode());
	        }
           if(payment.getPaymentStatus().getCode().equals(
                   MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_WAIVED.getCode())){
                batchEntity.setNextBatchStatus(MasterDataCodeConstants.VAT.VAT_CRF_WAIVED.getCode());
            }
	        batchEntity.setPreviousStatus(null);
	        batchEntity.setEntityTypeCode(Constant.ENTITY_VAT);
	        ServiceBatch serviceBatch = new ServiceBatch();
	        serviceBatch.setBatchNumber(batchNumber);

            batchEntity.setEntityReference(invoiceOrCreditNoteNumber);  //HunarC: Added this
            batchEntity.setBaseReference(vatAmount.getFinanceReference());    //HunarC: Added this            

            batchEntity.setBatch(serviceBatch);
	        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
	        try {
	            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
	        }
	        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
	            LOGGER.error("Error occurred while creating batch vat entity : CreateRegistrationFeesTransformer."
	                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
	            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

	         }
	        catch(Exception exception)
	        {
	            LOGGER.error("Error occured while calling creating batch vat entity  " + exception.getMessage(),exception);
	            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
	        }
	   	
		}
		
		//Step 2: Here for every charity, we create the BatchEntity record.
        
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(payment.getId().toString());
        if(payment.getPaymentStatus().getCode().equals(
                MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED.getCode())){
            batchEntity.setNextBatchStatus(
                    MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED.getCode());
        }
        if(payment.getPaymentStatus().getCode().equals(
                MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_WAIVED.getCode())){
            batchEntity.setNextBatchStatus(MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_WAIVED.getCode());
        }
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);
        batchEntity.setEntityReference(invoiceOrCreditNoteNumber);  //HunarC: Added this
        batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this          
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch payment entity : CreateRegistrationFeesTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch payment entity " + exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
        
		// Add to job context
		updateObjectInContext(registrationFee);

		return registrationFee;
	}

	/**
	 * Save credit note number.
	 * 
	 * @param id the id
	 * @param summaryInvoiceNumber the summary invoice number
	 */
   	public void saveCreditNoteNumber(Long id, String summaryInvoiceNumber) {
	        paymentService.saveCreditNoteNumber(id, summaryInvoiceNumber);
	}

	/**
	 * Add the transformed object to the Job context.
	 * 
	 * @param registrationFee of type registrationFee to add this object to
	 * executionContextList.
	 */
	@SuppressWarnings("unchecked")
	private void updateObjectInContext(RegistrationFee registrationFee) {

		ExecutionContext executionContext = stepExecution.getJobExecution()
				.getExecutionContext();

		List<RegistrationFee> registrationFeeList = (List<RegistrationFee>) executionContext
				.get(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST);
		if (registrationFeeList == null) {

			registrationFeeList = new ArrayList<RegistrationFee>();
			executionContext.put(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST,
					registrationFeeList);
		}
		registrationFeeList.add(registrationFee);
	}

    /**
     * Gets the generated credit note number.
     * 
     * @param paymentService the payment service
     * 
     * @return the generated credit note number
     */
    public String getGeneratedCreditNoteNumber(PaymentService paymentService) {
        String generateSequesnceNumber = paymentService.generateSequence(
                "CHARITY_REGISTRATION_FEE", "CREDIT_NOTE");
        String referenceNumber = generateSequenceHelper.prefixReferenceType(
                 "CHARITY_REGISTRATION_FEE", "CREDIT_NOTE",
                  generateSequesnceNumber);
        LOGGER.debug("generate sequence number is" + referenceNumber);
    
        return referenceNumber;
     }
       
    /**
     * Sets the generate sequence helper.
     * 
     * @param generateSequenceHelper the generateSequenceHelper to set
     */
    public void setGenerateSequenceHelper(
            GivingBatchHelper generateSequenceHelper) {
        this.generateSequenceHelper = generateSequenceHelper;
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
        List<RegistrationFee> registrationFeeList = (List<RegistrationFee>) executionContext
                        .get(Constant.CREATE_REGISTRATION_FEE_OBJECT_LIST);
        if (registrationFeeList == null || registrationFeeList.size() < 1) {
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
		this.stepExecution = stepExecution;
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        // Step 1: Here before step starts, we first create the Batch record.
		CreateBatchRequest createBatchRequest = new CreateBatchRequest();
		String batchNumber = Util.getGeneratedBatchNumber(
                        MasterDataCodeConstants.CharityRegistrationFee.CHARITY_REG_FEE_PAYMENT_INITIATED.getCode());
		ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("createRegistrationFeesJob");
        serviceBatch.setNextBatchName("CharityRegistrationFeeCollected");
		createBatchRequest.setServiceBatch(serviceBatch);

		try {
			locatorImpl.getGivingBatchExtManagementPort().createBatch(
					createBatchRequest);

			context.put(Constant.BATCH_NUMBER, batchNumber);
		} 
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : CreateRegistrationFeesTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

         }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch  " + exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }

	}
	
	   /**
   	 * Sets the payment service.
   	 * 
   	 * @param paymentService the paymentService to set
   	 */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
