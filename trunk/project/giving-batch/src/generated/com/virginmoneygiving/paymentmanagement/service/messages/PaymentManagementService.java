
package com.virginmoneygiving.paymentmanagement.service.messages;

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
@WebServiceClient(name = "paymentManagementService", targetNamespace = "http://www.virginmoneygiving.com/payment-management-ws/", wsdlLocation = "http://10.0.1.135:8080/payment-management-ws/paymentManagement?wsdl")
public class PaymentManagementService
    extends Service
{

    private final static URL PAYMENTMANAGEMENTSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.virginmoneygiving.paymentmanagement.service.messages.PaymentManagementService.class.getResource(".");
            url = new URL(baseUrl, "http://10.0.1.135:8080/payment-management-ws/paymentManagement?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://10.0.1.135:8080/payment-management-ws/paymentManagement?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        PAYMENTMANAGEMENTSERVICE_WSDL_LOCATION = url;
    }

    public PaymentManagementService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PaymentManagementService() {
        super(PAYMENTMANAGEMENTSERVICE_WSDL_LOCATION, new QName("http://www.virginmoneygiving.com/payment-management-ws/", "paymentManagementService"));
    }

    /**
     * 
     * @return
     *     returns PaymentManagementWs
     */
    @WebEndpoint(name = "PaymentManagementWs")
    public PaymentManagementWs getPaymentManagementWs() {
        return super.getPort(new QName("http://www.virginmoneygiving.com/payment-management-ws/", "PaymentManagementWs"), PaymentManagementWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PaymentManagementWs
     */
    @WebEndpoint(name = "PaymentManagementWs")
    public PaymentManagementWs getPaymentManagementWs(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.virginmoneygiving.com/payment-management-ws/", "PaymentManagementWs"), PaymentManagementWs.class, features);
    }

}
