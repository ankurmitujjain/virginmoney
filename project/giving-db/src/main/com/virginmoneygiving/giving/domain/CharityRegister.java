package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the charity registration data to be
 * persisted.
 * 
 * @author Yogesh Salunkhe
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "CHARITY_REGISTER")
@Audited
public class CharityRegister extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 4396121764708138580L;

    /** Charity Code. */
    private String code;

    /** Charity description. */
    private String description;

    /**
     * Get status code for charity.
     * 
     * @return the charity status code
     */
    @Id
    @Column(name = "CODE")
    @NotAudited
    public String getCode() {
        return code;
    }

    /**
     * Set the charity status code.
     * 
     * @param code the charity status code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get description for charity.
     * 
     * @return the description for Charity
     */
    @Column(name = "DESCRIPTION")
    @NotAudited
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for charity.
     * 
     * @param description the description for charity.
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
        return new StringBuilder("CharityRegister ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
