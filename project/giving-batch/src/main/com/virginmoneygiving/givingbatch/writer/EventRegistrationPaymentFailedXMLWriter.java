package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.oxm.XmlMappingException;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.Batch;
import com.virginmoneygiving.givingbatch.domain.FailedDonationPaymentBatch;
import com.virginmoneygiving.givingbatch.domain.FailedPayment;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * Class to create the summary report of failed event registrations by grouping them
 * according to the card type.
 * 
 * @author Yogesh Salunkhe
 */
public class EventRegistrationPaymentFailedXMLWriter extends StaxEventItemWriter implements
        StepExecutionListener, ItemWriteListener {

    /** Instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(EventRegistrationPaymentFailedXMLWriter.class);

    /** Instance for ExecutionContext. * */
    private ExecutionContext executionContext;
   
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
    
    /** The Constant CREDIT_NOTE. */
    private static final String CREDIT_NOTE =
        "CREDIT_NOTE";

    /** Create instance of batch delegate to inject inside xml. */
    private BatchDelegate batchDelegate;

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

    /** constants. * */
    private static String CARD_TYPE = "PreviousCardType";

    /** {@inheritDoc} **/
    public void write(List list) throws XmlMappingException, IOException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventRegistrationPaymentFailedXMLWriter -Start");
        }
        Map<String, FailedPayment> summaryMap =
            (Map<String, FailedPayment>) executionContext
                    .get(Constant.SUMMARY_MAP);
        if (summaryMap != null) {
            Iterator iterator = summaryMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, FailedPayment> e =
                        (Map.Entry<String, FailedPayment>) iterator.next();
                writeSummarisedObj(e.getValue());
            }
    
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("In EventRegistrationPaymentFailedXMLWriter - End.");
        }

    }

    /**
     * This method used to write the summarised objects.
     * 
     * @param paymentInitiated the payment initiated
     * 
     * @return void.
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeSummarisedObj(FailedPayment paymentInitiated) {
           try {
            super.write(Collections.singletonList(paymentInitiated));
        }
        catch (XmlMappingException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : EventRegistrationPaymentFailedXMLWriter Error while writing summarised object", e);
        }
        catch (IOException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : EventRegistrationPaymentFailedXMLWriter Error while writing summarised object", e);
        }
     }

    /** {@inheritDoc} **/
    public void beforeStep(StepExecution stepExecution) {
        executionContext = stepExecution.getJobExecution().getExecutionContext();

    }

    /** {@inheritDoc} **/
    public ExitStatus afterStep(StepExecution stepExecution) {

        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} **/
    public void afterWrite(List list) {

        FailedDonationPaymentBatch donationPaymentInitiatedBatch =
                new FailedDonationPaymentBatch();
		List<FailedPayment> failedPayment = null;
		Map<String, FailedPayment> summaryMap = (Map<String, FailedPayment>) executionContext
				.get(Constant.SUMMARY_MAP);
		if (summaryMap != null) {
			Iterator iterator = summaryMap.entrySet().iterator();
			failedPayment = new ArrayList<FailedPayment>();
			while (iterator.hasNext()) {
				Map.Entry<String, FailedPayment> entry = (Map.Entry<String, FailedPayment>) iterator
						.next();
				failedPayment.add(entry.getValue());
				
			}

		}
		donationPaymentInitiatedBatch.setFailedPayment(failedPayment);
	    String batchNumber =
	            (String) executionContext.get(Constant.BATCH_NUMBER);
	    if(LOGGER.isDebugEnabled()) {
	        LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
	    }
	    Batch batch =
	            Util
	                    .getBatchObject(
	                            MasterDataCodeConstants.EventRegistrationFee.EVENT_REG_FEE_PAYMENT_FAILED
	                                    .getCode(), batchNumber);
	    donationPaymentInitiatedBatch.setBatch(batch);
	    MessageHeader messageHeader =
	            Util
	                    .getMessageHeaderObject(MasterDataCodeConstants.EventRegistrationFee.EVENT_REG_FEE_PAYMENT_FAILED
	                            .getCode());
	    donationPaymentInitiatedBatch.setMessageHeader(messageHeader);

        try {
                batchDelegate
                        .processEventRegistrationPaymentsFailed(donationPaymentInitiatedBatch);
        }
        catch (Exception exception) {
            throw new RuntimeException();
        }

    }

    /** {@inheritDoc} **/
    public void beforeWrite(List list) {
        
    }

    /** {@inheritDoc} **/
    public void onWriteError(Exception exception, List list) {
        
    }

}
