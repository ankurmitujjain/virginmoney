package com.virginmoneygiving.cardpayment.event;

/**
 * Signifies that a card failed validation.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ValidationFailedEvent extends BasePaymentEvent {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1712521631876319L;

    /**
     * The Constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     */
    public ValidationFailedEvent(Object source, String guid, String message) {
        super(source, guid, message);
    }
}
