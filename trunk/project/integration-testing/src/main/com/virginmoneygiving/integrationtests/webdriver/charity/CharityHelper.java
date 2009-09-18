package com.virginmoneygiving.integrationtests.webdriver.charity;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.webdriver.GretnaIntegrationHelper;
import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationAccountHoldersDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationAdministrationAddressPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationCompletePage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationFindCharityResultsPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationSecurityDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationSetCategoryPage;
import com.virginmoneygiving.integrationtests.webdriver.charity.registration.CharityRegistrationSummaryPage;

/**
 * Helper class to help setup data and process <b>webdriver</b> web test flows for charity module.
 *
 * @author jallen
 */
public class CharityHelper extends GretnaIntegrationHelper {

    private WebDriver webDriver;
    private Connection jdbcConnection;
    private List<CharityRegistrationDetails> charities;

	public CharityHelper(WebDriver wd, Connection jdbcConnection) {
        this.webDriver = wd;
        this.jdbcConnection = jdbcConnection;
    }

    public CharityHelper(Connection jdbcConnection) {
        // create a web driver for the charity application
        HtmlUnitDriver charityHtmlUnitWebDriver = new HtmlUnitDriver();
        charityHtmlUnitWebDriver.setJavascriptEnabled(false);

        this.webDriver = charityHtmlUnitWebDriver;
        this.jdbcConnection = jdbcConnection;
    }
    
    public CharityHelper() {
        // create a web driver for the charity application
        HtmlUnitDriver charityHtmlUnitWebDriver = new HtmlUnitDriver();
        charityHtmlUnitWebDriver.setJavascriptEnabled(false);

        this.webDriver = charityHtmlUnitWebDriver;
    }

    public static CharityRegistrationDetails generateCharityRegistrationDetails() {

        CharityRegistrationDetails charityRegDetails = new CharityRegistrationDetails();

        charityRegDetails.charityDetails.name = "Auto Test Charity 001";
        charityRegDetails.charityDetails.registrationNumber = "AUTO001";
        charityRegDetails.charityDetails.addressLine1 = "1 Some Street";
        charityRegDetails.charityDetails.addressLine2 = "Some Place";
        charityRegDetails.charityDetails.town = "Some Town";
        charityRegDetails.charityDetails.county = "Some County";
        charityRegDetails.charityDetails.postCode = "W14 4RF";
        charityRegDetails.charityDetails.categories = new String[] {CharityRegistrationSetCategoryPage.CATEGORY_ID_ANIMALS_THAT_HELP_PEOPLE,
                CharityRegistrationSetCategoryPage.CATEGORY_ID_CHILD_PROTECTION};
        charityRegDetails.charityDetails.description = "This is an automated test charity";
        charityRegDetails.charityDetails.commonName = "Auto Test Charity One";
        charityRegDetails.charityDetails.abbreviations = "AUTO01";
        charityRegDetails.charityDetails.accountingEndDateDay = "01";
        charityRegDetails.charityDetails.accountingEndDateMonth = "01";
        charityRegDetails.charityDetails.accountingEndDateYear = "2010";

        // @todo - take a logo from our resources and copy it to temp dir.
        charityRegDetails.charityDetails.logoPath = "not found";

        charityRegDetails.administratorDetails.title = "Mr";
        charityRegDetails.administratorDetails.firstName = "Forename";
        charityRegDetails.administratorDetails.lastName = "Surname";
        charityRegDetails.administratorDetails.occupation = "Being Rich";
        charityRegDetails.administratorDetails.telephoneNumber = "07919222876";
        charityRegDetails.administratorDetails.email = "auto001.c.admin@example.com";

        charityRegDetails.securityDetails.dobDay = "01";
        charityRegDetails.securityDetails.dobMonth = "01";
        charityRegDetails.securityDetails.dobYear = "1990";
        charityRegDetails.securityDetails.password = "pa55word!!";

        charityRegDetails.trusteeDetails.title = "Mr";
        charityRegDetails.trusteeDetails.firstName = "Foo";
        charityRegDetails.trusteeDetails.lastName = "Foo";
        charityRegDetails.trusteeDetails.dobDay = "01";
        charityRegDetails.trusteeDetails.dobMonth = "01";
        charityRegDetails.trusteeDetails.dobYear = "1970";
        charityRegDetails.trusteeDetails.countryCode = "GB";
        charityRegDetails.trusteeDetails.addressLine1 = "1";
        charityRegDetails.trusteeDetails.addressLine1 = "1";
        charityRegDetails.trusteeDetails.town = "Someville";
        charityRegDetails.trusteeDetails.county = "Somesex";
        charityRegDetails.trusteeDetails.postCode = "W14 4RF";

        return charityRegDetails;
    }
    
    
    
