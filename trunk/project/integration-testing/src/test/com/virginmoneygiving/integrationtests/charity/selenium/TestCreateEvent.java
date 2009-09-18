package com.virginmoneygiving.integrationtests.charity.selenium;

import static org.junit.Assert.assertTrue;

import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.opsera.junit4.AfterFailure;
import com.opsera.junit4.AfterFailureRunner;
import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.creators.CreateFiveLiveCharities;
import com.virginmoneygiving.integrationtests.selenium.SeleniumHelper;

/**
 * Run a selenium test to create a charity event.
 *
 * @author ipriest
 *
 */
@RunWith(AfterFailureRunner.class)
public class TestCreateEvent {

    private SeleniumHelper selenium = null;
    private DbUnitHelper givingDbUnitHelper = null;
    
    @Before
    public void setUp() throws Exception {

        /*
         * Create charities
         */
        CreateFiveLiveCharities.createFiveLiveCharites();

        /*
         * Get a helper.
         */
        givingDbUnitHelper = 
            new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/selenium/createevent/giving-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/selenium/createevent/giving-before-dataset.xml");
        givingDbUnitHelper.setSetupDatabaseOperation(DatabaseOperation.CLEAN_INSERT);
        givingDbUnitHelper.onSetUp();
        
        /*
         * Set up selenium session
         */
        selenium = new SeleniumHelper();
        selenium.start();
    }

    @AfterFailure
    public void captureScreenShotOnFailure(Throwable failure) {
        if ( selenium != null ) {
            selenium.captureScreenShotOnFailure(this.getClass(), failure);
        }
    }

    @After
    public void tearDown() throws Exception {
        if ( selenium != null ) {
            selenium.stop();
            selenium = null;
        }
        givingDbUnitHelper.onTeardown();
    }
    
    @Test
    public void createEvent() throws Exception {
        selenium.open("/charity-web/");
        selenium.click("link=Charity Sign-in");
        selenium.waitForPageToLoad("30000");
        selenium.type("username", "c.admin@one.com");
        selenium.type("dobDay", "01");
        selenium.type("dobMonth", "01");
        selenium.type("dobYear", "1965");
        selenium.click("next-btn");
        selenium.waitForPageToLoad("30000");
        // Need to work out what password chars it's asking for
        int indexFirstCharacter = -1;
        int indexSecondCharacter = -1;
        String[] choices = new String[] {
            "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth"
        };
        String password = "Pa55word!";
        
        for( int i = 0; i < choices.length; i++ ) {
            if ( selenium.isTextPresent(choices[i]) ) {
                if ( indexFirstCharacter == -1 ) {
                    indexFirstCharacter = i;
                }
                else if ( indexSecondCharacter == -1 ) {
                    indexSecondCharacter = i;
                }
            }
        }

        selenium.type("firstCharacter", "" + password.charAt(indexFirstCharacter));
        selenium.type("secondCharacter", "" + password.charAt(indexSecondCharacter));

        selenium.click("login-btn");
        selenium.waitForPageToLoad("30000");
        
        // Page bug? Goes to search charity page instead of charity-portal. Force it
        // to charity portal.
        selenium.open("https://localhost:8443/charity-web/charity/charityportal.action");
        selenium.click("link=Events");
        selenium.click("link=Create an event");
        selenium.waitForPageToLoad("30000");
        selenium.click("//img[@alt='Get started']");
        selenium.waitForPageToLoad("30000");
        selenium.click("createEventBasicSave_organisedEventDetailsVo_selectEventBasicPageIndicatorY");
        selenium.type("createEventBasicSave_organisedEventDetailsVo_name", "Selenium Event");
        selenium.type("createEventBasicSave_eventDateVo_eventStartDay", "30");
        selenium.type("createEventBasicSave_eventDateVo_eventStartMonth", "09");
        selenium.type("createEventBasicSave_eventDateVo_eventStartYear", "2009");
        selenium.type("createEventBasicSave_eventDateVo_eventStartTime", "8:00");
        selenium.click("id026");
        selenium.select("locationlist", "label=Oxfordshire");
        selenium.select("organisedEventDetailsVo.accountId", "label=11-22-33 1234567871 HSBC Bank Account");
        selenium.click("createEventBasicSave_organisedEventDetailsVo_eventSetUpDateIndicatorY");
        selenium.click("createEventBasicSave__execute");
        selenium.waitForPageToLoad("30000");
        selenium.type("createEventDetailsSave_organisedEventDetailsVo_description", "Walk the Oxfordshire Way");
        selenium.type("createEventDetailsSave_organisedEventDetailsVo_joiningInstructions", "Turn up and walk");
        selenium.click("upload");
        selenium.type("upload", "C:\\Users\\ipriest\\Pictures\\187x1672_Henry.JPG");
        selenium.click("createEventDetailsSave__executeImage");
        selenium.waitForPageToLoad("30000");
        selenium.type("createEventDetailsSave_organisedEventDetailsVo_contactName", "Wally Walker");
        selenium.type("createEventDetailsSave_organisedEventDetailsVo_email", "w.walker@charityone.vmgtest.com");
        selenium.click("createEventDetailsSave_0");
        selenium.waitForPageToLoad("30000");
        selenium.click("charityDonationSplit_organisedEventBean_organisedEventFeeDetails_vmgManagingFeeIndicatorN");
        selenium.click("charityDonationSplit_organisedEventBean_organisedEventFeeDetails_eventSolelyAidOfCharityIndY");
        selenium.click("charityDonationSplit_navigationButton");
        selenium.waitForPageToLoad("30000");
        selenium.click("_navigationButton");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("Thank you - your event has been set up"));
        assertTrue(selenium.isTextPresent("Selenium Event"));
       
        givingDbUnitHelper.checkExpected();
    }
    
}
