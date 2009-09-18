package com.virginmoneygiving.cardpayment.service.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.virginmoneygiving.cardpayment.business.AuthenticateActionEnum;
import com.virginmoneygiving.cardpayment.business.AuthorisationOutcomeTypeEnum;
import com.virginmoneygiving.cardpayment.business.AuthorisationResult;
import com.virginmoneygiving.cardpayment.business.AuthoriseActionEnum;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderValidationResponse;
import com.virginmoneygiving.cardpayment.event.AuthenticationResponseReceivedEvent;
import com.virginmoneygiving.cardpayment.event.AuthorisationErrorEvent;
import com.virginmoneygiving.cardpayment.event.AuthorisationFailedEvent;
import com.virginmoneygiving.cardpayment.event.ValidationErrorEvent;
import com.virginmoneygiving.cardpayment.event.ValidationFailedEvent;
import com.virginmoneygiving.cardpayment.event.ValidationPassedEvent;
import com.virginmoneygiving.cardpayment.helper.DelimitedStringHandler;
import com.virginmoneygiving.cardpayment.helper.LogicGroupConstants;
import com.virginmoneygiving.cardpayment.helper.ThreeDSecureHelper;
import com.virginmoneygiving.cardpayment.service.EndpointProbeService;
import com.virginmoneygiving.cardpayment.service.OnlineCardPaymentService;
import com.virginmoneygiving.cardpayment.service.PaymentDecisionManager;
import com.virginmoneygiving.cardpayment.service.exception.OnlineCardPaymentServiceException;
import com.virginmoneygiving.cardpayment.service.exception.ThreeDSecureException;
import com.virginmoneygiving.cardpayment.serviceproxy.PaymentProviderServiceProxy;

/**
 * This class implements the OnlineCardPaymentService.
 * TODO: catch ProxySystemException
 * 
 * @author John Allen, Opsera Ltd.
 * @author Robin Bramley, Opsera Ltd.
 */
