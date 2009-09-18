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
public class CharityRegistrationSecurityDetailsPage extends GretnaWebDriverPage {

    public CharityRegistrationSecurityDetailsPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Security Details");
    }

    /**
     * @param string
     * @param string2
     * @param string3
     * @param string4
     */
    public CharityRegistrationCompletePage submitSecurityDetails(String dobDay, String dobMonth, String dobYear, String password) {

        logMatchingElements(By.xpath("//input")); 
        
        wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.dayOfBirth']")).sendKeys(dobDay);
        wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.monthOfBirth']")).sendKeys(dobMonth);
        wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.yearOfBirth']")).sendKeys(dobYear);
        wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.userPassword']")).sendKeys(password);
        wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.confirmPassword']")).sendKeys(password);
        wd.findElement(By.xpath("//input[@title='Register']")).click();

        return GretnaPageFactory.initElements(wd, CharityRegistrationCompletePage.class);
        
    }
        
}
