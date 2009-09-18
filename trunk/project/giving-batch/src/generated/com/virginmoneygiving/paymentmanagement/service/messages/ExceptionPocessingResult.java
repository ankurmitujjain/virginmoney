
package com.virginmoneygiving.paymentmanagement.service.messages;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ExceptionPocessingResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExceptionPocessingResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="transactionAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentTarget" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionPocessingResult", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "transactionDate",
    "transactionAmount",
    "transactionId",
    "paymentReference",
    "paymentSource",
    "paymentTarget"
})
public class ExceptionPocessingResult {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionDate;
    @XmlElement(required = true)
    protected BigDecimal transactionAmount;
    protected long transactionId;
    @XmlElement(required = true)
    protected String paymentReference;
    @XmlElement(required = true)
    protected String paymentSource;
    @XmlElement(required = true)
    protected String paymentTarget;

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionDate(XMLGregorianCalendar value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the transactionAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the value of the transactionAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransactionAmount(BigDecimal value) {
        this.transactionAmount = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     */
    public long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     */
    public void setTransactionId(long value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the paymentReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * Sets the value of the paymentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentReference(String value) {
        this.paymentReference = value;
    }

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

}
