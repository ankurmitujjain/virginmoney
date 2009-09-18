package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * The data object that represents the charity details subset retrieved based on
 * charity name.
 * 
 * @author Sejal Shah
 */
public class CharitySubset implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Charity ID. */
    private Long id;

    /** Charity Name. */
    private String name;

    /** Registered Charity Number for Charity. */
    private String registeredCharityNumber;

    /**
     * Gets the id.
     * 
     * @return the charity_id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Gets the registered charity number.
     * 
     * @return the registeredCharityNumber
     */
    public String getRegisteredCharityNumber() {
        return registeredCharityNumber;
    }

    /**
     * Sets the registered charity number.
     * 
     * @param registeredCharityNumber the registeredCharityNumber to set
     */
    public void setRegisteredCharityNumber(String registeredCharityNumber) {
        this.registeredCharityNumber = registeredCharityNumber;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("CharitySubset ( ")
            .append("id = ").append(id).append(tab)
            .append("name = ").append(name).append(tab)
            .append("registeredCharityNumber = ").append(registeredCharityNumber).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
