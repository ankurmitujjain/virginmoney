
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="CardToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SettlementID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateToPerformRefund" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AuthCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cardToken",
    "settlementID",
    "amount",
    "transactionNumber",
    "dateToPerformRefund",
    "authCode",
    "sid",
    "customerReference",
    "note"
})
@XmlRootElement(name = "RefundExistingCard")
public class RefundExistingCard {

    @XmlElement(name = "LoginToken")
    protected String loginToken;
    @XmlElement(name = "CardToken")
    protected String cardToken;
    @XmlElement(name = "SettlementID")
    protected int settlementID;
    @XmlElement(name = "Amount")
    protected String amount;
    @XmlElement(name = "TransactionNumber")
    protected String transactionNumber;
    @XmlElement(name = "DateToPerformRefund", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateToPerformRefund;
    @XmlElement(name = "AuthCode")
    protected String authCode;
    @XmlElement(name = "SID")
    protected String sid;
    @XmlElement(name = "CustomerReference")
    protected String customerReference;
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
     * Gets the value of the settlementID property.
     * 
     */
    public int getSettlementID() {
        return settlementID;
    }

    /**
     * Sets the value of the settlementID property.
     * 
     */
    public void setSettlementID(int value) {
        this.settlementID = value;
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
     * Gets the value of the dateToPerformRefund property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateToPerformRefund() {
        return dateToPerformRefund;
    }

    /**
     * Sets the value of the dateToPerformRefund property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateToPerformRefund(XMLGregorianCalendar value) {
        this.dateToPerformRefund = value;
    }

    /**
     * Gets the value of the authCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Sets the value of the authCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthCode(String value) {
        this.authCode = value;
    }

    /**
     * Gets the value of the sid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSID() {
        return sid;
    }

    /**
     * Sets the value of the sid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSID(String value) {
        this.sid = value;
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
