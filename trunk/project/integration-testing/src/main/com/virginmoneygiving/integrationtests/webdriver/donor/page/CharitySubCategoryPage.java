/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * @author Yogesh Salunkhe
 */
public class CharitySubCategoryPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public CharitySubCategoryPage(WebDriver webDriver) {
        super(webDriver, "charitysubcategories");
    }
    
    /**
     * This method will display the charities under the selected sub-category.
     *
     * @return {@link CharitiesPage} instance.
     */
    public CharitiesPage displayCharities() {
        
        logMatchingElements(By.xpath("//a"));
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-primary']/div[2]/div[@id='vm-sub-category-listing']/ul/li[1]/h4/a")).click();
        

        return GretnaPageFactory.initElements(wd, CharitiesPage.class);
    } 
}
