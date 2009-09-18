package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.mockObjects;

import org.apache.log4j.Logger;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUK;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUKSoap;
import com.virginmoney.addresslookup.exception.ServiceLocationException;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.PostcodeAnywhereWebServiceLocator;

/**
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class MockPostcodeAnywhereWebServiceLocator implements PostcodeAnywhereWebServiceLocator {

    private static Logger logger = Logger.getLogger(MockPostcodeAnywhereWebServiceLocator.class);

    public SimpleLookupUK getPostcodeAnywhereService() throws ServiceLocationException {
        return null; //This CANNOT return a service because we cant (yet...) create a mock Service object
    }

    public SimpleLookupUKSoap getPostcodeAnywherePort() throws ServiceLocationException {
        return new MockPostcodeAnywhereSoap();
    }

}

