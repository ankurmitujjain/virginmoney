package com.virginmoney.addresslookup.business;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * Represents list of {@link com.virginmoney.addresslookup.business.AbstractAddress} objects.
 *
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class AddressList {

    private static Logger logger = Logger.getLogger(AddressList.class);

    private AddressSearchParameters searchParameters;
    private List<AbstractAddress> addresses;

    public AddressList(AddressSearchParameters searchParameters) {
          this.searchParameters = searchParameters;
    }

    /**
     * Return the list of addresses - note that there is NO setter because it is possible to use the getter to obtain
     * the list and then modify it.
     *
     * @return
     */
    public List<AbstractAddress> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<AbstractAddress>();
        }
        sortAndFilterAddresses();
        return addresses;
    }

    /**
     * Sorts and filters the list of addresses based on the search parameters supplied when creating the instance of
     * this class.
     *
     * If the 'sort matching addresses' flag has been set and there is a search building, then records which match
     * the search building parameter will be placed at the start of the list .
     *
     * If the 'return only matching addresses' flag has been set and there is a search building, then only records
     * which match the search building parameter will be included in the results.
     *
     */
    public void sortAndFilterAddresses() {


        if (StringUtils.isBlank(searchParameters.getSearchBuilding()) || addresses.size() == 0) {
            return;
        }

        // create a list for each type of match
        List<AbstractAddress> matches = new ArrayList<AbstractAddress>();
        List<AbstractAddress> nonMatches = new ArrayList<AbstractAddress>();

        // loop round each address and assign it to one of the lists
        for (AbstractAddress address : this.addresses) {

            // if the request is to sort and/or only return matching results then seperate them out.
            if ((searchParameters.sortMatchingResults() || searchParameters.returnOnlyMatchingResults()) && address.isMatchesSearchBuilding())  {
                matches.add(address);
            }
            else
            {
                nonMatches.add(address);
            }

        }

        if (logger.isTraceEnabled()){
            logger.trace("sortAndFilterAddresses() finished: return only matching=" +
                    searchParameters.returnOnlyMatchingResults() + " sort=" +
                    searchParameters.sortMatchingResults() +
                    " started with " + addresses.size() +
                    " matches=" + matches.size() +
                    " non-matches=" + nonMatches.size()
            );
        }

        // set the results to be the matches matches followed by the non matches
        addresses = new ArrayList<AbstractAddress>();
        addresses.addAll(matches);

        // if the 'return only matching results' flag is set, then dont add non-matching results
        if (!searchParameters.returnOnlyMatchingResults()) {
            addresses.addAll(nonMatches);
        }

    }

}

