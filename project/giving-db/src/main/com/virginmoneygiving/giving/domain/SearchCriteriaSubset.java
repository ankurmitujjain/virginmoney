package com.virginmoneygiving.giving.domain;
import java.io.Serializable;

/**
 * The DTO object to represent the search fundraising criteria subset data.
 * @author EssakiY
 */
public class SearchCriteriaSubset implements Serializable {

    /** serialVersionUID. */
   private static final long serialVersionUID = 1L;
   
   /** charity id. */
   private String charityId;
   
    /** entity type represents the charity entities. */
   private String entityType;
    
    /** represents month from the user selection criteria. */
   private String month;
    
    /** represents year from the user selection criteria. */
    private String year;
   
   /** represents selectType from the user selection criteria. */
    private String selectType;

    /** represents requestType from the user selection criteria. */
    private String requestType;

	/**
	 * Gets the charity id.
	 * 
	 * @return the charityId
	 */
	public String getCharityId() {
		return charityId;
	}

	/**
	 * Sets the charity id.
	 * 
	 * @param charityId the charityId to set
	 */
	public void setCharityId(String charityId) {
		this.charityId = charityId;
	}

	/**
	 * Gets the entity type.
	 * 
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * Sets the entity type.
	 * 
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 * 
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Gets the year.
	 * 
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 * 
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Gets the select type.
	 * 
	 * @return the selectType
	 */
	public String getSelectType() {
		return selectType;
	}

	/**
	 * Sets the select type.
	 * 
	 * @param selectType the selectType to set
	 */
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	/**
	 * Gets the request type.
	 * 
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * Sets the request type.
	 * 
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * Gets the serial version uid.
	 * 
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

    
}
