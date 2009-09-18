package com.virginmoneygiving.security.verify.custom.business;

/**
 * This Class contains all the details related to password.
 * 
 * Created by IntelliJ IDEA.
 * User: choprah
 * Date: 03-Feb-2009
 * Time: 11:32:48
 * To change this template use File | Settings | File Templates.
 */
public class PasswordDetails {
    
    /** The password position. */
    private int passwordPosition;
    
    /** The password character. */
    private String passwordCharacter;


    /**
     * Gets the password position.
     * 
     * @return the password position
     */
    public int getPasswordPosition() {
        return passwordPosition;
    }

    /**
     * Sets the password position.
     * 
     * @param passwordPosition the new password position
     */
    public void setPasswordPosition(int passwordPosition) {
        this.passwordPosition = passwordPosition;
    }

    /**
     * Gets the password character.
     * 
     * @return the password character
     */
    public String getPasswordCharacter() {
        return passwordCharacter;
    }

    /**
     * Sets the password character.
     * 
     * @param passwordCharacter the new password character
     */
    public void setPasswordCharacter(String passwordCharacter) {
        this.passwordCharacter = passwordCharacter;
    }
}
