package com.virginmoney.addresslookup.business;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.business.PAFData;
import com.virginmoney.addresslookup.business.AddressStyle1;
import com.virginmoney.addresslookup.business.PAFDataFormatter;

/** Junit test class for {@link PAFDataFormatter}, using Style 1 addresses. */

public class TestPAFDataFormatterStyle1 {
    private Logger logger = Logger.getLogger(TestPAFDataFormatterStyle1.class);

    private PAFDataFormatter testPAFDataFormatter;
    private AddressSearchParameters.ResultFormats resultFormat;

    @Before
    public void setUp() {
        testPAFDataFormatter = new PAFDataFormatter();
        assertNotNull(testPAFDataFormatter);

        resultFormat = AddressSearchParameters.ResultFormats.STYLE1;
        assertNotNull(resultFormat);
    }

    @After
    public void tearDown() {

        testPAFDataFormatter = null;
    }

    /**
     * Verifies that the results from the PAF formatter match those that are expected.
     * @param expectedLine1 expected value for address line 1
     * @param expectedLine2  expected value for address line 1
     * @param expectedLine3  expected value for address line 1
     * @param expectedLine4  expected value for address line 1
     * @param expectedPostcode expected value for address line 1
     * @param resultAddress   expected value for address line 1
     */
    private void checkAddressResult(String expectedLine1,
                                    String expectedLine2,
                                    String expectedLine3,
                                    String expectedLine4,
                                    String expectedPostcode,
                                    boolean expectedMatch,
                                    com.virginmoney.addresslookup.business.AbstractAddress resultAddress)
    {

        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        assertNotNull(StringUtils.rightPad(methodName,40) + " : actualResultAddress must not be null", resultAddress);
        assertEquals("Check expected address class ", AddressStyle1.class,resultAddress.getClass());

        AddressStyle1 actualResultAddress = (AddressStyle1) resultAddress;

        logger.debug("Checking actualResultAddress=" + actualResultAddress);
        logger.debug(StringUtils.rightPad(methodName,40) + " : Address line 1 : matches="  + StringUtils.rightPad(String.valueOf(expectedLine1 == actualResultAddress.getAddressLine1()),6) + " expected=" + StringUtils.rightPad(expectedLine1 ,50)    + " actual=" +  actualResultAddress.getAddressLine1());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Address line 2 : matches="  + StringUtils.rightPad(String.valueOf(expectedLine2 == actualResultAddress.getAddressLine2()),6) + " expected=" + StringUtils.rightPad(expectedLine2 ,50)    + " actual=" +  actualResultAddress.getAddressLine2());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Address line 3 : matches="  + StringUtils.rightPad(String.valueOf(expectedLine3 == actualResultAddress.getAddressLine3()),6) + " expected=" + StringUtils.rightPad(expectedLine3 ,50)    + " actual=" +  actualResultAddress.getAddressLine3());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Address line 4 : matches="  + StringUtils.rightPad(String.valueOf(expectedLine4 == actualResultAddress.getAddressLine4()),6) + " expected=" + StringUtils.rightPad(expectedLine4 ,50)    + " actual=" +  actualResultAddress.getAddressLine4());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Postcode       : matches="  + StringUtils.rightPad(String.valueOf(expectedPostcode == actualResultAddress.getPostcode()) ,6) + " expected=" + StringUtils.rightPad(expectedPostcode ,50) + " actual=" +  actualResultAddress.getPostcode());
        logger.debug(StringUtils.rightPad(methodName,40) + " : SearchMatches  : matches="  + StringUtils.rightPad(String.valueOf(expectedMatch == actualResultAddress.isMatchesSearchBuilding()) ,6) + " expected=" + StringUtils.rightPad(String.valueOf(expectedMatch) ,50) + " actual=" +  actualResultAddress.isMatchesSearchBuilding());

        assertEquals(methodName + " : Address line 1", expectedLine1, actualResultAddress.getAddressLine1());
        assertEquals(methodName + " : Address line 2", expectedLine2, actualResultAddress.getAddressLine2());
        assertEquals(methodName + " : Address line 3", expectedLine3, actualResultAddress.getAddressLine3());
        assertEquals(methodName + " : Address line 4", expectedLine4, actualResultAddress.getAddressLine4());
        assertEquals(methodName + " : Postcode", expectedPostcode, actualResultAddress.getPostcode());
        assertEquals(methodName + " : MatchesSearchBuilding", expectedMatch, actualResultAddress.isMatchesSearchBuilding());
        assertTrue("picklist contain line 1  : " + expectedLine1 + " should be in " +
                actualResultAddress.getPicklistEntry(),
                StringUtils.containsIgnoreCase(actualResultAddress.getPicklistEntry(), expectedLine1));
        assertTrue("picklist contains postcode of paf address",
                StringUtils.containsIgnoreCase(actualResultAddress.getPicklistEntry(), expectedPostcode));

    }

    /** House with name in a village */
    @Test
    public void testNamedHouseLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedHouseLocality();

        String expectedLine1 = "Honeycombe, Mill Road";
        String expectedLine2 = "Stoke Holy Cross";
        String expectedLine3 = "Norwich";
        String expectedLine4 = "Norfolk";
        String expectedPostcode = "NR14 8PA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Honeycombe",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);

        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                true,                   // honeycombe is the building name
                actualResultAddress);

    }

    /** Business in a village, includes subbuilding, dependent locality */
    @Test
    public void testNamedBusinessSubbuildingLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessSubbuildingLocality();

        String expectedLine1 = "A R B G Dive Supplies";
        String expectedLine2 = "The Workshops, Mill House, Mill Road";
        String expectedLine3 = "Stoke Holy Cross";
        String expectedLine4 = "Norwich, Norfolk";
        String expectedPostcode = "NR14 8PA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"A R B G",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                true,                    // A R B G is part of the organisation name
                actualResultAddress);

    }

    /** Business in a village, no subbuilding, dependent locality */
    @Test
    public void testNamedBusinessNoSubbuildingLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessNoSubbuildingLocality();


        String expectedLine1 = "Post Office";
        String expectedLine2 = "Mill Road";
        String expectedLine3 = "Stoke Holy Cross";
        String expectedLine4 = "Norwich, Norfolk";
        String expectedPostcode = "NR14 8PA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Mill",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,       // Mill is part of the road name but NOT part of the building or organisation name
                actualResultAddress);

    }

    /** Numbered House */
    @Test
    public void testNumberedHouseLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNumberedHouseLocality();

        String expectedLine1 = "20 The Shrublands";
        String expectedLine2 = "Horsford";
        String expectedLine3 = "Norwich";
        String expectedLine4 = "Norfolk";
        String expectedPostcode = "NR10 3EL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"20",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                true,  // 20 is the bulding number
                actualResultAddress);

    }

    /** Numbered House with no locality details */
    @Test
    public void testNumberedHouseNoLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNumberedHouseNoLocality();

        String expectedLine1 = "12 Stylman Road";
        String expectedLine2 = "Norwich";
        String expectedLine3 = "Norfolk";
        String expectedLine4 = null;
        String expectedPostcode = "NR5 9EU";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"qwerty",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,         // qwerty isnt in the address
                actualResultAddress);

    }

    /** Flat , no locality, case 1 - no flat number */
    @Test
    public void testFlatNumberNoLocality1(){

        PAFData pafData = PAFFormatInputBuilder.getFlatNumberNoLocality1();

        String expectedLine1 = "Flat, 61 Imperial Court";
        String expectedLine2 = "Stoke-on-Trent";
        String expectedLine3 = "Staffordshire";
        String expectedLine4 = null;
        String expectedPostcode = "ST1 3EQ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Flat , no locality , case 2 - with a flat number */
    @Test
    public void testFlatNumberNoLocality2(){

        PAFData pafData = PAFFormatInputBuilder.getFlatNumberNoLocality2();

        String expectedLine1 = "Flat 2, Colman Court, Christchurch Avenue";
        String expectedLine2 = "London";
        String expectedLine3 = null;
        String expectedLine4 = null;
        String expectedPostcode = "N12 0DT";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business (small organisation) */
    @Test
    public void testSmallOrganisationNoLocality(){

        PAFData pafData = PAFFormatInputBuilder.getSmallOrganisationNoLocality();

        String expectedLine1 = "Bairstow Eves Countrywide";
        String expectedLine2 = "9-11 Station Avenue";
        String expectedLine3 = "Caterham";
        String expectedLine4 = "Surrey";
        String expectedPostcode = "CR3 6LB";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business (large organisation, shows dependent thoroughfare) */
    @Test
    public void testLargeOrganisationDependentThoroughfare(){

        PAFData pafData = PAFFormatInputBuilder.getLargeOrganisationDependentThoroughfare();

        String expectedLine1 = "Virgin Money";
        String expectedLine2 = "Discovery House, 4 Norwich Business Park, Whiting Road";
        String expectedLine3 = "Norwich";
        String expectedLine4 = "Norfolk";
        String expectedPostcode = "NR4 6EJ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business (large organisation, shows po box number) */
    @Test
    public void testLargeOrganisationPOBox(){

        PAFData pafData = PAFFormatInputBuilder.getLargeOrganisationPOBox();

        String expectedLine1 = "Norwich Union Insurance Group";
        String expectedLine2 = "PO Box 4";
        String expectedLine3 = "Norwich";
        String expectedLine4 = "Norfolk";
        String expectedPostcode = "NR1 3NG";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : cancer research (shows as residential) */
    @Test
    public void testCancerResearch(){

        PAFData pafData = PAFFormatInputBuilder.getCancerResearch();

        // Cancer research is NOT an organisation (according to the PAF file)
        String expectedLine1 = "41 Basford Bridge Lane";
        String expectedLine2 = "Cheddleton";
        String expectedLine3 = "Leek";
        String expectedLine4 = "Staffordshire";
        String expectedPostcode = "ST13 7EQ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : RNIB (shows as small organisation) */
    @Test
    public void testRNIB(){

        PAFData pafData = PAFFormatInputBuilder.getRNIB();

        String expectedLine1 = "R N I B";
        String expectedLine2 = "58-72 John Bright Street";
        String expectedLine3 = "Birmingham";
        String expectedLine4 = "West Midlands";
        String expectedPostcode = "B1 1BN";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : Rehab UK (shows as small organisation) */
    @Test
    public void testRehabUK(){

        PAFData pafData = PAFFormatInputBuilder.getRehabUK();

        String expectedLine1 = "Rehab Uk";
        String expectedLine2 = "Borough Buildings, 58-72 John Bright Street";
        String expectedLine3 = "Birmingham";
        String expectedLine4 = "West Midlands";
        String expectedPostcode = "B1 1BN";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : RSPCA (welsh) */
    @Test
    public void testRSPCAWales(){

        PAFData pafData = PAFFormatInputBuilder.getRSPCAWales();

        String expectedLine1 = "R S P C A";
        String expectedLine2 = "84 John Street";
        String expectedLine3 = "Porthcawl";
        String expectedLine4 = "Pen-y-bont ar Ogwr";
        String expectedPostcode = "CF36 3BD";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : RSPCA (english) */
    @Test
    public void testRSPCAEngland(){

        PAFData pafData = PAFFormatInputBuilder.getRSPCAEngland();

        String expectedLine1 = "R S P C A";
        String expectedLine2 = "Black Hat Lane, Bakers Hill";
        String expectedLine3 = "Exeter";
        String expectedLine4 = "Devon";
        String expectedPostcode = "EX2 9TA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business with double dependent locality */
    @Test
    public void testNamedBusinessDoubleDependentLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessDoubleDependentLocality();

        String expectedLine1 = "Amber Credit";
        String expectedLine2 = "Amber House, Unit 3, Lindenwood, Crockford Lane";
        String expectedLine3 = "Chineham Business Park, Chineham";
        String expectedLine4 = "Basingstoke, Hampshire";
        String expectedPostcode = "RG24 8QY";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business with double dependent locality , case 2 */
    @Test
    public void testNamedBusinessDoubleDependentLocality2(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessDoubleDependentLocality2();

        String expectedLine1 = "2M Power Systems Ltd";
        String expectedLine2 = "Unit 14, Robert Leonard Centre, Howe Moss Drive";
        String expectedLine3 = "Kirkhill Industrial Estate, Dyce";
        String expectedLine4 = "Aberdeen, Aberdeenshire";
        String expectedPostcode = "AB21 0GG";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Numbered house, double dependent locality */
    @Test
    public void testNumberedHouseDoubleDependentLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNumberedHouseDoubleDependentLocality();

        String expectedLine1 = "101 Inchgarth Road";
        String expectedLine2 = "Pitfodels, Cults";
        String expectedLine3 = "Aberdeen";
        String expectedLine4 = "Aberdeenshire";
        String expectedPostcode = "AB15 9NX";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Flat number in a double dependent locality */
    @Test
    public void testFlatNumberDoubleDependentLocality(){

        PAFData pafData = PAFFormatInputBuilder.getFlatNumberDoubleDependentLocality();

        String expectedLine1 = "Flat 1, Laurence Lowry Court, Lowry Drive";
        String expectedLine2 = "Pendlebury, Swinton";
        String expectedLine3 = "Manchester";
        String expectedLine4 = "Greater Manchester";
        String expectedPostcode = "M27 6DL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Organisation with department - Department A, Merseyside Police */
    @Test
    public void testDepartmentAMerseysidePolice(){

        PAFData pafData = PAFFormatInputBuilder.getDepartmentAMerseysidePolice();

        String expectedLine1 = "A Division, Merseyside Police";
        String expectedLine2 = "St. Anne Street Police Station, St. Anne Street";
        String expectedLine3 = "Liverpool";
        String expectedLine4 = "Merseyside";
        String expectedPostcode = "L3 3HJ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Division",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                true,  // division is part of the organisation name
                actualResultAddress);

    }

    /** Organisation with department, example 2 - Aberchirder, Aberdeenshire Council*/
    @Test
    public void testDepartmentAberdeenshireCouncil(){

        PAFData pafData = PAFFormatInputBuilder.getDepartmentAberdeenshireCouncil();

        String expectedLine1 = "Aberchirder, Aberdeenshire Council";
        String expectedLine2 = "Woodhill House, Westburn Road";
        String expectedLine3 = "Aberdeen";
        String expectedLine4 = "Aberdeenshire";
        String expectedPostcode = "AB16 9SH";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Organisation with department, example 3 - Acocks Green, Birmingham City Council */
    @Test
    public void testDepartmentBirminghamCityCouncil(){

        PAFData pafData = PAFFormatInputBuilder.getDepartmentBirminghamCityCouncil();

        String expectedLine1 = "Acocks Green, Birmingham City Council";
        String expectedLine2 = "The Council House";
        String expectedLine3 = "Birmingham";
        String expectedLine4 = "West Midlands";
        String expectedPostcode = "B2 2SA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** The world's longest place name, fortunately the Royal Mail use the short version because the full version is
     * Llanfairpwllgwyngyllgogerychwyrndrobwyll-llantysiliogogogoch. */
    @Test
    public void testLlanfairPGInWales(){

        PAFData pafData = PAFFormatInputBuilder.getLlanfairPGInWales();


        String expectedLine1 = "Premier";
        String expectedLine2 = "Pen Y Gongl";
        String expectedLine3 = "Newborough";
        String expectedLine4 = "Llanfairpwllgwyngyll, Sir Ynys Mon";
        String expectedPostcode = "LL61 6SL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** This is a very long organisation name on a very long thoroughfare. */
    @Test
    public void testLongOrganisationAndThoroughFare(){

        PAFData pafData = PAFFormatInputBuilder.getLongOrganisationAndThoroughFare();



        String expectedLine1 = "Junction One International Outlet Shopping";
        String expectedLine2 = "Administration Centre, 11 Junction One International Outlet Shopping";
        String expectedLine3 = "Antrim";
        String expectedLine4 = "County Antrim";
        String expectedPostcode = "BT41 4LL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(
                expectedLine1,
                expectedLine2,
                expectedLine3,
                expectedLine4,
                expectedPostcode,
                false,
                actualResultAddress);

    }

}

