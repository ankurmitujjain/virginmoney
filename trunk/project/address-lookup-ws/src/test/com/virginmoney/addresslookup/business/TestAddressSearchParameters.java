
package com.virginmoney.addresslookup.business;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;

/**
 Junit test class for {@link AddressSearchParameters}.
*/

public class TestAddressSearchParameters{
private Logger logger = Logger.getLogger(TestAddressSearchParameters.class);

private AddressSearchParameters testAddressSearchParameters;

    /**
     * Tests that a postcode-only search results in the correct default values for the other parameters.
     * @throws Exception
     */
@Test
    public void testPostcodeOnly() throws Exception {
        String testPostcode = "NR14 8PA";
        String testBuilding = null;
        AddressSearchParameters testParameters = new AddressSearchParameters(testPostcode,testBuilding);
        assertNotNull("testParameters should not be null",testParameters);

        assertEquals("postcode should match",testPostcode,testParameters.getSearchPostcode());

        assertEquals("search building should match",testBuilding,testParameters.getSearchBuilding());

        assertEquals("result style rule should match default",
            AddressSearchParameters.ResultFormats.STYLE1 ,testParameters.getResultFormat());

        assertEquals("provider rule should match default",
                AddressSearchParameters.ServiceProviders.PostcodeAnywhere ,testParameters.getServiceProvider());

        assertEquals("return only matching results rule should match default",
                AddressSearchParameters.ReturnMatchingResultRules.allResults ,testParameters.getReturnMatchingResultRule());

        assertEquals("sort matching results rule should match default",
                AddressSearchParameters.SortMatchingResultRules.sort ,testParameters.getSortMatchingResultRule());

        assertEquals("return PAF data rule should match default",
                AddressSearchParameters.ReturnPAFDataRules.noPAFData ,testParameters.getReturnPAFDataRule());

    }

    /**
     * Tests that a postcode and building search results in the correct default values for the other parameters.
     * @throws Exception
     */
    @Test
    public void testPostcodeAndBuilding() throws Exception {
        String testPostcode = "NR14 8PA";
        String testBuilding = "Honeycombe";
        AddressSearchParameters testParameters = new AddressSearchParameters(testPostcode,testBuilding);
        assertNotNull("testParameters should not be null",testParameters);

        assertEquals("postcode should match",testPostcode,testParameters.getSearchPostcode());

        assertEquals("search building should match",testBuilding,testParameters.getSearchBuilding());

        assertEquals("result style rule should match default",
                AddressSearchParameters.ResultFormats.STYLE1 ,testParameters.getResultFormat());

        assertEquals("provider rule should match default",
                AddressSearchParameters.ServiceProviders.PostcodeAnywhere ,testParameters.getServiceProvider());

        assertEquals("return only matching results rule should match default",
                AddressSearchParameters.ReturnMatchingResultRules.allResults ,testParameters.getReturnMatchingResultRule());

        assertEquals("sort matching results rule should match default",
                AddressSearchParameters.SortMatchingResultRules.sort ,testParameters.getSortMatchingResultRule());

        assertEquals("return PAF data rule should match default",
                AddressSearchParameters.ReturnPAFDataRules.noPAFData ,testParameters.getReturnPAFDataRule());

    }

    /**
     * Tests that a postcode and building search results in the correct default values for the other parameters when
     * null values are provided for them.
     * @throws Exception
     */
    @Test
    public void testPostcodeAndBuildingWithNulls() throws Exception {
        String testPostcode = "NR14 8PA";
        String testBuilding = "Honeycombe";
        AddressSearchParameters testParameters =
                new AddressSearchParameters(testPostcode, testBuilding, null, null, null, null, null);
        assertNotNull("testParameters should not be null",testParameters);

        assertEquals("postcode should match",testPostcode,testParameters.getSearchPostcode());

        assertEquals("search building should match",testBuilding,testParameters.getSearchBuilding());

        assertEquals("result style rule should match default",
                AddressSearchParameters.ResultFormats.STYLE1 ,testParameters.getResultFormat());

        assertEquals("provider rule should match default",
                AddressSearchParameters.ServiceProviders.PostcodeAnywhere ,testParameters.getServiceProvider());

        assertEquals("return only matching results rule should match default",
                AddressSearchParameters.ReturnMatchingResultRules.allResults ,testParameters.getReturnMatchingResultRule());

        assertEquals("sort matching results rule should match default",
                AddressSearchParameters.SortMatchingResultRules.sort ,testParameters.getSortMatchingResultRule());

        assertEquals("return PAF data rule should match default",
                AddressSearchParameters.ReturnPAFDataRules.noPAFData ,testParameters.getReturnPAFDataRule());

    }

