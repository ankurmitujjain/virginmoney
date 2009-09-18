
package com.logicgroup.basic.service.messages;

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
 *         &lt;element name="RefundExistingCardResult" type="{http://secure-payment-processing.com/}RefundReturn" minOccurs="0"/>
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
    "refundExistingCardResult"
})
@XmlRootElement(name = "RefundExistingCardResponse")
public class RefundExistingCardResponse {

    @XmlElement(name = "RefundExistingCardResult")
    protected RefundReturn refundExistingCardResult;

    /**
     * Gets the value of the refundExistingCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link RefundReturn }
     *     
     */
    public RefundReturn getRefundExistingCardResult() {
        return refundExistingCardResult;
    }

    /**
     * Sets the value of the refundExistingCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundReturn }
     *     
     */
    public void setRefundExistingCardResult(RefundReturn value) {
        this.refundExistingCardResult = value;
    }

}
