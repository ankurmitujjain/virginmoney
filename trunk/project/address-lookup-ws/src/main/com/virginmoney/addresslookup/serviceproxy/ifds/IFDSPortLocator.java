package com.virginmoney.addresslookup.serviceproxy.ifds;

import javax.xml.rpc.ServiceException;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttpPort;

/**
 * Locates the IFDS web service and returns a port to allow access to it. Note that this uses
 * JAX-RPC rather than JAX-WS because of the way IFDS have implemented their service calls.
 *
 * @author woodsn
 *         <p/>
 *         Last change : $Date$  Revision : $Revision$  By : $Author$
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public interface IFDSPortLocator {
    /**
     * Returns a jax-rpc port which can be used to access an IFDS service.
     * @return a jax-rpc port which can be used to access an IFDS service.
     * @throws javax.xml.rpc.ServiceException
         */
     OnlineServicesSoapHttpPort getWebServicePort() throws ServiceException;
}


