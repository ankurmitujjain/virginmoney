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
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.domain.TaxType;
import com.virginmoneygiving.givingbatch.domain.TransactionFeePaid;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.HandlingCharge;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes Payment object and transform it to TransactionFeePaid
 * object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Tarun Adiwal
 */
public class TransactionFeePaidTransformer implements StepExecutionListener,
		ItemProcessor<Payment, TransactionFeePaid> {

	/** Create instance of Logger to show Log information. */
	private static Logger LOGGER = Logger
			.getLogger(TransactionFeePaidTransformer.class);

	/** Declaring instance of Execution Context. */
	private StepExecution stepExecution;

	/** Payment service. * */
	private PaymentService paymentService;

	/** Giving Batch helper. */
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

	/** The Constant VMG_TRANSACTION_FEE. */
	private static final String VMG_TRANSACTION_FEE = "VMG_TRANSACTION_FEE";

	/**
	 * This method used to process the payment details.
	 * 
	 * @param payment of type payment
	 * 
	 * @return TransactionFeePaid object
	 * 
	 * @throws Exception process the payment details throws Exception.
	 */
	public TransactionFeePaid process(Payment payment) throws GivingBatchException {
	    if(LOGGER.isTraceEnabled()) {
	        LOGGER.trace("TransactionFeePaidTransformer - Start");
	    }
		HandlingCharge handlingCharge = null;
		VatAmount vatAmount = null;
		TaxType taxType = null;
		Set<VatAmount> vatAmountList = null;
		Set<HandlingCharge> handlingChargeSet = null;
		TransactionFeePaid feePaid = null;
		ExecutionContext context = stepExecution.getJobExecution()
				.getExecutionContext();
		String batchNumber = (String) context.get(Constant.BATCH_NUMBER);
		if (payment != null) {
			handlingChargeSet = payment.getHandlingCharge();
			feePaid = new TransactionFeePaid();
			PaymentType paymentType = new PaymentType();
			for (HandlingCharge handlingChargeIterator : handlingChargeSet) {
				vatAmountList = handlingChargeIterator.getVatAmount();
				paymentType.setAmount(handlingChargeIterator
						.getHandlingChargeAmount());
				paymentType.setTransactionDate(payment.getCreatedDateTime());
				paymentType.setTransactionType(payment.getPaymentType()
						.getCode());
				paymentType.setTransactionStatus(handlingChargeIterator
						.getHandlingChargeStatus().getCode());
				feePaid.setCreditNoteNumber(handlingChargeIterator
						.getFinanceReference());
				feePaid
						.setInvoiceNumber(handlingChargeIterator
								.getFinanceReference());

				CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
				ServiceBatchEntity batchEntity = new ServiceBatchEntity();
				batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
				batchEntity.setEntityId(handlingChargeIterator.getId().toString());
				batchEntity
						.setNextBatchStatus(MasterDataCodeConstants.VMGTranscationFee.VMG_TRASACTION_FEE_PAID
								.getCode());
				batchEntity.setPreviousStatus(null);

                batchEntity.setEntityReference(handlingChargeIterator.getFinanceReference());  //HunarC: Added this
                batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

                batchEntity.setEntityTypeCode(Constant.ENTITY_HANDLING_CHARGE);
				ServiceBatch serviceBatch = new ServiceBatch();
				serviceBatch.setBatchNumber(batchNumber);
				batchEntity.setBatch(serviceBatch);
				createBatchEntityRequest.setServiceBatchEntity(batchEntity);
				try {
				    locatorImpl.getGivingBatchExtManagementPort()
						.createBatchEntity(createBatchEntityRequest);
				}
		        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
		            LOGGER.error("Error occurred while creating batch Handling Charge Entity : TransactionFeePaidTransformer."
		                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
		            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
		        }
		        catch(Exception exception)
		        {
		            LOGGER.error("Error occured while calling creating batch Handling Charge Entity "+exception.getMessage(),exception);
		        }


				taxType = new TaxType();
				for (VatAmount vatAmountIterator : vatAmountList) {
					taxType.setAmount(vatAmountIterator.getVatAmount());
					taxType.setStatus(vatAmountIterator.getVatStatus().getCode());
					if (vatAmountIterator.getVatCode() != null) {
						taxType.setCode(vatAmountIterator.getVatCode().getCode());
						taxType.setRate(vatAmountIterator.getVatCode().getRate());
					}

					feePaid.setVatDetails(taxType);
					CreateBatchEntityRequest createBatchEntityRequestVat = new CreateBatchEntityRequest();
					ServiceBatchEntity batchEntityVat = new ServiceBatchEntity();
					batchEntityVat.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
					batchEntityVat.setEntityId(vatAmountIterator.getId().toString());
					batchEntityVat
							.setNextBatchStatus(MasterDataCodeConstants.VAT.VAT_HANDLING_CHARGE_PAID
									.getCode());
					batchEntityVat.setPreviousStatus(null);

                    batchEntityVat.setEntityReference(handlingChargeIterator.getFinanceReference());  //HunarC: Added this
                    batchEntityVat.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

                    batchEntityVat.setEntityTypeCode(Constant.ENTITY_VAT);
					ServiceBatch serviceBatchVat = new ServiceBatch();
					serviceBatchVat.setBatchNumber(batchNumber);
					batchEntityVat.setBatch(serviceBatchVat);
					createBatchEntityRequestVat
							.setServiceBatchEntity(batchEntityVat);
					try {
					    locatorImpl.getGivingBatchExtManagementPort()
							.createBatchEntity(createBatchEntityRequestVat);
					}
			        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
			            LOGGER.error("Error occurred while creating batch Handling Charge VAT Entity : TransactionFeePaidTransformer."
			                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
			            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
			        }
			        catch(Exception exception)
			        {
			            LOGGER.error("Error occured while calling creating batch Handling Charge VAT Entity "+exception.getMessage(),exception);
			            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
			        }

				}

			}

			feePaid.setFeeAmountDetails(paymentType);
			// feePaid.setCreditNoteNumber(payment.getCreditNoteReference());
			feePaid.setCharityReference(payment.getPaymentTarget());
			
			if (payment.getTargetBankSortCode() != null
					&& payment.getTargetBankAccount() != null) {
				String bankAccount = Util.getBankAccountNumber(payment
						.getTargetBankAccount());
				String bankSortCode = Util.getSortCode(payment
						.getTargetBankSortCode());
				LOGGER.debug("The Sort Code:" + bankSortCode);
				LOGGER.debug("The Bank Account Number :" + bankAccount);
				feePaid.setBankAccountUniqueId(bankSortCode
						.concat(bankAccount));
			}

		}

		updateObjectInContext(feePaid);
		 if(LOGGER.isTraceEnabled()) {
		     LOGGER.trace("TransactionFeePaidTransformer - End");
		 }
		return feePaid;
	}

	/**
	 * This method is used for to get the execution context.If it is null then
	 * put transactionFeePaidList.
	 * 
	 * @param transactionFeePaid of type TransactionFeePaid to add this object to
	 * executionContextList.
	 */
	private void updateObjectInContext(TransactionFeePaid transactionFeePaid) {
		LOGGER
				.trace("Inside TransactionFeePaidTransformer UpdateObjectContext -Start");
		ExecutionContext executionContext = stepExecution.getJobExecution()
				.getExecutionContext();

		List<TransactionFeePaid> transactionFeePaidList = (List<TransactionFeePaid>) executionContext
				.get(Constant.TRANSACTION_FEE_PAID_OBJECT_LIST);
		if (transactionFeePaidList == null) {
			transactionFeePaidList = new ArrayList<TransactionFeePaid>();
			executionContext.put(Constant.TRANSACTION_FEE_PAID_OBJECT_LIST,
					transactionFeePaidList);
		}
		transactionFeePaidList.add(transactionFeePaid);
		LOGGER
				.trace("Inside TransactionFeePaidTransformer UpdateObjectContext -End.");
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
        List<TransactionFeePaid> transactionFeePaidList = (List<TransactionFeePaid>) executionContext
                        .get(Constant.TRANSACTION_FEE_PAID_OBJECT_LIST);
        if (transactionFeePaidList == null || transactionFeePaidList.size() < 1) {
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
		LOGGER.debug("inside before step  execution context:" + stepExecution);
		this.stepExecution = stepExecution;
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
		// Step 1: Here before step starts, we first create the Batch record.
		CreateBatchRequest createBatchRequest = new CreateBatchRequest();
		String batchNumber = Util
				.getGeneratedBatchNumber(MasterDataCodeConstants.VMGTranscationFee.VMG_TRASACTION_FEE_PAID
						.getCode());
		ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("transactionFeePaidJob");
        serviceBatch.setNextBatchName("TRANSACTION_FEE_SETTLED");
		createBatchRequest.setServiceBatch(serviceBatch);

		try {
			locatorImpl.getGivingBatchExtManagementPort().createBatch(
					createBatchRequest);
			context.put(Constant.BATCH_NUMBER, batchNumber);
		}
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : TransactionFeePaidTransformer."
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
