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
public class DisplayFundraiserEmailDonor extends GretnaWebDriverPage {

    public DisplayFundraiserEmailDonor(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Create Your Fundraising Page");
    }

    /**
     * Display fundraiser thank you.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the display fundraiser thank you
     */
    public DisplayFundraiserThankYou displayFundraiserThankYou(FundraiserRegistrationDetails fundraiserRegDetails) {

        logMatchingElements(By.xpath("//textarea"));

        wd.findElement(By.xpath("//textarea[@name = 'personalComment']")).sendKeys(fundraiserRegDetails.fundraiserDetails.personalComment);
        // Trigger Next button submit.
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/form[@id='emailDonors']/div[2]/input[2]")).click();

        return GretnaPageFactory.initElements(wd, DisplayFundraiserThankYou.class);
    }
}
