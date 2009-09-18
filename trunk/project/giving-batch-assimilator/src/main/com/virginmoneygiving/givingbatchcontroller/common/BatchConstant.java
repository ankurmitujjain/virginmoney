package com.virginmoneygiving.givingbatchcontroller.common;

/**
 * Application constants used within the system..
 * User: choprah
 * Date: 01-May-2009
 * Time: 14:18:54
 */
public final class BatchConstant {
    
    /**
     * Default constructor.
     */
    private BatchConstant() {
    }

    /** The JMS message property name used to specify the batch job we're requesting be run. */
    public static final String JMS_MESSAGE_PROPERTY_NAME_BATCH_JOB_TYPE = "jobName";
    
    /** The property used to identify the Correlation parameter. */
    public static final String SI_CORRELATION_ID_PARAM_NAME  = "vmg_batchsystem_correlationId";
    
    /** Property used to identify the Spring Integration sequence size for aggregation purposes. */
    public static final String SI_SEQUENCE_SIZE_PARAM_NAME   = "springintegration_sequenceSize";
    
    /** Property used to identify the Spring Integration sequence number for aggregation purposes. */
    public static final String SI_SEQUENCE_NUMBER_PARAM_NAME = "springintegration_sequenceNumber";
    
    /** Property which indicates whether the full chain should be executed. */
    public static final String BATCH_PARAMETER_EXECUTE_CHAIN = "execute_batch_chain";
    
    /** The property used to identify the Batch Run ID parameter. */
    public static final String BATCH_PARAMETER_BATCH_RUN_ID = "vmg_batchsystem_batchrunid";

}
