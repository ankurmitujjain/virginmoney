package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The data object representing AMLData details.
 */
public class AMLData implements Serializable {

 
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The extract date. */
    private Date extractDate;
    
    /** The charities. */
    private List<CharityTrusteeDetails> charities;

   
    /**
     * Gets the extract date.
     * 
     * @return the extract date
     */
    public Date getExtractDate() {
        return extractDate;
    }

    /**
     * Sets the extract date.
     * 
     * @param extractDate the new extract date
     */
    public void setExtractDate(Date extractDate) {
        this.extractDate = extractDate;
    }

    /**
     * Gets the charities.
     * 
     * @return the charities
     */
    public List<CharityTrusteeDetails> getCharities() {
        return charities;
    }

    /**
     * Sets the charities.
     * 
     * @param charities the new charities
     */
    public void setCharities(List<CharityTrusteeDetails> charities) {
        this.charities = charities;
    }
    
}
