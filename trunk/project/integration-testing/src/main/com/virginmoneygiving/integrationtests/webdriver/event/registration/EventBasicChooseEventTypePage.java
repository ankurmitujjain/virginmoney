package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is used to set the event type radio selection on event setup page -> basic details tab.
 * @author vishals
 */
public class EventBasicChooseEventTypePage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public EventBasicChooseEventTypePage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Create Event | Basics" );
    }
    
    /**
     * This method will select the event type radio button on event setup page -> basic details tab.
     * @return {@link EventBasicDetailsPage} instance.
     */
    public EventBasicDetailsPage chooseEventType() {

    	logMatchingElements(By.xpath("//input"));
    	logMatchingElements(By.xpath("//select"));
    	
    	wd.findElement(By.xpath("//input[@type='radio' and @name='organisedEventDetailsVo.selectEventBasicPageIndicator' and @value='Y']")).setSelected();
    	logMatchingElements(By.xpath("//input"));
    	wd.findElement(By.xpath("//input[@name='navigationButton']")).click();
    	
    	return GretnaPageFactory.initElements(getWebDriver(), EventBasicDetailsPage.class);
    	
    }
    
}
