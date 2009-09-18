package com.virginmoneygiving.schedulerservice.serviceproxy.impl;

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
 * This class TestBatchJobRequestJob is used to test the methods inside this
 * class for test alert service proxy. it act as a mock object where without any
 * interaction with databse we are able to test the method with its required
 * output.
 * @author John Allen, Opsera Ltd.
 */
public class TestAlertServiceProxyImpl {

    /** unit under test. */
    private AlertServiceProxyImpl bean;

    /** mock ws port. */
    @Mock
    private AlertPort port;

    /**
     * Sets the up.
     * @throws Exception error occurred.
     */
    @Before
    public void setUp() throws Exception {
        bean = new AlertServiceProxyImpl();

        MockitoAnnotations.initMocks(this);
        bean.setPort(port);
    }

    /**
     * Tear down.
     * @throws Exception error occurred.
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.schedulerservice.serviceproxy.impl.AlertServiceProxyImpl#raiseAlert(java.lang.String)}.
     */
    @Test
    public void testRaiseAlert() {
        bean.raiseAlert("message");

        verify(port).logAlertRequest(isA(AlertDetail.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.schedulerservice.serviceproxy.impl.AlertServiceProxyImpl#raiseAlert(java.lang.String)}.
     */
    @Test
    public void testRaiseNullAlert() {
        bean.raiseAlert(null);

        verify(port, never()).logAlertRequest(isA(AlertDetail.class));
    }

}
