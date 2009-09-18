/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author jallen
 *
 */
public class CharityRegistrationAccountHoldersDetailsPage extends GretnaWebDriverPage {

    public CharityRegistrationAccountHoldersDetailsPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Account Holders Details"); 
    }

    /**
     * @param title
     * @param firstName
     * @param lastName
     * @param occupation
     * @param phoneNumber
     * @param email
     */
    public CharityRegistrationSecurityDetailsPage submitAccountHolderDetails(String title, String firstName, String lastName, String occupation, String telephoneNumber, String email, boolean acceptToC) {

        logMatchingElements(By.xpath("//select")); 
        logMatchingElements(By.xpath("//input")); 
        logMatchingElements(By.xpath("//option")); 

        wd.findElement(By.xpath("//option[@value='"+title+"']")).setSelected();
        wd.findElement(By.xpath("//input[@name = 'charityContactDVO.firstname']")).sendKeys(firstName);
        wd.findElement(By.xpath("//input[@name = 'charityContactDVO.lastname']")).sendKeys(lastName);
        wd.findElement(By.xpath("//input[@name = 'charityContactDVO.occupation']")).sendKeys(occupation);
        wd.findElement(By.xpath("//input[@name = 'charityContactDVO.telephoneNumber']")).sendKeys(telephoneNumber);
        wd.findElement(By.xpath("//input[@name = 'charityContactDVO.emailAddress']")).sendKeys(email);
        wd.findElement(By.xpath("//input[@name = 'charityContactDVO.acceptTermsAndCondition']")).setSelected();
        wd.findElement(By.xpath("//input[@title='Next']")).click();

        return GretnaPageFactory.initElements(wd, CharityRegistrationSecurityDetailsPage.class);
        
    }
     
}
