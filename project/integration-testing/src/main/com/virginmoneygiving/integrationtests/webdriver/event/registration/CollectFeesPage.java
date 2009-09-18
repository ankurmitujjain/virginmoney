package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is used to set the event other details on event setup page -> collect fees tab.
 * @author vishals
 */
public class CollectFeesPage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public CollectFeesPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Create Event | Collecting Your Fees" );
    }
    
    /**
     * This method populate the other details (excluding payment mode that is setup using
     * {@link CollectFeesSetupPaymentCollectionModePage})required on event setup page -> collect fees tab 
     * and trigger the next action. 
     * @return {@link AccountUsersPage} instance.
     */
    public AccountUsersPage setupFeesCollectionDetails() {
    	// Log details.
    	logMatchingElements(By.xpath("//radio"));
    	logMatchingElements(By.xpath("//input"));
    	// Setup screen data.
    	wd.findElement(By.xpath("//input[@type='radio' and @name='organisedEventBean.organisedEventFeeDetails.eventSolelyAidOfCharityInd' and @value='Y']")).setSelected();
    	// Trigger Next button submit.
    	wd.findElement(By.xpath("//input[@type='submit' and @name='navigationButton']")).click();
    	
    	return GretnaPageFactory.initElements(getWebDriver(), AccountUsersPage.class);
    }
    
}
