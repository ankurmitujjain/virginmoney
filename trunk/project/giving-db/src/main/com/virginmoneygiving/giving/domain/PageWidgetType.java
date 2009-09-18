package com.virginmoneygiving.giving.domain;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A type of widget, forming part of a page.
 * eg image gallery, days to go countdown, my history etc
 */
@Entity
@Table(name = "PAGE_WIDGET_TYPE")
public class PageWidgetType extends BaseAuditAttributes implements Serializable{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

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
     * @param code the new code
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
     * @param desctiption the new description
     */
    public void setDescription(String desctiption) {
        this.description = desctiption;
    }
    
    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("PageWidgetType ( ")
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
        if (obj instanceof PageWidgetType == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PageWidgetType rhs = (PageWidgetType) obj;
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
        return new HashCodeBuilder(133, 331)
          .append(code)
          .toHashCode();
    }
}
