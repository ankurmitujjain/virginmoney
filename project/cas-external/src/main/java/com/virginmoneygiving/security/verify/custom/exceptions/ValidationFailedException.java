package com.virginmoneygiving.security.verify.custom.exceptions;

import org.apache.log4j.Logger;

/**
 * Exception thrown if an account fails authentication.
 *
 * User: choprah
 * Date: 03-Nov-2008
 * Time: 15:44:54
 */
public class ValidationFailedException extends Exception {

    /**
     * Constructor.
     * 
     * @param log Logger to use
     * @param message Error message
     * @param cause Throwable
     */
    public ValidationFailedException(Logger log, String message, Throwable cause) {
        super(message, cause);
        log.error(message);
        log.error(cause);
    }

    /**
     * Constructor.
     * 
     * @param log  Logger
     * @param message error message
     */
    public ValidationFailedException(Logger log, String message) {
        super(message);
        log.error(message);
    }
}
