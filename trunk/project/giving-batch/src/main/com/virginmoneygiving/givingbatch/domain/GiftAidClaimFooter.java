package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This method used to store the gift aid claim footer information.
 * 
 * @author henryj
 */
public class GiftAidClaimFooter implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** record type. * */
    private String recordType = "FTR";

    /** job end date time. * */
    private String jobEndDateTime;

    /** charity record count. * */
    private Integer charityRecordCount = new Integer(0);

    /** donation record count. * */
    private Integer donationRecordCount = new Integer(0);

    /** donation grand total. * */
    private BigDecimal donationGrandTotal = new BigDecimal(0);

    /** gift aid grand total. * */
    private BigDecimal giftAidGrandTotal = new BigDecimal(0);

    /**
     * Gets the record type.
     * 
     * @return the recordType
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * Sets the record type.
     * 
     * @param recordType the recordType to set
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    /**
     * Gets the job end date time.
     * 
     * @return the jobEndDateTime
     */
    public String getJobEndDateTime() {
        if (jobEndDateTime == null) {
            jobEndDateTime = "";
        }
        return jobEndDateTime;
    }

    /**
     * Sets the job end date time.
     * 
     * @param jobEndDateTime the jobEndDateTime to set
     */
    public void setJobEndDateTime(String jobEndDateTime) {
        this.jobEndDateTime = jobEndDateTime;
    }

    /**
     * Gets the charity record count.
     * 
     * @return the charityRecordCount
     */
    public Integer getCharityRecordCount() {
        return charityRecordCount;
    }

    /**
     * Sets the charity record count.
     * 
     * @param charityRecordCount the charityRecordCount to set
     */
    public void setCharityRecordCount(Integer charityRecordCount) {
        this.charityRecordCount = charityRecordCount;
    }

    /**
     * Gets the donation record count.
     * 
     * @return the donationRecordCount
     */
    public Integer getDonationRecordCount() {
        return donationRecordCount;
    }

    /**
     * Sets the donation record count.
     * 
     * @param donationRecordCount the donationRecordCount to set
     */
    public void setDonationRecordCount(Integer donationRecordCount) {
        this.donationRecordCount = donationRecordCount;
    }

    /**
     * Gets the donation grand total.
     * 
     * @return the donationGrandTotal
     */
    public BigDecimal getDonationGrandTotal() {
        return donationGrandTotal;
    }

    /**
     * Sets the donation grand total.
     * 
     * @param donationGrandTotal the donationGrandTotal to set
     */
    public void setDonationGrandTotal(BigDecimal donationGrandTotal) {
        this.donationGrandTotal = donationGrandTotal;
    }

    /**
     * Gets the gift aid grand total.
     * 
     * @return the giftAidGrandTotal
     */
    public BigDecimal getGiftAidGrandTotal() {
        return giftAidGrandTotal;
    }

    /**
     * Sets the gift aid grand total.
     * 
     * @param giftAidGrandTotal the giftAidGrandTotal to set
     */
    public void setGiftAidGrandTotal(BigDecimal giftAidGrandTotal) {
        this.giftAidGrandTotal = giftAidGrandTotal;
    }

    /**
     * This method used to update the total charity count.
     */
    public void updateCharityCount() {
        this.charityRecordCount++;
    }

    /**
     * This method used to update the total donation count.
     */
    public void updateDonationCount() {
        this.donationRecordCount++;
    }

    /**
     * This method used to update the total donation amount.
     * 
     * @param donationTotal new value
     */
    public void updateDonationTotal(BigDecimal donationTotal) {
        if (this.donationGrandTotal == null) {
            this.donationGrandTotal = donationTotal.multiply(new BigDecimal(100));
        }
        else {
            this.donationGrandTotal =
                    this.donationGrandTotal.add(donationTotal.multiply(new BigDecimal(100)));
        }
    }

    /**
     * This method used to update the total gift aid amount.
     * 
     * @param giftAidTotal new value
     */
    public void updateGiftAidTotal(BigDecimal giftAidTotal) {
        if (this.giftAidGrandTotal == null) {
            this.giftAidGrandTotal = giftAidTotal.multiply(new BigDecimal(100));
        }
        else {
            this.giftAidGrandTotal = this.giftAidGrandTotal.add(giftAidTotal.multiply(new BigDecimal(100)));
        }
    }

}
