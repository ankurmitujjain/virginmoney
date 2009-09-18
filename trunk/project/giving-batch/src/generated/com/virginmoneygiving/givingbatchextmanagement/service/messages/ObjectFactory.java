
package com.virginmoneygiving.givingbatchextmanagement.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.virginmoneygiving.givingbatchextmanagement.service.messages package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.virginmoneygiving.givingbatchextmanagement.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceBatch }
     * 
     */
    public ServiceBatch createServiceBatch() {
        return new ServiceBatch();
    }

    /**
     * Create an instance of {@link CreateBatchResponse }
     * 
     */
    public CreateBatchResponse createCreateBatchResponse() {
        return new CreateBatchResponse();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link UpdateAllBatchEntityStatusRequest }
     * 
     */
    public UpdateAllBatchEntityStatusRequest createUpdateAllBatchEntityStatusRequest() {
        return new UpdateAllBatchEntityStatusRequest();
    }

    /**
     * Create an instance of {@link MessageHeader }
     * 
     */
    public MessageHeader createMessageHeader() {
        return new MessageHeader();
    }

    /**
     * Create an instance of {@link FetchBatchEntityResponse }
     * 
     */
    public FetchBatchEntityResponse createFetchBatchEntityResponse() {
        return new FetchBatchEntityResponse();
    }

    /**
     * Create an instance of {@link UpdateBatchEntityStatusResponse }
     * 
     */
    public UpdateBatchEntityStatusResponse createUpdateBatchEntityStatusResponse() {
        return new UpdateBatchEntityStatusResponse();
    }

    /**
     * Create an instance of {@link FetchBatchEntityRequest }
     * 
     */
    public FetchBatchEntityRequest createFetchBatchEntityRequest() {
        return new FetchBatchEntityRequest();
    }

    /**
     * Create an instance of {@link UpdateBatchEntityStatusRequest }
     * 
     */
    public UpdateBatchEntityStatusRequest createUpdateBatchEntityStatusRequest() {
        return new UpdateBatchEntityStatusRequest();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link CreateBatchEntityRequest }
     * 
     */
    public CreateBatchEntityRequest createCreateBatchEntityRequest() {
        return new CreateBatchEntityRequest();
    }

    /**
     * Create an instance of {@link CreateBatchRequest }
     * 
     */
    public CreateBatchRequest createCreateBatchRequest() {
        return new CreateBatchRequest();
    }

    /**
     * Create an instance of {@link ServiceBatchEntity }
     * 
     */
    public ServiceBatchEntity createServiceBatchEntity() {
        return new ServiceBatchEntity();
    }

    /**
     * Create an instance of {@link UpdateAllBatchEntityStatusResponse }
     * 
     */
    public UpdateAllBatchEntityStatusResponse createUpdateAllBatchEntityStatusResponse() {
        return new UpdateAllBatchEntityStatusResponse();
    }

    /**
     * Create an instance of {@link CreateBatchEntityResponse }
     * 
     */
    public CreateBatchEntityResponse createCreateBatchEntityResponse() {
        return new CreateBatchEntityResponse();
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
