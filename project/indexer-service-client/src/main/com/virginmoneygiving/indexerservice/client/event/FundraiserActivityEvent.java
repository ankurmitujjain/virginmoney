package com.virginmoneygiving.indexerservice.client.event;

import org.springframework.context.ApplicationContext;

import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;

/**
 * Base class for FundraiserActivity events.
 *
 * @author ian.priest@opsera.com
 */
public class FundraiserActivityEvent extends IndexerEvent {

    /** Generated UID. Regenerate if class is updated. */
    private static final long serialVersionUID = -3207313187358046466L;

    /** The FundraiserActivity. */
    private FundraiserActivity fundraiserActivity;
    
    /** The Fundraiser. */
    private Fundraiser fundraiser;

    /**
     * Create a new event.
     *
     * @param source
     *            The source of the event
     * @param fundraiser TODO
     * @param fundraiserActivity
     *            The fundraiserActivity
     * @param eventType
     *            The event type
     */
    public FundraiserActivityEvent(ApplicationContext source,
        Fundraiser fundraiser, FundraiserActivity fundraiserActivity, IndexerEventType eventType) {
        super(source, eventType);
        this.fundraiser = fundraiser;
        this.fundraiserActivity = fundraiserActivity;
    }

    /**
     * Getter for the fundraiserActivity.
     *
     * @return FundraiserActivity The fundraiserActivity
     */
    public FundraiserActivity getFundraiserActivity() {
        return fundraiserActivity;
    }

    /**
     * @return the fundraiser
     */
    public Fundraiser getFundraiser() {
        return fundraiser;
    }

}
