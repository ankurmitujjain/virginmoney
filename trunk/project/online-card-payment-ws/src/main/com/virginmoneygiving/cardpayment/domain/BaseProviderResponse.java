package com.virginmoneygiving.cardpayment.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Base provider response class Linked to requesting process GUID by FK to
 * TransactionRecord JPA will add a default discriminator - DTYPE varchar(31) <br/>
 * <br/>
 * 2009/02/16 - changed visibility to protected to see if it fixes <br/>
 * WARN [org.hibernate.tuple.entity.PojoEntityTuplizer] could not create proxy
 * factory
 * for:com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse
 * org.hibernate.HibernateException: Javassist Enhancement failed:
 * com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "PROVIDER_RESPONSE")
public abstract class BaseProviderResponse {

    /** surrogate primary key - allocated by auto-increment. */
    protected Long id = null;

    /** response datetime created. */
    protected Date createdDatetime;

    /** Result code from the provider. */
    protected String resultCode;
    
    /** Any error message from the provider. */
    protected String errorMessage;
    
    /** Provider specific error type. */
    protected String errorType;
    
    /** Card token to be used in place of PAN. */
    protected String cardToken;
    
    /** The acquirer ID. */
    protected String acquirerId;
    
    /** The acquirer name. */
    protected String acquirerName;

    /** Link to the request (FK Many to One). */
    protected TransactionRecord requestTransaction;

    /** Raw response data (flattened to nvp string). */
    protected String rawResponse;

    /** Response code from the provider. */
    protected String responseCode;

    /** Response result from the provider. */
    protected String responseResult;

    /**
     * Gets the id.
     * 
     * @return the id
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
    @Column(name = "CREATED_DATETIME", nullable = false, updatable = false)
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
     * Gets the result code.
     * 
     * @return the resultCode
     */
    @Column(name = "RESULT_CODE", updatable = false, length = 5)
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the result code.
     * 
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Gets the error message.
     * 
     * @return the errorMessage
     */
    @Column(name = "ERROR_MESSAGE", updatable = false)
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * 
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error type.
     * 
     * @return the errorType
     */
    @Column(name = "ERROR_TYPE", updatable = false, length = 30)
    public String getErrorType() {
        return errorType;
    }

    /**
     * Sets the error type.
     * 
     * @param errorType the errorType to set
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * Gets the card token.
     * 
     * @return the cardToken
     */
    @Column(name = "CARD_TOKEN", updatable = false, length = 80)
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Sets the card token.
     * 
     * @param cardToken the cardToken to set
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    /**
     * Gets the acquirer id.
     * 
     * @return the acquirerId
     */
    @Column(name = "ACQUIRER_ID", length = 1)
    public String getAcquirerId() {
        return acquirerId;
    }

    /**
     * Sets the acquirer id.
     * 
     * @param acquirerId the acquirerId to set
     */
    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    /**
     * Gets the acquirer name.
     * 
     * @return the acquirerName
     */
    @Column(name = "ACQUIRER_NAME")
    public String getAcquirerName() {
        return acquirerName;
    }

    /**
     * Sets the acquirer name.
     * 
     * @param acquirerName the acquirerName to set
     */
    public void setAcquirerName(String acquirerName) {
        this.acquirerName = acquirerName;
    }

    /**
     * Gets the request transaction.
     * 
     * @return the requestTransaction
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "TRANSACTION_RECORD_ID", nullable = false, updatable = false)
    public TransactionRecord getRequestTransaction() {
        return requestTransaction;
    }

    /**
     * Sets the request transaction.
     * 
     * @param requestTransaction the requestTransaction to set
     */
    public void setRequestTransaction(TransactionRecord requestTransaction) {
        this.requestTransaction = requestTransaction;
    }

    /**
     * Gets the raw response.
     * 
     * @return the rawResponse
     */
    @Lob
    @Column(name = "RAW_RESPONSE", nullable = false, updatable = false)
    public String getRawResponse() {
        return rawResponse;
    }

    /**
     * Sets the raw response.
     * 
     * @param rawResponse the rawResponse to set
     */
    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    /**
     * Set the created datetime before persist.
     */
    @PrePersist
    protected void allocateDate() {
        setCreatedDatetime(new Date());
    }

    /**
     * Gets the response code.
     * 
     * @return the responseCode
     */
    @Column(name = "RESPONSE_CODE", updatable = false, length = 2)
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the response code.
     * 
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Gets the response result.
     * 
     * @return the responseResult
     */
    @Column(name = "RESPONSE_RESULT", updatable = false, length = 40)
    public String getResponseResult() {
        return responseResult;
    }

    /**
     * Sets the response result.
     * 
     * @param responseResult the responseResult to set
     */
    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }
}
