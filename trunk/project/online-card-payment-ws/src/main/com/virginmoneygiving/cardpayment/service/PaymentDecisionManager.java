package com.virginmoneygiving.cardpayment.service;

import com.virginmoneygiving.cardpayment.business.AuthenticateActionEnum;
import com.virginmoneygiving.cardpayment.business.AuthoriseActionEnum;

/**
 * The decision points for accepting payments.
 * 
 * @author Robin Bramley, Opsera Ltd
 */
public interface PaymentDecisionManager {

    /**
     * Determine whether 3D Secure is required (post card validation).
     * 
     * @param cardProvider (e.g. Visa, MasterCard, AMEX)
     * @param paymentType the payment type
     * 
     * @return true, if checks if is authentication required
     */
    public boolean isAuthenticationRequired(String cardProvider, String paymentType);

    /**
     * Determine whether 3D Secure authentication response code is acceptable.
     * 
     * @param 
     * 
     * @return AuthoriseActionEnum - AUTH_3D/AUTH_NON_3D/ERROR
     */
    public AuthenticateActionEnum assessAuthenticationResponseCode(String responseCode);

    /**
     * Determine whether 3D Secure authentication response data is acceptable.
     * 
     * @param mdStatus the md status
     * 
     * @return true, if assess authentication response
     */
    public boolean assessAuthenticationResponse(String mdStatus);

    /**
     * Determine whether we'll complete a payment or cancel it.
     * 
     * @param statusCode the status code
     * 
     * @return true - accepted
     */
    public boolean assessAuthoriseResponse(String statusCode);

    /**
     * Determine whether we'll complete the payment or cancel it based on status
     * and AVS/CV2 checking.
     * 
     * @param statusCode the status code
     * @param authChecker the auth checker
     * @param cv2Response the cv2 response
     * @param avsAddressResponse the avs address response
     * @param avsPostCodeResponse the avs post code response
     * 
     * @return AuthoriseActionEnum - COMPLETE/CANCEL/NONE
     */
    public AuthoriseActionEnum assessAuthoriseResponse(String statusCode, String authChecker, String cv2Response, String avsAddressResponse, String avsPostCodeResponse);

    /**
     * Determine whether card validation was ok.
     * 
     * @param statusCode the status code
     * @param validationPassed the validation passed
     * 
     * @return true, if assess validate response
     */
    public boolean assessValidateResponse(String statusCode, String validationPassed);

    /**
     * Determine if the cancel method response was acceptable.
     * 
     * @param statusCode the status code
     * 
     * @return true, if assess cancel response
     */
    public boolean assessCancelResponse(String statusCode);
}
