package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.portal.FundraiserAccountPage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.PasswordDetailsPage;

/**
 * The Class DisplayFundraiserThankYou.
 * 
 * @author manishs
 */
public class DisplayFundraiserThankYou extends GretnaWebDriverPage {

    /**
     * Instantiates a new display fundraiser thank you.
     * 
     * @param webDriver the web driver
     */
    public DisplayFundraiserThankYou(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Alerting and Thank You Message");
    }

    /**
     * Display fundraiser portal home.
     */
    public FundraiserAccountPage displayFundraiserPortalHome() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div/div[@id='fur0130']/div[2]/a/img")).click();
        return GretnaPageFactory.initElements(getWebDriver(), FundraiserAccountPage.class);

    }
}
