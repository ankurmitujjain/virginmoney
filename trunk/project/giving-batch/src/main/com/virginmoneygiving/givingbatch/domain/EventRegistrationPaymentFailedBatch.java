package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Event Registration Payment Failed Batch details.
 * 
 * @author Yogesh Salunkhe
 */
public class EventRegistrationPaymentFailedBatch implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Batch instance. */
    private Batch batch;

    /** Batch to send to GLIS. */
    private List<EventRegistrationPaymentFailed> eventRegistrationPaymentFailed;


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
     * Getter for eventRegistrationPaymentFailed.
     * 
     * @return eventRegistrationPaymentFailed
     */
    public List<EventRegistrationPaymentFailed> getEventRegistrationPaymentFailed() {
        return eventRegistrationPaymentFailed;
    }

    /**
     * Setter for eventRegistrationPaymentFailed.
     * 
     * @param eventRegistrationPaymentFailed value to set
     */
    public void setEventRegistrationPaymentFailed(
            List<EventRegistrationPaymentFailed> eventRegistrationPaymentFailed) {
        this.eventRegistrationPaymentFailed = eventRegistrationPaymentFailed;
    }

}
