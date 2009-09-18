package com.virginmoneygiving.cardpayment.helper;

/**
 * Logic Group constants.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public final class LogicGroupConstants {

    /**
     * Hidden default constructor.
     */
    private LogicGroupConstants() {
        // do nothing
    }

    // constant keys - general
    /** The Constant HTTP_VERSION_KEY. */
    public static final String HTTP_VERSION_KEY = "version";
    
    /** The Constant MERCHANT_ID_KEY. */
    public static final String MERCHANT_ID_KEY = "merchantID";
    
    /** The Constant XID_KEY. */
    public static final String XID_KEY = "xid";
    
    /** The Constant MERCHANT_DATA_KEY. */
    public static final String MERCHANT_DATA_KEY = "MD";
    
    /** The Constant DIGEST_KEY. */
    public static final String DIGEST_KEY = "digest";

    // constant keys - request
    /** The Constant CARD_TYPE_KEY. */
    protected static final String CARD_TYPE_KEY = "cardType";
    
    /** The Constant PAN_KEY. */
    protected static final String PAN_KEY = "pan";
    
    /** The Constant EXPIRY_KEY. */
    protected static final String EXPIRY_KEY = "expiry";
    
    /** The Constant DEVICE_CATEGORY_KEY. */
    protected static final String DEVICE_CATEGORY_KEY = "deviceCategory";
    
    /** The Constant AMOUNT_KEY. */
    protected static final String AMOUNT_KEY = "purchAmount";
    
    /** The Constant EXPONENT_KEY. */
    protected static final String EXPONENT_KEY = "exponent";
    
    /** The Constant DESCRIPTION_KEY. */
    protected static final String DESCRIPTION_KEY = "description";
    
    /** The Constant CURRENCY_KEY. */
    protected static final String CURRENCY_KEY = "currency";
    
    /** The Constant OK_URL_KEY. */
    protected static final String OK_URL_KEY = "okUrl";
    
    /** The Constant FAIL_URL_KEY. */
    protected static final String FAIL_URL_KEY = "failUrl";

    // constant keys - response
    /** The Constant RES_MD_STATUS_KEY. */
    public static final String RES_MD_STATUS_KEY = "mdStatus";
    
    /** The Constant RES_MD_ERROR_MSG_KEY. */
    public static final String RES_MD_ERROR_MSG_KEY = "mdErrorMsg";
    
    /** The Constant RES_TX_STATUS_KEY. */
    public static final String RES_TX_STATUS_KEY = "txstatus";
    
    /** The Constant RES_IREQ_CODE_KEY. */
    public static final String RES_IREQ_CODE_KEY = "iReqCode";
    
    /** The Constant RES_IREQ_DETAIL_KEY. */
    public static final String RES_IREQ_DETAIL_KEY = "iReqDetail";
    
    /** The Constant RES_VENDOR_CODE_KEY. */
    public static final String RES_VENDOR_CODE_KEY = "vendorCode";
    
    /** The Constant RES_ECI_KEY. */
    public static final String RES_ECI_KEY = "eci";
    
    /** The Constant RES_CAVV_KEY. */
    public static final String RES_CAVV_KEY = "cavv";
    
    /** The Constant RES_CAVV_ALG_KEY. */
    public static final String RES_CAVV_ALG_KEY = "cavvAlgorithm";
    
    /** The Constant RES_SCHEME_ID_KEY. */
    public static final String RES_SCHEME_ID_KEY = "sID";
    
    /** The Constant RES_OPAL_ERROR_CODE_KEY. */
    public static final String RES_OPAL_ERROR_CODE_KEY = "opalErrorCode";

    // constant values
    /** TLG Payer Auth version. */
    public static final String HTTP_VERSION_VALUE = "2.0";
    
    /** TLG Payer Auth device category. */
    public static final String DEVICE_CATEGORY_VALUE = "0";

    /** TLG Web Service success response code. */
    public static final String WS_SUCCESS_CODE = "0";

    // String related
    /** Record separator. */
    public static final String REC_SEPARATOR = "\n";
    
    /** Field separator. */
    public static final String FIELD_SEPARATOR = ": ";

    // Transaction types
    /** The Constant TRANSACTION_COMPLETE. */
    public static final String TRANSACTION_COMPLETE = "Complete";
    
    /** The Constant TRANSACTION_CANCEL. */
    public static final String TRANSACTION_CANCEL = "Cancel";
    
    /** The Constant TRANSCTION_AUTHORISE_WITH_TOKEN. */
    public static final String TRANSCTION_AUTHORISE_WITH_TOKEN = "AuthoriseToken";
    
    /** The Constant TRANSACTION_AUTHORISE_WITH_3DSECURE. */
    public static final String TRANSACTION_AUTHORISE_WITH_3DSECURE = "AuthoriseWith3DSecure";
    
    /** The Constant TRANSACTION_AUTHORISE_WITH_CV2AVS. */
    public static final String TRANSACTION_AUTHORISE_WITH_CV2AVS = "AuthoriseWithCv2Avs";
    
    /** The Constant TRANSACTION_VALIDATE. */
    public static final String TRANSACTION_VALIDATE = "Validate";
}
