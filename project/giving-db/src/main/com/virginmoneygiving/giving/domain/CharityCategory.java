package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DTO representing a charity category.
 * 
 * @author Puneet Swarup
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "CHARITY_CATEGORY")
public class CharityCategory extends AuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -123645234L;

    /** The id for charity category. */
    private Long id = null;

    /** The code for the charity category. */
    private String categoryCode = null;

    /** The title for the charity category. */
    private String categoryTitle = null;

    /** The image location for the charity category. */
    private String categoryImageLocation = null;

    /** The code for the charity sub-category. */
    private String subcategoryCode = null;

    /** The title for the charity sub-category. */
    private String subcategoryTitle = null;

    /** The image location for the charity sub-category. */
    private String subcategoryImageLocation = null;

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
     * Gets the category code.
     * 
     * @return the categoryCode
     */
    @Column(name = "CATEGORY_CODE")
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets the category code.
     * 
     * @param categoryCode the categoryCode to set
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * Gets the category title.
     * 
     * @return the categoryTitle
     */
    @Column(name = "CATEGORY_TITLE")
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * Sets the category title.
     * 
     * @param categoryTitle the categoryTitle to set
     */
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    /**
     * Gets the category image location.
     * 
     * @return the categoryImageLocation
     */
    @Column(name = "CATEGORY_IMAGE_LOCATION")
    public String getCategoryImageLocation() {
        return categoryImageLocation;
    }

    /**
     * Sets the category image location.
     * 
     * @param categoryImageLocation the categoryImageLocation to set
     */
    public void setCategoryImageLocation(String categoryImageLocation) {
        this.categoryImageLocation = categoryImageLocation;
    }

    /**
     * Gets the subcategory code.
     * 
     * @return the subcategoryCode
     */
    @Column(name = "SUBCATEGORY_CODE")
    public String getSubcategoryCode() {
        return subcategoryCode;
    }

    /**
     * Sets the subcategory code.
     * 
     * @param subcategoryCode the subcategoryCode to set
     */
    public void setSubcategoryCode(String subcategoryCode) {
        this.subcategoryCode = subcategoryCode;
    }

    /**
     * Gets the subcategory title.
     * 
     * @return the subcategoryTitle
     */
    @Column(name = "SUBCATEGORY_TITLE")
    public String getSubcategoryTitle() {
        return subcategoryTitle;
    }

    /**
     * Sets the subcategory title.
     * 
     * @param subcategoryTitle the subcategoryTitle to set
     */
    public void setSubcategoryTitle(String subcategoryTitle) {
        this.subcategoryTitle = subcategoryTitle;
    }

    /**
     * Gets the subcategory image location.
     * 
     * @return the subcategoryImageLocation
     */
    @Column(name = "SUBCATEGORY_IMAGE_LOCATION")
    public String getSubcategoryImageLocation() {
        return subcategoryImageLocation;
    }

    /**
     * Sets the subcategory image location.
     * 
     * @param subcategoryImageLocation the subcategoryImageLocation to set
     */
    public void setSubcategoryImageLocation(String subcategoryImageLocation) {
        this.subcategoryImageLocation = subcategoryImageLocation;
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
        return new StringBuilder("CharityCategory ( ")
            .append("id = ").append(id).append(tab)
            .append("categoryCode = ").append(categoryCode).append(tab)
            .append("categoryTitle = ").append(categoryTitle).append(tab)
            .append("categoryImageLocation = ").append(categoryImageLocation).append(tab)
            .append("subcategoryCode = ").append(subcategoryCode).append(tab)
            .append("subcategoryTitle = ").append(subcategoryTitle).append(tab)
            .append("subcategoryImageLocation = ").append(subcategoryImageLocation).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
