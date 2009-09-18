package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Regular Donation Payment Collected Batch
 * details.
 * 
 * @author Tarun Adiwal.
 */
public class WebRegularDonationPaymentCollectedBatch implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;
    
    /** *. */
    private List<WebRegularDonationPaymentCollected> regularDonation;

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
     * Gets the regular donation.
     * 
     * @return the regularDonation
     */
    public List<WebRegularDonationPaymentCollected> getRegularDonation() {
        return regularDonation;
    }

    /**
     * Sets the regular donation.
     * 
     * @param regularDonation the regularDonation to set
     */
    public void setRegularDonation(
            List<WebRegularDonationPaymentCollected> regularDonation) {
        this.regularDonation = regularDonation;
    }
    
}
