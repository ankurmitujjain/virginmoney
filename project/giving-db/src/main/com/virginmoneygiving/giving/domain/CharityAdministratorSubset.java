package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * The data object that represents the charity administrator few details based on charity id.
 * 
 * @author Mahesh Yerudkar
 */
public class CharityAdministratorSubset implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Charity administrator ID. */
    private Long id;

    /** Charity Administrator forname. */
    private String forename;

    /** Charity Administrator surname. */
    private String surname;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * @param forename the new forename
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
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }


}
