
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GivingServiceDonationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GivingServiceDonationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="donorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="grossAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="eligibleForGiftAid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="paymentFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="regularDonationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="donorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donationReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="giftAidAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GivingServiceDonationDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "donorId",
    "charityId",
    "fundraiserActivityId",
    "grossAmount",
    "eligibleForGiftAid",
    "paymentFrequency",
    "regularDonationId",
    "paymentDay",
    "firstPaymentDate",
    "donorName",
    "donorMessage",
    "donorEmailAddress",
    "donationReference",
    "giftAidAmount"
})
public class GivingServiceDonationDetails {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long donorId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserActivityId;
    @XmlElement(required = true)
    protected BigDecimal grossAmount;
    protected boolean eligibleForGiftAid;
    @XmlElement(required = true)
    protected String paymentFrequency;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long regularDonationId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer paymentDay;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstPaymentDate;
    @XmlElement(required = true)
    protected String donorName;
    @XmlElement(required = true)
    protected String donorMessage;
    @XmlElement(required = true)
    protected String donorEmailAddress;
    @XmlElement(required = true)
    protected String donationReference;
    @XmlElement(required = true)
    protected BigDecimal giftAidAmount;

    /**
     * Gets the value of the donorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDonorId() {
        return donorId;
    }

    /**
     * Sets the value of the donorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDonorId(Long value) {
        this.donorId = value;
    }

    /**
     * Gets the value of the charityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCharityId(Long value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the fundraiserActivityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFundraiserActivityId() {
        return fundraiserActivityId;
    }

    /**
     * Sets the value of the fundraiserActivityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFundraiserActivityId(Long value) {
        this.fundraiserActivityId = value;
    }

    /**
     * Gets the value of the grossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    /**
     * Sets the value of the grossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGrossAmount(BigDecimal value) {
        this.grossAmount = value;
    }

    /**
     * Gets the value of the eligibleForGiftAid property.
     * 
     */
    public boolean isEligibleForGiftAid() {
        return eligibleForGiftAid;
    }

    /**
     * Sets the value of the eligibleForGiftAid property.
     * 
     */
    public void setEligibleForGiftAid(boolean value) {
        this.eligibleForGiftAid = value;
    }

    /**
     * Gets the value of the paymentFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Sets the value of the paymentFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentFrequency(String value) {
        this.paymentFrequency = value;
    }

    /**
     * Gets the value of the regularDonationId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRegularDonationId() {
        return regularDonationId;
    }

    /**
     * Sets the value of the regularDonationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRegularDonationId(Long value) {
        this.regularDonationId = value;
    }

    /**
     * Gets the value of the paymentDay property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPaymentDay() {
        return paymentDay;
    }

    /**
     * Sets the value of the paymentDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymentDay(Integer value) {
        this.paymentDay = value;
    }

    /**
     * Gets the value of the firstPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstPaymentDate() {
        return firstPaymentDate;
    }

    /**
     * Sets the value of the firstPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstPaymentDate(XMLGregorianCalendar value) {
        this.firstPaymentDate = value;
    }

    /**
     * Gets the value of the donorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorName() {
        return donorName;
    }

    /**
     * Sets the value of the donorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorName(String value) {
        this.donorName = value;
    }

    /**
     * Gets the value of the donorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorMessage() {
        return donorMessage;
    }

    /**
     * Sets the value of the donorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorMessage(String value) {
        this.donorMessage = value;
    }

    /**
     * Gets the value of the donorEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonorEmailAddress() {
        return donorEmailAddress;
    }

    /**
     * Sets the value of the donorEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonorEmailAddress(String value) {
        this.donorEmailAddress = value;
    }

    /**
     * Gets the value of the donationReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonationReference() {
        return donationReference;
    }

    /**
     * Sets the value of the donationReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonationReference(String value) {
        this.donationReference = value;
    }

    /**
     * Gets the value of the giftAidAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGiftAidAmount() {
        return giftAidAmount;
    }

    /**
     * Sets the value of the giftAidAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGiftAidAmount(BigDecimal value) {
        this.giftAidAmount = value;
    }

}
