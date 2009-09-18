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
 * Class representing Charity address details page [ Used for registered and administration address ]
 */
public class CharityAddressDetails extends GretnaWebDriverPage {
	
	
	 public CharityAddressDetails(WebDriver webDriver) {
	        super(webDriver, "Virgin Money Giving | Charity Account | Address Details");
	    }
	    
	    
	    public CharityAddressDetailsUpdated alterCharityAdminsitrationAddress(CharityRegistrationDetails charityRegistrationDetails) {

	     	wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAdministrationAddress.addressLine1']")).sendKeys(charityRegistrationDetails.charityDetails.addressLine1);
	     	wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAdministrationAddress.addressLine2']")).sendKeys(charityRegistrationDetails.charityDetails.addressLine2);
	     	wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAdministrationAddress.city']")).sendKeys(charityRegistrationDetails.charityDetails.town);
	     	wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAdministrationAddress.county']")).sendKeys(charityRegistrationDetails.charityDetails.county);
	     	wd.findElement(By.xpath("//input[@name = 'charityDVO.charityAdministrationAddress.postcode']")).sendKeys(charityRegistrationDetails.charityDetails.postCode);
		    wd.findElement(By.xpath("//input[@title='Save']")).click();	

	        return GretnaPageFactory.initElements(wd, CharityAddressDetailsUpdated.class);
	    }
	    
	    
	     
	    
	    
	   
	

}
