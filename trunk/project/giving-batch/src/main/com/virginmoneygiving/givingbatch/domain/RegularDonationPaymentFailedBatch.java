package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Regular Donation Payment Failed Batch details.
 * 
 * @author Yogesh Salunkhe
 */
public class RegularDonationPaymentFailedBatch implements Serializable {
    
    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** Batch instance. */
    private Batch batch;
    
    /** The regular donation payment failed. */
    private List<RegularDonationPaymentFailed> regularDonationPaymentFailed;


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
     * Gets the regular donation payment failed.
     * 
     * @return the regular donation payment failed
     */
    public List<RegularDonationPaymentFailed> getRegularDonationPaymentFailed() {
        return regularDonationPaymentFailed;
    }

    /**
     * Sets the regular donation payment failed.
     * 
     * @param regularDonationPaymentFailed the regular donation payment failed
     */
    public void setRegularDonationPaymentFailed(
            List<RegularDonationPaymentFailed> regularDonationPaymentFailed) {
        this.regularDonationPaymentFailed = regularDonationPaymentFailed;
    }


}
