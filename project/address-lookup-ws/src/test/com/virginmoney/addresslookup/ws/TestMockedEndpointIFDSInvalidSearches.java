package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.*;

/**
 *  This class extents {@link AbstractTestEndpointInvalidSearches} and tests the endpoint with invalid searches
 *  using a mocked IFDS service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMockedEndpointIFDSInvalidSearches extends AbstractTestEndpointInvalidSearches{

    private static Logger logger = Logger.getLogger(TestMockedEndpointIFDSInvalidSearches.class);

@Test
public void Dummy() throws Exception {
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.IFDS;
        super.returnPAFData = false;
    }

    /**
     * Checks that the PAF data parameter is ignored for IFDS cases  - the service will not return PAF data
     * for this provider.
     */
    @Test
    public void TestInvalidIFDSPAFData() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = null;


        AddressLookupRequest lookupRequest = new AddressLookupRequest();
        lookupRequest.setSearchPostcode(searchPostcode);
        lookupRequest.setSearchBuilding(searchBuilding);
        lookupRequest.setServiceProvider(ServiceProviders.IFDS);
        lookupRequest.setResultFormat(ResultFormats.STYLE_2);
        lookupRequest.setReturnPAFData(true);
        AddressLookupResponse results = testEndPoint.addressLookup(lookupRequest);

        Assert.assertNull("results should not have an Errors object", results.getErrors());
        Assert.assertTrue("results should have non-zero result count", results.getResultCount() > 0);
        Assert.assertEquals("results should reset the return PAF data rule", false, results.isReturnPAFData());
        Assert.assertEquals("results should reset the result format", ResultFormats.STYLE_1, results.getResultFormat());

        resultsContainFragment(results, "Honeycombe", true);// Nicks house is here
        resultsContainFragment(results, "Ye Olde Crisp Factory", false);// definitely not

        Address firstAddressFound = results.getAddresses().get(0);
        Assert.assertFalse("First result does not have the 'MatchesSearchBuilding' flag set ",
                firstAddressFound.isMatchesSearchBuilding());


   }



}

