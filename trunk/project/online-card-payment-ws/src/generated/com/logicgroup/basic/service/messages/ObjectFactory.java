
package com.logicgroup.basic.service.messages;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.logicgroup.basic.service.messages package. 
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

    private final static QName _AuthResponse_QNAME = new QName("http://secure-payment-processing.com/", "AuthResponse");
    private final static QName _RefundReturn_QNAME = new QName("http://secure-payment-processing.com/", "RefundReturn");
    private final static QName _SettlementReturn_QNAME = new QName("http://secure-payment-processing.com/", "SettlementReturn");
    private final static QName _LoginReturn_QNAME = new QName("http://secure-payment-processing.com/", "LoginReturn");
    private final static QName _ValidationExtendedResponse_QNAME = new QName("http://secure-payment-processing.com/", "ValidationExtendedResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.logicgroup.basic.service.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthoriseExistingCardResponse }
     * 
     */
    public AuthoriseExistingCardResponse createAuthoriseExistingCardResponse() {
        return new AuthoriseExistingCardResponse();
    }

    /**
     * Create an instance of {@link RefundExistingCard }
     * 
     */
    public RefundExistingCard createRefundExistingCard() {
        return new RefundExistingCard();
    }

    /**
     * Create an instance of {@link SettlementDetails }
     * 
     */
    public SettlementDetails createSettlementDetails() {
        return new SettlementDetails();
    }

    /**
     * Create an instance of {@link LoginDetails }
     * 
     */
    public LoginDetails createLoginDetails() {
        return new LoginDetails();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link ValidateCardResponse }
     * 
     */
    public ValidateCardResponse createValidateCardResponse() {
        return new ValidateCardResponse();
    }

    /**
     * Create an instance of {@link Transaction }
     * 
     */
    public Transaction createTransaction() {
        return new Transaction();
    }

    /**
     * Create an instance of {@link AuthorisationCodeResponse }
     * 
     */
    public AuthorisationCodeResponse createAuthorisationCodeResponse() {
        return new AuthorisationCodeResponse();
    }

    /**
     * Create an instance of {@link Settlement }
     * 
     */
    public Settlement createSettlement() {
        return new Settlement();
    }

    /**
     * Create an instance of {@link AuthorisationResponse }
     * 
     */
    public AuthorisationResponse createAuthorisationResponse() {
        return new AuthorisationResponse();
    }

    /**
     * Create an instance of {@link SettlementExistingCardResponse }
     * 
     */
    public SettlementExistingCardResponse createSettlementExistingCardResponse() {
        return new SettlementExistingCardResponse();
    }

    /**
     * Create an instance of {@link RefundResponse }
     * 
     */
    public RefundResponse createRefundResponse() {
        return new RefundResponse();
    }

    /**
     * Create an instance of {@link RefundExistingCardResponse }
     * 
     */
    public RefundExistingCardResponse createRefundExistingCardResponse() {
        return new RefundExistingCardResponse();
    }

    /**
     * Create an instance of {@link AuthResponse }
     * 
     */
    public AuthResponse createAuthResponse() {
        return new AuthResponse();
    }

    /**
     * Create an instance of {@link Metrics }
     * 
     */
    public Metrics createMetrics() {
        return new Metrics();
    }

    /**
     * Create an instance of {@link AuthoriseResponse }
     * 
     */
    public AuthoriseResponse createAuthoriseResponse() {
        return new AuthoriseResponse();
    }

    /**
     * Create an instance of {@link SettlementResponse }
     * 
     */
    public SettlementResponse createSettlementResponse() {
        return new SettlementResponse();
    }

    /**
     * Create an instance of {@link Bank }
     * 
     */
    public Bank createBank() {
        return new Bank();
    }

    /**
     * Create an instance of {@link ValidationResponse }
     * 
     */
    public ValidationResponse createValidationResponse() {
        return new ValidationResponse();
    }

    /**
     * Create an instance of {@link SettlementExistingCard }
     * 
     */
    public SettlementExistingCard createSettlementExistingCard() {
        return new SettlementExistingCard();
    }

    /**
     * Create an instance of {@link SolveError }
     * 
     */
    public SolveError createSolveError() {
        return new SolveError();
    }

    /**
     * Create an instance of {@link LoginReturn }
     * 
     */
    public LoginReturn createLoginReturn() {
        return new LoginReturn();
    }

    /**
     * Create an instance of {@link Card }
     * 
     */
    public Card createCard() {
        return new Card();
    }

    /**
     * Create an instance of {@link SettlementReturn }
     * 
     */
    public SettlementReturn createSettlementReturn() {
        return new SettlementReturn();
    }

    /**
     * Create an instance of {@link AuthorisationDetails }
     * 
     */
    public AuthorisationDetails createAuthorisationDetails() {
        return new AuthorisationDetails();
    }

    /**
     * Create an instance of {@link Authorise }
     * 
     */
    public Authorise createAuthorise() {
        return new Authorise();
    }

    /**
     * Create an instance of {@link ValidationExtendedResponse }
     * 
     */
    public ValidationExtendedResponse createValidationExtendedResponse() {
        return new ValidationExtendedResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Acquirer }
     * 
     */
    public Acquirer createAcquirer() {
        return new Acquirer();
    }

    /**
     * Create an instance of {@link Apacs }
     * 
     */
    public Apacs createApacs() {
        return new Apacs();
    }

    /**
     * Create an instance of {@link Refund }
     * 
     */
    public Refund createRefund() {
        return new Refund();
    }

    /**
     * Create an instance of {@link Cv2Avs }
     * 
     */
    public Cv2Avs createCv2Avs() {
        return new Cv2Avs();
    }

    /**
     * Create an instance of {@link RefundDetails }
     * 
     */
    public RefundDetails createRefundDetails() {
        return new RefundDetails();
    }

    /**
     * Create an instance of {@link RefundReturn }
     * 
     */
    public RefundReturn createRefundReturn() {
        return new RefundReturn();
    }

    /**
     * Create an instance of {@link Requester }
     * 
     */
    public Requester createRequester() {
        return new Requester();
    }

    /**
     * Create an instance of {@link AuthoriseExistingCard }
     * 
     */
    public AuthoriseExistingCard createAuthoriseExistingCard() {
        return new AuthoriseExistingCard();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ValidateCard }
     * 
     */
    public ValidateCard createValidateCard() {
        return new ValidateCard();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secure-payment-processing.com/", name = "AuthResponse")
    public JAXBElement<AuthResponse> createAuthResponse(AuthResponse value) {
        return new JAXBElement<AuthResponse>(_AuthResponse_QNAME, AuthResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefundReturn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secure-payment-processing.com/", name = "RefundReturn")
    public JAXBElement<RefundReturn> createRefundReturn(RefundReturn value) {
        return new JAXBElement<RefundReturn>(_RefundReturn_QNAME, RefundReturn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SettlementReturn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secure-payment-processing.com/", name = "SettlementReturn")
    public JAXBElement<SettlementReturn> createSettlementReturn(SettlementReturn value) {
        return new JAXBElement<SettlementReturn>(_SettlementReturn_QNAME, SettlementReturn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginReturn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secure-payment-processing.com/", name = "LoginReturn")
    public JAXBElement<LoginReturn> createLoginReturn(LoginReturn value) {
        return new JAXBElement<LoginReturn>(_LoginReturn_QNAME, LoginReturn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationExtendedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secure-payment-processing.com/", name = "ValidationExtendedResponse")
    public JAXBElement<ValidationExtendedResponse> createValidationExtendedResponse(ValidationExtendedResponse value) {
        return new JAXBElement<ValidationExtendedResponse>(_ValidationExtendedResponse_QNAME, ValidationExtendedResponse.class, null, value);
    }

}
