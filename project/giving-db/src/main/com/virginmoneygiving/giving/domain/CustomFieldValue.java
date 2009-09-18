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
 * DTO representing a custom field value.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "CUSTOM_FIELD_VALUE")
public class CustomFieldValue extends AssociationAuditAttributes implements
        Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -793722522463459L;

    /** Primary key for custom field value. */
    private Long id;

    /** Field value. */
    private String fieldValue;

    /** Charity custom field. */
    private CharityCustomField charityCustomField;

    /** Donor. */
    private Donor donor;

    /** Event. */
    private Event event;

    /** Fundraiser. */
    private Fundraiser fundraiser;

    /** Fundraiser activity. */
    private FundraiserActivity fundraiserActivity;

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
     * Gets the field value.
     * 
     * @return the fieldValue
     */
    @Column(name = "FIELD_VALUE")
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * Sets the field value.
     * 
     * @param fieldValue the fieldValue to set
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    /**
     * Gets the charity custom field.
     * 
     * @return the charityCustomField
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_CUSTOM_FIELD_ID")
    public CharityCustomField getCharityCustomField() {
        return charityCustomField;
    }

    /**
     * Sets the charity custom field.
     * 
     * @param charityCustomField the charityCustomField to set
     */
    public void setCharityCustomField(CharityCustomField charityCustomField) {
        this.charityCustomField = charityCustomField;
    }

    /**
     * Gets the donor.
     * 
     * @return the donor
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DONOR_ID")
    public Donor getDonor() {
        return donor;
    }

    /**
     * Sets the donor.
     * 
     * @param donor the donor to set
     */
    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    /**
     * Gets the event.
     * 
     * @return the event
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
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
     * Gets the fundraiser.
     * 
     * @return the fundraiser
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ID")
    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    /**
     * Sets the fundraiser.
     * 
     * @param fundraiser the fundraiser to set
     */
    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    /**
     * Gets the fundraiser activity.
     * 
     * @return the fundraiserActivity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUNDRAISER_ACTIVITY_ID")
    public FundraiserActivity getFundraiserActivity() {
        return fundraiserActivity;
    }

    /**
     * Sets the fundraiser activity.
     * 
     * @param fundraiserActivity the fundraiserActivity to set
     */
    public void setFundraiserActivity(FundraiserActivity fundraiserActivity) {
        this.fundraiserActivity = fundraiserActivity;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("CustomFieldValue ( ")
            .append("id = ").append(this.id).append(tab)
            .append("fieldValue = ").append(this.fieldValue).append(tab)
            .append("charityCustomField = ").append(this.charityCustomField).append(tab)
            .append("donor = ").append(this.donor).append(tab)
            .append("event = ").append(this.event).append(tab)
            .append("fundraiser = ").append(this.fundraiser).append(tab)
            .append("fundraiserActivity = ").append(this.fundraiserActivity).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
