package com.virginmoneygiving.givingbatch.common;

import com.virginmoneygiving.payment.constants.MasterDataCodeConstants;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;

/**
 * This class contains Constants used by Giving batch. Class has many constants
 * that are duplicates of those in
 * {@link com.virginmoneygiving.payment.constants.MasterDataCodeConstants}.
 * Always use those rather than the values in this class
 * 
 * @author Tarun Adiwal
 */
public final class Constant {

    /**
     * Default constructor.
     */
    private Constant() {

    }

    /** PAYMENT STATUS. */

    /**
     * WEB DONATION Payment status - Payment Initiated.
     *
     * @deprecated use {@link MasterDataCodeConstants.DON_PAYMENT_INITIATED}
     *             instead
     */
    @Deprecated
    public static final String DON_PAYMENT_INITIATED = "WEBPAYINIT";

    /** Event Registration Fee- Payment Initiated. */
    @Deprecated
    public static final String ERF_PAYMENT_INITIATED = "ERFPAYINIT";

    /** Payment status - Payment Processed. */
    public static final String PAYMENT_STATUS_PAYMENT_PROCESSED = "PAYPRO";

    /**
     * WEB DONATION Payment status - Payment Collected.
     *
     * @deprecated use {@link MasterDataCodeConstants.DON_PAYMENT_COLLECTED}
     *             instead
     */
    @Deprecated
    public static final String DON_PAYMENT_COLLECTED = "WEBPAYCOLL";

    /**
     * Event Registration Fee - Payment status - Payment Collected.
     *
     * @deprecated use {@link MasterDataCodeConstants.ERF_PAYMENT_COLLECTED}
     *             instead
     */
    @Deprecated
    public static final String ERF_PAYMENT_COLLECTED = "ERFPAYCOLL";

    /** Payment status - Payment Due. */
    public static final String PAYMENT_STATUS_PAYMENT_DUE = "PAYDUE";

    /** Handling charge status - Charge Paid. */
    public static final String HANDLING_CHARGE_STATUS_CHARGE_PAID =
		"TRNFEEPAID";

    /** Handling charge status - Charge Due. */
    public static final String HANDLING_CHARGE_STATUS_CHARGE_DUE = "HCD";

    /** Event Registration Fee - Payment Initiated. */
    public static final String ERF_PAYMENT_INITIATED_PROCESSED =
            "ERFPAYINIT_PROCESSED";

    /** Event Registration Fee - Payment Initiated. */
    public static final String ERF_PAYMENT_FAILED_PROCESSED =
            "ERFPAYFAIL_PROCESSED";

    /** Event Registration Fee - Payment Initiated. */
    public static final String REGULARDON_PAYMENT_INITIATED = "REGPAYINIT";

    /** Event Registration Fee - Payment Initiated. */
    public static final String REGULARDON_PAYMENT_FAILED_PROCESSED =
            "REGPAYFAIL_PROCESSED";

    /** WEB DONATION Payment status - Payment Initiated. */
    public static final String DON_PAYMENT_INITIATED_PROCESSED =
            "WEBPAYINIT_PROCESSED";

    /** WEB DONATION Payment status - Payment Collected. */
    public static final String DON_PAYMENT_COLLECTED_PROCESSED =
            "WEBPAYCOLL_PROCESSED";

    /** WEB DONATION Payment status - Payment Failed . */
    public static final String DON_PAYMENT_FAILED_PROCESSED =
            "WEBPAYFAIL_PROCESSED";

    /** Event Registration Payment Initiated Object list to put. */
    public static final String EVENT_REGISTRATION_PAYMENT_INITIATED_OBJECT_LIST =
            "EventRegistrationPaymentInitiatedObjectList";

    /** Event Registration Payment Initiated Object list to put. */
    public static final String EVENT_REGISTRATION_PAYMENT_FAILED_OBJECT_LIST =
            "EventRegistrationPaymentFailedObjectList";

    /** Regular Donation Payment Initiated Object list to put. */
    public static final String REGULAR_DONATION_PAYMENT_INITIATED_OBJECT_LIST =
            "RegularDonationPaymentInitiatedObjectList";

    /** Regular Donation Payment Initiated Object list to put. */
    public static final String REGULAR_DONATION_PAYMENT_FAILED_OBJECT_LIST =
            "RegularDonationPaymentFailedObjectList";

    /** Web Donation Payment Failed Object list to put. */
    public static final String WEB_DONATION_PAYMENT_FAILED_OBJECT_LIST =
            "WebDonationPaymentFailedObjectList";

