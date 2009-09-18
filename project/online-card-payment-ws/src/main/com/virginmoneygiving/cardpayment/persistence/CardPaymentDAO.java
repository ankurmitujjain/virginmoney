package com.virginmoneygiving.cardpayment.persistence;

import com.virginmoneygiving.cardpayment.domain.BaseProviderResponse;
import com.virginmoneygiving.cardpayment.domain.TransactionRecord;

/**
 * Card Payment persistence.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface CardPaymentDAO {

    /**
     * Persist a transaction record - which is a glorified sequence.
     * 
     * @param transactionRecord the transaction record
     * 
     * @return the transaction record
     */
    public TransactionRecord createTransactionRecord(TransactionRecord transactionRecord);

    /**
     * Save the response.
     * 
     * @param providerResponse the provider response
     * 
     * @return the base provider response
     */
    public BaseProviderResponse persistProviderResponse(BaseProviderResponse providerResponse);

}
