package com.virginmoneygiving.integrationtests.webdriver.fundraiser.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.EventSetupHomePage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.ChooseFundraisingReasonPage;

/**
 * The Class FundraiserPortalHomePage.
 * 
 * @author rohitm
 */
public class FundraiserPortalHomePage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * 
     * @param webDriver web driver instance.
     */
    public FundraiserPortalHomePage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving" );
    }
    
    /**
     * This method will trigger the create fundraising page link click on the fundraiser portal home page.
     * 
     * @return {@link EventSetupHomePage} instance.
     */
    public ChooseFundraisingReasonPage displayFundraisingReasonPage() {
        // Trigger click on Create Event link in left hand menu.    
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div/div/div/ul/li[@id='menuItem1']/a")).click();
        return GretnaPageFactory.initElements(getWebDriver(), ChooseFundraisingReasonPage.class);       
    }
}
