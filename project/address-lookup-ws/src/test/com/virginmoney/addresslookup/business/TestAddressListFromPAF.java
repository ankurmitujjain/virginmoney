
package com.virginmoney.addresslookup.business;

import org.junit.Test;
import org.junit.Assert;
import org.apache.log4j.Logger;

/**
 Junit test class for {@link AddressList}.
*/

public class TestAddressListFromPAF extends AbstractTestAddressList{
private Logger logger = Logger.getLogger(TestAddressListFromPAF.class);

    protected AddressList getAddressList(AddressSearchParameters searchParameters) {
        return PAFDataBuilder.buildAddressListFromPAFAddresses(searchParameters);
    }

    protected AddressSearchParameters getSearchParameters(String searchBuilding) {

        AddressSearchParameters searchParameters = new AddressSearchParameters("NR14 8PA",
                searchBuilding,
                AddressSearchParameters.defaultResultFormat,
                AddressSearchParameters.ServiceProviders.PostcodeAnywhere,
                AddressSearchParameters.ReturnMatchingResultRules.allResults,
                AddressSearchParameters.SortMatchingResultRules.sort,
                AddressSearchParameters.ReturnPAFDataRules.returnPAFData);
        return searchParameters;
    }

    @Test
    public void testVillageNameNotFound(){
         // Road is NOT expected to result in a match from a PAF data search, because none of the test cases
         // have a building or organisation name which contains it
         sortExpectNotFound("Stoke Holy Cross");
    }

    /**
    * Checks that search building number is not sorted to the start of the list if it is only a partial match.
    * i.e number 9 should not cause a match against number 99
    *
    */
    @Test
    public void TestPartialBuildingNumberSort() throws Exception {
       sortExpectNotFound("9");
   }


}
