package com.virginmoneygiving.integrationtests.webdriver.event;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.AccountUsersPage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.CharityPortalHomePage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.CollectFeesPage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.CollectFeesSetupPaymentCollectionModePage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.EventBasicChooseEventTypePage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.EventBasicDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.EventDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.EventSetupHomePage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.GlobalSignInPage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.PasswordDetailsPage;

/**
 * Helper class to help setup data and process <b>webdriver</b> web test flows for event module.
 *
 * @author vishals
 */
public class EventHelper {

	/** Web driver instance. */
    private WebDriver webDriver;
    /** JDBC connection instance required for DBUnit. */
    private Connection jdbcConnection;

    /**
     * Default constructor.
     */
    public EventHelper() {
        // create a web driver for the charity application
        HtmlUnitDriver eventHtmlUnitWebDriver = new HtmlUnitDriver();
        eventHtmlUnitWebDriver.setJavascriptEnabled(false);
        this.webDriver = eventHtmlUnitWebDriver;
    }
    
    /**
     * Constructor with two parameters.
     * @param wd web driver instance.
     * @param jdbcConnection jdbc connection instance.
     */
    public EventHelper(WebDriver wd, Connection jdbcConnection) {
        this.webDriver = wd;
        this.jdbcConnection = jdbcConnection;
    }
    
    /**
     * Constructor with connection object.
     * @param jdbcConnection jdbc connection instance.
     */
    public EventHelper(Connection jdbcConnection) {

        // create a web driver for the charity application
        HtmlUnitDriver eventHtmlUnitWebDriver = new HtmlUnitDriver();
        eventHtmlUnitWebDriver.setJavascriptEnabled(false);

        this.webDriver = eventHtmlUnitWebDriver;
        this.jdbcConnection = jdbcConnection;
    }   

    
    /**
     * Setup mock data required for create event user case journey.
     * @return {@link EventRegistrationDetails}.
     */
    public EventRegistrationDetails generateEventRegistrationDetails() {
    	
    	EventRegistrationDetails eventRegistrationDetails = new EventRegistrationDetails();

    	eventRegistrationDetails.eventBasicDetails.eventName = "AUTO GENERATED EVENT THROUGH JUNIT";

    	eventRegistrationDetails.eventBasicDetails.startDay = "01";
    	eventRegistrationDetails.eventBasicDetails.startMonth = "01";    	
    	// TODO : Replace with dynamic year generator.
    	eventRegistrationDetails.eventBasicDetails.startYear = "2010";
    	
    	eventRegistrationDetails.eventBasicDetails.locationCode="ALL";
    	eventRegistrationDetails.eventBasicDetails.eventDescription = "AUTO GENERATED EVENT THROUGH JUNIT";
    	eventRegistrationDetails.eventBasicDetails.contactName = "AUTO GENERATED";
        return eventRegistrationDetails;
    }
    
    /**
     * This method will trigger the charity event registration use case journey.
     * <br/>
     * Entry point for journey : Global sign-in page <br/>
     * Exit point for journey : Update event page on charity portal.
     * @param charity contains mock charity data.
     * @param eventRegistrationDetails contains mock event data.
     */
    @SuppressWarnings("unchecked")
	public void registerCharityEvent(CharityRegistrationDetails charity, EventRegistrationDetails eventRegistrationDetails) {
    	//Trigger the sing-in flow.
    	GlobalSignInPage singinPage = GretnaPageFactory.initiateSignIn(webDriver);
    	// Accept user name and DOB.
    	PasswordDetailsPage passwordDetailsPage = 
    						singinPage.acceptUsernameAndDOBDetails(charity.administratorDetails.email, 
    										   					   charity.securityDetails.dobDay,
    										   					   charity.securityDetails.dobMonth,
    										   					   charity.securityDetails.dobYear);
    	// Accept password.
    	CharityPortalHomePage charityPortalHomePage = 
    		(CharityPortalHomePage) passwordDetailsPage.acceptPassword(charity.securityDetails.password, CharityPortalHomePage.class);
    	
    	// Trigger Create event link and follow the journey till the end.
    	// Please note that webdriver will run in java script disabled mode and will make use of DDA pages wherever applicable.
    	EventSetupHomePage evnetSetupHomePage = charityPortalHomePage.displayCreateEventPage();    	
    	EventBasicChooseEventTypePage eventBasicChooseEventTypePage = evnetSetupHomePage.displayEventBasicDetailsPage();  	
    	EventBasicDetailsPage eventBasicDetailsPage = eventBasicChooseEventTypePage.chooseEventType();
    	EventDetailsPage eventDetailsPage = eventBasicDetailsPage.setupEventBasicDetails(eventRegistrationDetails);
    	CollectFeesSetupPaymentCollectionModePage collectFeesSetupPaymentCollectionModePage = eventDetailsPage.setupEventDetails(eventRegistrationDetails);
    	CollectFeesPage collectFeesPage = collectFeesSetupPaymentCollectionModePage.setupPaymentCollectionMode();
    	AccountUsersPage accountUsersPage = collectFeesPage.setupFeesCollectionDetails();
    	accountUsersPage.setupAccountUsers();   	
    }

    /**
     * @return the webDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * @param webDriver the webDriver to set
     */
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }   

}
