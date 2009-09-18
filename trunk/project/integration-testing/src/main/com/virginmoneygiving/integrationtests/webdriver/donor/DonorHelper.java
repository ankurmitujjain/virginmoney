package com.virginmoneygiving.integrationtests.webdriver.donor;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.virginmoneygiving.integrationtests.webdriver.GretnaPageFactory;
import com.virginmoneygiving.integrationtests.webdriver.charity.PaymentDetails;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.CharitiesPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.CharityCategoriesPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.CharityHomePage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.CharitySubCategoryPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.ConfirmGiftAidPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.ConfirmationPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.ConfirmationPageForRegisteredUser;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.GiftAidPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.PaymentDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.PaymentDetailsPageForRegisteredUser;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.SearchFundraiserPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.SignInPage;
import com.virginmoneygiving.integrationtests.webdriver.donor.page.SponsorshipDetailsPage;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.portal.FundraiserPortalHomePage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.GlobalSignInPage;
import com.virginmoneygiving.integrationtests.webdriver.vmg.signin.PasswordDetailsPage;

/**
 * Helper class to help setup data and process <b>webdriver</b> web test flows
 * for donor module.
 * 
 * @author Yogesh Salunkhe
 */
public class DonorHelper {
    
    /** Web driver instance. */
    private static WebDriver webDriver;
    /** JDBC connection instance required for DBUnit. */
    private Connection jdbcConnection;
    
    /**
     * Default constructor.
     */
    public DonorHelper() {
        // create a web driver for the donor application
         HtmlUnitDriver donorHtmlUnitWebDriver = new HtmlUnitDriver();
         donorHtmlUnitWebDriver.setJavascriptEnabled(false);
        //FirefoxDriver donorHtmlUnitWebDriver = new FirefoxDriver();
        this.webDriver = donorHtmlUnitWebDriver;
    }
    
    /**
     * Constructor with two parameters.
     * 
     * @param wd web driver instance.
     * @param jdbcConnection jdbc connection instance.
     */
    public DonorHelper(WebDriver wd, Connection jdbcConnection) {
        this.webDriver = wd;
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * Constructor with connection object.
     * @param jdbcConnection jdbc connection instance.
     */
    public DonorHelper(Connection jdbcConnection) {
        // create a web driver for the donor application
        HtmlUnitDriver donorHtmlUnitWebDriver = new HtmlUnitDriver();
        donorHtmlUnitWebDriver.setJavascriptEnabled(false);

        this.webDriver = donorHtmlUnitWebDriver;
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * This method will trigger the single donation for unregistered donor use case journey.
     * <br/>
     * Entry point for journey : Select Charity Categories Page <br/>
     * Exit point for journey : Make Donation on Confirmation page.
     * @param paymentDetails contains mock paymentDetails data.
     */
    public void makeSingleDonation(PaymentDetails paymentDetails) {

        // Trigger the make donation flow
        CharityCategoriesPage charityCategory =
                GretnaPageFactory.initiateCharityCategory(webDriver);
     // Trigger Create event link and follow the journey till the end.
     // Please note that webdriver will run in java script disabled mode and will make use of DDA pages wherever applicable.
        CharitySubCategoryPage charitySubCategory =
                charityCategory.displaySubCategories(paymentDetails);
        CharitiesPage charitiesPage = charitySubCategory.displayCharities();
        CharityHomePage charityHomePage = charitiesPage.displayCharityHome();
        SponsorshipDetailsPage sponsorshipDetailsPage =
                charityHomePage.displaySponsorshipDetails();
        SignInPage signInPage =
                sponsorshipDetailsPage
                        .displayDonorRegistrationSignInPage(paymentDetails);
        PaymentDetailsPage paymentDetailsPage =
                signInPage.displayPaymentDetails();
        GiftAidPage giftAidPage =
                paymentDetailsPage.displayGiftAid(paymentDetails);
        ConfirmGiftAidPage confirmGiftAidPage = giftAidPage.confirmGiftAid();
        ConfirmationPage confirmationPage =
                confirmGiftAidPage.confirmDonation();
        confirmationPage.makeDonation();
    }
    
    public void makeDonationToFundraiser(FundraiserRegistrationDetails fundraiser, PaymentDetails paymentDetails){
        
        SearchFundraiserPage searchFundraiserPage = 
            GretnaPageFactory.searchFundraiser(webDriver);
        searchFundraiserPage = searchFundraiserPage.searchFundraisers(fundraiser);
        SponsorshipDetailsPage sponsorshipDetailsPage = searchFundraiserPage.displayFundraiserResults();
        SignInPage signInPage = sponsorshipDetailsPage.displayDonorRegistrationSignInPageForRegisteredUser(paymentDetails);
        GlobalSignInPage singinPage = signInPage.fundraiserSignIn();
        // Accept user name and DOB.
/*        PasswordDetailsPage passwordDetailsPage = singinPage.acceptUsernameAndDOBDetails(paymentDetails.emailAddress,
                paymentDetails.dobDay, paymentDetails.dobMonth, paymentDetails.dobYear);
*/      
        PasswordDetailsPage passwordDetailsPage = singinPage.acceptUsernameAndDOBDetails(fundraiser.fundraiserDetails.emailAddress,
                fundraiser.securityDetails.dobDay, fundraiser.securityDetails.dobMonth, fundraiser.securityDetails.dobYear);
        

        // Accept password.
        PaymentDetailsPageForRegisteredUser paymentDetailsPage = (PaymentDetailsPageForRegisteredUser) passwordDetailsPage.acceptPassword(fundraiser.securityDetails.password,
                PaymentDetailsPageForRegisteredUser.class);

        GiftAidPage giftAidPage =
            paymentDetailsPage.displayGiftAid(paymentDetails);
        ConfirmGiftAidPage confirmGiftAidPage = giftAidPage.confirmGiftAid();
        ConfirmationPageForRegisteredUser confirmationForRegisteredUser =
                confirmGiftAidPage.confirmDonationForRegisteredUser();
        confirmationForRegisteredUser.makeDonation();
    }

    /**
     * This method is used to set the payment details which is required for making donation.
     * 
     * @return PaymentDetails object
     */
    public static PaymentDetails getPaymentDetails() {

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.amount = "8";
        paymentDetails.cardHolderName = "Yogesh";
        paymentDetails.cardNumber = "374245455400001";
        paymentDetails.cardType = "";
        paymentDetails.city = "Vashi";
        paymentDetails.country = "India";
        paymentDetails.email = "yogesh.salunkhe@arrkgroup.com";
        paymentDetails.expiryDate1 = "12";
        paymentDetails.expiryDate2 = "09";
        paymentDetails.securityCode = "5252";
        paymentDetails.county = "Lancashire";
        paymentDetails.addressLine1 = "11";
        paymentDetails.addressLine2 = "22";
        paymentDetails.houseName = "11";
        paymentDetails.houseNumber = "11";
        paymentDetails.issueNumber = "";
        paymentDetails.postCode = "NR1 4EJ";
        paymentDetails.validFrom1 = "";
        paymentDetails.validFrom2 = "";
        paymentDetails.message ="Test";
//        paymentDetails.emailAddress="ganesh.p@arrkgroup.com";
//        paymentDetails.dobDay="12";
//        paymentDetails.dobMonth="09";
//        paymentDetails.dobYear="1986";
//        paymentDetails.password="mumbai80$";
        return paymentDetails;

    }

    /**
     * @return the webDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * @param webDriver
     *            the webDriver to set
     */
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setJdbcConnection(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

}
