
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GivingServiceCardSecurityInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GivingServiceCardSecurityInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="csc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="houseNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="postcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GivingServiceCardSecurityInformation", namespace = "http://www.virginmoneygiving.com/type/giving-management/card-payment/", propOrder = {
    "csc",
    "houseNumber",
    "postcode"
})
public class GivingServiceCardSecurityInformation {

    @XmlElement(required = true)
    protected String csc;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer houseNumber;
    @XmlElement(required = true)
    protected String postcode;

    /**
     * Gets the value of the csc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsc() {
        return csc;
    }

    /**
     * Sets the value of the csc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsc(String value) {
        this.csc = value;
    }

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHouseNumber(Integer value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

}
