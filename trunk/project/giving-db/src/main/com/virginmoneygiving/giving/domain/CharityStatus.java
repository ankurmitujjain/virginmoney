package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the charity status data to be persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */

// both JPA and Hibernate Entity annotation is used
// for it defines mutability for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "CHARITY_STATUS")
@Audited
public class CharityStatus extends BaseAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7829307939063864654L;

    /** Code for charity status. */
    private String code;

    /** Description for charity status. */
    private String description;

    /**
     * Get the code for the charity status.
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
     * Set the code for the charity status.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the description for the charity status.
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    @NotAudited
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for the charity status.
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
        return new StringBuilder("CharityStatus ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
