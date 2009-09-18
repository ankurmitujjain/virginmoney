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
 * {@link TelephoneNumber}.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "PERSON_TELEPHONE_NUMBER")
@Audited
public class PersonTelephoneNumber extends EnversAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -57014567239707981L;

    /** Attribute id. */
    private Long id;

    /** Person. */
    private Person person;

    /** TelephoneNumber. */
    private TelephoneNumber telephoneNumber;

    /**
     * Gets the id.
     * 
     * @return the unique id for {@link PersonTelephoneNumber}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
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
    @JoinColumn(name = "PERSON_ID")
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
     * Gets the telephone number.
     * 
     * @return the telephoneNumber
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TELEPHONE_NUMBER_ID", referencedColumnName = "ID")
    @Audited
    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the telephone number.
     * 
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
