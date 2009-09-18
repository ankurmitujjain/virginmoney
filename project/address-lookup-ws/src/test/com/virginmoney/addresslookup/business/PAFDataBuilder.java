package com.virginmoney.addresslookup.business;

import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Builds a List of R {@link PAFData} objects which can be used in testing
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class PAFDataBuilder {

    private static Logger logger = Logger.getLogger(PAFDataBuilder.class);

    /**
     * Builds an {@AddressList} object using data from a postcode anywhere call.
     * @return an {@AddressList} object using data from a postcode anywhere call.
     */
    public static AddressList buildAddressListFromPAFAddresses(AddressSearchParameters searchParameters) {

        AddressList addressList= new  AddressList(searchParameters);
        List<AbstractAddress> addresses = addressList.getAddresses();


        for (PAFData pafData : buildListOfPAFAddresses()) {
            addresses.add(PAFDataFormatter.formatAddress(searchParameters,pafData));
//            addresses.add(new Address(null,null,null,null,null,pafData));
        }

        return addressList;

    }


    /**
     * Builds a list of {@link PAFData} objects using data from a postcode anywhere call.
     * @return a list of {@link PAFData} objects using data from a postcode anywhere call.
     */
    public static List<PAFData> buildListOfPAFAddresses() {
        List<PAFData> pafDataSet = new ArrayList<PAFData>();
        PAFData pafData = new PAFData();

        // This data has been scavenged from a Postcode anywhere call
        // This particular data has been used because it contains a mix of residential and business pafDataSet
        //For the purposes of testing, 'Alveston' has also been given a number
        pafDataSet.add(pafData);
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

        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451620.00");
        pafData.setOrganisationName("Old Mill Restaurant");
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1FW)");
        pafData.setIsResidential(false);
        pafData.setIsSmallOrganisation(true);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1F");
        pafData.setChecksum("W");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
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


        // NOTE - this used to be 'Avleston' but has been given the number 99 instead
        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451603.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1TA)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1T");
        pafData.setChecksum("A");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName(null);
        pafData.setBuildingNumber("99");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451605.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1S9)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1S");
        pafData.setChecksum("9");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Cordovan");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451606.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1R8)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1R");
        pafData.setChecksum("8");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Craigley");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451607.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1Q7)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1Q");
        pafData.setChecksum("7");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Dalriada");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451608.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2AS)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2A");
        pafData.setChecksum("S");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Dovedale");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451610.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1N4)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1N");
        pafData.setChecksum("4");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Greenways");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451611.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1L2)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1L");
        pafData.setChecksum("2");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("High Beech House");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451612.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1EV)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1E");
        pafData.setChecksum("V");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Holy Cross Vicarage");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451614.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1HY)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1H");
        pafData.setChecksum("Y");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Jacama");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451615.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2GY)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2G");
        pafData.setChecksum("Y");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Low Meadow");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451618.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2L3)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2L");
        pafData.setChecksum("3");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Mill Cottage");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451617.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1WD)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1W");
        pafData.setChecksum("D");
        pafData.setSubBuildingName(null);
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451616.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1UH)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1U");
        pafData.setChecksum("H");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Mill Road Stores");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451619.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1GX)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1G");
        pafData.setChecksum("X");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Netherfield");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451622.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1ZG)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1Z");
        pafData.setChecksum("G");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Purbeck Cottage");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451623.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1YF)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1Y");
        pafData.setChecksum("F");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Stonecroft");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451624.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1DU)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1D");
        pafData.setChecksum("U");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Tara");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451625.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1AR)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1A");
        pafData.setChecksum("R");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Tas Valley House");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451604.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2N5)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2N");
        pafData.setChecksum("5");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("The Blue Barn");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451609.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA2J1)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("2J");
        pafData.setChecksum("1");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("The Enchanted Rose");
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



        pafData = new PAFData();
        pafDataSet.add(pafData);
        pafData.setId("17451626.00");
        pafData.setOrganisationName(null);
        pafData.setDepartmentName(null);
        pafData.setPostTown("Norwich");
        pafData.setCounty("Norfolk");
        pafData.setPostcode("NR14 8PA");
        pafData.setMailsort("18619");
        pafData.setBarcode("(NR148PA1P6)");
        pafData.setIsResidential(true);
        pafData.setIsSmallOrganisation(false);
        pafData.setIsLargeOrganisation(false);
        pafData.setDeliveryPointSuffix("1P");
        pafData.setChecksum("6");
        pafData.setSubBuildingName(null);
        pafData.setBuildingName("Torre");
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


        logger.debug("Created " + pafDataSet.size()  + " pafDataSet");



        return pafDataSet;
    }
}

