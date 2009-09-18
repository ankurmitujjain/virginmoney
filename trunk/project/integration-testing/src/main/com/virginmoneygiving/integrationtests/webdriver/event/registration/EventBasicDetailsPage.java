package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.event.EventRegistrationDetails;

/**
 * This class is used to set the event other details on event setup page -> basic details tab.
 * @author vishals
 */
public class EventBasicDetailsPage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public EventBasicDetailsPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Create Event | Basics" );
    }
    
    /**
     * This method populate the other details (excluding event type that is setup using
     * {@link EventBasicChooseEventTypePage})required on event setup page -> basic details tab
     * and trigger the next action. 
     * @param eventRegistrationDetails contains event mock details.
     * @return {@link EventDetailsPage} instance.
     */
    public EventDetailsPage setupEventBasicDetails(EventRegistrationDetails eventRegistrationDetails) {
    	
    	logMatchingElements(By.xpath("//input"));
    	logMatchingElements(By.xpath("//select"));
    	logMatchingElements(By.xpath("//radio"));
    	logMatchingElements(By.xpath("//option"));    	
    	
    	wd.findElement(By.xpath("//input[@name = 'organisedEventDetailsVo.name']")).sendKeys(eventRegistrationDetails.eventBasicDetails.eventName);
    	wd.findElement(By.xpath("//input[@name = 'eventDateVo.eventStartDay']")).sendKeys(eventRegistrationDetails.eventBasicDetails.startDay);
    	wd.findElement(By.xpath("//input[@name = 'eventDateVo.eventStartMonth']")).sendKeys(eventRegistrationDetails.eventBasicDetails.startMonth);
    	wd.findElement(By.xpath("//input[@name = 'eventDateVo.eventStartYear']")).sendKeys(eventRegistrationDetails.eventBasicDetails.startYear);
    	wd.findElement(By.xpath("//input[@type='checkbox' and @name='selectActivityTypeCode']")).setSelected();
    	wd.findElement(By.xpath("//input[@type='radio' and @name='organisedEventDetailsVo.eventSetUpDateIndicator' and @value='Y']")).setSelected();
    	
    	
    	// Find the location select box. Iterate through values and set the selected value as
    	//second option in the drop down. 
    	WebElement locationSelectBox = wd.findElement(By.xpath("//select[@name='organisedEventDetailsVo.locationId']"));
    	
    	List<WebElement> locationOptions = locationSelectBox.findElements(By.tagName("option"));
    	int breakCounter = 2;
    	int loopCounter = 1;
    	
    	for (WebElement option : locationOptions) {    	    
    	    if(loopCounter == breakCounter) {
    	    	option.setSelected();
    	    	break;
    	    }   	    	
    	    loopCounter ++;	
    	    
    	}
    	
    	// Find the bank account id select box. Iterate through values and set the selected value as
    	//second option in the drop down. 
    	WebElement bankAccountSelectBox = wd.findElement(By.xpath("//select[@name='organisedEventDetailsVo.accountId']"));
    	
    	List<WebElement> bankAccountOptions = bankAccountSelectBox.findElements(By.tagName("option"));

    	loopCounter = 1;
    	
    	for (WebElement option : bankAccountOptions) {    	    
    	    if(loopCounter == breakCounter) {
    	    	option.setSelected();
    	    	break;
    	    }   	    	
    	    loopCounter ++;    	    
    	}
    	
    	// Trigger the next click.    	
    	wd.findElement(By.xpath("//input[@name='viewflow']")).click();
    	return GretnaPageFactory.initElements(getWebDriver(), EventDetailsPage.class);    	
    }
    
}
