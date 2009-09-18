package com.virginmoney.addresslookup.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.virginmoney.addresslookup.business.AbstractAddress;
import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.exception.ServiceLocationException;
import com.virginmoney.addresslookup.serviceproxy.AddressSearchServiceProxy;
import com.virginmoney.addresslookup.serviceproxy.ServiceProxyException;
import com.virginmoney.addresslookup.serviceproxy.ifds.IFDSServiceProxyImpl;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.PostcodeAnywhereServiceProxyImpl;
import com.virginmoney.addresslookup.validators.ValidationException;

/**
 * Implements an address search for a postcode, with optional house name/number and search provider.
 *
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */

public class AddressSearchServiceImpl implements AddressSearchService {

    private static Logger logger = Logger.getLogger(AddressSearchServiceImpl.class);

    // service proxy for postcode anywhere - populated via Spring
    AddressSearchServiceProxy postcodeAnywhereServiceProxy = new PostcodeAnywhereServiceProxyImpl();

    // service proxy for IFDS - populated via Spring
    AddressSearchServiceProxy IFDSServiceProxy = new IFDSServiceProxyImpl();

    // Spring validator - populated via spring
    private Validator addressSearchValidator;

    /**
     * Sets the service proxy to be used for calls to Postcode Anywhere, at runtime this is intended to be
     * populated via Spring injection.
     *
     * @param postcodeAnywhereServiceProxy the service proxy to be used for calls to Postcode Anywhere.
     */
    public void setPostcodeAnywhereServiceProxy(AddressSearchServiceProxy postcodeAnywhereServiceProxy) {
        this.postcodeAnywhereServiceProxy = postcodeAnywhereServiceProxy;
    }

    /**
     * Sets the service proxy to be used for calls to IFDS, at runtime this is intended to be
     * populated via Spring injection.
     *
     * @param IFDSServiceProxy the service proxy to be used for calls to IFDS.
     */
    public void setIFDSServiceProxy(AddressSearchServiceProxy IFDSServiceProxy) {
        this.IFDSServiceProxy = IFDSServiceProxy;
    }

    /**
     * Sets the Spring Validator class to be used to validate the request search parameters, at runtime this is intended to be
     * populated via Spring injection.
     *
     * @param addressSearchValidator the Spring Validator class to be used to validate the request search parameters.
     */
    public void setAddressSearchValidator(Validator addressSearchValidator) {
        this.addressSearchValidator = addressSearchValidator;
    }

    /** {@inheritDoc} */
    public AddressList doAddressSearch(AddressSearchParameters searchParameters) throws ValidationException {
        logger.trace("doAddressSearch() searching for postcode=" + searchParameters.getSearchPostcode() + " house=" +
                searchParameters.getSearchBuilding() + " provider=" +
                searchParameters.getSearchBuilding());

        Errors errors = new BeanPropertyBindingResult(searchParameters, "AddressSearchParameters");
        addressSearchValidator.validate(searchParameters, errors);
        if (errors.hasErrors()) {
            logger.error("Errors found validating searchParameters : " + searchParameters);
            throw new ValidationException("Errors found validating searchParameters : ", errors);
        }

        AddressList addresses = null;

        // chose the service provider to make the actual call
        switch (searchParameters.getServiceProvider()) {
            case IFDS: {
                addresses = searchUsingIFDS(searchParameters);
                break;
            }
            default: {
                addresses = searchUsingPostcodeAnywhere(searchParameters);
                break;
            }
        }
        
        String addressLine1 = null;
        int addressLineLength = 40;
        for (AbstractAddress address : addresses.getAddresses()){
        	if(address.getAddressLine1()!=null && address.getAddressLine1().length()>addressLineLength){
        		addressLine1 = trimString(address.getAddressLine1(), addressLineLength);
        		address.setAddressLine1(addressLine1);
    		}
        }
        
        return addresses;
    }

    private String trimString(String data, int length) {
    	String trimData = null;
    	if(data!=null && data.length()>length) {
    		trimData = data.substring(0,length);
    	}
    	return trimData;	
    }
    
    private AddressList searchUsingPostcodeAnywhere(AddressSearchParameters searchParameters) {

        AddressList searchResults = null;
        try {
            searchResults = postcodeAnywhereServiceProxy.doPostcodeSearch(searchParameters);
        }
        catch (ServiceProxyException e) {
            logger.error("Caught exception when calling Postcode Anywhere serviceProxy for addressSearchParameters=" + searchParameters);
            logger.error("           exception=" + e, e);
            return null;

        }
        return searchResults;

    }

    private AddressList searchUsingIFDS(AddressSearchParameters searchParameters) {

        AddressList searchResults = null;
        try {
            searchResults = IFDSServiceProxy.doPostcodeSearch(searchParameters);
        }
        catch (ServiceProxyException e) {
            logger.error("Caught exception when calling IFDS serviceProxy for addressSearchParameters=" + searchParameters);
            logger.error("           exception=" + e, e);
            return null;

        }
        return searchResults;

    }

}

