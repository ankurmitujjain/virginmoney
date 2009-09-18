
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GivingServiceCardDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GivingServiceCardDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maskedPan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="issueNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="holderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceCardTypeEnum"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="securityInfo" type="{http://www.virginmoneygiving.com/type/giving-management/card-payment/}GivingServiceCardSecurityInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GivingServiceCardDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/card-payment/", propOrder = {
    "pan",
    "maskedPan",
    "startMonth",
    "startYear",
    "endMonth",
    "endYear",
    "issueNumber",
    "holderName",
    "type",
    "token",
    "securityInfo"
})
public class GivingServiceCardDetails {

    @XmlElement(required = true, nillable = true)
    protected String pan;
    @XmlElement(required = true, nillable = true)
    protected String maskedPan;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer startMonth;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer startYear;
    protected int endMonth;
    protected int endYear;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer issueNumber;
    @XmlElement(required = true, nillable = true)
    protected String holderName;
    @XmlElement(required = true)
    protected GivingServiceCardTypeEnum type;
    @XmlElement(required = true, nillable = true)
    protected String token;
    @XmlElement(required = true, nillable = true)
    protected GivingServiceCardSecurityInformation securityInfo;

    /**
     * Gets the value of the pan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPan() {
        return pan;
    }

    /**
     * Sets the value of the pan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPan(String value) {
        this.pan = value;
    }

    /**
     * Gets the value of the maskedPan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskedPan() {
        return maskedPan;
    }

    /**
     * Sets the value of the maskedPan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskedPan(String value) {
        this.maskedPan = value;
    }

    /**
     * Gets the value of the startMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartMonth() {
        return startMonth;
    }

    /**
     * Sets the value of the startMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartMonth(Integer value) {
        this.startMonth = value;
    }

    /**
     * Gets the value of the startYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartYear() {
        return startYear;
    }

    /**
     * Sets the value of the startYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartYear(Integer value) {
        this.startYear = value;
    }

    /**
     * Gets the value of the endMonth property.
     * 
     */
    public int getEndMonth() {
        return endMonth;
    }

    /**
     * Sets the value of the endMonth property.
     * 
     */
    public void setEndMonth(int value) {
        this.endMonth = value;
    }

    /**
     * Gets the value of the endYear property.
     * 
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     * Sets the value of the endYear property.
     * 
     */
    public void setEndYear(int value) {
        this.endYear = value;
    }

    /**
     * Gets the value of the issueNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the value of the issueNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIssueNumber(Integer value) {
        this.issueNumber = value;
    }

    /**
     * Gets the value of the holderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Sets the value of the holderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHolderName(String value) {
        this.holderName = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link GivingServiceCardTypeEnum }
     *     
     */
    public GivingServiceCardTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivingServiceCardTypeEnum }
     *     
     */
    public void setType(GivingServiceCardTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the securityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GivingServiceCardSecurityInformation }
     *     
     */
    public GivingServiceCardSecurityInformation getSecurityInfo() {
        return securityInfo;
    }

    /**
     * Sets the value of the securityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GivingServiceCardSecurityInformation }
     *     
     */
    public void setSecurityInfo(GivingServiceCardSecurityInformation value) {
        this.securityInfo = value;
    }

}
