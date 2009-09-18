package com.virginmoneygiving.givingbatch.mbean;

/**
 * Interface for the MBean operations to manage jobs from the JMX console.
 * User: choprah
 * Date: 30-Apr-2009
 * Time: 09:48:53
 */
public interface BatchControllerMBeanInterface {

    /**
     * Start batch run.
     * 
     * @return the string
     */
    String startBatchRun();

    /**
     * Start thread group.
     * 
     * @param threadGroupName the thread group name
     * 
     * @return the string
     */
    String startThreadGroup(String threadGroupName);

    /**
     * Start thread.
     * 
     * @param threadName the thread name
     * 
     * @return the string
     */
    String startThread(String threadName);

    /**
     * Start batch job.
     * 
     * @param batchJobName the batch job name
     * @param executeChain the execute chain
     * @param correlationId the correlation id
     * @param sequenceSize the sequence size
     * @param sequenceNumber the sequence number
     * 
     * @return the string
     */
    String startBatchJob(String batchJobName, String executeChain, String correlationId, int sequenceSize,
                         int sequenceNumber);

}
