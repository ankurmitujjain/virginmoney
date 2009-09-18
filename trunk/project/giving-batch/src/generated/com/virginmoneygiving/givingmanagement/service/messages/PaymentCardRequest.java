
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
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="paymentCardDetail" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServicePaymentCardDetails"/>
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
    "header",
    "paymentCardDetail"
})
@XmlRootElement(name = "PaymentCardRequest")
public class PaymentCardRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected ServicePaymentCardDetails paymentCardDetail;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setHeader(MessageHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the paymentCardDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePaymentCardDetails }
     *     
     */
    public ServicePaymentCardDetails getPaymentCardDetail() {
        return paymentCardDetail;
    }

    /**
     * Sets the value of the paymentCardDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePaymentCardDetails }
     *     
     */
    public void setPaymentCardDetail(ServicePaymentCardDetails value) {
        this.paymentCardDetail = value;
    }

}
