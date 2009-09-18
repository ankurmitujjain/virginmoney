package com.virginmoneygiving.cardpayment.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.business.PaymentTypeEnum;

/**
 * Unit test for the LogicGroup 3D Secure Helper implementation.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestLogicGroup3DSecureHelper {

    // class under test
    /** The bean. */
    private LogicGroup3DSecureHelper bean;

    // test constants
    /** The Constant BAD_URL. */
    private static final String BAD_URL = "http://badUrl.com/";
    
    /** The Constant GOOD_URL. */
    private static final String GOOD_URL = "http://goodUrl.com/";
    
    /** The Constant TEST_PAN. */
    private static final String TEST_PAN = "4111111111111111";
    
    /** The Constant MERCHANT_ID. */
    private static final String MERCHANT_ID = "056";

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new LogicGroup3DSecureHelper();
        bean.setDigestAlgorithm("SHA-1");
        bean.setSaltAlgorithm("SHA1PRNG");
        bean.setSharedSecret("virgin");
        bean.setMerchantId(MERCHANT_ID);

        Map<String, String> currencyMap = new HashMap<String, String>();
        currencyMap.put("GBP", "826");
        bean.setCurrencyCodeMappings(currencyMap);

        Map<String, String> badMap = new HashMap<String,String>();
        badMap.put("EVENT_FEE", BAD_URL);
        badMap.put("PAYMENT", BAD_URL);

        Map<String, String> goodMap = new HashMap<String,String>();
        goodMap.put("EVENT_FEE", GOOD_URL);
        goodMap.put("PAYMENT", GOOD_URL);
        
        bean.setBadUrlsByPaymentType(badMap);
        bean.setGoodUrlsByPaymentType(goodMap);
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
     * {@link com.virginmoneygiving.cardpayment.helper.LogicGroup3DSecureHelper#buildFormVariables(com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails, com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testBuildFormVariables() throws Exception {
        // setup test data
        CardDetails cardDetails = new CardDetails();
        cardDetails.setPan(TEST_PAN);
        cardDetails.setEndMonth(9);
        cardDetails.setEndYear(2009);

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setCurrencyCode("GBP");
        paymentDetails.setAmount(new BigDecimal("10.00"));
        paymentDetails.setPaymentType(PaymentTypeEnum.PAYMENT);

        String guid = UUID.randomUUID().toString();

        // method invocation
        Map<String, String> map = bean.buildFormVariables(guid, cardDetails, paymentDetails);

        // assertions
        assertNotNull(map);
        assertEquals(LogicGroupConstants.HTTP_VERSION_VALUE, map.get(LogicGroupConstants.HTTP_VERSION_KEY));
        assertEquals(LogicGroupConstants.DEVICE_CATEGORY_VALUE, map.get(LogicGroupConstants.DEVICE_CATEGORY_KEY));
        assertEquals(28, map.get(LogicGroupConstants.XID_KEY).length()); // this
                                                                         // is
                                                                         // random
        assertEquals("826", map.get(LogicGroupConstants.CURRENCY_KEY));
        assertEquals(GOOD_URL, map.get(LogicGroupConstants.OK_URL_KEY));
        assertEquals(BAD_URL, map.get(LogicGroupConstants.FAIL_URL_KEY));
        assertEquals("1000", map.get(LogicGroupConstants.AMOUNT_KEY));
        assertEquals("2", map.get(LogicGroupConstants.EXPONENT_KEY));
        assertEquals("0909", map.get(LogicGroupConstants.EXPIRY_KEY));
        assertEquals(TEST_PAN, map.get(LogicGroupConstants.PAN_KEY));
        assertEquals(guid, map.get(LogicGroupConstants.MERCHANT_DATA_KEY));

        // these blank values are likely to change in future
        assertEquals("", map.get(LogicGroupConstants.CARD_TYPE_KEY));
        assertEquals("", map.get(LogicGroupConstants.DESCRIPTION_KEY));

        assertNotNull("Merchant ID must not be null", map.get(LogicGroupConstants.MERCHANT_ID_KEY));
        assertEquals(MERCHANT_ID, map.get(LogicGroupConstants.MERCHANT_ID_KEY));

        // we can't use a pre-canned value to compare due to XID
        assertNotNull("Digest must not be null", map.get(LogicGroupConstants.DIGEST_KEY));
    }

    /**
     * Test verify authentication response digest.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testVerifyAuthenticationResponseDigest() throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        /*
         * real example response collected from the prototype... version: 2.0
         * mdStatus: 1 xid: YmZG/JMDN3VLrYKNt8R9uE5dD3A= vendorCode: txstatus: Y
         * mdErrorMsg: Authenticated eci: 05 merchantID: 056 sID: 1 digest:
         * LHb9BjyZFtSbXe11dAfST3ARvnM= opalErrorCode: 0 md: MD: cavv:
         * AAABA5QRhQAAAAABEBGFAAAAAAA= cavvAlgorithm: 2 PAResSyntaxOK: true
         * PAResVerified: true iReqCode: iReqDetail:
         */
        // setup test data
        map.put(LogicGroupConstants.HTTP_VERSION_KEY, "2.0");
        map.put(LogicGroupConstants.MERCHANT_ID_KEY, MERCHANT_ID);
        map.put(LogicGroupConstants.XID_KEY, "YmZG/JMDN3VLrYKNt8R9uE5dD3A=");
        map.put(LogicGroupConstants.RES_MD_STATUS_KEY, "1");
        map.put(LogicGroupConstants.RES_MD_ERROR_MSG_KEY, "Authenticated");
        map.put(LogicGroupConstants.RES_TX_STATUS_KEY, "Y");
        map.put(LogicGroupConstants.RES_IREQ_CODE_KEY, "");
        map.put(LogicGroupConstants.RES_IREQ_DETAIL_KEY, "");
        map.put(LogicGroupConstants.RES_VENDOR_CODE_KEY, "");
        map.put(LogicGroupConstants.RES_ECI_KEY, "05");
        map.put(LogicGroupConstants.RES_CAVV_KEY, "AAABA5QRhQAAAAABEBGFAAAAAAA=");
        map.put(LogicGroupConstants.RES_CAVV_ALG_KEY, "2");
        map.put(LogicGroupConstants.MERCHANT_DATA_KEY, "");
        map.put(LogicGroupConstants.DIGEST_KEY, "LHb9BjyZFtSbXe11dAfST3ARvnM=");

        // method invocation
        boolean response = bean.verifyAuthenticationResponseDigest(map);

        // assertions
        assertTrue(response);
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.LogicGroup3DSecureHelper#digest(java.lang.String)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testDigest() throws Exception {
        String digest = bean.digest("TEST STRING");
        assertNotNull("digest must not be null", digest);
        assertEquals("050AnAV5epOnlyCVLpnHBUok58Q=", digest); // test value
                                                              // generated using
                                                              // a Groovy script
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.LogicGroup3DSecureHelper#generateXid()}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testGenerateXid() throws Exception {
        String xid = bean.generateXid();
        assertNotNull("xid must not be null", xid);
        assertEquals(28, xid.length());
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.LogicGroup3DSecureHelper#formatExpiry(int, int)}
     * .
     */
    @Test
    public void testFormatExpiry() {
        String dateRepresentation = bean.formatExpiry(10, 2009);
        assertNotNull("Date representation must not be null", dateRepresentation);
        assertEquals("0910", dateRepresentation);
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.helper.LogicGroup3DSecureHelper#formatExpiry(int, int)}
     * .
     */
    @Test
    public void testFormatExpiryMonthPadding() {
        String dateRepresentation = bean.formatExpiry(2, 2009);
        assertNotNull("Date representation must not be null", dateRepresentation);
        assertEquals("0902", dateRepresentation);
    }
}
