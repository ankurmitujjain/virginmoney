
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldCopy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "id",
    "charityId",
    "customFieldTypeCode",
    "customFieldValue",
    "customFieldCopy"
})
@XmlRootElement(name = "saveCustomFieldValuesRequest")
public class SaveCustomFieldValuesRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String charityId;
    @XmlElement(required = true)
    protected String customFieldTypeCode;
    @XmlElement(required = true)
    protected String customFieldValue;
    @XmlElement(required = true)
    protected String customFieldCopy;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setHeader(MessageHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

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
     * Gets the value of the customFieldTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldTypeCode() {
        return customFieldTypeCode;
    }

    /**
     * Sets the value of the customFieldTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldTypeCode(String value) {
        this.customFieldTypeCode = value;
    }

    /**
     * Gets the value of the customFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldValue() {
        return customFieldValue;
    }

    /**
     * Sets the value of the customFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldValue(String value) {
        this.customFieldValue = value;
    }

    /**
     * Gets the value of the customFieldCopy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldCopy() {
        return customFieldCopy;
    }

    /**
     * Sets the value of the customFieldCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldCopy(String value) {
        this.customFieldCopy = value;
    }

}
