package com.virginmoneygiving.integrationtests.fundraiser.webdriver;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.BaseWebdriverTests;
import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.GivingDbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.PaymentDbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.SecurityDbUnitHelper;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserHelper;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * The Class TestCreateFundraisingPage.
 * 
 * @author rohitm
 */
public class TestCreateFundraisingPage extends BaseWebdriverTests {
    
    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestCreateFundraisingPage.class);    
    /** GivingDbUnitHelper instance. */
    private DbUnitHelper givingDbUnitHelper = null;
    
    /** PaymentDbUnitHelper instance. */
    private DbUnitHelper paymentDbUnitHelper = null;
    /** SecurityDbUnitHelper instance. */
    private DbUnitHelper securityDbUnitHelper = null; 
    /** CharityHelper instance. */
    private FundraiserHelper fundraiserHelper = null;
    /** CharityRegistrationDetails instance. */
    private FundraiserRegistrationDetails fundraiserRegistrationDetails = null;

    /**
     * Sets the giving db unit helper.
     * 
     * @param givingDbUnitHelper the givingDbUnitHelper to set
     */
    public void setGivingDbUnitHelper(GivingDbUnitHelper givingDbUnitHelper) {
        this.givingDbUnitHelper = givingDbUnitHelper;
    }

    /**
     * Sets the payment db unit helper.
     * 
     * @param paymentDbUnitHelper the paymentDbUnitHelper to set
     */
    public void setPaymentDbUnitHelper(PaymentDbUnitHelper paymentDbUnitHelper) {
        this.paymentDbUnitHelper = paymentDbUnitHelper;
    }

    /**
     * @param securityDbUnitHelper the securityDbUnitHelper to set
     */
    public void setSecurityDbUnitHelper(SecurityDbUnitHelper securityDbUnitHelper) {
        this.securityDbUnitHelper = securityDbUnitHelper;
    }
    
    /**
     * @param fundraiserHelper the fundraiserHelper to set
     */
    public void setFundraiserHelper(FundraiserHelper fundraiserHelper) {
        this.fundraiserHelper = fundraiserHelper;
    }

    /**
     * @param fundraiserRegistrationDetails the fundraiserRegistrationDetails to set
     */
    public void setFundraiserRegistrationDetails(FundraiserRegistrationDetails fundraiserRegistrationDetails) {
        this.fundraiserRegistrationDetails = fundraiserRegistrationDetails;
    }

    @Test
    public void testCreateFundraisingPage() {
        //fundraiserRegistrationDetails = FundraiserHelper.generateFundraiserRegistrationDetails();
        fundraiserHelper.createFundraisingPage(fundraiserRegistrationDetails);
        
    }
}
