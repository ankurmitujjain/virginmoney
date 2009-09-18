package com.virginmoneygiving.giving.domain;

import java.sql.Timestamp;

/**
 * The Interface AuditInterface.
 */
public interface AuditInterface {

    /**
     * Gets the created user.
     * 
     * @return the created user
     */
    public String getCreatedUser();

    /**
     * Sets the created user.
     * 
     * @param createdUser the new created user
     */
    public void setCreatedUser(String createdUser);

    /**
     * Gets the created process.
     * 
     * @return the created process
     */
    public String getCreatedProcess();

    /**
     * Sets the created process.
     * 
     * @param createdProcess the new created process
     */
    public void setCreatedProcess(String createdProcess);

    /**
     * Gets the created ip address.
     * 
     * @return the created ip address
     */
    public String getCreatedIPAddress();

    /**
     * Sets the created ip address.
     * 
     * @param createdIPAddress the new created ip address
     */
    public void setCreatedIPAddress(String createdIPAddress);

    /**
     * Gets the updated date time.
     * 
     * @return the updated date time
     */
    public Timestamp getUpdatedDateTime();

    /**
     * Sets the updated date time.
     * 
     * @param updatedDateTime the new updated date time
     */
    public void setUpdatedDateTime(Timestamp updatedDateTime);

    /**
     * Gets the updated user.
     * 
     * @return the updated user
     */
    public String getUpdatedUser();

    /**
     * Sets the updated user.
     * 
     * @param updatedUser the new updated user
     */
    public void setUpdatedUser(String updatedUser);

    /**
     * Gets the updated process.
     * 
     * @return the updated process
     */
    public String getUpdatedProcess();

    /**
     * Sets the updated process.
     * 
     * @param updatedProcess the new updated process
     */
    public void setUpdatedProcess(String updatedProcess);

    /**
     * Gets the updated ip address.
     * 
     * @return the updated ip address
     */
    public String getUpdatedIPAddress();

    /**
     * Sets the updated ip address.
     * 
     * @param updatedIPAddress the new updated ip address
     */
    public void setUpdatedIPAddress(String updatedIPAddress);
}
