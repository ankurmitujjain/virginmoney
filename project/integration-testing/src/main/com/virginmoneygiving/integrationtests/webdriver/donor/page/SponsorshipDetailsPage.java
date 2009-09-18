package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.PaymentDetails;

/**
 * This class is display Sign In tab.
 * 
 * @author Yogesh Salunkhe
 */
public class SponsorshipDetailsPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public SponsorshipDetailsPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Donate | Donation Amount");
    }
    
    /**
     * This method will display the donor registration sign in page.
     *
     * @return {@link SignInPage} instance.
     */
    public SignInPage displayDonorRegistrationSignInPage(PaymentDetails paymentDetails) {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/div[@id='vm-sponsor-info']/p[2]/input[@id='other']")).setSelected();
        
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/div[@id='vm-sponsor-info']/div[1]/input[@id='otherAmount']")).
        sendKeys(paymentDetails.amount);
        
        
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[6]/input")).click();
        wd.findElement(By.xpath("//input[@type='image' and @title='Next']")).click();

        return GretnaPageFactory.initElements(wd, SignInPage.class);
    } 
    
    /**
     * This method will display the donor registration sign in page.
     *
     * @return {@link SignInPage} instance.
     */
    public SignInPage displayDonorRegistrationSignInPageForRegisteredUser(PaymentDetails paymentDetails) {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/div[1]/p[2]/input[@id='other']")).setSelected();
        
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/div[1]/div/input[@id='otherAmount']")).
        sendKeys(paymentDetails.amount);
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/div[3]/p[2]/input[@id='fundraserIncludeMessage']")).setSelected();
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/div[3]/p[3]/textarea[@id='fundraiserMyMessage']")).
        //sendKeys(paymentDetails.message);
        wd.findElement(By.xpath("//textarea[@name = 'donationDetailsDVO.message']")).sendKeys(paymentDetails.message); 
        
        
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/form[@id='vm-sponsor-info']/p[6]/input")).click();
        wd.findElement(By.xpath("//input[@type='image' and @title='Next']")).click();

        return GretnaPageFactory.initElements(wd, SignInPage.class);
    } 
}
