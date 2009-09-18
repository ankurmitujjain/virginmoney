package com.virginmoneygiving.givingbatch.serviceproxy.impl;

import org.apache.log4j.Logger;

import com.virginmoneygiving.givingbatch.exception.ServiceException;
import com.virginmoneygiving.givingmanagement.service.messages.GivingManagementService;
import com.virginmoneygiving.givingmanagement.service.messages.GivingManagementWs;

/**
 * The Giving Management Web service locator, it implements the
 * AbstractWebServiceLocator interface.
 * 
 * @author Srinivas Nageli
 */
public class GivingManagementServiceLocatorImpl extends AbstractGivingBatchServiceLocator {

    /** Creating Logger Instance. */
    private static Logger LOGGER = Logger.getLogger(GivingManagementServiceLocatorImpl.class);

    /** GivingManagementWs Instance. */
    private static GivingManagementWs givingManagementWs = null;

    /** Giving Management Service Name. */
    private static String GIVING_MANAGEMENT = "givingManagement";

    /**
     * Returns an instance of the giving management web service.
     * 
     * @return instance of {@link GivingManagementService}.
     * 
     * @throws ServiceException web service location exception.
     */
    public GivingManagementService getGivingManagementService() throws ServiceException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingManagementServiceLocatorImpl :: getGivingManagementService() method - START");
        }
        long startTime = System.currentTimeMillis();
        GivingManagementService givingManagementService =
                (GivingManagementService) this.getWebService(GIVING_MANAGEMENT);
        long finishTime = System.currentTimeMillis();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GivingManagementService Service proxy established connection : elapsed="
                            + GivingManagementServiceLocatorImpl.formatElapsedSeconds(startTime, finishTime));
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingManagementServiceLocatorImpl :: getGivingManagementService() method - END");
        }
        return givingManagementService;
    }

    /**
     * Returns an instance of the giving management web service port.
     * 
     * @return instance of {@link GivingManagementWs}.
     * 
     * @throws ServiceException web service location exception.
     */
    public GivingManagementWs getGivingManagementPort() throws ServiceException {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingManagementServiceLocatorImpl :: getGivingManagementPort() method - START");
        }
        if (givingManagementWs != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("GivingManagementService Service Reused.");
            }
            return givingManagementWs;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GivingManagementService Service Creating New.");
        }
        GivingManagementService givingManagementService = getGivingManagementService();

        long startTime = System.currentTimeMillis();
        givingManagementWs = givingManagementService.getGivingManagementWs();
        long finishTime = System.currentTimeMillis();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GivingManagementService Service proxy established connection : elapsed="
                            + GivingManagementServiceLocatorImpl.formatElapsedSeconds(startTime, finishTime));
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("GivingManagementServiceLocatorImpl :: getGivingManagementPort() method - END");
        }
        return givingManagementWs;
    }

    /**
     * TODO Delete after service caching properly tested. Returns an instance of
     * the GivingManagementService.
     * 
     * @param startTime the start time
     * @param finishTime the finish time
     * 
     * @return an instance of the GivingManagementService.
     */
    /*
     * public GivingManagementService getGivingManagementService() throws
     * Exception { return (GivingManagementService) this
     * .getWebService("givingManagement"); }
     *//**
     * Returns an instance of the Giving Management SOAP port.
     *
     * @return an instance of the Giving Management SOAP port.
     */
    /*
     * public GivingManagementWs getGivingManagementPort() throws Exception {
     * return getGivingManagementService().getGivingManagementWs(); }
     */

    /**
     * Returns time difference in seconds.
     *
     * @param startTime
     *            Start Time in millisecs
     * @param finishTime
     *            Start Finish in millisecs
     * @return elapsed time in seconds
     */
    private static long formatElapsedSeconds(final long startTime,
            final long finishTime) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside Utility : formatElapsedSeconds() method - START");
        }

        long elapsedTime = finishTime - startTime;
        if (elapsedTime > 0) {
            elapsedTime = elapsedTime / 1000;
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER
                    .trace("Inside Utility : formatElapsedSeconds() method - END");
        }

        return elapsedTime;
    }

}
