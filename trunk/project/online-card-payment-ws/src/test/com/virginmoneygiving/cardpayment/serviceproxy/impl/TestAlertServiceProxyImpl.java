package com.virginmoneygiving.cardpayment.serviceproxy.impl;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.virginmoneygiving.alert.service.messages.AlertDetail;
import com.virginmoneygiving.alert.service.messages.AlertPort;

/**
 * The Class TestAlertServiceProxyImpl.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestAlertServiceProxyImpl {

    /** unit under test. */
    private AlertServiceProxyImpl bean;
    
    /** The port. */
    @Mock
    private AlertPort port;
    
    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new AlertServiceProxyImpl();

        MockitoAnnotations.initMocks(this);
        bean.setPort(port);
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
     * Test method for {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.AlertServiceProxyImpl#raiseAlert(java.lang.String)}.
     */
    @Test
    public void testRaiseAlert() {
        bean.raiseAlert("message");
        
        verify(port).logAlertRequest(isA(AlertDetail.class));        
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.serviceproxy.impl.AlertServiceProxyImpl#raiseAlert(java.lang.String)}.
     */
    @Test
    public void testRaiseNullAlert() {
        bean.raiseAlert(null);
        
        verify(port, never()).logAlertRequest(isA(AlertDetail.class));        
    }

}
