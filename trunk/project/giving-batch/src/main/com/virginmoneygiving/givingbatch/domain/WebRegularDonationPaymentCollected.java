package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing Regular Payment Collected
 * details.
 * 
 * @author Tarun Adiwal.
 */
public class WebRegularDonationPaymentCollected implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Invoice Number for Web Regular Donation. * */
    private String invoiceNumber;

    /** Charity Reference. */
    private String charityReference;

    /** PaymentType. * */
    private DonationPayment regularDonationType;

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
     * Gets the regular donation type.
     * 
     * @return the regularDonationType
     */
    public DonationPayment getRegularDonationType() {
        return regularDonationType;
    }

    /**
     * Sets the regular donation type.
     * 
     * @param regularDonationType the regularDonationType to set
     */
    public void setRegularDonationType(DonationPayment regularDonationType) {
        this.regularDonationType = regularDonationType;
    }

}
