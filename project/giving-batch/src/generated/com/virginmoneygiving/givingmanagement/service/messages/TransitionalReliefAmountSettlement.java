
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TransitionalReliefAmountSettlement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransitionalReliefAmountSettlement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claimTotal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="claimNoOfRecords" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="claimedPeriodFrom" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="claimedPeriodTo" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
@XmlType(name = "TransitionalReliefAmountSettlement", namespace = "http://www.virginmoneygiving.com/type/giving-management/operations/", propOrder = {
    "claimTotal",
    "claimNoOfRecords",
    "claimedPeriodFrom",
    "claimedPeriodTo",
    "claimAvailableToSettle",
    "claimPreviouslySettled"
})
public class TransitionalReliefAmountSettlement {

    @XmlElement(required = true)
    protected BigDecimal claimTotal;
    protected long claimNoOfRecords;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar claimedPeriodFrom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar claimedPeriodTo;
    @XmlElement(required = true)
    protected BigDecimal claimAvailableToSettle;
    @XmlElement(required = true)
    protected BigDecimal claimPreviouslySettled;

    /**
     * Gets the value of the claimTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClaimTotal() {
        return claimTotal;
    }

    /**
     * Sets the value of the claimTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClaimTotal(BigDecimal value) {
        this.claimTotal = value;
    }

    /**
     * Gets the value of the claimNoOfRecords property.
     * 
     */
    public long getClaimNoOfRecords() {
        return claimNoOfRecords;
    }

    /**
     * Sets the value of the claimNoOfRecords property.
     * 
     */
    public void setClaimNoOfRecords(long value) {
        this.claimNoOfRecords = value;
    }

    /**
     * Gets the value of the claimedPeriodFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClaimedPeriodFrom() {
        return claimedPeriodFrom;
    }

    /**
     * Sets the value of the claimedPeriodFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClaimedPeriodFrom(XMLGregorianCalendar value) {
        this.claimedPeriodFrom = value;
    }

    /**
     * Gets the value of the claimedPeriodTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClaimedPeriodTo() {
        return claimedPeriodTo;
    }

    /**
     * Sets the value of the claimedPeriodTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClaimedPeriodTo(XMLGregorianCalendar value) {
        this.claimedPeriodTo = value;
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
