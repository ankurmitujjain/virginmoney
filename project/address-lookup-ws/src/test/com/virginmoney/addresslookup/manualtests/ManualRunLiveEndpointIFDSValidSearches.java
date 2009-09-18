package com.virginmoney.addresslookup.manualtests;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import com.virginmoney.addresslookup.ws.AbstractTestEndpointValidSearches;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.messages.ServiceProviders;

/**
 * This class extents {@link com.virginmoney.addresslookup.ws.AbstractTestEndpointValidSearches} and tests
 * the endpoint using valid searches to the live (i.e running, non-mock) IFDS service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class ManualRunLiveEndpointIFDSValidSearches extends AbstractTestEndpointValidSearches {

    private static Logger logger = Logger.getLogger(ManualRunLiveEndpointIFDSValidSearches.class);

@Test
public void Dummy() throws Exception{
    Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPoint();
        super.searchProvider = ServiceProviders.IFDS;
        super.returnPAFData = false;
    }


}

