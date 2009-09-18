package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The data object that represents the activity type data to be persisted.
 * 
 * @author Esakki Yadav.
 */

// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "ACTIVITY_TYPE")
public class ActivityType extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 7445352700087130446L;

    /** Activity Type Code. */
    private String code;

    /** Description ActivityType. */
    private String description;

    /** creating FundraisingReason. */
    private FundraisingReason fundraisingReason;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the fundraising reason.
     * 
     * @return the fundraisingReason object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISING_REASON_CODE")
    public FundraisingReason getFundraisingReason() {
        return fundraisingReason;
    }

    /**
     * Sets the fundraising reason.
     * 
     * @param fundraisingReason the fundraisingReason to set
     */
    public void setFundraisingReason(final FundraisingReason fundraisingReason) {
        this.fundraisingReason = fundraisingReason;
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

        final String tab = "    ";
        return new StringBuilder("ActivityType ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append("fundraisingReason = ").append(fundraisingReason).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
