
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PreviousGiftAidClaimDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreviousGiftAidClaimDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="giftAidClaimFromDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="giftAidClaimToDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="claimAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="noOfRecords" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="claimAvailableToSettle" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="claimPreviouslySettled" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreviousGiftAidClaimDetails", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "statusMessage",
    "giftAidClaimFromDate",
    "giftAidClaimToDate",
    "claimAmount",
    "noOfRecords",
    "claimAvailableToSettle",
    "claimPreviouslySettled"
})
public class PreviousGiftAidClaimDetails {

    @XmlElement(required = true)
    protected String statusMessage;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar giftAidClaimFromDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar giftAidClaimToDate;
    @XmlElement(required = true)
    protected BigDecimal claimAmount;
    protected long noOfRecords;
    @XmlElement(required = true)
    protected BigDecimal claimAvailableToSettle;
    @XmlElement(required = true)
    protected BigDecimal claimPreviouslySettled;

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Gets the value of the giftAidClaimFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGiftAidClaimFromDate() {
        return giftAidClaimFromDate;
    }

    /**
     * Sets the value of the giftAidClaimFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGiftAidClaimFromDate(XMLGregorianCalendar value) {
        this.giftAidClaimFromDate = value;
    }

    /**
     * Gets the value of the giftAidClaimToDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGiftAidClaimToDate() {
        return giftAidClaimToDate;
    }

    /**
     * Sets the value of the giftAidClaimToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGiftAidClaimToDate(XMLGregorianCalendar value) {
        this.giftAidClaimToDate = value;
    }

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

    /**
     * Gets the value of the claimAvailableToSettle property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimAvailableToSettle() {
        return claimAvailableToSettle;
    }

    /**
     * Sets the value of the claimAvailableToSettle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimAvailableToSettle(BigDecimal value) {
        this.claimAvailableToSettle = value;
    }

    /**
     * Gets the value of the claimPreviouslySettled property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimPreviouslySettled() {
        return claimPreviouslySettled;
    }

    /**
     * Sets the value of the claimPreviouslySettled property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimPreviouslySettled(BigDecimal value) {
        this.claimPreviouslySettled = value;
    }

}
