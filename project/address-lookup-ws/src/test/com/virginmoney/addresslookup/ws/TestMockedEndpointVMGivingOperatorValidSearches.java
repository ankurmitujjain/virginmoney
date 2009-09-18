package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.AddressLookupResponse;
import com.virginmoney.addresslookup.messages.Address;
import com.virginmoney.addresslookup.messages.ServiceProviders;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 *  This class extents {@link com.virginmoney.addresslookup.ws.AbstractTestEndpointValidSearches} and tests the endpoint with valid searches
 *  using a mocked postcode anywhere service for the VM Giving callcentre operator account.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMockedEndpointVMGivingOperatorValidSearches extends TestMockedEndpointPostcodeAnywhereValidSearches{

    private static Logger logger = Logger.getLogger(TestMockedEndpointVMGivingOperatorValidSearches.class);

@Test
public void Dummy() throws Exception {
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);

}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.VM_GIVING_OPERATOR;
        super.returnPAFData = true;
    }



}

