
package com.virginmoney.addresslookup.util;

import javax.xml.bind.MarshalException;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.GetAddressReq;
import com.virginmoney.addresslookup.serviceproxy.ifds.builders.GetAddressReqBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 Junit test class for {@link MarshalUtil}.
*/

public class TestMarshalUtilMarshalling {
private Logger logger = Logger.getLogger(TestMarshalUtilMarshalling.class);

private MarshalUtil testMarshalUtil;
private GetAddressReq testRequest;
private AddressSearchParameters testAddressSearchParameters;
private String testPostcode = "NR14 8PA";

    /*
   NOTE - If you are running this from within the IntelliJ 'Run' command and are having
       problems with it not finding the schema named below do the followinf

       go to File... Settings... Compiler and at the top is a box with resource patterns in it.
       Add the file pattern you need, e.g ?*.xsd to it and it will work
    */

    private static final String GetAddressReqSchemaName = "WEB-INF/wsdl/ifds/schemas/getAddress_Req.xsd"; // READ ABOVE COMMENT re classpaths

    @Before
    public void setUp() throws Exception {
       testMarshalUtil = new MarshalUtil();
       assertNotNull(testMarshalUtil);

        testAddressSearchParameters = new AddressSearchParameters(testPostcode,null);
        testRequest =  GetAddressReqBuilder.build(testAddressSearchParameters);

    }

@After
    public void tearDown() throws Exception {
    
        testMarshalUtil = null;
        testRequest = null;
        testAddressSearchParameters = null;
    }

    @Test
     /**
     * Creates a valid request, marshalls it and checks the contents of the resulting String.
     */
    public void testMarshallNoValidation() throws Exception {
        try {
            String requestXML = MarshalUtil.marshal(testRequest);
            logger.debug("testMarshallNoValidation() : requestXML=" + requestXML);
            Assert.assertTrue("Request must not be null", StringUtils.isNotBlank(requestXML));
            Assert.assertTrue("Request contains <header>", StringUtils.contains(requestXML, "<header>"));
            Assert.assertTrue("Request contains <mc>000045</mc>", StringUtils.contains(requestXML, "<mc>000045</mc>"));
            Assert.assertTrue("Request contains <getAddress_Req>",StringUtils.contains(requestXML, "<getAddress_Req>"));
            Assert.assertTrue("Request contains testPostcode " + testPostcode,StringUtils.contains(requestXML, testPostcode));
        }

        catch (Exception e) {
            Assert.fail("testMarshallNoValidation() Caught unexpected exception : " + e);
        }
    }

    @Test
     /**
     * Creates a valid request, marshalls it and checks the contents of the resulting String.
     */
    public void testMarshallValidValidation() throws Exception {
        try {
            String requestXML = MarshalUtil.marshalAndValidate(testRequest,GetAddressReqSchemaName);
            logger.debug("testMarshallNoValidation() : request=" + requestXML);
            Assert.assertTrue("Request must not be null", StringUtils.isNotBlank(requestXML));
            Assert.assertTrue("Request contains <header>", StringUtils.contains(requestXML, "<header>"));
            Assert.assertTrue("Request contains <mc>000045</mc>", StringUtils.contains(requestXML, "<mc>000045</mc>"));
            Assert.assertTrue("Request contains <getAddress_Req>",StringUtils.contains(requestXML, "<getAddress_Req>"));
            Assert.assertTrue("Request contains testPostcode " + testPostcode,StringUtils.contains(requestXML, testPostcode));
        }

        catch (Exception e) {
            Assert.fail("testMarshallNoValidation() Caught unexpected exception : " + e);
        }
    }

    @Test
    /**
     * Creates a valid request, marshalls it using an invalid schema name and checks that an exception is thrown.

     */
    public void testMarshallMissingSchemaValidation() throws Exception {
        try {
            String requestXML = MarshalUtil.marshalAndValidate(testRequest, "WEB-INF/wsdl/ifds/schemas/doesntExist.xsd");
            Assert.fail("testMarshallValidValidation() unexpected success, was expecting SchemaNotFoundException but not thrown");
        }
        catch (SchemaNotFoundException e) {
            Assert.assertTrue("testMarshallValidValidation() Caught expected exception : " + e ,true);
        }
        catch (Exception e) {
            Assert.fail("testMarshallValidValidation() Caught unexpected exception : " + e);
        }
    }

    @Test
    /**
     * Creates a valid request, removes the entire 'request' element, marshalls it and checks that a MarshalException is thrown.
     */
    public void testMarshallMissingRequestValidation() throws Exception {
        try {
            testRequest.setRequest(null);
            String requestXML = MarshalUtil.marshalAndValidate(testRequest, GetAddressReqSchemaName);
            Assert.fail("testMarshallValidValidation() unexpected success, was expecting SchemaNotFoundException but not thrown");
        }
        catch (MarshalException e) {
            logger.debug("testMarshallValidValidation() Caught expected exception : " + e);
            Assert.assertTrue("testMarshallValidValidation() Caught expected exception : " + e ,true);
        }
        catch (Exception e) {
            Assert.fail("testMarshallValidValidation() Caught unexpected exception : " + e);
        }
    }
    @Test
    /**
     * Creates a valid request, removes the product id, marshalls it and checks that a MarshalException is thrown.
     */
    public void testMarshallMissingProductIDValidation() throws Exception {
        try {
            testRequest.getRequest().setPostCode(null);
            String requestXML = MarshalUtil.marshalAndValidate(testRequest, GetAddressReqSchemaName);
            Assert.fail("testMarshallValidValidation() unexpected success, was expecting SchemaNotFoundException but not thrown");
        }
        catch (MarshalException e) {
            logger.debug("testMarshallValidValidation() Caught expected exception : " + e);
            Assert.assertTrue("testMarshallValidValidation() Caught expected exception : " + e ,true);
        }
        catch (Exception e) {
            Assert.fail("testMarshallValidValidation() Caught unexpected exception : " + e);
        }
    }
    @Test
    /**
     * Attempts to marshall a null object.
     */
    public void testMarshallNullStringValidation() throws Exception {
        try {
            String requestXML = MarshalUtil.marshalAndValidate(null, GetAddressReqSchemaName);
            Assert.fail("testMarshallValidValidation() unexpected success, was expecting MarshalException but not thrown");
        }
        catch (MarshalException e) {
            logger.debug("testMarshallValidValidation() Caught expected exception : " + e);
            Assert.assertTrue("testMarshallValidValidation() Caught expected exception : " + e ,true);
        }
        catch (Exception e) {
            Assert.fail("testMarshallValidValidation() Caught unexpected exception : " + e);
        }
    }



}
