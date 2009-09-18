package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This class is used to hold event properties to print into xml file.
 * 
 * @author dibaskumarp
 */
public class EventSummary implements Serializable {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** Primary key for Address. */
    private Long id;

    /** Event Name. */
    private String name;

    /** Event Description. */
    private String description;

    /** Date and Time of the Event. */
    private Timestamp eventDatetime;

    /** Event Description. */
    private String eventTimeText;
    
    /** Open Date for the Event. */
    private Timestamp openDate;

    /** Expiry Date of the Event. */
    private Timestamp expiryDate;
    
    /** Location for the Event. */
    private String location;

    /** Event status for this event. */
    private String eventStatus;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets the event datetime.
     * 
     * @return the eventDatetime
     */
    public Timestamp getEventDatetime() {
        return eventDatetime;
    }

    /**
     * Sets the event datetime.
     * 
     * @param eventDatetime the eventDatetime to set
     */
    public void setEventDatetime(Timestamp eventDatetime) {
        this.eventDatetime = eventDatetime;
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
     * Gets the open date.
     * 
     * @return the openDate
     */
    public Timestamp getOpenDate() {
        return openDate;
    }

    /**
     * Sets the open date.
     * 
     * @param openDate the openDate to set
     */
    public void setOpenDate(Timestamp openDate) {
        this.openDate = openDate;
    }

    /**
     * Gets the expiry date.
     * 
     * @return the expiryDate
     */
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date.
     * 
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the location.
     * 
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     * 
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

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
}
