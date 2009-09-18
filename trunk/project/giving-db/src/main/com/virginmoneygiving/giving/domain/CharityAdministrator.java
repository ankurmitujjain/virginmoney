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
 * The data object that represents the charity administrator data to be
 * persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "CHARITY_ADMINISTRATOR")
public class CharityAdministrator extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Primary key. */
    private Long id;

    /** main Ind. */
    private String mainInd;

    /** User role. */
    private UserRole userRole;

    /** Charity. */
    private Charity charity;

    /**
     * Get id .
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Set id .
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the main ind.
     * 
     * @return the mainInd
     */
    @Column(name = "MAIN_IND")
    public String getMainInd() {
        if (mainInd == null) {
            mainInd = "N";
        }
        return mainInd;
    }

    /**
     * Sets the main ind.
     * 
     * @param mainInd the mainInd to set
     */
    public void setMainInd(String mainInd) {
        this.mainInd = mainInd;
    }

    /**
     * Gets the user role.
     * 
     * @return the user
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ROLE_ID")
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets the user role.
     * 
     * @param userRole the user to set
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
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
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("CharityAdministrator ( ")
            .append("id = ").append(id).append(tab)
            .append("mainInd = ").append(mainInd).append(tab)
            .append("userRole = ").append(userRole).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
