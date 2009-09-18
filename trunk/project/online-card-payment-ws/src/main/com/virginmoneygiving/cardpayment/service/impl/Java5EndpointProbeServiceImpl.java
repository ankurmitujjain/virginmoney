package com.virginmoneygiving.cardpayment.service.impl;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.virginmoneygiving.cardpayment.service.EndpointProbeService;

/**
 * Simple implementation of the Endpoint Probe checkEndpoint using J2SE
 * HttpsUrlConnection and (<i>implicitly</i>) Java 5 ProxySelector. <br/><b>For
 * probing https endpoints only</b>.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class Java5EndpointProbeServiceImpl extends AbstractEndpointProbeService
        implements EndpointProbeService {

    /**
     * Default constructor.
     */
    public Java5EndpointProbeServiceImpl() {
        LOGGER = Logger.getLogger(Java5EndpointProbeServiceImpl.class);
    }

    /** (@inheritDoc) */
    public boolean checkEndpoint(String url) {
        // can't probe an empty URL
        if (StringUtils.isEmpty(url)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Endpoint probe asked to check an empty url");
            }
            return false;
        }

        // log the url we're probing (allows crude timings)
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Probing " + url);
        }

        // working variables
        HttpsURLConnection conn = null;
        int statusCode = 0;

        // set up the connection
        URL targetUrl = null;
        try {
            targetUrl = new URL(url);

            conn = (HttpsURLConnection) targetUrl.openConnection();
            conn.connect();
            // get the status code

            // get the status code
            statusCode = conn.getResponseCode();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Probe response (" + url + "): " + statusCode);
            }
        } catch (Exception e) {
            LOGGER.error("Endpoint probe received (probing " + url + ") "
                    + e.getClass().getSimpleName() + " : " + e.getMessage());
            LOGGER.info("Endpoint probe error detail: ", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return (statusCode == HttpsURLConnection.HTTP_OK);
    }
}
