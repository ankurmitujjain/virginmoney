package com.virginmoneygiving.integrationtests.webdriver.vmg.signin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.event.registration.CharityPortalHomePage;

/**
 * This class will fill up the data required on global sign-in password details page and
 * trigger the next action.
 * @author vishals
 */
public class PasswordDetailsPage<T> extends GretnaWebDriverPage {
   
	/** Constant for first character position text. */
	private final String FIRST = "First";
	/** Constant for second character position text. */
	private final String SECOND = "Second";
	/** Constant for third character position text. */
	private final String THIRD = "Third";
	/** Constant for fourth character position text. */
	private final String FOURTH = "Fourth";
	/** Constant for fifth character position text. */
	private final String FIFTH = "Fifth";
	/** Constant for sixth character position text. */
	private final String SIXTH = "Sixth";
	/** Constant for seventh character position text. */
	private final String SEVENTH = "Seventh";
	/** Constant for eight character position text. */
	private final String EIGHTH = "Eighth";
	/** Constant for nineth character position text. */
	private final String NINTH = "Ninth";
	/** Constant for tenth character position text. */
	private final String TENTH = "Tenth";	
	
	/**
	 * Constructor.
	 * @param webDriver webdriver instance.
	 */
    public PasswordDetailsPage(WebDriver webDriver ) {
    	// Sets up the title as well.
        super( webDriver, "Virgin Money Giving Authentication Service" );
    }

    /**
     * This method will fill up the data required on global sign-in password details page and
     * trigger the next action. 
     * @param password user password.
     * @param clazz instance of page class to be displayed next. e.g for charity account it will be {@link CharityPortalHomePage}.class.
     * @return proxy for the next page in the flow.
     */
    public T acceptPassword(String password, Class<T> clazz) {
    	logMatchingElements(By.xpath("//input"));
    	//Find password character positions to login.
    	String passwordChar1Position = getPasswordCharacter(wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='vm-signin']/div[1]/form[@id='credentials']/p[2]/span/label/strong")).getText(), password);
    	String passwordChar2Position = getPasswordCharacter(wd.findElement(By.xpath("/html/body/div[@id='container']/div[@id='content']/div[@id='vm-signin']/div[1]/form[@id='credentials']/p[3]/span/label/strong")).getText(), password);
    	
    	wd.findElement(By.xpath("//input[@name = 'passwordCharacter1']")).sendKeys(passwordChar1Position);
        wd.findElement(By.xpath("//input[@name = 'passwordCharacter2']")).sendKeys(passwordChar2Position);
        
        logMatchingElements(By.xpath("//input"));
        wd.findElement(By.xpath("//input[@name='submit']")).click();
        return GretnaPageFactory.initElements(getWebDriver(), clazz);         
    }
    
    /**
     * Utility method used for extracting the password characters based on the password positions displayed on the screen.
     * <br/>
     * Please note that this method implementation supports password positions up to only 10 characters.
     * Your password shouldn't be greater than 10 characters otherwise it will throw an exception.
     * @param passwordPostion password position. This will be have following pattern 'First', 'Second'  etc.
     * @param password user password.
     * @return
     */
    private String getPasswordCharacter(String passwordPostion, String password) {
    	
    	// Validate the password length.
    	if(password == null || password.length() > 10) {
    		throw new IllegalArgumentException("At present method only suports passwords of 10 character in length.");
    	}
    	
    	String passwordChar = "";
    	
    	char[] passChars = password.toCharArray();
    	
    	if(FIRST.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[0]);
    	} else if(SECOND.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[1]);
    	} else if(THIRD.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[2]);
    	} else if(FOURTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[3]);
    	} else if(FIFTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[4]);
    	} else if(SIXTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[5]);
    	} else if(SEVENTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[6]);
    	} else if(EIGHTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[7]);
    	} else if(NINTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[8]);
    	} else if(TENTH.equalsIgnoreCase(passwordPostion)) {
    		passwordChar = String.valueOf(passChars[9]);
    	} 
    	
    	return passwordChar;
    	
    }
    
}
