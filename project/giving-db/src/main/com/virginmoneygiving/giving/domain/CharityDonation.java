package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Date;

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
 * DTO representing a donation towards charity.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "CHARITY_DONATION")
public class CharityDonation extends AuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -293847345347L;

    /** The primary key for the charity donation. */
    private Long id = null;

    /** The donation for the charity. */
    private Donation donation = null;

    // TODO currently as bank account is not available,
    // TODO the charity donation is linked with charity directly.
    /** The charity the donation is made to. */
    private Charity charity = null;

    /** The payment frequency. */
    private String paymentFrequency = null;

    /** The payment day. */
    private Integer paymentDay = null;

    /** The first payment date. */
    private Date firstPaymentDate = null;

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
     * Gets the payment frequency.
     * 
     * @return the paymentFrequency
     */
    @Column(name = "PAYMENT_FREQUENCY")
    public String getPaymentFrequency() {
        if (paymentFrequency == null) {
            paymentFrequency = "M";
        }
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
     * Gets the payment day.
     * 
     * @return the paymentDay
     */
    @Column(name = "PAYMENT_DAY")
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
     * Gets the first payment date.
     * 
     * @return the firstPaymentDate
     */
    @Column(name = "FIRST_PAYMENT_DATE")
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
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("CharityDonation ( ")
            .append("id = ").append(id).append(tab)
            .append("paymentFrequency = ").append(paymentFrequency).append(tab)
            .append("paymentDay = ").append(paymentDay).append(tab)
            .append("firstPaymentDate = ").append(firstPaymentDate).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
