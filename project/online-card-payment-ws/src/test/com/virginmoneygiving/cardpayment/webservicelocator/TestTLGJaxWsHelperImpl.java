package com.virginmoneygiving.cardpayment.webservicelocator;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;

/**
 * Test the JaxWs Helper.
 * TODO: mock tests now we're using Spring JaxWsPortProxyFactoryBean
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestTLGJaxWsHelperImpl {
    
    /** unit under test. */
    private TLGJaxWsHelperImpl bean;

    /** The primary host. */
    private String primaryHost = "wsp1.secure-payment-processing.com";
    
    /** The secondary host. */
    private String secondaryHost = "wsp2.secure-payment-processing.com";
    
    /** The basic suffix. */
    private String basicSuffix = "/Payment-0009/PaymentAPI.asmx?WSDL";
    
    /** The extended suffix. */
    private String extendedSuffix = "/Payment-0009/ExtendedPaymentAPI.asmx?WSDL";

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new TLGJaxWsHelperImpl();
        
        Map<String,PaymentAPISoap> basicPortMap = new HashMap<String,PaymentAPISoap>();
        bean.setBasicPortMap(basicPortMap);
        
        Map<String,ExtendedPaymentAPISoap> extendedPortMap = new HashMap<String,ExtendedPaymentAPISoap>();
        bean.setExtendedPortMap(extendedPortMap);
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
     * Test method for {@link com.virginmoneygiving.cardpayment.webservicelocator.TLGJaxWsHelperImpl#getBasicPort()}.
     */
    @Test
    public void testGetBasicPort() {
        /*
         * Commented out as this introduces a buildtime dependency on TLG 
         * server availability.<br/>
         * Cannot mock with mockito due to needing additional BindingProvider 
         * interface.<br/>
         * The test does work as part of the ant build process... It is 
         * also exercised by the SOAP UI tests.<br/>
         */
        //assertNotNull(bean.getBasicPort());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.webservicelocator.TLGJaxWsHelperImpl#getBasicPort()}.
     */
    @Test
    public void testGetBasicPortBadHostname() {
        try {
            bean.getBasicPortByHostname(null); 
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }

        try {
            bean.getBasicPortByHostname("noop"); 
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.webservicelocator.TLGJaxWsHelperImpl#getExtendedPort()}.
     */
    @Test
    public void testGetExtendedPort() {
        /*
         * Commented out as this introduces a buildtime dependency on TLG 
         * server availability.<br/> 
         * Cannot mock with mockito due to needing additional BindingProvider 
         * interface.<br/>
         * The test does work as part of the ant build process... It is 
         * also exercised by the SOAP UI tests.<br/>
         */
        //assertNotNull(bean.getExtendedPort());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.webservicelocator.TLGJaxWsHelperImpl#getExtendedPort()}.
     */
    @Test
    public void testGetExtendedPortBadHostname() {
        try {
            bean.getExtendedPortByHostname(null); 
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }

        try {
            bean.getExtendedPortByHostname("noop"); 
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }
}
