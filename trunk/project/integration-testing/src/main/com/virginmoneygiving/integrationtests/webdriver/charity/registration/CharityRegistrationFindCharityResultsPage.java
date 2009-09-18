/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author jallen
 *
 */
public class CharityRegistrationFindCharityResultsPage extends GretnaWebDriverPage {

    public CharityRegistrationFindCharityResultsPage(WebDriver webDriver) {
        super( webDriver, "Virgin Money Giving | Charity Registration | Find Charity" );
    }

     public boolean isCharityFound() {
         LOGGER.debug("Page says this: " + wd.findElement( By.xpath("//div[@id=\"content-primary\"]/p")).getText());
         return ! wd.findElement( By.xpath("//div[@id=\"content-primary\"]/p")).getText().startsWith("We've found 0 matches for");
     }

     public CharityRegistrationDetailsPage goToNewCharityRegistrationPage() {
         wd.findElement(By.xpath("//input[@title=\"Next\"]")).click();
         return GretnaPageFactory.initElements(wd, CharityRegistrationDetailsPage.class);
     }
    }
