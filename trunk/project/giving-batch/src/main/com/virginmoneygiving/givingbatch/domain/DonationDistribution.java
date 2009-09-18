package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 *  The data object representing donation distribution details.
 * 
 * @author taruna
 */
public class DonationDistribution implements Serializable{

	/** Serial version uid. */
	private static final long serialVersionUID = 1L;
	
	/** Charity id. */
	private long charityId;
	
	/** Charity bank sort code. */
	private String sortCode;
	
	/** Charity bank account number. */
	private String accountNumber;
	
	
	/** Amount distribution percentage among the charities. */
	private int splitValue;

	/**
	 * Gets the charity id.
	 * 
	 * @return the charityId
	 */
	public long getCharityId() {
		return charityId;
	}

	/**
	 * Sets the charity id.
	 * 
	 * @param charityId the charityId to set
	 */
	public void setCharityId(long charityId) {
		this.charityId = charityId;
	}

	/**
	 * Gets the split value.
	 * 
	 * @return the splitValue
	 */
	public int getSplitValue() {
		return splitValue;
	}

	/**
	 * Sets the split value.
	 * 
	 * @param splitValue the splitValue to set
	 */
	public void setSplitValue(int splitValue) {
		this.splitValue = splitValue;
	}

	/**
	 * Gets the sort code.
	 * 
	 * @return the sortCode
	 */
	public String getSortCode() {
		return sortCode;
	}

	/**
	 * Sets the sort code.
	 * 
	 * @param sortCode the sortCode to set
	 */
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	/**
	 * Gets the account number.
	 * 
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 * 
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	} 

}
