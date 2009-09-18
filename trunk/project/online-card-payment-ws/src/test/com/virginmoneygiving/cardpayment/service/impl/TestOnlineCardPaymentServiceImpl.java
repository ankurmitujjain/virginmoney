package com.virginmoneygiving.cardpayment.service.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import com.virginmoneygiving.cardpayment.business.AuthenticateActionEnum;
import com.virginmoneygiving.cardpayment.business.AuthorisationOutcomeTypeEnum;
import com.virginmoneygiving.cardpayment.business.AuthorisationResult;
import com.virginmoneygiving.cardpayment.business.AuthoriseActionEnum;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.CardSecurityInformation;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.business.PaymentTypeEnum;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderValidationResponse;
import com.virginmoneygiving.cardpayment.event.AuthorisationErrorEvent;
import com.virginmoneygiving.cardpayment.event.BasePaymentEvent;
import com.virginmoneygiving.cardpayment.event.ValidationErrorEvent;
import com.virginmoneygiving.cardpayment.event.ValidationFailedEvent;
import com.virginmoneygiving.cardpayment.helper.ThreeDSecureHelper;
import com.virginmoneygiving.cardpayment.service.EndpointProbeService;
import com.virginmoneygiving.cardpayment.service.PaymentDecisionManager;
import com.virginmoneygiving.cardpayment.serviceproxy.PaymentProviderServiceProxy;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

