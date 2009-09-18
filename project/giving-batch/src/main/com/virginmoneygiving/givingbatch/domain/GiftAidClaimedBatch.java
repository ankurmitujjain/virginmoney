package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Gift Aid Claimed Batch details.
 * 
 * @author taruna
 */
public class GiftAidClaimedBatch implements Serializable {

    /** serial version Id. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** Transaction. */
    private List<Transaction> giftAidClaimed;

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
     * Gets the gift aid claimed.
     * 
     * @return the giftAidClaimed
     */
    public List<Transaction> getGiftAidClaimed() {
        return giftAidClaimed;
    }

    /**
     * Sets the gift aid claimed.
     * 
     * @param giftAidClaimed the giftAidClaimed to set
     */
    public void setGiftAidClaimed(List<Transaction> giftAidClaimed) {
        this.giftAidClaimed = giftAidClaimed;
    }

}
