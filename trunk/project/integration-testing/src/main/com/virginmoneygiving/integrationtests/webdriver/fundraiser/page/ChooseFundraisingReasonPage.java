package com.virginmoneygiving.integrationtests.webdriver.fundraiser.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * The Class ChooseFundraisingReasonPage.
 * 
 * @author manish sinha
 */
public class ChooseFundraisingReasonPage extends GretnaWebDriverPage {

    /**
     * Instantiates a new choose fundraising reason page.
     * 
     * @param webDriver the web driver
     */
    public ChooseFundraisingReasonPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Fundraising | Start Fundraising");
    }

    /**
     * Display personal challenge events.
     * 
     * @return the personal challenge events
     */
    public PersonalChallengeEvents displayPersonalChallengeEvents() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='fundraiser-column1']/div/div[2]/span/a/img")).click();

        return GretnaPageFactory.initElements(wd, PersonalChallengeEvents.class);
    }

}
