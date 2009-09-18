
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceFundraiserActivityCharityFundSplit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserActivityCharityFundSplit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityFundPercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserActivityCharityFundSplit", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "charityId",
    "charityName",
    "charityFundPercentage"
})
public class ServiceFundraiserActivityCharityFundSplit {

    @XmlElement(required = true)
    protected String charityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String charityFundPercentage;

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

}
