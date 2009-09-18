
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
 *         &lt;element name="paymentCardDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServicePaymentCardDetails"/>
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
    "paymentCardDetails"
})
@XmlRootElement(name = "paymentCardDetailsResponse", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/")
public class PaymentCardDetailsResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", required = true)
    protected ServicePaymentCardDetails paymentCardDetails;

    /**
     * Gets the value of the paymentCardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePaymentCardDetails }
     *     
     */
    public ServicePaymentCardDetails getPaymentCardDetails() {
        return paymentCardDetails;
    }

    /**
     * Sets the value of the paymentCardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePaymentCardDetails }
     *     
     */
    public void setPaymentCardDetails(ServicePaymentCardDetails value) {
        this.paymentCardDetails = value;
    }

}
