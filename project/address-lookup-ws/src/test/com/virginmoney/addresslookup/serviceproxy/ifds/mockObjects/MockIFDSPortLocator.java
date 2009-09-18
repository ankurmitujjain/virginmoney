package com.virginmoney.addresslookup.serviceproxy.ifds.mockObjects;

import javax.xml.rpc.ServiceException;
import org.apache.log4j.Logger;
import uk.co.ifdsgroup.onlineservices.messages.soap.OnlineServicesSoapHttpPort;
import com.virginmoney.addresslookup.serviceproxy.ifds.IFDSPortLocator;

/**
 * mock port locator for testing the IFDS Service proxy
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class MockIFDSPortLocator implements IFDSPortLocator {

    private static Logger logger = Logger.getLogger(MockIFDSPortLocator.class);

    public OnlineServicesSoapHttpPort getWebServicePort() throws ServiceException {
        return new MockIFDSSoap();
    }
}

