
package com.virginmoneygiving.cardpayment.service.messages;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.2-b05-RC1
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ServiceFaultException", targetNamespace = "http://www.virginmoneygiving.com/faults/")
public class OnlineCardPaymentServiceFaultException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ServiceFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public OnlineCardPaymentServiceFaultException(String message, ServiceFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public OnlineCardPaymentServiceFaultException(String message, ServiceFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.virginmoneygiving.cardpayment.service.messages.ServiceFault
     */
    public ServiceFault getFaultInfo() {
        return faultInfo;
    }

}