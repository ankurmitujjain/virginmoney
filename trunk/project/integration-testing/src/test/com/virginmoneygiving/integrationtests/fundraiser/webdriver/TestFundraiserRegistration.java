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
 * The Class TestFundraiserRegistration.
 * 
 * @author rohitm
 */
public class TestFundraiserRegistration extends BaseWebdriverTests {
    
    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestFundraiserRegistration.class);    
    
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
     * Sets the security db unit helper.
     * 
     * @param securityDbUnitHelper the securityDbUnitHelper to set
     */
    public void setSecurityDbUnitHelper(SecurityDbUnitHelper securityDbUnitHelper) {
        this.securityDbUnitHelper = securityDbUnitHelper;
    }

    /**
     * Sets the fundraiser helper.
     * 
     * @param fundraiserHelper the fundraiserHelper to set
     */
    public void setFundraiserHelper(FundraiserHelper fundraiserHelper) {
        this.fundraiserHelper = fundraiserHelper;
    }

    /**
     * Sets the fundraiser registration details.
     * 
     * @param fundraiserRegistrationDetails the fundraiserRegistrationDetails to set
     */
    public void setFundraiserRegistrationDetails(FundraiserRegistrationDetails fundraiserRegistrationDetails) {
        this.fundraiserRegistrationDetails = fundraiserRegistrationDetails;
    }

    /**
     * Test fundraiser registration.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testFundraiserRegistration() throws Exception {

        //fundraiserRegistrationDetails = FundraiserHelper.generateFundraiserRegistrationDetails();
        fundraiserHelper.registerNewFundraiser(fundraiserRegistrationDetails);
    }
}
