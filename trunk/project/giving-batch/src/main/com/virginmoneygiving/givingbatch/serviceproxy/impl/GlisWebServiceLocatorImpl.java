package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import org.apache.log4j.Logger;

import com.virginmoney.glis.messages.GlisPort;
import com.virginmoney.glis.messages.GlisService;
import com.virginmoneygiving.givingbatch.exception.GlisServiceException;
import com.virginmoneygiving.givingbatch.serviceproxy.AbstractWebServiceLocator;

/**
 * The Class GlisWebServiceLocatorImpl.
 * 
 * @author Siva Kumar
 */
public class GlisWebServiceLocatorImpl extends AbstractWebServiceLocator {
    
    /** creating Logger Instance. */
    private static Logger LOGGER =
            Logger.getLogger(GlisWebServiceLocatorImpl.class);

    private static GlisPort glisPort = null;
    /**
     * Returns an instance of the Glis web service.
     * 
     * @return an instance of the Glis web service.
     * 
     * @throws exception throws if webservice is not found.
     * @throws Exception the exception
     */
    public GlisService getGlisService() throws GlisServiceException {
        LOGGER.debug("Inside WebserviceLocator Impl get Glis Service.");
        return (GlisService) this.getWebService("glis");
    }

    /**
     * Returns an instance of the Glis SOAP port.
     * 
     * @return an instance of the Giving Glis SOAP port.
     * 
     * @throws exception throws if Port is not found.
     * @throws Exception the exception
     */
    public GlisPort getGlisPort() throws GlisServiceException {
        LOGGER.debug("Inside WebserviceLocator Impl get Glis Port.");
        if (glisPort != null) {
            return glisPort;
        }
        glisPort = getGlisService().getGlisPort();
        return glisPort;
        //return getGlisService().getGlisPort();

    }
}
