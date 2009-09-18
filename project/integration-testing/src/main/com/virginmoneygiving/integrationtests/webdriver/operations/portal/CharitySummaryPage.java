package com.virginmoneygiving.integrationtests.webdriver.operations.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityAccountPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityAddressDetails;

/**
 * Web driver class to represent charity summary page
 * @author saurabhh
 *
 */
public class CharitySummaryPage extends GretnaWebDriverPage {
	
	 
	 public CharitySummaryPage(WebDriver webDriver) {
	        super(webDriver,"Virgin Money Giving | Operations | Registration Summary");
	   }

	 /**
	  * Method to click Charity portal link
	  * @return CharityAccountPage.class
	  */
	 public CharityAccountPage clickOnCharityPortalLink(){
			
		 wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div/div/div/ul/li[5]/a")).click();	
	     
	     return GretnaPageFactory.initElements(getWebDriver(),CharityAccountPage.class);
		
	} 
	
	 
	 
}
