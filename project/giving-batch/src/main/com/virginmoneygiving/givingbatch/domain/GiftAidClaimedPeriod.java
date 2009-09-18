package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * The data object representing Gift Aid Claimed Period details.
 * 
 * @author taruna
 */
public class GiftAidClaimedPeriod implements Serializable{

	/** Serial version uid. */
	private static final long serialVersionUID = 1L;
	
	/** The claim period from. */
	private Date claimPeriodFrom ;
	
	/** The claim period to. */
	private Date claimPeriodTo;

	/**
	 * Gets the claim period from.
	 * 
	 * @return the claimPeriodFrom
	 */
	public Date getClaimPeriodFrom() {
		return claimPeriodFrom;
	}

	/**
	 * Sets the claim period from.
	 * 
	 * @param claimPeriodFrom the claimPeriodFrom to set
	 */
	public void setClaimPeriodFrom(Date claimPeriodFrom) {
		this.claimPeriodFrom = claimPeriodFrom;
	}

	/**
	 * Gets the claim period to.
	 * 
	 * @return the claimPeriodTo
	 */
	public Date getClaimPeriodTo() {
		return claimPeriodTo;
	}

	/**
	 * Sets the claim period to.
	 * 
	 * @param claimPeriodTo the claimPeriodTo to set
	 */
	public void setClaimPeriodTo(Date claimPeriodTo) {
		this.claimPeriodTo = claimPeriodTo;
	}
	
	

}
