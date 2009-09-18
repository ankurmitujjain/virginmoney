package com.virginmoneygiving.security.verify.custom.exceptions;

import org.apache.log4j.Logger;

/**
 * Exception thrown if an account is locked.
 *
 * @author AnkurJ
 */
public class ClassNotSupportedException extends Exception {

    /**
     * Constructor.
     * 
     * @param log Logger to use
     * @param message Error message
     * @param cause Throwable
     */
    public ClassNotSupportedException(Logger log, String message, Throwable cause) {
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
    public ClassNotSupportedException(Logger log, String message) {
        super(message);
        log.error(message);
    }
}
