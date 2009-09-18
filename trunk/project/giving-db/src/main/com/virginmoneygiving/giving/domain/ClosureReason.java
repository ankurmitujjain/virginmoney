/**
 * Copyright
 */
package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The Class ClosureReason.
 * 
 * @author Sejal Shah
 */

// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "CLOSURE_REASON")
@Audited
public class ClosureReason extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -3512276537733812001L;

    /** Code. */
    private String code;

    /** Description. */
    private String description;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    @Id
    @Column(name = "CODE")
    @NotAudited
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
    @NotAudited
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
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation
     * of this object.
     */
     @Override
     public String toString() {

        final String tab = "    ";
        return new StringBuilder("ClosureReason ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
