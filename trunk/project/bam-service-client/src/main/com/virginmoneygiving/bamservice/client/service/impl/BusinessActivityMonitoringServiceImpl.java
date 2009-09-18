package com.virginmoneygiving.bamservice.client.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.virginmoneygiving.bamservice.client.event.ActivityEvent;
import com.virginmoneygiving.bamservice.client.event.Event;
import com.virginmoneygiving.bamservice.client.service.BusinessActivityMonitoringService;


/**
 * Implementation class for {@link BusinessActivityMonitoringService}.
 *
 * @author Puneet Swarup
 */
public class BusinessActivityMonitoringServiceImpl implements
        ApplicationContextAware, BusinessActivityMonitoringService {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(BusinessActivityMonitoringServiceImpl.class);

    /** The {@link ApplicationContext}. */
    private ApplicationContext context;

    /** {@inheritDoc} */
    public void logEvent(final ActivityEvent activityEvent) {

        final Date timeInstance = Calendar.getInstance().getTime();
        final String date = new SimpleDateFormat("dd-MM-yyyy").format(timeInstance);
        final String time = new SimpleDateFormat("HH-mm-ss-SSS").format(timeInstance);

        LOGGER.debug("BusinessActivityMonitoringServiceImpl.logEvent " + activityEvent);

        final Event event = new Event(context, activityEvent);
        event.setDate(date);
        event.setTime(time);
        context.publishEvent(event);
    }

    /**
     * Sets the application context.
     *
     * @param applicationContext the applicationContext to set
     */
    public void setApplicationContext(
            final ApplicationContext applicationContext) {
        this.context = applicationContext;
    }
}
