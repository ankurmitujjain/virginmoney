package com.virginmoneygiving.services.jms;

import java.util.Map;
import java.io.Serializable;

/**
 * The Interface MessageDelegate.
 */
public interface MessageDelegate {
    
    /**
     * Handle message.
     * 
     * @param message the message
     */
    void handleMessage(String message);

    /**
     * Handle message.
     * 
     * @param message the message
     */
    void handleMessage(Map message);

    /**
     * Handle message.
     * 
     * @param message the message
     */
    void handleMessage(byte[] message);

    /**
     * Handle message.
     * 
     * @param message the message
     */
    void handleMessage(Serializable message);

}
