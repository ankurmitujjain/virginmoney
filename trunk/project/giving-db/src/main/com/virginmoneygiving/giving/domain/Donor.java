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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * Domain object which represents the Donor data.
 * 
 * @author vikas kale
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "DONOR")
@Audited
public class Donor extends AuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /** Donor unique Id. */
    private Long id;

    /** Refernce of UserRole object. */
    private UserRole userRole;

    /**
     * Getter method for donor Id.
     * 
     * @return id for Donor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @NotAudited
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for UserRole.
     * 
     * @return userRole
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ROLE_ID")
    @NotAudited
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets the user role.
     * 
     * @param userRole the new user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
        return new StringBuilder("Donor ( ")
            .append("id = ").append(id).append(tab)
            .append("userRole = ").append(userRole).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
