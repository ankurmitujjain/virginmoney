/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationSummaryPage;
import com.virginmoneygiving.integrationtests.webdriver.operations.portal.OperationsPortalPage;

/**
 * @author saurabhh
 * Select charity colors
 */
public class CharityPageColours extends GretnaWebDriverPage {
	
/** RGB value for Cyan color*/	
private static final String CYAN="00FFFF";	
	
	 public CharityPageColours(WebDriver webDriver) {
	        super(webDriver, "Virgin Money Giving | Charity Account | Charity Page Colours");
	    }	
	
	
//	 public CharityRegistrationSummaryPage alterCharityPageColours(){
	 public CharityPageColours alterCharityPageColours(){

		 
		 logMatchingElements(By.xpath("//input")); 
		 logMatchingElements(By.xpath("//button")); 
		
		 wd.findElement(By.xpath("//input[@id = 'cp1_Hex']")).sendKeys(CYAN);
	     wd.findElement(By.xpath("//input[@title='Save']")).click();
	     
	     
	     return GretnaPageFactory.initElements(getWebDriver(),CharityPageColours.class);
//	     return GretnaPageFactory.initElements(getWebDriver(),CharityRegistrationSummaryPage.class);
		 
		 
	 }
	 

	 
	 
	 
}
