
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SettledCharityPayments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettledCharityPayments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceOrCrNoteNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="paymentAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettledCharityPayments", propOrder = {
    "invoiceOrCrNoteNumber",
    "paymentAmount"
})
public class SettledCharityPayments {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String invoiceOrCrNoteNumber;
    protected double paymentAmount;

    /**
     * Gets the value of the invoiceOrCrNoteNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceOrCrNoteNumber() {
        return invoiceOrCrNoteNumber;
    }

    /**
     * Sets the value of the invoiceOrCrNoteNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceOrCrNoteNumber(String value) {
        this.invoiceOrCrNoteNumber = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     * 
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     * 
     */
    public void setPaymentAmount(double value) {
        this.paymentAmount = value;
    }

}
