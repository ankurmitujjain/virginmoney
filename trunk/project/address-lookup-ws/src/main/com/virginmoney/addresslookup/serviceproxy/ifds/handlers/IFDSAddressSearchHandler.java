package com.virginmoney.addresslookup.serviceproxy.ifds.handlers;

import java.util.List;
import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import com.virginmoney.addresslookup.business.AddressList;
import com.virginmoney.addresslookup.business.AddressStyle1;
import com.virginmoney.addresslookup.business.AbstractAddress;
import com.virginmoney.addresslookup.util.MarshalUtil;
import com.virginmoney.addresslookup.util.TimingUtil;
import com.virginmoney.addresslookup.serviceproxy.ifds.builders.GetAddressReqBuilder;
import com.virginmoney.ifds.utils.WebServiceLogging;
import com.virginmoney.addresslookup.serviceproxy.ServiceProxyException;
import com.virginmoney.addresslookup.resources.AddressConstants;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.GetAddressReq;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttpPort;
import uk.co.ifdsgroup.onlineservices.messages.getAddressRes.*;

/**
 * Handles the an address search request via the IFDS service.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class IFDSAddressSearchHandler {

    private static Logger logger = Logger.getLogger(IFDSAddressSearchHandler.class);
    private static String REQUEST_SCHEMA_NAME = "getAddress_Req.xsd";
    private static String CLASSNAME = "IFDSAddressSearchHandler";

    /**
     * Performs an address search.
     * @param searchParameters defines the search criteria.
     * @return  a list of addresses matching the search criteria.
     * @throws ServiceProxyException thrown in the event of an internal error.
     */
public AddressList doPostcodeSearch(OnlineServicesSoapHttpPort port,AddressSearchParameters searchParameters) throws ServiceProxyException{
    logger.trace("doPostcodeSearch() started for " + searchParameters);

    GetAddressRes getAddressRes = callIFDS(searchParameters, port);

    if (checkForErrors(getAddressRes))
        return new AddressList(searchParameters);


    return  unpackResults(searchParameters,getAddressRes);
}

    /**
     * Handles the call to IFDS and unmarshals the response
      * @param searchParameters
     * @param port
     * @return
     * @throws ServiceProxyException
     */
    private GetAddressRes callIFDS(AddressSearchParameters searchParameters, OnlineServicesSoapHttpPort port)
            throws ServiceProxyException
    {
        String requestXML = null;
        String responseXML = null;
        GetAddressRes getAddressRes = null;
        try {
            GetAddressReq requestObj = GetAddressReqBuilder.build(searchParameters);
            requestXML = MarshalUtil.marshalAndValidate(requestObj, AddressConstants.IFDS_SCHEMA_LOCATION + REQUEST_SCHEMA_NAME);

            long startTime = System.currentTimeMillis();
            responseXML = port.getAddress(requestXML);
            long finishTime = System.currentTimeMillis();

            logger.debug("IFDS Service proxy finished search       : elapsed=" +
                    TimingUtil.formatElapsedSeconds(startTime,finishTime)  +
                    " parameters=" + searchParameters);

            logger.trace("callIFDS() got responseXML=\n" + responseXML);

            // now log the call via the specialised class to write to the general IFDS call timing log
            WebServiceLogging.logWebServiceCall(logger,requestObj,startTime,finishTime,responseXML,CLASSNAME);

            getAddressRes = (GetAddressRes) MarshalUtil.unmarshal(responseXML, GetAddressRes.class);
            logger.trace("callIFDS() got getAddressRes=" + getAddressRes);
        }
        catch (Exception e) {
            logger.error("Unexpected Exception caught in Address Service when performing IFDS getAddress for parameters=" + searchParameters + " : " + e,e);
            logger.error("    getAddress request=\n" + requestXML);
            logger.error("    getAddress response=\n" + responseXML);
            throw new ServiceProxyException("Unexpected Exception caught in Address Service when performing IFDS getAddress for parameters=" + searchParameters + " : " + e,e);
        }
        return getAddressRes;
    }

    /**
     * Checks for errors in the response
     * @param responseObj responseObj An array of address from IFDS.
     * @return true if an error was found
     */
    private boolean checkForErrors(GetAddressRes responseObj) {

        uk.co.ifdsgroup.onlineservices.messages.getAddressRes.Error errorPart = responseObj.getError();
        if (!errorPart.isSuccess()) {
            logger.debug("doPostcodeSearch() IFDS error occurred  : "  );
            for (uk.co.ifdsgroup.onlineservices.messages.getAddressRes.Error.AnError anError  : errorPart.getAnError())  {
                logger.debug("     code=" + anError.getErrorCode() + " text=" + anError.getErrorText());
            }
            return true;
        }
        return false;
    }

    /**
     * Unpacks a set of address getAddressRes from IFDS into VM UKAddress objects.
     *
     * @param getAddressRes An array of address from IFDS.
     * @return the addresses as a Business object.
     */

    private AddressList unpackResults(AddressSearchParameters searchParameters,GetAddressRes getAddressRes) {
        AddressList businessAddressList = new AddressList(searchParameters);
        List<AbstractAddress> businessAddresses =  businessAddressList.getAddresses();


        // The IFDS Address search results structure is fairly complex so is worthy of explanation here before
        // attempting to read the code which follows :-
        //
        //         GetAddressRes                       = the top-level response object
        //              ...Response                          = the object which cotains the actual response details
        //                  ...Addresses                          = contains the list of addresses
        //                      ...(List of) AddressElements          =  contains a single address and its kind(type)
        //                          ... Address                            = an address wrapper
        //                              ... Lines                              = the address lines
        //                                  ... (List of) LineType                  = the address lines
        //                                     ... AddressLine                          = a single address line
        //                              ... Postcode                           = the address postcode
        //
        //  Clearer than a clear thing on a clear day in ClearLand .......
        //

        // get the Addresses object
        GetAddressRes.Response.Addresses responseAddresses = getAddressRes.getResponse().getAddresses();
        if (responseAddresses == null ) {
            logger.debug("No responseAddresses found");
            return businessAddressList;
        }

        // loop round each AddressElements object and extract its details
        for (GetAddressRes.Response.Addresses.AddressElements responseAddressElement : responseAddresses.getAddressElements()) {

            List<LineType> responseAddressLines = responseAddressElement.getAddress().getLines().getLine();

            // The IFDS address lookup always results in the default address layout
            AddressStyle1 businessAddress = new AddressStyle1(searchParameters,
                                                  responseAddressLines.get(0).getAddressLine(),
                                                  responseAddressLines.get(1).getAddressLine(),
                                                  responseAddressLines.get(2).getAddressLine(),
                                                  responseAddressLines.get(3).getAddressLine(),
                                                  responseAddressElement.getAddress().getPostCode());

            businessAddresses.add(businessAddress);
            logger.trace("    doPostcodeSearch() responseAddressElement=>" + businessAddress);
        }

        return businessAddressList;

    }

}

