package com.virginmoneygiving.givingbatch.common;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.GregorianCalendar;

import com.virginmoneygiving.givingbatch.serviceproxy.impl.GivingBatchExtManagementServiceLocatorImpl;
import com.virginmoneygiving.givingbatch.serviceproxy.impl.AlertServiceLocatorImpl;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.FetchServiceBatchRequest;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.FetchServiceBatchResponse;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.GivingBatchExtManagementServiceFaultException;
import com.virginmoneygiving.givingbatchextmanagement.service.messages.ServiceBatch;
import com.virginmoneygiving.givingbatchcontroller.common.JobChainProcessorInterface;
import com.virginmoneygiving.givingbatchcontroller.common.MessageManagerProxyInterface;
import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;
import com.virginmoneygiving.alert.service.messages.AlertContent;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.alert.service.messages.AlertPort;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * Created by IntelliJ IDEA.
 * User: choprah
 * Date: 01-May-2009
 * Time: 08:48:37
 * To change this template use File | Settings | File Templates.
 */
public class JobChainProcessorImpl implements JobChainProcessorInterface {

    /** The logger. */
    private static Logger logger = Logger.getLogger(JobChainProcessorImpl.class);

    /** The jms template. */
    private JmsTemplate jmsTemplate;

    /** The message manager. */
    private MessageManagerProxyInterface messageManager;

    /** The alert service locator. */
    private AlertServiceLocatorImpl alertServiceLocator = null;

    /** GivingBatchExtManagementServiceLocatorImpl. * */
    private GivingBatchExtManagementServiceLocatorImpl locatorImpl;

    /** The batch assimilator helper. */
    private BatchAssimilatorHelperImpl batchAssimilatorHelper;

    /**
     * Sets the jms template.
     * 
     * @param jmsTemplate the new jms template
     */
    @Resource
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Sets the batch assimilator helper.
     * 
     * @param batchAssimilatorHelper the new batch assimilator helper
     */
    @Resource
    public void setBatchAssimilatorHelper(BatchAssimilatorHelperImpl batchAssimilatorHelper) {
        this.batchAssimilatorHelper = batchAssimilatorHelper;
    }

    /**
     * Sets the message manager.
     * 
     * @param messageManager the new message manager
     */
    @Resource
    public void setMessageManager(MessageManagerProxyInterface messageManager) {
        logger.debug("Message manager set.");
        this.messageManager = messageManager;
    }

    /**
     * Sets the locator impl.
     * 
     * @param locatorImpl the locatorImpl to set
     */
    @Resource
    public void setLocatorImpl(GivingBatchExtManagementServiceLocatorImpl locatorImpl) {
        this.locatorImpl = locatorImpl;
    }

    /**
     * Spring injector for the AlertServiceLocatorImpl property.
     * 
     * @param alertServiceLocator return property
     */
    @Resource
    public void setAlertServiceLocator(AlertServiceLocatorImpl alertServiceLocator) {
        this.alertServiceLocator = alertServiceLocator;
    }

    /**
     * Process next job.
     * 
     * @param batchNumber the batch number
     * @param parameters the parameters
     * 
     * @return true, if successful
     */
    public boolean processNextJob(String batchNumber, Map<String, Object> parameters) {
        boolean result = true;
        processNextBatchJob(batchNumber);
        return result;
    }

    /**
     * Process current job.
     * 
     * @param jobName the job name
     * @param parameters the parameters
     * 
     * @return true, if successful
     */
    public boolean processCurrentJob(String jobName, Map<String, Object> parameters) {
        boolean result = true;
        createJobMessage(jobName, parameters);
        return result;
    }

    /**
     * Start job process.
     * 
     * @return true, if successful
     */
    public boolean startJobProcess() {
        boolean result = true;
        String setName = batchAssimilatorHelper.getStartupThreadGroup();
        String setThreads = batchAssimilatorHelper.fetchThreadGroup(setName);
        logger.debug("Starting batch processing with initial thread group/threads: " + setName + "/" + setThreads);
        messageManager.sendThreadGroupMessage(setThreads);
        logger.debug("Started batch processing with initial thread group/threads: " + setName + "/" + setThreads);
        return result;
    }

