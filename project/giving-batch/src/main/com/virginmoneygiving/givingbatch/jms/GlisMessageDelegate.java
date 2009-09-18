package com.virginmoneygiving.givingbatch.jms;

import javax.jms.Message;

/**
 * Handle messages from GLIS on the virginMoneyGivingQ.glis_responses queue
 * 
 * Listen for messages from GLIS. They will include payment collected responses.
 * 
 * @author ian.priest@opsera.com
 */
public interface GlisMessageDelegate {

    /**
     * Handle a text message.
     * 
     * @param message the message
     */
    public void handleMessage(Message message);

}
