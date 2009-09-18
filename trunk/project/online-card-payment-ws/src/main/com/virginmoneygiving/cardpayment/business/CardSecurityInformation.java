package com.virginmoneygiving.cardpayment.business;

/**
 * The Class CardSecurityInformation.
 */
public class CardSecurityInformation {

    /** The Card Security Code. */
    protected String csc;
    
    /** The house number. */
    protected Integer houseNumber;
    
    /** The postcode. */
    protected String postcode;

    /**
     * Gets the value of the Card Security Code property.
     * 
     * @return possible object is {@link String }
     */
    public String getCsc() {
        return csc;
    }

    /**
     * Sets the value of the Card Security Code property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setCsc(String value) {
        this.csc = value;
    }

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return the house number
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value the value
     */
    public void setHouseNumber(Integer value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the postcode property.
     * 
     * @return possible object is {@link String }
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

}
