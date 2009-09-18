package com.virginmoneygiving.cardpayment.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A Java 5 ProxySelector implementation for TLG routing (primary uses one
 * proxy, secondary uses another).<br/>
 * Will check the URI for <b>https</b> scheme plus configured hostnames (e.g.
 * <i>wsp1</i> and <i>wsp2</i> .secure-payment-processing.com) and select the
 * appropriate Proxy.<br/>
 * If it is not a TLG Secure Payment Service URI, we delegate to the platform
 * default ProxySelector.<br/>
 * Has a constructor argument to turn it off (e.g. if driven from external
 * properties loaded into a Spring context).
 * <br/><br/>
 * 
 * @see http://java.sun.com/j2se/1.5.0/docs/guide/net/proxies.html
 * @author Robin Bramley, Opsera Ltd.
 */
public class TLGProxySelector extends ProxySelector {
    
    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(TLGProxySelector.class);

    /** System default ProxySelector - obtained by register method. */
    private ProxySelector defaultSelector = null;

    /** Primary Proxy - constructed by register method. */
    private Proxy primaryProxy;

    /** Secondary Proxy - constructed by register method. */
    private Proxy secondaryProxy;

    //+DI
    /** Whether to use proxies (constructor only). */
    private final boolean useProxy;

    /** Primary TLG Secure Payment Service hostname. */
    private String primaryTlgHostname;

    /** Primary proxy server host. */
    private String primaryProxyHost;

    /** Primary proxy server port.<br/> Defaults to 443. */
    private Integer primaryProxyPort = 443;

    /** Secondary TLG Secure Payment Service hostname. */
    private String secondaryTlgHostname;

    /** Secondary proxy server host. */
    private String secondaryProxyHost;

    /** Secondary proxy server port.<br/> Defaults to 443. */
    private Integer secondaryProxyPort = 443;

    /**
     * Default constructor.
     * 
     * @param useProxy whether to install this selector.
     */
    public TLGProxySelector(boolean useProxy) {
        this.useProxy = useProxy;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("TLGProxySelector started - use proxy: " + useProxy);
        }
    }

    /**
     * Register the proxy selector (obtain default one first).
     */
    @PostConstruct
    public void register() {
        if (useProxy) {
            // sanity check properties
            if (StringUtils.isEmpty(primaryProxyHost)
                    || StringUtils.isEmpty(primaryTlgHostname)
                    || StringUtils.isEmpty(secondaryProxyHost)
                    || StringUtils.isEmpty(secondaryTlgHostname)) {
                throw new IllegalArgumentException("Proxy settings required");
            }

            // instantiate Proxy instances
            primaryProxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress(primaryProxyHost, primaryProxyPort));
            secondaryProxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress(secondaryProxyHost, secondaryProxyPort));

            // obtain existing default ProxySelector
            defaultSelector = ProxySelector.getDefault();
            ProxySelector.setDefault(this);
        }
    }

    /**
     * Unregister the proxy selector (and reinstate the default).
     */
    @PreDestroy
    public void unregister() {
        if (useProxy) {
            ProxySelector.setDefault(defaultSelector);
        }
    }

    /** (@inheritDoc) */
    @Override
    public void connectFailed(URI uri, SocketAddress socketaddress,
            IOException ioexception) {
        // Let's stick to the specs.
        if (uri == null || socketaddress == null || ioexception == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        }

        // Log failure
        LOGGER.warn("Could not connect to proxy [" + socketaddress
                + "] whilst trying to reach " + uri,
                ioexception);
    }

    /** (@inheritDoc) */
    @Override
    public List<Proxy> select(URI uri) {
        // Let's stick to the specs.
        if (uri == null) {
            throw new IllegalArgumentException("URI can't be null.");
        }

        // If it's a https URL, then we use our own list.
        String protocol = uri.getScheme();
        if (useProxy && "https".equalsIgnoreCase(protocol)) {
            List<Proxy> list = new ArrayList<Proxy>();
            String uriHost = uri.getHost();

            if (uriHost.equalsIgnoreCase(primaryTlgHostname)) {
                list.add(primaryProxy);
                return list;
            } else if (uriHost.equalsIgnoreCase(secondaryTlgHostname)) {
                list.add(secondaryProxy);
                return list;
            } else {
                // not one of the TLG urls so fall through to default proxy selector
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug(uriHost
                            + " - not a TLG URL so falling back to default ProxySelector.");
                }
            }
        }

        /*
         * Not HTTP or HTTPS (could be SOCKS or FTP)
         * defer to the default selector.
         */
        if (defaultSelector != null) {
            List<Proxy> returnList = defaultSelector.select(uri);
            return returnList;
        } else {
            List<Proxy> list = new ArrayList<Proxy>();
            list.add(Proxy.NO_PROXY);
            return list;
        }
    }

    /**
     * Gets the primary tlg hostname.
     * 
     * @return the primaryTlgHostname
     */
    public String getPrimaryTlgHostname() {
        return primaryTlgHostname;
    }

    /**
     * Sets the primary tlg hostname.
     * 
     * @param primaryTlgHostname the primaryTlgHostname to set
     */
    public void setPrimaryTlgHostname(String primaryTlgHostname) {
        this.primaryTlgHostname = primaryTlgHostname;
    }

    /**
     * Gets the primary proxy host.
     * 
     * @return the primaryProxyHost
     */
    public String getPrimaryProxyHost() {
        return primaryProxyHost;
    }

    /**
     * Sets the primary proxy host.
     * 
     * @param primaryProxyHost the primaryProxyHost to set
     */
    public void setPrimaryProxyHost(String primaryProxyHost) {
        this.primaryProxyHost = primaryProxyHost;
    }

    /**
     * Gets the primary proxy port.
     * 
     * @return the primaryProxyPort
     */
    public Integer getPrimaryProxyPort() {
        return primaryProxyPort;
    }

    /**
     * Sets the primary proxy port.
     * 
     * @param primaryProxyPort the primaryProxyPort to set
     */
    public void setPrimaryProxyPort(Integer primaryProxyPort) {
        this.primaryProxyPort = primaryProxyPort;
    }

    /**
     * Gets the secondary tlg hostname.
     * 
     * @return the secondaryTlgHostname
     */
    public String getSecondaryTlgHostname() {
        return secondaryTlgHostname;
    }

    /**
     * Sets the secondary tlg hostname.
     * 
     * @param secondaryTlgHostname the secondaryTlgHostname to set
     */
    public void setSecondaryTlgHostname(String secondaryTlgHostname) {
        this.secondaryTlgHostname = secondaryTlgHostname;
    }

    /**
     * Gets the secondary proxy host.
     * 
     * @return the secondaryProxyHost
     */
    public String getSecondaryProxyHost() {
        return secondaryProxyHost;
    }

    /**
     * Sets the secondary proxy host.
     * 
     * @param secondaryProxyHost the secondaryProxyHost to set
     */
    public void setSecondaryProxyHost(String secondaryProxyHost) {
        this.secondaryProxyHost = secondaryProxyHost;
    }

    /**
     * Gets the secondary proxy port.
     * 
     * @return the secondaryProxyPort
     */
    public Integer getSecondaryProxyPort() {
        return secondaryProxyPort;
    }

    /**
     * Sets the secondary proxy port.
     * 
     * @param secondaryProxyPort the secondaryProxyPort to set
     */
    public void setSecondaryProxyPort(Integer secondaryProxyPort) {
        this.secondaryProxyPort = secondaryProxyPort;
    }

}
