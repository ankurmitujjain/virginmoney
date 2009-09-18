/**
 *
 */
package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The data object representing Payment type details.
 * 
 * @author taruna
 */
public class PaymentType implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** amount. * */
    private BigDecimal amount;

    /** Transaction Type. * */
    private String transactionType;

    /** Transaction Status. * */
    private String transactionStatus;

    /** Transaction Date. * */
    private Date transactionDate;

    /** Reference Id. * */
    private Long referenceId;

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
     * Gets the transaction date.
     * 
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date.
     * 
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the reference id.
     * 
     * @return the referenceId
     */
    public Long getReferenceId() {
        return referenceId;
    }

    /**
     * Sets the reference id.
     * 
     * @param referenceId the referenceId to set
     */
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

}
