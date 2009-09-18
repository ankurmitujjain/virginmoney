package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The data object that represents the address type data to be persisted.
 * 
 * @author Sejal Shah.
 * @author Puneet Swarup - added audit attributes and toString.
 */
// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "ADDRESS_TYPE")
public class AddressType extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 7445352700087130446L;

    /** Address Type Code. */
    private String code;

    /** Description AddressType. */
    private String description;

    /**
     * Default constructor.
     */
    public AddressType() {
        super();
    }

    /**
     * Getter method for address type code.
     * 
     * @return the code
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Setter method for address type code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for address type code description.
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for address type code description.
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
        return new StringBuilder("AddressType ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
