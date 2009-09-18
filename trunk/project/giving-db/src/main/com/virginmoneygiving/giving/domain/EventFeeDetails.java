package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents Event subscription fee details.
 * 
 * @author Sushant Sawant
 */
@Entity
@Table(name = "EVENT_FEE_DETAIL")
@Audited
public class EventFeeDetails extends EnversAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -7937225173659753253L;

    /** Primary key for EVENT_FEE_DETAILS. */
    private Long id;

    /** Reference code for Fee. */
    private String feeReference;

    /** Fee Rule or Condition. */
    private String feeSituation;

    /** Fee Amount. */
    private BigDecimal feeAmount;

    /** Fee Currency. */
    private String feeCurrency;

    /** The {@link CharitableActivity} for the activity. */
    private CharitableActivity charitableActivity;

    /** Event. */
    private Event event;

    /**
     * Get id for EventFeeDetails.
     * 
     * @return the id for EventFeeDetails
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Set id for EventFeeDetails.
     * 
     * @param id The id to set for EventFeeDetails
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Fee Reference code.
     * 
     * @return the Fee Reference code
     */
    @Column(name = "FEE_REFERENCE")
    @Audited
    public String getFeeReference() {
        return feeReference;
    }

    /**
     * Set Fee Reference code.
     * 
     * @param feeReference the Fee Reference code
     */
    public void setFeeReference(String feeReference) {
        this.feeReference = feeReference;
    }

    /**
     * Get Fee Situation.
     * 
     * @return the Fee Situation
     */
    @Column(name = "FEE_SITUATION")
    @Audited
    public String getFeeSituation() {
        return feeSituation;
    }

    /**
     * Set Fee Situation.
     * 
     * @param feeSituation the Fee Situation
     */
    public void setFeeSituation(String feeSituation) {
        this.feeSituation = feeSituation;
    }

    /**
     * Set Fee Amount.
     * 
     * @return the fee amount
     */
    @Column(name = "FEE_AMOUNT", columnDefinition = "decimal(19,2)")
    @Audited
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * Get Fee Amount.
     * 
     * @param feeAmount the fee amount
     */
    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * Get Fee Currency.
     * 
     * @return the Fee Currency
     */
    @Column(name = "FEE_CURRENCY")
    @Audited
    public String getFeeCurrency() {
        return feeCurrency;
    }

    /**
     * Set Fee Currency.
     * 
     * @param feeCurrency the Fee Currency
     */
    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    /**
     * Gets the charitable activity.
     * 
     * @return the Charitable Activity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARITABLE_ACTIVITY_ID")
    @NotAudited
    public CharitableActivity getCharitableActivity() {
        return charitableActivity;
    }

    /**
     * Sets the charitable activity.
     * 
     * @param charitableActivity the Charitable Activity
     */
    public void setCharitableActivity(CharitableActivity charitableActivity) {
        this.charitableActivity = charitableActivity;
    }

    /**
     * Gets the event.
     * 
     * @return the event
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    @Audited
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event.
     * 
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("EventFeeDetails ( ")
            .append("id = ").append(this.id).append(tab)
            .append("feeReference = ").append(this.feeReference).append(tab)
            .append("feeSituation = ").append(this.feeSituation).append(tab)
            .append("feeAmount = ").append(this.feeAmount).append(tab)
            .append("feeCurrency = ").append(this.feeCurrency).append(tab)
            .append("charitableActivity = ").append(this.charitableActivity).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
} //End of class
