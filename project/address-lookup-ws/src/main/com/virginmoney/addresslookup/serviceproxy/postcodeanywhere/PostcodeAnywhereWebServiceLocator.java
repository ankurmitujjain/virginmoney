package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere;

import javax.xml.namespace.QName;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUK;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUKSoap;
import com.virginmoney.addresslookup.exception.ServiceLocationException;

/**
 * Locates an instance of the Postcode Anywhere service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */

public interface PostcodeAnywhereWebServiceLocator {
    /**
     * Locates an instance of the Postcode Anywhere service.
     * @return an instance of the Postcode Anywhere service.
     * @throws ServiceLocationException
     */
    SimpleLookupUK getPostcodeAnywhereService()  throws ServiceLocationException;

    /**
     * Locates an instance of the Postcode Anywhere SOAP port.
      * @return an instance of the Postcode Anywhere SOAP port.
     * @throws ServiceLocationException
     */
    SimpleLookupUKSoap getPostcodeAnywherePort()   throws ServiceLocationException;

}


