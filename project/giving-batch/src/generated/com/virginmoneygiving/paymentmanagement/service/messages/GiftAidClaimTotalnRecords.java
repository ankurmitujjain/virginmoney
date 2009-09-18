
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GiftAidClaimTotalnRecords complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GiftAidClaimTotalnRecords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claimAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="noOfRecords" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GiftAidClaimTotalnRecords", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "claimAmount",
    "noOfRecords"
})
public class GiftAidClaimTotalnRecords {

    @XmlElement(required = true)
    protected BigDecimal claimAmount;
    protected long noOfRecords;

    /**
     * Gets the value of the claimAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    /**
     * Sets the value of the claimAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimAmount(BigDecimal value) {
        this.claimAmount = value;
    }

    /**
     * Gets the value of the noOfRecords property.
     * 
     */
    public long getNoOfRecords() {
        return noOfRecords;
    }

    /**
     * Sets the value of the noOfRecords property.
     * 
     */
    public void setNoOfRecords(long value) {
        this.noOfRecords = value;
    }

}