    /** Payment status - Payment Failed. */
    public static final String PAYMENT_STATUS_PAYMENT_FAILED = "PAYFAIL";

    /** VAT STATUS'S. */

    /** VAT status - VAT Paid. */
    public static final String VAT_STATUS_VAT_PAID = "VATDONPAID";

    /** VAT status - VAT Due. */
    public static final String VAT_STATUS_VAT_DUE = "VATD";

    /** GIFT AID STATUS'S. */

    /** Gift aid status - Gift Aid Due. */
    public static final String GIFT_AID_STATUS_GIFT_AID_DUE = "GAD";

    /** Gift aid status - Gift Aid Claimed. */
    public static final String GIFT_AID_STATUS_GIFT_AID_CLAIMED = "GFTADCLAIM";

    /** Gift aid status - Gift Aid Claimed. */
    public static final String GIFT_AID_STATUS_GIFT_AID_CLAIMED_PENDING =
            "GFTADCLAIM_PENDING";

    /** Gift aid status - Gift Aid Claimed. */
    public static final String TRANSITIONAL_RELIEF_STATUS_PENDING =
            "TRANRLFCLM_PENDING";

    /** Gift aid status - Gift Aid Received. */
    public static final String GIFT_AID_STATUS_GIFT_AID_RECEIVED = "GFTAIDRECD";
    
    /** Gift aid status - Gift Aid Claimed. */
    public static final String GIFT_AID_STATUS_GIFT_AID_RECEIVED_PENDING = "GFTAIDRECD_PENDING";

    /** The Constant GIFT_AID_STATUS_GIFT_AID_RECEIVED_PROCESSED. */
    public static final String GIFT_AID_STATUS_GIFT_AID_RECEIVED_PROCESSED =
            "GFTAIDRECD_PROCESSED";

    /** The Constant GIFT_AID_STATUS_GIFT_AID_CLAIMED_PROCESSED. */
    public static final String GIFT_AID_STATUS_GIFT_AID_CLAIMED_PROCESSED =
            "GFTADCLAIM_PROCESSED";

    /** The Constant EVENT_STATUS_EVENT_PUBLISHED. */
    public static final String EVENT_STATUS_EVENT_PUBLISHED =
            "EVENT_PUBLISHED";

    /** The Constant EVENT_STATUS_EVENT_UNPUBLISHED. */
    public static final String EVENT_STATUS_EVENT_UNPUBLISHED =
            "EVENT_UNPUBLISHED";

    /** Transitional relief status - Transitional Relief Claimed. */
    public static final String TRANSITIONAL_RELIEF_STATUS_TRANSITIONAL_RELIEF_CLAIMED =
            "TRANRLFCLM";
    
    /** Gift aid status - Gift Aid Claimed. */
    public static final String TRANSITIONAL_RELIEF_STATUS_RECEIVED_PENDING = "TRNRLFRECD_PENDING";


    /** CARD CHARGE STATUS'S. */

    /** Card charge status - Card Charge Paid. */
    public static final String CARD_CHARGE_STATUS_CARD_CHARGE_PAID =
            "TRCHRGPAID";

    /** VAT RECORD TYPE'S. */

    /** VAT record type - Handling Charge. */
    public static final String VAT_RECORD_TYPE_HANDLING_CHARGE = "VATHC";

    /** VAT record type - Card Charge. */
    public static final String VAT_RECORD_TYPE_CARD_CHARGE = "VATCC";

    /** Reference type. */
    public static final String INVOICE = "INVOICE";

    /** Constants to store GiftAidClaimEnd and GiftAidClaimFooter instances in context. */
    public static final String GIFT_AID_CLAIM_END = "GIFT_AID_CLAIM_END";

    /** Gift Aid Calim Footer. */
    public static final String GIFT_AID_CLAIM_FOOTER = "GIFT_AID_CLAIM_FOOTER";

    /** Constants to format date and time. */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Dtae time format Constant. */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /** Payment Initiated Object list to put. */
    public static final String PAYMENT_INITIATED_OBJECT_LIST =
            "PaymentInitiatedObjectList";

    /** List Of Payment Ids *. */
    public static final String PAYMENT_ID_LIST = "PaymentIdList";

    /** Payment Initiated Object list to put. */
    public static final String PAYMENT_INITIATED_SUMMARY_MAP =
            "PaymentInitiatedSummaryMap";

