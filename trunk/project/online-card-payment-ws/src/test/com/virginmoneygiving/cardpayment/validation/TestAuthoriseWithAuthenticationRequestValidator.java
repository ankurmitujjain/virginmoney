package com.virginmoneygiving.cardpayment.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.virginmoneygiving.cardpayment.service.messages.AuthoriseWithAuthenticationRequest;
import com.virginmoneygiving.cardpayment.service.messages.MessageHeader;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardSecurityInformation;
import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentTypeEnum;

/**
 * TODO: migrate to an integration test.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestAuthoriseWithAuthenticationRequestValidator {

    /** unit under test. */
    private Validator bean;
    
    /** The req. */
    private AuthoriseWithAuthenticationRequest req;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/WEB-INF/resources/validation-context.xml");
        bean = (Validator) ctx.getBean("authenticationRequestValidator");
        
        req = new AuthoriseWithAuthenticationRequest();
        
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
     * Test supports.
     */
    @Test
    public void testSupports() {
        assertTrue(bean.supports(AuthoriseWithAuthenticationRequest.class));
    }
    
    /**
     * Test validate success.
     */
    @Test
    public void testValidateSuccess() {
        List<ServiceNameValuePair> authResponseData = new ArrayList<ServiceNameValuePair>();
        authResponseData.add(makeNVP("version", "2.0"));
        authResponseData.add(makeNVP("mdStatus", "1"));
        authResponseData.add(makeNVP("xid", "CY+tPbmNjpnihsV7daleZxzUS68="));
        authResponseData.add(makeNVP("vendorCode", ""));
        authResponseData.add(makeNVP("txstatus", "Y"));
        authResponseData.add(makeNVP("mdErrorMsg", "Authenticated"));
        authResponseData.add(makeNVP("eci", "05"));
        authResponseData.add(makeNVP("merchantID", "056"));
        authResponseData.add(makeNVP("sID", "1"));
        authResponseData.add(makeNVP("digest", "IVHNzOxPQ4ZU1RdtIWMsjKB1jNA="));
        authResponseData.add(makeNVP("opalErrorCode", "0"));
        authResponseData.add(makeNVP("md", ""));
        authResponseData.add(makeNVP("MD", ""));
        authResponseData.add(makeNVP("cavv", "AAABB0EJgwAAAAABEAmDAAAAAAA="));
        authResponseData.add(makeNVP("cavvAlgorithm", "2"));
        authResponseData.add(makeNVP("PAResSyntaxOK", "true"));
        authResponseData.add(makeNVP("PAResVerified", "true"));
        authResponseData.add(makeNVP("iReqCode", ""));
        authResponseData.add(makeNVP("iReqDetail", ""));

        req.getAuthResponseData().addAll(authResponseData);
        
        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithAuthenticationRequest");

        bean.validate(req, errors);

        assertEquals("Should be no errors " + flattenErrors(errors), 0, errors.getErrorCount());
    }

    
    /**
     * Test validate empty auth response data.
     */
    @Test
    public void testValidateEmptyAuthResponseData() {
        // validate request
        Errors errors = new BeanPropertyBindingResult(req, "AuthoriseWithAuthenticationRequest");

        bean.validate(req, errors);

        assertEquals("Should be one error " + flattenErrors(errors), 1, errors.getErrorCount());
    }

    /**
     * Make nvp.
     * 
     * @param name the name
     * @param value the value
     * 
     * @return the service name value pair
     */
    protected ServiceNameValuePair makeNVP(String name, String value) {
        ServiceNameValuePair nvp = new ServiceNameValuePair();
        nvp.setName(name);
        nvp.setValue(value);
        return nvp;
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
