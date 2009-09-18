package com.virginmoneygiving.integrationtests.webdriver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.page.ChooseFundraisingReasonPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.CharityCategoriesPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.SearchFundraiserPage;
import com.virginmoneygiving.integrationtests.webdriver.operations.login.OperationsLoginPage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.GlobalSignInPage;

/**
 * Page factory implementation for the application.
 * <br/>
 * This class will hold utility methods to initiate test case journey's.
 *
 * @author jallen
 */
public class GretnaPageFactory extends PageFactory {

    protected static String baseUrl = "http://localhost:8080";

    protected static Map<Class, String> publicEntryPointPages = new HashMap<Class, String>();
    
    // Add your application test entry points in this map.
    static {
    	// Entry point for charity registration process.
        publicEntryPointPages.put( CharityRegistrationPage.class, "charity-web/charity/searchCharityDisplay.action" );
        
        // Entry point for fundraiser registration process.
        publicEntryPointPages.put( ChooseFundraisingReasonPage.class, "fundraiser-web/fundraiser/chooseFundraiser.action" );

        
        // Entry point for charity portal -> create event process.
        // TODO : This URL should be charity-web/charity/signInAction.action-SSL.
        publicEntryPointPages.put( GlobalSignInPage.class, "charity-web/charity/signInAction.action" );
     // Entry point for single donation for unregistered user.
        publicEntryPointPages.put( CharityCategoriesPage.class, "fundraiser-web/donate/charityCategories.action" );
        // Entry point for donation for registered fundraiser.
        publicEntryPointPages.put( SearchFundraiserPage.class, "fundraiser-web/donate/indexerSearchCharityResults.action" );
        
        /**
         * Entry point for operations web login
         */
        publicEntryPointPages.put(OperationsLoginPage.class, "operations-web/operations/accountAccessSearchDisplay.action" );

    };
        
    /**
     * This method will be used to trigger the charity registration process.
     * @param webDriver webdriver instance.
     * @return {@link CharityRegistrationPage} instance.
     */
    public static CharityRegistrationPage newRegisterCharityPage(WebDriver webDriver) {
        webDriver.get( getPageUrl(CharityRegistrationPage.class));
        CharityRegistrationPage page = new CharityRegistrationPage( webDriver );
        PageFactory.initElements(webDriver, page);
        return page;
    }
    
    /**
     * This method will be used to trigger the charity registration process.
     * @param webDriver webdriver instance.
     * @return {@link GlobalSignInPage} instance.
     */
    public static GlobalSignInPage initiateSignIn(WebDriver webDriver) {
        webDriver.get( getPageUrl(GlobalSignInPage.class));
        GlobalSignInPage singinPage = new GlobalSignInPage( webDriver );
        PageFactory.initElements(webDriver, singinPage);
        return singinPage;
    }  
    
    /**
     * This method will be used to trigger the fundraiser registration process.
     * @param webDriver webdriver instance.
     * @return {@link ChooseFundraisingReasonPage} instance.
     */
    public static ChooseFundraisingReasonPage newRegisterFundraiserPage(WebDriver webDriver) {
        webDriver.get( getPageUrl(ChooseFundraisingReasonPage.class));
        ChooseFundraisingReasonPage page = new ChooseFundraisingReasonPage(webDriver);
        PageFactory.initElements(webDriver, page);
        return page;
    }
    
    /**
     * This method will be used to trigger the single donation for unregistered user.
     * 
     * @param webDriver webdriver instance.
     * @return {@link CharityCategoriesPage} instance.
     */
    public static CharityCategoriesPage initiateCharityCategory(WebDriver webDriver) {
        webDriver.get( getPageUrl(CharityCategoriesPage.class));
        CharityCategoriesPage charityCategory = new CharityCategoriesPage( webDriver );
        PageFactory.initElements(webDriver, charityCategory);
        return charityCategory;
    }
    
    /**
     * This method will be used to trigger the donation for fundraiser.
     * 
     * @param webDriver webdriver instance.
     * @return {@link SearchFundraiserPage} instance.
     */
    public static SearchFundraiserPage searchFundraiser(WebDriver webDriver) {
        webDriver.get( getPageUrl(SearchFundraiserPage.class));
        SearchFundraiserPage searchFundraiserPage = new SearchFundraiserPage( webDriver );
        PageFactory.initElements(webDriver, searchFundraiserPage);
        return searchFundraiserPage;
    }

    /**
     * This method will be used to trigger the operations login/ Registration part 2
     * @param webDriver webdriver instance.
     * @return {@link OperationsLoginPage} instance.
     */
    public static OperationsLoginPage newOperationsLoginPage(WebDriver webDriver) {
        webDriver.get(getPageUrl(OperationsLoginPage.class));
        OperationsLoginPage page = new OperationsLoginPage(webDriver);
        PageFactory.initElements(webDriver, page);
        return page;
    }
        
    /**
     * This method is used to retrieve the page action path from entry points.
     *
     * @param clazz class instance to be retrieved from entry points.
     * @return class action URL.
     */
    public static String getPagePath(Class<?> clazz) {
        return publicEntryPointPages.get(clazz);
    }

    /**
     * This method is used to retrieve the complete URL that will be used to
     * trigger the webdriver test case execution..
     *
     * @param clazz class instance to be retrieved from entry points.
     * @return class action URL.
     */
    public static String getPageUrl(Class<?> clazz) {
        return getBaseUrl() + "/" + getPagePath(clazz);
    }    
    
    /**
     * This method will return the application base URL for the test case execution.
     *
     * @return application base path URL.
     */
    public static String getBaseUrl() {
        if ( baseUrl == null ) {
            baseUrl = System.getProperty("wd.baseUrl", "http://localhost:8080");
        }
        return baseUrl;
    }    
    
}
