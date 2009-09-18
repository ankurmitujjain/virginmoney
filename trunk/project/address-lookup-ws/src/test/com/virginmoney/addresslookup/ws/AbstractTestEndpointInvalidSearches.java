package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.util.ServiceUtil;
import com.virginmoney.addresslookup.messages.*;

/**
 * Abstract class to define tests using invalid search criteria for the service endpoint.
 * Subclasses of this implement different service providers and endpoints with 'mock' or 'live' services.
 */
public abstract class AbstractTestEndpointInvalidSearches extends AbstractTestEndPointSearches {

    private static Logger logger = Logger.getLogger(AbstractTestEndpointInvalidSearches.class);


    /**
     * Checks that an error is raised when no search criteria are passed in.
     */
    @Test
    public void TestNullPostcode() throws Exception {
        String searchPostcode = null;
        String searchBuilding = null;

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        logger.debug("TestNullPostcode() response=\n" + ServiceUtil.messageToString(results,true));
        logger.debug("TestNullPostcode() results.getResultCount() =" + results.getResultCount());
        logger.debug("TestNullPostcode() results.getErrors()      =" + results.getErrors());
        logger.debug("TestNullPostcode() results.getAddresses()   =" + results.getAddresses());

        Assert.assertNotNull("results should have an Errors object",results.getErrors());
        Assert.assertTrue("results should have zero results",results.getResultCount() == 0);
        Assert.assertTrue("results should not have an Addresses object",results.getAddresses() == null || results.getAddresses().size() == 0);
        Assert.assertTrue("results should contain a postcode error",checkForErrorKey(results.getErrors(),"address-lookup-ws.postcode.null"));

   }


    /**
     * Checks that an error is raised when a postcode which is too long is passed in.
     */
    @Test
    public void TestTooLongPostcode() throws Exception {
        String searchPostcode = "NR4 6EJ ABC";
        String searchBuilding = null;

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        logger.debug("TestToolongPostcode() response=\n" + ServiceUtil.messageToString(results,true));
        logger.debug("TestToolongPostcode() results.getResultCount() =" + results.getResultCount());
        logger.debug("TestToolongPostcode() results.getErrors()      =" + results.getErrors());
        logger.debug("TestToolongPostcode() results.getAddresses()   =" + results.getAddresses());

        Assert.assertNotNull("results should have an Errors object",results.getErrors());
        Assert.assertTrue("results should have zero results",results.getResultCount() == 0);
        Assert.assertTrue("results should not have an Addresses object",results.getAddresses() == null || results.getAddresses().size() == 0);
        Assert.assertTrue("results should contain a postcode error",checkForErrorKey(results.getErrors(),"address-lookup-ws.postcode.length.toolong"));

   }

    /**
     * Checks that an error is raised when a postcode which is too short is passed in.
     */
    @Test
    public void TestTooShortPostcode() throws Exception {
        String searchPostcode = "NR4";
        String searchBuilding = null;

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        logger.debug("TestTooshortPostcode() response=\n" + ServiceUtil.messageToString(results,true));
        logger.debug("TestTooshortPostcode() results.getResultCount() =" + results.getResultCount());
        logger.debug("TestTooshortPostcode() results.getErrors()      =" + results.getErrors());
        logger.debug("TestTooshortPostcode() results.getAddresses()   =" + results.getAddresses());

        Assert.assertNotNull("results should have an Errors object",results.getErrors());
        Assert.assertTrue("results should have zero results",results.getResultCount() == 0);
        Assert.assertTrue("results should not have an Addresses object",results.getAddresses() == null || results.getAddresses().size() == 0);
        Assert.assertTrue("results should contain a postcode error",checkForErrorKey(results.getErrors(),"address-lookup-ws.postcode.length.tooshort"));

   }

    /**
     * Checks that no errors are raised when a non-existent postcode is passed in
     */
    @Test
    public void TestNonExistentPostcode() throws Exception {
        String searchPostcode = "XX999XX";
        String searchBuilding = null;

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        logger.debug("TestTooshortPostcode() response=\n" + ServiceUtil.messageToString(results,true));
        logger.debug("TestTooshortPostcode() results.getResultCount() =" + results.getResultCount());
        logger.debug("TestTooshortPostcode() results.getAddresses()   =" + results.getAddresses());

        Assert.assertTrue("results should not have an Errors object",results.getErrors() == null || results.getErrors().getErrors() ==null || results.getErrors().getErrors().size() == 00);
        Assert.assertTrue("results should have zero results",results.getResultCount() == 0);
        Assert.assertTrue("results should have an Addresses object",results.getAddresses() != null);
        Assert.assertTrue("results should have an empty Addresses object",results.getAddresses().size() == 0);

   }

    /**
     * Checks that no errors are raised when a non-existent postcode is passed in
     */
    @Test
    public void TestNonExistentPostcodeWithBuildingNumber() throws Exception {
        String searchPostcode = "XX999XX";
        String searchBuilding = "The old house";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        logger.debug("TestTooshortPostcode() response=\n" + ServiceUtil.messageToString(results,true));
        logger.debug("TestTooshortPostcode() results.getResultCount() =" + results.getResultCount());
        logger.debug("TestTooshortPostcode() results.getAddresses()   =" + results.getAddresses());

        Assert.assertTrue("results should not have an Errors object",results.getErrors() == null || results.getErrors().getErrors() ==null || results.getErrors().getErrors().size() == 00);
        Assert.assertTrue("results should have zero results",results.getResultCount() == 0);
        Assert.assertTrue("results should have an Addresses object",results.getAddresses() != null);
        Assert.assertTrue("results should have an empty Addresses object",results.getAddresses().size() == 0);

   }

   /**
    * Checks that no errors are raised when a non-existent postcode is passed in
    */
   @Test
   public void TestValidPostcodeWithNonExistentBuildingNumberAndMatchingResultsOnly() throws Exception {
       String searchPostcode = "NR14 8PA";
       String searchBuilding = "iqweuyreqwoiruydlkasjfhasdkbc";

       AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,true, true);

       logger.debug("TestTooshortPostcode() response=\n" + ServiceUtil.messageToString(results,true));
       logger.debug("TestTooshortPostcode() results.getResultCount() =" + results.getResultCount());
       logger.debug("TestTooshortPostcode() results.getAddresses()   =" + results.getAddresses());

       Assert.assertTrue("results should not have an Errors object",results.getErrors() == null || results.getErrors().getErrors() ==null || results.getErrors().getErrors().size() == 00);
       Assert.assertEquals("results should have zero results",0,results.getResultCount() );
       Assert.assertNotNull("results should have an Addresses object",results.getAddresses());
       Assert.assertEquals("results should have an empty Addresses object",0,results.getAddresses().size());

  }

}

