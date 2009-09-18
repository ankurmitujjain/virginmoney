package com.virginmoneygiving.cardpayment.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.virginmoneygiving.cardpayment.domain.BaseProviderResponse;
import com.virginmoneygiving.cardpayment.domain.TransactionRecord;

/**
 * Implements the CardPaymentDAO interface using JPA.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
@Transactional
public class JPACardPaymentDAOImpl implements CardPaymentDAO {

    /** Logger instance. */
    private static Logger LOGGER = Logger.getLogger(JPACardPaymentDAOImpl.class);

    /** JPA Entity Manager instance. */
    private EntityManager entityManager;

    /**
     * Dependency injection of JPA EntityManager.
     * 
     * @param entityManager the JPA EM
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        LOGGER.trace("JPA Entity Manager injected");
        this.entityManager = entityManager;
	}

    /*
     * (non-Javadoc)
     * @see com.virginmoneygiving.cardpayment.persistence.CardPaymentDAO#
     * createTransactionRecord
     * (com.virginmoneygiving.cardpayment.domain.TransactionRecord)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public TransactionRecord createTransactionRecord(TransactionRecord transactionRecord) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("createTransactionRecord() -  START");
		}

        entityManager.persist(transactionRecord);
        if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Allocated transactionNumber " + transactionRecord.toBase36String());
        }
        if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("createTransactionRecord() -  END");
		}
        return transactionRecord;
    }

    /*
     * (non-Javadoc)
     * @see com.virginmoneygiving.cardpayment.persistence.CardPaymentDAO#
     * persistProviderResponse
     * (com.virginmoneygiving.cardpayment.domain.BaseProviderResponse)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BaseProviderResponse persistProviderResponse(BaseProviderResponse providerResponse) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("persistProviderResponse() -  START");
		}

        entityManager.persist(providerResponse);
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("persistProviderResponse(BaseProviderResponse) - END");
		}
		return providerResponse;
    }
}
