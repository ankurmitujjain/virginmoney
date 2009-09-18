/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.operations.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * Web driver class to represent charity search page
 *
 */
public class SelectCharityPage extends GretnaWebDriverPage {

	 public SelectCharityPage(WebDriver webDriver) {
	        super(webDriver,"Virgin Money Giving | Operations | Search Results");
	    }	
	 
	    /*
		 * Method to select charity link for charity listed
		 * @return CharityRegistrationSummaryPage.class
		 */
	 public CharitySummaryPage selectCharity(){
			
		 logMatchingElements(By.xpath("//input")); 
		 wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-primary']/div[2]/div[@id='vm-create-page-two-matching']/table[@id='charity']/tbody/tr/td[1]/a")).click();
	
		 return GretnaPageFactory.initElements(getWebDriver(),CharitySummaryPage.class);
		
	}  
	 
	 
	 
	 
	 
	 
}
