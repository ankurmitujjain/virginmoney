package com.virginmoneygiving.cardpayment.webservicelocator;

import java.util.Map;
import javax.xml.ws.BindingProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;

/**
 * Instantiates Web Service Ports.
 * <br/><i>Allows nice mock testing of the HA Web Service Locator.</i>
 * <br/>Uses the Spring Framework JaxWsPortProxyFactoryBean to obtain the bean
 * to allow for AOP interception.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TLGJaxWsHelperImpl implements TLGJaxWsHelper {
    
    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(TLGJaxWsHelperImpl.class);

    /** Basic ports by hostname. */
    private Map<String,PaymentAPISoap> basicPortMap;

    /** Extended ports by hostname. */
    private Map<String,ExtendedPaymentAPISoap> extendedPortMap;
    
    //+setter DI
    /** Timeout for connecting to TLG service, in mS. */
    private int connectionTimeoutMs = 30000;

    /** Timeout for getting a response from TLG service, in mS. */
    private int responseTimeoutMs = 30000;
    //-setter DI 


    /** (@inheritDoc) */
    public PaymentAPISoap getBasicPortByHostname(String hostname) {
        if (StringUtils.isEmpty(hostname)) {
            throw new IllegalArgumentException("Hostname must be supplied");
        }

        PaymentAPISoap port = basicPortMap.get(hostname);
        if (port == null) {
            LOGGER.error("Received port request for unknown host " + hostname);
            throw new IllegalArgumentException("Unknown host " + hostname);
        }

        // set some timeouts
        ((BindingProvider) port).getRequestContext().put("com.sun.xml.ws.connect.timeout", connectionTimeoutMs);
        ((BindingProvider) port).getRequestContext().put("com.sun.xml.ws.request.timeout", responseTimeoutMs);

        return port;
    }

    /** (@inheritDoc) */
    public ExtendedPaymentAPISoap getExtendedPortByHostname(String hostname) {
        if (StringUtils.isEmpty(hostname)) {
            throw new IllegalArgumentException("Hostname must be supplied");
        }

        ExtendedPaymentAPISoap portExt = extendedPortMap.get(hostname);
        if (portExt == null) {
            LOGGER.error("Received port request for unknown host " + hostname);
            throw new IllegalArgumentException("Unknown host " + hostname);
        }

        // set some timeouts
        ((BindingProvider) portExt).getRequestContext().put("com.sun.xml.ws.connect.timeout", connectionTimeoutMs);
        ((BindingProvider) portExt).getRequestContext().put("com.sun.xml.ws.request.timeout", responseTimeoutMs);

        return portExt;
    }

    //+setter DI methods
    /**
     * Sets the connection timeout ms.
     * 
     * @param connectionTimeoutMs the connectionTimeoutMs to set
     */
    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    /**
     * Sets the response timeout ms.
     * 
     * @param responseTimeoutMs the responseTimeoutMs to set
     */
    public void setResponseTimeoutMs(int responseTimeoutMs) {
        this.responseTimeoutMs = responseTimeoutMs;
    }

    /**
     * Sets the basic port map.
     * 
     * @param basicPortMap the basicPortMap to set
     */
    public void setBasicPortMap(Map<String, PaymentAPISoap> basicPortMap) {
        this.basicPortMap = basicPortMap;
    }

    /**
     * Sets the extended port map.
     * 
     * @param extendedPortMap the extendedPortMap to set
     */
    public void setExtendedPortMap(
            Map<String, ExtendedPaymentAPISoap> extendedPortMap) {
        this.extendedPortMap = extendedPortMap;
    }
    //-setter DI methods
}
