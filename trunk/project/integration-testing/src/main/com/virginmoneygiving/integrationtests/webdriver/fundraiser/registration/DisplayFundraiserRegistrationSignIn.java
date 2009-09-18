package com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

public class DisplayFundraiserRegistrationSignIn extends GretnaWebDriverPage {
	
    public DisplayFundraiserRegistrationSignIn(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Fundraising | Sign-in / Register" );
    }
    
    public DisplayFundraiserDetails displayFundraiserDetails(FundraiserRegistrationDetails fundraiserRegDetails) {
    	
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='fur0101']/div/form[@id='vm-register-cha0010']/div[@id='content-secondary']/div/a/img")).click();
         
        return GretnaPageFactory.initElements(wd, DisplayFundraiserDetails.class);
      }
}
