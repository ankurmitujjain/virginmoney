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
 * DTO representing a custom field type.
 * 
 * @author Puneet Swarup
 */
@Entity
@Table(name = "CHARITY_CUSTOM_FIELD")
public class CharityCustomField extends AssociationAuditAttributes implements
        Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225224659L;

    /** Primary key for Charity custom field. */
    private Long id;

    /** Field label. */
    private String fieldLabel;

    /** Charity. */
    private Charity charity;

    /** Custom filed type. */
    private CustomFieldType customFieldType;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
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
     * Gets the field label.
     * 
     * @return the fieldLabel
     */
    @Column(name = "FIELD_LABEL")
    public String getFieldLabel() {
        return fieldLabel;
    }

    /**
     * Sets the field label.
     * 
     * @param fieldLabel the fieldLabel to set
     */
    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
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
     * Gets the custom field type.
     * 
     * @return the customFieldType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOM_FIELD_TYPE_CODE")
    public CustomFieldType getCustomFieldType() {
        return customFieldType;
    }

    /**
     * Sets the custom field type.
     * 
     * @param customFieldType the customFieldType to set
     */
    public void setCustomFieldType(CustomFieldType customFieldType) {
        this.customFieldType = customFieldType;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("CharityCustomField ( ")
            .append("id = ").append(this.id).append(tab)
            .append("fieldLabel = ").append(this.fieldLabel).append(tab)
            .append("charity = ").append(this.charity).append(tab)
            .append("customFieldType = ").append(this.customFieldType).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
