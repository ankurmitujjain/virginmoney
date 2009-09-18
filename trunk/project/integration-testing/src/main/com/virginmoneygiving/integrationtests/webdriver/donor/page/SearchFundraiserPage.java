package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * This class is display charity Home page .
 * 
 * @author Yogesh Salunkhe
 */
public class SearchFundraiserPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public SearchFundraiserPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Search Results");
    }
    
    /**
     * This method will display the charity home page of the selected charity.
     *
     * @return {@link CharityHomePage} instance.
     */
    public SponsorshipDetailsPage displayFundraiserResults() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/table[@id='result']/tbody/tr[1]/td/ul/li/a/img")).click();
        return GretnaPageFactory.initElements(wd, SponsorshipDetailsPage.class);
    } 
    
    /**
     * This method will display the charity home page of the selected charity.
     *
     * @return {@link CharityHomePage} instance.
     */
    public SearchFundraiserPage searchFundraisers(FundraiserRegistrationDetails fundraiserRegDetails) {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form/p[1]/input[@id='checkboxFundraiserActivity']")).setSelected();
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form/p[2]/input[@id='name_1']")).
        sendKeys(fundraiserRegDetails.administratorDetails.firstName);
        
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form/p[2]/input[2]")).click();
        
        return GretnaPageFactory.initElements(wd, SearchFundraiserPage.class);
    } 
}
