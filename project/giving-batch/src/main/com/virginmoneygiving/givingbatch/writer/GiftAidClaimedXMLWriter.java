package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.oxm.XmlMappingException;

import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.domain.Transaction;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.service.PaymentService;


/**
 * Class to create the summary report of claimed gift aids.
 * 
 * @author Tarun Adiwal
 */
public class GiftAidClaimedXMLWriter extends StaxEventItemWriter implements
        StepExecutionListener {

    /** instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(GiftAidClaimedXMLWriter.class);

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /** Decalaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** payment serice. * */
    private PaymentService paymentService;

    /** The generate sequence helper. */
    private GivingBatchHelper generateSequenceHelper;

    /** constants. * */
    private static String GIFT_AID_DATE = "PreviousGiftAidDate";

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

    /** {@inheritDoc} **/
    public void write(List itemList) throws XmlMappingException, IOException {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedXMLWriter::write() - START");
        }
        BigDecimal toatalAmount = null;
        List<Transaction> tempTransactionList = new ArrayList<Transaction>();
        List<Transaction> transactionList = itemList;
        int i = 0;
        Transaction transactionObj = new Transaction();
        toatalAmount = new BigDecimal(0);
        
        for (Transaction transaction : transactionList) {

            toatalAmount = toatalAmount.add(transaction.getAmount());
            transactionObj = transaction;
            
        }
        String invoiceNumber = getGeneratedInvoiceNumber(paymentService);
        Transaction summaryTransaction = new Transaction();
        summaryTransaction.setAmount(toatalAmount);
        summaryTransaction.setBankAccountUniqueId(transactionObj.getBankAccountUniqueId());
        summaryTransaction.setClaimPeriodFrom(transactionObj.getClaimPeriodFrom());
        summaryTransaction.setClaimPeriodTo(transactionObj.getClaimPeriodTo());
        summaryTransaction.setCustomer(transactionObj.getCustomer());
        summaryTransaction.setDate(transactionObj.getDate());
        summaryTransaction.setEventRef(transactionObj.getEventRef());
        summaryTransaction.setTransactionStatus(transactionObj.getTransactionStatus());
        summaryTransaction.setTransactionType(transactionObj.getTransactionType());
        summaryTransaction.setInvoiceNumber(invoiceNumber);
        //transactionObj.setAmount(toatalAmount);
        //super.write(Collections.singletonList(transactionObj));
        super.write(Collections.singletonList(summaryTransaction));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GiftAidClaimedXMLWriter::write() - END");
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
                paymentService.generateSequence("GIFT_AID", "INVOICE");
        String referenceNumber =
                generateSequenceHelper.prefixReferenceType("GIFT_AID",
                        "INVOICE", generateSequesnceNumber);
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate sequence number is" + referenceNumber);
        }
        return referenceNumber;
    }
    
    /**
     * This method used to write the summarised objects.
     * 
     * @param transactionList Transaction List
     * 
     * @throws IOException exception
     */
    public void writeSummarisedObj(List<Transaction> transactionList)
            {

        LOGGER.debug("GiftAidClaimedXMLWriter: In writeSummarisedObj" );
        for (int i = 0; i < transactionList.size(); i++) {

            Transaction transaction = transactionList.get(i);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("GiftAidClaimedXMLWriter: In writeSummarisedObj for Tran: " + transaction.getInvoiceNumber());
            }
            try {
                super.write(Collections.singletonList(transaction));
            }
            catch (XmlMappingException e) {
                throw new GivingBatchException(LOGGER, "Batch Failed : EventRegistrationPaymentInitiatedXMLWriter Error while writing summarised object", e);
            }
            catch (IOException e) {
                throw new GivingBatchException(LOGGER, "Batch Failed : EventRegistrationPaymentInitiatedXMLWriter Error while writing summarised object", e);
            }
        }
    }
      

    /**
     * Gets the previous gift aid date.
     * 
     * @return the previous gift aid date
     */
    public Date getPreviousGiftAidDate() {
      
        Date result = null;
        if (executionContext.containsKey(GIFT_AID_DATE)) {
            result = Date.valueOf(executionContext.getString(GIFT_AID_DATE));
        }
       
        return result;
    }

    /**
     * Sets the previous gift aid date.
     * 
     * @param date the new previous gift aid date
     */
    public void setPreviousGiftAidDate(Date date) {
        executionContext.put(GIFT_AID_DATE, date.toString());
    }

    /**
     * Gets the date.
     * 
     * @param s the s
     * 
     * @return the date
     * 
     * @throws Exception the exception
     */
    public Date getDate(String s) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateFormat.parse(s).getTime());

        return date;
    }

    /** {@inheritDoc} **/
    public void beforeStep(StepExecution stepExecution) {
        executionContext = stepExecution.getJobExecution().getExecutionContext();

    }

    /** {@inheritDoc} **/
    public ExitStatus afterStep(StepExecution stepExecution) {

        return ExitStatus.COMPLETED;
    }

}
