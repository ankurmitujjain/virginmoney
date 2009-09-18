
package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Domain class for TransitionalReliefClaimedBatch complex type.
 */
/**
 * @author Ian Priest
 *
 */
public class TransitionalReliefClaimedBatch implements Serializable {

    /** Serial Version Id. */
	private static final long serialVersionUID = -5918134196375907952L;
	
	/** The TransactionalRelief Records. */
	protected List<TransitionalReliefClaimed> transitionalReliefClaimed;

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
     * Gets the value of the transitionalReliefClaimed property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransitionalReliefClaimed }
     * 
     * @return the transitional relief claimed
     */
    public List<TransitionalReliefClaimed> getTransitionalReliefClaimed() {
        if (transitionalReliefClaimed == null) {
            transitionalReliefClaimed = new ArrayList<TransitionalReliefClaimed>();
        }
        return this.transitionalReliefClaimed;
    }

	/**
	 * Sets the transitional relief claimed.
	 * 
	 * @param transitionalReliefClaimed the new transitional relief claimed
	 */
	public void setTransitionalReliefClaimed(
			List<TransitionalReliefClaimed> transitionalReliefClaimed) {
		this.transitionalReliefClaimed = transitionalReliefClaimed;
	}

    
}
