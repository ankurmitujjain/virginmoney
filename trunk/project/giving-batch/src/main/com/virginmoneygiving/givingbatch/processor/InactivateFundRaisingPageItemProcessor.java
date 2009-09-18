package com.virginmoneygiving.givingbatch.processor;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.virginmoneygiving.giving.domain.CharitableActivity;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Page;
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventService;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

import javax.annotation.Resource;

/**
 * This Class will make the fund raising page inactive.
 * 
 * @author Yogesh Salunkhe
 */
public class InactivateFundRaisingPageItemProcessor implements StepExecutionListener, ItemProcessor<Page, Page> {

    /** instance for logger. * */
    private static Logger LOGGER =
            Logger.getLogger(InactivateFundRaisingPageItemProcessor.class);

    /** giving Service. * */
    private GivingService givingService;
    
    /** Create instance of batch delegate. */
    private BatchDelegate batchDelegate;

    /** Index events when they're published. */
    private IndexerEventService indexerEventService;
    
    /** Step execution instance. */
    private ExecutionContext executionContext;
    
    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl givingBatchExtServiceLocator;

    /**
     * Sets the giving batch ext service locator.
     * 
     * @param givingBatchExtServiceLocator the new giving batch ext service locator
     */
    @Resource
    public void setGivingBatchExtServiceLocator(GivingBatchExtManagementServiceLocatorImpl
            givingBatchExtServiceLocator) {
        this.givingBatchExtServiceLocator = givingBatchExtServiceLocator;
    }
    
    /**
     * Process the event to publish it.
     * 
     * @param page the page
     * 
     * @return Event
     * 
     * @throws Exception      */
    public Page process(Page page) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("InactivateFundRaisingPageItemProcessor.process - Start ID: " + page.getId() );
        }
        try {
            updatePageStatus(page.getId());
        } catch (Exception exception) {
            try {
                batchDelegate.raiseAlert("InactivateFundRaisingPageIsFailed",
                        "EventJobId1", exception);
            } catch (Exception exception2) {
                throw new RuntimeException();
            }

        }
        createBatchEntity(page.getId(), "InactivateFundRaisingPage");
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("InactivateFundRaisingPageItemProcessor.process - End ID: " + page.getId());
        }
        return page;
    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param pageId the page id
     */
    public void updatePageStatus(Long pageId) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("EventPublishProcessor.updateBatchStatus - Start ID: " + pageId);
        }
        givingService.updatePageStatus(pageId, "INACT");
        

        
        LOGGER.debug("EventPublishProcessor.updateBatchStatus - End ID: " + pageId);
    }

    /**
     * Sets the giving service.
     * 
     * @param givingService the givingService to set
     */
    public void setGivingService(GivingService givingService) {
        this.givingService = givingService;
    }

    /**
     * Sets the batch delegate.
     * 
     * @param batchDelegate the batchDelegate to set
     */
    public void setBatchDelegate(BatchDelegate batchDelegate) {
        this.batchDelegate = batchDelegate;
    }

    /**
     * Sets the indexer event service.
     * 
     * @param indexerEventService the indexerEventService to set
     */
    public void setIndexerEventService(IndexerEventService indexerEventService) {
        this.indexerEventService = indexerEventService;
    }
    
    /** {@inheritDoc} */
    public ExitStatus afterStep(StepExecution stepExecution) {
        // Update batch status
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
        String errMsg  = "Batch processed.";
        String bStatus = Constant.BATCH_STATUS_SUCCESSFUL;

        LOGGER.debug("inside afterStep method - updating batch status");
        try {
            UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setEndDateTime(Util.buildXMLGregorianDate());
            request.setStatus(bStatus);
            request.setErrorMessage(errMsg);
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().updateBatchStatus(request);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while setting batch status to complete : EventPublishProcessor:afterStep"
                        + e.getMessage(), e);
         }
        catch (Exception e) {
            LOGGER.error("Error setting batch status to complete in afterstep :EventPublishProcessor:afterStep", e);
        }

        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(StepExecution stepExecution) {
        
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchGivingControlData(jobName);
        
        LOGGER.debug("In beforeStep");
        executionContext = stepExecution.getJobExecution().getExecutionContext();
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber("InactivateFundRaiserPage");
        LOGGER.debug("In beforeStep batchNUmber = " + batchNumber);
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepExecution);
        createBatchRequest.setServiceBatch(serviceBatch);

        try{
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
            executionContext.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch : EventPublishProcessor:beforeStep"
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error occured while calling creating batch :EventPublishProcessor: beforeStep ", ex);
        }
    }

    
    /**
     * Creates the batch entity.
     * 
     * @param charityId the charity id
     * @param msgText the msg text
     */
    private void createBatchEntity(Long charityId, String msgText) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("InactivateFundRaisingPageItemProcessor::createBatchEntity() - START");
        }
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_SUCCESSFUL);
        batchEntity.setEntityId(charityId.toString());
        batchEntity.setNextBatchStatus(null);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        batchEntity.setEntityReference(charityId.toString());
        batchEntity.setErrorMessage(msgText);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort()
                    .createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch entity : InactivateFundRaisingPageItemProcessor."
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error occured while calling creating batch entity : InactivateFundRaisingPageItemProcessor ",ex);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("InactivateFundRaisingPageItemProcessor::createBatchEntity() - END");
        }

    }
}
