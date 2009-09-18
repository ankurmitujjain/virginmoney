package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * The Class DisplaySearchCharity.
 * 
 * @author manishs
 */
public class DisplaySearchCharity extends GretnaWebDriverPage {
	
    /**
     * Instantiates a new display search charity.
     * 
     * @param webDriver the web driver
     */
    public DisplaySearchCharity(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Search Results" );
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

      wd.findElement(By.xpath("//input[@type='radio' and @name='charityId' and @value='1']")).setSelected();
	  wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div/div[@id='content-primary']/form[@id='searchCharitiesDisplay']/div[2]/input[@id='searchCharitiesDisplay_searchCharitiesSave']")).click();

       return GretnaPageFactory.initElements(wd, FundraiserCharitySelection.class);
    }
    //TODO need to remove once application bug is fixed.
    //Application bug: Not giving result when search first time.
    /**
     * Display charity search.
     * 
     * @return the display search charity
     */
    public DisplaySearchCharity displayCharitySearch(){
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div/div[@id='content-primary']/form[@id='searchCharitiesDisplay']/p[2]/input[@id='searchCharitiesDisplay_0']")).click();
        return GretnaPageFactory.initElements(wd, DisplaySearchCharity.class);
    }
}
