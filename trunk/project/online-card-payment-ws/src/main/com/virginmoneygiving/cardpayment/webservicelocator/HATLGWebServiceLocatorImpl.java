package com.virginmoneygiving.cardpayment.webservicelocator;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.logicgroup.basic.service.messages.PaymentAPISoap;
import com.logicgroup.extended.service.messages.ExtendedPaymentAPISoap;
import com.virginmoneygiving.cardpayment.serviceproxy.AlertServiceProxy;
import com.virginmoneygiving.cardpayment.transport.TLGFailover;
import com.virginmoneygiving.cardpayment.transport.TLGWebServiceLocator;

/**
 * High Availability Web Service Locator for TLG Web Service API Ports.
 * <br/>Ports are created on demand (and then cached until failover).
 * <br/>Will alert on failover.
 * <br/>
 * <br/><b>The new TLG <i>Combined</i> API will simplify this greatly.</b>
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class HATLGWebServiceLocatorImpl implements TLGFailover,
        TLGWebServiceLocator {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(HATLGWebServiceLocatorImpl.class);

    //+constructor DI
    /** primary hostname. */
    private final String primaryHostname;

    /** secondary hostname. */
    private final String secondaryHostname;
    //-constructor DI

    //+Setter DI
    /** Alert service proxy for failover notifications. */
    @Resource
    private AlertServiceProxy alertServiceProxy;

    /** JaxWs delegate. */
    private TLGJaxWsHelper delegate;
    //-Setter DI

    /** Atomic reference to the active hostname. */
    private AtomicReference<String> activeHostname;

    /** Web Service client Port for payment API. */
    private AtomicReference<PaymentAPISoap> basicPort
        = new AtomicReference<PaymentAPISoap>();

    /** Web Service client Port for extended payment API. */
    private AtomicReference<ExtendedPaymentAPISoap> extendedPort
        = new AtomicReference<ExtendedPaymentAPISoap>();

    /**
     * Default constructor.
     * 
     * @param primaryHost the primary host
     * @param secondaryHost the secondary host
     */
    public HATLGWebServiceLocatorImpl(String primaryHost, String secondaryHost) {
        if (StringUtils.isEmpty(primaryHost)
                || StringUtils.isEmpty(secondaryHost)) {
            throw new IllegalArgumentException(
                    "Invalid configuration - constructor args must not be null/empty");
        }

        this.primaryHostname = primaryHost;
        this.secondaryHostname = secondaryHost;

        // set active hostname
        activeHostname = new AtomicReference<String>(primaryHost);
    }

    /**
     * Ensure we have all necessary dependencies.
     * <br/>This is only invoked by the container (as part of the bean life cycle).
     * <br/><i>Has package-private visibility for unit test access.</i>
     */
    @PostConstruct
    void afterPropertiesSet() {
	if (delegate == null) {
            throw new IllegalStateException("TLGJaxWSHelper is required");
        }
        if (alertServiceProxy == null) {
            throw new IllegalStateException("AlertServiceProxy is required");
        }
    }

    /** (@inheritDoc) */
    public void failover(Exception e) {
    	if (LOGGER.isInfoEnabled()) {
    	    LOGGER.info("Attached exception caused a TLG failover.", e);
    	}
        String currentActiveHostname = getActiveHostname();

        // if P->S; if S->P
        if (primaryHostname.equalsIgnoreCase(currentActiveHostname)) {
            String message = "Requesting failover from TLG Primary to TLG Secondary endpoint.";
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(message);
            }
            establishPorts(secondaryHostname);
            alertServiceProxy.raiseAlert(message);
        } else if (secondaryHostname.equalsIgnoreCase(currentActiveHostname)) {
            String message = "Requesting failover from TLG Secondary to TLG Primary";
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(message);
            }
            establishPorts(primaryHostname);
            alertServiceProxy.raiseAlert(message);
        } else {
            // "strange things are afoot at the Circle K"
            LOGGER.error("An invalid hostname (" + currentActiveHostname
                    + ")is reported as active!");
        }
    }

    /** (@inheritDoc) */
    public String getActiveHostname() {
        String returnString = activeHostname.get();

        return returnString;
    }

    /** (@inheritDoc) */
    public boolean isPrimary() {
        return primaryHostname.equalsIgnoreCase(getActiveHostname());
    }

    /** (@inheritDoc) */
    public boolean isPortInitialised() {
        return (basicPort.get() != null && extendedPort.get() != null);
    }

    /** (@inheritDoc) */
    public void setActiveHostname(String hostname) {
        if (StringUtils.isEmpty(hostname)) {
            throw new IllegalArgumentException("Hostname must be supplied");
        }

        // check if we've been passed garbage
        if (hostname.equalsIgnoreCase(getActiveHostname())) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("No failover action required as we're already on that host!");
            }
        } else if (hostname.equalsIgnoreCase(primaryHostname)) {
            String message = "TLG failover forced to primary (" + hostname + ")";
            LOGGER.warn(message);
            establishPorts(primaryHostname);
            alertServiceProxy.raiseAlert(message);
        } else if (hostname.equalsIgnoreCase(secondaryHostname)) {
            String message = "TLG failover forced to secondary (" + hostname + ")";
            LOGGER.warn(message);
            establishPorts(secondaryHostname);
            alertServiceProxy.raiseAlert(message);
        } else {
            String message =
                "TLG failover requested to unknown hostname (" + hostname + ")";
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    /** (@inheritDoc) */
    public PaymentAPISoap getBasicPort() {
        if (basicPort.get() == null) {
            establishPorts(getActiveHostname());
        }

	return basicPort.get();
    }

    /** (@inheritDoc) */
    public ExtendedPaymentAPISoap getExtendedPort() {
        if (extendedPort.get() == null) {
            establishPorts(getActiveHostname());
        }

        return extendedPort.get();
    }

    /**
     * Establish ports.
     * 
     * @param hostname the hostname
     */
    private synchronized void establishPorts(String hostname) {
        if (StringUtils.isEmpty(hostname)) {
            // shouldn't happen as this is a private call
            throw new IllegalArgumentException("Hostname must be supplied");
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Creating ports using " + hostname);
        }

        // set the basic port
        basicPort.set(delegate.getBasicPortByHostname(hostname));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Basic port created.");
        }

        // set the extended port
        extendedPort.set(delegate.getExtendedPortByHostname(hostname));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Extended port created.");
        }

        // update the active hostname
        activeHostname.set(hostname);
    }

    /**
     * Sets the delegate.
     * 
     * @param delegate the delegate to set
     */
    public void setDelegate(TLGJaxWsHelper delegate) {
        this.delegate = delegate;
    }

    /**
     * Sets the alert service proxy.
     * 
     * @param alertServiceProxy the alertServiceProxy to set
     */
    public void setAlertServiceProxy(AlertServiceProxy alertServiceProxy) {
        this.alertServiceProxy = alertServiceProxy;
    }
}
