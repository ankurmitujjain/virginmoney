package com.virginmoney.addresslookup.business;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.lang.StringUtils;

/**
 Junit test class for {@link com.virginmoney.addresslookup.business.AddressStyle1} when the address is constructed from 'simple'
 addresses.
*/

public class TestAddressFromSimple {
private Logger logger = Logger.getLogger(TestAddressFromSimple.class);

private AbstractAddress testAbstractAddress;
private List<AddressStyle1> Addresses;

    @Before
    public void setUp() throws Exception {

       Addresses = SimpleAddressBuilder.buildListOfSimpleAddresses(null);
       Assert.assertNotNull(Addresses);
       Assert.assertTrue(Addresses.size() > 0);

       testAbstractAddress = Addresses.get(0);

    }

@After
public void tearDown() throws Exception {

        testAbstractAddress = null;
        Addresses = null;
    }





@Test
    public void testGetPicklistEntry() throws Exception {
        Assert.assertTrue("picklist contains line 1 of  address", StringUtils.containsIgnoreCase(testAbstractAddress.getPicklistEntry(),
                testAbstractAddress.getAddressLine1()));
        Assert.assertTrue("picklist contains line 2 of  address", StringUtils.containsIgnoreCase(testAbstractAddress.getPicklistEntry(),
                testAbstractAddress.getAddressLine2()));
        Assert.assertTrue("picklist contains postcode of  address", StringUtils.containsIgnoreCase(testAbstractAddress.getPicklistEntry(),
                testAbstractAddress.getPostcode()));
    }

}

