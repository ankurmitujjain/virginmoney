package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * This class used to store the gift aid claim claim start information.
 * 
 * @author henryj
 */
public class GiftAidClaimStart implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;

    /** record type. */
    private String recordType = "CLA";

    /** HMRC reference. */
    private String hmrcRef;

    /** charity name. */
    private String charityName;

    /** accounting period end date. */
    private String accountingPeriodEndDate;

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
     * Gets the hmrc ref.
     * 
     * @return the hmrcRef
     */
    public String getHmrcRef() {
        return hmrcRef;
    }

    /**
     * Sets the hmrc ref.
     * 
     * @param hmrcRef the hmrcRef to set
     */
    public void setHmrcRef(String hmrcRef) {
        this.hmrcRef = hmrcRef;
    }

    /**
     * Gets the charity name.
     * 
     * @return the charityName
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the charity name.
     * 
     * @param charityName the charityName to set
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Gets the accounting period end date.
     * 
     * @return the accountingPeriodEndDate
     */
    public String getAccountingPeriodEndDate() {
        return accountingPeriodEndDate;
    }

    /**
     * Sets the accounting period end date.
     * 
     * @param accountingPeriodEndDate the accountingPeriodEndDate to set
     */
    public void setAccountingPeriodEndDate(String accountingPeriodEndDate) {
        this.accountingPeriodEndDate = accountingPeriodEndDate;
    }

}
