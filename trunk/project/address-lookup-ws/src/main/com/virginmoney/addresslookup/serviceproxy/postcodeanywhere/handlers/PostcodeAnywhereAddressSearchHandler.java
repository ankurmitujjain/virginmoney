package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.handlers;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.business.*;
import com.virginmoney.addresslookup.util.TimingUtil;
import com.virginmoney.addresslookup.util.ServiceUtil;
import com.virginmoney.addresslookup.serviceproxy.ServiceProxyException;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUKSoap;
import uk.co.postcodeanywhere.services.simplelookup.messages.ArrayOfAddress;
import net.sf.dozer.util.mapping.MapperIF;

/**
 * Handles a an address search via the PostcodeAnywhere service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class PostcodeAnywhereAddressSearchHandler {

    private static Logger logger = Logger.getLogger(PostcodeAnywhereAddressSearchHandler.class);

    /**
     * Performs an address search.
     * @param searchParameters defines the search criteria.
     * @return  a list of addresses matching the search criteria.
     * @throws com.virginmoney.addresslookup.serviceproxy.ServiceProxyException thrown in the event of an internal error.
     */
    public AddressList doPostcodeSearch(SimpleLookupUKSoap postcodeanywhereSoap, AddressSearchParameters searchParameters,
                                        MapperIF mapper) throws ServiceProxyException{
        // the postcode anywhere account details should be defined as a system property
        // values as at January 2008 for the TEST account are
        // postcodeAnywhere.accountCode=VIRGI11117
        // postcodeAnywhere.licenseKey=CH97-ZW47-JN11-MX65

        AddressSearchParameters.ServiceProviders serviceProvider =  searchParameters.getServiceProvider();
        if (serviceProvider == null) {
            logger.error("no service provider found in search parameters : " + searchParameters);
            throw new ServiceProxyException("no service provider found in search parameters : " + searchParameters);
        }


        String accountcode = System.getProperty(serviceProvider.getAccountCodePropertyName());
        String licensekey = System.getProperty(serviceProvider.getLicenseKeyPropertyName());
        logger.trace("doPostcodeSearch() accountcode property=\"" + serviceProvider.getAccountCodePropertyName() +
                "\" value=\"" + accountcode +
                "\" licencekey property=\"" + serviceProvider.getLicenseKeyPropertyName() + "\" value=\"" + licensekey + "\"");

        if (StringUtils.isBlank(accountcode)) {
            logger.error("PostcodeAnywhere account code not found - system property name=\"" + serviceProvider.getAccountCodePropertyName() + "\"");
            throw new ServiceProxyException("PostcodeAnywhere account code not found - system property name=\"" + serviceProvider.getAccountCodePropertyName() + "\"");
        }

        if (StringUtils.isBlank(licensekey)) {
            logger.error("PostcodeAnywhere license key not found - system property name=\"" + serviceProvider.getLicenseKeyPropertyName() + "\"");
            throw new ServiceProxyException("PostcodeAnywhere license key not found - system property name=\"" + serviceProvider.getLicenseKeyPropertyName() + "\"");
        }

        ArrayOfAddress results = null;
        try {
//            logger.debug("Service proxy started address search");
            long startTime = System.currentTimeMillis();

            results = postcodeanywhereSoap.fastAddress2(searchParameters.getSearchPostcode(),
                    // String postcode      - the postcode to search for
                    null,
                    // String building      - always blank so we get all the results and perform our own matching
                    "enLanguageEnglish",
                    // String language      - in english
                    "raw",
                    // String style         - this gives us a fully expanded address
                    "all_results",
                    // String options       - return the properties as well as the road details
                    accountcode,
                    // String accountCode   - our account code with postcodeanywhere
                    licensekey,
                    // String licenseKey    - our licence key  with postcodeanywhere
                    ""
                    // String machineId     - we dont use this because we our licencing model doesnt require it
            );

            long finishTime = System.currentTimeMillis();
            int resultSize = (results != null && results.getAddress() != null ? results.getAddress().size() : 0);
            logger.info("PostcodeAnywhere Service proxy finished search       : elapsed=" +
                    TimingUtil.formatElapsedSeconds(startTime,finishTime) + " result count=" + resultSize  +
                    " parameters=" + searchParameters);

        }
        catch (Exception e) {
            logger.debug("Caught exception after calling fastAddress for parameters="  + searchParameters + " : " + e);

            //Certain errors from postcode anywhere are not actually errors
            if (e.toString().contains("Please supply a complete postcode") ||
                    e.toString().contains("Please supply the ID for the address")) {
                logger.debug("ignoring this error and returning empty results");
                // now return blank results
                return new AddressList(searchParameters);
            }
            else {
                // rethrow the exception
                logger.error("Caught exception after calling fastAddress for parameters="  + searchParameters + " : " + e,e);
                throw new ServiceProxyException("Caught exception after calling fastAddress2 for parameters=" + searchParameters + " : " + e,e);
            }

        }

        if (logger.isTraceEnabled()) {
            logger.trace("doPostcodeSearch() results size= " + (results == null || results.getAddress() == null ? "null" : results.getAddress().size()) + " for parameters="  + searchParameters);
            if(results != null ) logger.trace("doPostcodeSearch() results =\n" +ServiceUtil.messageToString(results,true));
        }
        return unpackResults(results,searchParameters,mapper);
    }

    /**
     * Unpacks a set of address results from PostcodeAnywhere into VM UKAddress objects.
     *
     * @param results An array of address from PostcodeAnywhere
     */

    private AddressList unpackResults(ArrayOfAddress results,
                                      AddressSearchParameters searchParameters,
                                        MapperIF mapper) {
        AddressList addressList = new AddressList(searchParameters);
        List<AbstractAddress> searchResults = addressList.getAddresses();

        if(results != null) {
            for (uk.co.postcodeanywhere.services.simplelookup.messages.Address postcodeAnywhereAddress : results.getAddress()) {

                PAFData pafData = new PAFData();
                mapper.map(postcodeAnywhereAddress, pafData);
    //            Address address = new Address(pafData);
                AbstractAddress address = PAFDataFormatter.formatAddress(searchParameters,pafData);

                searchResults.add(address);
    //            logger.trace("    doPostcodeSearch() address=>" + address);
            }
        }
        return addressList;

    }

}

