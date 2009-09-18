package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.virginmoneygiving.payment.domain.TransitionalReliefAmount;

/**
 * This class used to store the the gift aid claim charity and donor information.
 * 
 * @author Srinivas Nageli
 */
public class GiftAidClaimRecord implements Serializable {

    
    /** Serial version uid. */
    private static final long serialVersionUID = 1L;

    /** charityId. */
    private String charityId;

    /** Gift aid amount. */
    private BigDecimal giftAidAmount;

    /** charity reference. */
    private String charityReference;

    /** charity name. */
    private String charityName;

    /** accounting period end date. */
    private Date accountingPeriodEndDate;

    /** donor surname. */
    private String donorSurname;

    /** donor fore name. */
    private String donorForname;

    /** donation date. */
    private Date donationDate;

    /** donation amount. */
    private BigDecimal donationAmount;
    
    /** Transitional relief amount. */
    private TransitionalReliefAmount transitionalReliefAmount;

    /** VMG HMRC Reference Number. */
    private String vmgHMRCReference;
    
    /**
     * Gets the vmg hmrc reference.
     * 
     * @return the vmgHMRCReference
     */
    public String getVmgHMRCReference() {
        return vmgHMRCReference;
    }

    /**
     * Sets the vmg hmrc reference.
     * 
     * @param vmgHMRCReference the vmgHMRCReference to set
     */
    public void setVmgHMRCReference(String vmgHMRCReference) {
        this.vmgHMRCReference = vmgHMRCReference;
    }
    
    /**
     * Gets the transitional relief amount.
     * 
     * @return the transitionalReliefAmount
     */
	public TransitionalReliefAmount getTransitionalReliefAmount() {
		return transitionalReliefAmount;
	}

	/**
	 * Sets the transitional relief amount.
	 * 
	 * @param transitionalReliefAmount the transitionalReliefAmount to set
	 */
	public void setTransitionalReliefAmount(
			TransitionalReliefAmount transitionalReliefAmount) {
		this.transitionalReliefAmount = transitionalReliefAmount;
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
     * Gets the donor forname.
     * 
     * @return the donorForname
     */
    public String getDonorForname() {
        return donorForname;
    }

    /**
     * Sets the donor forname.
     * 
     * @param donorForname the donorForname to set
     */
    public void setDonorForname(String donorForname) {
        this.donorForname = donorForname;
    }

    /**
     * Gets the donation date.
     * 
     * @return the donationDate
     */
    public Date getDonationDate() {
        return donationDate;
    }

    /**
     * Sets the donation date.
     * 
     * @param donationDate the donationDate to set
     */
    public void setDonationDate(Date donationDate) {
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

    /**
     * Gets the charity id.
     * 
     * @return the charityId
     */
    public String getCharityId() {
        return charityId;
    }

    /**
     * Sets the charity id.
     * 
     * @param charityId the charityId to set
     */
    public void setCharityId(String charityId) {
        this.charityId = charityId;
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

}
