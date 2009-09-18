package com.virginmoneygiving.cardpayment.webservicelocator;

import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;

/**
 * Extend the Web Service Locator interface to cater for setting hostname.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface TLGJaxWsHelper {

    /**
     * Obtain the basic port.
     * 
     * @param hostname the hostname
     * 
     * @return basic port
     */
    public PaymentAPISoap getBasicPortByHostname(String hostname);

    /**
     * Obtain the extended port.
     * 
     * @param hostname the hostname
     * 
     * @return extended port
     */
    public ExtendedPaymentAPISoap getExtendedPortByHostname(String hostname);

}
