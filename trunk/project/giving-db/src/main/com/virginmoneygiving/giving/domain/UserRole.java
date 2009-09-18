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
 * The data object that represents the user role data to be persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "USER_ROLE")
@Audited
public class UserRole extends EnversAssociationAuditAttributes implements
        Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -2855427082893059444L;

    /** user role id. */
    private Long id;

    /** Attribute role. */
    private Role role;

    /** User. */
    private User user;

    /**
     * Getter method for user role Id.
     * 
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Setter method for user role id.
     * 
     * @param id new value for user role id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for role.
     * 
     * @return object of {@link Role}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_CODE")
    @Audited
    public Role getRole() {
        return this.role;
    }

    /**
     * Setter method for role.
     * 
     * @param role to set Role object.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Getter method for user.
     * 
     * @return object of {@link User}
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    @Audited
    public User getUser() {
        return user;
    }

    /**
     * Setter method for user.
     * 
     * @param user to set User object.
     */
    public void setUser(User user) {
        this.user = user;
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
        return new StringBuilder("UserRole ( ")
            .append("id = ").append(id).append(tab)
            .append("role = ").append(role).append(tab)
            .append("user = ").append(user).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
