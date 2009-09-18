package com.virginmoneygiving.givingbatch.common;


import com.virginmoneygiving.givingbatchcontroller.common.BatchAssimilatorHelperInterface;

import java.util.*;

import org.apache.log4j.Logger;

/**
 * Helper class that provides methods to manage the batch chain.
 * User: choprah
 * Date: 01-May-2009
 * Time: 13:35:42
 */
public class BatchAssimilatorHelperImpl implements BatchAssimilatorHelperInterface {

    /** Holds the map that links a Thread with the initial batch job. */
    private Map<String, String> threadInitiationJobMap;
    
    /** Holds the threads for a given set. */
    private Map<String, String> threadGroupMap;
    
    /** Hold the batch status codes and values. */
    private Map<String, String> threadGroupRelationshipMap;
    
    /** Hold the Batch job sequence. */
    private Map<String, String> jobSequenceMap;
   
   /** Holds the relationship between a batch job and it's parent thread. */
    private Map<String, String> jobThreadRelationshipMap;
    
    /** The thread group to start the whole process. */
    private String startupThreadGroup = null;

    /** The JMS message property name used to specify the batch job we're requesting be run. */
    public static final String JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE = "jobName";
    
    /** create instance of logger. */
    private static final Logger LOGGER =
        Logger.getLogger(BatchAssimilatorHelperImpl.class);

    /**
     * Gets the threadInitiationJobMap property.
     * 
     * @return Map  threadInitiationJobMap
     */
    public Map<String, String> getThreadInitiationJobMap() {
        return threadInitiationJobMap;
    }

    /**
     * Sets the threadInitiationJobMap property.
     * 
     * @param threadInitiationJobMap Map to set
     */
    public void setThreadInitiationJobMap(Map<String, String> threadInitiationJobMap) {
        this.threadInitiationJobMap = threadInitiationJobMap;
    }

    /**
     * Retrieves the job associated with the thread specified.
     * 
     * @param threadName Thread to look up
     * 
     * @return String value
     */
    public String fetchInitialJob(String threadName) {
        String bs = threadInitiationJobMap.get(threadName);
        if (bs == null) {
            return null;
        }
        else {
            return bs;
        }
    }

    /**
     * Gets the threadGroupMap property.
     * 
     * @return threadGroupMap Map
     */
    public Map<String, String> getThreadGroupMap() {
        return threadGroupMap;
    }

    /**
     * Sets the threadGroupMap property.
     * 
     * @param threadGroupMap map to set
     */
    public void setThreadGroupMap(Map<String, String> threadGroupMap) {
        this.threadGroupMap = threadGroupMap;
    }

    /**
     * Retrieves the threads for the specified set.
     * 
     * @param setName Code to retrieve
     * 
     * @return String value
     */
    public String fetchThreadGroup(String setName) {
        String bs = threadGroupMap.get(setName);
        if (bs == null) {
            return "";
        }
        else {
            return bs;
        }
    }

    /**
     * Gets the threadGroupRelationshipMap property.
     * 
     * @return Map value
     */
    public Map<String, String> getThreadGroupRelationshipMap() {
        return threadGroupRelationshipMap;
    }

    /**
     * Sets the threadGroupRelationshipMap property.
     * 
     * @param threadGroupRelationshipMap Map to set
     */
    public void setThreadGroupRelationshipMap(Map<String, String> threadGroupRelationshipMap) {
        this.threadGroupRelationshipMap = threadGroupRelationshipMap;
    }

    /**
     * Retrieves the next set for the specified set.
     * 
     * @param setName Code to retrieve
     * 
     * @return String value
     */
    public String fetchNextGroup(String setName) {
        String bs = threadGroupRelationshipMap.get(setName);
        if (bs == null) {
            return "";
        }
        else {
            return bs;
        }
    }

    /**
     * Gets the jobSequenceMap property.
     * 
     * @return jobSequenceMap Map
     */
    public Map<String, String> getJobSequenceMap() {
        return jobSequenceMap;
    }

