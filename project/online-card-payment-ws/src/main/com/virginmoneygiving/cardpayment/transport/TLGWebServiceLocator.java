package com.virginmoneygiving.cardpayment.transport;

import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;

/**
 * Provide access to TLG Web Service Port objects.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface TLGWebServiceLocator {
    
    /**
     * Gets the basic port.
     * 
     * @return the basic port
     */
    public PaymentAPISoap getBasicPort();
    
    /**
     * Gets the extended port.
     * 
     * @return the extended port
     */
    public ExtendedPaymentAPISoap getExtendedPort();
}
