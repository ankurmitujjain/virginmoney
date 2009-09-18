package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.event.EventRegistrationDetails;

/**
 * This class is used to set the event other details on event setup page -> event details tab.
 * @author vishals
 */
public class EventDetailsPage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public EventDetailsPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Create Event | Event Details" );
    }
    
    /**
     * This method populate the event details on event setup page -> event details tab.
     * @param eventRegistrationDetails contains event mock details.
     * @return {@link CollectFeesSetupPaymentCollectionModePage} instance.
     */
    public CollectFeesSetupPaymentCollectionModePage setupEventDetails(EventRegistrationDetails eventRegistrationDetails) {
    	
    	logMatchingElements(By.xpath("//input"));
    	logMatchingElements(By.xpath("//textarea"));
    	
    	wd.findElement(By.xpath("//textarea[@name = 'organisedEventDetailsVo.description']")).sendKeys(eventRegistrationDetails.eventBasicDetails.eventDescription);
    	wd.findElement(By.xpath("//input[@name = 'organisedEventDetailsVo.contactName']")).sendKeys(eventRegistrationDetails.eventBasicDetails.contactName);
    	wd.findElement(By.xpath("//input[@type='submit' and @title='Next']")).click();
    	return GretnaPageFactory.initElements(getWebDriver(), CollectFeesSetupPaymentCollectionModePage.class);
    }
    
}
