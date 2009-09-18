package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * The  data object representing collect regular donation request.
 * 
 * @author taruna
 */
public class CollectRegularDonationRequest implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** The regular donation id. */
    private Long regularDonationId;

    /** Donor id. */
    private Long donorId = null;

    /** Charity id. */
    private Long charityId = null;

    /** Fundraiser Activity Id. */
    private Long fundraiserActivityId = null;

    /** Gross Amount. */
    private BigDecimal grossAmount = null;

    /** Eligible For GiftAid. */
    private boolean eligibleForGiftAid = false;

    /** Payment Frequency. */
    private String paymentFrequency = null;

    /** Payment Day. */
    private Integer paymentDay = null;

    /** Donor name. */
    private String donorName = null;

    /** Donor message. */
    private String donorMessage = null;

    /** Donor email address. */
    private String donorEmailAddress = null;

    /** Donation reference. */
    private String donationReference = null;

    /** GiftAid Amount. */
    private BigDecimal giftAidAmount = null;
    
    /** First payment date. */
    private Date firstPaymentDate = null;
    
    /** GiftAid Amount. */
    private Long donationId = null;
    
    /** First payment date. */
    private Long personId = null;


    /**
     * Gets the donor id.
     * 
     * @return the donorId
     */
    public Long getDonorId() {
        return donorId;
    }

    /**
     * Sets the donor id.
     * 
     * @param donorId the donorId to set
     */
    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    /**
     * Gets the charity id.
     * 
     * @return the charityId
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the charity id.
     * 
     * @param charityId the charityId to set
     */
    public void setCharityId(Long charityId) {
        this.charityId = charityId;
    }

    /**
     * Gets the fundraiser activity id.
     * 
     * @return the fundraiserActivityId
     */
    public Long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the fundraiser activity id.
     * 
     * @param fundraiserActivityId the fundraiserActivityId to set
     */
    public void setFundraiserActivityId(Long fundraiserActivityId) {
        this.fundraiserActivityId = fundraiserActivityId;
    }

    /**
     * Gets the gross amount.
     * 
     * @return the grossAmount
     */
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
     * Checks if is eligible for gift aid.
     * 
     * @return the eligibleForGiftAid
     */
    public boolean isEligibleForGiftAid() {
        return eligibleForGiftAid;
    }

    /**
     * Sets the eligible for gift aid.
     * 
     * @param eligibleForGiftAid the eligibleForGiftAid to set
     */
    public void setEligibleForGiftAid(boolean eligibleForGiftAid) {
        this.eligibleForGiftAid = eligibleForGiftAid;
    }

    /**
     * Gets the payment frequency.
     * 
     * @return the paymentFrequency
     */
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
     * Gets the payment day.
     * 
     * @return the paymentDay
     */
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
     * Gets the donor name.
     * 
     * @return the donorName
     */
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
     * Gets the donor email address.
     * 
     * @return the donorEmailAddress
     */
    public String getDonorEmailAddress() {
        return donorEmailAddress;
    }

    /**
     * Sets the donor email address.
     * 
     * @param donorEmailAddress the donorEmailAddress to set
     */
    public void setDonorEmailAddress(String donorEmailAddress) {
        this.donorEmailAddress = donorEmailAddress;
    }

    /**
     * Gets the donation reference.
     * 
     * @return the donationReference
     */
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
     * Gets the gift aid amount.
     * 
     * @return the giftAidAmount
     */
    public BigDecimal getGiftAidAmount() {
        return giftAidAmount;
    }

    /**
     * Sets the gift aid amount.
     * 
     * @param giftAidAmount the giftAidAmount to set
     */
    public void setGiftAidAmount(BigDecimal giftAidAmount) {
        this.giftAidAmount = giftAidAmount;
    }

    /**
     * Gets the first payment date.
     * 
     * @return the firstPaymentDate
     */
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
     * Gets the regular donation id.
     * 
     * @return the regularDonationId
     */
    public Long getRegularDonationId() {
        return regularDonationId;
    }

    /**
     * Sets the regular donation id.
     * 
     * @param regularDonationId the regularDonationId to set
     */
    public void setRegularDonationId(Long regularDonationId) {
        this.regularDonationId = regularDonationId;
    }

}
