package com.virginmoneygiving.integrationtests.webdriver.operations.portal;

/**
 * Web driver class to represent Operations portal page
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

public class OperationsPortalPage extends GretnaWebDriverPage {

	 public OperationsPortalPage(WebDriver webDriver) {
	        super(webDriver,"Virgin Money Giving | Operations | Search");
	    }		
	
	 /*
	 * Method to select a charity using search text "Auto Test" 
	 * @param String charityName
	 * @return SelectCharityPage.class
	 * 
	 */
	 public SelectCharityPage searchForCharity(String charityName){
			
		 logMatchingElements(By.xpath("//input")); 
		 wd.findElement(By.xpath("//input[@type='radio' and @name='accountSearch.searchFor' and @value='Charity']")).setSelected();
		 wd.findElement(By.xpath("//input[@name = 'accountSearch.name']")).sendKeys(charityName);
	     wd.findElement(By.xpath("//input[@title='Search']")).click();	
	     
	     return GretnaPageFactory.initElements(getWebDriver(),SelectCharityPage.class);
		
	}  
	 
	 
	
}
