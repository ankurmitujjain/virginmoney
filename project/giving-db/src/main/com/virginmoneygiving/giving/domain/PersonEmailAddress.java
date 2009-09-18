package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

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
 * Domain object representing association between {@link Person} and
 * {@link EmailAddress}.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "PERSON_EMAIL_ADDRESS")
@Audited
public class PersonEmailAddress extends EnversAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -570694024566707981L;

    /** Attribute id. */
    private Long id;

    /** Person. */
    private Person person;

    /** Email address. */
    private EmailAddress emailAddress;

    /**
     * Gets the id.
     * 
     * @return the unique id for {@link PersonEmailAddress}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id new value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the person.
     * 
     * @return the person
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    @Audited
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person.
     * 
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets the email address.
     * 
     * @return the emailAddress
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL_ADDRESS_ID", referencedColumnName = "ID")
    @Audited
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address.
     * 
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }
}
