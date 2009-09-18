/**
 *
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityDescription;

/**
 * @author jallen
 *
 */
public class CharityRegistrationSummaryPage extends GretnaWebDriverPage {


    public CharityRegistrationSummaryPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Registration | Summary" );
    }

    public CharityRegistrationSetCategoryPage goToSetCategoriesPage() {
        wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/ul[3]/li/a")).click();
        return GretnaPageFactory.initElements(wd, CharityRegistrationSetCategoryPage.class);

    }

    public CharityRegistrationSetDescriptionPage goToSetDescriptionPage() {
        wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/ul[4]/li/a")).click();
        return GretnaPageFactory.initElements(wd, CharityRegistrationSetDescriptionPage.class);
    }

    public CharityRegistrationSetLogoPage goToSetLogoPage() {
        wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/ul[5]/li/a")).click();
        return GretnaPageFactory.initElements(wd, CharityRegistrationSetLogoPage.class);
    }

    public CharityRegistrationSetMoreDetailsPage goToSetDetailsPage() {
        wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/ul[6]/li/a")).click();
        return GretnaPageFactory.initElements(wd, CharityRegistrationSetMoreDetailsPage.class);
    }

    public CharityRegistrationSetTrusteesPage goToTrusteeDetailsPage() {
        wd.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/ul[7]/li/a")).click();
        return GretnaPageFactory.initElements(wd, CharityRegistrationSetTrusteesPage.class);
    }



    public CharityDescription goToDescriptionPage() {
        wd.findElement(By.xpath("html/body/div[@id='container']/div[@id='content']/div/div[@id='content-sidebar']/div[@id='vm-categorynav']/div[1]/div/ul/li[8]/fieldset/ul[@id='accountMaintenance']/li[6]/a")).click();
        return GretnaPageFactory.initElements(wd, CharityDescription.class);
    }


}
