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
 * Charity description page
 */
public class CharityDescription extends GretnaWebDriverPage {
	
	 public CharityDescription(WebDriver webDriver) {
	        super(webDriver, "Virgin Money Giving | Charity Account | Description");
	    }		
	
public 	CharityDescription alterCharityDescription(CharityRegistrationDetails charityRegistrationDetails){
	
	
	wd.findElement(By.xpath("//textarea[@name = 'charityDescription']")).sendKeys(charityRegistrationDetails.charityDetails.description);
    wd.findElement(By.xpath("//input[@title='Save']")).click();		
	
	
return GretnaPageFactory.initElements(getWebDriver(),CharityDescription.class);	
	
} 


    public CharityDetails clickOnCharitytDetailsLink(){
	
	wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[8]/fieldset/ul[@id='accountMaintenance']/li[7]/a")).click();	
     
    return GretnaPageFactory.initElements(getWebDriver(),CharityDetails.class);	

	
} 
	 
	 

}
