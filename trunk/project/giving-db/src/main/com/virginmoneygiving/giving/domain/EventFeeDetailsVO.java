package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Value Object to store basic event, charity and event registration fee details.
 * 
 * @author Sushant Sawant
 */
public class EventFeeDetailsVO implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Charitable Activity Id. */
    private long charitableActivityId;
    
    /** Charity Id. */
    private long charityId;
    
    /** Charity name. */
    private String charityName;
    
    /** Charity Administrator First name. */
    private long eventId;
    
    /** Event name. */
    private String eventName;
    
    /** Event fee instructions. */
    private String feeInstructions;
    
    /** Flag which specifies whether alternate fee payment allowed. */
    private String alternateFeeInd;
    
    /** Event Fee List. */
    private List<EventFeeDetails> eventFeeList;

    /**
     * Getter method for charitableActivityId.
     * 
     * @return the charitableActivityId
     */
    public long getCharitableActivityId() {
        return charitableActivityId;
    }

    /**
     * Setter method for charitableActivityId.
     * 
     * @param charitableActivityId the charitableActivityId to set
     */
    public void setCharitableActivityId(long charitableActivityId) {
        this.charitableActivityId = charitableActivityId;
    }

    /**
     * Getter method for charityId.
     * 
     * @return the charityId
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Setter method for charityId.
     * 
     * @param charityId the charityId to set
     */
    public void setCharityId(long charityId) {
        this.charityId = charityId;
    }

    /**
     * Getter method for charityName.
     * 
     * @return the charityName
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Setter method for charityName.
     * 
     * @param charityName the charityName to set
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Getter method for eventId.
     * 
     * @return the eventId
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Setter method for eventId.
     * 
     * @param eventId the eventId to set
     */
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter method for eventName.
     * 
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Setter method for eventName.
     * 
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }



    /**
     * Getter method for feeInstructions.
     * 
     * @return the feeInstructions
     */
    public String getFeeInstructions() {
        return feeInstructions;
    }

    /**
     * Setter method for feeInstructions.
     * 
     * @param feeInstructions the feeInstructions to set
     */
    public void setFeeInstructions(String feeInstructions) {
        this.feeInstructions = feeInstructions;
    }

    /**
     * Getter method for alternateFeeInd.
     * 
     * @return the alternateFeeInd
     */
    public String getAlternateFeeInd() {
        return alternateFeeInd;
    }

    /**
     * Setter method for alternateFeeInd.
     * 
     * @param alternateFeeInd the alternateFeeInd to set
     */
    public void setAlternateFeeInd(String alternateFeeInd) {
        this.alternateFeeInd = alternateFeeInd;
    }

    /**
     * Getter method for eventFeeList.
     * 
     * @return the eventFeeList
     */
    public List<EventFeeDetails> getEventFeeList() {
        return eventFeeList;
    }

    /**
     * Setter method for eventFeeList.
     * 
     * @param eventFeeList the eventFeeList to set
     */
    public void setEventFeeList(List<EventFeeDetails> eventFeeList) {
        this.eventFeeList = eventFeeList;
    }

    /**
     * Getter method for serialVersionUID.
     * 
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    

	
}
