package com.virginmoneygiving.givingbatch.mbean;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

import com.virginmoneygiving.givingbatchcontroller.common.MessageManagerProxyInterface;
import com.virginmoneygiving.givingbatchcontroller.common.BatchAssimilatorHelperInterface;
import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: choprah
 * Date: 30-Apr-2009
 * Time: 09:52:10
 * To change this template use File | Settings | File Templates.
 */
@ManagedResource(objectName = "VirginMoneyGiving:name=batchManagement", description = "Provides management access to the Giving Batch Execution Control Service")
public class BatchControllerMBeanImpl implements BatchControllerMBeanInterface {

    /** The logger. */
    private static Logger logger = Logger.getLogger(BatchControllerMBeanImpl.class);

    /** The message manager. */
    private MessageManagerProxyInterface messageManager;

    /** The batch assimilator helper. */
    private BatchAssimilatorHelperInterface batchAssimilatorHelper;


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
     * Sets the batch assimilator helper.
     * 
     * @param batchAssimilatorHelper the new batch assimilator helper
     */
    @Resource
    public void setBatchAssimilatorHelper(BatchAssimilatorHelperInterface batchAssimilatorHelper) {
        this.batchAssimilatorHelper = batchAssimilatorHelper;
    }

    /**
     * Method to fetch all details.
     * 
     * @return String details
     */
    @ManagedOperation(description = "Starts the complete batch run.")
    public String startBatchRun() {
        logger.debug("Starting batch run");
        String setName = batchAssimilatorHelper.getStartupThreadGroup();
        logger.debug("Starting batch run with initial set: " + setName);
        return startThreadGroup(setName);
    }


