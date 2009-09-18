/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.charity.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author jallen
 *
 */
public class CharityRegistrationSetCategoryPage extends GretnaWebDriverPage {

    public static String CATEGORY_ID_ANIMALS_THAT_HELP_PEOPLE = "5";
    public static String CATEGORY_ID_CHILD_PROTECTION = "10";
    
    public CharityRegistrationSetCategoryPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Registration | Charity Category");
    }

    public CharityRegistrationSummaryPage setCategories(String[] ids) {
        for ( String id : ids ) {
            wd.findElement(By.xpath("//*[@id='id"+id+"']")).setSelected();
        }
        wd.findElement(By.xpath("//*[@id='saveId']")).click();
        return GretnaPageFactory.initElements(getWebDriver(), CharityRegistrationSummaryPage.class);        
    }
        
}
