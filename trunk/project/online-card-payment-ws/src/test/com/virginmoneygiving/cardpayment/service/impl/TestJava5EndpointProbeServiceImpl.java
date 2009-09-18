package com.virginmoneygiving.cardpayment.service.impl;

import static org.junit.Assert.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.virginmoneygiving.cardpayment.service.impl.https.MockHttpsUrlConnectionImpl;
import com.virginmoneygiving.cardpayment.serviceproxy.AlertServiceProxy;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * Unit test for Java5EndpointProbeServiceImpl.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestJava5EndpointProbeServiceImpl {

    // used impl not interface to save casting
    /** The bean. */
    private Java5EndpointProbeServiceImpl bean;

    /** The alert service. */
    @Mock
    private AlertServiceProxy alertService;
    
    /** The Constant HANDLER_PKGS. */
    private static final String HANDLER_PKGS = "java.protocol.handler.pkgs";
    
    /** The default handler. */
    private String defaultHandler;
    
    /** Whether to run mock unit or integration test. */
    private boolean runAMock;
    
    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new Java5EndpointProbeServiceImpl();
        MockitoAnnotations.initMocks(this);
        
        defaultHandler = System.getProperty(HANDLER_PKGS); 

        // this will use our dummy implementations
        System.setProperty(HANDLER_PKGS, 
                "com.virginmoneygiving.cardpayment.service.impl");
        
        URL testUrl = new URL("https://www.amazon.co.uk/");
        HttpsURLConnection conn = (HttpsURLConnection) testUrl.openConnection();
        runAMock = (conn instanceof MockHttpsUrlConnectionImpl);
        
        Logger.getLogger("TestJava5EndpointProbeServiceImpl").info("Using mock test: " + runAMock);
    }

    /**
     * Tear down.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        // reinstate for good measure
        if (defaultHandler == null) {
            System.clearProperty(HANDLER_PKGS);
        } else {
            System.setProperty(HANDLER_PKGS, defaultHandler);
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.SimpleEndpointProbeServiceImpl#checkEndpoint(java.lang.String)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testCheckEndpoint() throws Exception {
        if (runAMock) {
            assertTrue("Should return true", bean.checkEndpoint("https://www.amazon.co.uk/success"));
            assertFalse("Should return false", bean.checkEndpoint("https://www.foo.bar"));
        } else {
            //assertTrue("Should return true", bean.checkEndpoint("https://www.amazon.co.uk/"));
            //assertFalse("Should return false", bean.checkEndpoint("https://www.foo.bar"));
        }
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.SimpleEndpointProbeServiceImpl#checkEndpoint(java.lang.String)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testCheckEndpointNameException() throws Exception {
        assertFalse(bean.checkEndpoint(""));
        assertFalse(bean.checkEndpoint(null));
        assertFalse(bean.checkEndpoint("junk@foo"));
    }
    
    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.SimpleEndpointProbeServiceImpl#chooseUrl(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testChooseUrlPrimary() throws Exception {
        bean.setAlertService(alertService);
        if (runAMock) {
            assertEquals("https://www.rbramley.com/success", bean.chooseUrl("https://www.rbramley.com/success", "https://www.rbramley.com/"));
        } else {
            //assertEquals("https://www.amazon.co.uk/", bean.chooseUrl("https://www.amazon.co.uk/", "https://www.rbramley.com/"));            
        }
        verify(alertService, never()).raiseAlert(isA(String.class));
    }
    
    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.SimpleEndpointProbeServiceImpl#chooseUrl(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testChooseUrlSecondary() throws Exception {
        bean.setAlertService(alertService);
        //assertEquals("https://www.rbramley.com/success", bean.chooseUrl("https://www.rbramley.com/", "https://www.rbramley.com/success"));
        if (runAMock) {
            assertEquals("https://www.rbramley.com/success", bean.chooseUrl("https://www.rbramley.com/", "https://www.rbramley.com/success"));
        } else {
            //assertEquals("https://www.amazon.co.uk/", bean.chooseUrl("https://www.rbramley.com/foobar", "https://www.amazon.co.uk/"));            
        }
        verify(alertService, never()).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.service.impl.SimpleEndpointProbeServiceImpl#chooseUrl(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testBothEndpointsDown() throws Exception {
        bean.setAlertService(alertService);
        try {
            bean.chooseUrl("https://www.rbramley.com/foobar", "https://www.rbramley.com/barfoo");
            fail("should throw a RuntimeException");
        } catch (RuntimeException expected) {
            assertEquals("Neither endpoint available...", expected.getMessage());
        }
        verify(alertService).raiseAlert(isA(String.class));
    }
}
