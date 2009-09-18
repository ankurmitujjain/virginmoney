package com.virginmoneygiving.givingbatchcontroller.common;

import java.util.Map;

/**
 * Interface for message management.
 * User: choprah
 * Date: 30-Apr-2009
 * Time: 10:08:24
 */
public interface MessageManagerProxyInterface {
    
    /**
     * Sends the message to start a thread group.
     * 
     * @param messageText Thread group to start.
     * 
     * @return boolean result
     */
    boolean sendThreadGroupMessage(String messageText);

    /**
     * Sends the message to start a given thread.
     * 
     * @param messageText Thread group to start.
     * 
     * @return boolean result
     */
    boolean sendThreadMessage(String messageText);

    /**
     * Sends the message to start a particular batch job.
     * 
     * @param messageText Thread group to start.
     * @param executeChain Indicates whether the subsequent chain should be executed.
     * @param parameters Parameters
     * 
     * @return boolean result
     */
    boolean sendBatchJobMessage(String messageText, String executeChain, Map<String, Object> parameters);

    /**
     * Sends the completion message.
     * 
     * @param messageText current job.
     * @param executeChain Indicates whether the subsequent chain should be executed.
     * @param parameters Job parameters.
     * 
     * @return boolean result
     */    
    boolean sendCompletionMessage(String messageText, String executeChain, Map<String, Object> parameters);

}
