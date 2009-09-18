package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DTO representing a Page type.
 * 
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "PAGE_TYPE")
public class PageType extends BaseAuditAttributes implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 23787637983434241L;

    /** Code for page status. */
    private String code;

    /** Description for page status. */
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
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("PageType ( ")
            .append("code = ").append(this.code).append(tab)
            .append("description = ").append(this.description).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PageType == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PageType rhs = (PageType) obj;
        return new EqualsBuilder()
            .append(code, rhs.code)
            .isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(137, 731)
          .append(code)
          .toHashCode();
    }
}
