package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing Address details.
 */
public class Address implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** Address Line 1. * */
    private String address1;

    /** Address Line 2. * */
    private String address2;

    /** Address Line 3. * */
    private String address3;
    
    /** Address Line 4.* */ 
    private String address4;
    
    /** Post Code. * */
    private String postcode;

    /**
     * Gets the address1.
     * 
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the address1.
     * 
     * @param address1 the new address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Gets the address2.
     * 
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the address2.
     * 
     * @param address2 the new address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Gets the address3.
     * 
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * Sets the address3.
     * 
     * @param address3 the new address3
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * Gets the postcode.
     * 
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the postcode.
     * 
     * @param postcode the new postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }    
    
    /**
     * Gets the address4.
     * 
     * @return the address4
     */
    public String getAddress4() {
        return address4;
    }

    /**
     * Sets the address4.
     * 
     * @param address4 the new address4
     */
    public void setAddress4(String address4) {
        this.address4 = address4;
    }
}
