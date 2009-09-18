package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing charity addresses details.
 * 
 * @author taruna
 */
public class CharityAddresses implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Address Line 1. * */
    private String addressLine1;

    /** Address Line 2. * */
    private String addressLine2;

    /** Address Line 3. * */
    private String addressLine3;

    /** Address Line 4. * */
    private String addressLine4;

    /** Address Line 5. * */
    private String addressLine5;

    /** City. * */
    private String city;

    /** County or State. * */
    private String countyOrState;

    /** Post Code or Zip Code. * */
    private String postCodeOrZipCode;

    /** Country. * */
    private String country;

    /**
     * Gets the address line1.
     * 
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the address line1.
     * 
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets the address line2.
     * 
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the address line2.
     * 
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets the address line3.
     * 
     * @return the addressLine3
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * Sets the address line3.
     * 
     * @param addressLine3 the addressLine3 to set
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
     * Gets the address line4.
     * 
     * @return the addressLine4
     */
    public String getAddressLine4() {
        return addressLine4;
    }

    /**
     * Sets the address line4.
     * 
     * @param addressLine4 the addressLine4 to set
     */
    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    /**
     * Gets the address line5.
     * 
     * @return the addressLine5
     */
    public String getAddressLine5() {
        return addressLine5;
    }

    /**
     * Sets the address line5.
     * 
     * @param addressLine5 the addressLine5 to set
     */
    public void setAddressLine5(String addressLine5) {
        this.addressLine5 = addressLine5;
    }

    /**
     * Gets the city.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     * 
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the county or state.
     * 
     * @return the countryOrState
     */
    public String getCountyOrState() {
        return countyOrState;
    }

    /**
     * Sets the county or state.
     * 
     * @param countryOrState the countryOrState to set
     */
    public void setCountyOrState(String countryOrState) {
        this.countyOrState = countryOrState;
    }

    /**
     * Gets the post code or zip code.
     * 
     * @return the postCodeOrZipCode
     */
    public String getPostCodeOrZipCode() {
        return postCodeOrZipCode;
    }

    /**
     * Sets the post code or zip code.
     * 
     * @param postCodeOrZipCode the postCodeOrZipCode to set
     */
    public void setPostCodeOrZipCode(String postCodeOrZipCode) {
        this.postCodeOrZipCode = postCodeOrZipCode;
    }

    /**
     * Gets the country.
     * 
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     * 
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
