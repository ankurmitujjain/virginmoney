
package com.virginmoneygiving.cardpayment.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.virginmoneygiving.cardpayment.service.messages package. 
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

    private final static QName _ServiceFaultException_QNAME = new QName("http://www.virginmoneygiving.com/faults/", "ServiceFaultException");
    private final static QName _MessageHeader_QNAME = new QName("http://www.virginmoneygiving.com/type/header/", "MessageHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.virginmoneygiving.cardpayment.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthoriseWithCardRequest }
     * 
     */
    public AuthoriseWithCardRequest createAuthoriseWithCardRequest() {
        return new AuthoriseWithCardRequest();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link AuthoriseWithAuthenticationRequest }
     * 
     */
    public AuthoriseWithAuthenticationRequest createAuthoriseWithAuthenticationRequest() {
        return new AuthoriseWithAuthenticationRequest();
    }

    /**
     * Create an instance of {@link ServiceAuthorisationResult }
     * 
     */
    public ServiceAuthorisationResult createServiceAuthorisationResult() {
        return new ServiceAuthorisationResult();
    }

    /**
     * Create an instance of {@link AuthoriseWithCardResponse }
     * 
     */
    public AuthoriseWithCardResponse createAuthoriseWithCardResponse() {
        return new AuthoriseWithCardResponse();
    }

    /**
     * Create an instance of {@link ServiceCardSecurityInformation }
     * 
     */
    public ServiceCardSecurityInformation createServiceCardSecurityInformation() {
        return new ServiceCardSecurityInformation();
    }

    /**
     * Create an instance of {@link ServiceCardDetails }
     * 
     */
    public ServiceCardDetails createServiceCardDetails() {
        return new ServiceCardDetails();
    }

    /**
     * Create an instance of {@link AuthoriseWithAuthenticationResponse }
     * 
     */
    public AuthoriseWithAuthenticationResponse createAuthoriseWithAuthenticationResponse() {
        return new AuthoriseWithAuthenticationResponse();
    }

    /**
     * Create an instance of {@link AuthoriseWithTokenRequest }
     * 
     */
    public AuthoriseWithTokenRequest createAuthoriseWithTokenRequest() {
        return new AuthoriseWithTokenRequest();
    }

    /**
     * Create an instance of {@link ServicePaymentDetails }
     * 
     */
    public ServicePaymentDetails createServicePaymentDetails() {
        return new ServicePaymentDetails();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link ServiceNameValuePair }
     * 
     */
    public ServiceNameValuePair createServiceNameValuePair() {
        return new ServiceNameValuePair();
    }

    /**
     * Create an instance of {@link AuthoriseWithTokenResponse }
     * 
     */
    public AuthoriseWithTokenResponse createAuthoriseWithTokenResponse() {
        return new AuthoriseWithTokenResponse();
    }

    /**
     * Create an instance of {@link MessageHeader }
     * 
     */
    public MessageHeader createMessageHeader() {
        return new MessageHeader();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/faults/", name = "ServiceFaultException")
    public JAXBElement<ServiceFault> createServiceFaultException(ServiceFault value) {
        return new JAXBElement<ServiceFault>(_ServiceFaultException_QNAME, ServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.virginmoneygiving.com/type/header/", name = "MessageHeader")
    public JAXBElement<MessageHeader> createMessageHeader(MessageHeader value) {
        return new JAXBElement<MessageHeader>(_MessageHeader_QNAME, MessageHeader.class, null, value);
    }

}
