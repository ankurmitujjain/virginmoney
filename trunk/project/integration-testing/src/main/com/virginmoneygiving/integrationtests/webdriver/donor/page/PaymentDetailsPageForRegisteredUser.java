package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.PaymentDetails;

/**
 * This class is display Gift Aid tab.
 * 
 * @author Yogesh Salunkhe
 */
public class PaymentDetailsPageForRegisteredUser extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public PaymentDetailsPageForRegisteredUser(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Donate | Card Payment");
    }
    
    
    /**
     * This method will display the gift aid details page.
     *
     * @return {@link GiftAidPage} instance.
     */
    public GiftAidPage displayGiftAid(PaymentDetails paymentDetails) {
        
        // Find the location select box. Iterate through values and set the selected value as
        //second option in the drop down. 
        WebElement locationSelectBox = wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[1]/span[@id='cardType']/select[@id='cardTypeSelection']"));
        
        List<WebElement> cardOptions = locationSelectBox.findElements(By.tagName("option"));
        int breakCounter = 2;
        int loopCounter = 1;
        
        for (WebElement option : cardOptions) {         
            if(loopCounter == breakCounter) {
                option.setSelected();
                break;
            }               
            loopCounter ++; 
            
        }
        
        // Find the location select box. Iterate through values and set the selected value as
        //second option in the drop down. 
//        WebElement countrySelectBox = wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[8]/select[@id='countryOfResidence']"));
//        
//        List<WebElement> countryOptions = countrySelectBox.findElements(By.tagName("option"));
//        int breakCounter1 = 231;
//        int loopCounter1 = 1;
//        
//        for (WebElement option : countryOptions) {         
//            if(loopCounter1 == breakCounter1) {
//                option.setSelected();
//                break;
//            }               
//            loopCounter1 ++; 
//            
//        }
        
        
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[2]/input[@id='cardHolderName']")).
        //sendKeys(paymentDetails.cardHolderName);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[3]/input[@id='cardNumber']")).
        sendKeys(paymentDetails.cardNumber);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[4]/input[@id='validFromMonth']")).
        sendKeys(paymentDetails.validFrom1);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[4]/input[@id='validFromYear']")).
        sendKeys(paymentDetails.validFrom2);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[5]/input[@id='expiryMonth']")).
        sendKeys(paymentDetails.expiryDate1);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[5]/input[@id='expiryYear']")).
        sendKeys(paymentDetails.expiryDate2);

        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[6]/input[@id='issueNumber']")).
        sendKeys(paymentDetails.issueNumber);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[7]/input[@id='securityCode']")).
        sendKeys(paymentDetails.securityCode);
        
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/noscript/div[@id='displayAddressDataForNonJs']/div/p[1]/input[@id='regBuildingNmNo']")).
        //sendKeys(paymentDetails.addressLine1);
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/noscript/div[@id='displayAddressDataForNonJs']/div/p[3]/input[@id='vm-payment-details_paymentDetailsDVO_address_city']")).
        //sendKeys(paymentDetails.city);
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/noscript/div[@id='displayAddressDataForNonJs']/div/p[4]/input[@id='vm-payment-details_paymentDetailsDVO_address_county']")).
        sendKeys(paymentDetails.county);
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/noscript/div[@id='displayAddressDataForNonJs']/div/p[5]/input[@id='regPostCode']")).
        //sendKeys(paymentDetails.postCode);        
        
        //wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/div[1]/p[9]/input[@id='emailAddress']")).
        //sendKeys(paymentDetails.email);
       wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div/form[@id='vm-payment-details']/p/input[@id='vm-payment-details_0']")).click();
        //wd.findElement(By.xpath("//input[@type='image' and @title='Next']")).click();

        return GretnaPageFactory.initElements(wd, GiftAidPage.class);
    } 
}
