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
public class CharityRegistrationPage extends GretnaWebDriverPage {

    public CharityRegistrationPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Registration" );
    }

    public CharityRegistrationFindCharityResultsPage findCharity( String name ) {        
        wd.findElement(By.xpath("//input[@name = 'searchCharityText']")).sendKeys(name);
        wd.findElement(By.xpath("//button[@title=\"Find charity\"]")).click();        
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationFindCharityResultsPage.class);
    }
    
}
