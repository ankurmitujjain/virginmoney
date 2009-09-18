
package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain version of the RegistrationFeesBatch service object.
 * 
 * @author ipriest
 */
public class RegistrationFeesBatch implements Serializable {

	
    /** Serializable UID. */
	private static final long serialVersionUID = -7096267240472928326L;

	/** The new Registration fees. */
    private List<RegistrationFee> registrationFee;

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
     * Gets the value of the registrationFee property.
     * 
     * @return the registration fee
     */
    public List<RegistrationFee> getRegistrationFee() {
        if (registrationFee == null) {
            registrationFee = new ArrayList<RegistrationFee>();
        }
        return this.registrationFee;
    }

    /**
     * set the registrationFee property.
     * 
     * @param registrationFee the registration fee
     */
	public void setRegistrationFee(List<RegistrationFee> registrationFee) {
		this.registrationFee = registrationFee;
	}

}
