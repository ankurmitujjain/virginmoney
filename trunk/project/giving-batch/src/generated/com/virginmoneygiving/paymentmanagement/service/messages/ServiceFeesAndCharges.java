
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceFeesAndCharges complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFeesAndCharges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="calculatioDetailsId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="registrationFeeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="registrationFeeStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="waiveRegistrationFeeIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registrationFeeWaivedReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registrationFeeAmendmentHistory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionFeeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="waiveTransactionFeeIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionFeeWaivedReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionFeeAmendmentHistory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="regFeeEnabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="txnFeeEnabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="updatedDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="batchStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFeesAndCharges", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "charityId",
    "paymentId",
    "calculatioDetailsId",
    "registrationFeeAmount",
    "registrationFeeStatus",
    "paymentMethod",
    "waiveRegistrationFeeIndicator",
    "registrationFeeWaivedReason",
    "registrationFeeAmendmentHistory",
    "transactionFeeAmount",
    "waiveTransactionFeeIndicator",
    "transactionFeeWaivedReason",
    "transactionFeeAmendmentHistory",
    "regFeeEnabled",
    "txnFeeEnabled",
    "createdUser",
    "createdDateTime",
    "updatedUser",
    "updatedDateTime",
    "batchStatus"
})
public class ServiceFeesAndCharges {

    protected long charityId;
    protected long paymentId;
    protected long calculatioDetailsId;
    @XmlElement(required = true)
    protected BigDecimal registrationFeeAmount;
    @XmlElement(required = true)
    protected String registrationFeeStatus;
    @XmlElement(required = true)
    protected String paymentMethod;
    @XmlElement(required = true)
    protected String waiveRegistrationFeeIndicator;
    @XmlElement(required = true)
    protected String registrationFeeWaivedReason;
    @XmlElement(required = true)
    protected String registrationFeeAmendmentHistory;
    @XmlElement(required = true)
    protected BigDecimal transactionFeeAmount;
    @XmlElement(required = true)
    protected String waiveTransactionFeeIndicator;
    @XmlElement(required = true)
    protected String transactionFeeWaivedReason;
    @XmlElement(required = true)
    protected String transactionFeeAmendmentHistory;
    @XmlElement(required = true)
    protected String regFeeEnabled;
    @XmlElement(required = true)
    protected String txnFeeEnabled;
    @XmlElement(required = true)
    protected String createdUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDateTime;
    @XmlElement(required = true)
    protected String updatedUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedDateTime;
    @XmlElement(required = true)
    protected String batchStatus;

    /**
     * Gets the value of the charityId property.
     * 
     */
    public long getCharityId() {
        return charityId;
    }

    /**
     * Sets the value of the charityId property.
     * 
     */
    public void setCharityId(long value) {
        this.charityId = value;
    }

    /**
     * Gets the value of the paymentId property.
     * 
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     */
    public void setPaymentId(long value) {
        this.paymentId = value;
    }

    /**
     * Gets the value of the calculatioDetailsId property.
     * 
     */
    public long getCalculatioDetailsId() {
        return calculatioDetailsId;
    }

    /**
     * Sets the value of the calculatioDetailsId property.
     * 
     */
    public void setCalculatioDetailsId(long value) {
        this.calculatioDetailsId = value;
    }

    /**
     * Gets the value of the registrationFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRegistrationFeeAmount() {
        return registrationFeeAmount;
    }

    /**
     * Sets the value of the registrationFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRegistrationFeeAmount(BigDecimal value) {
        this.registrationFeeAmount = value;
    }

    /**
     * Gets the value of the registrationFeeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationFeeStatus() {
        return registrationFeeStatus;
    }

    /**
     * Sets the value of the registrationFeeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationFeeStatus(String value) {
        this.registrationFeeStatus = value;
    }

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethod(String value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the waiveRegistrationFeeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaiveRegistrationFeeIndicator() {
        return waiveRegistrationFeeIndicator;
    }

    /**
     * Sets the value of the waiveRegistrationFeeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaiveRegistrationFeeIndicator(String value) {
        this.waiveRegistrationFeeIndicator = value;
    }

    /**
     * Gets the value of the registrationFeeWaivedReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationFeeWaivedReason() {
        return registrationFeeWaivedReason;
    }

    /**
     * Sets the value of the registrationFeeWaivedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationFeeWaivedReason(String value) {
        this.registrationFeeWaivedReason = value;
    }

    /**
     * Gets the value of the registrationFeeAmendmentHistory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationFeeAmendmentHistory() {
        return registrationFeeAmendmentHistory;
    }

    /**
     * Sets the value of the registrationFeeAmendmentHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationFeeAmendmentHistory(String value) {
        this.registrationFeeAmendmentHistory = value;
    }

    /**
     * Gets the value of the transactionFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransactionFeeAmount() {
        return transactionFeeAmount;
    }

    /**
     * Sets the value of the transactionFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransactionFeeAmount(BigDecimal value) {
        this.transactionFeeAmount = value;
    }

    /**
     * Gets the value of the waiveTransactionFeeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaiveTransactionFeeIndicator() {
        return waiveTransactionFeeIndicator;
    }

    /**
     * Sets the value of the waiveTransactionFeeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaiveTransactionFeeIndicator(String value) {
        this.waiveTransactionFeeIndicator = value;
    }

    /**
     * Gets the value of the transactionFeeWaivedReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionFeeWaivedReason() {
        return transactionFeeWaivedReason;
    }

    /**
     * Sets the value of the transactionFeeWaivedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionFeeWaivedReason(String value) {
        this.transactionFeeWaivedReason = value;
    }

    /**
     * Gets the value of the transactionFeeAmendmentHistory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionFeeAmendmentHistory() {
        return transactionFeeAmendmentHistory;
    }

    /**
     * Sets the value of the transactionFeeAmendmentHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionFeeAmendmentHistory(String value) {
        this.transactionFeeAmendmentHistory = value;
    }

    /**
     * Gets the value of the regFeeEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegFeeEnabled() {
        return regFeeEnabled;
    }

    /**
     * Sets the value of the regFeeEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegFeeEnabled(String value) {
        this.regFeeEnabled = value;
    }

    /**
     * Gets the value of the txnFeeEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnFeeEnabled() {
        return txnFeeEnabled;
    }

    /**
     * Sets the value of the txnFeeEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnFeeEnabled(String value) {
        this.txnFeeEnabled = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
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
     * Gets the value of the updatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the value of the updatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedUser(String value) {
        this.updatedUser = value;
    }

    /**
     * Gets the value of the updatedDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedDateTime() {
        return updatedDateTime;
    }

    /**
     * Sets the value of the updatedDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedDateTime(XMLGregorianCalendar value) {
        this.updatedDateTime = value;
    }

    /**
     * Gets the value of the batchStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchStatus() {
        return batchStatus;
    }

    /**
     * Sets the value of the batchStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchStatus(String value) {
        this.batchStatus = value;
    }

}
