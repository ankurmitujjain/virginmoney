package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is make the Donation.
 * 
 * @author Yogesh Salunkhe
 */
public class ConfirmationPageForRegisteredUser extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public ConfirmationPageForRegisteredUser(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving");
    }
    
    /**
     * This method is used to make the donation and is the last step in the journey of single donation for unregistered user.
     *
     * @return {@link GiftAidPage} instance.
     */
    public void makeDonation() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[2]/input[@id='acceptTermsAndCondition']")).setSelected();
        
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[7]/input")).click();
        
        wd.findElement(By.xpath("//input[@type='image' and @title='Make donation']")).click();

    } 
}
