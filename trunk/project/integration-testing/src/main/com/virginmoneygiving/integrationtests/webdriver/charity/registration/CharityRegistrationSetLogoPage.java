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
public class CharityRegistrationSetLogoPage extends GretnaWebDriverPage {

    public CharityRegistrationSetLogoPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Charity Logo");
    }

    public CharityRegistrationSummaryPage setLogo(String logoPath) {
        wd.findElement(By.xpath("//*[@id='upload']")).sendKeys(logoPath);
        wd.findElement(By.xpath("//*[@id='uploadButton']")).click();        
        wd.findElement(By.xpath("//*[@id='saveId']")).click();        
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationSummaryPage.class);        
    }

}
