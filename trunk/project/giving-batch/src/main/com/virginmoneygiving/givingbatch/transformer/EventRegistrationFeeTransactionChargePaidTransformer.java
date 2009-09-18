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
import com.virginmoneygiving.givingbatch.domain.TransactionChargePaid;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.CardCharge;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.domain.VatAmount;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes Payment object and transforms it to TransactionChargePaid
 * object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Tarun Adiwal
 */
public class EventRegistrationFeeTransactionChargePaidTransformer implements
		StepExecutionListener, ItemProcessor<Payment, TransactionChargePaid> {

	/** Create instance of Logger to show Log information. */
	private static Logger LOGGER = Logger
			.getLogger(TransactionChargePaidTransformer.class);

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

	/**
	 * This method used to process the payment details.
	 * 
	 * @param payment of type payment
	 * 
	 * @return TransactionChargePaid object
	 * 
	 * @throws Exception process the payment details throws Exception.
	 */
	public TransactionChargePaid process(Payment payment) throws GivingBatchException {
	    if(LOGGER.isTraceEnabled()) {
    		LOGGER
    				.trace("START:EventRegistrationFeeTransactionChargePaidTransformer");
	    }
		TaxType taxType = null;
		Set<VatAmount> vatAmountList = null;
		Set<CardCharge> cardChargeList = null;
		TransactionChargePaid chargePaid = null;

		if (payment != null) {

			cardChargeList = payment.getCardCharge();
			chargePaid = new TransactionChargePaid();
			PaymentType paymentType = new PaymentType();
			for (CardCharge cardCharge : cardChargeList) {
				vatAmountList = cardCharge.getVatAmount();
				paymentType.setAmount(cardCharge.getAmount());
				paymentType.setTransactionDate(cardCharge.getCreatedDateTime());
				paymentType.setTransactionType(payment.getPaymentType()
						.getCode());
				paymentType.setTransactionStatus(cardCharge
						.getCardChargeStatus().getCode());

				// Step 2: Here for every charity, we create the BatchEntity
				// record.
				ExecutionContext context = stepExecution.getJobExecution()
						.getExecutionContext();
				String batchNumber = (String) context
						.get(Constant.BATCH_NUMBER);
				CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
				ServiceBatchEntity batchEntity = new ServiceBatchEntity();
				batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
				batchEntity.setEntityId(cardCharge.getId().toString());
				batchEntity
						.setNextBatchStatus(MasterDataCodeConstants.TransactionCharge.TRASACTION_CHARGE_PAID
								.getCode());
				batchEntity.setPreviousStatus(null);
				batchEntity.setEntityTypeCode(Constant.ENTITY_CARD_CHARGE);
                batchEntity.setEntityReference(cardCharge.getFinanceReference());  //HunarC: Added this
                batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this
                ServiceBatch serviceBatch = new ServiceBatch();
				serviceBatch.setBatchNumber(batchNumber);
				batchEntity.setBatch(serviceBatch);
				createBatchEntityRequest.setServiceBatchEntity(batchEntity);
				try {
				    locatorImpl.getGivingBatchExtManagementPort()
						.createBatchEntity(createBatchEntityRequest);
				}
		        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
		            LOGGER.error("Error occurred while creating batch Card Charge entity: " +
		            		"EventRegistrationFeeTransactionChargePaidTransformer."
		                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
		            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
		        }
                catch (Exception exception) {
                    LOGGER.error("Error occured while calling creating batch entity " + exception.getMessage(),exception);
                    throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
                }

				taxType = new TaxType();

				chargePaid
						.setCreditNoteNumber(cardCharge.getFinanceReference());
				chargePaid.setInvoiceNumber(cardCharge.getFinanceReference());
				for (VatAmount vatAmount : vatAmountList) {
					taxType.setAmount(vatAmount.getVatAmount());
					if (vatAmount.getVatCode() != null) {
						taxType.setCode(vatAmount.getVatCode().getCode());
						taxType.setRate(vatAmount.getVatCode().getRate());
					}

					taxType.setStatus(vatAmount.getVatStatus().getCode());
					chargePaid.setVatDetails(taxType);
					CreateBatchEntityRequest createBatchEntityRequestVat = new CreateBatchEntityRequest();
					ServiceBatchEntity batchEntityVat = new ServiceBatchEntity();
					batchEntityVat.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
					batchEntityVat.setEntityId(vatAmount.getId().toString());
					batchEntityVat
							.setNextBatchStatus(MasterDataCodeConstants.VAT.VAT_CARD_CHARGE_PAID
									.getCode());
					batchEntityVat.setPreviousStatus(null);
					batchEntityVat.setEntityTypeCode(Constant.ENTITY_VAT);
                    batchEntityVat.setEntityReference(cardCharge.getFinanceReference());  //HunarC: Added this
                    batchEntityVat.setBaseReference(payment.getFinanceReference());    //HunarC: Added this
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
	                    LOGGER.error("Error occurred while creating batch Card Charge vat entity: " +
	                            "EventRegistrationFeeTransactionChargePaidTransformer."
	                                + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
	                    throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
	                }
                    catch (Exception exception) {
                        LOGGER.error("Error occured while calling creating batch  " + exception.getMessage(),exception);
                        throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
                    }
				}

			}
			chargePaid.setChargeAmountDetails(paymentType);
			chargePaid.setCharityReference(payment.getPaymentTarget());
			
			if (payment.getTargetBankSortCode() != null
					&& payment.getTargetBankAccount() != null) {
				String bankAccount = Util.getBankAccountNumber(payment
						.getTargetBankAccount());
				String bankSortCode = Util.getSortCode(payment
						.getTargetBankSortCode());
				LOGGER.debug("The Sort Code:" + bankSortCode);
				LOGGER.debug("The Bank Account Number :" + bankAccount);
				chargePaid.setBankAccountUniqueId(bankSortCode
						.concat(bankAccount));
			}


		}

		updateObjectInContext(chargePaid);
		 if(LOGGER.isTraceEnabled()) {
    		LOGGER
    				.trace("END:EventRegistrationFeeTransactionChargePaidTransformer");
		 }
		return chargePaid;
	}

	/**
	 * This method is used for to get the execution context.If it is null then
	 * put EVENT_FEE_TRANSACTION_CHARGE_PAID_OBJECT_LIST.
	 * 
	 * @param transactionChargePaid of type TransactionChargePaid to add this object to
	 * executionContextList.
	 */
	private void updateObjectInContext(
			TransactionChargePaid transactionChargePaid) {
		ExecutionContext executionContext = stepExecution.getJobExecution()
				.getExecutionContext();
		List<TransactionChargePaid> transactionChargePaidList = (List<TransactionChargePaid>) executionContext
				.get(Constant.EVENT_FEE_TRANSACTION_CHARGE_PAID_OBJECT_LIST);
		if (transactionChargePaidList == null) {
			transactionChargePaidList = new ArrayList<TransactionChargePaid>();
			executionContext.put(
					Constant.EVENT_FEE_TRANSACTION_CHARGE_PAID_OBJECT_LIST,
					transactionChargePaidList);
		}
		transactionChargePaidList.add(transactionChargePaid);
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
        List<TransactionChargePaid> registrationFeeList = (List<TransactionChargePaid>) executionContext
                        .get(Constant.EVENT_FEE_TRANSACTION_CHARGE_PAID_OBJECT_LIST);
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
            catch(GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException){
                LOGGER.error("Error occurred while updating batch status in Card Charge vat entity: " +
                        "EventRegistrationFeeTransactionChargePaidTransformer."
                            + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
                throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
            }
            catch(Exception exception){
                LOGGER.error("Error occurred while updating batch status in Card Charge vat entity: " +
                        "EventRegistrationFeeTransactionChargePaidTransformer."
                            + exception.getMessage(), exception);
                throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
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
		// Step 1: Here before step starts, we first create the Batch record.
		CreateBatchRequest createBatchRequest = new CreateBatchRequest();
		String batchNumber = Util
				.getGeneratedBatchNumber(MasterDataCodeConstants.BatchEventType.EVENT_REG_FEE_TRANSACTION_CHARGE_PAID
						.getCode());
		ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("eventRegistrationFeeTransactionChargePaidJob");
        serviceBatch.setNextBatchName("TransactionChargeSettled");
        createBatchRequest.setServiceBatch(serviceBatch);

		try {
			locatorImpl.getGivingBatchExtManagementPort().createBatch(
					createBatchRequest);
			context.put(Constant.BATCH_NUMBER, batchNumber);
		} 
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : EventRegistrationFeeTransactionChargePaidTransformer."
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
