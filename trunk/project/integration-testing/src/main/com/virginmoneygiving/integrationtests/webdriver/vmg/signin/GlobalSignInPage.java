package com.virginmoneygiving.integrationtests.webdriver.vmg.signin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class will be used to trigger the flow on global sign-in page.
 * <br/>
 * As part execution username and dob details will be inserted on page and
 * next action will be triggered to display password details page.
 * 
 * @author vishals
 */
public class GlobalSignInPage extends GretnaWebDriverPage {
	/**
	 * Constructor.
	 * @param webDriver webdriver instance.
	 */
    public GlobalSignInPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving Authentication Service" );
    }

    /**
     * This method will populate global sign-in page input fields and trigger the next action.
     * @param username username
     * @param dobDay dob day.
     * @param dobMonthdob month.
     * @param dobYeardob year.
     * @return {@link PasswordDetailsPage} instance.
     */
    @SuppressWarnings("unchecked")
	public PasswordDetailsPage acceptUsernameAndDOBDetails( String username, String dobDay, String dobMonth, String dobYear ) {
    	// Used for logging.
    	logMatchingElements(By.xpath("//input"));        
    	// Populate the form fields on sing-in page.
        wd.findElement(By.xpath("//input[@name = 'username']")).sendKeys(username);
        wd.findElement(By.xpath("//input[@name = 'dobDay']")).sendKeys(dobDay);
        wd.findElement(By.xpath("//input[@name = 'dobMonth']")).sendKeys(dobMonth);
        wd.findElement(By.xpath("//input[@name = 'dobYear']")).sendKeys(dobYear);
        // Trigger the next button click action.
        wd.findElement(By.xpath("//input[@name='submit']")).click();        
        return GretnaPageFactory.initElements(getWebDriver(), PasswordDetailsPage.class);
    }
    
}
