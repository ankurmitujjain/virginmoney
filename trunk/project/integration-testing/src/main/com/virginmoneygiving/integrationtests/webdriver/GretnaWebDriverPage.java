package com.virginmoneygiving.integrationtests.webdriver;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jallen
 *
 */
public abstract class GretnaWebDriverPage {

    protected static final Logger LOGGER = Logger.getLogger(GretnaWebDriverPage.class);
    
    protected WebDriver wd;
    protected WebElement we;
    protected String pageTitle;

    /**
     * Constructor.
     * @param webDriver web driver
     */
    protected GretnaWebDriverPage(WebDriver webDriver, String pageTitle ){
        this.wd = webDriver; 
        this.pageTitle = pageTitle;
        if ( ! wd.getTitle().startsWith(getPageTitle() )) {
            throw new IllegalStateException("Unexpected page title, expected '" + getPageTitle() + "', actual '"+wd.getTitle()+"'");
        }
    }
    
    /**
     * Debugging helper method.
     * @param by the clause.
     */
    public void logMatchingElements( By by ) {
        List<WebElement> allSuggestions = wd.findElements(by);
        int i = 0;
        LOGGER.debug( "Looking for an element match to By clause: '" + by + "'");
        for (WebElement suggestion : allSuggestions) {
            LOGGER.debug( "   element [" + i++ + "]: " +  suggestion );
        }        
    }
    
    /**
     * Return the page title.
     * @return the page title.
     */
    public String getPageTitle() {
        return pageTitle;
    }
    
    /**
     * @return the wd
     */
    public WebDriver getWebDriver() {
        return wd;
    }

    /**
     * @param wd the wd to set
     */
    public void setWebDriver(WebDriver wd) {
        this.wd = wd;
    }

    /**
     * @return the we
     */
    public WebElement getWebElement() {
        return we;
    }

    /**
     * @param we the we to set
     */
    public void setWebElement(WebElement we) {
        this.we = we;
    }

    
}
