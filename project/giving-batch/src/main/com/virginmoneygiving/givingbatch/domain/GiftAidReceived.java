package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing Gift Aid Received details.
 * 
 * @author taruna
 */
public class GiftAidReceived implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Invoice Number. * */
    private String invoiceNumber;

    /** Charity Reference. * */
    private String charityReference;

    /** Amount details. * */
    private PaymentType amountDetails;

    /** Bank Account Unique ID. * */
    private String bankAccountUniqueId;

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
    public PaymentType getAmountDetails() {
        return amountDetails;
    }

    /**
     * Sets the amount details.
     * 
     * @param amountDetails the amountDetails to set
     */
    public void setAmountDetails(PaymentType amountDetails) {
        this.amountDetails = amountDetails;
    }

}
