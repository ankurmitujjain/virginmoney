
package com.virginmoneygiving.paymentmanagement.service.messages;

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
 *         &lt;element name="paymentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentReceiveSelection" type="{http://www.virginmoneygiving.com/type/payment-management/operations/}PaymentReceiveSelection"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
    "paymentStatus",
    "paymentReceiveSelection",
    "transactionDate"
})
@XmlRootElement(name = "fetchPaymentReceivedTransactionDetailsRequest", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
public class FetchPaymentReceivedTransactionDetailsRequest {

    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected String paymentStatus;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    protected PaymentReceiveSelection paymentReceiveSelection;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionDate;

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
    }

    /**
     * Gets the value of the paymentReceiveSelection property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentReceiveSelection }
     *     
     */
    public PaymentReceiveSelection getPaymentReceiveSelection() {
        return paymentReceiveSelection;
    }

    /**
     * Sets the value of the paymentReceiveSelection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentReceiveSelection }
     *     
     */
    public void setPaymentReceiveSelection(PaymentReceiveSelection value) {
        this.paymentReceiveSelection = value;
    }

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

}
