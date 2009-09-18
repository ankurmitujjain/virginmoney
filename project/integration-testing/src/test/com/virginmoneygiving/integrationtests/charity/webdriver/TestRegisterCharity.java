package com.virginmoneygiving.integrationtests.charity.webdriver;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.virginmoneygiving.integrationtests.BaseWebdriverTests;
import com.virginmoneygiving.integrationtests.dbunit.DbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.GivingDbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.PaymentDbUnitHelper;
import com.virginmoneygiving.integrationtests.dbunit.SecurityDbUnitHelper;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityHelper;
import com.virginmoneygiving.integrationtests.webdriver.charity.CharityRegistrationDetails;

/**
 * Test case class for charity registration process (covers both part 1 & 2).
 * <br/>
 * This journey will be consist of multiple steps as below
 * <ul>
 * <li>Register a charity.</li>
 * <li>Set charity details on registration part-2 flow.</li>
 * <li>Setup charity bank account.</li>
 * <li>Make charity live.</li>
 * </ul>
 * 
 * @author jallen
 */
public class TestRegisterCharity extends BaseWebdriverTests {

    /** Logger for logging. */
    private static final Logger LOGGER = Logger.getLogger(TestRegisterCharity.class);    
    /** GivingDbUnitHelper instance. */
	private DbUnitHelper givingDbUnitHelper = null;
	/** PaymentDbUnitHelper instance. */
    private DbUnitHelper paymentDbUnitHelper = null;
    /** SecurityDbUnitHelper instance. */
    private DbUnitHelper securityDbUnitHelper = null;    
    /** CharityHelper instance. */
    private CharityHelper charityHelper = null;
    /** CharityRegistrationDetails instance. */
    private CharityRegistrationDetails charityRegDetails = null;
    
    /**
	 * @param charityRegDetails the charityRegDetails to set
	 */
	public void setCharityRegDetails(CharityRegistrationDetails charityRegDetails) {
		this.charityRegDetails = charityRegDetails;
	}

	@Override
    protected void onSetUp() throws Exception {
        // Set up dbunit helpers and put the databases in a known state
        if (requiresInitialisedDatabase()) {
            initialiseDatabase();
        }

        charityHelper.setJdbcConnection(givingDbUnitHelper.getDatabaseTester().getConnection().getConnection());

/*        if (charityRegDetails == null) {
            charityRegDetails = CharityHelper.generateCharityRegistrationDetails();
        }*/
    }

    /**
     * Return true if the database should be initialised.
     * 
     * @return result
     */
    private boolean requiresInitialisedDatabase() {
        return true;
    }

    /**
     * Initialise the database initial state.
     *
     * @throws Exception
     */
    private void initialiseDatabase() throws Exception{
        givingDbUnitHelper.onSetUp();
        paymentDbUnitHelper.onSetUp();
        securityDbUnitHelper.onSetUp();
    }

    @Override
    protected void onTearDown() throws Exception{
        if (requiresInitialisedDatabase()) {
            paymentDbUnitHelper.onTeardown();
            givingDbUnitHelper.onTeardown();
            securityDbUnitHelper.onTeardown();
        }
        
        if(charityHelper != null ) {
        	charityHelper.getWebDriver().quit();
        }
    }

    @Test
    public void testCharityRegistrationPart1() throws Exception {
    	
        // kick off main charity registration flow        
        charityHelper.registerNewCharity(charityRegDetails);

        // create bank accounts via database calls        
        charityHelper.createCharityBankAccountsInDatabase(charityRegDetails);

        // make the charity live, again via database calls.
        charityHelper.makeCharityLiveInDatabase(charityRegDetails.charityDetails.name);

    }
    
    /**
     * Setter for {@link GivingDbUnitHelper}.
     * @param givingDbUnitHelper GivingDbUnitHelper instance.
     */
    public void setGivingDbUnitHelper(GivingDbUnitHelper givingDbUnitHelper) {
		this.givingDbUnitHelper = givingDbUnitHelper;
	}
    
    /**
     * Setter for {@link PaymentDbUnitHelper}.
     * @param paymentDbUnitHelper PaymentDbUnitHelper instance.
     */
	public void setPaymentDbUnitHelper(PaymentDbUnitHelper paymentDbUnitHelper) {
		this.paymentDbUnitHelper = paymentDbUnitHelper;
	}
	
	/**
	 * Setter for {@link SecurityDbUnitHelper}.
	 * @param securityDbUnitHelper SecurityDbUnitHelper instance.
	 */
	public void setSecurityDbUnitHelper(SecurityDbUnitHelper securityDbUnitHelper) {
		this.securityDbUnitHelper = securityDbUnitHelper;
	}
	
	/**
	 * Setter for {@link CharityHelper}.
	 * @param charityHelper CharityHelper instance.
	 */
    public void setCharityHelper(CharityHelper charityHelper) {
		this.charityHelper = charityHelper;
	}
}
