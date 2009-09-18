package com.virginmoney.addresslookup.serviceproxy.ifds.builders;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import com.virginmoney.addresslookup.business.AddressSearchParameters;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.GetAddressReq;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.Header;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.ObjectFactory;
import uk.co.ifdsgroup.onlineservices.messages.getAddressReq.Request;

/**
 * Creates an object to be used in the call to the IFDS Address search.
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class GetAddressReqBuilder {

    private static Logger logger = Logger.getLogger(GetAddressReqBuilder.class);

    private static ObjectFactory objectFactory = new ObjectFactory();

    /**
     *  Creates an object to be used in the call to the IFDS Address search.
     * @param addressSearchParameters search parameters
     * @return a message object containing the details to pass to IFDS.
     */
    public static GetAddressReq build(AddressSearchParameters addressSearchParameters) {

        logger.trace("build() creating request for  for postcode=\"" + addressSearchParameters.getSearchPostcode() +
                    "\"");

        GetAddressReq getAddressReq = objectFactory.createGetAddressReq();
        getAddressReq.setHeader(createHeader());
        getAddressReq.setRequest(createRequest(addressSearchParameters));

        logger.trace("build() created " + getAddressReq + " for postcode " + getAddressReq.getRequest().getPostCode());

        return getAddressReq;
    }

    /**
     * Creates the Header item for the request
     *
     * @return a Header.
     */
    private static Header createHeader() {
        Header header = objectFactory.createHeader();
        IFDSHeaderPopulator.mapHeader(header);
        return header;
    }

    /**
     * Creates the Request item for the request
     *
     * @return a Request.
     */
    private static Request createRequest(AddressSearchParameters addressSearchParameters) {
        Request request = objectFactory.createRequest();
        request.setPostCode(addressSearchParameters.getSearchPostcode());

        // If there is a search building, and the request is for only matching results to be returned
        // then set the search building into the request, otherwise leave it blank so that all results are
        // returned
        if (addressSearchParameters.returnOnlyMatchingResults() && StringUtils.isNotBlank(addressSearchParameters.getSearchBuilding())) {
           request.setAddressLine(addressSearchParameters.getSearchBuilding());
        }
        else
        {
            request.setAddressLine("");
        }

        return request;
    }

}

