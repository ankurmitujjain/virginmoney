package com.virginmoneygiving.indexerservice.client.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * Base class for indexer events.
 *
 * @author ian.priest@opsera.com
 */
public class IndexerEvent extends ApplicationContextEvent {

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 7065547810313872317L;
    
    /** The action. */
    private IndexerEventType eventType;

    /**
     * Create a new event.
     *
     * @param source
     *            The source of the event
     * @param eventType
     *            The event type
     */
    public IndexerEvent(ApplicationContext source,
        IndexerEventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    /**
     * Getter for the eventType.
     *
     * @return eventType the indexer event that occurred
     */
    public IndexerEventType getEventType() {
        return eventType;
    }

}
