package com.virginmoneygiving.givingbatchcontroller.common;

import java.util.Map;
import java.util.Set;

/**
 * Interface for the helper class.
 * User: choprah
 * Date: 01-May-2009
 * Time: 14:58:30
 */
public interface BatchAssimilatorHelperInterface {
    
    /**
     * Gets the threadInitiationJobMap property.
     * 
     * @return Map  threadInitiationJobMap
     */
    Map<String, String> getThreadInitiationJobMap();

    /**
     * Sets the threadInitiationJobMap property.
     * 
     * @param threadInitiationJobMap Map to set
     */
    void setThreadInitiationJobMap(Map<String, String> threadInitiationJobMap);

    /**
     * Retrieves the job associated with the thread specified.
     * 
     * @param threadName Thread to look up
     * 
     * @return String value
     */
    String fetchInitialJob(String threadName);
    
    /**
     * Gets the threadGroupMap property.
     * 
     * @return threadGroupMap Map
     */
    Map<String, String> getThreadGroupMap();

    /**
     * Sets the threadGroupMap property.
     * 
     * @param threadGroupMap map to set
     */
    void setThreadGroupMap(Map<String, String> threadGroupMap);

    /**
     * Retrieves the threads for the specified set.
     * 
     * @param setName Code to retrieve
     * 
     * @return String value
     */
    String fetchThreadGroup(String setName);
    
    /**
     * Gets the threadGroupRelationshipMap property.
     * 
     * @return Map value
     */
    Map<String, String> getThreadGroupRelationshipMap();

    /**
     * Sets the threadGroupRelationshipMap property.
     * 
     * @param threadGroupRelationshipMap Map to set
     */
    void setThreadGroupRelationshipMap(Map<String, String> threadGroupRelationshipMap);

    /**
     * Retrieves the next set for the specified set.
     * 
     * @param setName Code to retrieve
     * 
     * @return String value
     */
    String fetchNextGroup(String setName);
    
    /**
     * Gets the jobSequenceMap property.
     * 
     * @return jobSequenceMap Map
     */
    Map<String, String> getJobSequenceMap();

    /**
     * Sets the jobSequenceMap property.
     * 
     * @param jobSequenceMap map to set
     */
    void setJobSequenceMap(Map<String, String> jobSequenceMap);

    /**
     * Retrieves the next job name from the map.
     * 
     * @param jobName Code to retrieve
     * 
     * @return String value
     */
    String fetchNextJob(String jobName);
    
    /**
     * Retrieves the set that the given thread belongs to.
     * 
     * @param threadName name of the thread
     * 
     * @return String set name
     */
    String fetchSetForThread(String threadName);

    /**
     * Gets the startupThreadGroup property.
     * 
     * @return String value
     */
    String getStartupThreadGroup();

    /**
     * Finds the next set to execute.
     * 
     * @param currentSet Current set that has been completed.
     * 
     * @return String next set
     */
    String findNextSet(Set<String> currentSet);

    /**
     * Creates and returns a unique Batch Run ID.
     * 
     * @return String unique value
     */
    String getNewBatchRunId();
}