@Transactional
public class OnlineCardPaymentServiceImpl implements OnlineCardPaymentService,
        ApplicationEventPublisherAware {

    /** Payment decision manager used for Risk analysis. */
    @Resource
    private PaymentDecisionManager paymentDecisionManager;

    /** Service Proxy to the payment provider (e.g. TLG). */
    @Resource
    private PaymentProviderServiceProxy paymentProviderServiceProxy;

    /** Helper for 3D Secure processing. */
    @Resource
    private ThreeDSecureHelper threeDSecureHelper;

    /** Service to check endpoint availability. */
    private EndpointProbeService endpointProbeService;

    /** Application event publisher. */
    private ApplicationEventPublisher applicationEventPublisher;

    /** Primary 3D Secure MPI plugin URL. */
    private String primary3DUrl;

    /** Secondary 3D Secure MPI plugin URL. */
    private String secondary3DUrl;

    /** Logger Instance. */
    private static Logger LOGGER = Logger
            .getLogger(OnlineCardPaymentServiceImpl.class);

    /**
     * Validate a card - then determine whether it needs 3D Secure. <br/> If so,
     * build the form variables; <br/> If not, then process the payment as per
     * authorise with Token. <br/> <br/>
     * 
     * @param guid System transaction ID
     * @param paymentDetails payment details
     * @param cardDetails card details
     * 
     * @return result
     * 
     * @throws Exception from collaborator
     * @throws OnlineCardPaymentServiceException the online card payment service exception
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AuthorisationResult authoriseWithCard(String guid,
            PaymentDetails paymentDetails, CardDetails cardDetails)
            throws OnlineCardPaymentServiceException {
        AuthorisationResult authorisationResult = new AuthorisationResult();

        // validate the card
        ProviderValidationResponse response = paymentProviderServiceProxy
                .validate(guid, paymentDetails, cardDetails);

        // check validate response
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Assessing validation response - code: "
                    + response.getResultCode() + ", validation passed: "
                    + response.getValidationPassed());
        }
        if (!paymentDecisionManager.assessValidateResponse(response
                .getResultCode(), response.getValidationPassed())) {
            // invalid
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Error response from validation");
            }
            authorisationResult.setCode(0); // TODO: String to int (atoi)
                                            // conversion

            // TODO: add in errorMessage / error Type
            String message = "Card validation failed " + response.getErrorMessage();

            authorisationResult.setMessage(message);
            authorisationResult.setOutcome(AuthorisationOutcomeTypeEnum.ERROR);

            // publish business process event
            if (response.getResultCode().trim().equals("0")) {
                applicationEventPublisher
                        .publishEvent(new ValidationFailedEvent(
                                applicationEventPublisher, guid, message));

            } else {
                applicationEventPublisher
                        .publishEvent(new ValidationErrorEvent(
                                applicationEventPublisher, guid, message));
            }
        } else {
            // valid card details
            // publish business process event
            applicationEventPublisher
                    .publishEvent(new ValidationPassedEvent(
                            applicationEventPublisher, guid,
                            "Card validation passed."));

            String cardProvider = response.getAcquirerId();

            if (paymentDecisionManager.isAuthenticationRequired(cardProvider,
                    paymentDetails.getPaymentType().value())) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Authentication required");
                }
                authorisationResult.setCardToken(response.getCardToken());
                authorisationResult.setCode(0); // TODO: set this value
                authorisationResult.setMessage(null); // TODO: set this
                                                        // message
                authorisationResult
                        .setOutcome(AuthorisationOutcomeTypeEnum.AUTHENTICATION_REQUIRED);

                Map<String, String> nvp;
                try {
                    nvp = threeDSecureHelper.buildFormVariables(guid,
                            cardDetails, paymentDetails);
                } catch (ThreeDSecureException e) {
                    LOGGER.error("authoriseWithCard() + String primary3DUrl="
                            + primary3DUrl + ", String secondary3DUrl="
                            + secondary3DUrl + ", String guid=" + guid
                            + ", String cardProvider=" + cardProvider, e);
                    throw new OnlineCardPaymentServiceException(e);
                }

                // use probe to determine target server
                String targetUrl = endpointProbeService.chooseUrl(primary3DUrl,
                        secondary3DUrl);
                authorisationResult.setAuthTargetUrl(targetUrl);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Auth map contains " + nvp.size()
                            + " entries.");
                }

                authorisationResult.setAuthRequestData(nvp);
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER
                            .info("Calling authorise with cv2/avs as 3D Secure not required");
                }

                try {
                    ProviderAuthorisationResponse response2 = paymentProviderServiceProxy
                            .authoriseWithCv2Avs(guid, paymentDetails,
                                    cardDetails);

                    // complete/cancel as appropriate
                    performAuthoriseRiskAnalysis(guid, response2,
                            authorisationResult);
                } catch (RuntimeException re) {
                    LOGGER.error(re.getMessage());
                    // publish business process event
                    applicationEventPublisher
                            .publishEvent(new AuthorisationErrorEvent(
                                    applicationEventPublisher, guid, re
                                            .getMessage()));

                    throw re;
                }
            }
        }

        return authorisationResult;
    }

    /**
     * 2nd phase for a 3D Secure payment.
     * 
     * @param guid system transaction ID
     * @param paymentDetails payment details
     * @param cardDetails card details
     * @param authResponseData 3D Secure response data
     * 
     * @return the authorisation result
     * 
     * @throws Exception      * @throws OnlineCardPaymentServiceException the online card payment service exception
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AuthorisationResult authoriseWithAuthentication(String guid,
            PaymentDetails paymentDetails, CardDetails cardDetails,
            Map<String, String> authResponseData)
            throws OnlineCardPaymentServiceException {

        AuthorisationResult authorisationResult = new AuthorisationResult();

        // TODO: finish migrating these checks to Spring Validation?
        if (authResponseData == null) {
            String message = "No authentication data supplied for GUID: "
                    + guid;
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(message);
            }
            throw new IllegalArgumentException(message);
        }

        // publish business process event
        applicationEventPublisher
                .publishEvent(new AuthenticationResponseReceivedEvent(
                        applicationEventPublisher, guid,
                        "Received 3D Secure response.", authResponseData));

        if (authResponseData.get(LogicGroupConstants.RES_MD_STATUS_KEY) == null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Authentication data for GUID: " + guid
                        + " is missing "
                        + LogicGroupConstants.RES_MD_STATUS_KEY);
            }
            throw new IllegalArgumentException(
                    "No MD Status in Authentication data for GUID: " + guid);
        }

        // VMG-95 - check 3D Secure response for Opal Errors
        // already checked for presence by the custom Spring validation function
        if (authResponseData.get(LogicGroupConstants.RES_OPAL_ERROR_CODE_KEY) == null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Authentication data for GUID: " + guid
                        + " is missing "
                        + LogicGroupConstants.RES_MD_STATUS_KEY);
            }
            throw new IllegalArgumentException(
                    "No OPAL Error Code in Authentication data for GUID: "
                            + guid);
        }

        String opalErrorCode = authResponseData
                .get(LogicGroupConstants.RES_OPAL_ERROR_CODE_KEY);
        String mdErrorMessage = authResponseData
                .get(LogicGroupConstants.RES_MD_ERROR_MSG_KEY);

        // determine how to handle the OPAL error code
        AuthenticateActionEnum action = paymentDecisionManager.assessAuthenticationResponseCode(opalErrorCode); 

        // check for an error response
        if (action.equals(AuthenticateActionEnum.ERROR)) {
            // TODO: are any codes alertable?
            String message = "Error response from 3D Secure Payer Authentication:\nGUID: "
                    + guid
                    + " [error code: "
                    + opalErrorCode
                    + ", error message: " + mdErrorMessage + "]";
            LOGGER.error(message);
            throw new RuntimeException(message);
        }
        //TODO: AUTH_3D/AUTH_NON_3D handling if necessary

        // validate digest
        try {
            if (!threeDSecureHelper
                    .verifyAuthenticationResponseDigest(authResponseData)) {
                String message = "Digest verification failed for GUID: " + guid;
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(message);
                }
                throw new IllegalArgumentException(message);
            }
        } catch (ThreeDSecureException e) {
            throw new OnlineCardPaymentServiceException(
                    "Caught error verfiying digest", e);
        }

        // check the authResponse code
        if (!paymentDecisionManager
                .assessAuthenticationResponse(authResponseData
                        .get(LogicGroupConstants.RES_MD_STATUS_KEY))) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Rejecting invalid authentication response data for GUID: "
                                + guid);
            }
            throw new IllegalArgumentException(
                    "Invalid authentication data for GUID: " + guid);
        }

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Performing authorisation for GUID: " + guid);
            }
            ProviderAuthorisationResponse response = paymentProviderServiceProxy
                    .authoriseWithAuthentication(guid, paymentDetails,
                            cardDetails, authResponseData);

            // this now duplicates logic in the proxy
            if (Integer.parseInt(response.getResultCode()) != 0) {
                LOGGER.warn("Error response from TLG - GUID " + guid);
                throw new RuntimeException("Error from TLG for GUID " + guid);
            }

            // complete/cancel as appropriate
            performAuthoriseRiskAnalysis(guid, response, authorisationResult);
        } catch (RuntimeException e) {
            LOGGER.error("Error processing 3D Secure authorisation for GUID: "
                    + guid, e);

            // publish business process event
            applicationEventPublisher.publishEvent(new AuthorisationErrorEvent(
                    applicationEventPublisher, guid, e.getMessage()));

            throw e;
        }

        return authorisationResult;
    }

    /**
     * Authorise with a token (previously stored by a payment).
     * 
     * @param guid the system transaction ID
     * @param paymentDetails details of the payment
     * @param cardDetails card details
     * 
     * @return the authorisation result
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AuthorisationResult authoriseWithToken(String guid,
            PaymentDetails paymentDetails, CardDetails cardDetails) {
        AuthorisationResult authorisationResult = new AuthorisationResult();

        try {
            ProviderAuthorisationResponse response = paymentProviderServiceProxy
                    .authoriseByToken(guid, paymentDetails, cardDetails);

            authorisationResult.setCardToken(cardDetails.getToken());
            authorisationResult.setCode(0); // TODO: determine which code to
                                            // return
            authorisationResult.setMessage(null); // TODO: set this properly

            // check business response
            if (paymentDecisionManager.assessAuthoriseResponse(response
                    .getResponseCode())) {
                authorisationResult
                        .setOutcome(AuthorisationOutcomeTypeEnum.SUCCESS);
            } else {
                authorisationResult
                        .setOutcome(AuthorisationOutcomeTypeEnum.ERROR);
            }

            // VMG CR 105 use human readable reference
            authorisationResult.setProcessorTransactionId(response
                    .getProcessorReference());

            Map<String, String> nvp = DelimitedStringHandler.processNVP(
                    response.getRawResponse(),
                    LogicGroupConstants.REC_SEPARATOR,
                    LogicGroupConstants.FIELD_SEPARATOR);

            authorisationResult.setExtra(nvp);

        } catch (RuntimeException e) {
            LOGGER.error("Error processing token-based transaction for GUID: "
                    + guid, e);

            // publish business process event
            applicationEventPublisher.publishEvent(new AuthorisationErrorEvent(
                    applicationEventPublisher, guid, e.getMessage()));
            throw e;
        }

        return authorisationResult;
    }

    /**
     * Perform Authorise result risk analysis and complete/cancel as required.
     * We want to include the initial message to show why the action was taken
     * e.g. GetSignatureAuthorisation (Solve Response Result)
     * 
     * @param guid the system transaction ID
     * @param response provider authorisation response
     * @param authorisationResult the auth result
     */
    private void performAuthoriseRiskAnalysis(String guid,
            ProviderAuthorisationResponse response,
            AuthorisationResult authorisationResult) {

        ProviderAuthorisationResponse response2 = null;

        // check result code with decision manager
        // complete or cancel
        AuthoriseActionEnum pdmResult = paymentDecisionManager
                .assessAuthoriseResponse(response.getResponseCode(), response
                        .getAuthChecker(), response.getCv2Response(), response
                        .getAvsAddressResponse(), response
                        .getAvsPostCodeResponse());

        if (pdmResult == null) {
            // this should never happen as Payment Decision Manager defaults
            String message = "No config for " + response.getResponseCode()
                    + " (" + response.getResponseResult() + ")";

            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        } else if (pdmResult.equals(AuthoriseActionEnum.COMPLETE)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Completing transaction for GUID: " + guid);
            }
            response2 = paymentProviderServiceProxy.completeTransaction(guid,
                    response.getProcessorTransactionId());

            // handle response2 result code - system level
            if (Integer.parseInt(response2.getResultCode()) != 0) {
                String message = "Error response from TLG whilst completing payment for GUID: "
                        + guid;

                // log message
                LOGGER.error(message);

                // publish business process event
                applicationEventPublisher
                        .publishEvent(new AuthorisationErrorEvent(
                                applicationEventPublisher, guid, message));

                // system level error response - so throw a runtime exception
                throw new RuntimeException(message);
            }

            // TODO: check response2 responseCode - business level
            authorisationResult
                    .setOutcome(AuthorisationOutcomeTypeEnum.SUCCESS);
            // authorisationResult.setOutcome(
            // AuthorisationOutcomeTypeEnum.ERROR );
            authorisationResult.setMessage("Completed payment ("
                    + response.getResponseResult() + ")");
        } else if (pdmResult.equals(AuthoriseActionEnum.CANCEL)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Cancelling transaction for GUID: " + guid);
            }
            response2 = paymentProviderServiceProxy.cancelTransaction(guid,
                    response.getProcessorTransactionId());

            // check the system level response
            if (Integer.parseInt(response2.getResultCode()) != 0) {
                String message = "Error response from TLG whilst cancelling payment for GUID: "
                        + guid;

                // log message
                LOGGER.error(message);

                // publish business process event
                applicationEventPublisher
                        .publishEvent(new AuthorisationErrorEvent(
                                applicationEventPublisher, guid, message));

                // system level error response - so throw a runtime exception
                throw new RuntimeException(message);
            }

            // we always have an error outcome - but message may change
            authorisationResult.setOutcome(AuthorisationOutcomeTypeEnum.ERROR);

            if (paymentDecisionManager.assessCancelResponse(response2
                    .getResponseCode())) {
                String message = "Payment was cancelled ("
                        + response.getResponseResult() + ")";
                authorisationResult.setMessage(message);

                // publish business process event
                applicationEventPublisher
                        .publishEvent(new AuthorisationFailedEvent(
                                applicationEventPublisher, guid, message));
            } else {
                String message = "Unable to cancel payment for GUID: " + guid;
                authorisationResult.setMessage("Error processing payment");

                // log message
                LOGGER.error(message);

                // publish business process event
                applicationEventPublisher
                        .publishEvent(new AuthorisationErrorEvent(
                                applicationEventPublisher, guid, message));
            }
        } else {
            // No further action required
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No further action required");
            }
            // we always have an error outcome - but message may change
            authorisationResult.setOutcome(AuthorisationOutcomeTypeEnum.ERROR);
            authorisationResult.setMessage("Payment not completed/cancelled ("
                    + response.getResponseResult() + ")");
        }

        // map the card token
        if (response.getCardToken() != null) {
            authorisationResult.setCardToken(response.getCardToken());
        } else if (response2 != null && response2.getCardToken() != null) {
            authorisationResult.setCardToken(response2.getCardToken());
        } else {
            LOGGER.warn("No card token to return for GUID " + guid);
        }

        authorisationResult.setCode(0); // TODO: which code to use? TLG or
        // processor independent?

        // VMG CR 105 use human readable reference
        authorisationResult.setProcessorTransactionId(response
                .getProcessorReference());

        // convert the latest raw response to Extra data
        if (response2 != null && response2.getRawResponse() != null) {
            Map<String, String> nvp = DelimitedStringHandler.processNVP(
                    response2.getRawResponse(),
                    LogicGroupConstants.REC_SEPARATOR,
                    LogicGroupConstants.FIELD_SEPARATOR);
            authorisationResult.setExtra(nvp);
        }

    }

    // +DI
    /**
     * Sets the payment decision manager.
     * 
     * @param paymentDecisionManager the paymentDecisionManager to set
     */
    public void setPaymentDecisionManager(
            PaymentDecisionManager paymentDecisionManager) {
        this.paymentDecisionManager = paymentDecisionManager;
    }

    /**
     * Sets the payment provider service proxy.
     * 
     * @param paymentProviderServiceProxy the paymentProviderServiceProxy to set
     */
    public void setPaymentProviderServiceProxy(
            PaymentProviderServiceProxy paymentProviderServiceProxy) {
        this.paymentProviderServiceProxy = paymentProviderServiceProxy;
    }

    /**
     * Sets the three d secure helper.
     * 
     * @param threeDSecureHelper the threeDSecureHelper to set
     */
    public void setThreeDSecureHelper(ThreeDSecureHelper threeDSecureHelper) {
        this.threeDSecureHelper = threeDSecureHelper;
    }

    /**
     * Sets the endpoint probe service.
     * 
     * @param endpointProbeService the endpointProbeService to set
     */
    public void setEndpointProbeService(
            EndpointProbeService endpointProbeService) {
        this.endpointProbeService = endpointProbeService;
    }

    /**
     * Sets the application event publisher.
     * 
     * @param applicationEventPublisher the applicationEventPublisher to set
     */
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Sets the primary3 d url.
     * 
     * @param primary3DUrl the primary3DUrl to set
     */
    public void setPrimary3DUrl(String primary3DUrl) {
        this.primary3DUrl = primary3DUrl;
    }

    /**
     * Sets the secondary3 d url.
     * 
     * @param secondary3DUrl the secondary3DUrl to set
     */
    public void setSecondary3DUrl(String secondary3DUrl) {
        this.secondary3DUrl = secondary3DUrl;
    }
}
