package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * This class used to store the charity information.
 * 
 * @author Srinivas Nageli
 */
public class Charity implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5124027249827281120L;

    /** Charity Reference. * */
    private String charityReference;

    /** Charity Name. * */
    private String charityName;

    /** Accounting Period End Date. * */
    private Date accountingPeriodEndDate;

    /**
     * Gets the accounting period end date.
     * 
     * @return the accountingPeriodEndDate
     */
    public Date getAccountingPeriodEndDate() {
        return accountingPeriodEndDate;
    }

    /**
     * Sets the accounting period end date.
     * 
     * @param accountingPeriodEndDate the accountingPeriodEndDate to set
     */
    public void setAccountingPeriodEndDate(Date accountingPeriodEndDate) {
        this.accountingPeriodEndDate = accountingPeriodEndDate;
    }

    /**
     * Gets the charity reference.
     * 
     * @return the charityReference
     */
    public String getCharityReference() {
        return charityReference;
    }

    /**
     * Sets the charity reference.
     * 
     * @param charityReference the charityReference to set
     */
    public void setCharityReference(String charityReference) {
        this.charityReference = charityReference;
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

}
