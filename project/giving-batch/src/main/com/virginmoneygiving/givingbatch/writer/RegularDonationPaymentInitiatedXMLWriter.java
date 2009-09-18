package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;
import com.virginmoneygiving.givingbatch.domain.PaymentType;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * Class to create the summary report of initiated regular donations by grouping them
 * according to the card type.
 * 
 * @author Yogesh Salunkhe
 */
public class RegularDonationPaymentInitiatedXMLWriter extends
        StaxEventItemWriter implements StepExecutionListener, ItemWriteListener {

    /** Instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(RegularDonationPaymentInitiatedXMLWriter.class);

    /** Instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /** Declaring instance of Execution Context. */
    private StepExecution stepExecution;

    /** Payment service. * */
    private PaymentService paymentService;

    /** Giving Batch helper. */
    private GivingBatchHelper generateSequenceHelper;

    /** Create instance of batch delegate to inject inside xml. */
    private BatchDelegate batchDelegate;

    /** Payment Initiated List. */
    List<PaymentInitiated> globalPaymentInitiatedList =
            new ArrayList<PaymentInitiated>();

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

    /** constants. * */
    private static String CARD_TYPE = "PreviousCardType";

    /** The payment initiated obj list temp. */
    List<PaymentInitiated> paymentInitiatedObjListTemp;

    /** {@inheritDoc} **/
    public void write(List list) throws XmlMappingException, IOException {

        Map<String, PaymentInitiated> summaryMap =
            (Map<String, PaymentInitiated>) executionContext
                    .get(Constant.SUMMARY_MAP);
        if (summaryMap != null) {
            Iterator iterator = summaryMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, PaymentInitiated> entry =
                        (Map.Entry<String, PaymentInitiated>) iterator.next();
                writeSummarisedObj(entry.getValue());
            }
    
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
    public void writeSummarisedObj(PaymentInitiated paymentInitiated)
             {
           try {
            super.write(Collections.singletonList(paymentInitiated));
        }
        catch (XmlMappingException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : RegularDonationPaymentInitiatedXMLWriter while writing summarised object", e);
        }
        catch (IOException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : RegularDonationPaymentInitiatedXMLWriter while writing summarised object", e);
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
     * Gets the context list.
     * 
     * @param stepExecution the step execution
     * 
     * @return the context list
     */
    public List<Long> getContextList(StepExecution stepExecution) {
        List<Long> idList = null;
        if (stepExecution != null) {
            ExecutionContext executionContext =
                    stepExecution.getJobExecution().getExecutionContext();
            idList =
                    (List<Long>) executionContext.get(Constant.PAYMENT_ID_LIST);

        }
        return idList;
    }


    /**
     * This method used to retrieve card type from context.
     * 
     * @return the cardType.
     */
    public String getPreviousCardType() {
        String result = null;
        if (executionContext.containsKey(CARD_TYPE)) {
            result = executionContext.getString(CARD_TYPE);
        }
        return result;
    }

    /**
     * This method used to store the card type in context.
     * 
     * @param cardType the card type
     */
    private void setPreviousCardTye(String cardType) {
        executionContext.putString(CARD_TYPE, cardType);
    }

    /**
     * This method is used for to get the execution context.If it is null then
     * put PaymentInitiatedObjectList.
     * 
     * @param paymentInitiatedObjList the payment initiated obj list
     */
    private void updateObjectListInContext(
            List<PaymentInitiated> paymentInitiatedObjList) {
        // LOGGER
        // .info("Inside PaymentInitiatedTransformer UpdateObjectContext -Start");
        // DonationPaymentInitiatedBatch donationPaymentInitiatedBatch =
        // new DonationPaymentInitiatedBatch();
        // if (paymentInitiatedObjList != null) {
        // donationPaymentInitiatedBatch
        // .setPaymentInitiated(paymentInitiatedObjList);
        // }
        // ExecutionContext executionContext =
        // stepExecution.getExecutionContext();
        // List<PaymentInitiated> paymentInitaitedObjectList =
        // (List<PaymentInitiated>) executionContext
        // .get(Constant.PAYMENT_INITIATED_OBJECT_LIST);
        // if (paymentInitaitedObjectList == null) {
        // paymentInitaitedObjectList = new ArrayList<PaymentInitiated>();
        // executionContext.put(Constant.PAYMENT_INITIATED_OBJECT_LIST,
        // paymentInitiatedObjList);
        // }
        // // paymentInitaitedObjectList.add(paymentInitiated);
        //
        // LOGGER
        // .trace("Inside PaymentInitiatedTransformer UpdateObjectContext End.");
    }

    /** {@inheritDoc} **/
    public void afterWrite(List list) {

        DonationPaymentInitiatedBatch donationPaymentInitiatedBatch =
                new DonationPaymentInitiatedBatch();

        List<PaymentInitiated> paymentInitiated = null;
		Map<String, PaymentInitiated> summaryMap = (Map<String, PaymentInitiated>) executionContext
		.get(Constant.SUMMARY_MAP);
		if (summaryMap != null) {
			Iterator iterator = summaryMap.entrySet().iterator();
			paymentInitiated = new ArrayList<PaymentInitiated>();
			while (iterator.hasNext()) {
				Map.Entry<String, PaymentInitiated> entry = (Map.Entry<String, PaymentInitiated>) iterator
						.next();
				paymentInitiated.add(entry.getValue());
				
			}
		
		}
		
		donationPaymentInitiatedBatch.setPaymentInitiated(paymentInitiated);

	    String batchNumber =
	            (String) executionContext.get(Constant.BATCH_NUMBER);
       if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
        }
	    Batch batch =
	            Util
	                    .getBatchObject(
	                            MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED
	                                    .getCode(), batchNumber);
	    donationPaymentInitiatedBatch.setBatch(batch);
	    MessageHeader messageHeader =
	            Util
	                    .getMessageHeaderObject(MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED
	                            .getCode());
	    donationPaymentInitiatedBatch.setMessageHeader(messageHeader);

        try {
                batchDelegate
                        .processDonationPaymentsInitiated(donationPaymentInitiatedBatch);
        }
        catch (Exception exception) {
            throw new RuntimeException();
        }

        LOGGER.debug("End After write RegularDonationPaymentInitiatedXMLWriter");

    }

    /** {@inheritDoc} **/
    public void beforeWrite(List list) {

    }

    /** {@inheritDoc} **/
    public void onWriteError(Exception exception, List list) {

    }

    /** {@inheritDoc} **/
    public ExitStatus afterStep(StepExecution stepExecution) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Inside afterStep method " + stepExecution);
        }
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} **/
    public void beforeStep(StepExecution stepExecution) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Inside before step  execution context:" + stepExecution);
        }
        executionContext = stepExecution.getJobExecution().getExecutionContext();

    }

}
