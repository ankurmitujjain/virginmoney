package com.virginmoneygiving.giving.domain;

/**
 * The DTO object to represent the search fundraising event parameter data.
 * 
 * @author Esakkiy
 */
public class SearchFundraisingEventParameter {

    /** Event name. */
    protected String eventname;

    /** Event date. */
    protected String eventDate;

    /** Event location. */
    protected String location;

    /** Event type. */
    protected String eventType;

    /** Charity name. */
    protected String charityName;
    
    /** charity Id of type Long for searching. */
    protected Long charityId;

    /**
     * Gets the value of the eventname property.
     * 
     * @return possible object is {@link String }
     */
    public String getEventname() {
        return eventname;
    }

    /**
     * Sets the value of the eventname property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setEventname(String value) {
        this.eventname = value;
    }

    /**
     * Gets the value of the eventDate property.
     * 
     * @return possible object is {@link String }
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setEventDate(String value) {
        this.eventDate = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return possible object is {@link String }
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the eventType property.
     * 
     * @return possible object is {@link String }
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setEventType(String value) {
        this.eventType = value;
    }

    /**
     * Gets the value of the charityName property.
     * 
     * @return possible object is {@link String }
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the charity id.
     * 
     * @return the charityId
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the charity id.
     * 
     * @param value the charityId to set
     */
    public void setCharityId(Long value) {
        this.charityId = value;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("SearchFundraisingEventParameter ( ")
            .append("eventname = ").append(eventname).append(tab)
            .append("eventDate = ").append(eventDate).append(tab)
            .append("location = ").append(location).append(tab)
            .append("eventType = ").append(eventType).append(tab)
            .append("charityName = ").append(charityName).append(tab)
            .append("charityId = ").append(charityId).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
