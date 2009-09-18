package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Gift Aid Received Batch.
 * 
 * @author taruna
 */
public class GiftAidReceivedBatch implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** GiftAidReceived List. */
    private List<GiftAidReceived> giftAidReceived;

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
     * Gets the gift aid received.
     * 
     * @return the giftAidReceived
     */
    public List<GiftAidReceived> getGiftAidReceived() {
        return giftAidReceived;
    }

    /**
     * Sets the gift aid received.
     * 
     * @param giftAidReceived the giftAidReceived to set
     */
    public void setGiftAidReceived(List<GiftAidReceived> giftAidReceived) {
        this.giftAidReceived = giftAidReceived;
    }

}