    /** Donation Payment Colleted Object list to put. */
    public static final String PAYMENT_COLLECTED_OBJECT_LIST =
            "PaymentCollectedObjectList";

    /** Regular Donation Payment Colleted Object list to put. */
    public static final String REGULAR_DONATION_PAYMENT_COLLECTED_OBJECT_LIST =
            "RegularDonationPaymentCollectedObjectList";

    /** GiftAid Colleted Object list to put. */
    public static final String GIFTAID_RECEIVED_OBJECT_LIST =
            "GiftAidReceivedObjectList";

    /** GiftAid Claimed Object list to put. */
    public static final String GIFTAID_CLAIMED_OBJECT_LIST =
            "GiftAidClaimedObjectList";

    /** TransactionChargePaid Object list to put. */
    public static final String TRANSACTION_CHARGE_PAID_OBJECT_LIST =
            "TransactionChargePaidObjectList";

    /** TransactionChargePaid Object list to put. */
    public static final String EVENT_FEE_TRANSACTION_CHARGE_PAID_OBJECT_LIST =
            "EventFeeTransactionChargePaidObjectList";

    /** TransactionChargePaid Object list to put. */
    public static final String WEB_REGULAR_DONATION_TRANSACTION_CHARGE_PAID_OBJECT_LIST =
            "WebRegularDonationTransactionChargePaidObjectList";

    /** TransactionFeePaid Object list to put. */
    public static final String TRANSACTION_FEE_PAID_OBJECT_LIST =
            "TransactionFeePaidObjectList";

    /** TransactionFeePaid Object list to put. */
    public static final String REGULAR_DONATION_TRANSACTION_FEE_PAID_OBJECT_LIST =
            "RegularDonationTransactionFeePaidObjectList";

    /** Event Registration Fee TransactionFeePaid Object list to put. */
    public static final String EVENT_REGISTRATION_TRANSACTION_FEE_PAID_OBJECT_LIST =
            "EventRegistrationTransactionFeePaidObjectList";

    /** Register Object list to put. */
    public static final String REGISTER_CHARITY_OBJECT_LIST =
            "RegisterCharityObjectList";

    /** Regular donation object list. */
    public static final String REGULAR_DONATION_OBJECT_LIST =
            "RegularDonationObjectList";
    
    /** SaveRegularDonation objectList. */
    public static final String SAVE_REGULAR_DONATION_LIST = "SaveRegularDonation";
    
    /** Event Fee Payment Colleted Object list to put. */
    public static final String EVENT_FEE_PAYMENT_COLLECTED_OBJECT_LIST =
            "EventFeePaymentCollectedObjectList";

    /** Admin constant used for Administrator Address. */
    public static final String ADMIN = "Admin";

    /** Admin constant used for Register Address. */
    public static final String REGISTER = "Register";

    /** Admin constant used for Register Address. */
    public static final String CUSTOMER = "HMRC";

    /** Payment type code DON. */
    public static final String DONATION = "DON";

    /** CreateRegistrationFee Objects. */
    public static final String CREATE_REGISTRATION_FEE_OBJECT_LIST =
            "createRegistrationFeeObjectList";

    /** transitionalReliefClaimed Objects. */
    public static final String TRANSITIONAL_RELIEF_CLAIMED_OBJECT_LIST =
            "transitionalReliefClaimedObjectList";

    /** EventRegistrationpayment Objects. */
    public static final String EVENT_REGISTRATION_PAYMENT_OBJECT_LIST =
            "EventRegistrationpaymentObjectList";

    /** transitionalReliefReceived Objects. */
    public static final String TRANSITIONAL_RELIEF_RECEIVED_OBJECT_LIST =
            "transitionalReliefReceivedObjectList";

    /** WebDonationPayment Objects. */
    public static final String WEB_DONATION_PAYMENT_OBJECT_LIST =
            "WebDonationPaymentObjectList";

    /** Event Registration Fee - Payment Collected Processed. */
    public static final String ERF_PAYMENT_COLLECTED_PROCESSED =
            "ERFPAYCOLL_PROCESSED";

    /** Regular Donation Transaction Charge Collected Collected Processed. */
    public static final String DON_PAYMENT_TRANSACTION_CHARGE_COLLECTED_PROCESSED =
            "TRCHRGPAID_PROCESSED";

    /** Transaction Fee Paid Processed. */
    public static final String TRANSACTION_FEE_PAID_PROCESSED =
            "TRNFEEPAID_PROCESSED";

