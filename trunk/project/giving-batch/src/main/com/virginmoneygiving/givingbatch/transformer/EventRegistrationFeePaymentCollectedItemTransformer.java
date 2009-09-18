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

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.DonationPayment;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentCollected;
import com.virginmoneygiving.givingbatch.domain.FailedPayment;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes the Payment Object and converts into DonationPaymentCollected object
 * which is required by Oracle Finacials.
 * Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 *
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class EventRegistrationFeePaymentCollectedItemTransformer implements
        StepExecutionListener, ItemProcessor<Payment, DonationPaymentCollected> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger
                    .getLogger(EventRegistrationFeePaymentCollectedItemTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;
    
    /** Payment service. * */
    private PaymentService paymentService;
    
    /** Giving Batch helper. */
    private GivingBatchHelper generateSequenceHelper;
    
    /** Payment Failed List. */
    List<FailedPayment> globalPaymentFailedList =
            new ArrayList<FailedPayment>();
    
    /** The Constant EVENT_REGISTRATION_FEE. */
    private static final String EVENT_REGISTRATION_FEE =
        "EVENT_REGISTRATION_FEE";
    
    /** The Constant INVOICE. */
    private static final String INVOICE =
        "INVOICE";

    /** Create instance of batch delegate to inject inside xml. */
    private BatchDelegate batchDelegate;
    
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
     * Sets the batch delegate.
     * 
     * @param batchDelegate the batchDelegate to set
     */
    public void setBatchDelegate(BatchDelegate batchDelegate) {
        this.batchDelegate = batchDelegate;
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
     * This method used to process the payment details.
     * 
     * @param item of type payment
     * 
     * @return DonationPaymentCollected object
     * 
     * @throws Exception process the payment details throws Exception.
     */
    public DonationPaymentCollected process(Payment item) throws GivingBatchException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("START :EventRegistrationFeePaymentCollectedItemProcessor"
                    + item);
        }

        Payment payment = (Payment) item;
        DonationPaymentCollected donationPaymentCollected = null;
        if (payment != null) {
            donationPaymentCollected = new DonationPaymentCollected();
            DonationPayment donationPayment = new DonationPayment();

            donationPayment.setTransactionType(payment.getPaymentType()
                    .getCode());
            donationPayment.setTransactionStatus(payment.getPaymentStatus()
                    .getCode());
            donationPayment.setTransactionDate(payment.getCreatedDateTime());
            //TODO Setting the amount currency to zero as per GLIS requirement.
            donationPayment.setAmountCurrency(new BigDecimal(0));
           
            donationPayment.setAmount(payment.getGrossAmount());
            if (payment.getCardTransaction() != null) {
               // donationPayment.setAmount(payment.getCardTransaction()
                 //       .getTransactionAmount());
            }
            donationPaymentCollected.setInvoiceNumber(payment
                    .getFinanceReference());
            donationPaymentCollected.setCharityReference(payment
                    .getPaymentTarget());
            donationPaymentCollected.setAmountDetails(donationPayment);
            
            if(payment.getTargetBankSortCode() != null
                    && payment.getTargetBankAccount() != null){
            	String bankAccount = Util.getBankAccountNumber(payment.getTargetBankAccount());
            	String bankSortCode = Util.getSortCode(payment.getTargetBankSortCode());
            	if(LOGGER.isDebugEnabled()) {
                	LOGGER.debug("****************The Sort Code*********** :"+bankSortCode);
                	LOGGER.debug("****************The Bank Account Number *********** :"+bankAccount);
            	}
            	donationPaymentCollected.setBankAccountUniqueId(bankSortCode.concat(bankAccount));
            }
        
        
        //Step 2: Here for every charity, we create the BatchEntity record.
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	String batchNumber = (String)context.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
        batchEntity.setEntityId(payment.getId().toString());

        batchEntity.setEntityReference(donationPaymentCollected.getInvoiceNumber());  //HunarC: Added this
        batchEntity.setBaseReference(payment.getFinanceReference());    //HunarC: Added this

        batchEntity.setNextBatchStatus(MasterDataCodeConstants.EventRegistrationFee.EVENT_REG_FEE_PAYMENT_COLLECTED.getCode());
        batchEntity.setPreviousStatus(MasterDataCodeConstants.EventRegistrationFee.EVENT_REG_FEE_PAYMENT_INITIATED.getCode());
        batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch entity : EventRegistrationFeePaymentCollectedItemTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch entity " + exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }

        updateObjectInContext(donationPaymentCollected);
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("END :EventRegistrationFeePaymentCollectedItemProcessor"
                    + item);
        }
        } 
        return donationPaymentCollected;
    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put EVENT_FEE_PAYMENT_COLLECTED_OBJECT_LIST.
     * 
     * @param paymentCollected of type DonationPaymentCollected to add this object to
     * executionContextList.
     */
    @SuppressWarnings("unchecked")
    private void updateObjectInContext(DonationPaymentCollected paymentCollected) {
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<DonationPaymentCollected> eventPaymentCollectedObjectList =
                (List<DonationPaymentCollected>) executionContext
                        .get(Constant.EVENT_FEE_PAYMENT_COLLECTED_OBJECT_LIST);
        if (eventPaymentCollectedObjectList == null) {
            eventPaymentCollectedObjectList =
                    new ArrayList<DonationPaymentCollected>();
            executionContext.put(
                    Constant.EVENT_FEE_PAYMENT_COLLECTED_OBJECT_LIST,
                    eventPaymentCollectedObjectList);
        }
        eventPaymentCollectedObjectList.add(paymentCollected);

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
    @SuppressWarnings("unchecked")
    public ExitStatus afterStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        List<DonationPaymentCollected> registrationFeeList = (List<DonationPaymentCollected>) executionContext
                        .get(Constant.EVENT_FEE_PAYMENT_COLLECTED_OBJECT_LIST);
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
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("inside EventRegistrationFeePaymentCollectedItemTransformer before step  execution context:" + stepExecution);
        }
        this.stepExecution = stepExecution;
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(MasterDataCodeConstants.BatchEventType.EVENT_REG_FEE_PAYMENT_COLLECTED.getCode());
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("eventRegistrationFeePaymentCollectedJob");
        serviceBatch.setNextBatchName("EventRegistrationPaymentSettled");
        createBatchRequest.setServiceBatch(serviceBatch);
        
        try {
        	locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : EventRegistrationFeePaymentCollectedItemTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);

        }
        catch(Exception exception)
        {
            LOGGER.error("Error occured while calling creating batch  " + exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
    }
}
