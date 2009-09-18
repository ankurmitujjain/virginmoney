package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * Class to hold details about the Charity, which failed to go live.
 * 
 * @author Puneet Swarup
 */
public class FailedToGoLiveCharity implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = -293874982374L;

	/** Charity reference/Id. */
	private Long charityReference;

	/** Charity name. */
	private String charityName;

	/** Reason for which the charity could not be made live. */
	private String failureReason;

	
	/**
	 * Default Constructor.
	 */
	public FailedToGoLiveCharity() {
		super();
	}
	
	/**
	 * Full Constructor.
	 * 
	 * @param charityReference the charity id or reference
	 * @param charityName the name of charity
	 * @param failureReason the reason for failure
	 */
	public FailedToGoLiveCharity(final Long charityReference, final String charityName,
			final String failureReason) {
		super();
		this.charityReference = charityReference;
		this.charityName = charityName;
		this.failureReason = failureReason;
	}



	/**
	 * Gets the charity reference.
	 * 
	 * @return the charityReference
	 */
	public Long getCharityReference() {
		return charityReference;
	}

	/**
	 * Sets the charity reference.
	 * 
	 * @param charityReference the charityReference to set
	 */
	public void setCharityReference(Long charityReference) {
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
	 * Gets the failure reason.
	 * 
	 * @return the failureReason
	 */
	public String getFailureReason() {
		return failureReason;
	}

	/**
	 * Sets the failure reason.
	 * 
	 * @param failureReason the failureReason to set
	 */
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	public String toString() {
	
	    final String tab = "    ";
	
	    return new StringBuilder("FailedToGoLiveCharity ( ")
	        .append("charityReference = ").append(this.charityReference).append(tab)
	        .append("charityName = ").append(this.charityName).append(tab)
	        .append("failureReason = ").append(this.failureReason).append(tab)
	        .append(super.toString()).append(" )")
	        .toString();
	}

}
