package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class for Fundraising reason.
 * 
 * @author Edwin Tauro
 * @author Puneet Swarup - added audit attributes and toString.
 */
//both JPA and Hibernate Entity annotation is used for it defines mutability for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "FUNDRAISING_REASON")
public class FundraisingReason extends BaseAuditAttributes implements Serializable {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 4920240887393977765L;

    /** The max length for the code field. */
    public static final int CODE_LENGTH = 10;

    /** Fundraising reason code. */
    private String code;

    /** Fundraising reason code description. */
    private String description;

    /**
     * Getter method for Fundraising reason code.
     * 
     * @return String the code.
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Setter method for Fundraising reason code.
     * 
     * @param code for Fundraising reason.
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Getter method for Fundraising reason code description.
     * 
     * @return description for fundraising reason code.
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for Fundraising reason code description.
     * 
     * @param description for fundraising reason code.
     */
    public void setDescription(final String description) {
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
        return new StringBuilder("FundraisingReason ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
