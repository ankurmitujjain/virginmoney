package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The data object that represents the alert user to be persisted.
 * 
 * @author Rahul Vaidya
 */
@Entity
@Table(name = "ALERT_USER")
public class AlertUser extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 58965896858741L;

    /** Primary key for alert. */
    private Long id;

    /** Alert. */
    private AlertTrigger alertTrigger;

    /** Charity administrator. */
    private CharityAdministrator charityAdministrator;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the alert trigger.
     * 
     * @return the alert trigger
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ALERT_TRIGGER_ID")
    public AlertTrigger getAlertTrigger() {
        return alertTrigger;
    }

    /**
     * Sets the alert trigger.
     * 
     * @param alertTrigger the alert trigger to set
     */
    public void setAlertTrigger(AlertTrigger alertTrigger) {
        this.alertTrigger = alertTrigger;
    }

    /**
     * Gets the charity administrator.
     * 
     * @return the charityAdministrator
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ADMINISTRATOR_ID")
    public CharityAdministrator getCharityAdministrator() {
        return charityAdministrator;
    }

    /**
     * Sets the charity administrator.
     * 
     * @param charityAdministrator the charityAdministrator to set
     */
    public void setCharityAdministrator(
            CharityAdministrator charityAdministrator) {
        this.charityAdministrator = charityAdministrator;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("AlertUser ( ").append("id = ")
                .append(this.id).append(tab).append(
                        "charityAdministrator = ").append(
                        this.charityAdministrator).append(tab).append(
                        super.toString()).append(" )").toString();
    }
}
