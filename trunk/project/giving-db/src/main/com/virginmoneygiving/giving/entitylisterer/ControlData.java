package com.virginmoneygiving.giving.entitylisterer;

/**
 * Control data used for audit.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ControlData {
    
    /** User updating this record. */
    private String username = null;

    /** Process updating this record. */
    private String process = null;

    /** IP address updating this record. */
    private String ipAddress = null;

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the process.
     * 
     * @return the process
     */
    public String getProcess() {
        return process;
    }

    /**
     * Sets the process.
     * 
     * @param process the process to set
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * Gets the ip address.
     * 
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the ip address.
     * 
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
