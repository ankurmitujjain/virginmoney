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
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.domain.Transaction;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.GiftAidAmount;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes the Payment object return by the Item Reader and transform
 * it to the Transaction object and return the Transaction object.
 * Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Tarun Adiwal
 */
public class GiftAidClaimedTransformer implements StepExecutionListener,
        ItemProcessor<GiftAidAmount, Transaction> {

    /** creating instance of Logger. */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidClaimedTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** Instance of GivingBatchHelper. * */
    private GivingBatchHelper generateSequenceHelper;

    /** constants. * */
    private static String GIFT_AID_DATE = "PreviousGiftAidDate";    

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
     * This method used to process the giftAidAmount details.
     * 
     * @param giftAidAmount of type GiftAidAmount
     * 
     * @return GiftAidReceived object
     * 
     * @throws Exception process the giftAidAmount details throws Exception.
     */
    public Transaction process(GiftAidAmount giftAidAmount) throws GivingBatchException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedTransformer::process() - START");
        }
        Transaction transaction = null;
        if (giftAidAmount != null && giftAidAmount.getPayment() != null) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("START:GiftAidClaimedTransformer for ID/Amount: " + giftAidAmount.getId() + "/"
                        + giftAidAmount.getGiftAidAmount());
            }
            Payment payment = giftAidAmount.getPayment();
            transaction = new Transaction();
            transaction.setAmount(giftAidAmount.getGiftAidAmount());
            transaction.setDate(giftAidAmount.getGiftAidClaimedDate());
            transaction.setCustomer(Constant.CUSTOMER);
            transaction
                    .setClaimPeriodFrom(giftAidAmount.getClaimedPeriodFrom());
            transaction.setClaimPeriodTo(giftAidAmount.getClaimedPeriodTo());
            transaction.setTransactionType(payment.getPaymentType().getCode());
            transaction.setTransactionStatus(giftAidAmount.getGiftAidStatus()
                    .getCode());
            transaction.setInvoiceNumber(giftAidAmount.getFinanceReference());

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
                transaction.setBankAccountUniqueId(bankSortCode
                        .concat(bankAccount));
            }

            // TODO: Need to confirm from business
            // transaction.setEventRef(eventRef);
            LOGGER.debug("GiftAidClaimedTransformer TranInfo for Amount/Invoice: " + transaction.getAmount() + "/"
                    + transaction.getInvoiceNumber());


            String summaryInvoiceNumber = updateObjectInContext(transaction);
            transaction.setGroupInvoiceNumber(summaryInvoiceNumber);
            saveSummaryInvoiceNumber(giftAidAmount.getId(), summaryInvoiceNumber);

            //Step 2: Here for every charity, we create the BatchEntity record.
            ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
            String batchNumber = (String) context.get(Constant.BATCH_NUMBER);
            CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
            ServiceBatchEntity batchEntity = new ServiceBatchEntity();
            batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
            batchEntity.setEntityId(giftAidAmount.getId().toString());

            batchEntity.setEntityReference(summaryInvoiceNumber);  //HunarC: Added this
            batchEntity.setBaseReference(giftAidAmount.getFinanceReference());    //HunarC: Added this

            batchEntity.setNextBatchStatus(MasterDataCodeConstants.GiftAid.GIFT_AID_CLAIMED.getCode());
            batchEntity.setPreviousStatus(null);
            batchEntity.setEntityTypeCode(Constant.ENTITY_GIFTAID);
            ServiceBatch serviceBatch = new ServiceBatch();
            serviceBatch.setBatchNumber(batchNumber);
            batchEntity.setBatch(serviceBatch);
            createBatchEntityRequest.setServiceBatchEntity(batchEntity);
            try {
                locatorImpl.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
            }
            catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
                LOGGER.error("Error occurred while creating batch GiftAid Entity : GiftAidClaimedTransformer."
                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
                throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
            }
            catch (Exception exception) {
                LOGGER.error("Error occured while calling creating batch GiftAid Entity  " + exception.getMessage(),exception);
                throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedTransformer::process() - END");
        }
        return transaction;
    }

    /**
     * HUNARC: This is the new version.
     * This method is used for to get the execution context.If it is null then
     * put GiftAidClaimedObjectList.
     * 
     * @param transaction of type Transaction to add this object to
     * executionContextList.
     * 
     * @return the string
     */
    private String updateObjectInContext(Transaction transaction) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedTransformer::updateObjectInContext() - START");
        }
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        List<Transaction> giftAidClaimedObjectList =
                (List<Transaction>) executionContext.get(Constant.GIFTAID_CLAIMED_OBJECT_LIST);
        String invoiceNumber = transaction.getInvoiceNumber();
        if (giftAidClaimedObjectList == null || giftAidClaimedObjectList.size() == 0) {
            invoiceNumber = getGeneratedInvoiceNumber();
            giftAidClaimedObjectList = new ArrayList<Transaction>();
            transaction.setInvoiceNumber(invoiceNumber);

            Transaction summaryTransaction = new Transaction();
            summaryTransaction.setAmount(transaction.getAmount());
            summaryTransaction.setBankAccountUniqueId(transaction.getBankAccountUniqueId());
            summaryTransaction.setClaimPeriodFrom(transaction.getClaimPeriodFrom());
            summaryTransaction.setClaimPeriodTo(transaction.getClaimPeriodTo());
            summaryTransaction.setCustomer(transaction.getCustomer());
            summaryTransaction.setDate(transaction.getDate());
            summaryTransaction.setEventRef(transaction.getEventRef());
            summaryTransaction.setTransactionStatus(transaction.getTransactionStatus());
            summaryTransaction.setTransactionType(transaction.getTransactionType());
            summaryTransaction.setInvoiceNumber(invoiceNumber);

            giftAidClaimedObjectList.add(summaryTransaction);
            executionContext.put(Constant.GIFTAID_CLAIMED_OBJECT_LIST, giftAidClaimedObjectList);
            LOGGER.debug("GiftAidClaimedTransformer UpdateObjectContext-New Tran Invoice/Amount = "
                    + transaction.getInvoiceNumber() + "/" + transaction.getAmount());
        } else {
            Transaction summaryTran = giftAidClaimedObjectList.get(0);
            invoiceNumber = summaryTran.getInvoiceNumber();
            summaryTran.setAmount(summaryTran.getAmount().add(transaction.getAmount()));
            if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimedTransformer UpdateObjectContext-Update Tran Invoice/Amount = "
                    + summaryTran.getInvoiceNumber() + "/" + summaryTran.getAmount());
            }
        }
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Inside GiftAidClaimedTransformer UpdateObjectContext -End Inv Num = " + invoiceNumber);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedTransformer::updateObjectInContext() - END");
        }
        return invoiceNumber;
    }

    /**
     * HUNARC: This is the Arrk version. However, since we need to club all the transactions
     * and send only one record through to GLIS, this has been over-ridden by the method
     * above, which only creates one record.
     * This method is used for to get the execution context.If it is null then
     * put GiftAidClaimedObjectList.
     * 
     * @param transaction of type Transaction to add this object to
     * executionContextList.
     */
    // Changing method name as per Naming Conventions.
