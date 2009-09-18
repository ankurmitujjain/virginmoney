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
 * Represents charity category selection page
 */
public class CharityCategory extends GretnaWebDriverPage {
	
	
	public CharityCategory(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Account | Charity Category");
    }	
	
	
public 	CharityCategory altercharityCategory(CharityRegistrationDetails charityRegistrationDetails){

	for ( String id : charityRegistrationDetails.charityDetails.categories ) {
        wd.findElement(By.xpath("//*[@id='id"+id+"']")).setSelected();
        }
	
	 wd.findElement(By.xpath("//input[@title='Save']")).click();	
	
return GretnaPageFactory.initElements(getWebDriver(),CharityCategory.class);		
	
}
	

}
