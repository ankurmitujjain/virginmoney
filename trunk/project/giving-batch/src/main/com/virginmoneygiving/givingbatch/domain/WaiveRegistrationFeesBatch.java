package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents the Waive registration fee object.
 * 
 * @author Tarun Adiwal
 */
public class WaiveRegistrationFeesBatch implements Serializable{

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** Batch object.* */
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


    /** WaiveRegistrationFee object list.* */
    private List<WaiveRegistrationFee> registrationFee;

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
     * Gets the registration fee.
     * 
     * @return the registrationFee
     */
    public List<WaiveRegistrationFee> getRegistrationFee() {
        return registrationFee;
    }

    /**
     * Sets the registration fee.
     * 
     * @param registrationFee the registrationFee to set
     */
    public void setRegistrationFee(List<WaiveRegistrationFee> registrationFee) {
        this.registrationFee = registrationFee;
    }

}
