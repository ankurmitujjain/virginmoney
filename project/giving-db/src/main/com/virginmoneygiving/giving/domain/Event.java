package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The data object that represents the Event data to be persisted.
 * 
 * @author Esakki Yadav.
 * @author Puneet Swarup - added audit attributes and toString.
 * @author Saurabh Herwadkar - added variables and JPA annotations for EVT use
 * cases
 * @author Sushant Sawant - added new column time_free_text
 */
@Entity
@Table(name = "EVENT")
@Audited
public class Event extends EnversAuditAttributes implements Serializable {

    /** serial version UID. */
    private static final long serialVersionUID = -3608364457419720217L;

    /** Primary key for Address. */
    private Long id;

    /** Event Name. */
    private String name;

    /** Event Description. */
    private String description;

    /** Date and Time of the Event. */
    private Timestamp eventDatetime;

    /** Event Description. */
    private String eventTimeText;

    /** Open Date fot the Event. */
    private Timestamp openDate;

    /** Expiry Date of the Event. */
    private Timestamp expiryDate;

    /** Location for the Event. */
    private Location location;

    /** Event status for this event. */
    private EventStatus eventStatus;

    /** Joining instructions for this event. */
    private String joiningInstructions;

    /** logoUrl for this event. */
    private String logoUrl;

    /** Website URL for this event. */
    private String websiteUrl;

    /** Event contact name. */
    private String contactName;

    /** Event contact phone. */
    private String contactPhone;

    /** Event contact email address. */
    private String contactEmailAddress;

    /** Maximum charities allowed in this event. */
    private Integer maximumCharities;

    /** Event contact addrress. */
    private Address contactAddress;

    /** Get activites asscoaited with this event. */
    private Set<EventActivity> eventActivities;

    /** Get charitable activites asscoaited with this event. */
    private Set<CharitableActivity> charitableActivities;

    /** This property for fundraising split is there or not. */
    private String fundraiserSplitOverrideInd;

    /** This property indicates whether this event is created by operation user. */
    private String operationalEventInd = "N";

    /** Event previous status for this event. */
    private EventStatus eventPrevStatus;    
    
    /**
     * Gets the id.
     * 
     * @return Id EventId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @NotAudited
    public Long getId() {
        return id;
    }

    /**
     * Set the id for Event.
     * 
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     * 
     * @return name event name
     */
    @Column(name = "NAME")
    @NotAudited
    public String getName() {
        return name;
    }

    /**
     * Set the Event Name.
     * 
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location.
     * 
     * @return the locationId
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ISO_CODE")
    @NotAudited
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location.
     * 
     * @param location the locationId to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the description.
     * 
     * @return the Description
     */
    @Column(name = "DESCRIPTION" , columnDefinition = "text")
    @NotAudited
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description event description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the event datetime.
     * 
     * @return the eventDatetime.
     */
    @Column(name = "EVENT_DATETIME")
    @NotAudited
    public Timestamp getEventDatetime() {
        return eventDatetime;
    }

    /**
     * Sets the event datetime.
     * 
     * @param eventDatetime Date and Time of the Event.
     */
    public void setEventDatetime(Timestamp eventDatetime) {
        this.eventDatetime = eventDatetime;
    }

    /**
     * Gets the event time text.
     * 
     * @return eventTimeText Event Time in free text format
     */
    @Column(name = "EVENT_TIME_TEXT")
    @NotAudited
    public String getEventTimeText() {
        return eventTimeText;
    }

    /**
     * Sets the event time text.
     * 
     * @param eventTimeText Event Time in free text format
     */
    public void setEventTimeText(String eventTimeText) {
        this.eventTimeText = eventTimeText;
    }

    /**
     * Gets the open date.
     * 
     * @return the openDate.
     */
    @Column(name = "OPEN_DATETIME")
    @NotAudited
    public Timestamp getOpenDate() {
        return openDate;
    }

    /**
     * Sets the open date.
     * 
     * @param openDate Open Date for the Event..
     */
    public void setOpenDate(Timestamp openDate) {
        this.openDate = openDate;
    }

    /**
     * Gets the expiry date.
     * 
     * @return the expiryDate.
     */
    @Column(name = "EXPIRY_DATETIME")
    @NotAudited
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date.
     * 
     * @param expiryDate Expiry Date of the Event..
     */
    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the eventStatus
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_STATUS_CODE")
    @NotAudited
    public EventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * Sets the event status.
     * 
     * @param eventStatus the eventStatus to set
     */
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * Gets the joining instructions.
     * 
     * @return the joiningInstructions
     */
    @Column(name = "JOINING_INSTRUCTIONS" , columnDefinition = "text")
    @NotAudited
    public String getJoiningInstructions() {
        return joiningInstructions;
    }

    /**
     * Sets the joining instructions.
     * 
     * @param joiningInstructions the joiningInstructions to set
     */
    public void setJoiningInstructions(String joiningInstructions) {
        this.joiningInstructions = joiningInstructions;
    }

    /**
     * Gets the logo url.
     * 
     * @return the logoUrl
     */

    @Column(name = "LOGO_URL")
    @NotAudited
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Sets the logo url.
     * 
     * @param logoUrl the logoUrl to set
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * Gets the website url.
     * 
     * @return the websiteUrl
     */

