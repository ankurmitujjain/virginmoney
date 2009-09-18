package com.virginmoneygiving.cardpayment.event.listener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextStartedEvent;

import com.virginmoneygiving.cardpayment.event.AuthorisationErrorEvent;
import com.virginmoneygiving.cardpayment.event.AuthorisationSuccessfulEvent;
import com.virginmoneygiving.cardpayment.event.ValidationErrorEvent;
import com.virginmoneygiving.cardpayment.serviceproxy.AlertServiceProxy;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * Test the alert event listener.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestAlertingEventListener {

    /** unit under test. */
    private AlertingEventListener bean;

    /** Mock alert service. */
    @Mock
    private AlertServiceProxy alertService;

    /** Mock application context. */
    @Mock
    private ApplicationContext applicationContext;
    
    /** Constant for GUID. */
    private static final String TEST_GUID = "0xDEADBEEF";

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new AlertingEventListener();
        MockitoAnnotations.initMocks(this);
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
     * {@link com.virginmoneygiving.cardpayment.event.listener.AlertingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEvent() {
        bean.setAlertService(alertService);

        AuthorisationErrorEvent event = new AuthorisationErrorEvent(this,
                TEST_GUID, "test auth error event");

        // we spy on the event to ensure the getters are invoked
        AuthorisationErrorEvent spy = spy(event);

        bean.onApplicationEvent(spy);

        InOrder inOrder = inOrder(spy, alertService);
        inOrder.verify(spy).getMessage();
        inOrder.verify(spy).getGuid();
        inOrder.verify(alertService, times(1)).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.event.listener.AlertingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventValidationError() {
        bean.setAlertService(alertService);

        ValidationErrorEvent event = new ValidationErrorEvent(this,
                TEST_GUID, "test auth error event");

        // we spy on the event to ensure the getters are invoked
        ValidationErrorEvent spy = spy(event);

        bean.onApplicationEvent(spy);

        InOrder inOrder = inOrder(spy, alertService);
        inOrder.verify(spy).getMessage();
        inOrder.verify(spy).getGuid();
        inOrder.verify(alertService, times(1)).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.event.listener.AlertingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventNoOp() {
        bean.setAlertService(alertService);

        // test a success event as this isn't alert-able
        bean.onApplicationEvent(new AuthorisationSuccessfulEvent(this,
                TEST_GUID, "test auth success event"));

        // verify the alert service isn't called
        verify(alertService, never()).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.event.listener.AlertingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testAlertServiceError() {
        bean.setAlertService(alertService);
        doThrow(new RuntimeException()).when(alertService).raiseAlert(
                isA(String.class));

        AuthorisationErrorEvent event = new AuthorisationErrorEvent(this,
                TEST_GUID, "test auth error event");

        // we spy on the event to ensure the getters are invoked
        AuthorisationErrorEvent spy = spy(event);

        bean.onApplicationEvent(spy);

        InOrder inOrder = inOrder(spy, alertService);
        inOrder.verify(spy).getMessage();
        inOrder.verify(spy).getGuid();
        inOrder.verify(alertService, times(1)).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.event.listener.AlertingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventIgnore() {
        // invoke target with a Spring container event
        bean.onApplicationEvent(new ContextStartedEvent(applicationContext));
    }
}
