package com.virginmoneygiving.cardpayment.serviceproxy;

import java.util.Map;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderValidationResponse;

/**
 * This represents the methods required for 3rd Party Payment Providers.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface PaymentProviderServiceProxy {

    /**
     * Validate a card.
     * 
     * @param guid the guid
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * 
     * @return the provider validation response
     */
    public ProviderValidationResponse validate(String guid, PaymentDetails paymentDetails, CardDetails cardDetails);

    /**
     * Authorise a card with only CV2/AVS checking. (This will become obsolete
     * when next version of TLG SPIS is released as you will be able to use the
     * token)
     * 
     * @param guid the guid
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * 
     * @return the provider authorisation response
     */
    public ProviderAuthorisationResponse authoriseWithCv2Avs(String guid, PaymentDetails paymentDetails, CardDetails cardDetails);

    /**
     * Authorise a card with authentication.
     * 
     * @param guid the guid
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * @param authenticationData the authentication data
     * 
     * @return the provider authorisation response
     */
    public ProviderAuthorisationResponse authoriseWithAuthentication(String guid, PaymentDetails paymentDetails, CardDetails cardDetails,
            Map<String, String> authenticationData);

    /**
     * Authorise an Existing Card by token.
     * 
     * @param guid the guid
     * @param paymentDetails the payment details
     * @param cardDetails the card details
     * 
     * @return the provider authorisation response
     */
    public ProviderAuthorisationResponse authoriseByToken(String guid, PaymentDetails paymentDetails, CardDetails cardDetails);

    /**
     * Complete a transaction.
     * 
     * @param guid the guid
     * @param transactionId the transaction id
     * 
     * @return the provider authorisation response
     */
    public ProviderAuthorisationResponse completeTransaction(String guid, String transactionId);

    /**
     * Cancel a transaction.
     * 
     * @param guid the guid
     * @param transactionId the transaction id
     * 
     * @return the provider authorisation response
     */
    public ProviderAuthorisationResponse cancelTransaction(String guid, String transactionId);
}
