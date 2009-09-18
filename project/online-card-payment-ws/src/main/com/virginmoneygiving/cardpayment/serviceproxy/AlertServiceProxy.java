package com.virginmoneygiving.cardpayment.serviceproxy;

/**
 * Allow the service to raise system alerts.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface AlertServiceProxy {
    
    /**
     * Raise a system alert.
     * 
     * @param message - alert to be raised.
     */
    public void raiseAlert(String message);
}
