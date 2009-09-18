
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoginToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardExpiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardIssueNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TransactionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cv2AvsCV2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cv2AvsAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cv2AvsPostCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loginToken",
    "cardNumber",
    "cardStartDate",
    "cardExpiryDate",
    "cardIssueNumber",
    "amount",
    "sourceID",
    "authOnly",
    "transactionNumber",
    "customerReference",
    "cv2AvsCV2",
    "cv2AvsAddress",
    "cv2AvsPostCode",
    "note"
})
@XmlRootElement(name = "Authorise")
public class Authorise {

    @XmlElement(name = "LoginToken")
    protected String loginToken;
    @XmlElement(name = "CardNumber")
    protected String cardNumber;
    @XmlElement(name = "CardStartDate")
    protected String cardStartDate;
    @XmlElement(name = "CardExpiryDate")
    protected String cardExpiryDate;
    @XmlElement(name = "CardIssueNumber")
    protected String cardIssueNumber;
    @XmlElement(name = "Amount")
    protected String amount;
    @XmlElement(name = "SourceID")
    protected String sourceID;
    @XmlElement(name = "AuthOnly")
    protected boolean authOnly;
    @XmlElement(name = "TransactionNumber")
    protected String transactionNumber;
    @XmlElement(name = "CustomerReference")
    protected String customerReference;
    @XmlElement(name = "Cv2AvsCV2")
    protected String cv2AvsCV2;
    @XmlElement(name = "Cv2AvsAddress")
    protected String cv2AvsAddress;
    @XmlElement(name = "Cv2AvsPostCode")
    protected String cv2AvsPostCode;
    @XmlElement(name = "Note")
    protected String note;

    /**
     * Gets the value of the loginToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * Sets the value of the loginToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginToken(String value) {
        this.loginToken = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the cardStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardStartDate() {
        return cardStartDate;
    }

    /**
     * Sets the value of the cardStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardStartDate(String value) {
        this.cardStartDate = value;
    }

    /**
     * Gets the value of the cardExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    /**
     * Sets the value of the cardExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardExpiryDate(String value) {
        this.cardExpiryDate = value;
    }

    /**
     * Gets the value of the cardIssueNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardIssueNumber() {
        return cardIssueNumber;
    }

    /**
     * Sets the value of the cardIssueNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardIssueNumber(String value) {
        this.cardIssueNumber = value;
    }

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
     * Gets the value of the sourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceID() {
        return sourceID;
    }

    /**
     * Sets the value of the sourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceID(String value) {
        this.sourceID = value;
    }

    /**
     * Gets the value of the authOnly property.
     * 
     */
    public boolean isAuthOnly() {
        return authOnly;
    }

    /**
     * Sets the value of the authOnly property.
     * 
     */
    public void setAuthOnly(boolean value) {
        this.authOnly = value;
    }

    /**
     * Gets the value of the transactionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * Sets the value of the transactionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionNumber(String value) {
        this.transactionNumber = value;
    }

    /**
     * Gets the value of the customerReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReference() {
        return customerReference;
    }

    /**
     * Sets the value of the customerReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReference(String value) {
        this.customerReference = value;
    }

    /**
     * Gets the value of the cv2AvsCV2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCv2AvsCV2() {
        return cv2AvsCV2;
    }

    /**
     * Sets the value of the cv2AvsCV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCv2AvsCV2(String value) {
        this.cv2AvsCV2 = value;
    }

    /**
     * Gets the value of the cv2AvsAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCv2AvsAddress() {
        return cv2AvsAddress;
    }

    /**
     * Sets the value of the cv2AvsAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCv2AvsAddress(String value) {
        this.cv2AvsAddress = value;
    }

    /**
     * Gets the value of the cv2AvsPostCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCv2AvsPostCode() {
        return cv2AvsPostCode;
    }

    /**
     * Sets the value of the cv2AvsPostCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCv2AvsPostCode(String value) {
        this.cv2AvsPostCode = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

}
