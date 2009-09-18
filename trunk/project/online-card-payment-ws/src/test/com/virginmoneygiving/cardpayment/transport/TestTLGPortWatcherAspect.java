package com.virginmoneygiving.cardpayment.transport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

/**
 * The Class TestTLGPortWatcherAspect.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestTLGPortWatcherAspect {

    /** unit under test. */
    private TLGPortWatcherAspect bean;

    /** mock failover object. */
    @Mock
    private TLGFailover mockFailover;
    
    /** The Constant PRIMARY. */
    private static final String PRIMARY = "foo";
    
    /** The Constant SECONDARY. */
    private static final String SECONDARY = "bar";
    
    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        bean = new TLGPortWatcherAspect();
        bean.setFailover(mockFailover);
        bean.setPrimaryHost(PRIMARY);
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
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGPortWatcherAspect#doFailover(java.lang.Throwable)}.
     */
    @Test
    public void testDoFailover() {
        // record behaviour
        when(mockFailover.isPrimary()).thenReturn(true);

        // invoke test
        bean.doFailover(new Throwable("Oh no!"));

        // verify
        verify(mockFailover).setActiveHostname(SECONDARY);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGPortWatcherAspect#doFailover(java.lang.Throwable)}.
     */
    @Test
    public void testDoFailBack() {
        // record behaviour
        when(mockFailover.isPrimary()).thenReturn(false);

        // invoke test
        bean.doFailover(new Throwable("Oh no!"));

        // verify
        verify(mockFailover).setActiveHostname(PRIMARY);
    }
}
