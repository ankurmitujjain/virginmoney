
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraisingReasonFundSplit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraisingReasonFundSplit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityFundPercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityRegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityLogoUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraisingReasonFundSplit", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "charityId",
    "fundraiserActivityId",
    "charityName",
    "charityFundPercentage",
    "charityRegistrationNumber",
    "charityLogoUrl"
})
public class ServiceFundraisingReasonFundSplit {

    @XmlElement(required = true)
    protected String charityId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserActivityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String charityFundPercentage;
    @XmlElement(required = true)
    protected String charityRegistrationNumber;
    @XmlElement(required = true)
    protected String charityLogoUrl;

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityId(String value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the fundraiserActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the value of the fundraiserActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserActivityId(Long value) {
        this.fundraiserActivityId = value;
    }

    /**
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
    }

    /**
     * Gets the value of the charityFundPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityFundPercentage() {
        return charityFundPercentage;
    }

    /**
     * Sets the value of the charityFundPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityFundPercentage(String value) {
        this.charityFundPercentage = value;
    }

    /**
     * Gets the value of the charityRegistrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityRegistrationNumber() {
        return charityRegistrationNumber;
    }

    /**
     * Sets the value of the charityRegistrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityRegistrationNumber(String value) {
        this.charityRegistrationNumber = value;
    }

    /**
     * Gets the value of the charityLogoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityLogoUrl() {
        return charityLogoUrl;
    }

    /**
     * Sets the value of the charityLogoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityLogoUrl(String value) {
        this.charityLogoUrl = value;
    }

}
