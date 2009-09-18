package com.virginmoney.qa.addresslookup;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * *****************************************************************************
 * Class         :  AddressLookupScenarioFixture
 * Functionality :  Fitnesse Scenario fixture for the Address Lookup service
 * Author(s)     :  woodsn
 * Creation Date :  04-Jan-2008
 * Copyright     :  Virgin Money Ltd.
 * ******************************************************************************
 */

public class AddressLookupScenarioFixture extends fitlibrary.DoFixture {
    private static Logger logger = Logger.getLogger(AddressLookupScenarioFixture.class);

    // default value to indicate nulls
    public static String nullValue = "(null)";

    // default value to indicate not null for values where we cant comapre an actual value
    public static String notNullValue = "(not null)";

    private PostcodeSetterFixture postcodeSetterFixture;


    public PostcodeSetterFixture getPostcodeSetter() {
        postcodeSetterFixture = new PostcodeSetterFixture();
        return postcodeSetterFixture;
    }

    public SystemPropertySetter getSystemPropertySetter() {
        return new SystemPropertySetter();
    }

    public List<AddressLookupResult> getAddressLookUp() throws Exception {

        if (postcodeSetterFixture == null) {
            return null;
        }

        AddressLookupHelper helper = new AddressLookupHelper();
        return helper.doUKPostcodeSearch(postcodeSetterFixture.postcode, postcodeSetterFixture.buildingNameOrNumber);

    }

}
