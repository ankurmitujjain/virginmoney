package com.virginmoneygiving.indexerservice.client.event.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.indexerservice.client.event.CharityEvent;
import com.virginmoneygiving.indexerservice.client.event.EventEvent;
import com.virginmoneygiving.indexerservice.client.event.FundraiserActivityEvent;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventService;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventType;

/**
 * Simple service to raise Spring ApplicationEvent notifications.
 *
 * @author Ian Priest
 */
public class IndexerEventServiceImpl implements ApplicationContextAware,
    IndexerEventService {

    /** The application context. */
    private ApplicationContext ctx;

    /**
     * Setter for the application context.
     *
     * @param ctx
     *            the application context
     */
    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * {@inheritDoc}
     */
    public void publishFundraiserActivityDeletedEvent(
        Fundraiser fundraiser, FundraiserActivity fundraiserActivity) {

        FundraiserActivityEvent fundraiserActivityEvent =
            new FundraiserActivityEvent(ctx, fundraiser,
                fundraiserActivity, IndexerEventType.DELETE);
        ctx.publishEvent(fundraiserActivityEvent);
    }

    /**
     * {@inheritDoc}
     */
    public void publishFundraiserActivitySavedEvent(
        Fundraiser fundraiser, FundraiserActivity fundraiserActivity) {

        FundraiserActivityEvent fundraiserActivityEvent =
            new FundraiserActivityEvent(ctx, fundraiser,
                fundraiserActivity, IndexerEventType.UPDATE);
        ctx.publishEvent(fundraiserActivityEvent);
    }

    /**
     * {@inheritDoc}
     */
    public void publishCharitySavedEvent(Charity charity) {

        CharityEvent charityEvent =
            new CharityEvent(ctx, charity, IndexerEventType.UPDATE);
        ctx.publishEvent(charityEvent);
    }

    /**
     * {@inheritDoc}
     */
    public void publishCharityDeletedEvent(Charity charity) {

        CharityEvent charityEvent =
            new CharityEvent(ctx, charity, IndexerEventType.DELETE);
        ctx.publishEvent(charityEvent);
    }

    /**
     * {@inheritDoc}
     */
    public void publishEventDeletedEvent(Charity charity, Event event) {

        EventEvent eventEvent =
            new EventEvent(ctx, charity, event, IndexerEventType.DELETE);
        ctx.publishEvent(eventEvent);
    }

    /**
     * {@inheritDoc}
     */
    public void publishEventSavedEvent(Charity charity, Event event) {

        EventEvent eventEvent =
            new EventEvent(ctx, charity, event, IndexerEventType.UPDATE);
        ctx.publishEvent(eventEvent);
    }

}
