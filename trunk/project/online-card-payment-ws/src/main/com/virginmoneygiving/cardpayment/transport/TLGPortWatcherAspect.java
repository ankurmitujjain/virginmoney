package com.virginmoneygiving.cardpayment.transport;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Watch the Web Service ports for exceptions that should cause a failover.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
@Aspect
public class TLGPortWatcherAspect {

    /** logger instance. */
    private static final Logger LOGGER = Logger.getLogger(TLGPortWatcherAspect.class);

    /** Primary host for failover. */
    private String primaryHost;

    /** Secondary host for failover. */
    private String secondaryHost;

    /** The failover. */
    private TLGFailover failover;

    /**
     * A point cut declaration.
     */
    @Pointcut("within(com.logicgroup..*)")
    private void inTLG() {}

    /**
     * A point cut declaration.
     */
    @Pointcut("@annotation(javax.jws.WebMethod)")
    private void anyWebMethod() {}

    /**
     * <b>Pointcut expression has to cover all web methods on
     * (Extended)PaymentAPISoap</b>.
     * 
     * @param t the t
     */
    @AfterThrowing("anyWebMethod() && inTLG()")
    public void doFailover(Throwable t) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Throwable caught by TLG Port Watcher.", t);
        }

        // TODO: examine exception (the ports can only throw runtime exceptions)
/* ian.priest@opsera.co 14/05/09
 * Commented out in response to whizzable 13873, failed Fortify review.
 * Flag should be re-instated when appropriate exception policy has been decided.
        boolean failoverRequired = true;

        // does it require a failover
        if (failoverRequired) {
  */
            // determine which side to fall
            if (failover.isPrimary()) {
            	if (LOGGER.isInfoEnabled()) {
            	    LOGGER.info("Requesting failover to secondary host");
            	}
                // should we use the failover(exception) method?
                failover.setActiveHostname(secondaryHost);
            } else {
            	if (LOGGER.isInfoEnabled()) {
            	    LOGGER.info("Requesting failover to primary host");
            	}
                failover.setActiveHostname(primaryHost);
            }
/* Commented to balance braces - see above
	}
  */
    }

    /**
  * Sets the primary host.
  * 
  * @param primaryHost the primaryHost to set
  */
    public void setPrimaryHost(String primaryHost) {
        this.primaryHost = primaryHost;
    }

    /**
     * Sets the secondary host.
     * 
     * @param secondaryHost the secondaryHost to set
     */
    public void setSecondaryHost(String secondaryHost) {
        this.secondaryHost = secondaryHost;
    }

    /**
     * Sets the failover.
     * 
     * @param failover the failover to set
     */
    public void setFailover(TLGFailover failover) {
        this.failover = failover;
    }
}
