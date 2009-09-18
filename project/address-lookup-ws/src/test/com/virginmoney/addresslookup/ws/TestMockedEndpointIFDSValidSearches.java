package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.ServiceProviders;

/**
 * This class extents {@link AbstractTestEndpointValidSearches} and tests the endpoint with valid searches
 *  using a mocked IFDS service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMockedEndpointIFDSValidSearches extends AbstractTestEndpointValidSearches{

    private static Logger logger = Logger.getLogger(TestMockedEndpointIFDSValidSearches.class);

@Test
public void Dummy() throws Exception{
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
    super.TestNullValues();
}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.IFDS;
        super.returnPAFData = false;
    }


}

