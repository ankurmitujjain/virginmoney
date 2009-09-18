package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is display Confirm Gift Aid Page.
 * 
 * @author Yogesh Salunkhe
 */
public class GiftAidPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public GiftAidPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Donate | Gift Aid");
    }
    
    /**
     * This method will display the confirm gift aid declaration page.
     *
     * @return {@link ConfirmGiftAidPage} instance.
     */
    public ConfirmGiftAidPage confirmGiftAid() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[1]/input[@id='giftAidYes']")).setSelected();

        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[5]/input")).click();
        wd.findElement(By.xpath("//input[@type='image' and @title='Next']")).click();
        
        return GretnaPageFactory.initElements(wd, ConfirmGiftAidPage.class);
    } 
}