    @Column(name = "WEBSITE_URL")
    @NotAudited
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * Sets the website url.
     * 
     * @param websiteUrl the websiteUrl to set
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * Gets the contact name.
     * 
     * @return the contactName
     */
    @Column(name = "CONTACT_NAME")
    @NotAudited
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name.
     * 
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the contact phone.
     * 
     * @return the contactPhone
     */

    @Column(name = "CONTACT_TELEPHONE")
    @NotAudited
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the contact phone.
     * 
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Gets the contact email address.
     * 
     * @return the contactEmailAddress
     */
    @Column(name = "CONTACT_EMAIL_ADDRESS")
    @NotAudited
    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    /**
     * Sets the contact email address.
     * 
     * @param contactEmailAddress the contactEmailAddress to set
     */
    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    /**
     * Gets the contact address.
     * 
     * @return the contactAddress
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTACT_ADDRESS_ID")
    @NotAudited
    public Address getContactAddress() {
        return contactAddress;
    }

    /**
     * Sets the contact address.
     * 
     * @param contactAddress the contactAddress to set
     */
    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * Gets the maximum charities.
     * 
     * @return the maximumCharitites
     */
    @Column(name = "MAXIMUM_CHARITIES")
    @NotAudited
    public Integer getMaximumCharities() {
        return maximumCharities;
    }

    /**
     * Sets the maximum charities.
     * 
     * @param maximumCharities the maximumCharitites to set
     */
    public void setMaximumCharities(Integer maximumCharities) {
        this.maximumCharities = maximumCharities;
    }

    /**
     * @return the eventActivities
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    @NotAudited
    public Set<EventActivity> getEventActivities() {
        return eventActivities;
    }

    /**
     * Sets the event activities.
     * 
     * @param eventActivities the eventActivities to set
     */
    public void setEventActivities(Set<EventActivity> eventActivities) {
        this.eventActivities = eventActivities;
    }

    /**
     * Gets the charitable activities.
     * 
     * @return the charitableActivities
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    @NotAudited
    public Set<CharitableActivity> getCharitableActivities() {
        return charitableActivities;
    }

    /**
     * Sets the charitable activities.
     * 
     * @param charitableActivities the charitableActivities to set
     */
    public void setCharitableActivities(
            Set<CharitableActivity> charitableActivities) {
        this.charitableActivities = charitableActivities;
    }

    /**
     * Gets the fundraiser split override ind.
     * 
     * @return the fundraiserSplitOverrideInd
     */
    @Column(name = "FUNDRAISER_SPLIT_OVERRIDE_IND")
    @NotAudited
    public String getFundraiserSplitOverrideInd() {
        return fundraiserSplitOverrideInd;
    }

    /**
     * Sets the fundraiser split override ind.
     * 
     * @param fundraiserSplitOverrideInd the fundraiserSplitOverrideInd to set
     */
    public void setFundraiserSplitOverrideInd(String fundraiserSplitOverrideInd) {
        this.fundraiserSplitOverrideInd = fundraiserSplitOverrideInd;
    }

    /**
     * Gets the operational event ind.
     * 
     * @return the operationalEventInd
     */
    @Column(name = "OPERATIONAL_EVENT_IND")
    @NotAudited
    public String getOperationalEventInd() {
        if (operationalEventInd == null) {
            operationalEventInd = "N";
        }
        return operationalEventInd;
    }

    /**
     * Sets the operational event ind.
     * 
     * @param operationalEventInd the operationalEventInd to set
     */
    public void setOperationalEventInd(String operationalEventInd) {
        this.operationalEventInd = operationalEventInd;
    }

    /**
     * @return the eventPrevStatus
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_PREV_STATUS_CODE")
    @NotAudited
    public EventStatus getEventPrevStatus() {
        return eventPrevStatus;
    }

    /**
     * Sets the event prev status.
     * 
     * @param eventPrevStatus the eventPrevStatus to set
     */
    public void setEventPrevStatus(EventStatus eventPrevStatus) {
        this.eventPrevStatus = eventPrevStatus;
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
        return new StringBuilder("Event ( ").append("id = ").append(id).append(
                tab).append("name = ").append(name).append(tab).append(
                "description = ").append(description).append(tab).append(
                "eventDatetime = ").append(eventDatetime).append(tab).append(
                "eventTimeText = ").append(eventTimeText).append(tab).append(
                "openDate = ").append(openDate).append(tab).append(
                "expiryDate = ").append(expiryDate).append(tab).append(
                joiningInstructions).append(tab).append("logoUrl= ").append(
                logoUrl).append(tab).append("websiteUrl = ").append(websiteUrl)
                .append(tab).append("contactName = ").append(contactName)
                .append(tab).append("contactPhone = ").append(contactPhone)
                .append(tab).append("contactEmailAddress = ").append(
                        contactEmailAddress).append(tab).append(
                        "contactAddress = ").append(contactAddress).append(tab)
                .append("maximumCharities = ").append(maximumCharities).append(tab)
                .append("location = ").append(location).append(tab)
                .append("fundraiserSplitOverrideInd = ").append(
                        fundraiserSplitOverrideInd).append(super.toString())
                .append(tab).append("operationalEventInd = ").append(
                        operationalEventInd).append(super.toString())
                .append(" )").toString();
    }

} // end of class
