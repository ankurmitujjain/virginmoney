package com.virginmoneygiving.givingbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.givingbatch.exception.GivingBatchException;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.domain.Payment;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * This class is an Item Processor for the TransactionChargePaidItemProcessor batch.
 * Takes the object returned by the ItemReader and creates the class for the itemWriter.
 * Updates the batch Status in database.
 * 
 * @author Tarun Adiwal
 * @author Yogesh Salunkhe
 */
public class TransactionChargePaidItemProcessor implements ItemProcessor<Payment, Payment> {

	/** The LOGGER. */
	private static Logger LOGGER = Logger
			.getLogger(TransactionChargePaidItemProcessor.class);
	
	/** The payment service. */
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
	        LOGGER.trace("TransactionChargePaidItemProcessor.process - Start");
	    }
		updateBatchStatus(payment.getId(),
				MasterDataCodeConstants.TransactionCharge.TRASACTION_CHARGE_PAID.getCode());
		if(LOGGER.isTraceEnabled()) {
		     LOGGER.trace("TransactionChargePaidItemProcessor.process - End");
		}
		return payment;

	}

	/**
	 * The updateBatchStatus method calls givingService and updates the
	 * process status to 'Processed'in the database in DB.
	 * 
	 * @param id the id
	 * @param status the status
	 */
	public void updateBatchStatus(Long id, String status) {
			paymentService.updateStatusCardCharge(id, status);
	}


}
