package com.virginmoney.qa.addresslookup;

/**
 * *****************************************************************************
 * Class         :  AddressLookupResult
 * Functionality :  Java bean passed to Fitnesse by the AddressLookupScenarioFixture class to show the results
 * returned as a result of doing a postcode lookup. Strictly speaking this isnt a Fixture but its only purpose is to
 * allow fitnesse to check the result data
 * Author(s)     :  woodsn
 * Creation Date :  04-Jan-2008
 * Copyright     :  Virgin Money Ltd.
 * ******************************************************************************
 */

public class AddressLookupResult {

    public String buildingName;
    public String buildingNumber;
    public String organisationName;
    public String addressLine1;
    public String addressLine2;
    public String addressLine3;
    public String addressLine4;
    public String addressLine5;
    public String pAFCode;  // odd casing because thats what Fitnesse expects...
    public String postTown;
    public String county;
    public String postcode;
    public String pickListEntry;


    /**
     * Sets any null values to the default value
     */
    public void setNullValues() {
        if (buildingName == null || buildingName.equals("")) {
            buildingName = AddressLookupScenarioFixture.nullValue;
        }
        if (buildingNumber == null || buildingNumber.equals("")) {
            buildingNumber = AddressLookupScenarioFixture.nullValue;
        }
        if (organisationName == null || organisationName.equals("")) {
            organisationName = AddressLookupScenarioFixture.nullValue;
        }
        if (addressLine1 == null || addressLine1.equals("")) {
            addressLine1 = AddressLookupScenarioFixture.nullValue;
        }
        if (addressLine2 == null || addressLine2.equals("")) {
            addressLine2 = AddressLookupScenarioFixture.nullValue;
        }
        if (addressLine3 == null || addressLine3.equals("")) {
            addressLine3 = AddressLookupScenarioFixture.nullValue;
        }
        if (addressLine4 == null || addressLine4.equals("")) {
            addressLine4 = AddressLookupScenarioFixture.nullValue;
        }
        if (addressLine5 == null || addressLine5.equals("")) {
            addressLine5 = AddressLookupScenarioFixture.nullValue;
        }
        if (pAFCode == null || pAFCode.equals("")) {
            pAFCode = AddressLookupScenarioFixture.nullValue;
        }
        if (postTown == null || postTown.equals("")) {
            postTown = AddressLookupScenarioFixture.nullValue;
        }
        if (county == null || county.equals("")) {
            county = AddressLookupScenarioFixture.nullValue;
        }
        if (postcode == null || postcode.equals("")) {
            postcode = AddressLookupScenarioFixture.nullValue;
        }
        if (pickListEntry == null || pickListEntry.equals("")) {
            pickListEntry = AddressLookupScenarioFixture.nullValue; }

}
}
