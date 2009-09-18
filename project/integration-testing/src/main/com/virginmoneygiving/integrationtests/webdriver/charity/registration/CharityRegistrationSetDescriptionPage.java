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
public class CharityRegistrationSetDescriptionPage extends GretnaWebDriverPage {

    public CharityRegistrationSetDescriptionPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Charity Description");
    }

    public CharityRegistrationSummaryPage setDescription(String description) {
        wd.findElement(By.xpath("//*[@id='charityDescription']")).sendKeys(description);
        wd.findElement(By.xpath("//*[@id='saveId']")).click();        
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationSummaryPage.class);        
    }
    
}
