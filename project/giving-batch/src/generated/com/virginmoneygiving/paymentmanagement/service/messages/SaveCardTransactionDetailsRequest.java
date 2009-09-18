
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
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="cardTransaction" type="{http://www.virginmoneygiving.com/type/payment-management/}ServiceCardTransaction"/>
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
    "cardTransaction"
})
@XmlRootElement(name = "saveCardTransactionDetailsRequest")
public class SaveCardTransactionDetailsRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true)
    protected ServiceCardTransaction cardTransaction;

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
     * Gets the value of the cardTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCardTransaction }
     *     
     */
    public ServiceCardTransaction getCardTransaction() {
        return cardTransaction;
    }

    /**
     * Sets the value of the cardTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCardTransaction }
     *     
     */
    public void setCardTransaction(ServiceCardTransaction value) {
        this.cardTransaction = value;
    }

}
