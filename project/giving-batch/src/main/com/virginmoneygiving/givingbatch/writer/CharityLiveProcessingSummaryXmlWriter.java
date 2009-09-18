package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.oxm.XmlMappingException;

import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.domain.PutCharityLiveJobSummary;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * Class to fetch the summary report of put charity to live job and create
 * control report.
 * 
 * @author Puneet Swarup
 */
public class CharityLiveProcessingSummaryXmlWriter implements StepExecutionListener, Tasklet {

	/** Logger instance. */
	private static final Logger LOGGER = Logger
			.getLogger(CharityLiveProcessingSummaryXmlWriter.class);

	/** Step execution instance. */
	private StepExecution stepExecution;

	/** Object to hold processing summary. */
	private PutCharityLiveJobSummary summary;
	
	/** StaxEventItemWriter. */
	private StaxEventItemWriter writer;
	
	/** {@inheritDoc} */
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			 {
		try {
            writer.write(Collections.singletonList(this.summary));
        }
        catch (XmlMappingException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : CharityLiveProcessingSummaryXmlWriter Error while writing summarised object", e);
        }
        catch (IOException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed: CharityLiveProcessingSummaryXmlWriter Error while writing summarised object", e);
        }
		return RepeatStatus.FINISHED;
	}

	/** {@inheritDoc} */
	public ExitStatus afterStep(final StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityLiveProcessingSummaryXmlWriter::afterStep() - START");
        }
		this.stepExecution = stepExecution;
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityLiveProcessingSummaryXmlWriter::afterStep() - END");
        }
		return ExitStatus.COMPLETED;
	}

	/** {@inheritDoc} */
	public void beforeStep(final StepExecution stepExecution) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityLiveProcessingSummaryXmlWriter::beforeStep() - START");
        }
		this.stepExecution = stepExecution;
		this.summary = (PutCharityLiveJobSummary) this.stepExecution.getJobExecution()
				.getExecutionContext().get(Constant.PUT_CHARITY_LIVE_SUMMARY);
		if(LOGGER.isDebugEnabled()){
		    LOGGER.debug(this.summary);
	    }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("CharityLiveProcessingSummaryXmlWriter::beforeStep() - END");
        }
	}

	/**
	 * Sets the writer.
	 * 
	 * @param writer the writer to set
	 */
	public void setWriter(StaxEventItemWriter writer) {
		this.writer = writer;
	}
}
