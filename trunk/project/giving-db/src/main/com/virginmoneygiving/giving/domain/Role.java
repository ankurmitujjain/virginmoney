package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * DTO to persist a role.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */

//both JPA and Hibernate Entity annotation is used for it defines mutability for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "ROLE")
@Audited
public class Role extends BaseAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 1201932346657742347L;

    /** Attribute code. */
    private String code;

    /** Attribute description. */
    private String description;

   /**
    * Getter method for role code.
    * 
    * @return code
    */
    @Id
    @Column(name = "CODE")
    @NotAudited
    public String getCode() {
        return code;
    }

    /**
     * Setter method for code.
     * 
     * @param code new value for code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for description.
     * 
     * @return description
     */
    @Column(name = "DESCRIPTION")
    @NotAudited
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for description.
     * 
     * @param description new value for description
     */
    public void setDescription(String description) {
        this.description = description;
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
        return new StringBuilder("Role ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
