package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.messages.ServiceProviders;

/**
 *  This class extents {@link AbstractTestEndpointInvalidSearches} and tests the endpoint with invalid searches
 *  using a mocked postcode anywhere service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMockedEndpointPostcodeAnywhereInvalidSearches extends AbstractTestEndpointInvalidSearches{

    private static Logger logger = Logger.getLogger(TestMockedEndpointPostcodeAnywhereInvalidSearches.class);
    
@Test
public void Dummy(){
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.POSTCODE_ANYWHERE;
        super.returnPAFData = true;
    }

}

