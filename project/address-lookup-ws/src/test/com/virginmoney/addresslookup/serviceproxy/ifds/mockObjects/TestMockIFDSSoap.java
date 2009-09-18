
package com.virginmoney.addresslookup.serviceproxy.ifds.mockObjects;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 Performs some basic tests on {@link MockIFDSSoap} to verify that it works correctly before it is used in testing
 the IFDS service proxy.
*/

public class TestMockIFDSSoap {
private Logger logger = Logger.getLogger(TestMockIFDSSoap.class);

private MockIFDSSoap testMockIFDSSoap;
    
@Before
    public void setUp() throws Exception {
       testMockIFDSSoap = new MockIFDSSoap();
       assertNotNull(testMockIFDSSoap);
    }

@After
    public void tearDown() throws Exception {
    
        testMockIFDSSoap = null;
    }


@Test
    public void testNR46EJ() throws Exception {
        String xmlResponse = doAddressSearch("NR4 6EJ","");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain a known address", StringUtils.containsIgnoreCase(xmlResponse,"Whiting Road"));
    }

@Test
    public void testNR148PA() throws Exception {
        String xmlResponse = doAddressSearch("NR14 8PA","");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain a known address",StringUtils.containsIgnoreCase(xmlResponse,"Honeycombe"));
    }

@Test
    public void testNR148PAWithBuilding() throws Exception {
        String xmlResponse = doAddressSearch("NR14 8PA","Honeycombe");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain a known address",StringUtils.containsIgnoreCase(xmlResponse,"Honeycombe"));
        assertTrue("Response should not contain any other address",!StringUtils.containsIgnoreCase(xmlResponse,"Greenways"));
    }

@Test
    public void testNR86BD() throws Exception {
        String xmlResponse = doAddressSearch("NR8 6BD","");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain a known address",StringUtils.containsIgnoreCase(xmlResponse,"Drayton High Road"));
    }

    @Test
        public void testNR86BDWithBuilding() throws Exception {
            String xmlResponse = doAddressSearch("NR8 6BD","113");
            assertNotNull("Response should not be null", xmlResponse);
            assertTrue("Response should contain a known address",StringUtils.containsIgnoreCase(xmlResponse,"113"));
            assertTrue("Response should not contain any other address",!StringUtils.containsIgnoreCase(xmlResponse,"115"));
        }


@Test
    public void testN103EL() throws Exception {
        String xmlResponse = doAddressSearch("N10 3EL","");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain a known address",StringUtils.containsIgnoreCase(xmlResponse,"Midhurst Mansions"));
    }

@Test
    public void testN120DT() throws Exception {
        String xmlResponse = doAddressSearch("N12 0DT","");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain a known address",StringUtils.containsIgnoreCase(xmlResponse,"Flat 35"));
    }

@Test
    public void testNonExistentPostcode() throws Exception {
        String xmlResponse = doAddressSearch("AB123CD","");
        assertNotNull("Response should not be null", xmlResponse);
        assertTrue("Response should contain an error message",StringUtils.containsIgnoreCase(xmlResponse,"Postcode had no matching addresses"));
    }

    /**
     * Utility function to perform an address search
     * @param searchPostcode
     * @return
     * @throws java.rmi.RemoteException
     */
    private String doAddressSearch(String searchPostcode,String searchBuilding) throws java.rmi.RemoteException {


        String xmlRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<getAddress_Req><header><mc>000045</mc><source><systemInstance>33</systemInstance><subSystemInstance>0</subSystemInstance>" +
                "<requestUniqueID>0</requestUniqueID></source><sessionID>0</sessionID></header>" +
                "<request><addressLine>"+ searchBuilding + "</addressLine><postCode>" + searchPostcode +
                "</postCode></request></getAddress_Req>";

        String xmlResponse = testMockIFDSSoap.getAddress(xmlRequest);

        logger.debug("doAddressSearch  request for " + searchPostcode + " =\n" +
                xmlRequest);
        logger.debug("doAddressSearch response for " + searchPostcode + " =\n" +
                xmlResponse);
        return xmlResponse;
    }


}
