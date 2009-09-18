
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharitiesByCharityCategoryId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharitiesByCharityCategoryId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityRegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logoUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logoFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharitiesByCharityCategoryId", propOrder = {
    "charityId",
    "charityName",
    "charityRegistrationNumber",
    "logoUrl",
    "logoFileName",
    "description"
})
public class ServiceCharitiesByCharityCategoryId {

    protected long charityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true)
    protected String charityRegistrationNumber;
    @XmlElement(required = true)
    protected String logoUrl;
    @XmlElement(required = true)
    protected String logoFileName;
    @XmlElement(required = true)
    protected String description;

    /**
     * Gets the value of the charityId property.
     * 
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     */
    public void setCharityId(long value) {
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
     * Gets the value of the logoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Sets the value of the logoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoUrl(String value) {
        this.logoUrl = value;
    }

    /**
     * Gets the value of the logoFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoFileName() {
        return logoFileName;
    }

    /**
     * Sets the value of the logoFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoFileName(String value) {
        this.logoFileName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
