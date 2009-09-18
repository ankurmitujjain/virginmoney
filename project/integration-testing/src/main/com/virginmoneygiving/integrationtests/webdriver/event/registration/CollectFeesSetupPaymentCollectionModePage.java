package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is used to set the payment mode on event setup page -> collect fees tab.
 * @author vishals
 */
public class CollectFeesSetupPaymentCollectionModePage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public CollectFeesSetupPaymentCollectionModePage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Create Event | Collecting Your Fees" );
    }
    
    /**
     * Setup the payment collection mode on collect fees page and calls next that will load the same page.
     * @return {@link CollectFeesPage} instance..
     */
    public CollectFeesPage setupPaymentCollectionMode() {    	
    	logMatchingElements(By.xpath("//radio"));
    	logMatchingElements(By.xpath("//input"));
    	
    	wd.findElement(By.xpath("//input[@type='radio' and @name='organisedEventBean.organisedEventFeeDetails.vmgManagingFeeIndicator' and @value='N']")).setSelected();
    	wd.findElement(By.xpath("//input[@type='submit' and @name='navigationButton']")).click();
    	
    	return GretnaPageFactory.initElements(getWebDriver(), CollectFeesPage.class);
    }
    
}
