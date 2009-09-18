package com.virginmoneygiving.cardpayment.validation;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithTokenRequest;
import com.virginmoneygiving.cardpayment.service.messages.MessageHeader;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardSecurityInformation;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentTypeEnum;

/**
 * The Class TestAuthoriseWithTokenRequestValidator.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestAuthoriseWithTokenRequestValidator {

    /** unit under test. */
    private Validator bean;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/WEB-INF/resources/validation-context.xml");
        bean = (Validator) ctx.getBean("tokenRequestValidator");
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
     * {@link com.virginmoneygiving.cardpayment.validation.AuthoriseWithTokenRequestValidator#supports(java.lang.Class)}
     * .
     */
    @Test
    public void testSupports() {
        assertTrue(bean.supports(AuthoriseWithTokenRequest.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.AuthoriseWithTokenRequestValidator#validate(java.lang.Object, org.springframework.validation.Errors)}
     * .
     */
    @Test
    public void testValidationFailure() {
        AuthoriseWithTokenRequest req = new AuthoriseWithTokenRequest();
        ServiceCardDetails cd = new ServiceCardDetails();
        ServicePaymentDetails pd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();
        ServiceCardSecurityInformation cs = new ServiceCardSecurityInformation();

        cd.setSecurityInfo(cs);

        req.setCardDetails(cd);
        req.setPaymentDetails(pd);
        req.setGuid(java.util.UUID.randomUUID().toString());
        req.setHeader(header);

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithTokenRequest");

        bean.validate(req, errors);

        assertTrue("Should be > 0", errors.getErrorCount() > 0);
    }

    /**
     * Test validate success.
     */
    @Test
    public void testValidateSuccess() {
        AuthoriseWithTokenRequest req = new AuthoriseWithTokenRequest();

        ServiceCardDetails cd = new ServiceCardDetails();
        ServicePaymentDetails pd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();
        ServiceCardSecurityInformation cs = new ServiceCardSecurityInformation();

        cs.setCsc("5252");
        cs.setHouseNumber(11);
        cs.setPostcode("144");

        cd.setSecurityInfo(cs);
        cd.setToken("0xCAFEBABE");
        cd.setEndMonth(12);
        cd.setEndYear(2009);

        pd.setAmount(new BigDecimal("8.00"));
        pd.setCurrencyCode("GBP");
        pd.setPaymentRef("AMEX Test should pass");
        pd.setPaymentType(ServicePaymentTypeEnum.PAYMENT);

        req.setCardDetails(cd);
        req.setPaymentDetails(pd);
        req.setGuid(java.util.UUID.randomUUID().toString());
        req.setHeader(header);

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithTokenRequest");

        bean.validate(req, errors);

        assertEquals("Should be no errors", 0, errors.getErrorCount());
    }

}
