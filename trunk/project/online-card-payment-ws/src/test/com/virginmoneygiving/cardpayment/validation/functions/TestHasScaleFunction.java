package com.virginmoneygiving.cardpayment.validation.functions;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springmodules.validation.valang.functions.Function;
import org.springmodules.validation.valang.functions.LiteralFunction;

/**
 * The Class TestHasScaleFunction.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestHasScaleFunction {

    /** unit under test. */
    private HasScaleFunction bean;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        BasicConfigurator.configure();
        LogManager.getRootLogger().setLevel(Level.INFO);
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
     * {@link com.virginmoneygiving.cardpayment.validation.functions.HasScaleFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testWithDecimal() throws Exception {
        String testValue = "70.00";
        LiteralFunction container = new LiteralFunction(testValue);
        LiteralFunction scale = new LiteralFunction("2");
        bean = new HasScaleFunction(new Function[] {container, scale}, 1, 123);

        assertTrue((Boolean) bean.doGetResult(testValue));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.HasScaleFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testWithBigDecimal() throws Exception {
        BigDecimal testValue = new BigDecimal("70.00");
        LiteralFunction container = new LiteralFunction(testValue);
        LiteralFunction scale = new LiteralFunction("2");
        bean = new HasScaleFunction(new Function[] {container, scale}, 1, 123);

        assertTrue((Boolean) bean.doGetResult(testValue));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.validation.functions.HasScaleFunction#doGetResult(java.lang.Object)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testWithNumeric() throws Exception {
        String testValue = "123";
        LiteralFunction container = new LiteralFunction(testValue);
        LiteralFunction scale = new LiteralFunction("2");
        bean = new HasScaleFunction(new Function[] {container, scale}, 1, 123);

        assertFalse((Boolean) bean.doGetResult(testValue));
    }
}
