package com.virginmoneygiving.cardpayment.transport;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.log4j.Logger;

/**
 * This class is used for testing TLG failover without access to wsp2.
 * wsp2 is aliased to wsp1 - therefore we need to prevent SSL hostname
 * verification from spoiling the party.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class DummyHostnameVerifier implements HostnameVerifier {

    /** Logger instance. */
    protected static final Logger LOGGER = Logger.getLogger(DummyHostnameVerifier.class);

    /** whether to use this dummy hostname verifier. */
    private final boolean install;

    /** System default HostnameVerifier - obtained by register method. */
    private HostnameVerifier defaultHostnameVerifier = null;

    /**
     * Default constructor.
     * 
     * @param install - whether to make this the default hostname verifier
     */
    public DummyHostnameVerifier(boolean install) {
        this.install = install;
    }

    /**
     * Does not verify hostnames - always returns true.
     * 
     * @param hostname to verify
     * @param sess SSL session
     * 
     * @return true - always
     */
    public boolean verify(String hostname, SSLSession sess) {
    	if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Trusting hostname = "  + hostname);
    	}
		return true;
    }

    /**
     * Register the dummy hostname verifier (obtain default one first).
     */
    @PostConstruct
    public void register() {
		if (install) {
            LOGGER.warn("SSL Hostname verification is disabled - not for production use!");
            // obtain existing default HostnameVerifier
            defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            HttpsURLConnection.setDefaultHostnameVerifier(this);
        }
	}

    /**
     * Unregister the dummy hostname verifier (and reinstate the default).
     */
    @PreDestroy
    public void unregister() {
		if (install) {
            LOGGER.info("Reinstating default SSL Hostname verifier...");
            HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
        }
	}
}
