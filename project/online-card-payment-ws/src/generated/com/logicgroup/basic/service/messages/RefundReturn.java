
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefundReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RefundReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://secure-payment-processing.com/}Result" minOccurs="0"/>
 *         &lt;element name="RefundDetails" type="{http://secure-payment-processing.com/}RefundDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefundReturn", propOrder = {
    "result",
    "refundDetails"
})
public class RefundReturn {

    @XmlElement(name = "Result")
    protected Result result;
    @XmlElement(name = "RefundDetails")
    protected RefundDetails refundDetails;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the refundDetails property.
     * 
     * @return
     *     possible object is
     *     {@link RefundDetails }
     *     
     */
    public RefundDetails getRefundDetails() {
        return refundDetails;
    }

    /**
     * Sets the value of the refundDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundDetails }
     *     
     */
    public void setRefundDetails(RefundDetails value) {
        this.refundDetails = value;
    }

}
