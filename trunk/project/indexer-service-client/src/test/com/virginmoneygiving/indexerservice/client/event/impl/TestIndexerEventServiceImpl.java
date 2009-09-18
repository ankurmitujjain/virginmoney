package com.virginmoneygiving.indexerservice.client.event.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;
import com.virginmoneygiving.indexerservice.client.event.CharityEvent;
import com.virginmoneygiving.indexerservice.client.event.EventEvent;
import com.virginmoneygiving.indexerservice.client.event.FundraiserActivityEvent;
import com.virginmoneygiving.indexerservice.client.event.IndexerEventType;

/**
 * The Test Class for IndexerEventServiceImpl.
 * 
 * @author ian.priest@opsera.com
 */
public class TestIndexerEventServiceImpl {

    /**
     * The Class IsExpectedFundraiserActivityEvent.
     */
    class IsExpectedFundraiserActivityEvent extends ArgumentMatcher<FundraiserActivityEvent> {
        
        /** The application context. */
        ApplicationContext applicationContext;
        
        /** The fundraiser. */
        Fundraiser fundraiser;
        
        /** The fundraiser activity. */
        FundraiserActivity fundraiserActivity;
        
        /** The event type. */
        IndexerEventType eventType;
        
        /**
         * Instantiates a new checks if is expected fundraiser activity event.
         * 
         * @param applicationContext the application context
         * @param fundraiser the fundraiser
         * @param fundraiserActivity the fundraiser activity
         * @param eventType the event type
         */
        public IsExpectedFundraiserActivityEvent(ApplicationContext applicationContext, Fundraiser fundraiser, 
            FundraiserActivity fundraiserActivity, IndexerEventType eventType ) {
            
            this.applicationContext = applicationContext;
            this.fundraiser = fundraiser;
            this.fundraiserActivity = fundraiserActivity;
            this.eventType = eventType;
        }
        
        /**
         * Will throw assertion exception if no match.
         * 
         * @param object the object
         * 
         * @return true, if matches
         */
        public boolean matches(Object object) {
            
            FundraiserActivityEvent fundraiserActivityEvent = (FundraiserActivityEvent) object;
            
            assertEquals(mockApplicationContext, fundraiserActivityEvent.getApplicationContext());
            assertEquals(fundraiserActivity, fundraiserActivityEvent.getFundraiserActivity());
            assertEquals(fundraiser, fundraiserActivityEvent.getFundraiser());
            assertEquals(eventType, fundraiserActivityEvent.getEventType());
            
            return true;
        }
     }

    /**
     * The Class IsExpectedEventEvent.
     */
    class IsExpectedEventEvent extends ArgumentMatcher<EventEvent> {
        
        /** The application context. */
        ApplicationContext applicationContext;
        
        /** The charity. */
        Charity charity;
        
        /** The event. */
        Event event;
        
        /** The event type. */
        IndexerEventType eventType;
        
        /**
         * Instantiates a new checks if is expected event event.
         * 
         * @param applicationContext the application context
         * @param charity the charity
         * @param event the event
         * @param eventType the event type
         */
        public IsExpectedEventEvent(ApplicationContext applicationContext, Charity charity, 
            Event event, IndexerEventType eventType ) {
            
            this.applicationContext = applicationContext;
            this.event = event;
            this.charity = charity;
            this.eventType = eventType;
        }
        
        /**
         * Will throw assertion exception if no match.
         * 
         * @param object the object
         * 
         * @return true, if matches
         */
        public boolean matches(Object object) {
            
            EventEvent eventEvent = (EventEvent) object;
                        
            assertEquals(mockApplicationContext, eventEvent.getApplicationContext());
            assertEquals(charity, eventEvent.getCharity());
            assertEquals(event, eventEvent.getEvent());
            assertEquals(eventType, eventEvent.getEventType());
            
            return true;
        }
     }

    /**
     * The Class IsExpectedCharityEvent.
     */
    class IsExpectedCharityEvent extends ArgumentMatcher<CharityEvent> {
        
        /** The application context. */
        ApplicationContext applicationContext;
        
        /** The charity. */
        Charity charity;
        
        /** The event type. */
        IndexerEventType eventType;
        
        /**
         * Instantiates a new checks if is expected charity event.
         * 
         * @param applicationContext the application context
         * @param charity the charity
         * @param eventType the event type
         */
        public IsExpectedCharityEvent(ApplicationContext applicationContext, Charity charity, 
            IndexerEventType eventType ) {
            
            this.applicationContext = applicationContext;
            this.charity = charity;
            this.eventType = eventType;
        }
        
