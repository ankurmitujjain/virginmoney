package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * This class used to store the gift aid claim header information and write on header part.
 * 
 * @author Srinivas Nageli.
 */
public class GiftAidClaimHeader implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;

    /** record type. * */
    private String recordType = "HDR";

    /** job start date. * */
    private String jobStartDate;

    /** claim period start. * */
    private String claimPeriodStart;

    /** claim poeriod end. * */
    private String claimPeriodEnd;

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
     * Gets the job start date.
     * 
     * @return the jobStartDate
     */
    public String getJobStartDate() {
        return jobStartDate;
    }

    /**
     * Sets the job start date.
     * 
     * @param jobStartDate the jobStartDate to set
     */
    public void setJobStartDate(String jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    /**
     * Gets the claim period start.
     * 
     * @return the claimPeriodStart
     */
    public String getClaimPeriodStart() {
        return claimPeriodStart;
    }

    /**
     * Sets the claim period start.
     * 
     * @param claimPeriodStart the claimPeriodStart to set
     */
    public void setClaimPeriodStart(String claimPeriodStart) {
        this.claimPeriodStart = claimPeriodStart;
    }

    /**
     * Gets the claim period end.
     * 
     * @return the claimPeriodEnd
     */
    public String getClaimPeriodEnd() {
        return claimPeriodEnd;
    }

    /**
     * Sets the claim period end.
     * 
     * @param claimPeriodEnd the claimPeriodEnd to set
     */
    public void setClaimPeriodEnd(String claimPeriodEnd) {
        this.claimPeriodEnd = claimPeriodEnd;
    }

}
