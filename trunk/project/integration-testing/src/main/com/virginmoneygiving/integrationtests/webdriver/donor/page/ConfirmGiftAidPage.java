package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is display Confirmation Tab.
 * 
 * @author Yogesh Salunkhe
 */
public class ConfirmGiftAidPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public ConfirmGiftAidPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Donate | Gift Aid Declaration");
    }
    
    /**
     * This method will display the summary page containing details related to the donation for confirmation.
     *
     * @return {@link ConfirmationPage} instance.
     */
    public ConfirmationPage confirmDonation() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[3]/input[@id='giftAidYes']")).setSelected();

        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[5]/input")).click();
        
        wd.findElement(By.xpath("//input[@type='image' and @title='Next']")).click();
        
        return GretnaPageFactory.initElements(wd, ConfirmationPage.class);
    } 
    
    /**
     * This method will display the summary page containing details related to the donation for confirmation.
     *
     * @return {@link ConfirmationPage} instance.
     */
    public ConfirmationPageForRegisteredUser confirmDonationForRegisteredUser() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[3]/input[@id='giftAidYes']")).setSelected();

        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[5]/input")).click();
        
        wd.findElement(By.xpath("//input[@type='image' and @title='Next']")).click();
        
        return GretnaPageFactory.initElements(wd, ConfirmationPageForRegisteredUser.class);
    } 
}
