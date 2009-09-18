/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;

/**
 * @author saurabhh
 * Charity detials page
 */
public class CharityDetails extends GretnaWebDriverPage {

	
	 public CharityDetails(WebDriver webDriver) {
	        super(webDriver, "Virgin Money Giving | Charity Account | Charity Details");
	    }		
	
	 
     public CharityDetailsUpdated alterCharityDetails(CharityRegistrationDetails charityRegistrationDetails){
    	 
    	 wd.findElement(By.xpath("//input[@name = 'charityDetailsVO.commonName']")).sendKeys(charityRegistrationDetails.charityDetails.commonName);
    	 wd.findElement(By.xpath("//textarea[@name = 'charityDetailsVO.abbreviations']")).sendKeys(charityRegistrationDetails.charityDetails.abbreviations);
    	 wd.findElement(By.xpath("//input[@name = 'charityDetailsVO.accountingPeriodEndDateDay']")).sendKeys(charityRegistrationDetails.charityDetails.accountingEndDateDay);
    	 wd.findElement(By.xpath("//input[@name = 'charityDetailsVO.accountingPeriodEndDateMonth']")).sendKeys(charityRegistrationDetails.charityDetails.accountingEndDateMonth);
    	 wd.findElement(By.xpath("//input[@name = 'charityDetailsVO.accountingPeriodEndDateYear']")).sendKeys(charityRegistrationDetails.charityDetails.accountingEndDateYear);
    	 wd.findElement(By.xpath("//input[@title='Save']")).click();		
    	 
     return GretnaPageFactory.initElements(getWebDriver(),CharityDetailsUpdated.class);		 
    	 
     } 	 
     
     
    
	 
	 
	 
}
