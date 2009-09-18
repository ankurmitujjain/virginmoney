package com.virginmoneygiving.cardpayment.persistence;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.virginmoneygiving.cardpayment.domain.BaseProviderResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderAuthorisationResponse;
import com.virginmoneygiving.cardpayment.domain.ProviderValidationResponse;
import com.virginmoneygiving.cardpayment.domain.TransactionRecord;

/**
 * The Class TestJPACardPaymentDAOImpl.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestJPACardPaymentDAOImpl {

    /** The bean. */
    private JPACardPaymentDAOImpl bean;
    
    /** The entity manager. */
    @Mock
    private EntityManager entityManager;

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new JPACardPaymentDAOImpl();
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tear down.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.virginmoneygiving.cardpayment.persistence.JPACardPaymentDAOImpl#createTransactionRecord(com.virginmoneygiving.cardpayment.domain.TransactionRecord)}
     * .
     * 
     * @throws Exception the exception
     */
    @Test
    public void testCreateTransactionRecord() throws Exception {
        bean.setEntityManager(entityManager);
        TransactionRecord tx = new TransactionRecord();
        TransactionRecord tx2 = bean.createTransactionRecord(tx);
        assertNotNull(tx2);
        verify(entityManager).persist(isA(TransactionRecord.class));
    }

    /**
     * Test persist provider validation response.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testPersistProviderValidationResponse() throws Exception {
        bean.setEntityManager(entityManager);
        ProviderValidationResponse providerResponse = new ProviderValidationResponse();
        BaseProviderResponse pr2 = bean.persistProviderResponse(providerResponse);
        assertNotNull(pr2);
        verify(entityManager).persist(isA(BaseProviderResponse.class));
    }

    /**
     * Test persist provider authorisation response.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testPersistProviderAuthorisationResponse() throws Exception {
        bean.setEntityManager(entityManager);
        ProviderAuthorisationResponse providerResponse = new ProviderAuthorisationResponse();
        BaseProviderResponse pr2 = bean.persistProviderResponse(providerResponse);
        assertNotNull(pr2);
        verify(entityManager).persist(isA(BaseProviderResponse.class));
    }

}
