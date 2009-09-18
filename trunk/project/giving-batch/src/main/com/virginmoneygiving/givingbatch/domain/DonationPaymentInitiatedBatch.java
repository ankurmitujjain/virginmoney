package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Payment Initiated Batch details.
 * 
 * @author Siva Kumar
 */
public class DonationPaymentInitiatedBatch implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** PaymentInitiated instance. */
    private List<PaymentInitiated> paymentInitiated;

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
     * Gets the payment initiated.
     * 
     * @return the paymentInitiated
     */
    public List<PaymentInitiated> getPaymentInitiated() {
        return paymentInitiated;
    }

    /**
     * Sets the payment initiated.
     * 
     * @param paymentInitiated the paymentInitiated to set
     */
    public void setPaymentInitiated(List<PaymentInitiated> paymentInitiated) {
        this.paymentInitiated = paymentInitiated;
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
