package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
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
 * DTO representing a donation towards a fundraiser activity.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "FUNDRAISER_ACTIVITY_DONATION")
public class FundraiserActivityDonation extends AuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -293847298347L;

    /** The primary key for the fundraiser activity donation.. */
    private Long id = null;

    /** The fundraisers activity. */
    private FundraiserActivity fundraiserActivity = null;

    /** The donation to the fundraiser. */
    private Donation donation = null;

    /** The donor's name. */
    private String donorName = null;

    /** The message the from the donor to the fundraiser. */
    private String donorMessage = null;

    /** The Transitional Relief value. */
    protected BigDecimal taxBackAmount;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Gets the fundraiser activity.
     * 
     * @return the fundraiserActivity
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
     * Gets the donation.
     * 
     * @return the donation
     */

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DONATION_ID")
    public Donation getDonation() {
        return donation;
    }

    /**
     * Sets the donation.
     * 
     * @param donation the donation to set
     */
    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    /**
     * Gets the donor name.
     * 
     * @return the donorName
     */
    @Column(name = "DONOR_NAME")
    public String getDonorName() {
        return donorName;
    }

    /**
     * Sets the donor name.
     * 
     * @param donorName the donorName to set
     */
    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    /**
     * Gets the donor message.
     * 
     * @return the donorMessage
     */
    @Column(name = "DONOR_MESSAGE")
    public String getDonorMessage() {
        return donorMessage;
    }

    /**
     * Sets the donor message.
     * 
     * @param donorMessage the donorMessage to set
     */
    public void setDonorMessage(String donorMessage) {
        this.donorMessage = donorMessage;
    }

    /**
     * @return the taxBackAmount
     */
    @Column(name = "TAX_BACK_AMOUNT")
    public BigDecimal getTaxBackAmount() {
        return taxBackAmount;
    }

    /**
     * @param taxBackAmount the taxBackAmount to set
     */
    public void setTaxBackAmount(BigDecimal taxBackAmount) {
        this.taxBackAmount = taxBackAmount;
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
        return new StringBuilder("FundraiserActivityDonation ( ")
            .append("id = ").append(id).append(tab)
            .append("donorName = ").append(donorName).append(tab)
            .append("donorMessage = ").append(donorMessage).append(tab)
            .append("taxBackAmount = ").append(taxBackAmount).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }

}
