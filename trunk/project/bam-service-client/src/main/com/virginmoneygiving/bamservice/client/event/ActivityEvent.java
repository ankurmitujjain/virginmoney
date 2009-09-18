package com.virginmoneygiving.bamservice.client.event;

import java.io.Serializable;

/**
 * The class to hold data from the Business activity event raising entities.
 *
 * @author Puneet Swarup
 */
public class ActivityEvent implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -23475623456L;

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
     * Full Constructor.
     *
     * @param auser the user
     * @param aipAddress the ip address
     * @param asessionId the session id
     * @param asystemTransactionId the system transaction id
     * @param acorrelationId the correlation id
     * @param avmgAccount the vmg account
     * @param aeventType the {@link BusinessActivityEventType}
     * @param aoriginatingService the {@link OriginatingService}
     * @param aoriginatingServiceSubsystem the {@link OriginatingServiceSubsystem}
     */
    public ActivityEvent(String auser, String aipAddress, String asessionId,
            String asystemTransactionId, String acorrelationId,
            String avmgAccount, BusinessActivityEventType aeventType,
            OriginatingService aoriginatingService,
            OriginatingServiceSubsystem aoriginatingServiceSubsystem) {
        super();
        this.user = auser;
        this.ipAddress = aipAddress;
        this.sessionId = asessionId;
        this.systemTransactionId = asystemTransactionId;
        this.correlationId = acorrelationId;
        this.vmgAccount = avmgAccount;
        this.eventType = aeventType;
        this.originatingService = aoriginatingService;
        this.originatingServiceSubsystem = aoriginatingServiceSubsystem;
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

        return new StringBuilder("ActivityEvent ( ")
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
