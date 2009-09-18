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
public class CharityRegistrationSetMoreDetailsPage extends GretnaWebDriverPage {

    public CharityRegistrationSetMoreDetailsPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Charity Details");
    }

    public CharityRegistrationSummaryPage setDetails(String commonName, String abbreviations, String accountingEndDateDay, String accountingEndDateMonth, String accountingEndDateYear) {
        wd.findElement(By.xpath("//*[@id='commonName']")).sendKeys(commonName);
        wd.findElement(By.xpath("//*[@id='abbreviations']")).sendKeys(abbreviations);
        wd.findElement(By.xpath("//*[@id='accountingPeriodEndDay']")).sendKeys(accountingEndDateDay);
        wd.findElement(By.xpath("//*[@id='accountingPeriodEndMonth']")).sendKeys(accountingEndDateMonth);
        wd.findElement(By.xpath("//*[@id='accountingPeriodEndYear']")).sendKeys(accountingEndDateYear);
        wd.findElement(By.xpath("//*[@id='saveId']")).click();        
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationSummaryPage.class);        
    }

}