    /** Transaction Charge Paid Processed. */
    public static final String TRANSACTION_CHARGE_PAID_PROCESSED =
            "TRCHRGPAID_PROCESSED";

    /** Event Registration Fee Transaction Charge Collected Processed. */
    public static final String ERF_PAYMENT_TRANSACTION_CHARGE_COLLECTED_PROCESSED =
            "TRCHRGPAID_PROCESSED";

    /** Event Registration Fee Transaction Fee Collected Processed. */
    public static final String ERF_PAYMENT_TRANSACTION_FEE_PAID_PROCESSED =
            "TRNFEEPAID_PROCESSED";
    
    /** Waive Registration Fee Transaction Fee Collected Processed. */
    public static final String WAIVE_REG_FEE_OBJECT_LIST =
            "WaiveRegistrationFeeObjectList";

    /** Waive Registration Fee Transaction Fee Collected Processed. */
    public static final String MAINTAIN_CHARITY_OBJECT_LIST =
            "MaintainCharityObjectList";

    /** Summmary map constant. * */
    public static final String SUMMARY_MAP = "SummaryMap";

    /** Batch Related Constants. */

    public static final String BATCH_STATUS_SUCCESS = "SUCCESS";

    /** The Constant BATCH_STATUS_FAILED. */
    public static final String BATCH_STATUS_FAILED = "FAILED";

    /** Register charity - IRC. * */
    public static final String REGISTER_CHARITY = "REGISTER-CHARITY";

    /** Register charity - LIV. * */
    public static final String REGISTER_CHARITY_LIV = "REGISTER-CHARITY-LIV";
    
    /** Maintain charity - LIV. * */
    public static final String MAINTAIN_CHARITY = "MAINTAIN-CHARITY-LIV";

    /** Payment Initiated. * */
    public static final String PAYMENT_INITIATED = "PAYMENT_INITIATED";

    /** Payment collected. * */
    public static final String PAYMENT_COLLECTED = "PAYMENT_COLLECTED";

    /** Payment collected. * */
    public static final String COLLECT_REGULAR_DONATION = "COLLECT_REGULAR_DONATIONS";    

    /** Payment collected. * */
    public static final String REGULAR_DONATION_PAYMENT_COLLECTED =
            "REGULARDON_WEBPAYCOLL";

    /** Payment collected. * */
    public static final String EVENT_REGISTRATION_PAYMENT_COLLECTED =
            "EVTREGNFEE_ERFPAYCOLL";

    /** Transaction Charge paid. * */
    public static final String TRANSACTION_CHARGE_PAID =
            "TRANSACTION_CHARGE_PAID";

    /** Regular Donation Transaction Charge paid. * */
    public static final String REGULAR_DONATION_TRANSACTION_CHARGE_PAID =
            "REGULAR_DONATION_TRANSACTION_CHARGE_PAID";

    /** Event Registration Transaction Charge paid. * */
    public static final String EVENT_REGISTRATION_TRANSACTION_CHARGE_PAID =
            "EVENT_REGISTRATION_TRANSACTION_CHARGE_PAID";

    /** Transaction Fee paid. * */
    public static final String TRANSACTION_FEE_PAID = "TRANSACTION_FEE_PAID";

    /** Regular Donation Fee paid. * */
    public static final String REGULAR_DONATION_TRANSACTION_FEE_PAID =
            "REGULAR_DONATION_TRANSACTION_FEE_PAID";

    /** Event Registration Transaction Fee paid . * */
    public static final String EVENT_REGISTRATION_TRANSACTION_FEE_PAID =
            "EVENT_REGISTRATION_TRANSACTION_FEE_PAID";

    /** Gift Aid Claimed . * */
    public static final String GIFT_AID_CLAIMED = "GIFT_AID_CLAIMED";

    /** Gift Aid Received . * */
    public static final String GIFT_AID_RECEIVED = "GIFT_AID_RECEIVED";

    /** Transitional Relief Claimed . * */
    public static final String TRANSITIONAL_RELIEF_CLAIMED =
            "TRANSITIONAL_RELIEF_CLAIMED";

    /** Transitional Relief Received . * */
    public static final String TRANSITIONAL_RELIEF_RECEIVED =
            "TRANSITIONAL_RELIEF_RECEIVED";

    /** Entity type - Charity. * */
    public static final String ENTITY_CHARITY = "Charity";

    /** Entity type - Payment. * */
    public static final String ENTITY_PAYMENT = "Payment";

    /** Entity type - Vat. * */
    public static final String ENTITY_VAT = "Vat";

