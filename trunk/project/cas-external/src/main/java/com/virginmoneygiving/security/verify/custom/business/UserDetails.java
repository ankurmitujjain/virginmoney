package com.virginmoneygiving.security.verify.custom.business;

/**
 * This class is used to store the users password request details.
 *
 * @author choprah
 * @author $Author$
 * @version $Version$
 * @vm.creation.date 01 May 2008
 * @vm.copyright.year 2008
 */
import java.io.Serializable;

/**
 * This class is used to store the users password request details.
 * 
 * @author HunarC
 */
public class UserDetails implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8343864967200862794L;

    /** Unique User ID. */
    private Long   id;
    
    /** Location of Password character 1. */
    private int    location1;
    
    /** Password character 1. */
    private String character1;
    
    /** Location of Password character 2. */
    private int    location2;
    
    /** Password character 2. */
    private String character2;

    /**
     * Gets the value of the id property.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value the value
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the location1 property.
     * 
     * @return the location1
     */
    public int getLocation1() {
        return location1;
    }

    /**
     * Sets the value of the location1 property.
     * 
     * @param value the value
     */
    public void setLocation1(int value) {
        this.location1 = value;
    }

    /**
     * Gets the value of the character1 property.
     * 
     * @return the character1
     * 
     * possible object is
     * {@link String }
     */
    public String getCharacter1() {
        return character1;
    }

    /**
     * Sets the value of the character1 property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setCharacter1(String value) {
        this.character1 = value;
    }

    /**
     * Gets the value of the location2 property.
     * 
     * @return the location2
     */
    public int getLocation2() {
        return location2;
    }

    /**
     * Sets the value of the location2 property.
     * 
     * @param value the value
     */
    public void setLocation2(int value) {
        this.location2 = value;
    }

    /**
     * Gets the value of the character2 property.
     * 
     * @return the character2
     * 
     * possible object is
     * {@link String }
     */
    public String getCharacter2() {
        return character2;
    }

    /**
     * Sets the value of the character2 property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setCharacter2(String value) {
        this.character2 = value;
    }

}
