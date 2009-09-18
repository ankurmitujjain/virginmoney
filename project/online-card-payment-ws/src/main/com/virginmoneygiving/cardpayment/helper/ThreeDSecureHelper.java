package com.virginmoneygiving.cardpayment.helper;

import java.util.Map;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.service.exception.ThreeDSecureException;

/**
 * Assist with 3D Secure form POST Request and Response.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface ThreeDSecureHelper {

    /**
     * Builds the necessary name-value pairs required for the 3D Secure form
     * POST.
     * 
     * @param GUID the gUID
     * @param cardDetails the card details
     * @param paymentDetails the payment details
     * 
     * @return the map< string, string>
     * 
     * @throws ThreeDSecureException the three d secure exception
     */
    public Map<String, String> buildFormVariables(String GUID, CardDetails cardDetails, PaymentDetails paymentDetails) throws ThreeDSecureException;

    /**
     * Verify an authentication response digest is valid. This will not verify
     * session-level data (e.g. xid for Logic Group) or whether the status code
     * is acceptable.
     * 
     * @param formVariables the form variables
     * 
     * @return true, if verify authentication response digest
     * 
     * @throws ThreeDSecureException the three d secure exception
     */
    public boolean verifyAuthenticationResponseDigest(Map<String, String> formVariables) throws ThreeDSecureException;
}
