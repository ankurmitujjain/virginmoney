package com.virginmoneygiving.cardpayment.event;

import org.springframework.context.ApplicationEvent;

/**
 * Abstract base class for Payment events emitted by OCP.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public abstract class BasePaymentEvent extends ApplicationEvent {

    /** The GUID of the current process. */
    private String guid;
    
    /** The event message. */
    private String message;

    /**
     * Default event constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     */
    public BasePaymentEvent(Object source, String guid, String message) {
        super(source);
        this.guid = guid;
        this.message = message;
    }

    /**
     * Gets the guid.
     * 
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
