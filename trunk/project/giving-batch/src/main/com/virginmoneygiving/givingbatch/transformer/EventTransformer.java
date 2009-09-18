package com.virginmoneygiving.givingbatch.transformer;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.givingbatch.domain.EventSummary;

/**
 * Transfer Event object to EventSummary Object so all event reacord can be
 * written into xml file.
 * 
 * @author dibaskumarp
 */
public class EventTransformer implements ItemProcessor<Event, EventSummary> {

    /** Create instance of Logger to show Log information. */
    private static Logger LOGGER = Logger.getLogger(EventTransformer.class);

    /**
     * This method used to process the event details.
     * 
     * @param event of type Event
     * 
     * @return EventSummary object
     * 
     * @throws Exception    
     */
    public EventSummary process(Event event) {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventTransformer - process - Start");
        }
        EventSummary eventSummary = new EventSummary();
        eventSummary.setId(event.getId());
        eventSummary.setName(event.getName());
        eventSummary.setDescription(event.getDescription());
        eventSummary.setEventDatetime(event.getEventDatetime());
        eventSummary.setEventTimeText(event.getEventTimeText());
        eventSummary.setOpenDate(event.getOpenDate());
        eventSummary.setExpiryDate(event.getExpiryDate());
        eventSummary.setLocation(event.getLocation().getDescription());
        if ("PEN".equals(event.getEventStatus().getCode())) {
            eventSummary.setEventStatus("Publish");
        } else if ("PUB".equals(event.getEventStatus().getCode())) {
            eventSummary.setEventStatus("Expired");
        } else {
            eventSummary.setEventStatus(event.getEventStatus().getDescription());
        }        
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Inside EventTransformer - process - End");
        }
        return eventSummary;
    }

}
