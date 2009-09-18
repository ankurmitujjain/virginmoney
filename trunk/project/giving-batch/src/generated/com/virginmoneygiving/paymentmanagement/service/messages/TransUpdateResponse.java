
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
 *         &lt;element name="transUpdateStatus" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}TransUpdateStatus"/>
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
    "transUpdateStatus",
    "basicReponse"
})
@XmlRootElement(name = "transUpdateResponse", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class TransUpdateResponse {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected TransUpdateStatus transUpdateStatus;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected BasicResponse basicReponse;

    /**
     * Gets the value of the transUpdateStatus property.
     * 
     * @return
     *     possible object is
     *     {@link TransUpdateStatus }
     *     
     */
    public TransUpdateStatus getTransUpdateStatus() {
        return transUpdateStatus;
    }

    /**
     * Sets the value of the transUpdateStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransUpdateStatus }
     *     
     */
    public void setTransUpdateStatus(TransUpdateStatus value) {
        this.transUpdateStatus = value;
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
