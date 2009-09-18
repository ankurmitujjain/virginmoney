package com.virginmoney.addresslookup.business;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang.StringUtils;

/** Junit test class for {@link com.virginmoney.addresslookup.business.PAFDataFormatter}, using Style 2 addresses. */

public class TestPAFDataFormatterStyle2 {
    private Logger logger = Logger.getLogger(TestPAFDataFormatterStyle2.class);

    private PAFDataFormatter testPAFDataFormatter;
    private AddressSearchParameters.ResultFormats resultFormat;

    @Before
    public void setUp() {
        testPAFDataFormatter = new PAFDataFormatter();
        assertNotNull(testPAFDataFormatter);

        resultFormat = AddressSearchParameters.ResultFormats.STYLE2;
        assertNotNull(resultFormat);
    }

    @After
    public void tearDown() {

        testPAFDataFormatter = null;
    }

    private void checkAddressResult(String expectedOrganisationName,
                         String expectedLine1,
                         String expectedLine2,
                         String expectedPostTown,
                         String expectedCounty,
                         String expectedPostcode,
                         boolean expectedMatch,
                         AbstractAddress resultAddress)
    {

        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        assertNotNull(StringUtils.rightPad(methodName,40) + " : actualResultAddress must not be null", resultAddress);
        assertEquals("Check expected address class ",AddressStyle2.class,resultAddress.getClass());

        AddressStyle2 actualResultAddress = (AddressStyle2) resultAddress;
        logger.debug("Checking actualResultAddress=" + actualResultAddress);

        logger.debug(StringUtils.rightPad(methodName,40) + " : Organisation   : matches="  + StringUtils.rightPad(String.valueOf(expectedOrganisationName == actualResultAddress.getOrganisationName()),6) + " expected=" + StringUtils.rightPad(expectedOrganisationName ,50)    + " actual=" +  actualResultAddress.getOrganisationName());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Address line 1 : matches="  + StringUtils.rightPad(String.valueOf(expectedLine1 == actualResultAddress.getAddressLine1()),6) + " expected=" + StringUtils.rightPad(expectedLine1 ,50)    + " actual=" +  actualResultAddress.getAddressLine1());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Address line 2 : matches="  + StringUtils.rightPad(String.valueOf(expectedLine2 == actualResultAddress.getAddressLine2()),6) + " expected=" + StringUtils.rightPad(expectedLine2 ,50)    + " actual=" +  actualResultAddress.getAddressLine2());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Post town      : matches="  + StringUtils.rightPad(String.valueOf(expectedPostTown == actualResultAddress.getPostTown()),6) + " expected=" + StringUtils.rightPad(expectedPostTown ,50)    + " actual=" +  actualResultAddress.getPostTown());
        logger.debug(StringUtils.rightPad(methodName,40) + " : County         : matches="  + StringUtils.rightPad(String.valueOf(expectedCounty == actualResultAddress.getCounty()),6) + " expected=" + StringUtils.rightPad(expectedCounty ,50)    + " actual=" +  actualResultAddress.getCounty());
        logger.debug(StringUtils.rightPad(methodName,40) + " : Postcode       : matches="  + StringUtils.rightPad(String.valueOf(expectedPostcode == actualResultAddress.getPostcode()) ,6) + " expected=" + StringUtils.rightPad(expectedPostcode ,50) + " actual=" +  actualResultAddress.getPostcode());
        logger.debug(StringUtils.rightPad(methodName,40) + " : SearchMatches  : matches="  + StringUtils.rightPad(String.valueOf(expectedMatch == actualResultAddress.isMatchesSearchBuilding()) ,6) + " expected=" + StringUtils.rightPad(String.valueOf(expectedMatch) ,50) + " actual=" +  actualResultAddress.isMatchesSearchBuilding());

        assertEquals(methodName + " : OrganisationName", expectedOrganisationName, actualResultAddress.getOrganisationName());
        assertEquals(methodName + " : Address line 1", expectedLine1, actualResultAddress.getAddressLine1());
        assertEquals(methodName + " : Address line 2", expectedLine2, actualResultAddress.getAddressLine2());
        assertEquals(methodName + " : Post town", expectedPostTown, actualResultAddress.getPostTown());
        assertEquals(methodName + " : County", expectedCounty, actualResultAddress.getCounty());
        assertEquals(methodName + " : Postcode", expectedPostcode, actualResultAddress.getPostcode());
        assertEquals(methodName + " : MatchesSearchBuilding", expectedMatch, actualResultAddress.isMatchesSearchBuilding());

