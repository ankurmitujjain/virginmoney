package com.virginmoneygiving.cardpayment.business;


/**
 * Covers processing steps for Extended Authorise operations.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public enum AuthoriseActionEnum {

    /** The COMPLETE. */
    COMPLETE, 
    
    /** The CANCEL. */
     CANCEL, 
     
     /** The NONE. */
     NONE;

    /**
     * get the enum value as a String.
     * 
     * @return result
     */
    public String value() {
        return name();
    }

    /**
     * Create an enum from a string.
     * 
     * @param v value
     * 
     * @return type
     */
    public static AuthoriseActionEnum fromValue(String v) {
        return valueOf(v);
    }

}
