package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.GlobalSignInPage;

/**
 * This class is display Payment tab.
 * 
 * @author Yogesh Salunkhe
 */
public class SignInPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public SignInPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Donate | Sign In");
    }
    
    /**
     * This method will display the payment details page which contains cardtype, cardholder name, card number
     * expiry date, country, county, city, postcode, security code and email.
     *
     * @return {@link PaymentDetailsPage} instance.
     */
    public PaymentDetailsPage displayPaymentDetails() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-signin-info']/div[2]/p[2]/a/img")).click();
        
        return GretnaPageFactory.initElements(wd, PaymentDetailsPage.class);
    } 
    
    /**
     *
     * @return {@link GlobalSignInPage} instance.
     */
    public GlobalSignInPage fundraiserSignIn() {

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-signin-info']/div[1]/p[3]/a[@id='vm-signin-info_']/img")).click();
        
        return GretnaPageFactory.initElements(wd, GlobalSignInPage.class);
    } 
}
