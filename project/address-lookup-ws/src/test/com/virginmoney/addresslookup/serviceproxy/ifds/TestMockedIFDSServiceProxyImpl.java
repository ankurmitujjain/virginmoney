package com.virginmoney.addresslookup.serviceproxy.ifds;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import com.virginmoney.addresslookup.serviceproxy.AbstractTestServiceProxyImpl;
import com.virginmoney.addresslookup.SpringBuilder;
import com.virginmoney.addresslookup.business.AddressSearchParameters;

/**
 * Tests the IFDS Service handler class which processes address search requests, using a Mock service implmentation.
 * Copyright     :  Virgin Money Ltd.
 */
public class TestMockedIFDSServiceProxyImpl extends AbstractTestServiceProxyImpl {

    private static Logger logger = Logger.getLogger(TestMockedIFDSServiceProxyImpl.class);

    protected void getProxy() throws Exception {
        super.serviceProxy = SpringBuilder.getIFDSServiceProxyWithMockServices();
        super.returnPAFData = AddressSearchParameters.ReturnPAFDataRules.noPAFData;
    }



    @Test
    public void Dummy(){
        Assert.assertTrue("we need a dummy test otherwise Junit gets upset",true);
    }

}

