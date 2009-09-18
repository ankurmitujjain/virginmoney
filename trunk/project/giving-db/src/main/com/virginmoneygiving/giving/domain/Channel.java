package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * DTO representing a donation channel.
 * 
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "CHANNEL")
@Audited
public class Channel extends BaseAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -256747298347L;

    /** The code of the channel. */
    private String code = null;

    /** The description for this channel. */
    private String description = null;

    /**
     * Gets the code.
     * 
     * @return the name
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
        return new StringBuilder("Channel ( ")
            .append("code = ").append(code).append(tab)
            .append("description = ").append(description).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
