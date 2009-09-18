package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Transaction Charge Batch.
 * 
 * @author taruna
 */
public class TransactionChargePaidBatch implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** Batch instance. */
    private List<TransactionChargePaid> transactionCharge;

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
     * Gets the transaction charge.
     * 
     * @return the transactionCharge
     */
    public List<TransactionChargePaid> getTransactionCharge() {
        return transactionCharge;
    }

    /**
     * Sets the transaction charge.
     * 
     * @param transactionCharge the transactionCharge to set
     */
    public void setTransactionCharge(
            List<TransactionChargePaid> transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

}
