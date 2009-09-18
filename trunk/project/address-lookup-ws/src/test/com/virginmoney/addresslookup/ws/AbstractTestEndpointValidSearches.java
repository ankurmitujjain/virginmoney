package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Assert;
import org.junit.Test;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.messages.*;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 * Abstract class to define tests using valid search criteria for the service endpoint.
 * Subclasses of this implement different service providers and endpoints with 'mock' or 'live' services.
 */
public abstract class AbstractTestEndpointValidSearches extends AbstractTestEndPointSearches {

    private static Logger logger = Logger.getLogger(AbstractTestEndpointValidSearches.class);

    @BeforeClass
    public static void setSystemProperties() throws Exception {

        SpringBuilder.setSystemProperties();

    }

    /** Checks that an expected building name is returned and that an unexpected one isnt */
    @Test
    public void TestBuildingNameInResults() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, true);
        Assert.assertNull("results should not have an Errors object", results.getErrors());
        Assert.assertTrue("results should have non-zero result count", results.getResultCount() > 0);

        resultsContainFragment(results, "Honeycombe", true);// Nicks house is here
        resultsContainFragment(results, "Ye Olde Crisp Factory", false);// definitely not

        Address firstAddressFound = results.getAddresses().get(0);
        Assert.assertFalse("First result does not have the 'MatchesSearchBuilding' flag set ",
                firstAddressFound.isMatchesSearchBuilding());


    }

    /** Checks that an expected building name is sorted to the start of the list */
    @Test
    public void TestBuildingNameSortedInResults() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Greenways";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, true);

        Assert.assertNotNull("results not null", results);
        Assert.assertTrue("results found ", results.getAddresses().size() > 0);

        Address firstAddressFound = results.getAddresses().get(0);
        Assert.assertTrue("First result is the one searched for",
                addressContainsFragment(firstAddressFound, searchBuilding));

        Assert.assertTrue("First result has the 'MatchesSearchBuilding' flag set ",
                firstAddressFound.isMatchesSearchBuilding());

        // This is also a handy place to check the picklist
        Assert.assertNotNull("picklist should not be null", firstAddressFound.getPicklistEntry());
        Assert.assertNotNull("picklist should contain the search name",
                StringUtils.containsIgnoreCase(firstAddressFound.getPicklistEntry(), searchBuilding));

    }

   /** Checks that an expected building name is sorted to the start of the list */
    @Test
    public void TestInvalidSearchBuildingNameNotInResults() throws Exception {
       String searchPostcode = "NR14 8PA";
       String searchBuilding = "Ye Olde Crisp Factory";

       AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, true);
       Assert.assertNull("results should not have an Errors object", results.getErrors());
       Assert.assertTrue("results should have non-zero result count", results.getResultCount() > 0);

       resultsContainFragment(results, "Honeycombe", true);// Nicks house is here
       resultsContainFragment(results, "Ye Olde Crisp Factory", false);// definitely not

       Address firstAddressFound = results.getAddresses().get(0);
       Assert.assertFalse("First result does not have the 'MatchesSearchBuilding' flag set ",
               firstAddressFound.isMatchesSearchBuilding());
    }

    /** Checks that an expected building number is returned and that an unexpected one isnt */
    @Test
    public void TestBuildingNumberInResults() throws Exception {
        String searchPostcode = "NR8 6BD";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, true);

        resultsContainFragment(results, "113", true);// This house is here
        resultsContainFragment(results, "dummy", false);// definitely not
    }

    /** Checks that an expected building number is returned and that an unexpected one isnt */
    @Test
    public void TestFlatNumberInResults() throws Exception {
        String searchPostcode = "N12 0DT";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, true);

        resultsContainFragment(results, "Flat 7", true);// This house is here
        resultsContainFragment(results, "Flat Tyre", false);// definitely not
    }

    /** Checks that an expected building number is sorted to the start of the list */
    @Test
    public void TestBuildingNumberSortedInResults() throws Exception {
        String searchPostcode = "NR8 6BD";
        String searchBuilding = "117";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, true);

        Assert.assertNotNull("results not null", results);
        Assert.assertTrue("results found ", results.getAddresses().size() > 0);

        Address firstAddressFound = results.getAddresses().get(0);
        Assert.assertTrue("First result is the one searched for",
                addressContainsFragment(firstAddressFound, searchBuilding));

        Assert.assertTrue("First result has the 'MatchesSearchBuilding' flag set ",
                firstAddressFound.isMatchesSearchBuilding());

    }

    /** Checks that an expected building number is not sorted to the start of the list when the sort flag is not set */
    @Test
    public void TestBuildingNameNotSortedInResults() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Greenways";

        // tell the endpoint not to sort the results
        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, false);

        Assert.assertNotNull("results not null", results);
        Assert.assertTrue("results found ", results.getAddresses().size() > 0);

        Address firstAddressFound = results.getAddresses().get(0);
        // the first result for NR14 8PA is 'Tas Valley House'
        Assert.assertFalse("First result should not be the one searched for because sort results is false : searching for "
                + searchBuilding + " first result=" + firstAddressFound.getPicklistEntry(),
                addressContainsFragment(firstAddressFound, searchBuilding));

        Assert.assertFalse("First result should not have the 'MatchesSearchBuilding' flag set  because sort results is " +
                "false: searching for " + searchBuilding + " first result=" + firstAddressFound.getPicklistEntry(),
                firstAddressFound.isMatchesSearchBuilding());

    }
    /** Checks that an expected building number is not sorted to the start of the list when the sort flag is not set */
    @Test
    public void TestBuildingNumberNotSortedInResults() throws Exception {
        String searchPostcode = "NR8 6BD";
        String searchBuilding = "117";

        // tell the endpoint not to sort the results
        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, false, false);

        Assert.assertNotNull("results not null", results);
        Assert.assertTrue("results found ", results.getAddresses().size() > 0);

        Address firstAddressFound = results.getAddresses().get(0);
        // the first result for NR8 6BD is 109
        Assert.assertFalse("First result should not be the one searched for because sort results is false : searching for "
                + searchBuilding + " first result=" + firstAddressFound.getPicklistEntry(),
                addressContainsFragment(firstAddressFound, searchBuilding));

        Assert.assertFalse("First result should not have the 'MatchesSearchBuilding' flag set  because sort results is " +
                "false: searching for " + searchBuilding + " first result=" + firstAddressFound.getPicklistEntry(),
                firstAddressFound.isMatchesSearchBuilding());

    }


    /** Checks that only matching results are returned when the option is enabled */
    @Test
    public void TestMatchingResultsOnly() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Greenways";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, true, true);

        Assert.assertNotNull("results not null", results);
        Assert.assertTrue("results found ", results.getAddresses().size() > 0);

        Address firstAddressFound = results.getAddresses().get(0);
        Assert.assertTrue("First result is the one searched for",
                addressContainsFragment(firstAddressFound, searchBuilding));

        Assert.assertTrue("First result has the 'MatchesSearchBuilding' flag set ",
                firstAddressFound.isMatchesSearchBuilding());

        // This is also a handy place to check the picklist
        Assert.assertNotNull("picklist should not be null", firstAddressFound.getPicklistEntry());
        Assert.assertNotNull("picklist should contain the search name",
                StringUtils.containsIgnoreCase(firstAddressFound.getPicklistEntry(), searchBuilding));

        // check for any non-matching results and fail if we find them
        for (Address address : results.getAddresses()) {
            Assert.assertTrue("Only expecting to find building '" + searchBuilding + "' in results : found " +
                    address.getPicklistEntry(),
                    StringUtils.containsIgnoreCase(firstAddressFound.getPicklistEntry(), searchBuilding));
        }
    }
    /** Checks that only matching results are returned when the option is enabled and sorting is disabled */
    @Test
    public void TestMatchingResultsOnlyWithoutSort() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Greenways";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding, true, false);

        Assert.assertNotNull("results not null", results);
        Assert.assertTrue("results found ", results.getAddresses().size() > 0);

        Address firstAddressFound = results.getAddresses().get(0);
        Assert.assertTrue("First result is the one searched for",
                addressContainsFragment(firstAddressFound, searchBuilding));

        Assert.assertTrue("First result has the 'MatchesSearchBuilding' flag set ",
                firstAddressFound.isMatchesSearchBuilding());

        // This is also a handy place to check the picklist
        Assert.assertNotNull("picklist should not be null", firstAddressFound.getPicklistEntry());
        Assert.assertNotNull("picklist should contain the search name",
                StringUtils.containsIgnoreCase(firstAddressFound.getPicklistEntry(), searchBuilding));

        // check for any non-matching results and fail if we find them
        for (Address address : results.getAddresses()) {
            Assert.assertTrue("Only expecting to find building '" + searchBuilding + "' in results : found " +
                    address.getPicklistEntry(),
                    StringUtils.containsIgnoreCase(firstAddressFound.getPicklistEntry(), searchBuilding));
        }
    }

        /** Checks that an expected building number is not sorted to the start of the list when the sort flag is not set */
    @Test
    public void TestNullValues() throws Exception {
            String searchPostcode = "NR14 8PA";
            String searchBuilding = "Greenways";

            AddressLookupRequest lookupRequest = new AddressLookupRequest();
            lookupRequest.setSearchPostcode(searchPostcode);
            lookupRequest.setSearchBuilding(searchBuilding);
            lookupRequest.setServiceProvider(null);
            lookupRequest.setResultFormat(null);
            lookupRequest.setReturnOnlyMatchingAddresses(null);
            lookupRequest.setSortMatchingAddresses(null);
            lookupRequest.setReturnPAFData(null);
            AddressLookupResponse results = testEndPoint.addressLookup(lookupRequest);

            Assert.assertNotNull("results not null", results);
            Assert.assertTrue("results found ", results.getAddresses().size() > 0);

            Assert.assertEquals("Null provider should revert to default",
                    ServiceProviders.POSTCODE_ANYWHERE,
                    results.getServiceProvider());
            Assert.assertEquals("Null ReturnOnlyMatchingAddresses should revert to default",
                    false,
                    results.isReturnOnlyMatchingAddresses());
            Assert.assertEquals("Null SortMatchingAddresses should revert to default",
                    true,
                    results.isSortMatchingAddresses());
            Assert.assertEquals("Null ReturnPAFData should revert to default",
                    false,
                    results.isReturnPAFData());
            Assert.assertEquals("Null result format should revert to default",
                    ResultFormats.STYLE_1,
                    results.getResultFormat());

        }

}

