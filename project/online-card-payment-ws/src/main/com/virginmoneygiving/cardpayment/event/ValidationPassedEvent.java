package com.virginmoneygiving.cardpayment.event;

/**
 * Signifies that a card passed validation.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ValidationPassedEvent extends BasePaymentEvent {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1712521631876318L;

    /**
     * The Constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     */
    public ValidationPassedEvent(Object source, String guid, String message) {
        super(source, guid, message);
    }
}
