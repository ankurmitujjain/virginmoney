package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class will trigger the create event link click on the charity portal home page.
 * @author vishals
 */
public class CharityPortalHomePage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver web driver instance.
	 */
    public CharityPortalHomePage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account" );
    }

    
    /**
     * This method will trigger the create event link click on the charity portal home page.
     * @return {@link EventSetupHomePage} instance.
     */
    public EventSetupHomePage displayCreateEventPage() {
    	// Trigger click on Create Event link in left hand menu.   	
    	wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[5]/fieldset/ul[@id='events']/li[1]/a")).click();
    	return GretnaPageFactory.initElements(getWebDriver(), EventSetupHomePage.class);       
    }
    
}
