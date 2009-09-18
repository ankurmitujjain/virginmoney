package com.virginmoneygiving.integrationtests.webdriver.event.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;

/**
 * This class is used for setting up the account details on event setup page -> account users tab.
 * @author vishals
 */
public class AccountUsersPage extends GretnaWebDriverPage {
	
	/**
	 * Constructor.
	 * @param webDriver {@link WebDriver} instance.
	 */
    public AccountUsersPage(WebDriver webDriver ) {
        super( webDriver, "Virgin Money Giving | Charity Account | Events | Account Users" );
    }
    
    /**
     * Initiates a save event action on the page.
     */
    public void setupAccountUsers() {    	
    	
    	logMatchingElements(By.xpath("//input"));    	
    	// Initiate the save action. Pgae do not have any other input field to set.
    	wd.findElement(By.xpath("//input[@type='submit' and @name='navigationButton']")).click();
    }    
}