/*    private void updateObjectInContextOld(Transaction transaction) {
        LOGGER
                .trace("Inside GiftAidClaimedTransformer UpdateObjectContext -Start.");
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<Transaction> giftAidClaimedObjectList =
                (List<Transaction>) executionContext
                        .get(Constant.GIFTAID_CLAIMED_OBJECT_LIST);
        if (giftAidClaimedObjectList == null) {
            giftAidClaimedObjectList = new ArrayList<Transaction>();
            executionContext.put(Constant.GIFTAID_CLAIMED_OBJECT_LIST,
                    giftAidClaimedObjectList);
        }
        giftAidClaimedObjectList.add(transaction);
        LOGGER
                .trace("Inside GiftAidClaimedTransformer UpdateObjectContext -End.");
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
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedTransformer:afterStep START. ");
        }
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        String batchNumber = (String) executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        List<Transaction> tList = (List<Transaction>) executionContext
                .get(Constant.GIFTAID_CLAIMED_OBJECT_LIST);
        if (tList == null || tList.size() < 1) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("GiftAidClaimedTransformer:inside afterStep method - No matching records found ... setting batch status to completed.");
            }
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
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedTransformer:afterStep END. ");
        }
        return ExitStatus.COMPLETED;
    }

    /**
     * Save summary invoice number.
     * 
     * @param id the id.
     * @param summaryInvoiceNumber the summary invoice number.
     */
    public void saveSummaryInvoiceNumber(Long id, String summaryInvoiceNumber) {
        paymentService.saveSummaryGiftAidInvoiceNumber(id, summaryInvoiceNumber);
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
            LOGGER.debug("GiftAidClaimedTransformer:inside before step  execution context:" + stepExecution);
        }
        this.stepExecution = stepExecution;
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.GIFT_AID_CLAIMED);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("giftAidClaimedJob");
        serviceBatch.setNextBatchName("GiftAidReceived");
        createBatchRequest.setServiceBatch(serviceBatch);

        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
            context.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER.error("Error occurred while creating batch  : GiftAidClaimedTransformer."
                    + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
        }
        catch (Exception exception) {
            LOGGER.error("Error occured while calling creating batch  " + exception.getMessage(),exception);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
        }
    }

    /**
     * Gets the generated invoice number.
     * 
     * @return the generated invoice number
     */
    public String getGeneratedInvoiceNumber() {
        String generateSequesnceNumber = paymentService.generateSequence("GIFT_AID", "INVOICE");
        String referenceNumber = generateSequenceHelper.prefixReferenceType("GIFT_AID", "INVOICE",
                generateSequesnceNumber);
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("GiftAidClaimedTransformer:generated sequence number is" + referenceNumber);
        }
        return referenceNumber;
    }

}
