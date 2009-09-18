package com.virginmoney.qa.addresslookup;

import com.virginmoney.addresslookup.messages.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************************
 * Class         :  AddressLookupHelper
 * Functionality :  Calls the address lookup system to search for a postcode
 * and populates a list of results which can be used on the Fitnesse page
 * Author(s)     :  woodsn
 * Creation Date :  08-Jan-2008
 * Copyright     :  Virgin Money Ltd.
 * ******************************************************************************
 */

public class AddressLookupHelper {
    private Logger logger = Logger.getLogger(AddressLookupHelper.class);

    public List<AddressLookupResult> doUKPostcodeSearch(String postcode, String buildingNameOrNumber) throws Exception {

        List<AddressLookupResult> results = new ArrayList<AddressLookupResult>();

        logger.debug("doPostcodeSearch() helper started : postcode=" + postcode + " building=" + buildingNameOrNumber);

        // get the address lookup service - this will (should..) have been populated by the
        // fitnesse page
        AddressLookupService service = WebServiceLocator.getAddressLookupService();
        AddressLookupServicePortType port = service.getAddressLookupServicePortType();

        AddressLookupRequest request = new AddressLookupRequest();
        request.setSearchPostcode(postcode);
        request.setSearchBuilding(buildingNameOrNumber);

        AddressLookupResponse response = port.addressLookup(request);

        if (response == null || response.getAddresses() == null) {
            logger.debug("doPostcodeSearch() helper null response");
            return null;
        }

        logger.debug("doPostcodeSearch() helper response count = " + response.getAddresses().size());

        for (Address addressResponse : response.getAddresses()) {
            AddressLookupResult result = new AddressLookupResult();

            //result.organisationName = addressResponse.getOrganisationName();
            result.pAFCode = addressResponse.getPafData().getPAFCode();
            result.buildingName = addressResponse.getPafData().getBuildingName();
            result.buildingNumber = addressResponse.getPafData().getBuildingNumber();
            result.postTown = addressResponse.getPafData().getPostTown();
            result.county = addressResponse.getPafData().getCounty();
            result.postcode = addressResponse.getPafData().getPostcode();
            result.pickListEntry = addressResponse.getPicklistEntry();
            result.setNullValues();

            results.add(result);

        }


        logger.debug("doPostcodeSearch() helper finished");
        return results;

    }

}
