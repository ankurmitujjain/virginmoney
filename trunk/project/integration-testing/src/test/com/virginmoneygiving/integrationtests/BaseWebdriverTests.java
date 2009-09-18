package com.virginmoneygiving.integrationtests;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Base class that includes functionality for webdriver based test cases.
 * <br/>
 * All webdriver test case classes should extend from this class.
 * 
 * @author vishals
 */
public class BaseWebdriverTests extends AbstractDependencyInjectionSpringContextTests {  
	
	// TODO : Can we load them once?
    /** Define the spring config files here. */
    static final String[] CONFIG_LOCATIONS = new String[] {
        "/com/virginmoneygiving/integrationtests/spring/applicationContext-test.xml",
        "/com/virginmoneygiving/integrationtests/spring/applicationContext-charity-test-data.xml",
        "/com/virginmoneygiving/integrationtests/spring/applicationContext-event-test-data.xml",
        "/com/virginmoneygiving/integrationtests/spring/applicationContext-fundraiser-test-data.xml",
        "/com/virginmoneygiving/integrationtests/spring/applicationContext-operations-test-data.xml",
        "/com/virginmoneygiving/integrationtests/spring/applicationContext-donor-test-data.xml",
        "/com/virginmoneygiving/integrationtests/spring/propertyFileConfigurer.xml"
    };

    /* (non-Javadoc)
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations() {
      return CONFIG_LOCATIONS;
    }
    
}
