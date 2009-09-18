package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Charity Batch Details.
 * 
 * @author taruna
 */
public class CharitiesBatch implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** Message Header. */
    private MessageHeader messageHeader;
    
    /**
     * Getter for messageHeader.
     * 
     * @return the messageHeader
     */
    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    /**
     * Setter for messageHeader.
     * 
     * @param messageHeader the new messageHeader
     */
    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    /** charities - List of Charities. */
    private List<Charities> charities;

    /**
     * Gets the batch.
     * 
     * @return the batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Sets the batch.
     * 
     * @param batch the batch to set
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * Gets the charities.
     * 
     * @return the charities
     */
    public List<Charities> getCharities() {
        return charities;
    }

    /**
     * Sets the charities.
     * 
     * @param charities the charities to set
     */
    public void setCharities(List<Charities> charities) {
        this.charities = charities;
    }

}
