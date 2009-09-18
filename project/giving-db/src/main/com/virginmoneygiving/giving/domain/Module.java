package com.virginmoneygiving.giving.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The data object that represents the module to be persisted.
 * 
 * @author Mahesh Yerudkar
 */
@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "MODULE")
public class Module extends BaseAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Code for module representation. */
    private String code;

    /** Module descriptive name. */
    private String moduleName;


    /**
     * Gets the code.
     * 
     * @return the code
     */
    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the module name.
     * 
     * @return the moduleName
     */
    @Column(name = "MODULE_NAME")
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the module name.
     * 
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("Module ( ")
            .append("code = ").append(this.code).append(tab)
            .append("moduleName = ").append(this.moduleName).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }

}
