package com.virginmoneygiving.schedulerservice.listeners;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;

import com.virginmoneygiving.schedulerservice.serviceproxy.AlertServiceProxy;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * This class TestBatchJobRequestJob is used to test the methods inside this
 * class for alert event listener. it act as a mock object where without any interaction
 * with databse we are able to test the method with its required output.
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestAlertServiceTriggerListener {

    /** unit under test. */
    private AlertServiceTriggerListener bean;

    /** Mock alert service. */
    @Mock
    private AlertServiceProxy alertService;

    /** Mock trigger. */
    @Mock
    private Trigger trigger;

    /** Mock job execution context. */
    @Mock
    private JobExecutionContext jobExectuionContext;

    /**
     * Sets the up.
     * 
     * @throws Exception error occurred.
     */
    @Before
    public void setUp() throws Exception {
        bean = new AlertServiceTriggerListener();
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tear down.
     * @throws Exception error occurred.
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.schedulerservice.listeners.AlertServiceListener#getName()}.
     */
    @Test
    public void testGetName() {
        bean.getName();
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.schedulerservice.listeners.AlertServiceListener#triggerMisfired(Trigger)}.
     */
    @Test
    public void testTriggerMisfired() {
        bean.setAlertService(alertService);
        bean.triggerMisfired(trigger);
        verify(alertService, times(1)).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.schedulerservice.listeners.AlertServiceListener#triggerFired(Trigger, JobExecutionContext)}.
     */
    @Test
    public void testTriggerFired() {
        bean.setAlertService(alertService);
        bean.triggerFired(trigger, jobExectuionContext);
        verify(alertService, times(0)).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.schedulerservice.listeners.AlertServiceListener#triggerComplete(Trigger, JobExecutionContext, int)}.
     */
    @Test
    public void testTriggerComplete() {
        bean.setAlertService(alertService);
        bean.triggerComplete(trigger, jobExectuionContext, Trigger.INSTRUCTION_SET_TRIGGER_COMPLETE);
        verify(alertService, times(0)).raiseAlert(isA(String.class));
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.schedulerservice.listeners.AlertServiceListener#vetoJobExecution(Trigger, JobExecutionContext)}.
     */
    @Test
    public void testVetoJobExecution() {
        bean.setAlertService(alertService);
        bean.vetoJobExecution(trigger, jobExectuionContext);
        assertFalse("Trigger listener should not veto a job execution", bean.vetoJobExecution(trigger, jobExectuionContext));
    }

}
