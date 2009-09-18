package com.virginmoneygiving.cardpayment.ws;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.virginmoneygiving.cardpayment.business.AuthorisationResult;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.helper.OnlineCardPaymentDataMappingHelper;
import com.virginmoneygiving.cardpayment.service.OnlineCardPaymentService;
import com.virginmoneygiving.cardpayment.service.exception.OnlineCardPaymentServiceException;
import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithAuthenticationRequest;
import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithAuthenticationResponse;
import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardRequest;
import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardResponse;
import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithTokenRequest;
import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithTokenResponse;
import com.virginmoneygiving.cardpayment.service.messages.OnlineCardPaymentServiceFaultException;
import com.virginmoneygiving.cardpayment.service.messages.OnlineCardPaymentWs;
import com.virginmoneygiving.cardpayment.service.messages.ServiceAuthorisationOutcomeTypeEnum;
import com.virginmoneygiving.cardpayment.service.messages.ServiceAuthorisationResult;

/**
 * The Web Service endpoint. Handles service operations exposed on the WSDL.
 * <br>
 * The methods will validate the (unmarshalled) request data before it is
 * mapped to internal service objects.
 * 
 * @author John Allen, Opsera Ltd.
 * @author Robin Bramley, Opsera Ltd.
 */

@WebService(endpointInterface = "com.virginmoneygiving.cardpayment.service.messages.OnlineCardPaymentWs", targetNamespace = "http://www.virginmoneygiving.com/online-card-payment-ws/", serviceName = "onlineCardPaymentService", portName = "OnlineCardPaymentWs")
public class OnlineCardPaymentEndPoint implements OnlineCardPaymentWs {

    /** Logger to log logging. */
    private static final Logger LOGGER = Logger.getLogger(OnlineCardPaymentEndPoint.class);

    /** create instance variable for card payment service. */
    private OnlineCardPaymentService onlineCardPaymentService = null;

    /** created instance of Dozer. */
    private DozerBeanMapper dozer = null;

    /** Payment data mapping helper reference. */
    private OnlineCardPaymentDataMappingHelper cardPaymentDataMappingHelper = null;

    /** Spring validator for card requests. */
    private Validator cardRequestValidator;

    /** Spring validator for card token requests. */
    private Validator tokenRequestValidator;

    /** Spring validator for 3D Secure part 2 requests. */
    private Validator authenticationRequestValidator;

    /** Constant for logging the GUID in the Mapped Diagnostic Context (MDC). */
    private static final String GUID = "GUID";

    /**
     * Default Constructor.
     */
    public OnlineCardPaymentEndPoint() {
    }

    /**
     * Handle a card payment request. May result in a request for 3D Secure
     * authentication.
     * 
     * @param req the req
     * 
     * @return the authorise with card response
     * 
     * @throws OnlineCardPaymentServiceFaultException the online card payment service fault exception
     */
    public AuthoriseWithCardResponse authoriseWithCard(AuthoriseWithCardRequest req) throws OnlineCardPaymentServiceFaultException {
	if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("authoriseWithCard() -  START");
        }

        // set up response objects
        AuthoriseWithCardResponse response = new AuthoriseWithCardResponse();
        ServiceAuthorisationResult serviceAuthorisationResult = new ServiceAuthorisationResult();
        AuthorisationResult authResult = null;

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");
        ValidationUtils.invokeValidator(cardRequestValidator, req, errors);
        if (errors.hasErrors()) {
            String message = "Validation errors found with card request";

            LOGGER.error(message);
            logErrors(errors); // low-level debug of info

            // add them to response
            addErrorsToList(errors, serviceAuthorisationResult.getValidationMessages());
            serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
            serviceAuthorisationResult.setMessage(message);
        } else {
            // get process GUID
            String guid = req.getGuid();
            // make the system transaction GUID available to the logger
            MDC.put(GUID, guid);

            // common mappings
            CardDetails cardDetails = cardPaymentDataMappingHelper.mapCardDetails(req.getCardDetails());
            PaymentDetails paymentDetails = cardPaymentDataMappingHelper.mapPaymentDetails(req.getPaymentDetails());

            // Service invocation
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Authorising with card");
            }
            try {
                authResult = onlineCardPaymentService.authoriseWithCard(guid, paymentDetails, cardDetails);
            } catch (OnlineCardPaymentServiceException ocpse) {
                LOGGER.error("There was a problem authorising your card payment...", ocpse);
                serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
                serviceAuthorisationResult.setMessage(ocpse.getMessage());
            } catch (Exception e) {
                // We need to catch everything so the error is sent back as per the contract.
                LOGGER.error("There was a problem authorising your card payment...", e);
                serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
                serviceAuthorisationResult.setMessage(e.getMessage());
            } finally {
                // remove guid from this thread 
                MDC.remove(GUID);
            }

