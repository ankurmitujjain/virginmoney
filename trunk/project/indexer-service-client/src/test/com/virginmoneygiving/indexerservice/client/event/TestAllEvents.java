package com.virginmoneygiving.indexerservice.client.event;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.virginmoneygiving.giving.domain.Charity;
import com.virginmoneygiving.giving.domain.Event;
import com.virginmoneygiving.giving.domain.Fundraiser;
import com.virginmoneygiving.giving.domain.FundraiserActivity;

/**
 * Test the event classes.
 * 
 * @author ian.priest@opsera.com
 */
public class TestAllEvents {

    /** The mock application context. */
    ApplicationContext mockApplicationContext = null;
    
    /**
     * Sets the mock object for testing.
     */
    @Before
    public void setUp() {
        mockApplicationContext = mock(ApplicationContext.class);
    }
    
    /**
     * Test charity event.
     */
    @Test
    public void testCharityEvent() {
        Charity charity = new Charity();
        
        CharityEvent deleteCharityEvent = new CharityEvent(mockApplicationContext, charity, IndexerEventType.DELETE);
        assertEquals(mockApplicationContext, deleteCharityEvent.getApplicationContext());
        assertEquals(charity, deleteCharityEvent.getCharity());
        assertEquals(IndexerEventType.DELETE, deleteCharityEvent.getEventType());

        CharityEvent updateCharityEvent = new CharityEvent(mockApplicationContext, charity, IndexerEventType.UPDATE);
        assertEquals(mockApplicationContext, updateCharityEvent.getApplicationContext());
        assertEquals(charity, updateCharityEvent.getCharity());
        assertEquals(IndexerEventType.UPDATE, updateCharityEvent.getEventType());
  }

    /**
     * Test event event.
     */
    @Test
    public void testEventEvent() {
        Charity charity = new Charity();
        Event event = new Event();
        
        EventEvent deleteEventEvent = new EventEvent(mockApplicationContext, charity, event, IndexerEventType.DELETE);
        assertEquals(mockApplicationContext, deleteEventEvent.getApplicationContext());
        assertEquals(charity, deleteEventEvent.getCharity());
        assertEquals(event, deleteEventEvent.getEvent());
        assertEquals(IndexerEventType.DELETE, deleteEventEvent.getEventType());

        EventEvent updateEventEvent = new EventEvent(mockApplicationContext, charity, event, IndexerEventType.UPDATE);
        assertEquals(mockApplicationContext, updateEventEvent.getApplicationContext());
        assertEquals(charity, updateEventEvent.getCharity());
        assertEquals(event, updateEventEvent.getEvent());
        assertEquals(IndexerEventType.UPDATE, updateEventEvent.getEventType());
  }

    /**
     * Test fundraiser activity event.
     */
    @Test
    public void testFundraiserActivityEvent() {
        FundraiserActivity fundraiserActivity = new FundraiserActivity();
        Fundraiser fundraiser = new Fundraiser();
        
        FundraiserActivityEvent deleteFundraiserActivityEvent = new FundraiserActivityEvent(mockApplicationContext, fundraiser, fundraiserActivity, IndexerEventType.DELETE);
        assertEquals(mockApplicationContext, deleteFundraiserActivityEvent.getApplicationContext());
        assertEquals(fundraiserActivity, deleteFundraiserActivityEvent.getFundraiserActivity());
        assertEquals(fundraiser, deleteFundraiserActivityEvent.getFundraiser());
        assertEquals(IndexerEventType.DELETE, deleteFundraiserActivityEvent.getEventType());

        FundraiserActivityEvent updateFundriaserActivityEvent = new FundraiserActivityEvent(mockApplicationContext, fundraiser, fundraiserActivity, IndexerEventType.UPDATE);
        assertEquals(mockApplicationContext, updateFundriaserActivityEvent.getApplicationContext());
        assertEquals(fundraiserActivity, deleteFundraiserActivityEvent.getFundraiserActivity());
        assertEquals(fundraiser, deleteFundraiserActivityEvent.getFundraiser());
        assertEquals(IndexerEventType.UPDATE, updateFundriaserActivityEvent.getEventType());
  }
}
