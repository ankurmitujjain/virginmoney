package com.virginmoneygiving.giving.entitylisterer;

import org.apache.log4j.Logger;

/**
 * Thread Local storage of control data.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ThreadLocalControlDataStorage {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(ThreadLocalControlDataStorage.class);

    /** ThreadLocal. */
    private static ThreadLocal<ControlData> threadLocalControlData = new ThreadLocal<ControlData>();

    /**
     * Set the thread local copy of the Control Data.
     * 
     * @param controlData the control data
     */
    public static void set(ControlData controlData) {
        LOGGER.info("Setting control data to thread local");
        threadLocalControlData.set(controlData);
    }

    /**
     * Access the thread local copy of the Control Data.
     * 
     * @return the control data
     */
    public static ControlData get() {
        LOGGER.info("Retrieving control data from thread local");
        return threadLocalControlData.get();
    }
}
