package com.virginmoneygiving.indexerservice.jms.impl;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.virginmoneygiving.indexerservice.messages.DeleteFromIndexMessage;
import com.virginmoneygiving.indexerservice.messages.IndexedType;
import com.virginmoneygiving.indexerservice.messages.IndexerService;
import com.virginmoneygiving.indexerservice.messages.IndexerUpdate;
import com.virginmoneygiving.indexerservice.messages.UpdateInIndexMessage;

/**
 * Test Class for DefaultIndexerMessageDelegate.
 * 
 * @author Ian Priest
 */
public class DefaultIndexerMessageDelegateTest {

	/**
	 * The Class EqualsIndexerUpdate.
	 */
	class EqualsIndexerUpdate extends ArgumentMatcher<IndexerUpdate> {

	    /** The expected. */
    	IndexerUpdate expected = null;
		
		/**
		 * Instantiates a new equals indexer update.
		 * 
		 * @param expected the expected
		 */
		public EqualsIndexerUpdate(IndexerUpdate expected) {
			this.expected = expected;
		}
		
		/* (non-Javadoc)
		 * @see org.mockito.ArgumentMatcher#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(Object actual) {
			
			if ( !(actual instanceof IndexerUpdate) ) {
				return false;
			}
			
			return expected.getId().equals(((IndexerUpdate)actual).getId()) &&
					expected.getName().equals(((IndexerUpdate)actual).getName());
		}
		
	}

    /** The Constant ID. */
    private static final String ID = "345";

    /** The Constant NAME. */
    private static final String NAME = "Name";
	
	/** The default delegate. */
	private DefaultIndexerMessageDelegate defaultDelegate = null;
	
	/** The mock indexer service. */
	private IndexerService mockIndexerService = null;
	
	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		defaultDelegate = new DefaultIndexerMessageDelegate();
		mockIndexerService = mock(IndexerService.class);
		defaultDelegate.setIndexerService(mockIndexerService);
	}

	/**
	 * Test handle null message.
	 */
	@Test
	public void testHandleNullMessage() {
		// Try null
	    try {
	        defaultDelegate.handleMessage(null);
	        fail("Expected NPE");
	    }
	    catch ( NullPointerException npe) {
	        // expected
	    }
		
		verify(mockIndexerService, never()).updateInIndexMessage((IndexerUpdate) anyObject());
		verify(mockIndexerService, never()).deleteFromIndexMessage((IndexerUpdate) anyObject());
	}

	/**
	 * Test handle invalid message.
	 */
	@Test
	public void testHandleInvalidMessage() {
		// Try an invalid object
		XStream xStream = new XStream(new DomDriver());
		String xml = xStream.toXML(new String("I'm a test"));
		defaultDelegate.handleMessage(xml);
		
        verify(mockIndexerService, never()).updateInIndexMessage((IndexerUpdate) anyObject());
        verify(mockIndexerService, never()).deleteFromIndexMessage((IndexerUpdate) anyObject());
	}

	/**
	 * Test charity added.
	 */
	@Test
	public void testCharityAdded() {
		
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);
		UpdateInIndexMessage message = new UpdateInIndexMessage();
		message.setIndexerUpdate(indexerUpdate);
		
		XStream xStream = new XStream(new DomDriver());
		String xml = xStream.toXML(message);
		defaultDelegate.handleMessage(xml);
		
		verify(mockIndexerService).updateInIndexMessage(argThat(new EqualsIndexerUpdate(indexerUpdate)));
        verify(mockIndexerService, never()).deleteFromIndexMessage((IndexerUpdate) anyObject());
	}

	/**
	 * Test charity deleted.
	 */
	@Test
	public void testCharityDeleted() {
		
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);
        DeleteFromIndexMessage message = new DeleteFromIndexMessage();
        message.setIndexerUpdate(indexerUpdate);
		
		XStream xStream = new XStream(new DomDriver());
		String xml = xStream.toXML(message);
		defaultDelegate.handleMessage(xml);
		
        verify(mockIndexerService, never()).updateInIndexMessage((IndexerUpdate) anyObject());
        verify(mockIndexerService).deleteFromIndexMessage(argThat(new EqualsIndexerUpdate(indexerUpdate)));
	}

}
