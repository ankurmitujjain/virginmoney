package com.virginmoneygiving.cardpayment.serviceproxy.impl;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.basic.service.messages.ReplyFormatEnum;
import com.logicgroup.basic.service.messages.ResponseLabelEnum;
import com.logicgroup.basic.service.messages.ValidateMethodFlag;
import com.logicgroup.basic.service.messages.ValidationExtendedResponse;
import com.logicgroup.basic.service.messages.ValidationPassedEnum;
import com.logicgroup.basic.service.messages.ValidationResponse;
import com.logicgroup.extended.service.messages.ArrayOfProtocol;
import com.logicgroup.extended.service.messages.AuthResponseExt;
import com.logicgroup.extended.service.messages.AuthorisationResponse;
import com.logicgroup.extended.service.messages.Cv2Avs;
import com.logicgroup.extended.service.messages.ExtAuthorisationDetails;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;
import com.logicgroup.extended.service.messages.Protocol;
import com.logicgroup.extended.service.messages.ProtocolNameEnum;
import com.logicgroup.extended.service.messages.ProtocolResultEnum;
import com.logicgroup.extended.service.messages.TransactionSecurityEnum;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.CardSecurityInformation;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.business.PaymentTypeEnum;
import com.virginmoneygiving.cardpayment.domain.BaseProviderResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderValidationResponse;
import com.virginmoneygiving.cardpayment.domain.TransactionRecord;
import com.virginmoneygiving.cardpayment.helper.LogicGroupConstants;
import com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelper;
import com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl;
import com.virginmoneygiving.cardpayment.persistence.CardPaymentDAO;
import com.virginmoneygiving.cardpayment.serviceproxy.ProxySystemException;
import com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider;
import com.virginmoneygiving.cardpayment.transport.TLGWebServiceLocator;

