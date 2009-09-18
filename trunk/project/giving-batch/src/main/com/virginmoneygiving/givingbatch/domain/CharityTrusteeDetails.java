package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The data object representing Charity Trustee Details.
 */
public class CharityTrusteeDetails implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** The name of charity. * */
    private String name;

    /** Registration Number. * */
    private String referenceNumber;    
    
    /** The address. */
    private Address address;
    
    /** The trustees. */
    private List<TrusteeDetails> trustees;

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
     * Gets the reference Number.
     * 
     * @return the reference Number
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the reference Number.
     * 
     * @param referenceNumber the reference number
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
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

    /**
     * Gets the trustees.
     * 
     * @return the trustees
     */
    public List<TrusteeDetails> getTrustees() {
        return trustees;
    }

    /**
     * Sets the trustees.
     * 
     * @param trustees the trustees to set
     */
    public void setTrustees(List<TrusteeDetails> trustees) {
        this.trustees = trustees;
    } 
      
}
