package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The Class Location.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */

//both JPA and Hibernate Entity annotation is used for it defines mutability for the entity
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "LOCATION")
@Audited
public class Location extends AuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -1979726999869930704L;

    /** iso code for location. */
    private String isoCode;

    /** Description for charity status. */
    private String description;
    
    /** Description for display order. */
    private Long displayOrder;
 

    /**
     * Get the ISO code for country.
     * 
     * @return the isoCde
     */
    @Id
    @Column(name = "ISO_CODE")
    @NotAudited
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * Set the ISO code for country.
     * 
     * @param isoCode the isoCde to set
     */
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
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
     * Gets the display order.
     * 
     * @return the displayOrder
     */
    @Column(name = "DISPLAY_ORDER")
    @NotAudited
    public Long getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the display order.
     * 
     * @param displayOrder the displayOrder to set
     */
    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
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
        return new StringBuilder("Location ( ")
            .append("isoCode = ").append(isoCode).append(tab)
            .append("description = ").append(description).append(tab)
            .append("displayOrder = ").append(displayOrder).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
