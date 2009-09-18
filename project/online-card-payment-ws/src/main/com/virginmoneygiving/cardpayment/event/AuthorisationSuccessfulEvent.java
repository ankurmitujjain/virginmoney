package com.virginmoneygiving.cardpayment.event;

/**
 * Signifies that a card authorisation was successful.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class AuthorisationSuccessfulEvent extends BasePaymentEvent {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 171252163122119L;

    /**
     * The Constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     */
    public AuthorisationSuccessfulEvent(Object source, String guid, String message) {
        super(source, guid, message);
    }
}
