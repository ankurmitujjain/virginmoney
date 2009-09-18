package com.virginmoneygiving.cardpayment.transport;

import java.util.Date;
import org.apache.log4j.Logger;
import com.virginmoneygiving.cardpayment.service.EndpointProbeService;
import com.virginmoneygiving.cardpayment.service.exception.EndpointUnavailableException;

/**
 * Heartbeat using the endpoint probe.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class TLGHeartbeatImpl implements TLGHeartbeat {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(TLGHeartbeatImpl.class);

    //+DI
    /** Whether the heartbeat should check. */
    private boolean active = true;

    /** Primary URL to probe. */
    private String primaryUrl;

    /** Primary host for failover. */
    private String primaryHost;

    /** Secondary URL to probe. */
    private String secondaryUrl;

    /** Secondary host for failover. */
    private String secondaryHost;

    /** Endpoint probe. */
    private EndpointProbeService probe;

    /** Failover instance for notification. */
    private TLGFailover failover;
    //-DI

    /** Last checked at. */
    private Date lastCheckDatetime;

    /** (@inheritDoc) */
    public void heartbeat() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("TLG heartbeat called... Active = " + active);
        }

        if (active) {
            // attempt to probe in preferred order
            try {
                // set probe date time for JMX access
                if (lastCheckDatetime == null) {
                    lastCheckDatetime = new Date();
                } else {
                    lastCheckDatetime.setTime(System.currentTimeMillis());
                }

                // perform heartbeat
                if (failover.isPrimary()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Probing primary (then secondary if required)...");
                    }
                    String url = probe.chooseUrl(primaryUrl, secondaryUrl);
                    if (url.equals(secondaryUrl)) {
                        LOGGER.info("Requesting failover to secondary host");
                        failover.setActiveHostname(secondaryHost);
                    }
                } else {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Probing secondary (then primary if required)...");
                    }
                    String url = probe.chooseUrl(secondaryUrl, primaryUrl);
                    if (url.equals(primaryUrl)) {
                        LOGGER.info("Requesting failback to primary host");
                        failover.setActiveHostname(primaryHost);
                    }
                }

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Heartbeat complete...");
                }
            } catch (EndpointUnavailableException eue) {
                //The endpoint probe will raise an alert, so just log it
                LOGGER.fatal("TLG heartbeat failure", eue);
            }
        }
    }

    /**
     * Sets the primary url.
     * 
     * @param primaryUrl the primaryUrl to set
     */
    public void setPrimaryUrl(String primaryUrl) {
        this.primaryUrl = primaryUrl;
    }

    /**
     * Sets the primary host.
     * 
     * @param primaryHost the primaryHost to set
     */
    public void setPrimaryHost(String primaryHost) {
        this.primaryHost = primaryHost;
    }

    /**
     * Sets the secondary url.
     * 
     * @param secondaryUrl the secondaryUrl to set
     */
    public void setSecondaryUrl(String secondaryUrl) {
        this.secondaryUrl = secondaryUrl;
    }

    /**
     * Sets the secondary host.
     * 
     * @param secondaryHost the secondaryHost to set
     */
    public void setSecondaryHost(String secondaryHost) {
        this.secondaryHost = secondaryHost;
    }

    /**
     * Sets the probe.
     * 
     * @param probe the probe to set
     */
    public void setProbe(EndpointProbeService probe) {
        this.probe = probe;
    }

    /**
     * Sets the failover.
     * 
     * @param failover the failover to set
     */
    public void setFailover(TLGFailover failover) {
        this.failover = failover;
    }

    /**
     * Checks if is active.
     * 
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active.
     * 
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the last check datetime.
     * 
     * @return the lastCheckDatetime
     */
    public final Date getLastCheckDatetime() {
        return (Date) lastCheckDatetime.clone();
    }
}
