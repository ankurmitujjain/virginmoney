package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * DTO representing a Page status.
 * 
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "PAGE_STATUS")
public class PageStatus extends BaseAuditAttributes implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 23787637983434241L;

    /** The Constant ACTIVE. */
    public static final String ACTIVE = "ACT";
    
    /** The Constant INACTIVE. */
    public static final String INACTIVE = "INACT";

    /** Code for page status. */
    private String code;

    /** Description for page status. */
    private String description;

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
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("PageStatus ( ")
            .append("code = ").append(this.code).append(tab)
            .append("description = ").append(this.description).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }

    /**
     * Checks if is active.
     * 
     * @return true if page status is active.
     */
    @Transient
    public boolean isActive() {
        return ACTIVE.equals(code);
    }
}
