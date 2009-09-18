package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * The Class CharityCustomFieldValueSubset.
 */
@Entity
@Table(name = "CUSTOM_FIELD_VALUE")
public class CharityCustomFieldValueSubset extends AuditAttributes implements Serializable {

    /** primary key. */
    private Long id;
    
    /** charity custom field id fk to CHARITY_CUSTOM_FIELD. */
    private Long charityCustomFieldId;
	
	/** charity field fieldValue represents custom field fieldValue for charity entity. */
    private String fieldValue;
    
    /** Represents if a specific field value should be copied to fundraiser entity*/
    
    private String fieldCopy;
    
    /** donor id. */
    private Long donorId;
    
    /** fundraiser id. */
    private Long fundraiserId;
    
    /** fundraiser activity id. */
    private Long fundraiserActivityId;
    
    /** event id. */
    private Long eventId;
    
    /** start date time. */
    private Timestamp startDateTime = null;
    
    
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", precision = 10, nullable = false, unique = true)
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
	 * Gets the charity custom field id.
	 * 
	 * @return the charityCustomFieldId
	 */
	@Column(name = "CHARITY_CUSTOM_FIELD_ID")	
	public Long getCharityCustomFieldId() {
		return charityCustomFieldId;
	}
	
	/**
	 * Sets the charity custom field id.
	 * 
	 * @param charityCustomFieldId the charityCustomFieldId to set
	 */
	public void setCharityCustomFieldId(Long charityCustomFieldId) {
		this.charityCustomFieldId = charityCustomFieldId;
	}
	
	/**
	 * Gets the field value.
	 * 
	 * @return the fieldValue
	 */
    @Column(name = "FIELD_VALUE")
	public String getFieldValue() {
		return fieldValue;
	}
	
	/**
	 * Sets the field value.
	 * 
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	
	
	
	
	/**
	 * Gets the donor id.
	 * 
	 * @return the donorId
	 */
    @Column(name = "DONOR_ID")
	public Long getDonorId() {
		return donorId;
	}
	
	/**
	 * Sets the donor id.
	 * 
	 * @param donorId the donorId to set
	 */
	public void setDonorId(Long donorId) {
		this.donorId = donorId;
	}
	
	/**
	 * Gets the fundraiser id.
	 * 
	 * @return the fundraiserId
	 */
    @Column(name = "FUNDRAISER_ID")
	public Long getFundraiserId() {
		return fundraiserId;
	}
	
	/**
	 * Sets the fundraiser id.
	 * 
	 * @param fundraiserId the fundraiserId to set
	 */
	public void setFundraiserId(Long fundraiserId) {
		this.fundraiserId = fundraiserId;
	}
	
	/**
	 * Gets the fundraiser activity id.
	 * 
	 * @return the fundraiserActivityId
	 */
    @Column(name = "FUNDRAISER_ACTIVITY_ID")
	public Long getFundraiserActivityId() {
		return fundraiserActivityId;
	}
	
	/**
	 * Sets the fundraiser activity id.
	 * 
	 * @param fundraiserActivityId the fundraiserActivityId to set
	 */
	public void setFundraiserActivityId(Long fundraiserActivityId) {
		this.fundraiserActivityId = fundraiserActivityId;
	}
	
	/**
	 * Gets the event id.
	 * 
	 * @return the eventId
	 */
    @Column(name = "EVENT_ID")
	public Long getEventId() {
		return eventId;
	}
	
	/**
	 * Sets the event id.
	 * 
	 * @param eventId the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	/**
	 * Gets the start date time.
	 * 
	 * @return the startDateTime
	 */
    @Column(name = "START_DATETIME")	
	public Timestamp getStartDateTime() {
		return startDateTime;
	}
	
	/**
	 * Sets the start date time.
	 * 
	 * @param startDateTime the startDateTime to set
	 */
	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * @return the fieldCopy
	 */
	@Column(name = "EVENT_COPY_CODE_IND")
	public String getFieldCopy() {
		return fieldCopy;
	}

	/**
	 * @param fieldCopy the fieldCopy to set
	 */
	public void setFieldCopy(String fieldCopy) {
		this.fieldCopy = fieldCopy;
	}

    
    
}
