package com.virginmoney.addresslookup.exception;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: woodsn
 * Date: 10-Mar-2008
 * Time: 11:04:05
 * To change this template use File | Settings | File Templates.
 */
public class ServiceLocationException   extends Exception {

    public ServiceLocationException(Logger log, String message, Throwable cause) {
        super(message, cause);
        log.error(message);
        log.error(cause);
    }

    public ServiceLocationException(Logger log, String message) {
        super(message);
        log.error(message);
    }
}
