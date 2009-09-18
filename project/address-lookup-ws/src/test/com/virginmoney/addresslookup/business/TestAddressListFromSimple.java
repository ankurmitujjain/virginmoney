package com.virginmoney.addresslookup.business;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;

/**
 Junit test class for {@link com.virginmoney.addresslookup.business.AddressList} using 'simple' addresses.
*/

public class TestAddressListFromSimple extends AbstractTestAddressList{
private Logger logger = Logger.getLogger(TestAddressListFromSimple.class);

    protected AddressList getAddressList(AddressSearchParameters searchParameters) {
        return SimpleAddressBuilder.buildAddressListFromSimpleAddresses(searchParameters);
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
    public void Dummy(){
        Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
    }

}

