package com.virginmoneygiving.givingbatch.writer;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.oxm.XmlMappingException;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.domain.PutCharityLiveJobSummary;

/**
 * Logging item writer to fulfill the configuration requirements.
 * 
 * @author Puneet Swarup
 */
public class LoggingItemWriter extends
		StaxEventItemWriter<PutCharityLiveJobSummary> implements
		StepExecutionListener {
    
    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(LoggingItemWriter.class);

	/** The step execution. */
	private StepExecution stepExecution;

	/** The summary. */
	private PutCharityLiveJobSummary summary;

	/** {@inheritDoc} */
	@Override
	public void write(List<? extends PutCharityLiveJobSummary> summaryList)
			throws XmlMappingException, IOException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LoggingItemWriter::write() - START");
        }

        this.summary = (PutCharityLiveJobSummary) this.stepExecution
				.getJobExecution().getExecutionContext().get(
						Constant.PUT_CHARITY_LIVE_SUMMARY);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LoggingItemWriter::write() - END");
        }
    }

	/** {@inheritDoc} */
	public ExitStatus afterStep(final StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LoggingItemWriter::afterStep() - START");
        }

        stepExecution.getJobExecution().getExecutionContext().put(
				Constant.PUT_CHARITY_LIVE_SUMMARY, this.summary);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LoggingItemWriter::afterStep() - END");
        }
        return ExitStatus.COMPLETED;
	}

	/** {@inheritDoc} */
	public void beforeStep(final StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LoggingItemWriter::beforeStep() - START");
        }

        this.stepExecution = stepExecution;
		this.summary = (PutCharityLiveJobSummary) this.stepExecution
				.getJobExecution().getExecutionContext().get(
						Constant.PUT_CHARITY_LIVE_SUMMARY);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("LoggingItemWriter::beforeStep() - END");
        }
    }
}
