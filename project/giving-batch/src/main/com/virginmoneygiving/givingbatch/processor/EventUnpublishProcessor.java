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
import com.virginmoneygiving.giving.service.GivingService;
import com.virginmoneygiving.givingbatch.delegate.BatchDelegate;
import com.virginmoneygiving.givingbatch.common.Constant;
import com.virginmoneygiving.givingbatch.common.Util;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventService;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.*;

import javax.annotation.Resource;

/**
 * Make the event publish by updating its status to 'PUB'.
 * 
 * @author dibaskumarp
 */
public class EventUnpublishProcessor implements StepExecutionListener, ItemProcessor<Event, Event> {

    /** instance for logger. * */
    private static Logger LOGGER =
            Logger.getLogger(EventUnpublishProcessor.class);

    /** giving Service. * */
    private GivingService givingService;
    
    /** Create instance of batch delegate. */
    private BatchDelegate batchDelegate;

    /** Index events when they're un-published. */
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
     * Procees the event to unpublish it.
     * 
     * @param event of type Event.
     * 
     * @return Event
     */
    public Event process(Event event) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EventUnpublishProcessor.process - Start ID/Name: " + event.getId() + "/" + event.getName());
        }
        try {
            updateBatchStatus(event.getId());
        } catch (Exception ex) {
            try {
                batchDelegate.raiseAlert("EventUnpublishIsFailed",
                        "EventJobId2", ex);
            } catch (Exception e) {
                throw new RuntimeException();
            }

        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EventUnpublishProcessor.process - End ID/Name: " + event.getId() + "/" + event.getName());
        }
        return event;
    }

    /**
     * The updateBatchStatus method calls givingService and updates the
     * process status to 'Processed'in the database in DB.
     * 
     * @param eventId - id of Event
     */
    public void updateBatchStatus(Long eventId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EventUnpublishProcessor.updateBatchStatus - Start ID: " + eventId);
        }
        givingService.updateEventStatus(eventId, "EXP");

        try {
            Event event = givingService.fetchEventDetail(eventId);
            // Open-events are indexed without a charity id
            if ( event.getMaximumCharities() == null ) {
                indexerEventService.publishEventDeletedEvent(null, event);
            }
            // Charity events are indexed with the charity ids
            Set<CharitableActivity> charitableActivities =
                event.getCharitableActivities();
            if ( charitableActivities != null &&
                charitableActivities.size() > 0 ) {
                for (CharitableActivity charitableActivity : charitableActivities) {
                    indexerEventService.publishEventDeletedEvent(charitableActivity.getCharity(), event);
                }
            }
            createBatchEntity(event);
        }
        catch ( Exception e ) {
            LOGGER.warn("Unable to un-index Event with id = " + eventId + ": Event will be incorrectly included in search results.", e);
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EventUnpublishProcessor.updateBatchStatus - End ID: " + eventId);
        }
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
     * Gets the indexer event service.
     * 
     * @return the indexerEventService
     */
    public IndexerEventService getIndexerEventService() {
        return indexerEventService;
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
    public ExitStatus afterStep(StepExecution stepexecution) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventUnpublishProcessor - afterStep - START");
        }
        // Update batch status
        String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        executionContext.put(Constant.BATCH_PARAMETER_GLIS_PROCESSING, "NO");
        String errMsg  = "Batch processed.";
        String bStatus = Constant.BATCH_STATUS_SUCCESSFUL;

        try {
            UpdateBatchStatusRequest request = new UpdateBatchStatusRequest();
            request.setBatchNumber(batchNumber);
            request.setEndDateTime(Util.buildXMLGregorianDate());
            request.setStatus(bStatus);
            request.setErrorMessage(errMsg);
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().updateBatchStatus(request);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while setting batch status to complete : EventUnpublishProcessor:afterStep"
                        + e.getMessage(), e);
         }
        catch (Exception e) {
            LOGGER.error("Error setting batch status to complete in afterstep :EventUnpublishProcessor:afterStep", e);
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventUnpublishProcessor - afterStep - END");
        }
        return ExitStatus.COMPLETED;
    }

    /** {@inheritDoc} */
    public void beforeStep(StepExecution stepexecution) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventUnpublishProcessor - beforeStep - START");
        }
        executionContext = stepexecution.getJobExecution().getExecutionContext();
        String jobName = stepexecution.getJobExecution().getJobInstance().getJobName();
        Util.populateBatchPaymentControlData(jobName);
        //Step 1: Here before step starts, we first create the Batch record.
        CreateBatchRequest createBatchRequest = new CreateBatchRequest();
        String batchNumber = Util.getGeneratedBatchNumber(Constant.EVENT_STATUS_EVENT_UNPUBLISHED);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In beforeStep batchNUmber = " + batchNumber);
        }
        ServiceBatch serviceBatch = Util.createServiceBatch(batchNumber, stepexecution);
        serviceBatch.setCurrentJobName("eventUnpublishJob");
        serviceBatch.setNextBatchName("INDEPENDANT");
        createBatchRequest.setServiceBatch(serviceBatch);

        try{
        	givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatch(createBatchRequest);
        	executionContext.put(Constant.BATCH_NUMBER, batchNumber);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch : EventUnpublishProcessor:beforeStep"
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error occured while calling creating batch :EventUnpublishProcessor: beforeStep ", ex);
        }
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventUnpublishProcessor - beforeStep - END");
        }
    }

    /**
     * Creates a batch entity for the event.
     * 
     * @param event Event details
     */
    private void createBatchEntity(Event event) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventUnpublishProcessor - createBatchEntity - START");
        }
    	String batchNumber = (String)executionContext.get(Constant.BATCH_NUMBER);
        LOGGER.debug("Creating Batch Entity (EventId/name): " + event.getId() + "/" + event.getName());
        String msgText = "Event unpublished - Id: " + event.getId() + ", Name: " + event.getName();
        CreateBatchEntityRequest createBatchEntityRequest = new CreateBatchEntityRequest();
        ServiceBatchEntity batchEntity = new ServiceBatchEntity();
        batchEntity.setCurrentStatus(Constant.BATCH_STATUS_SUCCESSFUL);
        batchEntity.setEntityId("0");
        batchEntity.setNextBatchStatus(null);
        batchEntity.setPreviousStatus(null);
        batchEntity.setEntityTypeCode(Constant.ENTITY_CHARITY);
        batchEntity.setEntityReference(null);
        batchEntity.setBaseReference(null);
        batchEntity.setErrorMessage(msgText);
        ServiceBatch serviceBatch = new ServiceBatch();
        serviceBatch.setBatchNumber(batchNumber);
        batchEntity.setBatch(serviceBatch);
        createBatchEntityRequest.setServiceBatchEntity(batchEntity);
        try {
            givingBatchExtServiceLocator.getGivingBatchExtManagementPort().createBatchEntity(createBatchEntityRequest);
        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            LOGGER.error("Error occurred while creating batch entity : EventUnpublishProcessor:createBatchEntity"
                        + e.getMessage(), e);

         }
        catch(Exception ex)
        {
            LOGGER.error("Error while calling creating batch entity : EventUnpublishProcessor:createBatchEntity",ex);
        }

    }
}
