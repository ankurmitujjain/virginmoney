package com.virginmoneygiving.cardpayment.service;

import com.virginmoneygiving.cardpayment.service.exception.EndpointUnavailableException;

/**
 * Check whether an HTTP(S) endpoint is available (200 status). Implementations
 * may choose to cache the information. SSL/TLS certificate authorities are
 * taken from the standard truststore (jre/lib/security/cacerts).
 * 
 * @author Robin Bramley, Opsera Ltd
 */
public interface EndpointProbeService {

    /**
     * Check whether the endpoint is available.
     * 
     * @param url the url
     * 
     * @return true if status was OK
     */
    public boolean checkEndpoint(String url);

    /**
     * Test the primary url, if unavailable test the secondary url.
     * 
     * @param primaryUrl the primary url
     * @param secondaryUrl the secondary url
     * 
     * @return The name of the selected Url
     * 
     * @throws EndpointUnavailableException unchecked exception thrown if
     * both urls are unavailable
     */
    public String chooseUrl(String primaryUrl, String secondaryUrl);
}
