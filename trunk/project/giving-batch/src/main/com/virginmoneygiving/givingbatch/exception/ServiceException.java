package com.virginmoneygiving.givingbatch.exception;

/**
 * This class can be used to hold service exception raised in a process for
 * Constructing a new ServiceException with detailed message and cause.
 * @author Srinivas Nageli.
 */
public class ServiceException extends RuntimeException {

    /** Serial version UID. */
    private static final long serialVersionUID = 7975311855236677806L;

    /**
     * Constructs a new ServiceException with detailed message.
     * @param message exception message.
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * Constructs a new ServiceException with detailed cause.
     * @param cause exception cause.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ServiceException with detailed message and cause.
     * @param message exception message.
     * @param cause exception cause.
     */
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
