package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DTO representing a custom field type.
 * 
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "CUSTOM_FIELD_TYPE")
public class CustomFieldType  extends BaseAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225224659L;

    /** Code for this custom field type. */
    private String code = null;

    /** Description for this custom field type. */
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
        return new StringBuilder("CustomFieldType ( ").append("code = ").append(code)
                .append(tab).append("description = ").append(description)
                .append(tab).append(super.toString()).append(" )").toString();

    }
}
