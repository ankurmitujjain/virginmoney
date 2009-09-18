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
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.domain.RegularDonationPaymentFailed;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.domain.Payment;


//TODO-This Class is not been used and need to be removed.
/**
 * This class takes the Payment object and transform it to PaymentInitiated
 * object.
 * 
 * @author Yogesh Salunkhe
 */
public class PaymentSettledTransformer implements StepExecutionListener,
        ItemProcessor<Payment, RegularDonationPaymentFailed> {
    
    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER =
            Logger.getLogger(RegularDonationPaymentFailedTransformer.class);

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /**
     * This method used to process the payment details.
     * 
     * @param payment of type payment
     * 
     * @return TransactionFeePaid object
     * 
     * @throws Exception process the payment details throws Exception.
     */
    public RegularDonationPaymentFailed process(Payment payment) throws GivingBatchException {

        LOGGER.trace("In RegularDonationPaymentFailedTransformer" + payment);

        RegularDonationPaymentFailed regularDonationPaymentFailed = new RegularDonationPaymentFailed();
        if (payment.getPaymentStatus().getCode().equalsIgnoreCase(
                Constant.PAYMENT_STATUS_PAYMENT_FAILED)) {
            PaymentType paymentType = new PaymentType();
                                   
            paymentType.setTransactionType(payment.getPaymentType().getCode());
            paymentType.setTransactionStatus(payment.getPaymentStatus()
                    .getCode());
            paymentType.setTransactionDate(payment.getCreatedDateTime());
           
            if (payment.getCardTransaction() != null){
                
                paymentType.setAmount(payment.getCardTransaction()
                        .getTransactionAmount());
                paymentType.setTransactionDate(payment.getCardTransaction()
                        .getTransactionDateTime());
                regularDonationPaymentFailed.setCardType(payment.getCardTransaction()
                        .getTlgAcquirerName());
            }
            regularDonationPaymentFailed.setPaymentType(paymentType);
            regularDonationPaymentFailed.setInvoiceNumber(payment.getFinanceReference());
            // TODO: Need confirmation for these
            // cardTypeTransaction.setEventRef(eventRef);
            // cardTypeTransaction.setBankAccountUniqueId(bankAccountUniqueId);
        }
        updateObjectInContext(regularDonationPaymentFailed);
       
        return regularDonationPaymentFailed;
    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put PaymentInitiatedObjectList.
     * 
     * @param regularDonationPaymentFailed of type PaymentInitiated to add this object to
     * executionContextList.
     */
    private void updateObjectInContext(RegularDonationPaymentFailed regularDonationPaymentFailed) {
        LOGGER
                .trace("Inside RegularDonationPaymentFailedTransformer UpdateObjectContext -Start");
        ExecutionContext executionContext =
                stepExecution.getJobExecution().getExecutionContext();
        List<RegularDonationPaymentFailed> paymentInitaitedObjectList =
                (List<RegularDonationPaymentFailed>) executionContext
                        .get(Constant.REGULAR_DONATION_PAYMENT_FAILED_OBJECT_LIST);
        if (paymentInitaitedObjectList == null) {
            paymentInitaitedObjectList = new ArrayList<RegularDonationPaymentFailed>();
            executionContext.put(Constant.REGULAR_DONATION_PAYMENT_FAILED_OBJECT_LIST,
                    paymentInitaitedObjectList);
        }
        paymentInitaitedObjectList.add(regularDonationPaymentFailed);
        LOGGER
                .trace("Inside RegularDonationPaymentFailedTransformer UpdateObjectContext End.");
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
        LOGGER.trace("Inside afterStep method ");
        return ExitStatus.COMPLETED;
    }

    /**
     * This method called is before step starts and accessing the step execution
     * values.
     * 
     * @param stepExecution of type StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.trace("Inside before step  execution context:" + stepExecution);
        this.stepExecution = stepExecution;
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);

    }

}
