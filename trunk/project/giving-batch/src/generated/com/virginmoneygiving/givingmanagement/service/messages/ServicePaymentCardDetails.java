
package com.virginmoneygiving.givingmanagement.service.messages;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServicePaymentCardDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServicePaymentCardDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cardProviderCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="binNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maskedCardNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cardName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expiryMonth" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="expiryYear" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="validFromMonth" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="validFromYear" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="issueNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="cardToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cardHolderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingAddress" type="{http://www.virginmoneygiving.com/type/giving-management/common/}ServiceAddress"/>
 *         &lt;element name="billingEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServicePaymentCardDetails", namespace = "http://www.virginmoneygiving.com/type/giving-management/common/", propOrder = {
    "id",
    "personId",
    "cardProviderCode",
    "binNumber",
    "maskedCardNumber",
    "cardName",
    "expiryMonth",
    "expiryYear",
    "validFromMonth",
    "validFromYear",
    "issueNumber",
    "cardToken",
    "cardHolderName",
    "billingAddress",
    "billingEmailAddress"
})
public class ServicePaymentCardDetails {

    protected long id;
    protected long personId;
    @XmlElement(required = true)
    protected String cardProviderCode;
    @XmlElement(required = true)
    protected String binNumber;
    @XmlElement(required = true)
    protected String maskedCardNumber;
    @XmlElement(required = true)
    protected String cardName;
    @XmlElement(required = true)
    protected BigInteger expiryMonth;
    @XmlElement(required = true)
    protected BigInteger expiryYear;
    @XmlElement(required = true)
    protected BigInteger validFromMonth;
    @XmlElement(required = true)
    protected BigInteger validFromYear;
    @XmlElement(required = true)
    protected BigInteger issueNumber;
    @XmlElement(required = true)
    protected String cardToken;
    @XmlElement(required = true)
    protected String cardHolderName;
    @XmlElement(required = true)
    protected ServiceAddress billingAddress;
    @XmlElement(required = true)
    protected String billingEmailAddress;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the personId property.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Sets the value of the personId property.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

    /**
     * Gets the value of the cardProviderCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardProviderCode() {
        return cardProviderCode;
    }

    /**
     * Sets the value of the cardProviderCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardProviderCode(String value) {
        this.cardProviderCode = value;
    }

    /**
     * Gets the value of the binNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinNumber() {
        return binNumber;
    }

    /**
     * Sets the value of the binNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinNumber(String value) {
        this.binNumber = value;
    }

    /**
     * Gets the value of the maskedCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    /**
     * Sets the value of the maskedCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaskedCardNumber(String value) {
        this.maskedCardNumber = value;
    }

    /**
     * Gets the value of the cardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the value of the cardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardName(String value) {
        this.cardName = value;
    }

    /**
     * Gets the value of the expiryMonth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpiryMonth() {
        return expiryMonth;
    }

    /**
     * Sets the value of the expiryMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpiryMonth(BigInteger value) {
        this.expiryMonth = value;
    }

    /**
     * Gets the value of the expiryYear property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpiryYear() {
        return expiryYear;
    }

    /**
     * Sets the value of the expiryYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpiryYear(BigInteger value) {
        this.expiryYear = value;
    }

    /**
     * Gets the value of the validFromMonth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidFromMonth() {
        return validFromMonth;
    }

    /**
     * Sets the value of the validFromMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidFromMonth(BigInteger value) {
        this.validFromMonth = value;
    }

    /**
     * Gets the value of the validFromYear property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidFromYear() {
        return validFromYear;
    }

    /**
     * Sets the value of the validFromYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidFromYear(BigInteger value) {
        this.validFromYear = value;
    }

    /**
     * Gets the value of the issueNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the value of the issueNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIssueNumber(BigInteger value) {
        this.issueNumber = value;
    }

    /**
     * Gets the value of the cardToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Sets the value of the cardToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardToken(String value) {
        this.cardToken = value;
    }

    /**
     * Gets the value of the cardHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the value of the cardHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderName(String value) {
        this.cardHolderName = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setBillingAddress(ServiceAddress value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the billingEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingEmailAddress() {
        return billingEmailAddress;
    }

    /**
     * Sets the value of the billingEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingEmailAddress(String value) {
        this.billingEmailAddress = value;
    }

}
