package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The Class contains the details to save the regular donation.
 * 
 * @author taruna
 */
public class SaveRegularDonationRequest implements Serializable{

	/** Serial version uid. */
	private static final long serialVersionUID = 1L;
	
	/** Message Header. */
	private MessageHeader header;
	
	/** Gift Aid applicable indicator. */
	private boolean giftAidApplicable;
	
	/** Donor id. */
	private long donationId;
	
	/** Card transaction details. */
	private ServiceCardTransaction cardTransaction;
	
	/** Donation distribution. */
	private List<DonationDistribution> regularDonation;
	
	/**
	 * Gets the header.
	 * 
	 * @return the header
	 */
	public MessageHeader getHeader() {
		return header;
	}
	
	/**
	 * Sets the header.
	 * 
	 * @param header the header to set
	 */
	public void setHeader(MessageHeader header) {
		this.header = header;
	}
	
	/**
	 * Checks if is gift aid applicable.
	 * 
	 * @return the giftAidApplicable
	 */
	public boolean isGiftAidApplicable() {
		return giftAidApplicable;
	}
	
	/**
	 * Sets the gift aid applicable.
	 * 
	 * @param giftAidApplicable the giftAidApplicable to set
	 */
	public void setGiftAidApplicable(boolean giftAidApplicable) {
		this.giftAidApplicable = giftAidApplicable;
	}
	
	/**
	 * Gets the donation id.
	 * 
	 * @return the donationId
	 */
	public long getDonationId() {
		return donationId;
	}
	
	/**
	 * Sets the donation id.
	 * 
	 * @param donationId the donationId to set
	 */
	public void setDonationId(long donationId) {
		this.donationId = donationId;
	}
	
	/**
	 * Gets the card transaction.
	 * 
	 * @return the cardTransaction
	 */
	public ServiceCardTransaction getCardTransaction() {
		return cardTransaction;
	}
	
	/**
	 * Sets the card transaction.
	 * 
	 * @param cardTransaction the cardTransaction to set
	 */
	public void setCardTransaction(ServiceCardTransaction cardTransaction) {
		this.cardTransaction = cardTransaction;
	}
	
	/**
	 * Gets the regular donation.
	 * 
	 * @return the regularDonation
	 */
	public List<DonationDistribution> getRegularDonation() {
		return regularDonation;
	}
	
	/**
	 * Sets the regular donation.
	 * 
	 * @param regularDonation the regularDonation to set
	 */
	public void setRegularDonation(List<DonationDistribution> regularDonation) {
		this.regularDonation = regularDonation;
	}
	
    
}
