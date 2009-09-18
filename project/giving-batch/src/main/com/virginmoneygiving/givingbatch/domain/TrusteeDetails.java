package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *  This class represents the details of the Trustee.
 *  @author Srinivas
 */
public class TrusteeDetails implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** The forename. */
    private String forename;
    
    /** The surname. */
    private String surname;
    
    /** The dob. */
    private Date dob;
    
    /** The address. */
    private Address address;

    /**
     * Gets the forename.
     * 
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the forename.
     * 
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the surname.
     * 
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     * 
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the dob.
     * 
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the dob.
     * 
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets the address.
     * 
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * 
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }   

}
