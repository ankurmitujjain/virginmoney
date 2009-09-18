
package com.logicgroup.extended.service.messages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.2-b05-RC1
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ExtendedPaymentAPI", targetNamespace = "http://secure-payment-processing.com/", wsdlLocation = "file:/C:/VMG-workspace/online-card-payment-ws/conf/wsdl/ExtendedPaymentAPI.wsdl")
public class ExtendedPaymentAPI
    extends Service
{

    private final static URL EXTENDEDPAYMENTAPI_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.logicgroup.extended.service.messages.ExtendedPaymentAPI.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.logicgroup.extended.service.messages.ExtendedPaymentAPI.class.getResource(".");
            url = new URL(baseUrl, "file:/C:/VMG-workspace/online-card-payment-ws/conf/wsdl/ExtendedPaymentAPI.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/C:/VMG-workspace/online-card-payment-ws/conf/wsdl/ExtendedPaymentAPI.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        EXTENDEDPAYMENTAPI_WSDL_LOCATION = url;
    }

    public ExtendedPaymentAPI(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ExtendedPaymentAPI() {
        super(EXTENDEDPAYMENTAPI_WSDL_LOCATION, new QName("http://secure-payment-processing.com/", "ExtendedPaymentAPI"));
    }

    /**
     * 
     * @return
     *     returns ExtendedPaymentAPISoap
     */
    @WebEndpoint(name = "ExtendedPaymentAPISoap")
    public ExtendedPaymentAPISoap getExtendedPaymentAPISoap() {
        return super.getPort(new QName("http://secure-payment-processing.com/", "ExtendedPaymentAPISoap"), ExtendedPaymentAPISoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExtendedPaymentAPISoap
     */
    @WebEndpoint(name = "ExtendedPaymentAPISoap")
    public ExtendedPaymentAPISoap getExtendedPaymentAPISoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://secure-payment-processing.com/", "ExtendedPaymentAPISoap"), ExtendedPaymentAPISoap.class, features);
    }

}