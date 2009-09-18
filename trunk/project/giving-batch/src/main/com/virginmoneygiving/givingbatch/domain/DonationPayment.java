package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The data object representing Payment Collection details.
 * 
 * @author taruna
 */
public class DonationPayment extends PaymentType implements Serializable {

    /** serial version Id. */
    private static final long serialVersionUID = 1L;

    /** Currency Amount. * */
    private BigDecimal amountCurrency;

	/**
	 * Gets the amount currency.
	 * 
	 * @return the amountCurrency
	 */
	public BigDecimal getAmountCurrency() {
		return amountCurrency;
	}

	/**
	 * Sets the amount currency.
	 * 
	 * @param amountCurrency the amountCurrency to set
	 */
	public void setAmountCurrency(BigDecimal amountCurrency) {
		this.amountCurrency = amountCurrency;
	}

   
}
