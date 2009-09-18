package com.virginmoneygiving.givingbatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * @author taruna
 */
public class PaymentInitiatedJobFunctionalTests extends
        AbstractValidatingBatchLauncherTests {

    /**
     * Create the directory and some files in it.
     * 
     * @throws Exception
     */
    protected void onSetUp() throws Exception {

    }

    /**
     * We have directory with some files in it.
     * 
     * @throws Exception
     */
    protected void validatePreConditions() throws Exception {
    }

    /**
     * Directory still exists but contains no files.
     * 
     * @throws Exception
     */
    protected void validatePostConditions() throws Exception {
    }

}
