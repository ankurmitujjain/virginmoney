package com.virginmoneygiving.cardpayment.service.exception;

/**
 * Checked exceptin thrown by 3d-Secure classes.
 * 
 * @author ian.priest@opsera.com
 */
public class ThreeDSecureException extends Exception {

    /**
     * Instantiates a new three d secure exception.
     */
    public ThreeDSecureException() {
        super();
    }

    /**
     * Instantiates a new three d secure exception.
     * 
     * @param message the message
     */
    public ThreeDSecureException(String message) {
        super(message);
    }

    /**
     * Instantiates a new three d secure exception.
     * 
     * @param cause the cause
     */
    public ThreeDSecureException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new three d secure exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public ThreeDSecureException(String message, Throwable cause) {
        super(message, cause);
    }

}
