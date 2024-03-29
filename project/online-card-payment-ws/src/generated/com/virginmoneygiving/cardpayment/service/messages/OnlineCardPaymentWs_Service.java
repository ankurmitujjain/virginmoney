
package com.virginmoneygiving.cardpayment.service.messages;

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
@WebServiceClient(name = "online-card-payment-ws", targetNamespace = "http://www.virginmoneygiving.com/online-card-payment/", wsdlLocation = "file:/C:/VMG-workspace/online-card-payment-ws/conf/wsdl/online-card-payment-ws.wsdl")
public class OnlineCardPaymentWs_Service
    extends Service
{

    private final static URL ONLINECARDPAYMENTWS_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.virginmoneygiving.cardpayment.service.messages.OnlineCardPaymentWs_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.virginmoneygiving.cardpayment.service.messages.OnlineCardPaymentWs_Service.class.getResource(".");
            url = new URL(baseUrl, "file:/C:/VMG-workspace/online-card-payment-ws/conf/wsdl/online-card-payment-ws.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/C:/VMG-workspace/online-card-payment-ws/conf/wsdl/online-card-payment-ws.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        ONLINECARDPAYMENTWS_WSDL_LOCATION = url;
    }

    public OnlineCardPaymentWs_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OnlineCardPaymentWs_Service() {
        super(ONLINECARDPAYMENTWS_WSDL_LOCATION, new QName("http://www.virginmoneygiving.com/online-card-payment/", "online-card-payment-ws"));
    }

    /**
     * 
     * @return
     *     returns OnlineCardPaymentWs
     */
    @WebEndpoint(name = "online-card-payment-ws-port")
    public OnlineCardPaymentWs getOnlineCardPaymentWsPort() {
        return super.getPort(new QName("http://www.virginmoneygiving.com/online-card-payment/", "online-card-payment-ws-port"), OnlineCardPaymentWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OnlineCardPaymentWs
     */
    @WebEndpoint(name = "online-card-payment-ws-port")
    public OnlineCardPaymentWs getOnlineCardPaymentWsPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.virginmoneygiving.com/online-card-payment/", "online-card-payment-ws-port"), OnlineCardPaymentWs.class, features);
    }

}
