/**
 * 
 */
package com.virginmoneygiving.givingbatch.domain;

/**
 * The Enum CharityStatusType which has all the charity status types i.e LIV, IRC, Closed and Pending.
 * 
 * @author taruna
 */
public enum CharityStatusType {

    /** Live. */
    LIVE("Live"),
    
    /** IRC. */
    IRC("IRC"),
    
    /** Closed. */
    CLOSED("Closed"),
    
    /** Pending. */
    PENDING("Pending");

    /** Enum name. */
    private final String  type;

    /**
     * Constructor.
     * 
     * @param type the type value.
     */
    CharityStatusType(String type) {
        this.type = type;

    }

    /**
     * Gets the type.
     * 
     * @return the status
     */
    public String getType() {
        return type;
    }
}
