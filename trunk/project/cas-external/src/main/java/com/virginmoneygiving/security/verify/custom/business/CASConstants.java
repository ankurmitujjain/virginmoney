package com.virginmoneygiving.security.verify.custom.business;

/**
 * Holds Constants used in the CAS service
 *
 * User: choprah
 * Date: 22-Oct-2008
 * Time: 18:46:58.
 */
public class CASConstants {
    
    /** The Constant AUTHENTICATION_ERROR. */
    public static final String AUTHENTICATION_ERROR                              = "ERROR";
    
    /** The Constant PASSWORD_VALIDATION_OK. */
    public static final String PASSWORD_VALIDATION_OK                            = "VALIDATED-OK";
    
    /** The Constant PASSWORD_VALIDATION_FAILED. */
    public static final String PASSWORD_VALIDATION_FAILED                        = "VALIDATE-FAILED";
    
    /** The Constant PASSWORD_VALIDATION_TARGET_LOCKED_VMG. */
    public static final String PASSWORD_VALIDATION_TARGET_LOCKED_VMG             = "LOCKED-BY-VMG";
    
    /** The Constant PASSWORD_VALIDATION_TARGET_LOCKED_FAILED. */
    public static final String PASSWORD_VALIDATION_TARGET_LOCKED_FAILED          = "LOCKED-BY-FAILURE";
    
    /** The Constant PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK. */
    public static final String PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_OK       = "VALIDATED-TEMP-PASSOWRD";
    
    /** The Constant PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_EXPIRE. */
    public static final String PASSWORD_VALIDATION_TARGET_TEMP_PASSWORD_EXPIRE   = "TEMP-PASSOWRD-EXPIRED";

    /** The Constant REDIRECTION_TARGET_TYPE_REF. */
    public static final String REDIRECTION_TARGET_TYPE_REF                       = "TARGET_TYPE";

    /** The Constant REDIRECT_ACTION_PASSWORD_EXPIRED. */
    public static final String REDIRECT_ACTION_PASSWORD_EXPIRED           = "vmgssozone.redirect.temp.password.expired";
    
    /** The Constant REDIRECT_ACTION_PASSWORD_RESET. */
    public static final String REDIRECT_ACTION_PASSWORD_RESET             = "vmgssozone.redirect.temp.password.reset";
    
    /** The Constant REDIRECT_ACTION_ACCOUNT_LOCKED_BY_FAILURE. */
    public static final String REDIRECT_ACTION_ACCOUNT_LOCKED_BY_FAILURE  = "vmgssozone.redirect.account.locked.failed";
    
    /** The Constant REDIRECT_ACTION_ACCOUNT_LOCKED_BY_VMG. */
    public static final String REDIRECT_ACTION_ACCOUNT_LOCKED_BY_VMG      = "vmgssozone.redirect.account.locked.vmg";
    
    /** The Constant REDIRECT_ACTION_SERVICE_ERROR. */
    public static final String REDIRECT_ACTION_SERVICE_ERROR              = "vmgssozone.redirect.service.error";

    /** The Constant AFTER_FORCED_RESET_PASSWORD. */
    public static final String AFTER_FORCED_RESET_PASSWORD      = "AFTER_FORCED_RESET_PASSWORD";

    /** The Constant REDIRECT_ACTION_NO_SERVICE_DEFINED. */
    public static final String REDIRECT_ACTION_NO_SERVICE_DEFINED              = "vmgssozone.redirect.no.service.defined";
}
