package com.virginmoneygiving.indexerservice.solr;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import com.virginmoneygiving.indexerservice.messages.IndexedType;
import com.virginmoneygiving.indexerservice.messages.IndexerCharity;
import com.virginmoneygiving.indexerservice.messages.IndexerUpdate;
import com.virginmoneygiving.indexerservice.solr.SolrIndexerService;

/**
 * Test Class for SolrIndexerService.
 * 
 * @author Ian Priest
 */
public class SolrIndexerServiceTest {

	/** The Constant EXCEPTION_MESSAGE. */
	private static final String EXCEPTION_MESSAGE = "Test exception should be caught";
	
	/** The Constant ID. */
	private static final String ID = "357";
	
	/** The Constant NAME. */
	private static final String NAME = "RSPCA";
	
	/** The indexer service. */
	private SolrIndexerService indexerService = null;
	
	/** The mock solr server. */
	private SolrServer mockSolrServer = null;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		indexerService = new SolrIndexerService();
		mockSolrServer = mock(SolrServer.class);
		indexerService.setSolrServer(mockSolrServer);
	}

	/**
	 * Test get compound id charity basic.
	 */
	@Test
	public void testGetCompoundIdCharityBasic() {

	    String expectedId = "{" + ID + "}" + IndexedType.CHARITY.value();
	    
	    IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);
	    
        QName id = ((SolrIndexerService)indexerService).getCompoundId(indexerUpdate);
        assertEquals(expectedId, id.toString());
        
	}
	
    /**
     * Test get compound id fundraiser basic.
     */
    @Test
    public void testGetCompoundIdFundraiserBasic() {

        String expectedId = "{" + ID + "}" + IndexedType.FUNDRAISER_ACTIVITY.value();
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.FUNDRAISER_ACTIVITY);
        
        QName id = ((SolrIndexerService)indexerService).getCompoundId(indexerUpdate);
        assertEquals(expectedId, id.toString());
        
    }

    /**
     * Test get compound id fundraiser team.
     */
    @Test
    public void testGetCompoundIdFundraiserTeam() {

        String expectedId = "{" + ID + "}" + IndexedType.FUNDRAISER_ACTIVITY.value() + "-" + ID;
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setFundraiserId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.FUNDRAISER_ACTIVITY);
        
        QName id = ((SolrIndexerService)indexerService).getCompoundId(indexerUpdate);
        assertEquals(expectedId, id.toString());
        
    }

    /**
     * Test get compound id event open.
     */
    @Test
    public void testGetCompoundIdEventOpen() {

        String expectedId = "{" + ID + "}" + IndexedType.EVENT.value();
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.EVENT);
        
        QName id = ((SolrIndexerService)indexerService).getCompoundId(indexerUpdate);
        assertEquals(expectedId, id.toString());
        
    }

    /**
     * Test get compound id event charity.
     */
    @Test
    public void testGetCompoundIdEventCharity() {

        String expectedId = "{" + ID + "}" + IndexedType.EVENT.value();
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.EVENT);
        
        QName id = ((SolrIndexerService)indexerService).getCompoundId(indexerUpdate);
        assertEquals(expectedId, id.toString());
        
    }

    /**
     * Test update in index message is null.
     */
    @Test
	public void testUpdateInIndexMessageIsNull() {
		
		try {
			indexerService.updateInIndexMessage(null);
			fail("Expected NullPointerException");
		}
		catch ( NullPointerException npe ) {
			// Expected
		}
	
	}

	/**
	 * Test update in index message throws solr server exception.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdateInIndexMessageThrowsSolrServerException() throws Exception {
		
		IndexerUpdate indexerUpdate = new IndexerUpdate();
		indexerUpdate.setId(ID);
		indexerUpdate.setName(NAME);
		indexerUpdate.setType(IndexedType.CHARITY);

		when(mockSolrServer.add((SolrInputDocument) anyObject()))
			.thenThrow(new SolrServerException(EXCEPTION_MESSAGE));

		try {
			indexerService.updateInIndexMessage(indexerUpdate);
			fail("Expected RuntimeException to be thrown");
		}
		catch ( RuntimeException e ) {
			Throwable cause = e.getCause();
			assertNotNull(cause);
			assertTrue( cause instanceof SolrServerException );
			assertEquals( EXCEPTION_MESSAGE, cause.getMessage() );
		}
	}

	/**
	 * Test update in indexer message throws io exception.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdateInIndexerMessageThrowsIOException() throws Exception {
		
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);

		when(mockSolrServer.add((SolrInputDocument) anyObject()))
			.thenThrow(new IOException(EXCEPTION_MESSAGE));

		try {
			indexerService.updateInIndexMessage(indexerUpdate);
			fail("Expected RuntimeException to be thrown");
		}
		catch ( RuntimeException e ) {
			Throwable cause = e.getCause();
			assertNotNull(cause);
			assertTrue( cause instanceof IOException );
			assertEquals( EXCEPTION_MESSAGE, cause.getMessage() );
		}
	}

	/**
	 * Test delete from index message null.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testDeleteFromIndexMessageNull() throws Exception {

	    try {
    		indexerService.deleteFromIndexMessage(null);
            fail("Expected NullPointerException");
        }
        catch ( NullPointerException npe ) {
            // Expected
        }
	}

	/**
	 * Test delete from index message throws solr server exception.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testDeleteFromIndexMessageThrowsSolrServerException() throws Exception {

	    IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);
		
        QName expectedId = new QName(ID, IndexedType.CHARITY.value());
		when(mockSolrServer.deleteById(expectedId.toString()))
			.thenThrow(new SolrServerException(EXCEPTION_MESSAGE));

		try {
			indexerService.deleteFromIndexMessage(indexerUpdate);
			fail("Expected RuntimeException to be thrown");
		}
		catch ( RuntimeException e ) {
			Throwable cause = e.getCause();
			assertNotNull(cause);
			assertTrue( cause instanceof SolrServerException );
			assertEquals( EXCEPTION_MESSAGE, cause.getMessage() );
		}
	}

	/**
	 * Test delete from index message throws io exception.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testDeleteFromIndexMessageThrowsIOException() throws Exception {
		
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);

        QName expectedId = new QName(ID, IndexedType.CHARITY.value());
        when(mockSolrServer.deleteById(expectedId.toString()))
			.thenThrow(new IOException(EXCEPTION_MESSAGE));

		try {
			indexerService.deleteFromIndexMessage(indexerUpdate);
			fail("Expected RuntimeException to be thrown");
		}
		catch ( RuntimeException e ) {
			Throwable cause = e.getCause();
			assertNotNull(cause);
			assertTrue( cause instanceof IOException );
			assertEquals( EXCEPTION_MESSAGE, cause.getMessage() );
		}
	}

    /**
     * Test update in index message with charity.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testUpdateInIndexMessageWithCharity() throws Exception {
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);

        indexerService.updateInIndexMessage(indexerUpdate);
        
        verify(mockSolrServer).add((SolrInputDocument) anyObject());        
    }

    /**
     * Test update in index message with fundraiser activity.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testUpdateInIndexMessageWithFundraiserActivity() throws Exception {
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.FUNDRAISER_ACTIVITY);

        indexerService.updateInIndexMessage(indexerUpdate);
        
        verify(mockSolrServer).add((SolrInputDocument) anyObject());        
    }

    /**
     * Test update in index message with open event.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testUpdateInIndexMessageWithOpenEvent() throws Exception {
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.EVENT);

        indexerService.updateInIndexMessage(indexerUpdate);
        
        verify(mockSolrServer).add((SolrInputDocument) anyObject());        
    }

    /**
     * Test update in index message with charity event.
     * 
     * @throws Exception the exception
     */
    @Test
    public void testUpdateInIndexMessageWithCharityEvent() throws Exception {
        
        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.EVENT);
        
        IndexerCharity indexerCharity = new IndexerCharity();
        indexerCharity.setId(ID);
        indexerCharity.setName(NAME);
        indexerUpdate.getCharities().add(indexerCharity);

        indexerService.updateInIndexMessage(indexerUpdate);
        
        verify(mockSolrServer).add((SolrInputDocument) anyObject());        
    }

    /**
     * Only need to test one type as path is the same fro all types.
     * 
     * @throws Exception if something goes wrong
     */
    @Test
	public void testDeleteFromIndexMessageWithCharity() throws Exception {

        IndexerUpdate indexerUpdate = new IndexerUpdate();
        indexerUpdate.setId(ID);
        indexerUpdate.setName(NAME);
        indexerUpdate.setType(IndexedType.CHARITY);

        indexerService.deleteFromIndexMessage(indexerUpdate);
        
        QName expectedId = new QName(ID, IndexedType.CHARITY.value());
		verify(mockSolrServer).deleteById(expectedId.toString());		
	}

}
