package com.virginmoney.addresslookup.serviceproxy;

import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.exception.ServiceLocationException;

/**
 * Defines the intrface required for the Service proxy calls to the service providers when performing an address search.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */

public interface AddressSearchServiceProxy {
    /**
     * Performs an address search.
     * @param addressSearchParameters defines the search criteria.
     * @return  a list of addresses matching the search criteria.
     * @throws ServiceProxyException thrown in the event of an internal error.
     */

    AddressList doPostcodeSearch(AddressSearchParameters addressSearchParameters)
            throws ServiceProxyException;
}