        if (StringUtils.isNotBlank(expectedOrganisationName)) {
            assertTrue("picklist contain organisation : " + expectedOrganisationName + " should be in " +
                    actualResultAddress.getPicklistEntry(),
                    StringUtils.containsIgnoreCase(actualResultAddress.getPicklistEntry(), expectedLine1));
        }
        
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

        String expectedOrganisationName = null;
        String expectedLine1 = "Honeycombe, Mill Road";
        String expectedLine2 = "Stoke Holy Cross";
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR14 8PA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Honey",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);

        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                true, // honey is part of the house name
                actualResultAddress);

    }

    /** Business in a village, includes subbuilding, dependent locality */
    @Test
    public void testNamedBusinessSubbuildingLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessSubbuildingLocality();

        String expectedOrganisationName = "A R B G Dive Supplies";
        String expectedLine1 = "The Workshops, Mill House, Mill Road";
        String expectedLine2 = "Stoke Holy Cross";
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR14 8PA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"a r b g",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                true,   // a r b g is part of the company name
                actualResultAddress);

    }

    /** Business in a village, no subbuilding, dependent locality */
    @Test
    public void testNamedBusinessNoSubbuildingLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessNoSubbuildingLocality();


        String expectedOrganisationName = "Post Office";
        String expectedLine1 = "Mill Road";
        String expectedLine2 = "Stoke Holy Cross";
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR14 8PA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Road",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,           // Road is not part of the company or building name
                actualResultAddress);

    }

    /** Numbered House */
    @Test
    public void testNumberedHouseLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNumberedHouseLocality();

        String expectedLine1 = "20 The Shrublands";
        String expectedLine2 = "Horsford";
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR10 3EL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"20",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        String expectedOrganisationName = null;
        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                true,  // 20 is the building number
                actualResultAddress);

    }

    /** Numbered House with no locality details */
    @Test
    public void testNumberedHouseNoLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNumberedHouseNoLocality();

        String expectedLine1 = "12 Stylman Road";
        String expectedLine2 = null;
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR5 9EU";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Stylman",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        String expectedOrganisationName = null;
        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,              // Stylman is not part of the building name or number
                actualResultAddress);

    }

    /** Flat , no locality, case 1 - no flat number */
    @Test
    public void testFlatNumberNoLocality1(){

        PAFData pafData = PAFFormatInputBuilder.getFlatNumberNoLocality1();

        String expectedOrganisationName = null;
        String expectedLine1 = "Flat, 61 Imperial Court";
        String expectedLine2 = null;
        String expectedPostTown = "Stoke-on-Trent";
        String expectedCounty = "Staffordshire";
        String expectedPostcode = "ST1 3EQ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Flat , no locality , case 2 - with a flat number */
    @Test
    public void testFlatNumberNoLocality2(){

        PAFData pafData = PAFFormatInputBuilder.getFlatNumberNoLocality2();

        String expectedOrganisationName = null;
        String expectedLine1 = "Flat 2, Colman Court, Christchurch Avenue";
        String expectedLine2 = null;
        String expectedPostTown = "London";
        String expectedCounty = null;
        String expectedPostcode = "N12 0DT";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business (small organisation) */
    @Test
    public void testSmallOrganisationNoLocality(){

        PAFData pafData = PAFFormatInputBuilder.getSmallOrganisationNoLocality();

        String expectedOrganisationName = "Bairstow Eves Countrywide";
        String expectedLine1 = "9-11 Station Avenue";
        String expectedLine2 = null;
        String expectedPostTown = "Caterham";
        String expectedCounty = "Surrey";
        String expectedPostcode = "CR3 6LB";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business (large organisation, shows dependent thoroughfare) */
    @Test
    public void testLargeOrganisationDependentThoroughfare(){

        PAFData pafData = PAFFormatInputBuilder.getLargeOrganisationDependentThoroughfare();

        String expectedOrganisationName = "Virgin Money";
        String expectedLine1 = "Discovery House, 4 Norwich Business Park, Whiting Road";
        String expectedLine2 = null;
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR4 6EJ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Discovery House",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);

        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                true,
                actualResultAddress);

    }

    /** Business (large organisation, shows po box number) */
    @Test
    public void testLargeOrganisationPOBox(){

        PAFData pafData = PAFFormatInputBuilder.getLargeOrganisationPOBox();

        String expectedOrganisationName = "Norwich Union Insurance Group";
        String expectedLine1 = "PO Box 4";
        String expectedLine2 = null;
        String expectedPostTown = "Norwich";
        String expectedCounty = "Norfolk";
        String expectedPostcode = "NR1 3NG";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : cancer research (shows as residential) */
    @Test
    public void testCancerResearch(){

        PAFData pafData = PAFFormatInputBuilder.getCancerResearch();

        // Cancer research is NOT an organisation (according to the PAF file)
        String expectedOrganisationName = null;
        String expectedLine1 = "41 Basford Bridge Lane";
        String expectedLine2 = "Cheddleton";
        String expectedPostTown = "Leek";
        String expectedCounty = "Staffordshire";
        String expectedPostcode = "ST13 7EQ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : RNIB (shows as small organisation) */
    @Test
    public void testRNIB(){

        PAFData pafData = PAFFormatInputBuilder.getRNIB();

        String expectedOrganisationName = "R N I B";
        String expectedLine1 = "58-72 John Bright Street";
        String expectedLine2 = null;
        String expectedPostTown = "Birmingham";
        String expectedCounty = "West Midlands";
        String expectedPostcode = "B1 1BN";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : Rehab UK (shows as small organisation) */
    @Test
    public void testRehabUK(){

        PAFData pafData = PAFFormatInputBuilder.getRehabUK();

        String expectedOrganisationName = "Rehab Uk";
        String expectedLine1 = "Borough Buildings, 58-72 John Bright Street";
        String expectedLine2 = null;
        String expectedPostTown = "Birmingham";
        String expectedCounty = "West Midlands";
        String expectedPostcode = "B1 1BN";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : RSPCA (welsh) */
    @Test
    public void testRSPCAWales(){

        PAFData pafData = PAFFormatInputBuilder.getRSPCAWales();

        String expectedOrganisationName = "R S P C A";
        String expectedLine1 = "84 John Street";
        String expectedLine2 = null;
        String expectedPostTown = "Porthcawl";
        String expectedCounty = "Pen-y-bont ar Ogwr";
        String expectedPostcode = "CF36 3BD";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Charity : RSPCA (english) */
    @Test
    public void testRSPCAEngland(){

        PAFData pafData = PAFFormatInputBuilder.getRSPCAEngland();

        String expectedOrganisationName = "R S P C A";
        String expectedLine1 = "Black Hat Lane, Bakers Hill";
        String expectedLine2 = null;
        String expectedPostTown = "Exeter";
        String expectedCounty = "Devon";
        String expectedPostcode = "EX2 9TA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business with double dependent locality */
    @Test
    public void testNamedBusinessDoubleDependentLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessDoubleDependentLocality();

        String expectedOrganisationName = "Amber Credit";
        String expectedLine1 = "Amber House, Unit 3, Lindenwood, Crockford Lane";
        String expectedLine2 = "Chineham Business Park, Chineham";
        String expectedPostTown = "Basingstoke";
        String expectedCounty = "Hampshire";
        String expectedPostcode = "RG24 8QY";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Business with double dependent locality , case 2 */
    @Test
    public void testNamedBusinessDoubleDependentLocality2(){

        PAFData pafData = PAFFormatInputBuilder.getNamedBusinessDoubleDependentLocality2();

        String expectedOrganisationName = "2M Power Systems Ltd";
        String expectedLine1 = "Unit 14, Robert Leonard Centre, Howe Moss Drive";
        String expectedLine2 = "Kirkhill Industrial Estate, Dyce";
        String expectedPostTown = "Aberdeen";
        String expectedCounty = "Aberdeenshire";
        String expectedPostcode = "AB21 0GG";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Numbered house, double dependent locality */
    @Test
    public void testNumberedHouseDoubleDependentLocality(){

        PAFData pafData = PAFFormatInputBuilder.getNumberedHouseDoubleDependentLocality();

        String expectedOrganisationName = null;
        String expectedLine1 = "101 Inchgarth Road";
        String expectedLine2 = "Pitfodels, Cults";
        String expectedPostTown = "Aberdeen";
        String expectedCounty = "Aberdeenshire";
        String expectedPostcode = "AB15 9NX";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Flat number in a double dependent locality */
    @Test
    public void testFlatNumberDoubleDependentLocality(){

        PAFData pafData = PAFFormatInputBuilder.getFlatNumberDoubleDependentLocality();

        String expectedOrganisationName = null;
        String expectedLine1 = "Flat 1, Laurence Lowry Court, Lowry Drive";
        String expectedLine2 = "Pendlebury, Swinton";
        String expectedPostTown = "Manchester";
        String expectedCounty = "Greater Manchester";
        String expectedPostcode = "M27 6DL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** Organisation with department - Department A, Merseyside Police */
    @Test
    public void testDepartmentAMerseysidePolice(){

        PAFData pafData = PAFFormatInputBuilder.getDepartmentAMerseysidePolice();

        String expectedOrganisationName = "A Division, Merseyside Police";
        String expectedLine1 = "St. Anne Street Police Station, St. Anne Street";
        String expectedLine2 = null;
        String expectedPostTown = "Liverpool";
        String expectedCounty = "Merseyside";
        String expectedPostcode = "L3 3HJ";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"Division",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                true,  // 'Division is part of the  organisation anme
                actualResultAddress);

    }

    /** Organisation with department, example 2 - Aberchirder, Aberdeenshire Council*/
    @Test
    public void testDepartmentAberdeenshireCouncil(){

        PAFData pafData = PAFFormatInputBuilder.getDepartmentAberdeenshireCouncil();

        String expectedOrganisationName = "Aberchirder, Aberdeenshire Council";
        String expectedLine1 = "Woodhill House, Westburn Road";
        String expectedLine2 = null;
        String expectedPostTown = "Aberdeen";
        String expectedCounty = "Aberdeenshire";
        String expectedPostcode = "AB16 9SH";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,"qwerty",true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,  // qwerty is not part of the organisation or building name
                actualResultAddress);

    }

    /** Organisation with department, example 3 - Acocks Green, Birmingham City Council */
    @Test
    public void testDepartmentBirminghamCityCouncil(){

        PAFData pafData = PAFFormatInputBuilder.getDepartmentBirminghamCityCouncil();

        String expectedOrganisationName = "Acocks Green, Birmingham City Council";
        String expectedLine1 = "The Council House";
        String expectedLine2 = null;
        String expectedPostTown = "Birmingham";
        String expectedCounty = "West Midlands";
        String expectedPostcode = "B2 2SA";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** The world's longest place name, fortunately the Royal Mail use the short version because the full version is
     * Llanfairpwllgwyngyllgogerychwyrndrobwyll-llantysiliogogogoch. */
    @Test
    public void testLlanfairPGInWales(){

        PAFData pafData = PAFFormatInputBuilder.getLlanfairPGInWales();


        String expectedOrganisationName = "Premier";
        String expectedLine1 = "Pen Y Gongl";
        String expectedLine2 = "Newborough";
        String expectedPostTown = "Llanfairpwllgwyngyll";
        String expectedCounty = "Sir Ynys Mon";
        String expectedPostcode = "LL61 6SL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

    /** This is a very long organisation name on a very long thoroughfare. */
    @Test
    public void testLongOrganisationAndThoroughFare(){

        PAFData pafData = PAFFormatInputBuilder.getLongOrganisationAndThoroughFare();



        String expectedOrganisationName = "Junction One International Outlet Shopping";
        String expectedLine1 = "Administration Centre, 11 Junction One International Outlet Shopping";
        String expectedLine2 = null;
        String expectedPostTown = "Antrim";
        String expectedCounty = "County Antrim";
        String expectedPostcode = "BT41 4LL";

        AddressSearchParameters searchParameters = PAFFormatInputBuilder.createSearchParameters(resultFormat,expectedPostcode,null,true);
        AbstractAddress actualResultAddress = PAFDataFormatter.formatAddress(searchParameters,pafData);


        checkAddressResult(expectedOrganisationName,
                expectedLine1,
                expectedLine2,
                expectedPostTown,
                expectedCounty,
                expectedPostcode,
                false,
                actualResultAddress);

    }

}

