package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * The Class DisplayFundraiserPageIntro.
 * 
 * @author manishs
 */
public class DisplayFundraiserPageIntro extends GretnaWebDriverPage {

    /**
     * Instantiates a new display fundraiser page intro.
     * 
     * @param webDriver the web driver
     */
    public DisplayFundraiserPageIntro(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Create Page Introduction");
    }

    /**
     * Display fundraiser page preview.
     * 
     * @param fundraiserRegDetails the fundraiser reg details
     * 
     * @return the display fundraiser page preview
     */
    public DisplayFundraiserPagePreview displayFundraiserPagePreview(FundraiserRegistrationDetails fundraiserRegDetails) {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div/div[@id='content-primary']/div[2]/a/img")).click();

        return GretnaPageFactory.initElements(wd, DisplayFundraiserPagePreview.class);
    }

}