            // dozer map response (if we have one)
            if(authResult != null) {
                dozer.map(authResult, serviceAuthorisationResult);

                // WARN: augment authRequestData due to dozer 'generic' issue
                if (authResult.getAuthRequestData() != null) {
                    // add 3D Secure request data
                    serviceAuthorisationResult.getAuthRequestData().addAll(OnlineCardPaymentDataMappingHelper.mapToNVPList(authResult.getAuthRequestData()));
                }

                // WARN: augment 'extra' data due to dozer 'generic' issue
                if (authResult.getExtra() != null) {
                    // add TLG response data
                    serviceAuthorisationResult.getExtra().addAll(OnlineCardPaymentDataMappingHelper.mapToNVPList(authResult.getExtra()));
                }
            }
        }

        response.setResult(serviceAuthorisationResult);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("authoriseWithCard(AuthoriseWithCardRequest) - END");
        }
        return response;
    }

    /**
     * Handle part 2 of a 3D Secure payment.
     * 
     * @param req the req
     * 
     * @return the authorise with authentication response
     * 
     * @throws OnlineCardPaymentServiceFaultException the online card payment service fault exception
     */
    public AuthoriseWithAuthenticationResponse authoriseWithAuthentication(AuthoriseWithAuthenticationRequest req)
            throws OnlineCardPaymentServiceFaultException {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("authoriseWithAuthentication() -  START");
		}

        // set up response objects
        AuthoriseWithAuthenticationResponse response = new AuthoriseWithAuthenticationResponse();
        ServiceAuthorisationResult serviceAuthorisationResult = new ServiceAuthorisationResult();
        AuthorisationResult authResult = null;

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithAuthenticationRequest");
        ValidationUtils.invokeValidator(authenticationRequestValidator, req, errors);
        if (errors.hasErrors()) {
            String message = "Validation errors found with authentication request";

            LOGGER.error(message);
            logErrors(errors); // low-level debug of info

            // add them to response
            addErrorsToList(errors, serviceAuthorisationResult.getValidationMessages());

            // add error info to response
            serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
            serviceAuthorisationResult.setMessage(message);
        } else {
            String guid = req.getGuid();
            // make the system transaction GUID available to the logger
            MDC.put(GUID, guid);

            // common mappings
            CardDetails cardDetails = cardPaymentDataMappingHelper.mapCardDetails(req.getCardDetails());
            PaymentDetails paymentDetails = cardPaymentDataMappingHelper.mapPaymentDetails(req.getPaymentDetails());

            // map NVPs bypassing dozer
            Map<String, String> authResponseData = OnlineCardPaymentDataMappingHelper.mapNVPList(req.getAuthResponseData());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Auth Response data size : " + authResponseData.size());
            }

            // Service invocation
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Authorising with authentication");
            }

            try {
                authResult = onlineCardPaymentService.authoriseWithAuthentication(guid, paymentDetails, cardDetails, authResponseData);
            } catch (OnlineCardPaymentServiceException ocpse) {
                LOGGER.error("There was a problem authorising your card payment...", ocpse);
                // add error info to response
                serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
                serviceAuthorisationResult.setMessage(ocpse.getMessage());
            } catch (Exception e) {
                // We need to catch everything so the error is sent back as per the contract.
                LOGGER.error("There was a problem authorising your card payment...", e);
                // add error info to response
                serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
                serviceAuthorisationResult.setMessage(e.getMessage());
            } finally {
                // remove guid from this thread 
                MDC.remove(GUID);
            }

            // dozer map response (if we have one)
            if(authResult != null) {
                // dozer map response
                dozer.map(authResult, serviceAuthorisationResult);

                // WARN: augment due to dozer 'generic' issue
                if (authResult.getExtra() != null) {
                    serviceAuthorisationResult.getExtra().addAll(OnlineCardPaymentDataMappingHelper.mapToNVPList(authResult.getExtra()));
                }
            }
        }

        // add the result to the response
        response.setResult(serviceAuthorisationResult);

        return response;
    }

    /**
     * Handle a card token payment.
     * 
     * @param req the req
     * 
     * @return the authorise with token response
     * 
     * @throws OnlineCardPaymentServiceFaultException the online card payment service fault exception
     */
    public AuthoriseWithTokenResponse authoriseWithToken(AuthoriseWithTokenRequest req) throws OnlineCardPaymentServiceFaultException {
        // set up response objects
        AuthoriseWithTokenResponse response = new AuthoriseWithTokenResponse();
        ServiceAuthorisationResult serviceAuthorisationResult = new ServiceAuthorisationResult();
        AuthorisationResult authResult = null;

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithTokenRequest");
        ValidationUtils.invokeValidator(tokenRequestValidator, req, errors);
        if (errors.hasErrors()) {
            String message = "Validation errors found with token request";

            LOGGER.error(message);
            logErrors(errors); // low-level debug of info

            // add them to response
            addErrorsToList(errors, serviceAuthorisationResult.getValidationMessages());

            // add error info to response
            serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
            serviceAuthorisationResult.setMessage(message);
        } else {
            String guid = req.getGuid();
            // make the system transaction GUID available to the logger
            MDC.put(GUID, guid);

            // common mappings
            CardDetails cardDetails = cardPaymentDataMappingHelper.mapCardDetails(req.getCardDetails());
            PaymentDetails paymentDetails = cardPaymentDataMappingHelper.mapPaymentDetails(req.getPaymentDetails());

            // Service invocation
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Authorising with token");
            }
            try {
                authResult = onlineCardPaymentService.authoriseWithToken(guid, paymentDetails, cardDetails);
            } catch (OnlineCardPaymentServiceException ocpse) {
                LOGGER.error("There was a problem authorising your card payment...", ocpse);
                // add error info to response
                serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
                serviceAuthorisationResult.setMessage(ocpse.getMessage());
            } catch (Exception e) {
                // We need to catch everything so the error is sent back as per the contract.
                LOGGER.error("There was a problem authorising your card payment...", e);
                // add error info to response
                serviceAuthorisationResult.setOutcome(ServiceAuthorisationOutcomeTypeEnum.ERROR);
                serviceAuthorisationResult.setMessage(e.getMessage());
            } finally {
                // remove guid from this thread 
                MDC.remove(GUID);
            }

            // dozer map response (if we have one)
            if(authResult != null) {
                // dozer map response
                dozer.map(authResult, serviceAuthorisationResult);

                // WARN: augment due to dozer 'generic' issue
                if (authResult.getExtra() != null) {
                    serviceAuthorisationResult.getExtra().addAll(OnlineCardPaymentDataMappingHelper.mapToNVPList(authResult.getExtra()));
                }
            }

        }

        response.setResult(serviceAuthorisationResult);

        return response;
    }

    /**
     * Helper method to convert Spring Validation errors.
     * 
     * @param errors the errors
     * @param list the list
     */
    protected void addErrorsToList(Errors errors, List<String> list) {
        List errorList = errors.getAllErrors();
        Iterator i = errorList.iterator();

        while (i.hasNext()) {
            ObjectError e = (ObjectError) i.next();
            list.add(e.getDefaultMessage());
        }
    }

    /**
     * Helper method to log Spring Validation errors.
     * 
     * @param errors the errors
     */
    protected void logErrors(Errors errors) {
        if (LOGGER.isDebugEnabled()) {
            List errorList = errors.getAllErrors();
            Iterator i = errorList.iterator();
            LOGGER.debug("Validation errors for '" + errors.getObjectName() + "'");

            while (i.hasNext()) {
                ObjectError e = (ObjectError) i.next();
                LOGGER.debug(e.toString());
            }
        }
    }

    // +DI
    /**
     * Sets the online card payment service.
     * 
     * @param cardPaymentService the card payment service
     */
    public void setOnlineCardPaymentService(OnlineCardPaymentService cardPaymentService) {
        this.onlineCardPaymentService = cardPaymentService;
    }

    /**
     * Sets the dozer.
     * 
     * @param dozer the dozer to set
     */
    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }

    /**
     * Sets the card payment data mapping helper.
     * 
     * @param cardPaymentDataMappingHelper the cardPaymentDataMappingHelper to
     * set
     */
    public void setCardPaymentDataMappingHelper(OnlineCardPaymentDataMappingHelper cardPaymentDataMappingHelper) {
        this.cardPaymentDataMappingHelper = cardPaymentDataMappingHelper;
    }

    /**
     * Sets the card request validator.
     * 
     * @param cardRequestValidator the cardRequestValidator to set
     * 
     * @throws IllegalArgumentException if the validator is null or wrong type.
     */
    public void setCardRequestValidator(Validator cardRequestValidator) {
        if (cardRequestValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
        }
        if (!cardRequestValidator.supports(AuthoriseWithCardRequest.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must support the validation of [AuthoriseWithCardRequest] instances.");
        }
        this.cardRequestValidator = cardRequestValidator;
    }

    /**
     * Sets the token request validator.
     * 
     * @param tokenRequestValidator the tokenRequestValidator to set
     * 
     * @throws IllegalArgumentException if the validator is null or wrong type.
     */
    public void setTokenRequestValidator(Validator tokenRequestValidator) {
        if (tokenRequestValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
        }
        if (!tokenRequestValidator.supports(AuthoriseWithTokenRequest.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must support the validation of [AuthoriseWithTokenRequest] instances.");
        }
        this.tokenRequestValidator = tokenRequestValidator;
    }

    /**
     * Sets the authentication request validator.
     * 
     * @param authenticationRequestValidator the authenticationRequestValidator
     * to set
     * 
     * @throws IllegalArgumentException if the validator is null or wrong type.
     */
    public void setAuthenticationRequestValidator(Validator authenticationRequestValidator) {
        if (authenticationRequestValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");
        }
        if (!authenticationRequestValidator.supports(AuthoriseWithAuthenticationRequest.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must support the validation of [AuthoriseWithAuthenticationRequest] instances.");
        }
        this.authenticationRequestValidator = authenticationRequestValidator;
    }
    // -DI
}
