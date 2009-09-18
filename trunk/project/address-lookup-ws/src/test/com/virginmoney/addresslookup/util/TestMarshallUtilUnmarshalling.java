package com.virginmoney.addresslookup.util;

import javax.xml.bind.UnmarshalException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.co.ifdsgroup.onlineservices.messages.getAddressRes.GetAddressRes;

/**
 * Tests the marshalling utility with a variety of unmarshalling scenarios.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMarshallUtilUnmarshalling {

    private String responseXML;
    private static Logger logger = Logger.getLogger(TestMarshallUtilUnmarshalling.class);
    /*
   NOTE - If you are running this from within the IntelliJ 'Run' command and are having
       problems with it not finding the schema named below do the followinf

       go to File... Settings... Compiler and at the top is a box with resource patterns in it.
       Add the file pattern you need, e.g ?*.xsd to it and it will work
    */

    private static final String GetAddressResSchemaName = "WEB-INF/wsdl/ifds/schemas/getAddress_Res.xsd"; // READ ABOVE COMMENT re classpaths

    @Before
    public void setUp() throws Exception {

            responseXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><getAddress_Res>\n" + "<header><mc>000045</mc>\n" +
                    "<source><systemInstance>33</systemInstance><subSystemInstance>0</subSystemInstance><requestUniqueID>0</requestUniqueID><responseUniqueID/></source>\n" +
                    "<sessionID>0</sessionID></header><error><success>true</success></error>\n" +
                    "<request><addressLine/><postCode>NR14 8Pa</postCode></request>\n" + "<response><addresses>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Tas Valley House</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Tara</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Holy Cross Vicarage</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Old Mill Restaurant</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Netherfield</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Jacama</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Honeycombe</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>High Beech House</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Greenways</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Torre</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Dalriada</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Craigley</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Cordovan</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Alveston</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Mill Road Stores</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Mill House</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Stonecroft</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Purbeck Cottage</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Dovedale</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Low Meadow</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Post Office</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>The Enchanted Rose</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Mill Cottage</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>The Blue Barn</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>A R B G Dive Supplies,The Workshops,Mill House</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines><postCode>NR14 8PA</postCode></address></addressElements>\n" +
                    "</addresses></response></getAddress_Res>";

    assertNotNull(responseXML);
    }

    @After
    public void tearDown() throws Exception {

            responseXML = null;
    }

    @Test
    /**
     * Unmarshalls a valid response and checks the contents of the resulting Object.
     */
    public void testUnMarshallNoValidation() throws Exception {
            try {

            GetAddressRes responseObj =
            (GetAddressRes)MarshalUtil.unmarshal(responseXML, GetAddressRes.class);

    assertTrue("Response must not be null", responseObj != null);
    assertTrue("Response contains header", responseObj.getHeader() != null);
    assertEquals("Response contains mc=000045", "000045", responseObj.getHeader().getMc());
    assertTrue("Response contains 'response'", responseObj.getResponse() != null);
    assertTrue("Response contains 'addresses'", responseObj.getResponse().getAddresses() != null);
    assertTrue("Response contains product entries",
            responseObj.getResponse().getAddresses().getAddressElements().size() > 0);
    assertTrue("Response contains product entries",
            responseObj.getResponse()
            .getAddresses()
            .getAddressElements()
            .get(0)
            .getAddress()
            .getPostCode().equalsIgnoreCase("NR14 8PA"));
    }
            catch (Exception e) {

            fail("testUnMarshallNoValidation() Caught unexpected exception : " + e);
    }
            }

    @Test
    /**
     * Unmarshalls a valid response and checks the contents of the resulting Object.
     */
    public void testUnMarshallValidValidation() throws Exception {
            try {

            GetAddressRes responseObj = (GetAddressRes)MarshalUtil.unmarshalAndValidate(responseXML,
            GetAddressRes.class, GetAddressResSchemaName);

    assertTrue("Response must not be null", responseObj != null);
    assertTrue("Response contains header", responseObj.getHeader() != null);
    assertEquals("Response contains mc=000045", "000045", responseObj.getHeader().getMc());
    assertTrue("Response contains 'response'", responseObj.getResponse() != null);
    assertTrue("Response contains 'products'", responseObj.getResponse().getAddresses() != null);
    assertTrue("Response contains product entries",
            responseObj.getResponse().getAddresses().getAddressElements().size() > 0);
    assertTrue("Response contains product entries",
            responseObj.getResponse()
            .getAddresses()
            .getAddressElements()
            .get(0)
            .getAddress()
            .getPostCode().equalsIgnoreCase("NR14 8PA"));
    }
            catch (Exception e) {

            fail("testUnMarshallValidValidation() Caught unexpected exception : " + e);
    }
            }



    @Test
    /**
     * Unmarshalls a valid response and checks the contents of the resulting Object.
     */
    public void testUnMarshallNoPostcodeValidation() throws Exception {

            // comment out part of the response to force an error
        responseXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><getAddress_Res>\n" + "<header><mc>000045</mc>\n" +
                "<source><systemInstance>33</systemInstance><subSystemInstance>0</subSystemInstance><requestUniqueID>0</requestUniqueID><responseUniqueID/></source>\n" +
                "<sessionID>0</sessionID></header><error><success>true</success></error>\n" +
                "<request><addressLine/><postCode>NR14 8Pa</postCode></request>\n" +
                "<response><addresses>\n" +
                "<addressElements><addressKind>unknown</addressKind><address><lines><line><lineNo>1</lineNo><addressLine>Tas Valley House</addressLine></line><line><lineNo>2</lineNo><addressLine>Mill Road</addressLine></line><line><lineNo>3</lineNo><addressLine>Stoke Holy Cross</addressLine></line><line><lineNo>4</lineNo><addressLine>Norwich</addressLine></line><line><lineNo>5</lineNo><addressLine>Norfolk</addressLine></line></lines>" +
//                "<postCode>NR14 8PA</postCode>" +
                "</address></addressElements>\n" +
                "</addresses></response>" +
                "</getAddress_Res>";

    try {

            GetAddressRes responseObj = (GetAddressRes)MarshalUtil.unmarshalAndValidate(responseXML,
            GetAddressRes.class, GetAddressResSchemaName);

    fail(
            "testUnMarshallNoProductsValidation() unexpected success, was expecting MarshallException but not thrown");
    }
            catch (UnmarshalException e) {
            logger.debug("testUnMarshallNoProductsValidation() Caught expected exception : " + e);
    assertTrue("testUnMarshallNoProductsValidation() Caught expected exception : " + e, true);
    }
            catch (Exception e) {
            fail("testUnMarshallNoProductsValidation() Caught unexpected exception : " + e);
    }

            }


    @Test
    /**
     * Unmarshalls a valid response and checks th e contents of the resulting Object.
     */
    public void testUnMarshallNullResponseValidation() throws Exception {

            try {

            GetAddressRes responseObj = (GetAddressRes)MarshalUtil.unmarshalAndValidate(null,
            GetAddressRes.class, GetAddressResSchemaName);

    fail(
            "testUnMarshallNullResponseValidation() unexpected success, was expecting MarshallException but not thrown");
    }
            catch (UnmarshalException e) {
            logger.debug("testUnMarshallNullResponseValidation() Caught expected exception : " + e);
    assertTrue("testUnMarshallNullResponseValidation() Caught expected exception : " + e, true);
    }
            catch (Exception e) {
            fail("testUnMarshallNullResponseValidation() Caught unexpected exception : " + e);
    }

            }


    @Test
    /**
     * Unmarshalls a valid response and checks the contents of the resulting Object.
     */
    public void testUnMarshallMissingSchemaValidation() throws Exception {
            try {

            GetAddressRes responseObj = (GetAddressRes)MarshalUtil.unmarshalAndValidate(responseXML,
            GetAddressRes.class,
            "WEB-INF/wsdl/ifds/schemas/doesntExist.xsd");

    fail(
            "testUnMarshallMissingSchemaValidation() unexpected success, was expecting SchemaNotFoundException but not thrown");
    }
            catch (SchemaNotFoundException e) {
            logger.debug("testUnMarshallMissingSchemaValidation() Caught expected exception : " + e);
    assertTrue("testUnMarshallMissingSchemaValidation() Caught expected exception : " + e, true);
    }
            catch (Exception e) {
            fail("testUnMarshallMissingSchemaValidation() Caught unexpected exception : " + e);
    }

            }

            }

