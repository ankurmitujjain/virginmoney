package com.virginmoneygiving.cardpayment.service.exception;

/**
 * Runtime (unchecked) exception to indicate endpoint failure.
 * 
 * @author ian.priest@opsera.com
 */
public class EndpointUnavailableException extends RuntimeException {

    /**
     * Construtor.
     * 
     * @param message To explain exception.
     */
    public EndpointUnavailableException(String message) {
        super(message);
    }
}
