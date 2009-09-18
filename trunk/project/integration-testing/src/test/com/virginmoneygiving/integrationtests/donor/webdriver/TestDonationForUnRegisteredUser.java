package com.virginmoneygiving.integrationtests.donor.webdriver;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.BaseWebdriverTests;
import com.virginmoneygiving.integrationtests.webdriver.charity.PaymentDetails;
import com.virginmoneygiving.integrationtests.webdriver.donor.DonorHelper;

/**
 * Test case class for testing single donation flow for unregistered user.
 * 
 * @author Yogesh Salunkhe
 */
public class TestDonationForUnRegisteredUser extends BaseWebdriverTests {

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestDonationForUnRegisteredUser.class);    

    private DonorHelper donorHelper = null;
    /** Payment details instance **/
    private PaymentDetails paymentDetails;

    @Test
    public void testMakeDonationForUnRegisteredUser() throws Exception {
       
        //donorHelper.makeSingleDonation(paymentDetails);

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
	

}
