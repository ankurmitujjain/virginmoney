
package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.mockObjects;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import uk.co.postcodeanywhere.services.simplelookup.messages.ArrayOfAddress;
import uk.co.postcodeanywhere.services.simplelookup.messages.Address;

/**
 * Performs some basic tests on {@link MockPostcodeAnywhereSoap} to verify that it works correctly before it is used in testing
 the service proxy.
 */

public class TestMockPostcodeAnywhereSoap {
private Logger logger = Logger.getLogger(TestMockPostcodeAnywhereSoap.class);

private MockPostcodeAnywhereSoap testMockPostcodeAnywhereSoap;
    
@Before
    public void setUp() throws Exception {
       testMockPostcodeAnywhereSoap = new MockPostcodeAnywhereSoap();
       assertNotNull(testMockPostcodeAnywhereSoap);
    }

@After
    public void tearDown() throws Exception {
    
        testMockPostcodeAnywhereSoap = null;
    }

    @Test
        public void testNR46EJ() throws Exception {
            ArrayOfAddress results  = doAddressSearch("NR4 6EJ","");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Whiting Road"));
        }

    @Test
        public void testNR148PA() throws Exception {
            ArrayOfAddress results  = doAddressSearch("NR14 8PA","");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Honeycombe"));
        }
    @Test
        public void testNR148PAWithBuilding() throws Exception {
            ArrayOfAddress results  = doAddressSearch("NR14 8PA","Honeycombe");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Honeycombe"));
            assertTrue("Response should not contain any other address",!checkForAddress(results,"Greenways"));
        }

    @Test
        public void testNR86BD() throws Exception {
            ArrayOfAddress results  = doAddressSearch("NR8 6BD","");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Drayton High Road"));
        assertTrue("Response should contain a known house number",checkForAddress(results,"113"));
        }
   @Test
        public void testNR86BDwithBuilding() throws Exception {
            ArrayOfAddress results  = doAddressSearch("NR8 6BD","113");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Drayton High Road"));
            assertTrue("Response should contain a known house number",checkForAddress(results,"113"));
            assertTrue("Response should not contain any other house number",!checkForAddress(results,"115"));
        }

    @Test
        public void testN103EL() throws Exception {
            ArrayOfAddress results  = doAddressSearch("N10 3EL","");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Midhurst Mansions"));
        }

    @Test
        public void testN120DT() throws Exception {
            ArrayOfAddress results  = doAddressSearch("N12 0DT","");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain a known address",checkForAddress(results,"Flat 35"));
        }

    @Test
        public void testNonExistentPostcode() throws Exception {
            ArrayOfAddress results  = doAddressSearch("AB123CD","");
            assertNotNull("Response should not be null", results);
            assertTrue("Response should contain an error message",results == null || results.getAddress() == null ||
                    results.getAddress().size() == 0);
        }

    
    private ArrayOfAddress doAddressSearch(String searchPostcode,String searchBuilding) {
        logger.debug("doAddressSearch() searching for " + searchPostcode);
        return testMockPostcodeAnywhereSoap.fastAddress2(searchPostcode,searchBuilding,null,null,null,null,null,null);
    }

    private boolean checkForAddress(ArrayOfAddress response,String searchString) {

        if (response == null || response.getAddress() == null || response.getAddress().size() == 0) {
            return false;
        }

        for (Address address : response.getAddress()) {
            if (address.getLine1().contains(searchString) ||
                address.getLine2().contains(searchString) ||
                address.getLine3().contains(searchString) ||
                address.getLine4().contains(searchString) ||
                address.getLine5().contains(searchString) 
                    ) {
                return true;
            }
        }

        return false;
    }

}
