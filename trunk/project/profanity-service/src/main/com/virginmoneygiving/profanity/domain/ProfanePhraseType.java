package com.virginmoneygiving.profanity.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain object representing a profane phrase type.
 *
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "PROFANE_PHRASE_TYPE")
public class ProfanePhraseType extends BaseAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -270583331231232L;

    /** Profane phrase type code. */
    private String code;

    /** Profane phrase type description. */
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
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("ProfanePhraseType ( ")
            .append("code = ").append(this.code).append(tab)
            .append("description = ").append(this.description).append(tab)
            .append(super.toString()).append(" )").toString();
    }
}
