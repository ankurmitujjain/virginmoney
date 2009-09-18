
package com.virginmoneygiving.cardpayment.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCardSecurityInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCardSecurityInformation">
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
@XmlType(name = "ServiceCardSecurityInformation", namespace = "http://www.virginmoneygiving.com/type/online-card-payment/card-payment", propOrder = {
    "csc",
    "houseNumber",
    "postcode"
})
public class ServiceCardSecurityInformation {

    @XmlElement(required = true)
    protected String csc;
    protected int houseNumber;
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
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     */
    public void setHouseNumber(int value) {
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
