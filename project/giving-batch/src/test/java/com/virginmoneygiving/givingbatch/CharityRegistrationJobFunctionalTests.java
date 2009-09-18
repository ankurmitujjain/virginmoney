package com.virginmoneygiving.givingbatch;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.SimpleJobRepository;

public class CharityRegistrationJobFunctionalTests extends
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
