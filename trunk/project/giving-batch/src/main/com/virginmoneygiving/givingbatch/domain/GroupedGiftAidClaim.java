package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.virginmoneygiving.payment.domain.Payment;

/**
 * The Class GroupedGiftAidClaim.
 * 
 * @author taruna
 */
public class GroupedGiftAidClaim implements Serializable {

	/** Serial version uid. */
	private static final long serialVersionUID = 1L;
	
	/** The total gift aid claim. */
	private BigDecimal totalGiftAidClaim = null;
	
	/** The payment. */
	private Payment payment = null;

	/**
	 * Gets the total gift aid claim.
	 * 
	 * @return the totalGiftAidClaim
	 */
	public BigDecimal getTotalGiftAidClaim() {
		return totalGiftAidClaim;
	}

	/**
	 * Sets the total gift aid claim.
	 * 
	 * @param totalGiftAidClaim the totalGiftAidClaim to set
	 */
	public void setTotalGiftAidClaim(BigDecimal totalGiftAidClaim) {
		this.totalGiftAidClaim = totalGiftAidClaim;
	}

	/**
	 * Gets the payment.
	 * 
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * Sets the payment.
	 * 
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	

}
