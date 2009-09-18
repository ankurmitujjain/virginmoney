package com.virginmoneygiving.giving.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class hold all the master data code references.
 * 
 * @author vishal samant
 * @author Puneet Swarup - added address type constant for billing.
 */
public final class MasterDataCodeConstants {

    /**
     * Private constructor.
     */
    private MasterDataCodeConstants() {
        // empty private constructor.
    }

    /** Code for administration address type. */
    public static final String ADDRESS_TYPE_ADMIN = "ADMIN";

    /** Code for registered address type. */
    public static final String ADDRESS_TYPE_REGISTERED = "REGISTER";

    /** Code for billing address type. */
    public static final String ADDRESS_TYPE_BILLING = "BILLING";

    /** Code for registered fundraiser type. */
    public static final String ADDRESS_TYPE_FUR = "FUR";

    /** Code for registered donor type. */
    public static final String ADDRESS_TYPE_DONOR = "DON";

    /** UK country code. */
    public static final String COUNTRY_CODE_UK = "GB";

    /** Closure reason code. */
    public static final String CLOSURE_REASON_CODE_CLOSE = "CLOSE";

    /** Charity status code - Initial registration complete. */
    public static final String CHARITY_STATUS_CODE_IRC = "IRC";

    /** Charity status code - Unregistered with VMG site. */
    public static final String CHARITY_STATUS_CODE_UNR = "UNR";

    /** Charity status code - Live. */
    public static final String CHARITY_STATUS_CODE_LIV = "LIV";

    /** Charity status code - Suspended. */
    public static final String CHARITY_STATUS_CODE_SUS = "SUS";

    /** Charity status code - Closed. */
    public static final String CHARITY_STATUS_CODE_CLS = "CLS";

    /** Charity register code - Closed. */
    public static final String CHARITY_REGISTER_CODE_NEW = "NEW";

    /** Fundraiser status code - registered. */
    public static final String FUNDRAISER_STATUS_CODE_REGISTERED = "REGISTERED";

    /** Fundraiser status code - registered. */
    public static final String FUNDRAISER_STATUS_CODE_CLOSED = "CLOSED";
    
    /** Fundraiser status code - registered. */
    public static final String FUNDRAISER_STATUS_CODE_FAKE = "FAKE";

    /** Personal Email address type indicator. */
    public static final String EMAIL_ADDRESS_TYPE_PERSONAL = "P";

    /** Email address type indicator for contact email address. */
    public static final String EMAIL_ADDRESS_TYPE_CONTACT = "C";

    /** Main indicator for Charity Administrators as Y. */
    public static final String CHARITY_ADMIN_MAIN_IND_Y = "Y";

    /** Main indicator for Charity Administrators as N. */
    public static final String CHARITY_ADMIN_MAIN_IND_N = "N";

    /** Telephone line type indicator for landline. */
    public static final String TELEPHONE_LINE_TYPE_LANDLINE = "L";

    /** Telephone line type indicator for mobile. */
    public static final String TELEPHONE_LINE_TYPE_MOBILE = "M";

    /** Telephone line type indicator for fax. */
    public static final String TELEPHONE_LINE_TYPE_FAX = "F";

    /** Telephone line main indicator. */
    public static final String TELEPHONE_LINE_MAIN_YES = "Y";

    /** Telephone line main indicator. */
    public static final String TELEPHONE_LINE_MAIN_NO = "N";

    /** Fundraising reason code as PERSONAL. */
    public static final String FUNDRAISING_REASON_CODE_CHALLENGE = "CHALLENGE";

    /** Fundraising reason code as OCCASION. */
    public static final String FUNDRAISING_REASON_CODE_OCCASION = "OCCASION";

    /** Fundraising reason code as MEMORY. */
    public static final String FUNDRAISING_REASON_CODE_MEMORY = "MEMORY";

    /** Fundraising reason code as ORGANIZED. */
    public static final String FUNDRAISING_REASON_CODE_ORGANIZED = "ORGANIZED";

    /** Fundraising group indicator. */
    public static final String FUNDRAISER_AS_GROUP = "G";

    /** Fundraising individual indicator. */
    public static final String FUNDRAISER_AS_INDIVIDUAL = "I";

