package com.virginmoneygiving.givingbatch.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.domain.DonationPayment;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollected;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.domain.Payment;

/**
 * This class takes Payment object and transform it to DonationPaymentCollected
 * object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Tarun Adiwal
 */
public class PaymentCollectedItemTransformer implements StepExecutionListener,
        ItemProcessor<Payment, DonationPaymentCollected> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(PaymentCollectedItemTransformer.class);

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
     * This method used to process the payment details.
     * 
     * @param item of type payment
     * 
     * @return TransactionFeePaid object
     * 
     * @throws Exception process the payment details throws Exception.
     */
    public DonationPaymentCollected process(Payment item) throws GivingBatchException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("START :PaymentCollectedItemTransformer" + item);
        }
        Payment payment = (Payment) item;
        DonationPaymentCollected webDonationRecord = null;

        if (payment != null) {
            webDonationRecord = new DonationPaymentCollected();
            DonationPayment donationPayment = new DonationPayment();
            donationPayment.setTransactionType(payment.getPaymentType()
                    .getCode());
            donationPayment.setTransactionStatus(payment.getPaymentStatus()
                    .getCode());
            //TODO Setting the amount currency to zero as per GLIS requirement.
            donationPayment.setAmountCurrency(new BigDecimal(0));    
            // TODO: paymentType --> referenceId need to confirm.
            // donationPayment.setReferenceId(referenceId)

            if (payment.getCardTransaction() != null) {
                donationPayment.setTransactionDate(payment.getCardTransaction()
                        .getCreatedDateTime());

                donationPayment.setAmount(payment.getCardTransaction().getTransactionAmount());
            }
            // HunarC Change: Added the following line to set the PAYMENT AMOUNT as the donation amount.
            // This overrides the earlier statement where the CARD TRANSACTION AMOUNT is being set as the
            // donation amount, which is wrong because:
            // (a) A card payment can be split across multiple charity donations
            // (b) This process is creating a payment record per donation split for GLIS
            // (c) By setting the card amount as the donation amount for each payment transaction sent to GLIS,
            //     we end up sending the TOTAL amount several times to GLIS, instead of individual donation amounts.
            donationPayment.setAmount(payment.getGrossAmount());

            webDonationRecord.setInvoiceNumber(payment.getFinanceReference());
            webDonationRecord.setCharityReference(payment.getPaymentTarget());
            webDonationRecord.setAmountDetails(donationPayment);
            
            if (payment.getTargetBankSortCode() != null
					&& payment.getTargetBankAccount() != null) {
				String bankAccount = Util.getBankAccountNumber(payment
						.getTargetBankAccount());
				String bankSortCode = Util.getSortCode(payment
						.getTargetBankSortCode());
				LOGGER.debug("The Sort Code:" + bankSortCode);
				LOGGER.debug("The Bank Account Number :" + bankAccount);
				webDonationRecord.setBankAccountUniqueId(bankSortCode
						.concat(bankAccount));
			}
                        
        
        //Step 2: Here for every charity, we create the BatchEntity record.
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(payment.getId().toString());
        batchEntity.setNextBatchStatus(MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_COLLECTED.getCode());
        batchEntity.setPreviousStatus(MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED.getCode());
        batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);

        batchEntity.setEntityReference(payment.getFinanceReference());  //HunarC: Added this
        batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch Payment Entity : PaymentCollectedItemTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch Payment Entity "+exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }

        updateObjectInContext(webDonationRecord);
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("END :PaymentCollectedItemTransformer" + item);
        }
        return webDonationRecord;
    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put paymentCollectedObjectList.
     * 
     * @param donationPaymentCollected of type DonationPaymentCollected to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(
            DonationPaymentCollected donationPaymentCollected) {
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<DonationPaymentCollected> paymentCollectedObjectList =
                (List<DonationPaymentCollected>) executionContext
                        .get(Constant.PAYMENT_COLLECTED_OBJECT_LIST);
        if (paymentCollectedObjectList == null) {
            paymentCollectedObjectList =
                    new ArrayList<DonationPaymentCollected>();
            executionContext.put(Constant.PAYMENT_COLLECTED_OBJECT_LIST,
                    paymentCollectedObjectList);
        }
        paymentCollectedObjectList.add(donationPaymentCollected);

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
        List<DonationPaymentCollected> tList = (List<DonationPaymentCollected>) executionContext
                        .get(Constant.PAYMENT_COLLECTED_OBJECT_LIST);
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
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.PAYMENT_COLLECTED);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);

        serviceBatch.setCurrentJobName("paymentCollectedJob");
        serviceBatch.setNextBatchName("PaymentSettled");
        createBatchRequest.setServiceBatch(serviceBatch);
        
        try {
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : PaymentCollectedItemTransformer."
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
