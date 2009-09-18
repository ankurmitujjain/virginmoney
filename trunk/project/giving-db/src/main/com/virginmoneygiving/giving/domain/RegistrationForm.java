package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DTO representing a registration form.
 * 
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "REGISTRATION_FORM")
public class RegistrationForm extends AuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -2387623L;

    /** Primary key for registration form. */
    private Long id = null;

    /** Name of the registration form. */
    private String name = null;

    /** Flag indicating whether this form is required or not. */
    private String required = null;

    /** The url for the given form. */
    private String url = null;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Gets the name.
     * 
     * @return the name
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the required.
     * 
     * @return the required
     */
    @Column(name = "REQUIRED")
    public String getRequired() {
        return required;
    }

    /**
     * Sets the required.
     * 
     * @param required the required to set
     */
    public void setRequired(String required) {
        this.required = required;
    }

    /**
     * Gets the url.
     * 
     * @return the url
     */
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     * 
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
        return new StringBuilder("RegistrationForm ( ")
            .append("id = ").append(id).append(tab)
            .append("name = ").append(name).append(tab)
            .append("required = ").append(required).append(tab)
            .append("url = ").append(url).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
