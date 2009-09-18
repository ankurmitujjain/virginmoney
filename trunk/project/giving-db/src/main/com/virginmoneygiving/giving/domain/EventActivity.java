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
 * The data object represents the Event activity.
 * 
 * @author Sushant Sawant
 */

@Entity
@Table(name = "EVENT_ACTIVITY")
public class EventActivity extends AuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -1L;

    /** Primary key for event_activity table. */
    private Long id;

    /** Forgein key maps to get event from event table. */
    private Event event;

    /** Forgein key maps to code from ACTIVITY_TYPE. */
    private ActivityType activityType;

    /** Field to description of other activity whenever added. */
    private String otherActivityType;

    /**
     * Gets the id.
     * 
     * @return id EventActivity Id
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
     * @param id EventActivity Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the event.
     * 
     * @return event Event object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event.
     * 
     * @param event Event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Get the activity type.
     * 
     * @return activityType
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITY_TYPE_CODE")
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Set the activity type.
     * 
     * @param activityType activityType
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Gets the other activity type.
     * 
     * @return otherActivityType
     */
    @Column(name = "OTHER_ACTIVITY_TYPE")
    public String getOtherActivityType() {
        return otherActivityType;
    }

    /**
     * Sets the other activity type.
     * 
     * @param otherActivityType otherActivityType
     */
    public void setOtherActivityType(String otherActivityType) {
        this.otherActivityType = otherActivityType;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("EventActivity ( ").append("id = ").append(
                this.id).append(tab).append("activityType = ")
                .append(this.activityType).append(tab).append(
                        "otherActivityType = ").append(this.otherActivityType)
                .append(tab).append(super.toString()).append(" )").toString();
    }

} // end of class