    public static CharityRegistrationDetails amendCharityRegistrationDetails(){
    
    CharityRegistrationDetails charityRegDetails = new CharityRegistrationDetails();
	
    charityRegDetails.charityDetails.addressLine1 = "11";
    charityRegDetails.charityDetails.addressLine2 = "Some Place-new";
    charityRegDetails.charityDetails.town = "Some Town-new";
    charityRegDetails.charityDetails.county = "Some County-new";
    charityRegDetails.charityDetails.postCode = "sk3 8py";
    
    charityRegDetails.charityDetails.description = "This is an automated test charity-new";
    
    charityRegDetails.charityDetails.commonName = "Auto Test Charity One-new";
    charityRegDetails.charityDetails.abbreviations = "AUTO01-new";
    charityRegDetails.charityDetails.accountingEndDateDay = "01";
    charityRegDetails.charityDetails.accountingEndDateMonth = "02";
    charityRegDetails.charityDetails.accountingEndDateYear = "2010";
    
    charityRegDetails.charityDetails.categories = new String[] {"40"};
    
    	
    return charityRegDetails;	
    }
    
    public void registerNewCharity(CharityRegistrationDetails charity) {
    	
    	
        // we start at one of the public entry points to the application

        CharityRegistrationPage registerPage = GretnaPageFactory.newRegisterCharityPage(webDriver);

        // now see if our charity exists, at this stage the test can only
        // handle it if it does not, thus the database initialisation should
        // be performed before we run the script

        CharityRegistrationFindCharityResultsPage findCharityPage = 
            registerPage.findCharity(charity.charityDetails.name);

        // if it exists we can not progress (could easily be improved)

        if (findCharityPage.isCharityFound()) {
            throw new IllegalStateException(
                    "Expected not to find a charity with the specified name '" + 
                    charity.charityDetails.name + "'");
        }

        // now go to the new charity page

        CharityRegistrationDetailsPage charityAddressPage = 
            findCharityPage.goToNewCharityRegistrationPage();

        // fill in the charity admin details, it will return us the next page in
        // the flow.

        CharityRegistrationAdministrationAddressPage adminAddressPage = 
            charityAddressPage.submitCharityAddress(
                    charity.charityDetails.name,
                    charity.charityDetails.registrationNumber, 
                    charity.charityDetails.addressLine1, 
                    charity.charityDetails.addressLine2,
                    charity.charityDetails.town, 
                    charity.charityDetails.county, 
                    charity.charityDetails.postCode);

        // for the charity admin address we'll just use the charity's registered
        // address, will return us the next page in the flow, the account holder
        // details page

        CharityRegistrationAccountHoldersDetailsPage accountHolderDetailsPage = 
            adminAddressPage.useCharityAddressAsAccountHoldersAddress();

        // fill in administration account holders details, will return us the
        // security details page

        CharityRegistrationSecurityDetailsPage securityDetailsPage = 
            accountHolderDetailsPage.submitAccountHolderDetails(
                    charity.administratorDetails.title,
                    charity.administratorDetails.firstName, 
                    charity.administratorDetails.lastName, 
                    charity.administratorDetails.occupation,
                    charity.administratorDetails.telephoneNumber, 
                    charity.administratorDetails.email, 
                    charity.administratorDetails.acceptToC);

        // fill in the security details, will return us the charity registered
        // page

        CharityRegistrationCompletePage regCompletePage = 
            securityDetailsPage.submitSecurityDetails(
                    charity.securityDetails.dobDay,
                    charity.securityDetails.dobMonth, 
                    charity.securityDetails.dobYear, 
                    charity.securityDetails.password);

        // go to the summary page

        CharityRegistrationSummaryPage summaryPage = 
            regCompletePage.goToCharitySummaryPage();

        // set some categories

        summaryPage.goToSetCategoriesPage().setCategories(charity.charityDetails.categories);

        // set the description

        summaryPage.goToSetDescriptionPage().setDescription(charity.charityDetails.description);

        summaryPage.goToSetDetailsPage().setDetails(
                charity.charityDetails.commonName, 
                charity.charityDetails.abbreviations,
                charity.charityDetails.accountingEndDateDay, 
                charity.charityDetails.accountingEndDateMonth, 
                charity.charityDetails.accountingEndDateYear);

        // summaryPage.goToSetLogoPage().setLogo( charity.charityDetails.logoPath );

        summaryPage.goToTrusteeDetailsPage().setTrustees(
                new CharityRegistrationDetails.TrusteeDetails[] {charity.trusteeDetails});

    }