    /** Occasion code type. */
    public static final String OCCASION_CODE_OTHER = "OTHER_OCC";

    /** Fundraising reason code as ORGANIZED. */
    public static final String ORGINIZED_EVENT_INDICATOR = "O";

    /** CHANNEL_SOURCE_VMG. */
    public static final String CHANNEL_SOURCE_VMG = "VMG";

    /** Great Britain currency. */
    public static final String GBP_CURRENCY = "GBP";
    
    /** Pound symbol. */
    public static final String POUND_SYMBOL = "&pound;";

    /** Comma separator. */
    public static final String COMMA_SEPERATOR = ",";

    /** Donation type. */
    public static final String WEB_DONATION = "WEB_DONATION";

    /** Event Registration Fee type. */
    public static final String EVENT_REGISTRATION_FEE =
            "EVENT_REGISTRATION_FEE";
    
    /** Charity Registration Fee type. */
    public static final String CHARITY_REGISTRATION_FEE =
            "CHARITY_REGISTRATION_FEE";

    /** Indicates that the event supports only Single live registered charity. */
    public static final String CHARITIES_SUPPORTED_BY_EVENT_SINGLE = "S";

    /** Indicates that the event supports All (any) live registered charities. */
    public static final String CHARITIES_SUPPORTED_BY_EVENT_ALL = "A";

    /** Indicates that the event supports Restricted live registered charities. */
    public static final String CHARITIES_SUPPORTED_BY_EVENT_RESTRICTED = "R";

    /** Indicates that the fundraiser's event supports only multiple live registered charity. */
    public static final String CHARITIES_SUPPORTED_MULTIPLE = "M";

    /** Indicates a charity donation is a one off. */
    public static final String DONATION_CHARITY_INDICATOR_SINGLE = "S";

    /** Indicates a charity donation is monthly. */
    public static final String DONATION_CHARITY_INDICATOR_MULTIPLE = "M";

    /** Reference type for generate sequence. */
    public static final String INVOICE = "INVOICE";

    /** Activity type other. */
    public static final String ACTIVITY_TYPE_OTHER = "Other";

    /** The Constant USER_NOT_LOCKED. */
    public static final String USER_NOT_LOCKED = "N".intern();

    /** The Constant USER_TEMP_LOCKED. */
    public static final String USER_TEMP_LOCKED = "T".intern();

    /** The Constant USER_PERM_LOCKED. */
    public static final String USER_PERM_LOCKED = "P".intern();

    /** The Constant YES value. */
    public static final String YES = "Y";

    /** The Constant MONTHLY. */
    public static final String MONTHLY = "M";

    /** The Constant BUSINESS. */
    public static final String BUSINESS = "B";

    /** The Constant PERSONAL. */
    public static final String PERSONAL = "P";

    /** The Constant for EVENT STATUS DRAFT. */
    public static final String DRAFT = "DRF";

    /** The Constant for EVENT STATUS PUBLISH. */
    public static final String PUBLISH = "PUB";

    /** The Constant for EVENT STATUS PENDING. */
    public static final String PENDING = "PEN";

    /** The Constant for EVENT STATUS EXPIRED. */
    public static final String EXPIRED = "EXP";

    /** The Constant for EVENT STATUS CANCELLED. */
    public static final String CANCELLED = "CAN";

    /** The Constant for ACTIVE Bank account status. */
    public static final String ACTIVE = "ACT";

    /** The Constant for INACTIVE Bank account status. */
    public static final String INACTIVE = "INAC";
    
    /** The Constants for DELETION_REQUESTED Bank account status. */
    public static final String DELETION_REQUESTED = "DLRQ";

    /** The Constants for PRE_AUTHORISED Bank account status. */
    public static final String PRE_AUTHORISED = "PREAUTH";
    
    /** The Constants for NO. */
    public static final String NO = "N";

    /** Address type for bank address. */
    public static final String BANK = "BANK";

    /** Charity org type for commission data. */
    public static final String ORG_TYPE_REGISTERED = "R";

    /** Batch status. */
    public static final String BATCH_STATUS = "PROCESSED";


    // regular donation status codes
    /** The Constant REGULAR_DONATION_STATUS_ACTIVE. */
    public static final String REGULAR_DONATION_STATUS_ACTIVE = "ACTIVE";
    
