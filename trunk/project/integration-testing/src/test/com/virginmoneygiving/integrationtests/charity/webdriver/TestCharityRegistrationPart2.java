package com.virginmoneygiving.integrationtests.charity.webdriver;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.BaseWebdriverTests;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityHelper;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.event.EventHelper;
import com.virginmoneygiving.integrationtests.webdriver.event.EventRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.operations.OperationsCharityDetails;
import com.virginmoneygiving.integrationtests.webdriver.operations.OperationsHelper;
import com.virginmoneygiving.integrationtests.webdriver.operations.OperationsUserDetails;

/**
 * Test case class for charity Registration part 2
 * <br/>
 * This journey will be consist of multiple steps as below
 * <ul>
 * <li>Log on to application using Operations user credentails</li>
 * <li>Select a charity-Navigate to charity portal.</li>
 * <li>Complete steps for charity registration part 2</li>
 * </ul>
 * 
 * @author SaurabhH.
 */
public class TestCharityRegistrationPart2 extends BaseWebdriverTests {

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestCharityRegistrationPart2.class);
    /** Operations helper instance.*/
    private OperationsHelper operationsHelper;
    
    /** Charity helper instance*/
    private CharityHelper charityHelper;
    
    /**Instance of Operations charity */
    
    private OperationsCharityDetails operationsCharityDetails;
    
    
    /** Instance of Operations user details*/
    
    private OperationsUserDetails operationsUserDetails;
    
   
 
    @Test
	public void testCharityRegistrationPart2() throws Exception {
    	
    	// create operation user account.        
    	operationsHelper.createInternalUserAccountsInDatabase(operationsUserDetails.getUserName());
        // Complete charity registration part 2
		operationsHelper.charityRegistrationPart2(operationsUserDetails,charityHelper.amendCharityRegistrationDetails());
	}

	/**
	 * @param operationsHelper
	 *            the operationsHelper to set
	 */
	public void setOperationsHelper(OperationsHelper operationsHelper) {
		this.operationsHelper = operationsHelper;
	}

	/**
	 * @param operationsCharityDetails the operationsCharityDetails to set
	 */
	public void setOperationsCharityDetails(
			OperationsCharityDetails operationsCharityDetails) {
		this.operationsCharityDetails = operationsCharityDetails;
	}

	/**
	 * @param charityHelper the charityHelper to set
	 */
	public void setCharityHelper(CharityHelper charityHelper) {
		this.charityHelper = charityHelper;
	}

	/**
	 * @param operationsUserDetails the operationsUserDetails to set
	 */
	public void setOperationsUserDetails(OperationsUserDetails operationsUserDetails) {
		this.operationsUserDetails = operationsUserDetails;
	}
	
	
	
	
	
	
	
	
  
}
