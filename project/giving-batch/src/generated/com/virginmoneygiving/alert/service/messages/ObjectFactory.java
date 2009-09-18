
package com.virginmoneygiving.alert.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.virginmoneygiving.alert.service.messages package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ServiceFaultException_QNAME = new QName("http://www.virginmoney.com/type/crumb/faults/2008/01", "ServiceFaultException");
    private final static QName _AlertDetail_QNAME = new QName("http://www.virginmoney.com/type/alert/2008/07", "AlertDetail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.virginmoneygiving.alert.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ErrorList }
     * 
     */
    public ErrorList createErrorList() {
        return new ErrorList();
    }

    /**
     * Create an instance of {@link AlertDetail }
     * 
     */
    public AlertDetail createAlertDetail() {
        return new AlertDetail();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link AlertContent }
     * 
     */
    public AlertContent createAlertContent() {
        return new AlertContent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoney.com/type/crumb/faults/2008/01", name = "ServiceFaultException")
    public JAXBElement<ServiceFault> createServiceFaultException(ServiceFault value) {
        return new JAXBElement<ServiceFault>(_ServiceFaultException_QNAME, ServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlertDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoney.com/type/alert/2008/07", name = "AlertDetail")
    public JAXBElement<AlertDetail> createAlertDetail(AlertDetail value) {
        return new JAXBElement<AlertDetail>(_AlertDetail_QNAME, AlertDetail.class, null, value);
    }

}
