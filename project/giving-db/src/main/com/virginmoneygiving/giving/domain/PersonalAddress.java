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
 * {@link Address}.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "PERSONAL_ADDRESS")
@Audited
public class PersonalAddress extends EnversAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -57069402434534511L;

    /** Attribute id. */
    private Long id;

    /** Person. */
    private Person person;

    /** Address. */
    private Address address;

    /**
     * Gets the id.
     * 
     * @return the id
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
     * @param id the id to set
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
     * Gets the address.
     * 
     * @return the address
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    @Audited
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * 
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
