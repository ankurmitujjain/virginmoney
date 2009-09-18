package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Web Donation Payment Failed Batch details.
 * 
 * @author John Allen
 */
public class WebDonationPaymentFailedBatch implements Serializable {
    
    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** Batch instance. */
    private Batch batch;
    
    /** The web donation payment failed. */
    private List<WebDonationPaymentFailed> webDonationPaymentFailed;


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
     * Gets the web donation payment failed.
     * 
     * @return the web donation payment failed
     */
    public List<WebDonationPaymentFailed> getWebDonationPaymentFailed() {
        return webDonationPaymentFailed;
    }

    /**
     * Sets the web donation payment failed.
     * 
     * @param webDonationPaymentFailed the web donation payment failed
     */
    public void setWebDonationPaymentFailed(
            List<WebDonationPaymentFailed> webDonationPaymentFailed) {
        this.webDonationPaymentFailed = webDonationPaymentFailed;
    }

}
