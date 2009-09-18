
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secure-payment-processing.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="MerchantNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValidationPassed" type="{http://secure-payment-processing.com/}ValidationPassedEnum"/>
 *         &lt;element name="AuthorisedAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Acquirer" type="{http://secure-payment-processing.com/}Acquirer" minOccurs="0"/>
 *         &lt;element name="Bank" type="{http://secure-payment-processing.com/}Bank" minOccurs="0"/>
 *         &lt;element name="TransactionStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationResponse", propOrder = {
    "merchantNumber",
    "validationPassed",
    "authorisedAmount",
    "acquirer",
    "bank",
    "transactionStartTime"
})
public class ValidationResponse
    extends Response
{

    @XmlElement(name = "MerchantNumber")
    protected String merchantNumber;
    @XmlElement(name = "ValidationPassed", required = true)
    protected ValidationPassedEnum validationPassed;
    @XmlElement(name = "AuthorisedAmount")
    protected String authorisedAmount;
    @XmlElement(name = "Acquirer")
    protected Acquirer acquirer;
    @XmlElement(name = "Bank")
    protected Bank bank;
    @XmlElement(name = "TransactionStartTime")
    protected String transactionStartTime;

    /**
     * Gets the value of the merchantNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantNumber() {
        return merchantNumber;
    }

    /**
     * Sets the value of the merchantNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantNumber(String value) {
        this.merchantNumber = value;
    }

    /**
     * Gets the value of the validationPassed property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationPassedEnum }
     *     
     */
    public ValidationPassedEnum getValidationPassed() {
        return validationPassed;
    }

    /**
     * Sets the value of the validationPassed property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationPassedEnum }
     *     
     */
    public void setValidationPassed(ValidationPassedEnum value) {
        this.validationPassed = value;
    }

    /**
     * Gets the value of the authorisedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorisedAmount() {
        return authorisedAmount;
    }

    /**
     * Sets the value of the authorisedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorisedAmount(String value) {
        this.authorisedAmount = value;
    }

    /**
     * Gets the value of the acquirer property.
     * 
     * @return
     *     possible object is
     *     {@link Acquirer }
     *     
     */
    public Acquirer getAcquirer() {
        return acquirer;
    }

    /**
     * Sets the value of the acquirer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Acquirer }
     *     
     */
    public void setAcquirer(Acquirer value) {
        this.acquirer = value;
    }

    /**
     * Gets the value of the bank property.
     * 
     * @return
     *     possible object is
     *     {@link Bank }
     *     
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Sets the value of the bank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bank }
     *     
     */
    public void setBank(Bank value) {
        this.bank = value;
    }

    /**
     * Gets the value of the transactionStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionStartTime() {
        return transactionStartTime;
    }

    /**
     * Sets the value of the transactionStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionStartTime(String value) {
        this.transactionStartTime = value;
    }

}
