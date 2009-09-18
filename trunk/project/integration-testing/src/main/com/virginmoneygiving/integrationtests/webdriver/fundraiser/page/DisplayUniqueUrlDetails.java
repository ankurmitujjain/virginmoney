package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * The Class DisplayUniqueUrlDetails.
 * 
 * @author manishs
 */
public class DisplayUniqueUrlDetails extends GretnaWebDriverPage {
	
    /**
     * Instantiates a new display unique url details.
     * 
     * @param webDriver the web driver
     */
    public DisplayUniqueUrlDetails(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Choose URL"); 
    }
    
    /**
     * Display fundraiser page intro.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the display fundraiser page intro
     */
    public DisplayFundraiserPageIntro displayFundraiserPageIntro(FundraiserRegistrationDetails fundraiserRegDetails) {
    	
    	logMatchingElements(By.xpath("//input"));


	  wd.findElement(By.xpath("//input[@name = 'createUrlBean.profileName']")).sendKeys(fundraiserRegDetails.fundraiserDetails.fundraiserUrl);

      // Trigger Next button submit.
	  wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div/form[@id='uniqueUrlForm']/div[4]/input")).click();


        return GretnaPageFactory.initElements(wd, DisplayFundraiserPageIntro.class);
    }     
    
}
