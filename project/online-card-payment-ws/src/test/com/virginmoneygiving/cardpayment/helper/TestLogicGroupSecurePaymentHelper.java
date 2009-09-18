package com.virginmoneygiving.cardpayment.helper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.logicgroup.extended.service.messages.ArrayOfProtocol;
import com.logicgroup.extended.service.messages.ProtocolNameEnum;
import com.logicgroup.extended.service.messages.ProtocolResultEnum;
import com.logicgroup.extended.service.messages.TransactionSecurityEnum;
import com.virginmoneygiving.cardpayment.business.PaymentTypeEnum;

/**
 * The Class TestLogicGroupSecurePaymentHelper.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestLogicGroupSecurePaymentHelper {

    /** Class under test. */
    LogicGroupSecurePaymentHelperImpl bean;
    
    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new LogicGroupSecurePaymentHelperImpl();
        
        // set up data
        Map<String,TransactionSecurityEnum> mdStatusToSecurityModeMap = 
            new HashMap<String,TransactionSecurityEnum>();
        mdStatusToSecurityModeMap.put("1", TransactionSecurityEnum.BY_CARDHOLDER_CERTIFICATE);
        mdStatusToSecurityModeMap.put("2", TransactionSecurityEnum.BY_MERCHANT_CERTIFICATE);
        mdStatusToSecurityModeMap.put("4", TransactionSecurityEnum.BY_MERCHANT_CERTIFICATE);
        mdStatusToSecurityModeMap.put("_DEFAULT", TransactionSecurityEnum.NONE);
        bean.setMdStatusToSecurityModeMap(mdStatusToSecurityModeMap);

        Map<String,ProtocolNameEnum> schemeIdToProtocolNameMap = 
            new HashMap<String,ProtocolNameEnum>();
        schemeIdToProtocolNameMap.put("1", ProtocolNameEnum.THREE_D_SECURE);
        schemeIdToProtocolNameMap.put("2", ProtocolNameEnum.U_CAF);
        bean.setSchemeIdToProtocolNameMap(schemeIdToProtocolNameMap);
        
        Map<String,ProtocolResultEnum> mdStatusToProtocolResultMap = 
            new HashMap<String,ProtocolResultEnum>();
        mdStatusToProtocolResultMap.put("1", ProtocolResultEnum.SUCCESSFUL);
        mdStatusToProtocolResultMap.put("2", ProtocolResultEnum.UNABLE_TO_VERIFY);
        mdStatusToProtocolResultMap.put("4", ProtocolResultEnum.ATTEMPTED);
        mdStatusToProtocolResultMap.put("_DEFAULT", ProtocolResultEnum.NONE);
        bean.setMdStatusToProtocolResultMap(mdStatusToProtocolResultMap);

        Set<String> recurringPaymentTypes = new HashSet<String>();
        recurringPaymentTypes.add("REGULAR");
        bean.setRecurringPaymentTypes(recurringPaymentTypes);
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
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#flattenResponse(com.logicgroup.extended.service.messages.AuthResponseExt)}.
     */
    @Test
    public void testFlattenResponse() {
        //fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#flattenValidationResponse(com.logicgroup.basic.service.messages.ValidationExtendedResponse)}.
     */
    @Test
    public void testFlattenValidationResponse() {
        //fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#formatDate(java.lang.Integer, java.lang.Integer)}.
     */
    @Test
    public void testFormatDate() {
        //fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#formatIssueNumber(java.lang.Integer)}.
     */
    @Test
    public void testFormatIssueNumber() {
        //fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#determineProtocolByMdStatus(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDetermineProtocolByMdStatus() {
        ArrayOfProtocol a11 = bean
            .determineProtocolByMdStatus("1", "1");
        assertNotNull(a11);
        // TODO helper method to complete assertions SSL/NONE 3DSecure/successful
        
        ArrayOfProtocol a12 = bean
            .determineProtocolByMdStatus("1", "2");
        assertNotNull(a12);

        ArrayOfProtocol a14 = bean
            .determineProtocolByMdStatus("1", "4");
        assertNotNull(a14);

        ArrayOfProtocol a15 = bean
            .determineProtocolByMdStatus("1", "5");
        assertNotNull(a15);

        ArrayOfProtocol a21 = bean
            .determineProtocolByMdStatus("2", "1");
        assertNotNull(a21);
        // TODO helper method to complete assertions SSL/NONE 3DSecure/successful
        
        ArrayOfProtocol a22 = bean
            .determineProtocolByMdStatus("2", "2");
        assertNotNull(a22);
    
        ArrayOfProtocol a24 = bean
            .determineProtocolByMdStatus("2", "4");
        assertNotNull(a24);
    
        ArrayOfProtocol a25 = bean
            .determineProtocolByMdStatus("2", "5");
        assertNotNull(a25);

        // unknown scheme
        ArrayOfProtocol a31 = bean
            .determineProtocolByMdStatus("3", "1");
        assertNotNull(a31);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#determineSecurityModeByMdStatus(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDetermineSecurityModeByMdStatus() {
        assertEquals(TransactionSecurityEnum.BY_CARDHOLDER_CERTIFICATE, 
                bean.determineSecurityModeByMdStatus(
                        "1", "1"));
        assertEquals(TransactionSecurityEnum.BY_MERCHANT_CERTIFICATE, 
                bean.determineSecurityModeByMdStatus(
                        "1", "2"));
        assertEquals(TransactionSecurityEnum.BY_MERCHANT_CERTIFICATE, 
                bean.determineSecurityModeByMdStatus(
                        "1", "4"));
        assertEquals(TransactionSecurityEnum.NONE, 
                bean.determineSecurityModeByMdStatus(
                        "1", "5"));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#translateResponse(com.logicgroup.extended.service.messages.AuthResponseExt)}.
     */
    @Test
    public void testTranslateResponse() {
        //fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#determineRecurring(com.virginmoneygiving.cardpayment.business.PaymentTypeEnum)}.
     */
    @Test
    public void testDetermineRecurring() {
        // regular payment types should be true
        //assertTrue(bean.determineRecurring(PaymentTypeEnum.REGULAR_INITIAL.value()));
        assertTrue(bean.determineRecurring(PaymentTypeEnum.REGULAR.value()));
        //assertTrue(bean.determineRecurring(PaymentTypeEnum.REGULAR_UPDATE.value()));
        // other payment types should be false
        assertFalse(bean.determineRecurring(PaymentTypeEnum.EVENT_FEE.value()));
        assertFalse(bean.determineRecurring(PaymentTypeEnum.PAYMENT.value()));
        assertFalse(bean.determineRecurring(PaymentTypeEnum.REGISTRATION_FEE.value()));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.LogicGroupSecurePaymentHelperImpl#afterPropertiesSet()}.
     */
    @Test
    public void testAfterPropertiesSet() {
        bean.afterPropertiesSet();
    }

}
