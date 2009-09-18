
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServicePayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServicePayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentTarget" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grossAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceBankAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceBankSortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetBankAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetBankSortCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="financeReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vatAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="vatStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="paymentStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServicePayment", propOrder = {
    "paymentSource",
    "paymentTarget",
    "paymentReason",
    "grossAmount",
    "currency",
    "sourceBankAccount",
    "sourceBankSortCode",
    "targetBankAccount",
    "targetBankSortCode",
    "financeReference",
    "vatAmount",
    "vatStatusCode",
    "paymentType",
    "createdDateTime",
    "paymentStatusCode"
})
public class ServicePayment {

    @XmlElement(required = true)
    protected String paymentSource;
    @XmlElement(required = true)
    protected String paymentTarget;
    @XmlElement(required = true)
    protected String paymentReason;
    @XmlElement(required = true)
    protected BigDecimal grossAmount;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(required = true)
    protected String sourceBankAccount;
    @XmlElement(required = true)
    protected String sourceBankSortCode;
    @XmlElement(required = true)
    protected String targetBankAccount;
    @XmlElement(required = true)
    protected String targetBankSortCode;
    @XmlElement(required = true)
    protected String financeReference;
    @XmlElement(required = true)
    protected BigDecimal vatAmount;
    @XmlElement(required = true)
    protected String vatStatusCode;
    @XmlElement(required = true)
    protected String paymentType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDateTime;
    @XmlElement(required = true)
    protected String paymentStatusCode;

    /**
     * Gets the value of the paymentSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentSource() {
        return paymentSource;
    }

    /**
     * Sets the value of the paymentSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentSource(String value) {
        this.paymentSource = value;
    }

    /**
     * Gets the value of the paymentTarget property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTarget() {
        return paymentTarget;
    }

    /**
     * Sets the value of the paymentTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTarget(String value) {
        this.paymentTarget = value;
    }

    /**
     * Gets the value of the paymentReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentReason() {
        return paymentReason;
    }

    /**
     * Sets the value of the paymentReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentReason(String value) {
        this.paymentReason = value;
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
     * Gets the value of the sourceBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceBankAccount() {
        return sourceBankAccount;
    }

    /**
     * Sets the value of the sourceBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceBankAccount(String value) {
        this.sourceBankAccount = value;
    }

    /**
     * Gets the value of the sourceBankSortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceBankSortCode() {
        return sourceBankSortCode;
    }

    /**
     * Sets the value of the sourceBankSortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceBankSortCode(String value) {
        this.sourceBankSortCode = value;
    }

    /**
     * Gets the value of the targetBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetBankAccount() {
        return targetBankAccount;
    }

    /**
     * Sets the value of the targetBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetBankAccount(String value) {
        this.targetBankAccount = value;
    }

    /**
     * Gets the value of the targetBankSortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetBankSortCode() {
        return targetBankSortCode;
    }

    /**
     * Sets the value of the targetBankSortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetBankSortCode(String value) {
        this.targetBankSortCode = value;
    }

    /**
     * Gets the value of the financeReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinanceReference() {
        return financeReference;
    }

    /**
     * Sets the value of the financeReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinanceReference(String value) {
        this.financeReference = value;
    }

    /**
     * Gets the value of the vatAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    /**
     * Sets the value of the vatAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatAmount(BigDecimal value) {
        this.vatAmount = value;
    }

    /**
     * Gets the value of the vatStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVatStatusCode() {
        return vatStatusCode;
    }

    /**
     * Sets the value of the vatStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVatStatusCode(String value) {
        this.vatStatusCode = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentType(String value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the createdDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the value of the createdDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDateTime(XMLGregorianCalendar value) {
        this.createdDateTime = value;
    }

    /**
     * Gets the value of the paymentStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatusCode() {
        return paymentStatusCode;
    }

    /**
     * Sets the value of the paymentStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatusCode(String value) {
        this.paymentStatusCode = value;
    }

}
