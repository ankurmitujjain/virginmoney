/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author saurabhh
 * On updating chairty details, we are directed to this page in DDA mode
 */
public class CharityDetailsUpdated extends GretnaWebDriverPage {
	
	
	 public CharityDetailsUpdated(WebDriver webDriver) {
	        super(webDriver, "Virgin Money Giving");
	    }		
	 
	 
	 public CharityCategory clickOnCharityCategoryLink(){
    	 
	     wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[8]/fieldset/ul[@id='accountMaintenance']/li[8]/a")).click();		 
	    	 
	     return GretnaPageFactory.initElements(getWebDriver(),CharityCategory.class);		 	 
	    	 
	     }	 
	
	
	

}
