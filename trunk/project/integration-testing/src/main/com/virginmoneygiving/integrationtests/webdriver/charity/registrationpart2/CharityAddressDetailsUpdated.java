package com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * 
 * @author saurabhh
 * Represents the page directed to, when you update address details in DDA mode.
 */
public class CharityAddressDetailsUpdated extends GretnaWebDriverPage{
	
	
	 public CharityAddressDetailsUpdated(WebDriver webDriver) {
	        super(webDriver, "Virgin Money Giving | Charity Account | Address Details | Updated");
	    }
	 
	 
	    public CharityPageColours clickOnPageColoursLink(){
		
		wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[8]/fieldset/ul[@id='accountMaintenance']/li[5]/a")).click();	
	     
	    return GretnaPageFactory.initElements(getWebDriver(),CharityPageColours.class);	
	
		
	} 
	 

}
