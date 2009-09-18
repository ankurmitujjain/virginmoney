package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the bank account type to be persisted.
 * 
 * @author Rahul Vaidya
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "BANK_ACCOUNT_TYPE")
@Audited
public class BankAccountType extends BaseAuditAttributes implements
        Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 5896589655656L;

    /** Code for bank account type code. */
    private String code;

    /** Description for bank account type description. */
    private String description;

    /**
     * Get the code for the bank account type code.
     * 
     * @return the code
     */
    @Id
    @Column(name = "CODE")
    @NotAudited
    public String getCode() {
        return code;
    }

    /**
     * Set the code for the bank account type code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the bank account type description.
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    @NotAudited
    public String getDescription() {
        return description;
    }

    /**
     * Set the bank account type description.
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

        return new StringBuilder("BankAccountType ( ").append("code = ")
                .append(this.code).append(tab).append("description = ").append(
                        this.description).append(tab).append(super.toString())
                .append(" )").toString();
    }
}
