package com.virginmoneygiving.cardpayment.business;

import java.math.BigDecimal;

/**
 * The Class PaymentDetails.
 */
public class PaymentDetails {

    /** The currency code. */
    protected String currencyCode;
    
    /** The amount. */
    protected BigDecimal amount;
    
    /** The payment reference. */
    protected String paymentRef;
    
    /** The payment type. */
    protected PaymentTypeEnum paymentType;

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return possible object is {@link String }
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return possible object is {@link BigDecimal }
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value allowed object is {@link BigDecimal }
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the payment reference property.
     * 
     * @return possible object is {@link String }
     */
    public String getPaymentRef() {
        return paymentRef;
    }

    /**
     * Sets the value of the payment reference property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setPaymentRef(String value) {
        this.paymentRef = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return possible object is {@link PaymentTypeEnum }
     */
    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value allowed object is {@link PaymentTypeEnum }
     */
    public void setPaymentType(PaymentTypeEnum value) {
        this.paymentType = value;
    }

}
