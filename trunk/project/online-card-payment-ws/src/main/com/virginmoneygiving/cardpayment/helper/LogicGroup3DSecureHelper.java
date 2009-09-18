package com.virginmoneygiving.cardpayment.helper;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.service.exception.ThreeDSecureException;

/**
 * Build the FORM POST name-value pairs for the Logic Group MPI plug-in
 * implementation.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class LogicGroup3DSecureHelper implements ThreeDSecureHelper {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(LogicGroup3DSecureHelper.class);

    // +DI
    /** The currency code mappings. */
    private Map<String, String> currencyCodeMappings;

    /** The digest algorithm. */
    private String digestAlgorithm;
    
    /** The salt algorithm. */
    private String saltAlgorithm;

    // load from external properties
    /** The merchant id. */
    private String merchantId;
    
    /** The shared secret. */
    private String sharedSecret;

    /** The good urls by payment type. */
    private Map<String,String> goodUrlsByPaymentType;
    
    /** The bad urls by payment type. */
    private Map<String,String> badUrlsByPaymentType;

    // -DI

    /*
     * (non-Javadoc)
     * @seecom.virginmoneygiving.cardpayment.service.ThreeDSecureFormBuilder#
     * buildFormVariables
     * (com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails,
     * com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails)
     */
    public Map<String, String> buildFormVariables(String GUID, CardDetails cardDetails, PaymentDetails paymentDetails) throws ThreeDSecureException {
        String xid;
        try {
            xid = generateXid();
        } catch (NoSuchAlgorithmException e) {
            // this should never happen
            LOGGER.error("buildFormVariables() - Map<String,String> currencyCodeMappings=" + currencyCodeMappings + ", String digestAlgorithm=" + digestAlgorithm + ", String saltAlgorithm=" + saltAlgorithm + ", String merchantId=" + merchantId + ", String sharedSecret=" + sharedSecret + ", Map<String,String> goodUrlsByPaymentType=" + goodUrlsByPaymentType + ", Map<String,String> badUrlsByPaymentType=" + badUrlsByPaymentType + ", String GUID=" + GUID + ", CardDetails cardDetails=" + cardDetails + ", PaymentDetails paymentDetails=" + paymentDetails + ", NoSuchAlgorithmException e=" + e, e);
            throw new ThreeDSecureException(e);
        } catch (IOException e) {
            LOGGER.error("buildFormVariables() - Map<String,String> currencyCodeMappings=" + currencyCodeMappings + ", String digestAlgorithm=" + digestAlgorithm + ", String saltAlgorithm=" + saltAlgorithm + ", String merchantId=" + merchantId + ", String sharedSecret=" + sharedSecret + ", Map<String,String> goodUrlsByPaymentType=" + goodUrlsByPaymentType + ", Map<String,String> badUrlsByPaymentType=" + badUrlsByPaymentType + ", String GUID=" + GUID + ", CardDetails cardDetails=" + cardDetails + ", PaymentDetails paymentDetails=" + paymentDetails +  ", IOException e=" + e, e);
            throw new ThreeDSecureException(e);
        }

        Map<String, String> res = new HashMap<String, String>();
        res.put(LogicGroupConstants.HTTP_VERSION_KEY, LogicGroupConstants.HTTP_VERSION_VALUE);

        res.put(LogicGroupConstants.CARD_TYPE_KEY, ""); // optional: left blank
        res.put(LogicGroupConstants.PAN_KEY, cardDetails.getPan());

        // uses a helper method for formatting the 2 int fields to a YYMM String
        res.put(LogicGroupConstants.EXPIRY_KEY, formatExpiry(cardDetails.getEndMonth(), cardDetails.getEndYear()));
        res.put(LogicGroupConstants.DEVICE_CATEGORY_KEY, LogicGroupConstants.DEVICE_CATEGORY_VALUE);
        res.put(LogicGroupConstants.AMOUNT_KEY, paymentDetails.getAmount().unscaledValue().toString());
        res.put(LogicGroupConstants.EXPONENT_KEY, Integer.toString(paymentDetails.getAmount().scale()));
        res.put(LogicGroupConstants.DESCRIPTION_KEY, "");

        // map currency code
        res.put(LogicGroupConstants.CURRENCY_KEY, currencyCodeMappings.get(paymentDetails.getCurrencyCode()));

        // Opal merchant id is different from SPIS merchant id
        res.put(LogicGroupConstants.MERCHANT_ID_KEY, merchantId);
        res.put(LogicGroupConstants.XID_KEY, xid);

        //TODO: move the lookup to a method that provides null safety?
        res.put(LogicGroupConstants.OK_URL_KEY,
                goodUrlsByPaymentType.get(paymentDetails.getPaymentType().value()));
        res.put(LogicGroupConstants.FAIL_URL_KEY,
                badUrlsByPaymentType.get(paymentDetails.getPaymentType().value()));

        res.put(LogicGroupConstants.MERCHANT_DATA_KEY, GUID); // our process
                                                              // GUID

        // build digest:
        String digest;
        try {
            digest = createRequestDigest(res);
        } catch (NoSuchAlgorithmException e) {
            // this should never happen
            LOGGER.error("buildFormVariables() -  String digestAlgorithm=" + digestAlgorithm + ", String saltAlgorithm=" + saltAlgorithm + ", String merchantId=" + merchantId + ", String sharedSecret=" + sharedSecret + ", Map<String,String> goodUrlsByPaymentType=" + goodUrlsByPaymentType + ", Map<String,String> badUrlsByPaymentType=" + badUrlsByPaymentType + ", String GUID=" + GUID +  ", String xid=" + xid + ", Map<String,String> res=" + res + ", NoSuchAlgorithmException e=" + e, e);
            throw new ThreeDSecureException(e);
        } catch (IOException e) {
            LOGGER.error("buildFormVariables() -  String digestAlgorithm=" + digestAlgorithm + ", String saltAlgorithm=" + saltAlgorithm + ", String merchantId=" + merchantId + ", String sharedSecret=" + sharedSecret + ", Map<String,String> goodUrlsByPaymentType=" + goodUrlsByPaymentType + ", Map<String,String> badUrlsByPaymentType=" + badUrlsByPaymentType + ", String GUID=" + GUID +  ", String xid=" + xid + ", Map<String,String> res=" + res  + ", IOException e=" + e, e);
            throw new ThreeDSecureException(e);
        }
        res.put(LogicGroupConstants.DIGEST_KEY, digest);
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Auth map contains " + res.size() + " entries.");
        }

        return res;
    }

    /** {@inheritDoc}*/
    public boolean verifyAuthenticationResponseDigest(Map<String, String> formVariables) throws ThreeDSecureException {
        String suppliedDigest = formVariables.get(LogicGroupConstants.DIGEST_KEY);

        // build the string
        StringBuilder buf = new StringBuilder();
        buf.append(formVariables.get(LogicGroupConstants.HTTP_VERSION_KEY));
        buf.append(formVariables.get(LogicGroupConstants.MERCHANT_ID_KEY));
        buf.append(formVariables.get(LogicGroupConstants.XID_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_MD_STATUS_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_MD_ERROR_MSG_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_TX_STATUS_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_IREQ_CODE_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_IREQ_DETAIL_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_VENDOR_CODE_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_ECI_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_CAVV_KEY));
        buf.append(formVariables.get(LogicGroupConstants.RES_CAVV_ALG_KEY));
        // MD POST response next
        buf.append(formVariables.get(LogicGroupConstants.MERCHANT_DATA_KEY)); 
        buf.append(sharedSecret); // Use agreed merchant secret password

        String plaintext = buf.toString();
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(plaintext);
        }

        String calculatedDigest;
        try {
            calculatedDigest = digest(plaintext);
        } catch (NoSuchAlgorithmException e) {
            // this should never happen
            LOGGER.error("verifyAuthenticationResponseDigest() -  String digestAlgorithm=" + digestAlgorithm + ", String saltAlgorithm=" + saltAlgorithm + ", String merchantId=" + merchantId + ", String sharedSecret=" + sharedSecret +  ", String suppliedDigest=" + suppliedDigest + ", StringBuilder buf=" + buf + ", String plaintext=" + plaintext +  ", NoSuchAlgorithmException e=" + e, e);
            throw new ThreeDSecureException(e);
        } catch (IOException e) {
            LOGGER.error("verifyAuthenticationResponseDigest() -  String digestAlgorithm=" + digestAlgorithm + ", String saltAlgorithm=" + saltAlgorithm + ", String merchantId=" + merchantId + ", String sharedSecret=" + sharedSecret +  ", String suppliedDigest=" + suppliedDigest + ", StringBuilder buf=" + buf + ", String plaintext=" + plaintext +  ", IOException e=" + e, e);
            throw new ThreeDSecureException(e);
        }
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Calculated: " + calculatedDigest + "\nSupplied  : " + suppliedDigest);
        }

        return calculatedDigest.equals(suppliedDigest);
    }

    /**
     * Create the digest following The Logic Group rules.
     * 
     * @param formData the form data
     * 
     * @return the string
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws Exception      */
    private String createRequestDigest(Map<String, String> formData) throws NoSuchAlgorithmException, IOException {
        StringBuilder buf = new StringBuilder();
        buf.append(formData.get(LogicGroupConstants.HTTP_VERSION_KEY));
        buf.append(formData.get(LogicGroupConstants.CARD_TYPE_KEY));
        buf.append(formData.get(LogicGroupConstants.PAN_KEY));
        buf.append(formData.get(LogicGroupConstants.EXPIRY_KEY));
        buf.append(formData.get(LogicGroupConstants.DEVICE_CATEGORY_KEY));
        buf.append(formData.get(LogicGroupConstants.AMOUNT_KEY));
        buf.append(formData.get(LogicGroupConstants.EXPONENT_KEY));
        buf.append(formData.get(LogicGroupConstants.DESCRIPTION_KEY));
        buf.append(formData.get(LogicGroupConstants.CURRENCY_KEY));
        buf.append(formData.get(LogicGroupConstants.MERCHANT_ID_KEY));
        buf.append(formData.get(LogicGroupConstants.XID_KEY));
        buf.append(formData.get(LogicGroupConstants.OK_URL_KEY));
        buf.append(formData.get(LogicGroupConstants.FAIL_URL_KEY));
        buf.append(formData.get(LogicGroupConstants.MERCHANT_DATA_KEY));
        buf.append(sharedSecret);// Do not included this in the POST request
                                 // this is the merchant secret password.

        String plaintext = buf.toString();
        /*if(LOGGER.isDebugEnabled()){
            LOGGER.warn("Next log statement contains the PAN! Dev only...");
            LOGGER.debug(plaintext); // we need this logged during development - but
                                 // never in production due to the PAN.
        }*/
        String calculatedDigest = digest(plaintext);

        return calculatedDigest;
    }

    /**
     * Digest a String based on the configured digest algorithm.
     * 
     * @param plaintext the plaintext
     * 
     * @return the string
     * 
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected String digest(String plaintext) throws NoSuchAlgorithmException, IOException {
        MessageDigest mdigest = MessageDigest.getInstance(digestAlgorithm);
        byte[] digestResult = mdigest.digest(plaintext.getBytes());
        String calculatedDigest = encode(digestResult);

        return calculatedDigest;
    }

    /**
     * Base64 encode a String.
     * 
     * @param bytes the bytes
     * 
     * @return the string
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private String encode(byte[] bytes) throws IOException {
        return new String(Base64.encodeBase64(bytes));
    }
    
    /**
     * Generate a XID value based on TLG sample code - 20 bytes base64 encoded
     * to give a 28 byte String.
     * 
     * @return the string
     * 
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected String generateXid() throws NoSuchAlgorithmException, IOException {
        SecureRandom random = SecureRandom.getInstance(saltAlgorithm);
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return encode(bytes);
    }

    /**
     * Converts month and year into YYMM format protected visibility so that it
     * can be unit tested.
     * 
     * @param month the month
     * @param year the year
     * 
     * @return the string
     */
    protected String formatExpiry(int month, int year) {
        String sMonth = Integer.toString(month);
        String sYear = Integer.toString(year);

        // TODO: assertions

        StringBuilder sb = new StringBuilder(4);
        sb.append(StringUtils.right(sYear, 2));
        sb.append(StringUtils.leftPad(sMonth, 2, "0"));
        return sb.toString();
    }

    // Dependency Injection setters
    /**
     * Sets the currency code mappings.
     * 
     * @param currencyCodeMappings the currencyCodeMappings to set
     */
    public void setCurrencyCodeMappings(Map<String, String> currencyCodeMappings) {
        this.currencyCodeMappings = currencyCodeMappings;
    }

    /**
     * Sets the digest algorithm.
     * 
     * @param digestAlgorithm the digestAlgorithm to set
     */
    public void setDigestAlgorithm(String digestAlgorithm) {
        this.digestAlgorithm = digestAlgorithm;
    }

    /**
     * Sets the salt algorithm.
     * 
     * @param saltAlgorithm the saltAlgorithm to set
     */
    public void setSaltAlgorithm(String saltAlgorithm) {
        this.saltAlgorithm = saltAlgorithm;
    }

    /**
     * Sets the merchant id.
     * 
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * Sets the shared secret.
     * 
     * @param sharedSecret the sharedSecret to set
     */
    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    /**
     * Sets the good urls by payment type.
     * 
     * @param goodUrlsByPaymentType the goodUrlsByPaymentType to set
     */
    public void setGoodUrlsByPaymentType(Map<String, String> goodUrlsByPaymentType) {
        this.goodUrlsByPaymentType = goodUrlsByPaymentType;
    }

    /**
     * Sets the bad urls by payment type.
     * 
     * @param badUrlsByPaymentType the badUrlsByPaymentType to set
     */
    public void setBadUrlsByPaymentType(Map<String, String> badUrlsByPaymentType) {
        this.badUrlsByPaymentType = badUrlsByPaymentType;
    }
}
