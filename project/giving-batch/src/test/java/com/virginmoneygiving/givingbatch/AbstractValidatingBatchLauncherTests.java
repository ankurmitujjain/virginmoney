package com.virginmoneygiving.givingbatch;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Abstract TestCase that automatically starts a Spring (@link Lifecycle) after
 * obtaining it automatically via autowiring by type. It should be noted the
 * getConfigLocations must be implemented for dependency injection to work
 * properly.
 * 
 * @author Lucas Ward
 * @see AbstractDependencyInjectionSpringContextTests
 */
public abstract class AbstractValidatingBatchLauncherTests extends
        AbstractBatchLauncherTests {

    /**
     * 
     * @throws Exception
     */
    public void testLaunchJob() throws Exception {
        validatePreConditions();
        super.testLaunchJob();
        validatePostConditions();
    }

    /**
     * Make sure input data meets expectations
     */
    protected void validatePreConditions() throws Exception {
    }

    /**
     * Make sure job did what it was expected to do.
     */
    protected abstract void validatePostConditions() throws Exception;

}
