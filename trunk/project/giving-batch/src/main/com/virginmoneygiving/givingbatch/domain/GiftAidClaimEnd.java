package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.virginmoneygiving.givingbatch.common.Util;

/**
 * This class used to store the gift aid claim end details.
 * 
 * @author henryj
 */
public class GiftAidClaimEnd implements Serializable {

    /**
     * Gets the donation count padded string.
     * 
     * @return the donationCountPaddedString
     */
    public String getDonationCountPaddedString() {
        if (donationCountPaddedString == null)  {
            donationCountPaddedString = "";
        }
        return donationCountPaddedString;
    }

    /**
     * Sets the donation count padded string.
     * 
     * @param donationCountPaddedString the donationCountPaddedString to set
     */
    public void setDonationCountPaddedString(String donationCountPaddedString) {
        this.donationCountPaddedString = donationCountPaddedString;
    }

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;

    /** record type. * */
    private String recordType = "CLE";

    /** donation count. * */
    private Integer donationCount = new Integer(0);

    /** donation total. * */
    private BigDecimal donationTotal = new BigDecimal(0);

    /** gift aid total. * */
    private BigDecimal giftAidTotal = new BigDecimal(0);
    
    /** donation count with zero padded.* */
    private String donationCountPaddedString;
    
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
     * Gets the donation count.
     * 
     * @return the donationCount
     */
    public Integer getDonationCount() {
        return donationCount;
    }

    /**
     * Sets the donation count.
     * 
     * @param donationCount the donationCount to set
     */
    public void setDonationCount(Integer donationCount) {
        this.donationCount = donationCount;
    }

    /**
     * Gets the donation total.
     * 
     * @return the donationTotal
     */
    public BigDecimal getDonationTotal() {
        return donationTotal;
    }

    /**
     * Sets the donation total.
     * 
     * @param donationTotal the donationTotal to set
     */
    public void setDonationTotal(BigDecimal donationTotal) {
        this.donationTotal = donationTotal;
    }

    /**
     * Gets the gift aid total.
     * 
     * @return the giftAidTotal
     */
    public BigDecimal getGiftAidTotal() {
        return giftAidTotal;
    }

    /**
     * Sets the gift aid total.
     * 
     * @param giftAidTotal the giftAidTotal to set
     */
    public void setGiftAidTotal(BigDecimal giftAidTotal) {
        this.giftAidTotal = giftAidTotal;
    }

    /**
     * This method used to update the total donation count.
     */
    public void updateDonationCount() {
        this.donationCount++;
        this.donationCountPaddedString = Util.addLeftZeroPadding(this.donationCount, 5);
    }

    /**
     * This method used to update the total donation amount.
     * 
     * @param donationTotal new value
     */
    public void updateDonationTotal(BigDecimal donationTotal) {
        if (this.donationTotal == null) {
            this.donationTotal = donationTotal.multiply(new BigDecimal(100));
        }
        else {
            this.donationTotal = this.donationTotal.add(donationTotal.multiply(new BigDecimal(100)));
        }
    }

    /**
     * This method used to update the total gift aid amount.
     * 
     * @param giftAidTotal the gift aid total
     */
    public void updateGiftAidTotal(BigDecimal giftAidTotal) {
        if (this.giftAidTotal == null) {
            this.giftAidTotal = giftAidTotal.multiply(new BigDecimal(100));
        }
        else {
            this.giftAidTotal = this.giftAidTotal.add(giftAidTotal.multiply(new BigDecimal(100)));
        }
    }
    
    
}