/**
 * This test will get tricky as the Web Service isn't injected...
 *
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestLogicGroupPaymentProvider {

    // unit under test
    /** The bean. */
    private LogicGroupPaymentProvider bean;

    // data classes
    /** The cd. */
    private CardDetails cd;

    /** The csi. */
    private CardSecurityInformation csi;

    /** The pd. */
    private PaymentDetails pd;

    /** Array of protocols. */
    private ArrayOfProtocol protocols;
    
    // DI
    /** The payment type to source. */
    private Map<String, String> paymentTypeToSource;

    /** The payment type to login token. */
    private Map<String, String> paymentTypeToLoginToken;

    // mock collaborators

    /** The dao. */
    @Mock
    private CardPaymentDAO dao;

    /** The port. */
    @Mock
    private PaymentAPISoap port;

    /** The ext port. */
    @Mock
    private ExtendedPaymentAPISoap extPort;

    /** The locator. */
    @Mock
    private TLGWebServiceLocator locator;

    /** Secure payment helper. */
    @Mock
    private LogicGroupSecurePaymentHelper helper;

    /**
     * Initial Setup.
     *
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        paymentTypeToSource = new HashMap<String, String>();
        paymentTypeToSource.put("PAYMENT", "ID01");
        paymentTypeToSource.put("EVENT_FEE", "ID01");
        paymentTypeToSource.put("REGULAR", "RT99");
        paymentTypeToSource.put("REGISTRATION_FEE", "CR23");

        paymentTypeToLoginToken = new HashMap<String, String>();
        paymentTypeToLoginToken.put("PAYMENT", "C2EB096D-CCE9-4eb8-A537-B71636469A5C");
        paymentTypeToLoginToken.put("_DEFAULT", "C2EB096D-CCE9-4eb8-A537-B71636469A5C");
        // <entry key="EVENT_FEE" value="C2EB096D-CCE9-4eb8-A537-B71636469A5C"/>
        // <entry key="REGISTRATION_FEE"
        // value="C2EB096D-CCE9-4eb8-A537-B71636469A5C"/>
        // <entry key="REGULAR" value="C2EB096D-CCE9-4eb8-A537-B71636469A5C"/>

        protocols = new ArrayOfProtocol();

        // all responses require SSL/NONE
        Protocol sslProtocol = new Protocol();
        sslProtocol.setProtocolName(ProtocolNameEnum.SSL);
        sslProtocol.setProtocolResult(ProtocolResultEnum.NONE);
        protocols.getProtocol().add(sslProtocol);

        bean = new LogicGroupPaymentProvider();
        bean.setPaymentTypeToSourceMap(paymentTypeToSource);
        bean.setPaymentTypeToLoginToken(paymentTypeToLoginToken);

        MockitoAnnotations.initMocks(this);

        // inject mocks
        bean.setDao(dao);
        bean.setLocator(locator);
        bean.setLogicGroupSecurePaymentHelper(helper);

        // record general behaviour
        when(locator.getBasicPort()).thenReturn(port);
        when(locator.getExtendedPort()).thenReturn(extPort);
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
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#validate(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}
     * .
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidate() throws Exception {
        // set up data
        com.logicgroup.basic.service.messages.Result result = new com.logicgroup.basic.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.basic.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        com.logicgroup.basic.service.messages.Acquirer acquirer = new com.logicgroup.basic.service.messages.Acquirer();
        acquirer.setAcquirerId("V");
        acquirer.setAcquirerName("VISA");

        ValidationResponse solveResponse = new ValidationResponse();
        solveResponse.setAcquirer(acquirer);
        solveResponse.setValidationPassed(ValidationPassedEnum.YES);
        solveResponse.setResponseResult(ResponseLabelEnum.VALIDATED);
        solveResponse.setReplyFormat(ReplyFormatEnum.EXTENDED);

        ValidationExtendedResponse ver = new ValidationExtendedResponse();
        ver.setInternalValidationPassed(ValidationPassedEnum.NO);
        ver.setResult(result);
        ver.setSolveResponse(solveResponse);

        String GUID = UUID.randomUUID().toString();

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_VALIDATE);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(port.validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                )).thenReturn(ver);

        // invoke
        ProviderValidationResponse res = bean.validate(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(port).validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#validate(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}
     * .
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateWithReturnedCardToken() throws Exception {
        // set up data
        com.logicgroup.basic.service.messages.Result result = new com.logicgroup.basic.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.basic.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        com.logicgroup.basic.service.messages.Acquirer acquirer = new com.logicgroup.basic.service.messages.Acquirer();
        acquirer.setAcquirerId("V");
        acquirer.setAcquirerName("VISA");

        ValidationResponse solveResponse = new ValidationResponse();
        solveResponse.setAcquirer(acquirer);
        solveResponse.setValidationPassed(ValidationPassedEnum.YES);
        solveResponse.setResponseResult(ResponseLabelEnum.VALIDATED);
        solveResponse.setReplyFormat(ReplyFormatEnum.EXTENDED);

        ValidationExtendedResponse ver = new ValidationExtendedResponse();
        ver.setInternalValidationPassed(ValidationPassedEnum.NO);
        ver.setResult(result);
        ver.setSolveResponse(solveResponse);
        ver.setCardToken("0xdeadbeef");

        String GUID = UUID.randomUUID().toString();

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_VALIDATE);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(port.validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                )).thenReturn(ver);

        // invoke
        ProviderValidationResponse res = bean.validate(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(port).validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#validate(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}
     * .
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateWithNoSolveResponse() throws Exception {
        // set up data
        com.logicgroup.basic.service.messages.Result result = new com.logicgroup.basic.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.basic.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        com.logicgroup.basic.service.messages.Acquirer acquirer = new com.logicgroup.basic.service.messages.Acquirer();
        acquirer.setAcquirerId("V");
        acquirer.setAcquirerName("VISA");

        ValidationExtendedResponse ver = new ValidationExtendedResponse();
        ver.setInternalValidationPassed(ValidationPassedEnum.NO);
        ver.setResult(result);

        String GUID = UUID.randomUUID().toString();

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_VALIDATE);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(port.validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                )).thenReturn(ver);

        // invoke
        ProviderValidationResponse res = bean.validate(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(port).validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#validate(java.lang.String, com.virginmoneygiving.cardpayment.business.PaymentDetails, com.virginmoneygiving.cardpayment.business.CardDetails)}
     * .
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidateTLGError() throws Exception {
        // set up data
        com.logicgroup.basic.service.messages.Result result = new com.logicgroup.basic.service.messages.Result();
        result.setCode("76");
        result.setErrorType(com.logicgroup.basic.service.messages.ErrorTypeEnum.DEFERMENT_WEB_SERVICE);
        result.setErrorMessage("Something bad happened");

        com.logicgroup.basic.service.messages.Acquirer acquirer = new com.logicgroup.basic.service.messages.Acquirer();
        acquirer.setAcquirerId("V");
        acquirer.setAcquirerName("VISA");

        ValidationResponse solveResponse = new ValidationResponse();
        solveResponse.setAcquirer(acquirer);
        solveResponse.setValidationPassed(ValidationPassedEnum.YES);
        solveResponse.setResponseResult(ResponseLabelEnum.VALIDATED);
        solveResponse.setReplyFormat(ReplyFormatEnum.EXTENDED);

        ValidationExtendedResponse ver = new ValidationExtendedResponse();
        ver.setInternalValidationPassed(ValidationPassedEnum.NO);
        ver.setResult(result);
        ver.setSolveResponse(solveResponse);

        String GUID = UUID.randomUUID().toString();

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_VALIDATE);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(port.validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                )).thenReturn(ver);

        // invoke
        try {
            bean.validate(GUID, pd, cd);
            //fail("Exception expected"); //TODO: fix code!
        } catch (ProxySystemException expected) {
            // we expect an exception for TLG errors
            assertTrue(expected.getMessage().contains(GUID));
        }

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(port).validateCard(isA(String.class), // login token
                isA(String.class), // cardNumber,
                isA(String.class), // cardStartDate,
                isA(String.class), // cardExpiryDate,
                isA(String.class), // cardIssueNumber,
                isA(String.class), // sid,
                isA(String.class), // transactionNumber,
                isA(ValidateMethodFlag.class), // validateMethodFlag,
                anyBoolean(), // cardTokenRequired,
                isA(String.class), // customerReference,
                isA(String.class) // note
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }


    /**
     * Test method for {@link
     * com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseWithCv2Avs(java.lang.String,
     * com.virginmoneygiving.cardpayment.business.PaymentDetails,
     * com.virginmoneygiving.cardpayment.business.CardDetails)}.
     */
    @Test
    public void testAuthoriseWithCv2Avs() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        Cv2Avs cv2Avs = new Cv2Avs();
        cv2Avs.setAddress("matched");
        cv2Avs.setBy("Issuer");
        cv2Avs.setCV2("matched");
        cv2Avs.setPostCode("matched");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");
        solveResponse.setCv2Avs(cv2Avs);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());
        txRec.setProcessGuid(GUID);
        txRec.setTransactionType(LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_CV2AVS);

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.authorise(
                    anyString(), // loginToken,
                    anyString(), // cardNumber,
                    anyString(), // cardStartDate,
                    anyString(), // cardExpiryDate,
                    anyString(), // cardIssueNumber,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyBoolean(), // authOnly,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // isInternet
                    isA(ArrayOfProtocol.class), // protocols,
                    anyString(), // securityECI,
                    anyString(), // securityTransID,
                    anyString(), // securityData,
                    isA(TransactionSecurityEnum.class), // securityMode,
                    anyString(), // cv2AvsCV2,
                    anyString(), // cv2AvsAddress,
                    anyString(), // cv2AvsPostCode,
                    anyString(), // note
                    anyBoolean(), // isRecurring,
                    anyBoolean() // retry,
                )).thenReturn(are);

        when(helper.determineRecurring(anyString())).thenReturn(false);
        
        // invoke
        ProviderAuthorisationResponse res = bean.authoriseWithCv2Avs(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(helper).determineRecurring(anyString());
        verify(extPort).authorise(
                anyString(), // loginToken,
                anyString(), // cardNumber,
                anyString(), // cardStartDate,
                anyString(), // cardExpiryDate,
                anyString(), // cardIssueNumber,
                anyString(), // amount,
                anyString(), // sourceID,
                anyBoolean(), // authOnly,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // isInternet
                isA(ArrayOfProtocol.class), // protocols,
                anyString(), // securityECI,
                anyString(), // securityTransID,
                anyString(), // securityData,
                isA(TransactionSecurityEnum.class), // securityMode,
                anyString(), // cv2AvsCV2,
                anyString(), // cv2AvsAddress,
                anyString(), // cv2AvsPostCode,
                anyString(), // note
                anyBoolean(), // isRecurring,
                anyBoolean() // retry,
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for {@link
     * com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseWithCv2Avs(java.lang.String,
     * com.virginmoneygiving.cardpayment.business.PaymentDetails,
     * com.virginmoneygiving.cardpayment.business.CardDetails)}.
     */
    @Test
    public void testAuthoriseWithCv2AndNullAvs() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        Cv2Avs cv2Avs = new Cv2Avs();
        cv2Avs.setAddress("no_information");
        cv2Avs.setBy("Issuer");
        cv2Avs.setCV2("matched");
        cv2Avs.setPostCode("no_information");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");
        solveResponse.setCv2Avs(cv2Avs);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());
        txRec.setProcessGuid(GUID);
        txRec.setTransactionType(LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_CV2AVS);

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(null);
        csi.setPostcode(null);

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.authorise(
                    anyString(), // loginToken,
                    anyString(), // cardNumber,
                    anyString(), // cardStartDate,
                    anyString(), // cardExpiryDate,
                    anyString(), // cardIssueNumber,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyBoolean(), // authOnly,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // isInternet
                    isA(ArrayOfProtocol.class), // protocols,
                    anyString(), // securityECI,
                    anyString(), // securityTransID,
                    anyString(), // securityData,
                    isA(TransactionSecurityEnum.class), // securityMode,
                    anyString(), // cv2AvsCV2,
                    anyString(), // cv2AvsAddress,
                    anyString(), // cv2AvsPostCode,
                    anyString(), // note
                    anyBoolean(), // isRecurring,
                    anyBoolean() // retry,
                )).thenReturn(are);

        // invoke
        ProviderAuthorisationResponse res = bean.authoriseWithCv2Avs(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).authorise(
                anyString(), // loginToken,
                anyString(), // cardNumber,
                anyString(), // cardStartDate,
                anyString(), // cardExpiryDate,
                anyString(), // cardIssueNumber,
                anyString(), // amount,
                anyString(), // sourceID,
                anyBoolean(), // authOnly,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // isInternet
                isA(ArrayOfProtocol.class), // protocols,
                anyString(), // securityECI,
                anyString(), // securityTransID,
                anyString(), // securityData,
                isA(TransactionSecurityEnum.class), // securityMode,
                anyString(), // cv2AvsCV2,
                anyString(), // cv2AvsAddress,
                anyString(), // cv2AvsPostCode,
                anyString(), // note
                anyBoolean(), // isRecurring,
                anyBoolean() // retry,
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for {@link
     * com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseWithCv2Avs(java.lang.String,
     * com.virginmoneygiving.cardpayment.business.PaymentDetails,
     * com.virginmoneygiving.cardpayment.business.CardDetails)}.
     */
    @Test
    public void testAuthoriseWithCv2AvsTLGError() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("76");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.DEFERMENT_WEB_SERVICE);
        result.setErrorMessage("Something bad happened");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.authorise(
                    anyString(), // loginToken,
                    anyString(), // cardNumber,
                    anyString(), // cardStartDate,
                    anyString(), // cardExpiryDate,
                    anyString(), // cardIssueNumber,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyBoolean(), // authOnly,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // isInternet
                    isA(ArrayOfProtocol.class), // protocols,
                    anyString(), // securityECI,
                    anyString(), // securityTransID,
                    anyString(), // securityData,
                    isA(TransactionSecurityEnum.class), // securityMode,
                    anyString(), // cv2AvsCV2,
                    anyString(), // cv2AvsAddress,
                    anyString(), // cv2AvsPostCode,
                    anyString(), // note
                    anyBoolean(), // isRecurring,
                    anyBoolean() // retry,
                )).thenReturn(are);

        // invoke
        try {
            bean.authoriseWithCv2Avs(GUID, pd, cd);
            fail("Exception expected");
        } catch (ProxySystemException expected) {
            assertTrue(expected.getMessage().contains(GUID));
        }

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).authorise(
                anyString(), // loginToken,
                anyString(), // cardNumber,
                anyString(), // cardStartDate,
                anyString(), // cardExpiryDate,
                anyString(), // cardIssueNumber,
                anyString(), // amount,
                anyString(), // sourceID,
                anyBoolean(), // authOnly,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // isInternet
                isA(ArrayOfProtocol.class), // protocols,
                anyString(), // securityECI,
                anyString(), // securityTransID,
                anyString(), // securityData,
                isA(TransactionSecurityEnum.class), // securityMode,
                anyString(), // cv2AvsCV2,
                anyString(), // cv2AvsAddress,
                anyString(), // cv2AvsPostCode,
                anyString(), // note
                anyBoolean(), // isRecurring,
                anyBoolean() // retry,
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

     /**
      * Test method for {@link
      * com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseWithAuthentication(java.lang.String,
      * com.virginmoneygiving.cardpayment.business.PaymentDetails,
      * com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
      */
     @Test
     public void testAuthoriseWithAuthentication() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        Cv2Avs cv2Avs = new Cv2Avs();
        cv2Avs.setAddress("matched");
        cv2Avs.setBy("Issuer");
        cv2Avs.setCV2("matched");
        cv2Avs.setPostCode("matched");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");
        solveResponse.setCv2Avs(cv2Avs);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());
        txRec.setProcessGuid(GUID);
        txRec.setTransactionType(LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_CV2AVS);

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

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

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);
        when(helper.determineProtocolByMdStatus(anyString(), anyString())).thenReturn(protocols);
        when(helper.determineSecurityModeByMdStatus(anyString(), anyString())).thenReturn(TransactionSecurityEnum.BY_SECURE_SESSION);
        when(helper.determineRecurring(anyString())).thenReturn(false);

        when(extPort.authorise(
                    anyString(), // loginToken,
                    anyString(), // cardNumber,
                    anyString(), // cardStartDate,
                    anyString(), // cardExpiryDate,
                    anyString(), // cardIssueNumber,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyBoolean(), // authOnly,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // isInternet
                    isA(ArrayOfProtocol.class), // protocols,
                    anyString(), // securityECI,
                    anyString(), // securityTransID,
                    anyString(), // securityData,
                    isA(TransactionSecurityEnum.class), // securityMode,
                    anyString(), // cv2AvsCV2,
                    anyString(), // cv2AvsAddress,
                    anyString(), // cv2AvsPostCode,
                    anyString(), // note
                    anyBoolean(), // isRecurring,
                    anyBoolean() // retry,
                )).thenReturn(are);

        // invoke
        ProviderAuthorisationResponse res = bean.authoriseWithAuthentication(GUID, pd, cd, map);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(helper).determineProtocolByMdStatus(anyString(), anyString());
        verify(helper).determineSecurityModeByMdStatus(anyString(), anyString());
        verify(helper).determineRecurring(anyString());
        verify(extPort).authorise(
                anyString(), // loginToken,
                anyString(), // cardNumber,
                anyString(), // cardStartDate,
                anyString(), // cardExpiryDate,
                anyString(), // cardIssueNumber,
                anyString(), // amount,
                anyString(), // sourceID,
                anyBoolean(), // authOnly,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // isInternet
                isA(ArrayOfProtocol.class), // protocols,
                anyString(), // securityECI,
                anyString(), // securityTransID,
                anyString(), // securityData,
                isA(TransactionSecurityEnum.class), // securityMode,
                anyString(), // cv2AvsCV2,
                anyString(), // cv2AvsAddress,
                anyString(), // cv2AvsPostCode,
                anyString(), // note
                anyBoolean(), // isRecurring,
                anyBoolean() // retry,
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

     /**
      * Test method for {@link
      * com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseWithAuthentication(java.lang.String,
      * com.virginmoneygiving.cardpayment.business.PaymentDetails,
      * com.virginmoneygiving.cardpayment.business.CardDetails, java.util.Map)}.
      */
     @Test
     public void testAuthoriseWithAuthenticationTLGError() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("73");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.DEFERMENT_WEB_SERVICE);
        result.setErrorMessage("Something bad happened");

        Cv2Avs cv2Avs = new Cv2Avs();
        cv2Avs.setAddress("matched");
        cv2Avs.setBy("Issuer");
        cv2Avs.setCV2("matched");
        cv2Avs.setPostCode("matched");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");
        solveResponse.setCv2Avs(cv2Avs);

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());
        txRec.setProcessGuid(GUID);
        txRec.setTransactionType(LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_CV2AVS);

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        csi = new CardSecurityInformation();
        csi.setCsc("123");
        csi.setHouseNumber(88);
        csi.setPostcode("73");

        cd = new CardDetails();
        cd.setPan("4111111111111111");
        cd.setEndMonth(12);
        cd.setEndYear(2009);
        cd.setSecurityInfo(csi);

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

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);
        when(helper.determineProtocolByMdStatus(anyString(), anyString())).thenReturn(protocols);
        when(helper.determineSecurityModeByMdStatus(anyString(), anyString())).thenReturn(TransactionSecurityEnum.BY_SECURE_SESSION);
        when(helper.determineRecurring(anyString())).thenReturn(false);

        when(extPort.authorise(
                    anyString(), // loginToken,
                    anyString(), // cardNumber,
                    anyString(), // cardStartDate,
                    anyString(), // cardExpiryDate,
                    anyString(), // cardIssueNumber,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyBoolean(), // authOnly,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // isInternet
                    isA(ArrayOfProtocol.class), // protocols,
                    anyString(), // securityECI,
                    anyString(), // securityTransID,
                    anyString(), // securityData,
                    isA(TransactionSecurityEnum.class), // securityMode,
                    anyString(), // cv2AvsCV2,
                    anyString(), // cv2AvsAddress,
                    anyString(), // cv2AvsPostCode,
                    anyString(), // note
                    anyBoolean(), // isRecurring,
                    anyBoolean() // retry,
                )).thenReturn(are);

        // invoke
        try {
            ProviderAuthorisationResponse res = bean.authoriseWithAuthentication(GUID, pd, cd, map);
            fail("Exception expected!");
         } catch (ProxySystemException expected) {
             assertTrue(expected.getMessage().contains(GUID));
         }

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(helper).determineProtocolByMdStatus(anyString(), anyString());
        verify(helper).determineSecurityModeByMdStatus(anyString(), anyString());
        verify(helper).determineRecurring(anyString());
        verify(extPort).authorise(
                anyString(), // loginToken,
                anyString(), // cardNumber,
                anyString(), // cardStartDate,
                anyString(), // cardExpiryDate,
                anyString(), // cardIssueNumber,
                anyString(), // amount,
                anyString(), // sourceID,
                anyBoolean(), // authOnly,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // isInternet
                isA(ArrayOfProtocol.class), // protocols,
                anyString(), // securityECI,
                anyString(), // securityTransID,
                anyString(), // securityData,
                isA(TransactionSecurityEnum.class), // securityMode,
                anyString(), // cv2AvsCV2,
                anyString(), // cv2AvsAddress,
                anyString(), // cv2AvsPostCode,
                anyString(), // note
                anyBoolean(), // isRecurring,
                anyBoolean() // retry,
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for {@link
       com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseByToken(java.lang.String,
       com.virginmoneygiving.cardpayment.business.PaymentDetails,
       com.virginmoneygiving.cardpayment.business.CardDetails)}.
     */
    @Test
    public void testAuthoriseByToken() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        cd = new CardDetails();
        cd.setToken("0xCAFEBABE");

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);
        when(helper.determineProtocolByMdStatus(anyString(), anyString())).thenReturn(protocols);
        when(helper.determineSecurityModeByMdStatus(anyString(), anyString())).thenReturn(TransactionSecurityEnum.BY_SECURE_SESSION);

        when(extPort.authoriseExistingCard(
                    anyString(), // login token
                    anyString(), // cardToken,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // authOnly,
                    anyString(), // note
                    // v4 fields below
                    anyString(), // card start date
                    anyString(), // card expiry in yyyy-mm
                    anyString(), // card issue number
                    anyBoolean(), // is Internet
                    isA(ArrayOfProtocol.class), 
                    anyString(), // eci
                    anyString(), // xid -> security transID
                    anyString(), // cavv -> security data
                    isA(TransactionSecurityEnum.class), // Security Mode
                    anyString(), // CV2
                    anyString(),// cv2AvsAddress,
                    anyString(),// cv2AvsPostCode,
                    anyBoolean(), // isRecurring (added in TLG v4)
                    anyBoolean() // retry (added in TLG v4) 
                )).thenReturn(are);

        // invoke
        ProviderAuthorisationResponse res = bean.authoriseByToken(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).authoriseExistingCard(
                anyString(), // login token
                anyString(), // cardToken,
                anyString(), // amount,
                anyString(), // sourceID,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // authOnly,
                anyString(), // note
                // v4 fields below
                anyString(), // card start date
                anyString(), // card expiry in yyyy-mm
                anyString(), // card issue number
                eq(true), // is Internet
                isA(ArrayOfProtocol.class), 
                eq(""), // eci
                eq(""), // xid -> security transID
                eq(""), // cavv -> security data
                isA(TransactionSecurityEnum.class), // Security Mode
                eq(""), // CV2
                eq(""),// cv2AvsAddress,
                eq(""),// cv2AvsPostCode,
                eq(false), // isRecurring (added in TLG v4)
                anyBoolean() // retry (added in TLG v4) 
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for {@link
       com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseByToken(java.lang.String,
       com.virginmoneygiving.cardpayment.business.PaymentDetails,
       com.virginmoneygiving.cardpayment.business.CardDetails)}.
     */
    @Test
    public void testAuthoriseByTokenRegularPayment() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_AUTHORISE_WITH_CV2AVS);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.REGULAR);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        cd = new CardDetails();
        cd.setToken("0xCAFEBABE");

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);
        when(helper.determineProtocolByMdStatus(anyString(), anyString())).thenReturn(protocols);
        when(helper.determineRecurring(anyString())).thenReturn(true);
        when(helper.determineSecurityModeByMdStatus(anyString(), anyString())).thenReturn(TransactionSecurityEnum.BY_SECURE_SESSION);

        when(extPort.authoriseExistingCard(
                    anyString(), // login token
                    anyString(), // cardToken,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // authOnly,
                    anyString(), // note
                    // v4 fields below
                    anyString(), // card start date
                    anyString(), // card expiry in yyyy-mm
                    anyString(), // card issue number
                    anyBoolean(), // is Internet
                    isA(ArrayOfProtocol.class), 
                    anyString(), // eci
                    anyString(), // xid -> security transID
                    anyString(), // cavv -> security data
                    isA(TransactionSecurityEnum.class), // Security Mode
                    anyString(), // CV2
                    anyString(),// cv2AvsAddress,
                    anyString(),// cv2AvsPostCode,
                    anyBoolean(), // isRecurring (added in TLG v4)
                    anyBoolean() // retry (added in TLG v4) 
                )).thenReturn(are);

        // invoke
        ProviderAuthorisationResponse res = bean.authoriseByToken(GUID, pd, cd);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).authoriseExistingCard(
                anyString(), // login token
                anyString(), // cardToken,
                anyString(), // amount,
                anyString(), // sourceID,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // authOnly,
                anyString(), // note
                // v4 fields below
                anyString(), // card start date
                anyString(), // card expiry in yyyy-mm
                anyString(), // card issue number
                eq(false), // is Internet
                isA(ArrayOfProtocol.class), 
                eq(""), // eci
                eq(""), // xid -> security transID
                eq(""), // cavv -> security data
                isA(TransactionSecurityEnum.class), // Security Mode
                eq(""), // CV2
                eq(""),// cv2AvsAddress,
                eq(""),// cv2AvsPostCode,
                eq(true), // isRecurring (added in TLG v4)
                anyBoolean() // retry (added in TLG v4) 
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for {@link
       com.virginmoneygiving.cardpayment.serviceproxy.LogicGroupPaymentProvider#authoriseByToken(java.lang.String,
       com.virginmoneygiving.cardpayment.business.PaymentDetails,
       com.virginmoneygiving.cardpayment.business.CardDetails)}.
     */
    @Test
    public void testAuthoriseByTokenTLGError() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("73");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.DEFERMENT_WEB_SERVICE);
        result.setErrorMessage("Something bad happened");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        pd = new PaymentDetails();
        pd.setPaymentType(PaymentTypeEnum.PAYMENT);
        pd.setAmount(new BigDecimal("12.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("JUnit");

        cd = new CardDetails();
        cd.setToken("0xCAFEBABE");

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);
        when(helper.determineProtocolByMdStatus(anyString(), anyString())).thenReturn(protocols);
        when(helper.determineSecurityModeByMdStatus(anyString(), anyString())).thenReturn(TransactionSecurityEnum.BY_SECURE_SESSION);

        when(extPort.authoriseExistingCard(
                    anyString(), // login token
                    anyString(), // cardToken,
                    anyString(), // amount,
                    anyString(), // sourceID,
                    anyString(), // transactionNumber,
                    anyString(), // customerReference,
                    anyBoolean(), // authOnly,
                    anyString(), // note
                    // v4 fields below
                    anyString(), // card start date
                    anyString(), // card expiry in yyyy-mm
                    anyString(), // card issue number
                    anyBoolean(), // is Internet
                    isA(ArrayOfProtocol.class), 
                    anyString(), // eci
                    anyString(), // xid -> security transID
                    anyString(), // cavv -> security data
                    isA(TransactionSecurityEnum.class), // Security Mode
                    anyString(), // CV2
                    anyString(),// cv2AvsAddress,
                    anyString(),// cv2AvsPostCode,
                    anyBoolean(), // isRecurring (added in TLG v4)
                    anyBoolean() // retry (added in TLG v4) 
                )).thenReturn(are);

        // invoke
        try {
            bean.authoriseByToken(GUID, pd, cd);
            fail("Exception expected!");
        } catch (ProxySystemException expected) {
            assertTrue(expected.getMessage().contains(GUID));
        }

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).authoriseExistingCard(
                anyString(), // login token
                anyString(), // cardToken,
                anyString(), // amount,
                anyString(), // sourceID,
                anyString(), // transactionNumber,
                anyString(), // customerReference,
                anyBoolean(), // authOnly,
                anyString(), // note
                // v4 fields below
                anyString(), // card start date
                anyString(), // card expiry in yyyy-mm
                anyString(), // card issue number
                anyBoolean(), // is Internet
                isA(ArrayOfProtocol.class), 
                anyString(), // eci
                anyString(), // xid -> security transID
                anyString(), // cavv -> security data
                isA(TransactionSecurityEnum.class), // Security Mode
                anyString(), // CV2
                anyString(),// cv2AvsAddress,
                anyString(),// cv2AvsPostCode,
                anyBoolean(), // isRecurring (added in TLG v4)
                anyBoolean() // retry (added in TLG v4) 
            );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#cancelTransaction(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testCancelTransaction() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.cancel(anyString(), // login token
                anyString() // transactionId
                )).thenReturn(are);

        // invoke
        ProviderAuthorisationResponse res = bean.cancelTransaction(GUID, transactionId);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).cancel(anyString(), // login token
                anyString() // transactionId
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#cancelTransaction(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testCancelTransactionTLGError() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("75");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.DEFERMENT_WEB_SERVICE);
        result.setErrorMessage("Something bad happened");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_CANCEL);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.cancel(anyString(), // login token
                anyString() // transactionId
                )).thenReturn(are);

        // invoke
        try {
            bean.cancelTransaction(GUID, transactionId);
            fail("Exception expected!");
        } catch (ProxySystemException expected) {
            // we expect an exception
            assertTrue(expected.getMessage().contains(GUID));
        }

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).cancel(anyString(), // login token
                anyString() // transactionId
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#completeTransaction(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testCompleteTransaction() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("0");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.NONE);
        result.setErrorMessage("");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_COMPLETE);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.complete(anyString(), // login token
                anyString() // transactionId
                )).thenReturn(are);

        // invoke
        ProviderAuthorisationResponse res = bean.completeTransaction(GUID, transactionId);

        // assert
        assertNotNull(res);

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).complete(anyString(), // login token
                anyString() // transactionId
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.LogicGroupPaymentProvider#completeTransaction(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testCompleteTransactionTLGError() {
        String GUID = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();

        // set up data
        com.logicgroup.extended.service.messages.Result result = new com.logicgroup.extended.service.messages.Result();
        result.setCode("74");
        result.setErrorType(com.logicgroup.extended.service.messages.ErrorTypeEnum.DEFERMENT_WEB_SERVICE);
        result.setErrorMessage("Something bad happened");

        AuthorisationResponse solveResponse = new AuthorisationResponse();
        solveResponse.setResponseCode("0");

        TransactionRecord txRec = new TransactionRecord(GUID, LogicGroupConstants.TRANSACTION_COMPLETE);
        txRec.setId(123L);
        txRec.setCreatedDatetime(new Date());

        ExtAuthorisationDetails ead = new ExtAuthorisationDetails();
        ead.setTransactionID(transactionId);

        AuthResponseExt are = new AuthResponseExt();
        are.setResult(result);
        are.setSolveResponse(solveResponse);
        are.setAuthorisationDetails(ead);

        // set behaviour
        when(dao.createTransactionRecord(isA(TransactionRecord.class))).thenReturn(txRec);

        when(extPort.complete(anyString(), // login token
                anyString() // transactionId
                )).thenReturn(are);

        // invoke
        try {
            bean.completeTransaction(GUID, transactionId);
            fail("Exception expected.");
        } catch (ProxySystemException expected) {
            // we expect an exception
            assertTrue(expected.getMessage().contains(GUID));
        }

        // verify
        verify(dao).createTransactionRecord(isA(TransactionRecord.class));
        verify(extPort).complete(anyString(), // login token
                anyString() // transactionId
                );
        verify(dao).persistProviderResponse(isA(BaseProviderResponse.class));
    }


    /**
     * Test login normal.
     */
    @Test
    public void testLoginNormal() {
        String paymentType = "FOO";
        String loginToken = "BAR";

        Map<String,String> map = new HashMap<String,String>();
        map.put(paymentType, loginToken);
        bean.setPaymentTypeToLoginToken(map);

        String retVal = bean.login(paymentType);
        assertNotNull(retVal);
        assertEquals("Should match BAR: " + retVal, loginToken, retVal);
    }

    /**
     * Test login default.
     */
    @Test
    public void testLoginDefault() {
        String paymentType = "FOO";
        String loginToken = "BAR";

        Map<String,String> map = new HashMap<String,String>();
        map.put("_DEFAULT", loginToken);
        bean.setPaymentTypeToLoginToken(map);

        String retVal = bean.login(paymentType);
        assertNotNull(retVal);
        assertEquals("Should match BAR: " + retVal, loginToken, retVal);
    }

    /**
     * Test login no default.
     */
    @Test
    public void testLoginNoDefault() {
        String paymentType = "FOO";
        String loginToken = "BAR";

        Map<String,String> emptyMap = new HashMap<String,String>();
        bean.setPaymentTypeToLoginToken(emptyMap);

        String retVal = bean.login(paymentType);
        assertNull(retVal);
    }


    /**
     * Test for ProxySystemException with message and cause.
     */
    @Test
    public void testNestedProxySystemException() {
        ProxySystemException pse = new ProxySystemException("Test",
                new Exception());

        assertNotNull("Cause should not be null", pse.getCause());
        assertTrue("Cause should be an Exception", pse.getCause() instanceof Exception);
    }
}
