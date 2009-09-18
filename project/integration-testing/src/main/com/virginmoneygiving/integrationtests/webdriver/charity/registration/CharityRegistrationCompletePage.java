/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author jallen
 *
 */
public class CharityRegistrationCompletePage extends GretnaWebDriverPage {

    public CharityRegistrationCompletePage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Registration Complete"); 
    }

    /**
     * @return
     */
    public CharityRegistrationSummaryPage goToCharitySummaryPage() {

        wd.findElement(By.xpath("//div[@id='content-primary']/a")).click();
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationSummaryPage.class);        
    }
    
}
