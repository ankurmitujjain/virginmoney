package com.virginmoney.addresslookup.serviceproxy.ifds;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;
import org.apache.log4j.Logger;
import com.sun.xml.rpc.client.BasicService;
import com.virginmoney.addresslookup.util.ServiceUtil;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttp;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttpPort;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttp_Impl;

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
public class IFDSPortLocatorImpl implements IFDSPortLocator {

    private static Logger logger = Logger.getLogger(IFDSPortLocatorImpl.class);
    private static String SystemPropertyNameIFDSUrl = "IFDS.WEBSERVICE_PORT_ADDRESS";


    /**
     * Returns a jax-rpc port which can be used to access an IFDS service.
     * @return a jax-rpc port which can be used to access an IFDS service.
     * @throws javax.xml.rpc.ServiceException
         */
    public OnlineServicesSoapHttpPort getWebServicePort() throws ServiceException {
        try {
            // Get an instance of the service
            OnlineServicesSoapHttp service = new OnlineServicesSoapHttp_Impl();
//            logger.debug("got service : " + service);
//            ServiceUtil.logServiceDetails(logger, (BasicService)service);

            // Get the Port we need from the Service
            OnlineServicesSoapHttpPort port = service.getOnlineServicesSoapHttpPort();
//            logger.debug("got port: " + port);
//            ServiceUtil.logRMIPort(logger, port);

            // To set the runtime URL we need to convert the Port to a Stub
            Stub stub = (Stub)port;
            stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY,
                System.getProperty(SystemPropertyNameIFDSUrl));
//            logger.debug("port url now=" + ((Stub)port)._getProperty(Stub.ENDPOINT_ADDRESS_PROPERTY));
            return port;
        }
        catch (ServiceException e) {
            logger.error("getWebServicePort() caught unexpected exception " + e,e);
            throw e;
        }
    }

}

