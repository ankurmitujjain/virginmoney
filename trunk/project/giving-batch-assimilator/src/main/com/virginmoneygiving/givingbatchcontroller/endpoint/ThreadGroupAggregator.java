package com.virginmoneygiving.givingbatchcontroller.endpoint;

import java.util.*;

import org.springframework.integration.core.Message;
import org.springframework.integration.message.GenericMessage;
import org.apache.log4j.Logger;
import com.virginmoneygiving.givingbatchcontroller.common.BatchAssimilatorHelperInterface;
import com.virginmoneygiving.givingbatchcontroller.common.BatchConstant;

import javax.annotation.Resource;

/**
 * Aggregator for job threads. This class waits for the different threads to finish, aggregates the
 * result and then sends the completion message on the specified channel.
 * This is configured via XML (and not Annotations) to allow timeout to be configured externally.
 * User: choprah
 * Date: 05-May-2009
 * Time: 10:24:08
 */


//@MessageEndpoint
public class ThreadGroupAggregator {
	
	/** Instance for logger. */
    private static Logger logger = Logger.getLogger(ThreadGroupAggregator.class);
    
    /** Helper class. */
    private BatchAssimilatorHelperInterface batchAssimilatorHelper = null;

    /**
     * Spring injector for the helper class.
     * 
     * @param batchAssimilatorHelper Helper class to set.
     */
    @Resource
    public void setBatchControllerHelper(BatchAssimilatorHelperInterface batchAssimilatorHelper) {
        logger.debug("ThreadGroupAggregator: Setting Batch helper");
        this.batchAssimilatorHelper = batchAssimilatorHelper;
    }

    /**
     * This is the aggregator methodf.
     * 
     * @param messages List of messages
     * 
     * @return Message for completion.
     */
    //@Aggregator(inputChannel = "completions", outputChannel = "triggers", timeout = BatchConstant.AGGREGATOR_TIME_OUT)
    public Message joinThreads(List<Message> messages) {

        Set<String> set = new HashSet<String>();
        logger.debug("Now in Aggregator (XML Version)");
        String batchRunId = "";
        for(Message message : messages) {
            if (message.getHeaders().containsKey(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE)) {
                String jobName = (String)message.getHeaders().get(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE);
                logger.debug("Aggregator received job name: " + jobName);
                set.add(jobName);
            }
            if (message.getHeaders().containsKey(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID)) {
                batchRunId = (String)message.getHeaders().get(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID);
                logger.debug("Aggregator received batch Run ID: " + batchRunId);
            }
        }

        String nextSet = this.batchAssimilatorHelper.findNextSet(set);
        logger.debug("Aggregator-Next set is: " + nextSet);
        if (nextSet != null && nextSet.length() > 0) {
            logger.debug("Triggering next job/chain: " + nextSet);

            // create response
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE, nextSet);
            properties.put(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID, batchRunId);
            Message msg = new GenericMessage("", properties);
            return msg;
        } else {
            logger.debug("Nothing further to do...");
        }

        return null;
    }

    /**
     * This method defines the correlation strategy.
     * 
     * @param message Message to check.
     * 
     * @return String correlation id from the message.
     */
    //@CorrelationStrategy
    public String correlateByParameter(Message message) {
    	return (String)message.getHeaders().get(BatchConstant.SI_CORRELATION_ID_PARAM_NAME);
    }

    /**
     * Creates the SI message to send on the completions channel.
     * 
     * @param messageText Next set to execute.
     * @param executeChain Indicates whether the chain should be executed
     * 
     * @return SI Message
     */
/*    private org.springframework.integration.core.Message createSIMessage(String messageText, String executeChain) {
        logger.debug("Creating SI message for text: " + messageText);
        Map<String, Object> properties = new HashMap<String,Object>();
        properties.put(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE, messageText);
        if (executeChain != null && executeChain.length() > 0) {
            properties.put(BatchConstant.BATCH_PARAMETER_EXECUTE_CHAIN, executeChain);
        }
        org.springframework.integration.core.Message msg = new GenericMessage("", properties);

        logger.debug("Returning SI message for text: " + messageText);

        return msg;
    }*/


}
