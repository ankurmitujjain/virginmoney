package com.virginmoneygiving.cardpayment.serviceproxy;

/**
 * Indicates a system level failure from a proxy.
 * <br>
 * TODO: Is there a common base exception to extend?
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ProxySystemException extends RuntimeException {

    /** Default serial version UID. */
    private static final long serialVersionUID = -1231231717327398L;
    
    /**
     * Default constructor.
     * 
     * @param message error string
     */
    public ProxySystemException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     * 
     * @param message error string
     * @param t wrapped fault
     */
    public ProxySystemException(String message, Throwable t) {
        super(message, t);
    }
}
