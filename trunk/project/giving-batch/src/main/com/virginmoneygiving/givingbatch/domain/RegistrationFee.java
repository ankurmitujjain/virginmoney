
package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * Domain version of RegistrationFee service object.
 * 
 * @author ipriest
 */
public class RegistrationFee implements Serializable {

    /** Serial UID. */
    private static final long serialVersionUID = -1883372015171046166L;

    /** Invoice number. */
    private String invoiceNumber;
    
    /** Invoice number. */
    private String creditNoteNumber;
    
    /** Charity Ref. */
    private String charityReference;
    
    /** Fee details. */
    private PaymentType feeDetails;
    
    /** Vat Details. */
    private TaxType vatDetails;
    
    /** Event Ref. */
    private Integer eventRef;
    
    /** Bank account unique id. */
    private String bankAccountUniqueId;

    /**
     * Gets the value of the invoiceNumber property.
     * 
     * @return the invoice number
     * 
     * possible object is
     * {@link String }
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the charityReference property.
     * 
     * @return the charity reference
     * 
     * possible object is
     * {@link String }
     */
    public String getCharityReference() {
        return charityReference;
    }

    /**
     * Sets the value of the charityReference property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setCharityReference(String value) {
        this.charityReference = value;
    }

    /**
     * Gets the value of the feeDetails property.
     * 
     * @return the fee details
     * 
     * possible object is
     * {@link PaymentType }
     */
    public PaymentType getFeeDetails() {
        return feeDetails;
    }

    /**
     * Sets the value of the feeDetails property.
     * 
     * @param value allowed object is
     * {@link PaymentType }
     */
    public void setFeeDetails(PaymentType value) {
        this.feeDetails = value;
    }

    /**
     * Gets the value of the vatDetails property.
     * 
     * @return the vat details
     * 
     * possible object is
     * {@link TaxType }
     */
    public TaxType getVatDetails() {
        return vatDetails;
    }

    /**
     * Sets the value of the vatDetails property.
     * 
     * @param value allowed object is
     * {@link TaxType }
     */
    public void setVatDetails(TaxType value) {
        this.vatDetails = value;
    }

    /**
     * Gets the value of the eventRef property.
     * 
     * @return the event ref
     * 
     * possible object is
     * {@link Integer }
     */
    public Integer getEventRef() {
        return eventRef;
    }

    /**
     * Sets the value of the eventRef property.
     * 
     * @param value allowed object is
     * {@link Integer }
     */
    public void setEventRef(Integer value) {
        this.eventRef = value;
    }

    /**
     * Gets the value of the bankAccountUniqueId property.
     * 
     * @return the bank account unique id
     * 
     * possible object is
     * {@link String }
     */
    public String getBankAccountUniqueId() {
        return bankAccountUniqueId;
    }

    /**
     * Sets the value of the bankAccountUniqueId property.
     * 
     * @param value allowed object is
     * {@link String }
     */
    public void setBankAccountUniqueId(String value) {
        this.bankAccountUniqueId = value;
    }

    /**
     * Gets the credit note number.
     * 
     * @return the creditNoteNumber
     */
    public String getCreditNoteNumber() {
        return creditNoteNumber;
    }

    /**
     * Sets the credit note number.
     * 
     * @param creditNoteNumber the creditNoteNumber to set
     */
    public void setCreditNoteNumber(String creditNoteNumber) {
        this.creditNoteNumber = creditNoteNumber;
    }

}