    /**
     * Sets the jobSequenceMap property.
     * 
     * @param jobSequenceMap map to set
     */
    public void setJobSequenceMap(Map<String, String> jobSequenceMap) {
        this.jobSequenceMap = jobSequenceMap;
    }

    /**
     * Retrieves the next job name from the map.
     * 
     * @param jobName Code to retrieve
     * 
     * @return String value
     */
    public String fetchNextJob(String jobName) {
        String bs = jobSequenceMap.get(jobName);
        if (bs == null) {
            return "";
        }
        else {
            return bs;
        }
    }

    /**
     * Gets the jobThreadRelationshipMap property.
     * 
     * @return jobThreadRelationshipMap Map
     */
    public Map<String, String> getJobThreadRelationshipMap() {
        return jobThreadRelationshipMap;
    }

    /**
     * Sets the jobThreadRelationshipMap property.
     * 
     * @param jobThreadRelationshipMap map to set
     */
    public void setJobThreadRelationshipMap(Map<String, String> jobThreadRelationshipMap) {
        this.jobThreadRelationshipMap = jobThreadRelationshipMap;
    }

    /**
     * Retrieves the thread the job belongs to from the map.
     * 
     * @param jobName Code to retrieve
     * 
     * @return String value
     */
    public String fetchThreadForJob(String jobName) {
        String bs = jobThreadRelationshipMap.get(jobName);
        if (bs == null) {
            return "";
        }
        else {
            return bs;
        }
    }

    /**
     * Retrieves the set that the given thread belongs to.
     * 
     * @param threadName name of the thread
     * 
     * @return String set name
     */
    public String fetchSetForThread(String threadName) {
        if (this.threadGroupMap == null || this.threadGroupMap.isEmpty()) {
            return null;
        }

        Iterator itr = this.threadGroupMap.keySet().iterator();
        while (itr.hasNext()) {
            String key = (String)itr.next();
            String value = this.threadGroupMap.get(key);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(">>>> Key/Value/Pos = " + key + "/" + value + "/" + value.indexOf(threadName));
            }
            if (value.indexOf(threadName) >= 0) {
                return key;
            }
        }
        return null;
    }

    /**
     * Gets the startupThreadGroup property.
     * 
     * @return String value
     */
    public String getStartupThreadGroup() {
        return startupThreadGroup;
    }

    /**
     * Sets the startupThreadGroup property.
     * 
     * @param startupThreadGroup value to set
     */
    public void setStartupThreadGroup(String startupThreadGroup) {
        this.startupThreadGroup = startupThreadGroup;
    }
    
    /**
     * Creates and returns a unique Batch Run ID.
     * 
     * @return String unique value
     */
    public String getNewBatchRunId() {
        String runId = "RUNID-" + UUID.randomUUID().toString();

        return runId;
    }

    /**
     * Finds the next set to execute.
     * 
     * @param currentSet Current set that has been completed.
     * 
     * @return String next set
     */
    public String findNextSet(Set<String> currentSet) {
        Map<String, String> threadGroupMap = getThreadGroupMap();
        Map<String, String> setRelationMap = getThreadGroupRelationshipMap();

        Map<Set, String> controlMap = new HashMap<Set, String>();
        Iterator itr = threadGroupMap.keySet().iterator();
        while (itr.hasNext()) {
            String key = (String)itr.next();
            String value = threadGroupMap.get(key);
            String[] chains = value.split(":");
            Set<String> chainSet = new HashSet<String>();
            for (String chain : chains) {
                chainSet.add(chain);
            }
            String nextSet = setRelationMap.get(key);
            String nextGroup = fetchThreadGroup(nextSet);
            controlMap.put(chainSet, nextGroup);
        }

        String nextThreadSet = controlMap.get(currentSet);
        return nextThreadSet;

    }
}
