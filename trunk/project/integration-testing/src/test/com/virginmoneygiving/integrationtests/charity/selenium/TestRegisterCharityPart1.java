package com.virginmoneygiving.integrationtests.charity.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.opsera.junit4.AfterFailure;
import com.opsera.junit4.AfterFailureRunner;
import com.thoughtworks.selenium.Selenium;
import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.selenium.SeleniumHelper;

/**
 * Run a selenium test to register a charity - checks the runtime is functional.
 * Also tests the database has been correctly populated using dbunit.
 * @author ipriest
 *
 */
@RunWith(AfterFailureRunner.class)
public class TestRegisterCharityPart1 {

    private SeleniumHelper selenium = null;
    private DbUnitHelper givingDbUnitHelper = null;
    private DbUnitHelper paymentDbUnitHelper = null;
    
    @Before
    public void setUp() throws Exception {

        /*
         * Set up dbunit helpers and put the databases in a known state
         */
        givingDbUnitHelper = 
            new DbUnitHelper(
                DbUnitHelper.GIVING_DB_URL, 
                DbUnitHelper.GIVING_DB_USERNAME, 
                DbUnitHelper.GIVING_DB_PASSWORD, 
                DbUnitHelper.GIVING_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/selenium/registerpart1/giving-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/selenium/registerpart1/giving-before-dataset.xml");
        givingDbUnitHelper.onSetUp();
        
        paymentDbUnitHelper = 
            new DbUnitHelper(
                DbUnitHelper.PAYMENT_DB_URL, 
                DbUnitHelper.PAYMENT_DB_USERNAME, 
                DbUnitHelper.PAYMENT_DB_PASSWORD, 
                DbUnitHelper.PAYMENT_DB_SCHEMA,
                "/com/virginmoneygiving/integrationtests/charity/selenium/registerpart1/payment-after-dataset.xml",
                "/com/virginmoneygiving/integrationtests/charity/selenium/registerpart1/payment-before-dataset.xml");
        paymentDbUnitHelper.onSetUp();

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
    public void createCharity() throws Exception {
        
        /*
         * Create a charity via the browser
         */
        selenium.open("/charity-web/");
        selenium.waitForPageToLoad("30000");
        selenium
            .click("ui=charity-web::charityTopLevelActions(action=Click here to proceed with charity registration part-1)");
        selenium.waitForPageToLoad("30000");
        assertEquals("Virgin Money Giving | Charity Registration", selenium
            .getTitle());
        selenium
            .type("ui=charity-pages::charitySearchInput()", "nosuchcharity");
        selenium.click("ui=charity-pages::charitySearchSubmit()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Find Charity",
            selenium.getTitle());
        selenium.click("ui=charity-pages::charityNotListedNext()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Charity Details",
            selenium.getTitle());
        selenium.type("ui=charity-pages::charityName()", "Charity One");
        selenium.type("ui=charity-pages::registerCharityNumber()", "1");
        selenium.type("ui=common-pages::regBuildingNmNo()", "1");
        selenium.type("ui=common-pages::regPostCode()", "AA1 1AA");
        selenium.click("ui=charity-pages::charityAddressNext()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Administration Address",
            selenium.getTitle());
        selenium
            .click("ui=charity-pages::adminAddressSameAsRegistrationAddress()");
        selenium.click("ui=charity-pages::adminAddressNext()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Account Holders Details",
            selenium.getTitle());
        selenium.select("ui=charity-pages::title()", "label=Mr");
        selenium.type("ui=common-pages::firstName()", "Charity");
        selenium.type("ui=common-pages::lastName()", "Admin");
        selenium
            .type("ui=charity-pages::occupation()", "Charity Administrator");
        selenium.type("ui=common-pages::telephoneNumber()", "01111 111111");
        selenium.type("ui=common-pages::emailAddress()", "c.admin@one.com");
        selenium.click("ui=charity-pages::acceptTermsAndCondition()");
        selenium.click("ui=charity-pages::accountHolderNext()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Security Details",
            selenium.getTitle());
        selenium.type("ui=common-pages::dayOfBirth()", "01");
        selenium.type("ui=common-pages::monthOfBirth()", "01");
        selenium.type("ui=common-pages::yearOfBirth()", "1965");
        selenium.type("ui=common-pages::password()", "Pa55word!");
        selenium.type("ui=common-pages::confirmpass()", "Pa55word!");
        selenium.click("ui=charity-pages::registerNewCharity()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Registration Complete",
            selenium.getTitle());
        selenium.click("ui=charity-pages::gotoApplicationSummary()");
        selenium.waitForPageToLoad("30000");
        assertEquals(
            "Virgin Money Giving | Charity Registration | Summary",
            selenium.getTitle());
        selenium
            .click("ui=charity-pages::charityAccountSummaryActions(action=Charity category)");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=charity-pages::animalsThatHelp()");
        selenium.click("ui=charity-pages::saveCategory()");
        selenium.waitForPageToLoad("30000");
        selenium.click("ui=charity-pages::gotoApplicationSummary()");
        selenium.waitForPageToLoad("30000");
        assertEquals("Virgin Money Giving | Charity Registration | Summary",
            selenium.getTitle());

        /*
         * Confirm the database is in the expected state
         */
        givingDbUnitHelper.checkExpected();
        paymentDbUnitHelper.checkExpected();
        
    }
    
}