        /**
         * Will throw assertion exception if no match.
         * 
         * @param object the object
         * 
         * @return true, if matches
         */
        public boolean matches(Object object) {
            
            CharityEvent charityEvent = (CharityEvent) object;
                        
            assertEquals(mockApplicationContext, charityEvent.getApplicationContext());
            assertEquals(charity, charityEvent.getCharity());
            assertEquals(eventType, charityEvent.getEventType());
            
            return true;
        }
     }

    /** The indexer event service impl. */
    private IndexerEventServiceImpl indexerEventServiceImpl = null;
    
    /** The mock application context. */
    private ApplicationContext mockApplicationContext = null;
    
    /**
     * Sets the up.
     * 
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        mockApplicationContext = mock(ApplicationContext.class);
        
        indexerEventServiceImpl = new IndexerEventServiceImpl();
        indexerEventServiceImpl.setApplicationContext(mockApplicationContext);
    }

    /**
     * Test publish fundraiser activity deleted event.
     */
    @Test
    public void testPublishFundraiserActivityDeletedEvent() {

        FundraiserActivity fundraiserActivity = new FundraiserActivity();
        Fundraiser fundraiser = new Fundraiser();
        
        indexerEventServiceImpl.publishFundraiserActivityDeletedEvent(fundraiser, fundraiserActivity);
        IsExpectedFundraiserActivityEvent isExpectedFundraiserActivityEvent = 
            new IsExpectedFundraiserActivityEvent(mockApplicationContext, 
                fundraiser,
                fundraiserActivity,
                IndexerEventType.DELETE);
        verify(mockApplicationContext).publishEvent((ApplicationEvent)argThat(isExpectedFundraiserActivityEvent));
    }

    /**
     * Test publish fundraiser activity saved event.
     */
    @Test
    public void testPublishFundraiserActivitySavedEvent() {

        FundraiserActivity fundraiserActivity = new FundraiserActivity();
        Fundraiser fundraiser = new Fundraiser();
        
        indexerEventServiceImpl.publishFundraiserActivitySavedEvent(fundraiser, fundraiserActivity);
        IsExpectedFundraiserActivityEvent isExpectedFundraiserActivityEvent = 
            new IsExpectedFundraiserActivityEvent(mockApplicationContext, 
                fundraiser,
                fundraiserActivity,
                IndexerEventType.UPDATE);
        verify(mockApplicationContext).publishEvent((ApplicationEvent)argThat(isExpectedFundraiserActivityEvent));
    }

    /**
     * Test publish charity saved event.
     */
    @Test
    public void testPublishCharitySavedEvent() {
        Charity charity = new Charity();
        
        indexerEventServiceImpl.publishCharitySavedEvent(charity);
        IsExpectedCharityEvent isExpectedCharityEvent = 
            new IsExpectedCharityEvent(mockApplicationContext, 
                charity,
                IndexerEventType.UPDATE);
        verify(mockApplicationContext).publishEvent((ApplicationEvent)argThat(isExpectedCharityEvent));
    }

    /**
     * Test publish charity deleted event.
     */
    @Test
    public void testPublishCharityDeletedEvent() {

        Charity charity = new Charity();
        
        indexerEventServiceImpl.publishCharityDeletedEvent(charity);
        IsExpectedCharityEvent isExpectedCharityEvent = 
            new IsExpectedCharityEvent(mockApplicationContext, 
                charity,
                IndexerEventType.DELETE);
        verify(mockApplicationContext).publishEvent((ApplicationEvent)argThat(isExpectedCharityEvent));
    }

    /**
     * Test publish event deleted event.
     */
    @Test
    public void testPublishEventDeletedEvent() {

        Event event = new Event();
        Charity charity = new Charity();
        
        indexerEventServiceImpl.publishEventDeletedEvent(charity, event);
        IsExpectedEventEvent isExpectedEventEvent = 
            new IsExpectedEventEvent(mockApplicationContext, 
                charity,
                event,
                IndexerEventType.DELETE);
        verify(mockApplicationContext).publishEvent((ApplicationEvent)argThat(isExpectedEventEvent));
    }

    /**
     * Test publish event saved event.
     */
    @Test
    public void testPublishEventSavedEvent() {

        Charity charity = new Charity();
        Event event = new Event();
        
        indexerEventServiceImpl.publishEventSavedEvent(charity, event);
        IsExpectedEventEvent isExpectedEventEvent = 
            new IsExpectedEventEvent(mockApplicationContext, 
                charity,
                event,
                IndexerEventType.UPDATE);
        verify(mockApplicationContext).publishEvent((ApplicationEvent)argThat(isExpectedEventEvent));
    }

}
