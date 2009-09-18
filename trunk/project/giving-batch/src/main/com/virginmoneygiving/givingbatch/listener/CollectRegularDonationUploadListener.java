package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.jms.core.JmsTemplate;

import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;

/**
 * Currently this class is not being used.
 *
 * @author Tarun Adiwal
 */
public class CollectRegularDonationUploadListener extends
		StepExecutionListenerSupport implements ItemWriteListener<Object> {

	/** create instance of logger. */
	private static final Logger LOGGER = Logger
			.getLogger(CollectRegularDonationUploadListener.class);

	/** create instance of batch delegate. */
	private BatchDelegate batchDelegate;

	/** create instance of stepExecution. */
	private StepExecution stepExecution;

	/** Giving service. */
	private GivingService givingService;

	/** Email template. */
	JmsTemplate jmsEmailTemplate;

	/**
	 * Sets the jms email template.
	 * 
	 * @param jmsEmailTemplate the new jms email template
	 */
	public void setJmsEmailTemplate(JmsTemplate jmsEmailTemplate) {
		this.jmsEmailTemplate = jmsEmailTemplate;
	}

	/**
	 * Sets the batch delegate.
	 * 
	 * @param batchDelegate the batchDelegate to set
	 */
	public void setBatchDelegate(BatchDelegate batchDelegate) {
		this.batchDelegate = batchDelegate;
	}

	 /** {@inheritDoc} */
	public ExitStatus afterStep(StepExecution stepExecution) {
		return ExitStatus.COMPLETED;
	}

	 /** {@inheritDoc} */
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	 /** {@inheritDoc} */
	public void afterWrite(List<? extends Object> list) {

	}

	 /** {@inheritDoc} */
	public void beforeWrite(List<? extends Object> list) {

	}

	 /** {@inheritDoc} */
	public void onWriteError(Exception exception, List<? extends Object> list) {

	}

	/**
	 * Sets the giving service.
	 * 
	 * @param givingService the givingService to set
	 */
	public void setGivingService(GivingService givingService) {
		this.givingService = givingService;
	}

}
