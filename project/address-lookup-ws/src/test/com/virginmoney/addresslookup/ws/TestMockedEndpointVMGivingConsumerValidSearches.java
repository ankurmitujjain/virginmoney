package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.ServiceProviders;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 *  This class extents {@link AbstractTestEndpointValidSearches} and tests the endpoint with valid searches
 *  using a mocked postcode anywhere service for the VM Giving website consumer account.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMockedEndpointVMGivingConsumerValidSearches extends TestMockedEndpointPostcodeAnywhereValidSearches{

    private static Logger logger = Logger.getLogger(TestMockedEndpointVMGivingConsumerValidSearches.class);

@Test
public void Dummy() throws Exception {
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);

}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.VM_GIVING_CONSUMER;
        super.returnPAFData = true;
    }

}

