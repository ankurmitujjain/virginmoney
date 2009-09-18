/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails.TrusteeDetails;

/**
 * @author jallen
 *
 */
public class CharityRegistrationSetTrusteesPage extends GretnaWebDriverPage {

    public CharityRegistrationSetTrusteesPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Trustee Details");
    }

    public CharityRegistrationSummaryPage setTrustees( CharityRegistrationDetails.TrusteeDetails[] trustees) {
        int count = trustees.length;
        if ( count == 0 || count > 3 ) {
            throw new IllegalStateException("Can only have between 1 and 3 trustees");
        }
        
        wd.findElement(By.xpath("//select/option[@value='"+count+"']")).setSelected();
        wd.findElement(By.xpath("//*[@id='next']")).click();
        int i = 1;
        
        for ( CharityRegistrationDetails.TrusteeDetails trustee : trustees) {
            
            wd.findElement(By.xpath("//*[@id='trustee1href" + i++ + "']")).click();

            wd.findElement(By.xpath("//option[@value='"+trustee.title+"']")).setSelected();
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_firstname\"]")).sendKeys(trustee.firstName);
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_lastname\"]")).sendKeys(trustee.lastName);
            wd.findElement(By.xpath("//*[@id=\"dob\"]")).sendKeys(trustee.dobDay);
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_dobMonth\"]")).sendKeys(trustee.dobMonth);
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_dobYear\"]")).sendKeys(trustee.dobYear);
            wd.findElement(By.xpath("//option[@value='"+trustee.countryCode+"']")).setSelected();
            wd.findElement(By.xpath("//*[@id=\"regBuildingNmNo\"]")).sendKeys(trustee.addressLine1);
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_trusteeAddressDetails_addressLine2\"]")).sendKeys(trustee.addressLine2);
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_trusteeAddressDetails_city\"]")).sendKeys(trustee.town);
            wd.findElement(By.xpath("//*[@id=\"trusteeDetailsSave_trusteeDetails_trusteeAddressDetails_county\"]")).sendKeys(trustee.county);
            wd.findElement(By.xpath("//*[@id=\"regPostCode\"]")).sendKeys(trustee.postCode);
            
            wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/form/div/div[2]/input")).click();
            
        }
        wd.findElement(By.xpath("/html/body/div/div[2]/p/a")).click();
               
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationSummaryPage.class);        
    }
}
