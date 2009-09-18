package com.virginmoneygiving.cardpayment.validation;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithCardRequest;
import com.virginmoneygiving.cardpayment.service.messages.MessageHeader;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardSecurityInformation;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentTypeEnum;

/**
 * Integration test for Valang configuration (migrated from custom validator).
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestAuthoriseWithCardRequestValidator {

    /** unit under test. */
    private Validator bean;

    // test request
    /** The req. */
    private AuthoriseWithCardRequest req;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        LogManager.getRootLogger().setLevel(Level.INFO);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/WEB-INF/resources/validation-context.xml");
        bean = (Validator) ctx.getBean("cardRequestValidator");

        req = new AuthoriseWithCardRequest();

        ServiceCardDetails cd = new ServiceCardDetails();
        ServicePaymentDetails pd = new ServicePaymentDetails();
        MessageHeader header = new MessageHeader();
        ServiceCardSecurityInformation cs = new ServiceCardSecurityInformation();

        cs.setCsc("5252");
        cs.setHouseNumber(11);
        cs.setPostcode("144");

        cd.setSecurityInfo(cs);
        cd.setPan("374245455400001");
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
     * {@link com.virginmoneygiving.cardpayment.validation.AuthoriseWithCardRequestValidator#supports(java.lang.Class)}
     * .
     */
    @Test
    public void testSupports() {
        assertTrue(bean.supports(AuthoriseWithCardRequest.class));
    }

    /**
     * Test validate success.
     */
    @Test
    public void testValidateSuccess() {
        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be no errors " + flattenErrors(errors), 0, errors.getErrorCount());
    }

    /**
     * Test csc as alphanumeric.
     */
    @Test
    public void testCscAsAlphanumeric() {
        // override
        req.getCardDetails().getSecurityInfo().setCsc("0a12");

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test csc too short.
     */
    @Test
    public void testCscTooShort() {
        // override
        req.getCardDetails().getSecurityInfo().setCsc("12");

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test csc too long.
     */
    @Test
    public void testCscTooLong() {
        // override
        req.getCardDetails().getSecurityInfo().setCsc("12345");

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test csc as null.
     */
    @Test
    public void testCscAsNull() {
        // override
        req.getCardDetails().getSecurityInfo().setCsc(null);

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test postcode as alphanumeric.
     */
    @Test
    public void testPostcodeAsAlphanumeric() {
        // override
        req.getCardDetails().getSecurityInfo().setPostcode("W14 4RG");

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test postcode as null.
     */
    @Test
    public void testPostcodeAsNull() {
        // override
        req.getCardDetails().getSecurityInfo().setPostcode(null);

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test house number as null.
     */
    @Test
    public void testHouseNumberAsNull() {
        // override
        req.getCardDetails().getSecurityInfo().setHouseNumber(null);

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be no errors " + flattenErrors(errors), 0, errors.getErrorCount());
    }

    /**
     * Test house number as negative.
     */
    @Test
    public void testHouseNumberAsNegative() {
        // override
        req.getCardDetails().getSecurityInfo().setHouseNumber(-1);

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Test amount no scale.
     */
    @Test
    public void testAmountNoScale() {
        // override
        req.getPaymentDetails().setAmount(new BigDecimal("8"));

        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithCardRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Flatten errors.
     * 
     * @param errors the errors
     * 
     * @return the string
     */
    protected String flattenErrors(Errors errors) {
        StringBuilder sb = new StringBuilder();
        List errorList = errors.getAllErrors();
        Iterator i = errorList.iterator();

        while (i.hasNext()) {
            ObjectError e = (ObjectError) i.next();
            sb.append(" : ").append(e.toString());
        }
        
        return sb.toString();
    }

}
