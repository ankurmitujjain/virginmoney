package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

/**
 * The data object that represents the charity administrator few details based on charity id.
 * 
 * @author Mahesh Yerudkar
 */
public class RegisteredCharityUser implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** user id. */
    private Long id;
    
    /** Charity Administrator forname. */
    private String forename;
    
    /** Charity Administrator surname. */
    private String surname;    
    
    /** The title. */
    private String title;    
    
    /** The login email address. */
    private String loginEmailAddress;    
    
    /** The birth day. */
    private int birthDay;
    
    /** The birth month. */
    private int birthMonth;
    
    /** The birth year. */
    private int birthYear;    

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

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the login email address.
     * 
     * @return the loginEmailAddress
     */
    public String getLoginEmailAddress() {
        return loginEmailAddress;
    }

    /**
     * Sets the login email address.
     * 
     * @param loginEmailAddress the loginEmailAddress to set
     */
    public void setLoginEmailAddress(String loginEmailAddress) {
        this.loginEmailAddress = loginEmailAddress;
    }

    /**
     * Gets the birth day.
     * 
     * @return the birthDay
     */
    public int getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the birth day.
     * 
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Gets the birth month.
     * 
     * @return the birthMonth
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /**
     * Sets the birth month.
     * 
     * @param birthMonth the birthMonth to set
     */
    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    /**
     * Gets the birth year.
     * 
     * @return the birthYear
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Sets the birth year.
     * 
     * @param birthYear the birthYear to set
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
