package com.virginmoneygiving.givingbatch.transformer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.FailedPayment;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes the Payment object and transform it to
 * EventRegistrationPaymentFailed object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Yogesh Salunkhe
 */
public class EventRegistrationPaymentFailedTransformer implements
		StepExecutionListener, ItemProcessor<Payment, FailedPayment> {
	
	/** Create instance of Logger to show Log information. */
	private static Logger LOGGER = Logger
			.getLogger(EventRegistrationPaymentFailedTransformer.class);

	/** Payment service. * */
	private PaymentService paymentService;

	/** Declaring instance of Execution Context. */
	private StepExecution stepExecution;

	/** instance for ExecutionContext. * */
	private ExecutionContext executionContext;

	/** The card type. */
	private static String CARD_TYPE = "PreviousCardType";

	/** Instance of GivingBatchHelper. * */
	private GivingBatchHelper generateSequenceHelper;

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
	 * This method used to process the payment details.
	 * 
	 * @param payment of type payment
	 * 
	 * @return EventRegistrationPaymentFailed object
	 * 
	 * @throws Exception process the payment details throws Exception.
	 */
	public FailedPayment process(Payment payment) throws GivingBatchException {
	    if(LOGGER.isDebugEnabled()) {
	        LOGGER.debug("In EventRegistrationPaymentFailedTransformer" + payment);
	    }
		String batchNumber = (String) executionContext
				.get(Constant.BATCH_NUMBER);
		if(LOGGER.isDebugEnabled()) {
    		LOGGER
    				.debug("################################ BATCH NUMBER #######################:"
    						+ batchNumber);
		}
		FailedPayment failedPayment = new FailedPayment();
		Map<String, FailedPayment> map = new HashMap<String, FailedPayment>();
		if (payment != null) {
			PaymentType paymentType = new PaymentType();

			paymentType.setTransactionType(payment.getPaymentType().getCode());
			paymentType.setTransactionStatus(payment.getPaymentStatus()
					.getCode());
			paymentType.setAmount(payment.getGrossAmount());

			if (payment.getCardTransaction() != null) {
//				paymentType.setAmount(payment.getCardTransaction()
//						.getTransactionAmount());
				paymentType.setTransactionDate(payment.getCardTransaction()
						.getTransactionDateTime());
				failedPayment.setCardType(payment.getCardTransaction()
						.getCardType());
			}
			failedPayment.setPaymentType(paymentType);

			if (payment.getTargetBankSortCode() != null
					&& payment.getTargetBankAccount() != null) {
				String bankAccount = Util.getBankAccountNumber(payment
						.getTargetBankAccount());
				String bankSortCode = Util.getSortCode(payment
						.getTargetBankSortCode());
				if(LOGGER.isDebugEnabled()) {
    				LOGGER.debug("The Sort Code:" + bankSortCode);
    				LOGGER.debug("The Bank Account Number :" + bankAccount);
				}
				failedPayment.setBankAccountUniqueId(bankSortCode
						.concat(bankAccount));
			}

		

		String cardType = failedPayment.getCardType();
        String entityReference = "";  // HunarC-Added this
        map = getSummaryMap();
		if (map != null) {
			if (map.containsKey(cardType)) {
				FailedPayment initiated = map.get(cardType);
				if(initiated != null){  //Null check 13051 ::TarunA
				PaymentType paymentType2 = initiated.getPaymentType();
				BigDecimal amount = paymentType2.getAmount();
				paymentType2.setAmount(amount.add(failedPayment.getPaymentType()
						.getAmount()));
				initiated.setPaymentType(paymentType2);
                entityReference = initiated.getCreditNoteNumber();
                //saveSummaryCreditNoteNumber(payment.getId(), initiated.getInvoiceNumber());
                saveSummaryCreditNoteNumber(payment.getId(), initiated.getCreditNoteNumber());
                updateObjectInContext(initiated);
				}
            } else {
				String invoiceNumber = getGeneratedInvoiceNumber(paymentService);
				failedPayment.setCreditNoteNumber(invoiceNumber);
                entityReference = invoiceNumber;
                saveSummaryCreditNoteNumber(payment.getId(), invoiceNumber);
				updateObjectInContext(failedPayment);
			}
		} else {
			String invoiceNumber = getGeneratedInvoiceNumber(paymentService);
			failedPayment.setCreditNoteNumber(invoiceNumber);
            entityReference = invoiceNumber;
            saveSummaryCreditNoteNumber(payment.getId(), invoiceNumber);
			updateObjectInContext(failedPayment);
		}

		// Step 2: Here for every charity, we create the BatchEntity record.
		CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
		ServiceBatchEntity batchEntity = new ServiceBatchEntity();
		batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
		batchEntity.setEntityId(payment.getId().toString());

        batchEntity.setEntityReference(entityReference);  //HunarC: Added this
        batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this        

        batchEntity
				.setNextBatchStatus(MasterDataCodeConstants.EventRegistrationFee.EVENT_REG_FEE_PAYMENT_FAILED
						.getCode());
		batchEntity.setPreviousStatus(null);
		batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);
		ServiceBatch serviceBatch = new ServiceBatch();
		serviceBatch.setBatchNumber(batchNumber);
		batchEntity.setBatch(serviceBatch);
		createBatchEntityRequest.setServiceBatchEntity(batchEntity);
		try {
		    locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(
				createBatchEntityRequest);
		}
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch entity : EventRegistrationPaymentFailedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
		}//Null check 13051 ::TarunA
		return failedPayment;
	}

	/**
	 * Save summary credit note number.
	 * 
	 * @param id the id
	 * @param summaryInvoiceNumber the summary invoice number
	 */
	public void saveSummaryCreditNoteNumber(Long id, String summaryInvoiceNumber) {
		paymentService.saveSummaryCreditNoteNumber(id, summaryInvoiceNumber);
	}

	/**
	 * Gets the generated invoice number.
	 * 
	 * @param paymentService the payment service
	 * 
	 * @return the generated invoice number
	 */
	public String getGeneratedInvoiceNumber(PaymentService paymentService) {
		String generateSequesnceNumber = paymentService.generateSequence(
				"EVENT_REGISTRATION_FEE", "CREDIT_NOTE");
		String referenceNumber = generateSequenceHelper.prefixReferenceType(
				"EVENT_REGISTRATION_FEE", "CREDIT_NOTE",
				generateSequesnceNumber);
		LOGGER.debug("generate sequence number is" + referenceNumber);

		return referenceNumber;
	}

	/**
	 * Gets the summary map.
	 * 
	 * @return the summary map
	 */
	private Map<String, FailedPayment> getSummaryMap() {
		Map<String, FailedPayment> map = null;
		if (executionContext != null) {
			map = (Map<String, FailedPayment>) executionContext
					.get(Constant.SUMMARY_MAP);

		}
		return map;
	}

	/**
	 * This method is used for to get the execution context.If it is null then
	 * put SummaryMap with cardtype as key and FailedPayment object as value in the execution context.
	 * 
	 * @param failedPayment of type PaymentInitiated to add this object to
	 * executionContextList.
	 */
	private void updateObjectInContext(FailedPayment failedPayment) {
	    if(LOGGER.isTraceEnabled()) {
    		LOGGER
    				.trace("Inside EventRegistrationPaymentFailedTransformer UpdateObjectContext -Start");
	    }
		if (executionContext != null) {
			Map<String, FailedPayment> summaryMap = (Map<String, FailedPayment>) executionContext
					.get(Constant.SUMMARY_MAP);
			if (summaryMap == null) {
				summaryMap = new HashMap<String, FailedPayment>();
				summaryMap.put(failedPayment.getCardType(), failedPayment);
			} else {
				summaryMap.put(failedPayment.getCardType(), failedPayment);
			}
			executionContext.put(Constant.SUMMARY_MAP, summaryMap);

		}
		 if(LOGGER.isTraceEnabled()) {
    		LOGGER
    				.trace("Inside EventRegistrationPaymentFailedTransformer UpdateObjectContext End.");
		 }

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
        //ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        Map<String, FailedPayment> summaryMap = (Map<String, FailedPayment>) executionContext
                        .get(Constant.SUMMARY_MAP);
        if (summaryMap == null || summaryMap.size() < 1) {
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
		// this.stepExecution = stepExecution;
		//executionContext = stepExecution.getExecutionContext();
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        executionContext = stepExecution.getJobExecution().getExecutionContext();
        // Step 1: Here before step starts, we first create the Batch record.
		CreateBatchRequest createBatchRequest = new CreateBatchRequest();
		String batchNumber = Util
				.getGeneratedBatchNumber(MasterDataCodeConstants.EventRegistrationFee.EVENT_REG_FEE_PAYMENT_FAILED
						.getCode());
		ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("eventRegistrationPaymentFailedJob");
        serviceBatch.setNextBatchName("INDEPENDENT");
		createBatchRequest.setServiceBatch(serviceBatch);

		try {
			locatorImpl.getGivingBatchExtManagementPort().createBatch(
					createBatchRequest);
			executionContext.put(Constant.BATCH_NUMBER, batchNumber);
		} 
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : EventRegistrationPaymentFailedTransformer."
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
	 * Sets the generate sequence helper.
	 * 
	 * @param generateSequenceHelper the generateSequenceHelper to set
	 */
	public void setGenerateSequenceHelper(
			GivingBatchHelper generateSequenceHelper) {
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

}
