
package com.virginmoney.addresslookup.business;

import java.util.List;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;

/**
 Junit test class for {@link AddressStyle1} usimg {@link PAFData} address data.
*/

public class TestAddressFromPAF {
private Logger logger = Logger.getLogger(TestAddressFromPAF.class);

private AbstractAddress testAddress;
private List<PAFData> pafAddresses;
private PAFData testPAFData;

    @Before
    public void setUp() throws Exception {

       pafAddresses = PAFDataBuilder.buildListOfPAFAddresses();
       assertNotNull(pafAddresses);
       assertTrue(pafAddresses.size() > 0);

       testPAFData = pafAddresses.get(0);

        AddressSearchParameters searchParameters = new AddressSearchParameters("NR103EL",
                null,
                AddressSearchParameters.ResultFormats.STYLE1,
                AddressSearchParameters.ServiceProviders.PostcodeAnywhere,
                AddressSearchParameters.defaultReturnMatchingResultRule,
                AddressSearchParameters.defaultSortMatchingResultRule,
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData);
        testAddress = new AddressStyle1(searchParameters,null,null,null,null,null,testPAFData);
       assertNotNull(testAddress);
    }

@After
    public void tearDown() throws Exception {
    
        testAddress = null;
        pafAddresses = null;
    }



@Test
    public void testGetPafData() throws Exception {
        logger.debug("             testPAFData=" + testPAFData);
        logger.debug("testaddress.getPafData()=" + testAddress.getPafData());
        assertEquals("PAFData should agree with source data ", testPAFData,testAddress.getPafData());
    }
}
