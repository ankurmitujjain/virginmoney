package com.virginmoneygiving.bamservice.client.event;

import java.io.Serializable;

import org.springframework.context.ApplicationEvent;

/**
 * The base class for an Event for Business Activity Monitoring message.
 *
 * @author Puneet Swarup
 */
public class Event extends ApplicationEvent implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -23475623456L;

    /** Date of event. */
    private String date;

    /** Time of event. */
    private String time;

    /** User whose action triggered event. */
    private String user;

    /** Ip address of the node of the user. */
    private String ipAddress;

    /** Session id. */
    private String sessionId;

    /** System Transaction Id. */
    private String systemTransactionId;

    /** Correlation Id. */
    private String correlationId;

    /** VMG account. */
    private String vmgAccount;

    /** Event type. */
    private BusinessActivityEventType eventType;

    /** Originating Service. */
    private OriginatingService originatingService;

    /** Originating service sub-system. */
    private OriginatingServiceSubsystem originatingServiceSubsystem;

    /**
     * Full constructor.
     *
     * @param asource the source
     * @param event the {@link ActivityEvent}
     */
    public Event(Object asource, ActivityEvent event) {
        super(asource);
        this.user = event.getUser();
        this.ipAddress = event.getIpAddress();
        this.sessionId = event.getSessionId();
        this.systemTransactionId = event.getSystemTransactionId();
        this.correlationId = event.getCorrelationId();
        this.vmgAccount = event.getVmgAccount();
        this.eventType = event.getEventType();
        this.originatingService = event.getOriginatingService();
        this.originatingServiceSubsystem = event.getOriginatingServiceSubsystem();
    }

    /**
     * Sets the application context.
     *
     * @param context the application Context.
     */
    public void setApplicationContext(Object context) {
        this.source = context;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time.
     *
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the ip address.
     *
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the ip address.
     *
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the session id.
     *
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the session id.
     *
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Gets the system transaction id.
     *
     * @return the systemTransactionId
     */
    public String getSystemTransactionId() {
        return systemTransactionId;
    }

    /**
     * Sets the system transaction id.
     *
     * @param systemTransactionId the systemTransactionId to set
     */
    public void setSystemTransactionId(String systemTransactionId) {
        this.systemTransactionId = systemTransactionId;
    }

    /**
     * Gets the correlation id.
     *
     * @return the correlationId
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the correlation id.
     *
     * @param correlationId the correlationId to set
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    /**
     * Gets the vmg account.
     *
     * @return the vmgAccount
     */
    public String getVmgAccount() {
        return vmgAccount;
    }

    /**
     * Sets the vmg account.
     *
     * @param vmgAccount the vmgAccount to set
     */
    public void setVmgAccount(String vmgAccount) {
        this.vmgAccount = vmgAccount;
    }

    /**
     * Gets the event type.
     *
     * @return the eventType
     */
    public BusinessActivityEventType getEventType() {
        return eventType;
    }

    /**
     * Sets the event type.
     *
     * @param eventType the eventType to set
     */
    public void setEventType(BusinessActivityEventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the originating service.
     *
     * @return the originatingService
     */
    public OriginatingService getOriginatingService() {
        return originatingService;
    }

    /**
     * Sets the originating service.
     *
     * @param originatingService the originatingService to set
     */
    public void setOriginatingService(OriginatingService originatingService) {
        this.originatingService = originatingService;
    }

    /**
     * Gets the originating service subsystem.
     *
     * @return the originatingServiceSubsystem
     */
    public OriginatingServiceSubsystem getOriginatingServiceSubsystem() {
        return originatingServiceSubsystem;
    }

    /**
     * Sets the originating service subsystem.
     *
     * @param originatingServiceSubsystem the originatingServiceSubsystem to set
     */
    public void setOriginatingServiceSubsystem(
            OriginatingServiceSubsystem originatingServiceSubsystem) {
        this.originatingServiceSubsystem = originatingServiceSubsystem;
    }

    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {

        final String tab = "    ";

        return new StringBuilder("Event ( ")
            .append("date = ").append(this.date).append(tab)
            .append("time = ").append(this.time).append(tab)
            .append("user = ").append(this.user).append(tab)
            .append("ipAddress = ").append(this.ipAddress).append(tab)
            .append("sessionId = ").append(this.sessionId).append(tab)
            .append("systemTransactionId = ").append(this.systemTransactionId).append(tab)
            .append("correlationId = ").append(this.correlationId).append(tab)
            .append("vmgAccount = ").append(this.vmgAccount).append(tab)
            .append("eventType = ").append(this.eventType).append(tab)
            .append("originatingService = ").append(this.originatingService).append(tab)
            .append("originatingServiceSubsystem = ").append(this.originatingServiceSubsystem).append(tab)
            .append(super.toString()).append(" )")
            .toString();
    }
}
