package com.virginmoneygiving.integrationtests.donor.webdriver;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.BaseWebdriverTests;
import com.virginmoneygiving.integrationtests.webdriver.charity.PaymentDetails;
import com.virginmoneygiving.integrationtests.webdriver.donor.DonorHelper;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserHelper;
import com.virginmoneygiving.integrationtests.webdriver.fundraiser.FundraiserRegistrationDetails;

/**
 * Test case class for testing donation flow fundraiser after log in.
 * 
 * @author Yogesh Salunkhe
 */
public class TestDonationForFundraiser extends BaseWebdriverTests {

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestDonationForFundraiser.class);    

    private DonorHelper donorHelper = null;
    
    /** Payment details instance **/
    private PaymentDetails paymentDetails;
    
    private FundraiserRegistrationDetails fundraiserRegistrationDetails;

    @Test
    public void testMakeDonationForRegisteredFundraiser() throws Exception {
       
       // donorHelper.makeDonationToFundraiser(fundraiserRegistrationDetails,paymentDetails);

    }
    
    /**
     * @param donationHelper the donationHelper to set
     */
    public void setDonationHelper(DonorHelper donationHelper) {
        this.donorHelper = donationHelper;
    }

    /**
     * @param paymentDetails the paymentDetails to set
     */
    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    /**
     * @param fundraiserRegistrationDetails the fundraiserRegistrationDetails to set
     */
    public void setFundraiserRegistrationDetails(
            FundraiserRegistrationDetails fundraiserRegistrationDetails) {
        this.fundraiserRegistrationDetails = fundraiserRegistrationDetails;
    }
	

}
