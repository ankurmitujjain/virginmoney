package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import com.virginmoneygiving.givingmanagement.service.messages.MessageHeader;

/**
 * This class contains the details for making the donation with active card.
 * 
 * @author Tarun Adiwal
 */
public class MakeDonationWithActiveCard implements Serializable{

	/** Serial version uid. */
	private static final long serialVersionUID = 1L;
	
	/** The header. */
	private transient MessageHeader header;
	
	/** The donation details. */
	private CollectRegularDonationRequest donationDetails;

	/**
	 * Gets the donation details.
	 * 
	 * @return the donationDetails
	 */
	public CollectRegularDonationRequest getDonationDetails() {
		return donationDetails;
	}

	/**
	 * Sets the donation details.
	 * 
	 * @param donationDetails the donationDetails to set
	 */
	public void setDonationDetails(CollectRegularDonationRequest donationDetails) {
		this.donationDetails = donationDetails;
	}

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
	
	

}
