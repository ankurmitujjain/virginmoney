package com.virginmoneygiving.schedulerservice.jmx;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import static org.mockito.Mockito.*;

/**
 * This class TestBatchJobRequestJob is used to test the methods inside this
 * class for jms schedular. it act as a mock object where without any interaction
 * with databse we are able to test the method with its required output.
 * @author jallen
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestJMXSchedulerService {

    /** unit under test. */
    private JMXSchedulerService bean;

    /** mock scheduler. */
    @Mock
    private Scheduler mockScheduler;

    /** mock trigger. */
    @Mock
    private Trigger mockTrigger;

    /** mock cron trigger. */
    @Mock
    private CronTrigger mockCronTrigger;

    /**
     * Sets the up.
     * @throws Exception error occurred
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bean = new JMXSchedulerService();
        bean.setScheduler(mockScheduler);
    }

    /**
     * Test the FQN splitter.
     */
    @Test
    public void testSplitTriggerFQN() {
        int i = 0;
        try {
            JMXSchedulerService.splitTriggerFQN("foo");
            fail("Supplied FQN was illegal, yet no complaint from split method");
        } catch (IllegalArgumentException e) {
            i = i + 1;
        }
        try {
            JMXSchedulerService.splitTriggerFQN(".");
            fail("Supplied FQN was illegal, yet no complaint from split method");
        } catch (IllegalArgumentException e) {
            i = i + 1;
        }
        try {
            JMXSchedulerService.splitTriggerFQN("foo.");
            fail("Supplied FQN was illegal, yet no complaint from split method");
        } catch (IllegalArgumentException e) {
            i = i + 1;
        }
        try {
            JMXSchedulerService.splitTriggerFQN(".foo");
            fail("Supplied FQN was illegal, yet no complaint from split method");
        } catch (IllegalArgumentException e) {
            i = i + 1;
        }
        try {
            JMXSchedulerService.splitTriggerFQN("foo.bar.foo");
            fail("Supplied FQN was illegal, yet no complaint from split method");
        } catch (IllegalArgumentException e) {
            i = i + 1;
        }
        try {
            String[] part = JMXSchedulerService.splitTriggerFQN("foo.bar");
            assertEquals("FQN group part wrong", "foo", part[0]);
            assertEquals("FQN name part wrong", "bar", part[1]);

            part = JMXSchedulerService.splitTriggerFQN("f.b");
            assertEquals("FQN group part wrong", "f", part[0]);
            assertEquals("FQN name part wrong", "b", part[1]);

        } catch (IllegalArgumentException e) {
            fail("Supplied FQN was legal but we got an exception");
        }
    }

    /**
     * Tests a method on the service/component.
     * 
     * @throws Exception error occurred
     */
    @Test
    public void testSchedulerShutdownNice() throws Exception {
        bean.shutdownSheduler(true);
        verify(mockScheduler).shutdown(true);
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testSchedulerShutdownNoWait() throws Exception {
        bean.shutdownSheduler(false);
        verify(mockScheduler).shutdown(false);
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testSchedulerStandby() throws Exception {
        bean.standbyScheduler();
        verify(mockScheduler).standby();
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testSchedulerStart() throws Exception {
        bean.startScheduler();
        verify(mockScheduler).start();
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testSchedulerName() throws Exception {
        when(mockScheduler.getSchedulerName()).thenReturn("foo");
        assertEquals("foo", bean.showSchedulerName());
        verify(mockScheduler).getSchedulerName();
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testSchedulerState() throws Exception {
        when(mockScheduler.isStarted()).thenReturn(true).thenReturn(false)
            .thenReturn(false).thenReturn(false);
        when(mockScheduler.isInStandbyMode()).thenReturn(true)
            .thenReturn(false).thenReturn(false);
        when(mockScheduler.isShutdown()).thenReturn(true).thenReturn(false);

        assertEquals("running", bean.showSchedulerState());
        assertEquals("standing-by", bean.showSchedulerState());
        assertEquals("shutdown", bean.showSchedulerState());
        assertEquals("unknown", bean.showSchedulerState());

        // CHECKSTYLE:OFF
        verify(mockScheduler, times(4)).isStarted();  
        verify(mockScheduler, times(3)).isInStandbyMode(); 
        verify(mockScheduler, times(2)).isShutdown();
        // CHECKSTYLE:ON
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testGetTriggerNull() throws SchedulerException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
                .thenReturn(null);

        try {
            bean.getTrigger("foo.bar");
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
            assertTrue(expected.getMessage().contains("foo"));
        }
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testGetTrigger() throws Exception {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);

        bean.getTrigger("foo.bar");
        verify(mockScheduler).getTrigger(anyString(), anyString());
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testPauseTrigger() throws Exception {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);

        bean.pauseTrigger("foo.bar");
        verify(mockScheduler).getTrigger(anyString(), anyString());
        verify(mockScheduler).pauseTrigger(anyString(), anyString());
    }

    /**
     * Tests a method on the service/component.
     * @throws Exception error occurred
     */
    @Test
    public void testResumeTrigger() throws Exception {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);

        bean.resumeTrigger("foo.bar");
        verify(mockScheduler).getTrigger(anyString(), anyString());
        verify(mockScheduler).resumeTrigger(anyString(), anyString());
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testPauseTriggerGroup() throws SchedulerException {
        bean.pauseTriggerGroup("foo");
        verify(mockScheduler).pauseTriggerGroup("foo");
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testResumeTriggerGroup() throws SchedulerException {
        bean.resumeTriggerGroup("foo");

        verify(mockScheduler).resumeTriggerGroup("foo");
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testShowTriggerState() throws SchedulerException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);

        when(mockTrigger.getName()).thenReturn("bar");
        when(mockTrigger.getGroup()).thenReturn("foo");

        when(mockScheduler.getTriggerState(anyString(), anyString()))
            .thenReturn(Trigger.STATE_NORMAL)
            .thenReturn(Trigger.STATE_BLOCKED)
            .thenReturn(Trigger.STATE_COMPLETE)
            .thenReturn(Trigger.STATE_ERROR)
            .thenReturn(Trigger.STATE_PAUSED)
            .thenReturn(-1);

        assertEquals("normal", bean.showTriggerState("foo.bar"));
        assertEquals("blocked", bean.showTriggerState("foo.bar"));
        assertEquals("complete", bean.showTriggerState("foo.bar"));
        assertEquals("error", bean.showTriggerState("foo.bar"));
        assertEquals("paused", bean.showTriggerState("foo.bar"));
        assertEquals("unknown", bean.showTriggerState("foo.bar"));

        // CHECKSTYLE:OFF
        verify(mockScheduler, times(6)).getTrigger(anyString(), anyString());
        verify(mockTrigger, times(6)).getName();
        verify(mockTrigger, times(6)).getGroup();
        verify(mockScheduler, times(6)).getTriggerState(anyString(), anyString());
        // CHECKSTYLE:ON
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testGetCronTriggerWrongType() throws SchedulerException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);

        try {
            bean.getCronTrigger("foo.bar");
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }

        verify(mockScheduler).getTrigger(anyString(), anyString());
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testGetCronTrigger() throws SchedulerException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockCronTrigger);

        bean.getCronTrigger("foo.bar");

        verify(mockScheduler).getTrigger(anyString(), anyString());
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testShowCronTriggerCronExpression() throws SchedulerException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockCronTrigger);
        when(mockCronTrigger.getCronExpression())
            .thenReturn("0 0/5 * * * ?");

        assertEquals("0 0/5 * * * ?", bean.showCronTriggerCronExpression("foo.bar"));

        verify(mockScheduler).getTrigger(anyString(), anyString());
        verify(mockCronTrigger).getCronExpression();
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testListAllTriggersNone() throws SchedulerException {
        when(mockScheduler.getTriggerGroupNames()).thenReturn(new String[]{});
        bean.listAllTriggers();
        verify(mockScheduler).getTriggerGroupNames();
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testListAllTriggers() throws SchedulerException {
        when(mockScheduler.getTriggerGroupNames()).thenReturn(new String[]{"foo"});
        when(mockScheduler.getTriggerNames("foo")).thenReturn(new String[]{"bar"});
        bean.listAllTriggers();
        verify(mockScheduler).getTriggerGroupNames();
        verify(mockScheduler).getTriggerNames("foo");
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testListTriggerGroups() throws SchedulerException {
        when(mockScheduler.getTriggerGroupNames()).thenReturn(new String[]{});
        bean.listTriggerGroups();
        verify(mockScheduler).getTriggerGroupNames();
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testListTriggersInGroup() throws SchedulerException {
        when(mockScheduler.getTriggerNames("foo")).thenReturn(new String[]{});
        bean.listTriggersInGroup("foo");
        verify(mockScheduler).getTriggerNames("foo");
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     * @throws ParseException error occurred.
     */
    @Test
    public void testRescheduleCronTrigger() throws SchedulerException, ParseException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockCronTrigger);
        when(mockCronTrigger.getCronExpression())
            .thenReturn("0 0/5 * * * ?");

        bean.rescheduleCronTrigger("foo.bar", "0 5/15 * * * ?");

        verify(mockScheduler).getTrigger(anyString(), anyString());
        verify(mockCronTrigger).getCronExpression();
        verify(mockCronTrigger).setCronExpression("0 5/15 * * * ?");
        //verify(mockScheduler).unscheduleJob(anyString(), anyString());
        //verify(mockScheduler).scheduleJob(mockCronTrigger);
        //verify(mockScheduler).rescheduleJob(anyString(), anyString(), mockCronTrigger);
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testShowEmptyTriggerDataMap() throws SchedulerException {
        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);
        when(mockTrigger.getJobDataMap()).thenReturn(new JobDataMap());

        String mapAsString = bean.showTriggerDataMap("foo.bar");
        assertNotNull(mapAsString);
        assertEquals("[]", mapAsString);
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testShowTriggerDataMapOneEntry() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("foo", "bar");

        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);
        when(mockTrigger.getJobDataMap()).thenReturn(jobDataMap);

        String mapAsString = bean.showTriggerDataMap("foo.bar");
        assertNotNull(mapAsString);
        assertTrue(mapAsString.startsWith("["));
        assertTrue(mapAsString.endsWith("]"));
        assertTrue(mapAsString.contains("{foo=bar}"));
    }

    /**
     * Tests a method on the service/component.
     * @throws SchedulerException error occurred
     */
    @Test
    public void testShowTriggerDataMapTwoEntries() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("foo", "bar");
        jobDataMap.put("cafe", "bar");

        when(mockScheduler.getTrigger(anyString(), anyString()))
            .thenReturn(mockTrigger);
        when(mockTrigger.getJobDataMap()).thenReturn(jobDataMap);

        String mapAsString = bean.showTriggerDataMap("foo.bar");
        assertNotNull(mapAsString);
        assertTrue(mapAsString.startsWith("["));
        assertTrue(mapAsString.endsWith("]"));
        assertTrue(mapAsString.contains("{foo=bar}"));
        assertTrue(mapAsString.contains("{cafe=bar}"));
        assertTrue(mapAsString.contains(","));
    }

}
