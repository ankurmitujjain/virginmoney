package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The Class Country.
 * 
 * @author Sejal Shah
 * @author Puneet Swarup - added audit attributes and toString.
 */

// both JPA and Hibernate Entity annotation is used for it defines mutability
// for the entity
@javax.persistence.Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "COUNTRY")
@Audited
public class Country extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 7046685465449287008L;

    /** iso code for country. */
    private String isoCode;

    /** Name for country. */
    private String name;

    /** currency for country. */
    private String currency;

    /** Currency symbol for country. */
    private String currencySymbol;

    /** Currency symbol placement for country. */
    private String currencySymbolPlacement;

    /** DisplayOrder for country. */
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
     * Get the name for country.
     * 
     * @return the name
     */
    @Column(name = "NAME")
    @NotAudited
    public String getName() {
        return name;
    }

    /**
     * Set the name for country.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the currency for country.
     * 
     * @return the currency
     */
    @Column(name = "CURRENCY")
    @NotAudited
    public String getCurrency() {
        return currency;
    }

    /**
     * Set the currency for country.
     * 
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Get the currencySymbol for country.
     * 
     * @return the currencySymbol
     */
    @Column(name = "CURRENCY_SYMBOL")
    @NotAudited
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * Set the currencySymbol for country.
     * 
     * @param currencySymbol the currencySymbol to set
     */
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * Get the currencySymbolPlacement for country.
     * 
     * @return the currencySymbolPlacement
     */
    @Column(name = "CURRENCY_SYMBOL_PLACEMENT")
    @NotAudited
    public String getCurrencySymbolPlacement() {
        if (currencySymbolPlacement == null) {
            currencySymbolPlacement = "Before";
        }
        return currencySymbolPlacement;
    }

    /**
     * Set the currencySymbolPlacement for country.
     * 
     * @param currencySymbolPlacement the currencySymbolPlacement to set
     */
    public void setCurrencySymbolPlacement(String currencySymbolPlacement) {
        this.currencySymbolPlacement = currencySymbolPlacement;
    }

    /**
     * Get the displayOrder for country.
     * 
     * @return the displayOrder
     */
    @Column(name = "DISPLAY_ORDER")
    @NotAudited
    public Long getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Set the displayOrder for country.
     * 
     * @param displayOrder the displayOrder to set
     */
    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
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
        return new StringBuilder("Country ( ").append("isoCode = ").append(
                isoCode).append(tab).append("name = ").append(name).append(tab)
                .append("currency = ").append(currency).append(tab).append(
                        "currencySymbol = ").append(currencySymbol).append(tab)
                .append("currencySymbolPlacement = ").append(
                        currencySymbolPlacement).append(tab).append(
                        "displayOrder = ").append(displayOrder).append(tab)
                .append(super.toString()).append(" )").toString();
    }
}
