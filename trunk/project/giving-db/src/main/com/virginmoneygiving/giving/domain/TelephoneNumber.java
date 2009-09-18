package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * The data object that represents the Telephone number data to be persisted.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */

@Entity
@Table(name = "TELEPHONE_NUMBER")
@Audited
public class TelephoneNumber extends EnversAuditAttributes implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 2705833300964726522L;

    /** Attribute id. */
    private Long id;

    /** Attribute lineTypeInd. */
    private String lineTypeInd;

    /** Attribute mainInd. */
    private String mainInd;

    /** Attribute presentationNumber. */
    private String presentationNumber;

    /** Attribute dialableNumber. */
    private String dialableNumber;

    /**
     * Gets the id.
     * 
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Audited
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id new value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Telephone type indicator.
     * <p>
     * L = Land line M = Mobile F = Fax
     * 
     * @return lineTypeInd
     */
    @Column(name = "LINE_TYPE_IND")
    @Audited
    public String getLineTypeInd() {
        return lineTypeInd;
    }

    /**
     * Sets the line type ind.
     * 
     * @param lineTypeInd new value for lineTypeInd
     */
    public void setLineTypeInd(String lineTypeInd) {
        this.lineTypeInd = lineTypeInd;
    }

    /**
     * Y = Main contact number N = Not main contact number.
     * 
     * @return mainInd
     */
    @Column(name = "MAIN_IND")
    @Audited
    public String getMainInd() {
        return mainInd;
    }

    /**
     * Sets the main ind.
     * 
     * @param mainInd new value for mainInd
     */
    public void setMainInd(String mainInd) {
        this.mainInd = mainInd;
    }

    /**
     * Gets the presentation number.
     * 
     * @return presentationNumber
     */
    @Column(name = "PRESENTATION_NUMBER")
    @Audited
    public String getPresentationNumber() {
        return presentationNumber;
    }

    /**
     * Sets the presentation number.
     * 
     * @param presentationNumber new value for presentationNumber
     */
    public void setPresentationNumber(String presentationNumber) {
        this.presentationNumber = presentationNumber;
    }

    /**
     * The presentation number with all non numeric characters removed.
     * 
     * @return dialableNumber
     */
    @Column(name = "DIALABLE_NUMBER")
    @Audited
    public String getDialableNumber() {
        return dialableNumber;
    }

    /**
     * Sets the dialable number.
     * 
     * @param dialableNumber new value for dialableNumber
     */
    public void setDialableNumber(String dialableNumber) {
        this.dialableNumber = dialableNumber;
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
        return new StringBuilder("TelephoneNumber ( ")
            .append("id = ").append(id).append(tab)
            .append("lineTypeInd = ").append(lineTypeInd).append(tab)
            .append("mainInd = ").append(mainInd).append(tab)
            .append("presentationNumber = ").append(presentationNumber).append(tab)
            .append("dialableNumber = ").append(dialableNumber).append(tab)
            .append(super.toString())
            .append(" )").toString();
    }
}
