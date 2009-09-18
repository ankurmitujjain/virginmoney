package com.virginmoneygiving.bamservice.client.listener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.virginmoneygiving.bamservice.client.event.BusinessActivityEvent;
import com.virginmoneygiving.bamservice.client.event.Event;
import com.virginmoneygiving.bamservice.client.jms.BusinessActivityJmsMessageSender;

/**
 * Listener that responds to the Business Activity Monitoring events, by sending
 * JMS message in the queue.
 *
 * @author Puneet Swarup
 */
public class BusinessActivityEventListener implements ApplicationListener {

    /** Logger instance. */
    private static final Logger LOGGER =
            Logger.getLogger(BusinessActivityEventListener.class);

    /** BAM JMS message sender. */
    private BusinessActivityJmsMessageSender jmsQueueSender;

    /** {@inheritDoc} */
    public void onApplicationEvent(final ApplicationEvent eventObject) {

        LOGGER.trace("BAMEventListener - onApplicationEvent " + eventObject);
        if (eventObject instanceof Event) {
            LOGGER.debug("Received event object : " + eventObject.toString());
            final BusinessActivityEvent businessActivityEvent =
                    mapEventToBusinessActivityEvent((Event) eventObject);
            jmsQueueSender.sendMessage(businessActivityEvent);
        }
    }

    /**
     * Map the {@link Event} object to {@link BusinessActivityEvent} object.
     *
     * @param event the {@link Event} object.
     * @return {@link BusinessActivityEvent} object.
     */
    private BusinessActivityEvent mapEventToBusinessActivityEvent(
            final Event event) {
        final BusinessActivityEvent businessActivityEvent =
                new BusinessActivityEvent();
        businessActivityEvent.setDate(event.getDate());
        businessActivityEvent.setTime(event.getTime());
        businessActivityEvent.setIpAddress(event.getIpAddress());
        businessActivityEvent.setUser(event.getUser());
        businessActivityEvent.setVmgAccount(event.getVmgAccount());
        businessActivityEvent.setEventType(event.getEventType());
        businessActivityEvent.setCorrelationId(event.getCorrelationId());
        businessActivityEvent.setOriginatingService(event.getOriginatingService());
        businessActivityEvent.setOriginatingServiceSubsystem(event.getOriginatingServiceSubsystem());
        businessActivityEvent.setSessionId(event.getSessionId());
        businessActivityEvent.setSystemTransactionId(event.getSystemTransactionId());
        return businessActivityEvent;
    }

    /**
     * Sets the jms queue sender.
     *
     * @param jmsQueueSender the jmsQueueSender to set
     */
    @Resource
    public void setJmsQueueSender(
            final BusinessActivityJmsMessageSender jmsQueueSender) {
        this.jmsQueueSender = jmsQueueSender;
    }
}