    /** The Constant REGULAR_DONATION_STATUS_FAILED. */
    public static final String REGULAR_DONATION_STATUS_FAILED = "FAILED";
    
    /** The Constant REGULAR_DONATION_STATUS_CANCELLED. */
    public static final String REGULAR_DONATION_STATUS_CANCELLED = "CANCELLED";

    /** Batch Status Pending. */

    /**
     * Constants for Pending status.
     */
    /** IRC status pending. **/
    public static final String IRC_PENDING = "IRC_PENDING";

    /** LIV status pending. * */
    public static final String LIV_PENDING = "LIV_PENDING";

    /** Active page status. * */
    public static final String PAGE_STATUS_ACTIVE = "ACT";

    /** Inactive page status. * */
    public static final String PAGE_STATUS_INACTIVE = "INACT";

    /** The Constant PAGE_TYPE_FUNDRAISER. */
    public static final String PAGE_TYPE_FUNDRAISER = "FUND";
    
    /** Group - team. * */
    public static final String GROUP_TYPE_TEAM = "TEAM";

    /** Group - network. * */
    public static final String GROUP_TYPE_NETWORK = "NETWORK";

    /** UCCHA090- Start- Constants for charity custom reporting fields. */
    public static final String DELIM = "##";

    /** The Constant CustomFieldTypes_DONOR. */
    public static final String CustomFieldTypes_DONOR = "DON";

    /** The Constant CustomFieldTypes_DONOR_DEFAULT_LABEL. */
    public static final String CustomFieldTypes_DONOR_DEFAULT_LABEL =

            "Donor code 1##Donor code 2##Donor code 3##Donor code 4##Donor code 5";

    /** The Constant CustomFieldTypes_EVENT. */
    public static final String CustomFieldTypes_EVENT = "EVT";

    /** The Constant CustomFieldTypes_EVENT_DEFAULT_LABEL. */
    public static final String CustomFieldTypes_EVENT_DEFAULT_LABEL =

            "Event code 1##Event code 2##Event code 3##Event code 4##Event code 5";

    /** The Constant CustomFieldTypes_FUNDRAISER. */
    public static final String CustomFieldTypes_FUNDRAISER = "FUN";

    /** The Constant CustomFieldTypes_FUNDRAISER_DEFAULT_LABEL. */
    public static final String CustomFieldTypes_FUNDRAISER_DEFAULT_LABEL =

            "Fundraiser code 1##Fundraiser code 2##Fundraiser code 3##Fundraiser code 4##Fundraiser code 5";

    /** The Constant CustomFieldTypes_FUNDRAISER_ACTIVITY. */
    public static final String CustomFieldTypes_FUNDRAISER_ACTIVITY = "FAC";

    /** The Constant CustomFieldTypes_FUNDRAISER_ACTIVITY_DEFAULT_LABEL. */
    public static final String CustomFieldTypes_FUNDRAISER_ACTIVITY_DEFAULT_LABEL =

            "Page code 1##Page code 2##Page code 3##Page code 4##Page code 5";
    
    /** The Constant FUNDRAISING_PAGE_PARAM1. */
    public static final String FUNDRAISING_PAGE_PARAM1 = "userUrl=";

	/** The Constant FUNDRAISING_PAGE_PARAM2. */
	public static final String FUNDRAISING_PAGE_PARAM2 = "pageUrl=";
    
    /** The Constant FUNDRAISING_PAGE_HASH. */
    public static final String FUNDRAISING_PAGE_HASH="#"; 
    
    /** Constant for true */
    public static final String TRUE="true"; 
    
    /** Constant for false */
    public static final String FALSE="false"; 
    
    
    /** Option selected-true */
    
    public static final String COPY_SELECTED_TRUE="Y";
    
    /** Option selected is false*/
    
    public static final String COPY_SELECTED_FALSE="N";
    
    /**Empty string */
    
    public static final String EMPTY_STRING="";
    
    /** Constant for fundraising to team as a whole */
    public static final String SPONSOR_TEAM = "sponsorTeam";    
    

    /**
     * UCCHA090- End- Constants for charity custom reporting fields.
     */

