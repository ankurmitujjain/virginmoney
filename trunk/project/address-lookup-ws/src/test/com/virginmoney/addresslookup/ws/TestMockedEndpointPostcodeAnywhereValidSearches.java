package com.virginmoney.addresslookup.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.messages.AddressLookupResponse;
import com.virginmoney.addresslookup.messages.Address;
import com.virginmoney.addresslookup.messages.ServiceProviders;
import com.virginmoney.addresslookup.messages.ResultFormats;

/**
 *  This class extents {@link AbstractTestEndpointValidSearches} and tests the endpoint with valid searches
 *  using a mocked postcode anywhere service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TestMockedEndpointPostcodeAnywhereValidSearches extends AbstractTestEndpointValidSearches{

    private static Logger logger = Logger.getLogger(TestMockedEndpointPostcodeAnywhereValidSearches.class);
    
@Test
public void Dummy() throws Exception {
    assertTrue("we need a dummy test otherwise Junit gets upset",true);

}

    public void setUpEndPoint() {
        super.testEndPoint = SpringBuilder.getEndPointWithMockServices();
        super.searchProvider = ServiceProviders.POSTCODE_ANYWHERE;
        super.returnPAFData = true;
    }

    /**
     * Checks that residential status is returned correctly for known addresses.
     */
    @Test
    public void TestResidentialStatuses() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"Honeycombe");
        logger.debug("address=" + address);
        Assert.assertNotNull("Honeycombe is in results",address);
        assertTrue("Honeycombe is residential",address.getPafData().isIsResidential());


   }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestNonResidentialStatuses() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Old Mill Restaurant";   // this should NOT return a match from postcode anywhere because
                                                      // its checked in the PAF bulding name fields, not the locality name

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = results.getAddresses().get(0);

        logger.debug("address=" + address);
        Assert.assertNotNull("Old Mill Restaurant is in results",address);
        Assert.assertFalse("Old Mill Restaurant not residential",address.getPafData().isIsResidential());

        Assert.assertTrue("First result has the 'MatchesSearchBuilding' flag set : " +
                address.getPicklistEntry(),
                address.isMatchesSearchBuilding());

   }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestNonMatchingProperty() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "Stoke Holy Cross";   // this should NOT return a match from postcode anywhere because
                                                      // its checked in the PAF bulding name fields, not the locality name

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address firstAddress = results.getAddresses().get(0);
        Assert.assertFalse("First result does not have the 'MatchesSearchBuilding' flag set : " +
                firstAddress.getPicklistEntry(),
                firstAddress.isMatchesSearchBuilding());

   }

    /**
     * Checks that PAF data is returned if it is requested
     */
    @Test
    public void TestPAFDataReturned() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";
        super.returnPAFData = true;

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);
        Address firstAddress = results.getAddresses().get(0);
        assertNotNull("Results should contain address data",firstAddress);
        assertNotNull("Results should  contain PAF data",firstAddress.getPafData()) ;
        Assert.assertFalse("First result does not have the 'MatchesSearchBuilding' flag set ",
                firstAddress.isMatchesSearchBuilding());

   }


    /**
     * Checks that PAF data is not returned if it is requested
     */
    @Test
    public void TestNoPAFDataReturned() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";
        super.returnPAFData = false;

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);
        Address firstAddress = results.getAddresses().get(0);
        assertNotNull("Results should contain address data",firstAddress);
        assertNull("Results should not contain PAF data",firstAddress.getPafData()) ;

   }

    /**
     * Checks that non-residential status is returned correctly for known addresses.
     */
    @Test
    public void TestPOBox() throws Exception {
        String searchPostcode = "NR1 3NG";
        String searchBuilding = "";

        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"PO Box 4");
        logger.debug("address=" + address);
        Assert.assertNotNull("PO Box 4 should be found in results",address);
        Assert.assertTrue("PO Box 4 not organisation : ",address.getPafData().isIsLargeOrganisation());

   }

    /**
     * Checks that a named house is returned correctly using address result Style 2.
     */
    @Test
    public void TestStyle2Namedhouse() throws Exception {
        String searchPostcode = "NR14 8PA";
        String searchBuilding = "";

        super.resultFormat = ResultFormats.STYLE_2;


        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"Honeycombe");
        logger.debug("address=" + address);
        Assert.assertNotNull("Honeycombe is in results",address);
        Assert.assertTrue("Honeycombe is residential",address.getPafData().isIsResidential());
        Assert.assertEquals("Result in in correct format",super.resultFormat,results.getResultFormat());
        Assert.assertEquals("Organisation name is correct",null,address.getOrganisationName());
        Assert.assertEquals("Address line 1 is correct","Honeycombe, Mill Road",address.getAddressLine1());
        Assert.assertEquals("Address line 2 is correct","Stoke Holy Cross",address.getAddressLine2());
        Assert.assertEquals("Address line 3 is correct",null,address.getAddressLine3());
        Assert.assertEquals("Address line 4 is correct",null,address.getAddressLine4());
        Assert.assertEquals("PostTown is correct","Norwich",address.getPostTown());
        Assert.assertEquals("County is correct","Norfolk",address.getCounty());


   }


    /**
     * Checks that a numbered house is returned correctly using address result Style 2.
     */
    @Test
    public void TestStyle2NumberedHouse() throws Exception {
        String searchPostcode = "NR8 6BD";
        String searchBuilding = "";

        super.resultFormat = ResultFormats.STYLE_2;


        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"113");
        logger.debug("address=" + address);
        Assert.assertNotNull("113 is in results",address);
        Assert.assertTrue("113 is residential",address.getPafData().isIsResidential());
        Assert.assertEquals("Result in in correct format",super.resultFormat,results.getResultFormat());
        Assert.assertEquals("Organisation name is correct",null,address.getOrganisationName());
        Assert.assertEquals("Address line 1 is correct","113 Drayton High Road",address.getAddressLine1());
        Assert.assertEquals("Address line 2 is correct","Drayton",address.getAddressLine2());
        Assert.assertEquals("Address line 3 is correct",null,address.getAddressLine3());
        Assert.assertEquals("Address line 4 is correct",null,address.getAddressLine4());
        Assert.assertEquals("PostTown is correct","Norwich",address.getPostTown());
        Assert.assertEquals("County is correct","Norfolk",address.getCounty());


   }

    /**
     * Checks that a company with a PO Box number correctly using address result Style 2.
     *
     */
    @Test
    public void TestStyle2POBox() throws Exception {
        String searchPostcode = "NR1 3NG";
        String searchBuilding = "";

        super.resultFormat = ResultFormats.STYLE_2;


        AddressLookupResponse results = doAddressSearch(searchPostcode, searchBuilding,false, true);

        Address address = getAddressFromResults(results,"PO Box");
        logger.debug("address=" + address);
        Assert.assertNotNull("NR1 3NG is in results",address);
        Assert.assertTrue("NR1 3NG is large Organisation",address.getPafData().isIsLargeOrganisation());
        Assert.assertEquals("Result in in correct format",super.resultFormat,results.getResultFormat());
        Assert.assertEquals("Organisation name is correct","Norwich Union Insurance Group",address.getOrganisationName());
        Assert.assertEquals("Address line 1 is correct","PO Box 4",address.getAddressLine1());
        Assert.assertEquals("Address line 2 is correct",null,address.getAddressLine2());
        Assert.assertEquals("Address line 3 is correct",null,address.getAddressLine3());
        Assert.assertEquals("Address line 4 is correct",null,address.getAddressLine4());
        Assert.assertEquals("PostTown is correct","Norwich",address.getPostTown());
        Assert.assertEquals("County is correct","Norfolk",address.getCounty());


   }




}

