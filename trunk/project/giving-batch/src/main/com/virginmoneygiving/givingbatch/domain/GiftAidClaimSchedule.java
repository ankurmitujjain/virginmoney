package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class used to store the gift aid claim schedule information.
 * 
 * @author henryj
 */
public class GiftAidClaimSchedule implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;

    /** record type. */
    private String recordType = "SCH";

    /** donor surname. */
    private String donorSurname;

    /** donor fore name. */
    private String donorForename;

    /** donation date. */
    private String donationDate;

    /** donation amount. */
    private BigDecimal donationAmount;

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
     * Gets the donor surname.
     * 
     * @return the donorSurname
     */
    public String getDonorSurname() {
        return donorSurname;
    }

    /**
     * Sets the donor surname.
     * 
     * @param donorSurname the donorSurname to set
     */
    public void setDonorSurname(String donorSurname) {
        this.donorSurname = donorSurname;
    }

    /**
     * Gets the donor forename.
     * 
     * @return the donorForename
     */
    public String getDonorForename() {
        return donorForename;
    }

    /**
     * Sets the donor forename.
     * 
     * @param donorForename the donorForename to set
     */
    public void setDonorForename(String donorForename) {
        this.donorForename = donorForename;
    }

    /**
     * Gets the donation date.
     * 
     * @return the donationDate
     */
    public String getDonationDate() {
        return donationDate;
    }

    /**
     * Sets the donation date.
     * 
     * @param donationDate the donationDate to set
     */
    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    /**
     * Gets the donation amount.
     * 
     * @return the donationAmount
     */
    public BigDecimal getDonationAmount() {
        return donationAmount;
    }

    /**
     * Sets the donation amount.
     * 
     * @param donationAmount the donationAmount to set
     */
    public void setDonationAmount(BigDecimal donationAmount) {
        this.donationAmount = donationAmount;
    }

}
