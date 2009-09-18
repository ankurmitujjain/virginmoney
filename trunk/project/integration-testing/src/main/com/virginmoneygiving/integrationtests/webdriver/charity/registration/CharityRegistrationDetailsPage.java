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
 */
public class CharityRegistrationDetailsPage extends GretnaWebDriverPage {

    public CharityRegistrationDetailsPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Charity Details");
    }

    public CharityRegistrationAdministrationAddressPage submitCharityAddress(
            String name, String regNumber, String addressLine1, String addressLine2,
            String town, String county, String postCode) {

        logMatchingElements(By.xpath("//input")); 
        
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityName']")).sendKeys(name);
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityRegistrationNumber']")).sendKeys(regNumber);
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAddress.addressLine1']")).sendKeys(addressLine1);
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAddress.addressLine2']")).sendKeys(addressLine2);
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAddress.city']")).sendKeys(town);
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAddress.county']")).sendKeys(county);
        wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAddress.postcode']")).sendKeys(postCode);
        wd.findElement(By.xpath("//input[@title='Next']")).click();

        return GretnaPageFactory.initElements(wd, CharityRegistrationAdministrationAddressPage.class);

        // wd.findElement(By.xpath("//*[@id=\"adminAddressSameAsRegistrationAddress\"]")).click();
    }

}
