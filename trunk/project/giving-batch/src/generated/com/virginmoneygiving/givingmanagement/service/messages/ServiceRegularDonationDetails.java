
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceRegularDonationDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRegularDonationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="donorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="grossAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="firstPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="paymentFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="giftAidEligibleInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="statusIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="charityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="latestPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="nextPaymentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRegularDonationDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/donor/", propOrder = {
    "id",
    "donorId",
    "charityId",
    "grossAmount",
    "firstPaymentDate",
    "paymentFrequency",
    "currency",
    "paymentDay",
    "giftAidEligibleInd",
    "statusIndicator",
    "charityName",
    "latestPaymentDate",
    "nextPaymentDate"
})
public class ServiceRegularDonationDetails {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long donorId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long charityId;
    @XmlElement(required = true)
    protected BigDecimal grossAmount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstPaymentDate;
    @XmlElement(required = true)
    protected String paymentFrequency;
    @XmlElement(required = true)
    protected String currency;
    protected int paymentDay;
    protected boolean giftAidEligibleInd;
    protected boolean statusIndicator;
    @XmlElement(required = true, nillable = true)
    protected String charityName;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar latestPaymentDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar nextPaymentDate;

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
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
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
     * Gets the value of the giftAidEligibleInd property.
     * 
     */
    public boolean isGiftAidEligibleInd() {
        return giftAidEligibleInd;
    }

    /**
     * Sets the value of the giftAidEligibleInd property.
     * 
     */
    public void setGiftAidEligibleInd(boolean value) {
        this.giftAidEligibleInd = value;
    }

    /**
     * Gets the value of the statusIndicator property.
     * 
     */
    public boolean isStatusIndicator() {
        return statusIndicator;
    }

    /**
     * Sets the value of the statusIndicator property.
     * 
     */
    public void setStatusIndicator(boolean value) {
        this.statusIndicator = value;
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
     * Gets the value of the latestPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestPaymentDate() {
        return latestPaymentDate;
    }

    /**
     * Sets the value of the latestPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestPaymentDate(XMLGregorianCalendar value) {
        this.latestPaymentDate = value;
    }

    /**
     * Gets the value of the nextPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextPaymentDate() {
        return nextPaymentDate;
    }

    /**
     * Sets the value of the nextPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextPaymentDate(XMLGregorianCalendar value) {
        this.nextPaymentDate = value;
    }

}
