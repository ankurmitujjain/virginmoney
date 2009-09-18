package com.virginmoneygiving.cardpayment.event;

/**
 * Signifies that the card validation operation failed.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ValidationErrorEvent extends BasePaymentEvent {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 17125216318763201L;

    /**
     * The Constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     */
    public ValidationErrorEvent(Object source, String guid, String message) {
        super(source, guid, message);
    }
}
