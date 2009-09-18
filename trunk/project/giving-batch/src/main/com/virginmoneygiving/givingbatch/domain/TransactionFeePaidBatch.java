package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing the Transaction Fee Batch.
 * 
 * @author taruna
 */
public class TransactionFeePaidBatch implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

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


    /** Transaction Fee Paid. */
    private List<TransactionFeePaid> transactionFee;

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
     * Gets the transaction fee.
     * 
     * @return the transactionFee
     */
    public List<TransactionFeePaid> getTransactionFee() {
        return transactionFee;
    }

    /**
     * Sets the transaction fee.
     * 
     * @param transactionFee the transactionFee to set
     */
    public void setTransactionFee(List<TransactionFeePaid> transactionFee) {
        this.transactionFee = transactionFee;
    }

}
