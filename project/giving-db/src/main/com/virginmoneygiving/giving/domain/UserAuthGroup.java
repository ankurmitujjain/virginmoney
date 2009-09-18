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
 * DTO representing a security authorization Group, a User is member of.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "USER_AUTH_GROUP")
public class UserAuthGroup extends AssociationAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225224999L;

    /** This userPermission identifier. */
    private Long id = null;

    /** Forgein key maps to get event from User table. */
    private User user;    
    
    /** Forgein key maps to code from AuthGroup. */
    private AuthGroup authGroup;    

    /**
     * Gets the id.
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
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Set the event.
     * 
     * @return event Event object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     * 
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
    
   
    /**
     * Get the authGroup type.
     * 
     * @return authGroup
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTH_GROUP_CODE")
    public AuthGroup getAuthGroup() {
        return authGroup;
    }

    /**
     * Set the authGroup type.
     * 
     * @param authGroup authGroup
     */
    public void setAuthGroup(AuthGroup authGroup) {
        this.authGroup = authGroup;
    }    

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        final String tab = "    ";
        return new StringBuilder("UserAuthGroup ( ").append("id = ").append(id)
                .append(tab).append("AuthGroup = ").append(authGroup)
                .append(tab).append(super.toString()).append(" )").toString();
    }
    
    @Override
    public int hashCode() {
        int hashCode = 17;
        if (user != null) {
            hashCode = 37 * hashCode + user.getId().hashCode();
        }
        if (authGroup != null) {
            hashCode = 37 * hashCode + authGroup.hashCode();
        }
        if (getStartDatetime() != null) {
            hashCode = 37 * hashCode + getStartDatetime().hashCode();
        }
        if (getEndDatetime() != null) {
            hashCode = 37 * hashCode + getEndDatetime().hashCode();
        }
        
        return hashCode;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserAuthGroup)) {
            return false;
        }
        UserAuthGroup uag = (UserAuthGroup) o;
        
        boolean equal = true;
        
        if (this.authGroup != null) {
            if (!this.authGroup.equals(uag.authGroup)) {
                equal = false;
            }
        }
        if (this.user != null) {
            if (uag.user == null) {
                equal = false;
            } else {
                if (!this.user.getId().equals(uag.user.getId())) {
                    equal = false;
                }
            }
        }
        if (this.getStartDatetime() != null) {
            if (!this.getStartDatetime().equals(uag.getStartDatetime())) {
                equal = false;
            }
        }
        if (this.getEndDatetime() != null) {
            if (!this.getEndDatetime().equals(uag.getEndDatetime())) {
                equal = false;
            }
        }
        
        return equal;
    }
}
