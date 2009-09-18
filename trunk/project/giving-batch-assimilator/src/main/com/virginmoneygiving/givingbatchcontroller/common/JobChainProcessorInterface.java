package com.virginmoneygiving.givingbatchcontroller.common;

import java.util.Map;

/**
 * Interface for the class that provides Chain processing methods..
 * User: choprah
 * Date: 01-May-2009
 * Time: 08:48:37
 */
public interface JobChainProcessorInterface {
    
    /**
     * Processes the next job in the chain, for the given current batch number.
     * 
     * @param batchNumber Currently executing batch.
     * @param parameters Job parameters.
     * 
     * @return boolean success flag.
     */
    boolean processNextJob(String batchNumber, Map<String, Object> parameters);

    /**
     * Processes the batch job requested.
     * 
     * @param jobName batch job to execute.
     * @param parameters Job parameters.
     * 
     * @return boolean success flag.
     */
    boolean processCurrentJob(String jobName, Map<String, Object> parameters);
    
    /**
     * Processes the timeout on an aggregator.
     * 
     * @param jobName batch job last processed.
     * @param parameters Job parameters.
     * 
     * @return boolean success flag.
     */
    boolean processAggregatorTimeout(String jobName, Map<String, Object> parameters);

    /**
     * Starts the complete batch process.
     * 
     * @return boolean result.
     */
    boolean startJobProcess();
}
