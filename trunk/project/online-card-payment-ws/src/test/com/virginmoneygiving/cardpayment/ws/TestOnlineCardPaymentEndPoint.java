package com.virginmoneygiving.cardpayment.ws;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.virginmoneygiving.cardpayment.business.AuthorisationOutcomeTypeEnum;
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
import com.virginmoneygiving.cardpayment.service.messages.MessageHeader;
import com.virginmoneygiving.cardpayment.service.messages.ServiceAuthorisationOutcomeTypeEnum;
import com.virginmoneygiving.cardpayment.service.messages.ServiceAuthorisationResult;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

/**
 * The Class TestOnlineCardPaymentEndPoint.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestOnlineCardPaymentEndPoint {

    /** The bean. */
    private OnlineCardPaymentEndPoint bean;

    // collaborators
    /** The dozer. */
    @Mock
    private DozerBeanMapper dozer;
    
    /** The mock validator. */
    @Mock
    private Validator mockValidator;
    
    /** The service. */
    @Mock
    private OnlineCardPaymentService service;
    
    /** The card payment data mapping helper. */
    private OnlineCardPaymentDataMappingHelper cardPaymentDataMappingHelper;

    /**
     * Intial Setup.
     * 
     * @throws java.lang.Exception exception
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new OnlineCardPaymentEndPoint();
        MockitoAnnotations.initMocks(this);

        cardPaymentDataMappingHelper = new OnlineCardPaymentDataMappingHelper();
        cardPaymentDataMappingHelper.setDozer(dozer);

        bean.setDozer(dozer);
        bean.setOnlineCardPaymentService(service);
        bean.setCardPaymentDataMappingHelper(cardPaymentDataMappingHelper);
    }

    /**
     * Tear down.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithCard(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardRequiringAuthentication() throws Exception {
        Map<String, String> authRequestData = new HashMap<String,String>();
        authRequestData.put("foo", "bar");
        
        AuthorisationResult mockResult = new AuthorisationResult();
        mockResult.setAuthRequestData(authRequestData);
        mockResult.setOutcome(AuthorisationOutcomeTypeEnum.AUTHENTICATION_REQUIRED);
        
        // expected behaviour
        when(mockValidator.supports(AuthoriseWithCardRequest.class)).thenReturn(true);
        
        when(service.authoriseWithCard(anyString(), isA(PaymentDetails.class), isA(CardDetails.class))).thenReturn(
                mockResult); 

        // inject collaborator (will invoke supports with behaviour above)
        bean.setCardRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithCardRequest req = new AuthoriseWithCardRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithCardResponse res = bean.authoriseWithCard(req);

        // verifications
        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithCardRequest.class);
        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithCard(anyString(), isA(PaymentDetails.class), isA(CardDetails.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithCard(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardWithServiceException() throws Exception {
        String exceptionMessage = "Oops";

        Map<String, String> authRequestData = new HashMap<String,String>();
        authRequestData.put("foo", "bar");
        
        AuthorisationResult mockResult = new AuthorisationResult();
        mockResult.setAuthRequestData(authRequestData);
        mockResult.setOutcome(AuthorisationOutcomeTypeEnum.AUTHENTICATION_REQUIRED);
        
        // expected behaviour
        when(mockValidator.supports(AuthoriseWithCardRequest.class)).thenReturn(true);
        
        when(service.authoriseWithCard(anyString(), isA(PaymentDetails.class), isA(CardDetails.class)))
            .thenThrow(new OnlineCardPaymentServiceException(exceptionMessage));

        // inject collaborator (will invoke supports with behaviour above)
        bean.setCardRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithCardRequest req = new AuthoriseWithCardRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithCardResponse res = bean.authoriseWithCard(req);
        assertNotNull(res);
        assertNotNull(res.getResult());
        assertEquals(ServiceAuthorisationOutcomeTypeEnum.ERROR, res.getResult().getOutcome());
        assertEquals(exceptionMessage, res.getResult().getMessage());

        // verifications
        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithCardRequest.class);
        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithCard(anyString(), isA(PaymentDetails.class), isA(CardDetails.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithCard(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardNoAuthentication() throws Exception {
        Map<String, String> extraData = new HashMap<String,String>();
        extraData.put("foo", "bar");
        
        AuthorisationResult mockResult = new AuthorisationResult();
        mockResult.setExtra(extraData);
        mockResult.setOutcome(AuthorisationOutcomeTypeEnum.AUTHENTICATION_REQUIRED);
        
        // expected behaviour
        when(mockValidator.supports(AuthoriseWithCardRequest.class)).thenReturn(true);
        
        when(service.authoriseWithCard(anyString(), isA(PaymentDetails.class), isA(CardDetails.class))).thenReturn(
                mockResult); 

        // inject collaborator (will invoke supports with behaviour above)
        bean.setCardRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithCardRequest req = new AuthoriseWithCardRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithCardResponse res = bean.authoriseWithCard(req);

        // verifications
        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithCardRequest.class);
        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithCard(anyString(), isA(PaymentDetails.class), isA(CardDetails.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithCard(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardValidationErrors() throws Exception {
        Validator spy = spy(new ErrorValidator());
        bean.setCardRequestValidator(spy);
        
        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithCardRequest req = new AuthoriseWithCardRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithCardResponse res = bean.authoriseWithCard(req);

        // verifications
        // performed both on DI and by Spring ValidationUtils
        verify(spy, times(2)).supports(AuthoriseWithCardRequest.class);
        verify(spy).validate(isA(Object.class), isA(Errors.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithAuthentication(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithAuthenticationRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthentication() throws Exception {

        // expected behaviour
        when(mockValidator.supports(AuthoriseWithAuthenticationRequest.class)).thenReturn(true);

        when(service.authoriseWithAuthentication(isA(String.class), isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class))).thenReturn(
                new AuthorisationResult()); // TODO: make proper
        // stub

        // inject collaborator (will invoke supports with behaviour above)
        bean.setAuthenticationRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithAuthenticationRequest req = new AuthoriseWithAuthenticationRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithAuthenticationResponse res = bean.authoriseWithAuthentication(req);

        // verifications

        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithAuthenticationRequest.class);

        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithAuthentication(isA(String.class), isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class));
        verify(dozer).map(anyObject(), isA(ServiceAuthorisationResult.class));

        // TODO: assertions
        assertNotNull(res);
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithAuthentication(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithAuthenticationRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationValidationErrors() throws Exception {
        Validator spy = spy(new ErrorValidator());
        bean.setAuthenticationRequestValidator(spy);
        
        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithAuthenticationRequest req = new AuthoriseWithAuthenticationRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithAuthenticationResponse res = bean.authoriseWithAuthentication(req);

        // verifications
        // performed both on DI and by Spring ValidationUtils
        verify(spy, times(2)).supports(AuthoriseWithAuthenticationRequest.class);
        verify(spy).validate(isA(Object.class), isA(Errors.class));

        // TODO: assertions
        assertNotNull(res);
        assertNotNull(res.getResult());
        assertEquals(ServiceAuthorisationOutcomeTypeEnum.ERROR, res.getResult().getOutcome());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithAuthentication(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithAuthenticationRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationWithServiceException() throws Exception {
        String exceptionMessage = "Oops";

        // expected behaviour
        when(mockValidator.supports(AuthoriseWithAuthenticationRequest.class)).thenReturn(true);

        when(service.authoriseWithAuthentication(isA(String.class), isA(PaymentDetails.class), 
                isA(CardDetails.class), isA(Map.class))).thenThrow(
                new OnlineCardPaymentServiceException(exceptionMessage));

        // inject collaborator (will invoke supports with behaviour above)
        bean.setAuthenticationRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithAuthenticationRequest req = new AuthoriseWithAuthenticationRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithAuthenticationResponse res = bean.authoriseWithAuthentication(req);

        // verifications

        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithAuthenticationRequest.class);

        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithAuthentication(isA(String.class), isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class));

        // assertions
        assertNotNull(res);
        assertNotNull(res.getResult());
        assertEquals(ServiceAuthorisationOutcomeTypeEnum.ERROR, res.getResult().getOutcome());
        assertEquals(exceptionMessage, res.getResult().getMessage());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithToken(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithTokenRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithToken() throws Exception {

        // expected behaviour
        when(mockValidator.supports(AuthoriseWithTokenRequest.class)).thenReturn(true);

        when(service.authoriseWithToken(isA(String.class), isA(PaymentDetails.class), isA(CardDetails.class))).thenReturn(new AuthorisationResult()); // TODO:
        // make
        // proper
        // stub

        // inject collaborator (will invoke supports with behaviour above)
        bean.setTokenRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithTokenRequest req = new AuthoriseWithTokenRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithTokenResponse res = bean.authoriseWithToken(req);

        // verifications

        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithTokenRequest.class);

        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithToken(isA(String.class), isA(PaymentDetails.class), isA(CardDetails.class));
        verify(dozer).map(anyObject(), isA(ServiceAuthorisationResult.class));

        // TODO: assertions
        assertNotNull(res);
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithToken(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithTokenRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithTokenWithServiceException() throws Exception {
        String exceptionMessage = "Oops";

        // expected behaviour
        when(mockValidator.supports(AuthoriseWithTokenRequest.class)).thenReturn(true);

        when(service.authoriseWithToken(isA(String.class), isA(PaymentDetails.class), 
                isA(CardDetails.class))).thenThrow(new OnlineCardPaymentServiceException(exceptionMessage));

        // inject collaborator (will invoke supports with behaviour above)
        bean.setTokenRequestValidator(mockValidator);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithTokenRequest req = new AuthoriseWithTokenRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithTokenResponse res = bean.authoriseWithToken(req);

        // verifications

        // performed both on DI and by Spring ValidationUtils
        verify(mockValidator, times(2)).supports(AuthoriseWithTokenRequest.class);

        verify(mockValidator).validate(isA(Object.class), isA(Errors.class));
        verify(service).authoriseWithToken(isA(String.class), isA(PaymentDetails.class), isA(CardDetails.class));

        // assertions
        assertNotNull(res);
        assertNotNull(res.getResult());
        assertEquals(ServiceAuthorisationOutcomeTypeEnum.ERROR, res.getResult().getOutcome());
        assertEquals(exceptionMessage, res.getResult().getMessage());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.ws.OnlineCardPaymentEndPoint#authoriseWithToken(com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithTokenRequest)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithTokenValidationErrors() throws Exception {
        Validator spy = spy(new ErrorValidator());
        bean.setTokenRequestValidator(spy);

        // create request objects
        ServiceCardDetails scd = new ServiceCardDetails();
        ServicePaymentDetails spd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();

        // TODO: put data into request objects

        // create request
        AuthoriseWithTokenRequest req = new AuthoriseWithTokenRequest();
        req.setGuid(UUID.randomUUID().toString());
        req.setCardDetails(scd);
        req.setHeader(header);
        req.setPaymentDetails(spd);

        // invoke
        AuthoriseWithTokenResponse res = bean.authoriseWithToken(req);

        // verifications
        // performed both on DI and by Spring ValidationUtils
        verify(spy, times(2)).supports(AuthoriseWithTokenRequest.class);
        verify(spy).validate(isA(Object.class), isA(Errors.class));

        // TODO: assertions
        assertNotNull(res);
        assertNotNull(res.getResult());
        assertEquals(ServiceAuthorisationOutcomeTypeEnum.ERROR, res.getResult().getOutcome());
    }
    
    /**
     * Test null validators.
     */
    @Test
    public void testNullValidators() {
        try {
            bean.setAuthenticationRequestValidator(null);
            fail("Exception expected");
        } catch(IllegalArgumentException expected) {
            // do nothing
        }
        
        try {
            bean.setTokenRequestValidator(null);
            fail("Exception expected");
        } catch(IllegalArgumentException expected) {
            // do nothing
        }

        try {
            bean.setCardRequestValidator(null);
            fail("Exception expected");
        } catch(IllegalArgumentException expected) {
            // do nothing
        }
    }

    /**
     * Test bad validator config.
     */
    @Test
    public void testBadValidatorConfig() {
        when(mockValidator.supports(isA(Class.class))).thenReturn(false);

        try {
            bean.setAuthenticationRequestValidator(mockValidator);
            fail("Exception expected");
        } catch(IllegalArgumentException expected) {
            // do nothing
        }
        
        try {
            bean.setTokenRequestValidator(mockValidator);
            fail("Exception expected");
        } catch(IllegalArgumentException expected) {
            // do nothing
        }

        try {
            bean.setCardRequestValidator(mockValidator);
            fail("Exception expected");
        } catch(IllegalArgumentException expected) {
            // do nothing
        }

        verify(mockValidator, times(3)).supports(isA(Class.class));
    }

    /**
     * Test Validator that will always return a validation error.
     */
    private class ErrorValidator implements Validator {
        
        /* (non-Javadoc)
         * @see org.springframework.validation.Validator#supports(java.lang.Class)
         */
        public boolean supports(Class clazz) { return true; }
        
        /* (non-Javadoc)
         * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
         */
        public void validate(Object target, Errors errors) {
            errors.reject("Foo");
        }
    };
}
