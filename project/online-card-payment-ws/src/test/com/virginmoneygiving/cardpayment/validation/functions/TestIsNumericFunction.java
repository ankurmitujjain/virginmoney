package com.virginmoneygiving.cardpayment.validation.functions;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springmodules.validation.valang.functions.Function;
import org.springmodules.validation.valang.functions.LiteralFunction;

/**
 * The Class TestIsNumericFunction.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestIsNumericFunction {

    /** unit under test. */
    private IsNumericFunction bean;

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
     * {@link com.virginmoneygiving.cardpayment.validation.functions.IsNumericFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testWithNumeric() throws Exception {
        String testValue = "123";
        LiteralFunction container = new LiteralFunction(testValue);
        bean = new IsNumericFunction(new Function[] {container}, 1, 123);

        assertTrue((Boolean) bean.doGetResult(testValue));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.IsNumericFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testWithEmptyString() throws Exception {
        String testValue = "";
        LiteralFunction container = new LiteralFunction(testValue);
        bean = new IsNumericFunction(new Function[] {container}, 1, 123);

        assertFalse((Boolean) bean.doGetResult(testValue));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.IsNumericFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testWithAlpha() throws Exception {
        String testValue = "r2d2";
        LiteralFunction container = new LiteralFunction(testValue);
        bean = new IsNumericFunction(new Function[] {container}, 1, 123);

        assertFalse((Boolean) bean.doGetResult(testValue));
    }
}
