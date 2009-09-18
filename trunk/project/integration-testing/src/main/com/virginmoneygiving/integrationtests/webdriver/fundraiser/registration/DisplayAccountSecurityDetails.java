/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.DisplayUniqueUrlDetails;

/**
 * @author manishs
 *
 */
public class DisplayAccountSecurityDetails extends GretnaWebDriverPage {
	
    public DisplayAccountSecurityDetails(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Your Security Details"); 
    }
    
    public DisplayUniqueUrlDetails displayUniqueUrlDetails(FundraiserRegistrationDetails fundraiserRegDetails) {
    	
    	logMatchingElements(By.xpath("//input"));


	  wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.dayOfBirth']")).sendKeys(fundraiserRegDetails.securityDetails.dobDay);
	  wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.monthOfBirth']")).sendKeys(fundraiserRegDetails.securityDetails.dobMonth);
	  wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.yearOfBirth']")).sendKeys(fundraiserRegDetails.securityDetails.dobYear);

	  wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.userPassword']")).sendKeys(fundraiserRegDetails.securityDetails.password);
	  wd.findElement(By.xpath("//input[@name = 'securityDetailsDVO.confirmPassword']")).sendKeys(fundraiserRegDetails.securityDetails.confirmPassword);

      // Trigger Next button submit.
	  wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='cha0090']/form[@id='vm-sponsor-info']/div[2]/input")).click();


        return GretnaPageFactory.initElements(wd, DisplayUniqueUrlDetails.class);
    }     
    

}
