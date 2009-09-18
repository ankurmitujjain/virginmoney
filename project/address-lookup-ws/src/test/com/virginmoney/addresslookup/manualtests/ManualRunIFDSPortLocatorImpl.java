package com.virginmoney.addresslookup.manualtests;

import org.apache.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.serviceproxy.ifds.IFDSPortLocatorImpl;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttpPort;

/**
 * Manually tests that the IFDS port locator returns a valid port which can be used to access IFDS.
 *
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class ManualRunIFDSPortLocatorImpl {

    private static Logger logger = Logger.getLogger(ManualRunIFDSPortLocatorImpl.class);

    private IFDSPortLocatorImpl testIFDSPortLocatorImpl;
    private OnlineServicesSoapHttpPort testPort;

    @Before
    public void setUp() throws Exception {

        SpringBuilder.setSystemProperties();

        testIFDSPortLocatorImpl = new IFDSPortLocatorImpl();
        assertNotNull(testIFDSPortLocatorImpl);

        testPort = testIFDSPortLocatorImpl.getWebServicePort();
        assertNotNull(testPort);

    }

    /**
     * Tests that the ifds port will return results for a postcode search.
     * @throws Exception
     */
    @Test
    public void testLookupWithoutHouseNumber() throws Exception {

        String searchPostcode = "NR14 8Pa";
        String searchAddressLine = "";

        String xmlResponse = doAddressSearch(searchPostcode,searchAddressLine );

        assertNotNull(xmlResponse);
    }

    /**
     * Tests that the ifds port will return results for a postcode search which includes a house name/number.
     * @throws Exception
     */
    @Test
    public void testLookupWithHouseNumber() throws Exception {

        String searchPostcode = "NR14 8Pa";
        String searchAddressLine = "honeycombe";

        String xmlResponse = doAddressSearch(searchPostcode,searchAddressLine );

        assertNotNull(xmlResponse);
    }

     /**
     * Runs various searches to obtain data for testing (check the logs afterwards).
     * @throws Exception
     */
    @Test
    public void scavengeAddressData() throws Exception {

        assertNotNull(doAddressSearch("NR46EJ", null));
        assertNotNull(doAddressSearch("NR148PA", null));
        assertNotNull(doAddressSearch("NR86BD", null));
        assertNotNull(doAddressSearch("N103EL", null));
        assertNotNull(doAddressSearch("N120DT", null));
        assertNotNull(doAddressSearch("XX999XX", null));  // non-existent
    }


    /**
     * Utility function to perform an address search
     * @param searchAddressLine
     * @param searchPostcode
     * @return
     * @throws java.rmi.RemoteException
     */
    private String doAddressSearch(String searchPostcode,String searchAddressLine) throws java.rmi.RemoteException {
        String xmlRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<getAddress_Req><header><mc>000045</mc><source><systemInstance>33</systemInstance><subSystemInstance>0</subSystemInstance>" +
                "<requestUniqueID>0</requestUniqueID></source><sessionID>0</sessionID></header>" +
                "<request><addressLine>" + searchAddressLine + "</addressLine><postCode>" + searchPostcode +
                "</postCode></request></getAddress_Req>";

        String xmlResponse = testPort.getAddress(xmlRequest);

        logger.debug("doAddressSearch()  request for " + searchPostcode + ":" + searchAddressLine + " =\n" +
                xmlRequest);
        logger.debug("doAddressSearch() response for " + searchPostcode + ":" + searchAddressLine + " =\n" +
                xmlResponse);
        return xmlResponse;
    }

}


