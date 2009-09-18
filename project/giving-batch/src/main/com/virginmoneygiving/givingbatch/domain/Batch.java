package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The data object representing Batch details.
 * 
 * @author Siva Kumar
 */
public class Batch implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** batch number of type STring. */
    private String batchNumber;

    /** batchDate of type TimeStamp. */
    private Timestamp batchDate;

    /** batch Type of type STring. */
    private String batchType;

    /** batch Chunk Id of type String. */
    private String batchChunkId;

    /** processBatchOnErrors of type boolean. */
    private Boolean processBatchOnErrors;

    /**
     * Gets the batch number.
     * 
     * @return the batch number
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the batch number.
     * 
     * @param batchNumber the new batch number
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the batch date.
     * 
     * @return the batchDate
     */
    public Timestamp getBatchDate() {
        return batchDate;
    }

    /**
     * Sets the batch date.
     * 
     * @param batchDate the batchDate to set
     */
    public void setBatchDate(Timestamp batchDate) {
        this.batchDate = batchDate;
    }

    /**
     * Gets the batch type.
     * 
     * @return the batchType
     */
    public String getBatchType() {
        return batchType;
    }

    /**
     * Sets the batch type.
     * 
     * @param batchType the batchType to set
     */
    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    /**
     * Gets the batch chunk id.
     * 
     * @return the batchChunkId
     */
    public String getBatchChunkId() {
        return batchChunkId;
    }

    /**
     * Sets the batch chunk id.
     * 
     * @param batchChunkId the batchChunkId to set
     */
    public void setBatchChunkId(String batchChunkId) {
        this.batchChunkId = batchChunkId;
    }

    /**
     * Gets the process batch on errors.
     * 
     * @return the processBatchOnErrors
     */
    public Boolean getProcessBatchOnErrors() {
        return processBatchOnErrors;
    }

    /**
     * Sets the process batch on errors.
     * 
     * @param processBatchOnErrors the processBatchOnErrors to set
     */
    public void setProcessBatchOnErrors(Boolean processBatchOnErrors) {
        this.processBatchOnErrors = processBatchOnErrors;
    }

}
