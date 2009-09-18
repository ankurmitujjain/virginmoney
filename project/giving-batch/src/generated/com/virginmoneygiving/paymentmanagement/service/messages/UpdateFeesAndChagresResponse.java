
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
 *         &lt;element name="feesAndChargesDetails" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}ServiceFeesAndCharges"/>
 *         &lt;element name="basicReponse" type="{http://www.virginmoneygiving.com/type/payment-management/common/}BasicResponse"/>
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
    "feesAndChargesDetails",
    "basicReponse"
})
@XmlRootElement(name = "updateFeesAndChagresResponse", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class UpdateFeesAndChagresResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected ServiceFeesAndCharges feesAndChargesDetails;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the feesAndChargesDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFeesAndCharges }
     *     
     */
    public ServiceFeesAndCharges getFeesAndChargesDetails() {
        return feesAndChargesDetails;
    }

    /**
     * Sets the value of the feesAndChargesDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFeesAndCharges }
     *     
     */
    public void setFeesAndChargesDetails(ServiceFeesAndCharges value) {
        this.feesAndChargesDetails = value;
    }

    /**
     * Gets the value of the basicReponse property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResponse }
     *     
     */
    public BasicResponse getBasicReponse() {
        return basicReponse;
    }

    /**
     * Sets the value of the basicReponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResponse }
     *     
     */
    public void setBasicReponse(BasicResponse value) {
        this.basicReponse = value;
    }

}
