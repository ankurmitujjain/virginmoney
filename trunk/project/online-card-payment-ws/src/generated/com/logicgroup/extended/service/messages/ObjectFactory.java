
package com.logicgroup.extended.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.logicgroup.extended.service.messages package. 
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

    private final static QName _AuthResponseExt_QNAME = new QName("http://secure-payment-processing.com/", "AuthResponseExt");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.logicgroup.extended.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cv2Avs }
     * 
     */
    public Cv2Avs createCv2Avs() {
        return new Cv2Avs();
    }

    /**
     * Create an instance of {@link Metrics }
     * 
     */
    public Metrics createMetrics() {
        return new Metrics();
    }

    /**
     * Create an instance of {@link AuthoriseExistingCardResponse }
     * 
     */
    public AuthoriseExistingCardResponse createAuthoriseExistingCardResponse() {
        return new AuthoriseExistingCardResponse();
    }

    /**
     * Create an instance of {@link AuthoriseExistingCard }
     * 
     */
    public AuthoriseExistingCard createAuthoriseExistingCard() {
        return new AuthoriseExistingCard();
    }

    /**
     * Create an instance of {@link AuthorisationCodeResponse }
     * 
     */
    public AuthorisationCodeResponse createAuthorisationCodeResponse() {
        return new AuthorisationCodeResponse();
    }

    /**
     * Create an instance of {@link Acquirer }
     * 
     */
    public Acquirer createAcquirer() {
        return new Acquirer();
    }

    /**
     * Create an instance of {@link AuthorisationResponse }
     * 
     */
    public AuthorisationResponse createAuthorisationResponse() {
        return new AuthorisationResponse();
    }

    /**
     * Create an instance of {@link Authorise }
     * 
     */
    public Authorise createAuthorise() {
        return new Authorise();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ArrayOfProtocol }
     * 
     */
    public ArrayOfProtocol createArrayOfProtocol() {
        return new ArrayOfProtocol();
    }

    /**
     * Create an instance of {@link Bank }
     * 
     */
    public Bank createBank() {
        return new Bank();
    }

    /**
     * Create an instance of {@link Transaction }
     * 
     */
    public Transaction createTransaction() {
        return new Transaction();
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link CompleteResponse }
     * 
     */
    public CompleteResponse createCompleteResponse() {
        return new CompleteResponse();
    }

    /**
     * Create an instance of {@link Apacs }
     * 
     */
    public Apacs createApacs() {
        return new Apacs();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link Complete }
     * 
     */
    public Complete createComplete() {
        return new Complete();
    }

    /**
     * Create an instance of {@link AuthoriseResponse }
     * 
     */
    public AuthoriseResponse createAuthoriseResponse() {
        return new AuthoriseResponse();
    }

    /**
     * Create an instance of {@link Protocol }
     * 
     */
    public Protocol createProtocol() {
        return new Protocol();
    }

    /**
     * Create an instance of {@link SolveError }
     * 
     */
    public SolveError createSolveError() {
        return new SolveError();
    }

    /**
     * Create an instance of {@link AuthResponseExt }
     * 
     */
    public AuthResponseExt createAuthResponseExt() {
        return new AuthResponseExt();
    }

    /**
     * Create an instance of {@link Requester }
     * 
     */
    public Requester createRequester() {
        return new Requester();
    }

    /**
     * Create an instance of {@link CancelResponse }
     * 
     */
    public CancelResponse createCancelResponse() {
        return new CancelResponse();
    }

    /**
     * Create an instance of {@link ExtAuthorisationDetails }
     * 
     */
    public ExtAuthorisationDetails createExtAuthorisationDetails() {
        return new ExtAuthorisationDetails();
    }

    /**
     * Create an instance of {@link Card }
     * 
     */
    public Card createCard() {
        return new Card();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthResponseExt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secure-payment-processing.com/", name = "AuthResponseExt")
    public JAXBElement<AuthResponseExt> createAuthResponseExt(AuthResponseExt value) {
        return new JAXBElement<AuthResponseExt>(_AuthResponseExt_QNAME, AuthResponseExt.class, null, value);
    }

}
