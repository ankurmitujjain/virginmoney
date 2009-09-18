package com.virginmoneygiving.cardpayment.transport;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.virginmoneygiving.cardpayment.service.EndpointProbeService;
import com.virginmoneygiving.cardpayment.service.exception.EndpointUnavailableException;

/**
 * Heartbeat test.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestTLGHeartbeatImpl {

    /** Unit under test. */
    private TLGHeartbeatImpl bean;
    
    /** The mock failover. */
    @Mock
    private TLGFailover mockFailover;

    /** The mock probe. */
    @Mock
    private EndpointProbeService mockProbe;
    
    /** The Constant PRIMARY. */
    private static final String PRIMARY = "primary";
    
    /** The Constant SECONDARY. */
    private static final String SECONDARY = "secondary";
    
    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        bean = new TLGHeartbeatImpl();
        bean.setActive(true);
        bean.setFailover(mockFailover);
        bean.setProbe(mockProbe);
        bean.setPrimaryUrl(PRIMARY);
        bean.setPrimaryHost(PRIMARY);
        bean.setSecondaryUrl(SECONDARY);
        bean.setSecondaryHost(SECONDARY);
        
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
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGHeartbeatImpl#heartbeat()}.
     */
    @Test
    public void testHeartbeatPrimaryPrimaryOk() {
        when(mockFailover.isPrimary()).thenReturn(true);
        when(mockProbe.chooseUrl(PRIMARY, SECONDARY)).thenReturn(PRIMARY);
        
        bean.heartbeat();
        assertTrue(bean.isActive());
        assertNotNull(bean.getLastCheckDatetime());
        
        verify(mockFailover).isPrimary();
        verify(mockProbe).chooseUrl(PRIMARY, SECONDARY);
        verify(mockFailover, never()).setActiveHostname(anyString());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGHeartbeatImpl#heartbeat()}.
     */
    @Test
    public void testHeartbeatPrimarySecondaryOk() {
        when(mockFailover.isPrimary()).thenReturn(true);
        when(mockProbe.chooseUrl(PRIMARY, SECONDARY)).thenReturn(SECONDARY);
        
        bean.heartbeat();
        assertTrue(bean.isActive());
        assertNotNull(bean.getLastCheckDatetime());
        
        verify(mockFailover).isPrimary();
        verify(mockProbe).chooseUrl(PRIMARY, SECONDARY);
        verify(mockFailover).setActiveHostname(SECONDARY);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGHeartbeatImpl#heartbeat()}.
     */
    @Test
    public void testHeartbeatPrimaryNeitherOk() {
        when(mockFailover.isPrimary()).thenReturn(true);
        when(mockProbe.chooseUrl(PRIMARY, SECONDARY))
            .thenThrow(new EndpointUnavailableException("Oops..."));
        
        bean.heartbeat();
        assertTrue(bean.isActive());
        assertNotNull(bean.getLastCheckDatetime());
        
        verify(mockFailover).isPrimary();
        verify(mockProbe).chooseUrl(PRIMARY, SECONDARY);
        verify(mockFailover, never()).setActiveHostname(anyString());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGHeartbeatImpl#heartbeat()}.
     */
    @Test
    public void testHeartbeatSecondarySecondaryOk() {
        when(mockFailover.isPrimary()).thenReturn(false);
        when(mockProbe.chooseUrl(SECONDARY, PRIMARY)).thenReturn(SECONDARY);
        
        bean.heartbeat();
        assertTrue(bean.isActive());
        assertNotNull(bean.getLastCheckDatetime());
        
        verify(mockFailover).isPrimary();
        verify(mockProbe).chooseUrl(SECONDARY, PRIMARY);
        verify(mockFailover, never()).setActiveHostname(anyString());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGHeartbeatImpl#heartbeat()}.
     */
    @Test
    public void testHeartbeatSecondaryPrimaryOk() {
        when(mockFailover.isPrimary()).thenReturn(false);
        when(mockProbe.chooseUrl(SECONDARY, PRIMARY)).thenReturn(PRIMARY);
        
        bean.heartbeat();
        assertTrue(bean.isActive());
        assertNotNull(bean.getLastCheckDatetime());
        
        verify(mockFailover).isPrimary();
        verify(mockProbe).chooseUrl(SECONDARY, PRIMARY);
        verify(mockFailover).setActiveHostname(PRIMARY);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGHeartbeatImpl#heartbeat()}.
     */
    @Test
    public void testHeartbeatSecondaryNeitherOk() {
        when(mockFailover.isPrimary()).thenReturn(false);
        when(mockProbe.chooseUrl(SECONDARY, PRIMARY))
            .thenThrow(new EndpointUnavailableException("Oops..."));
        
        bean.heartbeat();
        assertTrue(bean.isActive());
        assertNotNull(bean.getLastCheckDatetime());
        
        verify(mockFailover).isPrimary();
        verify(mockProbe).chooseUrl(SECONDARY, PRIMARY);
        verify(mockFailover, never()).setActiveHostname(anyString());
    }
}
