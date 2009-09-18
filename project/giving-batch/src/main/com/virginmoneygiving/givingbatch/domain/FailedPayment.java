package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;

/**
 * The data object representing Failed Payment details.
 * 
 * @author Siva Kumar
 */
public class FailedPayment implements Serializable {
    
    /** serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** cardType of type String. */
    private String cardType;
    
    /** payment type details. */
    private PaymentType paymentType;
    
    /** invoiceNumber number of type String. */
    private String invoiceNumber;
	
	/** creditNoteNumber number of type String. */
    private String creditNoteNumber;
    
    /** eventRef of type Integer. */
    private Integer eventRef;
    
    /** bankAccountUniqueId of type String. */
    private String bankAccountUniqueId;

    /**
     * Gets the card type.
     * 
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the card type.
     * 
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets the payment type.
     * 
     * @return the paymentType
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     * 
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
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
    
    /**
     * Gets the invoice number.
     * 
     * @return the invoice number
     */
    public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * Sets the invoice number.
	 * 
	 * @param invoiceNumber the new invoice number
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
}
