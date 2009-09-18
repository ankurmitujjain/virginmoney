package com.virginmoneygiving.cardpayment.event.listener;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextStartedEvent;

import static org.mockito.Mockito.*;

import com.virginmoneygiving.cardpayment.event.AuthenticationResponseReceivedEvent;
import com.virginmoneygiving.cardpayment.event.AuthorisationFailedEvent;
import com.virginmoneygiving.cardpayment.event.AuthorisationSuccessfulEvent;

/**
 * Test the logging event listener.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestLoggingEventListener {

    /** Unit under test. */
    private LoggingEventListener bean;

    /** The logger. */
    @Mock
    private Logger logger;

    /** The application context. */
    @Mock
    private ApplicationContext applicationContext;

    /** The Constant TEST_GUID. */
    private static final String TEST_GUID = "0xDEADBEEF";

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new LoggingEventListener();
        MockitoAnnotations.initMocks(this);

        // force it to use a mock logger
        bean.LOGGER = logger;
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
     * Test method for {@link com.virginmoneygiving.cardpayment.event.listener.LoggingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventFor3D() {
        // create event
        AuthenticationResponseReceivedEvent event = new AuthenticationResponseReceivedEvent(this,
                TEST_GUID, "test auth response event", null);
        // create spy (for behaviour testing)
        AuthenticationResponseReceivedEvent spy = spy(event);

        // invoke test
        bean.onApplicationEvent(spy);

        // verify
        InOrder inOrder = inOrder(spy, logger);
        inOrder.verify(spy, times(1)).getMessage();
        inOrder.verify(spy, times(1)).getGuid();
        inOrder.verify(spy, times(1)).getAuthResponseData();
        verify(logger, times(1)).info(isA(String.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.event.listener.LoggingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventDebug() {
        // set up a success event as not info loggable
        AuthorisationSuccessfulEvent event = new AuthorisationSuccessfulEvent(this,
                TEST_GUID, "test auth success event");

        // create spy (for behaviour testing)
        AuthorisationSuccessfulEvent spy = spy(event);

        // test with logger set to debug
        when(logger.isDebugEnabled()).thenReturn(true);

        // test a success event as this isn't info loggable
        bean.onApplicationEvent(spy);

        // verify behaviour
        verify(logger, times(1)).isDebugEnabled();
        verify(spy, times(1)).getMessage();
        verify(spy, times(1)).getGuid();
        verify(logger, times(1)).debug(isA(String.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.event.listener.LoggingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventNoOp() {
        // set up a success event as not info loggable
        AuthorisationSuccessfulEvent event = new AuthorisationSuccessfulEvent(this,
                TEST_GUID, "test auth success event");

        // create spy (for behaviour testing)
        AuthorisationSuccessfulEvent spy = spy(event);

        // test with logger not set to debug
        when(logger.isDebugEnabled()).thenReturn(false);

        // invoke target
        bean.onApplicationEvent(spy);

        // verify behaviour
        verify(logger, times(1)).isDebugEnabled();
        verify(spy, never()).getMessage();
        verify(spy, never()).getGuid();
        verify(logger, never()).debug(isA(String.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.event.listener.LoggingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventNoOpAuthorisationFailure() {
        // set up a success event as not info loggable
        AuthorisationFailedEvent event = new AuthorisationFailedEvent(this,
                TEST_GUID, "test auth failure event");

        // create spy (for behaviour testing)
        AuthorisationFailedEvent spy = spy(event);

        // test with logger not set to debug
        when(logger.isDebugEnabled()).thenReturn(false);

        // invoke target
        bean.onApplicationEvent(spy);

        // verify behaviour
        verify(logger, times(1)).isDebugEnabled();
        verify(spy, never()).getMessage();
        verify(spy, never()).getGuid();
        verify(logger, never()).debug(isA(String.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.event.listener.LoggingEventListener#onApplicationEvent(org.springframework.context.ApplicationEvent)}.
     */
    @Test
    public void testOnApplicationEventIgnore() {
        // invoke target with a Spring container event
        bean.onApplicationEvent(new ContextStartedEvent(applicationContext));

        // verify behaviour
        verify(logger, never()).debug(isA(String.class));
    }

}
