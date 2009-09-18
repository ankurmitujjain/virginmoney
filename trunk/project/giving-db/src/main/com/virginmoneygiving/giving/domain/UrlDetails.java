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
 * Domain class to represent URL details like url, url type.
 * 
 * @author vikask
 */
@Entity
@Table(name = "URL_DETAILS")
public class UrlDetails extends AuditAttributes implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4304717565993187837L;

    /** The id. */
    private Long id;

    /** The url. */
    private String url;

    /** The url type. */
    private UrlType urlType;

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
     * Gets the url.
     * 
     * @return the url
     */
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     * 
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the url type.
     * 
     * @return the urlType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "URL_TYPE_CODE")
    public UrlType getUrlType() {
        if (urlType == null) {
            urlType = new UrlType();
            urlType.setCode("fundraiser");
        }
        return urlType;
    }

    /**
     * Sets the url type.
     * 
     * @param urlType the urlType to set
     */
    public void setUrlType(UrlType urlType) {
        this.urlType = urlType;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("UrlDetails ( ")
            .append("id = ").append(this.id).append(tab)
            .append("url = ").append(this.url).append(tab)
            .append("urlType = ").append(this.urlType).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
