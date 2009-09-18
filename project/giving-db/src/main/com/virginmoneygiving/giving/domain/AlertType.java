package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The data object that represents the alert type to be persisted.
 * 
 * @author Rahul Vaidya
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "ALERT_TYPE")
public class AlertType extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 5896589689742L;

    /** Code for bank account type code. */
    private String code;

    /** Description for bank account type description. */
    private String description;

    /** Alert name. */
    private String alertName;

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
     * Gets the alert name.
     * 
     * @return the alertName
     */
    @Column(name = "ALERT_NAME")
    public String getAlertName() {
        return alertName;
    }

    /**
     * Sets the alert name.
     * 
     * @param alertName the alertName to set
     */
    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("AlertType ( ").append("code = ").append(
                this.code).append(tab).append("description = ").append(
                this.description).append(tab).append("alertName = ").append(
                this.alertName).append(tab).append(super.toString()).append(
                " )").toString();
    }

}
