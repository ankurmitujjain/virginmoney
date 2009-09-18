package com.virginmoneygiving.cardpayment.webservicelocator;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;
import com.virginmoneygiving.cardpayment.serviceproxy.AlertServiceProxy;

/**
 * The Class TestHATLGWebServiceLocatorImpl.
 */
public class TestHATLGWebServiceLocatorImpl {

    /** unit under test. */
    private HATLGWebServiceLocatorImpl bean;

    /** The primary host. */
    private String primaryHost = "wsp1.secure-payment-processing.com";
    
    /** The secondary host. */
    private String secondaryHost = "wsp2.secure-payment-processing.com";

    /** The mock delegate. */
    @Mock
    private TLGJaxWsHelper mockDelegate;

    /** The mock alert service proxy. */
    @Mock
    private AlertServiceProxy mockAlertServiceProxy;

    /** The mock basic port. */
    @Mock
    private PaymentAPISoap mockBasicPort;
    
    /** The mock extended port. */
    @Mock
    private ExtendedPaymentAPISoap mockExtendedPort;
    
    /**
     * Initial Setup.
     * 
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        bean = new HATLGWebServiceLocatorImpl(
                primaryHost,
                secondaryHost);
        
        bean.setDelegate(mockDelegate);
        bean.setAlertServiceProxy(mockAlertServiceProxy);
    }

    /**
     * Tear down.
     * 
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test failover.
     */
    @Test
    public void testFailover() {
        assertEquals(primaryHost, bean.getActiveHostname());
        bean.failover(new RuntimeException("Foo!"));
        assertEquals(secondaryHost, bean.getActiveHostname());
        verify(mockAlertServiceProxy).raiseAlert(anyString());
    }
    
    /**
     * Test failover no op.
     */
    @Test
    public void testFailoverNoOp() {
        assertEquals(primaryHost, bean.getActiveHostname());
        bean.setActiveHostname(primaryHost);
        assertEquals(primaryHost, bean.getActiveHostname());
        verify(mockDelegate, never()).getBasicPortByHostname(anyString());
        verify(mockDelegate, never()).getExtendedPortByHostname(anyString());
        verify(mockAlertServiceProxy, never()).raiseAlert(anyString());
    }

    /**
     * Test failback.
     */
    @Test
    public void testFailback() {
        assertEquals(primaryHost, bean.getActiveHostname());
        bean.setActiveHostname(secondaryHost);
        assertEquals(secondaryHost, bean.getActiveHostname());
        bean.failover(new RuntimeException("Foo!"));
        assertEquals(primaryHost, bean.getActiveHostname());
        verify(mockAlertServiceProxy, times(2)).raiseAlert(anyString());
    }

    /**
     * Test failback2.
     */
    @Test
    public void testFailback2() {
        assertEquals(primaryHost, bean.getActiveHostname());
        bean.setActiveHostname(secondaryHost);
        assertEquals(secondaryHost, bean.getActiveHostname());
        bean.setActiveHostname(primaryHost);
        assertEquals(primaryHost, bean.getActiveHostname());
        verify(mockAlertServiceProxy, times(2)).raiseAlert(anyString());
    }

    /**
     * Test get active hostname.
     */
    @Test
    public void testGetActiveHostname() {
        assertEquals(primaryHost, bean.getActiveHostname());
        bean.setActiveHostname(secondaryHost);
        assertEquals(secondaryHost, bean.getActiveHostname());
        verify(mockAlertServiceProxy).raiseAlert(anyString());
    }

