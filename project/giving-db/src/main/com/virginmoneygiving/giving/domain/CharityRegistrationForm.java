package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * DTO representing a charity registration form.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "CHARITY_REGISTRATION_FORM")
public class CharityRegistrationForm extends EnversAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -23645234L;

    /** Primary key for charity registration form. */
    private Long id = null;

    /** Time instance when this form was downloaded. */
    private Timestamp downloadedDatetime = null;

    /** Time instance when this form was received. */
    private Timestamp receivedDatetime = null;

    /** Charity for which this form was downloaded/received. */
    private Charity charity = null;

    /** The registration form. */
    private RegistrationForm registrationForm = null;

    /**
     * Gets the id.
     * 
     * @return the id
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
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the downloaded datetime.
     * 
     * @return the downloadedDateTime
     */
    @Column(name = "DOWNLOADED_DATETIME")
    public Timestamp getDownloadedDatetime() {
        return downloadedDatetime;
    }

    /**
     * Sets the downloaded datetime.
     * 
     * @param downloadedDatetime the downloadedDateTime to set
     */
    public void setDownloadedDatetime(Timestamp downloadedDatetime) {
        this.downloadedDatetime = downloadedDatetime;
    }

    /**
     * Gets the received datetime.
     * 
     * @return the receivedDateTime
     */
    @Column(name = "RECEIVED_DATETIME")
    public Timestamp getReceivedDatetime() {
        return receivedDatetime;
    }

    /**
     * Sets the received datetime.
     * 
     * @param receivedDatetime the receivedDateTime to set
     */
    public void setReceivedDatetime(Timestamp receivedDatetime) {
        this.receivedDatetime = receivedDatetime;
    }

    /**
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CHARITY_ID")
    @Audited
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the charity to set
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the registration form.
     * 
     * @return the registrationForm
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REGISTRATION_FORM_ID")
    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }

    /**
     * Sets the registration form.
     * 
     * @param registrationForm the registrationForm to set
     */
    public void setRegistrationForm(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("CharityRegistrationForm ( ")
            .append("id = ").append(id).append(tab)
            .append("downloadedDatetime = ").append(downloadedDatetime).append(tab)
            .append("receivedDatetime = ").append(receivedDatetime).append(tab)
            .append("registrationForm = ").append(registrationForm).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
