package com.virginmoneygiving.schedulerservice.batch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobDataMap;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.apache.log4j.Logger;

/**
 * Batch request job; This Quartz job dispatches a JMS message targeted at the
 * appropriate batch job service. Note this Quartz job does not wait for, or
 * receive any result from the invoked batch job.
 * @author John Allen (john.allen@opsera.com)
 */
public class BatchJobRequestJob implements Job {

    /** logger. */
    private static final Logger LOGGER = Logger.getLogger(BatchJobRequestJob.class);

    /** The JMS message property name used to specify the batch job we're requesting be run. */
    public static final String JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE = "jobName";

    /** The batch job we're going to invoke. */
    private String batchJobName;

    /** The Batch Job Service JMS destination JNDI name we need to talk to. */
    private String batchJobServiceJndiName;

    /** Spring JmsTemplate, injected by the SpringBeanJobFactory. The name of this attribute is detailed because it is injected by the Scheduler's *global* job factory and not injected directly into the Trigger or Job, thus there may be other jobs requiring different JMS templates and we can only distinguish between them by their name. */
    private transient JmsTemplate batchJobServiceJmsTemplate;

    /**
     * Execute.
     * @param context job context
     * @throws JobExecutionException exception
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {

        // Message is as JMS TextMessage type with the batchJobType specified in
        // a Message header property with the key name
        // JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE
        String tmpJobName = null;
        if (context != null) {
            JobDataMap dataMap = context.getMergedJobDataMap();
            tmpJobName = dataMap.getString(BatchJobJobDataMapKeys.BATCH_JOB_TYPE.getKeyName());
        }
        //LOGGER.debug("Data map size: " + dataMap.size() + ", " + dataMap.toString());
        final String jobName = tmpJobName;

        LOGGER.debug("Executing job Type/name: " + JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE + "/" + jobName);

        try {
            batchJobServiceJmsTemplate.send(getJmsDestinationJndiName(), new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    Message message = session.createTextMessage();
                    message.setStringProperty(JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE, jobName);
                    return message;
                }
            });
        } catch (JmsException e) {
            throw new JobExecutionException("JMS message send failed", e);
        }
    }

    /**
     * The identifier for batch job we're going to invoke.
     * @return the batch job type
     */
    public String getBatchJobName() {
        return this.batchJobName;
    }

    /**
     * The batch job type we want to invoke.
     * @param jobName the job type.
     */
    public void setBatchJobType(final String jobName) {
        this.batchJobName = jobName;
    }

    /**
     * The JMS template used for communicating with the Batch Job service.
     * @return JMS template
     */
    public JmsTemplate getBatchJobServiceJmsTemplate() {
        return this.batchJobServiceJmsTemplate;
    }

    /**
     * Set the JMS template used for communicating with the Batch Job service.
     * This template must support JNDI destination resolving as the actual
     * destination is specified by at runtime.
     * @param jmsTemplate the jms template.
     */
    public void setBatchJobServiceJmsTemplate(JmsTemplate jmsTemplate) {
        if (!(jmsTemplate.getDestinationResolver() instanceof JndiDestinationResolver)) {
            throw new IllegalArgumentException("JMS template must support JNDI destination resolving");
        }
        this.batchJobServiceJmsTemplate = jmsTemplate;
    }

    /**
     * The JNDI name for the Batch Service JMS queue.
     * @return the JNDI name
     */
    public String getJmsDestinationJndiName() {
        return batchJobServiceJndiName;
    }

    /**
     * Sets the JNDI name for the Batch Service JMS queue.
     * @param jmsDestinationJndiName the JNDI name
     */
    public void setJmsDestinationJndiName(String jmsDestinationJndiName) {
        this.batchJobServiceJndiName = jmsDestinationJndiName;
    }

}
