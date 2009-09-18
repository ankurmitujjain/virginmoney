package com.virginmoney.addresslookup.business;

import org.apache.log4j.Logger;

/**
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class PAFFormatInputBuilder {

    private static Logger logger = Logger.getLogger(PAFFormatInputBuilder.class);

    /**
     * Utility method to create default search parameters for the PAFDataFormatter test classes.
     * @param searchPostcode  the postcode to search for
     * @param searchBuilding  the optional building name or number to search for
     * @param returnPAFData   indicates if PAF data is to be returned.
     * @return A search parameters object constructed with the above input arguments.
     */
    public static AddressSearchParameters createSearchParameters(AddressSearchParameters.ResultFormats resultFormat,
                                                                 String searchPostcode,
                                                                 String searchBuilding,
                                                                 boolean returnPAFData)
    {

        AddressSearchParameters.ReturnPAFDataRules returnPAFDataRule = null;
        if (returnPAFData) {
            returnPAFDataRule = AddressSearchParameters.ReturnPAFDataRules.returnPAFData;
        }
        return new AddressSearchParameters(searchPostcode,searchBuilding,resultFormat,null,null,null,returnPAFDataRule);
    }


    /**
     * Creates a PAF record for House with name in a village.
     *
     * @return  a PAF record for House with name in a village.
     */
    static PAFData getNamedHouseLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("17451613.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1J0)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1J");
        pafData.setChecksum("0");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Honeycombe");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Mill");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality("Stoke Holy Cross");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Business in a village, includes subbuilding, dependent locality.
     *
     * @return  a PAF record for Business in a village, includes subbuilding, dependent locality.
     */
    static PAFData getNamedBusinessSubbuildingLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("28129000.00");
        pafData.setOrganisationName("A R B G Dive Supplies");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2P7)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2P");
        pafData.setChecksum("7");
        pafData.setSubBuildingName("The Workshops");
        pafData.setBuildingName("Mill House");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Mill");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality("Stoke Holy Cross");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Business in a village, includes subbuilding, dependent locality.
     *
     * @return  a PAF record for Business in a village, includes subbuilding, dependent locality.
     */
    static PAFData getNamedBusinessNoSubbuildingLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("17451621.00");
        pafData.setOrganisationName("Post Office");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2HZ)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2H");
        pafData.setChecksum("Z");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Mill");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality("Stoke Holy Cross");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Numbered House.
     *
     * @return  a PAF record for Numbered House.
     */
    static PAFData getNumberedHouseLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("17404422.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR10 3EL");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR103EL1AI)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("I");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("20");
        pafData.setThoroughfareName("The Shrublands");
        pafData.setThoroughfareDescriptor(null);
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality("Horsford");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("0");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Numbered House with no locality details.
     *
     * @return  a PAF record for Numbered House with no locality details.
     */
    static PAFData getNumberedHouseNoLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("17360666.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR5 9EU");
        pafData.setMailsort("18635");
        pafData.setBarcode("(NR59EU1AU)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("U");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("12");
        pafData.setThoroughfareName("Stylman");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for a Flat , no locality, case 1 - no flat number .
     *
     * @return  a PAF record for a Flat , no locality, case 1 - no flat number .
     */
    static PAFData getFlatNumberNoLocality1() {
        PAFData pafData = new PAFData();

        pafData.setId("23460537.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Stoke-on-Trent");
        pafData.setCounty("Staffordshire");
        pafData.setPostcode("ST1 3EQ");
        pafData.setMailsort("19571");
        pafData.setBarcode("(ST13EQ2N1)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2N");
        pafData.setChecksum("1");
        pafData.setSubBuildingName("Flat");
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("61");
        pafData.setThoroughfareName("Imperial");
        pafData.setThoroughfareDescriptor("Court");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for a Flat , no locality, case 2 - with a flat number .
     *
     * @return  a PAF record for a Flat , no locality, case 2 - with a flat number .
     */
    static PAFData getFlatNumberNoLocality2() {
        PAFData pafData = new PAFData();

        pafData.setId("15603901.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("London");
        pafData.setCounty(null);
        pafData.setPostcode("N12 0DT");
        pafData.setMailsort("24053");
        pafData.setBarcode("(N120DT1QC)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1Q");
        pafData.setChecksum("C");
        pafData.setSubBuildingName("Flat 2");
        pafData.setBuildingName("Colman Court");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Christchurch");
        pafData.setThoroughfareDescriptor("Avenue");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Business (small organisation).
     *
     * @return  a PAF record for Business (small organisation).
     */
    static PAFData getSmallOrganisationNoLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("5419873.00");
        pafData.setOrganisationName("Bairstow Eves Countrywide");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Caterham");
        pafData.setCounty("Surrey");
        pafData.setPostcode("CR3 6LB");
        pafData.setMailsort("26732");
        pafData.setBarcode("(CR36LB1YW)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1Y");
        pafData.setChecksum("W");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("9-11");
        pafData.setThoroughfareName("Station");
        pafData.setThoroughfareDescriptor("Avenue");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for  Business (large organisation, shows dependent thoroughfare).
     *
     * @return  a PAF record for  Business (large organisation, shows dependent thoroughfare).
     */
    static PAFData getLargeOrganisationDependentThoroughfare() {
        PAFData pafData = new PAFData();

        pafData.setId("17343183.00");
        pafData.setOrganisationName("Virgin Money");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR4 6EJ");
        pafData.setMailsort("18634");
        pafData.setBarcode("(NR46EJ1AL)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(true);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("L");
        pafData.setSubBuildingName("Discovery House");
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("4");
        pafData.setThoroughfareName("Whiting");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName("Norwich Business");
        pafData.setDependentThoroughfareDescriptor("Park");
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Business (large organisation, shows po box number) .
     *
     * @return  a PAF record for Business (large organisation, shows po box number) .
     */
    static PAFData getLargeOrganisationPOBox() {
        PAFData pafData = new PAFData();

        pafData.setId("17306882.00");
        pafData.setOrganisationName("Norwich Union Insurance Group");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR1 3NG");
        pafData.setMailsort("18631");
        pafData.setBarcode("(NR13NG1AF)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(true);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("F");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName(null);
        pafData.setThoroughfareDescriptor(null);
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber("4");
        pafData.setNumberOfHouseholds("0");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Charity : cancer research (shows as residential).
     *
     * @return  a PAF record for Charity : cancer research (shows as residential)'.
     */
    static PAFData getCancerResearch() {
        PAFData pafData = new PAFData();

        pafData.setId("23674390.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Leek");
        pafData.setCounty("Staffordshire");
        pafData.setPostcode("ST13 7EQ");
        pafData.setMailsort("19542");
        pafData.setBarcode("(ST137EQ3QJ)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("3Q");
        pafData.setChecksum("J");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("41");
        pafData.setThoroughfareName("Basford Bridge");
        pafData.setThoroughfareDescriptor("Lane");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality("Cheddleton");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Charity : RNIB (shows as small organisation).
     *
     * @return  a PAF record for Charity : RNIB (shows as small organisation).
     */
    static PAFData getRNIB() {
        PAFData pafData = new PAFData();

        pafData.setId("326723.00");
        pafData.setOrganisationName("R N I B");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Birmingham");
        pafData.setCounty("West Midlands");
        pafData.setPostcode("B1 1BN");
        pafData.setMailsort("18581");
        pafData.setBarcode("(B11BN1WQ)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1W");
        pafData.setChecksum("Q");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("58-72");
        pafData.setThoroughfareName("John Bright");
        pafData.setThoroughfareDescriptor("Street");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Charity : Rehab UK (shows as small organisation).
     *
     * @return  a PAF record for Charity : Rehab UK (shows as small organisation).
     */
    static PAFData getRehabUK() {
        PAFData pafData = new PAFData();

        pafData.setId("326722.00");
        pafData.setOrganisationName("Rehab Uk");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Birmingham");
        pafData.setCounty("West Midlands");
        pafData.setPostcode("B1 1BN");
        pafData.setMailsort("18581");
        pafData.setBarcode("(B11BN2A5)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2A");
        pafData.setChecksum("5");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Borough Buildings");
        pafData.setBuildingNumber("58-72");
        pafData.setThoroughfareName("John Bright");
        pafData.setThoroughfareDescriptor("Street");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Charity : RSPCA (welsh).
     *
     * @return  a PAF record for Charity : RSPCA (welsh).
     */
    static PAFData getRSPCAWales() {
        PAFData pafData = new PAFData();

        pafData.setId("4334147.00");
        pafData.setOrganisationName("R S P C A");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Porthcawl");
        pafData.setCounty("Pen-y-bont ar Ogwr");
        pafData.setPostcode("CF36 3BD");
        pafData.setMailsort("33059");
        pafData.setBarcode("(CF363BD1WK)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1W");
        pafData.setChecksum("K");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("84");
        pafData.setThoroughfareName("John");
        pafData.setThoroughfareDescriptor("Street");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Charity : RSPCA (english).
     *
     * @return  a PAF record for Charity : RSPCA (english).
     */
    static PAFData getRSPCAEngland() {
        PAFData pafData = new PAFData();

        pafData.setId("8769828.00");
        pafData.setOrganisationName("R S P C A");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Exeter");
        pafData.setCounty("Devon");
        pafData.setPostcode("EX2 9TA");
        pafData.setMailsort("32411");
        pafData.setBarcode("(EX29TA1DS)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1D");
        pafData.setChecksum("S");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Black Hat Lane");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Bakers");
        pafData.setThoroughfareDescriptor("Hill");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Business with double dependent locality.
     *
     * @return  a PAF record for Business with double dependent locality.
     */
    static PAFData getNamedBusinessDoubleDependentLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("19966530.00");
        pafData.setOrganisationName("Amber Credit");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Basingstoke");
        pafData.setCounty("Hampshire");
        pafData.setPostcode("RG24 8QY");
        pafData.setMailsort("29882");
        pafData.setBarcode("(RG248QY1AA)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("A");
        pafData.setSubBuildingName("Amber House");
        pafData.setBuildingName("Unit 3");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Crockford");
        pafData.setThoroughfareDescriptor("Lane");
        pafData.setDependentThoroughfareName("Lindenwood");
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality("Chineham Business Park");
        pafData.setDependentLocality("Chineham");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Business with double dependent locality , case 2.
     *
     * @return  a PAF record for Business with double dependent locality , case 2.
     */
    static PAFData getNamedBusinessDoubleDependentLocality2() {
        PAFData pafData = new PAFData();

        pafData.setId("67024.00");
        pafData.setOrganisationName("2M Power Systems Ltd");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Aberdeen");
        pafData.setCounty("Aberdeenshire");
        pafData.setPostcode("AB21 0GG");
        pafData.setMailsort("11021");
        pafData.setBarcode("(AB210GG2YA)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2Y");
        pafData.setChecksum("A");
        pafData.setSubBuildingName("Unit 14");
        pafData.setBuildingName("Robert Leonard Centre");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Howe Moss");
        pafData.setThoroughfareDescriptor("Drive");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality("Kirkhill Industrial Estate");
        pafData.setDependentLocality("Dyce");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Numbered house, double dependent locality.
     *
     * @return  a PAF record for Numbered house, double dependent locality.
     */
     static PAFData getNumberedHouseDoubleDependentLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("52228.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Aberdeen");
        pafData.setCounty("Aberdeenshire");
        pafData.setPostcode("AB15 9NX");
        pafData.setMailsort("11023");
        pafData.setBarcode("(AB159NX1BG)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1B");
        pafData.setChecksum("G");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("101");
        pafData.setThoroughfareName("Inchgarth");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality("Pitfodels");
        pafData.setDependentLocality("Cults");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Flat number in a double dependent locality.
     *
     * @return  a PAF record for Flat number in a double dependent locality.
     */
    static PAFData getFlatNumberDoubleDependentLocality() {
        PAFData pafData = new PAFData();

        pafData.setId("14602547.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Manchester");
        pafData.setCounty("Greater Manchester");
        pafData.setPostcode("M27 6DL");
        pafData.setMailsort("12432");
        pafData.setBarcode("(M276DL3JG)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("3J");
        pafData.setChecksum("G");
        pafData.setSubBuildingName("Flat 1");
        pafData.setBuildingName("Laurence Lowry Court");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Lowry");
        pafData.setThoroughfareDescriptor("Drive");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality("Pendlebury");
        pafData.setDependentLocality("Swinton");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Organisation with department - Department A, Merseyside Police .
     *
     * @return  a PAF record for Organisation with department - Department A, Merseyside Police.
     */
    static PAFData getDepartmentAMerseysidePolice() {
        PAFData pafData = new PAFData();

        pafData.setId("12494046.00");
        pafData.setOrganisationName("Merseyside Police");
        pafData.setDepartmentName("A Division");
        pafData.setPostTown("Liverpool");
        pafData.setCounty("Merseyside");
        pafData.setPostcode("L3 3HJ");
        pafData.setMailsort("13811");
        pafData.setBarcode("(L33HJ1AK)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(true);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("K");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("St. Anne Street Police Station");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("St. Anne");
        pafData.setThoroughfareDescriptor("Street");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("0");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Organisation with department, example 2 - Aberchirder, Aberdeenshire Council.
     *
     * @return  a PAF record for Organisation with department, example 2 - Aberchirder, Aberdeenshire Council.
     */
    static PAFData getDepartmentAberdeenshireCouncil() {
        PAFData pafData = new PAFData();

        pafData.setId("28004212.00");
        pafData.setOrganisationName("Aberdeenshire Council");
        pafData.setDepartmentName("Aberchirder");
        pafData.setPostTown("Aberdeen");
        pafData.setCounty("Aberdeenshire");
        pafData.setPostcode("AB16 9SH");
        pafData.setMailsort(null);
        pafData.setBarcode("(AB169SH1AB)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("B");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Woodhill House");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName("Westburn");
        pafData.setThoroughfareDescriptor("Road");
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for Organisation with department, example 3 - Acocks Green, Birmingham City Council.
     *
     * @return  a PAF record for Organisation with department, example 3 - Acocks Green, Birmingham City Council.
     */
    static PAFData getDepartmentBirminghamCityCouncil() {
        PAFData pafData = new PAFData();

        pafData.setId("28024929.00");
        pafData.setOrganisationName("Birmingham City Council");
        pafData.setDepartmentName("Acocks Green");
        pafData.setPostTown("Birmingham");
        pafData.setCounty("West Midlands");
        pafData.setPostcode("B2 2SA");
        pafData.setMailsort("18581");
        pafData.setBarcode("(B22SA1AA)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("A");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("The Council House");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName(null);
        pafData.setThoroughfareDescriptor(null);
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for The world's longest place name.
     *
     * @return  a PAF record for The world's longest place name.
     */
    static PAFData getLlanfairPGInWales() {
        PAFData pafData = new PAFData();

        pafData.setOrganisationName("Premier");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Llanfairpwllgwyngyll");
        pafData.setCounty("Sir Ynys Mon");
        pafData.setPostcode("LL61 6SL");
        pafData.setMailsort("14051");
        pafData.setBarcode("(LL616SL1QJ)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1Q");
        pafData.setChecksum("J");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Pen Y Gongl");
        pafData.setBuildingNumber(null);
        pafData.setThoroughfareName(null);
        pafData.setThoroughfareDescriptor(null);
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality("Newborough");
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

    /**
     * Creates a PAF record for a very long organisation name on a very long thoroughfare.
     *
     * @return  a PAF record for a very long organisation name on a very long thoroughfare.
     */
    static PAFData getLongOrganisationAndThoroughFare() {
        PAFData pafData = new PAFData();

        pafData.setId("3514410.00");
        pafData.setOrganisationName("Junction One International Outlet Shopping");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Antrim");
        pafData.setCounty("County Antrim");
        pafData.setPostcode("BT41 4LL");
        pafData.setMailsort("10281");
        pafData.setBarcode("(BT414LL1AE)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("E");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Administration Centre");
        pafData.setBuildingNumber("11");
        pafData.setThoroughfareName("Junction One International Outlet Shopping");
        pafData.setThoroughfareDescriptor(null);
        pafData.setDependentThoroughfareName(null);
        pafData.setDependentThoroughfareDescriptor(null);
        pafData.setDoubleDependentLocality(null);
        pafData.setDependentLocality(null);
        pafData.setPoBoxNumber(null);
        pafData.setNumberOfHouseholds("1");
        pafData.setConcatenationOperator(null);
        pafData.setGridEastM(0);
        pafData.setGridNorthM(0);
        pafData.setObjective2(false);
        pafData.setTransitional(false);
        return pafData;
    }

}

