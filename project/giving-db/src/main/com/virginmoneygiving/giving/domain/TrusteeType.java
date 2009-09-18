package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class to persist the trustee type.
 * 
 * @author Srinivas N
 */
@Entity
@Table(name = "TRUSTEE_TYPE")
public class TrusteeType extends BaseAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 6434718068018509890L;

    /** Primary key for trustee type. */
    private String code = null;

    /** Trustee type description. */
    private String description = null;

    /**
     * Constructor with code.
     * 
     * @param trusteeTypeCode the code to set
     */
    public TrusteeType(String trusteeTypeCode) {
        this.code = trusteeTypeCode;
    }

    /**
     * Default constructor.
     */
    public TrusteeType() {
    }

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
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("TrusteeType ( ").append("code = ").append(
                code).append(tab).append("description = ").append(description)
                .append(tab).append(super.toString()).append(" )").toString();
    }

}
