package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;

import org.hibernate.envers.NotAudited;

import com.virginmoneygiving.giving.entitylisterer.ControlDataEntityListener;



/**
 * Class holding all the audit attributes.
 * <p>
 * All domain class requiring these audit attributes, would extend this class.
 * <p>
 * Whenever a transient / detached object is updated, the {@link AuditAttributes#preUpdate()}
 * is called and {@link AuditAttributes#updatedDateTime} is populated with
 * current time stamp.
 * 
 * @author Puneet Swarup
 */
@MappedSuperclass
@EntityListeners(ControlDataEntityListener.class)
public class AuditAttributes extends BaseAuditAttributes implements
        Serializable,AuditInterface {

    /** Serial version UID. */
    private static final long serialVersionUID = -7937225173659L;

    /** User creating this record. */
    private String createdUser = null;

    /** Process creating this record. */
    private String createdProcess = null;

    /** IP address creating this record. */
    private String createdIPAddress = null;

    /** Record updation date time. */
    private Timestamp updatedDateTime = null;

    /** User updating this record. */
    private String updatedUser = null;

    /** Process updating this record. */
    private String updatedProcess = null;

    /** IP address updating this record. */
    private String updatedIPAddress = null;

    /**
     * Method called before update of this or sub-class object.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedDateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
   }

    /**
     * Gets the created user.
     * 
     * @return the createdUser
     */
    @Column(name = "CREATED_USER", updatable = false)
    @NotAudited
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the created user.
     * 
     * @param createdUser the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * Gets the created process.
     * 
     * @return the createdProcess
     */
    @Column(name = "CREATED_PROCESS", updatable = false)
    @NotAudited
    public String getCreatedProcess() {
        return createdProcess;
    }

    /**
     * Sets the created process.
     * 
     * @param createdProcess the createdProcess to set
     */
    public void setCreatedProcess(String createdProcess) {
        this.createdProcess = createdProcess;
    }

    /**
     * Gets the created ip address.
     * 
     * @return the createdIPAddress
     */
    @Column(name = "CREATED_IP_ADDRESS", updatable = false)
    @NotAudited
    public String getCreatedIPAddress() {
        return createdIPAddress;
    }

    /**
     * Sets the created ip address.
     * 
     * @param createdIPAddress the createdIPAddress to set
     */
    public void setCreatedIPAddress(String createdIPAddress) {
        this.createdIPAddress = createdIPAddress;
    }

    /**
     * Gets the updated date time.
     * 
     * @return the updatedDateTime
     */
    @Column(name = "UPDATED_DATETIME")
    @NotAudited
    public Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }

    /**
     * Sets the updated date time.
     * 
     * @param updatedDateTime the updatedDateTime to set
     */
    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    /**
     * Gets the updated user.
     * 
     * @return the updatedUser
     */
    @Column(name = "UPDATED_USER")
    @NotAudited
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the updated user.
     * 
     * @param updatedUser the updatedUser to set
     */
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * Gets the updated process.
     * 
     * @return the updatedProcess
     */
    @Column(name = "UPDATED_PROCESS")
    @NotAudited
    public String getUpdatedProcess() {
        return updatedProcess;
    }

    /**
     * Sets the updated process.
     * 
     * @param updatedProcess the updatedProcess to set
     */
    public void setUpdatedProcess(String updatedProcess) {
        this.updatedProcess = updatedProcess;
    }

    /**
     * Gets the updated ip address.
     * 
     * @return the updatedIPAddress
     */
    @Column(name = "UPDATED_IP_ADDRESS")
    @NotAudited
    public String getUpdatedIPAddress() {
        return updatedIPAddress;
    }

    /**
     * Sets the updated ip address.
     * 
     * @param updatedIPAddress the updatedIPAddress to set
     */
    public void setUpdatedIPAddress(String updatedIPAddress) {
        this.updatedIPAddress = updatedIPAddress;
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
        return new StringBuilder(super.toString())
            .append(tab).append("createdUser = ").append(createdUser)
            .append(tab).append("createdProcess = ").append(createdProcess)
            .append(tab).append("createdIPAddress = ").append(createdIPAddress)
            .append(tab).append("updatedDateTime = ").append(updatedDateTime)
            .append(tab).append("updatedUser = ").append(updatedUser)
            .append(tab).append("updatedProcess = ").append(updatedProcess)
            .append(tab).append("updatedIPAddress = ").append(updatedIPAddress)
            .toString();
    }
}
