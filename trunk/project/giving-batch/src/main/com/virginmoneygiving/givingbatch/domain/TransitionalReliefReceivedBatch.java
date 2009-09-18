
package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain Java class for TransitionalReliefReceivedBatch complex type.
 */
public class TransitionalReliefReceivedBatch implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3751426064559992862L;
	
	/** The transitional relief received. */
	protected List<TransitionalReliefReceived> transitionalReliefReceived;

    /** Message Header. */
    private MessageHeader messageHeader;
    
    /** Batch. */
    private Batch batch;

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

    /**
     * Getter for batch.
     * 
     * @return the batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Setter for batch.
     * 
     * @param batch the new batch
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * Gets the transitional relief received.
     * 
     * @return the transitional relief received
     */
    public List<TransitionalReliefReceived> getTransitionalReliefReceived() {
        if (transitionalReliefReceived == null) {
            transitionalReliefReceived = new ArrayList<TransitionalReliefReceived>();
        }
        return this.transitionalReliefReceived;
    }

	/**
	 * Sets the transitional relief received.
	 * 
	 * @param transitionalReliefReceived the new transitional relief received
	 */
	public void setTransitionalReliefReceived(
			List<TransitionalReliefReceived> transitionalReliefReceived) {
		this.transitionalReliefReceived = transitionalReliefReceived;
	}

}
