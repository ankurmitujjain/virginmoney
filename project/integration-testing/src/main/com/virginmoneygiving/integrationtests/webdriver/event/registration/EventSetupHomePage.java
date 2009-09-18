package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class will trigger the next action on event setup home page.
 * @author vishals
 */
public class EventSetupHomePage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public EventSetupHomePage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Create Event" );
    }
    
    /**
     * This method will trigger the Get started button click action on event setup home page.
     * @return {@link EventBasicChooseEventTypePage} instance.
     */
    public EventBasicChooseEventTypePage displayEventBasicDetailsPage() {
    	logMatchingElements(By.xpath("//img"));
    	wd.findElement(By.xpath("//img[@title=\"Get started\"]")).click();
    	return GretnaPageFactory.initElements(getWebDriver(), EventBasicChooseEventTypePage.class);
    	
    }
    
}
