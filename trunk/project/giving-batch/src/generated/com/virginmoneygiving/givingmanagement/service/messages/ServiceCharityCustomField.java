
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCharityCustomField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCharityCustomField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customFieldTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fieldLabel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCharityCustomField", propOrder = {
    "charityId",
    "customFieldTypeCode",
    "fieldLabel"
})
public class ServiceCharityCustomField {

    @XmlElement(required = true, nillable = true)
    protected String charityId;
    @XmlElement(required = true, nillable = true)
    protected String customFieldTypeCode;
    @XmlElement(required = true, nillable = true)
    protected String fieldLabel;

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
     * Gets the value of the fieldLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldLabel() {
        return fieldLabel;
    }

    /**
     * Sets the value of the fieldLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldLabel(String value) {
        this.fieldLabel = value;
    }

}
