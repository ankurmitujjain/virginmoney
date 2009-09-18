package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * This class used to store the donor details.
 * 
 * @author Srinivas Nageli
 */
public class Person implements Serializable {

    /** Serial version id. */
    private static final long serialVersionUID = 6332199889569281956L;

    /** surname of the person. */
    private String surname;

    /** forename of the person. * */
    private String forename;

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

}
