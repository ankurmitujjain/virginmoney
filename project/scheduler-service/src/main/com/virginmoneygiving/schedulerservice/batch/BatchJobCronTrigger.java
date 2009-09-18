package com.virginmoneygiving.schedulerservice.batch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * Simple subclass of {@link CronTriggerBean} that exposes the
 * {@link BatchJobRequestJob} required attributes as POJO attributes rather than
 * relying upon the underlying {@link org.quartz.JobDataMap}.
 * @see CronTriggerBean
 * @author John Allen (john.allen@opsera.com)
 */
public class BatchJobCronTrigger extends CronTriggerBean {

    /** serialisation id. */
    private static final long serialVersionUID = 1677995808369247634L;

    /** The batch job name. */
    private String batchJobName;

    /** The JMS destination JNDI name that identifies the batch job service we need to send a message to. */
    private String batchJobServiceJmsJndiName;

    /**
     * Bean post processor that converts our POJO attributes into a a
     * {@link JobDataMap} suitable for our underlying {@link CronTriggerBean}.
     */
    @PostConstruct
    void initialiseJobDataMap() {
        // only store strings in the data map
        getJobDataMap().put(BatchJobJobDataMapKeys.BATCH_JOB_TYPE.getKeyName(), getBatchJobName());
        getJobDataMap().put(BatchJobJobDataMapKeys.JMS_DESTINATION_JNDI_NAME.getKeyName(), getBatchJobServiceJmsJndiName());
    }

    /**
     * returns our targeted Batch Job service JNDI name.
     * 
     * @return the JMS JNDI name.
     */
    public String getBatchJobServiceJmsJndiName() {
        return this.batchJobServiceJmsJndiName;
    }

    /**
     * Set the JMS Batch Job service JNDI name we need to send a message to.
     * @param jndiName the jndi name
     */
    @Resource
    public void setBatchJobServiceJmsJndiName(String jndiName) {
        this.batchJobServiceJmsJndiName = jndiName;
    }

    /**
     * returns the batch job name.
     * @return the job type
     */
    public String getBatchJobName() {
        return this.batchJobName;
    }

    /**
     * Set the batch job name.
     * @param jobName the job type
     */
    @Resource
    public void setBatchJobName(final String jobName) {
        this.batchJobName = jobName;
    }
}
