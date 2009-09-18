
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceDonationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceDonationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="donorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fundraiserActivityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="donationDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="grossAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="eligibleForGiftAid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="paymentFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="donorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donorEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="donationReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="giftAidAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="paymentCardDetails" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServicePaymentCardDetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDonationDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "id",
    "donorId",
    "charityId",
    "charityName",
    "fundraiserActivityId",
    "donationDatetime",
    "grossAmount",
    "eligibleForGiftAid",
    "paymentFrequency",
    "paymentDay",
    "firstPaymentDate",
    "donorName",
    "donorMessage",
    "donorEmailAddress",
    "donationReference",
    "giftAidAmount",
    "paymentCardDetails"
})
public class ServiceDonationDetails {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long donorId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true)
    protected String charityName;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long fundraiserActivityId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar donationDatetime;
    @XmlElement(required = true)
    protected BigDecimal grossAmount;
    protected boolean eligibleForGiftAid;
    @XmlElement(required = true)
    protected String paymentFrequency;
    protected int paymentDay;
    @XmlElement(required = true)
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
    @XmlElement(required = true)
    protected ServicePaymentCardDetails paymentCardDetails;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

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
     * Gets the value of the charityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the value of the charityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityName(String value) {
        this.charityName = value;
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
     * Gets the value of the donationDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDonationDatetime() {
        return donationDatetime;
    }

    /**
     * Sets the value of the donationDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDonationDatetime(XMLGregorianCalendar value) {
        this.donationDatetime = value;
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
     * Gets the value of the paymentDay property.
     * 
     */
    public int getPaymentDay() {
        return paymentDay;
    }

    /**
     * Sets the value of the paymentDay property.
     * 
     */
    public void setPaymentDay(int value) {
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

    /**
     * Gets the value of the paymentCardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServicePaymentCardDetails }
     *     
     */
    public ServicePaymentCardDetails getPaymentCardDetails() {
        return paymentCardDetails;
    }

    /**
     * Sets the value of the paymentCardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicePaymentCardDetails }
     *     
     */
    public void setPaymentCardDetails(ServicePaymentCardDetails value) {
        this.paymentCardDetails = value;
    }

}
