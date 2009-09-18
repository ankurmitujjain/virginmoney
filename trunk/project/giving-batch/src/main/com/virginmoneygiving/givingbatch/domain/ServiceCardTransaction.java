package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Domain class for card transaction service details
 * 
 * @author taruna
 */
public class ServiceCardTransaction implements Serializable{

	/** Serial verions uid. */
	private static final long serialVersionUID = 1L;
	
	/** Transaction Date Time. */
	private Timestamp transactionDatetime;
	
	/** Transaction Amount. */
	private BigDecimal transactionAmount;
	
	/** Currency . */
	private String currency;
	
	/** Transaction Number. */
	private String transactionNumber;
	
	/** Card type. */
	private String cardType;
	
	/**
	 * Gets the transaction datetime.
	 * 
	 * @return the transactionDatetime
	 */
	public Timestamp getTransactionDatetime() {
		return transactionDatetime;
	}
	
	/**
	 * Sets the transaction datetime.
	 * 
	 * @param transactionDatetime the transactionDatetime to set
	 */
	public void setTransactionDatetime(Timestamp transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}
	
	/**
	 * Gets the transaction amount.
	 * 
	 * @return the transactionAmount
	 */
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	
	/**
	 * Sets the transaction amount.
	 * 
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	/**
	 * Gets the currency.
	 * 
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * Sets the currency.
	 * 
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	/**
	 * Gets the transaction number.
	 * 
	 * @return the transactionNumber
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}
	
	/**
	 * Sets the transaction number.
	 * 
	 * @param transactionNumber the transactionNumber to set
	 */
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
	/**
	 * Gets the card type.
	 * 
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}
	
	/**
	 * Sets the card type.
	 * 
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	
	
	

}
