package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the EventRegistrationFeeTransactionChargePaid batch.
 * Takes the payment object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class EventRegistrationFeeTransactionChargePaidItemProcessor implements
        ItemProcessor<Payment, Payment> {

    /** The LOGGER. */
    private static Logger LOGGER =
            Logger.getLogger(EventRegistrationFeeTransactionChargePaidItemProcessor.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /** constants. * */
    private static String CARD_TYPE = "PreviousCardType";

    /** Instance of GivingBatchHelper. * */
    private GivingBatchHelper generateSequenceHelper;

    /** payment service. * */
    private PaymentService paymentService;

    /**
     * Sets the payment service.
     * 
     * @param paymentService the paymentService to set
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /** {@inheritDoc} **/
    public Payment process(Payment payment) throws GivingBatchException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationFeeTransactionChargePaidItemProcessor.process - Start");
        }
        updateBatchStatus(payment.getId(),
        		payment.getPaymentStatus().getCode());
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("EventRegistrationFeeTransactionChargePaidItemProcessor.process - End");
        }
        return payment;

    }

    /**
     * The updateBatchStatus method calls paymentService and updates the
     * batch status to 'TRCHRGPAID_PENDING' in the database in DB.
     * 
     * @param id the id
     * @param status the status
     */
    public void updateBatchStatus(Long id, String status) {
        LOGGER
                .trace("EventRegistrationFeeTransactionChargePaidItemProcessor.updateBatchStatus - Start");
        paymentService.updateStatusCardCharge(id, status);
        LOGGER
                .trace("EventRegistrationFeeTransactionChargePaidItemProcessor.updateBatchStatus - End");
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

}
