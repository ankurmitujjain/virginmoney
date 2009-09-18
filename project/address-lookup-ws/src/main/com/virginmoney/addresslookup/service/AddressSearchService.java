package com.virginmoney.addresslookup.service;

import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.validators.ValidationException;

/**
 * Defines the interface for an address search for a postcode, with optional house name/number and search provider.
 *
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */


public interface AddressSearchService {

    /**
     * Searches for an address using a postcode, with optional house name /number and optional address lookup provider.
     * @param searchParameters Contains the search parameters.
     * @return A list of addresses, if the searchParameters included a house name / number then any matching records
     * will be sorted to the beginning of the results.
     * @throws ValidationException when invalid parameters are supplied
     */
    AddressList doAddressSearch(AddressSearchParameters searchParameters)
            throws ValidationException;
}


