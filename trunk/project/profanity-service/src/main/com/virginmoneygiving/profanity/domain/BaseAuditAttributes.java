package com.virginmoneygiving.profanity.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

/**
 * Base Class holding basic audit attributes.
 * <p>
 * All domain class requiring these audit attributes, would extend this class.
 * <p>
 * Whenever a new object is persisted, the {@link BaseAuditAttributes#prePersist()}
 * is called and {@link BaseAuditAttributes#createdDateTime} is populated with
 * current time stamp.
 *
 * @author Puneet Swarup
 */
@MappedSuperclass
public class BaseAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225234659L;

    /** Record creation date time. */
    private Timestamp createdDateTime = null;

    /**
     * Method called before persisting this or sub-class object.
     */
    @PrePersist
    public void prePersist() {
        this.createdDateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Gets the created date time.
     *
     * @return the createdDateTime
     */
    @Column(name = "CREATED_DATETIME")
    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the created date time.
     *
     * @param createdDateTime the createdDateTime to set
     */
    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     *
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {
        return new StringBuilder("createdDateTime = ")
            .append(createdDateTime).toString();
    }
}
