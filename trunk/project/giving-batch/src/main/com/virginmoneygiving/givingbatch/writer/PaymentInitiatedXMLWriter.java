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
import com.virginmoneygiving.givingbatch.domain.DonationPaymentInitiatedBatch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;
import com.virginmoneygiving.givingbatch.domain.PaymentInitiated;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * Class to create the summary report of initiated web donations by grouping them
 * according to the card type.
 * 
 * @author Tarun Adiwal
 */
public class PaymentInitiatedXMLWriter extends StaxEventItemWriter implements
		StepExecutionListener, ItemWriteListener {

	/** instance for Logger. * */
	private static Logger LOGGER = Logger
			.getLogger(PaymentInitiatedXMLWriter.class);

	/** instance for ExecutionContext. * */
	private ExecutionContext executionContext;

	/** instance for StepExecution. * */
	private StepExecution stepExecution;

	/** Decalaring instance of Execution Context. */
	// private StepExecution stepExecution;
	/** payment serice. **/
	private PaymentService paymentService;

	/** *. */
	private GivingBatchHelper generateSequenceHelper;

	/** Create instance of batch delegate to inject inside xml. */
	private BatchDelegate batchDelegate;

	/** Global PaymentInitiated lis. * */
	private List<PaymentInitiated> globalPaymentinitiatedList = new ArrayList<PaymentInitiated>();

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

	/** {@inheritDoc} **/
	public void write(List list) throws XmlMappingException, IOException {

		/*
		 * ExecutionContext executionContext =
		 * stepExecution.getJobExecution().getExecutionContext();
		 */

		Map<String, PaymentInitiated> summaryMap = (Map<String, PaymentInitiated>) executionContext
				.get(Constant.SUMMARY_MAP);
		if (summaryMap != null) {
			Iterator iterator = summaryMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, PaymentInitiated> entry = (Map.Entry<String, PaymentInitiated>) iterator
						.next();
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
     */
    public void writeSummarisedObj(PaymentInitiated paymentInitiated)
    {
        try {
            super.write(Collections.singletonList(paymentInitiated));
        }
        catch (XmlMappingException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : Payment Initiated batch while writing summarised object", e);
        }
        catch (IOException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed: Payment Initiated batch while writing summarised object", e);
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
		String generateSequesnceNumber = paymentService.generateSequence(
				"WEB_DONATION", "INVOICE");
		String referenceNumber = generateSequenceHelper.prefixReferenceType(
				"WEB_DONATION", "INVOICE", generateSequesnceNumber);
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
			ExecutionContext executionContext = stepExecution.getJobExecution()
					.getExecutionContext();
			idList = (List<Long>) executionContext
					.get(Constant.PAYMENT_ID_LIST);

		}
		return idList;
	}

	/**
	 * This method used to store the card type in context.
	 * 
	 * @param cardType the card type
	 */
	private void setPreviousCardTye(String cardType) {
		executionContext.putString(CARD_TYPE, cardType);
	}

	/** {@inheritDoc} **/
	public void beforeStep(StepExecution stepExecution) {
		// this.stepExecution = stepExecution;
		executionContext = stepExecution.getJobExecution().getExecutionContext();
	}

	/** {@inheritDoc} **/
	public ExitStatus afterStep(StepExecution stepExecution) {

		return ExitStatus.COMPLETED;
	}

	/**
	 * Sets the batch delegate.
	 * 
	 * @param batchDelegate the batchDelegate to set
	 */
	public void setBatchDelegate(BatchDelegate batchDelegate) {
		this.batchDelegate = batchDelegate;
	}

	/** {@inheritDoc} **/
	public void afterWrite(List list) {
		DonationPaymentInitiatedBatch paymentInitiatedBatch = new DonationPaymentInitiatedBatch();
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

		paymentInitiatedBatch.setPaymentInitiated(paymentInitiated);

		String batchNumber = (String) executionContext
				.get(Constant.BATCH_NUMBER);
		LOGGER.debug("*******BATCH NUMBER ********* :" + batchNumber);
		Batch batch = Util
				.getBatchObject(
						MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED
								.getCode(), batchNumber);
		paymentInitiatedBatch.setBatch(batch);
		MessageHeader messageHeader = Util
				.getMessageHeaderObject(MasterDataCodeConstants.WebDonation.WEB_DONATION_PAYMENT_INITIATED
						.getCode());
		paymentInitiatedBatch.setMessageHeader(messageHeader);
		try {
			batchDelegate
					.processDonationPaymentsInitiated(paymentInitiatedBatch);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

	}

	/** {@inheritDoc} **/
	public void beforeWrite(List list) {
		// TODO Auto-generated method stub

	}

	/** {@inheritDoc} **/
	public void onWriteError(Exception exception, List list) {
		// TODO Auto-generated method stub

	}

}
