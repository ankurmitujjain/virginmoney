
package com.logicgroup.basic.service.messages;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.2-b05-RC1
 * Generated source version: 2.1
 * 
 */
@WebService(name = "PaymentAPISoap", targetNamespace = "http://secure-payment-processing.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PaymentAPISoap {


    /**
     * Create authorisation using a card PAN.
     * 
     * @param cardNumber
     * @param cardStartDate
     * @param authOnly
     * @param cardExpiryDate
     * @param note
     * @param cardIssueNumber
     * @param cv2AvsAddress
     * @param amount
     * @param loginToken
     * @param cv2AvsPostCode
     * @param cv2AvsCV2
     * @param transactionNumber
     * @param customerReference
     * @param sourceID
     * @return
     *     returns com.logicgroup.basic.service.messages.AuthResponse
     */
    @WebMethod(operationName = "Authorise", action = "http://secure-payment-processing.com/Authorise")
    @WebResult(name = "AuthoriseResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "Authorise", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.Authorise")
    @ResponseWrapper(localName = "AuthoriseResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.AuthoriseResponse")
    public AuthResponse authorise(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardNumber,
        @WebParam(name = "CardStartDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardStartDate,
        @WebParam(name = "CardExpiryDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardExpiryDate,
        @WebParam(name = "CardIssueNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardIssueNumber,
        @WebParam(name = "Amount", targetNamespace = "http://secure-payment-processing.com/")
        String amount,
        @WebParam(name = "SourceID", targetNamespace = "http://secure-payment-processing.com/")
        String sourceID,
        @WebParam(name = "AuthOnly", targetNamespace = "http://secure-payment-processing.com/")
        boolean authOnly,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "Cv2AvsCV2", targetNamespace = "http://secure-payment-processing.com/")
        String cv2AvsCV2,
        @WebParam(name = "Cv2AvsAddress", targetNamespace = "http://secure-payment-processing.com/")
        String cv2AvsAddress,
        @WebParam(name = "Cv2AvsPostCode", targetNamespace = "http://secure-payment-processing.com/")
        String cv2AvsPostCode,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

    /**
     * Create authorisation using a card token.
     * 
     * @param amount
     * @param loginToken
     * @param authOnly
     * @param cardToken
     * @param customerReference
     * @param transactionNumber
     * @param note
     * @param sourceID
     * @return
     *     returns com.logicgroup.basic.service.messages.AuthResponse
     */
    @WebMethod(operationName = "AuthoriseExistingCard", action = "http://secure-payment-processing.com/AuthoriseExistingCard")
    @WebResult(name = "AuthoriseExistingCardResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "AuthoriseExistingCard", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.AuthoriseExistingCard")
    @ResponseWrapper(localName = "AuthoriseExistingCardResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.AuthoriseExistingCardResponse")
    public AuthResponse authoriseExistingCard(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardToken", targetNamespace = "http://secure-payment-processing.com/")
        String cardToken,
        @WebParam(name = "Amount", targetNamespace = "http://secure-payment-processing.com/")
        String amount,
        @WebParam(name = "SourceID", targetNamespace = "http://secure-payment-processing.com/")
        String sourceID,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "AuthOnly", targetNamespace = "http://secure-payment-processing.com/")
        boolean authOnly,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

    /**
     * Authenticate user.
     * 
     * @param password
     * @param userID
     * @param machineName
     * @return
     *     returns com.logicgroup.basic.service.messages.LoginReturn
     */
    @WebMethod(operationName = "Login", action = "http://secure-payment-processing.com/Login")
    @WebResult(name = "LoginResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "Login", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.Login")
    @ResponseWrapper(localName = "LoginResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.LoginResponse")
    public LoginReturn login(
        @WebParam(name = "UserID", targetNamespace = "http://secure-payment-processing.com/")
        String userID,
        @WebParam(name = "Password", targetNamespace = "http://secure-payment-processing.com/")
        String password,
        @WebParam(name = "MachineName", targetNamespace = "http://secure-payment-processing.com/")
        String machineName);

    /**
     * Create a settlement using a card token.
     * 
     * @param amount
     * @param sid
     * @param dateToPerformSettlement
     * @param loginToken
     * @param authorisationID
     * @param cardToken
     * @param customerReference
     * @param transactionNumber
     * @param note
     * @param authCode
     * @return
     *     returns com.logicgroup.basic.service.messages.SettlementReturn
     */
    @WebMethod(operationName = "SettlementExistingCard", action = "http://secure-payment-processing.com/SettlementExistingCard")
    @WebResult(name = "SettlementExistingCardResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "SettlementExistingCard", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.SettlementExistingCard")
    @ResponseWrapper(localName = "SettlementExistingCardResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.SettlementExistingCardResponse")
    public SettlementReturn settlementExistingCard(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardToken", targetNamespace = "http://secure-payment-processing.com/")
        String cardToken,
        @WebParam(name = "AuthorisationID", targetNamespace = "http://secure-payment-processing.com/")
        int authorisationID,
        @WebParam(name = "Amount", targetNamespace = "http://secure-payment-processing.com/")
        String amount,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "DateToPerformSettlement", targetNamespace = "http://secure-payment-processing.com/")
        XMLGregorianCalendar dateToPerformSettlement,
        @WebParam(name = "AuthCode", targetNamespace = "http://secure-payment-processing.com/")
        String authCode,
        @WebParam(name = "SID", targetNamespace = "http://secure-payment-processing.com/")
        String sid,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

    /**
     * Create a settlement using a card PAN.
     * 
     * @param cardStartDate
     * @param cardNumber
     * @param amount
     * @param sid
     * @param dateToPerformSettlement
     * @param loginToken
     * @param customerReference
     * @param transactionNumber
     * @param cardExpiryDate
     * @param note
     * @param authCode
     * @param cardIssueNumber
     * @return
     *     returns com.logicgroup.basic.service.messages.SettlementReturn
     */
    @WebMethod(operationName = "Settlement", action = "http://secure-payment-processing.com/Settlement")
    @WebResult(name = "SettlementResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "Settlement", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.Settlement")
    @ResponseWrapper(localName = "SettlementResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.SettlementResponse")
    public SettlementReturn settlement(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardNumber,
        @WebParam(name = "CardStartDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardStartDate,
        @WebParam(name = "CardExpiryDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardExpiryDate,
        @WebParam(name = "CardIssueNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardIssueNumber,
        @WebParam(name = "Amount", targetNamespace = "http://secure-payment-processing.com/")
        String amount,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "DateToPerformSettlement", targetNamespace = "http://secure-payment-processing.com/")
        XMLGregorianCalendar dateToPerformSettlement,
        @WebParam(name = "AuthCode", targetNamespace = "http://secure-payment-processing.com/")
        String authCode,
        @WebParam(name = "SID", targetNamespace = "http://secure-payment-processing.com/")
        String sid,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

    /**
     * Create a refund using a card token.
     * 
     * @param amount
     * @param sid
     * @param settlementID
     * @param loginToken
     * @param dateToPerformRefund
     * @param cardToken
     * @param customerReference
     * @param transactionNumber
     * @param note
     * @param authCode
     * @return
     *     returns com.logicgroup.basic.service.messages.RefundReturn
     */
    @WebMethod(operationName = "RefundExistingCard", action = "http://secure-payment-processing.com/RefundExistingCard")
    @WebResult(name = "RefundExistingCardResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "RefundExistingCard", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.RefundExistingCard")
    @ResponseWrapper(localName = "RefundExistingCardResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.RefundExistingCardResponse")
    public RefundReturn refundExistingCard(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardToken", targetNamespace = "http://secure-payment-processing.com/")
        String cardToken,
        @WebParam(name = "SettlementID", targetNamespace = "http://secure-payment-processing.com/")
        int settlementID,
        @WebParam(name = "Amount", targetNamespace = "http://secure-payment-processing.com/")
        String amount,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "DateToPerformRefund", targetNamespace = "http://secure-payment-processing.com/")
        XMLGregorianCalendar dateToPerformRefund,
        @WebParam(name = "AuthCode", targetNamespace = "http://secure-payment-processing.com/")
        String authCode,
        @WebParam(name = "SID", targetNamespace = "http://secure-payment-processing.com/")
        String sid,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

    /**
     * Create a refund using a card PAN.
     * 
     * @param cardStartDate
     * @param cardNumber
     * @param amount
     * @param sid
     * @param loginToken
     * @param dateToPerformRefund
     * @param customerReference
     * @param transactionNumber
     * @param cardExpiryDate
     * @param note
     * @param authCode
     * @param cardIssueNumber
     * @return
     *     returns com.logicgroup.basic.service.messages.RefundReturn
     */
    @WebMethod(operationName = "Refund", action = "http://secure-payment-processing.com/Refund")
    @WebResult(name = "RefundResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "Refund", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.Refund")
    @ResponseWrapper(localName = "RefundResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.RefundResponse")
    public RefundReturn refund(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardNumber,
        @WebParam(name = "CardStartDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardStartDate,
        @WebParam(name = "CardExpiryDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardExpiryDate,
        @WebParam(name = "CardIssueNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardIssueNumber,
        @WebParam(name = "Amount", targetNamespace = "http://secure-payment-processing.com/")
        String amount,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "DateToPerformRefund", targetNamespace = "http://secure-payment-processing.com/")
        XMLGregorianCalendar dateToPerformRefund,
        @WebParam(name = "AuthCode", targetNamespace = "http://secure-payment-processing.com/")
        String authCode,
        @WebParam(name = "SID", targetNamespace = "http://secure-payment-processing.com/")
        String sid,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

    /**
     * Validate a card using card PAN.
     * 
     * @param cardStartDate
     * @param cardNumber
     * @param sid
     * @param loginToken
     * @param customerReference
     * @param transactionNumber
     * @param cardExpiryDate
     * @param note
     * @param cardTokenRequired
     * @param validateMethodFlag
     * @param cardIssueNumber
     * @return
     *     returns com.logicgroup.basic.service.messages.ValidationExtendedResponse
     */
    @WebMethod(operationName = "ValidateCard", action = "http://secure-payment-processing.com/ValidateCard")
    @WebResult(name = "ValidateCardResult", targetNamespace = "http://secure-payment-processing.com/")
    @RequestWrapper(localName = "ValidateCard", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.ValidateCard")
    @ResponseWrapper(localName = "ValidateCardResponse", targetNamespace = "http://secure-payment-processing.com/", className = "com.logicgroup.basic.service.messages.ValidateCardResponse")
    public ValidationExtendedResponse validateCard(
        @WebParam(name = "LoginToken", targetNamespace = "http://secure-payment-processing.com/")
        String loginToken,
        @WebParam(name = "CardNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardNumber,
        @WebParam(name = "CardStartDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardStartDate,
        @WebParam(name = "CardExpiryDate", targetNamespace = "http://secure-payment-processing.com/")
        String cardExpiryDate,
        @WebParam(name = "CardIssueNumber", targetNamespace = "http://secure-payment-processing.com/")
        String cardIssueNumber,
        @WebParam(name = "SID", targetNamespace = "http://secure-payment-processing.com/")
        String sid,
        @WebParam(name = "TransactionNumber", targetNamespace = "http://secure-payment-processing.com/")
        String transactionNumber,
        @WebParam(name = "ValidateMethodFlag", targetNamespace = "http://secure-payment-processing.com/")
        ValidateMethodFlag validateMethodFlag,
        @WebParam(name = "CardTokenRequired", targetNamespace = "http://secure-payment-processing.com/")
        boolean cardTokenRequired,
        @WebParam(name = "CustomerReference", targetNamespace = "http://secure-payment-processing.com/")
        String customerReference,
        @WebParam(name = "Note", targetNamespace = "http://secure-payment-processing.com/")
        String note);

}
