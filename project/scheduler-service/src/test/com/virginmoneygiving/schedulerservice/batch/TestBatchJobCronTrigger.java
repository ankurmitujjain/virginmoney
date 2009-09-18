package com.virginmoneygiving.schedulerservice.batch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class TestBatchJobCronTrigger is used to test the methods inside this
 * class . it act as a mock object where without any interaction with databse we
 * are able to test the method with its required output.
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestBatchJobCronTrigger {

    /** unit under test. */
    private BatchJobCronTrigger bean;

    /**
     * set up.
     * @throws Exception error occurred
     */
    @Before
    public void setUp() throws Exception {
        bean = new BatchJobCronTrigger();
    }

    /**
     * tear down.
     * @throws Exception error occurred
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.schedulerservice.batch.BatchJobCronTrigger#initialiseJobDataMap()}.
     */
    @Test
    public void testInitialiseJobDataMap() {
        bean.setBatchJobName("foo");
        bean.setBatchJobServiceJmsJndiName("foobar");
        bean.initialiseJobDataMap();
    }
}
