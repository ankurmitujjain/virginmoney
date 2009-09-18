package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.serviceproxy.AbstractTestServiceProxyImpl;
import com.virginmoney.addresslookup.serviceproxy.ServiceProxyException;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.handlers.PostcodeAnywhereAddressSearchHandler;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.mockObjects.MockPostcodeAnywhereSoap;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AbstractAddress;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 * Tests the IFDS Service handler class which processes address search requests, using a Mock service implmentation.
 * Copyright     :  Virgin Money Ltd.
 */
public class TestMockedPostcodeAnywhereServiceProxyImpl extends AbstractTestServiceProxyImpl {

    private static Logger logger = Logger.getLogger(TestMockedPostcodeAnywhereServiceProxyImpl.class);

    protected void getProxy() throws Exception {
        super.serviceProxy = SpringBuilder.getPostcodeAnywhereServiceProxyWithMockServices();
        super.returnPAFData = AddressSearchParameters.ReturnPAFDataRules.returnPAFData;
    }



    @Test
    public void Dummy(){
        Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
    }

    /**
     * Checks that residential status is returned correctly for known addresses.
     */
    @Test
    public void TestResidentialStatuses() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);

        AbstractAddress address = getAddressFromResults(results,"Honeycombe");
        logger.debug("address=" + address);
        Assert.assertNotNull("Honeycombe is in results",address);
        assertTrue("Honeycombe is residential",address.getPafData().isIsResidential());


   }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestNonResidentialStatuses() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);

        AbstractAddress address = getAddressFromResults(results,"Old Mill Restaurant");
        logger.debug("address=" + address);
        Assert.assertNotNull("Old Mill Restaurant is in results",address);
        Assert.assertFalse("Old Mill Restaurant not  residential",address.getPafData().isIsResidential());

   }
    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestIncompletePostcodeException() throws Exception {
        String searchPostcode = MockPostcodeAnywhereSoap.INCOMPLETE_EXCEPTION_POSTCODE;
        String searchBuilding = "";
        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);

        Assert.assertEquals("incomplete postcode should return no results and no exception",0,results.getAddresses().size());
    }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestMissingPostcodeException() throws Exception {
        String searchPostcode = MockPostcodeAnywhereSoap.MISSING_ID_EXCEPTION_POSTCODE;
        String searchBuilding = "";
        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);

        Assert.assertEquals("missind postcode should return no results and no exception",0,results.getAddresses().size());
    }

    @Test
        public void testOtherException() throws Exception {
        String searchPostcode = MockPostcodeAnywhereSoap.OTHER_EXCEPTION_POSTCODE;
        String searchBuilding = "";

        try {
            AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);
            fail("An exception should be thrown when no postcode anywhere licence key is present");
        }
        catch (ServiceProxyException e) {
            logger.debug("Caught expected ServiceProxyException");
        }
    }


    @Test
        public void testNoLicenseKey() throws Exception {
        System.clearProperty(AddressSearchParameters.ServiceProviders.PostcodeAnywhere.getLicenseKeyPropertyName());
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        try {
            AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);
            fail("An exception should be thrown when no postcode anywhere licence key is present");
        }
        catch (ServiceProxyException e) {
            logger.debug("Caught expected ServiceProxyException");
            assertTrue("No licence key causes exception with 'license key' in the description",
                    StringUtils.containsIgnoreCase(e.getMessage(),"license key"));
        }
    }


    @Test
        public void testNoAccountCode() throws Exception {
        System.clearProperty(AddressSearchParameters.ServiceProviders.PostcodeAnywhere.getAccountCodePropertyName());
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        try {
            AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);
            fail("An exception should be thrown when no postcode anywhere account code is present");
        }
        catch (ServiceProxyException e) {
            logger.debug("Caught expected ServiceProxyException");
            assertTrue("No licence key causes exception with 'account code' in the description",
                    StringUtils.containsIgnoreCase(e.getMessage(),"account code"));
        }
    }
  

}

