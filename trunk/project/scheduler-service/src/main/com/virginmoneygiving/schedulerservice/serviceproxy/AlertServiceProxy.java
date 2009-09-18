package com.virginmoneygiving.schedulerservice.serviceproxy;

/**
 * Allow the service to raise system alerts.
 * @author John Allen, Opsera Ltd.
 */
public interface AlertServiceProxy {

    /**
     * Raise a system alert.
     * @param message - alert to be raised.
     */
    void raiseAlert(String message);
}
