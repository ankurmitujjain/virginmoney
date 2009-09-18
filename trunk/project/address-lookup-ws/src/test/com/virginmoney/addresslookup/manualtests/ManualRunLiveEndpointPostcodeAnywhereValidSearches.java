package com.virginmoney.addresslookup.manualtests;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.ws.AbstractTestEndpointValidSearches;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.AddressLookupResponse;
import com.virginmoney.addresslookup.messages.Address;
import com.virginmoney.addresslookup.messages.ServiceProviders;

/**
 *  This class extents {@link com.virginmoney.addresslookup.ws.AbstractTestEndpointValidSearches} and tests the endpoint with valid searches
 *  using a mocked postcode anywhere service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class ManualRunLiveEndpointPostcodeAnywhereValidSearches extends AbstractTestEndpointValidSearches {

    private static Logger logger = Logger.getLogger(ManualRunLiveEndpointPostcodeAnywhereValidSearches.class);

@Test
public void Dummy(){
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPoint();
        super.searchProvider = ServiceProviders.POSTCODE_ANYWHERE;
        super.returnPAFData = true;
    }

    /**
     * Checks that residential status is returned correctly for known addresses.
     */
    @Test
    public void TestResidentialStatuses() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"Honeycombe");
        logger.debug("address=" + address);
        Assert.assertNotNull("Honeycombe is in results",address);
        Assert.assertTrue("Honeycombe is residential",address.getPafData().isIsResidential());


   }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestNonResidentialStatuses() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"Old Mill Restaurant");
        logger.debug("address=" + address);
        Assert.assertNotNull("Old Mill Restaurant is in results",address);
        Assert.assertFalse("Old Mill Restaurant not  residential",address.getPafData().isIsResidential());

   }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestPOBox() throws Exception {
        String searchPostcode = "NR1 3NG";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"PO Box 4");
        logger.debug("address=" + address);
        Assert.assertNotNull("PO Box 4 should be found in results",address);
        Assert.assertTrue("PO Box 4 not organisation : ",address.getPafData().isIsLargeOrganisation());

   }


}

