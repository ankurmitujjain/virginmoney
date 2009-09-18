package com.virginmoneygiving.cardpayment.serviceproxy.impl;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.basic.service.messages.ValidateMethodFlag;
import com.logicgroup.basic.service.messages.ValidationExtendedResponse;
import com.logicgroup.extended.service.messages.ArrayOfProtocol;
import com.logicgroup.extended.service.messages.AuthResponseExt;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;
import com.logicgroup.extended.service.messages.Protocol;
import com.logicgroup.extended.service.messages.ProtocolNameEnum;
import com.logicgroup.extended.service.messages.ProtocolResultEnum;
import com.logicgroup.extended.service.messages.TransactionSecurityEnum;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderValidationResponse;
import com.virginmoneygiving.cardpayment.domain.TransactionRecord;
import com.virginmoneygiving.cardpayment.helper.LogicGroupConstants;
import com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelper;
import com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl;
import com.virginmoneygiving.cardpayment.persistence.CardPaymentDAO;
import com.virginmoneygiving.cardpayment.serviceproxy.PaymentProviderServiceProxy;
import com.virginmoneygiving.cardpayment.serviceproxy.ProxySystemException;
import com.virginmoneygiving.cardpayment.transport.TLGWebServiceLocator;

/**
 * Logic Group payment provider Web Service integration.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class LogicGroupPaymentProvider implements PaymentProviderServiceProxy {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(LogicGroupPaymentProvider.class);

    /** Default login token map key. */
    private static final String DEFAULT_LOGIN_TOKEN_KEY = "_DEFAULT";

    /** Maps payment type to login token. */
    private Map<String, String> paymentTypeToLoginToken;

    /** Used for mapping payment type to source ID. */
    private Map<String, String> paymentTypeToSourceMap;

    /** DAO to persist records to the Card Payment schema. */
    @Resource
    private CardPaymentDAO dao;

    /** Web Service Locator. */
    private TLGWebServiceLocator locator;

    /** Secure Payment Helper. */
    @Resource
    private LogicGroupSecurePaymentHelper logicGroupSecurePaymentHelper;

    /**
     * Ensure dependencies have been injected.
     */
    @PostConstruct
    public void afterPropertiesSet() {
        if (locator == null) {
            throw new IllegalStateException("TLGWebServiceLocator is required");
        }        
    }
    
    /**
     * Placeholder method if 'known login token' is only used for batch jobs.
     * 
     * @param paymentType the payment type
     * 
     * @return the string
     */
    protected String login(String paymentType) {
        if (paymentTypeToLoginToken.containsKey(paymentType)) {
            return paymentTypeToLoginToken.get(paymentType);
        } else {
            if (!paymentTypeToLoginToken.containsKey(DEFAULT_LOGIN_TOKEN_KEY)) {
                LOGGER.error("Invalid configuration _DEFAULT login token missing");
            }
            return paymentTypeToLoginToken.get(DEFAULT_LOGIN_TOKEN_KEY);
        }
    }

    /** (@inheritDoc) */
    public ProviderValidationResponse validate(String guid, PaymentDetails paymentDetails, CardDetails cardDetails) {

        // lookup port 
        PaymentAPISoap port = locator.getBasicPort();
        
        String paymentType = paymentDetails.getPaymentType().value();
        String sourceId = paymentTypeToSourceMap.get(paymentType);
        String pRef = paymentDetails.getPaymentRef();

        // Create transaction record
        TransactionRecord txRec = dao.createTransactionRecord(new TransactionRecord(guid, LogicGroupConstants.TRANSACTION_VALIDATE, pRef));

        // invoke
        ValidationExtendedResponse proxyResponse = port.validateCard(
                login(paymentType), 
                cardDetails.getPan(), // card number (PAN)
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getStartMonth(), 
                        cardDetails.getStartYear()), // card start date
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getEndMonth(), 
                        cardDetails.getEndYear()), // card expiry in yyyy-mm
                LogicGroupSecurePaymentHelperImpl.formatIssueNumber(cardDetails
                        .getIssueNumber()), // card issue number
                sourceId, // source ID from map
                txRec.toBase36String(), // transaction number
                ValidateMethodFlag.SOLVE_SE, // validation method
                false, // card token required
                paymentDetails.getPaymentRef(), // customer ref
                guid); // free text note field
        
        String rawResponse = LogicGroupSecurePaymentHelperImpl.flattenValidationResponse(proxyResponse);

        String opCode = proxyResponse.getResult().getCode();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GUID: " + guid + " - Validation response was " + opCode 
                    + "\nMapped response: " + rawResponse);
        }

        ProviderValidationResponse response = new ProviderValidationResponse();

        // build our response object
        response.setRequestTransaction(txRec); // initial request link
        response.setRawResponse(rawResponse);

        response.setResultCode(opCode); 
        response.setErrorMessage(proxyResponse.getResult().getErrorMessage());
        response.setErrorType(proxyResponse.getResult().getErrorType().value());

        // check the system level response 
        if (opCode.trim().equals(LogicGroupConstants.WS_SUCCESS_CODE)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Card Validation operation successful for GUID: " + guid);
            }

            // set card token if one is returned (we're not currently requesting one)
            if (proxyResponse.getCardToken() != null) {
                response.setCardToken(proxyResponse.getCardToken());
            }

            // Get solve response data if present
            if (proxyResponse.getSolveResponse() != null) {
                response.setValidationPassed(proxyResponse.getSolveResponse().getValidationPassed().value());
                response.setAcquirerId(proxyResponse.getSolveResponse().getAcquirer().getAcquirerId());
                response.setAcquirerName(proxyResponse.getSolveResponse().getAcquirer().getAcquirerName());
                
                // VMG-79 Add Solve Error Message from failed TLG Validation to response
                response.setResponseCode(proxyResponse.getSolveResponse().getResponseCode());
                response.setResponseResult(proxyResponse.getSolveResponse().getResponseResult().toString());

                // sometimes get error info from here that is more relevant
                if (proxyResponse.getSolveResponse().getError() != null) {
                    StringBuilder sb = new StringBuilder(response.getErrorMessage());
                    sb.append(" [").append(
                            proxyResponse.getSolveResponse().getError()
                                    .getErrorCode()).append(" : ").append(
                            proxyResponse.getSolveResponse().getError()
                                    .getErrorMessage()).append("]");

                    response.setErrorMessage(sb.toString());
                }
            }
        } else {
            LOGGER.warn("Card Validation operation failed for GUID: " + guid);
        }

        // save the response to the database
        dao.persistProviderResponse(response);

        return response;
    }

    /** (@inheritDoc) */
    public ProviderAuthorisationResponse authoriseWithCv2Avs(String guid, PaymentDetails paymentDetails, CardDetails cardDetails) {

        // lookup portExt
        ExtendedPaymentAPISoap portExt = locator.getExtendedPort();

        // prepare data
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Payment type: " + paymentDetails.getPaymentType().toString());
        }
        String paymentType = paymentDetails.getPaymentType().value();
        String sourceId = paymentTypeToSourceMap.get(paymentType);
        String pRef = paymentDetails.getPaymentRef();
        // create transaction record
        TransactionRecord txRec = dao.createTransactionRecord(new TransactionRecord(guid, LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_CV2AVS, pRef));

        ArrayOfProtocol protocols = new ArrayOfProtocol();

        // all responses require SSL/NONE
        Protocol sslProtocol = new Protocol();
        sslProtocol.setProtocolName(ProtocolNameEnum.SSL);
        sslProtocol.setProtocolResult(ProtocolResultEnum.NONE);
        protocols.getProtocol().add(sslProtocol);

        String houseNumber = "";
        if (cardDetails.getSecurityInfo().getHouseNumber() != null) {
            houseNumber = Integer.toString(cardDetails.getSecurityInfo().getHouseNumber());
        }

        String postCode = "";
        if (cardDetails.getSecurityInfo().getPostcode() != null) {
            postCode = cardDetails.getSecurityInfo().getPostcode();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Invoking TLG extended authorise with cv2/avs for GUID: " + guid);
        }

        // invoke
        AuthResponseExt proxyResponse = portExt.authorise(
                login(paymentType), 
                cardDetails.getPan(), // card number (PAN)
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getStartMonth(), 
                        cardDetails.getStartYear()), // card start date
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getEndMonth(), 
                        cardDetails.getEndYear()), // card expiry in yyyy-mm
                LogicGroupSecurePaymentHelperImpl.formatIssueNumber(cardDetails
                        .getIssueNumber()), // card issue number
                paymentDetails.getAmount().unscaledValue().toString(), // amount in pence
                sourceId, // source ID
                true, // auth only
                txRec.toBase36String(), // transaction number
                paymentDetails.getPaymentRef(), // customer ref
                true, // is Internet
                protocols, 
                "", // eci
                "", // xid -> security transID
                "", // cavv -> security data
                TransactionSecurityEnum.BY_SECURE_SESSION, // Security Mode
                cardDetails.getSecurityInfo().getCsc(), // CV2
                houseNumber, // cv2AvsAddress,
                postCode, // cv2AvsPostCode,
                guid, // Note
                logicGroupSecurePaymentHelper.determineRecurring(paymentType), // isRecurring (added in TLG v4)
                false); // retry (added in TLG v4) 

        // get the system level response code
        String opCode = proxyResponse.getResult().getCode();

        // map response
        ProviderAuthorisationResponse response = LogicGroupSecurePaymentHelperImpl.translateResponse(proxyResponse);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GUID: " + guid + " - Authorisation response " + opCode
                    + "\nMapped response: " + response.getRawResponse());
        }

        response.setRequestTransaction(txRec); // initial request link (FK)

        dao.persistProviderResponse(response);

        // check system level response
        if (!opCode.trim().equals(LogicGroupConstants.WS_SUCCESS_CODE)) {
            String message = "Authorise with Cv2Avs failed for GUID: " + guid 
                + " - code: " + opCode + " error: " 
                + proxyResponse.getResult().getErrorMessage();
            
            LOGGER.error(message);
            throw new ProxySystemException(message);
        }

        return response;
    }

    /** (@inheritDoc) */
    public ProviderAuthorisationResponse authoriseWithAuthentication(String guid, PaymentDetails paymentDetails, CardDetails cardDetails,
            Map<String, String> authenticationData) {

        // lookup portExt
        ExtendedPaymentAPISoap portExt = locator.getExtendedPort();
        
        String paymentType = paymentDetails.getPaymentType().value();
        String sourceId = paymentTypeToSourceMap.get(paymentType);
        String pRef = paymentDetails.getPaymentRef();

        // create transaction record
        TransactionRecord txRec = dao.createTransactionRecord(
                new TransactionRecord(guid, 
                        LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_3DSECURE, pRef));

        // augment the request with necessary protocol data
        ArrayOfProtocol protocols = logicGroupSecurePaymentHelper
            .determineProtocolByMdStatus(authenticationData.get(
                LogicGroupConstants.RES_SCHEME_ID_KEY),
                authenticationData.get(LogicGroupConstants.RES_MD_STATUS_KEY));

        String houseNumber = "";
        if (cardDetails.getSecurityInfo().getHouseNumber() != null) {
            houseNumber = Integer.toString(cardDetails.getSecurityInfo().getHouseNumber());
        }

        String postCode = "";
        if (cardDetails.getSecurityInfo().getPostcode() != null) {
            postCode = cardDetails.getSecurityInfo().getPostcode();
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Invoking TLG extended authorise for GUID: " + guid);
        }

        // invoke
        AuthResponseExt proxyResponse = portExt.authorise(
                login(paymentType), 
                cardDetails.getPan(), // card number (PAN)
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getStartMonth(), 
                        cardDetails.getStartYear()), // card start date
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getEndMonth(), 
                        cardDetails.getEndYear()), // card expiry in yyyy-mm
                LogicGroupSecurePaymentHelperImpl.formatIssueNumber(cardDetails
                        .getIssueNumber()), // card issue number
                paymentDetails.getAmount().unscaledValue().toString(), // amount in pence
                sourceId, // source ID
                true, // auth only
                txRec.toBase36String(), // transaction number
                paymentDetails.getPaymentRef(), // customer ref
                true, // is Internet
                protocols, 
                authenticationData.get(LogicGroupConstants.RES_ECI_KEY), // eci
                authenticationData.get(LogicGroupConstants.XID_KEY), // xid -> security transID
                authenticationData.get(LogicGroupConstants.RES_CAVV_KEY), // cavv -> security data
                logicGroupSecurePaymentHelper.determineSecurityModeByMdStatus(authenticationData.get(LogicGroupConstants.RES_SCHEME_ID_KEY), authenticationData
                        .get(LogicGroupConstants.RES_MD_STATUS_KEY)), // Security Mode
                cardDetails.getSecurityInfo().getCsc(), // CV2
                houseNumber,// cv2AvsAddress,
                postCode,// cv2AvsPostCode,
                guid, // Note
                logicGroupSecurePaymentHelper.determineRecurring(paymentType), // isRecurring (added in TLG v4)
                false); // retry (added in TLG v4) 

        // get the system level response code
        String opCode = proxyResponse.getResult().getCode();

        // map response
        ProviderAuthorisationResponse response = LogicGroupSecurePaymentHelperImpl.translateResponse(proxyResponse);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GUID: " + guid + " - Authorisation response " + opCode
                    + "\nMapped response: " + response.getRawResponse());
        }

        response.setRequestTransaction(txRec); // initial request link (foreign key)
        if (response.getCardToken() == null) {
            if (cardDetails.getToken() != null) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Filling in card token value from initial request");
                }
                response.setCardToken(cardDetails.getToken());
            }
        }

        dao.persistProviderResponse(response);

        // check system level response
        if (!opCode.trim().equals(LogicGroupConstants.WS_SUCCESS_CODE)) {
            String message = "Authorise with authentication failed for GUID: " + guid 
                + " - code: " + opCode + " error: " 
                + proxyResponse.getResult().getErrorMessage();
            
            LOGGER.error(message);
            throw new ProxySystemException(message);
        }

        return response;
    }

    /** (@inheritDoc) */
    public ProviderAuthorisationResponse authoriseByToken(String guid, PaymentDetails paymentDetails, CardDetails cardDetails) {

        // lookup portExt
        ExtendedPaymentAPISoap portExt = locator.getExtendedPort();
        
        String paymentType = paymentDetails.getPaymentType().value();
        String sourceId = paymentTypeToSourceMap.get(paymentType);
        String pRef = paymentDetails.getPaymentRef();
        // create transaction record
        TransactionRecord txRec = dao.createTransactionRecord(
                new TransactionRecord(guid, 
                        LogicGroupConstants.TRANSCTION_AUTHORISE_WITH_TOKEN, pRef));

        ArrayOfProtocol protocols = logicGroupSecurePaymentHelper.determineProtocolByMdStatus("", "");
        
        // Recurring transactions are treated differently from Internet transactions
        boolean recurring = logicGroupSecurePaymentHelper.determineRecurring(paymentType);
        
        // TODO: handle response
        AuthResponseExt proxyResponse = portExt.authoriseExistingCard(
                login(paymentType), 
                cardDetails.getToken(), // card
                paymentDetails.getAmount().unscaledValue().toString(), // amount in pence
                sourceId, // source ID
                txRec.toBase36String(), // transaction number
                paymentDetails.getPaymentRef(), // customer ref
                true, // auth only
                guid, // Note
                // v4 below - some optional fields are blank
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getStartMonth(), 
                        cardDetails.getStartYear()), // card start date
                LogicGroupSecurePaymentHelperImpl.formatDate(cardDetails.getEndMonth(), 
                        cardDetails.getEndYear()), // card expiry in yyyy-mm
                LogicGroupSecurePaymentHelperImpl.formatIssueNumber(cardDetails
                        .getIssueNumber()), // card issue number
                !recurring, // is Internet
                protocols, 
                "", // eci
                "", // xid -> security transID
                "", // cavv -> security data
                logicGroupSecurePaymentHelper.determineSecurityModeByMdStatus("", ""), // Security Mode
                "", // CV2
                "",// cv2AvsAddress,
                "",// cv2AvsPostCode,
                recurring, // isRecurring (added in TLG v4)
                false); // retry (added in TLG v4) 

        // get the system level response code
        String opCode = proxyResponse.getResult().getCode();

        // map response
        ProviderAuthorisationResponse response = LogicGroupSecurePaymentHelperImpl.translateResponse(proxyResponse);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GUID: " + guid + " - Authorisation response was " + opCode
                    + "\nMapped response: " + response.getRawResponse());
        }

        response.setRequestTransaction(txRec); // initial request link (FK)

        // set the original token on it - as Logic Group return null
        response.setCardToken(cardDetails.getToken());

        dao.persistProviderResponse(response);

        // check system level response
        if (!opCode.trim().equals(LogicGroupConstants.WS_SUCCESS_CODE)) {
            String message = "Authorise with token failed for GUID: " + guid 
                + " - code: " + opCode + " error: " 
                + proxyResponse.getResult().getErrorMessage();
            
            LOGGER.error(message);
            throw new ProxySystemException(message);
        }
        
        return response;
    }

    /** (@inheritDoc) */
    public ProviderAuthorisationResponse cancelTransaction(String guid, String transactionId) {

        // lookup portExt
        ExtendedPaymentAPISoap portExt = locator.getExtendedPort();
        
        // create transaction record
        TransactionRecord txRec = dao.createTransactionRecord(
                new TransactionRecord(guid, LogicGroupConstants.TRANSACTION_CANCEL, null));

        // TODO: handle response
        /*
         * expect code 5: Cancelled alert on code 6: unable to cancel
         */
        // TODO: error handling
        AuthResponseExt proxyResponse = portExt.cancel(
                login(DEFAULT_LOGIN_TOKEN_KEY), transactionId);

        // get the system level response code
        String opCode = proxyResponse.getResult().getCode();

        // map response
        ProviderAuthorisationResponse response = LogicGroupSecurePaymentHelperImpl
                .translateResponse(proxyResponse);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GUID: " + guid + " - Cancel response was " + opCode
                    + "\nMapped response: " + response.getRawResponse());
        }
        
        response.setRequestTransaction(txRec); // initial request link (FK)

        // save to the db
        dao.persistProviderResponse(response);

        // check system level response
        if (!opCode.trim().equals(LogicGroupConstants.WS_SUCCESS_CODE)) {
            String message = "Cancel failed for GUID: " + guid 
                + " - code: " + opCode + " error: " 
                + proxyResponse.getResult().getErrorMessage();
            
            LOGGER.error(message);
            throw new ProxySystemException(message);
        }
        
        return response;
    }

    /** (@inheritDoc) */
    public ProviderAuthorisationResponse completeTransaction(String guid, String transactionId) {

        // lookup portExt
        ExtendedPaymentAPISoap portExt = locator.getExtendedPort();
        
        // create transaction record
        TransactionRecord txRec = dao.createTransactionRecord(
                new TransactionRecord(guid, LogicGroupConstants.TRANSACTION_COMPLETE, null));

        // TODO: error handling
        AuthResponseExt proxyResponse = portExt.complete(
                login(DEFAULT_LOGIN_TOKEN_KEY), transactionId);

        // get the system level response code
        String opCode = proxyResponse.getResult().getCode();
        
        // map response
        ProviderAuthorisationResponse response = LogicGroupSecurePaymentHelperImpl
                .translateResponse(proxyResponse);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GUID: " + guid + " - Complete response was " + opCode
                    + "\nMapped response: " + response.getRawResponse()); 
        }
        response.setRequestTransaction(txRec); // initial request link (FK)

        dao.persistProviderResponse(response);

        // check system level response
        if (!opCode.trim().equals(LogicGroupConstants.WS_SUCCESS_CODE)) {
            String message = "Complete failed for GUID: " + guid 
                + " - code: " + opCode + " error: " 
                + proxyResponse.getResult().getErrorMessage();
            
            LOGGER.error(message);
            throw new ProxySystemException(message);
        }
        
        return response;
    }

    // DI
    /**
     * Sets the payment type to source map.
     * 
     * @param paymentTypeToSourceMap the paymentTypeToSourceMap to set
     */
    public void setPaymentTypeToSourceMap(Map<String, String> paymentTypeToSourceMap) {
        this.paymentTypeToSourceMap = paymentTypeToSourceMap;
    }

    /**
     * Sets the payment type to login token.
     * 
     * @param paymentTypeToLoginToken the paymentTypeToLoginToken to set
     */
    public void setPaymentTypeToLoginToken(Map<String, String> paymentTypeToLoginToken) {
        this.paymentTypeToLoginToken = paymentTypeToLoginToken;
    }

    /**
     * Sets the dao.
     * 
     * @param dao the dao to set
     */
    public void setDao(CardPaymentDAO dao) {
        this.dao = dao;
    }

    /**
     * Sets the locator.
     * 
     * @param locator the locator to set
     */
    public void setLocator(TLGWebServiceLocator locator) {
        this.locator = locator;
    }

    /**
     * @param logicGroupSecurePaymentHelper the logicGroupSecurePaymentHelper to set
     */
    public void setLogicGroupSecurePaymentHelper(
            LogicGroupSecurePaymentHelper logicGroupSecurePaymentHelper) {
        this.logicGroupSecurePaymentHelper = logicGroupSecurePaymentHelper;
    }
}
