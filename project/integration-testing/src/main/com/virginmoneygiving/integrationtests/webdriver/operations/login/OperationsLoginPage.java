package com.virginmoneygiving.integrationtests.webdriver.operations.login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.GretnaWebDriverPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityAccountPage;
import com.virginmoneygiving.integrationtests.webdriver.operations.portal.OperationsPortalPage;
/**
 * 
 * @author saurabhh
 * Web driver class to represent operations login page
 */
public class OperationsLoginPage extends GretnaWebDriverPage{
	
	/*
	 * 
	 */
	 public OperationsLoginPage(WebDriver webDriver) {
	        super(webDriver,"User Authentication");
	    }	
	
		
	/*
	 * Method to login into operations web, with Operations users details
	 * @param String userName
	 * @param String password
	 * @return OperationsPortalPage.class 
	 * 
	 */
	public OperationsPortalPage submitOperationsLogin(String userName,String password){
		
		 logMatchingElements(By.xpath("//input")); 
		 logMatchingElements(By.xpath("//button")); 
		
		 wd.findElement(By.xpath("//input[@name = 'username']")).sendKeys(userName);
	     wd.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
	     wd.findElement(By.xpath("//button[@title='signIn']")).click();	
	     
	     return GretnaPageFactory.initElements(getWebDriver(),OperationsPortalPage.class);
		
	} 
	
}
