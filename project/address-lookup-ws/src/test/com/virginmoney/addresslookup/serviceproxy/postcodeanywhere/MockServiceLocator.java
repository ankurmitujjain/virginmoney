package com.virginmoney.addresslookup.serviceproxy.postcodeanywhere;

import javax.xml.ws.Service;
import org.apache.log4j.Logger;
import com.virginmoney.addresslookup.serviceproxy.AbstractWebServiceLocator;
import com.virginmoney.addresslookup.exception.ServiceLocationException;

/**
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class MockServiceLocator extends AbstractWebServiceLocator {

    private static Logger logger = Logger.getLogger(MockServiceLocator.class);

    public Service getMock() throws ServiceLocationException {
        return getWebService("nickTest");
    }
}