    /**
     * Test set active hostname null.
     */
    @Test
    public void testSetActiveHostnameNull() {
        try {
            bean.setActiveHostname(null);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

    /**
     * Test is primary.
     */
    @Test
    public void testIsPrimary() {
        assertTrue(bean.isPrimary());
        bean.setActiveHostname(secondaryHost);
        assertFalse(bean.isPrimary());
        verify(mockAlertServiceProxy).raiseAlert(anyString());
    }

    /**
     * Test is port initialised.
     */
    @Test
    public void testIsPortInitialised() {
        // record behaviour
        when(mockDelegate.getBasicPortByHostname(anyString())).thenReturn(mockBasicPort);
        when(mockDelegate.getExtendedPortByHostname(anyString())).thenReturn(mockExtendedPort);        
        
        assertFalse(bean.isPortInitialised());
        assertNotNull(bean.getBasicPort());
        assertTrue(bean.isPortInitialised());

        // verify 
        verify(mockDelegate).getBasicPortByHostname(anyString());
        verify(mockDelegate).getExtendedPortByHostname(anyString());
    }

    /**
     * Test get basic port.
     */
    @Test
    public void testGetBasicPort() {
        // record behaviour
        when(mockDelegate.getBasicPortByHostname(anyString())).thenReturn(mockBasicPort);        
        when(mockDelegate.getExtendedPortByHostname(anyString())).thenReturn(mockExtendedPort);        

        // test
        PaymentAPISoap port = bean.getBasicPort();

        // assertions
        assertNotNull(port);
        assertTrue(bean.isPortInitialised());
        // verify 
        verify(mockDelegate).getBasicPortByHostname(anyString());
        verify(mockDelegate).getExtendedPortByHostname(anyString());
    }

    /**
     * Test get extended port.
     */
    @Test
    public void testGetExtendedPort() {
        // record behaviour
        when(mockDelegate.getBasicPortByHostname(anyString())).thenReturn(mockBasicPort);        
        when(mockDelegate.getExtendedPortByHostname(anyString())).thenReturn(mockExtendedPort);        

        // test
        ExtendedPaymentAPISoap port = bean.getExtendedPort();

        // assertions
        assertNotNull(port);
        assertTrue(bean.isPortInitialised());

        // verify 
        verify(mockDelegate).getBasicPortByHostname(anyString());
        verify(mockDelegate).getExtendedPortByHostname(anyString());
    }

    /**
     * Test missing constructor args.
     */
    @Test
    public void testMissingConstructorArgs() {
        try {
            new HATLGWebServiceLocatorImpl(null, null);
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
        
        try {
            new HATLGWebServiceLocatorImpl("foo", null);
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

    /**
     * Test after props set.
     */
    @Test
    public void testAfterPropsSet() {
        // this requires 2 collaborators to be set
        // 1. test with neither
        try {
            HATLGWebServiceLocatorImpl ha = new HATLGWebServiceLocatorImpl("foo", "bar");
            ha.afterPropertiesSet();
            fail("exception expected");
        } catch (IllegalStateException expected) {
            assertNotNull(expected.getMessage());
        }

        // 2. test with first
        try {
            HATLGWebServiceLocatorImpl ha = new HATLGWebServiceLocatorImpl("foo", "bar");
            ha.setDelegate(mockDelegate);
            ha.afterPropertiesSet();
            fail("exception expected");
        } catch (IllegalStateException expected) {
            assertNotNull(expected.getMessage());
        }

        // 3. test with both
        bean.afterPropertiesSet();
    }

    /**
     * Test bad constructor args.
     */
    @Test
    public void testBadConstructorArgs() {
        when(mockDelegate.getBasicPortByHostname(anyString())).thenThrow(
                new RuntimeException("Bar", new MalformedURLException("Foo")));
        
        try {
            HATLGWebServiceLocatorImpl ha = 
                new HATLGWebServiceLocatorImpl("wsp1@com", "wsp2@com");
            ha.setDelegate(mockDelegate);
            ha.getBasicPort();
            fail("exception expected");
        } catch (RuntimeException expected) {
            assertNotNull(expected.getMessage());
            assertNotNull(expected.getCause());
            assertTrue(expected.getCause() instanceof MalformedURLException);
        }

        verify(mockDelegate).getBasicPortByHostname(anyString());
    }

}
