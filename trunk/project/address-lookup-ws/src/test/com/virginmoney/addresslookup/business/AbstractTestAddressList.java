package com.virginmoney.addresslookup.business;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang.StringUtils;

/**
 * Abstract class to carry out general tests on an {@link AddressList} object.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public abstract class AbstractTestAddressList {

    private static Logger logger = Logger.getLogger(AbstractTestAddressList.class);

    protected abstract AddressList getAddressList(AddressSearchParameters searchParameters);
    protected abstract AddressSearchParameters getSearchParameters(String searchBuilding);

   /**
    * Checks that search building name is sorted to the start of the list
    */
    @Test
    public void TestBuildingNameSort() throws Exception {
       sortExpectFound("Greenways");
   }

    /**
    * Checks that search building number is sorted to the start of the list
    */
    @Test
    public void TestBuildingNumberSort() throws Exception {
       sortExpectFound("99");
   }

    /**
    * Checks that search building number is sorted to the start of the list
    */
    @Test
    public void TestOrganisationNameSort() throws Exception {
       sortExpectFound("Old Mill Restaurant");
   }

    /**
    * Checks that a null sort key doesnt change the order of data in the list.
    */
    @Test
    public void TestSortingWithNullKey() throws Exception {
        sortExpectNotFound(null);
   }

    /**
    * Checks that a blank sort key doesnt change the order of data in the list.
    */
    @Test
    public void TestSortingWithBlankKey() throws Exception {
        sortExpectNotFound("  ");
   }

    /**
    * Checks that a sort key which is not in the results doesnt change the order of data in the list.
    */
    @Test
    public void TestSortingWithNonExistentKey() throws Exception {
        sortExpectNotFound("qwertyuiopasdfghjkl");
   }


    /**
     * Utility method to check that a sortkey which is expected to change the ordering of addresses does do this.
     * @param searchBuilding the key to sort the data on
     */
    protected void sortExpectFound(String searchBuilding) {

        AddressSearchParameters searchParameters = getSearchParameters(searchBuilding);
        AddressList testAddressList = getAddressList(searchParameters);


        // the picklist of the first address should contain the sort key
        AbstractAddress firstAddress = testAddressList.getAddresses().get(0);
        assertNotNull("first address should not be null", firstAddress.getPicklistEntry());
        assertTrue("first address should contain the search name (" + searchBuilding + ") in " +
                firstAddress.getAddressLine1() + ", " + firstAddress.getAddressLine2(),
                  StringUtils.containsIgnoreCase(firstAddress.getAddressLine1(),searchBuilding) ||
                  StringUtils.containsIgnoreCase(firstAddress.getAddressLine2(),searchBuilding)
        );
        assertTrue("first address should have its MatchesSearchBuilding property set for the search name (" +
                searchBuilding + ")", firstAddress.isMatchesSearchBuilding());
    }


    /**
     * Utility method to check that a sortkey which is expected to change the ordering of addresses does do this.
     * @param searchBuilding the key to sort the data on
     */
    protected void sortExpectNotFound(String searchBuilding) {
        AddressSearchParameters searchParameters = getSearchParameters(searchBuilding);
        AddressList testAddressList = getAddressList(searchParameters);

        // the picklist of the first address should contain the sort key
        AbstractAddress firstAddress = testAddressList.getAddresses().get(0);
        assertNotNull("first address should not be null", firstAddress.getPicklistEntry());

        assertFalse("first address should not contain the search name (" + searchBuilding + ") in " +
                firstAddress.getAddressLine1() + ", " + firstAddress.getAddressLine2(),
                  StringUtils.containsIgnoreCase(firstAddress.getAddressLine1(),searchBuilding) ||
                  StringUtils.containsIgnoreCase(firstAddress.getAddressLine2(),searchBuilding) 
        );
        assertFalse("first address should have its MatchesSearchBuilding property set for the search name (" +
                searchBuilding + ")", firstAddress.isMatchesSearchBuilding());
    }

}

