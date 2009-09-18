package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Assert;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.messages.*;
import com.virginmoney.addresslookup.util.ServiceUtil;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.SpringBuilder;

/**
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public abstract class AbstractTestEndPointSearches {

    private static Logger logger = Logger.getLogger(AbstractTestEndPointSearches.class);
    protected AddressLookupServicePortType testEndPoint = null;
    protected ServiceProviders searchProvider = null;
    protected ResultFormats resultFormat = null;
    protected Boolean returnPAFData = false;

    @Before
    public void getEndpoint() throws Exception {
        SpringBuilder.setSystemProperties();
        
        setUpEndPoint();

        Assert.assertNotNull("Endpoint exists", testEndPoint);

    }

    /**
     * Sets up the endpoint to be tested
     */
    public abstract void setUpEndPoint();

    /**
     * Utility method to perform an address search on the endpoint.
     *
     * @param searchPostcode     postcode to search for
     * @param searchBuilding optional bulding name ot number to search for
     *
     * @param returnOnlyMatchingResults    set the 'return only matching results' flag in the request
     * @param sortMatchingResults    set the 'sort results' flag in the request
     * @return list of matching addresses
     *
     * @throws com.virginmoney.addresslookup.messages.AddressLookupException
     *
     */
    protected AddressLookupResponse doAddressSearch(String searchPostcode, String searchBuilding, boolean returnOnlyMatchingResults,
                                                    boolean sortMatchingResults) throws AddressLookupException
    {
        AddressLookupRequest lookupRequest = new AddressLookupRequest();
        lookupRequest.setSearchPostcode(searchPostcode);
        lookupRequest.setSearchBuilding(searchBuilding);
        lookupRequest.setServiceProvider(searchProvider);
        lookupRequest.setResultFormat(resultFormat);
        lookupRequest.setReturnPAFData(returnPAFData);
        lookupRequest.setReturnOnlyMatchingAddresses(returnOnlyMatchingResults);
        lookupRequest.setSortMatchingAddresses(sortMatchingResults);
        AddressLookupResponse results = testEndPoint.addressLookup(lookupRequest);
        return results;
    }

    /**
     * Utility method to search a list of results for a given building name. Unlike the postcode anywhere version of the
     * results, IFDS addresses do not have PAF data  so the address line details are checked.
     *
     * @param results
     * @param searchFragment
     * @param expected
     */

    protected void resultsContainFragment(AddressLookupResponse results, String searchFragment, boolean expected) {
        logger.debug("resultsContainFragment() " + (expected?"":"not ") + "expecting to find \"" +searchFragment + "\"" );
        Assert.assertNotNull("results should not be null", results);
        Assert.assertTrue("results should not be empty ", results.getAddresses().size() > 0);

        Address address = getAddressFromResults(results, searchFragment);
        if (address == null) {
             logger.debug("resultsContainFragment() got nothing");
        }
        else
        {
            logger.debug("resultsContainFragment() got address " + address.getPicklistEntry());
        }

        if (expected) {
            if (address == null) {
                logger.debug("resultsContainFragment() didnt find an expected address matching \"" +searchFragment + "\"" );
            }
            Assert.assertTrue("buildingName \"" + searchFragment + "\" should be found in search for postcode \"" +
                    results.getSearchPostcode() + "\"" + " and building \"" + results.getSearchBuilding() +
                    "\"", address != null);
        }
        else {
            if (address != null) {
                logger.debug("resultsContainFragment() found an unexpected address matching \"" +searchFragment + "\" \n" +
                      ServiceUtil.messageToString(address,true));
            }
            Assert.assertTrue("buildingName \"" + searchFragment + "\" should not be found in search for postcode \"" +
                    results.getSearchPostcode() + "\"" + " and building \"" + results.getSearchBuilding() +
                    "\"", address == null);

        }
    }

    /**
     * Finds an address in a list of results, using building name as a search key.
     *
     * @param results
     * @param searchString
     *
     * @return
     */
    protected Address getAddressFromResults(AddressLookupResponse results, String searchString) {

        logger.trace("getAddressFromResults(()   Searching for " + searchString);
        for (Address address : results.getAddresses()) {
            logger.trace("                  ............in " + address.getPicklistEntry());
            if (addressContainsFragment(address, searchString))  {
                   logger.trace(" ... returning\n" + ServiceUtil.messageToString(address,true));
                   return address;
            }
        }
        logger.trace(" ... not found " + searchString);
        return null;
    }

    /**
     * Utility method to see if a given Address contains a string value.
     * @param address  the address to search
     * @param searchString  The string to search for
     * @return  true if searchString is in the address
     */
    protected boolean addressContainsFragment(Address address, String searchString) {
        // use PAF data if possible
        PAFData pafData = address.getPafData();
        if (returnPAFData && pafData != null) {

            logger.trace("addressContainsFragment() looking for '" + searchString + "' using PAFdata in " + address.getPicklistEntry());
            if (StringUtils.equalsIgnoreCase(pafData.getBuildingName(), searchString) ||
                    StringUtils.equalsIgnoreCase(pafData.getBuildingNumber(), searchString) ||
                    StringUtils.equalsIgnoreCase(pafData.getSubBuildingName(), searchString) ||
                    StringUtils.equalsIgnoreCase(pafData.getOrganisationName(), searchString)) {
                logger.trace("addressContainsFragment() found it in PAF:" + address);
                return true;
            }

        }
        logger.trace("addressContainsFragment() looking for '" + searchString + "' using simple data in " +  address.getPicklistEntry());
        // otherwise use the simple address data
        if (StringUtils.containsIgnoreCase(address.getAddressLine1(), searchString) ||
                StringUtils.containsIgnoreCase(address.getAddressLine2(), searchString) ||
                StringUtils.containsIgnoreCase(address.getAddressLine3(), searchString) ||
                StringUtils.containsIgnoreCase(address.getAddressLine4(), searchString)) {
            logger.trace("addressContainsFragment() found it in simple:");
            return true;
        }
        logger.trace("addressContainsFragment() didnt find '" + searchString + "' in " + address.getPicklistEntry());
        return false;
    }

    /**
     * checks that the Errors object contains a specific code.
     *
     * @param errors The errors object.
     * @param code   The code to check for.
     *
     * @return true if the errors object contains the supplied error code.
     */
    protected boolean checkForErrorKey(ErrorList errors, String code) {
        for (ErrorMessage error :  errors.getErrors()) {
            //        logger.debug("error=" + error + " class=" + error.getClass().getName());
            if (error.getErrorMessageKey().equals(code)) {
                return true;
            }
        }
        return false;
    }
    
}

