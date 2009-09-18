package com.virginmoneygiving.cardpayment.service;

import java.util.Map;
import com.virginmoneygiving.cardpayment.business.AuthorisationResult;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.service.exception.OnlineCardPaymentServiceException;

/**
 * This interface has all the methods that are exposed on the web service.
 */
public interface OnlineCardPaymentService {

    /**
     * Handles a request for an authorisation with card details. This may result
     * in a response that we require 3D Secure authentication.
     * 
     * @param GUID the gUID
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * 
     * @return the authorisation result
     * 
     * @throws Exception      * @throws OnlineCardPaymentServiceException the online card payment service exception
     */
    AuthorisationResult authoriseWithCard(String GUID, PaymentDetails paymentDetails, CardDetails cardDetails) throws OnlineCardPaymentServiceException;

    /**
     * Performs an authorisation with a card token.
     * 
     * @param GUID the gUID
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * 
     * @return the authorisation result
     * 
     * @throws Exception      * @throws OnlineCardPaymentServiceException the online card payment service exception
     */
    AuthorisationResult authoriseWithToken(String GUID, PaymentDetails paymentDetails, CardDetails cardDetails) throws OnlineCardPaymentServiceException;

    /**
     * Performs an authorisation with authentication (3D Secure) data.
     * 
     * @param GUID the gUID
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * @param authResponseData the auth response data
     * 
     * @return the authorisation result
     * 
     * @throws Exception      * @throws OnlineCardPaymentServiceException the online card payment service exception
     */
    AuthorisationResult authoriseWithAuthentication(String GUID, PaymentDetails paymentDetails, CardDetails cardDetails, Map<String, String> authResponseData)
            throws OnlineCardPaymentServiceException;

}
