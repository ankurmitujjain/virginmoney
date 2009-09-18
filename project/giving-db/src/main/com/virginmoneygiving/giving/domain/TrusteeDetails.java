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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Domain class to persist the trustee details.
 * 
 * @author Srinivas N
 */
@Entity
@Table(name = "TRUSTEE_DETAILS")
public class TrusteeDetails extends AuditAttributes implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 4270334960198736050L;

    /** Attribute id. */
    private Long id;

    /** Attribute person details. */
    private Person personDetails;

    /** Attribute charity details. */
    private Charity charityDetails;

    /** Attribute trustee status. */
    private TrusteeStatus trusteeStatus;

    /** Trustee detail. */
    private TrusteeType trusteeType;

    /**
     * Returns the unique identification for {@link TrusteeDetails}.
     * 
     * @return id the unique identification for {@link TrusteeDetails}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identification for {@link TrusteeDetails}.
     * 
     * @param id new value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the person details.
     * 
     * @return the personDetails
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    public Person getPersonDetails() {
        return personDetails;
    }

    /**
     * Sets the person details.
     * 
     * @param personDetails the personDetails to set
     */
    public void setPersonDetails(Person personDetails) {
        this.personDetails = personDetails;
    }

    /**
     * Gets the charity details.
     * 
     * @return the charityDetails
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARITY_ID")
    public Charity getCharityDetails() {
        return charityDetails;
    }

    /**
     * Sets the charity details.
     * 
     * @param charityDetails the charityDetails to set
     */
    public void setCharityDetails(Charity charityDetails) {
        this.charityDetails = charityDetails;
    }

    /**
     * Gets the trustee status.
     * 
     * @return the trusteeStatus
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TRUSTEE_STATUS_CODE")
    public TrusteeStatus getTrusteeStatus() {
        return trusteeStatus;
    }

    /**
     * Sets the trustee status.
     * 
     * @param trusteeStatus the trusteeStatus to set
     */
    public void setTrusteeStatus(TrusteeStatus trusteeStatus) {
        this.trusteeStatus = trusteeStatus;
    }

    /**
     * Gets the trustee type.
     * 
     * @return the trusteeType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TRUSTEE_TYPE_CODE")
    public TrusteeType getTrusteeType() {
        return trusteeType;
    }

    /**
     * Sets the trustee type.
     * 
     * @param trusteeType the trusteeType to set
     */
    public void setTrusteeType(TrusteeType trusteeType) {
        this.trusteeType = trusteeType;
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
        return new StringBuilder("Trustee ( ").append("id = ").append(id)
                .append(tab).append("personDetails = ").append(personDetails)
                .append(tab).append("charityDetails = ").append(charityDetails)
                .append(tab).append("trusteeStatus = ").append(trusteeStatus)
                .append(tab).append(super.toString()).append(" )").toString();
    }

}
