
package com.virginmoneygiving.cardpayment.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCardDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCardDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PAN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maskedPAN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="issueNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="securityInfo" type="{http://www.virginmoneygiving.com/type/online-card-payment/card-payment}ServiceCardSecurityInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCardDetails", namespace = "http://www.virginmoneygiving.com/type/online-card-payment/card-payment", propOrder = {
    "pan",
    "maskedPAN",
    "startMonth",
    "startYear",
    "endMonth",
    "endYear",
    "issueNumber",
    "token",
    "securityInfo"
})
public class ServiceCardDetails {

    @XmlElement(name = "PAN", required = true, nillable = true)
    protected String pan;
    @XmlElement(required = true, nillable = true)
    protected String maskedPAN;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer startMonth;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer startYear;
    protected int endMonth;
    protected int endYear;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer issueNumber;
    @XmlElement(required = true, nillable = true)
    protected String token;
    @XmlElement(required = true, nillable = true)
    protected ServiceCardSecurityInformation securityInfo;

    /**
     * Gets the value of the pan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAN() {
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
    public void setPAN(String value) {
        this.pan = value;
    }

    /**
     * Gets the value of the maskedPAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskedPAN() {
        return maskedPAN;
    }

    /**
     * Sets the value of the maskedPAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskedPAN(String value) {
        this.maskedPAN = value;
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
     *     {@link ServiceCardSecurityInformation }
     *     
     */
    public ServiceCardSecurityInformation getSecurityInfo() {
        return securityInfo;
    }

    /**
     * Sets the value of the securityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCardSecurityInformation }
     *     
     */
    public void setSecurityInfo(ServiceCardSecurityInformation value) {
        this.securityInfo = value;
    }

}
