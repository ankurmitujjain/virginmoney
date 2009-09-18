
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharitySplitDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharitySplitDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityRegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityFundPercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharitySplitDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "charityId",
    "charityName",
    "charityRegistrationNumber",
    "charityUrl",
    "charityFundPercentage",
    "charityEmailAddress"
})
public class ServiceCharitySplitDetails {

    @XmlElement(required = true)
    protected String charityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String charityRegistrationNumber;
    @XmlElement(required = true)
    protected String charityUrl;
    @XmlElement(required = true)
    protected String charityFundPercentage;
    @XmlElement(required = true)
    protected String charityEmailAddress;

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
     * Gets the value of the charityUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityUrl() {
        return charityUrl;
    }

    /**
     * Sets the value of the charityUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityUrl(String value) {
        this.charityUrl = value;
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
     * Gets the value of the charityEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityEmailAddress() {
        return charityEmailAddress;
    }

    /**
     * Sets the value of the charityEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityEmailAddress(String value) {
        this.charityEmailAddress = value;
    }

}
