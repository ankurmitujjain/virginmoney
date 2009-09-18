package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Failed Donation Payment Batch details.
 * 
 * @author Siva Kumar
 */
public class FailedDonationPaymentBatch implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** PaymentInitiated instance. */
    private List<FailedPayment> failedPayment;

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

    /**
     * Sets the failed payment.
     * 
     * @param failedPayment the failed payment
     */
    public void setFailedPayment(List<FailedPayment> failedPayment) {
        this.failedPayment = failedPayment;
    }

    /**
     * Gets the failed payment.
     * 
     * @return the list of failed payment
     */
    public List<FailedPayment> getFailedPayment() {
        return failedPayment;
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

}
