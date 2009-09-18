
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for BACPaymentCollected complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BACPaymentCollected">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="charityReference" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="amountDetails" type="{http://www.virginmoney.com/type/glis/2009/01}PaymentType"/>
 *         &lt;element name="bankAccountUniqueId" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="eventRef" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BACPaymentCollected", propOrder = {
    "invoiceNumber",
    "charityReference",
    "amountDetails",
    "bankAccountUniqueId",
    "eventRef"
})
public class BACPaymentCollected {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String invoiceNumber;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String charityReference;
    @XmlElement(required = true)
    protected PaymentType amountDetails;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String bankAccountUniqueId;
    protected Integer eventRef;

    /**
     * Gets the value of the invoiceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the charityReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityReference() {
        return charityReference;
    }

    /**
     * Sets the value of the charityReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityReference(String value) {
        this.charityReference = value;
    }

    /**
     * Gets the value of the amountDetails property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentType }
     *     
     */
    public PaymentType getAmountDetails() {
        return amountDetails;
    }

    /**
     * Sets the value of the amountDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentType }
     *     
     */
    public void setAmountDetails(PaymentType value) {
        this.amountDetails = value;
    }

    /**
     * Gets the value of the bankAccountUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountUniqueId() {
        return bankAccountUniqueId;
    }

    /**
     * Sets the value of the bankAccountUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountUniqueId(String value) {
        this.bankAccountUniqueId = value;
    }

    /**
     * Gets the value of the eventRef property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEventRef() {
        return eventRef;
    }

    /**
     * Sets the value of the eventRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEventRef(Integer value) {
        this.eventRef = value;
    }

}
