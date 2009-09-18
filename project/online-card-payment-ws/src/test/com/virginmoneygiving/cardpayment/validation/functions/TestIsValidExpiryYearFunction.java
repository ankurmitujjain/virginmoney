package com.virginmoneygiving.cardpayment.validation.functions;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springmodules.validation.valang.functions.Function;
import org.springmodules.validation.valang.functions.LiteralFunction;

/**
 * The Class TestIsValidExpiryYearFunction.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestIsValidExpiryYearFunction {

    /** unit under test. */
    private IsValidExpiryYearFunction bean;

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
     * {@link com.virginmoneygiving.cardpayment.validation.functions.IsValidExpiryYearFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testSuccess() throws Exception {
        Calendar cal = new GregorianCalendar();
        String testValue = String.valueOf(cal.get(Calendar.YEAR));
        LiteralFunction container = new LiteralFunction(testValue);
        bean = new IsValidExpiryYearFunction(new Function[] {container}, 1, 123);

        assertTrue((Boolean) bean.doGetResult(testValue));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.IsValidExpiryYearFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFailure() throws Exception {
        Calendar cal = new GregorianCalendar();
        // manipulate the calendar
        cal.add(Calendar.YEAR, -1);
        Date lastYear = cal.getTime();

        String testValue = String.valueOf(cal.get(Calendar.YEAR));
        LiteralFunction container = new LiteralFunction(testValue);
        bean = new IsValidExpiryYearFunction(new Function[] {container}, 1, 123);

        assertFalse((Boolean) bean.doGetResult(testValue));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.IsValidExpiryYearFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testEmptyString() throws Exception {
        String testValue = "";
        LiteralFunction container = new LiteralFunction(testValue);
        bean = new IsValidExpiryYearFunction(new Function[] {container}, 1, 123);

        assertFalse((Boolean) bean.doGetResult(testValue));
    }
}