    public boolean makeCharityLiveInDatabase(String charityName) throws Exception {
        Statement statement = jdbcConnection.createStatement();
        return (statement.executeUpdate("update charity set charity_status_code = 'LIV' where name = '" + charityName + "'") == 1);
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

    
    public Integer getCharityId(String charityName, IDatabaseConnection connection) throws Exception {
        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable("charity", "select id from charity where name = '"+charityName+"'");
        ITable auto1 = dataSet.getTable("charity");
        return Integer.parseInt(auto1.getValue(0, "id").toString());
    }

    /**
     * @param charityRegistrationPart1Charity
     * @throws Exception
     */
    public void createCharityBankAccountsInDatabase(CharityRegistrationDetails regDetails) throws Exception {
         
        DbUnitHelper givingDbUnitHelper = new DbUnitHelper(DbUnitHelper.GIVING_DB_URL, DbUnitHelper.GIVING_DB_USERNAME, DbUnitHelper.GIVING_DB_PASSWORD,
                DbUnitHelper.GIVING_DB_SCHEMA, "unused", "/com/virginmoneygiving/integrationtests/charity/webdriver/registerpart1/giving-add-charity-bank-account.xml");

        givingDbUnitHelper.setSetupDatabaseOperation(DatabaseOperation.NONE);
        givingDbUnitHelper.onSetUp();
        IDatabaseConnection connection = givingDbUnitHelper.getDatabaseTester().getConnection();

        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());

        Integer charityId = getCharityId(regDetails.charityDetails.name, connection);
        Integer bankId = getNextId("bank", connection);
        Integer addressId = getNextId("address", connection);
        Integer bankAddressId = getNextId("bank_address", connection);
        Integer bankAccountId = getNextId("bank_account", connection);

        ReplacementDataSet replacementInitialDataset = (ReplacementDataSet) givingDbUnitHelper.getDatabaseTester().getDataSet();
        replacementInitialDataset.addReplacementObject("[BANK_ID]", bankId);
        replacementInitialDataset.addReplacementObject("[ADDRESS_ID]", addressId);
        replacementInitialDataset.addReplacementObject("[BANK_ADDRESS_ID]", bankAddressId);
        replacementInitialDataset.addReplacementObject("[BANK_ACCOUNT_ID]", bankAccountId);
        replacementInitialDataset.addReplacementObject("[CHARITY_ID]", charityId);

        DatabaseOperation.INSERT.execute(connection, replacementInitialDataset);
    }
    
    public void setJdbcConnection(Connection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	public void setCharities(List<CharityRegistrationDetails> charities) {
		this.charities = charities;
	}
    
    
}