    /**
     * Creates the job message.
     * 
     * @param jobName the job name
     * @param messageProperties the message properties
     */
    private void createJobMessage(String jobName, Map<String, Object> messageProperties) {
        final Map<String, Object> msgProperties = messageProperties;
        final String job = jobName;
        logger.debug("Sending job request to JMS for job: " + jobName);

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setStringProperty("jobName", job);
                if (msgProperties != null && !msgProperties.isEmpty()) {
                    Iterator itr = msgProperties.keySet().iterator();
                    while (itr.hasNext()) {
                        String key = (String) itr.next();
                        Object value = msgProperties.get(key);
                        Object valueType = value;
                        if (value instanceof Integer) {
                            valueType = ((Integer) value).longValue();
                        }
                        else if (value instanceof String) {
                            valueType = ((String) value);
                        }
                        logger.debug("Now adding property (Key/Value/Type): " + key + "/" + value + "/"
                                + value.getClass().getSimpleName());
                        message.setObjectProperty(key, valueType);
                    }
                }

                return message;
            }
        });

        logger.debug("Job request sent JMS for job: " + jobName);
    }

    /**
     * Process next batch job.
     * 
     * @param batchNumber the batch number
     * 
     * @return the string
     */
    private String processNextBatchJob(String batchNumber) {
        String result = "START";
        String jobName = "";
        Map<String, Object> parameters = new HashMap<String, Object>();
        logger.debug("JobChainProcessorImpl.processNextBatchJobJob Starting for batch: " + batchNumber);
        try {
            FetchServiceBatchRequest request = new FetchServiceBatchRequest();
            request.setBatchNumber(batchNumber);
            FetchServiceBatchResponse response =
                    locatorImpl.getGivingBatchExtManagementPort().fetchServiceBatch(request);
            if (response != null && response.getBatch() != null) {
                ServiceBatch batch = response.getBatch();
                jobName = batch.getCurrentJobName();
                boolean jobChainInd = batch.isJobChainRequest();
                logger.debug("Retrieved batch (" + batch.getBatchNumber() + "), Job = " + jobName
                        + ", Start next chain = " + jobChainInd);
                if (!jobChainInd) {
                    logger.debug("Retrieved batch (" + batch.getBatchNumber() + "), Job = " + jobName
                            + ", Single job request. Stopping here ...");
                    return "SINGLE-JOB";
                }
                String nextJob = batchAssimilatorHelper.fetchNextJob(jobName);
                logger.debug("Next job name: " + nextJob);
                String corrId    = batch.getCorrelationId();
                int    seqSize   = batch.getSequenceSize();
                int    seqNumber = batch.getSequenceNumber();
                boolean chainInd = batch.isJobChainRequest();
                String  batchRunId = batch.getBatchRunId();
                if (corrId != null && corrId.length() > 0) {
                    parameters.put(BatchConstant.SI_CORRELATION_ID_PARAM_NAME, corrId);
                }
                if (batchRunId != null && batchRunId.length() > 0) {
                    parameters.put(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID, batchRunId);
                }
                if (seqSize > 0) {
                    parameters.put(BatchConstant.SI_SEQUENCE_SIZE_PARAM_NAME, seqSize);
                }
                if (seqNumber > 0) {
                    parameters.put(BatchConstant.SI_SEQUENCE_NUMBER_PARAM_NAME, seqNumber);
                }

                if (chainInd) {
                    parameters.put(Constant.BATCH_PARAMETER_EXECUTE_CHAIN, "YES");
                }

                if (nextJob == null || nextJob.length() < 1) {
                    String parentThread = batchAssimilatorHelper.fetchThreadForJob(jobName);
                    logger.debug("End of job thread for job: " + jobName + ". Parent Thread = " + parentThread);
                    sendEndOfThreadMessage(parentThread, parameters);
                } else {
                    createJobMessage(nextJob, parameters);
                }
            } else {
                logger.debug("Could not retrieve batch: " + batchNumber);
                return "INVALID-BATCH";
            }

        }
        catch (GivingBatchExtManagementServiceFaultException e) {
            logger.error("Error retrieving batch (" + batchNumber + ")", e);
            return "ERROR";
        }

        return "END";
    }

    /**
     * Processes the timeout on an aggregator.
     * 
     * @param jobName batch job last processed.
     * @param parameters Job parameters.
     * 
     * @return boolean success flag.
     */
    public boolean processAggregatorTimeout(String jobName, Map<String, Object> parameters) {
        logger.debug("JobChainProcessorImpl.processAggregatorTimeout received message for: " + jobName);
        boolean result = true;
        String corrId = (String) parameters.get(BatchConstant.SI_CORRELATION_ID_PARAM_NAME);
        String batchRunId = (String) parameters.get(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID);
        logger.debug("JobChainProcessorImpl.processAggregatorTimeout CorrID/Batch Run ID: "
                + corrId + "/" + batchRunId);
        String setName = batchAssimilatorHelper.fetchSetForThread(jobName);
        String errorMessage = "Batch aggregator timed out processing Thread group: " + setName
                + "(Thread/Job: " + jobName + " was completed). Correlation ID= " + corrId + ", Batch run ID = "
                + batchRunId;
        raiseAlert(jobName, corrId, batchRunId, errorMessage);
        logger.debug(errorMessage);
        return result;
    }

    /**
     * Send end of thread message.
     * 
     * @param threadName the thread name
     * @param parameters the parameters
     */
    private void sendEndOfThreadMessage(String threadName, Map<String, Object> parameters) {
        logger.debug("Processing End of job thread for Thread = " + threadName);
        messageManager.sendCompletionMessage(threadName, "YES", parameters);
        logger.debug("End of job thread for Thread = " + threadName + " has been sent.");
    }

    /**
     * Raise an alert for a failed job chain.
     * 
     * @param jobName name of the job that was running. E.g.
     * transitionalReliefClaimed
     * @param jobId id of the job. Need to get from batch runtime context
     * @param message to send to the alert service
     * @param batchRunId the batch run id
     */
    private void raiseAlert(String jobName, String jobId, String batchRunId, String message) {
        /*
         * Create the service messages
         */
        AlertContent alertContent = new AlertContent();
        alertContent.setAlertMessage(message);
        GregorianCalendar cal = new GregorianCalendar();
        alertContent.setDateTimeOfAlert(new XMLGregorianCalendarImpl(cal));
        alertContent.setJobId(jobId);
        alertContent.setJobName(jobName + ", Run ID = " + batchRunId);
        alertContent.setService("giving-batch");

        AlertDetail alertDetail = new AlertDetail();
        alertDetail.setContent(alertContent);

        /*
         * Call the alert service
         */
        AlertPort alertService = null;
		try {
			alertService = alertServiceLocator.getAlertPort();
		} catch (Exception e) {
			logger.error("Caught exception trying to find alert service: ", e);
			return;
		}
        alertService.logAlertRequest(alertDetail);
    }
}

