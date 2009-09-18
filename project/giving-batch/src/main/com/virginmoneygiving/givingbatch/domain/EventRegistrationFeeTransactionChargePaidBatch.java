package com.virginmoneygiving.givingbatch.domain;

import java.util.List;

/**
 * The data object representing event registration fee transaction charge paid batch details.
 * 
 * @author Tarun Adiwal.
 */
public class EventRegistrationFeeTransactionChargePaidBatch {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** List of TransactionChargePaid. */
    private List<TransactionChargePaid> transactionCharges;

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
     * Gets the transaction charges.
     * 
     * @return the transactionCharges
     */
    public List<TransactionChargePaid> getTransactionCharges() {
        return transactionCharges;
    }

    /**
     * Sets the transaction charges.
     * 
     * @param transactionCharges the transactionCharges to set
     */
    public void setTransactionCharges(List<TransactionChargePaid> transactionCharges) {
        this.transactionCharges = transactionCharges;
    }
}
