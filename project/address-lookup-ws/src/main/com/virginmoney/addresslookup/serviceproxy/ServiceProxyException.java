package com.virginmoney.addresslookup.serviceproxy;

import org.apache.log4j.Logger;


/**
 * Insulates calling classes from the Exceptions thrown by the Service Proxy.
 * @author woodsn
 *         <p/>
 *         Last change : $Date$  Revision : $Revision$  By : $Author$
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class ServiceProxyException extends Exception {

    private static Logger logger = Logger.getLogger(ServiceProxyException.class);
    public ServiceProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceProxyException(String message) {
        super(message);
    }
}
