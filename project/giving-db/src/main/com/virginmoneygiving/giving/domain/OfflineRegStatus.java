package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The data object that represents the various statuses for off line
 * registration module.
 * 
 * @author Mahesh Yerudkar
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "OFFLINE_REG_STATUS")
public class OfflineRegStatus extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Code for off line registration module. */
    private String code;

    /** off line registration module code descriptive. */
    private String status;


    /**
     * Gets the code.
     * 
     * @return the code.
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the status.
     * 
     * @return the description name for code.
     */
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
