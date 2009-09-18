package com.virginmoneygiving.givingbatch.jms;

import javax.jms.TextMessage;

/**
 * Handle messages fro GLIS on the virginMoneyGivingQ.glis_responses queue
 * 
 * Listen for messages from GLIS. They will include payment collected responses.
 * 
 * @author ian.priest@opsera.com
 */
public interface BatchMessageDelegate {

    /**
     * Handle a text message.
     * 
     * @param message the message
     */
    public void handleMessage(TextMessage message);

}
