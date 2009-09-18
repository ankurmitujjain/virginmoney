package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.registration.DisplayFundraiserRegistrationSignIn;

/**
 * The Class FundraiserCharitySelection.
 * 
 * @author manishs
 */
public class FundraiserCharitySelection extends GretnaWebDriverPage {
	
    /**
     * Instantiates a new fundraiser charity selection.
     * 
     * @param webDriver the web driver
     */
    public FundraiserCharitySelection(WebDriver webDriver ) {
        super(webDriver, "Virgin Money Giving | Fundraising | Choose Charity");
    }

    /**
     * Display seach charity result.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the display search charity
     */
    public DisplaySearchCharity displaySeachCharityResult(FundraiserRegistrationDetails fundraiserRegDetails) {
    	
    	logMatchingElements(By.xpath("//radio"));
    	logMatchingElements(By.xpath("//input"));


      wd.findElement(By.xpath("//input[@type='radio' and @name='fundraisingReasonBean.howManyCharities' and @value='S']")).setSelected();
  	  wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='fur0070']/div/form[@id='fundraiserRegistrationSignInDisplay']/div[@id='content-secondary']/div[2]/div/input[@id='addCharityName']")).sendKeys(fundraiserRegDetails.fundraiserDetails.charityName);
	  wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='fur0070']/div/form[@id='fundraiserRegistrationSignInDisplay']/div[@id='content-secondary']/div[2]/div/noscript/input[@id='fundraiserRegistrationSignInDisplay_submitNJS']")).click();


       return GretnaPageFactory.initElements(wd, DisplaySearchCharity.class);
    } 
    
    /**
     * Display fundraiser registration sign in.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the display fundraiser registration sign in
     */
    public DisplayFundraiserRegistrationSignIn displayFundraiserRegistrationSignIn(FundraiserRegistrationDetails fundraiserRegDetails) {
        wd.findElement(By.xpath("//input[@type='radio' and @name='fundraisingReasonBean.charityContribution' and @value='Y']")).setSelected();
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='fur0070']/div/form[@id='fundraiserRegistrationSignInDisplay']/div[4]/input")).click();
    	
       return GretnaPageFactory.initElements(wd, DisplayFundraiserRegistrationSignIn.class);
    }
   
    /**
     * Display fundraiser page intro.
     * 
     * @return the display fundraiser page intro
     */
    public DisplayFundraiserPageIntro displayFundraiserPageIntro() {
        wd.findElement(By.xpath("//input[@type='radio' and @name='fundraisingReasonBean.charityContribution' and @value='Y']")).setSelected();
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='fur0070']/div/form[@id='fundraiserRegistrationSignInDisplay']/div[4]/input")).click();
        
       return GretnaPageFactory.initElements(wd, DisplayFundraiserPageIntro.class);
    }
    
}