// TODO: Auto-generated Javadoc
/**
 * Test the main service logic.<br>
 * TODO: implement more of the edge cases...
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestOnlineCardPaymentServiceImpl {

    /** unit under test. */
    private OnlineCardPaymentServiceImpl bean;

    // collaborators
    /** Mock event publisher. */
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    /** Mock endpoint probe. */
    @Mock
    private EndpointProbeService endpointProbeService;

    /** Mock decision manager. */
    @Mock
    private PaymentDecisionManager paymentDecisionManager;

    /** Mock TLG proxy. */
    @Mock
    private PaymentProviderServiceProxy paymentProviderServiceProxy;

    /** Mock 3D helper. */
    @Mock
    private ThreeDSecureHelper threeDSecureHelper;

    // mock test data for verification

    /** Mock validation response. */
    @Mock
    private ProviderValidationResponse providerValidationResponse;

    /** Mock authorisation response. */
    @Mock
    private ProviderAuthorisationResponse providerAuthorisationResponse;

    /** Mock second authorisation response (for complete/cancel). */
    @Mock
    private ProviderAuthorisationResponse providerAuthorisationResponse2;

    // test data
    /** GUID constant. */
    private static final String TEST_GUID = "0xDEADBEEF";

    /** card details. */
    private CardDetails cardDetails;

    /** Payment details. */
    private PaymentDetails paymentDetails;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new OnlineCardPaymentServiceImpl();
        MockitoAnnotations.initMocks(this);

        bean.setApplicationEventPublisher(applicationEventPublisher);
        bean.setEndpointProbeService(endpointProbeService);
        bean.setPaymentDecisionManager(paymentDecisionManager);
        bean.setPaymentProviderServiceProxy(paymentProviderServiceProxy);
        bean.setThreeDSecureHelper(threeDSecureHelper);

        bean.setPrimary3DUrl("http://localhost:8080/primary");
        bean.setSecondary3DUrl("http://localhost:8080/secondary");

        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);
        
        cardDetails = new CardDetails();
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
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithCard(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardSuccess() throws Exception {
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks

        when(providerValidationResponse.getAcquirerId()).thenReturn("V");
        when(providerValidationResponse.getResultCode()).thenReturn("0");
        when(providerValidationResponse.getValidationPassed())
                .thenReturn("Yes");
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getResponseCode()).thenReturn("0");
        when(providerAuthorisationResponse.getAuthChecker()).thenReturn(
                "Issuer");
        when(providerAuthorisationResponse.getCv2Response()).thenReturn(
                "matched");
        when(providerAuthorisationResponse.getAvsAddressResponse()).thenReturn(
                "matched");
        when(providerAuthorisationResponse.getAvsPostCodeResponse())
                .thenReturn("matched");
        when(providerAuthorisationResponse.getProcessorTransactionId())
                .thenReturn("oxcafebabe");
        when(providerAuthorisationResponse2.getResultCode()).thenReturn("0");

        // set collaborator behaviour

        // Request a TLG card validation
        when(paymentProviderServiceProxy.validate(TEST_GUID, paymentDetails,
                        cardDetails)).thenReturn(providerValidationResponse);

        // Ensure that we think it is a valid card
        when(paymentDecisionManager.assessValidateResponse(
                        isA(String.class), isA(String.class))).thenReturn(true);

        // Ensure we have no 3D secure
        when(paymentDecisionManager.isAuthenticationRequired(
                isA(String.class), isA(String.class))).thenReturn(false);

        when(paymentProviderServiceProxy.authoriseWithCv2Avs(TEST_GUID,
                        paymentDetails, cardDetails)).thenReturn(
                providerAuthorisationResponse);

        when(paymentDecisionManager
                        .assessAuthoriseResponse(isA(String.class),
                                isA(String.class), isA(String.class),
                                isA(String.class), isA(String.class)))
                .thenReturn(AuthoriseActionEnum.COMPLETE);

        when(paymentProviderServiceProxy.completeTransaction(
                        isA(String.class), isA(String.class))).thenReturn(
                providerAuthorisationResponse2);

        // test
        assertNotNull(bean.authoriseWithCard(TEST_GUID, paymentDetails,
                cardDetails));

        // verify
        verify(paymentProviderServiceProxy).validate(TEST_GUID, paymentDetails,
                cardDetails);
        verify(providerValidationResponse, atLeastOnce()).getResultCode();
        verify(providerValidationResponse, atLeastOnce()).getValidationPassed();
        verify(providerValidationResponse, atLeastOnce()).getAcquirerId();
        verify(applicationEventPublisher).publishEvent(
                isA(BasePaymentEvent.class));
        verify(paymentDecisionManager).assessValidateResponse(
                isA(String.class), isA(String.class));
        verify(paymentDecisionManager).isAuthenticationRequired(
                isA(String.class), isA(String.class));
        verify(paymentProviderServiceProxy, times(1)).authoriseWithCv2Avs(
                TEST_GUID, paymentDetails, cardDetails);
        verify(paymentDecisionManager).assessAuthoriseResponse(
                isA(String.class), isA(String.class), isA(String.class),
                isA(String.class), isA(String.class));
        verify(paymentProviderServiceProxy).completeTransaction(
                isA(String.class), isA(String.class));
        verify(providerAuthorisationResponse2, atLeastOnce()).getResultCode();
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithCard(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardValidationFailure() throws Exception {
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks

        when(providerValidationResponse.getResultCode()).thenReturn("0");
        when(providerValidationResponse.getValidationPassed()).thenReturn("No");

        // set collaborator behaviour

        // Request a TLG card validation
        when(paymentProviderServiceProxy.validate(TEST_GUID, paymentDetails,
                        cardDetails)).thenReturn(providerValidationResponse);

        // Ensure that we think it is an invalid card
        when(paymentDecisionManager.assessValidateResponse(
                        isA(String.class), isA(String.class)))
                .thenReturn(false);

        // test
        bean.authoriseWithCard(TEST_GUID, paymentDetails, cardDetails);

        // verify
        verify(paymentProviderServiceProxy).validate(TEST_GUID, paymentDetails,
                cardDetails);
        verify(providerValidationResponse, atLeastOnce()).getResultCode();
        verify(providerValidationResponse, atLeastOnce()).getValidationPassed();
        verify(applicationEventPublisher).publishEvent(
                isA(ValidationFailedEvent.class));
        verify(paymentDecisionManager).assessValidateResponse(
                isA(String.class), isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithCard(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardValidationError() throws Exception {
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks

        when(providerValidationResponse.getResultCode()).thenReturn("-6250");
        when(providerValidationResponse.getValidationPassed()).thenReturn("No");

        // set collaborator behaviour

        // Request a TLG card validation
        when(paymentProviderServiceProxy.validate(TEST_GUID, paymentDetails,
                        cardDetails)).thenReturn(providerValidationResponse);

        // Ensure that we think it is an invalid card
        when(paymentDecisionManager.assessValidateResponse(
                        isA(String.class), isA(String.class)))
                .thenReturn(false);

        // test
        bean.authoriseWithCard(TEST_GUID, paymentDetails, cardDetails);

        // verify
        verify(paymentProviderServiceProxy).validate(TEST_GUID, paymentDetails,
                cardDetails);
        verify(providerValidationResponse, atLeastOnce()).getResultCode();
        verify(providerValidationResponse, atLeastOnce()).getValidationPassed();
        verify(applicationEventPublisher).publishEvent(
                isA(ValidationErrorEvent.class));
        verify(paymentDecisionManager).assessValidateResponse(
                isA(String.class), isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithCard(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCard3dRequired() throws Exception {
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks

        when(providerValidationResponse.getAcquirerId()).thenReturn("V");
        when(providerValidationResponse.getResultCode()).thenReturn("0");
        when(providerValidationResponse.getValidationPassed())
                .thenReturn("Yes");

        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getResponseCode()).thenReturn("0");
        when(providerAuthorisationResponse.getAuthChecker()).thenReturn(
                "Issuer");
        when(providerAuthorisationResponse.getCv2Response()).thenReturn(
                "matched");
        when(providerAuthorisationResponse.getAvsAddressResponse()).thenReturn(
                "matched");
        when(providerAuthorisationResponse.getAvsPostCodeResponse())
                .thenReturn("matched");
        when(providerAuthorisationResponse.getProcessorTransactionId())
                .thenReturn("oxcafebabe");

        when(providerAuthorisationResponse2.getResultCode()).thenReturn("0");

        // set collaborator behaviour

        // Request a TLG card validation
        when(paymentProviderServiceProxy.validate(TEST_GUID, paymentDetails,
                        cardDetails)).thenReturn(providerValidationResponse);

        // Ensure that we think it is a valid card
        when(paymentDecisionManager.assessValidateResponse(
                        isA(String.class), isA(String.class))).thenReturn(true);

        // Ensure we have 3D secure
        when(paymentDecisionManager.isAuthenticationRequired(
                        isA(String.class), isA(String.class))).thenReturn(true);

        // test
        bean.authoriseWithCard(TEST_GUID, paymentDetails, cardDetails);

        // verify
        verify(paymentProviderServiceProxy).validate(TEST_GUID, paymentDetails,
                cardDetails);
        verify(providerValidationResponse, atLeastOnce()).getResultCode();
        verify(providerValidationResponse, atLeastOnce()).getValidationPassed();
        verify(providerValidationResponse, atLeastOnce()).getAcquirerId();
        verify(applicationEventPublisher).publishEvent(
                isA(BasePaymentEvent.class));
        verify(paymentDecisionManager).assessValidateResponse(
                isA(String.class), isA(String.class));
        verify(paymentDecisionManager).isAuthenticationRequired(
                isA(String.class), isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithCard(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardWithValidateProxyException() throws Exception {
        String exceptionMessage = "Oops!";
        
        // set collaborator behaviour

        // Request a TLG card validation
        when(paymentProviderServiceProxy.validate(TEST_GUID, paymentDetails,
                        cardDetails)).thenThrow(new RuntimeException(exceptionMessage));

        // test
        try {
            bean.authoriseWithCard(TEST_GUID, paymentDetails, cardDetails);
            fail("Exception expected...");
        } catch (Exception expected) {
            assertEquals(exceptionMessage, expected.getMessage());
        }

        // verify
        verify(paymentProviderServiceProxy).validate(TEST_GUID, paymentDetails,
                cardDetails);
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithCard(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithCardWithAuthoriseProxyException() throws Exception {
        String exceptionMessage = "Oops!";

        when(providerValidationResponse.getAcquirerId()).thenReturn("V");
        when(providerValidationResponse.getResultCode()).thenReturn("0");
        when(providerValidationResponse.getValidationPassed())
                .thenReturn("Yes");

        // set collaborator behaviour
        // Request a TLG card validation
        when(paymentProviderServiceProxy.validate(TEST_GUID, paymentDetails,
                cardDetails)).thenReturn(providerValidationResponse);

        // Ensure that we think it is a valid card
        when(paymentDecisionManager.assessValidateResponse(
                        isA(String.class), isA(String.class))).thenReturn(true);

        // Ensure we have no 3D secure
        when(paymentDecisionManager.isAuthenticationRequired(
                isA(String.class), isA(String.class))).thenReturn(false);

        when(paymentProviderServiceProxy.authoriseWithCv2Avs(TEST_GUID,
                        paymentDetails, cardDetails))
                        .thenThrow(new RuntimeException(exceptionMessage));

        // test
        try {
            bean.authoriseWithCard(TEST_GUID, paymentDetails, cardDetails);
            fail("Exception expected...");
        } catch (Exception expected) {
            assertEquals(exceptionMessage, expected.getMessage());
        }

        // verify
        verify(paymentProviderServiceProxy).validate(TEST_GUID, paymentDetails,
                cardDetails);
        verify(paymentDecisionManager).assessValidateResponse(
                isA(String.class), isA(String.class));
        verify(paymentDecisionManager).isAuthenticationRequired(
                isA(String.class), isA(String.class));
        verify(paymentProviderServiceProxy).authoriseWithCv2Avs(TEST_GUID,
                    paymentDetails, cardDetails);
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationComplete() throws Exception {
        String guid = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);
        paymentDetails.setAmount(new BigDecimal("12.00"));
        paymentDetails.setCurrencyCode("GBP");
        paymentDetails.setPaymentRef("JUnit");

        CardSecurityInformation csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cardDetails = new CardDetails();
        cardDetails.setPan("4111111111111111");
        cardDetails.setEndMonth(12);
        cardDetails.setEndYear(2009);
        cardDetails.setSecurityInfo(csi);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("xid","CY+tPbmNjpnihsV7daleZxzUS68=");
        map.put("vendorCode","");
        map.put("txstatus","Y");
        map.put("mdErrorMsg","Authenticated");
        map.put("eci","05");
        map.put("merchantID","056");
        map.put("sID","1");
        map.put("digest","IVHNzOxPQ4ZU1RdtIWMsjKB1jNA=");
        map.put("opalErrorCode","0");
        map.put("md","");
        map.put("MD","");
        map.put("cavv","AAABB0EJgwAAAAABEAmDAAAAAAA=");
        map.put("cavvAlgorithm","2");
        map.put("PAResSyntaxOK","true");
        map.put("PAResVerified","true");
        map.put("iReqCode","");
        map.put("iReqDetail","");

        //record behaviour
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getProcessorTransactionId())
            .thenReturn("oxcafebabe");
        when(providerAuthorisationResponse2.getResultCode()).thenReturn("0");

        // collaborators
        when(threeDSecureHelper.verifyAuthenticationResponseDigest(isA(Map.class))).thenReturn(true);
        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.AUTH_3D);
        when(paymentDecisionManager.assessAuthenticationResponse(anyString())).thenReturn(true);
        when(paymentProviderServiceProxy.authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class)))
                .thenReturn(providerAuthorisationResponse);
        when(paymentDecisionManager.assessAuthoriseResponse(
                anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(AuthoriseActionEnum.COMPLETE);
        when(paymentProviderServiceProxy.completeTransaction(
                anyString(), anyString())).thenReturn(
                        providerAuthorisationResponse2);

        // invoke
        AuthorisationResult authRes = bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
        assertNotNull(authRes);

        // verify
        verify(providerAuthorisationResponse).getResultCode();
        verify(providerAuthorisationResponse).getProcessorTransactionId(); 
        verify(providerAuthorisationResponse).getProcessorReference(); 
        verify(providerAuthorisationResponse2).getResultCode();
        verify(threeDSecureHelper).verifyAuthenticationResponseDigest(isA(Map.class));
        verify(paymentDecisionManager).assessAuthenticationResponse(anyString());
        verify(paymentProviderServiceProxy).authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class));
        verify(paymentDecisionManager).assessAuthoriseResponse(
                anyString(),anyString(),anyString(),anyString(),anyString());
        verify(paymentProviderServiceProxy).completeTransaction(
                anyString(), anyString());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationCancel() throws Exception {
        String guid = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);
        paymentDetails.setAmount(new BigDecimal("12.00"));
        paymentDetails.setCurrencyCode("GBP");
        paymentDetails.setPaymentRef("JUnit");

        CardSecurityInformation csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cardDetails = new CardDetails();
        cardDetails.setPan("4111111111111111");
        cardDetails.setEndMonth(12);
        cardDetails.setEndYear(2009);
        cardDetails.setSecurityInfo(csi);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("xid","CY+tPbmNjpnihsV7daleZxzUS68=");
        map.put("vendorCode","");
        map.put("txstatus","Y");
        map.put("mdErrorMsg","Authenticated");
        map.put("eci","05");
        map.put("merchantID","056");
        map.put("sID","1");
        map.put("digest","IVHNzOxPQ4ZU1RdtIWMsjKB1jNA=");
        map.put("opalErrorCode","0");
        map.put("md","");
        map.put("MD","");
        map.put("cavv","AAABB0EJgwAAAAABEAmDAAAAAAA=");
        map.put("cavvAlgorithm","2");
        map.put("PAResSyntaxOK","true");
        map.put("PAResVerified","true");
        map.put("iReqCode","");
        map.put("iReqDetail","");

        //record behaviour
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getProcessorTransactionId())
            .thenReturn("oxcafebabe");
        when(providerAuthorisationResponse2.getResultCode()).thenReturn("0");

        // collaborators
        when(threeDSecureHelper.verifyAuthenticationResponseDigest(isA(Map.class))).thenReturn(true);
        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.AUTH_3D);
        when(paymentDecisionManager.assessAuthenticationResponse(anyString())).thenReturn(true);
        when(paymentProviderServiceProxy.authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class)))
                .thenReturn(providerAuthorisationResponse);
        when(paymentDecisionManager.assessAuthoriseResponse(
                anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(AuthoriseActionEnum.CANCEL);
        when(paymentProviderServiceProxy.cancelTransaction(
                anyString(), anyString())).thenReturn(
                        providerAuthorisationResponse2);

        // invoke
        AuthorisationResult authRes = bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
        assertNotNull(authRes);

        // verify
        verify(providerAuthorisationResponse).getResultCode();
        verify(providerAuthorisationResponse).getProcessorTransactionId(); 
        verify(providerAuthorisationResponse).getProcessorReference(); 
        verify(providerAuthorisationResponse2).getResultCode();
        verify(threeDSecureHelper).verifyAuthenticationResponseDigest(isA(Map.class));
        verify(paymentDecisionManager).assessAuthenticationResponse(anyString());
        verify(paymentProviderServiceProxy).authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class));
        verify(paymentDecisionManager).assessAuthoriseResponse(
                anyString(),anyString(),anyString(),anyString(),anyString());
        verify(paymentProviderServiceProxy).cancelTransaction(
                anyString(), anyString());
        verify(applicationEventPublisher, times(2)).publishEvent(
                isA(BasePaymentEvent.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationNoFurtherAction() throws Exception {
        String guid = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);
        paymentDetails.setAmount(new BigDecimal("12.00"));
        paymentDetails.setCurrencyCode("GBP");
        paymentDetails.setPaymentRef("JUnit");

        CardSecurityInformation csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cardDetails = new CardDetails();
        cardDetails.setPan("4111111111111111");
        cardDetails.setEndMonth(12);
        cardDetails.setEndYear(2009);
        cardDetails.setSecurityInfo(csi);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("xid","CY+tPbmNjpnihsV7daleZxzUS68=");
        map.put("vendorCode","");
        map.put("txstatus","Y");
        map.put("mdErrorMsg","Authenticated");
        map.put("eci","05");
        map.put("merchantID","056");
        map.put("sID","1");
        map.put("digest","IVHNzOxPQ4ZU1RdtIWMsjKB1jNA=");
        map.put("opalErrorCode","0");
        map.put("md","");
        map.put("MD","");
        map.put("cavv","AAABB0EJgwAAAAABEAmDAAAAAAA=");
        map.put("cavvAlgorithm","2");
        map.put("PAResSyntaxOK","true");
        map.put("PAResVerified","true");
        map.put("iReqCode","");
        map.put("iReqDetail","");

        //record behaviour
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getProcessorTransactionId())
            .thenReturn("oxcafebabe");

        // collaborators
        when(threeDSecureHelper.verifyAuthenticationResponseDigest(isA(Map.class))).thenReturn(true);
        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.AUTH_3D);
        when(paymentDecisionManager.assessAuthenticationResponse(anyString())).thenReturn(true);
        when(paymentProviderServiceProxy.authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class)))
                .thenReturn(providerAuthorisationResponse);
        when(paymentDecisionManager.assessAuthoriseResponse(
                anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(AuthoriseActionEnum.NONE);

        // invoke
        AuthorisationResult authRes = bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
        assertNotNull(authRes);
        assertEquals(AuthorisationOutcomeTypeEnum.ERROR, authRes.getOutcome());

        // verify
        verify(providerAuthorisationResponse).getResultCode();
        verify(providerAuthorisationResponse).getProcessorReference();
        verify(threeDSecureHelper).verifyAuthenticationResponseDigest(isA(Map.class));
        verify(paymentDecisionManager).assessAuthenticationResponse(anyString());
        verify(paymentProviderServiceProxy).authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class));
        verify(paymentDecisionManager).assessAuthoriseResponse(
                anyString(),anyString(),anyString(),anyString(),anyString());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * This should not happen as Spring Validation valang rules prevent it being null.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationNullAuthData() throws Exception {
        String guid = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        cardDetails = new CardDetails();
        
        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, null);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertTrue("Should contain guid", expected.getMessage().contains(guid));
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * This should not happen as Spring Validation valang rules check the data.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationInvalidAuthData() throws Exception {
        String guid = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        cardDetails = new CardDetails();
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        
        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertTrue("Should contain guid", expected.getMessage().contains(guid));
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * This should not happen as Spring Validation valang rules check the data.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationMissingOpal() throws Exception {
        String guid = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        cardDetails = new CardDetails();
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        
        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertTrue("Should contain guid", expected.getMessage().contains(guid));
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * This should not happen as Spring Validation valang rules check the data.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationOpalError() throws Exception {
        String guid = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        cardDetails = new CardDetails();
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("opalErrorCode","86");

        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.ERROR);

        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
            fail("Exception expected");
        } catch (RuntimeException expected) {
            assertTrue("Should contain guid", expected.getMessage().contains(guid));
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * This should not happen as Spring Validation valang rules check the data.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationDigestFailure() throws Exception {
        String guid = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        cardDetails = new CardDetails();
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("opalErrorCode","0");
        map.put("digest","IVHNzOxPQ4ZU1RdtIW=");

        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.AUTH_3D);

        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage() + " should contain guid", expected.getMessage().contains(guid));
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationRejectedMdStatus() throws Exception {
        String guid = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);
        paymentDetails.setAmount(new BigDecimal("12.00"));
        paymentDetails.setCurrencyCode("GBP");
        paymentDetails.setPaymentRef("JUnit");

        CardSecurityInformation csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cardDetails = new CardDetails();
        cardDetails.setPan("4111111111111111");
        cardDetails.setEndMonth(12);
        cardDetails.setEndYear(2009);
        cardDetails.setSecurityInfo(csi);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("xid","CY+tPbmNjpnihsV7daleZxzUS68=");
        map.put("vendorCode","");
        map.put("txstatus","Y");
        map.put("mdErrorMsg","Authenticated");
        map.put("eci","05");
        map.put("merchantID","056");
        map.put("sID","1");
        map.put("digest","IVHNzOxPQ4ZU1RdtIWMsjKB1jNA=");
        map.put("opalErrorCode","0");
        map.put("md","");
        map.put("MD","");
        map.put("cavv","AAABB0EJgwAAAAABEAmDAAAAAAA=");
        map.put("cavvAlgorithm","2");
        map.put("PAResSyntaxOK","true");
        map.put("PAResVerified","true");
        map.put("iReqCode","");
        map.put("iReqDetail","");

        //record behaviour

        // collaborators
        when(threeDSecureHelper.verifyAuthenticationResponseDigest(isA(Map.class))).thenReturn(true);
        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.AUTH_3D);
        when(paymentDecisionManager.assessAuthenticationResponse(anyString())).thenReturn(false);

        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage().contains(guid));
        }

        // verify
        verify(threeDSecureHelper).verifyAuthenticationResponseDigest(isA(Map.class));
        verify(paymentDecisionManager).assessAuthenticationResponse(anyString());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithAuthentication(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithAuthenticationProxyException() throws Exception {
        String exceptionMessage = "Oh dear.";
        String guid = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);
        paymentDetails.setAmount(new BigDecimal("12.00"));
        paymentDetails.setCurrencyCode("GBP");
        paymentDetails.setPaymentRef("JUnit");

        CardSecurityInformation csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cardDetails = new CardDetails();
        cardDetails.setPan("4111111111111111");
        cardDetails.setEndMonth(12);
        cardDetails.setEndYear(2009);
        cardDetails.setSecurityInfo(csi);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("version","2.0");
        map.put("mdStatus","1");
        map.put("xid","CY+tPbmNjpnihsV7daleZxzUS68=");
        map.put("vendorCode","");
        map.put("txstatus","Y");
        map.put("mdErrorMsg","Authenticated");
        map.put("eci","05");
        map.put("merchantID","056");
        map.put("sID","1");
        map.put("digest","IVHNzOxPQ4ZU1RdtIWMsjKB1jNA=");
        map.put("opalErrorCode","0");
        map.put("md","");
        map.put("MD","");
        map.put("cavv","AAABB0EJgwAAAAABEAmDAAAAAAA=");
        map.put("cavvAlgorithm","2");
        map.put("PAResSyntaxOK","true");
        map.put("PAResVerified","true");
        map.put("iReqCode","");
        map.put("iReqDetail","");

        //record behaviour

        // collaborators
        when(threeDSecureHelper.verifyAuthenticationResponseDigest(isA(Map.class))).thenReturn(true);
        when(paymentDecisionManager.assessAuthenticationResponseCode(anyString())).thenReturn(AuthenticateActionEnum.AUTH_3D);
        when(paymentDecisionManager.assessAuthenticationResponse(anyString())).thenReturn(true);
        when(paymentProviderServiceProxy.authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class)))
                .thenThrow(new RuntimeException(exceptionMessage));

        // invoke
        try {
            bean.authoriseWithAuthentication(guid, paymentDetails, cardDetails, map);
            fail("Exception expected");
        } catch (RuntimeException expected) {
            assertEquals(exceptionMessage, expected.getMessage());
        }

        // verify
        verify(threeDSecureHelper).verifyAuthenticationResponseDigest(isA(Map.class));
        verify(paymentDecisionManager).assessAuthenticationResponse(anyString());
        verify(paymentProviderServiceProxy).authoriseWithAuthentication(anyString(),
                isA(PaymentDetails.class), isA(CardDetails.class), isA(Map.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithToken(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithToken() throws Exception {
        cardDetails.setToken("0xcafebabe");
        
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getResponseCode()).thenReturn("0");
        when(providerAuthorisationResponse.getProcessorTransactionId())
                .thenReturn("oxcafebabe");

        // set collaborator behaviour
        when(paymentProviderServiceProxy.authoriseByToken(TEST_GUID,
                        paymentDetails, cardDetails)).thenReturn(
                providerAuthorisationResponse);

        when(paymentDecisionManager
                        .assessAuthoriseResponse(isA(String.class)))
                .thenReturn(true);

        // invoke
        AuthorisationResult res = bean.authoriseWithToken(TEST_GUID, paymentDetails,
                cardDetails);

        // assertions
        assertNotNull(res);
        assertEquals(AuthorisationOutcomeTypeEnum.SUCCESS, res.getOutcome());
        
        // verify
        verify(paymentProviderServiceProxy, times(1)).authoriseByToken(
                TEST_GUID, paymentDetails, cardDetails);
        verify(paymentDecisionManager).assessAuthoriseResponse(anyString());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithToken(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithTokenBadTransactionResponse() throws Exception {
        cardDetails.setToken("0xcafebabe");
        
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getResponseCode()).thenReturn("0");
        when(providerAuthorisationResponse.getProcessorTransactionId())
                .thenReturn("oxcafebabe");

        // set collaborator behaviour
        when(paymentProviderServiceProxy.authoriseByToken(TEST_GUID,
                        paymentDetails, cardDetails)).thenReturn(
                providerAuthorisationResponse);

        when(paymentDecisionManager
                        .assessAuthoriseResponse(isA(String.class)))
                .thenReturn(false);

        // invoke
        AuthorisationResult res = bean.authoriseWithToken(TEST_GUID, paymentDetails,
                cardDetails);

        // assertions
        assertNotNull(res);
        assertEquals(AuthorisationOutcomeTypeEnum.ERROR, res.getOutcome());
        
        // verify
        verify(paymentProviderServiceProxy, times(1)).authoriseByToken(
                TEST_GUID, paymentDetails, cardDetails);
        verify(paymentDecisionManager).assessAuthoriseResponse(anyString());
    }
    
    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.OnlineCardPaymentServiceImpl#authoriseWithToken(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testAuthoriseWithTokenProxyException() throws Exception {
        String exceptionMessage = "Oops!";
        cardDetails.setToken("0xcafebabe");
        
        // Note: the endpoint uses Spring Validation to perform basic null
        // checks
        when(providerAuthorisationResponse.getResultCode()).thenReturn("0");
        when(providerAuthorisationResponse.getResponseCode()).thenReturn("0");
        when(providerAuthorisationResponse.getProcessorTransactionId())
                .thenReturn("oxcafebabe");

        // set collaborator behaviour
        when(paymentProviderServiceProxy.authoriseByToken(TEST_GUID,
                paymentDetails, cardDetails))
                .thenThrow(new RuntimeException(exceptionMessage));

        // invoke
        try {
            AuthorisationResult res = bean.authoriseWithToken(TEST_GUID, paymentDetails,
                cardDetails);
            fail("Exception expected");
        } catch (RuntimeException expected) {
            assertEquals(exceptionMessage, expected.getMessage());
        }
        
        // verify
        verify(paymentProviderServiceProxy, times(1)).authoriseByToken(
                TEST_GUID, paymentDetails, cardDetails);
        verify(applicationEventPublisher).publishEvent(
                isA(AuthorisationErrorEvent.class));
    }
}
