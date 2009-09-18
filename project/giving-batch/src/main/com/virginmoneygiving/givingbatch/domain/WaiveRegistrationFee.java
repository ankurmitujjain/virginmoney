package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * This class represents the Registration Fee Object.
 * 
 * @author taruna
 */
public class WaiveRegistrationFee implements Serializable{

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** Credit Note Number. * */
    private String creditNoteNumber;
    
    /** Charity Reference. * */
    private String charityReference;
    
    /** Charity registration fee details. * */
    private PaymentType feeDetails;
    
    /** Charity registration fee VAT details. * */
    private TaxType vatDetails;
    
    /** Event reference. * */
    private Integer eventRef;
    
    /** Invoice number. * */
    private String invoiceNumber;
    
    /** Bank account unique id. * */
    private String bankAccountUniqueId;

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
     * Gets the fee details.
     * 
     * @return the feeDetails
     */
    public PaymentType getFeeDetails() {
        return feeDetails;
    }

    /**
     * Sets the fee details.
     * 
     * @param feeDetails the feeDetails to set
     */
    public void setFeeDetails(PaymentType feeDetails) {
        this.feeDetails = feeDetails;
    }

    /**
     * Gets the vat details.
     * 
     * @return the vatDetails
     */
    public TaxType getVatDetails() {
        return vatDetails;
    }

    /**
     * Sets the vat details.
     * 
     * @param vatDetails the vatDetails to set
     */
    public void setVatDetails(TaxType vatDetails) {
        this.vatDetails = vatDetails;
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
