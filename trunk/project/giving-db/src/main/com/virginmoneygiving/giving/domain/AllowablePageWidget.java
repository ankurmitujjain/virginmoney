package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class AllowablePageWidget.
 * 
 * @author Rahul J
 */
@Entity
@Table(name = "ALLOWABLE_PAGE_WIDGET")
public class AllowablePageWidget extends BaseAuditAttributes implements Serializable {

    /** Serial version uid. */
    private static final long serialVersionUID = 4270334960198736050L;

    /** Attribute id. */
    private Long id;

    /** Attribute pageType. */
    private PageType pageType;

    /** Attribute PageWidgetType. */
    private PageWidgetType pageWidgetType;

    /** Attribute start date time. */
    private Timestamp startDateTime = null;
    
    /** Attribute end date time. */
    private Timestamp endDateTime = null;

    /**
     * Returns the unique identification for {@link TrusteeDetails}.
     * 
     * @return id the unique identification for {@link TrusteeDetails}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identification for {@link TrusteeDetails}.
     * 
     * @param id new value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the page type.
     * 
     * @return pageType
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PAGE_TYPE_CODE")
    public PageType getPageType() {
        return pageType;
    }

    /**
     * Sets the page type.
     * 
     * @param pageType the pageType to set
     */
    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }
    
    /**
     * Gets the page widget type.
     * 
     * @return pageWidgetType
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PAGE_WIDGET_TYPE_CODE")
    public PageWidgetType getPageWidgetType() {
        return pageWidgetType;
    }

    /**
     * Sets the page widget type.
     * 
     * @param pageWidgetType the pageWidgetType to set
     */
    public void setPageWidgetType(PageWidgetType pageWidgetType) {
        this.pageWidgetType = pageWidgetType;
    }
    
    /**
     * Gets the start date time.
     * 
     * @return the startDateTime
     */
    @Column(name = "START_DATETIME")
    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the start date time.
     * 
     * @param startDateTime the startDateTime to set
     */
    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    /**
     * Gets the end date time.
     * 
     * @return the endDateTime
     */
    @Column(name = "END_DATETIME")
    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the end date time.
     * 
     * @param endDateTime the endDateTime to set
     */
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
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
        return new StringBuilder("AllowablePageWidget ( ").append("id = ").append(id)
                .append(tab).append("pageType = ").append(pageType)
                .append(tab).append("pageWidgetType = ").append(pageWidgetType)
                .append(tab).append("startDateTime = ").append(startDateTime)
                .append(tab).append("endDateTime = ").append(endDateTime)
                .append(tab).append(super.toString()).append(" )").toString();
    }

}