    /**
     * Tests that a postcode and building search results using IFDS overrides the appropriate parameters.
     * @throws Exception
     */
    @Test
    public void testPostcodeAndBuildingIFDS() throws Exception {
        String testPostcode = "NR14 8PA";
        String testBuilding = "Honeycombe";
        AddressSearchParameters testParameters = new AddressSearchParameters(testPostcode,
                testBuilding,
                AddressSearchParameters.ResultFormats.STYLE2,      // IFDS should overrule this to  STYLE 1
                AddressSearchParameters.ServiceProviders.IFDS,
                null,
                null,
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData // IFDS should overrule this to dont return paf data
        );
        assertNotNull("testParameters should not be null",testParameters);

        assertEquals("postcode should match",testPostcode,testParameters.getSearchPostcode());

        assertEquals("search building should match",testBuilding,testParameters.getSearchBuilding());

        assertEquals("result style should be Style 1 for IFDS ",
                AddressSearchParameters.ResultFormats.STYLE1 ,testParameters.getResultFormat());

        assertEquals("provider should be IFDS",
                AddressSearchParameters.ServiceProviders.IFDS ,testParameters.getServiceProvider());

        assertEquals("return only matching results rule should match default",
                AddressSearchParameters.ReturnMatchingResultRules.allResults ,testParameters.getReturnMatchingResultRule());

        assertEquals("return only matching results boolean should be correct ", false,testParameters.returnOnlyMatchingResults());

        assertEquals("sort matching results rule should match default",
                AddressSearchParameters.SortMatchingResultRules.sort ,testParameters.getSortMatchingResultRule());

        assertEquals("sort matching results boolean should be correct ", true,testParameters.sortMatchingResults());

        assertEquals("return PAF data should not be allowed for IFDS",
                AddressSearchParameters.ReturnPAFDataRules.noPAFData ,testParameters.getReturnPAFDataRule());

        assertEquals("return PAF data boolean should be correct ", false,testParameters.returnPAFData());

    }
    /**
     * Tests that a postcode and building search results using IFDS overrides the appropriate parameters.
     * @throws Exception
     */
    @Test
    public void testPostcodeAndBuildingVMGivingOperator() throws Exception {
        String testPostcode = "NR14 8PA";
        String testBuilding = "Honeycombe";
        AddressSearchParameters testParameters = new AddressSearchParameters(testPostcode,
                testBuilding,
                AddressSearchParameters.ResultFormats.STYLE2,      // IFDS should overrule this to  STYLE 1
                AddressSearchParameters.ServiceProviders.VMGivingOperator,
                AddressSearchParameters.ReturnMatchingResultRules.onlyMatchingResults,
                AddressSearchParameters.SortMatchingResultRules.dontSort,
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData // IFDS should overrule this to dont return paf data
        );
        assertNotNull("testParameters should not be null",testParameters);

        assertEquals("postcode should match",testPostcode,testParameters.getSearchPostcode());

        assertEquals("search building should match",testBuilding,testParameters.getSearchBuilding());

        assertEquals("result style rule should match default",
                AddressSearchParameters.ResultFormats.STYLE2 ,testParameters.getResultFormat());

        assertEquals("provider should be VM Giving Operator",
                AddressSearchParameters.ServiceProviders.VMGivingOperator ,testParameters.getServiceProvider());

        assertEquals("return only matching results rule should match default",
                AddressSearchParameters.ReturnMatchingResultRules.onlyMatchingResults ,testParameters.getReturnMatchingResultRule());

        assertEquals("return only matching results boolean should be correct ", true,testParameters.returnOnlyMatchingResults());

        assertEquals("sort matching results rule should match default",
                AddressSearchParameters.SortMatchingResultRules.dontSort ,testParameters.getSortMatchingResultRule());

        assertEquals("sort matching results boolean should be correct ", false,testParameters.sortMatchingResults());

        assertEquals("return PAF data should be true",
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData ,testParameters.getReturnPAFDataRule());

        assertEquals("return PAF data boolean should be correct ", true,testParameters.returnPAFData());

    }

}
