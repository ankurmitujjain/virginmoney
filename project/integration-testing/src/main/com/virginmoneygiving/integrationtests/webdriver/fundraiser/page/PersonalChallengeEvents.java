package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * The Class PersonalChallengeEvents.
 * 
 * @author manishs
 */
public class PersonalChallengeEvents extends GretnaWebDriverPage {
	
    /**
     * Instantiates a new personal challenge events.
     * 
     * @param webDriver the web driver
     */
    public PersonalChallengeEvents(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Fundraising | My Personal Challenge" );
    }
    
    /**
     * Display fundraiser charity selection.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the fundraiser charity selection
     */
    public FundraiserCharitySelection displayFundraiserCharitySelection(FundraiserRegistrationDetails fundraiserRegDetails) {
    	
    	logMatchingElements(By.xpath("//radio"));
    	logMatchingElements(By.xpath("//input"));


      wd.findElement(By.xpath("//input[@type='radio' and @name='personalChallengeEvents.activityRaisedMoney' and @value='029']")).setSelected();
	  wd.findElement(By.xpath("//input[@type='text' and @name = 'personalChallengeEvents.eventCompletionDay']")).sendKeys(fundraiserRegDetails.fundraiserDetails.eventCompletionDay);
	  wd.findElement(By.xpath("//input[@type='text' and @name = 'personalChallengeEvents.eventCompletionMonth']")).sendKeys(fundraiserRegDetails.fundraiserDetails.eventCompletionMonth);
	  wd.findElement(By.xpath("//input[@type='text' and @name = 'personalChallengeEvents.eventCompletionYear']")).sendKeys(fundraiserRegDetails.fundraiserDetails.eventCompletionYear);
  	  wd.findElement(By.xpath("//input[@type='radio' and @name='personalChallengeEvents.fundraisingGroup' and @value='I']")).setSelected();

      // Trigger Next button submit.
  	wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[1]/div[@id='fur0040']/form[@id='personalChallengeEventsSave']/div/div[4]/input")).click();


        return GretnaPageFactory.initElements(wd, FundraiserCharitySelection.class);
    } 
}
