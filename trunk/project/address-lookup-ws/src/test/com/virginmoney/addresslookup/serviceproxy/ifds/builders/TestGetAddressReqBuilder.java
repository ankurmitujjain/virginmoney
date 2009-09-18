
package com.virginmoney.addresslookup.serviceproxy.ifds.builders;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.GetAddressReq;

/**
 Junit test class for {@link GetAddressReqBuilder}.
*/

public class TestGetAddressReqBuilder {
private Logger logger = Logger.getLogger(TestGetAddressReqBuilder.class);



@Before
    public void setUp() throws Exception {
    }

@After
    public void tearDown() throws Exception {
    }

@Test
    public void testMessageObject() throws Exception {
    AddressSearchParameters addressSearchParameters = new AddressSearchParameters("NR14 8PA",null,null,null,null,null,
            AddressSearchParameters.ReturnPAFDataRules.returnPAFData);

    GetAddressReq getAddressReq = GetAddressReqBuilder.build(addressSearchParameters);
    logger.debug("testMessageObject() got request " + getAddressReq);

    assertNotNull("GetAddressReq should not be null ", getAddressReq);
    assertNotNull("GetAddressReq.header should not be null ", getAddressReq.getHeader());
    assertNotNull("GetAddressReq.header.source should not be null ", getAddressReq.getHeader().getSource());
    assertNotNull("GetAddressReq.header.source.requestuniqueid should not be null ", getAddressReq.getHeader().getSource().getRequestUniqueID());

    assertNotNull("GetAddressReq.request should not be null ", getAddressReq.getRequest());
    assertNotNull("GetAddressReq.request.postcode should not be null ", getAddressReq.getRequest().getPostCode());
    assertEquals("GetAddressReq.request.postcode should match seach parameter ",addressSearchParameters.getSearchPostcode(), getAddressReq.getRequest().getPostCode());


}


}
