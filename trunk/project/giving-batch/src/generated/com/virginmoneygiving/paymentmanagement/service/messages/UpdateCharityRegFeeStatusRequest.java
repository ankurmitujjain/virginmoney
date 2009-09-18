
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="charityRegFeeTransactionIdsList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="newStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "charityRegFeeTransactionIdsList",
    "newStatus"
})
@XmlRootElement(name = "updateCharityRegFeeStatusRequest", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class UpdateCharityRegFeeStatusRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", type = Long.class)
    protected List<Long> charityRegFeeTransactionIdsList;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected String newStatus;

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
     * Gets the value of the charityRegFeeTransactionIdsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charityRegFeeTransactionIdsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharityRegFeeTransactionIdsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCharityRegFeeTransactionIdsList() {
        if (charityRegFeeTransactionIdsList == null) {
            charityRegFeeTransactionIdsList = new ArrayList<Long>();
        }
        return this.charityRegFeeTransactionIdsList;
    }

    /**
     * Gets the value of the newStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewStatus() {
        return newStatus;
    }

    /**
     * Sets the value of the newStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewStatus(String value) {
        this.newStatus = value;
    }

}
