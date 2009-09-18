package com.virginmoneygiving.giving.constants;

/**
 * Constants for DAO layer.
 * 
 * @author sivakumar
 */
public final class ServiceConstants {
    
    /**
     * Default constructor.
     */
    private ServiceConstants() {
    }

    /** used inside JPACharityDAO Impl as named parameter. */
    public static final String CHARITY_NAME = "charityName";

    /** used inside JPACharityDAO Impl as named parameter. */
    public static final String CHARITY_NUMBER = "charityNumber";

    /** used inside JPACharityDAO Impl as named parameter. */
    public static final String NAME = "name";

    /** used inside JPACharityDAO Impl as named parameter. */
    public static final String REGISTRATION_NUMBER = "registrationNumber";

    /** used inside JPACharityDAO Impl as named parameter. */
    public static final String CHARITY_ID = "charityId";

    /** Error message key. */
    public static final String SAVE_CHARITY_ERROR_MESSAGE_KEY =
            "error.message.key";

    /** Error message code. */
    public static final String SAVE_CHARITY_ERROR_MESSAGE_CODE =
            "error.message.code";

    /** Default error message. */
    public static final String SAVE_CHARITY_DEFAULT_ERROR_MESSAGE =
            "Exception occured";
    
    /** number regular expression. */
    public static final String NUMBER_REGULAR_EXPRESSION = "(^[0-9]*$)";

    /** number regular expression. */
    public static final String PROCESSED = "PRO";

    
    /** Code for administration address type. */
    public static final String ADDRESS_TYPE_ADMIN = "ADMIN";
    
    /** Integer Constants. */
    public static final int ZERO = 0;
    
    /** The Constant ONE. */
    public static final int ONE = 1;
    
    /** The Constant TWO. */
    public static final int TWO = 2;
    
    /** The Constant THREE. */
    public static final int THREE = 3;
    
    /** The Constant FOUR. */
    public static final int FOUR = 4;
    
    /** The Constant FIVE. */
    public static final int FIVE = 5;
    
    /** The Constant SIX. */
    public static final int SIX = 6;
    
    /** UCCHA090- start. */
	public static final String REPORTING_CODES_ALL_ALLOCATED="ALL_ALLOCATED";
	
	/** The Constant REPORTING_CODES_NOT_ALLOCATED. */
	public static final String REPORTING_CODES_NOT_ALLOCATED="NOT_ALLOCATED";
    /** UCCHA090- end */
}
