package com.virginmoneygiving.cardpayment.validation.functions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springmodules.validation.valang.functions.Function;
import org.springmodules.validation.valang.functions.LiteralFunction;

import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;

/**
 * The Class TestCheckAuthenticationResponseFunction.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestCheckAuthenticationResponseFunction {

    /** unit under test. */
    private CheckAuthenticationResponseFunction bean;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
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
     * {@link com.virginmoneygiving.cardpayment.validation.functions.CheckAuthenticationResponseFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testSuccess() throws Exception {
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

        LiteralFunction container = new LiteralFunction(authResponseData);
        bean = new CheckAuthenticationResponseFunction(new Function[] {container}, 1, 123);
        assertTrue((Boolean) bean.doGetResult(container));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.CheckAuthenticationResponseFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFailure() throws Exception {
        List<ServiceNameValuePair> authResponseData = new ArrayList<ServiceNameValuePair>();
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

        LiteralFunction container = new LiteralFunction(authResponseData);
        bean = new CheckAuthenticationResponseFunction(new Function[] {container}, 1, 123);
        assertFalse((Boolean) bean.doGetResult(container));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.CheckAuthenticationResponseFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFailureOnWrongCollectionType() throws Exception {
        Map<String, String> authResponseData = new HashMap<String, String>();

        LiteralFunction container = new LiteralFunction(authResponseData);
        bean = new CheckAuthenticationResponseFunction(new Function[] {container}, 1, 123);
        assertFalse((Boolean) bean.doGetResult(container));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.CheckAuthenticationResponseFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFailureOnClassCast() throws Exception {
        List<String> authResponseData = new ArrayList<String>();

        LiteralFunction container = new LiteralFunction(authResponseData);
        bean = new CheckAuthenticationResponseFunction(new Function[] {container}, 1, 123);
        assertFalse((Boolean) bean.doGetResult(container));
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
}
