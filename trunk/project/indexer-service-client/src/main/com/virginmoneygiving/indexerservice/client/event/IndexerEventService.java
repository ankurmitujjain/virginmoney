package com.virginmoneygiving.indexerservice.client.event;

import com.virginmoneygiving.giving.domain.CharitableActivity;
import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;

/**
 * Simple service to raise Spring ApplicationEvent notifications.
 *
 * @author ian.priest@opsera.com
 *
 */
public interface IndexerEventService {

    /**
     * Index a new or updated active FundraiserActivity.
     *
     * Passes the activity to the indexer-service so that searches will find it.
     * @param fundraiser TODO
     * @param fundraiserActivity
     *            the FundraiserActivity that has finished setup
     */
    void publishFundraiserActivitySavedEvent(
        Fundraiser fundraiser, FundraiserActivity fundraiserActivity);

    /**
     * Remove a FundraiserActivity from the search index.
     * @param fundraiser TODO
     * @param fundraiserActivity
     *            The activity to remove.
     */
    void publishFundraiserActivityDeletedEvent(
        Fundraiser fundraiser, FundraiserActivity fundraiserActivity);

    /**
     * Index a new or updated Event.
     *
     * Passes the Event to the indexer service so that searches will find it.
     * @param charitableActivity TODO
     * @param event
     *            The Event
     */
    void publishEventSavedEvent(Charity charity, Event event);

    /**
     * Delete an Event from the index-service index so it won't show up in
     * searches.
     * @param charitableActivity 
     *			Details of the charity no longer taking part in the event
     * @param event
     *            The Event to remove from the index.
     */
    void publishEventDeletedEvent(Charity charity, Event event);

    /**
     * Index a new or updated Charity.
     *
     * Passes the Charity to the indexer service so that searches will find it.
     *
     * @param charity
     *            The Charity
     */
    void publishCharitySavedEvent(Charity charity);

    /**
     * Delete a Charity from the index-service index so it won't show up in
     * searches.
     *
     * @param charity
     *            The Charity to remove from the index.
     */
    void publishCharityDeletedEvent(Charity charity);
}
