package com.virginmoney.addresslookup.serviceproxy.ifds;

import javax.xml.rpc.ServiceException;
import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.serviceproxy.AddressSearchServiceProxy;
import com.virginmoney.addresslookup.serviceproxy.ServiceProxyException;
import com.virginmoney.addresslookup.serviceproxy.ifds.handlers.IFDSAddressSearchHandler;
import net.sf.dozer.util.mapping.MapperIF;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttpPort;

/**
 * Service Proxy implementation for IFDS.
 * Copyright     :  Virgin Money Ltd.
 */
public class IFDSServiceProxyImpl implements AddressSearchServiceProxy {

    private static Logger logger = Logger.getLogger(IFDSServiceProxyImpl.class);

    // IFDSAddressSearchHandler to handle the marshalling and unmarshalling of the call to IFDS
    IFDSAddressSearchHandler ifdsAddressSearchHandler = new IFDSAddressSearchHandler();

    // dozer mapper - set by Spring
    private MapperIF mapper;

    // web service locator - set by spring
    private IFDSPortLocator IFDSPortLocator = new IFDSPortLocatorImpl();

    public void setMapper(MapperIF mapper) {
        this.mapper = mapper;
    }

    public void setIFDSPortLocator(IFDSPortLocator IFDSPortLocator) {
        this.IFDSPortLocator = IFDSPortLocator;
    }

    public AddressList doPostcodeSearch(AddressSearchParameters addressSearchParameters)
            throws ServiceProxyException
    {
        logger.debug("doPostcodeSearch() searching for postcode=\"" + addressSearchParameters.getSearchPostcode() +
                "\" with filterText  =\"" + addressSearchParameters
                .getSearchBuilding() + "\" using proxyHost=\"" + System.getProperty("http.proxyHost") +
                ":" + System.getProperty("http.proxyPort") + "\"");

        OnlineServicesSoapHttpPort port;
        try {
            port = IFDSPortLocator.getWebServicePort();
        }
        catch (ServiceException e) {
            logger.error("doPostcodeSearch() caught ServiceLocationException when obtaining port: " + e,e);
            throw new ServiceProxyException("doPostcodeSearch() caught ServiceLocationException when obtaining port ",e);
        }

        return ifdsAddressSearchHandler.doPostcodeSearch(port,addressSearchParameters);
    }


}

