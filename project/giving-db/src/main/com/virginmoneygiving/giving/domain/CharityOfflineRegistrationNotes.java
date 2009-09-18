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
 * The data object that represents Charity Off Line Registration Notes.
 * 
 * @author Mahesh Yerudkar
 */
@Entity
@Table(name = "CHARITY_OFFLINE_REG_NOTES")
public class CharityOfflineRegistrationNotes extends AuditAttributes implements
        Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Primary key for Charity Off line Registration Notes. */
    private Long id;

    /** CharityOfflineRegistration. */
    private CharityOfflineRegistration charityOfflineRegistration;

    /** Module. */
    private Module module;

    /** Notes. */
    private String notes;

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
     * Gets the module.
     * 
     * @return the CharityOfflineRegistration object.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODULE_CODE")
    public Module getModule() {
        return module;
    }

    /**
     * Sets the module.
     * 
     * @param module module code to set.
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * Gets the notes.
     * 
     * @return the string User Name who updated the data.
     */
    @Column(name = "NOTES", columnDefinition = "text")
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes.
     * 
     * @param notes Notes to set.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
