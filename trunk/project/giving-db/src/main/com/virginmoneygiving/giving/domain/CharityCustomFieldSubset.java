package com.virginmoneygiving.giving.domain;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.PreUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * The Class CharityCustomFieldSubset.
 * 
 * @author sunilh
 */
@Entity
@Table(name = "CHARITY_CUSTOM_FIELD")
public class CharityCustomFieldSubset extends AuditAttributes implements Serializable {


    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** primary key. */
    private Long id;
    
    /** Charity Id. */
	private Long charityId;
   
   /** Charity custom field type code represents charity entities. */
	private String customFieldTypeCode;	
	
	/** charity field labels represents custom field labels for charity entity. */
    private String fieldLabel;
    
    /** start date time. */
    private Timestamp startDateTime = null;
    
	/**
	 * Gets the charity id.
	 * 
	 * @return the charityId
	 */
    @Column(name = "CHARITY_ID")
	public Long getCharityId() {
		return charityId;
	}
	
	/**
	 * Sets the charity id.
	 * 
	 * @param charityId the charityId to set
	 */
	public void setCharityId(Long charityId) {
		this.charityId = charityId;
	}
	
	/**
	 * Gets the custom field type code.
	 * 
	 * @return the customFieldTypeCode
	 */
    @Column(name = "CUSTOM_FIELD_TYPE_CODE")
	public String getCustomFieldTypeCode() {
		return customFieldTypeCode;
	}
	
	/**
	 * Sets the custom field type code.
	 * 
	 * @param customFieldTypeCode the customFieldTypeCode to set
	 */
	public void setCustomFieldTypeCode(String customFieldTypeCode) {
		this.customFieldTypeCode = customFieldTypeCode;
	}
	
	/**
	 * Gets the field label.
	 * 
	 * @return the fieldLabel
	 */
    @Column(name = "FIELD_LABEL")
	public String getFieldLabel() {
		return fieldLabel;
	}
	
	/**
	 * Sets the field label.
	 * 
	 * @param fieldLabel the fieldLabel to set
	 */
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	
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
	 * Gets the serial version uid.
	 * 
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	
    


}
