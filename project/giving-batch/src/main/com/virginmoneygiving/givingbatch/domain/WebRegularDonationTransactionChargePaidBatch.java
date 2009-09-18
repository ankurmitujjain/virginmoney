package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Regular Donation Transaction Charge Paid Batch details.
 * 
 * @author Tarun Adiwal.
 */
public class WebRegularDonationTransactionChargePaidBatch implements Serializable {

    /**
     * Serial Version Uid.
     */
    private static final long serialVersionUID = 1L;
    
    /** Batch instance. */
    private Batch batch;
    
    /** List of TransactionChargePaid. */
    private List<TransactionChargePaid> transactionCharges;

    /**
     * @return the batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * @return the transactionCharges
     */
    public List<TransactionChargePaid> getTransactionCharges() {
        return transactionCharges;
    }

    /**
     * @param transactionCharges the transactionCharges to set
     */
    public void setTransactionCharges(List<TransactionChargePaid> transactionCharges) {
        this.transactionCharges = transactionCharges;
    }

    
}
