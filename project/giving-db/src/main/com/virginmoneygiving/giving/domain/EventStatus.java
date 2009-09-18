package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table to maintain names/ description of event status.
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "EVENT_STATUS")
public class EventStatus extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -1L;

    /** Status code- Draft/ Pending/ Published. */
    private String code;

    /** Description of above status code. */
    private String description;

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
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("EventStatus ( ").append("code = ").append(
                code).append(tab).append("description= ").append(description)
                .append(tab).append(super.toString()).append(" )").toString();
    }
}
