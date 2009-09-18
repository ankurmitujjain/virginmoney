
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Transaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Transaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Source" type="{http://secure-payment-processing.com/}TransactionSourceEnum"/>
 *         &lt;element name="Security" type="{http://secure-payment-processing.com/}TransactionSecurityEnum"/>
 *         &lt;element name="Customer" type="{http://secure-payment-processing.com/}TransactionCustomerEnum"/>
 *         &lt;element name="Type" type="{http://secure-payment-processing.com/}TransactionTypeEnum"/>
 *         &lt;element name="AuthorisationDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Verify" type="{http://secure-payment-processing.com/}VerifyEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transaction", propOrder = {
    "amount",
    "description",
    "source",
    "security",
    "customer",
    "type",
    "authorisationDateTime",
    "transactionStartTime",
    "verify"
})
public class Transaction {

    @XmlElement(name = "Amount")
    protected String amount;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Source", required = true)
    protected TransactionSourceEnum source;
    @XmlElement(name = "Security", required = true)
    protected TransactionSecurityEnum security;
    @XmlElement(name = "Customer", required = true)
    protected TransactionCustomerEnum customer;
    @XmlElement(name = "Type", required = true)
    protected TransactionTypeEnum type;
    @XmlElement(name = "AuthorisationDateTime")
    protected String authorisationDateTime;
    @XmlElement(name = "TransactionStartTime")
    protected String transactionStartTime;
    @XmlElement(name = "Verify", required = true)
    protected VerifyEnum verify;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionSourceEnum }
     *     
     */
    public TransactionSourceEnum getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionSourceEnum }
     *     
     */
    public void setSource(TransactionSourceEnum value) {
        this.source = value;
    }

    /**
     * Gets the value of the security property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionSecurityEnum }
     *     
     */
    public TransactionSecurityEnum getSecurity() {
        return security;
    }

    /**
     * Sets the value of the security property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionSecurityEnum }
     *     
     */
    public void setSecurity(TransactionSecurityEnum value) {
        this.security = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionCustomerEnum }
     *     
     */
    public TransactionCustomerEnum getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionCustomerEnum }
     *     
     */
    public void setCustomer(TransactionCustomerEnum value) {
        this.customer = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionTypeEnum }
     *     
     */
    public TransactionTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionTypeEnum }
     *     
     */
    public void setType(TransactionTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the authorisationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorisationDateTime() {
        return authorisationDateTime;
    }

    /**
     * Sets the value of the authorisationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorisationDateTime(String value) {
        this.authorisationDateTime = value;
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

    /**
     * Gets the value of the verify property.
     * 
     * @return
     *     possible object is
     *     {@link VerifyEnum }
     *     
     */
    public VerifyEnum getVerify() {
        return verify;
    }

    /**
     * Sets the value of the verify property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerifyEnum }
     *     
     */
    public void setVerify(VerifyEnum value) {
        this.verify = value;
    }

}