    /** Entity type - CardCharge. * */
    public static final String ENTITY_CARD_CHARGE = "CardCharge";

    /** Entity type - Handling Charge. * */
    public static final String ENTITY_HANDLING_CHARGE = "HandlingCh";

    /** Entity type - Payment. * */
    public static final String ENTITY_GIFTAID = "GiftAid";

    /** Entity type - Transitional Relief. * */
    public static final String ENTITY_TRANSITIONAL_RELIEF = "TrnRlf";

    /** Status - Pending. * */
    public static final String PENDING = "Pending";

    /** Batch Number. * */
    public static final String BATCH_NUMBER = "batchNumber";

    /** Batch Object. * */
    public static final String BATCH_OBJECT = "batchObject";

    /** MessageHeader Object. * */
    public static final String MESSAGE_HEADER_OBJECT = "messageHeaderObject";

    /** PutCharityLive job summary data name. */
    public static final String PUT_CHARITY_LIVE_SUMMARY =
            "putCharityLiveSummary";
    
    /** Constant Y. */
    public static final String YES = "Y";
    
    /** Constant N. */
    public static final String NO = "N";
    
    /** Independent batch . */
    public static final String INDEPENDENT_BATCH = "INDEPENDENT";
    
    /** Status used for Claimed Period. */
    public static final String CURRENT = "current";
    
    /** The Constant PREVIOUS. */
    public static final String PREVIOUS = "previous";
    
    /** Status Failed. */
    public static final String FAILED = "FAILED";
    
    /** The Constant GIFT_AID_CLAIM_RECORD_OBJECT_LIST. */
    public static final String GIFT_AID_CLAIM_RECORD_OBJECT_LIST = "GiftAidClaimRecordObjectList";
    
    /** Address type : Admin. */
    public static final String ADDRESS_TYPE_ADMIN = "Admin";
    
    /** Address type : Register. */
    public static final String ADDRESS_TYPE_REGISTER = "Register";

    /** The Constant TRUSTEE_DETAILS. */
    public static final String TRUSTEE_DETAILS = "TRUSTEE-DETAILS";

    /*
     * HunarC: Added the following constants to define the batch Status codes.
     */

    /** The Constant BATCH_STATUS_PROCESSING. */
    public static final String BATCH_STATUS_PROCESSING = "BATCH_STATUS_PROCESSING";
    
    /** The Constant BATCH_STATUS_VALIDATING. */
    public static final String BATCH_STATUS_VALIDATING = "BATCH_STATUS_VALIDATING";
    
    /** The Constant BATCH_STATUS_WAITING. */
    public static final String BATCH_STATUS_WAITING    = "BATCH_STATUS_WAITING";
    
    /** The Constant BATCH_STATUS_SUCCESSFUL. */
    public static final String BATCH_STATUS_SUCCESSFUL = "BATCH_STATUS_SUCCESSFUL";
    
    /** The Constant BATCH_STATUS_FAIL. */
    public static final String BATCH_STATUS_FAIL       = "BATCH_STATUS_FAIL";
    
    /** The Constant BATCH_STATUS_WARNING. */
    public static final String BATCH_STATUS_WARNING    = "BATCH_STATUS_WARNING";
    
    /** The Constant BATCH_STATUS_REJECTED. */
    public static final String BATCH_STATUS_REJECTED   = "BATCH_STATUS_REJECTED";
    
    /** The Constant BATCH_STATUS_ERROR. */
    public static final String BATCH_STATUS_ERROR      = "BATCH_STATUS_ERROR";

    /** The Constant BATCH_PARAMETER_CUTOFF_DATE. */
    public static final String BATCH_PARAMETER_CUTOFF_DATE = "BATCH_CUTOFF_DATETIME";

    /** The Constant BATCH_PARAMETER_GLIS_PROCESSING. */
    public static final String BATCH_PARAMETER_GLIS_PROCESSING = "GLIS_PROCESS_INITIATED";

    /** The Constant BATCH_PARAMETER_EXECUTE_CHAIN. */
    public static final String BATCH_PARAMETER_EXECUTE_CHAIN = "execute_batch_chain";
    /** Date/Time formatter. */
    public static final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss.SSS");

    /** The Constant BATCH_STARTUP_JOB_NAME. */
    public static final String BATCH_STARTUP_JOB_NAME = "batchStartupJob";
    /** The Constant BATCH_FINISHED_JOB_NAME. */
    public static final String BATCH_FINISHED_JOB_NAME = "batchFinishedJob";
}