    /** Role codes. */
    //TODO Whenever you make change to a existing role code in this list,
    //please make necessary role code changes in NamedQueryRepository class. if any.
    public enum Roles {

        /** Charity Administration. */
        ROLE_CHARITY_ADMIN("ROLE_C_AD"),

        /** Charity User - Other than Admin. */
        ROLE_CHARITY_USER("ROLE_C_US"),

        /** Fundraiser. */
        ROLE_FUNDRAISER("ROLE_FUND"),

        /** Donor. */
        ROLE_DONOR("ROLE_DONOR"),

        /** Operations user. */
        ROLE_OPS_USER("ROLE_O_US"),

        /** Operations Admin. */
        ROLE_OPS_ADMIN("ROLE_O_AD"),

        /** Every user who uses reporting has to have this role. */
        ROLE_USER("ROLE_USER"),

        /** This is the jasper super user. */
        ROLE_ADMINISTRATOR("ROLE_ADMINISTRATOR");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        Roles(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Offline registration status codes.
     */
    public enum OfflineRegStatus {

        /** Completed. */
        ORS_COMPLETED("COMP"),

        /** Received from HMRC. */
        ORS_HMRC_RECEIVED("HMRC_REV"),

        /** Sent to HMRC. */
        ORS_HMRC_SENT("HMRC_SENT"),

        /** Incomplete. */
        ORS_INCOMPLETE("IN_COMP"),

        /** Not applicable. */
        ORS_NOT_APPLICABLE("NOT_APP"),

        /** Outstanding. */
        ORS_OUTSTANDING("OUT_STD"),

        /** Received. */
        ORS_RECEIVED("RECEIVED"),

        /** Received and accepted. */
        ORS_RECEIVED_ACCEPTED("REV_ACPT"),

        /** Received from charity. */
        ORS_RECEIVED_FROM_CHARITY("REV_CHA"),

        /** Received incomplete. */
        ORS_RECEIVED_INCOMPLETE("REV_INCOMP"),

        /** Waived. */
        ORS_WAIVED("WAIVED");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        OfflineRegStatus(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Bank account type codes.
     */
    public enum BankAccountType {

        /** Trading bank account type. */
        BANK_ACCOUNT_TYPE_TRADING("TRA"),

        /** Donation bank account type. */
        BANK_ACCOUNT_TYPE_DONATION("DON"),

        /** Waived. */
        BANK_ACCOUNT_TYPE_BOTH("BOTH");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        BankAccountType(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Bank account status codes.
     */
    public enum BankAccountStatus {

        /** Active. */
        BANK_ACCOUNT_STATUS_ACTIVE("ACT"),

        /** Deletion requested. */
        BANK_ACCOUNT_STATUS_DELETION_REQUESTED("DLRQ"),

        /** Inactive. */
        BANK_ACCOUNT_STATUS_INACTIVE("INAC"),

        /** Waived. */
        BANK_ACCOUNT_STATUS_PREAUTHORISATION("PREAUTH");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        BankAccountStatus(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Page status codes.
     */
    public enum PageStatus {

        /** Active. */
        PAGE_STATUS_ACTIVE("ACT"),

        /** Inactive. */
        PAGE_STATUS_INACTIVE("INAC");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        PageStatus(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Charity status codes.
     */
    public enum CharityStatus {

        /** Initial registration completed. */
        CHARITY_STATUS_INITIAL_REG_COMPLETED("IRC"),

        /** Live. */
        CHARITY_STATUS_LIVE("LIV"),

        /** Suspended. */
        CHARITY_STATUS_SUSPENDED("SUS"),

        /** Closed. */
        CHARITY_STATUS_CLOSED("CLS"),

        /** Unregistered with VMG. */
        CHARITY_STATUS_UNREGISTERED("UNR"),
        
        /** FAKE charity registration. */
        CHARITY_STATUS_FAKE("FAKE"),
        
        /** Processed. */
        CHARITY_STATUS_PROCESSED("PRO");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        CharityStatus(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Group codes.
     */
    //TODO Whenever you make change to a existing group code in this list,
    //please make necessary role code changes in NamedQueryRepository class if any.
    public enum Groups {

        /** Charity - User Maintenance. */
        GROUP_CHARITY_USER_MAINT("GROUP_CHARITY_USER_MAINT"),

        /** Charity - Account - Maintenance. */
        GROUP_CHARITY_ACCOUNT_MAINT("GROUP_CHARITY_ACCOUNT_MAINT"),
        
        /** Charity - Reporting Allocate. */
        GROUP_CHARITY_REPORTING_CREATE("GROUP_CHARITY_REPORTING_CREATE"),

        /** Charity - Event. */
        GROUP_CHARITY_EVENTS("GROUP_CHARITY_EVENTS"),

        /** Charity - Reporting - Setup and allocation of codes. */
        GROUP_CHARITY_REPORTING_SETUP("GROUP_CHARITY_REPORTING_SETUP"),

        /** Charity - Reporting - View reports. */
        GROUP_CHARITY_REPORTING_VIEW("GROUP_CHARITY_REPORTING_VIEW"),

        /** Charity - Charity Page. */
        GROUP_CHARITY_CHARITY_PAGE("GROUP_CHARITY_CHARITY_PAGE"),

        /** Charity - Alerts. */
        GROUP_CHARITY_ALERTS("GROUP_CHARITY_ALERTS"),

        /** Charity - Forum. */
        GROUP_CHARITY_FORUM("GROUP_CHARITY_FORUM"),

        /** Global - Payment received. Read only. */
        GROUP_PAY_RECEIVED_VIEW("GROUP_PAY_RECEIVED_VIEW"),

        /** Global - Payment received. Read/write. */
        GROUP_PAY_RECEIVED_CREATE("GROUP_PAY_RECEIVED_CREATE"),

        /** Global - Gift aid claim. Read only. */
        GROUP_GIFT_AID_CLAIM_VIEW("GROUP_GIFT_AID_CLAIM_VIEW"),

        /** Global - Gift aid claim. Read/write. */
        GROUP_GIFT_AID_CLAIM_CREATE("GROUP_GIFT_AID_CLAIM_CREATE"),

        /** Global - Gift Aid Settlement. Read only. */
        GROUP_GIFT_AID_SETTL_VIEW("GROUP_GIFT_AID_SETTL_VIEW"),

        /** Global - Gift Aid Settlement. Read/write. */
        GROUP_GIFT_AID_SETTL_CREATE("GROUP_GIFT_AID_SETTL_CREATE"),

        /** Global - Transitional Relief Settlement. Read only. */
        GROUP_TR_SETTL_VIEW("GROUP_TR_SETTL_VIEW"),

        /** Global - Transitional Relief Settlement. Read/write.. */
        GROUP_TR_SETTL_CREATE("GROUP_TR_SETTL_CREATE"),

        /** Global - Exception Processing. Read only. */
        GROUP_EXCEPTION_PROC_VIEW("GROUP_EXCEPTION_PROC_VIEW"),

        /** Global - Exception Processing. Read/write. */
        GROUP_EXCEPTION_PROC_CREATE("GROUP_EXCEPTION_PROC_CREATE"),

        /** Global - System Processing Report. */
        GROUP_STSTEM_PROC_REPORT("GROUP_STSTEM_PROC_REPORT"),

        /** Global - User Access. Read only. */
        GROUP_USER_ACCESS_VIEW("GROUP_USER_ACCESS_VIEW"),

        /** Global - User Access. Read/write. */
        GROUP_USER_ACCESS_CREATE("GROUP_USER_ACCESS_CREATE"),

        /** Global - Events. Read only. */
        GROUP_EVENTS_VIEW("GROUP_EVENTS_VIEW"),

        /** Global - Events. Write. */
        GROUP_EVENTS_CREATE("GROUP_EVENTS_CREATE"),

        /** Reporting - Operational Support Report. */
        GROUP_OPERATION_SUPPORT_REPORT("GROUP_OPERATION_SUPPORT_REPORT"),

        /** Reporting - MI Reporting. */
        GROUP_MI_REPORTING("GROUP_MI_REPORTING"),

        /** Reporting - Financial Reports. */
        GROUP_FINANCIAL_REPORTS("GROUP_FINANCIAL_REPORTS"),

        /** Charity - Charity Master Permission. */
        GROUP_CHARITY_MASTER("GROUP_CHARITY_MASTER"),

        /** Charity - Offline Registration. */
        GROUP_CHARITY_OFFLINE_REG("GROUP_CHARITY_OFFLINE_REG"),

        /** Charity - Waived Fees - Read only. */
        GROUP_CHARITY_WAIVEDFEE_VIEW("GROUP_CHARITY_WAIVEDFEE_VIEW"),

        /** Charity - Waived Fees - Read/write. */
        GROUP_CHARITY_WAIVEDFEE_CREATE("GROUP_CHARITY_WAIVEDFEE_CREATE"),

        /** Charity - Bank a/c details. Read only. */
        GROUP_CHARITY_BANK_ACCT_VIEW("GROUP_CHARITY_BANK_ACCT_VIEW"),

        /** Charity - Bank a/c details. Read/write. */
        GROUP_CHARITY_BANK_ACCT_CREATE("GROUP_CHARITY_BANK_ACCT_CREATE"),

        /** Charity - Portal. */
        GROUP_CHARITY_PORTAL("GROUP_CHARITY_PORTAL"),

        /** Fundraiser - Fundraiser Master Permission. */
        GROUP_FUNDRAISER_MASTER("GROUP_FUNDRAISER_MASTER"),

        /** Fundraiser - Lock/Unlock accounts. */
        GROUP_FUNDRAISER_LOCK_ACCT("GROUP_FUNDRAISER_LOCK_ACCT");

        /** Status code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param statusCode the statusCode to set.
         */
        Groups(final String statusCode) {
            this.code = statusCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }

    /** Prefix for all the the available auth groups. */
    public static final String USER_AUTH_GROUP_PREFIX = "GROUP_";

    /** Prefix for all the available roles. */
    public static final String USER_ROLE_PREFIX = "ROLE_";

    /** The list of auth group codes for charity administrator. */
    public static final List<String> CHARITY_ADMINISTRATOR_AUTH_GROUPS;

    static {
        CHARITY_ADMINISTRATOR_AUTH_GROUPS = new ArrayList<String>();
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_USER_MAINT.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_ACCOUNT_MAINT.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_EVENTS.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_REPORTING_SETUP.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_REPORTING_VIEW.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_REPORTING_CREATE.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_CHARITY_PAGE.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_ALERTS.getCode());
        CHARITY_ADMINISTRATOR_AUTH_GROUPS.add(Groups.GROUP_CHARITY_FORUM.getCode());
    }

    /**
     * The Enum CharityUserRoles.
     */
    public enum CharityUserRoles {
        
        /** The CHARIT y_ rol e_ admin. */
        CHARITY_ROLE_ADMIN("ROLE_C_AD"),
        
        /** The CHARIT y_ rol e_ us. */
        CHARITY_ROLE_US("ROLE_C_US");
        
        /** The code. */
        private final String code;
        
        /**
         * Instantiates a new charity user roles.
         * 
         * @param role the role
         */
        private CharityUserRoles(final String role) {
            this.code = role;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }               
    }
    
    /**
     * Alert type codes.
     */
    public enum AlertType {
    	
    	/** Donation with frequency and count. */
        DONATION_FREQUENCY_COUNT("D_FRQ_CNT"),
        
        /** Donation with value(amount). */
        DONATION_VALUE("D_VAL"),
        
        /** Donation with value(amount) and count. */
        DONATION_VALUE_COUNT("D_VAL_CNT"),
        
        /** Fundraiser donation with frequency and count. */
        FUNDRAISER_DONATION_FREQUENCY_COUNT("FD_FRQ_CNT"),
        
        /** Fundraiser join event. */
        FUNDRAISER_JOIN_EVENT("FD_JOIN"),
        
        /** Fundraiser donation with value(amount). */
        FUNDRAISER_DONATION_VALUE("FD_VAL"),
        
        /** Fundraiser donation with value(amount) and count. */
        FUNDRAISER_DONATION_VALUE_COUNT("FD_VAL_CNT"),
        
        /** VMG payment. */
        VMG_PAYMENT("VMG_PAY");        
        
    	/** Alert code. */
        private final String code;

        /**
         * The Constructor.
         * 
         * @param alertCode the alertCode to set.
         */
        AlertType(final String alertCode) {
            this.code = alertCode;
        }

        /**
         * Gets the code.
         * 
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }
    
}
