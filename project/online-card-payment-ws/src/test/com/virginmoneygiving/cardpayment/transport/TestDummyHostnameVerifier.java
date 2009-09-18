package com.virginmoneygiving.cardpayment.transport;

import static org.junit.Assert.*;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the dummy hostname verifier.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestDummyHostnameVerifier {

    /** The bean. */
    private DummyHostnameVerifier bean;
    
    /** real default. */
    HostnameVerifier realHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

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
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.DummyHostnameVerifier#DummyHostnameVerifier(boolean)}.
     */
    @Test
    public void testDummyHostnameVerifier() {
        bean = new DummyHostnameVerifier(false);
        bean.register();
        assertEquals(realHostnameVerifier, HttpsURLConnection.getDefaultHostnameVerifier());
        bean.unregister();
        assertEquals(realHostnameVerifier, HttpsURLConnection.getDefaultHostnameVerifier());
        
        bean = new DummyHostnameVerifier(true);
        bean.register();
        assertTrue(HttpsURLConnection.getDefaultHostnameVerifier() instanceof DummyHostnameVerifier);
        bean.unregister();
        assertEquals(realHostnameVerifier, HttpsURLConnection.getDefaultHostnameVerifier());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.DummyHostnameVerifier#verify(java.lang.String, javax.net.ssl.SSLSession)}.
     */
    @Test
    public void testVerify() {
        bean = new DummyHostnameVerifier(false);
        assertTrue(bean.verify("foo", null));
    }

}
