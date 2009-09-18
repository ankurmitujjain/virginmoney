
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
 *         &lt;element name="transitionalReliefClaimPeriod" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}claimPeriod"/>
 *         &lt;element name="recordCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "transitionalReliefClaimPeriod",
    "recordCount"
})
@XmlRootElement(name = "updateGiftAidClaimForSettlementRequest", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class UpdateGiftAidClaimForSettlementRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected ClaimPeriod transitionalReliefClaimPeriod;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
    protected long recordCount;

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
     * Gets the value of the transitionalReliefClaimPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link ClaimPeriod }
     *     
     */
    public ClaimPeriod getTransitionalReliefClaimPeriod() {
        return transitionalReliefClaimPeriod;
    }

    /**
     * Sets the value of the transitionalReliefClaimPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClaimPeriod }
     *     
     */
    public void setTransitionalReliefClaimPeriod(ClaimPeriod value) {
        this.transitionalReliefClaimPeriod = value;
    }

    /**
     * Gets the value of the recordCount property.
     * 
     */
    public long getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the value of the recordCount property.
     * 
     */
    public void setRecordCount(long value) {
        this.recordCount = value;
    }

}
