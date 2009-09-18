package com.virginmoneygiving.giving.domain;

import java.sql.Timestamp;
import java.util.Set;

/**
 * The DTO object to represent the search fundraising event result data.
 * 
 * @author Essaki
 */
public class SearchFundraisingEventResult {

    /** Event Id. */
    protected Long eventId;

    /** Event name. */
    protected String eventName;

    /** Event location. */
    protected String location;

    /** Charity id. */
    protected Long charityId;

    /** Charity name. */
    protected String charityName;

    /** Event date time. */
    protected Timestamp eventDatetime;

    /** Event date time. */
    protected String eventTimeText;

    /** Description. */
    protected String description;

    /** Charity supported indicator. */
    protected String charitySupportedInd;
    
    /** whether to alter the split of funds. */
    protected String fundraiserSplitOverrideInd;
    
    /** Proportion of funds raised going to charity. */
    protected String splitPercentage;
  
    /** operational Event Indicator to check event is created by operational user. */
    protected String operationalEventInd;
    
    /** Get charitable activites asscoaited with this event. */
    private Set<CharitableActivity> charitableActivities;

    /**
     * Gets the charitable activities.
     * 
     * @return the charitable activities
     */
    public Set<CharitableActivity> getCharitableActivities() {
		return charitableActivities;
	}

	/**
	 * Sets the charitable activities.
	 * 
	 * @param charitableActivities the new charitable activities
	 */
	public void setCharitableActivities(Set<CharitableActivity> charitableActivities) {
		this.charitableActivities = charitableActivities;
	}

	/**
	 * Gets the fundraiser split override ind.
	 * 
	 * @return the fundraiser split override ind
	 */
	public String getFundraiserSplitOverrideInd() {
		return fundraiserSplitOverrideInd;
	}

	/**
	 * Sets the fundraiser split override ind.
	 * 
	 * @param fundraiserSplitOverrideInd the new fundraiser split override ind
	 */
	public void setFundraiserSplitOverrideInd(String fundraiserSplitOverrideInd) {
		this.fundraiserSplitOverrideInd = fundraiserSplitOverrideInd;
	}

	/**
	 * Gets the split percentage.
	 * 
	 * @return the split percentage
	 */
	public String getSplitPercentage() {
		return splitPercentage;
	}

	/**
	 * Sets the split percentage.
	 * 
	 * @param splitPercentage the new split percentage
	 */
	public void setSplitPercentage(String splitPercentage) {
		this.splitPercentage = splitPercentage;
	}

	/**
	 * Gets the value of the eventId property.
	 * 
	 * @return the event id
	 */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     * @param value the value to set
     */
    public void setEventId(Long value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the eventName property.
     * 
     * @return possible object is {@link String }
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setEventName(String value) {
        this.eventName = value;
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
     * Gets the value of the charityId property.
     * 
     * @return charity id.
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value the value to set.
     */
    public void setCharityId(Long value) {
        this.charityId = value;
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
     * Gets the value of the eventDatetime property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public Timestamp getEventDatetime() {
        return eventDatetime;
    }

    /**
     * Sets the value of the eventDatetime property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setEventDatetime(Timestamp value) {
        this.eventDatetime = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return possible object is {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the charity supported ind.
     * 
     * @return the charitySupportedInd
     */
    public String getCharitySupportedInd() {
        return charitySupportedInd;
    }

    /**
     * Sets the charity supported ind.
     * 
     * @param charitySupportedInd the charitySupportedInd to set
     */
    public void setCharitySupportedInd(String charitySupportedInd) {
        this.charitySupportedInd = charitySupportedInd;
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
        return new StringBuilder("SearchFundraisingEventResult ( ")
            .append("eventId = ").append(eventId).append(tab)
            .append("eventName = ").append(eventName).append(tab)
            .append("location = ").append(location).append(tab)
            .append("charityId = ").append(charityId).append(tab)
            .append("charityName = ").append(charityName).append(tab)
            .append("eventDatetime = ").append(eventDatetime).append(tab)
            .append("eventTimeText = ").append(eventTimeText).append(tab)
            .append("description = ").append(description).append(tab)
            .append("charitySupportedInd = ").append(charitySupportedInd).append(tab)
            .append(super.toString())
            .append(" )").toString();
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
	 * Gets the operational Event Indicator.
	 *
	 * @return the operationalEventInd
	 */
	public String getOperationalEventInd() {
		return operationalEventInd;
	}

	/**
	 * Sets the operational Event Indicator.
	 *
	 * @param operationalEventInd the operationalEventInd to set
	 */
	public void setOperationalEventInd(String operationalEventInd) {
		this.operationalEventInd = operationalEventInd;
	}
}
