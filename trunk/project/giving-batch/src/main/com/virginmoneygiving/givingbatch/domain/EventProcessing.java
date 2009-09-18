
package com.virginmoneygiving.givingbatch.domain;

import com.virginmoney.glis.messages.EventCategoryType;
import com.virginmoney.glis.messages.EventProcessingTypes;
import com.virginmoney.glis.messages.MessageHeader;

/**
 * Domain version of {@link com.virginmoney.glis.messages.EventProcessing}.
 */
public class EventProcessing {

    /** Event Type. */
    protected EventProcessingTypes eventType;
    /** Batch number. */
    protected String batchNumber;
    /** Event category. */
    protected EventCategoryType eventCategory;
    
    /** Message Header .*/
    protected MessageHeader header;

    /**
	 * @return the header
	 */
	public MessageHeader getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(MessageHeader header) {
		this.header = header;
	}

	/**
     * Gets the value of the eventType property.
     *
     * @return
     *     possible object is
     *     {@link EventProcessingTypes }
     */
    public EventProcessingTypes getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
      
     * @param value
     *     allowed object is
     *     {@link EventProcessingTypes }
     */
    public void setEventType(EventProcessingTypes eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the value of the batchNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the value of the batchNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the value of the eventCategory property.
     *
     * @return
     *     possible object is
     *     {@link EventCategoryType }
     */
    public EventCategoryType getEventCategory() {
        return eventCategory;
    }

    /**
     * Sets the value of the eventCategory property.
     *
     * @param value
     *     allowed object is
     *     {@link EventCategoryType }
     */
    public void setEventCategory(EventCategoryType eventCategory) {
        this.eventCategory = eventCategory;
    }

}
