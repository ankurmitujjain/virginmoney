package com.virginmoneygiving.cardpayment.event.listener;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import com.virginmoneygiving.cardpayment.event.AuthorisationErrorEvent;
import com.virginmoneygiving.cardpayment.event.BasePaymentEvent;
import com.virginmoneygiving.cardpayment.event.ValidationErrorEvent;
import com.virginmoneygiving.cardpayment.serviceproxy.AlertServiceProxy;

/**
 * Raises alerts for relevant events. This class implements the Observer pattern
 * using the Spring ApplicationListener interface. <br>
 * The Spring container will automatically register any ApplicationListener
 * beans that are declared within the Spring context.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class AlertingEventListener implements ApplicationListener {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(AlertingEventListener.class);

    /** The alert service. */
    @Resource
    private AlertServiceProxy alertService;

    /** {@inheritDoc}*/
    public void onApplicationEvent(ApplicationEvent event) {
        // check event type (registered listeners will receive every App Event)
        if (event instanceof ValidationErrorEvent) {
            String message = ((ValidationErrorEvent) event).getMessage();
            String guid = ((ValidationErrorEvent) event).getGuid();
            
            try {
                // invoke alert service
                alertService.raiseAlert("GUID: " + guid + "; " + message);
            } catch (Exception e) {
                // catch, log & swallow as we're an observer
                LOGGER.error("Error raising alert [ValidationErrorEvent" + 
                        " - GUID: " + guid + "; message: " + message + "]", e);
            }
        } else if (event instanceof AuthorisationErrorEvent) {
            String message = ((AuthorisationErrorEvent) event).getMessage();
            String guid = ((AuthorisationErrorEvent) event).getGuid();
            
            try {
                // invoke alert service
                alertService.raiseAlert("GUID: " + guid + "; " + message);
            } catch (Exception e) {
                // catch, log & swallow as we're an observer
                LOGGER.error("Error raising alert [AuthorisationErrorEvent" + 
                        " - GUID: " + guid + "; message: " + message + "]", e);
            }
        } else if (event instanceof BasePaymentEvent) {
            if (LOGGER.isDebugEnabled()) {
                String message = ((BasePaymentEvent) event).getMessage();
                String guid = ((BasePaymentEvent) event).getGuid();

                LOGGER.debug("Not raising alert for event [" +
                        event.getClass().getSimpleName() + " - GUID: " +
                        guid + "; message: " + message + "]");
            }
        }
    }

    /**
     * Sets the alert service.
     * 
     * @param alertService the alertService to set
     */
    public void setAlertService(AlertServiceProxy alertService) {
        this.alertService = alertService;
    }
}
