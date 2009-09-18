package com.virginmoneygiving.givingbatchcontroller.common;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.integration.message.GenericMessage;

import javax.jms.*;
import javax.annotation.Resource;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Implements the message manager interface.
 * User: choprah
 * Date: 30-Apr-2009
 * Time: 10:09:55
 * To change this template use File | Settings | File Templates.
 */
public class MessageManagerProxyImpl implements MessageManagerProxyInterface {
    
    /** Logger to log messages. */
    private static Logger logger = Logger.getLogger(MessageManagerProxyImpl.class);
    
    /** Name of the JMS Template to use for triggers channel. */
    private JmsTemplate triggersJMSTemplate;
    
    /** Name of the JMS Template to use for batch channel. */
    private JmsTemplate batchJMSTemplate;
    
    /** Name of the JMS Template to use for completions channel. */
    private JmsTemplate completionsJMSTemplate;
    
    /** Helper class. */
    private BatchAssimilatorHelperInterface batchAssimilatorHelper = null;

    /**
     * Spring injector for the  triggersJMSTemplate property.
     * 
     * @param triggersJMSTemplate template to set
     */
    public void setTriggersJMSTemplate(JmsTemplate triggersJMSTemplate) {
        this.triggersJMSTemplate = triggersJMSTemplate;
    }

    /**
     * Spring injector for the  batchJMSTemplate property.
     * 
     * @param batchJMSTemplate template to set
     */
    public void setBatchJMSTemplate(JmsTemplate batchJMSTemplate) {
        this.batchJMSTemplate = batchJMSTemplate;
    }

    /**
     * Spring injector for the  completionsJMSTemplate property.
     * 
     * @param completionsJMSTemplate template to set
     */
    public void setCompletionsJMSTemplate(JmsTemplate completionsJMSTemplate) {
        this.completionsJMSTemplate = completionsJMSTemplate;
    }

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
     * Sends the message to start a thread group.
     * 
     * @param messageText Thread group to start.
     * 
     * @return boolean result
     */
    public boolean sendThreadGroupMessage(String messageText) {
        boolean result = logMessage("Thread Group", triggersJMSTemplate, messageText, null, null);
        return result;
    }

    /**
     * Sends the message to start a given thread.
     * 
     * @param messageText Thread group to start.
     * 
     * @return boolean result
     */
    public boolean sendThreadMessage(String messageText) {
        boolean result = logMessage("Thread", triggersJMSTemplate, messageText, null, null);
        return result;

    }

    /**
     * Sends the message to start a particular batch job.
     * 
     * @param messageText Thread group to start.
     * @param executeChain Indicates whether the subsequent chain should be executed.
     * @param parameters the parameters
     * 
     * @return boolean result
     */
    public boolean sendBatchJobMessage(String messageText, String executeChain, Map<String, Object> parameters) {
        boolean result = logMessage("Batch job", batchJMSTemplate, messageText, executeChain, parameters);
        return result;

    }

    /**
     * Sends the completion message.
     * 
     * @param messageText current job.
     * @param executeChain Indicates whether the subsequent chain should be executed.
     * @param parameters Job parameters.
     * 
     * @return boolean result
     */
    public boolean sendCompletionMessage(String messageText, String executeChain, Map<String, Object> parameters) {
        //showParameterValues(parameters);
        boolean result = logMessage("Completion job", completionsJMSTemplate, messageText, executeChain, parameters);
        return result;

    }

    /**
     * This method sends the message to the specified JMS queue.
     * 
     * @param source Call source
     * @param template name of the queue to send the message to.
     * @param messageText Message text
     * @param executeChain Indicates whether the chain should be executed
     * @param parameters Job parameters
     * 
     * @return boolean status
     */
    private boolean logMessage(String source, JmsTemplate template, String messageText, String executeChain,
                               Map<String, Object> parameters) {
        boolean result = true;
        final String msgText = messageText;
        final String chainInd = executeChain;
        final Map<String, Object> paramMap = parameters;
        logger.debug("BatchController - Source/Message = " + source + "/" + messageText);
    	if (template == null) {
            logger.debug("Message not sent as JMS template is NULL. Source/Message = " + source + "/" + messageText);
        }
        else if (messageText == null ) {
            logger.debug("Message not sent as Message is NULL. Source/Message = " + source + "/" + messageText);
        }
    	if (template != null) {
            logger.debug("BatchController: Queue/Message: " + template.getDefaultDestinationName()
                    + "/" + messageText);
    	}
        try {
            if (template != null) {
                template.send(new MessageCreator() {
    	            public Message createMessage(Session session) throws JMSException {
                        //TextMessage message = session.createTextMessage(msgText);
                        GenericMessage siMessage = (GenericMessage)createSIMessage(msgText, chainInd, paramMap);
                        ObjectMessage message = session.createObjectMessage(siMessage);
                        return message;
    	            }
    	        });
            }

            logger.debug("Message successfully sent to the " + template.getDefaultDestinationName() + " queue");
        }
        catch (Exception e) {
            logger.debug("Unexpected error sending Glis Message to Queue: " + template.getDefaultDestinationName(), e);
            return false;
        }

        return result;
    }

    /**
     * Creates a Spring Integration message.
     * 
     * @param messageText Job name.
     * @param executeChain Indicates whether the chain should be executed
     * @param parameters Job parameters
     * 
     * @return Message new message
     */
    private org.springframework.integration.core.Message createSIMessage(String messageText, String executeChain,
                                                                         Map<String, Object> parameters) {
        logger.debug("Creating SI message for text: " + messageText);
        Map<String, Object> properties = new HashMap<String,Object>();
        properties.put(BatchConstant.JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE, messageText);
        if (executeChain != null && executeChain.length() > 0) {
            properties.put(BatchConstant.BATCH_PARAMETER_EXECUTE_CHAIN, executeChain);
        }
        if (parameters != null && !parameters.isEmpty()) {
            Iterator itr = parameters.keySet().iterator();
            while (itr.hasNext()) {
                String key = (String)itr.next();
                Object value = parameters.get(key);
                logger.debug("Adding SI property : " + key + "/" + value.toString());
                properties.put(key, value);
            }
        }

        if (!properties.containsKey(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID)) {
            properties.put(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID, batchAssimilatorHelper.getNewBatchRunId());
            logger.debug("Added Batch Run ID: " + properties.get(BatchConstant.BATCH_PARAMETER_BATCH_RUN_ID));
        }

        org.springframework.integration.core.Message msg = new GenericMessage("", properties);
        logger.debug("Returning SI message for text: " + messageText);

        return msg;
    }

    /**
     * Debug method to show all job parameters.
     * 
     * @param parameters Job parameters.
     */
/*    private void showParameterValues(Map<String, Object> parameters) {
        logger.debug(" ===== In MessageManager, showParameterValues ======");
        if (parameters == null || parameters.isEmpty()) {
            logger.debug(" ===== In MessageManager, showParameterValues is EMPTY ======");
            return;
        }
        Iterator itr = parameters.keySet().iterator();
        while (itr.hasNext()) {
            String key = (String)itr.next();
            Object value = parameters.get(key);
            logger.debug(" ===== Parameter Key/Value/Type: " + key + "/" + value.toString() + "/"
                    +  value.getClass().getName() + "======");
        }

        logger.debug(" ===== Completed MessageManager, showParameterValues ======");
    }
*/
}
