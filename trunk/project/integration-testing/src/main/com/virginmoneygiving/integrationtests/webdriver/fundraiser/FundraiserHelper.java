package com.virginmoneygiving.integrationtests.webdriver.fundraiser;

import java.sql.Connection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.ChooseFundraisingReasonPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplayFundraiserEmailDonor;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplayFundraiserPageIntro;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplayFundraiserPagePreview;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplayFundraiserThankYou;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplaySearchCharity;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplayUniqueUrlDetails;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.FundraiserCharitySelection;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.PersonalChallengeEvents;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.portal.FundraiserAccountPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.portal.FundraiserPortalHomePage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration.DisplayAccountSecurityDetails;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration.DisplayFundraiserDetails;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration.DisplayFundraiserRegistrationSignIn;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.GlobalSignInPage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.PasswordDetailsPage;

/**
 * The Class FundraiserHelper.
 * 
 * @author rohitm
 */
public class FundraiserHelper {

    /** The web driver. */
    private WebDriver webDriver;
    
    /** The jdbc connection. */
    private Connection jdbcConnection;
    
    /** The fundraisers. */
    private List<FundraiserRegistrationDetails> fundraisers;

    /**
     * Instantiates a new fundraiser helper.
     * 
     * @param wd the wd
     * @param jdbcConnection the jdbc connection
     */
    public FundraiserHelper(WebDriver wd, Connection jdbcConnection) {
        this.webDriver = wd;
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * Instantiates a new fundraiser helper.
     * 
     * @param jdbcConnection the jdbc connection
     */
    public FundraiserHelper(Connection jdbcConnection) {
        // create a web driver for the charity application
        HtmlUnitDriver fundraiserHtmlUnitWebDriver = new HtmlUnitDriver();
        fundraiserHtmlUnitWebDriver.setJavascriptEnabled(false);

        this.webDriver = fundraiserHtmlUnitWebDriver;
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * Instantiates a new fundraiser helper.
     */
    public FundraiserHelper() {
        HtmlUnitDriver fundraiserHtmlUnitWebDriver = new HtmlUnitDriver();
        fundraiserHtmlUnitWebDriver.setJavascriptEnabled(false);
        // FirefoxDriver fundraiserHtmlUnitWebDriver = new FirefoxDriver();
        this.webDriver = fundraiserHtmlUnitWebDriver;
    }

    /**
     * Generate fundraiser registration details.
     * 
     * @return the fundraiser registration details
     */
    public static FundraiserRegistrationDetails generateFundraiserRegistrationDetails() {

        FundraiserRegistrationDetails fundraiserRegDetails = new FundraiserRegistrationDetails();
        fundraiserRegDetails.fundraiserDetails.reasonCode = "CHALLENGE";
        fundraiserRegDetails.fundraiserDetails.eventCompletionDay = "12";
        fundraiserRegDetails.fundraiserDetails.eventCompletionMonth = "12";
        fundraiserRegDetails.fundraiserDetails.eventCompletionYear = "2010";
        fundraiserRegDetails.fundraiserDetails.charityName = "Auto Test Charity 001";

        fundraiserRegDetails.administratorDetails.title = "Mr";
        fundraiserRegDetails.administratorDetails.firstName = "fundraiser";
        fundraiserRegDetails.administratorDetails.lastName = "details";
        fundraiserRegDetails.fundraiserDetails.buildingNameAndNumber = "1234";
        fundraiserRegDetails.fundraiserDetails.town = "town";
        fundraiserRegDetails.fundraiserDetails.county = "country";
        fundraiserRegDetails.fundraiserDetails.postCode = "SK8 2EL";
        fundraiserRegDetails.fundraiserDetails.preferredTelephoneNumber = "07787899123";
        fundraiserRegDetails.fundraiserDetails.preferredTelephoneNumberType = "L";
        fundraiserRegDetails.fundraiserDetails.emailAddress = "fundraiser.details@arrkgroup.com";

        fundraiserRegDetails.securityDetails.dobDay = "10";
        fundraiserRegDetails.securityDetails.dobMonth = "10";
        fundraiserRegDetails.securityDetails.dobYear = "1982";
        fundraiserRegDetails.securityDetails.password = "admin*1239";
        fundraiserRegDetails.securityDetails.confirmPassword = "admin*1239";

        fundraiserRegDetails.fundraiserDetails.fundraiserUrl = "fundraiserdetails";

        fundraiserRegDetails.fundraiserDetails.pageTitle = "pagetitlesss";
        fundraiserRegDetails.fundraiserDetails.personalComment = "message details";

        return fundraiserRegDetails;
    }

    /**
     * Register new fundraiser.
     * 
     * @param fundraiser the fundraiser
     */
    @SuppressWarnings("unchecked")
    public void registerNewFundraiser(FundraiserRegistrationDetails fundraiser) {

        ChooseFundraisingReasonPage registerFundraiserPage = GretnaPageFactory.newRegisterFundraiserPage(webDriver);
        PersonalChallengeEvents personalChallengeEvents = registerFundraiserPage.displayPersonalChallengeEvents();
        FundraiserCharitySelection fundraiserCharitySelection = personalChallengeEvents.displayFundraiserCharitySelection(fundraiser);
        DisplaySearchCharity displaySearchCharity = fundraiserCharitySelection.displaySeachCharityResult(fundraiser);
        // TODO need to remove once application bug is fixed.
        // Application bug: Not giving result when search first time.
        displaySearchCharity = displaySearchCharity.displayCharitySearch();
        fundraiserCharitySelection = displaySearchCharity.displayFundraiserCharitySelection(fundraiser);
        DisplayFundraiserRegistrationSignIn displayFundraiserRegistrationSignIn = fundraiserCharitySelection.displayFundraiserRegistrationSignIn(fundraiser);
        DisplayFundraiserDetails displayFundraiserDetails = displayFundraiserRegistrationSignIn.displayFundraiserDetails(fundraiser);
        DisplayAccountSecurityDetails displayAccountSecurityDetails = displayFundraiserDetails.displayAccountSecurityDetails(fundraiser);
        DisplayUniqueUrlDetails displayUniqueUrlDetails = displayAccountSecurityDetails.displayUniqueUrlDetails(fundraiser);
        DisplayFundraiserPageIntro displayFundraiserPageIntro = displayUniqueUrlDetails.displayFundraiserPageIntro(fundraiser);
        DisplayFundraiserPagePreview displayFundraiserPagePreview = displayFundraiserPageIntro.displayFundraiserPagePreview(fundraiser);
        DisplayFundraiserEmailDonor displaFundraiserEmailDonor = displayFundraiserPagePreview.displayFundraiserEmailDonor(fundraiser);
        DisplayFundraiserThankYou displayFundraiserThankYou = displaFundraiserEmailDonor.displayFundraiserThankYou(fundraiser);
        FundraiserAccountPage fundraiserAccountPage=displayFundraiserThankYou.displayFundraiserPortalHome();
        fundraiserAccountPage.signOut();
    }

    /**
     * Gets the web driver.
     * 
     * @return the webDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Sets the web driver.
     * 
     * @param webDriver the webDriver to set
     */
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    /**
     * Sets the jdbc connection.
     * 
     * @param jdbcConnection the new jdbc connection
     */
    public void setJdbcConnection(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * Sets the fundraisers.
     * 
     * @param fundraisers the new fundraisers
     */
    public void setFundraisers(List<FundraiserRegistrationDetails> fundraisers) {
        this.fundraisers = fundraisers;
    }

    /**
     * Creates the fundraising page.
     * 
     * @param fundraiser the fundraiser
     */
    @SuppressWarnings("unchecked")
    public void createFundraisingPage(FundraiserRegistrationDetails fundraiser) {

        // Trigger the sing-in flow.
        GlobalSignInPage singinPage = GretnaPageFactory.initiateSignIn(webDriver);
        // Accept user name and DOB.
        PasswordDetailsPage passwordDetailsPage = singinPage.acceptUsernameAndDOBDetails(fundraiser.fundraiserDetails.emailAddress,
                fundraiser.securityDetails.dobDay, fundraiser.securityDetails.dobMonth, fundraiser.securityDetails.dobYear);
        // Accept password.
        FundraiserPortalHomePage fundraiserPortalHomePage = (FundraiserPortalHomePage) passwordDetailsPage.acceptPassword(fundraiser.securityDetails.password,
                FundraiserPortalHomePage.class);
        ChooseFundraisingReasonPage chooseFundraisingReasonPage = fundraiserPortalHomePage.displayFundraisingReasonPage();
        PersonalChallengeEvents personalChallengeEvents = chooseFundraisingReasonPage.displayPersonalChallengeEvents();
        FundraiserCharitySelection fundraiserCharitySelection = personalChallengeEvents.displayFundraiserCharitySelection(fundraiser);
        DisplaySearchCharity displaySearchCharity = fundraiserCharitySelection.displaySeachCharityResult(fundraiser);
        // TODO need to remove once application bug is fixed.
        // Application bug: Not giving result when search first time.
        displaySearchCharity = displaySearchCharity.displayCharitySearch();
        fundraiserCharitySelection = displaySearchCharity.displayFundraiserCharitySelection(fundraiser);
        DisplayFundraiserPageIntro displayFundraiserPageIntro = fundraiserCharitySelection.displayFundraiserPageIntro();
        DisplayFundraiserPagePreview displayFundraiserPagePreview = displayFundraiserPageIntro.displayFundraiserPagePreview(fundraiser);
        DisplayFundraiserEmailDonor displaFundraiserEmailDonor = displayFundraiserPagePreview.displayFundraiserEmailDonor(fundraiser);
        DisplayFundraiserThankYou displayFundraiserThankYou = displaFundraiserEmailDonor.displayFundraiserThankYou(fundraiser);
        displayFundraiserThankYou.displayFundraiserPortalHome();
    }
}
