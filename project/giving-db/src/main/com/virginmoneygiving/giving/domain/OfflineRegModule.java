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
 * The data object that represents many to many relationship between Module and
 * OfflineRegStatus.
 * 
 * @author Mahesh Yerudkar
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "OFFLINE_REG_MODULE")
public class OfflineRegModule extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Primary key for Permitted Off line Registration Module. */
    private Long id;

    /** Module. */
    private Module module;

    /** OfflineRegStatus. */
    private OfflineRegStatus offlineRegStatus;

    /**
     * Gets the id.
     * 
     * @return the id.
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
     * @param id the id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for Module.
     * 
     * @return object of {@link Module}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODULE_CODE")
    public Module getModule() {
        return module;
    }

    /**
     * Sets the module.
     * 
     * @param module the Module to set.
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * Gets the offline reg status.
     * 
     * @return the OfflineRegStatus.
     */
    @ManyToOne
    @JoinColumn(name = "OFFLINE_REG_STATUS_CODE")
    public OfflineRegStatus getOfflineRegStatus() {
        return offlineRegStatus;
    }

    /**
     * Sets the offline reg status.
     * 
     * @param offlineRegStatus the OfflineRegStatus to set.
     */
    public void setOfflineRegStatus(OfflineRegStatus offlineRegStatus) {
        this.offlineRegStatus = offlineRegStatus;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("OfflineRegModule ( ").append("id = ").append(
                this.id).append(tab).append("module = ").append(this.module)
                .append(tab).append("offlineRegStatus = ").append(
                        this.offlineRegStatus).append(tab).append(
                        super.toString()).append(" )").toString();
    }
}
