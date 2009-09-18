package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: afensome
 * Date: 25-Feb-2009
 * Time: 16:13:17
 * To change this template use File | Settings | File Templates.
 * 
 * @author Puneet Swarup - extended this object from
 * {@link AuditAttributes} to maintain audit information
 */
@Entity
@Table(name = "PAGE_WIDGET", uniqueConstraints=
    @UniqueConstraint(columnNames={"PAGE_ID", "PAGE_WIDGET_TYPE_CODE"}))
public class PageWidget implements Serializable  {
    
    /** Default Id. */
    private static final long serialVersionUID = 1L;
    
    /** The id. */
    private Long id;
    
    /** The page. */
    private Page page;
    
    /** The widget type. */
    private PageWidgetType widgetType;
   
 
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
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the page.
     * 
     * @return the page
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAGE_ID")
    public Page getPage() {
        return page;
    }

    /**
     * Sets the page.
     * 
     * @param page the new page
     */
    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * Gets the widget type.
     * 
     * @return the widget type
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAGE_WIDGET_TYPE_CODE")
    public PageWidgetType getWidgetType() {
        return widgetType;
    }

    /**
     * Sets the widget type.
     * 
     * @param widgetType the new widget type
     */
    public void setWidgetType(PageWidgetType widgetType) {
        this.widgetType = widgetType;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // equal iff widget type equals and page equals
        if (obj instanceof PageWidget == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PageWidget rhs = (PageWidget) obj;
        return new EqualsBuilder()
            .append(page, rhs.page)
            .append(widgetType, rhs.widgetType)
            .isEquals();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(131, 231)
          .append(page)
          .append(widgetType)
          .toHashCode();
    }

    
 }
