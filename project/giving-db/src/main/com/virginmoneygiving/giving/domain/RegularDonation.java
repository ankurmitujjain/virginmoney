package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

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
 * DTO representing a regular donation.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "REGULAR_DONATION")
@Audited
public class RegularDonation extends EnversAssociationAuditAttributes implements
        Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -293847298347L;

    /** Id. */
    private Long id = null;

    /** Donor. */
    private Donor donor = null;

    /** Charity. */
    private Charity charity = null;

    /** Gross amount. */
    private BigDecimal grossAmount = null;

    /** First payment date. */
    private Date firstPaymentDate = null;

    /** Payment frequency. */
    private String paymentFrequency = "M";

    /** Currency. */
    private String currency = null;

    /** Payment day. */
    private Integer paymentDay = null;

    /** The next payment date. */
    private Date nextPaymentDate = null;
    
    /** The latest payment date. */
    private Date latestPaymentDate = null;

    /** Gift aid eligible indicator. */
    private String giftAidEligibleIndicator = "N";
    
    /** Status indicator. */
    private String statusIndicator = null;

    /**
     * Gets the status indicator.
     * 
     * @return the statusIndicator
     */
    @Column(name = "STATUS_IND")
    @NotAudited
	public String getStatusIndicator() {
		return statusIndicator;
	}

	/**
	 * Sets the status indicator.
	 * 
	 * @param statusIndicator the statusIndicator to set
	 */
	public void setStatusIndicator(String statusIndicator) {
		this.statusIndicator = statusIndicator;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
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
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER)
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
     * Gets the first payment date.
     * 
     * @return the firstPaymentDate
     */
    @Column(name = "FIRST_PAYMENT_DATE")
    @Audited
    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    /**
     * Sets the first payment date.
     * 
     * @param firstPaymentDate the firstPaymentDate to set
     */
    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    /**
     * Gets the next payment date.
     * 
     * @return the nextPaymentDate
     */
    @Column(name = "NEXT_PAYMENT_DATE")
    @NotAudited
    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    /**
     * Sets the next payment date.
     * 
     * @param nextPaymentDate the nextPaymentDate to set
     */
    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

        /**
         * Gets the latest payment date.
         * 
         * @return the latestPaymentDate
         */
    @Column(name = "LATEST_PAYMENT_DATE")
    @NotAudited
    public Date getLatestPaymentDate() {
        return latestPaymentDate;
    }

    /**
     * Sets the latest payment date.
     * 
     * @param latestPaymentDate the nextPaymentDate to set
     */
    public void setLatestPaymentDate(Date latestPaymentDate) {
        this.latestPaymentDate = latestPaymentDate;
    }

    /**
     * Gets the payment frequency.
     * 
     * @return the paymentFrequency
     */
    @Column(name = "PAYMENT_FREQUENCY")
    @Audited
    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Sets the payment frequency.
     * 
     * @param paymentFrequency the paymentFrequency to set
     */
    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    /**
     * Gets the currency.
     * 
     * @return the currency
     */
    @Column(name = "CURRENCY")
    @Audited
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency.
     * 
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets the payment day.
     * 
     * @return the paymentDay
     */
    @Column(name = "PAYMENT_DAY")
    @Audited
    public Integer getPaymentDay() {
        return paymentDay;
    }

    /**
     * Sets the payment day.
     * 
     * @param paymentDay the paymentDay to set
     */
    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    /**
     * Gets the gift aid eligible indicator.
     * 
     * @return the giftAidEligibleIndicator
     */
    @Column(name = "GIFT_AID_ELIGIBLE_IND")
    @Audited
    public String getGiftAidEligibleIndicator() {
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
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("RegularDonation ( ")
            .append("id = ").append(this.id).append(tab)
            .append("donor = ").append(this.donor).append(tab)
            .append("charity = ").append(this.charity).append(tab)
            .append("grossAmount = ").append(this.grossAmount).append(tab)
            .append("firstPaymentDate = ").append(this.firstPaymentDate).append(tab)
            .append("paymentFrequency = ").append(this.paymentFrequency).append(tab)
            .append("currency = ").append(this.currency).append(tab)
            .append("paymentDay = ").append(this.paymentDay).append(tab)
            .append("giftAidEligibleIndicator = ").append(this.giftAidEligibleIndicator).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
