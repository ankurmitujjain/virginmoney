package com.virginmoneygiving.indexerservice.client.event;

import org.springframework.context.ApplicationContext;

import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Event;

/**
 * Unfortunately named class for Event events.
 *
 * @author ian.priest@opsera.com
 */
public class EventEvent extends IndexerEvent {

    /** Generated UID. Regenerate if class is updated. */
    private static final long serialVersionUID = -9157958776487292081L;

    /** The Event. */
    private Event event;
    
    /** The Activity. */
    private Charity charity;

    /**
     * Create a new event.
     *
     * @param source
     *            The source of the event
     * @param charity
     *          The charity joining or hosting the event. Null is allowed for ops-web open events.
     * @param event
     *            The event
     * @param eventType
     *            The event type
     */
    public EventEvent(ApplicationContext source,
        Charity charity, Event event, IndexerEventType eventType) {
        super(source, eventType);
        this.charity = charity;
        this.event = event;
    }

    /**
     * Getter for the Event.
     *
     * @return Event The event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @return the charity
     */
    public Charity getCharity() {
        return charity;
    }

}
