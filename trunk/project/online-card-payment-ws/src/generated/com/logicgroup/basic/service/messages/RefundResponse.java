
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
 *         &lt;element name="RefundResult" type="{http://secure-payment-processing.com/}RefundReturn" minOccurs="0"/>
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
    "refundResult"
})
@XmlRootElement(name = "RefundResponse")
public class RefundResponse {

    @XmlElement(name = "RefundResult")
    protected RefundReturn refundResult;

    /**
     * Gets the value of the refundResult property.
     * 
     * @return
     *     possible object is
     *     {@link RefundReturn }
     *     
     */
    public RefundReturn getRefundResult() {
        return refundResult;
    }

    /**
     * Sets the value of the refundResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundReturn }
     *     
     */
    public void setRefundResult(RefundReturn value) {
        this.refundResult = value;
    }

}
