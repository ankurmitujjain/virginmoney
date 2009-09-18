package com.virginmoneygiving.cardpayment.transport;

/**
 * Specifies the TLG Failover interface. <br/>
 * This can used by various notifiers to control a failover/failback.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface TLGFailover {

    /**
     * Get Active hostname (for logging).
     * 
     * @return currently active host name
     */
    public String getActiveHostname();

    /**
     * Check whether we're on primary server.
     * 
     * @return true iff active hostname is the primary hostname
     */
    public boolean isPrimary();

    /**
     * Check whether the port has been initialised.
     * 
     * @return true iff the port is not null
     */
    public boolean isPortInitialised();

    /**
     * Force a failover/failback to a specified host.
     * 
     * @param hostname - target
     */
    public void setActiveHostname(String hostname);

    /**
     * Notify of a failure that requires a failover.
     * 
     * @param e - cause
     */
    public void failover(Exception e);
}
