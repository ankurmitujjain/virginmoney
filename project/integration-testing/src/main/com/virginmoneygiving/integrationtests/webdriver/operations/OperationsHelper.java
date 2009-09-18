/**
 * 
 */
package com.virginmoneygiving.integrationtests.webdriver.operations;

import java.sql.Connection;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.webdriver.GretnaIntegrationHelper;
import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityAccountPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationSummaryPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityCategory;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityDescription;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityDetails;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityDetailsUpdated;
import com.virginmoneygiving.integrationtests.webdriver.charity.registrationpart2.CharityPageColours;
import com.virginmoneygiving.integrationtests.webdriver.operations.OperationsUserConstants;
import com.virginmoneygiving.integrationtests.webdriver.operations.login.OperationsLoginPage;
import com.virginmoneygiving.integrationtests.webdriver.operations.portal.CharitySummaryPage;
import com.virginmoneygiving.integrationtests.webdriver.operations.portal.OperationsPortalPage;
import com.virginmoneygiving.integrationtests.webdriver.operations.portal.SelectCharityPage;

/**
 * @author jallen
 * Class holding utility methods for operations related test cases
 */
public class OperationsHelper extends GretnaIntegrationHelper {

    private WebDriver webDriver;

    public OperationsHelper(WebDriver wd) {
        this.webDriver = wd;
    }

    public OperationsHelper() {

        // create a web driver for the charity application
        HtmlUnitDriver charityHtmlUnitWebDriver = new HtmlUnitDriver();
        charityHtmlUnitWebDriver.setJavascriptEnabled(false);

        this.webDriver = charityHtmlUnitWebDriver;
    }

    /**
     * @return the webDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * @param webDriver the webDriver to set
     */
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    
    
    /**
	 * Method to populate operational user's username and password as
	 * "gretnabind" and "chooChoh7aeD"
	 * 
	 * @return OperationsUserDetails operationUserDetails
	 */
	public static OperationsUserDetails generateOperationUserDetails() {

		OperationsUserDetails operationUserDetails = new OperationsUserDetails();

		operationUserDetails
				.setUserName(OperationsUserConstants.OPERATIONS_USER_LOGIN);
		operationUserDetails
				.setPassword(OperationsUserConstants.OPERATIONS_USER_PASSWORD);

		return operationUserDetails;

	}
	
	
	
	/** Method to create operations user accounts inside database
     * @param charityRegistrationPart1Charity
     * @throws Exception
     */
    public void createInternalUserAccountsInDatabase(String userName) throws Exception {
         
        DbUnitHelper givingDbUnitHelper = new DbUnitHelper(DbUnitHelper.GIVING_DB_URL, DbUnitHelper.GIVING_DB_USERNAME, DbUnitHelper.GIVING_DB_PASSWORD,
                DbUnitHelper.GIVING_DB_SCHEMA, "unused", "/com/virginmoneygiving/integrationtests/charity/webdriver/giving-setup-operation-admin-user-account.xml");

        givingDbUnitHelper.setSetupDatabaseOperation(DatabaseOperation.NONE);
        givingDbUnitHelper.onSetUp();
        IDatabaseConnection connection = givingDbUnitHelper.getDatabaseTester().getConnection();

        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());

        Integer personId = getNextId("person", connection);
        Integer userId = getNextId("user", connection);
        Integer userRoleId = getNextId("user_role", connection);
        Integer userAuthGroupId = getNextId("user_auth_group", connection);

        ReplacementDataSet replacementInitialDataset = (ReplacementDataSet) givingDbUnitHelper.getDatabaseTester().getDataSet();
        replacementInitialDataset.addReplacementObject("[PERSON_ID]", personId);
        replacementInitialDataset.addReplacementObject("[USER_ID]", userId);
        replacementInitialDataset.addReplacementObject("[USER_ROLE_ID]", userRoleId);
        replacementInitialDataset.addReplacementObject("[USER_NAME]",userName);
        for(int i=1; i<=39; i++) {
        	replacementInitialDataset.addReplacementObject("[USER_AUTH_GROUP_ID"+i+"]", userAuthGroupId);
        	userAuthGroupId++;
        }

        DatabaseOperation.INSERT.execute(connection, replacementInitialDataset);
    }
	
	/**
	 * Helper method to take in Charity amend details/ Operational user details 
	 * @param operationsCharityDetails
	 * @param charityRegistrationDetails
	 */
	public  void charityRegistrationPart2(OperationsUserDetails operationsUserDetails,CharityRegistrationDetails charityRegistrationDetails){
		
	// Step1 -Login with operational user details- developer2/VMG2009	
		
	// Initialize the operations login page	
	OperationsLoginPage loginPage=GretnaPageFactory.newOperationsLoginPage(webDriver);	
	
	// Login
	
	OperationsPortalPage operationsPortalPage=loginPage.submitOperationsLogin(generateOperationUserDetails().getUserName(),generateOperationUserDetails().getPassword());
	
    // Enter search criteria and get list of charities
	
	SelectCharityPage selectCharityPage= operationsPortalPage.searchForCharity(OperationsUserConstants.CHARITY_SEARCH_TEXT);
	
    // Select a specific charity and view Registration summary
	
	CharitySummaryPage charitySummaryPage=selectCharityPage.selectCharity();
	
	// select Charity portal view on Registration summary page
	
	CharityAccountPage charityAccountPage =charitySummaryPage.clickOnCharityPortalLink();
	
	// Select the page colors link on the charity account page
	
	CharityPageColours charityPageColours=charityAccountPage.clickOnPageColoursLink();
	
	// Update color scheme on page colors page
	
//    CharityRegistrationSummaryPage charityRegistrationSummaryPage=charityPageColours.alterCharityPageColours();
    charityPageColours =charityPageColours.alterCharityPageColours();

    // Select description link on charity summary page
    
//    CharityDescription charityDescription= charityRegistrationSummaryPage.goToDescriptionPage();
    CharityDescription charityDescription= charityAccountPage.clickOnCharityDescriptionLink();

    // Alter description on Description page
    
    charityDescription=charityDescription.alterCharityDescription(charityRegistrationDetails);
    
    //Select details link on description page
    
    CharityDetails charityDetails= charityDescription.clickOnCharitytDetailsLink();
    
    // Alter details
    
    CharityDetailsUpdated charityDetailsUpdated=charityDetails.alterCharityDetails(charityRegistrationDetails);
    
    //Select charity category link
    
    CharityCategory charityCategory=charityDetailsUpdated.clickOnCharityCategoryLink();
    
    // Update charity category
    
    charityCategory=charityCategory.altercharityCategory(charityRegistrationDetails);
    
    // Process ends
    
	
	
	}
	
	
	
	
	
    
    
    
    

}
