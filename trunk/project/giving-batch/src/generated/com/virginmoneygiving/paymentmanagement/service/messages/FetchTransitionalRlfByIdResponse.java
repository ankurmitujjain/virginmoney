
package com.virginmoneygiving.paymentmanagement.service.messages;

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
 *         &lt;element name="transitionalRlf" type="{http://www.virginmoneygiving.com/type/payment-management/}ServiceEntity"/>
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
    "transitionalRlf"
})
@XmlRootElement(name = "fetchTransitionalRlfByIdResponse")
public class FetchTransitionalRlfByIdResponse {

    @XmlElement(required = true)
    protected ServiceEntity transitionalRlf;

    /**
     * Gets the value of the transitionalRlf property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEntity }
     *     
     */
    public ServiceEntity getTransitionalRlf() {
        return transitionalRlf;
    }

    /**
     * Sets the value of the transitionalRlf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEntity }
     *     
     */
    public void setTransitionalRlf(ServiceEntity value) {
        this.transitionalRlf = value;
    }

}