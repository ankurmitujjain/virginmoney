package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere;

import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.exception.ServiceLocationException;
import com.virginmoney.addresslookup.serviceproxy.AddressSearchServiceProxy;
import com.virginmoney.addresslookup.serviceproxy.ServiceProxyException;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.handlers.PostcodeAnywhereAddressSearchHandler;
import net.sf.dozer.util.mapping.MapperIF;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUKSoap;

/**
 * Service Proxy implementation  for the Postcode Anywhere service.
 */

public class PostcodeAnywhereServiceProxyImpl implements AddressSearchServiceProxy {

    Logger logger = Logger.getLogger(PostcodeAnywhereServiceProxyImpl.class);


    // dozer mapper - set by Spring
    private MapperIF mapper;

    // web service locator - set by spring
    private PostcodeAnywhereWebServiceLocator webServiceLocator = new PostcodeAnywhereWebServiceLocatorImpl();

    // address search handler, for now this will never change so isnt spring'ed.
    PostcodeAnywhereAddressSearchHandler addressSearchHandler = new PostcodeAnywhereAddressSearchHandler();

    /**
     * Gets the Soap Port from the service locator
     * @return
     * @throws ServiceProxyException
     */
    private SimpleLookupUKSoap getSoapPort() throws ServiceProxyException {
        SimpleLookupUKSoap postcodeanywhereSoap;
        try {
            postcodeanywhereSoap = webServiceLocator.getPostcodeAnywherePort();
        }
        catch (ServiceLocationException e) {
            logger.error("doPostcodeSearch() caught ServiceLocationException when obtaining port: " + e,e);
            throw new ServiceProxyException("doPostcodeSearch() caught ServiceLocationException when obtaining port ",e);
        }
        return postcodeanywhereSoap;
    }

    /**
     * Performs a UK postcode search and returns a list of matching addresses.
     *
     * @param addressSearchParameters the postcode to search for building number or organisation name will be returned
     *                                at the top of the list
     */
    public AddressList doPostcodeSearch(AddressSearchParameters addressSearchParameters)
            throws ServiceProxyException
    {

        logger.debug("doPostcodeSearch() searching for postcode=\"" + addressSearchParameters.getSearchPostcode() +
                "\" with filterText  =\"" + addressSearchParameters
                .getSearchBuilding() + "\" using proxyHost=\"" + System.getProperty("http.proxyHost") +
                ":" + System.getProperty("http.proxyPort") + "\"");

        SimpleLookupUKSoap postcodeanywhereSoap = getSoapPort();

        return addressSearchHandler.doPostcodeSearch(postcodeanywhereSoap, addressSearchParameters,mapper);

    }


    /**
     * Sets the web service locator used to access the postcode anywhere service.
     *
     * @param webServiceLocator Value to set for property 'locator'.
     */
    public void setWebServiceLocator(PostcodeAnywhereWebServiceLocator webServiceLocator) {
        this.webServiceLocator = webServiceLocator;
    }


    /**
     * Setter for property 'mapper'.
     *
     * @param mapper Value to set for property 'mapper'.
     */
    public void setMapper(MapperIF mapper) {
        this.mapper = mapper;
    }


}
