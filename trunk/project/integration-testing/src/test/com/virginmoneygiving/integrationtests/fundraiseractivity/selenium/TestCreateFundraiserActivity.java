package com.virginmoneygiving.integrationtests.fundraiseractivity.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.opsera.junit4.AfterFailure;
import com.opsera.junit4.AfterFailureRunner;
import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.creators.CreateFiveLiveCharities;
import com.virginmoneygiving.integrationtests.selenium.SeleniumHelper;

@RunWith(AfterFailureRunner.class)
public class TestCreateFundraiserActivity {

    private SeleniumHelper selenium = null;
    private DbUnitHelper givingDbUnitHelper = null;
    
    @Before
    public void setUp() throws Exception {
        
        /*
         * Set up dbunit and put the database in a known state
         */
        givingDbUnitHelper = 
            new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/fundraiseractivity/selenium/create/after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/fundraiseractivity/selenium/create/before-dataset.xml");
        givingDbUnitHelper.onSetUp();
        
        /*
         * Ensure a charity (or 5) is in the database
         */
        CreateFiveLiveCharities.createFiveLiveCharites();

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
        if ( givingDbUnitHelper != null ) {
            givingDbUnitHelper.onTeardown();
        }
    }
    
    @Test
	public void testCreateFundraiserActivity_OneCharity() throws Exception {
        
        /*
         * Run Selenium
         */
        selenium.open("/fundraiser-web/");
        selenium.click("ui=fundraiser-web::fundraiserTopLevelActions(action=Choose Fundraising Reason)");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=fundraiser-pages::personalChallenge()");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=fundraiser-pages::fundraiserEvents(action=031)");
        selenium.select("ui=fundraiser-pages::eventsDetails()", "label=Non-location specific");
        selenium.type("ui=fundraiser-pages::eventCompletionDay()", "31");
        selenium.type("ui=fundraiser-pages::eventCompletionMonth()", "12");
        selenium.type("ui=fundraiser-pages::eventCompletionYear()", "2009");
        selenium.select("ui=fundraiser-pages::eventDuration()", "label=1 month");
        selenium.click("ui=fundraiser-pages::alone()");
        selenium.click("submit");
        selenium.waitForPageToLoad("30000");
        selenium.type("ui=fundraiser-pages::charityName()", "Charity One");
        selenium.type("ui=fundraiser-pages::fundraisingTargetAmount()", "1000");
        selenium.click("ui=fundraiser-pages::contributedNo()");
        selenium.click("fundraiserCharitySave_0");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=fundraiser-pages::unregisteredNext()");
        selenium.waitForPageToLoad("30000");
        selenium.select("ui=fundraiser-pages::registerfundraiser.title()", "label=Mr");
        selenium.type("ui=common-pages::firstName()", "Freddy");
        selenium.type("ui=common-pages::lastName()", "Fundraiser");
        selenium.type("ui=common-pages::regBuildingNmNo()", "1");
        selenium.type("ui=common-pages::regPostCode()", "AA1 1AA");
        selenium.select("ui=fundraiser-pages::preferredTelephoneNumberType()", "label=Home");
        selenium.type("ui=common-pages::emailAddress()", "f.fundraiser@vmgtest.com");
        selenium.type("alternativeTelephoneNumber", "01111 111111");
        selenium.click("termsAndConditionsAccepted");
        selenium.click("fundraiserdetailsForm_0");
        selenium.waitForPageToLoad("30000");
        selenium.type("ui=common-pages::dayOfBirth()", "01");
        selenium.type("ui=common-pages::monthOfBirth()", "01");
        selenium.type("ui=common-pages::yearOfBirth()", "1965");
        selenium.type("ui=common-pages::password()", "Pa55word!");
        selenium.type("ui=common-pages::confirmpass()", "Pa55word!");
        selenium.click("ui=fundraiser-pages::eventTabNext()");
        selenium.waitForPageToLoad("30000");
        assertEquals("Virgin Money Giving | Fundraising | Choose URL", selenium.getTitle());
        selenium.click("uniqueUrlForm_0");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=fundraiser-pages::createFundraiserPage()");
        selenium.waitForPageToLoad("30000");
        selenium.type("createFundraiserPage_pageDetails_url", "Freddy's Personal Challenge");
        selenium.click("createFundraiserPage_0");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=fundraiser-pages::myPage()");
        selenium.waitForPageToLoad("30000");
		
		/*
		 * Check the state of the database
		 */
		givingDbUnitHelper.checkExpected();
	}
}
