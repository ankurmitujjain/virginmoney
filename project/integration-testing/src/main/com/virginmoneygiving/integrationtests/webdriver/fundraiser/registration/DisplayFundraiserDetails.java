/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * @author manishs
 *
 */
public class DisplayFundraiserDetails extends GretnaWebDriverPage {
	
    public DisplayFundraiserDetails(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Your Details"); 
    }

    public DisplayAccountSecurityDetails displayAccountSecurityDetails(FundraiserRegistrationDetails fundraiserRegDetails) {
    	
        logMatchingElements(By.xpath("//select")); 
        logMatchingElements(By.xpath("//input")); 
        logMatchingElements(By.xpath("//option"));
        String title = fundraiserRegDetails.administratorDetails.title;
  	    wd.findElement(By.xpath("//option[@value='"+title+"']")).setSelected();
    	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.forename']")).sendKeys(fundraiserRegDetails.administratorDetails.firstName);
    	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.surname']")).sendKeys(fundraiserRegDetails.administratorDetails.lastName);
  	    
    	// Find the location select box. Iterate through values and set the selected value as
    	//second option in the drop down. 
    	WebElement locationSelectBox = wd.findElement(By.xpath("//select[@name='fundraiserDetailsBean.countryCode']"));
    	
    	List<WebElement> locationOptions = locationSelectBox.findElements(By.tagName("option"));
    	int breakCounter = 2;
    	int loopCounter = 1;
    	
    	for (WebElement option : locationOptions) {    	    
    	    if(loopCounter == breakCounter) {
    	    	option.setSelected();
    	    	break;
    	    }   	    	
    	    loopCounter ++;	
    	    
    	}
        String preferedTelephoneType = fundraiserRegDetails.fundraiserDetails.preferredTelephoneNumberType;
    	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.buildingNameOrNumber']")).sendKeys(fundraiserRegDetails.fundraiserDetails.buildingNameAndNumber);
    	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.fundraiserAddress.city']")).sendKeys(fundraiserRegDetails.fundraiserDetails.town);
    	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.fundraiserAddress.country']")).sendKeys(fundraiserRegDetails.fundraiserDetails.county);
       	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.postcode']")).sendKeys(fundraiserRegDetails.fundraiserDetails.postCode);
       	wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.preferredTelephoneNumber']")).sendKeys(fundraiserRegDetails.fundraiserDetails.preferredTelephoneNumber);
  	    //wd.findElement(By.xpath("//option[@value = 'L']")).sendKeys(fundraiserRegDetails.fundraiserDetails.preferredTelephoneNumberType);
  	    wd.findElement(By.xpath("//option[@value='"+preferedTelephoneType+"']")).setSelected();
  	    
  	    wd.findElement(By.xpath("//input[@name = 'fundraiserDetailsBean.emailAddress']")).sendKeys(fundraiserRegDetails.fundraiserDetails.emailAddress);
    	wd.findElement(By.xpath("//input[@type='checkbox' and @name='termsAndConditionsAccepted']")).setSelected();
    	wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='fur210']/form[@id='fundraiserdetailsForm']/div[4]/input")).click();
        return GretnaPageFactory.initElements(wd, DisplayAccountSecurityDetails.class);
      }
}
