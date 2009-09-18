package com.virginmoneygiving.integrationtests.webdriver.donor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.PaymentDetails;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.EventSetupHomePage;

/**
 * This class is display choose a charity sub categories page for the selected category.
 * 
 * @author Yogesh Salunkhe
 *
 */
public class CharityCategoriesPage extends GretnaWebDriverPage {

    /**
     * Constructor.
     * @param webDriver {@link WebDriver} instance.
     */
    public CharityCategoriesPage(WebDriver webDriver) {
        super(webDriver, "Virgin Money Giving | Charity Categories");
    }
    
    /**
     * This method will display the subcategories for the selected category.
     *
     * @return {@link CharitySubCategoryPage} instance.
     */
    public CharitySubCategoryPage displaySubCategories(PaymentDetails paymentDetails) {

        logMatchingElements(By.xpath("//a"));
        wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='content-secondary']/div[@id='vm-category-list']/ul/li[1]/a")).click();

        return GretnaPageFactory.initElements(wd, CharitySubCategoryPage.class);
    } 
}
