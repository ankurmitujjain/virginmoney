
package com.virginmoneygiving.giving.domain;

import java.sql.Timestamp;

/**
 * The Class EventHomepageDetailsDVO.
 * 
 * @author saurabhh
 * This class is a value object for event details
 */
public class EventHomepageDetailsDVO {

	/** Event name. */	
	private String eventName;	

	/** Event location. */	
	private String eventLocation;

	/** Event date and time. */		
	private Timestamp eventDate;

	/** Event time- String because it can be free flow text. */		
	private String eventTime;
	
	/** To find out Event status for particular event. */
	
	private String eventStatus;

	/**
	 * Gets the event status.
	 * 
	 * @return the eventStatus
	 */
	public String getEventStatus() {
		return eventStatus;
	}
	
	/**
	 * Sets the event status.
	 * 
	 * @param eventStatus the eventStatus to set
	 */
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}


	/** Event contact name. */	

	private String contactName;

	/** Event contact phone. */	

	private String contactPhone;

	/** Event contact mail. */	

	private String contactEmail;

	/** Event logo url. */	

	private String eventLogo;

	/** Event website url. */	

	private String websiteUrl;

	/** Event building name. */
	private String buildingName;

	/** Event building number. */

	private String buildingNumber;

	/** Event post code. */

	private String postCode;

	/** Event description. */

	private String description;

	/** A variable to hold days to go for an event. */

	private String daysToGo;

	/** Joining instrucitons for this event. */

	private String joiningInstructions;

	/** Town or City of contact person. */

	private String townCity;

	/** A variable to hold the charity log to which this Event belongs. */

	private String charityLogo;
	
	/** A variable to hold the charity Name which this Event belongs. */

	private String charityName;

    /** A variable to specify whther this event was created by operation user. */
    private String operationEventInd;	
   

    /** Address line 1. */

    private String addressLine1;    
    
    /** Address line 2. */

    private String addressLine2;   
    
    /** Indicator to denote whether it is a single charity event or not*/

    private String singleCharityEvent;
    
    
	/**
	 * Gets the address line2.
	 * 
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Sets the address line2.
	 * 
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Gets the address line1.
	 * 
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Sets the address line1.
	 * 
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Gets the operation event ind.
	 * 
	 * @return the operationEventInd
	 */
    public String getOperationEventInd() {
        return operationEventInd;
    }


    /**
     * Sets the operation event ind.
     * 
     * @param operationEventInd the operationEventInd to set
     */
    public void setOperationEventInd(String operationEventInd) {
        this.operationEventInd = operationEventInd;
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
	 * Gets the event time.
	 * 
	 * @return the eventTime
	 */
	public String getEventTime() {
		return eventTime;
	}


	/**
	 * Sets the event time.
	 * 
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * Gets the charity logo.
	 * 
	 * @return the charityLogo
	 */
	public String getCharityLogo() {
		return charityLogo;
	}


	/**
	 * Sets the charity logo.
	 * 
	 * @param charityLogo the charityLogo to set
	 */
	public void setCharityLogo(String charityLogo) {
		this.charityLogo = charityLogo;
	}


	/**
	 * Gets the days to go.
	 * 
	 * @return the daysToGo
	 */
	public String getDaysToGo() {
		return daysToGo;
	}

	/**
	 * Sets the days to go.
	 * 
	 * @param daysToGo the daysToGo to set
	 */
	public void setDaysToGo(String daysToGo) {
		this.daysToGo = daysToGo;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the event name.
	 * 
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Sets the event name.
	 * 
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Gets the event location.
	 * 
	 * @return the eventLocation
	 */
	public String getEventLocation() {
		return eventLocation;
	}

	/**
	 * Sets the event location.
	 * 
	 * @param eventLocation the eventLocation to set
	 */
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}


	/**
	 * Gets the event date.
	 * 
	 * @return the eventDate
	 */
	public Timestamp getEventDate() {
		return eventDate;
	}


	/**
	 * Sets the event date.
	 * 
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}


	/**
	 * Gets the contact name.
	 * 
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * Sets the contact name.
	 * 
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * Gets the contact phone.
	 * 
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * Sets the contact phone.
	 * 
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * Gets the contact email.
	 * 
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * Sets the contact email.
	 * 
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * Gets the event logo.
	 * 
	 * @return the eventLogo
	 */
	public String getEventLogo() {
		return eventLogo;
	}

	/**
	 * Sets the event logo.
	 * 
	 * @param eventLogo the eventLogo to set
	 */
	public void setEventLogo(String eventLogo) {
		this.eventLogo = eventLogo;
	}

	/**
	 * Gets the website url.
	 * 
	 * @return the websiteUrl
	 */
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	/**
	 * Sets the website url.
	 * 
	 * @param websiteUrl the websiteUrl to set
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/**
	 * Gets the building name.
	 * 
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * Sets the building name.
	 * 
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * Gets the building number.
	 * 
	 * @return the buildingNumber
	 */
	public String getBuildingNumber() {
		return buildingNumber;
	}

	/**
	 * Sets the building number.
	 * 
	 * @param buildingNumber the buildingNumber to set
	 */
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	/**
	 * Gets the post code.
	 * 
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * Sets the post code.
	 * 
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	/**
	 * Gets the joining instructions.
	 * 
	 * @return the joiningInstructions
	 */
	public String getJoiningInstructions() {
		return joiningInstructions;
	}


	/**
	 * Sets the joining instructions.
	 * 
	 * @param joiningInstructions the joiningInstructions to set
	 */
	public void setJoiningInstructions(String joiningInstructions) {
		this.joiningInstructions = joiningInstructions;
	}


	/**
	 * Gets the town city.
	 * 
	 * @return the townCity
	 */
	public String getTownCity() {
		return townCity;
	}


	/**
	 * Sets the town city.
	 * 
	 * @param townCity the townCity to set
	 */
	public void setTownCity(String townCity) {
		this.townCity = townCity;
	}

	/**
	 * @return the singleCharityEvent
	 */
	public String getSingleCharityEvent() {
		return singleCharityEvent;
	}

	/**
	 * @param singleCharityEvent the singleCharityEvent to set
	 */
	public void setSingleCharityEvent(String singleCharityEvent) {
		this.singleCharityEvent = singleCharityEvent;
	}




}//end of class
