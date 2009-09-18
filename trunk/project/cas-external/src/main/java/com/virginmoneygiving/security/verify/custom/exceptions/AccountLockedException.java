package com.virginmoneygiving.security.verify.custom.exceptions;

import org.apache.log4j.Logger;

/**
 * This Class is used to throw an Exception, if an account is locked.
 * 
 * User: HunarC
 * Date: 03-Oct-2008
 * Time: 16:31:58
 */
public class AccountLockedException extends Exception {

    /**
     * Constructor.
     * 
     * @param log Logger to use
     * @param message Error message
     * @param cause Throwable
     */
    public AccountLockedException(Logger log, String message, Throwable cause) {
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
    public AccountLockedException(Logger log, String message) {
        super(message);
        log.error(message);
    }
}
