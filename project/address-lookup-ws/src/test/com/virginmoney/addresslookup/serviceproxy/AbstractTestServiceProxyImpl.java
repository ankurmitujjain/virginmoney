package com.virginmoney.addresslookup.serviceproxy;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AbstractAddress;

/**
 * Abstract class to perform tests on a service proxy implementation
 * Copyright     :  Virgin Money Ltd.
 */
public abstract class AbstractTestServiceProxyImpl {

    private static Logger logger = Logger.getLogger(AbstractTestServiceProxyImpl.class);

    protected AddressSearchServiceProxy serviceProxy = null;
    protected AddressSearchParameters.ReturnPAFDataRules returnPAFData = AddressSearchParameters.ReturnPAFDataRules.noPAFData;


    @Before
    public void setUp() throws Exception {
        SpringBuilder.setSystemProperties();
        getProxy();
        Assert.assertNotNull("serviceProxy should not be null", serviceProxy);
    }

    /**
     * Sets up the proxy for the tests.
      */
    protected abstract void getProxy() throws Exception ;



     /**
      * Checks that an expected building name is returned and that an unexpected one isnt
      */
     @Test
     public void TestBuildingNameInResults() throws Exception {
         String searchPostcode = "NR14 8PA";
         String searchBuilding = "";
         AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);

         resultsContainFragment(results,"Honeycombe",true); // Nicks house is here
         resultsContainFragment(results,"Ye Olde Crisp Factory",false); // definitely not
    }


    /**
     * Checks that an expected building number is returned and that an unexpected one isnt
     */
     @Test
    public void TestBuildingNumberInResults() throws Exception {
        String searchPostcode = "NR8 6BD";
        String searchBuilding = "";
        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);
        resultsContainFragment(results,"113",true); // This house is here
        resultsContainFragment(results,"dummy",false); // definitely not
   }


    protected AddressList doAddressSearch(String searchPostcode, String searchBuilding,
                                                  boolean returnOnlyMatchingResults) throws ServiceProxyException {

        AddressSearchParameters.ReturnMatchingResultRules returnMatchingResultRule = (returnOnlyMatchingResults?AddressSearchParameters.ReturnMatchingResultRules.onlyMatchingResults:AddressSearchParameters.ReturnMatchingResultRules.allResults);

        AddressSearchParameters searchParameters = new AddressSearchParameters(searchPostcode,searchBuilding,null,null,returnMatchingResultRule,null,
                returnPAFData);

        AddressList results = serviceProxy.doPostcodeSearch(searchParameters);
        return results;
    }

    /**
     * Checks that an expected building number is returned and that an unexpected one isnt
     */
     @Test
    public void TestFlatNumberInResults() throws Exception {
        String searchPostcode = "N12 0DT";
        String searchBuilding = "";
        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);
        resultsContainFragment(results,"Flat 7",true); // This house is here
        resultsContainFragment(results,"Flat Tyre",false); // definitely not
   }


    /**
     * Checks that an empty result is returned when searching for a non-existent postcode.
     */
    @Test
    public void TestInvalidPostcode() throws Exception {
        String searchPostcode = "QQ123QQ";
        String searchBuilding = "";
        AddressList results = doAddressSearch(searchPostcode, searchBuilding,false);
        List<AbstractAddress> addresses = results.getAddresses();
        Assert.assertNotNull("Search for nonexistint postcode should not result in null results",addresses);
        Assert.assertTrue("Search for nonexistint postcode should result in zero results",addresses.size() == 0);
   }


    /**
     * Checks that only matching results are returned when the option is enabled
     */
    @Test
    public void TestMatchingResultsOnly() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Greenways";

        AddressList results = doAddressSearch(searchPostcode, searchBuilding,true);

        Assert.assertNotNull("results should not be nulll" , results);
        Assert.assertTrue("results should be returned " , results.getAddresses().size() > 0);

        AbstractAddress firstAddressFound = results.getAddresses().get(0);
        Assert.assertTrue("First result is the one searched for",addressContainsFragment(firstAddressFound,searchBuilding));

        // This is also a handy place to check the picklist
        Assert.assertNotNull("picklist should not be null",firstAddressFound.getPicklistEntry());
        Assert.assertNotNull("picklist should contain the search name",StringUtils.containsIgnoreCase(firstAddressFound.getPicklistEntry(),searchBuilding));

        // check for any non-matching results and fail if we find them
         for (AbstractAddress abstractAddress : results.getAddresses()) {
               Assert.assertTrue("Only expecting to find building '" + searchBuilding + "' in results : found " + abstractAddress
                       .getPicklistEntry(),addressContainsFragment(firstAddressFound,searchBuilding));
             }

   }





    /**
     * Utility method to search a list of results for a given building name.
     * @param results
     * @param searchFragment
     * @param expected
     */

    protected void resultsContainFragment(AddressList results,  String searchFragment, boolean expected ) {
        Assert.assertNotNull("results should not be null", results);
        Assert.assertTrue("results should not be empty ", results.getAddresses().size() > 0);

        AbstractAddress abstractAddress = getAddressFromResults(results, searchFragment);

        if (expected) {
            Assert.assertTrue("buildingName \"" + searchFragment + "\" should be found in search ", abstractAddress != null);
        }
        else {
            Assert.assertTrue("buildingName \"" + searchFragment + "\" should not be found in search", abstractAddress == null);

        }
    }




    /**
     * Finds an address in a list of results, using building name as a search key.
     * @param results
     * @param searchString
     * @return
     */
    protected AbstractAddress getAddressFromResults(AddressList results, String searchString) {
        for (AbstractAddress address : results.getAddresses()) {

            // If the result has unformatted PAF address data then use it to match against
            if (addressContainsFragment(address, searchString))
                return address;
        }
        return null;
    }

    /**
     * checks to see if a given Address contains a string value.
     * @param address  the address to search
     * @param searchString  The string to search for
     * @return  true if searchString is in the address
     */
    protected boolean addressContainsFragment(AbstractAddress address, String searchString) {

        // Use the unformatted PAF data if its available
        if (address.getPafData() != null) {
            String addressBuildingName = address.getPafData().getBuildingName();
            String addressBuildingNumber = address.getPafData().getBuildingNumber();
            String addressSubBuildingName = address.getPafData().getSubBuildingName();
            String organisationName = address.getPafData().getOrganisationName();
            if (StringUtils.equalsIgnoreCase(addressBuildingName, searchString) ||
                    StringUtils.equalsIgnoreCase(addressBuildingNumber, searchString) ||
                    StringUtils.equalsIgnoreCase(addressSubBuildingName, searchString) ||
                    StringUtils.equalsIgnoreCase(organisationName, searchString)) 
            {
                return true;
            }

        }
        // otherwise use the simple address data
        if (StringUtils.containsIgnoreCase(address.getAddressLine1(), searchString) ||
                StringUtils.containsIgnoreCase(address.getAddressLine2(), searchString)) {
            //                logger.trace("found it :" + address);
            return true;
        }
        return false;
    }




}

