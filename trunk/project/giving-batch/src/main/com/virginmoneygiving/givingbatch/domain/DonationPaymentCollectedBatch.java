package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Payment Collection Batch details.
 * 
 * @author taruna
 */
public class DonationPaymentCollectedBatch implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** webDonationPayments. */
    private List<DonationPaymentCollected> webDonationPayments;

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
     * Gets the web donation payments.
     * 
     * @return the webDonationPayments
     */
    public List<DonationPaymentCollected> getWebDonationPayments() {
        return webDonationPayments;
    }

    /**
     * Sets the web donation payments.
     * 
     * @param webDonationPayments the webDonationPayments to set
     */
    public void setWebDonationPayments(
            List<DonationPaymentCollected> webDonationPayments) {
        this.webDonationPayments = webDonationPayments;
    }

}
