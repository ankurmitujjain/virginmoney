package com.virginmoneygiving.cardpayment.service.exception;

/**
 * Thrown by OCP interfaces.
 * 
 * @author ian.priest@opsera.com
 */
public class OnlineCardPaymentServiceException extends Exception {

    /**
     * Instantiates a new online card payment service exception.
     */
    public OnlineCardPaymentServiceException() {
        super();
    }

    /**
     * Instantiates a new online card payment service exception.
     * 
     * @param message the message
     */
    public OnlineCardPaymentServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new online card payment service exception.
     * 
     * @param cause the cause
     */
    public OnlineCardPaymentServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new online card payment service exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public OnlineCardPaymentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
