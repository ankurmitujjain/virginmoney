package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DTO representing a categorised category.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "CATEGORISED_CHARITY")
public class CategorisedCharity extends AssociationAuditAttributes implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -293847298347L;

    /** The primary key for the Categorised charity. */
    private Long id = null;

    /** The charity which is being categorised. */
    private Charity charity = null;

    /** The charity category for the categorised charity. */
    private CharityCategory charityCategory = null;

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
     * Gets the charity.
     * 
     * @return the charity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARITY_ID")
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity.
     * 
     * @param charity the charity to set
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Gets the charity category.
     * 
     * @return the charityCategory
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    public CharityCategory getCharityCategory() {
        return charityCategory;
    }

    /**
     * Sets the charity category.
     * 
     * @param charityCategory the charityCategory to set
     */
    public void setCharityCategory(CharityCategory charityCategory) {
        this.charityCategory = charityCategory;
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
        return new StringBuilder("CategorisedCharity ( ")
            .append("id = ").append(id).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
