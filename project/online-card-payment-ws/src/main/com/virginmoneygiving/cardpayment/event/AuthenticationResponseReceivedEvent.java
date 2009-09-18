package com.virginmoneygiving.cardpayment.event;

import java.util.Map;

/**
 * Signifies that a card authorisation failed.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class AuthenticationResponseReceivedEvent extends BasePaymentEvent {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1712521631221327L;

    /** 3D Secure response data. */
    Map<String, String> authResponseData;
    
    /**
     * Default constructor.
     * 
     * @param source the source
     * @param guid the guid
     * @param message the message
     * @param authResponseData the auth response data
     */
    public AuthenticationResponseReceivedEvent(Object source, String guid, 
            String message, Map<String, String> authResponseData) {
        super(source, guid, message);
        this.authResponseData = authResponseData;
    }

    /**
     * Gets the authentication response data.
     * 
     * @return the authResponseData
     */
    public Map<String, String> getAuthResponseData() {
        return authResponseData;
    }
}
