package com.opsera.junit4;

import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * JUnit runner which runs all @AfterFailure methods in a test class.
 * Note: requires Junit 4.5. We also have to have junit 4.4 as the Spring 
 * intgration test framework doesn't work with 4.5 in Spring 2.5.
 * As a result, make sure that the junit 4.5 library is on the classpath
 * <b>after</b> the junit 4.4 library or the integration tests that run with
 * the Spring junit test runner won't work.
 * 
 * Note: No credit me - it all belongs to 
 * https://dev.youdevise.com/YDBlog/index.php?title=capture_screenshots_of_selenium_browser_&more=1&c=1&tb=1&pb=1
 * 
 * @author ian.priest@opsera.com
 */
public class AfterFailureRunner extends BlockJUnit4ClassRunner {

    /**
     * @param klass Test class
     * @throws InitializationError
     *             if the test class is malformed.
     */
    public AfterFailureRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * Override withAfters() so we can append to the statement which will invoke the test
     * method. We don't override methodBlock() because we wont be able to reference 
     * the target object. 
     * 
     * @author ipriest Switched to call @AfterFailure methods before @After methods to
     *              avoid problems with teardown methods closing the selenium session
     */
    @Override
    protected Statement withAfters(FrameworkMethod method, Object target, 
                                   Statement statement) {
//        statement = super.withAfters(method, target, statement);
//        return withAfterFailures(method, target, statement);
        statement = withAfterFailures(method, target, statement);
        return super.withAfters(method, target, statement);
    }

    protected Statement withAfterFailures(FrameworkMethod method, Object target, 
                                          Statement statement) {
        List<FrameworkMethod> failures =
            getTestClass().getAnnotatedMethods(AfterFailure.class);
        return new RunAfterFailures(statement, failures, target);
    }
}
