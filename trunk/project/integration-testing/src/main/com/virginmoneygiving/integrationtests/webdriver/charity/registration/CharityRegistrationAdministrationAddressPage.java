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
public class CharityRegistrationAdministrationAddressPage extends GretnaWebDriverPage {

    public CharityRegistrationAdministrationAddressPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Administration Address");
    }
    
    public CharityRegistrationAccountHoldersDetailsPage useCharityAddressAsAccountHoldersAddress() {

        wd.findElement(By.xpath("//*[@id=\"adminAddressSameAsRegistrationAddress\"]")).click();
        wd.findElement(By.xpath("//input[@title='Next']")).click();

        return GretnaPageFactory.initElements(wd, CharityRegistrationAccountHoldersDetailsPage.class);
    } 
}
