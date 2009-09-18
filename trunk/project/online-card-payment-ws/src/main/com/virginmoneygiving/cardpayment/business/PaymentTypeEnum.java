package com.virginmoneygiving.cardpayment.business;

/**
 * Payment types.
 */
public enum PaymentTypeEnum {

    /** The REGISTRATION FEE. */
    REGISTRATION_FEE, 
    
     /** The EVENT FEE. */
     EVENT_FEE,
     
     /** The REGULAR. */
     REGULAR, 
     
     /** The PAYMENT. */
     PAYMENT, 
     
     /** The REGULAR INITIAL. */
     REGULAR_INITIAL, 
     
     /** The REGULAR UPDATE. */
     REGULAR_UPDATE;

    /**
     * Get the name of the enum.
     * 
     * @return name of the enum.
     */
    public String value() {
        return name();
    }

    /**
     * Create an enum value from a string.
     * 
     * @param v name of the enum.
     * 
     * @return Enum.
     */
    public static PaymentTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
