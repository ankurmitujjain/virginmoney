package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * Value Object to store charity retrieved based on search criteria.
 * 
 * @author dibaskumarp
 */
public class CharityVO implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;
    
    /** Charity Name. */
    private String charityName;

    /** Vmg Reference Number of the Charity. */
    private Long vmgReference;
    
    /** Post Code of Charity. */
    private String postCode;
    
    /** Charity Number. */
    private String charityNumber;
    
    /** Status of Charity. */
    private String status;
    
    /** Charity Id. */
    private Long charityId;

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
	 * @param charityId the charityId to set
	 */
	public void setCharityId(Long charityId) {
		this.charityId = charityId;
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
     * Gets the vmg reference.
     * 
     * @return the vmgReference
     */
    public Long getVmgReference() {
        return vmgReference;
    }

    /**
     * Sets the vmg reference.
     * 
     * @param vmgReference the vmgReference to set
     */
    public void setVmgReference(Long vmgReference) {
        this.vmgReference = vmgReference;
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
     * Gets the charity number.
     * 
     * @return the charityNumber
     */
    public String getCharityNumber() {
        return charityNumber;
    }

    /**
     * Sets the charity number.
     * 
     * @param charityNumber the charityNumber to set
     */
    public void setCharityNumber(String charityNumber) {
        this.charityNumber = charityNumber;
    }

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
