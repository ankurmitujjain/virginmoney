package com.virginmoneygiving.cardpayment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Provider authorisation response data.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
@Entity
public class ProviderAuthorisationResponse extends BaseProviderResponse {

    /** Transaction identifier allocated by the Payment processor. Required to complete/cancel extended payments. */
    private String processorTransactionId;

    /** Reference allocated by the Payment processor. */
    private String processorReference;

    /** The 'authority' that checked the CV2/AVS data: <ul> <li>merchant_host <li>acquirer_host <li>card_scheme <li>issuer </ul>. */
    private String authChecker;

    /** CV2 check response: <ul> <li>no_information <li>not_checked <li>matched <li>not_matched </ul>. */
    private String cv2Response;

    /** AVS address response: <ul> <li>no_information <li>not_checked <li>matched <li>not_matched </ul>. */
    private String avsAddressResponse;

    /** AVS postcode check response: <ul> <li>no_information <li>not_checked <li>matched <li>not_matched </ul>. */
    private String avsPostCodeResponse;

    /**
     * Gets the processor transaction id.
     * 
     * @return the processorTransactionId
     */
    @Column(name = "PROVIDER_TRANSACTION_ID", updatable = false)
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
     * Gets the processor reference.
     * 
     * @return the processorReference
     */
    @Column(name = "PROVIDER_REFERENCE", updatable = false, length = 50)
    public String getProcessorReference() {
        return processorReference;
    }

    /**
     * Sets the processor reference.
     * 
     * @param processorReference the processorReference to set
     */
    public void setProcessorReference(String processorReference) {
        this.processorReference = processorReference;
    }

    /**
     * Gets the auth checker.
     * 
     * @return the authChecker
     */
    @Column(name = "AUTH_CHECKER", updatable = false, length = 20)
    public String getAuthChecker() {
        return authChecker;
    }

    /**
     * Sets the auth checker.
     * 
     * @param authChecker the authChecker to set
     */
    public void setAuthChecker(String authChecker) {
        this.authChecker = authChecker;
    }

    /**
     * Gets the cv2 response.
     * 
     * @return the cv2Response
     */
    @Column(name = "CV2_RESPONSE", updatable = false, length = 20)
    public String getCv2Response() {
        return cv2Response;
    }

    /**
     * Sets the cv2 response.
     * 
     * @param cv2Response the cv2Response to set
     */
    public void setCv2Response(String cv2Response) {
        this.cv2Response = cv2Response;
    }

    /**
     * Gets the avs address response.
     * 
     * @return the avsAddressResponse
     */
    @Column(name = "AVS_ADDRESS_RESPONSE", updatable = false, length = 20)
    public String getAvsAddressResponse() {
        return avsAddressResponse;
    }

    /**
     * Sets the avs address response.
     * 
     * @param avsAddressResponse the avsAddressResponse to set
     */
    public void setAvsAddressResponse(String avsAddressResponse) {
        this.avsAddressResponse = avsAddressResponse;
    }

    /**
     * Gets the avs post code response.
     * 
     * @return the avsPostCodeResponse
     */
    @Column(name = "AVS_POSTCODE_RESPONSE", updatable = false, length = 20)
    public String getAvsPostCodeResponse() {
        return avsPostCodeResponse;
    }

    /**
     * Sets the avs post code response.
     * 
     * @param avsPostCodeResponse the avsPostCodeResponse to set
     */
    public void setAvsPostCodeResponse(String avsPostCodeResponse) {
        this.avsPostCodeResponse = avsPostCodeResponse;
    }
}
