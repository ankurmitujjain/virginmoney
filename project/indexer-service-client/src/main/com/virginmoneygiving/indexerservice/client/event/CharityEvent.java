package com.virginmoneygiving.indexerservice.client.event;

import org.springframework.context.ApplicationContext;

import com.virginmoneygiving.giving.domain.Charity;

/**
 * Base class for Charity events.
 *
 * @author ian.priest@opsera.com
 */
public class CharityEvent extends IndexerEvent {

    /** Generated UID. Regenerate if class is updated. */
    private static final long serialVersionUID = -4762524459536821522L;

    /** The Charity. */
    private Charity charity;

    /**
     * Create a new event.
     *
     * @param source
     *            The source of the event
     * @param charity
     *            The charity
     * @param eventType
     *            The event type
     */
    public CharityEvent(ApplicationContext source,
        Charity charity, IndexerEventType eventType) {
        super(source, eventType);
        this.charity = charity;
    }

    /**
     * Getter for the registered charity.
     *
     * @return Charity The charity
     */
    public Charity getCharity() {
        return charity;
    }

}
