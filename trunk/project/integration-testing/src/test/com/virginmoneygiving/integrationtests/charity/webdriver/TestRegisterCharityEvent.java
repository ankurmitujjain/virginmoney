package com.virginmoneygiving.integrationtests.charity.webdriver;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.BaseWebdriverTests;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityHelper;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.event.EventHelper;
import com.virginmoneygiving.integrationtests.webdriver.event.EventRegistrationDetails;

/**
 * Test case class for event registration by a charity administrator.
 * <br/>
 * This journey will be consist of multiple steps as below
 * <ul>
 * <li>Log on to application using charity account created by {@link TestRegisterCharity} execution.</li>
 * <li>Once on the charity portal, click create event link from menu.</li>
 * <li> Follow the the event creation steps till end for happy path. </li>
 * </ul>
 * 
 * @author vishals.
 */
public class TestRegisterCharityEvent extends BaseWebdriverTests {

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestRegisterCharityEvent.class);
    /** Event helper instance.*/
    private EventHelper eventHelper;
    /** CharityRegistrationDetails instance.*/
    private CharityRegistrationDetails charityRegDetails;
    /** EventRegistrationDetails instance.*/
    private EventRegistrationDetails eventRegistrationDetails;
    


	/*@Override
    protected void onSetUp() {

    	if (charityRegDetails == null) {
            charityRegDetails = CharityHelper.generateCharityRegistrationDetails();            
        }
        if (eventRegistrationDetails == null) {
        	eventRegistrationDetails = eventHelper.generateEventRegistrationDetails();
        }
    }
    
    @Override
    protected void onTearDown() throws Exception{
    	if(eventHelper != null) {
    		eventHelper.getWebDriver().quit();
    	}
    }*/

    @Test
    public void testCharityEventRegistration() throws Exception {
        // Trigger charity event registration flow        
    	eventHelper.registerCharityEvent(charityRegDetails, eventRegistrationDetails);
    }
    
    /**
     * Setter for {@link EventHelper}.
     * @param eventHelper EventHelper instance.
     */
    public void setEventHelper(EventHelper eventHelper) {
    	this.eventHelper = eventHelper;    	
    }    
    
    public void setEventRegistrationDetails(
			EventRegistrationDetails eventRegistrationDetails) {
		this.eventRegistrationDetails = eventRegistrationDetails;
	}

	public void setCharityRegDetails(CharityRegistrationDetails charityRegDetails) {
		this.charityRegDetails = charityRegDetails;
	}
    
    

}
