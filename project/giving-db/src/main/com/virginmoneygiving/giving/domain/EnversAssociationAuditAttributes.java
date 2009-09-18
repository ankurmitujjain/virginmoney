package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.envers.NotAudited;

import com.virginmoneygiving.giving.entitylisterer.ControlDataEntityListener;

/**
 * Class holding all the association attributes.
 * <p>
 * All domain class requiring control column attributes, would extend this
 * class.
 * <p>
 * Whenever a new object is persisted, the
 * {@link EnversAssociationAuditAttributes#preAssociate()} is called and
 * {@link EnversAssociationAuditAttributes#startDatetime} is populated with
 * current time stamp.
 * 
 * @author taruna
 */
@MappedSuperclass
@EntityListeners(ControlDataEntityListener.class)
public class EnversAssociationAuditAttributes extends EnversAuditAttributes implements
        Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    

    /** Start date time. */
    private Timestamp startDatetime;

    /** End date time. */
    private Timestamp endDatetime;

    /**
     * Method called before persist of this or sub-class object.
     */
    @PrePersist
    public void preAssociate() {
        this.startDatetime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Gets the start datetime.
     * 
     * @return the startDatetime
     */
    @Column(name = "START_DATETIME")
    @NotAudited
    public Timestamp getStartDatetime() {
        return startDatetime;
    }

    /**
     * Sets the start datetime.
     * 
     * @param startDatetime the startDatetime to set
     */
    public void setStartDatetime(Timestamp startDatetime) {
        this.startDatetime = startDatetime;
    }

    /**
     * Gets the end datetime.
     * 
     * @return the endDatetime
     */
    @Column(name = "END_DATETIME")
    @NotAudited
    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    /**
     * Sets the end datetime.
     * 
     * @param endDatetime the endDatetime to set
     */
    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder(super.toString())
            .append(tab).append("startDatetime = ").append(startDatetime)
            .append(tab).append("endDatetime = ").append(endDatetime)
            .toString();
    }


}
