package com.virginmoneygiving.cardpayment.business;

/**
 * Representation of a credit card.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class CardDetails {

    /** Primary Account Number (aka card number). */
    private String pan;

    /** Masked PAN. */
    private String maskedPan;

    /** Month the card was valid from - may be null. */
    private Integer startMonth;

    /** Year the card was valid from - may be null. */
    private Integer startYear;

    /** Month the card expires - cannot be null. */
    private int endMonth;

    /** Year the card expires - cannot be null. */
    private int endYear;

    /** Card issue number - may be null. */
    private Integer issueNumber;

    /** The PCI-compliant card token - may be null for a new card. */
    private String token;

    /** AVS/CV2 data. */
    private CardSecurityInformation securityInfo;

    /**
     * Gets the value of the pan property.
     * 
     * @return possible object is {@link String }
     */
    public String getPan() {
        return pan;
    }

    /**
     * Sets the value of the pan property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setPan(String value) {
        this.pan = value;
    }

    /**
     * Gets the value of the maskedPan property.
     * 
     * @return possible object is {@link String }
     */
    public String getMaskedPan() {
        return maskedPan;
    }

    /**
     * Sets the value of the maskedPan property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setMaskedPan(String value) {
        this.maskedPan = value;
    }

    /**
     * Gets the value of the startMonth property.
     * 
     * @return start month
     */
    public Integer getStartMonth() {
        return startMonth;
    }

    /**
     * Sets the value of the startMonth property.
     * 
     * @param value the value
     */
    public void setStartMonth(Integer value) {
        this.startMonth = value;
    }

    /**
     * Gets the value of the startYear property.
     * 
     * @return start year
     */
    public Integer getStartYear() {
        return startYear;
    }

    /**
     * Sets the value of the startYear property.
     * 
     * @param value the value
     */
    public void setStartYear(Integer value) {
        this.startYear = value;
    }

    /**
     * Gets the value of the endMonth property.
     * 
     * @return end month
     */
    public int getEndMonth() {
        return endMonth;
    }

    /**
     * Sets the value of the endMonth property.
     * 
     * @param value the value
     */
    public void setEndMonth(int value) {
        this.endMonth = value;
    }

    /**
     * Gets the value of the endYear property.
     * 
     * @return end year
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     * Sets the value of the endYear property.
     * 
     * @param value the value
     */
    public void setEndYear(int value) {
        this.endYear = value;
    }

    /**
     * Gets the value of the issueNumber property.
     * 
     * @return possible object is {@link Integer }
     */
    public Integer getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the value of the issueNumber property.
     * 
     * @param value allowed object is {@link Integer }
     */
    public void setIssueNumber(Integer value) {
        this.issueNumber = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return possible object is {@link String }
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the securityInfo property.
     * 
     * @return possible object is {@link CardSecurityInformation }
     */
    public CardSecurityInformation getSecurityInfo() {
        return securityInfo;
    }

    /**
     * Sets the value of the securityInfo property.
     * 
     * @param value allowed object is {@link CardSecurityInformation }
     */
    public void setSecurityInfo(CardSecurityInformation value) {
        this.securityInfo = value;
    }

}
