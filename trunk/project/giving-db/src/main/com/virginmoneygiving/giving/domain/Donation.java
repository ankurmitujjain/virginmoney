package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
 * DTO representing a donation.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "DONATION")
@Audited
public class Donation extends EnversAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -293847298347L;

    /** The primary key for the donation. */
    private Long id = null;

    /** The donor who is making the donation. */
    private Donor donor = null;

    /** The person represented by this donor. */
    private Person person = null;

    /** The channel through which the donation is being made. */
    private Channel channel = null;

    /** The date time instance when donation is made. */
    private Timestamp donationDatetime = null;

    /** The amount of donation. */
    private BigDecimal grossAmount = null;

    /** The flag to indicate whether gift aid is applicable or not. */
    private String giftAidEligibleIndicator;

    /** The donation reference. */
    private String donationReference = null;

    /** Donor name. */
    private String donorName = null;

    /** Donor message. */
    private String donorMessage = null;

    /** Fundraiser activity. */
    private FundraiserActivity fundraiserActivity = null;

    /** Regular donation. */
    private RegularDonation regularDonation = null;

    /** Charity. */
    private Charity charity = null;
    
    /** Donation Failure Indicator. */
    private String donationFailedInd = "N";

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Audited
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
     * Gets the donor.
     * 
     * @return the donor
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DONOR_ID")
    @Audited
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
     * Gets the person.
     * 
     * @return the person
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID")
    @Audited
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person.
     * 
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets the channel.
     * 
     * @return the channel
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHANNEL_CODE")
    @Audited
    public Channel getChannel() {
        if (channel == null) {
            channel = new Channel();
            channel.setCode("VMG");
        }
        return channel;
    }

    /**
     * Sets the channel.
     * 
     * @param channel the channel to set
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * Gets the donation datetime.
     * 
     * @return the donationDatetime
     */
    @Column(name = "DONATION_DATETIME")
    @Audited
    public Timestamp getDonationDatetime() {
        return donationDatetime;
    }

    /**
     * Sets the donation datetime.
     * 
     * @param donationDatetime the donationDatetime to set
     */
    public void setDonationDatetime(Timestamp donationDatetime) {
        this.donationDatetime = donationDatetime;
    }

    /**
     * Gets the gross amount.
     * 
     * @return the grossAmount
     */
    @Column(name = "GROSS_AMOUNT", columnDefinition = "decimal(19,2)")
    @Audited
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    /**
     * Sets the gross amount.
     * 
     * @param grossAmount the grossAmount to set
     */
    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    /**
     * Gets the gift aid eligible indicator.
     * 
     * @return the giftAidEligibleIndicator
     */
    @Column(name = "GIFT_AID_ELIGIBLE_IND")
    @Audited
    public String getGiftAidEligibleIndicator() {
        if(giftAidEligibleIndicator == null) {
            giftAidEligibleIndicator = "N";
        }
        return giftAidEligibleIndicator;
    }

    /**
     * Sets the gift aid eligible indicator.
     * 
     * @param giftAidEligibleIndicator the giftAidEligibleIndicator to set
     */
    public void setGiftAidEligibleIndicator(String giftAidEligibleIndicator) {
        this.giftAidEligibleIndicator = giftAidEligibleIndicator;
    }

    /**
     * Gets the donation reference.
     * 
     * @return the donationReference
     */
    @Column(name = "DONATION_REFERENCE")
    @Audited
    public String getDonationReference() {
        return donationReference;
    }

    /**
     * Sets the donation reference.
     * 
     * @param donationReference the donationReference to set
     */
    public void setDonationReference(String donationReference) {
        this.donationReference = donationReference;
    }

    /**
     * Gets the donor name.
     * 
     * @return the donorName
     */
    @Column(name = "DONOR_NAME")
    @NotAudited
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
    @NotAudited
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
     * Gets the fundraiser activity.
     * 
     * @return the fundraiserActivity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNDRAISER_ACTIVITY_ID")
    @Audited
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
     * Gets the regular donation.
     * 
     * @return the regularDonation
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REGULAR_DONATION_ID")
    @Audited
    public RegularDonation getRegularDonation() {
        return regularDonation;
    }

    /**
     * Sets the regular donation.
     * 
     * @param regularDonation the regularDonation to set
     */
    public void setRegularDonation(RegularDonation regularDonation) {
        this.regularDonation = regularDonation;
    }

    /**
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARITY_ID")
    @Audited
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
     * Gets the donor message.
     * 
     * @return the donorMessage
     */
    @Column(name = "DONATION_FAILED_IND")
    @NotAudited    
	public String getDonationFailedInd() {
		return donationFailedInd;
	}

	public void setDonationFailedInd(String donationFailedInd) {
		this.donationFailedInd = donationFailedInd;
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
        return new StringBuilder("Donation ( ").append("id = ").append(id)
                .append(tab).append("donor = ").append(donor).append(tab)
                .append("person = ").append(person).append(tab).append(
                        "channel = ").append(channel).append(tab).append(
                        "donationDatetime = ").append(donationDatetime).append(
                        tab).append("grossAmount = ").append(grossAmount)
                .append(tab).append("giftAidEligibleIndicator = ").append(
                        giftAidEligibleIndicator).append(tab).append(
                        "donationReference = ").append(donationReference)
                .append(tab).append(super.toString()).append(" )").toString();
    }


}
