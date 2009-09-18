package com.virginmoneygiving.cardpayment.helper;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;

/**
 * Test parts of OnlineCardPaymentDataMappingHelper that other tests don't
 * reach.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestOnlineCardPaymentDataMappingHelper {

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Tear down.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.helper.OnlineCardPaymentDataMappingHelper#mapToNVPList(java.util.Map)}.
     */
    @Test
    public void testMapToNVPList() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        
        List<ServiceNameValuePair> list = OnlineCardPaymentDataMappingHelper
            .mapToNVPList(map);
        
        assertNotNull(list);
        assertEquals(3, list.size());
    }

}
