package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing Payment Collection details.
 * 
 * @author taruna
 */
public class DonationPaymentCollected implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** invoiceNumber. */
    private String invoiceNumber;

    /** charityReference. */
    private String charityReference;

    /** amountDetails. */
    private DonationPayment amountDetails;

    /** eventRef. */
    private Integer eventRef;

    /** bankAccountUniqueId. */
    private String bankAccountUniqueId;

    /**
     * Gets the invoice number.
     * 
     * @return the invoiceNumber
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the invoice number.
     * 
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * Gets the charity reference.
     * 
     * @return the charityReference
     */
    public String getCharityReference() {
        return charityReference;
    }

    /**
     * Sets the charity reference.
     * 
     * @param charityReference the charityReference to set
     */
    public void setCharityReference(String charityReference) {
        this.charityReference = charityReference;
    }

    /**
     * Gets the amount details.
     * 
     * @return the amountDetails
     */
    public DonationPayment getAmountDetails() {
        return amountDetails;
    }

    /**
     * Sets the amount details.
     * 
     * @param amountDetails the amountDetails to set
     */
    public void setAmountDetails(DonationPayment amountDetails) {
        this.amountDetails = amountDetails;
    }

    /**
     * Gets the event ref.
     * 
     * @return the eventRef
     */
    public Integer getEventRef() {
        return eventRef;
    }

    /**
     * Sets the event ref.
     * 
     * @param eventRef the eventRef to set
     */
    public void setEventRef(Integer eventRef) {
        this.eventRef = eventRef;
    }

    /**
     * Gets the bank account unique id.
     * 
     * @return the bankAccountUniqueId
     */
    public String getBankAccountUniqueId() {
        return bankAccountUniqueId;
    }

    /**
     * Sets the bank account unique id.
     * 
     * @param bankAccountUniqueId the bankAccountUniqueId to set
     */
    public void setBankAccountUniqueId(String bankAccountUniqueId) {
        this.bankAccountUniqueId = bankAccountUniqueId;
    }

}
