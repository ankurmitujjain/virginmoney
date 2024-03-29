
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceProfanityCheckResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceProfanityCheckResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cleaned" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceProfanityCheckResult", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "key",
    "value",
    "cleaned"
})
public class ServiceProfanityCheckResult {

    @XmlElement(required = true)
    protected String key;
    protected boolean value;
    @XmlElement(required = true)
    protected String cleaned;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the value property.
     * 
     */
    public boolean isValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    /**
     * Gets the value of the cleaned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCleaned() {
        return cleaned;
    }

    /**
     * Sets the value of the cleaned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCleaned(String value) {
        this.cleaned = value;
    }

}
