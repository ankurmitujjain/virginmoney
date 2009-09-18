package com.virginmoneygiving.indexerservice.client.listener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.virginmoneygiving.indexerservice.client.event.CharityEvent;
import com.virginmoneygiving.indexerservice.client.event.EventEvent;
import com.virginmoneygiving.indexerservice.client.event.FundraiserActivityEvent;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventType;
import com.virginmoneygiving.indexerservice.client.jms.IndexerJmsSender;

/**
 * Listener that responds to IndexerEvents events by sending a JMS message to the
 * Indexer Service.
 *
 * Ensure that fundraiser-activities, charities and events are indexed for searching
 *
 * @author ipriest
 */
public class IndexerApplicationEventListener implements ApplicationListener {

    /** Log messages. */
	private static final Logger LOGGER = Logger.getLogger(IndexerApplicationEventListener.class);
	
	/** The JMS Sender. */
    private IndexerJmsSender jmsQueueSender;
	
    /**
     * Method called when an application event is raised.
     *
     * @param event the evnt raised
     */
	public void onApplicationEvent(ApplicationEvent event) {

		if(event instanceof FundraiserActivityEvent) {
			
			LOGGER.debug("FundraiserActivityEvent has been raised.");
			FundraiserActivityEvent fundraiserActivityEvent = (FundraiserActivityEvent)event;
			
			if ( fundraiserActivityEvent.getEventType() == IndexerEventType.UPDATE ) {
			    jmsQueueSender.sendFundraiserActivitySavedMessage(fundraiserActivityEvent.getFundraiser(), fundraiserActivityEvent.getFundraiserActivity());
			}
			else if ( fundraiserActivityEvent.getEventType() == IndexerEventType.DELETE ) {
                jmsQueueSender.sendFundraiserActivityDeletedMessage(fundraiserActivityEvent.getFundraiser(), fundraiserActivityEvent.getFundraiserActivity());
            }
		}
		else if(event instanceof CharityEvent) {
            
            LOGGER.debug("CharityEvent has been raised.");
            CharityEvent charityEvent = (CharityEvent)event;
            
            if ( charityEvent.getEventType() == IndexerEventType.UPDATE ) {
                jmsQueueSender.sendCharitySavedMessage(charityEvent.getCharity());
            }
            else if ( charityEvent.getEventType() == IndexerEventType.DELETE ) {
                jmsQueueSender.sendCharityDeletedMessage(charityEvent.getCharity());
            }
        }
        if(event instanceof EventEvent) {
            
            LOGGER.debug("EventEvent has been raised.");
            EventEvent eventEvent = (EventEvent)event;
            
            if ( eventEvent.getEventType() == IndexerEventType.UPDATE ) {
                jmsQueueSender.sendEventSavedMessage(eventEvent.getCharity(), eventEvent.getEvent());
            }
            else if ( eventEvent.getEventType() == IndexerEventType.DELETE ) {
                jmsQueueSender.sendEventDeletedMessage(eventEvent.getCharity(), eventEvent.getEvent());
            }
        }
	
	}

    @Resource
    public void setJmsQueueSender(IndexerJmsSender jmsQueueSender) {
        this.jmsQueueSender = jmsQueueSender;
    }
}
	
