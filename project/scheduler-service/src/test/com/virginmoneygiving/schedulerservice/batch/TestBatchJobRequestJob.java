package com.virginmoneygiving.schedulerservice.batch;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionException;
import org.springframework.jms.MessageFormatException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.destination.JndiDestinationResolver;

import static org.mockito.Mockito.*;

/**
 * This class TestBatchJobRequestJob is used to test the methods inside this
 * class for jms sender. it act as a mock object where without any interaction
 * with databse we are able to test the method with its required output.
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestBatchJobRequestJob {

    /** unit under test. */
    private BatchJobRequestJob bean;

    /** mock JMS template. */
    @Mock
    private JmsTemplate mockJmsTemplate;

    /** mock jndi resolver. */
    @Mock
    private JndiDestinationResolver mockJndiDestinationResolver;

    /**
     * set up.
     * @throws Exception error occurred
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mockJmsTemplate.getDestinationResolver()).thenReturn(mockJndiDestinationResolver);

        bean = new BatchJobRequestJob();
        bean.setBatchJobServiceJmsTemplate(mockJmsTemplate);
        bean.setBatchJobType("bar");
        bean.setJmsDestinationJndiName("foo");
    }

    /**
     * tear down.
     * @throws Exception error occurred
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.schedulerservice.batch.BatchJobRequestJob#execute(org.quartz.JobExecutionContext)}.
     * @throws Exception error occurred
     */
    @Test
    public void testExecute() throws Exception {
        bean.execute(null);
        verify(mockJmsTemplate).send(anyString(), isA(MessageCreator.class));
    }

    /**
     * Test method for {@link com.virginmoneygiving.schedulerservice.batch.BatchJobRequestJob#execute(org.quartz.JobExecutionContext)}.
     * @throws Exception error occurred
     */
    @Test
    public void testExecuteException() throws Exception {
        doThrow(new MessageFormatException(
                new javax.jms.MessageFormatException("Oops")))
                .when(mockJmsTemplate).send(anyString(), isA(MessageCreator.class));

        try {
            bean.execute(null);
            fail("exception expected");
        } catch (JobExecutionException expected) {
            assertNotNull(expected.getMessage());
            assertNotNull(expected.getCause());
        }

        verify(mockJmsTemplate).send(anyString(), isA(MessageCreator.class));
    }

    /**
     * test jndi resolver mutator.
     */
    @Test
    public void testIncorrectDestinationResolver() {
        when(mockJmsTemplate.getDestinationResolver()).thenReturn(null);

        try {
            bean.setBatchJobServiceJmsTemplate(mockJmsTemplate);
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

}
