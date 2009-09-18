
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for WaiveRegistrationFee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WaiveRegistrationFee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditNoteNumber" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="charityReference" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="FeeDetails" type="{http://www.virginmoney.com/type/glis/2009/01}PaymentType"/>
 *         &lt;element name="VatDetails" type="{http://www.virginmoney.com/type/glis/2009/01}TaxType"/>
 *         &lt;element name="eventRef" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="invoiceNumber" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="bankAccountUniqueId" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WaiveRegistrationFee", propOrder = {
    "creditNoteNumber",
    "charityReference",
    "feeDetails",
    "vatDetails",
    "eventRef",
    "invoiceNumber",
    "bankAccountUniqueId"
})
public class WaiveRegistrationFee {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String creditNoteNumber;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String charityReference;
    @XmlElement(name = "FeeDetails", required = true)
    protected PaymentType feeDetails;
    @XmlElement(name = "VatDetails", required = true)
    protected TaxType vatDetails;
    protected Integer eventRef;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String invoiceNumber;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String bankAccountUniqueId;

    /**
     * Gets the value of the creditNoteNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditNoteNumber() {
        return creditNoteNumber;
    }

    /**
     * Sets the value of the creditNoteNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditNoteNumber(String value) {
        this.creditNoteNumber = value;
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
     * Gets the value of the feeDetails property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentType }
     *     
     */
    public PaymentType getFeeDetails() {
        return feeDetails;
    }

    /**
     * Sets the value of the feeDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentType }
     *     
     */
    public void setFeeDetails(PaymentType value) {
        this.feeDetails = value;
    }

    /**
     * Gets the value of the vatDetails property.
     * 
     * @return
     *     possible object is
     *     {@link TaxType }
     *     
     */
    public TaxType getVatDetails() {
        return vatDetails;
    }

    /**
     * Sets the value of the vatDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxType }
     *     
     */
    public void setVatDetails(TaxType value) {
        this.vatDetails = value;
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

}
