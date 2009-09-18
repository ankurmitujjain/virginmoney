package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the Person data to be persisted.
 * 
 * @author Sejal Shah
 * @author Edwin Tauro - Handle personal address for the fundraiser.
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "PERSON")
@Audited
public class Person extends EnversAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -5706940241099707981L;

    /** Attribute id. */
    private Long id;

    /** Attribute surname. */
    private String surname;

    /** Attribute forename. */
    private String forename;

    /** Attribute title. */
    private String title;

    /** Attribute dobDay. */
    private Integer dobDay;

    /** Attribute dobMonth. */
    private Integer dobMonth;

    /** Attribute dobYear. */
    private Integer dobYear;

    /** Attribute occupation. */
    private String occupation;

    /** Set of EmailAddress. */
    private Set<PersonEmailAddress> personEmailAddresses;

    /** Set of TelephoneNumber. */
    private Set<PersonTelephoneNumber> personTelephoneNumbers;

    /** Personal addresses. */
    private Set<PersonalAddress> personalAddresses;


    /**
     * Returns the unique identification for {@link Person}.
     * 
     * @return id the unique identification for {@link Person}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identification for {@link Person}.
     * 
     * @param id new value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the surname for the {@link Person}.
     * 
     * @return surname the surname for the {@link Person}.
     */
    @Column(name = "SURNAME")
    @NotAudited
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname for the {@link Person}.
     * 
     * @param surname new value for surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the forename.
     * 
     * @return forename
     */
    @Column(name = "FORENAME")
    @NotAudited
    public String getForename() {
        return forename;
    }

    /**
     * Sets the forename.
     * 
     * @param forename new value for forename
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the title.
     * 
     * @return title
     */
    @Column(name = "TITLE")
    @NotAudited
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title new value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the dob day.
     * 
     * @return dobDay
     */
    @Column(name = "DOB_DAY")
    @Audited
    public Integer getDobDay() {
        return dobDay;
    }

    /**
     * Sets the dob day.
     * 
     * @param dobDay new value for dobDay
     */
    public void setDobDay(Integer dobDay) {
        this.dobDay = dobDay;
    }

    /**
     * Gets the dob month.
     * 
     * @return dobMonth
     */
    @Column(name = "DOB_MONTH")
    @Audited
    public Integer getDobMonth() {
        return dobMonth;
    }

    /**
     * Sets the dob month.
     * 
     * @param dobMonth new value for dobMonth
     */
    public void setDobMonth(Integer dobMonth) {
        this.dobMonth = dobMonth;
    }

    /**
     * Gets the dob year.
     * 
     * @return dobYear
     */
    @Column(name = "DOB_YEAR")
    @Audited
    public Integer getDobYear() {
        return dobYear;
    }

    /**
     * Sets the dob year.
     * 
     * @param dobYear new value for dobYear
     */
    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }

    /**
     * Gets the occupation.
     * 
     * @return occupation
     */
    @Column(name = "OCCUPATION")
    @NotAudited
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the occupation.
     * 
     * @param occupation new value for occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Get the Set of PersonEmailAddress.
     * 
     * @return set of {@link PersonEmailAddress}.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    @NotAudited
    public Set<PersonEmailAddress> getPersonEmailAddresses() {
        return this.personEmailAddresses;
    }

    /**
     * Set the set of {@link PersonEmailAddress}.
     * 
     * @param personEmailAddresses set of {@link PersonEmailAddress}.
     */
    public void setPersonEmailAddresses(Set<PersonEmailAddress> personEmailAddresses) {
        this.personEmailAddresses = personEmailAddresses;
    }

    /**
     * Get the set of {@link PersonTelephoneNumbers}.
     * 
     * @return set of {@link PersonTelephoneNumbers}.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    @NotAudited
    public Set<PersonTelephoneNumber> getPersonTelephoneNumbers() {
        return this.personTelephoneNumbers;
    }

    /**
     * Set the set of {@link PersonTelephoneNumbers}.
     * 
     * @param personTelephoneNumbers set of {@link PersonTelephoneNumbers}.
     */
    public void setPersonTelephoneNumbers(Set<PersonTelephoneNumber> personTelephoneNumbers) {
        this.personTelephoneNumbers = personTelephoneNumbers;
    }

    /**
     * Gets the personal addresses.
     * 
     * @return the personal_addresses
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    @NotAudited
    public Set<PersonalAddress> getPersonalAddresses() {
        return personalAddresses;
    }

    /**
     * Sets the personal addresses.
     * 
     * @param personalAddresses the personal_addresses to set
     */
    public void setPersonalAddresses(Set<PersonalAddress> personalAddresses) {
        this.personalAddresses = personalAddresses;
    }



    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("Person ( ").append("id = ").append(id)
                .append(tab).append("surname = ").append(surname).append(tab)
                .append("forename = ").append(forename).append(tab).append(
                        "title = ").append(title).append(tab).append(
                        "dobDay = ").append(dobDay).append(tab).append(
                        "dobMonth = ").append(dobMonth).append(tab).append(
                        "dobYear = ").append(dobYear).append(tab).append(
                        "occupation = ").append(occupation).append(tab).append(
                        super.toString()).append(" )").toString();
    }
}
