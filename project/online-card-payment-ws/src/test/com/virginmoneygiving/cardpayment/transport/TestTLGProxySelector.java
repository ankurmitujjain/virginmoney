package com.virginmoneygiving.cardpayment.transport;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * The Class TestTLGProxySelector.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TestTLGProxySelector {

    /** Unit under test to use proxies. */
    private TLGProxySelector bean = new TLGProxySelector(true);

    /** The mock default proxy selector. */
    @Mock
    private ProxySelector mockDefaultProxySelector;

    /** The real default proxy selector. */
    private ProxySelector realDefaultProxySelector = ProxySelector.getDefault();

    /** The Constant PRIMARY_TLG_HOSTNAME. */
    private static final String PRIMARY_TLG_HOSTNAME = "wsp1.secure-payment-processing.com";
    
    /** The Constant SECONDARY_TLG_HOSTNAME. */
    private static final String SECONDARY_TLG_HOSTNAME = "wsp2.secure-payment-processing.com";

    /** The Constant PRIMARY_PROXY_HOSTNAME. */
    private static final String PRIMARY_PROXY_HOSTNAME = "localhost";
    
    /** The Constant SECONDARY_PROXY_HOSTNAME. */
    private static final String SECONDARY_PROXY_HOSTNAME = "localhost2";

    /**
     * Initial Setup.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // set the mock so we can get the behaviour we want
        ProxySelector.setDefault(mockDefaultProxySelector);
        
        bean.setPrimaryProxyHost(PRIMARY_PROXY_HOSTNAME);
        bean.setPrimaryTlgHostname(PRIMARY_TLG_HOSTNAME);
        bean.setSecondaryProxyHost(SECONDARY_PROXY_HOSTNAME);
        bean.setSecondaryTlgHostname(SECONDARY_TLG_HOSTNAME);
    }

    /**
     * Tear down.
     * 
     * @throws java.lang.Exception      * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        ProxySelector.setDefault(realDefaultProxySelector);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#register()}.
     */
    @Test
    public void testRegister() {
        bean.register();
        
        assertTrue(ProxySelector.getDefault() instanceof TLGProxySelector);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#register()}.
     */
    @Test
    public void testRegisterWithNulls() {
        bean.setPrimaryProxyHost(null);
        bean.setSecondaryProxyHost(null);
        try {
            bean.register();
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#unregister()}.
     */
    @Test
    public void testUnregister() {
        bean.register();
        assertTrue(ProxySelector.getDefault() instanceof TLGProxySelector);
        bean.unregister();
        assertFalse(ProxySelector.getDefault() instanceof TLGProxySelector);
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#connectFailed(java.net.URI, java.net.SocketAddress, java.io.IOException)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testConnectFailedURISocketAddressIOException() throws Exception {
        URI uri = new URI("http://www.google.co.uk");
        SocketAddress socketaddress = new InetSocketAddress("localhost", 8080);

        // test with different null permutations
        try {
            bean.connectFailed(null, socketaddress, new IOException());
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }

        try {
            bean.connectFailed(uri, null, new IOException());
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }

        try {
            bean.connectFailed(uri, socketaddress, null);
            fail("exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }

        // test with all args
        bean.connectFailed(uri, socketaddress, new IOException("Oops"));
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#select(java.net.URI)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testSelectURIForPrimary() throws Exception {
        // run test
        bean.register();
        List<Proxy> list = bean.select(new URI("https://" + PRIMARY_TLG_HOSTNAME + "/foo?wsdl"));

        // assertions
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(Proxy.Type.HTTP, list.get(0).type());        
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#select(java.net.URI)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testSelectURIForSecondary() throws Exception {
        // run test
        bean.register();
        List<Proxy> list = bean.select(new URI("https://" + SECONDARY_TLG_HOSTNAME + "/foo?wsdl"));

        // assertions
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(Proxy.Type.HTTP, list.get(0).type());        
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#select(java.net.URI)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testSelectURIWithNull() throws Exception {
        // run test
        bean.register();
        try {
            bean.select(null);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#select(java.net.URI)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testHttpsSelectNonTLGURINoFallback() throws Exception {
        // prepare
        ProxySelector.setDefault(null);

        // run test
        bean.register();
        List<Proxy> list = bean.select(new URI("https://www.google.co.uk"));

        // assertions
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(Proxy.Type.DIRECT, list.get(0).type());
    }

    /**
     * Test method for {@link com.virginmoneygiving.cardpayment.transport.TLGProxySelector#select(java.net.URI)}.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testHttpSelectNonTLGURINoFallback() throws Exception {
        // prepare
        ProxySelector.setDefault(null);

        // run test
        bean.register();
        List<Proxy> list = bean.select(new URI("http://www.google.co.uk"));

        // assertions
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(Proxy.Type.DIRECT, list.get(0).type());
    }

}
