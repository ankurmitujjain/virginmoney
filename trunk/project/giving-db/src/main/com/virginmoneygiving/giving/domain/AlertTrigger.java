package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The data object that represents the alert trigger to be persisted.
 * 
 * @author Rahul Vaidya
 */
@Entity
@Table(name = "ALERT_TRIGGER")
public class AlertTrigger extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -79372251736598445L;

    /** Primary key for alert. */
    private Long id;

    /** Charity. */
    private Charity charity;

    /** Alert type. */
    private AlertType alertType;

    /** Threshold amount. */
    private BigDecimal thresholdAmount;

    /** Threshold duration. */
    private Integer thresholdDuration;

    /** Threshold frequency. */
    private Integer thresholdFrequency;

    /** Charity Administrator to whome Alert should be sent . */
    private Set<AlertUser> alertUsers;

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
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the charity to set
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the alert type.
     * 
     * @return the alertType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ALERT_TYPE_CODE")
    public AlertType getAlertType() {
        return alertType;
    }

    /**
     * Sets the alert type.
     * 
     * @param alertType the alertType to set
     */
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    /**
     * Gets the threshold amount.
     * 
     * @return the thresholdAmount
     */
    @Column(name = "THRESHOLD_AMOUNT", columnDefinition = "decimal(19,2)")
    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    /**
     * Sets the threshold amount.
     * 
     * @param thresholdAmount the thresholdAmount to set
     */
    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    /**
     * Gets the threshold duration.
     * 
     * @return the thresholdDuration
     */
    @Column(name = "THRESHOLD_DURATION")
    public Integer getThresholdDuration() {
        return thresholdDuration;
    }

    /**
     * Sets the threshold duration.
     * 
     * @param thresholdDuration the thresholdDuration to set
     */
    public void setThresholdDuration(Integer thresholdDuration) {
        this.thresholdDuration = thresholdDuration;
    }

    /**
     * Gets the threshold frequency.
     * 
     * @return the thresholdFrequency
     */
    @Column(name = "THRESHOLD_FREQUENCY")
    public Integer getThresholdFrequency() {
        return thresholdFrequency;
    }

    /**
     * Sets the threshold frequency.
     * 
     * @param thresholdFrequency the thresholdFrequency to set
     */
    public void setThresholdFrequency(Integer thresholdFrequency) {
        this.thresholdFrequency = thresholdFrequency;
    }

    /**
     * Gets the alert users.
     * 
     * @return the alert triggers.
     */
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ALERT_TRIGGER_ID")
    public Set<AlertUser> getAlertUsers() {
        return this.alertUsers;
    }

    /**
     * Sets the alert users.
     * 
     * @param alertUsers the alert users to set
     */
    public void setAlertUsers(Set<AlertUser> alertUsers) {
        this.alertUsers = alertUsers;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("AlertTrigger ( ")
            .append("id = ").append(this.id).append(tab)
            .append("charity = ").append(this.charity).append(tab)
            .append("alertType = ").append(this.alertType).append(tab)
            .append("thresholdAmount = ").append(this.thresholdAmount).append(tab)
            .append("thresholdDuration = ").append(this.thresholdDuration).append(tab)
            .append("thresholdFrequency = ").append(this.thresholdFrequency).append(tab)
            .append("alertUsers = ").append(this.alertUsers).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
