package com.virginmoneygiving.integrationtests;

import com.virginmoneygiving.integrationtests.charity.webdriver.TestCharityRegistrationPart2;
import com.virginmoneygiving.integrationtests.charity.webdriver.TestRegisterCharity;
import com.virginmoneygiving.integrationtests.charity.webdriver.TestRegisterCharityEvent;
import com.virginmoneygiving.integrationtests.fundraiser.webdriver.TestFundraiserRegistration;
import com.virginmoneygiving.integrationtests.fundraiser.webdriver.TestCreateFundraisingPage;
import com.virginmoneygiving.integrationtests.donor.webdriver.TestDonationForFundraiser;
import com.virginmoneygiving.integrationtests.donor.webdriver.TestDonationForUnRegisteredUser;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * The Class TestVMGApplicationSuit.
 * @author rohitm
 */
public class TestVMGApplicationSuit {
    
    /**
     * Suite.
     * 
     * @return the test
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.virginmoneygiving.integrationtests.charity.webdriver");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestRegisterCharity.class);
        suite.addTestSuite(TestCharityRegistrationPart2.class);
        suite.addTestSuite(TestRegisterCharityEvent.class);
        suite.addTestSuite(TestFundraiserRegistration.class);
        suite.addTestSuite(TestCreateFundraisingPage.class);
        suite.addTestSuite(TestDonationForUnRegisteredUser.class);
        suite.addTestSuite(TestDonationForFundraiser.class);
        //$JUnit-END$
        return suite;
    }

}
