package com.virginmoneygiving.givingbatch.jms.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.virginmoneygiving.givingbatch.jms.BatchMessageDelegate;
import com.virginmoneygiving.givingbatch.launcher.JobExecutionService;
import com.virginmoneygiving.givingbatchcontroller.common.JobChainProcessorInterface;
import org.apache.log4j.Logger;

/**
 * Message-driven POJO implementation for handling job requests.
 * 
 * @author henry
 * @author Ian Priest changed to use TextMessage header properties for job parameters
 */
public class DefaultBatchMessageDelegate implements BatchMessageDelegate {

	/** Log messages. */
    private Logger logger =
            Logger.getLogger(DefaultBatchMessageDelegate.class.getName());

    /** The job execution service. */
    private JobExecutionService jobExecutionService = null;

    /** The job chain processor. */
    private JobChainProcessorInterface jobChainProcessor = null;

    /**
     * Sets the job chain processor.
     * 
     * @param jobChainProcessor the new job chain processor
     */
    @Resource
    public void setJobChainProcessor(JobChainProcessorInterface jobChainProcessor) {
        this.jobChainProcessor = jobChainProcessor;
    }

    /**
     * Receives a batch-job trigger.
     * 
     * Get the job parameters from the message then start the job.
     * Note that if the message properties must include a {@link JobExecutionService.JOB_NAME} property
     * as a minimum
     * 
     * @param message The text message. Content is ignored.
     */
    public void handleMessage(TextMessage message) {
        if(logger.isDebugEnabled()) {
            logger.debug("Message: " + message);
        }
		try {

            String jobName = message.getStringProperty(JobExecutionService.JOB_NAME);
            if (jobName == null || jobName.length() < 1) {
                logger.warn("No job name specified in incoming message. Nothing processed... " + message);
                return;
            }

            if (jobName.equalsIgnoreCase(JobExecutionService.JOB_NAME_BATCH_START)) {
                if(logger.isTraceEnabled()) {
                    logger.trace("Starting the complete batch process ...");
                }
                boolean result = jobChainProcessor.startJobProcess();
                if(logger.isDebugEnabled()) {
                    logger.debug("Started the complete batch process with result: " + result);
                }
                return;
            }
            if(logger.isDebugEnabled()) {
                logger.debug("Starting batch job: " + jobName);
            }
            
            /*
			 * Get job parameters and convert to a suitable Map
			 */
			Enumeration<String> propertyNames = message.getPropertyNames();
			Map<String, Object> jobParametersMap = new HashMap<String, Object>();
			
			while (propertyNames.hasMoreElements()) {

                String propertyName = (String) propertyNames.nextElement();
				Object property = message.getObjectProperty(propertyName);
				if(logger.isDebugEnabled()) {
				    logger.debug("*** Populating Message Property: " + propertyName + "/" + property.toString());
				}

                jobParametersMap.put(propertyName, property);
			}
			
			/*
			 * Attempt to run the job
			 */
	        getJobExecutionService().executeJob(jobParametersMap);
		
		} catch (JMSException e) {
			logger.error("Error encountered trying to process incoming message: " + message.toString(), e);
		}

    }

    /**
     * Gets the job execution service.
     * 
     * @return the jobExecutionService
     */
    public JobExecutionService getJobExecutionService() {
        return jobExecutionService;
    }

    /**
     * Sets the job execution service.
     * 
     * @param jobExecutionService the jobExecutionService to set
     */
    @Resource
    public void setJobExecutionService(JobExecutionService jobExecutionService) {
        this.jobExecutionService = jobExecutionService;
    }

}
