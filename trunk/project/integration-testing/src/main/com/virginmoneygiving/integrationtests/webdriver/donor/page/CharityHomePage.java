package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is display Sponsorship Details tab.
 *  
 * @author Yogesh Salunkhe
 */
public class CharityHomePage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public CharityHomePage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Auto Test Charity 001");
    }
    
    /**
     * This method will display the sponsorship details page which contains amount and frequency of the donation.
     *
     * @return {@link SponsorshipDetailsPage} instance.
     */
    public SponsorshipDetailsPage displaySponsorshipDetails() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='vm-create-event-homepage60-col1']/div/a/img")).click();

        return GretnaPageFactory.initElements(wd, SponsorshipDetailsPage.class);
    } 
}
