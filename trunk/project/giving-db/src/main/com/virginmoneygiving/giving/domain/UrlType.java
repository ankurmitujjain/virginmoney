package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class to represent URL Type.
 * 
 * @author vikask
 */
// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "URL_TYPE")
public class UrlType extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -7616381584548537788L;

    /** Url type code. */
    private String code;

    /** Url Type description. */
    private String description;

    /**
     * Getter method for url type code.
     * 
     * @return the code of url type.
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Setter method for url type code.
     * 
     * @param code to set url type code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for url type description.
     * 
     * @return the description of url type.
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for url type description.
     * 
     * @param description to set url type description.
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

        return new StringBuilder("UrlType ( ")
            .append("code = ").append(this.code).append(tab)
            .append("description = ").append(this.description).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
