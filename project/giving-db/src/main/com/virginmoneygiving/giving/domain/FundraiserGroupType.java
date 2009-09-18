package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InvitedFundraisingMemberType object defined.
 * 
 * @author esakkiy
 */
// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@javax.persistence.Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "FUNDRAISER_GROUP_TYPE")
public class FundraiserGroupType extends BaseAuditAttributes implements
        Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -293847298347L;

    /** The code for the INVITED_FUNDRAISING_MEMBER_TYPE. */
    private String code;

    /** The description for the code used. */
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
     * Allowed codes are INVITED / REGISTERED.
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

        return new StringBuilder("FundraiserGroupType ( ").append("code = ")
                .append(this.code).append(tab).append("description = ").append(
                        this.description).append(tab).append(super.toString())
                .append(" )").toString();
    }
}