    /* (non-Javadoc)
     * @see com.virginmoneygiving.givingbatch.mbean.BatchControllerMBeanInterface#startThreadGroup(java.lang.String)
     */
    @ManagedOperation(description = "Start a new thread group.")
    @ManagedOperationParameters({
    @ManagedOperationParameter(name = "threadGroupName", description = "Thread group to start.")})
    public String startThreadGroup(String threadGroupName) {
        String result = "";

        logger.debug("Starting thread group: " + threadGroupName);

        if (threadGroupName == null) {
            result = "Group name is null. No process initiated.";
            return result;
        }

        String setName = threadGroupName.toUpperCase().trim();
        String setThreads = batchAssimilatorHelper.fetchThreadGroup(setName);
        if (setThreads == null) {
            result = "No threads found for group (" + setName + "). No process initiated.";
            return result;
        }
        boolean msgResult = messageManager.sendThreadGroupMessage(setThreads);
        if (msgResult) {
            result = "Thread group (" + threadGroupName + ") successfully initiated with threads: " + setThreads;
        }
        else {
            result = "Thread group (" + threadGroupName + ") failed with threads: " + setThreads;
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.givingbatch.mbean.BatchControllerMBeanInterface#startThread(java.lang.String)
     */
    @ManagedOperation(description = "Start a new thread.")
    @ManagedOperationParameters({
    @ManagedOperationParameter(name = "threadName", description = "Thread to start.")})
    public String startThread(String threadName) {
        String result = "";

        logger.debug("Starting thread: " + threadName);

        if (threadName == null) {
            result = "Thread name is null. No process initiated.";
            return result;
        }

        String tName = threadName.toUpperCase().trim();
        String initialBatchName = batchAssimilatorHelper.fetchInitialJob(tName);
        if (initialBatchName == null) {
            result = "No starting job found for thread (" + tName + "). No process initiated.";
            return result;
        }
        boolean msgResult = messageManager.sendThreadMessage(threadName);
        if (msgResult) {
            result = "Thread (" + threadName + ") successfully started with initial job: " + initialBatchName;
        }
        else {
            result = "Thread (" + threadName + ") failed with job: " + initialBatchName;
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.virginmoneygiving.givingbatch.mbean.BatchControllerMBeanInterface#startBatchJob(java.lang.String, java.lang.String, java.lang.String, int, int)
     */
    @ManagedOperation(description = "Start a new Batch job.")
    @ManagedOperationParameters({
       @ManagedOperationParameter(name = "batchJobName", description = "Batch job to start."),
       @ManagedOperationParameter(name = "executeChain", description = "Process subsequent jobs in thread (YES, NO (=Default))."),
       @ManagedOperationParameter(name = "correlationId", description = "Correlation ID if resuming a job."),
       @ManagedOperationParameter(name = "sequenceSize", description = "Sequence size value if resuming a job (or 0)."),
       @ManagedOperationParameter(name = "sequenceNumber", description = "Sequence number value if resuming a job (or 0).")
            })
    public String startBatchJob(String batchJobName, String executeChain, String correlationId, int sequenceSize,
                                int sequenceNumber) {
        String result = "";

        logger.debug("Starting Batch job: " + batchJobName);

        if (batchJobName == null) {
            result = "Batch job name is null. No process initiated.";
            return result;
        }

        if (executeChain != null && executeChain.equalsIgnoreCase("")) {
            executeChain = "NO";
        }

        if (executeChain != null) {
            if (!(executeChain.equalsIgnoreCase("YES") || executeChain.equalsIgnoreCase("NO"))) {
                result = "Valid values are YES or NO (or null).";
                return result;
            }
        }
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (correlationId != null && correlationId.length() > 0) {
            parameters.put(BatchConstant.SI_CORRELATION_ID_PARAM_NAME, correlationId);
        }
        if (sequenceNumber > 0) {
            parameters.put(BatchConstant.SI_SEQUENCE_NUMBER_PARAM_NAME, sequenceNumber);
        }
        if (sequenceSize > 0) {
            parameters.put(BatchConstant.SI_SEQUENCE_SIZE_PARAM_NAME, sequenceSize);
        }
        parameters.put(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID, batchAssimilatorHelper.getNewBatchRunId());

        String initialBatchName = batchAssimilatorHelper.fetchNextJob(batchJobName);
        if (initialBatchName == null) {
            result = "No job found for thread (" + batchJobName + "). No process initiated.";
            return result;
        }
        boolean msgResult = messageManager.sendBatchJobMessage(batchJobName, executeChain, parameters);
        if (msgResult) {
            result = "Batch job process successfully initiated with job: " + batchJobName;
        }
        else {
            result = "Batch job process failed with job: " + batchJobName;
        }
        return result;

    }

    /**
     * Method to fetch all details.
     * 
     * @return String details
     */
    @ManagedOperation(description = "Retrieve all details.")
    public String fetchAllDetails() {
        return buildString();
    }

    /**
     * Creates the output string of all details for the specified logger.
     * 
     * @return Logger details
     */
    private String buildString() {

        StringBuilder sb = new StringBuilder();
        Iterator setsItr  = batchAssimilatorHelper.getThreadGroupMap().keySet().iterator();
        while (setsItr.hasNext()) {
            sb.append("<ul>");
            String setName = (String)setsItr.next();
            String nextSet = batchAssimilatorHelper.fetchNextGroup(setName);
            sb.append("<br/><hr><br/><b>Set name: " + setName + "  (Next set: " + nextSet + ")</b>");
            String setThreads = batchAssimilatorHelper.fetchThreadGroup(setName);
            String[] chains = setThreads.split(":");
            for (String chain : chains) {
                sb.append("<br/><ul>Thread name: " + chain);
                String initJob = batchAssimilatorHelper.fetchInitialJob(chain);
                String nextJob = initJob;
                sb.append("<ul>Job names: ");
                sb.append("<ul>");
                sb.append("<table width=\"90%\" style=\"font: 70% Verdana, Arial, Helvetica, sans-serif; font-size: 12px;\">");
                while (nextJob != null && nextJob.length() > 0) {
                    nextJob = batchAssimilatorHelper.fetchNextJob(initJob);
                    //sb.append("<li>Current job: " + initJob + ", Next job: " + nextJob + "</li>");
                    sb.append("<tr><td>" + initJob + "</td>");
                    sb.append("<td>Next job: " + nextJob + "</td></tr>");
                    initJob = nextJob;
                }
                sb.append("</table>");
                sb.append("</ul>");
                sb.append("</ul>");
                sb.append("</ul>");
            }
            sb.append("</ul>");
        }
        return sb.toString();
    }

}
