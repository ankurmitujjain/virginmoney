package com.virginmoneygiving.cardpayment.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * The transactionRecord.id can be base-36 encoded to form TransactionNumber for
 * TLG (e.g. Long.toString(1000000L,36).toUpperCase()) TODO: add more context
 * for troubleshooting
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
@Entity
@Table(name = "TRANSACTION_RECORD")
public class TransactionRecord {

    /** Primary key - allocated by auto-increment. */
    private Long id = null;

    /** This will get set by the PrePersist. */
    private Date createdDatetime;

    /** The process GUID - ties in with audit. */
    private String processGuid;

    /** type of transaction e.g. validate | authorise */
    private String transactionType;

    /** Stores the payment invoice number. */
    private String paymentReference;

    /**
     * Default constructor.
     */
    public TransactionRecord() {
    }

    /**
     * Constructor for use by PaymentServiceProxy impls.
     *
     * @param processGuid the process guid
     * @param transactionType the transaction type
     */
    public TransactionRecord(String processGuid, String transactionType) {
        this.processGuid = processGuid;
        this.transactionType = transactionType;
        this.paymentReference = null;
    }

    /**
     * Constructor for use by PaymentServiceProxy impls.
     * 
     * @param processGuid the process guid
     * @param transactionType the transaction type
     */
    public TransactionRecord(String processGuid, String transactionType, String paymentReference) {
        this.processGuid = processGuid;
        this.transactionType = transactionType;
        this.paymentReference = paymentReference;
    }

    /**
     * Gets the id.
     * 
     * @return the id.
     */
    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the created datetime.
     * 
     * @return the createdDatetime
     */
    @Column(name = "CREATED_DATETIME", updatable = false)
    public Date getCreatedDatetime() {
        return (Date) createdDatetime.clone();
    }

    /**
     * Sets the created datetime.
     * 
     * @param createdDatetime the createdDatetime to set
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * Gets the process guid.
     * 
     * @return the processGuid
     */
    @Column(name = "PROCESS_GUID", length = 36, updatable = false)
    public String getProcessGuid() {
        return processGuid;
    }

    /**
     * Sets the process guid.
     * 
     * @param processGuid the processGuid to set
     */
    public void setProcessGuid(String processGuid) {
        this.processGuid = processGuid;
    }

    /**
     * Gets the transaction type.
     * 
     * @return the transactionType
     */
    @Column(name = "TRANSACTION_TYPE", length = 30, updatable = false)
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
     * Set the created datetime before persist.
     */
    @PrePersist
    protected void allocateDate() {
        setCreatedDatetime(new Date());
    }

    /**
     * Base36 encode the id (e.g. 1000000 -> LFLS)
     * 
     * @return the id base36 encoded
     */
    public String toBase36String() {
        if (id == null) {
            return null;
        }

        return Long.toString(id, 36).toUpperCase();
    }

    /**
     * Gets the paymentReference property.
     * @return String value
     */
    @Column(name = "PAYMENT_REFERENCE", length = 20, updatable = false)
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * Sets the paymentReference property.
     * @param paymentReference valye to set
     */
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}
