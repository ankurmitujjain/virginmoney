package com.virginmoneygiving.cardpayment.helper;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.logicgroup.basic.service.messages.ValidationExtendedResponse;
import com.logicgroup.extended.service.messages.ArrayOfProtocol;
import com.logicgroup.extended.service.messages.AuthResponseExt;
import com.logicgroup.extended.service.messages.AuthorisationResponse;
import com.logicgroup.extended.service.messages.ExtAuthorisationDetails;
import com.logicgroup.extended.service.messages.Protocol;
import com.logicgroup.extended.service.messages.ProtocolNameEnum;
import com.logicgroup.extended.service.messages.ProtocolResultEnum;
import com.logicgroup.extended.service.messages.Result;
import com.logicgroup.extended.service.messages.TransactionSecurityEnum;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;

/**
 * Helper class for the Logic Group Secure Payment proxy service impl. <br>
 * Provides:
 * <ul>
 * <li>response conversion for logging / persistence</li>
 * <li>etc.</li>
 * </ul>
 * TODO: constants for keys
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class LogicGroupSecurePaymentHelperImpl implements LogicGroupSecurePaymentHelper {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(LogicGroupSecurePaymentHelperImpl.class);

    /**
     * Default key for maps.
     */
    public static final String DEFAULT_KEY = "_DEFAULT";
    
    /**
     * Map of TransactionSecurityEnum (security mode) by MD status.
     */
    private Map<String,TransactionSecurityEnum> mdStatusToSecurityModeMap;

    /**
     * Map of ProtocolNameEnum by scheme ID.
     */
    private Map<String,ProtocolNameEnum> schemeIdToProtocolNameMap;

    /**
     * Map of ProtocolResultEnum by MD status.
     */
    private Map<String,ProtocolResultEnum> mdStatusToProtocolResultMap;

    /**
     * The payment types that require the recurring flag.
     */
    private Set<String> recurringPaymentTypes;
    
    /**
     * Flatten response for logging / persistence. Could do in Groovy using
     * property iteration with ?. and { sb.append(it) }
     * 
     * @param proxyResponse the proxy response
     * 
     * @return delimited key value pairs
     */
    public static String flattenResponse(AuthResponseExt proxyResponse) {
        StringBuilder sb = new StringBuilder();

        Result result = proxyResponse.getResult();
        ExtAuthorisationDetails authDetails = proxyResponse.getAuthorisationDetails();
        AuthorisationResponse authResponse = proxyResponse.getSolveResponse();

        if (result != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Flattening result info");
            }
            sb.append("Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(result.getCode()).append(LogicGroupConstants.REC_SEPARATOR);
            sb.append("Error Type").append(LogicGroupConstants.FIELD_SEPARATOR).append(result.getErrorType().value()).append(LogicGroupConstants.REC_SEPARATOR);
            sb.append("Error Message").append(LogicGroupConstants.FIELD_SEPARATOR).append(result.getErrorMessage()).append(LogicGroupConstants.REC_SEPARATOR);
        }

        if (authDetails != null) {
            sb.append("Auth ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(authDetails.getAuthorisationID()).append(LogicGroupConstants.REC_SEPARATOR);
            sb.append("Card Token").append(LogicGroupConstants.FIELD_SEPARATOR).append(authDetails.getCardToken()).append(LogicGroupConstants.REC_SEPARATOR);
            sb.append("Transaction ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(authDetails.getTransactionID()).append(
                    LogicGroupConstants.REC_SEPARATOR);
        }

        if (authResponse != null) {
            if (authResponse.getAcquirer() != null) {
                sb.append("Solve Acquirer ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getAcquirer().getAcquirerId()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Acquirer Name").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getAcquirer().getAcquirerName()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getApacs() != null) {
                sb.append("Solve APACS Headers code").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getHeadersCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Message").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getMessage()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Msg Seq #").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getMessageSeqNumber()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Msg Type").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getMessageType()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Response Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getResponseCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Terminal Dial").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getTerminalDial()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Terminal ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getTerminalId()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve APACS Terminal Type").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getApacs().getTerminalType()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }

            if (authResponse.getAuthorisationCode() != null) {
                sb.append("Solve Authorisation code").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getAuthorisationCode().getCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getAuthorisedAmount() != null) { // String
                sb.append("Solve Authorised Amount").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getAuthorisedAmount()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getBank() != null) {
                sb.append("Solve Bank ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getBank().getBankId()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Bank Name").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getBank().getBankName()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getCard() != null) {
                sb.append("Solve Card Expiry").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCard().getEnd()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Card Start").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCard().getStart()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Card Issue").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCard().getIssue()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getCv2Avs() != null) {
                sb.append("CV2/AVS checked by").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCv2Avs().getBy()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("CV2 response").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCv2Avs().getCV2()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("AVS address response").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCv2Avs().getAddress()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("AVS postcode response").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getCv2Avs().getPostCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getDescription() != null) { // str
                sb.append("Solve Description").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getDescription()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getError() != null) {
                sb.append("Solve Error Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getError().getErrorCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Error Message").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getError().getErrorMessage()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getMerchantNumber() != null) { // str
                sb.append("Solve Merchant Number").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getMerchantNumber()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getMetrics() != null) {
                // may be reserved for future use
                sb.append("Solve Metrics").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getMetrics().toString()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getReference() != null) { // str
                sb.append("Solve Reference").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getReference()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getReplyFormat() != null) { // enum
                sb.append("Solve Reply Format").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getReplyFormat().value()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getRequester() != null) {
                sb.append("Solve Requester Source ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getRequester().getSourceId()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Requester Transaction Number").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                        authResponse.getRequester().getTransactionNumber()).append(LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getResponseCode() != null) { // str
                sb.append("Solve Response Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getResponseCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getResponseResult() != null) { // enum
                sb.append("Solve Response Result").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getResponseResult().value()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getTelephone() != null) { // str
                sb.append("Solve Telephone").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTelephone()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }
            if (authResponse.getTransaction() != null) {
                sb.append("Solve Transaction Amount").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getAmount()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Authorisation Datetime").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                        authResponse.getTransaction().getAuthorisationDateTime()).append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Customer").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getCustomer()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Description").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getDescription())
                        .append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Security").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getSecurity().value())
                        .append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Source").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getSource().value())
                        .append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Start time").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                        authResponse.getTransaction().getTransactionStartTime()).append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Type").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getType().value()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Transaction Verify").append(LogicGroupConstants.FIELD_SEPARATOR).append(authResponse.getTransaction().getVerify().value())
                        .append(LogicGroupConstants.REC_SEPARATOR);
            }
        }

        return sb.toString();
    }

    /**
     * Flatten validation response.
     * 
     * @param response the response
     * 
     * @return flattened name value pairs
     */
    public static String flattenValidationResponse(ValidationExtendedResponse response) {
        StringBuilder sb = new StringBuilder();

        if (response != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Flattening ValidationExtendedResponse info");
            }

            if (response.getResult() != null) {
                sb.append("Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getResult().getCode()).append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Error Type").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getResult().getErrorType().value()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Error Message").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getResult().getErrorMessage()).append(
                        LogicGroupConstants.REC_SEPARATOR);
            }

            sb.append("Card token").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getCardToken()).append(LogicGroupConstants.REC_SEPARATOR);
            sb.append("Internal validation passed").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getInternalValidationPassed().value()).append(
                    LogicGroupConstants.REC_SEPARATOR);

            if (response.getSolveResponse() != null) {
                sb.append("Solve validation passed").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                        response.getSolveResponse().getValidationPassed().value()).append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve authorised amount").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getAuthorisedAmount())
                        .append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Description").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getDescription()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Merchant Number").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getMerchantNumber()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Txn start time").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getTransactionStartTime())
                        .append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Response Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getResponseCode()).append(
                        LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Response Result").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getResponseResult().value())
                        .append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Response Reply Format").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                        response.getSolveResponse().getReplyFormat().value()).append(LogicGroupConstants.REC_SEPARATOR);
                sb.append("Solve Response Reference").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getReference()).append(
                        LogicGroupConstants.REC_SEPARATOR);

                if (response.getSolveResponse().getAcquirer() != null) {
                    sb.append("Solve Acquirer ID").append(LogicGroupConstants.FIELD_SEPARATOR)
                            .append(response.getSolveResponse().getAcquirer().getAcquirerId()).append(LogicGroupConstants.REC_SEPARATOR);
                    sb.append("Solve Acquirer Name").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                            response.getSolveResponse().getAcquirer().getAcquirerName()).append(LogicGroupConstants.REC_SEPARATOR);
                }
                if (response.getSolveResponse().getBank() != null) {
                    sb.append("Solve Bank ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getBank().getBankId()).append(
                            LogicGroupConstants.REC_SEPARATOR);
                    sb.append("Solve Bank name").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getBank().getBankName())
                            .append(LogicGroupConstants.REC_SEPARATOR);
                }
                if (response.getSolveResponse().getError() != null) {
                    sb.append("Solve Error Code").append(LogicGroupConstants.FIELD_SEPARATOR).append(response.getSolveResponse().getError().getErrorCode())
                            .append(LogicGroupConstants.REC_SEPARATOR);
                    sb.append("Solve Error Message").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                            response.getSolveResponse().getError().getErrorMessage()).append(LogicGroupConstants.REC_SEPARATOR);
                }
                // if(response.getSolveResponse().getMetrics() != null) {
                // // reserved for future use
                // }
                if (response.getSolveResponse().getRequester() != null) {
                    sb.append("Solve Requester Source ID").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                            response.getSolveResponse().getRequester().getSourceId()).append(LogicGroupConstants.REC_SEPARATOR);
                    sb.append("Solve Requester Transaction Number").append(LogicGroupConstants.FIELD_SEPARATOR).append(
                            response.getSolveResponse().getRequester().getTransactionNumber()).append(LogicGroupConstants.REC_SEPARATOR);
                }

            }
        }

        return sb.toString();
    }

    /**
     * Converts month and year into YYYY-MM format.
     * 
     * @param month the month
     * @param year the year
     * 
     * @return the string
     */
    public static String formatDate(Integer month, Integer year) {
        if (month == null || year == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Returning date as an empty string...");
            }
            return "";
        }

        String sMonth = Integer.toString(month);

        // TODO: assertions
        StringBuilder sb = new StringBuilder(7);
        sb.append(year);
        sb.append("-");
        sb.append(StringUtils.leftPad(sMonth, 2, "0"));

        String retVal = sb.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Returning date as " + retVal);
        }

        return retVal;
    }

    /**
     * Format issue numbers without padding (as spec says as shown on card).
     * 
     * @param issueNumber the issue number
     * 
     * @return the string
     */
    public static String formatIssueNumber(Integer issueNumber) {
        if (issueNumber == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Returning issue number as an empty string...");
            }
            return "";
        }

        String retVal = issueNumber.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Returning issue number as " + retVal);
        }

        return retVal;
        // return StringUtils.leftPad(issueNumber.toString(), 2, "0");
    }

    /**
     * Determine a protocol result by MD status.
     * <b>The rules are currently the same for both schemes.</b>
     * @param mdStatus
     * @return
     */
    private ProtocolResultEnum determineProtocolResult(String mdStatus) {
        if (mdStatusToProtocolResultMap.containsKey(mdStatus)) {
            return mdStatusToProtocolResultMap.get(mdStatus);
        } else {
            return mdStatusToProtocolResultMap.get(DEFAULT_KEY);
        }
    }
    
    /**
     * Decide the protocol name and result for extended authorise.
     * 
     * @param scheme the scheme
     * @param mdStatus the md status
     * 
     * @return the array of protocol
     */
    public ArrayOfProtocol determineProtocolByMdStatus(String scheme, String mdStatus) {
        ArrayOfProtocol protocols = new ArrayOfProtocol();

        // all responses require SSL/NONE
        Protocol sslProtocol = new Protocol();
        sslProtocol.setProtocolName(ProtocolNameEnum.SSL);
        sslProtocol.setProtocolResult(ProtocolResultEnum.NONE);
        protocols.getProtocol().add(sslProtocol);

        Protocol protocol = new Protocol();

        if(schemeIdToProtocolNameMap.containsKey(scheme)) {
            protocol.setProtocolName(schemeIdToProtocolNameMap.get(scheme));
            protocol.setProtocolResult(determineProtocolResult(mdStatus));
            protocols.getProtocol().add(protocol);
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unknown scheme " + scheme);
            }
        }

        return protocols;
    }

    /**
     * Determine the security mode.
     * 
     * @param scheme - not currently used as rules are same
     * @param mdStatus the md status
     * 
     * @return the transaction security enum
     */
    public TransactionSecurityEnum determineSecurityModeByMdStatus(String scheme, String mdStatus) {
        if (mdStatusToSecurityModeMap.containsKey(mdStatus)) {
            return mdStatusToSecurityModeMap.get(mdStatus);
        } else {
            return mdStatusToSecurityModeMap.get(DEFAULT_KEY);
        }
    }

    /**
     * Determine whether this payment type is recurring.
     * 
     * @param paymentType the payment type
     * 
     * @return true, if determine recurring
     */
    public boolean determineRecurring(String paymentType) {
        return recurringPaymentTypes.contains(paymentType);
    }

    /**
     * Translate the response (and set raw response using flattened object).
     * Does not set Id/CreatedDateTime (both handled by JPA) or
     * TransactionRecord. TODO: null safety
     * 
     * @param proxyResponse the proxy response
     * 
     * @return the provider authorisation response
     */
    public static ProviderAuthorisationResponse translateResponse(AuthResponseExt proxyResponse) {
        ProviderAuthorisationResponse response = new ProviderAuthorisationResponse();

        response.setRawResponse(LogicGroupSecurePaymentHelperImpl.flattenResponse(proxyResponse));
        response.setResultCode(proxyResponse.getResult().getCode());
        response.setProcessorTransactionId(proxyResponse.getAuthorisationDetails().getTransactionID());
        response.setErrorType(proxyResponse.getResult().getErrorType().value());
        response.setErrorMessage(proxyResponse.getResult().getErrorMessage());
        response.setCardToken(proxyResponse.getAuthorisationDetails().getCardToken());

        if (proxyResponse.getSolveResponse() != null) {
            response.setResponseCode(proxyResponse.getSolveResponse().getResponseCode());
            response.setProcessorReference(proxyResponse.getSolveResponse().getReference());

            if (proxyResponse.getSolveResponse().getCv2Avs() != null) {
                response.setAuthChecker(proxyResponse.getSolveResponse().getCv2Avs().getBy());
                response.setAvsAddressResponse(proxyResponse.getSolveResponse().getCv2Avs().getAddress());
                response.setAvsPostCodeResponse(proxyResponse.getSolveResponse().getCv2Avs().getPostCode());
                response.setCv2Response(proxyResponse.getSolveResponse().getCv2Avs().getCV2());
            }
            if (proxyResponse.getSolveResponse().getAcquirer() != null) {
                response.setAcquirerId(proxyResponse.getSolveResponse().getAcquirer().getAcquirerId());
                response.setAcquirerName(proxyResponse.getSolveResponse().getAcquirer().getAcquirerName());
            }
            if (proxyResponse.getSolveResponse().getResponseResult() != null) { // enum
                response.setResponseResult(proxyResponse.getSolveResponse().getResponseResult().value());
            }
        }

        // TODO: map SolveError?

        return response;
    }

    /**
     * Check we're initialised correctly!
     */
    @PostConstruct
    protected void afterPropertiesSet() {
        if (recurringPaymentTypes == null) {
            throw new IllegalArgumentException("Recurring payment type set required!");
        }

        if (schemeIdToProtocolNameMap == null) {
            throw new IllegalArgumentException("Scheme to Protocol Name mapping required!");
        }

        // check the mdStatus maps have a default value
        if (mdStatusToProtocolResultMap == null) {
            throw new IllegalArgumentException("MD Status to Protocol Result mappings required!");
        } else if (!mdStatusToProtocolResultMap.containsKey(DEFAULT_KEY)) {
            throw new IllegalArgumentException("Default MD Status to Protocol Result mapping required!");
        }

        if (mdStatusToSecurityModeMap == null) {
            throw new IllegalArgumentException("MD Status to Security Mode mappings required!");
        } else if (!mdStatusToSecurityModeMap.containsKey(DEFAULT_KEY)) {
            throw new IllegalArgumentException("Default MD Status to Security Mode mapping required!");
        }
    }
    
    // +DI
    /**
     * @param mdStatusToSecurityModeMap the mdStatusToSecurityModeMap to set
     */
    public void setMdStatusToSecurityModeMap(
            Map<String, TransactionSecurityEnum> mdStatusToSecurityModeMap) {
        this.mdStatusToSecurityModeMap = mdStatusToSecurityModeMap;
    }

    /**
     * @param schemeIdToProtocolNameMap the schemeIdToProtocolNameMap to set
     */
    public void setSchemeIdToProtocolNameMap(
            Map<String, ProtocolNameEnum> schemeIdToProtocolNameMap) {
        this.schemeIdToProtocolNameMap = schemeIdToProtocolNameMap;
    }

    /**
     * @param mdStatusToProtocolResultMap the mdStatusToProtocolResultMap to set
     */
    public void setMdStatusToProtocolResultMap(
            Map<String, ProtocolResultEnum> mdStatusToProtocolResultMap) {
        this.mdStatusToProtocolResultMap = mdStatusToProtocolResultMap;
    }

    /**
     * @param recurringPaymentTypes the recurringPaymentTypes to set
     */
    public void setRecurringPaymentTypes(Set<String> recurringPaymentTypes) {
        this.recurringPaymentTypes = recurringPaymentTypes;
    }
    //-DI
}
