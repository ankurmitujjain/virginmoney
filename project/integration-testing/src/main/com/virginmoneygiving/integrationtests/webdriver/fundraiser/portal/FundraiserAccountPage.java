/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.fundraiser.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author saurabhh
 * Java class to represent fundraiser account page
 */
public class FundraiserAccountPage extends GretnaWebDriverPage {

	/**
	 * @param webDriver
	 * @param pageTitle
	 */
	public FundraiserAccountPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving");
    }
	
	
	/**
	 * Method to select sign out link on Account page 
	 */
	public void signOut(){
		
      wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='header']/div[@id='site-controls']/a[3]")).click();
		 
      }	
		
		
	

}
