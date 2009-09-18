package com.virginmoneygiving.giving.domain;

import java.sql.Timestamp;

/**
 * The Class OwnOrganisedEventsDVO.
 */
public class OwnOrganisedEventsDVO {
	
	/** serial version UID. */
    private static final long serialVersionUID = 7046685465449287008L;
    
    /** Id for Event Id. */
    private long eventId;
    
    /** name for Event Name. */
    private String name;
  
    /** The event date. */
    private Timestamp eventDateTime;
    
    /** The event time. */
    private String eventTimeText;
    
    /** The location. */
    private Location location;
    
    /** The logo URL. */
    private String logoUrl;
    
    
    /** The status of the event if present. */
    private EventStatus eventStatus;

	

	/**
	 * Gets the event date time.
	 * 
	 * @return the eventDateTime
	 */
	public Timestamp getEventDateTime() {
		return eventDateTime;
	}

	/**
	 * Sets the event date time.
	 * 
	 * @param eventDateTime the eventDateTime to set
	 */
	public void setEventDateTime(Timestamp eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	/**
	 * Gets the event time text.
	 * 
	 * @return the eventTimeText
	 */
	public String getEventTimeText() {
		return eventTimeText;
	}

	/**
	 * Sets the event time text.
	 * 
	 * @param eventTimeText the eventTimeText to set
	 */
	public void setEventTimeText(String eventTimeText) {
		this.eventTimeText = eventTimeText;
	}

	/**
	 * Gets the event id.
	 * 
	 * @return the eventId
	 */
	public long getEventId() {
		return eventId;
	}

	/**
	 * Sets the event id.
	 * 
	 * @param eventId the eventId to set
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * Gets the logo url.
	 * 
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * Sets the logo url.
	 * 
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * Gets the event status.
	 * 
	 * @return the eventStatus
	 */
	public EventStatus getEventStatus() {
		return eventStatus;
	}

	/**
	 * Sets the event status.
	 * 
	 * @param eventStatus the eventStatus to set
	 */
	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	
    
    
    
	
	

}//end of class
