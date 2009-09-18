package com.virginmoneygiving.cardpayment.event;

/**
 * Signifies that a card authorisation failed.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class AuthorisationErrorEvent extends BasePaymentEvent {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1712521631221327L;

    /**
     * Default constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     */
    public AuthorisationErrorEvent(Object source, String guid, String message) {
        super(source, guid, message);
    }
}
