package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DTO representing a authorisation group.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "AUTH_GROUP")
public class AuthGroup extends BaseAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225224659L;

    /** Code for this AuthGroup. */
    private String code = null;

    /** Description for this AuthGroup. */
    private String description = null;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        final String tab = "    ";
        return new StringBuilder("AuthGroup ( ").append("code = ").append(code)
                .append(tab).append("description = ").append(description)
                .append(tab).append(super.toString()).append(" )").toString();

    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthGroup)) {
            return false;
        }
        AuthGroup auth = (AuthGroup) o;
        
        return ( (auth.code.equals(this.code)) && (auth.description.equals(this.description)) );
    }
    
    @Override
    public int hashCode() {
        int hashCode = 17;
        if (code != null) {
            hashCode = 37 * hashCode + code.hashCode();
        }
        if (description != null) {
            hashCode = 37 * hashCode + description.hashCode();
        }
        
        return hashCode;
    }
}
