package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Event Registration Transaction Fee Paid Batch details.
 * 
 * @author Tarun Adiwal
 */
public class EventRegistrationTransactionFeePaidBatch implements Serializable {

    /** Serial Version Uid. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

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
