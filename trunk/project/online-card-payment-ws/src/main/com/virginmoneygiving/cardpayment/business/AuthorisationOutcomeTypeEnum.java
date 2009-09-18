package com.virginmoneygiving.cardpayment.business;

/**
 * Possible outcomes of authorisation.
 * @author johna
 */
public enum AuthorisationOutcomeTypeEnum {

    /** the enums. */
    
    /** The SUCCESS. */
    SUCCESS,
    
    /** The AUTHENTICATION_REQUIRED. */
    AUTHENTICATION_REQUIRED, 
    
    /** The ERROR. */
    ERROR;

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
    public static AuthorisationOutcomeTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
