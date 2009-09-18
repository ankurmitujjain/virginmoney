
package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * Domain Java class for TransitionalReliefReceived complex type.
 */
public class TransitionalReliefReceived implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4250056810213705924L;

	/** The invoice number. */
	protected String invoiceNumber;
    
    /** The charity reference. */
    protected String charityReference;
    
    /** The amount details. */
    protected PaymentType amountDetails;
    
    /** The bank account unique id. */
    protected String bankAccountUniqueId;
    
    /** The event ref. */
    protected int eventRef;

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
     * Gets the value of the amountDetails property.
     * 
     * @return the amount details
     * 
     * possible object is
     * {@link PaymentType }
     */
    public PaymentType getAmountDetails() {
        return amountDetails;
    }

    /**
     * Sets the value of the amountDetails property.
     * 
     * @param value allowed object is
     * {@link PaymentType }
     */
    public void setAmountDetails(PaymentType value) {
        this.amountDetails = value;
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
     * Gets the value of the eventRef property.
     * 
     * @return the event ref
     */
    public int getEventRef() {
        return eventRef;
    }

    /**
     * Sets the value of the eventRef property.
     * 
     * @param value the value
     */
    public void setEventRef(int value) {
        this.eventRef = value;
    }

}
