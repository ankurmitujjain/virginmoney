package com.virginmoney.addresslookup.manualtests;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.ServiceProviders;
import com.virginmoney.addresslookup.ws.AbstractTestEndpointInvalidSearches;

/**
 *  This class extents {@link com.virginmoney.addresslookup.ws.AbstractTestEndpointInvalidSearches} and tests the endpoint with invalid searches
 *  using the live (i.e non-mocked) IFDS service.

 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class ManualRunLiveEndpointIFDSInvalidSearches extends AbstractTestEndpointInvalidSearches {

    private static Logger logger = Logger.getLogger(ManualRunLiveEndpointIFDSInvalidSearches.class);

@Test
public void Dummy(){
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.IFDS;
        super.returnPAFData = false;
    }


}

