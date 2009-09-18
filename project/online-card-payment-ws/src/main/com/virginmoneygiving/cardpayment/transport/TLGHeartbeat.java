package com.virginmoneygiving.cardpayment.transport;

import java.util.Date;

/**
 * Check for TLG service availability.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public interface TLGHeartbeat {

    /**
     * Check the TLG service is available.
     */
    public void heartbeat();
    
    /**
     * Check whether the heartbeat is enabled.
     * 
     * @return the active
     */
    public boolean isActive();

    /**
     * Toggle the heartbeat;.
     * 
     * @param active the active to set
     */
    public void setActive(boolean active);

    /**
     * When did the heartbeat last check?.
     * 
     * @return the lastCheckDatetime
     */
    public Date getLastCheckDatetime();

}
