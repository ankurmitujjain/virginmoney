
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
 *         &lt;element name="servicePayment" type="{http://www.virginmoneygiving.com/type/payment-management/}ServicePayment"/>
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
    "servicePayment"
})
@XmlRootElement(name = "fetchCharityRegistrationInvoiceDetailsResponse")
public class FetchCharityRegistrationInvoiceDetailsResponse {

    @XmlElement(required = true)
    protected ServicePayment servicePayment;

    /**
     * Gets the value of the servicePayment property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePayment }
     *     
     */
    public ServicePayment getServicePayment() {
        return servicePayment;
    }

    /**
     * Sets the value of the servicePayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePayment }
     *     
     */
    public void setServicePayment(ServicePayment value) {
        this.servicePayment = value;
    }

}
