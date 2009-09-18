package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The data object that represents charity off line registration data.
 * 
 * @author Mahesh Yerudkar
 */
@Entity
@Table(name = "CHARITY_OFFLINE_REG_LOG")
public class CharityOfflineRegistrationLog extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Primary key for Charity Off line Registration Log. */
    private Long id;

    /** CharityOfflineRegistration. */
    private CharityOfflineRegistration charityOfflineRegistration;

    /** OfflineRegModule. */
    private OfflineRegModule offlineRegModule;

    /**
     * Gets the id.
     * 
     * @return the id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the charity offline registration.
     * 
     * @return the CharityOfflineRegistration object.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_OFFLINE_REG_ID")
    public CharityOfflineRegistration getCharityOfflineRegistration() {
        return charityOfflineRegistration;
    }

    /**
     * Sets the charity offline registration.
     * 
     * @param charityOfflineRegistration the Charity Off line Registration object to set.
     */
    public void setCharityOfflineRegistration(
            CharityOfflineRegistration charityOfflineRegistration) {
        this.charityOfflineRegistration = charityOfflineRegistration;
    }

    /**
     * Gets the offline reg module.
     * 
     * @return the OfflineRegModule object.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OFFLINE_REG_MODULE_ID")
    public OfflineRegModule getOfflineRegModule() {
        return offlineRegModule;
    }

    /**
     * Sets the offline reg module.
     * 
     * @param offlineRegModule the Off Line Registration Module object to set.
     */
    public void setOfflineRegModule(OfflineRegModule offlineRegModule) {
        this.offlineRegModule = offlineRegModule;
    }
}
