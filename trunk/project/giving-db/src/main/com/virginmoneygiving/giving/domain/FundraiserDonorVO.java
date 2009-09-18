package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * Value Object to store fundraiser or donor retrieved based on search criteria.
 * 
 * @author dibaskumarp
 */
public class FundraiserDonorVO implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Fundraiser/Donor Name. */
    private String name;

    /** Vmg Reference Number for Fundraiser/Donor. */
    private Long vmgReference;

    /** Post Code of Fundraiser/Donor. */
    private String postCode;

    /** Date of Birth of Fundraiser/Donor. */
    private String dateOfBirth;

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
     * Gets the date of birth.
     * 
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     * 
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
