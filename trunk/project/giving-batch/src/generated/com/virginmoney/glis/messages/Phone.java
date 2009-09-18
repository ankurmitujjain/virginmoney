
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Phone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Phone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phoneType" type="{http://www.virginmoney.com/type/glis/2009/01}PhoneType"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phone", propOrder = {
    "phoneType",
    "areaCode",
    "phoneNumber"
})
public class Phone {

    @XmlElement(required = true)
    protected PhoneType phoneType;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String areaCode;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String phoneNumber;

    /**
     * Gets the value of the phoneType property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneType }
     *     
     */
    public PhoneType getPhoneType() {
        return phoneType;
    }

    /**
     * Sets the value of the phoneType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneType }
     *     
     */
    public void setPhoneType(PhoneType value) {
        this.phoneType = value;
    }

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

}
