package com.virginmoneygiving.cardpayment.service.impl;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.virginmoneygiving.cardpayment.service.EndpointProbeService;
import com.virginmoneygiving.cardpayment.service.exception.EndpointUnavailableException;
import com.virginmoneygiving.cardpayment.serviceproxy.AlertServiceProxy;

/**
 * Base class for EndpointProbeService implementations.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public abstract class AbstractEndpointProbeService implements
        EndpointProbeService {

    /** Logger. */
    protected Logger LOGGER;

    /** Alert service proxy. */
    @Resource
    private AlertServiceProxy alertService;

    /** (@inheritDoc) */
    public String chooseUrl(String primaryUrl, String secondaryUrl) {
        // null checks are handled in checkEndpoint
        if (checkEndpoint(primaryUrl)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Returning first URL (" + primaryUrl + ")");
            }
            return primaryUrl;
        } else if (checkEndpoint(secondaryUrl)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Returning second URL (" + secondaryUrl + ")");
            }
            return secondaryUrl;
        }

        String message = "Cannot access endpoints [" + primaryUrl + ", "
                + secondaryUrl + "]";

        // log the error
        LOGGER.error(message);

        // use alert service to notify Ops
        alertService.raiseAlert(message);

        // Raise unavailable exception
        throw new EndpointUnavailableException("Neither endpoint available...");
    }

    /**
     * Sets the alert service.
     * 
     * @param alertService the alertService to set
     */
    public void setAlertService(AlertServiceProxy alertService) {
        this.alertService = alertService;
    }

}
