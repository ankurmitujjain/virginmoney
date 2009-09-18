package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is display charity Home page .
 * 
 * @author Yogesh Salunkhe
 */
public class CharitiesPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public CharitiesPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Categories |Animals|Animals that help (e.g. guide dogs)");
    }
    
    /**
     * This method will display the charity home page of the selected charity.
     *
     * @return {@link CharityHomePage} instance.
     */
    public CharityHomePage displayCharityHome() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div[2]/table[@id='charity']/tbody/tr/td/ul/li/div[2]/h2/a")).click();

        return GretnaPageFactory.initElements(wd, CharityHomePage.class);
    } 
}
