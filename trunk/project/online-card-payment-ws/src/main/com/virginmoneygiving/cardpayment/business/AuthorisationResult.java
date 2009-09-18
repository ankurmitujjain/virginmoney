package com.virginmoneygiving.cardpayment.business;

import java.util.Map;

/**
 * result object.
 * @author robinb
 */
public class AuthorisationResult {

    /** outcome. */
    private AuthorisationOutcomeTypeEnum outcome;
    
    /** message. */
    private String message;
    
    /** code. */
    private int code;
    
    /** id. */
    private String processorTransactionId;
    
    /** card token. */
    private String cardToken;
    
    /** request data (3d). */
    private Map<String, String> authRequestData;
    
    /** url (3d). */
    private String authTargetUrl;
    
    /** extra. */
    private Map<String, String> extra;

    /**
     * Gets the outcome.
     * 
     * @return the outcome
     */
    public AuthorisationOutcomeTypeEnum getOutcome() {
        return outcome;
    }

    /**
     * Sets the outcome.
     * 
     * @param outcome the outcome to set
     */
    public void setOutcome(AuthorisationOutcomeTypeEnum outcome) {
        this.outcome = outcome;
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     * 
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the processor transaction id.
     * 
     * @return the processorTransactionId
     */
    public String getProcessorTransactionId() {
        return processorTransactionId;
    }

    /**
     * Sets the processor transaction id.
     * 
     * @param processorTransactionId the processorTransactionId to set
     */
    public void setProcessorTransactionId(String processorTransactionId) {
        this.processorTransactionId = processorTransactionId;
    }

    /**
     * Gets the card token.
     * 
     * @return the cardToken
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Sets the card token.
     * 
     * @param cardToken the cardToken to set
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    /**
     * Gets the auth request data.
     * 
     * @return the authRequestData
     */
    public Map<String, String> getAuthRequestData() {
        return authRequestData;
    }

    /**
     * Sets the auth request data.
     * 
     * @param authRequestData the authRequestData to set
     */
    public void setAuthRequestData(Map<String, String> authRequestData) {
        this.authRequestData = authRequestData;
    }

    /**
     * Gets the auth target url.
     * 
     * @return the authTargetUrl
     */
    public String getAuthTargetUrl() {
        return authTargetUrl;
    }

    /**
     * Sets the auth target url.
     * 
     * @param authTargetUrl the authTargetUrl to set
     */
    public void setAuthTargetUrl(String authTargetUrl) {
        this.authTargetUrl = authTargetUrl;
    }

    /**
     * Gets the extra.
     * 
     * @return the extra
     */
    public Map<String, String> getExtra() {
        return extra;
    }

    /**
     * Sets the extra.
     * 
     * @param extra the extra to set
     */
    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

}
