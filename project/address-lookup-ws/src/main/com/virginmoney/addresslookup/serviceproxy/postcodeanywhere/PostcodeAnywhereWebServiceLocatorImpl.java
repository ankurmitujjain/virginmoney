package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.serviceproxy.AbstractWebServiceLocator;
import com.virginmoney.addresslookup.serviceproxy.postcodeanywhere.handlers.PostcodeAnywhereAddressSearchHandler;
import com.virginmoney.addresslookup.exception.ServiceLocationException;
import com.virginmoney.addresslookup.resources.AddressConstants;
import com.virginmoney.addresslookup.util.TimingUtil;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUK;
import uk.co.postcodeanywhere.services.simplelookup.messages.SimpleLookupUKSoap;

/**
 * Obtains an instance of the PostCode anywhere Web Service and Ports.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class PostcodeAnywhereWebServiceLocatorImpl extends AbstractWebServiceLocator
        implements PostcodeAnywhereWebServiceLocator
{

    private static Logger logger = Logger.getLogger(PostcodeAnywhereWebServiceLocatorImpl.class);


    /**
     * Returns an instance of the Postcode Anywhere web service.
     * @return an instance of the Postcode Anywhere web service.
     * @throws ServiceLocationException if the service cannot be located.
     */
    public SimpleLookupUK getPostcodeAnywhereService()  throws ServiceLocationException {

        long startTime = System.currentTimeMillis();
        SimpleLookupUK postcodeanywhere = (SimpleLookupUK)this.getWebService("postcodeAnywhere");
        long finishTime = System.currentTimeMillis();
        logger.debug("Service proxy established connection : elapsed=" +
                TimingUtil.formatElapsedSeconds(startTime,finishTime));

        return postcodeanywhere;
    }

    /**
     * Returns an instance of the Postcode Anywhere SOAP port.
     * @return an instance of the Postcode Anywhere SOAP port.
     * @throws ServiceLocationException if the service cannot be located.
     */
    public SimpleLookupUKSoap getPostcodeAnywherePort()   throws ServiceLocationException {
        SimpleLookupUK postcodeanywhere = getPostcodeAnywhereService();
        long startTime = System.currentTimeMillis();
        SimpleLookupUKSoap postcodeanywhereSoap = postcodeanywhere.getSimpleLookupUKSoap();
        long finishTime = System.currentTimeMillis();
        logger.debug("Service proxy established soap       : elapsed=" +
                TimingUtil.formatElapsedSeconds(startTime,finishTime));

        return postcodeanywhereSoap;

    }





}
