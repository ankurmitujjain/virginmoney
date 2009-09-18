package com.virginmoneygiving.schedulerservice.batch;

/**
 * The Quartz {@link org.quartz.JobDataMap} key names used to pass data between
 * {@link org.quartz.Trigger}, {@link org.quartz.JobDetail} and our
 * {@link BatchJobRequestJob}.
 * @see BatchJobCronTrigger
 * @see CronTrigger
 * @see JobDataMap
 * @author John Allen (john.allen@opsera.com)
 */
public enum BatchJobJobDataMapKeys {
    
    /** data keys. */
    BATCH_JOB_TYPE("jobName"), 
 /** The JM s_ destinatio n_ jnd i_ name. */
 JMS_DESTINATION_JNDI_NAME("jmsDestinationJndiName");

    /** String representation of the {@link JobDataMap} key name. */
    private String keyName;

    /**
     * Constructor.
     * @param name the string representation for this enum.
     */
    BatchJobJobDataMapKeys(final String name) {
        this.keyName = name;
    }

    /**
     * Returns the string representation of this enum.
     * @return the string
     */
    public String getKeyName() {
        return keyName;
    }

}
