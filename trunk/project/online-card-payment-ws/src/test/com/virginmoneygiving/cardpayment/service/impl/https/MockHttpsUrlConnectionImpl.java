package com.virginmoneygiving.cardpayment.service.impl.https;

import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.log4j.Logger;

/**
 * Custom url connection impl for unit testing.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class MockHttpsUrlConnectionImpl extends HttpsURLConnection {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(MockHttpsUrlConnectionImpl.class);
    
    /** Whether to succeed or not. */
    private boolean success;

    /**
     * Default constructor.
     * 
     * @param url the url
     * @param succeed the succeed
     */
    public MockHttpsUrlConnectionImpl(URL url, boolean succeed) {
        super(url);
        this.success = succeed;
        LOGGER.info("Mock HttpsUrlConnection - will succeed: " + succeed);
    }

    /** {@inheritDoc} */
    @Override
    public int getResponseCode() throws IOException {
        if (success) {
            LOGGER.debug("returning " + HTTP_OK);
            return HTTP_OK; 
        } else {
            LOGGER.debug("returning " + HTTP_INTERNAL_ERROR);
            return HTTP_INTERNAL_ERROR;
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getCipherSuite() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Certificate[] getLocalCertificates() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Certificate[] getServerCertificates()
            throws SSLPeerUnverifiedException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void disconnect() {
        // Do nothing
    }

    /** {@inheritDoc} */
    @Override
    public boolean usingProxy() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void connect() throws IOException {
        // Do nothing
    }

}
