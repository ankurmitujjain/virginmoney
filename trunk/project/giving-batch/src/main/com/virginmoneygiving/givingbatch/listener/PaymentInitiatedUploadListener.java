package com.virginmoneygiving.givingbatch.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is used to make a gliss call for PaymentInitiated job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class PaymentInitiatedUploadListener extends
    StepExecutionListenerSupport implements ItemWriteListener {

    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(PaymentInitiatedUploadListener.class);

    /** create instance of batch delegate to injectinside xml. */
    private BatchDelegate batchDelegate;

    /** create instance of stepExecution. */
    private StepExecution stepExecution;

    /**
     * Sets the batch delegate.
     * 
     * @param batchDelegate the batchDelegate to set
     */
    public void setBatchDelegate(BatchDelegate batchDelegate) {
        this.batchDelegate = batchDelegate;
    }

    /** payment serice. */
    private PaymentService paymentService;

    /** Sequence helper. */
    private GivingBatchHelper generateSequenceHelper;

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
     * This method will assign the step execution context whenever transformer
     * called.
     * 
     * @param stepExecution of tyep StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * Generate a sequence number.
     * 
     * @param paymentService to use to get next sequence
     * 
     * @return a formatted sequence number
     */
    public String getGeneratedInvoiceNumber(PaymentService paymentService) {
        String generateSequesnceNumber =
            paymentService.generateSequence("DONATION", "INVOICE");
        String referenceNumber =
            generateSequenceHelper.prefixReferenceType("DONATION", "INVOICE",
                generateSequesnceNumber);
        LOGGER.debug("generate sequence number is" + referenceNumber);

        return referenceNumber;
    }

    /**
     * Call GLIS Service.
     * 
     * Will raise runtime exception to ensure transaction rollback if a
     * GLIS error is returned.
     * 
     * @param list not used
     */
    @SuppressWarnings("unchecked")
    public void afterWrite(List ignored) {
        LOGGER
            .info("Inside PaymentInitiatedUploadListener afterWrite method -Start");
        DonationPaymentInitiatedBatch donationPaymentInitiatedBatch =
            new DonationPaymentInitiatedBatch();
        ExecutionContext executionContext =
            stepExecution.getJobExecution().getExecutionContext();
        LOGGER.debug("Exceution context is:" + executionContext);

        if (executionContext != null) {

            HashMap<String, PaymentInitiated> paymentInitiatedMap =
                (HashMap<String, PaymentInitiated>) executionContext
                    .get(Constant.PAYMENT_INITIATED_SUMMARY_MAP);
            if (paymentInitiatedMap != null) {
                List<PaymentInitiated> paymentInitiatedList =
                    new ArrayList<PaymentInitiated>();
                for (Iterator iterator =
                    paymentInitiatedMap.keySet().iterator(); iterator.hasNext();) {
                    String key = (String) iterator.next();
                    PaymentInitiated obj =
                        (PaymentInitiated) paymentInitiatedMap.get(key);
                    String invoiceNumber =
                        getGeneratedInvoiceNumber(paymentService);
                    obj.setInvoiceNumber(invoiceNumber);
                    paymentInitiatedList.add(obj);
                }
                donationPaymentInitiatedBatch
                    .setPaymentInitiated(paymentInitiatedList);
            }
        }

        try {
            batchDelegate
                .processDonationPaymentsInitiated(donationPaymentInitiatedBatch);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        LOGGER
            .info("Inside PaymentInitiatedUploadListener afterWrite method -End");

    }

    /**
     * Unimplemented.
     * 
     * @param ignored not used
     */
    public void beforeWrite(List ignored) {
    }

    /**
     * Unimplemented.
     * 
     * @param exception not used
     * @param list not used
     */
    public void onWriteError(Exception exception, List list) {
    }

}
