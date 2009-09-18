
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
 *         &lt;element name="transactionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "transactionType",
    "referenceType"
})
@XmlRootElement(name = "generateSequenceRequest")
public class GenerateSequenceRequest {

    @XmlElement(name = "Header", required = true)
    protected MessageHeader header;
    @XmlElement(required = true, nillable = true)
    protected String transactionType;
    @XmlElement(required = true, nillable = true)
    protected String referenceType;

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
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the referenceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Sets the value of the referenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceType(String value) {
        this.referenceType = value;
    }

}
