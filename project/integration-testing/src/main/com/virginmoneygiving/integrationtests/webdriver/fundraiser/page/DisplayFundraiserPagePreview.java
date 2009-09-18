package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * @author manishs
 * 
 */
public class DisplayFundraiserPagePreview extends GretnaWebDriverPage {

    public DisplayFundraiserPagePreview(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Create Your Fundraising Page");
    }

    /**
     * Display fundraiser email donor.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the display fundraiser email donor
     */
    public DisplayFundraiserEmailDonor displayFundraiserEmailDonor(FundraiserRegistrationDetails fundraiserRegDetails) {

        wd.findElement(By.xpath("//input[@name = 'pageDetails.title']")).sendKeys(fundraiserRegDetails.fundraiserDetails.pageTitle);

        // Trigger Next button submit.
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/form[@id='createFundraiserPage']/div[2]/input")).click();

        return GretnaPageFactory.initElements(wd, DisplayFundraiserEmailDonor.class);
    }

}
