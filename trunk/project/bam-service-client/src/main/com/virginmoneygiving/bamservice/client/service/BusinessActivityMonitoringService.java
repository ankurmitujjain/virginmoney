package com.virginmoneygiving.bamservice.client.service;

import org.springframework.context.ApplicationContextAware;

import com.virginmoneygiving.bamservice.client.event.ActivityEvent;

/**
 * Service to raise Spring ApplicationEvent notifications for Business Audit
 * Monitoring.
 *
 * @author Puneet Swarup
 */
public interface BusinessActivityMonitoringService extends
        ApplicationContextAware {

    /**
     * Log Business Activity Monitoring Event.
     *
     * @param event the {@link ActivityEvent}
     */
    void logEvent(ActivityEvent event);
}
