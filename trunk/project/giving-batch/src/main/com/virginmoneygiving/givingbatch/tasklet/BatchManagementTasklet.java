package com.virginmoneygiving.givingbatch.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.virginmoney.glis.messages.EventCategoryType;
import com.virginmoney.glis.messages.EventProcessingTypes;
import com.virginmoney.glis.messages.MessageHeader;
import com.virginmoneygiving.givingbatch.common.GivingBatchHelper;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.domain.EventProcessing;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.CreateBatchRequest;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.ServiceBatch;
import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import com.virginmoneygiving.payment.service.PaymentService;

/**
 * Creates the Batch Startup and Batch Finished entries in the batch table.
 *
 * @author Hunar Chopra
 */
public class BatchManagementTasklet extends StepExecutionListenerSupport implements Tasklet {

    /** create instance of logger. */
    private static final Logger LOGGER = Logger.getLogger(BatchManagementTasklet.class);

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;
    
    /**
     * Sets the locator impl.
     *
     * @param locatorImpl the locatorImpl to set
     */
	public void setLocatorImpl(GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
		this.locatorImpl = locatorImpl;
	}

    /** Processing task type. */
    private String processingType;

    /** StepExecution object to fetch execution properties. */
    private StepExecution stepExecution;


	/**
	 * Sets the processing type.
	 *
	 * @param processingType the new processing type
	 */
	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}

    /**
     * This is used to execute the step, call the delegate and return status.
     *
     * @param stepContribution of type StepContribution
     * @param chunkContext the chunk context
     *
     * @return Status of the Step
     *
     * @throws Exception if step is not executed properly
     */
    @SuppressWarnings("unchecked")
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchTypeKey = "BATCH-RUN";
        String batchComment = "batch";
        if (this.processingType != null && this.processingType.equalsIgnoreCase(Constant.BATCH_STARTUP_JOB_NAME)) {
            batchTypeKey = "START-BATCH-";
            batchComment = "Started batch run for Run ID: ";
        }
        else if (this.processingType != null && this.processingType.equalsIgnoreCase(Constant.BATCH_FINISHED_JOB_NAME)) {
            batchTypeKey = "END-BATCH-";
            batchComment = "Finished batch run for Run ID: ";
        }
        String batchNumber = Util.getGeneratedBatchNumber(batchTypeKey);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        serviceBatch.setCurrentJobName(this.processingType);
        serviceBatch.setNextBatchName("INDEPENDANT");
        serviceBatch.setBatchStatus(Constant.BATCH_STATUS_SUCCESSFUL); 
        serviceBatch.setBatchRemarks(batchComment.concat(serviceBatch.getBatchRunId()));
        if (serviceBatch.getBatchRunId() != null && serviceBatch.getBatchRunId().length() > 0) {
            batchNumber = batchTypeKey.concat(serviceBatch.getBatchRunId());
            serviceBatch.setBatchNumber(batchNumber);
        }
        serviceBatch.setExecutionEndDateTime(Util.buildXMLGregorianDate());
        createBatchRequest.setServiceBatch(serviceBatch);
        stepExecution.getJobExecution().getExecutionContext().put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");

        try {
            locatorImpl.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
            stepExecution.getJobExecution().getExecutionContext().put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch(Exception ex) {
            LOGGER.error("Error occured while calling createBatch Service " + ex, ex);
            // No further action as this does not really impact the batch run.
        }

        return RepeatStatus.FINISHED;
    }

    /**
     * This method called before step starts and assigns the step execution
     * values.
     *
     * @param stepExecution of type StepExecution
     */
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.debug("inside before step  execution context:" + stepExecution);
        this.stepExecution = stepExecution;
    }
}

