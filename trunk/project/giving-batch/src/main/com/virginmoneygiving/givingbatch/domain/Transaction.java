package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * The data object representing Gift Aid Claimed.
 * 
 * @author taruna
 */
public class Transaction implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Amount. * */
    private BigDecimal amount;

    /** Date of claimed. * */
    private Date date;

    /** Customer. * */
    private String customer;

    /** Claimed Period From date. * */
    private Date claimPeriodFrom;

    /** Claimed Period To date. * */
    private Date claimPeriodTo;

    /** Transaction type. * */
    private String transactionType;

    /** Transaction Status. * */
    private String transactionStatus;

    /** Invoice Number. * */
    private String invoiceNumber;

    /** Event reference. * */
    private Integer eventRef;

    /** Bank Account Unique Id. * */
    private String bankAccountUniqueId;

    /** Consolidation invoice number. */
    private String groupInvoiceNumber;

    /**
     * Gets the amount.
     * 
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     * 
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the date.
     * 
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     * 
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the customer.
     * 
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     * 
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Gets the claim period from.
     * 
     * @return the claimPeriodFrom
     */
    public Date getClaimPeriodFrom() {
        return claimPeriodFrom;
    }

    /**
     * Sets the claim period from.
     * 
     * @param claimPeriodFrom the claimPeriodFrom to set
     */
    public void setClaimPeriodFrom(Date claimPeriodFrom) {
        this.claimPeriodFrom = claimPeriodFrom;
    }

    /**
     * Gets the claim period to.
     * 
     * @return the claimPeriodTo
     */
    public Date getClaimPeriodTo() {
        return claimPeriodTo;
    }

    /**
     * Sets the claim period to.
     * 
     * @param claimPeriodTo the claimPeriodTo to set
     */
    public void setClaimPeriodTo(Date claimPeriodTo) {
        this.claimPeriodTo = claimPeriodTo;
    }

    /**
     * Gets the transaction type.
     * 
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the transaction type.
     * 
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the transaction status.
     * 
     * @return the transactionStatus
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Sets the transaction status.
     * 
     * @param transactionStatus the transactionStatus to set
     */
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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
     * Gets the groupInvoiceNumber property.
     * 
     * @return String value
     */
    public String getGroupInvoiceNumber() {
        return groupInvoiceNumber;
    }

    /**
     * Sets the groupInvoiceNumber property.
     * 
     * @param groupInvoiceNumber value to set
     */
    public void setGroupInvoiceNumber(String groupInvoiceNumber) {
        this.groupInvoiceNumber = groupInvoiceNumber;
    }
}
