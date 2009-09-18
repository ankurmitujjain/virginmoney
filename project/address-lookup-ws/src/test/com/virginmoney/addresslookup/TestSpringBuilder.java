package com.virginmoney.addresslookup;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import static org.junit.Assert.*;
import com.virginmoney.addresslookup.messages.AddressLookupServicePortType;
import com.virginmoney.addresslookup.serviceproxy.AddressSearchServiceProxy;

/**
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestSpringBuilder {

    private static Logger logger = Logger.getLogger(TestSpringBuilder.class);

    /**
     * Test that the environment setup class can create an instance of ApplicationContext.
     */
    @Test
    public void testGetApplicationContext() {
        ApplicationContext testAc = SpringBuilder.getApplicationContext();
        assertNotNull("Application context not found", testAc);
    }
    /**
     * Test that the environment setup class can create an instance of the service endpoint.
     */
   @Test
    public void testGetEndPointFromContext() {
        ApplicationContext testAc = SpringBuilder.getApplicationContext();
        assertNotNull("Application context not found", testAc);

        Object endpointObj = testAc.getBean(SpringBuilder.ENDPOINT_BEAN_NAME);
        assertNotNull("Endpoint with mock services not found",endpointObj);
        assertTrue("Enpoint not an instance of AddressLookupServicePortType",endpointObj instanceof AddressLookupServicePortType);


    }
    /**
     * Test that the environment setup class can create an instance of the service endpoint with mock service entries.
     */
   @Test
    public void testGetEndPointWithMockServicesFromContext() {
        ApplicationContext testAc = SpringBuilder.getApplicationContext();
        assertNotNull("Application context not found", testAc);

        Object endpointObj = testAc.getBean(SpringBuilder.ENDPOINT_WITH_MOCK_SERVICES_BEAN_NAME);
        assertNotNull("Endpoint with mock services not found",endpointObj);
        assertTrue("Enpoint not an instance of AddressLookupServicePortType",endpointObj instanceof AddressLookupServicePortType);

    }

   /**
     * Test that the environment setup class can create an instance of the service endpoint with mock service entries.
     */
   @Test
    public void testGetEndPointWithMockServicesFromSetup() {

        AddressLookupServicePortType endpoint = SpringBuilder.getEndPointWithMockServices();
        assertNotNull("Endpoint with mock services not found",endpoint);

    }

    /**
     * Test that the environment setup class can create an instance of the postcode anywhere service proxy with mock
     * service entries.
     */
   @Test
    public void testGetPostcodeAnywhereServiceProxyWithMockServicesFromContext() {
        AddressSearchServiceProxy serviceProxy = SpringBuilder.getPostcodeAnywhereServiceProxyWithMockServices();
        assertNotNull("Endpoint with mock services not found",serviceProxy);
        logger.debug("testGetPostcodeAnywhereServiceProxyWithMockServicesFromContext() located " + serviceProxy.getClass().getName());

    }

   /**
    * Test that the environment setup class can create an instance of the IFDS service proxy with mock
    * service entries.
     */
   @Test
    public void testGetIFDSServiceProxyWithMockServicesFromContext() {
        AddressSearchServiceProxy serviceProxy = SpringBuilder.getIFDSServiceProxyWithMockServices();
        assertNotNull("Endpoint with mock services not found",serviceProxy);
       logger.debug("testGetIFDSServiceProxyWithMockServicesFromContext() located " + serviceProxy.getClass().getName());

    }
}

