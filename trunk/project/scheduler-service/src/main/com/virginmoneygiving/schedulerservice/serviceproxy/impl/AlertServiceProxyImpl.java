package com.virginmoneygiving.schedulerservice.serviceproxy.impl;

import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.virginmoneygiving.alert.service.messages.AlertContent;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.alert.service.messages.AlertPort;
import com.virginmoneygiving.schedulerservice.serviceproxy.AlertServiceProxy;

/**
 * Implementation of alert service proxy to communicate with the alert web service.
 * @author John Allen, Opsera Ltd.
 */
public class AlertServiceProxyImpl implements AlertServiceProxy {

    /** logger instance. */
    private static final Logger LOGGER = Logger.getLogger(AlertServiceProxyImpl.class);

    /** The Alert Web Service. */
    private AlertPort port;

    /**
     * Raise alert.
     * @param message the message
     * @see com.virginmoneygiving.schedulerservice.serviceproxy.AlertServiceProxy#raiseAlert(java.lang.String)
     */
    public void raiseAlert(String message) {
        if (!StringUtils.isEmpty(message)) {
            LOGGER.warn("*** ALERT ***: " + message);

            // Create the service messages
            AlertContent alertContent = new AlertContent();
            alertContent.setAlertMessage(message);
            GregorianCalendar cal = new GregorianCalendar();
            alertContent.setDateTimeOfAlert(new XMLGregorianCalendarImpl(cal));
            alertContent.setJobName("N/A");
            alertContent.setService("scheduler-service");

            AlertDetail alertDetail = new AlertDetail();
            alertDetail.setContent(alertContent);

            //Call the alert service
            port.logAlertRequest(alertDetail);
        }
    }

    /**
     * Sets the port.
     * @param port the port to set
     */
    public void setPort(AlertPort port) {
        this.port = port;
    }
}
