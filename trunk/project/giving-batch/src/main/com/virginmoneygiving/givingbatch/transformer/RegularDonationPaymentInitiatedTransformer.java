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
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class takes the Payment object and transform it to PaymentInitiated
 * object.Before actual batch processing a unique batch number is created and 
 * after batch processing the batch outcome status is updated in batch table.
 * 
 * @author Yogesh Salunkhe
 */
public class RegularDonationPaymentInitiatedTransformer implements
        StepExecutionListener, ItemProcessor<Payment, PaymentInitiated> {
    
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(RegularDonationPaymentInitiatedTransformer.class);

    /** Payment service. * */
    private PaymentService paymentService;

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

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
     * @return TransactionFeePaid object
     * 
     * @throws Exception process the payment details throws Exception.
     */
    public PaymentInitiated process(Payment payment) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("In Regular Donation Payment Initiated Transformer"
                    + payment);
        }

        String batchNumber =
                (String) executionContext.get(Constant.BATCH_NUMBER);
        LOGGER
                .debug("################################ BATCH NUMBER #######################:"
                        + batchNumber);

        PaymentInitiated paymentInitiated = new PaymentInitiated();
        Map<String, PaymentInitiated> map =
                new HashMap<String, PaymentInitiated>();
        if (payment != null) {
            PaymentType paymentType = new PaymentType();

            paymentType.setTransactionType(payment.getPaymentType().getCode());
            paymentType.setTransactionStatus(payment.getPaymentStatus()
                    .getCode());
            paymentType.setAmount(payment.getGrossAmount());

            if (payment.getCardTransaction() != null) {
                // paymentType.setAmount(payment.getCardTransaction()
                // .getTransactionAmount());
                paymentType.setTransactionDate(payment.getCardTransaction()
                        .getTransactionDateTime());
                paymentInitiated.setCardType(payment.getCardTransaction()
                        .getCardType());
            }
            paymentInitiated.setPaymentType(paymentType);

            if (payment.getTargetBankSortCode() != null
                    && payment.getTargetBankAccount() != null) {
                String bankAccount =
                        Util.getBankAccountNumber(payment
                                .getTargetBankAccount());
                String bankSortCode =
                        Util.getSortCode(payment.getTargetBankSortCode());
                LOGGER.debug("The Sort Code:" + bankSortCode);
                LOGGER.debug("The Bank Account Number :" + bankAccount);
                paymentInitiated.setBankAccountUniqueId(bankSortCode
                        .concat(bankAccount));
            }

            String cardType = paymentInitiated.getCardType();
            String entityReference = ""; // HunarC - Added this
            map = getSummaryMap();
            if (map != null) {
                if (map.containsKey(cardType)) {
                    PaymentInitiated initiated = map.get(cardType);
                    if (initiated != null) {        // Null check 13051 :: TarunA
                        PaymentType paymentType2 = initiated.getPaymentType();
                        BigDecimal amount = paymentType2.getAmount();
                        paymentType2.setAmount(amount.add(paymentInitiated
                                .getPaymentType().getAmount()));
                        initiated.setPaymentType(paymentType2);
                        entityReference = initiated.getInvoiceNumber();
                        saveSummaryInvoiceNumber(payment.getId(), initiated
                                .getInvoiceNumber());
                        updateObjectInContext(initiated);
                    }
                }
                else {
                    String invoiceNumber =
                            getGeneratedInvoiceNumber(paymentService);
                    paymentInitiated.setInvoiceNumber(invoiceNumber);
                    entityReference = invoiceNumber;
                    saveSummaryInvoiceNumber(payment.getId(), invoiceNumber);
                    updateObjectInContext(paymentInitiated);
                }
            }
            else {
                String invoiceNumber =
                        getGeneratedInvoiceNumber(paymentService);
                paymentInitiated.setInvoiceNumber(invoiceNumber);
                entityReference = invoiceNumber;
                saveSummaryInvoiceNumber(payment.getId(), invoiceNumber);
                updateObjectInContext(paymentInitiated);
            }

            // Step 2: Here for every charity, we create the BatchEntity record.
            CreateBatchEntityRequest createBatchEntityRequest =
                    new CreateBatchEntityRequest();
            ServiceBatchEntity batchEntity = new ServiceBatchEntity();
            batchEntity.setCurrentStatus(Constant.BATCH_STATUS_PROCESSING);
            batchEntity.setEntityId(payment.getId().toString());
            batchEntity
                    .setNextBatchStatus(MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED
                            .getCode());
            batchEntity.setPreviousStatus(null);

            batchEntity.setEntityReference(entityReference); // HunarC: Added
                                                             // this
            batchEntity.setBaseReference(payment.getFinanceReference()); // HunarC:
                                                                         // Added
                                                                         // this

            batchEntity.setEntityTypeCode(Constant.ENTITY_PAYMENT);
            ServiceBatch serviceBatch = new ServiceBatch();
            serviceBatch.setBatchNumber(batchNumber);
            batchEntity.setBatch(serviceBatch);
            createBatchEntityRequest.setServiceBatchEntity(batchEntity);
            try {
                locatorImpl.getGivingBatchExtManagementPort()
                        .createBatchEntity(createBatchEntityRequest);
            }
            catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
                LOGGER
                        .error(
                                "Error occurred while creating batch Payment Entity : RegularDonationPaymentInitiatedTransformer."
                                        + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
                throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
            }
            catch (Exception exception) {
                LOGGER
                        .error("Error occured while calling creating batch Payment Entity "
                                + exception.getMessage(),exception);
                throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, exception);
            }
        } // Null check 13051 :: TarunA
        return paymentInitiated;
    }

    /**
     * Gets the summary map.
     * 
     * @return the summary map
     */
    private Map<String, PaymentInitiated> getSummaryMap() {
        Map<String, PaymentInitiated> map = null;
        if (executionContext != null) {
            map =
                    (Map<String, PaymentInitiated>) executionContext
                            .get(Constant.SUMMARY_MAP);

        }
        return map;
    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put SUMMARY_MAP which has cardType as key and the failedPayment object as value.
     * 
     * @param paymentInitiated of type PaymentInitiated to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(PaymentInitiated paymentInitiated) {
        if (executionContext != null) {
            Map<String, PaymentInitiated> summaryMap =
                    (Map<String, PaymentInitiated>) executionContext
                            .get(Constant.SUMMARY_MAP);
            if (summaryMap == null) {
                summaryMap = new HashMap<String, PaymentInitiated>();
                summaryMap
                        .put(paymentInitiated.getCardType(), paymentInitiated);
            }
            else {
                summaryMap
                        .put(paymentInitiated.getCardType(), paymentInitiated);
            }
            executionContext.put(Constant.SUMMARY_MAP, summaryMap);

        }
    }

    /**
     * Gets the generated invoice number.
     * 
     * @param paymentService the payment service
     * 
     * @return the generated invoice number
     */
    public String getGeneratedInvoiceNumber(PaymentService paymentService) {
        String generateSequesnceNumber =
                paymentService.generateSequence("WEB_DONATION", "INVOICE");
        String referenceNumber =
                generateSequenceHelper.prefixReferenceType("WEB_DONATION",
                        "INVOICE", generateSequesnceNumber);
        LOGGER.debug("generate sequence number is" + referenceNumber);

        return referenceNumber;
    }

    /**
     * Save summary invoice number.
     * 
     * @param id the id
     * @param summaryInvoiceNumber the summary invoice number
     */
    public void saveSummaryInvoiceNumber(Long id, String summaryInvoiceNumber) {
        paymentService.saveSummaryInvoiceNumber(id, summaryInvoiceNumber);
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
        // ExecutionContext executionContext =
        // stepExecution.getJobExecution().getExecutionContext();
        String batchNumber =
                (String) executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "YES");
        Map<String, PaymentInitiated> summaryMap =
                (Map<String, PaymentInitiated>) executionContext
                        .get(Constant.SUMMARY_MAP);
        if (summaryMap == null || summaryMap.size() < 1) {
            LOGGER
                    .debug("inside afterStep method - No matching records found ... setting batch status to completed.");
            try {
                UpdateBatchStatusRequest request =
                        new UpdateBatchStatusRequest();
                request.setBatchNumber(batchNumber);
                request.setEndDateTime(Util.buildXMLGregorianDate());
                request.setStatus(Constant.BATCH_STATUS_SUCCESSFUL);
                request.setErrorMessage("No records found.");
                locatorImpl.getGivingBatchExtManagementPort()
                        .updateBatchStatus(request);
                executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING,
                        "NO");
            }
            catch (GivingBatchExtManagementServiceFaultException exception) {
                LOGGER
                        .error(
                                "Unexpected error setting batch status to complete when no records found" +exception.getMessage(),
                                exception);
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
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("inside before step  execution context:" + stepExecution);
        }
        // this.stepExecution = stepExecution;
        // executionContext = stepExecution.getExecutionContext();
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        // Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber =
                Util
                        .getGeneratedBatchNumber(MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED
                                .getCode());
        ServiceBatch serviceBatch =
                Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName("regularDonationPaymentInitiatedJob");
        serviceBatch.setNextBatchName("RegularDonationPaymentCollected");
        createBatchRequest.setServiceBatch(serviceBatch);

        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatch(
                    createBatchRequest);
            /*
             * executionContext = stepExecution.getJobExecution()
             * .getExecutionContext();
             */
            executionContext.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException batchExtManagementServiceFaultException) {
            LOGGER
                    .error(
                            "Error occurred while creating batch  : RegularDonationPaymentInitiatedTransformer."
                                    + batchExtManagementServiceFaultException.getMessage(), batchExtManagementServiceFaultException);
            throw new GivingBatchException(LOGGER,  "Batch Number"+batchNumber, batchExtManagementServiceFaultException);
            
        }
        catch (Exception exception) {
            LOGGER.error("Error occured while calling creating batch  " + exception.getMessage(),exception);
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
