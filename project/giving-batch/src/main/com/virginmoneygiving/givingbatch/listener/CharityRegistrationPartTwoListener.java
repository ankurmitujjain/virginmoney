package com.virginmoneygiving.givingbatch.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.Batch;
import com.virginmoneygiving.givingbatch.domain.Charities;
import com.virginmoneygiving.givingbatch.domain.CharitiesBatch;
import com.virginmoneygiving.givingbatch.domain.MessageHeader;

/**
 * This class is used to make a gliss call for CharityRegistrationPartTwo job.
 * In case of failure entire batch operation will be roll back.
 * 
 * @author Tarun Adiwal
 */
public class CharityRegistrationPartTwoListener extends
		StepExecutionListenerSupport implements ItemWriteListener {

	/** create instance of logger. */
	private static final Logger LOGGER = Logger
			.getLogger(CharityRegistrationPartTwoListener.class);

	/** create instance of batch delegate to inject inside xml. */
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
	 * Call GLIS Service.
	 * 
	 * Will raise runtime exception to ensure transaction rollback if a GLIS
	 * error is returned.
	 * 
	 * @param list not used
	 */
	@SuppressWarnings("unchecked")
	public void afterWrite(List list) {
	    if(LOGGER.isTraceEnabled()) {
    		LOGGER
    				.trace("Inside CharityRegistrationPartTwoListener afterWrite method- Start");
	    }
		CharitiesBatch charitiesBatch = new CharitiesBatch();
		ExecutionContext executionContext = stepExecution.getJobExecution()
				.getExecutionContext();
		if (LOGGER.isDebugEnabled()) {
		    LOGGER.debug("Exceution context is:" + executionContext);
		}
		if (executionContext != null) {
			List<Charities> charitiesList = (List<Charities>) executionContext
					.get(Constant.REGISTER_CHARITY_OBJECT_LIST);

			charitiesBatch.setCharities(charitiesList);
			String batchNumber = (String) executionContext
					.get(Constant.BATCH_NUMBER);
			if (LOGGER.isDebugEnabled()) {
			    LOGGER.debug("*******BATCH NUMBER CharityRegistrationPartTwoListener********* :" + batchNumber);
			}
			Batch batch = Util.getBatchObject(Constant.REGISTER_CHARITY_LIV,
					batchNumber);
			charitiesBatch.setBatch(batch);
			MessageHeader messageHeader = Util
					.getMessageHeaderObject(Constant.REGISTER_CHARITY);
			charitiesBatch.setMessageHeader(messageHeader);

		}
		try {
			batchDelegate.maintainCharities(charitiesBatch);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		if(LOGGER.isTraceEnabled()) {
    		LOGGER
    				.info("Inside CharityRegistrationPartTwoListener afterWrite method- End");
		}
	}

	/**
	 * Unimplemented.
	 * 
	 * @param list not used
	 */
	@SuppressWarnings("unchecked")
	public void beforeWrite(List list) {
	}

	/**
	 * Unimplemented.
	 * 
	 * @param exception not used
	 * @param list not used
	 */
	@SuppressWarnings("unchecked")
	public void onWriteError(Exception exception, List list) {
	}

}
