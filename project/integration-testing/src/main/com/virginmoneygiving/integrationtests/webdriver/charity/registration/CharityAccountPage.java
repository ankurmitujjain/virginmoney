/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityAddressDetails;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityPageColours;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityDescription;

/**
 * @author saurabhh
 *
 */
public class CharityAccountPage extends GretnaWebDriverPage {
	
	 public CharityAccountPage(WebDriver webDriver) {
	        super(webDriver,"Virgin Money Giving | Charity Account");
	   }	
	 
	 
	 
         public CharityPageColours  clickOnPageColoursLink(){
		
		 wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[8]/fieldset/ul[@id='accountMaintenance']/li[5]/a")).click();	
	     
	     return GretnaPageFactory.initElements(getWebDriver(),CharityPageColours.class);
		
}
 
         public CharityDescription clickOnCharityDescriptionLink(){

		 wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[8]/fieldset/ul[@id='accountMaintenance']/li[6]/a")).click();

	     return GretnaPageFactory.initElements(getWebDriver(),CharityDescription.class);

}



}


