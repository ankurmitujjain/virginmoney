package com.virginmoneygiving.ldap.domain;

/**
 * This domain class is used to hold active directory domain policy attribute.
 * 
 * @author vishals
 */
public final class DomainPolicyAttributes {

    /** Lockout duration defined on domain level. */
    private Long lockoutDuration;
    
    /** Lockout threshold defined on domain level. */
    private Long lockoutThreshold;

    /**
     * Getter for lockout duration.
     * 
     * @return lockout duration.
     */
    public Long getLockoutDuration() {
        return lockoutDuration;
    }

    /**
     * Setter for lockout duration.
     * 
     * @param lockoutDuration lockout duration.
     */
    public void setLockoutDuration(Long lockoutDuration) {
        this.lockoutDuration = lockoutDuration;
    }

    /**
     * Getter for lockout threshold.
     * 
     * @return lockout threshold.
     */
    public Long getLockoutThreshold() {
        return lockoutThreshold;
    }

    /**
     * Setter for lockout threshold.
     * 
     * @param lockoutThreshold lockout threshold.
     */
    public void setLockoutThreshold(Long lockoutThreshold) {
        this.lockoutThreshold = lockoutThreshold;
    }

}
